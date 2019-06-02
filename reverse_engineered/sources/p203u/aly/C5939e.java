package p203u.aly;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;
import com.umeng.analytics.C4731a;
import com.umeng.analytics.C4742b.C4732h;
import com.umeng.analytics.C4742b.C4733a;
import com.umeng.analytics.C4742b.C4734b;
import com.umeng.analytics.C4742b.C4735c;
import com.umeng.analytics.C4742b.C4736d;
import com.umeng.analytics.C4742b.C4737e;
import com.umeng.analytics.C4742b.C4738f;
import com.umeng.analytics.C4742b.C4739g;
import com.umeng.analytics.C4742b.C4740i;
import com.umeng.analytics.C4742b.C4741j;
import com.umeng.analytics.C4744d;
import com.umeng.analytics.C4747i;
import com.umeng.analytics.C4754h;
import com.umeng.analytics.C4762j;
import java.util.List;
import p203u.aly.av.C5861h;
import p203u.aly.av.C5869o;
import p203u.aly.cf.C5932a;

/* compiled from: CacheImpl */
/* renamed from: u.aly.e */
public final class C5939e implements C5938m, C5846t {
    /* renamed from: o */
    private static Context f19034o;
    /* renamed from: a */
    private final long f19035a = 28800000;
    /* renamed from: b */
    private final int f19036b = 5000;
    /* renamed from: c */
    private C5953q f19037c = null;
    /* renamed from: d */
    private C4762j f19038d = null;
    /* renamed from: e */
    private C5959x f19039e = null;
    /* renamed from: f */
    private aa f19040f = null;
    /* renamed from: g */
    private C5961z f19041g = null;
    /* renamed from: h */
    private ab f19042h = null;
    /* renamed from: i */
    private C5937a f19043i = null;
    /* renamed from: j */
    private C5932a f19044j = null;
    /* renamed from: k */
    private int f19045k = 10;
    /* renamed from: l */
    private long f19046l = 0;
    /* renamed from: m */
    private int f19047m = 0;
    /* renamed from: n */
    private int f19048n = 0;

    /* compiled from: CacheImpl */
    /* renamed from: u.aly.e$1 */
    class C59361 extends C4747i {
        /* renamed from: a */
        final /* synthetic */ C5939e f19027a;

        C59361(C5939e c5939e) {
            this.f19027a = c5939e;
        }

        /* renamed from: a */
        public void mo6180a() {
            this.f19027a.m21923a();
        }
    }

    /* compiled from: CacheImpl */
    /* renamed from: u.aly.e$a */
    public class C5937a {
        /* renamed from: a */
        final /* synthetic */ C5939e f19028a;
        /* renamed from: b */
        private C4732h f19029b;
        /* renamed from: c */
        private int f19030c = -1;
        /* renamed from: d */
        private int f19031d = -1;
        /* renamed from: e */
        private int f19032e = -1;
        /* renamed from: f */
        private int f19033f = -1;

        public C5937a(C5939e c5939e) {
            this.f19028a = c5939e;
            int[] a = c5939e.f19044j.m21830a(-1, -1);
            this.f19030c = a[0];
            this.f19031d = a[1];
        }

        /* renamed from: a */
        protected void m21898a(boolean z) {
            int i = 1;
            int i2 = 0;
            if (this.f19028a.f19040f.m21090b()) {
                C4732h c4732h;
                if (!((this.f19029b instanceof C4734b) && this.f19029b.mo6178a())) {
                    i = 0;
                }
                if (i != 0) {
                    c4732h = this.f19029b;
                } else {
                    c4732h = new C4734b(this.f19028a.f19039e, this.f19028a.f19040f);
                }
                this.f19029b = c4732h;
            } else {
                if (!((this.f19029b instanceof C4735c) && this.f19029b.mo6178a())) {
                    i = 0;
                }
                if (i == 0) {
                    if (z && this.f19028a.f19042h.m21093a()) {
                        this.f19029b = new C4735c((int) this.f19028a.f19042h.m21094b());
                        this.f19028a.m21911b((int) this.f19028a.f19042h.m21094b());
                    } else if (ah.f18581a && this.f19028a.f19044j.m21833b()) {
                        ah.m21153a("Debug: send log every 15 seconds");
                        this.f19029b = new C4733a(this.f19028a.f19039e);
                    } else if (this.f19028a.f19041g.m22070a()) {
                        ah.m21153a("Start A/B Test");
                        if (this.f19028a.f19041g.m22071b() == 6) {
                            if (this.f19028a.f19044j.m21829a()) {
                                i2 = this.f19028a.f19044j.m21836d(90000);
                            } else if (this.f19031d > 0) {
                                i2 = this.f19031d;
                            } else {
                                i2 = this.f19033f;
                            }
                        }
                        this.f19029b = m21896a(this.f19028a.f19041g.m22071b(), i2);
                    } else {
                        i = this.f19032e;
                        i2 = this.f19033f;
                        if (this.f19030c != -1) {
                            i = this.f19030c;
                            i2 = this.f19031d;
                        }
                        this.f19029b = m21896a(i, i2);
                    }
                }
            }
            ah.m21153a("Report policy : " + this.f19029b.getClass().getSimpleName());
        }

        /* renamed from: b */
        public C4732h m21899b(boolean z) {
            m21898a(z);
            return this.f19029b;
        }

        /* renamed from: a */
        private C4732h m21896a(int i, int i2) {
            switch (i) {
                case 0:
                    return this.f19029b instanceof C4739g ? this.f19029b : new C4739g();
                case 1:
                    return this.f19029b instanceof C4736d ? this.f19029b : new C4736d();
                case 4:
                    if (this.f19029b instanceof C4738f) {
                        return this.f19029b;
                    }
                    return new C4738f(this.f19028a.f19039e);
                case 5:
                    if (this.f19029b instanceof C4740i) {
                        return this.f19029b;
                    }
                    return new C4740i(C5939e.f19034o);
                case 6:
                    if (!(this.f19029b instanceof C4737e)) {
                        return new C4737e(this.f19028a.f19039e, (long) i2);
                    }
                    C4732h c4732h = this.f19029b;
                    ((C4737e) c4732h).m18631a((long) i2);
                    return c4732h;
                case 8:
                    if (this.f19029b instanceof C4741j) {
                        return this.f19029b;
                    }
                    return new C4741j(this.f19028a.f19039e);
                default:
                    if (this.f19029b instanceof C4736d) {
                        return this.f19029b;
                    }
                    return new C4736d();
            }
        }

        /* renamed from: a */
        public void m21897a(C5932a c5932a) {
            int[] a = c5932a.m21830a(-1, -1);
            this.f19030c = a[0];
            this.f19031d = a[1];
        }
    }

    public C5939e(Context context) {
        f19034o = context;
        this.f19037c = new C5953q(context);
        this.f19039e = new C5959x(context);
        this.f19038d = C4762j.m18682a(context);
        this.f19044j = cf.m21838a(context).m21846b();
        this.f19043i = new C5937a(this);
        this.f19041g = C5961z.m22062a(f19034o);
        this.f19040f = aa.m21084a(f19034o);
        this.f19042h = ab.m21091a(f19034o, this.f19039e);
        SharedPreferences a = C5955u.m22014a(f19034o);
        this.f19046l = a.getLong("thtstart", 0);
        this.f19047m = a.getInt("gkvc", 0);
        this.f19048n = a.getInt("ekvc", 0);
    }

    /* renamed from: a */
    public void m21923a() {
        if (af.m21128j(f19034o)) {
            m21920f();
        } else {
            ah.m21153a("network is unavailable");
        }
    }

    /* renamed from: a */
    public void mo7223a(C5862n c5862n) {
        if (c5862n != null) {
            this.f19037c.m22007a(c5862n);
        }
        m21909a(c5862n instanceof C5869o);
    }

    /* renamed from: b */
    public void mo7225b(C5862n c5862n) {
        this.f19037c.m22007a(c5862n);
    }

    /* renamed from: b */
    public void mo7224b() {
        if (this.f19037c.m22005a() > 0) {
            try {
                this.f19038d.m18694a(m21922a(new int[0]));
            } catch (Throwable th) {
                ah.m21157a(th);
                if (th instanceof OutOfMemoryError) {
                    this.f19038d.m18702f();
                }
                if (th != null) {
                    th.printStackTrace();
                }
            }
        }
        C5955u.m22014a(f19034o).edit().putLong("thtstart", this.f19046l).putInt("gkvc", this.f19047m).putInt("ekvc", this.f19048n).commit();
    }

    /* renamed from: c */
    public void mo7226c() {
        m21907a(m21922a(new int[0]));
    }

    /* renamed from: a */
    private void m21909a(boolean z) {
        boolean e = this.f19039e.m22047e();
        if (e) {
            av.f18718c = this.f19039e.m22055m();
        }
        if (m21913b(z)) {
            m21920f();
        } else if (e || m21919e()) {
            mo7224b();
        }
    }

    /* renamed from: a */
    private void m21905a(int i) {
        int currentTimeMillis = (int) (System.currentTimeMillis() - this.f19039e.m22056n());
        m21907a(m21922a(i, currentTimeMillis));
        C4754h.m18672a(new C59361(this), (long) i);
    }

    /* renamed from: a */
    private void m21907a(av avVar) {
        if (avVar != null) {
            try {
                byte[] a;
                bz a2 = bz.m21801a(f19034o);
                a2.m21805a();
                try {
                    a = new an().m21192a(a2.m21807b());
                    avVar.f18720a.f18677O = Base64.encodeToString(a, 0);
                } catch (Exception e) {
                }
                a = C4762j.m18682a(f19034o).m18698b(m21914c(avVar));
                if (a != null && !C4744d.m18641a(f19034o, a)) {
                    bx b;
                    if (m21921g()) {
                        b = bx.m21786b(f19034o, C4731a.m18614a(f19034o), a);
                    } else {
                        b = bx.m21784a(f19034o, C4731a.m18614a(f19034o), a);
                    }
                    a = b.m21795c();
                    C4762j a3 = C4762j.m18682a(f19034o);
                    a3.m18702f();
                    a3.m18695a(a);
                    a2.m21808c();
                    av.f18718c = 0;
                }
            } catch (Exception e2) {
            }
        }
    }

    /* renamed from: a */
    protected av m21922a(int... iArr) {
        Object obj = null;
        try {
            if (TextUtils.isEmpty(C4731a.m18614a(f19034o))) {
                ah.m21165d("Appkey is missing ,Please check AndroidManifest.xml");
                return null;
            }
            av e = C4762j.m18682a(f19034o).m18701e();
            if (e == null && this.f19037c.m22005a() == 0) {
                return null;
            }
            if (e == null) {
                e = new av();
            }
            this.f19037c.m22006a(e);
            if (e.f18721b.f18654c != null && ah.f18581a && e.f18721b.f18654c.size() > 0) {
                for (C5869o c5869o : e.f18721b.f18654c) {
                    Object obj2;
                    if (c5869o.f18712h.size() > 0) {
                        obj2 = 1;
                    } else {
                        obj2 = obj;
                    }
                    obj = obj2;
                }
                if (obj == null) {
                    ah.m21162c("missing Activities or PageViews");
                }
            }
            this.f19040f.m21087a(e, f19034o);
            if (iArr != null && iArr.length == 2) {
                e.f18721b.f18656e.f18625a = Integer.valueOf(iArr[0] / 1000);
                e.f18721b.f18656e.f18626b = (long) iArr[1];
                e.f18721b.f18656e.f18627c = true;
            }
            return e;
        } catch (Throwable e2) {
            ah.m21160b("Fail to construct message ...", e2);
            C4762j.m18682a(f19034o).m18702f();
            ah.m21157a(e2);
            return null;
        }
    }

    /* renamed from: b */
    private boolean m21913b(boolean z) {
        if (!af.m21128j(f19034o)) {
            ah.m21153a("network is unavailable");
            return false;
        } else if (this.f19039e.m22047e()) {
            return true;
        } else {
            return this.f19043i.m21899b(z).mo6177a(z);
        }
    }

    /* renamed from: e */
    private boolean m21919e() {
        return this.f19037c.m22005a() > this.f19045k;
    }

    /* renamed from: f */
    private void m21920f() {
        try {
            if (this.f19038d.m18703g()) {
                C5957v c5957v = new C5957v(f19034o, this.f19039e);
                c5957v.m22027a((C5846t) this);
                if (this.f19040f.m21090b()) {
                    c5957v.m22029b(true);
                }
                c5957v.m22025a();
                return;
            }
            av a = m21922a(new int[0]);
            if (m21912b(a)) {
                C5957v c5957v2 = new C5957v(f19034o, this.f19039e);
                c5957v2.m22027a((C5846t) this);
                if (this.f19040f.m21090b()) {
                    c5957v2.m22029b(true);
                }
                c5957v2.m22026a(m21914c(a));
                c5957v2.m22028a(m21921g());
                c5957v2.m22025a();
            }
        } catch (Throwable th) {
            if (th instanceof OutOfMemoryError) {
                if (th != null) {
                    th.printStackTrace();
                }
            } else if (th != null) {
                th.printStackTrace();
            }
        }
    }

    /* renamed from: b */
    private boolean m21912b(av avVar) {
        if (avVar != null && avVar.m21240a()) {
            return true;
        }
        return false;
    }

    /* renamed from: c */
    private av m21914c(av avVar) {
        int i;
        int i2;
        int i3 = 5000;
        if (avVar.f18721b.f18652a != null) {
            i = 0;
            for (i2 = 0; i2 < avVar.f18721b.f18652a.size(); i2++) {
                i += ((C5861h) avVar.f18721b.f18652a.get(i2)).f18630b.size();
            }
        } else {
            i = 0;
        }
        if (avVar.f18721b.f18653b != null) {
            for (i2 = 0; i2 < avVar.f18721b.f18653b.size(); i2++) {
                i += ((C5861h) avVar.f18721b.f18653b.get(i2)).f18630b.size();
            }
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.f19046l > 28800000) {
            int i4 = i - 5000;
            if (i4 > 0) {
                m21906a(-5000, i4, avVar);
            }
            this.f19047m = 0;
            if (i4 > 0) {
                i = 5000;
            }
            this.f19048n = i;
            this.f19046l = currentTimeMillis;
        } else {
            int i5 = this.f19047m > 5000 ? 0 : (this.f19047m + 0) - 5000;
            i2 = this.f19048n > 5000 ? i : (this.f19048n + i) - 5000;
            if (i5 > 0 || i2 > 0) {
                m21906a(i5, i2, avVar);
            }
            this.f19047m = i5 > 0 ? 5000 : this.f19047m + 0;
            if (i2 <= 0) {
                i3 = this.f19048n + i;
            }
            this.f19048n = i3;
        }
        return avVar;
    }

    /* renamed from: a */
    private void m21906a(int i, int i2, av avVar) {
        List list;
        int size;
        int size2;
        if (i > 0) {
            list = avVar.f18721b.f18653b;
            if (list.size() >= i) {
                size = list.size() - i;
                for (size2 = list.size() - 1; size2 >= size; size2--) {
                    list.remove(size2);
                }
            } else {
                size2 = i - list.size();
                list.clear();
            }
        }
        if (i2 > 0) {
            list = avVar.f18721b.f18652a;
            if (list.size() >= i2) {
                size = list.size() - i2;
                for (size2 = list.size() - 1; size2 >= size; size2--) {
                    list.remove(size2);
                }
                return;
            }
            size2 = i2 - list.size();
            list.clear();
        }
    }

    /* renamed from: g */
    private boolean m21921g() {
        switch (this.f19044j.m21834c(-1)) {
            case -1:
                return C4731a.f16614j;
            case 1:
                return true;
            default:
                return false;
        }
    }

    /* renamed from: b */
    private void m21911b(int i) {
        m21905a(i);
    }

    /* renamed from: a */
    public void mo7171a(C5932a c5932a) {
        this.f19041g.mo7171a(c5932a);
        this.f19040f.mo7171a(c5932a);
        this.f19042h.mo7171a(c5932a);
        this.f19043i.m21897a(c5932a);
    }
}
