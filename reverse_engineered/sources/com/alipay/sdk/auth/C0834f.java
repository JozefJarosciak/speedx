package com.alipay.sdk.auth;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/* renamed from: com.alipay.sdk.auth.f */
final class C0834f implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ C0833e f2009a;

    C0834f(C0833e c0833e) {
        this.f2009a = c0833e;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.f2009a.f2008b.f1992a.f1999g = true;
        this.f2009a.f2007a.proceed();
        dialogInterface.dismiss();
    }
}
