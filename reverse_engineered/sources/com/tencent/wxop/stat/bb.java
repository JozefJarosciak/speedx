package com.tencent.wxop.stat;

class bb implements Runnable {
    /* renamed from: a */
    final /* synthetic */ int f16029a;
    /* renamed from: b */
    final /* synthetic */ au f16030b;

    bb(au auVar, int i) {
        this.f16030b = auVar;
        this.f16029a = i;
    }

    public void run() {
        this.f16030b.m17983b(this.f16029a, true);
        this.f16030b.m17983b(this.f16029a, false);
    }
}
