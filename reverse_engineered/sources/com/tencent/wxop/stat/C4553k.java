package com.tencent.wxop.stat;

import java.util.List;

/* renamed from: com.tencent.wxop.stat.k */
class C4553k implements Runnable {
    /* renamed from: a */
    final /* synthetic */ List f16134a;
    /* renamed from: b */
    final /* synthetic */ C4526h f16135b;
    /* renamed from: c */
    final /* synthetic */ C4551i f16136c;

    C4553k(C4551i c4551i, List list, C4526h c4526h) {
        this.f16136c = c4551i;
        this.f16134a = list;
        this.f16135b = c4526h;
    }

    public void run() {
        this.f16136c.m18124a(this.f16134a, this.f16135b);
    }
}
