package com.alipay.sdk.util;

import android.app.Activity;

/* renamed from: com.alipay.sdk.util.n */
final class C0887n implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Activity f2238a;

    C0887n(Activity activity) {
        this.f2238a = activity;
    }

    public final void run() {
        this.f2238a.finish();
    }
}
