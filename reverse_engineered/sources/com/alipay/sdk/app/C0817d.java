package com.alipay.sdk.app;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/* renamed from: com.alipay.sdk.app.d */
final class C0817d implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ C0816c f1925a;

    C0817d(C0816c c0816c) {
        this.f1925a = c0816c;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.f1925a.f1924b.f1919c = true;
        this.f1925a.f1923a.proceed();
        dialogInterface.dismiss();
    }
}
