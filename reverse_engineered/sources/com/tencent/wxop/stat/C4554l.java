package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.common.C4539k;

/* renamed from: com.tencent.wxop.stat.l */
final class C4554l implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Context f16137a;

    C4554l(Context context) {
        this.f16137a = context;
    }

    public final void run() {
        C4525a.m17934a(StatServiceImpl.f15889t).m17949h();
        C4539k.m18041a(this.f16137a, true);
        au.m17968a(this.f16137a);
        C4551i.m18122b(this.f16137a);
        StatServiceImpl.f15887r = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new ao());
        if (StatConfig.getStatSendStrategy() == StatReportStrategy.APP_LAUNCH) {
            StatServiceImpl.commitEvents(this.f16137a, -1);
        }
        if (StatConfig.isDebugEnable()) {
            StatServiceImpl.f15886q.m18009d("Init MTA StatService success.");
        }
    }
}
