package com.tencent.bugly.crashreport.crash;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.tencent.bugly.crashreport.biz.C4415b;
import com.tencent.bugly.crashreport.common.info.C4417a;
import com.tencent.bugly.crashreport.common.info.C4418b;
import com.tencent.bugly.crashreport.common.strategy.C4421a;
import com.tencent.bugly.proguard.C4471t;
import com.tencent.bugly.proguard.C4475w;

/* compiled from: BUGLY */
public class BuglyBroadcastRecevier extends BroadcastReceiver {
    /* renamed from: d */
    private static BuglyBroadcastRecevier f15347d = null;
    /* renamed from: a */
    private IntentFilter f15348a = new IntentFilter();
    /* renamed from: b */
    private Context f15349b;
    /* renamed from: c */
    private String f15350c;
    /* renamed from: e */
    private boolean f15351e = true;

    public static synchronized BuglyBroadcastRecevier getInstance() {
        BuglyBroadcastRecevier buglyBroadcastRecevier;
        synchronized (BuglyBroadcastRecevier.class) {
            if (f15347d == null) {
                f15347d = new BuglyBroadcastRecevier();
            }
            buglyBroadcastRecevier = f15347d;
        }
        return buglyBroadcastRecevier;
    }

    protected void finalize() throws Throwable {
        super.finalize();
        if (this.f15349b != null) {
            this.f15349b.unregisterReceiver(this);
        }
    }

    public synchronized void addFilter(String str) {
        if (!this.f15348a.hasAction(str)) {
            this.f15348a.addAction(str);
        }
        C4475w.m17751c("add action %s", str);
    }

    public synchronized void regist(Context context) {
        try {
            C4475w.m17747a("regis BC", new Object[0]);
            this.f15349b = context;
            context.registerReceiver(this, this.f15348a);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public synchronized void unregist(Context context) {
        try {
            C4475w.m17747a("unregis BC", new Object[0]);
            context.unregisterReceiver(this);
            this.f15349b = context;
        } catch (Throwable th) {
            if (!C4475w.m17748a(th)) {
                th.printStackTrace();
            }
        }
    }

    public final void onReceive(Context context, Intent intent) {
        try {
            m17397a(context, intent);
        } catch (Throwable th) {
            if (!C4475w.m17748a(th)) {
                th.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    private synchronized boolean m17397a(Context context, Intent intent) {
        boolean z = true;
        synchronized (this) {
            if (!(context == null || intent == null)) {
                if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                    if (this.f15351e) {
                        this.f15351e = false;
                    } else {
                        String e = C4418b.m17368e(this.f15349b);
                        C4475w.m17751c("is Connect BC " + e, new Object[0]);
                        C4475w.m17747a("network %s changed to %s", this.f15350c, e);
                        if (e == null) {
                            this.f15350c = null;
                        } else {
                            String str = this.f15350c;
                            this.f15350c = e;
                            long currentTimeMillis = System.currentTimeMillis();
                            C4421a a = C4421a.m17388a();
                            C4471t a2 = C4471t.m17703a();
                            C4417a a3 = C4417a.m17303a(context);
                            if (a == null || a2 == null || a3 == null) {
                                C4475w.m17752d("not inited BC not work", new Object[0]);
                            } else if (!e.equals(str)) {
                                if (currentTimeMillis - a2.m17721a(C4436c.f15450a) > 30000) {
                                    C4475w.m17747a("try to upload crash on network changed.", new Object[0]);
                                    C4436c a4 = C4436c.m17444a();
                                    if (a4 != null) {
                                        a4.m17448a(0);
                                    }
                                }
                                if (currentTimeMillis - a2.m17721a(1001) > 30000) {
                                    C4475w.m17747a("try to upload userinfo on network changed.", new Object[0]);
                                    C4415b.f15243a.m17270b();
                                }
                            }
                        }
                    }
                }
            }
            z = false;
        }
        return z;
    }
}
