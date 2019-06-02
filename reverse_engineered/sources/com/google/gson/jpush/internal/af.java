package com.google.gson.jpush.internal;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class af {
    /* renamed from: a */
    private static final Map<Class<?>, Class<?>> f14495a;
    /* renamed from: b */
    private static final Map<Class<?>, Class<?>> f14496b;

    static {
        Map hashMap = new HashMap(16);
        Map hashMap2 = new HashMap(16);
        m16324a(hashMap, hashMap2, Boolean.TYPE, Boolean.class);
        m16324a(hashMap, hashMap2, Byte.TYPE, Byte.class);
        m16324a(hashMap, hashMap2, Character.TYPE, Character.class);
        m16324a(hashMap, hashMap2, Double.TYPE, Double.class);
        m16324a(hashMap, hashMap2, Float.TYPE, Float.class);
        m16324a(hashMap, hashMap2, Integer.TYPE, Integer.class);
        m16324a(hashMap, hashMap2, Long.TYPE, Long.class);
        m16324a(hashMap, hashMap2, Short.TYPE, Short.class);
        m16324a(hashMap, hashMap2, Void.TYPE, Void.class);
        f14495a = Collections.unmodifiableMap(hashMap);
        f14496b = Collections.unmodifiableMap(hashMap2);
    }

    /* renamed from: a */
    public static <T> Class<T> m16323a(Class<T> cls) {
        Class<T> cls2 = (Class) f14495a.get(C4017a.m16319a((Object) cls));
        return cls2 == null ? cls : cls2;
    }

    /* renamed from: a */
    private static void m16324a(Map<Class<?>, Class<?>> map, Map<Class<?>, Class<?>> map2, Class<?> cls, Class<?> cls2) {
        map.put(cls, cls2);
        map2.put(cls2, cls);
    }

    /* renamed from: a */
    public static boolean m16325a(Type type) {
        return f14495a.containsKey(type);
    }
}
