package cn.sharesdk.framework;

import cn.sharesdk.framework.authorize.AuthorizeHelper;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.authorize.C0558d;
import cn.sharesdk.framework.authorize.C0579c;
import cn.sharesdk.framework.authorize.C0584e;
import cn.sharesdk.framework.authorize.SSOListener;

/* compiled from: PlatformHelper */
/* renamed from: cn.sharesdk.framework.b */
public abstract class C0565b implements AuthorizeHelper {
    /* renamed from: a */
    protected Platform f1193a;
    /* renamed from: b */
    private AuthorizeListener f1194b;
    /* renamed from: c */
    private SSOListener f1195c;

    public C0565b(Platform platform) {
        this.f1193a = platform;
    }

    public Platform getPlatform() {
        return this.f1193a;
    }

    public C0558d getSSOProcessor(C0579c c0579c) {
        return null;
    }

    /* renamed from: c */
    public int m1951c() {
        return this.f1193a.getPlatformId();
    }

    /* renamed from: b */
    protected void m1950b(AuthorizeListener authorizeListener) {
        this.f1194b = authorizeListener;
        C0584e c0584e = new C0584e();
        c0584e.m2028a(this.f1194b);
        c0584e.m2023a(this);
    }

    public AuthorizeListener getAuthorizeListener() {
        return this.f1194b;
    }

    /* renamed from: a */
    protected void m1949a(SSOListener sSOListener) {
        this.f1195c = sSOListener;
        C0579c c0579c = new C0579c();
        c0579c.m2024a(sSOListener);
        c0579c.m2023a(this);
    }

    public SSOListener getSSOListener() {
        return this.f1195c;
    }
}
