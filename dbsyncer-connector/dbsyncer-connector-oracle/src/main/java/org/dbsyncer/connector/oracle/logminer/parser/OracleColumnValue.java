package org.dbsyncer.connector.oracle.logminer.parser;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.NullValue;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.IsNullExpression;
import org.dbsyncer.common.column.AbstractColumnValue;
import org.dbsyncer.common.util.CollectionUtils;
import org.dbsyncer.common.util.DateFormatUtil;
import org.dbsyncer.common.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

public class OracleColumnValue extends AbstractColumnValue<Expression> {

    private final Logger logger = LoggerFactory.getLogger(getClass());


    public OracleColumnValue(Expression value) {
        setValue(value);
    }


    @Override
    public String asString() {
        if (isNull()){
            return null;
        }
        return getValue().toString();
    }

    @Override
    public byte[] asByteArray() {
        return new byte[0];
    }

    @Override
    public Short asShort() {
        return Short.valueOf(asString());
    }

    @Override
    public Integer asInteger() {
        if (asString() == null){
            return null;
        }
       return Integer.valueOf(asString());
    }

    @Override
    public Long asLong() {
        return Long.valueOf(asString());
    }

    @Override
    public Float asFloat() {
        return Float.valueOf(asString());
    }

    @Override
    public Double asDouble() {
        return Double.valueOf(asString());
    }

    @Override
    public Boolean asBoolean() {
        return null;
    }

    @Override
    public BigDecimal asBigDecimal() {
        if (asString() == null){
            return null;
        }
        return new BigDecimal(asString());
    }

    @Override
    public Date asDate() {
        return null;
    }

    @Override
    public Timestamp asTimestamp() {
        if (isNull()){
            return null;
        }
        Function function = (Function) getValue();
        List<String> multipartName = function.getMultipartName();
        ExpressionList parameters = function.getParameters();
        if (CollectionUtils.isEmpty(multipartName) || CollectionUtils.isEmpty(parameters)) {
            return null;
        }

        String nameType = Objects.toString(multipartName.get(0));
        Object value = parameters.get(0);
        if (nameType == null || value == null) {
            return null;
        }

        if (value instanceof StringValue) {
            StringValue val = (StringValue) value;
            value = val.getValue();
        }

        try {
            switch (nameType) {
                case "TO_DATE":
                    return toDate(value);
                case "TO_TIMESTAMP":
                    return toTimestamp(value);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public OffsetDateTime asOffsetDateTime(){
        if (isNull()){
            return null;
        }
        Function function = (Function) getValue();
        List<String> multipartName = function.getMultipartName();
        ExpressionList parameters = function.getParameters();
        if (CollectionUtils.isEmpty(multipartName) || CollectionUtils.isEmpty(parameters)) {
            return null;
        }

        String nameType = Objects.toString(multipartName.get(0));
        Object value = parameters.get(0);
        if (nameType == null || value == null) {
            return null;
        }

        if (value instanceof StringValue) {
            StringValue val = (StringValue) value;
            value = val.getValue();
        }

        try {
            switch (nameType) {
                case "TO_TIMESTAMP_TZ":
                    return toOffsetDateTime(value);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public Time asTime() {
        return null;
    }

    @Override
    public BigInteger asBigInteger() {
        if (isNull()){
            return null;
        }
        return new BigInteger(asString());
    }

    @Override
    public boolean isNull() {
        if (getValue() instanceof IsNullExpression){
            return true;
        }
        if (getValue() instanceof NullValue){
            return true;
        }
        if (getValue() instanceof IsNullExpression){
            return true;
        }
        return false;
    }

    private Timestamp toDate(Object value) {
        return DateFormatUtil.stringToTimestamp(Objects.toString(value));
    }

    private Timestamp toTimestamp(Object value) {
        return DateFormatUtil.stringToTimestamp(StringUtil.replace(Objects.toString(value), StringUtil.POINT, StringUtil.EMPTY));
    }

    private OffsetDateTime toOffsetDateTime(Object value){
        return DateFormatUtil.timestampWithTimeZoneToOffsetDateTime(Objects.toString(value));
    }



}
