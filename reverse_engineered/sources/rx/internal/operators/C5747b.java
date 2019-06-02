package rx.internal.operators;

import rx.C5717a.C5695a;
import rx.C5717a.C5710b;
import rx.C5721e;
import rx.exceptions.C5736a;
import rx.p211d.C5728b;
import rx.p211d.C5731d;

/* compiled from: OnSubscribeLift */
/* renamed from: rx.internal.operators.b */
public final class C5747b<T, R> implements C5695a<R> {
    /* renamed from: a */
    static final C5728b f18352a = C5731d.m20840a().m20842c();
    /* renamed from: b */
    final C5695a<T> f18353b;
    /* renamed from: c */
    final C5710b<? extends R, ? super T> f18354c;

    public /* synthetic */ void call(Object obj) {
        m20908a((C5721e) obj);
    }

    public C5747b(C5695a<T> c5695a, C5710b<? extends R, ? super T> c5710b) {
        this.f18353b = c5695a;
        this.f18354c = c5710b;
    }

    /* renamed from: a */
    public void m20908a(C5721e<? super R> c5721e) {
        C5721e c5721e2;
        try {
            c5721e2 = (C5721e) f18352a.m20836a(this.f18354c).call(c5721e);
            c5721e2.m20819b();
            this.f18353b.call(c5721e2);
        } catch (Throwable th) {
            C5736a.m20858a(th);
            c5721e.mo7151a(th);
        }
    }
}
