package com.twitter.sdk.android.core.identity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import com.twitter.sdk.android.core.C4580e;
import com.twitter.sdk.android.core.C4645j;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthException;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.identity.C4595c.C4592a;
import com.twitter.sdk.android.core.internal.oauth.OAuth1aService;
import com.twitter.sdk.android.core.internal.oauth.OAuthResponse;
import io.fabric.sdk.android.C1520c;

/* compiled from: OAuthController */
/* renamed from: com.twitter.sdk.android.core.identity.a */
class C4593a implements C4592a {
    /* renamed from: a */
    final C4588a f16234a;
    /* renamed from: b */
    TwitterAuthToken f16235b;
    /* renamed from: c */
    private final ProgressBar f16236c;
    /* renamed from: d */
    private final WebView f16237d;
    /* renamed from: e */
    private final TwitterAuthConfig f16238e;
    /* renamed from: f */
    private final OAuth1aService f16239f;

    /* compiled from: OAuthController */
    /* renamed from: com.twitter.sdk.android.core.identity.a$a */
    interface C4588a {
        /* renamed from: a */
        void mo6134a(int i, Intent intent);
    }

    /* compiled from: OAuthController */
    /* renamed from: com.twitter.sdk.android.core.identity.a$1 */
    class C45901 extends C4580e<OAuthResponse> {
        /* renamed from: a */
        final /* synthetic */ C4593a f16232a;

        C45901(C4593a c4593a) {
            this.f16232a = c4593a;
        }

        /* renamed from: a */
        public void mo6128a(C4645j<OAuthResponse> c4645j) {
            this.f16232a.f16235b = ((OAuthResponse) c4645j.f16364a).f16283a;
            String a = this.f16232a.f16239f.m18267a(this.f16232a.f16235b);
            C1520c.h().mo6215a("Twitter", "Redirecting user to web view to complete authorization flow");
            this.f16232a.m18203a(this.f16232a.f16237d, new C4595c(this.f16232a.f16239f.m18266a(this.f16232a.f16238e), this.f16232a), a, new C4594b());
        }

        /* renamed from: a */
        public void mo6127a(TwitterException twitterException) {
            C1520c.h().mo6222d("Twitter", "Failed to get request token", twitterException);
            this.f16232a.m18201a(1, new TwitterAuthException("Failed to get request token"));
        }
    }

    /* compiled from: OAuthController */
    /* renamed from: com.twitter.sdk.android.core.identity.a$2 */
    class C45912 extends C4580e<OAuthResponse> {
        /* renamed from: a */
        final /* synthetic */ C4593a f16233a;

        C45912(C4593a c4593a) {
            this.f16233a = c4593a;
        }

        /* renamed from: a */
        public void mo6128a(C4645j<OAuthResponse> c4645j) {
            Intent intent = new Intent();
            OAuthResponse oAuthResponse = (OAuthResponse) c4645j.f16364a;
            intent.putExtra("screen_name", oAuthResponse.f16284b);
            intent.putExtra("user_id", oAuthResponse.f16285c);
            intent.putExtra("tk", oAuthResponse.f16283a.f7051b);
            intent.putExtra("ts", oAuthResponse.f16283a.f7052c);
            this.f16233a.f16234a.mo6134a(-1, intent);
        }

        /* renamed from: a */
        public void mo6127a(TwitterException twitterException) {
            C1520c.h().mo6222d("Twitter", "Failed to get access token", twitterException);
            this.f16233a.m18201a(1, new TwitterAuthException("Failed to get access token"));
        }
    }

    C4593a(ProgressBar progressBar, WebView webView, TwitterAuthConfig twitterAuthConfig, OAuth1aService oAuth1aService, C4588a c4588a) {
        this.f16236c = progressBar;
        this.f16237d = webView;
        this.f16238e = twitterAuthConfig;
        this.f16239f = oAuth1aService;
        this.f16234a = c4588a;
    }

    /* renamed from: a */
    void m18200a() {
        C1520c.h().mo6215a("Twitter", "Obtaining request token to start the sign in flow");
        this.f16239f.m18268a(m18206b());
    }

    /* renamed from: b */
    C4580e<OAuthResponse> m18206b() {
        return new C45901(this);
    }

    /* renamed from: a */
    protected void m18201a(int i, TwitterAuthException twitterAuthException) {
        Intent intent = new Intent();
        intent.putExtra("auth_error", twitterAuthException);
        this.f16234a.mo6134a(i, intent);
    }

    /* renamed from: a */
    void m18203a(WebView webView, WebViewClient webViewClient, String str, WebChromeClient webChromeClient) {
        WebSettings settings = webView.getSettings();
        settings.setAllowFileAccess(false);
        settings.setJavaScriptEnabled(false);
        settings.setSaveFormData(false);
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setWebViewClient(webViewClient);
        webView.loadUrl(str);
        webView.setVisibility(4);
        webView.setWebChromeClient(webChromeClient);
    }

    /* renamed from: b */
    private void m18195b(Bundle bundle) {
        C1520c.h().mo6215a("Twitter", "OAuth web view completed successfully");
        if (bundle != null) {
            String string = bundle.getString("oauth_verifier");
            if (string != null) {
                C1520c.h().mo6215a("Twitter", "Converting the request token to an access token.");
                this.f16239f.m18269a(m18207c(), this.f16235b, string);
                return;
            }
        }
        C1520c.h().mo6222d("Twitter", "Failed to get authorization, bundle incomplete " + bundle, null);
        m18201a(1, new TwitterAuthException("Failed to get authorization, bundle incomplete"));
    }

    /* renamed from: c */
    C4580e<OAuthResponse> m18207c() {
        return new C45912(this);
    }

    /* renamed from: b */
    private void m18196b(WebViewException webViewException) {
        C1520c.h().mo6222d("Twitter", "OAuth web view completed with an error", webViewException);
        m18201a(1, new TwitterAuthException("OAuth web view completed with an error"));
    }

    /* renamed from: d */
    private void m18198d() {
        this.f16237d.stopLoading();
        m18199e();
    }

    /* renamed from: e */
    private void m18199e() {
        this.f16236c.setVisibility(8);
    }

    /* renamed from: a */
    public void mo6136a(WebView webView, String str) {
        m18199e();
        webView.setVisibility(0);
    }

    /* renamed from: a */
    public void mo6135a(Bundle bundle) {
        m18195b(bundle);
        m18198d();
    }

    /* renamed from: a */
    public void mo6137a(WebViewException webViewException) {
        m18196b(webViewException);
        m18198d();
    }
}
