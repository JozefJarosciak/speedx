package com.alipay.p018a.p019a;

import java.lang.reflect.Type;
import java.util.Date;

/* renamed from: com.alipay.a.a.c */
public final class C0718c implements C0714i, C0715j {
    /* renamed from: a */
    public final Object mo2398a(Object obj) {
        return Long.valueOf(((Date) obj).getTime());
    }

    /* renamed from: a */
    public final Object mo2399a(Object obj, Type type) {
        return new Date(((Long) obj).longValue());
    }

    /* renamed from: a */
    public final boolean mo2400a(Class<?> cls) {
        return Date.class.isAssignableFrom(cls);
    }
}
