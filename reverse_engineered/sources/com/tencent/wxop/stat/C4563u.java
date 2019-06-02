package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.p201a.C4513e;
import com.tencent.wxop.stat.p201a.C4515b;
import com.tencent.wxop.stat.p201a.C4516c;

/* renamed from: com.tencent.wxop.stat.u */
final class C4563u implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Context f16153a;
    /* renamed from: b */
    final /* synthetic */ StatSpecifyReportedInfo f16154b;
    /* renamed from: c */
    final /* synthetic */ C4516c f16155c;

    C4563u(Context context, StatSpecifyReportedInfo statSpecifyReportedInfo, C4516c c4516c) {
        this.f16153a = context;
        this.f16154b = statSpecifyReportedInfo;
        this.f16155c = c4516c;
    }

    public final void run() {
        try {
            C4513e c4515b = new C4515b(this.f16153a, StatServiceImpl.m17874a(this.f16153a, false, this.f16154b), this.f16155c.f15910a, this.f16154b);
            c4515b.m17916b().f15912c = this.f16155c.f15912c;
            new aq(c4515b).m17959a();
        } catch (Throwable th) {
            StatServiceImpl.f15886q.m18011e(th);
            StatServiceImpl.m17880a(this.f16153a, th);
        }
    }
}
