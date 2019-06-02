package com.alipay.sdk.auth;

/* renamed from: com.alipay.sdk.auth.c */
final class C0831c implements Runnable {
    /* renamed from: a */
    final /* synthetic */ String f2004a;
    /* renamed from: b */
    final /* synthetic */ AuthActivity f2005b;

    C0831c(AuthActivity authActivity, String str) {
        this.f2005b = authActivity;
        this.f2004a = str;
    }

    public final void run() {
        try {
            this.f2005b.f1995c.loadUrl("javascript:" + this.f2004a);
        } catch (Exception e) {
        }
    }
}
