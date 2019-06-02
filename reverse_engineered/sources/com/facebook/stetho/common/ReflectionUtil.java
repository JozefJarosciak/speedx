package com.facebook.stetho.common;

import java.lang.reflect.Field;

public final class ReflectionUtil {
    private ReflectionUtil() {
    }

    public static Class<?> tryGetClassForName(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static Field tryGetDeclaredField(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Throwable e) {
            LogUtil.m15275d(e, "Could not retrieve %s field from %s", str, cls);
            return null;
        }
    }

    public static Object getFieldValue(Field field, Object obj) {
        try {
            return field.get(obj);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
