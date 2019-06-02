package io.fabric.sdk.android.services.concurrency.internal;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public abstract class AbstractFuture<V> implements Future<V> {
    /* renamed from: a */
    private final Sync<V> f17231a = new Sync();

    static final class Sync<V> extends AbstractQueuedSynchronizer {
        private static final long serialVersionUID = 0;
        private Throwable exception;
        private V value;

        Sync() {
        }

        protected int tryAcquireShared(int i) {
            if (m19263b()) {
                return 1;
            }
            return -1;
        }

        protected boolean tryReleaseShared(int i) {
            setState(i);
            return true;
        }

        /* renamed from: a */
        V m19259a(long j) throws TimeoutException, CancellationException, ExecutionException, InterruptedException {
            if (tryAcquireSharedNanos(-1, j)) {
                return getValue();
            }
            throw new TimeoutException("Timeout waiting for task.");
        }

        /* renamed from: a */
        V m19258a() throws CancellationException, ExecutionException, InterruptedException {
            acquireSharedInterruptibly(-1);
            return getValue();
        }

        private V getValue() throws CancellationException, ExecutionException {
            int state = getState();
            switch (state) {
                case 2:
                    if (this.exception == null) {
                        return this.value;
                    }
                    throw new ExecutionException(this.exception);
                case 4:
                case 8:
                    throw AbstractFuture.m19265a("Task was cancelled.", this.exception);
                default:
                    throw new IllegalStateException("Error, synchronizer in invalid state: " + state);
            }
        }

        /* renamed from: b */
        boolean m19263b() {
            return (getState() & 14) != 0;
        }

        /* renamed from: c */
        boolean m19264c() {
            return (getState() & 12) != 0;
        }

        /* renamed from: a */
        boolean m19260a(V v) {
            return complete(v, null, 2);
        }

        /* renamed from: a */
        boolean m19261a(Throwable th) {
            return complete(null, th, 2);
        }

        /* renamed from: a */
        boolean m19262a(boolean z) {
            return complete(null, null, z ? 8 : 4);
        }

        private boolean complete(V v, Throwable th, int i) {
            boolean compareAndSetState = compareAndSetState(0, 1);
            if (compareAndSetState) {
                this.value = v;
                if ((i & 12) != 0) {
                    th = new CancellationException("Future.cancel() was called.");
                }
                this.exception = th;
                releaseShared(i);
            } else if (getState() == 1) {
                acquireShared(-1);
            }
            return compareAndSetState;
        }
    }

    protected AbstractFuture() {
    }

    /* renamed from: a */
    static final CancellationException m19265a(String str, Throwable th) {
        CancellationException cancellationException = new CancellationException(str);
        cancellationException.initCause(th);
        return cancellationException;
    }

    public V get(long j, TimeUnit timeUnit) throws InterruptedException, TimeoutException, ExecutionException {
        return this.f17231a.m19259a(timeUnit.toNanos(j));
    }

    public V get() throws InterruptedException, ExecutionException {
        return this.f17231a.m19258a();
    }

    public boolean isDone() {
        return this.f17231a.m19263b();
    }

    public boolean isCancelled() {
        return this.f17231a.m19264c();
    }

    public boolean cancel(boolean z) {
        if (!this.f17231a.m19262a(z)) {
            return false;
        }
        if (z) {
            mo6255a();
        }
        return true;
    }

    /* renamed from: a */
    protected void mo6255a() {
    }

    /* renamed from: a */
    protected boolean m19267a(V v) {
        return this.f17231a.m19260a((Object) v);
    }

    /* renamed from: a */
    protected boolean m19268a(Throwable th) {
        if (th != null) {
            return this.f17231a.m19261a(th);
        }
        throw new NullPointerException();
    }
}
