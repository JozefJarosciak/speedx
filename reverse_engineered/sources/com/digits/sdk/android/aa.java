package com.digits.sdk.android;

import android.annotation.TargetApi;
import com.twitter.sdk.android.core.C4586l;
import com.twitter.sdk.android.core.C4587i;
import com.twitter.sdk.android.core.C4655n;
import com.twitter.sdk.android.core.internal.C4603b;
import com.twitter.sdk.android.core.internal.C4608c;
import com.twitter.sdk.android.core.internal.scribe.C4629a;
import io.fabric.sdk.android.C1468h;
import io.fabric.sdk.android.C1520c;
import io.fabric.sdk.android.services.concurrency.C1522b;
import io.fabric.sdk.android.services.p094c.C1521c;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

@C1522b(a = {C4655n.class})
/* compiled from: Digits */
public class aa extends C1468h<Void> {
    /* renamed from: a */
    private volatile ag f6866a;
    /* renamed from: b */
    private volatile ContactsClient f6867b;
    /* renamed from: c */
    private C4586l<ap> f6868c;
    /* renamed from: d */
    private C4608c<ap> f6869d;
    /* renamed from: e */
    private C2877a f6870e;
    /* renamed from: f */
    private am f6871f = new an(null);
    /* renamed from: l */
    private ar f6872l;
    /* renamed from: m */
    private int f6873m;

    /* renamed from: n */
    protected /* synthetic */ Object mo2813n() {
        return m8090e();
    }

    /* renamed from: a */
    public static aa m8083a() {
        return (aa) C1520c.m8348a(aa.class);
    }

    /* renamed from: b */
    public static C4586l<ap> m8084b() {
        return m8083a().f6868c;
    }

    /* renamed from: c */
    public String mo2810c() {
        return "1.10.3.111";
    }

    /* renamed from: d */
    protected boolean mo2811d() {
        new C4603b().a(m8079q(), mo2812g(), mo2812g() + ":" + "session_store" + ".xml");
        this.f6868c = new C4587i(new C1521c(m8079q(), "session_store"), new ap$a(), "active_session", "session");
        this.f6872l = new ar();
        return super.mo2811d();
    }

    /* renamed from: e */
    protected Void m8090e() {
        this.f6868c.b();
        this.f6871f = m8087w();
        m8085u();
        m8086v();
        this.f6869d = new C4608c(m8084b(), m8096k(), this.f6872l);
        this.f6869d.a(m8080r().m8366d());
        return null;
    }

    @TargetApi(21)
    /* renamed from: f */
    int m8091f() {
        if (this.f6873m != 0) {
            return this.f6873m;
        }
        return C2876R.style.Digits_default;
    }

    /* renamed from: g */
    public String mo2812g() {
        return "com.digits.sdk.android:digits";
    }

    /* renamed from: h */
    ag m8093h() {
        if (this.f6866a == null) {
            m8085u();
        }
        return this.f6866a;
    }

    /* renamed from: i */
    protected am m8094i() {
        return this.f6871f;
    }

    /* renamed from: u */
    private synchronized void m8085u() {
        if (this.f6866a == null) {
            this.f6866a = new ag();
        }
    }

    /* renamed from: j */
    public ContactsClient m8095j() {
        if (this.f6867b == null) {
            m8086v();
        }
        return this.f6867b;
    }

    /* renamed from: v */
    private synchronized void m8086v() {
        if (this.f6867b == null) {
            this.f6867b = new ContactsClient();
        }
    }

    /* renamed from: k */
    protected ExecutorService m8096k() {
        return m8080r().m8367e();
    }

    /* renamed from: w */
    private am m8087w() {
        List arrayList = new ArrayList();
        arrayList.add(this.f6868c);
        if (this.f6866a == null || this.f6866a.a() == null) {
            return new an(new C4629a(this, "Digits", arrayList, m8078p()));
        }
        return new an(new C4629a(this, this.f6866a.a().toString(), arrayList, m8078p()));
    }

    /* renamed from: l */
    protected C2877a m8097l() {
        if (this.f6870e == null) {
            m8098m();
        }
        return this.f6870e;
    }

    /* renamed from: m */
    protected void m8098m() {
        this.f6870e = new C2898b().a(m8079q(), this.f6873m);
    }
}
