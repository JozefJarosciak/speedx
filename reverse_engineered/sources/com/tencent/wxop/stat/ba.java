package com.tencent.wxop.stat;

import java.util.List;

class ba implements C4526h {
    /* renamed from: a */
    final /* synthetic */ List f16026a;
    /* renamed from: b */
    final /* synthetic */ boolean f16027b;
    /* renamed from: c */
    final /* synthetic */ au f16028c;

    ba(au auVar, List list, boolean z) {
        this.f16028c = auVar;
        this.f16026a = list;
        this.f16027b = z;
    }

    /* renamed from: a */
    public void mo6119a() {
        StatServiceImpl.m17889c();
        this.f16028c.m18000a(this.f16026a, this.f16027b, true);
    }

    /* renamed from: b */
    public void mo6120b() {
        StatServiceImpl.m17890d();
        this.f16028c.m17999a(this.f16026a, 1, this.f16027b, true);
    }
}
