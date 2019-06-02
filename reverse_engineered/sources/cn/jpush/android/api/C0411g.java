package cn.jpush.android.api;

import android.content.Context;

/* renamed from: cn.jpush.android.api.g */
final class C0411g implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Context f548a;
    /* renamed from: b */
    final /* synthetic */ C0409e f549b;

    C0411g(C0409e c0409e, Context context) {
        this.f549b = c0409e;
        this.f548a = context;
    }

    public final void run() {
        C0409e.m1184b(this.f549b, this.f548a);
    }
}
