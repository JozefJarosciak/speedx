package p203u.aly;

import android.content.Context;
import ch.qos.logback.classic.turbo.ReconfigureOnChangeFilter;
import p203u.aly.av.C5869o;
import p203u.aly.cf.C5932a;

/* compiled from: Defcon */
/* renamed from: u.aly.aa */
public class aa implements C5846t {
    /* renamed from: c */
    private static aa f18553c = null;
    /* renamed from: a */
    private int f18554a = 0;
    /* renamed from: b */
    private final long f18555b = ReconfigureOnChangeFilter.DEFAULT_REFRESH_PERIOD;

    /* renamed from: a */
    public static synchronized aa m21084a(Context context) {
        aa aaVar;
        synchronized (aa.class) {
            if (f18553c == null) {
                f18553c = new aa();
                f18553c.m21086a(cf.m21838a(context).m21846b().m21825a(0));
            }
            aaVar = f18553c;
        }
        return aaVar;
    }

    private aa() {
    }

    /* renamed from: a */
    public void m21087a(av avVar, Context context) {
        if (this.f18554a == 1) {
            avVar.f18721b.f18660i = null;
            avVar.f18721b.f18652a = null;
            avVar.f18721b.f18653b = null;
            avVar.f18721b.f18659h = null;
        } else if (this.f18554a == 2) {
            avVar.f18721b.f18654c.clear();
            avVar.f18721b.f18654c.add(m21089b(context));
            avVar.f18721b.f18660i = null;
            avVar.f18721b.f18652a = null;
            avVar.f18721b.f18653b = null;
            avVar.f18721b.f18659h = null;
        } else if (this.f18554a == 3) {
            avVar.f18721b.f18654c = null;
            avVar.f18721b.f18660i = null;
            avVar.f18721b.f18652a = null;
            avVar.f18721b.f18653b = null;
            avVar.f18721b.f18659h = null;
        }
    }

    /* renamed from: b */
    public C5869o m21089b(Context context) {
        C5869o c5869o = new C5869o();
        c5869o.f18707b = C5958w.m22034g(context);
        long currentTimeMillis = System.currentTimeMillis();
        c5869o.f18708c = currentTimeMillis;
        c5869o.f18709d = currentTimeMillis + ReconfigureOnChangeFilter.DEFAULT_REFRESH_PERIOD;
        c5869o.f18710e = ReconfigureOnChangeFilter.DEFAULT_REFRESH_PERIOD;
        return c5869o;
    }

    /* renamed from: a */
    public long m21085a() {
        switch (this.f18554a) {
            case 1:
                return 14400000;
            case 2:
                return 28800000;
            case 3:
                return 86400000;
            default:
                return 0;
        }
    }

    /* renamed from: a */
    public void m21086a(int i) {
        if (i >= 0 && i <= 3) {
            this.f18554a = i;
        }
    }

    /* renamed from: b */
    public boolean m21090b() {
        return this.f18554a != 0;
    }

    /* renamed from: a */
    public void mo7171a(C5932a c5932a) {
        m21086a(c5932a.m21825a(0));
    }
}
