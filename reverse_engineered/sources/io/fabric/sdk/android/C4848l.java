package io.fabric.sdk.android;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import io.fabric.sdk.android.services.common.C4875g;
import io.fabric.sdk.android.services.common.C4877i;
import io.fabric.sdk.android.services.common.DeliveryMechanism;
import io.fabric.sdk.android.services.network.C4924c;
import io.fabric.sdk.android.services.network.C4925b;
import io.fabric.sdk.android.services.settings.C4933d;
import io.fabric.sdk.android.services.settings.C4934e;
import io.fabric.sdk.android.services.settings.C4937h;
import io.fabric.sdk.android.services.settings.C4946n;
import io.fabric.sdk.android.services.settings.C4951q;
import io.fabric.sdk.android.services.settings.C4952s;
import io.fabric.sdk.android.services.settings.C4955x;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

/* compiled from: Onboarding */
/* renamed from: io.fabric.sdk.android.l */
class C4848l extends C1468h<Boolean> {
    /* renamed from: a */
    private final C4924c f17108a = new C4925b();
    /* renamed from: b */
    private PackageManager f17109b;
    /* renamed from: c */
    private String f17110c;
    /* renamed from: d */
    private PackageInfo f17111d;
    /* renamed from: e */
    private String f17112e;
    /* renamed from: f */
    private String f17113f;
    /* renamed from: l */
    private String f17114l;
    /* renamed from: m */
    private String f17115m;
    /* renamed from: n */
    private String f17116n;
    /* renamed from: o */
    private final Future<Map<String, C4847j>> f17117o;
    /* renamed from: p */
    private final Collection<C1468h> f17118p;

    /* renamed from: n */
    protected /* synthetic */ Object m19071n() {
        return m19065a();
    }

    public C4848l(Future<Map<String, C4847j>> future, Collection<C1468h> collection) {
        this.f17117o = future;
        this.f17118p = collection;
    }

    /* renamed from: c */
    public String m19068c() {
        return "1.3.10.97";
    }

    /* renamed from: d */
    protected boolean m19069d() {
        try {
            this.f17114l = p().m19190g();
            this.f17109b = q().getPackageManager();
            this.f17110c = q().getPackageName();
            this.f17111d = this.f17109b.getPackageInfo(this.f17110c, 0);
            this.f17112e = Integer.toString(this.f17111d.versionCode);
            this.f17113f = this.f17111d.versionName == null ? "0.0" : this.f17111d.versionName;
            this.f17115m = this.f17109b.getApplicationLabel(q().getApplicationInfo()).toString();
            this.f17116n = Integer.toString(q().getApplicationInfo().targetSdkVersion);
            return true;
        } catch (Throwable e) {
            C1520c.h().mo6222d("Fabric", "Failed init", e);
            return false;
        }
    }

    /* renamed from: a */
    protected Boolean m19065a() {
        boolean a;
        String e = C4877i.m19171e(q());
        C4952s e2 = m19064e();
        if (e2 != null) {
            try {
                Map map;
                if (this.f17117o != null) {
                    map = (Map) this.f17117o.get();
                } else {
                    map = new HashMap();
                }
                a = m19061a(e, e2.f17341a, m19066a(map, this.f17118p).values());
            } catch (Throwable e3) {
                C1520c.h().mo6222d("Fabric", "Error performing auto configuration.", e3);
            }
            return Boolean.valueOf(a);
        }
        a = false;
        return Boolean.valueOf(a);
    }

    /* renamed from: e */
    private C4952s m19064e() {
        try {
            C4951q.m19411a().m19413a(this, this.k, this.f17108a, this.f17112e, this.f17113f, m19067b()).m19415c();
            return C4951q.m19411a().m19414b();
        } catch (Throwable e) {
            C1520c.h().mo6222d("Fabric", "Error dealing with settings", e);
            return null;
        }
    }

    /* renamed from: a */
    Map<String, C4847j> m19066a(Map<String, C4847j> map, Collection<C1468h> collection) {
        for (C1468h c1468h : collection) {
            if (!map.containsKey(c1468h.g())) {
                map.put(c1468h.g(), new C4847j(c1468h.g(), c1468h.c(), "binary"));
            }
        }
        return map;
    }

    /* renamed from: g */
    public String m19070g() {
        return "io.fabric.sdk.android:fabric";
    }

    /* renamed from: a */
    private boolean m19061a(String str, C4934e c4934e, Collection<C4847j> collection) {
        if ("new".equals(c4934e.f17300b)) {
            if (m19062b(str, c4934e, collection)) {
                return C4951q.m19411a().m19416d();
            }
            C1520c.h().mo6222d("Fabric", "Failed to create app with Crashlytics service.", null);
            return false;
        } else if ("configured".equals(c4934e.f17300b)) {
            return C4951q.m19411a().m19416d();
        } else {
            if (!c4934e.f17303e) {
                return true;
            }
            C1520c.h().mo6215a("Fabric", "Server says an update is required - forcing a full App update.");
            m19063c(str, c4934e, collection);
            return true;
        }
    }

    /* renamed from: b */
    private boolean m19062b(String str, C4934e c4934e, Collection<C4847j> collection) {
        return new C4937h(this, m19067b(), c4934e.f17301c, this.f17108a).mo6261a(m19059a(C4946n.m19409a(q(), str), (Collection) collection));
    }

    /* renamed from: c */
    private boolean m19063c(String str, C4934e c4934e, Collection<C4847j> collection) {
        return m19060a(c4934e, C4946n.m19409a(q(), str), (Collection) collection);
    }

    /* renamed from: a */
    private boolean m19060a(C4934e c4934e, C4946n c4946n, Collection<C4847j> collection) {
        return new C4955x(this, m19067b(), c4934e.f17301c, this.f17108a).mo6261a(m19059a(c4946n, (Collection) collection));
    }

    /* renamed from: a */
    private C4933d m19059a(C4946n c4946n, Collection<C4847j> collection) {
        return new C4933d(new C4875g().m19139a(q()), p().m19185b(), this.f17113f, this.f17112e, C4877i.m19152a(C4877i.m19173g(r0)), this.f17115m, DeliveryMechanism.determineFrom(this.f17114l).getId(), this.f17116n, "0", c4946n, collection);
    }

    /* renamed from: b */
    String m19067b() {
        return C4877i.m19163b(q(), "com.crashlytics.ApiEndpoint");
    }
}
