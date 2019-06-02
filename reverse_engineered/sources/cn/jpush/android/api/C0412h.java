package cn.jpush.android.api;

import android.content.Context;

/* renamed from: cn.jpush.android.api.h */
final class C0412h implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Context f550a;
    /* renamed from: b */
    final /* synthetic */ C0409e f551b;

    C0412h(C0409e c0409e, Context context) {
        this.f551b = c0409e;
        this.f550a = context;
    }

    public final void run() {
        C0409e.m1180a(this.f551b, this.f550a);
    }
}
