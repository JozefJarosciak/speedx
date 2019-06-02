package com.tencent.wxop.stat;

import android.content.Context;

/* renamed from: com.tencent.wxop.stat.w */
final class C4565w implements Runnable {
    /* renamed from: a */
    final /* synthetic */ String f16159a;
    /* renamed from: b */
    final /* synthetic */ Context f16160b;
    /* renamed from: c */
    final /* synthetic */ StatSpecifyReportedInfo f16161c;

    C4565w(String str, Context context, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        this.f16159a = str;
        this.f16160b = context;
        this.f16161c = statSpecifyReportedInfo;
    }

    public final void run() {
        try {
            synchronized (StatServiceImpl.f15884o) {
                if (StatServiceImpl.f15884o.size() >= StatConfig.getMaxParallelTimmingEvents()) {
                    StatServiceImpl.f15886q.error("The number of page events exceeds the maximum value " + Integer.toString(StatConfig.getMaxParallelTimmingEvents()));
                    return;
                }
                StatServiceImpl.f15882m = this.f16159a;
                if (StatServiceImpl.f15884o.containsKey(StatServiceImpl.f15882m)) {
                    StatServiceImpl.f15886q.m18010e("Duplicate PageID : " + StatServiceImpl.f15882m + ", onResume() repeated?");
                    return;
                }
                StatServiceImpl.f15884o.put(StatServiceImpl.f15882m, Long.valueOf(System.currentTimeMillis()));
                StatServiceImpl.m17874a(this.f16160b, true, this.f16161c);
            }
        } catch (Throwable th) {
            StatServiceImpl.f15886q.m18011e(th);
            StatServiceImpl.m17880a(this.f16160b, th);
        }
    }
}
