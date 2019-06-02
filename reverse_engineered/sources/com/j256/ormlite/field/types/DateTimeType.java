package com.j256.ormlite.field.types;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.DatabaseResults;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.sql.SQLException;

public class DateTimeType extends BaseDataType {
    private static final String[] associatedClassNames = new String[]{"org.joda.time.DateTime"};
    private static Class<?> dateTimeClass = null;
    private static Method getMillisMethod = null;
    private static Constructor<?> millisConstructor = null;
    private static final DateTimeType singleTon = new DateTimeType();

    private DateTimeType() {
        super(SqlType.LONG, new Class[0]);
    }

    protected DateTimeType(SqlType sqlType, Class<?>[] clsArr) {
        super(sqlType, clsArr);
    }

    public static DateTimeType getSingleton() {
        return singleTon;
    }

    public String[] getAssociatedClassNames() {
        return associatedClassNames;
    }

    public Object javaToSqlArg(FieldType fieldType, Object obj) throws SQLException {
        try {
            Method millisMethod = getMillisMethod();
            if (obj == null) {
                return null;
            }
            return millisMethod.invoke(obj, new Object[0]);
        } catch (Throwable e) {
            throw SqlExceptionUtil.create("Could not use reflection to get millis from Joda DateTime: " + obj, e);
        }
    }

    public Object parseDefaultString(FieldType fieldType, String str) {
        return Long.valueOf(Long.parseLong(str));
    }

    public Object resultToSqlArg(FieldType fieldType, DatabaseResults databaseResults, int i) throws SQLException {
        return Long.valueOf(databaseResults.getLong(i));
    }

    public Object sqlArgToJava(FieldType fieldType, Object obj, int i) throws SQLException {
        try {
            return getConstructor().newInstance(new Object[]{(Long) obj});
        } catch (Throwable e) {
            throw SqlExceptionUtil.create("Could not use reflection to construct a Joda DateTime", e);
        }
    }

    public boolean isEscapedValue() {
        return false;
    }

    public boolean isAppropriateId() {
        return false;
    }

    public Class<?> getPrimaryClass() {
        try {
            return getDateTimeClass();
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    private Method getMillisMethod() throws Exception {
        if (getMillisMethod == null) {
            getMillisMethod = getDateTimeClass().getMethod("getMillis", new Class[0]);
        }
        return getMillisMethod;
    }

    private Constructor<?> getConstructor() throws Exception {
        if (millisConstructor == null) {
            millisConstructor = getDateTimeClass().getConstructor(new Class[]{Long.TYPE});
        }
        return millisConstructor;
    }

    private Class<?> getDateTimeClass() throws ClassNotFoundException {
        if (dateTimeClass == null) {
            dateTimeClass = Class.forName("org.joda.time.DateTime");
        }
        return dateTimeClass;
    }
}
