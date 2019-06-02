package cn.jpush.android.api;

import android.content.Context;
import cn.jpush.android.data.C0429c;

/* renamed from: cn.jpush.android.api.n */
final class C0418n implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Context f565a;
    /* renamed from: b */
    final /* synthetic */ C0429c f566b;

    C0418n(Context context, C0429c c0429c) {
        this.f565a = context;
        this.f566b = c0429c;
    }

    public final void run() {
        C0417m.m1221b(this.f565a, this.f566b);
    }
}
