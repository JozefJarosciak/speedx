package io.fabric.sdk.android.services.concurrency.internal;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;

/* compiled from: RetryThreadPoolExecutor */
/* renamed from: io.fabric.sdk.android.services.concurrency.internal.g */
public class C4914g extends ScheduledThreadPoolExecutor {
    /* renamed from: a */
    private final C4909e f17242a;
    /* renamed from: b */
    private final C4908a f17243b;

    public C4914g(int i, C4909e c4909e, C4908a c4908a) {
        this(i, Executors.defaultThreadFactory(), c4909e, c4908a);
    }

    public C4914g(int i, ThreadFactory threadFactory, C4909e c4909e, C4908a c4908a) {
        super(i, threadFactory);
        if (c4909e == null) {
            throw new NullPointerException("retry policy must not be null");
        } else if (c4908a == null) {
            throw new NullPointerException("backoff must not be null");
        } else {
            this.f17242a = c4909e;
            this.f17243b = c4908a;
        }
    }

    /* renamed from: a */
    public Future<?> m19282a(Runnable runnable) {
        return m19281a(Executors.callable(runnable));
    }

    /* renamed from: a */
    private <T> Future<T> m19281a(Callable<T> callable) {
        if (callable == null) {
            throw new NullPointerException();
        }
        Object c4912d = new C4912d(callable, new C4913f(this.f17243b, this.f17242a), this);
        execute(c4912d);
        return c4912d;
    }
}
