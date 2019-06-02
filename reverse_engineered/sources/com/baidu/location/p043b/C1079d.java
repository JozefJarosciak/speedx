package com.baidu.location.p043b;

import android.content.Context;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.GpsStatus.Listener;
import android.location.GpsStatus.NmeaListener;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import ch.qos.logback.classic.turbo.ReconfigureOnChangeFilter;
import ch.qos.logback.core.spi.AbstractComponentTracker;
import com.baidu.location.BDLocation;
import com.baidu.location.C1102f;
import com.baidu.location.Jni;
import com.baidu.location.p041a.C1038a;
import com.baidu.location.p041a.C1046c;
import com.baidu.location.p041a.C1049f;
import com.baidu.location.p041a.C1067r;
import com.baidu.location.p041a.C1070t;
import com.baidu.location.p042d.C1091b;
import com.baidu.location.p042d.C1095d;
import com.baidu.location.p042d.C1100j;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import org.apache.http.HttpStatus;

/* renamed from: com.baidu.location.b.d */
public class C1079d {
    /* renamed from: c */
    private static C1079d f2598c = null;
    /* renamed from: k */
    private static int f2599k = 0;
    /* renamed from: s */
    private static String f2600s = null;
    /* renamed from: A */
    private HashMap<Integer, List<GpsSatellite>> f2601A;
    /* renamed from: a */
    private final long f2602a = 1000;
    /* renamed from: b */
    private final long f2603b = 9000;
    /* renamed from: d */
    private Context f2604d;
    /* renamed from: e */
    private LocationManager f2605e = null;
    /* renamed from: f */
    private Location f2606f;
    /* renamed from: g */
    private C1077b f2607g = null;
    /* renamed from: h */
    private C1078c f2608h = null;
    /* renamed from: i */
    private GpsStatus f2609i;
    /* renamed from: j */
    private C1076a f2610j = null;
    /* renamed from: l */
    private int f2611l = 0;
    /* renamed from: m */
    private long f2612m = 0;
    /* renamed from: n */
    private boolean f2613n = false;
    /* renamed from: o */
    private boolean f2614o = false;
    /* renamed from: p */
    private String f2615p = null;
    /* renamed from: q */
    private boolean f2616q = false;
    /* renamed from: r */
    private long f2617r = 0;
    /* renamed from: t */
    private Handler f2618t = null;
    /* renamed from: u */
    private final int f2619u = 1;
    /* renamed from: v */
    private final int f2620v = 2;
    /* renamed from: w */
    private final int f2621w = 3;
    /* renamed from: x */
    private final int f2622x = 4;
    /* renamed from: y */
    private int f2623y;
    /* renamed from: z */
    private int f2624z;

    /* renamed from: com.baidu.location.b.d$a */
    private class C1076a implements Listener, NmeaListener {
        /* renamed from: a */
        long f2585a;
        /* renamed from: b */
        final /* synthetic */ C1079d f2586b;
        /* renamed from: c */
        private long f2587c;
        /* renamed from: d */
        private final int f2588d;
        /* renamed from: e */
        private boolean f2589e;
        /* renamed from: f */
        private List<String> f2590f;
        /* renamed from: g */
        private String f2591g;
        /* renamed from: h */
        private String f2592h;
        /* renamed from: i */
        private String f2593i;
        /* renamed from: j */
        private long f2594j;

        private C1076a(C1079d c1079d) {
            this.f2586b = c1079d;
            this.f2585a = 0;
            this.f2587c = 0;
            this.f2588d = HttpStatus.SC_BAD_REQUEST;
            this.f2589e = false;
            this.f2590f = new ArrayList();
            this.f2591g = null;
            this.f2592h = null;
            this.f2593i = null;
            this.f2594j = 0;
        }

        /* renamed from: a */
        public void m3888a(String str) {
            if (System.currentTimeMillis() - this.f2587c > 400 && this.f2589e && this.f2590f.size() > 0) {
                try {
                    C1081f c1081f = new C1081f(this.f2590f, this.f2591g, this.f2592h, this.f2593i);
                    if (c1081f.m3940a()) {
                        C1100j.f2733d = this.f2586b.m3892a(c1081f, this.f2586b.f2624z);
                        if (C1100j.f2733d > 0) {
                            C1079d.f2600s = String.format(Locale.CHINA, "&nmea=%.1f|%.1f&g_tp=%d", new Object[]{Double.valueOf(c1081f.m3942c()), Double.valueOf(c1081f.m3941b()), Integer.valueOf(C1100j.f2733d)});
                        }
                    } else {
                        C1100j.f2733d = 0;
                    }
                } catch (Exception e) {
                    C1100j.f2733d = 0;
                }
                this.f2590f.clear();
                this.f2593i = null;
                this.f2592h = null;
                this.f2591g = null;
                this.f2589e = false;
            }
            if (str.startsWith("$GPGGA")) {
                this.f2589e = true;
                this.f2591g = str.trim();
            } else if (str.startsWith("$GPGSV")) {
                this.f2590f.add(str.trim());
            } else if (str.startsWith("$GPGSA")) {
                this.f2593i = str.trim();
            }
            this.f2587c = System.currentTimeMillis();
        }

        public void onGpsStatusChanged(int i) {
            int i2 = 0;
            if (this.f2586b.f2605e != null) {
                switch (i) {
                    case 2:
                        this.f2586b.m3923d(null);
                        this.f2586b.m3915b(false);
                        C1079d.f2599k = 0;
                        return;
                    case 4:
                        if (this.f2586b.f2614o) {
                            try {
                                if (this.f2586b.f2609i == null) {
                                    this.f2586b.f2609i = this.f2586b.f2605e.getGpsStatus(null);
                                } else {
                                    this.f2586b.f2605e.getGpsStatus(this.f2586b.f2609i);
                                }
                                this.f2586b.f2623y = 0;
                                this.f2586b.f2624z = 0;
                                this.f2586b.f2601A = new HashMap();
                                int i3 = 0;
                                for (GpsSatellite gpsSatellite : this.f2586b.f2609i.getSatellites()) {
                                    if (gpsSatellite.usedInFix()) {
                                        i3++;
                                        if (gpsSatellite.getPrn() <= 32) {
                                            i2++;
                                        }
                                        if (gpsSatellite.getSnr() >= ((float) C1100j.f2709F)) {
                                            this.f2586b.f2624z = this.f2586b.f2624z + 1;
                                        }
                                        this.f2586b.m3898a(gpsSatellite, this.f2586b.f2601A);
                                    }
                                }
                                if (i2 > 0) {
                                    this.f2586b.f2611l = i2;
                                }
                                if (i3 > 0) {
                                    this.f2594j = System.currentTimeMillis();
                                    C1079d.f2599k = i3;
                                    return;
                                } else if (System.currentTimeMillis() - this.f2594j > 100) {
                                    this.f2594j = System.currentTimeMillis();
                                    C1079d.f2599k = i3;
                                    return;
                                } else {
                                    return;
                                }
                            } catch (Exception e) {
                                return;
                            }
                        }
                        return;
                    default:
                        return;
                }
            }
        }

        public void onNmeaReceived(long j, String str) {
            if (this.f2586b.f2614o && str != null && !str.equals("") && str.length() >= 9 && str.length() <= 150 && this.f2586b.m3937i()) {
                this.f2586b.f2618t.sendMessage(this.f2586b.f2618t.obtainMessage(2, str));
            }
        }
    }

    /* renamed from: com.baidu.location.b.d$b */
    private class C1077b implements LocationListener {
        /* renamed from: a */
        final /* synthetic */ C1079d f2595a;

        private C1077b(C1079d c1079d) {
            this.f2595a = c1079d;
        }

        public void onLocationChanged(Location location) {
            this.f2595a.f2617r = System.currentTimeMillis();
            this.f2595a.m3915b(true);
            this.f2595a.m3923d(location);
            this.f2595a.f2613n = false;
        }

        public void onProviderDisabled(String str) {
            this.f2595a.m3923d(null);
            this.f2595a.m3915b(false);
        }

        public void onProviderEnabled(String str) {
        }

        public void onStatusChanged(String str, int i, Bundle bundle) {
            switch (i) {
                case 0:
                    this.f2595a.m3923d(null);
                    this.f2595a.m3915b(false);
                    return;
                case 1:
                    this.f2595a.f2612m = System.currentTimeMillis();
                    this.f2595a.f2613n = true;
                    this.f2595a.m3915b(false);
                    return;
                case 2:
                    this.f2595a.f2613n = false;
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: com.baidu.location.b.d$c */
    private class C1078c implements LocationListener {
        /* renamed from: a */
        final /* synthetic */ C1079d f2596a;
        /* renamed from: b */
        private long f2597b;

        private C1078c(C1079d c1079d) {
            this.f2596a = c1079d;
            this.f2597b = 0;
        }

        public void onLocationChanged(Location location) {
            if (!this.f2596a.f2614o && location != null && location.getProvider() == "gps" && System.currentTimeMillis() - this.f2597b >= AbstractComponentTracker.LINGERING_TIMEOUT && C1070t.m3842a(location, false)) {
                this.f2597b = System.currentTimeMillis();
                this.f2596a.f2618t.sendMessage(this.f2596a.f2618t.obtainMessage(4, location));
            }
        }

        public void onProviderDisabled(String str) {
        }

        public void onProviderEnabled(String str) {
        }

        public void onStatusChanged(String str, int i, Bundle bundle) {
        }
    }

    private C1079d() {
    }

    /* renamed from: a */
    private int m3892a(C1081f c1081f, int i) {
        if (f2599k >= C1100j.f2706C) {
            return 1;
        }
        if (f2599k <= C1100j.f2705B) {
            return 4;
        }
        double c = c1081f.m3942c();
        if (c <= ((double) C1100j.f2753x)) {
            return 1;
        }
        if (c >= ((double) C1100j.f2754y)) {
            return 4;
        }
        c = c1081f.m3941b();
        return c > ((double) C1100j.f2755z) ? c >= ((double) C1100j.f2704A) ? 4 : i < C1100j.f2708E ? i <= C1100j.f2707D ? 4 : this.f2601A != null ? m3893a(this.f2601A) : 3 : 1 : 1;
    }

    /* renamed from: a */
    private int m3893a(HashMap<Integer, List<GpsSatellite>> hashMap) {
        if (this.f2623y > 4) {
            List arrayList = new ArrayList();
            List arrayList2 = new ArrayList();
            int i = 0;
            for (Entry value : hashMap.entrySet()) {
                int i2;
                List list = (List) value.getValue();
                if (list != null) {
                    Object a = m3910a(list);
                    if (a != null) {
                        arrayList.add(a);
                        i2 = i + 1;
                        arrayList2.add(Integer.valueOf(i));
                        i = i2;
                    }
                }
                i2 = i;
                i = i2;
            }
            if (!arrayList.isEmpty()) {
                double[] dArr;
                double[] dArr2 = new double[2];
                int size = arrayList.size();
                for (int i3 = 0; i3 < size; i3++) {
                    dArr = (double[]) arrayList.get(i3);
                    i = ((Integer) arrayList2.get(i3)).intValue();
                    dArr[0] = dArr[0] * ((double) i);
                    dArr[1] = dArr[1] * ((double) i);
                    dArr2[0] = dArr2[0] + dArr[0];
                    dArr2[1] = dArr[1] + dArr2[1];
                }
                dArr2[0] = dArr2[0] / ((double) size);
                dArr2[1] = dArr2[1] / ((double) size);
                dArr = m3918b(dArr2[0], dArr2[1]);
                if (dArr[0] <= ((double) C1100j.f2710G)) {
                    return 1;
                }
                if (dArr[0] >= ((double) C1100j.f2711H)) {
                    return 4;
                }
            }
        }
        return 3;
    }

    /* renamed from: a */
    public static synchronized C1079d m3897a() {
        C1079d c1079d;
        synchronized (C1079d.class) {
            if (f2598c == null) {
                f2598c = new C1079d();
            }
            c1079d = f2598c;
        }
        return c1079d;
    }

    /* renamed from: a */
    private String m3898a(GpsSatellite gpsSatellite, HashMap<Integer, List<GpsSatellite>> hashMap) {
        int floor = (int) Math.floor((double) (gpsSatellite.getAzimuth() / 6.0f));
        float elevation = gpsSatellite.getElevation();
        int floor2 = (int) Math.floor(((double) elevation) / 1.5d);
        float snr = gpsSatellite.getSnr();
        int round = Math.round(snr / 5.0f);
        int prn = gpsSatellite.getPrn();
        int i = prn >= 65 ? prn - 32 : prn;
        if (snr >= 10.0f && elevation >= 1.0f) {
            List list = (List) hashMap.get(Integer.valueOf(round));
            if (list == null) {
                list = new ArrayList();
            }
            list.add(gpsSatellite);
            hashMap.put(Integer.valueOf(round), list);
            this.f2623y++;
        }
        if (floor >= 64) {
            if (floor2 < 64) {
                if (i >= 65) {
                }
            }
            return null;
        }
        if (floor2 < 64) {
            if (i >= 65) {
            }
        }
        return null;
        if (i >= 65) {
        }
        return null;
    }

    /* renamed from: a */
    public static String m3899a(Location location) {
        float f = -1.0f;
        if (location == null) {
            return null;
        }
        float speed = (float) (((double) location.getSpeed()) * 3.6d);
        if (!location.hasSpeed()) {
            speed = -1.0f;
        }
        int accuracy = (int) (location.hasAccuracy() ? location.getAccuracy() : -1.0f);
        double altitude = location.hasAltitude() ? location.getAltitude() : 555.0d;
        if (location.hasBearing()) {
            f = location.getBearing();
        }
        return String.format(Locale.CHINA, "&ll=%.5f|%.5f&s=%.1f&d=%.1f&ll_r=%d&ll_n=%d&ll_h=%.2f&ll_t=%d", new Object[]{Double.valueOf(location.getLongitude()), Double.valueOf(location.getLatitude()), Float.valueOf(speed), Float.valueOf(f), Integer.valueOf(accuracy), Integer.valueOf(f2599k), Double.valueOf(altitude), Long.valueOf(location.getTime() / 1000)});
    }

    /* renamed from: a */
    private void m3903a(double d, double d2, float f) {
        int i = 0;
        if (d >= 73.146973d && d <= 135.252686d && d2 <= 54.258807d && d2 >= 14.604847d && f <= 18.0f) {
            int i2 = (int) ((d - C1100j.f2747r) * 1000.0d);
            int i3 = (int) ((C1100j.f2748s - d2) * 1000.0d);
            if (i2 <= 0 || i2 >= 50 || i3 <= 0 || i3 >= 50) {
                String.format(Locale.CHINA, "&ll=%.5f|%.5f", new Object[]{Double.valueOf(d), Double.valueOf(d2)}) + "&im=" + C1091b.m3989a().m3994b();
                C1100j.f2745p = d;
                C1100j.f2746q = d2;
            } else {
                i2 += i3 * 50;
                i3 = i2 >> 2;
                i2 &= 3;
                if (C1100j.f2751v) {
                    i = (C1100j.f2750u[i3] >> (i2 * 2)) & 3;
                }
            }
        }
        if (C1100j.f2749t != i) {
            C1100j.f2749t = i;
        }
    }

    /* renamed from: a */
    private void m3907a(String str, Location location) {
        if (location != null) {
            String str2 = str + C1038a.m3645a().m3654c();
            boolean d = C1085h.m3959a().m3967d();
            C1067r.m3824a(new C1072a(C1074b.m3866a().m3884f()));
            C1067r.m3822a(System.currentTimeMillis());
            C1067r.m3823a(new Location(location));
            C1067r.m3825a(str2);
            if (!d) {
                C1070t.m3837a(C1067r.m3827c(), null, C1067r.m3828d(), str2);
            }
        }
    }

    /* renamed from: a */
    public static boolean m3908a(Location location, Location location2, boolean z) {
        if (location == location2) {
            return false;
        }
        if (location == null || location2 == null) {
            return true;
        }
        float speed = location2.getSpeed();
        if (z && ((C1100j.f2749t == 3 || !C1095d.m4003a().m4005a(location2.getLongitude(), location2.getLatitude())) && speed < 5.0f)) {
            return true;
        }
        float distanceTo = location2.distanceTo(location);
        return speed > C1100j.f2713J ? distanceTo > C1100j.f2715L : speed > C1100j.f2712I ? distanceTo > C1100j.f2714K : distanceTo > 5.0f;
    }

    /* renamed from: a */
    private double[] m3909a(double d, double d2) {
        return new double[]{Math.sin(Math.toRadians(d2)) * d, Math.cos(Math.toRadians(d2)) * d};
    }

    /* renamed from: a */
    private double[] m3910a(List<GpsSatellite> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        double[] dArr = new double[2];
        for (GpsSatellite gpsSatellite : list) {
            if (gpsSatellite != null) {
                double[] a = m3909a((double) (90.0f - gpsSatellite.getElevation()), (double) gpsSatellite.getAzimuth());
                dArr[0] = dArr[0] + a[0];
                dArr[1] = dArr[1] + a[1];
            }
        }
        int size = list.size();
        dArr[0] = dArr[0] / ((double) size);
        dArr[1] = dArr[1] / ((double) size);
        return dArr;
    }

    /* renamed from: b */
    public static String m3913b(Location location) {
        String a = C1079d.m3899a(location);
        return a != null ? a + "&g_tp=0" : a;
    }

    /* renamed from: b */
    private void m3915b(boolean z) {
        this.f2616q = z;
        if (!z || !m3937i()) {
        }
    }

    /* renamed from: b */
    private double[] m3918b(double d, double d2) {
        double d3 = 0.0d;
        if (d2 != 0.0d) {
            d3 = Math.toDegrees(Math.atan(d / d2));
        } else if (d > 0.0d) {
            d3 = 90.0d;
        } else if (d < 0.0d) {
            d3 = 270.0d;
        }
        return new double[]{Math.sqrt((d * d) + (d2 * d2)), d3};
    }

    /* renamed from: c */
    public static String m3921c(Location location) {
        String a = C1079d.m3899a(location);
        return a != null ? a + f2600s : a;
    }

    /* renamed from: d */
    private void m3923d(Location location) {
        this.f2618t.sendMessage(this.f2618t.obtainMessage(1, location));
    }

    /* renamed from: e */
    private void m3925e(Location location) {
        if (location != null) {
            int i = f2599k;
            if (i == 0) {
                try {
                    i = location.getExtras().getInt("satellites");
                } catch (Exception e) {
                }
            }
            if (i != 0 || C1100j.f2741l) {
                Location location2;
                this.f2606f = location;
                i = f2599k;
                if (this.f2606f == null) {
                    this.f2615p = null;
                    location2 = null;
                } else {
                    Location location3 = new Location(this.f2606f);
                    this.f2606f.setTime(System.currentTimeMillis());
                    float speed = (float) (((double) this.f2606f.getSpeed()) * 3.6d);
                    if (!this.f2606f.hasSpeed()) {
                        speed = -1.0f;
                    }
                    if (i == 0) {
                        try {
                            i = this.f2606f.getExtras().getInt("satellites");
                        } catch (Exception e2) {
                        }
                    }
                    this.f2615p = String.format(Locale.CHINA, "&ll=%.5f|%.5f&s=%.1f&d=%.1f&ll_n=%d&ll_t=%d", new Object[]{Double.valueOf(this.f2606f.getLongitude()), Double.valueOf(this.f2606f.getLatitude()), Float.valueOf(speed), Float.valueOf(this.f2606f.getBearing()), Integer.valueOf(i), Long.valueOf(r2)});
                    m3903a(this.f2606f.getLongitude(), this.f2606f.getLatitude(), speed);
                    location2 = location3;
                }
                try {
                    C1049f.m3722a().m3730a(this.f2606f);
                } catch (Exception e3) {
                }
                if (location2 != null) {
                    C1046c.m3693a().m3720a(location2);
                }
                if (m3937i() && this.f2606f != null && f2599k > 2 && C1070t.m3842a(this.f2606f, true)) {
                    boolean d = C1085h.m3959a().m3967d();
                    C1067r.m3824a(new C1072a(C1074b.m3866a().m3884f()));
                    C1067r.m3822a(System.currentTimeMillis());
                    C1067r.m3823a(new Location(this.f2606f));
                    C1067r.m3825a(C1038a.m3645a().m3654c());
                    if (!d) {
                        C1070t.m3837a(C1067r.m3827c(), null, C1067r.m3828d(), C1038a.m3645a().m3654c());
                        return;
                    }
                    return;
                }
                return;
            }
            return;
        }
        this.f2606f = null;
    }

    /* renamed from: a */
    public void m3929a(boolean z) {
        if (z) {
            m3931c();
        } else {
            m3932d();
        }
    }

    /* renamed from: b */
    public synchronized void m3930b() {
        if (C1102f.isServing) {
            this.f2604d = C1102f.getServiceContext();
            try {
                this.f2605e = (LocationManager) this.f2604d.getSystemService(MapboxEvent.TYPE_LOCATION);
                this.f2610j = new C1076a();
                this.f2605e.addGpsStatusListener(this.f2610j);
                this.f2608h = new C1078c();
                this.f2605e.requestLocationUpdates("passive", 9000, 0.0f, this.f2608h);
            } catch (Exception e) {
            }
            this.f2618t = new C1080e(this);
        }
    }

    /* renamed from: c */
    public void m3931c() {
        if (!this.f2614o) {
            try {
                this.f2607g = new C1077b();
                try {
                    this.f2605e.sendExtraCommand("gps", "force_xtra_injection", new Bundle());
                } catch (Exception e) {
                }
                this.f2605e.requestLocationUpdates("gps", 1000, 0.0f, this.f2607g);
                this.f2605e.addNmeaListener(this.f2610j);
                this.f2614o = true;
            } catch (Exception e2) {
            }
        }
    }

    /* renamed from: d */
    public void m3932d() {
        if (this.f2614o) {
            if (this.f2605e != null) {
                try {
                    if (this.f2607g != null) {
                        this.f2605e.removeUpdates(this.f2607g);
                    }
                    if (this.f2610j != null) {
                        this.f2605e.removeNmeaListener(this.f2610j);
                    }
                } catch (Exception e) {
                }
            }
            C1100j.f2733d = 0;
            C1100j.f2749t = 0;
            this.f2607g = null;
            this.f2614o = false;
            m3915b(false);
        }
    }

    /* renamed from: e */
    public synchronized void m3933e() {
        m3932d();
        if (this.f2605e != null) {
            try {
                if (this.f2610j != null) {
                    this.f2605e.removeGpsStatusListener(this.f2610j);
                }
                this.f2605e.removeUpdates(this.f2608h);
            } catch (Exception e) {
            }
            this.f2610j = null;
            this.f2605e = null;
        }
    }

    /* renamed from: f */
    public String m3934f() {
        if (this.f2606f == null) {
            return null;
        }
        double[] dArr;
        int i;
        String str = "{\"result\":{\"time\":\"" + C1100j.m4010a() + "\",\"error\":\"61\"},\"content\":{\"point\":{\"x\":" + "\"%f\",\"y\":\"%f\"},\"radius\":\"%d\",\"d\":\"%f\"," + "\"s\":\"%f\",\"n\":\"%d\"";
        int accuracy = (int) (this.f2606f.hasAccuracy() ? this.f2606f.getAccuracy() : 10.0f);
        float speed = (float) (((double) this.f2606f.getSpeed()) * 3.6d);
        if (!this.f2606f.hasSpeed()) {
            speed = -1.0f;
        }
        double[] dArr2 = new double[2];
        if (C1095d.m4003a().m4005a(this.f2606f.getLongitude(), this.f2606f.getLatitude())) {
            dArr2 = Jni.coorEncrypt(this.f2606f.getLongitude(), this.f2606f.getLatitude(), BDLocation.BDLOCATION_WGS84_TO_GCJ02);
            if (dArr2[0] > 0.0d || dArr2[1] > 0.0d) {
                dArr = dArr2;
                i = 1;
            } else {
                dArr2[0] = this.f2606f.getLongitude();
                dArr2[1] = this.f2606f.getLatitude();
                dArr = dArr2;
                i = 1;
            }
        } else {
            dArr2[0] = this.f2606f.getLongitude();
            dArr2[1] = this.f2606f.getLatitude();
            dArr = dArr2;
            i = 0;
        }
        String format = String.format(Locale.CHINA, str, new Object[]{Double.valueOf(dArr[0]), Double.valueOf(dArr[1]), Integer.valueOf(accuracy), Float.valueOf(this.f2606f.getBearing()), Float.valueOf(speed), Integer.valueOf(f2599k)});
        if (i == 0) {
            format = format + ",\"in_cn\":\"0\"";
        }
        if (!this.f2606f.hasAltitude()) {
            return format + "}}";
        }
        return format + String.format(Locale.CHINA, ",\"h\":%.2f}}", new Object[]{Double.valueOf(this.f2606f.getAltitude())});
    }

    /* renamed from: g */
    public Location m3935g() {
        return (this.f2606f != null && Math.abs(System.currentTimeMillis() - this.f2606f.getTime()) <= ReconfigureOnChangeFilter.DEFAULT_REFRESH_PERIOD) ? this.f2606f : null;
    }

    /* renamed from: h */
    public boolean m3936h() {
        try {
            return (this.f2606f == null || this.f2606f.getLatitude() == 0.0d || this.f2606f.getLongitude() == 0.0d || (f2599k <= 2 && this.f2606f.getExtras().getInt("satellites", 3) <= 2)) ? false : true;
        } catch (Exception e) {
            return (this.f2606f == null || this.f2606f.getLatitude() == 0.0d || this.f2606f.getLongitude() == 0.0d) ? false : true;
        }
    }

    /* renamed from: i */
    public boolean m3937i() {
        if (!m3936h() || System.currentTimeMillis() - this.f2617r > AbstractComponentTracker.LINGERING_TIMEOUT) {
            return false;
        }
        return (!this.f2613n || System.currentTimeMillis() - this.f2612m >= 3000) ? this.f2616q : true;
    }
}
