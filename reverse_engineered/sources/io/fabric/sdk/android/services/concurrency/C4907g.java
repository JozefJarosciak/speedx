package io.fabric.sdk.android.services.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* compiled from: PriorityThreadPoolExecutor */
/* renamed from: io.fabric.sdk.android.services.concurrency.g */
public class C4907g extends ThreadPoolExecutor {
    /* renamed from: a */
    private static final int f17228a = Runtime.getRuntime().availableProcessors();
    /* renamed from: b */
    private static final int f17229b = (f17228a + 1);
    /* renamed from: c */
    private static final int f17230c = ((f17228a * 2) + 1);

    /* compiled from: PriorityThreadPoolExecutor */
    /* renamed from: io.fabric.sdk.android.services.concurrency.g$a */
    protected static final class C4906a implements ThreadFactory {
        /* renamed from: a */
        private final int f17227a;

        public C4906a(int i) {
            this.f17227a = i;
        }

        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setPriority(this.f17227a);
            thread.setName("Queue");
            return thread;
        }
    }

    public /* synthetic */ BlockingQueue getQueue() {
        return m19257b();
    }

    <T extends Runnable & C4842a & C4844h & C4843e> C4907g(int i, int i2, long j, TimeUnit timeUnit, DependencyPriorityBlockingQueue<T> dependencyPriorityBlockingQueue, ThreadFactory threadFactory) {
        super(i, i2, j, timeUnit, dependencyPriorityBlockingQueue, threadFactory);
        prestartAllCoreThreads();
    }

    /* renamed from: a */
    public static <T extends Runnable & C4842a & C4844h & C4843e> C4907g m19256a(int i, int i2) {
        return new C4907g(i, i2, 1, TimeUnit.SECONDS, new DependencyPriorityBlockingQueue(), new C4906a(10));
    }

    /* renamed from: a */
    public static C4907g m19255a() {
        return C4907g.m19256a(f17229b, f17230c);
    }

    protected <T> RunnableFuture<T> newTaskFor(Runnable runnable, T t) {
        return new C4902d(runnable, t);
    }

    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        return new C4902d(callable);
    }

    public void execute(Runnable runnable) {
        if (C4905f.m19246a((Object) runnable)) {
            super.execute(runnable);
        } else {
            super.execute(newTaskFor(runnable, null));
        }
    }

    protected void afterExecute(Runnable runnable, Throwable th) {
        C4844h c4844h = (C4844h) runnable;
        c4844h.mo6229b(true);
        c4844h.mo6227a(th);
        m19257b().recycleBlockedQueue();
        super.afterExecute(runnable, th);
    }

    /* renamed from: b */
    public DependencyPriorityBlockingQueue m19257b() {
        return (DependencyPriorityBlockingQueue) super.getQueue();
    }
}
