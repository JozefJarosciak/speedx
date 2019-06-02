package com.tencent.wxop.stat;

class at implements C4526h {
    /* renamed from: a */
    final /* synthetic */ aq f15993a;

    at(aq aqVar) {
        this.f15993a = aqVar;
    }

    /* renamed from: a */
    public void mo6119a() {
        StatServiceImpl.m17889c();
        if (au.m17982b().f15997a > 0) {
            StatServiceImpl.commitEvents(this.f15993a.f15989d, -1);
        }
    }

    /* renamed from: b */
    public void mo6120b() {
        au.m17982b().m17997a(this.f15993a.f15986a, null, this.f15993a.f15988c, true);
        StatServiceImpl.m17890d();
    }
}
