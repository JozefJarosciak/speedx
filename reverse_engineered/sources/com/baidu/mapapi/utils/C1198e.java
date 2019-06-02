package com.baidu.mapapi.utils;

import android.content.Context;

/* renamed from: com.baidu.mapapi.utils.e */
final class C1198e implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Context f3536a;
    /* renamed from: b */
    final /* synthetic */ int f3537b;

    C1198e(Context context, int i) {
        this.f3536a = context;
        this.f3537b = i;
    }

    public void run() {
        long currentTimeMillis = System.currentTimeMillis();
        do {
            if (System.currentTimeMillis() - currentTimeMillis > 3000) {
                C1192a.m4471a(this.f3536a);
                C1192a.m4470a(this.f3537b, this.f3536a);
            }
        } while (!C1192a.f3533v.isInterrupted());
    }
}
