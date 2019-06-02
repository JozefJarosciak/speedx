package com.j256.ormlite.field.types;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.DatabaseResults;
import java.sql.SQLException;
import java.util.Date;

public class DateStringType extends BaseDateType {
    public static int DEFAULT_WIDTH = 50;
    private static final DateStringType singleTon = new DateStringType();

    public static DateStringType getSingleton() {
        return singleTon;
    }

    private DateStringType() {
        super(SqlType.STRING, new Class[0]);
    }

    protected DateStringType(SqlType sqlType, Class<?>[] clsArr) {
        super(sqlType, clsArr);
    }

    public Object parseDefaultString(FieldType fieldType, String str) throws SQLException {
        Object convertDateStringConfig = BaseDateType.convertDateStringConfig(fieldType, defaultDateFormatConfig);
        try {
            return BaseDateType.normalizeDateString(convertDateStringConfig, str);
        } catch (Throwable e) {
            throw SqlExceptionUtil.create("Problems with field " + fieldType + " parsing default date-string '" + str + "' using '" + convertDateStringConfig + "'", e);
        }
    }

    public Object resultToSqlArg(FieldType fieldType, DatabaseResults databaseResults, int i) throws SQLException {
        return databaseResults.getString(i);
    }

    public Object sqlArgToJava(FieldType fieldType, Object obj, int i) throws SQLException {
        String str = (String) obj;
        Object convertDateStringConfig = BaseDateType.convertDateStringConfig(fieldType, defaultDateFormatConfig);
        try {
            return BaseDateType.parseDateString(convertDateStringConfig, str);
        } catch (Throwable e) {
            throw SqlExceptionUtil.create("Problems with column " + i + " parsing date-string '" + str + "' using '" + convertDateStringConfig + "'", e);
        }
    }

    public Object javaToSqlArg(FieldType fieldType, Object obj) {
        return BaseDateType.convertDateStringConfig(fieldType, defaultDateFormatConfig).getDateFormat().format((Date) obj);
    }

    public Object makeConfigObject(FieldType fieldType) {
        String format = fieldType.getFormat();
        if (format == null) {
            return defaultDateFormatConfig;
        }
        return new DateStringFormatConfig(format);
    }

    public int getDefaultWidth() {
        return DEFAULT_WIDTH;
    }

    public Object resultStringToJava(FieldType fieldType, String str, int i) throws SQLException {
        return sqlArgToJava(fieldType, str, i);
    }

    public Class<?> getPrimaryClass() {
        return byte[].class;
    }
}
