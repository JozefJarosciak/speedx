package com.tencent.wxop.stat;

import android.content.Context;

final class ae implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Context f15959a;

    ae(Context context) {
        this.f15959a = context;
    }

    public final void run() {
        try {
            new Thread(new ap(this.f15959a, null, null), "NetworkMonitorTask").start();
        } catch (Throwable th) {
            StatServiceImpl.f15886q.m18011e(th);
            StatServiceImpl.m17880a(this.f15959a, th);
        }
    }
}
