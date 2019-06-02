package com.tencent.wxop.stat;

import android.content.Context;
import java.util.Map;

final class af implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Context f15960a;
    /* renamed from: b */
    final /* synthetic */ Map f15961b;
    /* renamed from: c */
    final /* synthetic */ StatSpecifyReportedInfo f15962c;

    af(Context context, Map map, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        this.f15960a = context;
        this.f15961b = map;
        this.f15962c = statSpecifyReportedInfo;
    }

    public final void run() {
        try {
            new Thread(new ap(this.f15960a, this.f15961b, this.f15962c), "NetworkMonitorTask").start();
        } catch (Throwable th) {
            StatServiceImpl.f15886q.m18011e(th);
            StatServiceImpl.m17880a(this.f15960a, th);
        }
    }
}
