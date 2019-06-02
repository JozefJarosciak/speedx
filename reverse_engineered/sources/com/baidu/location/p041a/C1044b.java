package com.baidu.location.p041a;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import com.baidu.android.bbalbs.common.util.CommonParam;
import com.baidu.location.BDLocation;
import com.baidu.location.Jni;
import com.baidu.location.LocationClientOption;
import com.baidu.location.p042d.C1041e;
import com.baidu.location.p042d.C1100j;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import io.rong.imlib.statistics.UserData;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/* renamed from: com.baidu.location.a.b */
public class C1044b {
    /* renamed from: e */
    private static Method f2350e = null;
    /* renamed from: f */
    private static Method f2351f = null;
    /* renamed from: g */
    private static Method f2352g = null;
    /* renamed from: h */
    private static Method f2353h = null;
    /* renamed from: i */
    private static Method f2354i = null;
    /* renamed from: j */
    private static Class<?> f2355j = null;
    /* renamed from: a */
    C1042c f2356a = new C1042c(this);
    /* renamed from: b */
    private Context f2357b = null;
    /* renamed from: c */
    private TelephonyManager f2358c = null;
    /* renamed from: d */
    private C1040a f2359d = new C1040a();
    /* renamed from: k */
    private WifiManager f2360k = null;
    /* renamed from: l */
    private C1043d f2361l = null;
    /* renamed from: m */
    private String f2362m = null;
    /* renamed from: n */
    private LocationClientOption f2363n;
    /* renamed from: o */
    private C1035b f2364o;
    /* renamed from: p */
    private String f2365p = null;
    /* renamed from: q */
    private String f2366q = null;
    /* renamed from: r */
    private String f2367r = null;

    /* renamed from: com.baidu.location.a.b$b */
    public interface C1035b {
        void onReceiveLocation(BDLocation bDLocation);
    }

    /* renamed from: com.baidu.location.a.b$a */
    private class C1040a {
        /* renamed from: a */
        public int f2326a;
        /* renamed from: b */
        public int f2327b;
        /* renamed from: c */
        public int f2328c;
        /* renamed from: d */
        public int f2329d;
        /* renamed from: e */
        public int f2330e;
        /* renamed from: f */
        public int f2331f;
        /* renamed from: g */
        public char f2332g;
        /* renamed from: h */
        final /* synthetic */ C1044b f2333h;

        private C1040a(C1044b c1044b) {
            this.f2333h = c1044b;
            this.f2326a = -1;
            this.f2327b = -1;
            this.f2328c = -1;
            this.f2329d = -1;
            this.f2330e = Integer.MAX_VALUE;
            this.f2331f = Integer.MAX_VALUE;
            this.f2332g = '\u0000';
        }

        /* renamed from: c */
        private boolean m3660c() {
            return this.f2326a > -1 && this.f2327b > 0;
        }

        /* renamed from: a */
        public int m3661a() {
            return (this.f2328c <= 0 || !m3660c()) ? 2 : (this.f2328c == 460 || this.f2328c == 454 || this.f2328c == 455 || this.f2328c == 466) ? 1 : 0;
        }

        /* renamed from: b */
        public String m3662b() {
            if (!m3660c()) {
                return null;
            }
            StringBuffer stringBuffer = new StringBuffer(128);
            stringBuffer.append("&nw=");
            stringBuffer.append(this.f2332g);
            stringBuffer.append(String.format(Locale.CHINA, "&cl=%d|%d|%d|%d", new Object[]{Integer.valueOf(this.f2328c), Integer.valueOf(this.f2329d), Integer.valueOf(this.f2326a), Integer.valueOf(this.f2327b)}));
            if (this.f2330e < Integer.MAX_VALUE && this.f2331f < Integer.MAX_VALUE) {
                stringBuffer.append(String.format(Locale.CHINA, "&cdmall=%.6f|%.6f", new Object[]{Double.valueOf(((double) this.f2331f) / 14400.0d), Double.valueOf(((double) this.f2330e) / 14400.0d)}));
            }
            return stringBuffer.toString();
        }
    }

    /* renamed from: com.baidu.location.a.b$c */
    class C1042c extends C1041e {
        /* renamed from: a */
        String f2345a;
        /* renamed from: b */
        final /* synthetic */ C1044b f2346b;

        C1042c(C1044b c1044b) {
            this.f2346b = c1044b;
            this.f2345a = null;
            this.k = new HashMap();
        }

        /* renamed from: a */
        public void mo2597a() {
            this.h = C1100j.m4019c();
            if (!(this.f2346b.f2366q == null || this.f2346b.f2367r == null)) {
                this.f2345a += String.format(Locale.CHINA, "&ki=%s&sn=%s", new Object[]{this.f2346b.f2366q, this.f2346b.f2367r});
            }
            String encodeTp4 = Jni.encodeTp4(this.f2345a);
            this.f2345a = null;
            this.k.put("bloc", encodeTp4);
            this.k.put("trtm", String.format(Locale.CHINA, "%d", new Object[]{Long.valueOf(System.currentTimeMillis())}));
        }

        /* renamed from: a */
        public void m3674a(String str) {
            this.f2345a = str;
            m3672e();
        }

        /* renamed from: a */
        public void mo2598a(boolean z) {
            if (z && this.j != null) {
                try {
                    BDLocation bDLocation;
                    try {
                        bDLocation = new BDLocation(this.j);
                    } catch (Exception e) {
                        bDLocation = new BDLocation();
                        bDLocation.setLocType(63);
                    }
                    if (bDLocation != null) {
                        if (bDLocation.getLocType() == 161) {
                            bDLocation.setCoorType(this.f2346b.f2363n.coorType);
                            this.f2346b.f2364o.onReceiveLocation(bDLocation);
                        }
                    }
                } catch (Exception e2) {
                }
            }
            if (this.k != null) {
                this.k.clear();
            }
        }
    }

    /* renamed from: com.baidu.location.a.b$d */
    protected class C1043d {
        /* renamed from: a */
        public List<ScanResult> f2347a = null;
        /* renamed from: b */
        final /* synthetic */ C1044b f2348b;
        /* renamed from: c */
        private long f2349c = 0;

        public C1043d(C1044b c1044b, List<ScanResult> list) {
            this.f2348b = c1044b;
            this.f2347a = list;
            this.f2349c = System.currentTimeMillis();
            m3677c();
        }

        /* renamed from: b */
        private String m3676b() {
            WifiInfo connectionInfo = this.f2348b.f2360k.getConnectionInfo();
            if (connectionInfo == null) {
                return null;
            }
            try {
                String bssid = connectionInfo.getBSSID();
                String replace = bssid != null ? bssid.replace(":", "") : null;
                return replace.length() == 12 ? new String(replace) : null;
            } catch (Exception e) {
                return null;
            }
        }

        /* renamed from: c */
        private void m3677c() {
            if (m3678a() >= 1) {
                Object obj = 1;
                for (int size = this.f2347a.size() - 1; size >= 1 && r2 != null; size--) {
                    int i = 0;
                    obj = null;
                    while (i < size) {
                        Object obj2;
                        if (((ScanResult) this.f2347a.get(i)).level < ((ScanResult) this.f2347a.get(i + 1)).level) {
                            ScanResult scanResult = (ScanResult) this.f2347a.get(i + 1);
                            this.f2347a.set(i + 1, this.f2347a.get(i));
                            this.f2347a.set(i, scanResult);
                            obj2 = 1;
                        } else {
                            obj2 = obj;
                        }
                        i++;
                        obj = obj2;
                    }
                }
            }
        }

        /* renamed from: a */
        public int m3678a() {
            return this.f2347a == null ? 0 : this.f2347a.size();
        }

        /* renamed from: a */
        public String m3679a(int i) {
            if (m3678a() < 2) {
                return null;
            }
            StringBuffer stringBuffer = new StringBuffer(512);
            int size = this.f2347a.size();
            Object obj = 1;
            int i2 = 0;
            String b = m3676b();
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            while (i5 < size) {
                int i6;
                if (((ScanResult) this.f2347a.get(i5)).level == 0) {
                    i6 = i2;
                } else {
                    i3++;
                    if (obj != null) {
                        stringBuffer.append("&wf=");
                        obj = null;
                    } else {
                        stringBuffer.append("|");
                    }
                    String replace = ((ScanResult) this.f2347a.get(i5)).BSSID.replace(":", "");
                    stringBuffer.append(replace);
                    if (b != null && replace.equals(b)) {
                        i4 = i3;
                    }
                    i6 = ((ScanResult) this.f2347a.get(i5)).level;
                    if (i6 < 0) {
                        i6 = -i6;
                    }
                    stringBuffer.append(String.format(Locale.CHINA, ";%d;", new Object[]{Integer.valueOf(i6)}));
                    i6 = i2 + 1;
                    if (i6 > i) {
                        break;
                    }
                }
                i5++;
                i2 = i6;
            }
            if (i4 > 0) {
                stringBuffer.append("&wf_n=");
                stringBuffer.append(i4);
            }
            return obj != null ? null : stringBuffer.toString();
        }
    }

    public C1044b(Context context, LocationClientOption locationClientOption, C1035b c1035b) {
        String deviceId;
        String a;
        this.f2357b = context.getApplicationContext();
        this.f2363n = locationClientOption;
        this.f2364o = c1035b;
        String packageName = this.f2357b.getPackageName();
        try {
            this.f2358c = (TelephonyManager) this.f2357b.getSystemService(UserData.PHONE_KEY);
            deviceId = this.f2358c.getDeviceId();
        } catch (Exception e) {
            deviceId = null;
        }
        try {
            a = CommonParam.m3534a(this.f2357b);
        } catch (Exception e2) {
            a = null;
        }
        if (a != null) {
            this.f2362m = "&prod=" + this.f2363n.prodName + ":" + packageName + "|&cu=" + a + "&coor=" + locationClientOption.getCoorType();
        } else {
            this.f2362m = "&prod=" + this.f2363n.prodName + ":" + packageName + "|&im=" + deviceId + "&coor=" + locationClientOption.getCoorType();
        }
        StringBuffer stringBuffer = new StringBuffer(256);
        stringBuffer.append("&fw=");
        stringBuffer.append("7.12");
        stringBuffer.append("&lt=1");
        stringBuffer.append("&mb=");
        stringBuffer.append(Build.MODEL);
        stringBuffer.append("&resid=");
        stringBuffer.append("12");
        if (locationClientOption.getAddrType() != null) {
        }
        if (locationClientOption.getAddrType() != null && locationClientOption.getAddrType().equals("all")) {
            this.f2362m += "&addr=all";
        }
        if (locationClientOption.isNeedAptag || locationClientOption.isNeedAptagd) {
            this.f2362m += "&sema=";
            if (locationClientOption.isNeedAptag) {
                this.f2362m += "aptag|";
            }
            if (locationClientOption.isNeedAptagd) {
                this.f2362m += "aptagd|";
            }
            this.f2366q = C1055i.m3758b(this.f2357b);
            this.f2367r = C1055i.m3759c(this.f2357b);
        }
        stringBuffer.append("&first=1");
        stringBuffer.append("&os=A");
        stringBuffer.append(VERSION.SDK);
        this.f2362m += stringBuffer.toString();
        this.f2360k = (WifiManager) this.f2357b.getSystemService(MapboxEvent.ATTRIBUTE_WIFI);
        a = m3687a();
        if (!TextUtils.isEmpty(a)) {
            a = a.replace(":", "");
        }
        if (!(TextUtils.isEmpty(a) || a.equals("020000000000"))) {
            this.f2362m += "&mac=" + a;
        }
        m3688b();
    }

    /* renamed from: a */
    private String m3680a(int i) {
        String b;
        String a;
        if (i < 3) {
            i = 3;
        }
        try {
            m3682a(this.f2358c.getCellLocation());
            b = this.f2359d.m3662b();
        } catch (Exception e) {
            b = null;
        }
        try {
            this.f2361l = null;
            this.f2361l = new C1043d(this, this.f2360k.getScanResults());
            a = this.f2361l.m3679a(i);
        } catch (Exception e2) {
            a = null;
        }
        if (b == null && a == null) {
            this.f2365p = null;
            return null;
        }
        if (a != null) {
            b = b + a;
        }
        if (b == null) {
            return null;
        }
        this.f2365p = b + this.f2362m;
        return b + this.f2362m;
    }

    /* renamed from: a */
    private void m3682a(CellLocation cellLocation) {
        int i = 0;
        if (cellLocation != null && this.f2358c != null) {
            C1040a c1040a = new C1040a();
            String networkOperator = this.f2358c.getNetworkOperator();
            if (networkOperator != null && networkOperator.length() > 0) {
                try {
                    if (networkOperator.length() >= 3) {
                        int intValue = Integer.valueOf(networkOperator.substring(0, 3)).intValue();
                        if (intValue < 0) {
                            intValue = this.f2359d.f2328c;
                        }
                        c1040a.f2328c = intValue;
                    }
                    String substring = networkOperator.substring(3);
                    if (substring != null) {
                        char[] toCharArray = substring.toCharArray();
                        while (i < toCharArray.length && Character.isDigit(toCharArray[i])) {
                            i++;
                        }
                    }
                    i = Integer.valueOf(substring.substring(0, i)).intValue();
                    if (i < 0) {
                        i = this.f2359d.f2329d;
                    }
                    c1040a.f2329d = i;
                } catch (Exception e) {
                }
            }
            if (cellLocation instanceof GsmCellLocation) {
                c1040a.f2326a = ((GsmCellLocation) cellLocation).getLac();
                c1040a.f2327b = ((GsmCellLocation) cellLocation).getCid();
                c1040a.f2332g = 'g';
            } else if (cellLocation instanceof CdmaCellLocation) {
                c1040a.f2332g = 'c';
                if (f2355j == null) {
                    try {
                        f2355j = Class.forName("android.telephony.cdma.CdmaCellLocation");
                        f2350e = f2355j.getMethod("getBaseStationId", new Class[0]);
                        f2351f = f2355j.getMethod("getNetworkId", new Class[0]);
                        f2352g = f2355j.getMethod("getSystemId", new Class[0]);
                        f2353h = f2355j.getMethod("getBaseStationLatitude", new Class[0]);
                        f2354i = f2355j.getMethod("getBaseStationLongitude", new Class[0]);
                    } catch (Exception e2) {
                        f2355j = null;
                        return;
                    }
                }
                if (f2355j != null && f2355j.isInstance(cellLocation)) {
                    try {
                        i = ((Integer) f2352g.invoke(cellLocation, new Object[0])).intValue();
                        if (i < 0) {
                            i = this.f2359d.f2329d;
                        }
                        c1040a.f2329d = i;
                        c1040a.f2327b = ((Integer) f2350e.invoke(cellLocation, new Object[0])).intValue();
                        c1040a.f2326a = ((Integer) f2351f.invoke(cellLocation, new Object[0])).intValue();
                        Object invoke = f2353h.invoke(cellLocation, new Object[0]);
                        if (((Integer) invoke).intValue() < Integer.MAX_VALUE) {
                            c1040a.f2330e = ((Integer) invoke).intValue();
                        }
                        invoke = f2354i.invoke(cellLocation, new Object[0]);
                        if (((Integer) invoke).intValue() < Integer.MAX_VALUE) {
                            c1040a.f2331f = ((Integer) invoke).intValue();
                        }
                    } catch (Exception e3) {
                        return;
                    }
                }
            }
            if (c1040a.m3660c()) {
                this.f2359d = c1040a;
            } else {
                this.f2359d = null;
            }
        }
    }

    /* renamed from: a */
    public String m3687a() {
        String str = null;
        try {
            WifiInfo connectionInfo = this.f2360k.getConnectionInfo();
            if (connectionInfo != null) {
                str = connectionInfo.getMacAddress();
            }
        } catch (Exception e) {
        }
        return str;
    }

    /* renamed from: b */
    public String m3688b() {
        try {
            return m3680a(15);
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: c */
    public void m3689c() {
        if (this.f2365p != null && this.f2359d != null && this.f2359d.m3661a() == 1 && null == null) {
            this.f2356a.m3674a(this.f2365p);
        }
    }
}
