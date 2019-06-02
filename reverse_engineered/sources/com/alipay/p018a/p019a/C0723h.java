package com.alipay.p018a.p019a;

import com.alipay.p018a.p020b.C0726a;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.json.alipay.C5666b;

/* renamed from: com.alipay.a.a.h */
public final class C0723h implements C0714i, C0715j {
    /* renamed from: a */
    private static Map<Object, Object> m2811a(Type type) {
        Class cls = type;
        while (cls != Properties.class) {
            if (cls == Hashtable.class) {
                return new Hashtable();
            }
            if (cls == IdentityHashMap.class) {
                return new IdentityHashMap();
            }
            if (cls == SortedMap.class || cls == TreeMap.class) {
                return new TreeMap();
            }
            if (cls == ConcurrentMap.class || cls == ConcurrentHashMap.class) {
                return new ConcurrentHashMap();
            }
            if (cls == Map.class || cls == HashMap.class) {
                return new HashMap();
            }
            if (cls == LinkedHashMap.class) {
                return new LinkedHashMap();
            }
            if (cls instanceof ParameterizedType) {
                cls = ((ParameterizedType) cls).getRawType();
            } else {
                Class cls2 = cls;
                if (cls2.isInterface()) {
                    throw new IllegalArgumentException("unsupport type " + cls);
                }
                try {
                    return (Map) cls2.newInstance();
                } catch (Throwable e) {
                    throw new IllegalArgumentException("unsupport type " + cls, e);
                }
            }
        }
        return new Properties();
    }

    /* renamed from: a */
    public final Object mo2398a(Object obj) {
        Map treeMap = new TreeMap();
        for (Entry entry : ((Map) obj).entrySet()) {
            if (entry.getKey() instanceof String) {
                treeMap.put((String) entry.getKey(), C0721f.m2807b(entry.getValue()));
            } else {
                throw new IllegalArgumentException("Map key must be String!");
            }
        }
        return treeMap;
    }

    /* renamed from: a */
    public final Object mo2399a(Object obj, Type type) {
        if (!obj.getClass().equals(C5666b.class)) {
            return null;
        }
        C5666b c5666b = (C5666b) obj;
        Map a = C0723h.m2811a(type);
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Class cls = parameterizedType.getActualTypeArguments()[0];
            Type type2 = parameterizedType.getActualTypeArguments()[1];
            if (String.class == cls) {
                Iterator a2 = c5666b.a();
                while (a2.hasNext()) {
                    String str = (String) a2.next();
                    if (C0726a.m2821a((Class) type2)) {
                        a.put(str, c5666b.a(str));
                    } else {
                        a.put(str, C0720e.m2804a(c5666b.a(str), type2));
                    }
                }
                return a;
            }
            throw new IllegalArgumentException("Deserialize Map Key must be String.class");
        }
        throw new IllegalArgumentException("Deserialize Map must be Generics!");
    }

    /* renamed from: a */
    public final boolean mo2400a(Class<?> cls) {
        return Map.class.isAssignableFrom(cls);
    }
}
