package p203u.aly;

import android.content.Context;
import ch.qos.logback.core.spi.ComponentTracker;
import com.umeng.analytics.C4731a;
import com.umeng.analytics.C4744d;
import com.umeng.analytics.C4762j;
import p203u.aly.cf.C5932a;

/* compiled from: ImLatent */
/* renamed from: u.aly.ab */
public class ab implements C5846t {
    /* renamed from: l */
    private static ab f18556l = null;
    /* renamed from: a */
    private final long f18557a = 1296000000;
    /* renamed from: b */
    private final long f18558b = 129600000;
    /* renamed from: c */
    private final int f18559c = ComponentTracker.DEFAULT_TIMEOUT;
    /* renamed from: d */
    private final int f18560d = 10000;
    /* renamed from: e */
    private C4762j f18561e;
    /* renamed from: f */
    private C5959x f18562f;
    /* renamed from: g */
    private long f18563g = 1296000000;
    /* renamed from: h */
    private int f18564h = 10000;
    /* renamed from: i */
    private long f18565i = 0;
    /* renamed from: j */
    private long f18566j = 0;
    /* renamed from: k */
    private Context f18567k;

    /* renamed from: a */
    public static synchronized ab m21091a(Context context, C5959x c5959x) {
        ab abVar;
        synchronized (ab.class) {
            if (f18556l == null) {
                f18556l = new ab(context, c5959x);
                f18556l.mo7171a(cf.m21838a(context).m21846b());
            }
            abVar = f18556l;
        }
        return abVar;
    }

    private ab(Context context, C5959x c5959x) {
        this.f18567k = context;
        this.f18561e = C4762j.m18682a(context);
        this.f18562f = c5959x;
    }

    /* renamed from: a */
    public boolean m21093a() {
        if (this.f18561e.m18703g() || this.f18562f.m22047e()) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis() - this.f18562f.m22056n();
        if (currentTimeMillis > this.f18563g) {
            this.f18565i = (long) C4744d.m18638a(this.f18564h, bx.m21783a(this.f18567k));
            this.f18566j = currentTimeMillis;
            return true;
        } else if (currentTimeMillis <= 129600000) {
            return false;
        } else {
            this.f18565i = 0;
            this.f18566j = currentTimeMillis;
            return true;
        }
    }

    /* renamed from: b */
    public long m21094b() {
        return this.f18565i;
    }

    /* renamed from: a */
    public void mo7171a(C5932a c5932a) {
        this.f18563g = c5932a.m21826a(1296000000);
        int b = c5932a.m21831b(0);
        if (b != 0) {
            this.f18564h = b;
        } else if (C4731a.f16615k <= 0 || C4731a.f16615k > ComponentTracker.DEFAULT_TIMEOUT) {
            this.f18564h = 10000;
        } else {
            this.f18564h = C4731a.f16615k;
        }
    }
}
