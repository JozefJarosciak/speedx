package com.pingplusplus.android;

import android.net.Uri;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient.FileChooserParams;
import android.webkit.WebView;

/* renamed from: com.pingplusplus.android.u */
class C4305u extends C4304y {
    /* renamed from: a */
    final /* synthetic */ C4300q f15011a;

    C4305u(C4300q c4300q, WebViewEx webViewEx) {
        this.f15011a = c4300q;
        webViewEx.getClass();
        super(webViewEx);
    }

    public void onCloseWindow(WebView webView) {
        super.onCloseWindow(webView);
        webView.destroy();
        this.f15011a.f14993d.onBackPressed();
    }

    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, FileChooserParams fileChooserParams) {
        this.f15011a.m17015a((ValueCallback) valueCallback);
        return true;
    }
}
