package com.tencent.bugly.crashreport.crash;

import android.content.Context;
import ch.qos.logback.core.spi.AbstractComponentTracker;
import com.tencent.bugly.BuglyStrategy$a;
import com.tencent.bugly.crashreport.common.info.C4417a;
import com.tencent.bugly.crashreport.common.strategy.C4421a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.anr.C4431b;
import com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler;
import com.tencent.bugly.proguard.C4462n;
import com.tencent.bugly.proguard.C4464o;
import com.tencent.bugly.proguard.C4466q;
import com.tencent.bugly.proguard.C4471t;
import com.tencent.bugly.proguard.C4474v;
import com.tencent.bugly.proguard.C4475w;
import com.tencent.bugly.proguard.C4479y;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.crashreport.crash.c */
public final class C4436c {
    /* renamed from: a */
    public static int f15450a = 0;
    /* renamed from: b */
    public static boolean f15451b = false;
    /* renamed from: c */
    public static boolean f15452c = true;
    /* renamed from: d */
    public static int f15453d = 20000;
    /* renamed from: e */
    public static int f15454e = 20000;
    /* renamed from: f */
    public static long f15455f = 604800000;
    /* renamed from: g */
    public static String f15456g = null;
    /* renamed from: h */
    public static boolean f15457h = false;
    /* renamed from: i */
    public static String f15458i = null;
    /* renamed from: j */
    public static int f15459j = 5000;
    /* renamed from: k */
    public static boolean f15460k = true;
    /* renamed from: l */
    public static String f15461l = null;
    /* renamed from: m */
    public static String f15462m = null;
    /* renamed from: p */
    private static C4436c f15463p;
    /* renamed from: n */
    public final C4433b f15464n;
    /* renamed from: o */
    private final Context f15465o;
    /* renamed from: q */
    private final C4440e f15466q;
    /* renamed from: r */
    private final NativeCrashHandler f15467r;
    /* renamed from: s */
    private C4421a f15468s = C4421a.m17388a();
    /* renamed from: t */
    private C4474v f15469t;
    /* renamed from: u */
    private final C4431b f15470u;
    /* renamed from: v */
    private Boolean f15471v;

    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.crashreport.crash.c$2 */
    class C44352 extends Thread {
        /* renamed from: a */
        private /* synthetic */ C4436c f15449a;

        C44352(C4436c c4436c) {
            this.f15449a = c4436c;
        }

        public final void run() {
            if (C4479y.m17790a(this.f15449a.f15465o, "local_crash_lock", (long) AbstractComponentTracker.LINGERING_TIMEOUT)) {
                List a = this.f15449a.f15464n.m17437a();
                if (a != null && a.size() > 0) {
                    List arrayList;
                    int size = a.size();
                    if (((long) size) > 100) {
                        arrayList = new ArrayList();
                        Collections.sort(a);
                        for (int i = 0; ((long) i) < 100; i++) {
                            arrayList.add(a.get((size - 1) - i));
                        }
                    } else {
                        arrayList = a;
                    }
                    this.f15449a.f15464n.m17439a(arrayList, 0, false, false, false);
                }
                C4479y.m17807b(this.f15449a.f15465o, "local_crash_lock");
            }
        }
    }

    private C4436c(int i, Context context, C4474v c4474v, boolean z, BuglyStrategy$a buglyStrategy$a, C4462n c4462n, String str) {
        f15450a = i;
        Context a = C4479y.m17772a(context);
        this.f15465o = a;
        this.f15469t = c4474v;
        this.f15464n = new C4433b(i, a, C4471t.m17703a(), C4464o.m17672a(), this.f15468s, buglyStrategy$a, c4462n);
        C4417a a2 = C4417a.m17303a(a);
        this.f15466q = new C4440e(a, this.f15464n, this.f15468s, a2);
        this.f15467r = NativeCrashHandler.getInstance(a, a2, this.f15464n, this.f15468s, c4474v, z, str);
        a2.f15263C = this.f15467r;
        this.f15470u = new C4431b(a, this.f15468s, a2, c4474v, this.f15464n);
    }

    /* renamed from: a */
    public static synchronized void m17446a(int i, Context context, boolean z, BuglyStrategy$a buglyStrategy$a, C4462n c4462n, String str) {
        synchronized (C4436c.class) {
            if (f15463p == null) {
                f15463p = new C4436c(1004, context, C4474v.m17740a(), z, buglyStrategy$a, null, null);
            }
        }
    }

    /* renamed from: a */
    public static synchronized C4436c m17444a() {
        C4436c c4436c;
        synchronized (C4436c.class) {
            c4436c = f15463p;
        }
        return c4436c;
    }

    /* renamed from: a */
    public final void m17449a(StrategyBean strategyBean) {
        this.f15466q.m17475a(strategyBean);
        this.f15467r.onStrategyChanged(strategyBean);
        this.f15470u.m17418a(strategyBean);
        C4474v.m17740a().m17742a(new C44352(this), 0);
    }

    /* renamed from: b */
    public final boolean m17452b() {
        Boolean bool = this.f15471v;
        if (bool != null) {
            return bool.booleanValue();
        }
        String str = C4417a.m17304b().f15289d;
        List<C4466q> a = C4464o.m17672a().m17689a(1);
        List arrayList = new ArrayList();
        if (a == null || a.size() <= 0) {
            this.f15471v = Boolean.valueOf(false);
            return false;
        }
        for (C4466q c4466q : a) {
            if (str.equals(c4466q.f15717c)) {
                this.f15471v = Boolean.valueOf(true);
                arrayList.add(c4466q);
            }
        }
        if (arrayList.size() > 0) {
            C4464o.m17672a().m17691a(arrayList);
        }
        return true;
    }

    /* renamed from: c */
    public final synchronized void m17453c() {
        this.f15466q.m17474a();
        this.f15467r.setUserOpened(true);
        this.f15470u.m17420a(true);
    }

    /* renamed from: d */
    public final synchronized void m17454d() {
        this.f15466q.m17477b();
        this.f15467r.setUserOpened(false);
        this.f15470u.m17420a(false);
    }

    /* renamed from: e */
    public final void m17455e() {
        this.f15466q.m17474a();
    }

    /* renamed from: f */
    public final void m17456f() {
        this.f15467r.setUserOpened(false);
    }

    /* renamed from: g */
    public final void m17457g() {
        this.f15467r.setUserOpened(true);
    }

    /* renamed from: h */
    public final void m17458h() {
        this.f15470u.m17420a(true);
    }

    /* renamed from: i */
    public final void m17459i() {
        this.f15470u.m17420a(false);
    }

    /* renamed from: j */
    public final synchronized void m17460j() {
        this.f15467r.testNativeCrash();
    }

    /* renamed from: k */
    public final synchronized void m17461k() {
        int i = 0;
        synchronized (this) {
            C4431b c4431b = this.f15470u;
            while (true) {
                int i2 = i + 1;
                if (i >= 30) {
                    break;
                }
                try {
                    C4475w.m17747a("try main sleep for make a test anr! try:%d/30 , kill it if you don't want to wait!", Integer.valueOf(i2));
                    C4479y.m17804b(5000);
                    i = i2;
                } catch (Throwable th) {
                    if (!C4475w.m17748a(th)) {
                        th.printStackTrace();
                    }
                }
            }
        }
    }

    /* renamed from: l */
    public final boolean m17462l() {
        return this.f15470u.m17421a();
    }

    /* renamed from: a */
    public final void m17451a(Thread thread, Throwable th, boolean z, String str, byte[] bArr, boolean z2) {
        final Thread thread2 = thread;
        final Throwable th2 = th;
        final byte[] bArr2 = null;
        final boolean z3 = z2;
        this.f15469t.m17741a(new Runnable(this, false, null) {
            /* renamed from: g */
            private /* synthetic */ C4436c f15448g;

            public final void run() {
                try {
                    C4475w.m17751c("post a throwable %b", Boolean.valueOf(false));
                    this.f15448g.f15466q.m17476a(thread2, th2, false, null, bArr2);
                    if (z3) {
                        C4475w.m17747a("clear user datas", new Object[0]);
                        C4417a.m17303a(this.f15448g.f15465o).m17306A();
                    }
                } catch (Throwable th) {
                    if (!C4475w.m17750b(th)) {
                        th.printStackTrace();
                    }
                    C4475w.m17753e("java catch error: %s", th2.toString());
                }
            }
        });
    }

    /* renamed from: a */
    public final void m17450a(CrashDetailBean crashDetailBean) {
        this.f15464n.m17443c(crashDetailBean);
    }

    /* renamed from: a */
    public final void m17448a(long j) {
        C4474v.m17740a().m17742a(new C44352(this), 0);
    }
}
