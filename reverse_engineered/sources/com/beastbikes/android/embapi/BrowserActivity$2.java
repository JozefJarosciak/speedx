package com.beastbikes.android.embapi;

import android.net.Uri;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient.FileChooserParams;
import android.webkit.WebView;
import com.beastbikes.framework.ui.android.DefaultWebChromeClient;
import com.beastbikes.framework.ui.android.WebActivity;

class BrowserActivity$2 extends DefaultWebChromeClient {
    /* renamed from: a */
    final /* synthetic */ BrowserActivity f8219a;

    BrowserActivity$2(BrowserActivity browserActivity, WebActivity webActivity) {
        this.f8219a = browserActivity;
        super(webActivity);
    }

    public void onReceivedTitle(WebView webView, String str) {
        super.onReceivedTitle(webView, str);
        this.f8219a.f4410d = str;
        this.f8219a.setTitle(str);
    }

    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, FileChooserParams fileChooserParams) {
        BrowserActivity.a(this.f8219a, valueCallback);
        return true;
    }

    public void onGeolocationPermissionsShowPrompt(String str, Callback callback) {
        callback.invoke(str, true, false);
        super.onGeolocationPermissionsShowPrompt(str, callback);
    }
}
