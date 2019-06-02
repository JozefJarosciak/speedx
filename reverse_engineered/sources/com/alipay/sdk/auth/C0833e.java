package com.alipay.sdk.auth;

import android.webkit.SslErrorHandler;
import com.alipay.sdk.auth.AuthActivity.C0827b;
import com.alipay.sdk.widget.C0892d;

/* renamed from: com.alipay.sdk.auth.e */
final class C0833e implements Runnable {
    /* renamed from: a */
    final /* synthetic */ SslErrorHandler f2007a;
    /* renamed from: b */
    final /* synthetic */ C0827b f2008b;

    C0833e(C0827b c0827b, SslErrorHandler sslErrorHandler) {
        this.f2008b = c0827b;
        this.f2007a = sslErrorHandler;
    }

    public final void run() {
        C0892d.m3501a(this.f2008b.f1992a, "安全警告", "由于您的设备缺少根证书，将无法校验该访问站点的安全性，可能存在风险，请选择是否继续？", "继续", new C0834f(this), "退出", new C0835g(this));
    }
}
