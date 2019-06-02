package rx.p211d;

import rx.exceptions.C5736a;

/* compiled from: RxJavaErrorHandler */
/* renamed from: rx.d.a */
public abstract class C5727a {
    /* renamed from: a */
    public void m20831a(Throwable th) {
    }

    /* renamed from: a */
    public final String m20830a(Object obj) {
        try {
            return m20832b(obj);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return obj.getClass().getName() + ".errorRendering";
        } catch (Throwable th) {
            C5736a.m20858a(th);
            return obj.getClass().getName() + ".errorRendering";
        }
    }

    /* renamed from: b */
    protected String m20832b(Object obj) throws InterruptedException {
        return null;
    }
}
