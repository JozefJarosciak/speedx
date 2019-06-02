package com.baidu.lbsapi.auth;

import com.baidu.lbsapi.auth.C1021c.C1020a;

/* renamed from: com.baidu.lbsapi.auth.j */
class C1029j implements C1020a<String> {
    /* renamed from: a */
    final /* synthetic */ String f2294a;
    /* renamed from: b */
    final /* synthetic */ LBSAuthManager f2295b;

    C1029j(LBSAuthManager lBSAuthManager, String str) {
        this.f2295b = lBSAuthManager;
        this.f2294a = str;
    }

    /* renamed from: a */
    public void m3625a(String str) {
        this.f2295b.m3578a(str, this.f2294a);
    }
}
