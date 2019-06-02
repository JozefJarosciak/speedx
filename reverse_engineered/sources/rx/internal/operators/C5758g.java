package rx.internal.operators;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import rx.C5717a.C5710b;
import rx.C5720f;
import rx.C5721e;
import rx.C5725c;
import rx.C5733d;
import rx.C5733d.C5726a;
import rx.exceptions.MissingBackpressureException;
import rx.internal.schedulers.C5768c;
import rx.internal.schedulers.C5773e;
import rx.internal.util.C5834f;
import rx.internal.util.p213a.C5799b;
import rx.internal.util.p214b.C5825r;
import rx.internal.util.p214b.C5826y;
import rx.p208a.C5711a;
import rx.p211d.C5731d;

/* compiled from: OperatorObserveOn */
/* renamed from: rx.internal.operators.g */
public final class C5758g<T> implements C5710b<T, T> {
    /* renamed from: a */
    private final C5733d f18386a;
    /* renamed from: b */
    private final boolean f18387b;
    /* renamed from: c */
    private final int f18388c;

    /* compiled from: OperatorObserveOn */
    /* renamed from: rx.internal.operators.g$a */
    private static final class C5757a<T> extends C5721e<T> implements C5711a {
        /* renamed from: a */
        final C5721e<? super T> f18375a;
        /* renamed from: b */
        final C5726a f18376b;
        /* renamed from: c */
        final NotificationLite<T> f18377c;
        /* renamed from: d */
        final boolean f18378d;
        /* renamed from: e */
        final Queue<Object> f18379e;
        /* renamed from: f */
        final int f18380f;
        /* renamed from: g */
        volatile boolean f18381g;
        /* renamed from: h */
        final AtomicLong f18382h = new AtomicLong();
        /* renamed from: i */
        final AtomicLong f18383i = new AtomicLong();
        /* renamed from: j */
        Throwable f18384j;
        /* renamed from: k */
        long f18385k;

        /* compiled from: OperatorObserveOn */
        /* renamed from: rx.internal.operators.g$a$1 */
        class C57561 implements C5725c {
            /* renamed from: a */
            final /* synthetic */ C5757a f18374a;

            C57561(C5757a c5757a) {
                this.f18374a = c5757a;
            }

            public void request(long j) {
                if (j > 0) {
                    C5746a.m20906a(this.f18374a.f18382h, j);
                    this.f18374a.m20933d();
                }
            }
        }

        public C5757a(C5733d c5733d, C5721e<? super T> c5721e, boolean z, int i) {
            this.f18375a = c5721e;
            this.f18376b = c5733d.mo7167a();
            this.f18378d = z;
            this.f18377c = NotificationLite.m20864a();
            if (i <= 0) {
                i = C5834f.f18521b;
            }
            this.f18380f = i - (i >> 2);
            if (C5826y.m21015a()) {
                this.f18379e = new C5825r(i);
            } else {
                this.f18379e = new C5799b(i);
            }
            m20816a((long) i);
        }

        /* renamed from: c */
        void m20932c() {
            C5721e c5721e = this.f18375a;
            c5721e.mo7164a(new C57561(this));
            c5721e.m20818a(this.f18376b);
            c5721e.m20818a((C5720f) this);
        }

        /* renamed from: a */
        public void mo7150a(T t) {
            if (!isUnsubscribed() && !this.f18381g) {
                if (this.f18379e.offer(this.f18377c.m20865a((Object) t))) {
                    m20933d();
                } else {
                    mo7151a(new MissingBackpressureException());
                }
            }
        }

        /* renamed from: a */
        public void mo7149a() {
            if (!isUnsubscribed() && !this.f18381g) {
                this.f18381g = true;
                m20933d();
            }
        }

        /* renamed from: a */
        public void mo7151a(Throwable th) {
            if (isUnsubscribed() || this.f18381g) {
                C5731d.m20840a().m20841b().m20831a(th);
                return;
            }
            this.f18384j = th;
            this.f18381g = true;
            m20933d();
        }

        /* renamed from: d */
        protected void m20933d() {
            if (this.f18383i.getAndIncrement() == 0) {
                this.f18376b.mo7166a(this);
            }
        }

        public void call() {
            long j = 1;
            long j2 = this.f18385k;
            Queue queue = this.f18379e;
            C5721e c5721e = this.f18375a;
            NotificationLite notificationLite = this.f18377c;
            do {
                long j3 = this.f18382h.get();
                while (j3 != j2) {
                    boolean z = this.f18381g;
                    Object poll = queue.poll();
                    boolean z2 = poll == null;
                    if (!m20931a(z, z2, c5721e, queue)) {
                        if (z2) {
                            break;
                        }
                        c5721e.mo7150a(notificationLite.m20871d(poll));
                        long j4 = 1 + j2;
                        if (j4 == ((long) this.f18380f)) {
                            j2 = C5746a.m20907b(this.f18382h, j4);
                            m20816a(j4);
                            j4 = 0;
                        } else {
                            j2 = j3;
                        }
                        j3 = j2;
                        j2 = j4;
                    } else {
                        return;
                    }
                }
                if (j3 != j2 || !m20931a(this.f18381g, queue.isEmpty(), c5721e, queue)) {
                    this.f18385k = j2;
                    j = this.f18383i.addAndGet(-j);
                } else {
                    return;
                }
            } while (j != 0);
        }

        /* renamed from: a */
        boolean m20931a(boolean z, boolean z2, C5721e<? super T> c5721e, Queue<Object> queue) {
            if (c5721e.isUnsubscribed()) {
                queue.clear();
                return true;
            }
            if (z) {
                if (!this.f18378d) {
                    Throwable th = this.f18384j;
                    if (th != null) {
                        queue.clear();
                        try {
                            c5721e.mo7151a(th);
                            return true;
                        } finally {
                            this.f18376b.unsubscribe();
                        }
                    } else if (z2) {
                        try {
                            c5721e.mo7149a();
                            return true;
                        } finally {
                            this.f18376b.unsubscribe();
                        }
                    }
                } else if (z2) {
                    Throwable th2 = this.f18384j;
                    if (th2 != null) {
                        try {
                            c5721e.mo7151a(th2);
                        } catch (Throwable th3) {
                            this.f18376b.unsubscribe();
                        }
                    } else {
                        c5721e.mo7149a();
                    }
                    this.f18376b.unsubscribe();
                }
            }
            return false;
        }
    }

    public /* synthetic */ Object call(Object obj) {
        return m20934a((C5721e) obj);
    }

    public C5758g(C5733d c5733d, boolean z, int i) {
        this.f18386a = c5733d;
        this.f18387b = z;
        if (i <= 0) {
            i = C5834f.f18521b;
        }
        this.f18388c = i;
    }

    /* renamed from: a */
    public C5721e<? super T> m20934a(C5721e<? super T> c5721e) {
        if ((this.f18386a instanceof C5768c) || (this.f18386a instanceof C5773e)) {
            return c5721e;
        }
        C5721e c5757a = new C5757a(this.f18386a, c5721e, this.f18387b, this.f18388c);
        c5757a.m20932c();
        return c5757a;
    }
}
