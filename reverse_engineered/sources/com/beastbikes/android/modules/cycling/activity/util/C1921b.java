package com.beastbikes.android.modules.cycling.activity.util;

import android.content.Context;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.GpsStatus.Listener;
import android.location.LocationManager;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: GpsStatusObserve */
/* renamed from: com.beastbikes.android.modules.cycling.activity.util.b */
public abstract class C1921b implements Listener {
    /* renamed from: c */
    private static final Logger f8620c = LoggerFactory.getLogger(C1921b.class);
    /* renamed from: a */
    private LocationManager f8621a;
    /* renamed from: b */
    private List<GpsSatellite> f8622b = new ArrayList();
    /* renamed from: d */
    private boolean f8623d;

    /* renamed from: a */
    public abstract void mo3285a();

    /* renamed from: b */
    public abstract void mo3286b();

    public C1921b(Context context) {
        this.f8621a = (LocationManager) context.getSystemService(MapboxEvent.TYPE_LOCATION);
    }

    public void onGpsStatusChanged(int i) {
        switch (i) {
            case 1:
                f8620c.info("定位启动");
                return;
            case 2:
                f8620c.info("定位结束");
                return;
            case 3:
                f8620c.info("第一次定位");
                return;
            case 4:
                m9928a(i, this.f8621a.getGpsStatus(null));
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    private void m9928a(int i, GpsStatus gpsStatus) {
        if (gpsStatus != null && i == 4) {
            this.f8622b.clear();
            int i2 = 0;
            for (GpsSatellite gpsSatellite : gpsStatus.getSatellites()) {
                int snr;
                if (((double) gpsSatellite.getSnr()) > 0.0d) {
                    this.f8622b.add(gpsSatellite);
                    snr = (int) (gpsSatellite.getSnr() + ((float) i2));
                } else {
                    snr = i2;
                }
                i2 = snr;
            }
            if (i2 > 150) {
                mo3285a();
                this.f8623d = true;
            } else if (this.f8623d) {
                mo3286b();
                this.f8623d = false;
            }
        }
    }

    /* renamed from: c */
    public void m9931c() {
        this.f8621a.addGpsStatusListener(this);
    }

    /* renamed from: d */
    public void m9932d() {
        this.f8621a.removeGpsStatusListener(this);
    }
}
