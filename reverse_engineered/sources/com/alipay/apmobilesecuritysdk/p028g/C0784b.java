package com.alipay.apmobilesecuritysdk.p028g;

import java.util.LinkedList;

/* renamed from: com.alipay.apmobilesecuritysdk.g.b */
public final class C0784b {
    /* renamed from: a */
    private static C0784b f1847a = new C0784b();
    /* renamed from: b */
    private Thread f1848b = null;
    /* renamed from: c */
    private LinkedList<Runnable> f1849c = new LinkedList();

    /* renamed from: a */
    public static C0784b m3006a() {
        return f1847a;
    }

    /* renamed from: a */
    public final synchronized void m3009a(Runnable runnable) {
        this.f1849c.add(runnable);
        if (this.f1848b == null) {
            this.f1848b = new Thread(new C0785c(this));
            this.f1848b.start();
        }
    }
}
