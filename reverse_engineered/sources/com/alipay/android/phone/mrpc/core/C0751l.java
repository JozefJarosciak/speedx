package com.alipay.android.phone.mrpc.core;

import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.TimeUnit;

/* renamed from: com.alipay.android.phone.mrpc.core.l */
public final class C0751l implements ab {
    /* renamed from: b */
    private static C0751l f1755b = null;
    /* renamed from: i */
    private static final ThreadFactory f1756i = new C0753n();
    /* renamed from: a */
    Context f1757a;
    /* renamed from: c */
    private ThreadPoolExecutor f1758c = new ThreadPoolExecutor(10, 11, 3, TimeUnit.SECONDS, new ArrayBlockingQueue(20), f1756i, new CallerRunsPolicy());
    /* renamed from: d */
    private C0739b f1759d = C0739b.m2840a("android");
    /* renamed from: e */
    private long f1760e;
    /* renamed from: f */
    private long f1761f;
    /* renamed from: g */
    private long f1762g;
    /* renamed from: h */
    private int f1763h;

    private C0751l(Context context) {
        this.f1757a = context;
        try {
            this.f1758c.allowCoreThreadTimeOut(true);
        } catch (Exception e) {
        }
        CookieSyncManager.createInstance(this.f1757a);
        CookieManager.getInstance().setAcceptCookie(true);
    }

    /* renamed from: a */
    public static final C0751l m2867a(Context context) {
        return f1755b != null ? f1755b : C0751l.m2868b(context);
    }

    /* renamed from: b */
    private static final synchronized C0751l m2868b(Context context) {
        C0751l c0751l;
        synchronized (C0751l.class) {
            if (f1755b != null) {
                c0751l = f1755b;
            } else {
                c0751l = new C0751l(context);
                f1755b = c0751l;
            }
        }
        return c0751l;
    }

    /* renamed from: a */
    public final C0739b m2869a() {
        return this.f1759d;
    }

    /* renamed from: a */
    public final Future<C0756u> mo2418a(C0754t c0754t) {
        long j = 0;
        if (C0760s.m2910a(this.f1757a)) {
            String str = "HttpManager" + hashCode() + ": Active Task = %d, Completed Task = %d, All Task = %d,Avarage Speed = %d KB/S, Connetct Time = %d ms, All data size = %d bytes, All enqueueConnect time = %d ms, All socket time = %d ms, All request times = %d times";
            Object[] objArr = new Object[9];
            objArr[0] = Integer.valueOf(this.f1758c.getActiveCount());
            objArr[1] = Long.valueOf(this.f1758c.getCompletedTaskCount());
            objArr[2] = Long.valueOf(this.f1758c.getTaskCount());
            objArr[3] = Long.valueOf(this.f1762g == 0 ? 0 : ((this.f1760e * 1000) / this.f1762g) >> 10);
            if (this.f1763h != 0) {
                j = this.f1761f / ((long) this.f1763h);
            }
            objArr[4] = Long.valueOf(j);
            objArr[5] = Long.valueOf(this.f1760e);
            objArr[6] = Long.valueOf(this.f1761f);
            objArr[7] = Long.valueOf(this.f1762g);
            objArr[8] = Integer.valueOf(this.f1763h);
            String.format(str, objArr);
        }
        Object c0758q = new C0758q(this, (C0755o) c0754t);
        Object c0752m = new C0752m(this, c0758q, c0758q);
        this.f1758c.execute(c0752m);
        return c0752m;
    }

    /* renamed from: a */
    public final void m2871a(long j) {
        this.f1760e += j;
    }

    /* renamed from: b */
    public final void m2872b(long j) {
        this.f1761f += j;
        this.f1763h++;
    }

    /* renamed from: c */
    public final void m2873c(long j) {
        this.f1762g += j;
    }
}
