package cn.sharesdk.framework.authorize;

import android.content.Intent;

/* compiled from: SSOAuthorizeActivity */
/* renamed from: cn.sharesdk.framework.authorize.c */
public class C0579c extends C0578a {
    /* renamed from: b */
    protected SSOListener f1247b;
    /* renamed from: c */
    private C0558d f1248c;

    /* renamed from: a */
    public void m2024a(SSOListener sSOListener) {
        this.f1247b = sSOListener;
    }

    public void onCreate() {
        this.f1248c = this.a.getSSOProcessor(this);
        if (this.f1248c == null) {
            finish();
            AuthorizeListener authorizeListener = this.a.getAuthorizeListener();
            if (authorizeListener != null) {
                authorizeListener.onError(new Throwable("Failed to start SSO for " + this.a.getPlatform().getName()));
                return;
            }
            return;
        }
        this.f1248c.m1937a(32973);
        this.f1248c.mo2262a();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        this.f1248c.mo2263a(i, i2, intent);
    }

    public void onNewIntent(Intent intent) {
        this.f1248c.m1939a(intent);
    }
}
