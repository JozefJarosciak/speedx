package rx.internal.util;

import rx.p208a.C5709f;
import rx.p208a.C5715e;
import rx.p208a.C5716g;

public final class UtilityFunctions {
    /* renamed from: a */
    private static final C5797a f18477a = new C5797a();

    private enum AlwaysTrue implements C5709f<Object, Boolean> {
        INSTANCE;

        public Boolean call(Object obj) {
            return Boolean.valueOf(true);
        }
    }

    /* renamed from: rx.internal.util.UtilityFunctions$a */
    private static final class C5797a<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, R> implements C5715e<R>, C5709f<T0, R>, C5716g<T0, T1, R> {
        C5797a() {
        }

        public R call() {
            return null;
        }

        public R call(T0 t0) {
            return null;
        }
    }

    /* renamed from: a */
    public static <T> C5709f<? super T, Boolean> m20974a() {
        return AlwaysTrue.INSTANCE;
    }
}
