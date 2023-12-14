/**
 * DBSyncer Copyright 2020-2023 All Rights Reserved.
 */
package org.dbsyncer.connector.oracle.logminer.parser.impl;

import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.insert.Insert;
import org.dbsyncer.common.util.StringUtil;
import org.dbsyncer.connector.oracle.logminer.parser.AbstractParser;

import java.util.List;

/**
 * @Author AE86
 * @Version 1.0.0
 * @Date 2023-12-14 14:58
 */
public class InsertSql extends AbstractParser {

    private Insert insert;

    public InsertSql(Insert insert) {
        this.insert = insert;
    }

    @Override
    public List<Object> parseColumns() {
        List<Column> columns = insert.getColumns();
        ExpressionList values = insert.getSelect().getValues().getExpressions();
        for (int i = 0; i < columns.size(); i++) {
            columnMap.put(StringUtil.replace(columns.get(i).getColumnName(), StringUtil.DOUBLE_QUOTATION, StringUtil.EMPTY), values.get(i).toString());
        }
        return getColumnsFromDB();
    }

}
