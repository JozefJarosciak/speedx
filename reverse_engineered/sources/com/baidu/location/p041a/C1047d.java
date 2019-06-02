package com.baidu.location.p041a;

import android.location.Location;

/* renamed from: com.baidu.location.a.d */
class C1047d implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Location f2410a;
    /* renamed from: b */
    final /* synthetic */ C1046c f2411b;

    C1047d(C1046c c1046c, Location location) {
        this.f2411b = c1046c;
        this.f2410a = location;
    }

    public void run() {
        this.f2411b.m3703b(this.f2410a);
    }
}
