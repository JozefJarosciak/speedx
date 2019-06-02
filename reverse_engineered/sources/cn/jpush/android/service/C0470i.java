package cn.jpush.android.service;

import android.content.Context;

/* renamed from: cn.jpush.android.service.i */
final class C0470i implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Context f861a;
    /* renamed from: b */
    final /* synthetic */ C0469h f862b;

    C0470i(C0469h c0469h, Context context) {
        this.f862b = c0469h;
        this.f861a = context;
    }

    public final void run() {
        this.f862b.m1505e(this.f861a);
        this.f862b.m1509c(this.f861a);
    }
}
