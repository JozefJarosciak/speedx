package com.j256.ormlite.field.types;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.DatabaseResults;
import java.sql.SQLException;
import java.util.Date;

public class DateLongType extends BaseDateType {
    private static final DateLongType singleTon = new DateLongType();

    public static DateLongType getSingleton() {
        return singleTon;
    }

    private DateLongType() {
        super(SqlType.LONG, new Class[0]);
    }

    protected DateLongType(SqlType sqlType, Class<?>[] clsArr) {
        super(sqlType, clsArr);
    }

    public Object parseDefaultString(FieldType fieldType, String str) throws SQLException {
        try {
            return Long.valueOf(Long.parseLong(str));
        } catch (Throwable e) {
            throw SqlExceptionUtil.create("Problems with field " + fieldType + " parsing default date-long value: " + str, e);
        }
    }

    public Object resultToSqlArg(FieldType fieldType, DatabaseResults databaseResults, int i) throws SQLException {
        return Long.valueOf(databaseResults.getLong(i));
    }

    public Object sqlArgToJava(FieldType fieldType, Object obj, int i) {
        return new Date(((Long) obj).longValue());
    }

    public Object javaToSqlArg(FieldType fieldType, Object obj) {
        return Long.valueOf(((Date) obj).getTime());
    }

    public boolean isEscapedValue() {
        return false;
    }

    public Object resultStringToJava(FieldType fieldType, String str, int i) {
        return sqlArgToJava(fieldType, Long.valueOf(Long.parseLong(str)), i);
    }

    public Class<?> getPrimaryClass() {
        return Date.class;
    }
}
