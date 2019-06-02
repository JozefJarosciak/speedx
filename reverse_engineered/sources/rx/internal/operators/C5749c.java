package rx.internal.operators;

import rx.C5717a.C5710b;
import rx.C5720f;
import rx.C5721e;
import rx.C5725c;
import rx.exceptions.C5736a;
import rx.internal.producers.SingleDelayedProducer;
import rx.p208a.C5709f;

/* compiled from: OperatorAny */
/* renamed from: rx.internal.operators.c */
public final class C5749c<T> implements C5710b<Boolean, T> {
    /* renamed from: a */
    final C5709f<? super T, Boolean> f18360a;
    /* renamed from: b */
    final boolean f18361b;

    public /* synthetic */ Object call(Object obj) {
        return m20912a((C5721e) obj);
    }

    public C5749c(C5709f<? super T, Boolean> c5709f, boolean z) {
        this.f18360a = c5709f;
        this.f18361b = z;
    }

    /* renamed from: a */
    public C5721e<? super T> m20912a(final C5721e<? super Boolean> c5721e) {
        final C5725c singleDelayedProducer = new SingleDelayedProducer(c5721e);
        C5720f c57481 = new C5721e<T>(this) {
            /* renamed from: a */
            boolean f18355a;
            /* renamed from: b */
            boolean f18356b;
            /* renamed from: e */
            final /* synthetic */ C5749c f18359e;

            /* renamed from: a */
            public void mo7150a(T t) {
                this.f18355a = true;
                try {
                    if (((Boolean) this.f18359e.f18360a.call(t)).booleanValue() && !this.f18356b) {
                        this.f18356b = true;
                        singleDelayedProducer.setValue(Boolean.valueOf(!this.f18359e.f18361b));
                        unsubscribe();
                    }
                } catch (Throwable th) {
                    C5736a.m20861a(th, this, t);
                }
            }

            /* renamed from: a */
            public void mo7151a(Throwable th) {
                c5721e.mo7151a(th);
            }

            /* renamed from: a */
            public void mo7149a() {
                if (!this.f18356b) {
                    this.f18356b = true;
                    if (this.f18355a) {
                        singleDelayedProducer.setValue(Boolean.valueOf(false));
                    } else {
                        singleDelayedProducer.setValue(Boolean.valueOf(this.f18359e.f18361b));
                    }
                }
            }
        };
        c5721e.m20818a(c57481);
        c5721e.mo7164a(singleDelayedProducer);
        return c57481;
    }
}
