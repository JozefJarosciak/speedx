package rx.internal.operators;

import java.io.Serializable;
import rx.C5719b;

public final class NotificationLite<T> {
    /* renamed from: a */
    private static final NotificationLite f18302a = new NotificationLite();
    /* renamed from: b */
    private static final Object f18303b = new C57371();
    /* renamed from: c */
    private static final Object f18304c = new C57382();

    /* renamed from: rx.internal.operators.NotificationLite$1 */
    static class C57371 implements Serializable {
        private static final long serialVersionUID = 1;

        C57371() {
        }

        public String toString() {
            return "Notification=>Completed";
        }
    }

    /* renamed from: rx.internal.operators.NotificationLite$2 */
    static class C57382 implements Serializable {
        private static final long serialVersionUID = 2;

        C57382() {
        }

        public String toString() {
            return "Notification=>NULL";
        }
    }

    private static class OnErrorSentinel implements Serializable {
        private static final long serialVersionUID = 3;
        /* renamed from: a */
        final Throwable f18301a;

        public OnErrorSentinel(Throwable th) {
            this.f18301a = th;
        }

        public String toString() {
            return "Notification=>Error:" + this.f18301a;
        }
    }

    private NotificationLite() {
    }

    /* renamed from: a */
    public static <T> NotificationLite<T> m20864a() {
        return f18302a;
    }

    /* renamed from: a */
    public Object m20865a(T t) {
        if (t == null) {
            return f18304c;
        }
        return t;
    }

    /* renamed from: b */
    public Object m20868b() {
        return f18303b;
    }

    /* renamed from: a */
    public Object m20866a(Throwable th) {
        return new OnErrorSentinel(th);
    }

    /* renamed from: a */
    public boolean m20867a(C5719b<? super T> c5719b, Object obj) {
        if (obj == f18303b) {
            c5719b.mo7149a();
            return true;
        } else if (obj == f18304c) {
            c5719b.mo7150a(null);
            return false;
        } else if (obj == null) {
            throw new IllegalArgumentException("The lite notification can not be null");
        } else if (obj.getClass() == OnErrorSentinel.class) {
            c5719b.mo7151a(((OnErrorSentinel) obj).f18301a);
            return true;
        } else {
            c5719b.mo7150a(obj);
            return false;
        }
    }

    /* renamed from: b */
    public boolean m20869b(Object obj) {
        return obj == f18303b;
    }

    /* renamed from: c */
    public boolean m20870c(Object obj) {
        return obj instanceof OnErrorSentinel;
    }

    /* renamed from: d */
    public T m20871d(Object obj) {
        return obj == f18304c ? null : obj;
    }
}
