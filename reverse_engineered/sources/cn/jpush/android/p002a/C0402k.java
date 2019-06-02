package cn.jpush.android.p002a;

import android.net.wifi.ScanResult;
import ch.qos.logback.core.CoreConstants;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.an;
import org.json.JSONObject;

/* renamed from: cn.jpush.android.a.k */
public final class C0402k implements Comparable<C0402k> {
    /* renamed from: z */
    private static final String[] f487z;
    /* renamed from: a */
    public final String f488a;
    /* renamed from: b */
    public final int f489b;
    /* renamed from: c */
    public final String f490c;
    /* renamed from: d */
    final /* synthetic */ C0401j f491d;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 7;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "+h4^44^ D'=o4D=";
        r0 = -1;
        r4 = r3;
    L_0x0008:
        r1 = r1.toCharArray();
        r5 = r1.length;
        r6 = 0;
        r7 = 1;
        if (r5 > r7) goto L_0x002d;
    L_0x0011:
        r7 = r1;
        r8 = r6;
        r11 = r5;
        r5 = r1;
        r1 = r11;
    L_0x0016:
        r10 = r5[r6];
        r9 = r8 % 5;
        switch(r9) {
            case 0: goto L_0x0070;
            case 1: goto L_0x0073;
            case 2: goto L_0x0075;
            case 3: goto L_0x0078;
            default: goto L_0x001d;
        };
    L_0x001d:
        r9 = 85;
    L_0x001f:
        r9 = r9 ^ r10;
        r9 = (char) r9;
        r5[r6] = r9;
        r6 = r8 + 1;
        if (r1 != 0) goto L_0x002b;
    L_0x0027:
        r5 = r7;
        r8 = r6;
        r6 = r1;
        goto L_0x0016;
    L_0x002b:
        r5 = r1;
        r1 = r7;
    L_0x002d:
        if (r5 > r6) goto L_0x0011;
    L_0x002f:
        r5 = new java.lang.String;
        r5.<init>(r1);
        r1 = r5.intern();
        switch(r0) {
            case 0: goto L_0x0043;
            case 1: goto L_0x004b;
            case 2: goto L_0x0053;
            case 3: goto L_0x005b;
            case 4: goto L_0x0063;
            case 5: goto L_0x006b;
            default: goto L_0x003b;
        };
    L_0x003b:
        r3[r2] = r1;
        r2 = 1;
        r1 = "5`0o4<e!U&+";
        r0 = 0;
        r3 = r4;
        goto L_0x0008;
    L_0x0043:
        r3[r2] = r1;
        r2 = 2;
        r1 = "9f6";
        r0 = 1;
        r3 = r4;
        goto L_0x0008;
    L_0x004b:
        r3[r2] = r1;
        r2 = 3;
        r1 = "+r:T";
        r0 = 2;
        r3 = r4;
        goto L_0x0008;
    L_0x0053:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\u000fh5Y\u001c6g<K7+r:Th";
        r0 = 3;
        r3 = r4;
        goto L_0x0008;
    L_0x005b:
        r3[r2] = r1;
        r2 = 5;
        r1 = "t!7r8e";
        r0 = 4;
        r3 = r4;
        goto L_0x0008;
    L_0x0063:
        r3[r2] = r1;
        r2 = 6;
        r1 = "t! C<<<t";
        r0 = 5;
        r3 = r4;
        goto L_0x0008;
    L_0x006b:
        r3[r2] = r1;
        f487z = r4;
        return;
    L_0x0070:
        r9 = 88;
        goto L_0x001f;
    L_0x0073:
        r9 = 1;
        goto L_0x001f;
    L_0x0075:
        r9 = 83;
        goto L_0x001f;
    L_0x0078:
        r9 = 48;
        goto L_0x001f;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.a.k.<clinit>():void");
    }

    public C0402k(C0401j c0401j, ScanResult scanResult) {
        this.f491d = c0401j;
        this.f488a = scanResult.BSSID;
        this.f489b = scanResult.level;
        this.f490c = an.m1660c(scanResult.SSID);
    }

    public C0402k(C0401j c0401j, String str, int i, String str2) {
        this.f491d = c0401j;
        this.f488a = str;
        this.f489b = i;
        this.f490c = an.m1660c(str2);
    }

    /* renamed from: a */
    public final JSONObject m1031a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(f487z[1], this.f488a);
            jSONObject.put(f487z[0], this.f489b);
            jSONObject.put(f487z[3], this.f490c);
            jSONObject.put(f487z[2], 0);
        } catch (Exception e) {
            ac.m1593i();
        }
        return jSONObject;
    }

    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return ((C0402k) obj).f489b - this.f489b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof C0402k) {
            C0402k c0402k = (C0402k) obj;
            if (c0402k.f489b == this.f489b && c0402k.f488a.equals(this.f488a)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.f489b ^ this.f488a.hashCode();
    }

    public final String toString() {
        return new StringBuilder(f487z[4]).append(this.f488a).append(CoreConstants.SINGLE_QUOTE_CHAR).append(f487z[5]).append(this.f489b).append(f487z[6]).append(this.f490c).append(CoreConstants.SINGLE_QUOTE_CHAR).append(CoreConstants.CURLY_RIGHT).toString();
    }
}
