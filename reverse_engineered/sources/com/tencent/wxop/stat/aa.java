package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.p201a.C4520h;

final class aa implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Context f15950a;
    /* renamed from: b */
    final /* synthetic */ StatSpecifyReportedInfo f15951b;
    /* renamed from: c */
    final /* synthetic */ StatAppMonitor f15952c;

    aa(Context context, StatSpecifyReportedInfo statSpecifyReportedInfo, StatAppMonitor statAppMonitor) {
        this.f15950a = context;
        this.f15951b = statSpecifyReportedInfo;
        this.f15952c = statAppMonitor;
    }

    public final void run() {
        try {
            new aq(new C4520h(this.f15950a, StatServiceImpl.m17874a(this.f15950a, false, this.f15951b), this.f15952c, this.f15951b)).m17959a();
        } catch (Throwable th) {
            StatServiceImpl.f15886q.m18011e(th);
            StatServiceImpl.m17880a(this.f15950a, th);
        }
    }
}
