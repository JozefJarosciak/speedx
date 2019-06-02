package io.fabric.sdk.android.services.concurrency.internal;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: RetryFuture */
/* renamed from: io.fabric.sdk.android.services.concurrency.internal.d */
class C4912d<T> extends AbstractFuture<T> implements Runnable {
    /* renamed from: a */
    C4913f f17235a;
    /* renamed from: b */
    private final C4914g f17236b;
    /* renamed from: c */
    private final Callable<T> f17237c;
    /* renamed from: d */
    private final AtomicReference<Thread> f17238d = new AtomicReference();

    C4912d(Callable<T> callable, C4913f c4913f, C4914g c4914g) {
        this.f17237c = callable;
        this.f17235a = c4913f;
        this.f17236b = c4914g;
    }

    public void run() {
        if (!isDone() && this.f17238d.compareAndSet(null, Thread.currentThread())) {
            try {
                m19267a(this.f17237c.call());
                this.f17238d.getAndSet(null);
            } catch (Throwable th) {
                this.f17238d.getAndSet(null);
            }
        }
    }

    /* renamed from: b */
    private C4909e m19273b() {
        return this.f17235a.m19279c();
    }

    /* renamed from: c */
    private C4908a m19274c() {
        return this.f17235a.m19278b();
    }

    /* renamed from: d */
    private int m19275d() {
        return this.f17235a.m19277a();
    }

    /* renamed from: a */
    protected void mo6255a() {
        Thread thread = (Thread) this.f17238d.getAndSet(null);
        if (thread != null) {
            thread.interrupt();
        }
    }
}
