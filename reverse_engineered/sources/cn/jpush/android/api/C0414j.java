package cn.jpush.android.api;

import android.content.Context;

/* renamed from: cn.jpush.android.api.j */
final class C0414j implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Context f554a;
    /* renamed from: b */
    final /* synthetic */ C0409e f555b;

    C0414j(C0409e c0409e, Context context) {
        this.f555b = c0409e;
        this.f554a = context;
    }

    public final void run() {
        C0409e.m1184b(this.f555b, this.f554a);
    }
}
