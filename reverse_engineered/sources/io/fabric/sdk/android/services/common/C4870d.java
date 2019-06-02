package io.fabric.sdk.android.services.common;

import android.content.Context;
import io.fabric.sdk.android.C1520c;

/* compiled from: AdvertisingInfoReflectionStrategy */
/* renamed from: io.fabric.sdk.android.services.common.d */
class C4870d implements C4869f {
    /* renamed from: a */
    private final Context f17154a;

    public C4870d(Context context) {
        this.f17154a = context.getApplicationContext();
    }

    /* renamed from: a */
    boolean m19133a(Context context) {
        try {
            if (((Integer) Class.forName("com.google.android.gms.common.GooglePlayServicesUtil").getMethod("isGooglePlayServicesAvailable", new Class[]{Context.class}).invoke(null, new Object[]{context})).intValue() == 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: a */
    public C4865b mo6249a() {
        if (m19133a(this.f17154a)) {
            return new C4865b(m19129b(), m19130c());
        }
        return null;
    }

    /* renamed from: b */
    private String m19129b() {
        try {
            return (String) Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient$Info").getMethod("getId", new Class[0]).invoke(m19131d(), new Object[0]);
        } catch (Exception e) {
            C1520c.h().mo6219c("Fabric", "Could not call getId on com.google.android.gms.ads.identifier.AdvertisingIdClient$Info");
            return null;
        }
    }

    /* renamed from: c */
    private boolean m19130c() {
        try {
            return ((Boolean) Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient$Info").getMethod("isLimitAdTrackingEnabled", new Class[0]).invoke(m19131d(), new Object[0])).booleanValue();
        } catch (Exception e) {
            C1520c.h().mo6219c("Fabric", "Could not call isLimitAdTrackingEnabled on com.google.android.gms.ads.identifier.AdvertisingIdClient$Info");
            return false;
        }
    }

    /* renamed from: d */
    private Object m19131d() {
        Object obj = null;
        try {
            obj = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getMethod("getAdvertisingIdInfo", new Class[]{Context.class}).invoke(null, new Object[]{this.f17154a});
        } catch (Exception e) {
            C1520c.h().mo6219c("Fabric", "Could not call getAdvertisingIdInfo on com.google.android.gms.ads.identifier.AdvertisingIdClient");
        }
        return obj;
    }
}
