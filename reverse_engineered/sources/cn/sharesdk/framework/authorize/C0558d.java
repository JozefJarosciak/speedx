package cn.sharesdk.framework.authorize;

import android.content.Intent;

/* compiled from: SSOProcessor */
/* renamed from: cn.sharesdk.framework.authorize.d */
public abstract class C0558d {
    /* renamed from: a */
    protected C0579c f1182a;
    /* renamed from: b */
    protected int f1183b;
    /* renamed from: c */
    protected SSOListener f1184c;

    /* renamed from: a */
    public abstract void mo2262a();

    public C0558d(C0579c c0579c) {
        this.f1182a = c0579c;
        this.f1184c = c0579c.m2022a().getSSOListener();
    }

    /* renamed from: a */
    public void m1937a(int i) {
        this.f1183b = i;
    }

    /* renamed from: a */
    public void mo2263a(int i, int i2, Intent intent) {
    }

    /* renamed from: a */
    protected void m1939a(Intent intent) {
    }
}
