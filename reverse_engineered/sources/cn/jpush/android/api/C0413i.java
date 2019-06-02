package cn.jpush.android.api;

import android.content.Context;

/* renamed from: cn.jpush.android.api.i */
final class C0413i implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Context f552a;
    /* renamed from: b */
    final /* synthetic */ C0409e f553b;

    C0413i(C0409e c0409e, Context context) {
        this.f553b = c0409e;
        this.f552a = context;
    }

    public final void run() {
        C0409e.m1184b(this.f553b, this.f552a);
    }
}
