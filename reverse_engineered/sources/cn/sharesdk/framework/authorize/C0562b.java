package cn.sharesdk.framework.authorize;

import android.webkit.WebView;
import cn.sharesdk.framework.C0561d;

/* compiled from: AuthorizeWebviewClient */
/* renamed from: cn.sharesdk.framework.authorize.b */
public abstract class C0562b extends C0561d {
    /* renamed from: a */
    protected C0584e f1188a;
    /* renamed from: b */
    protected String f1189b;
    /* renamed from: c */
    protected AuthorizeListener f1190c;

    public C0562b(C0584e c0584e) {
        this.f1188a = c0584e;
        AuthorizeHelper a = c0584e.m2022a();
        this.f1189b = a.getRedirectUri();
        this.f1190c = a.getAuthorizeListener();
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
        webView.stopLoading();
        AuthorizeListener authorizeListener = this.f1188a.m2022a().getAuthorizeListener();
        this.f1188a.finish();
        if (authorizeListener != null) {
            authorizeListener.onError(new Throwable(str + " (" + i + "): " + str2));
        }
    }
}
