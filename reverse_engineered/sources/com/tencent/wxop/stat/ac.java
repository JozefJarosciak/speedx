package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.p201a.C4513e;
import com.tencent.wxop.stat.p201a.C4515b;
import com.tencent.wxop.stat.p201a.C4516c;

final class ac implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Context f15953a;
    /* renamed from: b */
    final /* synthetic */ StatSpecifyReportedInfo f15954b;
    /* renamed from: c */
    final /* synthetic */ C4516c f15955c;
    /* renamed from: d */
    final /* synthetic */ int f15956d;

    ac(Context context, StatSpecifyReportedInfo statSpecifyReportedInfo, C4516c c4516c, int i) {
        this.f15953a = context;
        this.f15954b = statSpecifyReportedInfo;
        this.f15955c = c4516c;
        this.f15956d = i;
    }

    public final void run() {
        try {
            C4513e c4515b = new C4515b(this.f15953a, StatServiceImpl.m17874a(this.f15953a, false, this.f15954b), this.f15955c.f15910a, this.f15954b);
            c4515b.m17916b().f15912c = this.f15955c.f15912c;
            Long valueOf = Long.valueOf((long) this.f15956d);
            c4515b.m17914a(Long.valueOf(valueOf.longValue() <= 0 ? 1 : valueOf.longValue()).longValue());
            new aq(c4515b).m17959a();
        } catch (Throwable th) {
            StatServiceImpl.f15886q.m18011e(th);
            StatServiceImpl.m17880a(this.f15953a, th);
        }
    }
}
