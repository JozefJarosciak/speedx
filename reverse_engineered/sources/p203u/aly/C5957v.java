package p203u.aly;

import android.content.Context;
import android.util.Base64;
import com.umeng.analytics.C4731a;
import com.umeng.analytics.C4744d;
import com.umeng.analytics.C4762j;
import com.umeng.analytics.C4762j.C4761b;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import p203u.aly.ch.C5933a;

/* compiled from: Sender */
/* renamed from: u.aly.v */
public class C5957v {
    /* renamed from: d */
    private static Context f19096d;
    /* renamed from: a */
    private bz f19097a;
    /* renamed from: b */
    private cf f19098b;
    /* renamed from: c */
    private final int f19099c = 1;
    /* renamed from: e */
    private C5959x f19100e;
    /* renamed from: f */
    private C5954r f19101f;
    /* renamed from: g */
    private av f19102g;
    /* renamed from: h */
    private boolean f19103h = false;
    /* renamed from: i */
    private boolean f19104i;

    /* compiled from: Sender */
    /* renamed from: u.aly.v$1 */
    class C59561 implements C4761b {
        /* renamed from: a */
        final /* synthetic */ C5957v f19095a;

        C59561(C5957v c5957v) {
            this.f19095a = c5957v;
        }

        /* renamed from: a */
        public void mo7227a(File file) {
        }

        /* renamed from: b */
        public boolean mo7228b(File file) {
            Throwable th;
            InputStream fileInputStream;
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    byte[] b = ag.m21151b(fileInputStream);
                    try {
                        int i;
                        ag.m21152c(fileInputStream);
                        byte[] a = this.f19095a.f19101f.m22013a(b);
                        if (a == null) {
                            i = 1;
                        } else {
                            i = this.f19095a.m22019a(a);
                        }
                        if (i == 2 && this.f19095a.f19100e.m22054l()) {
                            this.f19095a.f19100e.m22053k();
                        }
                        if (!this.f19095a.f19104i && i == 1) {
                            return false;
                        }
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    ag.m21152c(fileInputStream);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                fileInputStream = null;
                ag.m21152c(fileInputStream);
                throw th;
            }
        }

        /* renamed from: c */
        public void mo7229c(File file) {
            this.f19095a.f19100e.m22052j();
        }
    }

    public C5957v(Context context, C5959x c5959x) {
        this.f19097a = bz.m21801a(context);
        this.f19098b = cf.m21838a(context);
        f19096d = context;
        this.f19100e = c5959x;
        this.f19101f = new C5954r(context);
        this.f19101f.m22012a(this.f19100e);
    }

    /* renamed from: a */
    public void m22026a(av avVar) {
        this.f19102g = avVar;
    }

    /* renamed from: a */
    public void m22028a(boolean z) {
        this.f19103h = z;
    }

    /* renamed from: b */
    public void m22029b(boolean z) {
        this.f19104i = z;
    }

    /* renamed from: a */
    public void m22027a(C5846t c5846t) {
        this.f19098b.m21844a(c5846t);
    }

    /* renamed from: a */
    public void m22025a() {
        if (this.f19102g != null) {
            m22023c();
        } else {
            m22022b();
        }
    }

    /* renamed from: b */
    private void m22022b() {
        C4762j.m18682a(f19096d).m18704h().m18676a(new C59561(this));
    }

    /* renamed from: c */
    private void m22023c() {
        byte[] a;
        this.f19097a.m21805a();
        av avVar = this.f19102g;
        try {
            a = new an().m21192a(this.f19097a.m21807b());
            avVar.f18720a.f18677O = Base64.encodeToString(a, 0);
        } catch (Throwable e) {
            ah.m21157a(e);
        }
        a = C4762j.m18682a(f19096d).m18698b(avVar);
        if (!C4744d.m18641a(f19096d, a)) {
            if (a == null) {
                ah.m21165d("message is null");
                return;
            }
            bx b;
            int i;
            if (this.f19103h) {
                b = bx.m21786b(f19096d, C4731a.m18614a(f19096d), a);
            } else {
                b = bx.m21784a(f19096d, C4731a.m18614a(f19096d), a);
            }
            byte[] c = b.m21795c();
            C4762j.m18682a(f19096d).m18702f();
            a = this.f19101f.m22013a(c);
            if (a == null) {
                i = 1;
            } else {
                i = m22019a(a);
            }
            switch (i) {
                case 1:
                    if (!this.f19104i) {
                        C4762j.m18682a(f19096d).m18695a(c);
                        return;
                    }
                    return;
                case 2:
                    if (this.f19100e.m22054l()) {
                        this.f19100e.m22053k();
                    }
                    this.f19097a.m21808c();
                    this.f19100e.m22052j();
                    av.f18718c = 0;
                    return;
                case 3:
                    this.f19100e.m22052j();
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: a */
    private int m22019a(byte[] bArr) {
        bf bfVar = new bf();
        try {
            new al(new C5933a()).m21191a(bfVar, bArr);
            if (bfVar.f18850a == 1) {
                this.f19098b.m21847b(bfVar.m21603i());
                this.f19098b.m21849d();
            }
            ah.m21158b("send log:" + bfVar.m21600f());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (bfVar.f18850a == 1) {
            return 2;
        }
        return 3;
    }
}
