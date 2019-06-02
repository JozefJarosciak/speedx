package io.fabric.sdk.android.services.settings;

import android.annotation.SuppressLint;
import android.content.SharedPreferences.Editor;
import io.fabric.sdk.android.C1468h;
import io.fabric.sdk.android.C1520c;
import io.fabric.sdk.android.services.common.C4877i;
import io.fabric.sdk.android.services.common.C4878j;
import io.fabric.sdk.android.services.p094c.C1521c;
import io.fabric.sdk.android.services.p094c.C4862b;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: DefaultSettingsController */
/* renamed from: io.fabric.sdk.android.services.settings.j */
class C4940j implements C4939r {
    /* renamed from: a */
    private final C4954v f17308a;
    /* renamed from: b */
    private final C4941u f17309b;
    /* renamed from: c */
    private final C4878j f17310c;
    /* renamed from: d */
    private final C4936g f17311d;
    /* renamed from: e */
    private final C4943w f17312e;
    /* renamed from: f */
    private final C1468h f17313f;
    /* renamed from: g */
    private final C4862b f17314g = new C1521c(this.f17313f);

    public C4940j(C1468h c1468h, C4954v c4954v, C4878j c4878j, C4941u c4941u, C4936g c4936g, C4943w c4943w) {
        this.f17313f = c1468h;
        this.f17308a = c4954v;
        this.f17310c = c4878j;
        this.f17309b = c4941u;
        this.f17311d = c4936g;
        this.f17312e = c4943w;
    }

    /* renamed from: a */
    public C4952s mo6264a() {
        return mo6265a(SettingsCacheBehavior.USE_CACHE);
    }

    /* renamed from: a */
    public C4952s mo6265a(SettingsCacheBehavior settingsCacheBehavior) {
        Throwable th;
        C4952s c4952s;
        Throwable th2;
        C4952s c4952s2 = null;
        try {
            if (!(C1520c.i() || m19390d())) {
                c4952s2 = m19384b(settingsCacheBehavior);
            }
            if (c4952s2 == null) {
                try {
                    JSONObject a = this.f17312e.mo6267a(this.f17308a);
                    if (a != null) {
                        c4952s2 = this.f17309b.mo6266a(this.f17310c, a);
                        this.f17311d.mo6263a(c4952s2.f17347g, a);
                        m19383a(a, "Loaded settings: ");
                        m19387a(m19388b());
                    }
                } catch (Throwable e) {
                    th = e;
                    c4952s = c4952s2;
                    th2 = th;
                    C1520c.h().mo6222d("Fabric", "Unknown error while loading Crashlytics settings. Crashes will be cached until settings can be retrieved.", th2);
                    return c4952s;
                }
            }
            c4952s = c4952s2;
            if (c4952s == null) {
                try {
                    c4952s = m19384b(SettingsCacheBehavior.IGNORE_CACHE_EXPIRATION);
                } catch (Exception e2) {
                    th2 = e2;
                    C1520c.h().mo6222d("Fabric", "Unknown error while loading Crashlytics settings. Crashes will be cached until settings can be retrieved.", th2);
                    return c4952s;
                }
            }
        } catch (Throwable e3) {
            th = e3;
            c4952s = null;
            th2 = th;
            C1520c.h().mo6222d("Fabric", "Unknown error while loading Crashlytics settings. Crashes will be cached until settings can be retrieved.", th2);
            return c4952s;
        }
        return c4952s;
    }

    /* renamed from: b */
    private C4952s m19384b(SettingsCacheBehavior settingsCacheBehavior) {
        Throwable th;
        C4952s c4952s = null;
        try {
            if (SettingsCacheBehavior.SKIP_CACHE_LOOKUP.equals(settingsCacheBehavior)) {
                return null;
            }
            JSONObject a = this.f17311d.mo6262a();
            if (a != null) {
                C4952s a2 = this.f17309b.mo6266a(this.f17310c, a);
                if (a2 != null) {
                    m19383a(a, "Loaded cached settings: ");
                    long a3 = this.f17310c.mo6251a();
                    if (SettingsCacheBehavior.IGNORE_CACHE_EXPIRATION.equals(settingsCacheBehavior) || !a2.m19417a(a3)) {
                        try {
                            C1520c.h().mo6215a("Fabric", "Returning cached settings.");
                            return a2;
                        } catch (Throwable e) {
                            Throwable th2 = e;
                            c4952s = a2;
                            th = th2;
                            C1520c.h().mo6222d("Fabric", "Failed to get cached settings", th);
                            return c4952s;
                        }
                    }
                    C1520c.h().mo6215a("Fabric", "Cached settings have expired.");
                    return null;
                }
                C1520c.h().mo6222d("Fabric", "Failed to transform cached settings data.", null);
                return null;
            }
            C1520c.h().mo6215a("Fabric", "No cached settings data found.");
            return null;
        } catch (Exception e2) {
            th = e2;
            C1520c.h().mo6222d("Fabric", "Failed to get cached settings", th);
            return c4952s;
        }
    }

    /* renamed from: a */
    private void m19383a(JSONObject jSONObject, String str) throws JSONException {
        C1520c.h().mo6215a("Fabric", str + jSONObject.toString());
    }

    /* renamed from: b */
    String m19388b() {
        return C4877i.m19152a(C4877i.m19173g(this.f17313f.q()));
    }

    /* renamed from: c */
    String m19389c() {
        return this.f17314g.m19107a().getString("existing_instance_identifier", "");
    }

    @SuppressLint({"CommitPrefEdits"})
    /* renamed from: a */
    boolean m19387a(String str) {
        Editor b = this.f17314g.m19109b();
        b.putString("existing_instance_identifier", str);
        return this.f17314g.m19108a(b);
    }

    /* renamed from: d */
    boolean m19390d() {
        return !m19389c().equals(m19388b());
    }
}
