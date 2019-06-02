package com.alipay.p018a.p019a;

import java.lang.reflect.Type;

/* renamed from: com.alipay.a.a.d */
public final class C0719d implements C0714i, C0715j {
    /* renamed from: a */
    public final Object mo2398a(Object obj) {
        return ((Enum) obj).name();
    }

    /* renamed from: a */
    public final Object mo2399a(Object obj, Type type) {
        return Enum.valueOf((Class) type, obj.toString());
    }

    /* renamed from: a */
    public final boolean mo2400a(Class<?> cls) {
        return Enum.class.isAssignableFrom(cls);
    }
}
