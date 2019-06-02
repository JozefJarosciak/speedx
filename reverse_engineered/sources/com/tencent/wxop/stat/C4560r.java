package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.p201a.C4517d;

/* renamed from: com.tencent.wxop.stat.r */
final class C4560r implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Throwable f16147a;
    /* renamed from: b */
    final /* synthetic */ Context f16148b;
    /* renamed from: c */
    final /* synthetic */ StatSpecifyReportedInfo f16149c;

    C4560r(Throwable th, Context context, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        this.f16147a = th;
        this.f16148b = context;
        this.f16149c = statSpecifyReportedInfo;
    }

    public final void run() {
        if (this.f16147a == null) {
            StatServiceImpl.f15886q.error((Object) "The Throwable error message of StatService.reportException() can not be null!");
        } else {
            new aq(new C4517d(this.f16148b, StatServiceImpl.m17874a(this.f16148b, false, this.f16149c), 1, this.f16147a, this.f16149c)).m17959a();
        }
    }
}
