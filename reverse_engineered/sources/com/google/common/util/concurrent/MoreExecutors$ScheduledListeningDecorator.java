package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.common.util.concurrent.ForwardingListenableFuture.SimpleForwardingListenableFuture;
import java.util.concurrent.Callable;
import java.util.concurrent.Delayed;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

class MoreExecutors$ScheduledListeningDecorator extends MoreExecutors$ListeningDecorator implements ListeningScheduledExecutorService {
    final ScheduledExecutorService delegate;

    /* renamed from: com.google.common.util.concurrent.MoreExecutors$ScheduledListeningDecorator$ListenableScheduledTask */
    private static final class ListenableScheduledTask<V> extends SimpleForwardingListenableFuture<V> implements ListenableScheduledFuture<V> {
        private final ScheduledFuture<?> scheduledDelegate;

        public ListenableScheduledTask(ListenableFuture<V> listenableFuture, ScheduledFuture<?> scheduledFuture) {
            super(listenableFuture);
            this.scheduledDelegate = scheduledFuture;
        }

        public boolean cancel(boolean z) {
            boolean cancel = super.cancel(z);
            if (cancel) {
                this.scheduledDelegate.cancel(z);
            }
            return cancel;
        }

        public long getDelay(TimeUnit timeUnit) {
            return this.scheduledDelegate.getDelay(timeUnit);
        }

        public int compareTo(Delayed delayed) {
            return this.scheduledDelegate.compareTo(delayed);
        }
    }

    /* renamed from: com.google.common.util.concurrent.MoreExecutors$ScheduledListeningDecorator$NeverSuccessfulListenableFutureTask */
    private static final class NeverSuccessfulListenableFutureTask extends AbstractFuture<Void> implements Runnable {
        private final Runnable delegate;

        public NeverSuccessfulListenableFutureTask(Runnable runnable) {
            this.delegate = (Runnable) Preconditions.checkNotNull(runnable);
        }

        public void run() {
            try {
                this.delegate.run();
            } catch (Throwable th) {
                setException(th);
                RuntimeException propagate = Throwables.propagate(th);
            }
        }
    }

    MoreExecutors$ScheduledListeningDecorator(ScheduledExecutorService scheduledExecutorService) {
        super(scheduledExecutorService);
        this.delegate = (ScheduledExecutorService) Preconditions.checkNotNull(scheduledExecutorService);
    }

    public ListenableScheduledFuture<?> schedule(Runnable runnable, long j, TimeUnit timeUnit) {
        Object create = ListenableFutureTask.create(runnable, null);
        return new ListenableScheduledTask(create, this.delegate.schedule(create, j, timeUnit));
    }

    public <V> ListenableScheduledFuture<V> schedule(Callable<V> callable, long j, TimeUnit timeUnit) {
        Object create = ListenableFutureTask.create(callable);
        return new ListenableScheduledTask(create, this.delegate.schedule(create, j, timeUnit));
    }

    public ListenableScheduledFuture<?> scheduleAtFixedRate(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        Object neverSuccessfulListenableFutureTask = new NeverSuccessfulListenableFutureTask(runnable);
        return new ListenableScheduledTask(neverSuccessfulListenableFutureTask, this.delegate.scheduleAtFixedRate(neverSuccessfulListenableFutureTask, j, j2, timeUnit));
    }

    public ListenableScheduledFuture<?> scheduleWithFixedDelay(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        Object neverSuccessfulListenableFutureTask = new NeverSuccessfulListenableFutureTask(runnable);
        return new ListenableScheduledTask(neverSuccessfulListenableFutureTask, this.delegate.scheduleWithFixedDelay(neverSuccessfulListenableFutureTask, j, j2, timeUnit));
    }
}
