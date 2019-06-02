package rx.internal.operators;

import rx.C5717a.C5710b;
import rx.C5720f;
import rx.C5721e;
import rx.C5725c;
import rx.exceptions.C5736a;
import rx.exceptions.OnErrorThrowable;
import rx.internal.util.C5831e;

/* compiled from: OperatorCast */
/* renamed from: rx.internal.operators.d */
public class C5751d<T, R> implements C5710b<R, T> {
    /* renamed from: a */
    final Class<R> f18365a;

    /* compiled from: OperatorCast */
    /* renamed from: rx.internal.operators.d$a */
    static final class C5750a<T, R> extends C5721e<T> {
        /* renamed from: a */
        final C5721e<? super R> f18362a;
        /* renamed from: b */
        final Class<R> f18363b;
        /* renamed from: c */
        boolean f18364c;

        public C5750a(C5721e<? super R> c5721e, Class<R> cls) {
            this.f18362a = c5721e;
            this.f18363b = cls;
        }

        /* renamed from: a */
        public void mo7150a(T t) {
            try {
                this.f18362a.mo7150a(this.f18363b.cast(t));
            } catch (Throwable th) {
                C5736a.m20858a(th);
                unsubscribe();
                mo7151a(OnErrorThrowable.addValueAsLastCause(th, t));
            }
        }

        /* renamed from: a */
        public void mo7151a(Throwable th) {
            if (this.f18364c) {
                C5831e.m21032a(th);
                return;
            }
            this.f18364c = true;
            this.f18362a.mo7151a(th);
        }

        /* renamed from: a */
        public void mo7149a() {
            if (!this.f18364c) {
                this.f18362a.mo7149a();
            }
        }

        /* renamed from: a */
        public void mo7164a(C5725c c5725c) {
            this.f18362a.mo7164a(c5725c);
        }
    }

    public /* synthetic */ Object call(Object obj) {
        return m20917a((C5721e) obj);
    }

    public C5751d(Class<R> cls) {
        this.f18365a = cls;
    }

    /* renamed from: a */
    public C5721e<? super T> m20917a(C5721e<? super R> c5721e) {
        C5720f c5750a = new C5750a(c5721e, this.f18365a);
        c5721e.m20818a(c5750a);
        return c5750a;
    }
}
