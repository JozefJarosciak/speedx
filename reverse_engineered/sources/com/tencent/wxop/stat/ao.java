package com.tencent.wxop.stat;

import com.tencent.wxop.stat.p201a.C4517d;
import java.lang.Thread.UncaughtExceptionHandler;

class ao implements UncaughtExceptionHandler {
    ao() {
    }

    public void uncaughtException(Thread thread, Throwable th) {
        if (StatConfig.isEnableStatService() && StatServiceImpl.f15889t != null) {
            if (StatConfig.isAutoExceptionCaught()) {
                au.m17968a(StatServiceImpl.f15889t).m17997a(new C4517d(StatServiceImpl.f15889t, StatServiceImpl.m17874a(StatServiceImpl.f15889t, false, null), 2, th, thread, null), null, false, true);
                StatServiceImpl.f15886q.debug("MTA has caught the following uncaught exception:");
                StatServiceImpl.f15886q.error(th);
            }
            StatServiceImpl.flushDataToDB(StatServiceImpl.f15889t);
            if (StatServiceImpl.f15887r != null) {
                StatServiceImpl.f15886q.m18009d("Call the original uncaught exception handler.");
                if (!(StatServiceImpl.f15887r instanceof ao)) {
                    StatServiceImpl.f15887r.uncaughtException(thread, th);
                }
            }
        }
    }
}
