package p203u.aly;

import android.content.Context;
import com.umeng.analytics.C4747i;
import com.umeng.analytics.C4754h;

/* compiled from: CacheService */
/* renamed from: u.aly.f */
public final class C5943f implements C5938m {
    /* renamed from: c */
    private static C5943f f19053c;
    /* renamed from: a */
    private C5938m f19054a = new C5939e(this.f19055b);
    /* renamed from: b */
    private Context f19055b;

    /* compiled from: CacheService */
    /* renamed from: u.aly.f$2 */
    class C59412 extends C4747i {
        /* renamed from: a */
        final /* synthetic */ C5943f f19051a;

        C59412(C5943f c5943f) {
            this.f19051a = c5943f;
        }

        /* renamed from: a */
        public void mo6180a() {
            this.f19051a.f19054a.mo7224b();
        }
    }

    /* compiled from: CacheService */
    /* renamed from: u.aly.f$3 */
    class C59423 extends C4747i {
        /* renamed from: a */
        final /* synthetic */ C5943f f19052a;

        C59423(C5943f c5943f) {
            this.f19052a = c5943f;
        }

        /* renamed from: a */
        public void mo6180a() {
            this.f19052a.f19054a.mo7226c();
        }
    }

    private C5943f(Context context) {
        this.f19055b = context.getApplicationContext();
    }

    /* renamed from: a */
    public static synchronized C5943f m21932a(Context context) {
        C5943f c5943f;
        synchronized (C5943f.class) {
            if (f19053c == null && context != null) {
                f19053c = new C5943f(context);
            }
            c5943f = f19053c;
        }
        return c5943f;
    }

    /* renamed from: a */
    public void mo7223a(final C5862n c5862n) {
        C4754h.m18673b(new C4747i(this) {
            /* renamed from: b */
            final /* synthetic */ C5943f f19050b;

            /* renamed from: a */
            public void mo6180a() {
                this.f19050b.f19054a.mo7223a(c5862n);
            }
        });
    }

    /* renamed from: b */
    public void mo7225b(C5862n c5862n) {
        this.f19054a.mo7225b(c5862n);
    }

    /* renamed from: b */
    public void mo7224b() {
        C4754h.m18673b(new C59412(this));
    }

    /* renamed from: c */
    public void mo7226c() {
        C4754h.m18674c(new C59423(this));
    }
}
