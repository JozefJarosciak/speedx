package rx.internal.util;

import rx.C5721e;
import rx.p208a.C5694b;
import rx.p208a.C5711a;

/* compiled from: ActionSubscriber */
/* renamed from: rx.internal.util.a */
public final class C5800a<T> extends C5721e<T> {
    /* renamed from: a */
    final C5694b<? super T> f18485a;
    /* renamed from: b */
    final C5694b<Throwable> f18486b;
    /* renamed from: c */
    final C5711a f18487c;

    public C5800a(C5694b<? super T> c5694b, C5694b<Throwable> c5694b2, C5711a c5711a) {
        this.f18485a = c5694b;
        this.f18486b = c5694b2;
        this.f18487c = c5711a;
    }

    /* renamed from: a */
    public void mo7150a(T t) {
        this.f18485a.call(t);
    }

    /* renamed from: a */
    public void mo7151a(Throwable th) {
        this.f18486b.call(th);
    }

    /* renamed from: a */
    public void mo7149a() {
        this.f18487c.call();
    }
}
