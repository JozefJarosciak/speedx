package com.baidu.lbsapi.auth;

/* renamed from: com.baidu.lbsapi.auth.d */
class C1022d implements Runnable {
    /* renamed from: a */
    final /* synthetic */ C1021c f2278a;

    C1022d(C1021c c1021c) {
        this.f2278a = c1021c;
    }

    public void run() {
        C1017a.m3589a("postWithHttps start Thread id = " + String.valueOf(Thread.currentThread().getId()));
        this.f2278a.m3607a(new C1026g(this.f2278a.f2275a).m3622a(this.f2278a.f2276b));
    }
}
