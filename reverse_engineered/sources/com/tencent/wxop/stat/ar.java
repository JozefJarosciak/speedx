package com.tencent.wxop.stat;

class ar implements C4526h {
    /* renamed from: a */
    final /* synthetic */ aq f15991a;

    ar(aq aqVar) {
        this.f15991a = aqVar;
    }

    /* renamed from: a */
    public void mo6119a() {
        StatServiceImpl.m17889c();
        if (au.m17982b().m17995a() >= StatConfig.getMaxBatchReportCount()) {
            au.m17982b().m17996a(StatConfig.getMaxBatchReportCount());
        }
    }

    /* renamed from: b */
    public void mo6120b() {
        StatServiceImpl.m17890d();
    }
}
