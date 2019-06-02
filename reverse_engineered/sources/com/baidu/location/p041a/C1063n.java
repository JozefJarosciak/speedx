package com.baidu.location.p041a;

import com.baidu.location.C1102f;
import com.baidu.location.p043b.C1085h;

/* renamed from: com.baidu.location.a.n */
class C1063n implements Runnable {
    /* renamed from: a */
    final /* synthetic */ C1062m f2515a;

    C1063n(C1062m c1062m) {
        this.f2515a = c1062m;
    }

    public void run() {
        if (C1085h.m3963h() || this.f2515a.m3807a(C1102f.getServiceContext())) {
            this.f2515a.m3671d();
        }
    }
}
