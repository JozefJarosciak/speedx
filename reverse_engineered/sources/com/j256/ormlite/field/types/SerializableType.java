package com.j256.ormlite.field.types;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.DatabaseResults;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Arrays;

public class SerializableType extends BaseDataType {
    private static final SerializableType singleTon = new SerializableType();

    public static SerializableType getSingleton() {
        return singleTon;
    }

    private SerializableType() {
        super(SqlType.SERIALIZABLE, new Class[0]);
    }

    protected SerializableType(SqlType sqlType, Class<?>[] clsArr) {
        super(sqlType, clsArr);
    }

    public Object parseDefaultString(FieldType fieldType, String str) throws SQLException {
        throw new SQLException("Default values for serializable types are not supported");
    }

    public Object resultToSqlArg(FieldType fieldType, DatabaseResults databaseResults, int i) throws SQLException {
        return databaseResults.getBytes(i);
    }

    public Object sqlArgToJava(FieldType fieldType, Object obj, int i) throws SQLException {
        ObjectInputStream objectInputStream;
        Throwable e;
        byte[] bArr = (byte[]) obj;
        try {
            objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bArr));
            try {
                Object readObject = objectInputStream.readObject();
                if (objectInputStream != null) {
                    try {
                        objectInputStream.close();
                    } catch (IOException e2) {
                    }
                }
                return readObject;
            } catch (Exception e3) {
                e = e3;
                try {
                    throw SqlExceptionUtil.create("Could not read serialized object from byte array: " + Arrays.toString(bArr) + "(len " + bArr.length + ")", e);
                } catch (Throwable th) {
                    e = th;
                    if (objectInputStream != null) {
                        try {
                            objectInputStream.close();
                        } catch (IOException e4) {
                        }
                    }
                    throw e;
                }
            }
        } catch (Exception e5) {
            e = e5;
            objectInputStream = null;
            throw SqlExceptionUtil.create("Could not read serialized object from byte array: " + Arrays.toString(bArr) + "(len " + bArr.length + ")", e);
        } catch (Throwable th2) {
            e = th2;
            objectInputStream = null;
            if (objectInputStream != null) {
                objectInputStream.close();
            }
            throw e;
        }
    }

    public Object javaToSqlArg(FieldType fieldType, Object obj) throws SQLException {
        Throwable e;
        ObjectOutputStream objectOutputStream = null;
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(byteArrayOutputStream);
            try {
                objectOutputStream2.writeObject(obj);
                objectOutputStream2.close();
                objectOutputStream2 = null;
                Object toByteArray = byteArrayOutputStream.toByteArray();
                if (null != null) {
                    try {
                        objectOutputStream2.close();
                    } catch (IOException e2) {
                    }
                }
                return toByteArray;
            } catch (Exception e3) {
                e = e3;
                objectOutputStream = objectOutputStream2;
                try {
                    throw SqlExceptionUtil.create("Could not write serialized object to byte array: " + obj, e);
                } catch (Throwable th) {
                    e = th;
                    if (objectOutputStream != null) {
                        try {
                            objectOutputStream.close();
                        } catch (IOException e4) {
                        }
                    }
                    throw e;
                }
            } catch (Throwable th2) {
                e = th2;
                objectOutputStream = objectOutputStream2;
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                throw e;
            }
        } catch (Exception e5) {
            e = e5;
            throw SqlExceptionUtil.create("Could not write serialized object to byte array: " + obj, e);
        }
    }

    public boolean isValidForField(Field field) {
        return Serializable.class.isAssignableFrom(field.getType());
    }

    public boolean isStreamType() {
        return true;
    }

    public boolean isComparable() {
        return false;
    }

    public boolean isAppropriateId() {
        return false;
    }

    public boolean isArgumentHolderRequired() {
        return true;
    }

    public Object resultStringToJava(FieldType fieldType, String str, int i) throws SQLException {
        throw new SQLException("Serializable type cannot be converted from string to Java");
    }

    public Class<?> getPrimaryClass() {
        return Serializable.class;
    }
}
