package com.tencent.wxop.stat;

import com.tencent.wxop.stat.p201a.C4513e;

class ay implements Runnable {
    /* renamed from: a */
    final /* synthetic */ C4513e f16018a;
    /* renamed from: b */
    final /* synthetic */ C4526h f16019b;
    /* renamed from: c */
    final /* synthetic */ boolean f16020c;
    /* renamed from: d */
    final /* synthetic */ boolean f16021d;
    /* renamed from: e */
    final /* synthetic */ au f16022e;

    ay(au auVar, C4513e c4513e, C4526h c4526h, boolean z, boolean z2) {
        this.f16022e = auVar;
        this.f16018a = c4513e;
        this.f16019b = c4526h;
        this.f16020c = z;
        this.f16021d = z2;
    }

    public void run() {
        this.f16022e.m17984b(this.f16018a, this.f16019b, this.f16020c, this.f16021d);
    }
}
