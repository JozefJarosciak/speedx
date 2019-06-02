package com.twitter.sdk.android.core.identity;

import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import io.fabric.sdk.android.services.network.C4929h;
import java.net.URI;
import java.util.Map.Entry;
import java.util.TreeMap;

/* compiled from: OAuthWebViewClient */
/* renamed from: com.twitter.sdk.android.core.identity.c */
class C4595c extends WebViewClient {
    /* renamed from: a */
    private final String f16240a;
    /* renamed from: b */
    private final C4592a f16241b;

    /* compiled from: OAuthWebViewClient */
    /* renamed from: com.twitter.sdk.android.core.identity.c$a */
    interface C4592a {
        /* renamed from: a */
        void mo6135a(Bundle bundle);

        /* renamed from: a */
        void mo6136a(WebView webView, String str);

        /* renamed from: a */
        void mo6137a(WebViewException webViewException);
    }

    public C4595c(String str, C4592a c4592a) {
        this.f16240a = str;
        this.f16241b = c4592a;
    }

    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        this.f16241b.mo6136a(webView, str);
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (!str.startsWith(this.f16240a)) {
            return super.shouldOverrideUrlLoading(webView, str);
        }
        TreeMap a = C4929h.m19368a(URI.create(str), false);
        Bundle bundle = new Bundle(a.size());
        for (Entry entry : a.entrySet()) {
            bundle.putString((String) entry.getKey(), (String) entry.getValue());
        }
        this.f16241b.mo6135a(bundle);
        return true;
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
        super.onReceivedError(webView, i, str, str2);
        this.f16241b.mo6137a(new WebViewException(i, str, str2));
    }

    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        super.onReceivedSslError(webView, sslErrorHandler, sslError);
        this.f16241b.mo6137a(new WebViewException(sslError.getPrimaryError(), null, null));
    }
}
