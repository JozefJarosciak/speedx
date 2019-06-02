package com.alipay.android.phone.mrpc.core;

import java.lang.reflect.Proxy;

/* renamed from: com.alipay.android.phone.mrpc.core.x */
public final class C0761x {
    /* renamed from: a */
    private C0744g f1801a;
    /* renamed from: b */
    private C0763z f1802b = new C0763z(this);

    public C0761x(C0744g c0744g) {
        this.f1801a = c0744g;
    }

    /* renamed from: a */
    public final C0744g m2911a() {
        return this.f1801a;
    }

    /* renamed from: a */
    public final <T> T m2912a(Class<T> cls) {
        return Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new C0762y(this.f1801a, cls, this.f1802b));
    }
}
