package cn.jpush.android.api;

import android.content.Context;

/* renamed from: cn.jpush.android.api.f */
final class C0410f implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Context f546a;
    /* renamed from: b */
    final /* synthetic */ C0409e f547b;

    C0410f(C0409e c0409e, Context context) {
        this.f547b = c0409e;
        this.f546a = context;
    }

    public final void run() {
        C0409e.m1180a(this.f547b, this.f546a);
    }
}
