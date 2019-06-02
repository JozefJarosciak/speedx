package p203u.aly;

import android.support.v4.os.EnvironmentCompat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* compiled from: AbstractIdTracker */
/* renamed from: u.aly.bu */
public abstract class bu {
    /* renamed from: a */
    private final int f18735a = 10;
    /* renamed from: b */
    private final int f18736b = 20;
    /* renamed from: c */
    private final String f18737c;
    /* renamed from: d */
    private List<ba> f18738d;
    /* renamed from: e */
    private bb f18739e;

    /* renamed from: a */
    public abstract String mo7207a();

    public bu(String str) {
        this.f18737c = str;
    }

    /* renamed from: b */
    public boolean m21308b() {
        return m21303g();
    }

    /* renamed from: c */
    public String m21309c() {
        return this.f18737c;
    }

    /* renamed from: d */
    public boolean m21310d() {
        if (this.f18739e == null || this.f18739e.m21412i() <= 20) {
            return true;
        }
        return false;
    }

    /* renamed from: g */
    private boolean m21303g() {
        bb bbVar = this.f18739e;
        String c = bbVar == null ? null : bbVar.m21404c();
        int i = bbVar == null ? 0 : bbVar.m21412i();
        String a = m21305a(mo7207a());
        if (a == null || a.equals(c)) {
            return false;
        }
        if (bbVar == null) {
            bbVar = new bb();
        }
        bbVar.m21397a(a);
        bbVar.m21396a(System.currentTimeMillis());
        bbVar.m21395a(i + 1);
        ba baVar = new ba();
        baVar.m21346a(this.f18737c);
        baVar.m21355c(a);
        baVar.m21350b(c);
        baVar.m21345a(bbVar.m21409f());
        if (this.f18738d == null) {
            this.f18738d = new ArrayList(2);
        }
        this.f18738d.add(baVar);
        if (this.f18738d.size() > 10) {
            this.f18738d.remove(0);
        }
        this.f18739e = bbVar;
        return true;
    }

    /* renamed from: e */
    public bb m21311e() {
        return this.f18739e;
    }

    /* renamed from: f */
    public List<ba> m21312f() {
        return this.f18738d;
    }

    /* renamed from: a */
    public void m21306a(List<ba> list) {
        this.f18738d = list;
    }

    /* renamed from: a */
    public String m21305a(String str) {
        if (str == null) {
            return null;
        }
        String trim = str.trim();
        if (trim.length() == 0 || "0".equals(trim) || EnvironmentCompat.MEDIA_UNKNOWN.equals(trim.toLowerCase(Locale.US))) {
            return null;
        }
        return trim;
    }

    /* renamed from: a */
    public void m21307a(bc bcVar) {
        this.f18739e = (bb) bcVar.m21455d().get(this.f18737c);
        List<ba> i = bcVar.m21460i();
        if (i != null && i.size() > 0) {
            if (this.f18738d == null) {
                this.f18738d = new ArrayList();
            }
            for (ba baVar : i) {
                if (this.f18737c.equals(baVar.f18757a)) {
                    this.f18738d.add(baVar);
                }
            }
        }
    }
}
