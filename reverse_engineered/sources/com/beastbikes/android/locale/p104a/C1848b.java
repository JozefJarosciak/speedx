package com.beastbikes.android.locale.p104a;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import com.alipay.sdk.util.C0880h;
import com.beastbikes.android.C1371a;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: UtilsLocationManager */
/* renamed from: com.beastbikes.android.locale.a.b */
public class C1848b implements C1371a {
    /* renamed from: d */
    private static final Logger f8318d = LoggerFactory.getLogger(C1848b.class);
    /* renamed from: e */
    private static C1848b f8319e;
    /* renamed from: a */
    private LocationManager f8320a;
    /* renamed from: b */
    private C1847a f8321b;
    /* renamed from: c */
    private WeakReference<C1823a> f8322c;
    /* renamed from: f */
    private Context f8323f;
    /* renamed from: g */
    private Timer f8324g;

    /* compiled from: UtilsLocationManager */
    /* renamed from: com.beastbikes.android.locale.a.b$a */
    private class C1847a implements LocationListener {
        /* renamed from: a */
        final /* synthetic */ C1848b f8317a;

        private C1847a(C1848b c1848b) {
            this.f8317a = c1848b;
        }

        public void onLocationChanged(Location location) {
            if (location != null) {
                if (this.f8317a.f8324g != null) {
                    this.f8317a.f8324g.cancel();
                }
                if (this.f8317a.f8321b != null) {
                    this.f8317a.f8320a.removeUpdates(this.f8317a.f8321b);
                    this.f8317a.f8320a = null;
                    this.f8317a.f8321b = null;
                }
                C1823a c1823a = (C1823a) this.f8317a.f8322c.get();
                if (c1823a != null) {
                    c1823a.mo3253a(location);
                    if (this.f8317a.f8323f != null) {
                        Class cls = C1848b.m9630a().getClass();
                        if (cls != null) {
                            Editor edit = this.f8317a.f8323f.getSharedPreferences(cls.getName(), 0).edit();
                            edit.putString("beast.location.manager.lat", location.getLatitude() + "");
                            edit.putString("beast.location.manager.lon", location.getLongitude() + "");
                            edit.apply();
                            C1848b.f8318d.info("SharedPreferences location is " + location.getLatitude() + C0880h.f2220b + location.getLongitude());
                        }
                    }
                }
            }
        }

        public void onStatusChanged(String str, int i, Bundle bundle) {
            switch (i) {
            }
        }

        public void onProviderEnabled(String str) {
            C1848b.f8318d.info("Locationmanager provider " + str + " is enabled");
        }

        public void onProviderDisabled(String str) {
            C1848b.f8318d.info("Locationmanager provider " + str + " is disabled");
        }
    }

    private C1848b() {
    }

    /* renamed from: a */
    public static synchronized C1848b m9630a() {
        C1848b c1848b;
        synchronized (C1848b.class) {
            if (f8319e == null) {
                f8319e = new C1848b();
            }
            c1848b = f8319e;
        }
        return c1848b;
    }

    /* renamed from: a */
    public void m9636a(Context context, C1823a c1823a) {
        m9637a(context, c1823a, 0);
    }

    /* renamed from: a */
    public void m9637a(Context context, C1823a c1823a, final long j) {
        try {
            this.f8323f = (Context) new WeakReference(context).get();
            this.f8322c = new WeakReference(c1823a);
            this.f8321b = new C1847a();
            this.f8320a = (LocationManager) context.getSystemService(MapboxEvent.TYPE_LOCATION);
            if (this.f8320a.isProviderEnabled("network")) {
                this.f8320a.requestLocationUpdates("network", 200, 0.0f, this.f8321b);
            }
            if (this.f8320a.isProviderEnabled("gps")) {
                this.f8320a.requestLocationUpdates("gps", 200, 0.0f, this.f8321b);
            }
            if (j > 0) {
                this.f8324g = new Timer();
                this.f8324g.schedule(new TimerTask(this) {
                    /* renamed from: b */
                    final /* synthetic */ C1848b f8316b;

                    public void run() {
                        if (this.f8316b.f8321b != null) {
                            if (this.f8316b.f8322c.get() != null) {
                                ((C1823a) this.f8316b.f8322c.get()).e_();
                                this.f8316b.f8320a.removeUpdates(this.f8316b.f8321b);
                            }
                            C1848b.f8318d.warn("locationManager timeout:" + j);
                        }
                    }
                }, j);
            }
        } catch (Exception e) {
            f8318d.error("getLocation exception " + e.toString());
        }
    }
}
