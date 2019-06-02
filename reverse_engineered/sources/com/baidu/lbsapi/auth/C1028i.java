package com.baidu.lbsapi.auth;

import java.util.Hashtable;

/* renamed from: com.baidu.lbsapi.auth.i */
class C1028i implements Runnable {
    /* renamed from: a */
    final /* synthetic */ int f2288a;
    /* renamed from: b */
    final /* synthetic */ boolean f2289b;
    /* renamed from: c */
    final /* synthetic */ String f2290c;
    /* renamed from: d */
    final /* synthetic */ String f2291d;
    /* renamed from: e */
    final /* synthetic */ Hashtable f2292e;
    /* renamed from: f */
    final /* synthetic */ LBSAuthManager f2293f;

    C1028i(LBSAuthManager lBSAuthManager, int i, boolean z, String str, String str2, Hashtable hashtable) {
        this.f2293f = lBSAuthManager;
        this.f2288a = i;
        this.f2289b = z;
        this.f2290c = str;
        this.f2291d = str2;
        this.f2292e = hashtable;
    }

    public void run() {
        if (C1017a.f2273a) {
            C1017a.m3589a("status = " + this.f2288a + "; forced = " + this.f2289b + "checkAK = " + this.f2293f.m3583b(this.f2290c));
        }
        if (this.f2288a == LBSAuthManager.CODE_UNAUTHENTICATE || this.f2289b || this.f2288a == -1 || this.f2293f.m3583b(this.f2290c)) {
            if (C1017a.f2273a) {
                C1017a.m3589a("authenticate sendAuthRequest");
            }
            String[] b = C1019b.m3598b(LBSAuthManager.f2265a);
            if (C1017a.f2273a) {
                C1017a.m3589a("authStrings.length:" + b.length);
            }
            if (b == null || b.length <= 1) {
                this.f2293f.m3579a(this.f2289b, this.f2291d, this.f2292e, this.f2290c);
                return;
            }
            if (C1017a.f2273a) {
                C1017a.m3589a("more sha1 auth");
            }
            this.f2293f.m3580a(this.f2289b, this.f2291d, this.f2292e, b, this.f2290c);
        } else if (LBSAuthManager.CODE_AUTHENTICATING == this.f2288a) {
            if (C1017a.f2273a) {
                C1017a.m3589a("authenticate wait  ");
            }
            LBSAuthManager.f2266d.m3629b();
            this.f2293f.m3578a(null, this.f2290c);
        } else {
            if (C1017a.f2273a) {
                C1017a.m3589a("authenticate else  ");
            }
            this.f2293f.m3578a(null, this.f2290c);
        }
    }
}
