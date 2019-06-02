package rx.internal.schedulers;

import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import rx.C5720f;
import rx.exceptions.OnErrorNotImplementedException;
import rx.internal.util.C5835g;
import rx.p208a.C5711a;
import rx.p211d.C5731d;
import rx.subscriptions.CompositeSubscription;

public final class ScheduledAction extends AtomicReference<Thread> implements Runnable, C5720f {
    private static final long serialVersionUID = -3962399486978279857L;
    /* renamed from: a */
    final C5835g f18397a;
    /* renamed from: b */
    final C5711a f18398b;

    private static final class Remover2 extends AtomicBoolean implements C5720f {
        private static final long serialVersionUID = 247232374289553518L;
        /* renamed from: a */
        final ScheduledAction f18391a;
        /* renamed from: b */
        final C5835g f18392b;

        public Remover2(ScheduledAction scheduledAction, C5835g c5835g) {
            this.f18391a = scheduledAction;
            this.f18392b = c5835g;
        }

        public boolean isUnsubscribed() {
            return this.f18391a.isUnsubscribed();
        }

        public void unsubscribe() {
            if (compareAndSet(false, true)) {
                this.f18392b.m21041b(this.f18391a);
            }
        }
    }

    private static final class Remover extends AtomicBoolean implements C5720f {
        private static final long serialVersionUID = 247232374289553518L;
        /* renamed from: a */
        final ScheduledAction f18393a;
        /* renamed from: b */
        final CompositeSubscription f18394b;

        public Remover(ScheduledAction scheduledAction, CompositeSubscription compositeSubscription) {
            this.f18393a = scheduledAction;
            this.f18394b = compositeSubscription;
        }

        public boolean isUnsubscribed() {
            return this.f18393a.isUnsubscribed();
        }

        public void unsubscribe() {
            if (compareAndSet(false, true)) {
                this.f18394b.remove(this.f18393a);
            }
        }
    }

    /* renamed from: rx.internal.schedulers.ScheduledAction$a */
    private final class C5759a implements C5720f {
        /* renamed from: a */
        final /* synthetic */ ScheduledAction f18395a;
        /* renamed from: b */
        private final Future<?> f18396b;

        C5759a(ScheduledAction scheduledAction, Future<?> future) {
            this.f18395a = scheduledAction;
            this.f18396b = future;
        }

        public void unsubscribe() {
            if (this.f18395a.get() != Thread.currentThread()) {
                this.f18396b.cancel(true);
            } else {
                this.f18396b.cancel(false);
            }
        }

        public boolean isUnsubscribed() {
            return this.f18396b.isCancelled();
        }
    }

    public ScheduledAction(C5711a c5711a) {
        this.f18398b = c5711a;
        this.f18397a = new C5835g();
    }

    public ScheduledAction(C5711a c5711a, CompositeSubscription compositeSubscription) {
        this.f18398b = c5711a;
        this.f18397a = new C5835g(new Remover(this, compositeSubscription));
    }

    public ScheduledAction(C5711a c5711a, C5835g c5835g) {
        this.f18398b = c5711a;
        this.f18397a = new C5835g(new Remover2(this, c5835g));
    }

    public void run() {
        try {
            lazySet(Thread.currentThread());
            this.f18398b.call();
        } catch (Throwable th) {
            Throwable th2;
            if (th2 instanceof OnErrorNotImplementedException) {
                th2 = new IllegalStateException("Exception thrown on Scheduler.Worker thread. Add `onError` handling.", th2);
            } else {
                th2 = new IllegalStateException("Fatal Exception thrown on Scheduler.Worker thread.", th2);
            }
            C5731d.m20840a().m20841b().m20831a(th2);
            Thread currentThread = Thread.currentThread();
            currentThread.getUncaughtExceptionHandler().uncaughtException(currentThread, th2);
        } finally {
            unsubscribe();
        }
    }

    public boolean isUnsubscribed() {
        return this.f18397a.isUnsubscribed();
    }

    public void unsubscribe() {
        if (!this.f18397a.isUnsubscribed()) {
            this.f18397a.unsubscribe();
        }
    }

    public void add(C5720f c5720f) {
        this.f18397a.m21040a(c5720f);
    }

    public void add(Future<?> future) {
        this.f18397a.m21040a(new C5759a(this, future));
    }

    public void addParent(CompositeSubscription compositeSubscription) {
        this.f18397a.m21040a(new Remover(this, compositeSubscription));
    }

    public void addParent(C5835g c5835g) {
        this.f18397a.m21040a(new Remover2(this, c5835g));
    }
}
