package com.beastbikes.android.embapi;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build.VERSION;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import com.beastbikes.framework.android.p103h.C1809c;
import com.beastbikes.framework.ui.android.DefaultWebViewClient;
import com.beastbikes.framework.ui.android.WebActivity;
import java.util.Collections;

class BrowserActivity$1 extends DefaultWebViewClient {
    /* renamed from: a */
    final /* synthetic */ BrowserActivity f8218a;

    BrowserActivity$1(BrowserActivity browserActivity, WebActivity webActivity) {
        this.f8218a = browserActivity;
        super(webActivity);
    }

    @TargetApi(21)
    public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
        if (!"GET".equalsIgnoreCase(webResourceRequest.getMethod())) {
            return null;
        }
        String uri = webResourceRequest.getUrl().toString();
        if (C1815e.m9522a(webResourceRequest.getUrl(), this.f8218a) != null) {
            return super.shouldInterceptRequest(webView, webResourceRequest);
        }
        if (!uri.startsWith("http://hybrid.beastbikes.com/1.0")) {
            return super.shouldInterceptRequest(webView, webResourceRequest);
        }
        BrowserActivity.b().trace("Intercepting1 " + uri);
        C1809c c1809c = (C1809c) BrowserActivity.c().get(uri);
        if (c1809c != null) {
            WebResourceResponse a = c1809c.mo3250a(webView, webResourceRequest.getMethod(), uri, webResourceRequest.getRequestHeaders());
            if (a != null) {
                return a;
            }
        }
        return super.shouldInterceptRequest(webView, webResourceRequest);
    }

    public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        if (!str.startsWith("http://hybrid.beastbikes.com/1.0")) {
            return super.shouldInterceptRequest(webView, str);
        }
        BrowserActivity.b().info("trace " + str);
        C1809c c1809c = (C1809c) BrowserActivity.c().get(str);
        if (c1809c != null) {
            WebResourceResponse a = c1809c.mo3250a(webView, "GET", str, Collections.emptyMap());
            if (a != null) {
                return a;
            }
        }
        return super.shouldInterceptRequest(webView, str);
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (C1815e.m9522a(Uri.parse(str), this.f8218a) != null) {
            return true;
        }
        if ((VERSION.SDK_INT < 19) && str != null && str.startsWith("sinaweibo://")) {
            return true;
        }
        return super.shouldOverrideUrlLoading(webView, str);
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        try {
            if (!this.f8218a.isFinishing() && BrowserActivity.a(this.f8218a) != null && !BrowserActivity.a(this.f8218a).isShowing()) {
                BrowserActivity.a(this.f8218a).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onPageFinished(WebView webView, String str) {
        try {
            if (!this.f8218a.isFinishing() && BrowserActivity.a(this.f8218a) != null) {
                BrowserActivity.a(this.f8218a).dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
