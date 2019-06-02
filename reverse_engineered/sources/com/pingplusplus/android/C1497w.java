package com.pingplusplus.android;

import android.content.Context;
import android.webkit.JavascriptInterface;

/* renamed from: com.pingplusplus.android.w */
public class C1497w {
    /* renamed from: a */
    Context f7019a;
    /* renamed from: b */
    final /* synthetic */ C4300q f7020b;

    C1497w(C4300q c4300q, Context context) {
        this.f7020b = c4300q;
        this.f7019a = context;
    }

    @JavascriptInterface
    public void paymentResult(String str) {
        if (str == null) {
            C4300q.c(this.f7020b).m8285a("fail", "unknown_error");
        } else if (str.equals("success")) {
            C4300q.c(this.f7020b).m8284a("success");
        } else {
            C4300q.c(this.f7020b).m8285a("fail", "unknown_error");
        }
    }

    @JavascriptInterface
    public void setResult(String str) {
        if (str == null) {
            C4300q.c(this.f7020b).m8285a("fail", "unknown_error");
        } else if (str.equals("success")) {
            C4300q.c(this.f7020b).m8284a("success");
        } else {
            C4300q.c(this.f7020b).m8285a("fail", "unknown_error");
        }
    }

    @JavascriptInterface
    public void testmodeResult(String str) {
        if (str == null) {
            C4300q.c(this.f7020b).m8285a("fail", "unknown_error");
        } else if (str.equals("success")) {
            C4300q.c(this.f7020b).m8284a("success");
        } else if (str.equals("cancel")) {
            C4300q.c(this.f7020b).m8285a("cancel", "user_cancelled");
        } else if (str.equals("fail")) {
            C4300q.c(this.f7020b).m8285a("fail", "channel_returns_fail");
        } else if (str.equals("error")) {
            C4300q.c(this.f7020b).m8285a("fail", "testmode_notify_failed");
        } else {
            C4300q.c(this.f7020b).m8285a("fail", "unknown_error");
        }
    }
}
