package rx.internal.util;

import rx.p211d.C5731d;

/* compiled from: RxJavaPluginUtils */
/* renamed from: rx.internal.util.e */
public final class C5831e {
    /* renamed from: a */
    public static void m21032a(Throwable th) {
        try {
            C5731d.m20840a().m20841b().m20831a(th);
        } catch (Throwable th2) {
            C5831e.m21033b(th2);
        }
    }

    /* renamed from: b */
    private static void m21033b(Throwable th) {
        System.err.println("RxJavaErrorHandler threw an Exception. It shouldn't. => " + th.getMessage());
        th.printStackTrace();
    }
}
