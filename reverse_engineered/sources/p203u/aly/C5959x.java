package p203u.aly;

import android.content.Context;
import android.content.SharedPreferences;
import ch.qos.logback.core.CoreConstants;
import com.umeng.analytics.C4762j;

/* compiled from: StatTracer */
/* renamed from: u.aly.x */
public class C5959x implements C5951o {
    /* renamed from: a */
    public int f19109a;
    /* renamed from: b */
    public int f19110b;
    /* renamed from: c */
    public long f19111c;
    /* renamed from: d */
    private final int f19112d = CoreConstants.MILLIS_IN_ONE_HOUR;
    /* renamed from: e */
    private int f19113e;
    /* renamed from: f */
    private long f19114f = 0;
    /* renamed from: g */
    private long f19115g = 0;
    /* renamed from: h */
    private Context f19116h;

    public C5959x(Context context) {
        m22041a(context);
    }

    /* renamed from: a */
    private void m22041a(Context context) {
        this.f19116h = context.getApplicationContext();
        SharedPreferences a = C5955u.m22014a(context);
        this.f19109a = a.getInt("successful_request", 0);
        this.f19110b = a.getInt("failed_requests ", 0);
        this.f19113e = a.getInt("last_request_spent_ms", 0);
        this.f19111c = a.getLong("last_request_time", 0);
        this.f19114f = a.getLong("last_req", 0);
    }

    /* renamed from: e */
    public boolean m22047e() {
        boolean z;
        if (this.f19111c == 0) {
            z = true;
        } else {
            z = false;
        }
        boolean z2;
        if (C4762j.m18682a(this.f19116h).m18703g()) {
            z2 = false;
        } else {
            z2 = true;
        }
        if (z && r3) {
            return true;
        }
        return false;
    }

    /* renamed from: f */
    public void m22048f() {
        this.f19109a++;
        this.f19111c = this.f19114f;
    }

    /* renamed from: g */
    public void m22049g() {
        this.f19110b++;
    }

    /* renamed from: h */
    public void m22050h() {
        this.f19114f = System.currentTimeMillis();
    }

    /* renamed from: i */
    public void m22051i() {
        this.f19113e = (int) (System.currentTimeMillis() - this.f19114f);
    }

    /* renamed from: j */
    public void m22052j() {
        C5955u.m22014a(this.f19116h).edit().putInt("successful_request", this.f19109a).putInt("failed_requests ", this.f19110b).putInt("last_request_spent_ms", this.f19113e).putLong("last_request_time", this.f19111c).putLong("last_req", this.f19114f).commit();
    }

    /* renamed from: k */
    public void m22053k() {
        C5955u.m22014a(this.f19116h).edit().putLong("first_activate_time", System.currentTimeMillis()).commit();
    }

    /* renamed from: l */
    public boolean m22054l() {
        if (this.f19115g == 0) {
            this.f19115g = C5955u.m22014a(this.f19116h).getLong("first_activate_time", 0);
        }
        return this.f19115g == 0;
    }

    /* renamed from: m */
    public long m22055m() {
        return m22054l() ? System.currentTimeMillis() : this.f19115g;
    }

    /* renamed from: n */
    public long m22056n() {
        return this.f19114f;
    }

    /* renamed from: a */
    public static void m22042a(Context context, av avVar) {
        SharedPreferences a = C5955u.m22014a(context);
        avVar.f18720a.f18674L = (long) a.getInt("failed_requests ", 0);
        avVar.f18720a.f18673K = (long) a.getInt("successful_request", 0);
        avVar.f18720a.f18675M = (long) a.getInt("last_request_spent_ms", 0);
    }

    /* renamed from: a */
    public void mo7230a() {
        m22050h();
    }

    /* renamed from: b */
    public void mo7231b() {
        m22051i();
    }

    /* renamed from: c */
    public void mo7232c() {
        m22048f();
    }

    /* renamed from: d */
    public void mo7233d() {
        m22049g();
    }
}
