package com.j256.ormlite.field.types;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.DatabaseResults;
import java.sql.SQLException;

public class StringBytesType extends BaseDataType {
    private static final String DEFAULT_STRING_BYTES_CHARSET_NAME = "Unicode";
    private static final StringBytesType singleTon = new StringBytesType();

    public static StringBytesType getSingleton() {
        return singleTon;
    }

    private StringBytesType() {
        super(SqlType.BYTE_ARRAY, new Class[0]);
    }

    protected StringBytesType(SqlType sqlType, Class<?>[] clsArr) {
        super(sqlType, clsArr);
    }

    public Object parseDefaultString(FieldType fieldType, String str) throws SQLException {
        throw new SQLException("String-bytes type cannot have default values");
    }

    public Object resultToSqlArg(FieldType fieldType, DatabaseResults databaseResults, int i) throws SQLException {
        return databaseResults.getBytes(i);
    }

    public Object sqlArgToJava(FieldType fieldType, Object obj, int i) throws SQLException {
        byte[] bArr = (byte[]) obj;
        String charsetName = getCharsetName(fieldType);
        try {
            return new String(bArr, charsetName);
        } catch (Throwable e) {
            throw SqlExceptionUtil.create("Could not convert string with charset name: " + charsetName, e);
        }
    }

    public Object javaToSqlArg(FieldType fieldType, Object obj) throws SQLException {
        String str = (String) obj;
        String charsetName = getCharsetName(fieldType);
        try {
            return str.getBytes(charsetName);
        } catch (Throwable e) {
            throw SqlExceptionUtil.create("Could not convert string with charset name: " + charsetName, e);
        }
    }

    public boolean isAppropriateId() {
        return false;
    }

    public boolean isArgumentHolderRequired() {
        return true;
    }

    public Object resultStringToJava(FieldType fieldType, String str, int i) throws SQLException {
        throw new SQLException("String-bytes type cannot be converted from string to Java");
    }

    public Class<?> getPrimaryClass() {
        return String.class;
    }

    private String getCharsetName(FieldType fieldType) {
        if (fieldType == null || fieldType.getFormat() == null) {
            return DEFAULT_STRING_BYTES_CHARSET_NAME;
        }
        return fieldType.getFormat();
    }
}
