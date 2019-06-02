package com.alipay.p018a.p019a;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.TreeMap;
import org.json.alipay.C5666b;

/* renamed from: com.alipay.a.a.g */
public final class C0722g implements C0714i, C0715j {
    /* renamed from: a */
    public final Object mo2398a(Object obj) {
        Map treeMap = new TreeMap();
        Class cls = obj.getClass();
        Field[] declaredFields = cls.getDeclaredFields();
        while (!cls.equals(Object.class)) {
            if (declaredFields != null && declaredFields.length > 0) {
                for (Field field : declaredFields) {
                    Object obj2;
                    if (field == null || obj == null) {
                        obj2 = null;
                    } else {
                        if ("this$0".equals(field.getName())) {
                            obj2 = null;
                        } else {
                            boolean isAccessible = field.isAccessible();
                            field.setAccessible(true);
                            Object obj3 = field.get(obj);
                            if (obj3 == null) {
                                obj2 = null;
                            } else {
                                field.setAccessible(isAccessible);
                                obj2 = C0721f.m2807b(obj3);
                            }
                        }
                    }
                    if (obj2 != null) {
                        treeMap.put(field.getName(), obj2);
                    }
                }
            }
            cls = cls.getSuperclass();
            declaredFields = cls.getDeclaredFields();
        }
        return treeMap;
    }

    /* renamed from: a */
    public final Object mo2399a(Object obj, Type type) {
        if (!obj.getClass().equals(C5666b.class)) {
            return null;
        }
        C5666b c5666b = (C5666b) obj;
        Class cls = (Class) type;
        Object newInstance = cls.newInstance();
        while (!cls.equals(Object.class)) {
            Field[] declaredFields = cls.getDeclaredFields();
            if (declaredFields != null && declaredFields.length > 0) {
                for (Field field : declaredFields) {
                    String name = field.getName();
                    Type genericType = field.getGenericType();
                    if (c5666b.b(name)) {
                        field.setAccessible(true);
                        field.set(newInstance, C0720e.m2804a(c5666b.a(name), genericType));
                    }
                }
            }
            cls = cls.getSuperclass();
        }
        return newInstance;
    }

    /* renamed from: a */
    public final boolean mo2400a(Class<?> cls) {
        return true;
    }
}
