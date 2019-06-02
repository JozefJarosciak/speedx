package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.p201a.C4513e;
import com.tencent.wxop.stat.p201a.C4515b;
import com.tencent.wxop.stat.p201a.C4516c;

/* renamed from: com.tencent.wxop.stat.s */
final class C4561s implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Context f16150a;
    /* renamed from: b */
    final /* synthetic */ StatSpecifyReportedInfo f16151b;
    /* renamed from: c */
    final /* synthetic */ C4516c f16152c;

    C4561s(Context context, StatSpecifyReportedInfo statSpecifyReportedInfo, C4516c c4516c) {
        this.f16150a = context;
        this.f16151b = statSpecifyReportedInfo;
        this.f16152c = c4516c;
    }

    public final void run() {
        try {
            C4513e c4515b = new C4515b(this.f16150a, StatServiceImpl.m17874a(this.f16150a, false, this.f16151b), this.f16152c.f15910a, this.f16151b);
            c4515b.m17916b().f15911b = this.f16152c.f15911b;
            new aq(c4515b).m17959a();
        } catch (Throwable th) {
            StatServiceImpl.f15886q.m18011e(th);
            StatServiceImpl.m17880a(this.f16150a, th);
        }
    }
}
