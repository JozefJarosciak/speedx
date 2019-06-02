package com.tencent.bugly;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.bugly.crashreport.common.info.C4417a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.BuglyBroadcastRecevier;
import com.tencent.bugly.crashreport.crash.C4436c;
import com.tencent.bugly.crashreport.crash.C4439d;
import com.tencent.bugly.proguard.C4461m;
import com.tencent.bugly.proguard.C4475w;

/* compiled from: BUGLY */
public class CrashModule extends C4401a {
    public static final int MODULE_ID = 1004;
    /* renamed from: c */
    private static int f15197c = 0;
    /* renamed from: d */
    private static boolean f15198d = false;
    /* renamed from: e */
    private static CrashModule f15199e = new CrashModule();
    /* renamed from: a */
    private long f15200a;
    /* renamed from: b */
    private BuglyStrategy$a f15201b;

    public static CrashModule getInstance() {
        f15199e.id = 1004;
        return f15199e;
    }

    public static boolean hasInitialized() {
        return f15198d;
    }

    public synchronized void init(Context context, boolean z, BuglyStrategy buglyStrategy) {
        if (context != null) {
            if (!f15198d) {
                C4475w.m17747a("Initializing crash module.", new Object[0]);
                C4461m a = C4461m.m17648a();
                int i = f15197c + 1;
                f15197c = i;
                a.m17659a(1004, i);
                f15198d = true;
                CrashReport.setContext(context);
                m17246a(context, buglyStrategy);
                C4436c.m17446a(1004, context, z, this.f15201b, null, null);
                C4436c a2 = C4436c.m17444a();
                a2.m17455e();
                if (buglyStrategy == null || buglyStrategy.isEnableNativeCrashMonitor()) {
                    a2.m17457g();
                } else {
                    C4475w.m17747a("[crash] Closed native crash monitor!", new Object[0]);
                    a2.m17456f();
                }
                if (buglyStrategy == null || buglyStrategy.isEnableANRCrashMonitor()) {
                    a2.m17458h();
                } else {
                    C4475w.m17747a("[crash] Closed ANR monitor!", new Object[0]);
                    a2.m17459i();
                }
                C4439d.m17464a(context);
                BuglyBroadcastRecevier instance = BuglyBroadcastRecevier.getInstance();
                instance.addFilter("android.net.conn.CONNECTIVITY_CHANGE");
                instance.regist(context);
                a = C4461m.m17648a();
                i = f15197c - 1;
                f15197c = i;
                a.m17659a(1004, i);
            }
        }
    }

    /* renamed from: a */
    private synchronized void m17246a(Context context, BuglyStrategy buglyStrategy) {
        if (buglyStrategy != null) {
            Object libBuglySOFilePath = buglyStrategy.getLibBuglySOFilePath();
            if (!TextUtils.isEmpty(libBuglySOFilePath)) {
                C4417a.m17303a(context).f15298m = libBuglySOFilePath;
                C4475w.m17747a("setted libBugly.so file path :%s", libBuglySOFilePath);
            }
            if (buglyStrategy.getCrashHandleCallback() != null) {
                this.f15201b = buglyStrategy.getCrashHandleCallback();
                C4475w.m17747a("setted CrashHanldeCallback", new Object[0]);
            }
            if (buglyStrategy.getAppReportDelay() > 0) {
                this.f15200a = buglyStrategy.getAppReportDelay();
                C4475w.m17747a("setted delay: %d", Long.valueOf(this.f15200a));
            }
        }
    }

    public void onServerStrategyChanged(StrategyBean strategyBean) {
        if (strategyBean != null) {
            C4436c a = C4436c.m17444a();
            if (a != null) {
                a.m17449a(strategyBean);
            }
        }
    }

    public String[] getTables() {
        return new String[]{"t_cr"};
    }
}
