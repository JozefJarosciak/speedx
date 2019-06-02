package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.base.Supplier;
import com.google.common.base.Throwables;
import com.google.common.util.concurrent.Service.Listener;
import com.google.common.util.concurrent.Service.State;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Beta
public abstract class AbstractIdleService implements Service {
    private final Service delegate = new C38442();
    private final Supplier<String> threadNameSupplier = new C38411();

    /* renamed from: com.google.common.util.concurrent.AbstractIdleService$1 */
    class C38411 implements Supplier<String> {
        C38411() {
        }

        public String get() {
            String valueOf = String.valueOf(String.valueOf(AbstractIdleService.this.serviceName()));
            String valueOf2 = String.valueOf(String.valueOf(AbstractIdleService.this.state()));
            return new StringBuilder((valueOf.length() + 1) + valueOf2.length()).append(valueOf).append(" ").append(valueOf2).toString();
        }
    }

    /* renamed from: com.google.common.util.concurrent.AbstractIdleService$2 */
    class C38442 extends AbstractService {

        /* renamed from: com.google.common.util.concurrent.AbstractIdleService$2$1 */
        class C38421 implements Runnable {
            C38421() {
            }

            public void run() {
                try {
                    AbstractIdleService.this.startUp();
                    C38442.this.notifyStarted();
                } catch (Throwable th) {
                    C38442.this.notifyFailed(th);
                    RuntimeException propagate = Throwables.propagate(th);
                }
            }
        }

        /* renamed from: com.google.common.util.concurrent.AbstractIdleService$2$2 */
        class C38432 implements Runnable {
            C38432() {
            }

            public void run() {
                try {
                    AbstractIdleService.this.shutDown();
                    C38442.this.notifyStopped();
                } catch (Throwable th) {
                    C38442.this.notifyFailed(th);
                    RuntimeException propagate = Throwables.propagate(th);
                }
            }
        }

        C38442() {
        }

        protected final void doStart() {
            MoreExecutors.renamingDecorator(AbstractIdleService.this.executor(), AbstractIdleService.this.threadNameSupplier).execute(new C38421());
        }

        protected final void doStop() {
            MoreExecutors.renamingDecorator(AbstractIdleService.this.executor(), AbstractIdleService.this.threadNameSupplier).execute(new C38432());
        }
    }

    /* renamed from: com.google.common.util.concurrent.AbstractIdleService$3 */
    class C38453 implements Executor {
        C38453() {
        }

        public void execute(Runnable runnable) {
            MoreExecutors.newThread((String) AbstractIdleService.this.threadNameSupplier.get(), runnable).start();
        }
    }

    protected abstract void shutDown() throws Exception;

    protected abstract void startUp() throws Exception;

    protected AbstractIdleService() {
    }

    protected Executor executor() {
        return new C38453();
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

    protected String serviceName() {
        return getClass().getSimpleName();
    }
}
