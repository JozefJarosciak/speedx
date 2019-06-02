package com.alipay.sdk.app;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/* renamed from: com.alipay.sdk.app.e */
final class C0818e implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ C0816c f1926a;

    C0818e(C0816c c0816c) {
        this.f1926a = c0816c;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.f1926a.f1923a.cancel();
        this.f1926a.f1924b.f1919c = false;
        C0821h.f1929a = C0821h.m3171a();
        this.f1926a.f1924b.f1918b.finish();
    }
}
