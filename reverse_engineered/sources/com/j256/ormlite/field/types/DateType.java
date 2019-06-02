package com.j256.ormlite.field.types;

import ch.qos.logback.core.CoreConstants;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.DatabaseResults;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class DateType extends BaseDateType {
    private static final DateType singleTon = new DateType();

    public static DateType getSingleton() {
        return singleTon;
    }

    private DateType() {
        super(SqlType.DATE, new Class[]{Date.class});
    }

    protected DateType(SqlType sqlType, Class<?>[] clsArr) {
        super(sqlType, clsArr);
    }

    public Object parseDefaultString(FieldType fieldType, String str) throws SQLException {
        DateStringFormatConfig convertDateStringConfig = BaseDateType.convertDateStringConfig(fieldType, getDefaultDateFormatConfig());
        try {
            return new Timestamp(BaseDateType.parseDateString(convertDateStringConfig, str).getTime());
        } catch (Throwable e) {
            throw SqlExceptionUtil.create("Problems parsing default date string '" + str + "' using '" + convertDateStringConfig + CoreConstants.SINGLE_QUOTE_CHAR, e);
        }
    }

    public Object resultToSqlArg(FieldType fieldType, DatabaseResults databaseResults, int i) throws SQLException {
        return databaseResults.getTimestamp(i);
    }

    public Object sqlArgToJava(FieldType fieldType, Object obj, int i) {
        return new Date(((Timestamp) obj).getTime());
    }

    public Object javaToSqlArg(FieldType fieldType, Object obj) {
        return new Timestamp(((Date) obj).getTime());
    }

    public boolean isArgumentHolderRequired() {
        return true;
    }

    protected DateStringFormatConfig getDefaultDateFormatConfig() {
        return defaultDateFormatConfig;
    }
}
