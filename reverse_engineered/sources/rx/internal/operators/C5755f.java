package rx.internal.operators;

import rx.C5717a.C5710b;
import rx.C5720f;
import rx.C5721e;
import rx.C5725c;
import rx.exceptions.C5736a;
import rx.exceptions.OnErrorThrowable;
import rx.internal.util.C5831e;
import rx.p208a.C5709f;

/* compiled from: OperatorMap */
/* renamed from: rx.internal.operators.f */
public final class C5755f<T, R> implements C5710b<R, T> {
    /* renamed from: a */
    final C5709f<? super T, ? extends R> f18373a;

    /* compiled from: OperatorMap */
    /* renamed from: rx.internal.operators.f$a */
    static final class C5754a<T, R> extends C5721e<T> {
        /* renamed from: a */
        final C5721e<? super R> f18370a;
        /* renamed from: b */
        final C5709f<? super T, ? extends R> f18371b;
        /* renamed from: c */
        boolean f18372c;

        public C5754a(C5721e<? super R> c5721e, C5709f<? super T, ? extends R> c5709f) {
            this.f18370a = c5721e;
            this.f18371b = c5709f;
        }

        /* renamed from: a */
        public void mo7150a(T t) {
            try {
                this.f18370a.mo7150a(this.f18371b.call(t));
            } catch (Throwable th) {
                C5736a.m20858a(th);
                unsubscribe();
                mo7151a(OnErrorThrowable.addValueAsLastCause(th, t));
            }
        }

        /* renamed from: a */
        public void mo7151a(Throwable th) {
            if (this.f18372c) {
                C5831e.m21032a(th);
                return;
            }
            this.f18372c = true;
            this.f18370a.mo7151a(th);
        }

        /* renamed from: a */
        public void mo7149a() {
            if (!this.f18372c) {
                this.f18370a.mo7149a();
            }
        }

        /* renamed from: a */
        public void mo7164a(C5725c c5725c) {
            this.f18370a.mo7164a(c5725c);
        }
    }

    public /* synthetic */ Object call(Object obj) {
        return m20927a((C5721e) obj);
    }

    public C5755f(C5709f<? super T, ? extends R> c5709f) {
        this.f18373a = c5709f;
    }

    /* renamed from: a */
    public C5721e<? super T> m20927a(C5721e<? super R> c5721e) {
        C5720f c5754a = new C5754a(c5721e, this.f18373a);
        c5721e.m20818a(c5754a);
        return c5754a;
    }
}
