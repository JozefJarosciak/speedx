package com.alipay.sdk.auth;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/* renamed from: com.alipay.sdk.auth.g */
final class C0835g implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ C0833e f2010a;

    C0835g(C0833e c0833e) {
        this.f2010a = c0833e;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.f2010a.f2007a.cancel();
        this.f2010a.f2008b.f1992a.f1999g = false;
        C0836h.m3225a(this.f2010a.f2008b.f1992a, this.f2010a.f2008b.f1992a.f1996d + "?resultCode=150");
        this.f2010a.f2008b.f1992a.finish();
    }
}
