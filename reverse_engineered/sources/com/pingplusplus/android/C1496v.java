package com.pingplusplus.android;

import android.webkit.JavascriptInterface;

/* renamed from: com.pingplusplus.android.v */
final class C1496v {
    /* renamed from: a */
    final /* synthetic */ C4300q f7018a;

    C1496v(C4300q c4300q) {
        this.f7018a = c4300q;
    }

    @JavascriptInterface
    public void showSource(String str) {
        if (str.contains("支付成功")) {
            C4300q.a(this.f7018a, true);
            C4300q.c(this.f7018a).f7010b = "success";
        }
        if (str.contains("您已购买成功") || str.contains("本次消费已计入您的账单")) {
            C4300q.a(this.f7018a, true);
            C4300q.c(this.f7018a).f7010b = "success";
        }
    }
}
