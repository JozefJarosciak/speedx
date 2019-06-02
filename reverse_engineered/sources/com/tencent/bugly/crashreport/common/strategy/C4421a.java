package com.tencent.bugly.crashreport.common.strategy;

import android.content.Context;
import android.os.Parcelable;
import com.alipay.sdk.cons.C0844a;
import com.tencent.bugly.C4401a;
import com.tencent.bugly.crashreport.biz.C4415b;
import com.tencent.bugly.crashreport.common.info.C4417a;
import com.tencent.bugly.proguard.C4464o;
import com.tencent.bugly.proguard.C4466q;
import com.tencent.bugly.proguard.C4474v;
import com.tencent.bugly.proguard.C4475w;
import com.tencent.bugly.proguard.C4479y;
import com.tencent.bugly.proguard.ao;
import java.util.List;
import java.util.Map;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.crashreport.common.strategy.a */
public final class C4421a {
    /* renamed from: a */
    public static int f15340a = 1000;
    /* renamed from: b */
    private static C4421a f15341b = null;
    /* renamed from: c */
    private final List<C4401a> f15342c;
    /* renamed from: d */
    private final C4474v f15343d;
    /* renamed from: e */
    private final StrategyBean f15344e;
    /* renamed from: f */
    private StrategyBean f15345f = null;
    /* renamed from: g */
    private Context f15346g;

    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.crashreport.common.strategy.a$1 */
    class C44201 extends Thread {
        /* renamed from: a */
        private /* synthetic */ C4421a f15339a;

        C44201(C4421a c4421a) {
            this.f15339a = c4421a;
        }

        public final void run() {
            try {
                Map a = C4464o.m17672a().m17690a(C4421a.f15340a, null, true);
                if (a != null) {
                    byte[] bArr = (byte[]) a.get("key_imei");
                    byte[] bArr2 = (byte[]) a.get("key_ip");
                    if (bArr != null) {
                        C4417a.m17303a(this.f15339a.f15346g).m17334e(new String(bArr));
                    }
                    if (bArr2 != null) {
                        C4417a.m17303a(this.f15339a.f15346g).m17332d(new String(bArr2));
                    }
                }
                C4421a c4421a = this.f15339a;
                this.f15339a.f15345f = C4421a.m17391d();
            } catch (Throwable th) {
                if (!C4475w.m17748a(th)) {
                    th.printStackTrace();
                }
            }
            this.f15339a.m17393a(this.f15339a.f15345f, false);
        }
    }

    private C4421a(Context context, List<C4401a> list) {
        this.f15346g = context;
        this.f15344e = new StrategyBean();
        this.f15342c = list;
        this.f15343d = C4474v.m17740a();
    }

    /* renamed from: a */
    public static synchronized C4421a m17389a(Context context, List<C4401a> list) {
        C4421a c4421a;
        synchronized (C4421a.class) {
            if (f15341b == null) {
                f15341b = new C4421a(context, list);
            }
            c4421a = f15341b;
        }
        return c4421a;
    }

    /* renamed from: a */
    public final void m17392a(long j) {
        this.f15343d.m17742a(new C44201(this), j);
    }

    /* renamed from: a */
    public static synchronized C4421a m17388a() {
        C4421a c4421a;
        synchronized (C4421a.class) {
            c4421a = f15341b;
        }
        return c4421a;
    }

    /* renamed from: b */
    public final synchronized boolean m17395b() {
        return this.f15345f != null;
    }

    /* renamed from: c */
    public final StrategyBean m17396c() {
        if (this.f15345f != null) {
            return this.f15345f;
        }
        return this.f15344e;
    }

    /* renamed from: a */
    protected final void m17393a(StrategyBean strategyBean, boolean z) {
        C4475w.m17751c("[Strategy] Notify %s", C4415b.class.getName());
        C4415b.m17276a(strategyBean, z);
        for (C4401a c4401a : this.f15342c) {
            try {
                C4475w.m17751c("[Strategy] Notify %s", c4401a.getClass().getName());
                c4401a.onServerStrategyChanged(strategyBean);
            } catch (Throwable th) {
                if (!C4475w.m17748a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    /* renamed from: a */
    public final void m17394a(ao aoVar) {
        if (aoVar != null) {
            if (this.f15345f == null || aoVar.f15626h != this.f15345f.f15329p) {
                StrategyBean strategyBean = new StrategyBean();
                strategyBean.f15320g = aoVar.f15619a;
                strategyBean.f15322i = aoVar.f15621c;
                strategyBean.f15321h = aoVar.f15620b;
                if (C4479y.m17813c(aoVar.f15622d)) {
                    C4475w.m17751c("[Strategy] Upload url changes to %s", aoVar.f15622d);
                    strategyBean.f15331r = aoVar.f15622d;
                }
                if (C4479y.m17813c(aoVar.f15623e)) {
                    C4475w.m17751c("[Strategy] Exception upload url changes to %s", aoVar.f15623e);
                    strategyBean.f15332s = aoVar.f15623e;
                }
                if (!(aoVar.f15624f == null || C4479y.m17792a(aoVar.f15624f.f15614a))) {
                    strategyBean.f15334u = aoVar.f15624f.f15614a;
                }
                if (aoVar.f15626h != 0) {
                    strategyBean.f15329p = aoVar.f15626h;
                }
                if (aoVar.f15625g != null && aoVar.f15625g.size() > 0) {
                    strategyBean.f15335v = aoVar.f15625g;
                    String str = (String) aoVar.f15625g.get("B11");
                    if (str == null || !str.equals(C0844a.f2048d)) {
                        strategyBean.f15323j = false;
                    } else {
                        strategyBean.f15323j = true;
                    }
                    str = (String) aoVar.f15625g.get("B3");
                    if (str != null) {
                        strategyBean.f15338y = Long.valueOf(str).longValue();
                    }
                    strategyBean.f15330q = (long) aoVar.f15627i;
                    strategyBean.f15337x = (long) aoVar.f15627i;
                    str = (String) aoVar.f15625g.get("B27");
                    if (str != null && str.length() > 0) {
                        try {
                            int parseInt = Integer.parseInt(str);
                            if (parseInt > 0) {
                                strategyBean.f15336w = parseInt;
                            }
                        } catch (Throwable e) {
                            if (!C4475w.m17748a(e)) {
                                e.printStackTrace();
                            }
                        }
                    }
                    str = (String) aoVar.f15625g.get("B25");
                    if (str == null || !str.equals(C0844a.f2048d)) {
                        strategyBean.f15325l = false;
                    } else {
                        strategyBean.f15325l = true;
                    }
                }
                C4475w.m17747a("[Strategy] enableCrashReport:%b, enableQuery:%b, enableUserInfo:%b, enableAnr:%b, enableBlock:%b, enableSession:%b, enableSessionTimer:%b, sessionOverTime:%d, enableCocos:%b, strategyLastUpdateTime:%d", Boolean.valueOf(strategyBean.f15320g), Boolean.valueOf(strategyBean.f15322i), Boolean.valueOf(strategyBean.f15321h), Boolean.valueOf(strategyBean.f15323j), Boolean.valueOf(strategyBean.f15324k), Boolean.valueOf(strategyBean.f15327n), Boolean.valueOf(strategyBean.f15328o), Long.valueOf(strategyBean.f15330q), Boolean.valueOf(strategyBean.f15325l), Long.valueOf(strategyBean.f15329p));
                this.f15345f = strategyBean;
                C4464o.m17672a().m17695b(2);
                C4466q c4466q = new C4466q();
                c4466q.f15716b = 2;
                c4466q.f15715a = strategyBean.f15318e;
                c4466q.f15719e = strategyBean.f15319f;
                c4466q.f15721g = C4479y.m17795a((Parcelable) strategyBean);
                C4464o.m17672a().m17694a(c4466q);
                m17393a(strategyBean, true);
            }
        }
    }

    /* renamed from: d */
    public static StrategyBean m17391d() {
        List a = C4464o.m17672a().m17689a(2);
        if (a != null && a.size() > 0) {
            C4466q c4466q = (C4466q) a.get(0);
            if (c4466q.f15721g != null) {
                return (StrategyBean) C4479y.m17776a(c4466q.f15721g, StrategyBean.CREATOR);
            }
        }
        return null;
    }
}
