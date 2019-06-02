package com.alipay.android.phone.mrpc.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/* renamed from: com.alipay.android.phone.mrpc.core.y */
public final class C0762y implements InvocationHandler {
    /* renamed from: a */
    protected C0744g f1803a;
    /* renamed from: b */
    protected Class<?> f1804b;
    /* renamed from: c */
    protected C0763z f1805c;

    public C0762y(C0744g c0744g, Class<?> cls, C0763z c0763z) {
        this.f1803a = c0744g;
        this.f1804b = cls;
        this.f1805c = c0763z;
    }

    public final Object invoke(Object obj, Method method, Object[] objArr) {
        C0763z c0763z = this.f1805c;
        Class cls = this.f1804b;
        return c0763z.m2913a(method, objArr);
    }
}
