package com.umeng.analytics;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* compiled from: QueuedWork */
/* renamed from: com.umeng.analytics.h */
public class C4754h {
    /* renamed from: a */
    private static List<WeakReference<ScheduledFuture<?>>> f16660a = new ArrayList();
    /* renamed from: b */
    private static ExecutorService f16661b = Executors.newSingleThreadExecutor();
    /* renamed from: c */
    private static long f16662c = 5;
    /* renamed from: d */
    private static ScheduledExecutorService f16663d = Executors.newSingleThreadScheduledExecutor();

    /* renamed from: a */
    public static void m18671a(Runnable runnable) {
        if (f16661b.isShutdown()) {
            f16661b = Executors.newSingleThreadExecutor();
        }
        f16661b.execute(runnable);
    }

    /* renamed from: a */
    public static void m18670a() {
        try {
            for (WeakReference weakReference : f16660a) {
                ScheduledFuture scheduledFuture = (ScheduledFuture) weakReference.get();
                if (scheduledFuture != null) {
                    scheduledFuture.cancel(false);
                }
            }
            f16660a.clear();
            if (!f16661b.isShutdown()) {
                f16661b.shutdown();
            }
            if (!f16663d.isShutdown()) {
                f16663d.shutdown();
            }
            f16661b.awaitTermination(f16662c, TimeUnit.SECONDS);
            f16663d.awaitTermination(f16662c, TimeUnit.SECONDS);
        } catch (Exception e) {
        }
    }

    /* renamed from: b */
    public static synchronized void m18673b(Runnable runnable) {
        synchronized (C4754h.class) {
            if (f16663d.isShutdown()) {
                f16663d = Executors.newSingleThreadScheduledExecutor();
            }
            f16663d.execute(runnable);
        }
    }

    /* renamed from: a */
    public static synchronized void m18672a(Runnable runnable, long j) {
        synchronized (C4754h.class) {
            if (f16663d.isShutdown()) {
                f16663d = Executors.newSingleThreadScheduledExecutor();
            }
            f16660a.add(new WeakReference(f16663d.schedule(runnable, j, TimeUnit.MILLISECONDS)));
        }
    }

    /* renamed from: c */
    public static synchronized void m18674c(Runnable runnable) {
        synchronized (C4754h.class) {
            if (f16663d.isShutdown()) {
                f16663d = Executors.newSingleThreadScheduledExecutor();
            }
            try {
                f16663d.submit(runnable).get(5, TimeUnit.SECONDS);
            } catch (Exception e) {
            }
        }
    }
}
