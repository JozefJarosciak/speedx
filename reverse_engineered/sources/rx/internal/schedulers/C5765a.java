package rx.internal.schedulers;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import rx.C5720f;
import rx.C5733d;
import rx.C5733d.C5726a;
import rx.internal.util.C5835g;
import rx.internal.util.RxThreadFactory;
import rx.p208a.C5711a;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.Subscriptions;

/* compiled from: EventLoopsScheduler */
/* renamed from: rx.internal.schedulers.a */
public final class C5765a extends C5733d {
    /* renamed from: b */
    static final int f18417b;
    /* renamed from: c */
    static final C5764c f18418c = new C5764c(RxThreadFactory.NONE);
    /* renamed from: d */
    static final C5762b f18419d = new C5762b(null, 0);
    /* renamed from: e */
    final AtomicReference<C5762b> f18420e;

    /* compiled from: EventLoopsScheduler */
    /* renamed from: rx.internal.schedulers.a$a */
    private static class C5761a extends C5726a {
        /* renamed from: a */
        private final C5835g f18401a = new C5835g();
        /* renamed from: b */
        private final CompositeSubscription f18402b = new CompositeSubscription();
        /* renamed from: c */
        private final C5835g f18403c = new C5835g(this.f18401a, this.f18402b);
        /* renamed from: d */
        private final C5764c f18404d;

        C5761a(C5764c c5764c) {
            this.f18404d = c5764c;
        }

        public void unsubscribe() {
            this.f18403c.unsubscribe();
        }

        public boolean isUnsubscribed() {
            return this.f18403c.isUnsubscribed();
        }

        /* renamed from: a */
        public C5720f mo7166a(final C5711a c5711a) {
            if (isUnsubscribed()) {
                return Subscriptions.unsubscribed();
            }
            return this.f18404d.m20944a(new C5711a(this) {
                /* renamed from: b */
                final /* synthetic */ C5761a f18400b;

                public void call() {
                    if (!this.f18400b.isUnsubscribed()) {
                        c5711a.call();
                    }
                }
            }, 0, null, this.f18401a);
        }
    }

    /* compiled from: EventLoopsScheduler */
    /* renamed from: rx.internal.schedulers.a$b */
    static final class C5762b {
        /* renamed from: a */
        final int f18405a;
        /* renamed from: b */
        final C5764c[] f18406b;
        /* renamed from: c */
        long f18407c;

        C5762b(ThreadFactory threadFactory, int i) {
            this.f18405a = i;
            this.f18406b = new C5764c[i];
            for (int i2 = 0; i2 < i; i2++) {
                this.f18406b[i2] = new C5764c(threadFactory);
            }
        }

        /* renamed from: a */
        public C5764c m20936a() {
            int i = this.f18405a;
            if (i == 0) {
                return C5765a.f18418c;
            }
            C5764c[] c5764cArr = this.f18406b;
            long j = this.f18407c;
            this.f18407c = 1 + j;
            return c5764cArr[(int) (j % ((long) i))];
        }
    }

    /* compiled from: EventLoopsScheduler */
    /* renamed from: rx.internal.schedulers.a$c */
    static final class C5764c extends C5763d {
        C5764c(ThreadFactory threadFactory) {
            super(threadFactory);
        }
    }

    static {
        int intValue = Integer.getInteger("rx.scheduler.max-computation-threads", 0).intValue();
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        if (intValue <= 0 || intValue > availableProcessors) {
            intValue = availableProcessors;
        }
        f18417b = intValue;
        f18418c.unsubscribe();
    }

    /* renamed from: a */
    public C5726a mo7167a() {
        return new C5761a(((C5762b) this.f18420e.get()).m20936a());
    }

    /* renamed from: a */
    public C5720f m20947a(C5711a c5711a) {
        return ((C5762b) this.f18420e.get()).m20936a().m20945b(c5711a, -1, TimeUnit.NANOSECONDS);
    }
}
