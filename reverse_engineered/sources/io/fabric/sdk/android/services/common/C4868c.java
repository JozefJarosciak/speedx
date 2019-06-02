package io.fabric.sdk.android.services.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import io.fabric.sdk.android.C1520c;
import io.fabric.sdk.android.services.p094c.C1521c;
import io.fabric.sdk.android.services.p094c.C4862b;

/* compiled from: AdvertisingInfoProvider */
/* renamed from: io.fabric.sdk.android.services.common.c */
class C4868c {
    /* renamed from: a */
    private final Context f17152a;
    /* renamed from: b */
    private final C4862b f17153b;

    public C4868c(Context context) {
        this.f17152a = context.getApplicationContext();
        this.f17153b = new C1521c(context, "TwitterAdvertisingInfoPreferences");
    }

    /* renamed from: a */
    public C4865b m19124a() {
        C4865b b = m19125b();
        if (m19122c(b)) {
            C1520c.h().mo6215a("Fabric", "Using AdvertisingInfo from Preference Store");
            m19119a(b);
            return b;
        }
        b = m19123e();
        m19121b(b);
        return b;
    }

    /* renamed from: a */
    private void m19119a(final C4865b c4865b) {
        new Thread(new C4866h(this) {
            /* renamed from: b */
            final /* synthetic */ C4868c f17151b;

            /* renamed from: a */
            public void mo6248a() {
                C4865b a = this.f17151b.m19123e();
                if (!c4865b.equals(a)) {
                    C1520c.h().mo6215a("Fabric", "Asychronously getting Advertising Info and storing it to preferences");
                    this.f17151b.m19121b(a);
                }
            }
        }).start();
    }

    @SuppressLint({"CommitPrefEdits"})
    /* renamed from: b */
    private void m19121b(C4865b c4865b) {
        if (m19122c(c4865b)) {
            this.f17153b.m19108a(this.f17153b.m19109b().putString("advertising_id", c4865b.f17148a).putBoolean("limit_ad_tracking_enabled", c4865b.f17149b));
        } else {
            this.f17153b.m19108a(this.f17153b.m19109b().remove("advertising_id").remove("limit_ad_tracking_enabled"));
        }
    }

    /* renamed from: b */
    protected C4865b m19125b() {
        return new C4865b(this.f17153b.m19107a().getString("advertising_id", ""), this.f17153b.m19107a().getBoolean("limit_ad_tracking_enabled", false));
    }

    /* renamed from: c */
    public C4869f m19126c() {
        return new C4870d(this.f17152a);
    }

    /* renamed from: d */
    public C4869f m19127d() {
        return new C4874e(this.f17152a);
    }

    /* renamed from: c */
    private boolean m19122c(C4865b c4865b) {
        return (c4865b == null || TextUtils.isEmpty(c4865b.f17148a)) ? false : true;
    }

    /* renamed from: e */
    private C4865b m19123e() {
        C4865b a = m19126c().mo6249a();
        if (m19122c(a)) {
            C1520c.h().mo6215a("Fabric", "Using AdvertisingInfo from Reflection Provider");
        } else {
            a = m19127d().mo6249a();
            if (m19122c(a)) {
                C1520c.h().mo6215a("Fabric", "Using AdvertisingInfo from Service Provider");
            } else {
                C1520c.h().mo6215a("Fabric", "AdvertisingInfo not present");
            }
        }
        return a;
    }
}
