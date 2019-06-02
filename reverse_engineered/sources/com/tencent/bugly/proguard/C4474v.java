package com.tencent.bugly.proguard;

import com.tencent.bugly.C4402b;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.v */
public final class C4474v {
    /* renamed from: a */
    private static C4474v f15772a;
    /* renamed from: b */
    private ScheduledExecutorService f15773b;

    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.proguard.v$1 */
    class C44731 implements ThreadFactory {
        C44731(C4474v c4474v) {
        }

        public final Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName("BUGLY_THREAD");
            return thread;
        }
    }

    protected C4474v() {
        this.f15773b = null;
        this.f15773b = Executors.newScheduledThreadPool(3, new C44731(this));
        if (this.f15773b == null || this.f15773b.isShutdown()) {
            C4475w.m17752d("[AsyncTaskHandler] ScheduledExecutorService is not valiable!", new Object[0]);
        }
    }

    /* renamed from: a */
    public static synchronized C4474v m17740a() {
        C4474v c4474v;
        synchronized (C4474v.class) {
            if (f15772a == null) {
                f15772a = new C4474v();
            }
            c4474v = f15772a;
        }
        return c4474v;
    }

    /* renamed from: a */
    public final synchronized boolean m17742a(Runnable runnable, long j) {
        boolean z = false;
        synchronized (this) {
            if (!m17744c()) {
                C4475w.m17752d("[AsyncTaskHandler] Async handler was closed, should not post task.", new Object[0]);
            } else if (runnable == null) {
                C4475w.m17752d("[AsyncTaskHandler] Task input is null.", new Object[0]);
            } else {
                if (j <= 0) {
                    j = 0;
                }
                C4475w.m17751c("[AsyncTaskHandler] Post a delay(time: %dms) task: %s", Long.valueOf(j), runnable.getClass().getName());
                try {
                    this.f15773b.schedule(runnable, j, TimeUnit.MILLISECONDS);
                    z = true;
                } catch (Throwable th) {
                    if (C4402b.f15204c) {
                        th.printStackTrace();
                    }
                }
            }
        }
        return z;
    }

    /* renamed from: a */
    public final synchronized boolean m17741a(Runnable runnable) {
        boolean z = false;
        synchronized (this) {
            if (!m17744c()) {
                C4475w.m17752d("[AsyncTaskHandler] Async handler was closed, should not post task.", new Object[0]);
            } else if (runnable == null) {
                C4475w.m17752d("[AsyncTaskHandler] Task input is null.", new Object[0]);
            } else {
                C4475w.m17751c("[AsyncTaskHandler] Post a normal task: %s", runnable.getClass().getName());
                try {
                    this.f15773b.execute(runnable);
                    z = true;
                } catch (Throwable th) {
                    if (C4402b.f15204c) {
                        th.printStackTrace();
                    }
                }
            }
        }
        return z;
    }

    /* renamed from: b */
    public final synchronized void m17743b() {
        if (!(this.f15773b == null || this.f15773b.isShutdown())) {
            C4475w.m17751c("[AsyncTaskHandler] Close async handler.", new Object[0]);
            this.f15773b.shutdownNow();
        }
    }

    /* renamed from: c */
    public final synchronized boolean m17744c() {
        boolean z;
        z = (this.f15773b == null || this.f15773b.isShutdown()) ? false : true;
        return z;
    }
}
