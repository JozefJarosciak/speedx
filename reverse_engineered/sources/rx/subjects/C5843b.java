package rx.subjects;

import rx.C5717a.C5695a;
import rx.C5721e;
import rx.p210c.C5724b;

/* compiled from: SerializedSubject */
/* renamed from: rx.subjects.b */
public class C5843b<T, R> extends C5840c<T, R> {
    /* renamed from: c */
    private final C5724b<T> f18550c;
    /* renamed from: d */
    private final C5840c<T, R> f18551d;

    /* compiled from: SerializedSubject */
    /* renamed from: rx.subjects.b$1 */
    class C58421 implements C5695a<R> {
        /* renamed from: a */
        final /* synthetic */ C5840c f18549a;

        C58421(C5840c c5840c) {
            this.f18549a = c5840c;
        }

        public /* synthetic */ void call(Object obj) {
            m21063a((C5721e) obj);
        }

        /* renamed from: a */
        public void m21063a(C5721e<? super R> c5721e) {
            this.f18549a.m20808a((C5721e) c5721e);
        }
    }

    public C5843b(C5840c<T, R> c5840c) {
        super(new C58421(c5840c));
        this.f18551d = c5840c;
        this.f18550c = new C5724b(c5840c);
    }

    /* renamed from: a */
    public void mo7149a() {
        this.f18550c.mo7149a();
    }

    /* renamed from: a */
    public void mo7151a(Throwable th) {
        this.f18550c.mo7151a(th);
    }

    /* renamed from: a */
    public void mo7150a(T t) {
        this.f18550c.mo7150a((Object) t);
    }
}
