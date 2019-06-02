package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public abstract class AbstractFuture<V> implements ListenableFuture<V> {
    private final ExecutionList executionList = new ExecutionList();
    private final Sync<V> sync = new Sync();

    static final class Sync<V> extends AbstractQueuedSynchronizer {
        static final int CANCELLED = 4;
        static final int COMPLETED = 2;
        static final int COMPLETING = 1;
        static final int INTERRUPTED = 8;
        static final int RUNNING = 0;
        private static final long serialVersionUID = 0;
        private Throwable exception;
        private V value;

        Sync() {
        }

        protected int tryAcquireShared(int i) {
            if (isDone()) {
                return 1;
            }
            return -1;
        }

        protected boolean tryReleaseShared(int i) {
            setState(i);
            return true;
        }

        V get(long j) throws TimeoutException, CancellationException, ExecutionException, InterruptedException {
            if (tryAcquireSharedNanos(-1, j)) {
                return getValue();
            }
            throw new TimeoutException("Timeout waiting for task.");
        }

        V get() throws CancellationException, ExecutionException, InterruptedException {
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
                    throw AbstractFuture.cancellationExceptionWithCause("Task was cancelled.", this.exception);
                default:
                    throw new IllegalStateException("Error, synchronizer in invalid state: " + state);
            }
        }

        boolean isDone() {
            return (getState() & 14) != 0;
        }

        boolean isCancelled() {
            return (getState() & 12) != 0;
        }

        boolean wasInterrupted() {
            return getState() == 8;
        }

        boolean set(V v) {
            return complete(v, null, 2);
        }

        boolean setException(Throwable th) {
            return complete(null, th, 2);
        }

        boolean cancel(boolean z) {
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

    public V get(long j, TimeUnit timeUnit) throws InterruptedException, TimeoutException, ExecutionException {
        return this.sync.get(timeUnit.toNanos(j));
    }

    public V get() throws InterruptedException, ExecutionException {
        return this.sync.get();
    }

    public boolean isDone() {
        return this.sync.isDone();
    }

    public boolean isCancelled() {
        return this.sync.isCancelled();
    }

    public boolean cancel(boolean z) {
        if (!this.sync.cancel(z)) {
            return false;
        }
        this.executionList.execute();
        if (z) {
            interruptTask();
        }
        return true;
    }

    protected void interruptTask() {
    }

    protected final boolean wasInterrupted() {
        return this.sync.wasInterrupted();
    }

    public void addListener(Runnable runnable, Executor executor) {
        this.executionList.add(runnable, executor);
    }

    protected boolean set(V v) {
        boolean z = this.sync.set(v);
        if (z) {
            this.executionList.execute();
        }
        return z;
    }

    protected boolean setException(Throwable th) {
        boolean exception = this.sync.setException((Throwable) Preconditions.checkNotNull(th));
        if (exception) {
            this.executionList.execute();
        }
        return exception;
    }

    static final CancellationException cancellationExceptionWithCause(String str, Throwable th) {
        CancellationException cancellationException = new CancellationException(str);
        cancellationException.initCause(th);
        return cancellationException;
    }
}
