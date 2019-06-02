package rx.internal.util;

import java.util.List;
import java.util.concurrent.TimeUnit;
import rx.C5717a;
import rx.C5717a.C5710b;
import rx.C5733d;
import rx.Notification;
import rx.exceptions.OnErrorNotImplementedException;
import rx.internal.operators.C5749c;
import rx.p208a.C5694b;
import rx.p208a.C5709f;
import rx.p208a.C5712c;
import rx.p208a.C5715e;
import rx.p208a.C5716g;
import rx.p209b.C5718a;

public enum InternalObservableUtils {
    ;
    
    public static final C5781g COUNTER = null;
    public static final C5694b<Throwable> ERROR_NOT_IMPLEMENTED = null;
    public static final C5710b<Boolean, Object> IS_EMPTY = null;
    public static final C5782h LONG_COUNTER = null;
    public static final C5780f OBJECT_EQUALS = null;
    public static final C5791q TO_ARRAY = null;
    /* renamed from: a */
    static final C5789o f18459a = null;
    /* renamed from: b */
    static final C5779e f18460b = null;

    /* renamed from: rx.internal.util.InternalObservableUtils$a */
    static final class C5775a<T, R> implements C5716g<R, T, R> {
        /* renamed from: a */
        final C5712c<R, ? super T> f18440a;

        public C5775a(C5712c<R, ? super T> c5712c) {
            this.f18440a = c5712c;
        }
    }

    /* renamed from: rx.internal.util.InternalObservableUtils$b */
    static final class C5776b implements C5709f<Object, Boolean> {
        /* renamed from: a */
        final Object f18441a;

        public /* synthetic */ Object call(Object obj) {
            return m20957a(obj);
        }

        public C5776b(Object obj) {
            this.f18441a = obj;
        }

        /* renamed from: a */
        public Boolean m20957a(Object obj) {
            boolean z = obj == this.f18441a || (obj != null && obj.equals(this.f18441a));
            return Boolean.valueOf(z);
        }
    }

    /* renamed from: rx.internal.util.InternalObservableUtils$c */
    static final class C5777c implements C5694b<Throwable> {
        C5777c() {
        }

        public /* synthetic */ void call(Object obj) {
            m20958a((Throwable) obj);
        }

        /* renamed from: a */
        public void m20958a(Throwable th) {
            throw new OnErrorNotImplementedException(th);
        }
    }

    /* renamed from: rx.internal.util.InternalObservableUtils$d */
    static final class C5778d implements C5709f<Object, Boolean> {
        /* renamed from: a */
        final Class<?> f18442a;

        public /* synthetic */ Object call(Object obj) {
            return m20959a(obj);
        }

        public C5778d(Class<?> cls) {
            this.f18442a = cls;
        }

        /* renamed from: a */
        public Boolean m20959a(Object obj) {
            return Boolean.valueOf(this.f18442a.isInstance(obj));
        }
    }

    /* renamed from: rx.internal.util.InternalObservableUtils$e */
    static final class C5779e implements C5709f<Notification<?>, Throwable> {
        C5779e() {
        }

        public /* synthetic */ Object call(Object obj) {
            return m20960a((Notification) obj);
        }

        /* renamed from: a */
        public Throwable m20960a(Notification<?> notification) {
            return notification.m20788a();
        }
    }

    /* renamed from: rx.internal.util.InternalObservableUtils$f */
    static final class C5780f implements C5716g<Object, Object, Boolean> {
        C5780f() {
        }
    }

    /* renamed from: rx.internal.util.InternalObservableUtils$g */
    static final class C5781g implements C5716g<Integer, Object, Integer> {
        C5781g() {
        }
    }

    /* renamed from: rx.internal.util.InternalObservableUtils$h */
    static final class C5782h implements C5716g<Long, Object, Long> {
        C5782h() {
        }
    }

    /* renamed from: rx.internal.util.InternalObservableUtils$i */
    static final class C5783i implements C5709f<C5717a<? extends Notification<?>>, C5717a<?>> {
        /* renamed from: a */
        final C5709f<? super C5717a<? extends Void>, ? extends C5717a<?>> f18443a;

        public /* synthetic */ Object call(Object obj) {
            return m20961a((C5717a) obj);
        }

        public C5783i(C5709f<? super C5717a<? extends Void>, ? extends C5717a<?>> c5709f) {
            this.f18443a = c5709f;
        }

        /* renamed from: a */
        public C5717a<?> m20961a(C5717a<? extends Notification<?>> c5717a) {
            return (C5717a) this.f18443a.call(c5717a.m20810b(InternalObservableUtils.f18459a));
        }
    }

    /* renamed from: rx.internal.util.InternalObservableUtils$j */
    static final class C5784j<T> implements C5715e<C5718a<T>> {
        /* renamed from: a */
        private final C5717a<T> f18444a;
        /* renamed from: b */
        private final int f18445b;

        public /* synthetic */ Object call() {
            return m20962a();
        }

        private C5784j(C5717a<T> c5717a, int i) {
            this.f18444a = c5717a;
            this.f18445b = i;
        }

        /* renamed from: a */
        public C5718a<T> m20962a() {
            return this.f18444a.m20804a(this.f18445b);
        }
    }

    /* renamed from: rx.internal.util.InternalObservableUtils$k */
    static final class C5785k<T> implements C5715e<C5718a<T>> {
        /* renamed from: a */
        private final TimeUnit f18446a;
        /* renamed from: b */
        private final C5717a<T> f18447b;
        /* renamed from: c */
        private final long f18448c;
        /* renamed from: d */
        private final C5733d f18449d;

        public /* synthetic */ Object call() {
            return m20963a();
        }

        private C5785k(C5717a<T> c5717a, long j, TimeUnit timeUnit, C5733d c5733d) {
            this.f18446a = timeUnit;
            this.f18447b = c5717a;
            this.f18448c = j;
            this.f18449d = c5733d;
        }

        /* renamed from: a */
        public C5718a<T> m20963a() {
            return this.f18447b.m20806a(this.f18448c, this.f18446a, this.f18449d);
        }
    }

    /* renamed from: rx.internal.util.InternalObservableUtils$l */
    private static final class C5786l<T> implements C5715e<C5718a<T>> {
        /* renamed from: a */
        private final C5717a<T> f18450a;

        public /* synthetic */ Object call() {
            return m20964a();
        }

        private C5786l(C5717a<T> c5717a) {
            this.f18450a = c5717a;
        }

        /* renamed from: a */
        public C5718a<T> m20964a() {
            return this.f18450a.m_();
        }
    }

    /* renamed from: rx.internal.util.InternalObservableUtils$m */
    static final class C5787m<T> implements C5715e<C5718a<T>> {
        /* renamed from: a */
        private final long f18451a;
        /* renamed from: b */
        private final TimeUnit f18452b;
        /* renamed from: c */
        private final C5733d f18453c;
        /* renamed from: d */
        private final int f18454d;
        /* renamed from: e */
        private final C5717a<T> f18455e;

        public /* synthetic */ Object call() {
            return m20965a();
        }

        private C5787m(C5717a<T> c5717a, int i, long j, TimeUnit timeUnit, C5733d c5733d) {
            this.f18451a = j;
            this.f18452b = timeUnit;
            this.f18453c = c5733d;
            this.f18454d = i;
            this.f18455e = c5717a;
        }

        /* renamed from: a */
        public C5718a<T> m20965a() {
            return this.f18455e.m20805a(this.f18454d, this.f18451a, this.f18452b, this.f18453c);
        }
    }

    /* renamed from: rx.internal.util.InternalObservableUtils$n */
    static final class C5788n implements C5709f<C5717a<? extends Notification<?>>, C5717a<?>> {
        /* renamed from: a */
        final C5709f<? super C5717a<? extends Throwable>, ? extends C5717a<?>> f18456a;

        public /* synthetic */ Object call(Object obj) {
            return m20966a((C5717a) obj);
        }

        public C5788n(C5709f<? super C5717a<? extends Throwable>, ? extends C5717a<?>> c5709f) {
            this.f18456a = c5709f;
        }

        /* renamed from: a */
        public C5717a<?> m20966a(C5717a<? extends Notification<?>> c5717a) {
            return (C5717a) this.f18456a.call(c5717a.m20810b(InternalObservableUtils.f18460b));
        }
    }

    /* renamed from: rx.internal.util.InternalObservableUtils$o */
    static final class C5789o implements C5709f<Object, Void> {
        C5789o() {
        }

        public /* synthetic */ Object call(Object obj) {
            return m20967a(obj);
        }

        /* renamed from: a */
        public Void m20967a(Object obj) {
            return null;
        }
    }

    /* renamed from: rx.internal.util.InternalObservableUtils$p */
    static final class C5790p<T, R> implements C5709f<C5717a<T>, C5717a<R>> {
        /* renamed from: a */
        final C5709f<? super C5717a<T>, ? extends C5717a<R>> f18457a;
        /* renamed from: b */
        final C5733d f18458b;

        public /* synthetic */ Object call(Object obj) {
            return m20968a((C5717a) obj);
        }

        public C5790p(C5709f<? super C5717a<T>, ? extends C5717a<R>> c5709f, C5733d c5733d) {
            this.f18457a = c5709f;
            this.f18458b = c5733d;
        }

        /* renamed from: a */
        public C5717a<R> m20968a(C5717a<T> c5717a) {
            return ((C5717a) this.f18457a.call(c5717a)).m20801a(this.f18458b);
        }
    }

    /* renamed from: rx.internal.util.InternalObservableUtils$q */
    static final class C5791q implements C5709f<List<? extends C5717a<?>>, C5717a<?>[]> {
        C5791q() {
        }

        public /* synthetic */ Object call(Object obj) {
            return m20969a((List) obj);
        }

        /* renamed from: a */
        public C5717a<?>[] m20969a(List<? extends C5717a<?>> list) {
            return (C5717a[]) list.toArray(new C5717a[list.size()]);
        }
    }

    static {
        COUNTER = new C5781g();
        LONG_COUNTER = new C5782h();
        OBJECT_EQUALS = new C5780f();
        TO_ARRAY = new C5791q();
        f18459a = new C5789o();
        f18460b = new C5779e();
        ERROR_NOT_IMPLEMENTED = new C5777c();
        IS_EMPTY = new C5749c(UtilityFunctions.m20974a(), true);
    }

    public static C5709f<Object, Boolean> equalsWith(Object obj) {
        return new C5776b(obj);
    }

    public static C5709f<Object, Boolean> isInstanceOf(Class<?> cls) {
        return new C5778d(cls);
    }

    public static final C5709f<C5717a<? extends Notification<?>>, C5717a<?>> createRepeatDematerializer(C5709f<? super C5717a<? extends Void>, ? extends C5717a<?>> c5709f) {
        return new C5783i(c5709f);
    }

    public static <T, R> C5709f<C5717a<T>, C5717a<R>> createReplaySelectorAndObserveOn(C5709f<? super C5717a<T>, ? extends C5717a<R>> c5709f, C5733d c5733d) {
        return new C5790p(c5709f, c5733d);
    }

    public static final C5709f<C5717a<? extends Notification<?>>, C5717a<?>> createRetryDematerializer(C5709f<? super C5717a<? extends Throwable>, ? extends C5717a<?>> c5709f) {
        return new C5788n(c5709f);
    }

    public static <T> C5715e<C5718a<T>> createReplaySupplier(C5717a<T> c5717a) {
        return new C5786l(c5717a);
    }

    public static <T> C5715e<C5718a<T>> createReplaySupplier(C5717a<T> c5717a, int i) {
        return new C5784j(c5717a, i);
    }

    public static <T> C5715e<C5718a<T>> createReplaySupplier(C5717a<T> c5717a, long j, TimeUnit timeUnit, C5733d c5733d) {
        return new C5785k(c5717a, j, timeUnit, c5733d);
    }

    public static <T> C5715e<C5718a<T>> createReplaySupplier(C5717a<T> c5717a, int i, long j, TimeUnit timeUnit, C5733d c5733d) {
        return new C5787m(c5717a, i, j, timeUnit, c5733d);
    }

    public static <T, R> C5716g<R, T, R> createCollectorCaller(C5712c<R, ? super T> c5712c) {
        return new C5775a(c5712c);
    }
}
