package rx.internal.util;

import java.util.concurrent.atomic.AtomicBoolean;
import rx.C5717a;
import rx.C5717a.C5695a;
import rx.C5719b;
import rx.C5720f;
import rx.C5721e;
import rx.C5725c;
import rx.C5733d;
import rx.exceptions.C5736a;
import rx.internal.schedulers.C5765a;
import rx.p208a.C5709f;
import rx.p208a.C5711a;
import rx.p211d.C5728b;
import rx.p211d.C5731d;

public final class ScalarSynchronousObservable<T> extends C5717a<T> {
    /* renamed from: c */
    static C5728b f18474c = C5731d.m20840a().m20842c();
    /* renamed from: d */
    static final boolean f18475d = Boolean.valueOf(System.getProperty("rx.just.strong-mode", "false")).booleanValue();
    /* renamed from: e */
    final T f18476e;

    static final class ScalarAsyncProducer<T> extends AtomicBoolean implements C5711a, C5725c {
        private static final long serialVersionUID = -2466317989629281651L;
        /* renamed from: a */
        final C5721e<? super T> f18469a;
        /* renamed from: b */
        final T f18470b;
        /* renamed from: c */
        final C5709f<C5711a, C5720f> f18471c;

        public ScalarAsyncProducer(C5721e<? super T> c5721e, T t, C5709f<C5711a, C5720f> c5709f) {
            this.f18469a = c5721e;
            this.f18470b = t;
            this.f18471c = c5709f;
        }

        public void request(long j) {
            if (j < 0) {
                throw new IllegalArgumentException("n >= 0 required but it was " + j);
            } else if (j != 0 && compareAndSet(false, true)) {
                this.f18469a.m20818a((C5720f) this.f18471c.call(this));
            }
        }

        public void call() {
            C5719b c5719b = this.f18469a;
            if (!c5719b.isUnsubscribed()) {
                Object obj = this.f18470b;
                try {
                    c5719b.mo7150a(obj);
                    if (!c5719b.isUnsubscribed()) {
                        c5719b.mo7149a();
                    }
                } catch (Throwable th) {
                    C5736a.m20861a(th, c5719b, obj);
                }
            }
        }

        public String toString() {
            return "ScalarAsyncProducer[" + this.f18470b + ", " + get() + "]";
        }
    }

    /* renamed from: rx.internal.util.ScalarSynchronousObservable$a */
    static final class C5796a<T> implements C5695a<T> {
        /* renamed from: a */
        final T f18472a;
        /* renamed from: b */
        final C5709f<C5711a, C5720f> f18473b;

        public /* synthetic */ void call(Object obj) {
            m20972a((C5721e) obj);
        }

        C5796a(T t, C5709f<C5711a, C5720f> c5709f) {
            this.f18472a = t;
            this.f18473b = c5709f;
        }

        /* renamed from: a */
        public void m20972a(C5721e<? super T> c5721e) {
            c5721e.mo7164a(new ScalarAsyncProducer(c5721e, this.f18472a, this.f18473b));
        }
    }

    /* renamed from: b */
    public C5717a<T> m20973b(final C5733d c5733d) {
        C5709f c57931;
        if (c5733d instanceof C5765a) {
            final C5765a c5765a = (C5765a) c5733d;
            c57931 = new C5709f<C5711a, C5720f>(this) {
                /* renamed from: b */
                final /* synthetic */ ScalarSynchronousObservable f18463b;

                public /* synthetic */ Object call(Object obj) {
                    return m20970a((C5711a) obj);
                }

                /* renamed from: a */
                public C5720f m20970a(C5711a c5711a) {
                    return c5765a.m20947a(c5711a);
                }
            };
        } else {
            c57931 = new C5709f<C5711a, C5720f>(this) {
                /* renamed from: b */
                final /* synthetic */ ScalarSynchronousObservable f18468b;

                public /* synthetic */ Object call(Object obj) {
                    return m20971a((C5711a) obj);
                }

                /* renamed from: a */
                public C5720f m20971a(final C5711a c5711a) {
                    final C5720f a = c5733d.mo7167a();
                    a.mo7166a(new C5711a(this) {
                        /* renamed from: c */
                        final /* synthetic */ C57952 f18466c;

                        public void call() {
                            try {
                                c5711a.call();
                            } finally {
                                a.unsubscribe();
                            }
                        }
                    });
                    return a;
                }
            };
        }
        return C5717a.m20796a(new C5796a(this.f18476e, c57931));
    }
}
