package cn.jpush.android.p003b.p004a;

import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebView;

/* renamed from: cn.jpush.android.b.a.a */
public final class C0421a extends C0420c {
    public C0421a(String str, Class cls) {
        super(str, cls);
    }

    public final boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
        return super.onJsAlert(webView, str, str2, jsResult);
    }

    public final boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        return super.onJsPrompt(webView, str, str2, str3, jsPromptResult);
    }

    public final void onProgressChanged(WebView webView, int i) {
        super.onProgressChanged(webView, i);
    }
}
