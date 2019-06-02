package rx.internal.operators;

import rx.C5717a.C5710b;
import rx.C5720f;
import rx.C5721e;
import rx.C5725c;
import rx.exceptions.C5736a;
import rx.exceptions.OnErrorThrowable;
import rx.internal.util.C5831e;
import rx.p208a.C5709f;

/* compiled from: OperatorFilter */
/* renamed from: rx.internal.operators.e */
public final class C5753e<T> implements C5710b<T, T> {
    /* renamed from: a */
    final C5709f<? super T, Boolean> f18369a;

    /* compiled from: OperatorFilter */
    /* renamed from: rx.internal.operators.e$a */
    static final class C5752a<T> extends C5721e<T> {
        /* renamed from: a */
        final C5721e<? super T> f18366a;
        /* renamed from: b */
        final C5709f<? super T, Boolean> f18367b;
        /* renamed from: c */
        boolean f18368c;

        public C5752a(C5721e<? super T> c5721e, C5709f<? super T, Boolean> c5709f) {
            this.f18366a = c5721e;
            this.f18367b = c5709f;
            m20816a(0);
        }

        /* renamed from: a */
        public void mo7150a(T t) {
            try {
                if (((Boolean) this.f18367b.call(t)).booleanValue()) {
                    this.f18366a.mo7150a((Object) t);
                } else {
                    m20816a(1);
                }
            } catch (Throwable th) {
                C5736a.m20858a(th);
                unsubscribe();
                mo7151a(OnErrorThrowable.addValueAsLastCause(th, t));
            }
        }

        /* renamed from: a */
        public void mo7151a(Throwable th) {
            if (this.f18368c) {
                C5831e.m21032a(th);
                return;
            }
            this.f18368c = true;
            this.f18366a.mo7151a(th);
        }

        /* renamed from: a */
        public void mo7149a() {
            if (!this.f18368c) {
                this.f18366a.mo7149a();
            }
        }

        /* renamed from: a */
        public void mo7164a(C5725c c5725c) {
            super.mo7164a(c5725c);
            this.f18366a.mo7164a(c5725c);
        }
    }

    public /* synthetic */ Object call(Object obj) {
        return m20922a((C5721e) obj);
    }

    public C5753e(C5709f<? super T, Boolean> c5709f) {
        this.f18369a = c5709f;
    }

    /* renamed from: a */
    public C5721e<? super T> m20922a(C5721e<? super T> c5721e) {
        C5720f c5752a = new C5752a(c5721e, this.f18369a);
        c5721e.m20818a(c5752a);
        return c5752a;
    }
}
