package com.alipay.p018a.p019a;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.json.alipay.C5664a;

/* renamed from: com.alipay.a.a.a */
public final class C0716a implements C0714i, C0715j {
    /* renamed from: a */
    public final Object mo2398a(Object obj) {
        Object[] objArr = (Object[]) obj;
        List arrayList = new ArrayList();
        for (Object b : objArr) {
            arrayList.add(C0721f.m2807b(b));
        }
        return arrayList;
    }

    /* renamed from: a */
    public final Object mo2399a(Object obj, Type type) {
        if (!obj.getClass().equals(C5664a.class)) {
            return null;
        }
        C5664a c5664a = (C5664a) obj;
        if (type instanceof GenericArrayType) {
            throw new IllegalArgumentException("Does not support generic array!");
        }
        Type componentType = ((Class) type).getComponentType();
        int a = c5664a.a();
        Object newInstance = Array.newInstance(componentType, a);
        for (int i = 0; i < a; i++) {
            Array.set(newInstance, i, C0720e.m2804a(c5664a.a(i), componentType));
        }
        return newInstance;
    }

    /* renamed from: a */
    public final boolean mo2400a(Class<?> cls) {
        return cls.isArray();
    }
}
