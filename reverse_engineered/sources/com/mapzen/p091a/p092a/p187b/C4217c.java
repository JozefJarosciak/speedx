package com.mapzen.p091a.p092a.p187b;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import ch.qos.logback.classic.turbo.ReconfigureOnChangeFilter;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import com.mapzen.p091a.p092a.p187b.C4216e.C4214a;

/* compiled from: FusionEngine */
/* renamed from: com.mapzen.a.a.b.c */
public class C4217c extends C4216e implements LocationListener {
    /* renamed from: a */
    static C4213a f14859a = new C4223h();
    /* renamed from: b */
    private static final String f14860b = C4217c.class.getSimpleName();
    /* renamed from: c */
    private final LocationManager f14861c;
    /* renamed from: d */
    private Location f14862d;
    /* renamed from: e */
    private Location f14863e;

    public C4217c(Context context, C4214a c4214a) {
        super(context, c4214a);
        this.f14861c = (LocationManager) context.getSystemService(MapboxEvent.TYPE_LOCATION);
    }

    /* renamed from: a */
    public Location mo5988a() {
        long a = f14859a.mo5994a() - ReconfigureOnChangeFilter.DEFAULT_REFRESH_PERIOD;
        Location location = null;
        long j = Long.MIN_VALUE;
        float f = Float.MAX_VALUE;
        for (String lastKnownLocation : this.f14861c.getAllProviders()) {
            long time;
            float f2;
            Location location2;
            Location lastKnownLocation2 = this.f14861c.getLastKnownLocation(lastKnownLocation);
            if (lastKnownLocation2 != null) {
                float accuracy = lastKnownLocation2.getAccuracy();
                time = lastKnownLocation2.getTime();
                if (time > a && accuracy < f) {
                    f2 = accuracy;
                    location2 = lastKnownLocation2;
                    f = f2;
                    location = location2;
                    j = time;
                } else if (time < a && f == Float.MAX_VALUE && time > j) {
                    f2 = f;
                    location2 = lastKnownLocation2;
                    f = f2;
                    location = location2;
                    j = time;
                }
            }
            time = j;
            f2 = f;
            location2 = location;
            f = f2;
            location = location2;
            j = time;
        }
        return location;
    }

    /* renamed from: b */
    protected void mo5989b() {
        switch (m16739f().m16716d()) {
            case 100:
                m16741g();
                m16742h();
                return;
            case 102:
                m16742h();
                return;
            case 104:
                m16742h();
                return;
            case 105:
                m16743i();
                return;
            default:
                return;
        }
    }

    /* renamed from: c */
    protected void mo5990c() {
        if (this.f14861c != null) {
            this.f14861c.removeUpdates(this);
        }
    }

    /* renamed from: g */
    private void m16741g() {
        try {
            this.f14861c.requestLocationUpdates("gps", m16739f().m16714b(), m16739f().m16715c(), this);
        } catch (Throwable e) {
            Log.e(f14860b, "Unable to register for GPS updates.", e);
        }
    }

    /* renamed from: h */
    private void m16742h() {
        try {
            this.f14861c.requestLocationUpdates("network", m16739f().m16714b(), m16739f().m16715c(), this);
        } catch (Throwable e) {
            Log.e(f14860b, "Unable to register for network updates.", e);
        }
    }

    /* renamed from: i */
    private void m16743i() {
        try {
            this.f14861c.requestLocationUpdates("passive", m16739f().m16714b(), m16739f().m16715c(), this);
        } catch (Throwable e) {
            Log.e(f14860b, "Unable to register for passive updates.", e);
        }
    }

    public void onLocationChanged(Location location) {
        if ("gps".equals(location.getProvider())) {
            this.f14862d = location;
            if (m16738e() != null && C4217c.m16740a(this.f14862d, this.f14863e)) {
                m16738e().mo5987a(this, location);
            }
        } else if ("network".equals(location.getProvider())) {
            this.f14863e = location;
            if (m16738e() != null && C4217c.m16740a(this.f14863e, this.f14862d)) {
                m16738e().mo5987a(this, location);
            }
        }
    }

    public void onStatusChanged(String str, int i, Bundle bundle) {
    }

    public void onProviderEnabled(String str) {
    }

    public void onProviderDisabled(String str) {
    }

    /* renamed from: a */
    public static boolean m16740a(Location location, Location location2) {
        if (location == null) {
            return false;
        }
        if (location2 == null || C4223h.m16762a(location) > C4223h.m16762a(location2) + 60000000000L) {
            return true;
        }
        if (!location.hasAccuracy()) {
            return false;
        }
        if (!location2.hasAccuracy() || location.getAccuracy() < location2.getAccuracy()) {
            return true;
        }
        return false;
    }
}
