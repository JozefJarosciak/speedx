package com.baidu.location.p043b;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.Handler;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.spi.AbstractComponentTracker;
import com.alipay.sdk.util.C0880h;
import com.baidu.location.C1102f;
import com.baidu.location.p041a.C1059j;
import com.baidu.location.p041a.C1067r;
import com.baidu.location.p041a.C1070t;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import java.util.List;

/* renamed from: com.baidu.location.b.h */
public class C1085h {
    /* renamed from: a */
    public static long f2641a = 0;
    /* renamed from: b */
    private static C1085h f2642b = null;
    /* renamed from: c */
    private WifiManager f2643c = null;
    /* renamed from: d */
    private C1084a f2644d = null;
    /* renamed from: e */
    private C1082g f2645e = null;
    /* renamed from: f */
    private long f2646f = 0;
    /* renamed from: g */
    private long f2647g = 0;
    /* renamed from: h */
    private boolean f2648h = false;
    /* renamed from: i */
    private Handler f2649i = new Handler();

    /* renamed from: com.baidu.location.b.h$a */
    private class C1084a extends BroadcastReceiver {
        /* renamed from: a */
        final /* synthetic */ C1085h f2638a;
        /* renamed from: b */
        private long f2639b;
        /* renamed from: c */
        private boolean f2640c;

        private C1084a(C1085h c1085h) {
            this.f2638a = c1085h;
            this.f2639b = 0;
            this.f2640c = false;
        }

        public void onReceive(Context context, Intent intent) {
            if (context != null) {
                String action = intent.getAction();
                if (action.equals("android.net.wifi.SCAN_RESULTS")) {
                    C1085h.f2641a = System.currentTimeMillis() / 1000;
                    this.f2638a.m3964p();
                    C1059j.m3769c().m3786f();
                    if (System.currentTimeMillis() - C1067r.m3826b() <= 5000) {
                        C1070t.m3837a(C1067r.m3827c(), this.f2638a.m3974l(), C1067r.m3828d(), C1067r.m3821a());
                    }
                } else if (action.equals("android.net.wifi.STATE_CHANGE") && ((NetworkInfo) intent.getParcelableExtra("networkInfo")).getState().equals(State.CONNECTED) && System.currentTimeMillis() - this.f2639b >= 5000) {
                    this.f2639b = System.currentTimeMillis();
                    if (!this.f2640c) {
                        this.f2640c = true;
                    }
                }
            }
        }
    }

    private C1085h() {
    }

    /* renamed from: a */
    public static synchronized C1085h m3959a() {
        C1085h c1085h;
        synchronized (C1085h.class) {
            if (f2642b == null) {
                f2642b = new C1085h();
            }
            c1085h = f2642b;
        }
        return c1085h;
    }

    /* renamed from: a */
    private String m3960a(long j) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(String.valueOf((int) (j & 255)));
        stringBuffer.append(CoreConstants.DOT);
        stringBuffer.append(String.valueOf((int) ((j >> 8) & 255)));
        stringBuffer.append(CoreConstants.DOT);
        stringBuffer.append(String.valueOf((int) ((j >> 16) & 255)));
        stringBuffer.append(CoreConstants.DOT);
        stringBuffer.append(String.valueOf((int) ((j >> 24) & 255)));
        return stringBuffer.toString();
    }

    /* renamed from: a */
    public static boolean m3962a(C1082g c1082g, C1082g c1082g2, float f) {
        if (c1082g == null || c1082g2 == null) {
            return false;
        }
        List list = c1082g.f2633a;
        List list2 = c1082g2.f2633a;
        if (list == list2) {
            return true;
        }
        if (list == null || list2 == null) {
            return false;
        }
        int size = list.size();
        int size2 = list2.size();
        float f2 = (float) (size + size2);
        if (size == 0 && size2 == 0) {
            return true;
        }
        if (size == 0 || size2 == 0) {
            return false;
        }
        int i = 0;
        int i2 = 0;
        while (i < size) {
            int i3;
            String str = ((ScanResult) list.get(i)).BSSID;
            if (str == null) {
                i3 = i2;
            } else {
                for (int i4 = 0; i4 < size2; i4++) {
                    if (str.equals(((ScanResult) list2.get(i4)).BSSID)) {
                        i3 = i2 + 1;
                        break;
                    }
                }
                i3 = i2;
            }
            i++;
            i2 = i3;
        }
        return ((float) (i2 * 2)) > f2 * f;
    }

    /* renamed from: h */
    public static boolean m3963h() {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) C1102f.getServiceContext().getSystemService("connectivity")).getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.getType() == 1;
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: p */
    private void m3964p() {
        if (this.f2643c != null) {
            try {
                List scanResults = this.f2643c.getScanResults();
                if (scanResults != null) {
                    C1082g c1082g = new C1082g(scanResults, System.currentTimeMillis());
                    if (this.f2645e == null || !c1082g.m3948a(this.f2645e)) {
                        this.f2645e = c1082g;
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    /* renamed from: b */
    public synchronized void m3965b() {
        if (!this.f2648h) {
            if (C1102f.isServing) {
                this.f2643c = (WifiManager) C1102f.getServiceContext().getSystemService(MapboxEvent.ATTRIBUTE_WIFI);
                this.f2644d = new C1084a();
                try {
                    C1102f.getServiceContext().registerReceiver(this.f2644d, new IntentFilter("android.net.wifi.SCAN_RESULTS"));
                } catch (Exception e) {
                }
                this.f2648h = true;
            }
        }
    }

    /* renamed from: c */
    public synchronized void m3966c() {
        if (this.f2648h) {
            try {
                C1102f.getServiceContext().unregisterReceiver(this.f2644d);
                f2641a = 0;
            } catch (Exception e) {
            }
            this.f2644d = null;
            this.f2643c = null;
            this.f2648h = false;
        }
    }

    /* renamed from: d */
    public boolean m3967d() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.f2647g > 0 && currentTimeMillis - this.f2647g <= 5000) {
            return false;
        }
        this.f2647g = currentTimeMillis;
        return m3968e();
    }

    /* renamed from: e */
    public boolean m3968e() {
        if (this.f2643c == null) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.f2646f > 0) {
            if (currentTimeMillis - this.f2646f <= 5000 || currentTimeMillis - (f2641a * 1000) <= 5000) {
                return false;
            }
            if (C1085h.m3963h() && currentTimeMillis - this.f2646f <= AbstractComponentTracker.LINGERING_TIMEOUT) {
                return false;
            }
        }
        return m3970g();
    }

    /* renamed from: f */
    public String m3969f() {
        String str = "";
        if (this.f2643c == null) {
            return str;
        }
        try {
            return (this.f2643c.isWifiEnabled() || (VERSION.SDK_INT > 17 && this.f2643c.isScanAlwaysAvailable())) ? "&wifio=1" : str;
        } catch (NoSuchMethodError e) {
            return str;
        } catch (Exception e2) {
            return str;
        }
    }

    @SuppressLint({"NewApi"})
    /* renamed from: g */
    public boolean m3970g() {
        try {
            if (!this.f2643c.isWifiEnabled() && (VERSION.SDK_INT <= 17 || !this.f2643c.isScanAlwaysAvailable())) {
                return false;
            }
            this.f2643c.startScan();
            this.f2646f = System.currentTimeMillis();
            return true;
        } catch (NoSuchMethodError e) {
            return false;
        } catch (Exception e2) {
            return false;
        }
    }

    /* renamed from: i */
    public WifiInfo m3971i() {
        if (this.f2643c == null) {
            return null;
        }
        try {
            WifiInfo connectionInfo = this.f2643c.getConnectionInfo();
            if (connectionInfo == null || connectionInfo.getBSSID() == null) {
                return null;
            }
            String bssid = connectionInfo.getBSSID();
            if (bssid != null) {
                bssid = bssid.replace(":", "");
                if ("000000000000".equals(bssid) || "".equals(bssid)) {
                    return null;
                }
            }
            return connectionInfo;
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: j */
    public String m3972j() {
        StringBuffer stringBuffer = new StringBuffer();
        WifiInfo i = C1085h.m3959a().m3971i();
        if (i == null || i.getBSSID() == null) {
            return null;
        }
        String replace = i.getBSSID().replace(":", "");
        int rssi = i.getRssi();
        String k = C1085h.m3959a().m3973k();
        if (rssi < 0) {
            rssi = -rssi;
        }
        if (replace == null) {
            return null;
        }
        stringBuffer.append("&wf=");
        stringBuffer.append(replace);
        stringBuffer.append(C0880h.f2220b);
        stringBuffer.append("" + rssi + C0880h.f2220b);
        stringBuffer.append(i.getSSID());
        stringBuffer.append("&wf_n=1");
        if (k != null) {
            stringBuffer.append("&wf_gw=");
            stringBuffer.append(k);
        }
        return stringBuffer.toString();
    }

    /* renamed from: k */
    public String m3973k() {
        if (this.f2643c == null) {
            return null;
        }
        DhcpInfo dhcpInfo = this.f2643c.getDhcpInfo();
        return dhcpInfo != null ? m3960a((long) dhcpInfo.gateway) : null;
    }

    /* renamed from: l */
    public C1082g m3974l() {
        return (this.f2645e == null || !this.f2645e.m3956f()) ? m3976n() : this.f2645e;
    }

    /* renamed from: m */
    public C1082g m3975m() {
        return (this.f2645e == null || !this.f2645e.m3957g()) ? m3976n() : this.f2645e;
    }

    /* renamed from: n */
    public C1082g m3976n() {
        if (this.f2643c != null) {
            try {
                return new C1082g(this.f2643c.getScanResults(), this.f2646f);
            } catch (Exception e) {
            }
        }
        return new C1082g(null, 0);
    }

    /* renamed from: o */
    public String m3977o() {
        String str = null;
        try {
            WifiInfo connectionInfo = this.f2643c.getConnectionInfo();
            if (connectionInfo != null) {
                str = connectionInfo.getMacAddress();
            }
        } catch (Exception e) {
        }
        return str;
    }
}
