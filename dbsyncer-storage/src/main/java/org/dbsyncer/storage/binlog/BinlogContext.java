package org.dbsyncer.storage.binlog;

import org.apache.commons.io.FileUtils;
import org.dbsyncer.common.scheduled.ScheduledTaskJob;
import org.dbsyncer.common.util.CollectionUtils;
import org.dbsyncer.common.util.JsonUtil;
import org.dbsyncer.common.util.NumberUtil;
import org.dbsyncer.common.util.StringUtil;
import org.dbsyncer.storage.binlog.impl.BinlogPipeline;
import org.dbsyncer.storage.binlog.impl.BinlogWriter;
import org.dbsyncer.storage.binlog.proto.BinlogMessage;
import org.dbsyncer.storage.model.BinlogConfig;
import org.dbsyncer.storage.model.BinlogIndex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class BinlogContext implements ScheduledTaskJob, Closeable {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final long BINLOG_MAX_SIZE = 256 * 1024 * 1024;

    private static final int BINLOG_EXPIRE_DAYS = 7;

    private static final String LINE_SEPARATOR = System.lineSeparator();

    private static final Charset DEFAULT_CHARSET = Charset.defaultCharset();

    private static final String BINLOG = "binlog";

    private static final String BINLOG_INDEX = BINLOG + ".index";

    private static final String BINLOG_CONFIG = BINLOG + ".config";

    private final List<BinlogIndex> indexList = new LinkedList<>();

    private final String path;

    private final File configFile;

    private final File indexFile;

    private final BinlogPipeline pipeline;

    private final Lock lock = new ReentrantLock(true);

    private volatile boolean running;

    private BinlogConfig config;

    public BinlogContext(String taskName) throws IOException {
        path = new StringBuilder(System.getProperty("user.dir")).append(File.separatorChar)
                .append("data").append(File.separatorChar)
                .append("binlog").append(File.separatorChar)
                .append(taskName).append(File.separatorChar)
                .toString();
        File dir = new File(path);
        if (!dir.exists()) {
            FileUtils.forceMkdir(dir);
        }

        // binlog.index
        indexFile = new File(path + BINLOG_INDEX);
        // binlog.config
        configFile = new File(path + BINLOG_CONFIG);
        if (!configFile.exists()) {
            // binlog.000001
            config = initBinlogConfig(createNewBinlogName(0));
        }

        // read index
        Assert.isTrue(indexFile.exists(), String.format("The index file '%s' is not exist.", indexFile.getName()));
        readIndex();

        // delete index file
        deleteExpiredIndexFile();

        // {"binlog":"binlog.000001","pos":0}
        if (null == config) {
            config = JsonUtil.jsonToObj(FileUtils.readFileToString(configFile, DEFAULT_CHARSET), BinlogConfig.class);
        }

        // no index
        if (CollectionUtils.isEmpty(indexList)) {
            // binlog.000002
            config = initBinlogConfig(createNewBinlogName(getBinlogIndex(config.getFileName())));
            readIndex();
        }

        // 配置文件已失效，取最早的索引文件
        BinlogIndex binlogIndex = getBinlogIndexByName(config.getFileName());
        if (null == binlogIndex) {
            logger.warn("The binlog file '{}' is expired.", config.getFileName());
            config = new BinlogConfig().setFileName(config.getFileName());
            write(configFile, JsonUtil.objToJson(config), false);
        }

        pipeline = new BinlogPipeline(this);
        logger.info("BinlogContext initialized with config:{}", JsonUtil.objToJson(config));
    }

    @Override
    public void run() {
        if (running) {
            return;
        }

        final Lock binlogLock = lock;
        boolean locked = false;
        try {
            locked = binlogLock.tryLock();
            if (locked) {
                running = true;
                doCheck();
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

    @Override
    public void close() {
        pipeline.close();
    }

    public void flush() throws IOException {
        config.setFileName(pipeline.getReaderFileName());
        config.setPosition(pipeline.getReaderOffset());
        write(configFile, JsonUtil.objToJson(config), false);
    }

    public byte[] readLine() throws IOException {
        return pipeline.readLine();
    }

    public void write(BinlogMessage message) throws IOException {
        pipeline.write(message);
    }

    /**
     * <p>1. 生成新索引（超过限制大小 ｜ 过期）</p>
     * <p>2. 关闭索引流（状态运行 & 无锁 & 30s未用）</p>
     * <p>3. 删除旧索引（状态关闭 & 过期）</p>
     */
    private void doCheck() throws IOException {
        createNewBinlogIndex();
    }

    private void createNewBinlogIndex() throws IOException {
        final String writerFileName = pipeline.getWriterFileName();
        File file = new File(path + writerFileName);
        if (file.length() > BINLOG_MAX_SIZE || isExpiredFile(file)) {
            String newBinlogName = createNewBinlogName(getBinlogIndex(writerFileName));
            logger.info("文件大小已达到{}MB, 超过限制{}MB, 准备切换新文件{}.", getMB(file.length()), getMB(BINLOG_MAX_SIZE), newBinlogName);
            indexList.add(new BinlogIndex(newBinlogName, getFileCreateDateTime(new File(path + newBinlogName))));
            pipeline.setBinlogWriter(new BinlogWriter(path, getLastBinlogIndex()));
        }
    }

    private long getMB(long size) {
        return size / 1024 / 1024;
    }

    private void readIndex() throws IOException {
        indexList.clear();
        List<String> indexNames = FileUtils.readLines(indexFile, DEFAULT_CHARSET);
        if (!CollectionUtils.isEmpty(indexNames)) {
            for (String indexName : indexNames) {
                indexList.add(new BinlogIndex(indexName, getFileCreateDateTime(new File(path + indexName))));
            }
        }
    }

    private BinlogConfig initBinlogConfig(String binlogName) throws IOException {
        BinlogConfig config = new BinlogConfig().setFileName(binlogName);
        write(configFile, JsonUtil.objToJson(config), false);
        write(indexFile, binlogName + LINE_SEPARATOR, false);
        write(new File(path + binlogName), "", false);
        return config;
    }

    private void deleteExpiredIndexFile() throws IOException {
        if (CollectionUtils.isEmpty(indexList)) {
            return;
        }
        boolean delete = false;
        Iterator<BinlogIndex> iterator = indexList.iterator();
        while (iterator.hasNext()) {
            BinlogIndex next = iterator.next();
            if (null == next) {
                continue;
            }
            File file = new File(path + next.getFileName());
            if (!file.exists()) {
                logger.info("Delete invalid binlog file '{}'.", next.getFileName());
                iterator.remove();
                delete = true;
                continue;
            }
            if (isExpiredFile(file)) {
                FileUtils.forceDelete(file);
                iterator.remove();
                delete = true;
                logger.info("Delete expired binlog file '{}'.", next.getFileName());
            }
        }

        if (delete) {
            StringBuilder indexBuilder = new StringBuilder();
            indexList.forEach(i -> indexBuilder.append(i.getFileName()).append(LINE_SEPARATOR));
            write(indexFile, indexBuilder.toString(), false);
        }
    }

    private boolean isExpiredFile(File file) throws IOException {
        final LocalDateTime createTime = getFileCreateDateTime(file);
        return createTime.isBefore(LocalDateTime.now().minusDays(BINLOG_EXPIRE_DAYS));
    }

    private LocalDateTime getFileCreateDateTime(File file) throws IOException {
        BasicFileAttributes attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
        return attr.creationTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    private String createNewBinlogName(int index) {
        return String.format("%s.%06d", BINLOG, index % 999999 + 1);
    }

    private int getBinlogIndex(String binlogName) {
        return NumberUtil.toInt(StringUtil.substring(binlogName, BINLOG.length() + 1));
    }

    private void write(File file, String line, boolean append) throws IOException {
        FileUtils.write(file, line, DEFAULT_CHARSET, append);
    }

    public BinlogIndex getLastBinlogIndex() {
        return indexList.get(indexList.size() - 1);
    }

    public BinlogIndex getBinlogIndexByName(String fileName) {
        Map<String, BinlogIndex> binlogIndex = indexList.stream().collect(Collectors.toMap(BinlogIndex::getFileName, i -> i, (k1, k2) -> k1));
        return binlogIndex.get(fileName);
    }

    public String getPath() {
        return path;
    }

    public BinlogConfig getConfig() {
        return config;
    }

}