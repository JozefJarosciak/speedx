package rx.internal.schedulers;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import rx.C5720f;
import rx.C5733d;
import rx.C5733d.C5726a;
import rx.p208a.C5711a;
import rx.subscriptions.BooleanSubscription;
import rx.subscriptions.Subscriptions;

/* compiled from: TrampolineScheduler */
/* renamed from: rx.internal.schedulers.e */
public final class C5773e extends C5733d {
    /* renamed from: b */
    public static final C5773e f18439b = new C5773e();

    /* compiled from: TrampolineScheduler */
    /* renamed from: rx.internal.schedulers.e$a */
    private static class C5771a extends C5726a implements C5720f {
        /* renamed from: a */
        final AtomicInteger f18432a = new AtomicInteger();
        /* renamed from: b */
        final PriorityBlockingQueue<C5772b> f18433b = new PriorityBlockingQueue();
        /* renamed from: c */
        private final BooleanSubscription f18434c = new BooleanSubscription();
        /* renamed from: d */
        private final AtomicInteger f18435d = new AtomicInteger();

        C5771a() {
        }

        /* renamed from: a */
        public C5720f mo7166a(C5711a c5711a) {
            return m20952a(c5711a, m20828a());
        }

        /* renamed from: a */
        private C5720f m20952a(C5711a c5711a, long j) {
            if (this.f18434c.isUnsubscribed()) {
                return Subscriptions.unsubscribed();
            }
            final C5772b c5772b = new C5772b(c5711a, Long.valueOf(j), this.f18432a.incrementAndGet());
            this.f18433b.add(c5772b);
            if (this.f18435d.getAndIncrement() != 0) {
                return Subscriptions.create(new C5711a(this) {
                    /* renamed from: b */
                    final /* synthetic */ C5771a f18431b;

                    public void call() {
                        this.f18431b.f18433b.remove(c5772b);
                    }
                });
            }
            do {
                c5772b = (C5772b) this.f18433b.poll();
                if (c5772b != null) {
                    c5772b.f18436a.call();
                }
            } while (this.f18435d.decrementAndGet() > 0);
            return Subscriptions.unsubscribed();
        }

        public void unsubscribe() {
            this.f18434c.unsubscribe();
        }

        public boolean isUnsubscribed() {
            return this.f18434c.isUnsubscribed();
        }
    }

    /* compiled from: TrampolineScheduler */
    /* renamed from: rx.internal.schedulers.e$b */
    private static final class C5772b implements Comparable<C5772b> {
        /* renamed from: a */
        final C5711a f18436a;
        /* renamed from: b */
        final Long f18437b;
        /* renamed from: c */
        final int f18438c;

        public /* synthetic */ int compareTo(Object obj) {
            return m20954a((C5772b) obj);
        }

        C5772b(C5711a c5711a, Long l, int i) {
            this.f18436a = c5711a;
            this.f18437b = l;
            this.f18438c = i;
        }

        /* renamed from: a */
        public int m20954a(C5772b c5772b) {
            int compareTo = this.f18437b.compareTo(c5772b.f18437b);
            if (compareTo == 0) {
                return C5773e.m20955a(this.f18438c, c5772b.f18438c);
            }
            return compareTo;
        }
    }

    /* renamed from: a */
    public C5726a mo7167a() {
        return new C5771a();
    }

    private C5773e() {
    }

    /* renamed from: a */
    static int m20955a(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        return i == i2 ? 0 : 1;
    }
}
