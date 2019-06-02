package com.alipay.p018a.p019a;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.json.alipay.C5664a;

/* renamed from: com.alipay.a.a.k */
public final class C0724k implements C0714i {
    /* renamed from: a */
    public final Object mo2399a(Object obj, Type type) {
        int i = 0;
        if (!obj.getClass().equals(C5664a.class)) {
            return null;
        }
        C5664a c5664a = (C5664a) obj;
        Collection hashSet = new HashSet();
        Type type2 = type instanceof ParameterizedType ? ((ParameterizedType) type).getActualTypeArguments()[0] : Object.class;
        while (i < c5664a.a()) {
            hashSet.add(C0720e.m2804a(c5664a.a(i), type2));
            i++;
        }
        return hashSet;
    }

    /* renamed from: a */
    public final boolean mo2400a(Class<?> cls) {
        return Set.class.isAssignableFrom(cls);
    }
}
