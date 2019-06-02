package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.common.C4539k;
import java.util.Timer;
import java.util.TimerTask;

/* renamed from: com.tencent.wxop.stat.d */
public class C4547d {
    /* renamed from: b */
    private static volatile C4547d f16118b = null;
    /* renamed from: a */
    private Timer f16119a = null;
    /* renamed from: c */
    private Context f16120c = null;

    private C4547d(Context context) {
        this.f16120c = context.getApplicationContext();
        this.f16119a = new Timer(false);
    }

    /* renamed from: a */
    public static C4547d m18110a(Context context) {
        if (f16118b == null) {
            synchronized (C4547d.class) {
                if (f16118b == null) {
                    f16118b = new C4547d(context);
                }
            }
        }
        return f16118b;
    }

    /* renamed from: a */
    public void m18111a() {
        if (StatConfig.getStatSendStrategy() == StatReportStrategy.PERIOD) {
            long sendPeriodMinutes = (long) ((StatConfig.getSendPeriodMinutes() * 60) * 1000);
            if (StatConfig.isDebugEnable()) {
                C4539k.m18052b().m18012i("setupPeriodTimer delay:" + sendPeriodMinutes);
            }
            m18112a(new C4548e(this), sendPeriodMinutes);
        }
    }

    /* renamed from: a */
    public void m18112a(TimerTask timerTask, long j) {
        if (this.f16119a != null) {
            if (StatConfig.isDebugEnable()) {
                C4539k.m18052b().m18012i("setupPeriodTimer schedule delay:" + j);
            }
            this.f16119a.schedule(timerTask, j);
        } else if (StatConfig.isDebugEnable()) {
            C4539k.m18052b().m18014w("setupPeriodTimer schedule timer == null");
        }
    }
}
