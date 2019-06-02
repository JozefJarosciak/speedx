package com.pingplusplus.android;

import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/* renamed from: com.pingplusplus.android.y */
public class C4304y extends WebChromeClient {
    /* renamed from: b */
    final /* synthetic */ WebViewEx f15010b;

    public C4304y(WebViewEx webViewEx) {
        this.f15010b = webViewEx;
    }

    public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        return ((webView instanceof WebViewEx) && this.f15010b.m16970a(webView, str, str2, str3, jsPromptResult)) ? true : super.onJsPrompt(webView, str, str2, str3, jsPromptResult);
    }

    public void onProgressChanged(WebView webView, int i) {
        this.f15010b.m16965a(webView);
        super.onProgressChanged(webView, i);
    }

    public void onReceivedTitle(WebView webView, String str) {
        this.f15010b.m16965a(webView);
    }
}
