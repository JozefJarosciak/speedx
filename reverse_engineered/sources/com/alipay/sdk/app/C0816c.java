package com.alipay.sdk.app;

import android.webkit.SslErrorHandler;
import com.alipay.sdk.widget.C0892d;

/* renamed from: com.alipay.sdk.app.c */
final class C0816c implements Runnable {
    /* renamed from: a */
    final /* synthetic */ SslErrorHandler f1923a;
    /* renamed from: b */
    final /* synthetic */ C0815b f1924b;

    C0816c(C0815b c0815b, SslErrorHandler sslErrorHandler) {
        this.f1924b = c0815b;
        this.f1923a = sslErrorHandler;
    }

    public final void run() {
        C0892d.m3501a(this.f1924b.f1918b, "安全警告", "安全连接证书校验无效，将无法保证访问数据的安全性，可能存在风险，请选择是否继续？", "继续", new C0817d(this), "退出", new C0818e(this));
    }
}
