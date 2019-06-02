package com.alipay.sdk.app;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Handler;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.alipay.sdk.app.statistic.C0823a;
import com.alipay.sdk.app.statistic.C0825c;
import com.alipay.sdk.util.C0885l;
import com.alipay.sdk.widget.C0889a;

/* renamed from: com.alipay.sdk.app.b */
public final class C0815b extends WebViewClient {
    /* renamed from: a */
    boolean f1917a;
    /* renamed from: b */
    private Activity f1918b;
    /* renamed from: c */
    private boolean f1919c;
    /* renamed from: d */
    private Handler f1920d;
    /* renamed from: e */
    private C0889a f1921e;
    /* renamed from: f */
    private Runnable f1922f = new C0819f(this);

    public C0815b(Activity activity) {
        this.f1918b = activity;
        this.f1920d = new Handler(this.f1918b.getMainLooper());
    }

    public final void onReceivedError(WebView webView, int i, String str, String str2) {
        this.f1917a = true;
        super.onReceivedError(webView, i, str, str2);
    }

    public final void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        C0823a.m3184a(C0825c.f1951a, C0825c.f1967q, "证书错误");
        if (this.f1919c) {
            sslErrorHandler.proceed();
            this.f1919c = false;
            return;
        }
        this.f1918b.runOnUiThread(new C0816c(this, sslErrorHandler));
    }

    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        return C0885l.m3468a(webView, str, this.f1918b);
    }

    public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        if (this.f1921e == null) {
            this.f1921e = new C0889a(this.f1918b, C0889a.f2240a);
        }
        this.f1921e.m3498a();
        this.f1920d.postDelayed(this.f1922f, 30000);
        super.onPageStarted(webView, str, bitmap);
    }

    public final void onPageFinished(WebView webView, String str) {
        m3167b();
        this.f1920d.removeCallbacks(this.f1922f);
    }

    /* renamed from: a */
    private void m3165a() {
        if (this.f1921e == null) {
            this.f1921e = new C0889a(this.f1918b, C0889a.f2240a);
        }
        this.f1921e.m3498a();
    }

    /* renamed from: b */
    private void m3167b() {
        if (this.f1921e != null) {
            this.f1921e.m3499b();
        }
        this.f1921e = null;
    }

    /* renamed from: c */
    private boolean m3169c() {
        return this.f1917a;
    }
}
