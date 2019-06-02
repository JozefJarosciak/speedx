package rx;

import java.util.concurrent.TimeUnit;
import rx.exceptions.C5736a;
import rx.exceptions.OnErrorFailedException;
import rx.internal.operators.C5747b;
import rx.internal.operators.C5751d;
import rx.internal.operators.C5753e;
import rx.internal.operators.C5755f;
import rx.internal.operators.C5758g;
import rx.internal.operators.OperatorReplay;
import rx.internal.util.C5800a;
import rx.internal.util.C5834f;
import rx.internal.util.InternalObservableUtils;
import rx.internal.util.ScalarSynchronousObservable;
import rx.p208a.C5694b;
import rx.p208a.C5709f;
import rx.p208a.C5714d;
import rx.p209b.C5718a;
import rx.p210c.C5722a;
import rx.p211d.C5728b;
import rx.p211d.C5731d;

/* compiled from: Observable */
/* renamed from: rx.a */
public class C5717a<T> {
    /* renamed from: b */
    static final C5728b f18270b = C5731d.m20840a().m20842c();
    /* renamed from: a */
    final C5695a<T> f18271a;

    /* compiled from: Observable */
    /* renamed from: rx.a$a */
    public interface C5695a<T> extends C5694b<C5721e<? super T>> {
    }

    /* compiled from: Observable */
    /* renamed from: rx.a$b */
    public interface C5710b<R, T> extends C5709f<C5721e<? super R>, C5721e<? super T>> {
    }

    protected C5717a(C5695a<T> c5695a) {
        this.f18271a = c5695a;
    }

    /* renamed from: a */
    public static <T> C5717a<T> m20796a(C5695a<T> c5695a) {
        return new C5717a(f18270b.m20834a((C5695a) c5695a));
    }

    /* renamed from: a */
    public final <R> C5717a<R> m20799a(C5710b<? extends R, ? super T> c5710b) {
        return new C5717a(new C5747b(this.f18271a, c5710b));
    }

    /* renamed from: a */
    public final <R> C5717a<R> m20798a(Class<R> cls) {
        return m20799a(new C5751d(cls));
    }

    /* renamed from: a */
    public final C5717a<T> m20800a(C5709f<? super T, Boolean> c5709f) {
        return m20799a(new C5753e(c5709f));
    }

    /* renamed from: b */
    public final <R> C5717a<R> m20810b(C5709f<? super T, ? extends R> c5709f) {
        return m20799a(new C5755f(c5709f));
    }

    /* renamed from: a */
    public final C5717a<T> m20801a(C5733d c5733d) {
        return m20802a(c5733d, C5834f.f18521b);
    }

    /* renamed from: a */
    public final C5717a<T> m20802a(C5733d c5733d, int i) {
        return m20803a(c5733d, false, i);
    }

    /* renamed from: a */
    public final C5717a<T> m20803a(C5733d c5733d, boolean z, int i) {
        if (this instanceof ScalarSynchronousObservable) {
            return ((ScalarSynchronousObservable) this).m20973b(c5733d);
        }
        return m20799a(new C5758g(c5733d, z, i));
    }

    /* renamed from: b */
    public final <R> C5717a<R> m20809b(Class<R> cls) {
        return m20800a(InternalObservableUtils.isInstanceOf(cls)).m20798a((Class) cls);
    }

    public final C5718a<T> m_() {
        return OperatorReplay.m20900a(this);
    }

    /* renamed from: a */
    public final C5718a<T> m20804a(int i) {
        return OperatorReplay.m20901a(this, i);
    }

    /* renamed from: a */
    public final C5718a<T> m20805a(int i, long j, TimeUnit timeUnit, C5733d c5733d) {
        if (i >= 0) {
            return OperatorReplay.m20903a(this, j, timeUnit, c5733d, i);
        }
        throw new IllegalArgumentException("bufferSize < 0");
    }

    /* renamed from: a */
    public final C5718a<T> m20806a(long j, TimeUnit timeUnit, C5733d c5733d) {
        return OperatorReplay.m20902a(this, j, timeUnit, c5733d);
    }

    /* renamed from: a */
    public final C5720f m20807a(C5694b<? super T> c5694b) {
        if (c5694b != null) {
            return m20811b(new C5800a(c5694b, InternalObservableUtils.ERROR_NOT_IMPLEMENTED, C5714d.m20795a()));
        }
        throw new IllegalArgumentException("onNext can not be null");
    }

    /* renamed from: a */
    public final C5720f m20808a(C5721e<? super T> c5721e) {
        try {
            c5721e.m20819b();
            f18270b.m20835a(this, this.f18271a).call(c5721e);
            return f18270b.m20837a((C5720f) c5721e);
        } catch (Throwable th) {
            C5736a.m20858a(th);
            f18270b.m20833a(new RuntimeException("Error occurred attempting to subscribe [" + th.getMessage() + "] and then again while trying to pass to onError.", th));
        }
    }

    /* renamed from: b */
    public final C5720f m20811b(C5721e<? super T> c5721e) {
        return C5717a.m20797a((C5721e) c5721e, this);
    }

    /* renamed from: a */
    static <T> C5720f m20797a(C5721e<? super T> c5721e, C5717a<T> c5717a) {
        if (c5721e == null) {
            throw new IllegalArgumentException("subscriber can not be null");
        } else if (c5717a.f18271a == null) {
            throw new IllegalStateException("onSubscribe function can not be null.");
        } else {
            C5720f c5722a;
            c5721e.m20819b();
            if (!(c5721e instanceof C5722a)) {
                c5722a = new C5722a(c5721e);
            }
            try {
                f18270b.m20835a(c5717a, c5717a.f18271a).call(c5722a);
                return f18270b.m20837a(c5722a);
            } catch (Throwable th) {
                C5736a.m20858a(th);
                f18270b.m20833a(new OnErrorFailedException("Error occurred attempting to subscribe [" + th.getMessage() + "] and then again while trying to pass to onError.", th));
            }
        }
    }
}
