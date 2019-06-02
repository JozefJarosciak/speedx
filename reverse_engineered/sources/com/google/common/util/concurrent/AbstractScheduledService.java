package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.base.Throwables;
import com.google.common.util.concurrent.Service.Listener;
import com.google.common.util.concurrent.Service.State;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

@Beta
public abstract class AbstractScheduledService implements Service {
    private static final Logger logger = Logger.getLogger(AbstractScheduledService.class.getName());
    private final AbstractService delegate = new C38501();

    /* renamed from: com.google.common.util.concurrent.AbstractScheduledService$1 */
    class C38501 extends AbstractService {
        private volatile ScheduledExecutorService executorService;
        private final ReentrantLock lock = new ReentrantLock();
        private volatile Future<?> runningTask;
        private final Runnable task = new C38461();

        /* renamed from: com.google.common.util.concurrent.AbstractScheduledService$1$1 */
        class C38461 implements Runnable {
            C38461() {
            }

            public void run() {
                C38501.this.lock.lock();
                try {
                    AbstractScheduledService.this.runOneIteration();
                    C38501.this.lock.unlock();
                } catch (Throwable th) {
                    C38501.this.lock.unlock();
                }
            }
        }

        /* renamed from: com.google.common.util.concurrent.AbstractScheduledService$1$2 */
        class C38472 implements Supplier<String> {
            C38472() {
            }

            public String get() {
                String valueOf = String.valueOf(String.valueOf(AbstractScheduledService.this.serviceName()));
                String valueOf2 = String.valueOf(String.valueOf(C38501.this.state()));
                return new StringBuilder((valueOf.length() + 1) + valueOf2.length()).append(valueOf).append(" ").append(valueOf2).toString();
            }
        }

        /* renamed from: com.google.common.util.concurrent.AbstractScheduledService$1$3 */
        class C38483 implements Runnable {
            C38483() {
            }

            public void run() {
                C38501.this.lock.lock();
                try {
                    AbstractScheduledService.this.startUp();
                    C38501.this.runningTask = AbstractScheduledService.this.scheduler().schedule(AbstractScheduledService.this.delegate, C38501.this.executorService, C38501.this.task);
                    C38501.this.notifyStarted();
                    C38501.this.lock.unlock();
                } catch (Throwable th) {
                    C38501.this.lock.unlock();
                }
            }
        }

        /* renamed from: com.google.common.util.concurrent.AbstractScheduledService$1$4 */
        class C38494 implements Runnable {
            C38494() {
            }

            public void run() {
                try {
                    C38501.this.lock.lock();
                    if (C38501.this.state() != State.STOPPING) {
                        C38501.this.lock.unlock();
                        return;
                    }
                    AbstractScheduledService.this.shutDown();
                    C38501.this.lock.unlock();
                    C38501.this.notifyStopped();
                } catch (Throwable th) {
                    C38501.this.notifyFailed(th);
                    RuntimeException propagate = Throwables.propagate(th);
                }
            }
        }

        C38501() {
        }

        protected final void doStart() {
            this.executorService = MoreExecutors.renamingDecorator(AbstractScheduledService.this.executor(), new C38472());
            this.executorService.execute(new C38483());
        }

        protected final void doStop() {
            this.runningTask.cancel(false);
            this.executorService.execute(new C38494());
        }
    }

    /* renamed from: com.google.common.util.concurrent.AbstractScheduledService$2 */
    class C38512 implements ThreadFactory {
        C38512() {
        }

        public Thread newThread(Runnable runnable) {
            return MoreExecutors.newThread(AbstractScheduledService.this.serviceName(), runnable);
        }
    }

    public static abstract class Scheduler {
        abstract Future<?> schedule(AbstractService abstractService, ScheduledExecutorService scheduledExecutorService, Runnable runnable);

        public static Scheduler newFixedDelaySchedule(long j, long j2, TimeUnit timeUnit) {
            final long j3 = j;
            final long j4 = j2;
            final TimeUnit timeUnit2 = timeUnit;
            return new Scheduler() {
                public Future<?> schedule(AbstractService abstractService, ScheduledExecutorService scheduledExecutorService, Runnable runnable) {
                    return scheduledExecutorService.scheduleWithFixedDelay(runnable, j3, j4, timeUnit2);
                }
            };
        }

        public static Scheduler newFixedRateSchedule(long j, long j2, TimeUnit timeUnit) {
            final long j3 = j;
            final long j4 = j2;
            final TimeUnit timeUnit2 = timeUnit;
            return new Scheduler() {
                public Future<?> schedule(AbstractService abstractService, ScheduledExecutorService scheduledExecutorService, Runnable runnable) {
                    return scheduledExecutorService.scheduleAtFixedRate(runnable, j3, j4, timeUnit2);
                }
            };
        }

        private Scheduler() {
        }
    }

    @Beta
    public static abstract class CustomScheduler extends Scheduler {

        private class ReschedulableCallable extends ForwardingFuture<Void> implements Callable<Void> {
            private Future<Void> currentFuture;
            private final ScheduledExecutorService executor;
            private final ReentrantLock lock = new ReentrantLock();
            private final AbstractService service;
            private final Runnable wrappedRunnable;

            ReschedulableCallable(AbstractService abstractService, ScheduledExecutorService scheduledExecutorService, Runnable runnable) {
                this.wrappedRunnable = runnable;
                this.executor = scheduledExecutorService;
                this.service = abstractService;
            }

            public Void call() throws Exception {
                this.wrappedRunnable.run();
                reschedule();
                return null;
            }

            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void reschedule() {
                /*
                r4 = this;
                r0 = r4.lock;
                r0.lock();
                r0 = r4.currentFuture;	 Catch:{ Throwable -> 0x002d }
                if (r0 == 0) goto L_0x0011;
            L_0x0009:
                r0 = r4.currentFuture;	 Catch:{ Throwable -> 0x002d }
                r0 = r0.isCancelled();	 Catch:{ Throwable -> 0x002d }
                if (r0 != 0) goto L_0x0027;
            L_0x0011:
                r0 = com.google.common.util.concurrent.AbstractScheduledService.CustomScheduler.this;	 Catch:{ Throwable -> 0x002d }
                r0 = r0.getNextSchedule();	 Catch:{ Throwable -> 0x002d }
                r1 = r4.executor;	 Catch:{ Throwable -> 0x002d }
                r2 = r0.delay;	 Catch:{ Throwable -> 0x002d }
                r0 = r0.unit;	 Catch:{ Throwable -> 0x002d }
                r0 = r1.schedule(r4, r2, r0);	 Catch:{ Throwable -> 0x002d }
                r4.currentFuture = r0;	 Catch:{ Throwable -> 0x002d }
            L_0x0027:
                r0 = r4.lock;
                r0.unlock();
            L_0x002c:
                return;
            L_0x002d:
                r0 = move-exception;
                r1 = r4.service;	 Catch:{ all -> 0x0039 }
                r1.notifyFailed(r0);	 Catch:{ all -> 0x0039 }
                r0 = r4.lock;
                r0.unlock();
                goto L_0x002c;
            L_0x0039:
                r0 = move-exception;
                r1 = r4.lock;
                r1.unlock();
                throw r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.AbstractScheduledService.CustomScheduler.ReschedulableCallable.reschedule():void");
            }

            public boolean cancel(boolean z) {
                this.lock.lock();
                try {
                    boolean cancel = this.currentFuture.cancel(z);
                    return cancel;
                } finally {
                    this.lock.unlock();
                }
            }

            protected Future<Void> delegate() {
                throw new UnsupportedOperationException("Only cancel is supported by this future");
            }
        }

        @Beta
        protected static final class Schedule {
            private final long delay;
            private final TimeUnit unit;

            public Schedule(long j, TimeUnit timeUnit) {
                this.delay = j;
                this.unit = (TimeUnit) Preconditions.checkNotNull(timeUnit);
            }
        }

        protected abstract Schedule getNextSchedule() throws Exception;

        public CustomScheduler() {
            super();
        }

        final Future<?> schedule(AbstractService abstractService, ScheduledExecutorService scheduledExecutorService, Runnable runnable) {
            Future reschedulableCallable = new ReschedulableCallable(abstractService, scheduledExecutorService, runnable);
            reschedulableCallable.reschedule();
            return reschedulableCallable;
        }
    }

    protected abstract void runOneIteration() throws Exception;

    protected abstract Scheduler scheduler();

    protected AbstractScheduledService() {
    }

    protected void startUp() throws Exception {
    }

    protected void shutDown() throws Exception {
    }

    protected ScheduledExecutorService executor() {
        final ScheduledExecutorService newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor(new C38512());
        addListener(new Listener() {
            public void terminated(State state) {
                newSingleThreadScheduledExecutor.shutdown();
            }

            public void failed(State state, Throwable th) {
                newSingleThreadScheduledExecutor.shutdown();
            }
        }, MoreExecutors.directExecutor());
        return newSingleThreadScheduledExecutor;
    }

    protected String serviceName() {
        return getClass().getSimpleName();
    }

    public String toString() {
        String valueOf = String.valueOf(String.valueOf(serviceName()));
        String valueOf2 = String.valueOf(String.valueOf(state()));
        return new StringBuilder((valueOf.length() + 3) + valueOf2.length()).append(valueOf).append(" [").append(valueOf2).append("]").toString();
    }

    public final boolean isRunning() {
        return this.delegate.isRunning();
    }

    public final State state() {
        return this.delegate.state();
    }

    public final void addListener(Listener listener, Executor executor) {
        this.delegate.addListener(listener, executor);
    }

    public final Throwable failureCause() {
        return this.delegate.failureCause();
    }

    public final Service startAsync() {
        this.delegate.startAsync();
        return this;
    }

    public final Service stopAsync() {
        this.delegate.stopAsync();
        return this;
    }

    public final void awaitRunning() {
        this.delegate.awaitRunning();
    }

    public final void awaitRunning(long j, TimeUnit timeUnit) throws TimeoutException {
        this.delegate.awaitRunning(j, timeUnit);
    }

    public final void awaitTerminated() {
        this.delegate.awaitTerminated();
    }

    public final void awaitTerminated(long j, TimeUnit timeUnit) throws TimeoutException {
        this.delegate.awaitTerminated(j, timeUnit);
    }
}
