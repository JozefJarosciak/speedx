package cn.jpush.android.p002a;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import cn.jpush.android.util.C0490b;
import cn.jpush.android.util.ac;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: cn.jpush.android.a.j */
public final class C0401j {
    /* renamed from: z */
    private static final String[] f484z;
    /* renamed from: a */
    private WifiManager f485a;
    /* renamed from: b */
    private Context f486b = null;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 9;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "U\u0015i\u0016=A\u0013z\u0011i\u0018";
        r0 = -1;
        r4 = r3;
    L_0x0009:
        r1 = r1.toCharArray();
        r5 = r1.length;
        r6 = 0;
        r7 = 1;
        if (r5 > r7) goto L_0x002e;
    L_0x0012:
        r7 = r1;
        r8 = r6;
        r11 = r5;
        r5 = r1;
        r1 = r11;
    L_0x0017:
        r10 = r5[r6];
        r9 = r8 % 5;
        switch(r9) {
            case 0: goto L_0x0082;
            case 1: goto L_0x0085;
            case 2: goto L_0x0088;
            case 3: goto L_0x008b;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 29;
    L_0x0020:
        r9 = r9 ^ r10;
        r9 = (char) r9;
        r5[r6] = r9;
        r6 = r8 + 1;
        if (r1 != 0) goto L_0x002c;
    L_0x0028:
        r5 = r7;
        r8 = r6;
        r6 = r1;
        goto L_0x0017;
    L_0x002c:
        r5 = r1;
        r1 = r7;
    L_0x002e:
        if (r5 > r6) goto L_0x0012;
    L_0x0030:
        r5 = new java.lang.String;
        r5.<init>(r1);
        r1 = r5.intern();
        switch(r0) {
            case 0: goto L_0x0044;
            case 1: goto L_0x004c;
            case 2: goto L_0x0054;
            case 3: goto L_0x005c;
            case 4: goto L_0x0064;
            case 5: goto L_0x006c;
            case 6: goto L_0x0074;
            case 7: goto L_0x007d;
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = "W\u0012j\u0007mG\u001f{\u001ay\u0003";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "U\u0015i\u0016";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "V\u001dh";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "Q\b}\u0010sE\u0019|\u000b";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "A\t}\rxL\bX\u0016{KF";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "U\u0015i\u0016'";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "C\u0012k\rrK\u0018!\u000fxP\u0011f\fnK\u0013aQ\\a?J,N}?@>Oq9P3Ra=[6Rl";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = "A\u0013a\u0011xA\b";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        f484z = r4;
        return;
    L_0x0082:
        r9 = 34;
        goto L_0x0020;
    L_0x0085:
        r9 = 124; // 0x7c float:1.74E-43 double:6.13E-322;
        goto L_0x0020;
    L_0x0088:
        r9 = 15;
        goto L_0x0020;
    L_0x008b:
        r9 = 127; // 0x7f float:1.78E-43 double:6.27E-322;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.a.j.<clinit>():void");
    }

    public C0401j(Context context) {
        this.f485a = (WifiManager) context.getSystemService(f484z[2]);
        this.f486b = context;
    }

    /* renamed from: a */
    private List<C0402k> m1027a(JSONArray jSONArray) {
        ac.m1576a();
        if (!m1028a()) {
            return null;
        }
        C0402k c0402k;
        WifiInfo connectionInfo = this.f485a.getConnectionInfo();
        if (connectionInfo != null) {
            C0402k c0402k2 = new C0402k(this, connectionInfo.getBSSID(), connectionInfo.getRssi(), connectionInfo.getSSID());
            new StringBuilder(f484z[5]).append(c0402k2.toString());
            c0402k = c0402k2;
            ac.m1576a();
        } else {
            c0402k = null;
        }
        ArrayList arrayList = new ArrayList();
        if (c0402k != null) {
            JSONObject a = c0402k.m1031a();
            a.put(f484z[3], f484z[8]);
            jSONArray.put(a);
        }
        List scanResults = VERSION.SDK_INT < 23 ? this.f485a.getScanResults() : (this.f486b == null || !C0490b.m1697c(this.f486b, f484z[7])) ? null : this.f485a.getScanResults();
        if (r0 != null && r0.size() > 0) {
            int i = -200;
            C0402k c0402k3 = null;
            for (ScanResult c0402k4 : r0) {
                C0402k c0402k5 = new C0402k(this, c0402k4);
                new StringBuilder(f484z[6]).append(c0402k5.toString());
                ac.m1576a();
                if (c0402k.equals(c0402k5)) {
                    ac.m1576a();
                } else {
                    int i2;
                    arrayList.add(c0402k5);
                    if (c0402k5.f490c.equals(c0402k.f490c) || c0402k5.f489b <= i) {
                        c0402k2 = c0402k3;
                        i2 = i;
                    } else {
                        C0402k c0402k6 = c0402k5;
                        i2 = c0402k5.f489b;
                        c0402k2 = c0402k6;
                    }
                    c0402k3 = c0402k2;
                    i = i2;
                }
            }
            if (c0402k3 != null) {
                a = c0402k3.m1031a();
                a.put(f484z[3], f484z[4]);
                jSONArray.put(a);
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public final boolean m1028a() {
        try {
            return this.f485a.isWifiEnabled();
        } catch (Exception e) {
            ac.m1593i();
            return false;
        }
    }

    /* renamed from: b */
    public final WifiManager m1029b() {
        return this.f485a;
    }

    /* renamed from: c */
    public final JSONArray m1030c() {
        int i = 0;
        JSONArray jSONArray = new JSONArray();
        try {
            List<C0402k> a = m1027a(jSONArray);
            if (a != null) {
                i = a.size();
            }
            new StringBuilder(f484z[0]).append(i);
            ac.m1576a();
            for (C0402k a2 : a) {
                jSONArray.put(a2.m1031a());
            }
        } catch (Throwable th) {
            try {
                th.printStackTrace();
                new StringBuilder(f484z[1]).append(th.getMessage());
                ac.m1588e();
            } catch (Exception e) {
                ac.m1593i();
            }
        }
        return jSONArray;
    }
}
