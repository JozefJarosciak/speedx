package com.pingplusplus.android;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/* renamed from: com.pingplusplus.android.z */
public class C4302z extends WebViewClient {
    /* renamed from: b */
    final /* synthetic */ WebViewEx f15008b;

    protected C4302z(WebViewEx webViewEx) {
        this.f15008b = webViewEx;
    }

    public void doUpdateVisitedHistory(WebView webView, String str, boolean z) {
        this.f15008b.m16965a(webView);
        super.doUpdateVisitedHistory(webView, str, z);
    }

    public void onLoadResource(WebView webView, String str) {
        this.f15008b.m16965a(webView);
        super.onLoadResource(webView, str);
    }

    public void onPageFinished(WebView webView, String str) {
        this.f15008b.m16965a(webView);
        super.onPageFinished(webView, str);
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        this.f15008b.m16965a(webView);
        super.onPageStarted(webView, str, bitmap);
    }
}
