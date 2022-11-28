package org.dbsyncer.storage.binlog;

import com.google.protobuf.InvalidProtocolBufferException;
import org.dbsyncer.common.config.BinlogRecorderConfig;
import org.dbsyncer.common.model.Paging;
import org.dbsyncer.common.scheduled.ScheduledTaskJob;
import org.dbsyncer.common.scheduled.ScheduledTaskService;
import org.dbsyncer.common.snowflake.SnowflakeIdWorker;
import org.dbsyncer.common.util.CollectionUtils;
import org.dbsyncer.storage.StorageService;
import org.dbsyncer.storage.binlog.proto.BinlogMessage;
import org.dbsyncer.storage.constant.BinlogConstant;
import org.dbsyncer.storage.constant.ConfigConstant;
import org.dbsyncer.storage.enums.StorageEnum;
import org.dbsyncer.storage.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author AE86
 * @version 1.0.0
 * @date 2022/11/25 0:53
 */
public abstract class AbstractBinlogService<Message> implements BinlogRecorder {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private StorageService storageService;

    @Autowired
    private ScheduledTaskService scheduledTaskService;

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    @Autowired
    private BinlogRecorderConfig binlogRecorderConfig;

    private Queue<BinlogMessage> queue;

    private WriterTask writerTask = new WriterTask();

    private ReaderTask readerTask = new ReaderTask();

    @PostConstruct
    private void init() {
        queue = new LinkedBlockingQueue(binlogRecorderConfig.getQueueCapacity());
        scheduledTaskService.start(binlogRecorderConfig.getWriterPeriodMillisecond(), writerTask);
        scheduledTaskService.start(binlogRecorderConfig.getReaderPeriodMillisecond(), readerTask);
    }

    /**
     * 反序列化消息
     *
     * @param message
     * @return
     */
    protected abstract Message deserialize(String messageId, BinlogMessage message);

    @Override
    public void flush(BinlogMessage message) {
        queue.offer(message);
    }

    @Override
    public void complete(List<String> messageIds) {
        if (!CollectionUtils.isEmpty(messageIds)) {
            storageService.removeBatch(StorageEnum.BINLOG, messageIds);
        }
    }

    /**
     * 合并缓存队列任务到磁盘
     */
    final class WriterTask implements ScheduledTaskJob {

        @Override
        public void run() {
            if (queue.isEmpty()) {
                return;
            }

            List<Map> tasks = new ArrayList<>();
            int count = 0;
            long now = Instant.now().toEpochMilli();
            Map task = null;
            while (!queue.isEmpty() && count < binlogRecorderConfig.getBatchCount()) {
                BinlogMessage message = queue.poll();
                if (null != message) {
                    task = new HashMap();
                    task.put(ConfigConstant.CONFIG_MODEL_ID, String.valueOf(snowflakeIdWorker.nextId()));
                    task.put(ConfigConstant.BINLOG_STATUS, BinlogConstant.READY);
                    task.put(ConfigConstant.CONFIG_MODEL_JSON, message.toByteArray());
                    task.put(ConfigConstant.CONFIG_MODEL_CREATE_TIME, now);
                    tasks.add(task);
                }
                count++;
            }

            if (!CollectionUtils.isEmpty(tasks)) {
                storageService.addBatch(StorageEnum.BINLOG, tasks);
            }
            tasks = null;
        }
    }

    /**
     * 从磁盘读取日志到任务队列
     */
    final class ReaderTask implements ScheduledTaskJob {

        private final Lock lock = new ReentrantLock(true);

        private volatile boolean running;

        @Override
        public void run() {
            // 读取任务数 >= 1/2缓存同步队列容量则继续等待
            if (running || binlogRecorderConfig.getBatchCount() + getQueue().size() >= getQueueCapacity() / 2) {
                return;
            }

            final Lock binlogLock = lock;
            boolean locked = false;
            try {
                locked = binlogLock.tryLock();
                if (locked) {
                    running = true;
                    doParse();
                }
            } catch (Exception e) {
                logger.error(e.getMessage());
            } finally {
                if (locked) {
                    running = false;
                    binlogLock.unlock();
                }
            }
        }

        private void doParse() {
            //  TODO 查询[待处理] 或 [处理中 & 处理超时]
            Query query = new Query();
            query.setType(StorageEnum.BINLOG);
            query.addFilter(ConfigConstant.BINLOG_STATUS, String.valueOf(BinlogConstant.READY));
            query.setPageNum(1);
            query.setPageSize(binlogRecorderConfig.getBatchCount());
            Paging paging = storageService.query(query);
            if (CollectionUtils.isEmpty(paging.getData())) {
                return;
            }

            List<Map> list = (List<Map>) paging.getData();
            final int size = list.size();
            final List<Message> messages = new ArrayList<>(size);
            final List<Map> updateTasks = new ArrayList<>(size);
            boolean existProcessing = false;
            for (int i = 0; i < size; i++) {
                Map row = list.get(i);
                String id = (String) row.get(ConfigConstant.CONFIG_MODEL_ID);
                Integer status = (Integer) row.get(ConfigConstant.BINLOG_STATUS);
                byte[] bytes = (byte[]) row.get(ConfigConstant.CONFIG_MODEL_JSON);
                if (BinlogConstant.PROCESSING == status) {
                    existProcessing = true;
                }
                try {
                    Message message = deserialize(id, BinlogMessage.parseFrom(bytes));
                    if (null != message) {
                        messages.add(message);
                        row.put(ConfigConstant.CONFIG_MODEL_CREATE_TIME, Instant.now().toEpochMilli());
                        updateTasks.add(row);
                    }
                } catch (InvalidProtocolBufferException e) {
                    logger.error(e.getMessage());
                }
            }
            if (existProcessing) {
                logger.warn("存在超时未处理数据，正在重试，建议优化配置参数[max-processing-seconds={}].", binlogRecorderConfig.getMaxProcessingSeconds());
            }

            // 如果在更新消息状态的过程中服务被中止，为保证数据的安全性，重启后消息可能会同步2次）
            storageService.editBatch(StorageEnum.BINLOG, updateTasks);
            getQueue().addAll(messages);
        }
    }

}