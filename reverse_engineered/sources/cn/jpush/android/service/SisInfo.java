package cn.jpush.android.service;

import cn.jpush.android.C0404a;
import cn.jpush.android.util.ac;
import com.google.gson.jpush.C4042k;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class SisInfo {
    private static Pattern IPV4_PATTERN = Pattern.compile(IPV4_REGEX);
    private static final String IPV4_REGEX;
    private static final String TAG;
    private static final C4042k _gson = new C4042k();
    /* renamed from: z */
    private static final String[] f829z;
    boolean invalidSis = false;
    List<String> ips;
    String mainConnIp;
    int mainConnPort;
    List<String> op_conns;
    List<String> optionConnIp = new ArrayList();
    List<Integer> optionConnPort = new ArrayList();
    String originSis;
    List<String> ssl_ips;
    List<String> ssl_op_conns;
    List<String> udp_report;
    String user;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 7;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "IM\u001b,5|K";
        r0 = 6;
        r4 = r3;
    L_0x0008:
        r1 = r1.toCharArray();
        r5 = r1.length;
        r6 = 0;
        r7 = 1;
        if (r5 > r7) goto L_0x0039;
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
            case 0: goto L_0x008b;
            case 1: goto L_0x008e;
            case 2: goto L_0x0091;
            case 3: goto L_0x0094;
            default: goto L_0x001d;
        };
    L_0x001d:
        r9 = 91;
    L_0x001f:
        r9 = r9 ^ r10;
        r9 = (char) r9;
        r5[r6] = r9;
        r6 = r8 + 1;
        if (r1 != 0) goto L_0x0037;
    L_0x0027:
        r5 = r7;
        r8 = r6;
        r6 = r1;
        goto L_0x0016;
    L_0x002b:
        TAG = r1;
        r1 = "2\f3Uv+yW>k7\u001d5\u001ej6\u0016\u00159u3X@W\u0000*\t\\8\u0000*\tQ8\u00074\r\u0014Mi/XHnGxFLra\u0017\u0015MsA\u0014ET\u0006%XHbG_YIig\r\u0014MiA\u0014EQ\u0006A\u0014E\\\u00063X@WnA\u0014EP\u00063\r";
        r0 = 7;
        goto L_0x0008;
    L_0x0031:
        IPV4_REGEX = r1;
        r1 = "OJ\r\u001d+G\u001c\u0000?:\tH\f5lE\u0004\f?:W\u0001\u0016{7\u0004\u0001\u0015(:E\u001a\u0017:c\u0004\u0004\u00005:M\u001bEk";
        r0 = -1;
        goto L_0x0008;
    L_0x0037:
        r5 = r1;
        r1 = r7;
    L_0x0039:
        if (r5 > r6) goto L_0x0011;
    L_0x003b:
        r5 = new java.lang.String;
        r5.<init>(r1);
        r1 = r5.intern();
        switch(r0) {
            case 0: goto L_0x004f;
            case 1: goto L_0x0057;
            case 2: goto L_0x005f;
            case 3: goto L_0x0067;
            case 4: goto L_0x006f;
            case 5: goto L_0x0077;
            case 6: goto L_0x002b;
            case 7: goto L_0x0031;
            default: goto L_0x0047;
        };
    L_0x0047:
        r3[r2] = r1;
        r2 = 1;
        r1 = "OJ\r\u001d+G\u001c\u0000?:\tH,5lE\u0004\f?:W\u0001\u0016{7\u0004\u0006\n{sT\u001bE0]F";
        r0 = 0;
        r3 = r4;
        goto L_0x0008;
    L_0x004f:
        r3[r2] = r1;
        r2 = 2;
        r1 = "\\E\u0001\t>~\u0004\u001c\n{jE\u001a\u0016>:M\u0018\u0016v+\u0004EE6{M\u0006E2j\n";
        r0 = 1;
        r3 = r4;
        goto L_0x0008;
    L_0x0057:
        r3[r2] = r1;
        r2 = 3;
        r1 = "\\E\u0001\t>~\u0004\u001c\n{jE\u001a\u0016>:K\u0018:8uJ\u0006Ev:";
        r0 = 2;
        r3 = r4;
        goto L_0x0008;
    L_0x005f:
        r3[r2] = r1;
        r2 = 4;
        r1 = "TKH\u0017>jK\u001a\u0011{xE\u000b\u000e.j\u0004\u0001\u0015u";
        r0 = 3;
        r3 = r4;
        goto L_0x0008;
    L_0x0067:
        r3[r2] = r1;
        r2 = 5;
        r1 = "UJ\u0004\u001c{wE\u0001\u000b{sTH\f5:W\u0001\u0016u";
        r0 = 4;
        r3 = r4;
        goto L_0x0008;
    L_0x006f:
        r3[r2] = r1;
        r2 = 6;
        r1 = "\\E\u0001\t>~\u0004\u001c\n{jE\u001a\u0016>:M\u0018\u0016v(\u0004EE?B\t\u00107n\u0004\u0001\u0015u";
        r0 = 5;
        r3 = r4;
        goto L_0x0008;
    L_0x0077:
        r3[r2] = r1;
        f829z = r4;
        r0 = new com.google.gson.jpush.k;
        r0.<init>();
        _gson = r0;
        r0 = IPV4_REGEX;
        r0 = java.util.regex.Pattern.compile(r0);
        IPV4_PATTERN = r0;
        return;
    L_0x008b:
        r9 = 26;
        goto L_0x001f;
    L_0x008e:
        r9 = 36;
        goto L_0x001f;
    L_0x0091:
        r9 = 104; // 0x68 float:1.46E-43 double:5.14E-322;
        goto L_0x001f;
    L_0x0094:
        r9 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        goto L_0x001f;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.service.SisInfo.<clinit>():void");
    }

    public static SisInfo fromJson(String str) {
        return (SisInfo) _gson.a(str, SisInfo.class);
    }

    public static boolean isValidIPV4(String str) {
        return IPV4_PATTERN.matcher(str).matches();
    }

    public void configure() {
        int size = this.ips.size();
        if (size != 0) {
            C0404a.m1103f(this.originSis);
            if (size > 1) {
                try {
                    C0478q c0478q = new C0478q((String) this.ips.get(1));
                    C0404a.m1088c(c0478q.f893a);
                    C0404a.m1084c(c0478q.f894b);
                } catch (Throwable e) {
                    ac.m1579a(TAG, f829z[6], e);
                }
            } else {
                ac.m1587d(TAG, f829z[5]);
            }
            if (size > 2) {
                C0404a.m1093d((String) this.ips.get(2));
            } else {
                ac.m1587d(TAG, f829z[4]);
            }
            if (this.user != null) {
                C0404a.m1100e(this.user);
            }
        }
    }

    public String getMainConnIp() {
        return this.mainConnIp;
    }

    public int getMainConnPort() {
        return this.mainConnPort;
    }

    public List<String> getOptionConnIp() {
        return this.optionConnIp;
    }

    public List<Integer> getOptionConnPort() {
        return this.optionConnPort;
    }

    public String getOriginSis() {
        return this.originSis;
    }

    public boolean isInvalidSis() {
        return this.invalidSis;
    }

    public void parseAndSet(String str) {
        this.originSis = str;
        if (this.ips == null) {
            ac.m1589e(TAG, f829z[1]);
            this.invalidSis = true;
        } else if (this.ips.size() == 0) {
            ac.m1589e(TAG, f829z[0]);
            this.invalidSis = true;
        } else {
            try {
                C0478q c0478q = new C0478q((String) this.ips.get(0));
                this.mainConnIp = c0478q.f893a;
                this.mainConnPort = c0478q.f894b;
                if (this.op_conns == null) {
                    ac.m1581b();
                    return;
                }
                for (String str2 : this.op_conns) {
                    try {
                        C0478q c0478q2 = new C0478q(str2);
                        this.optionConnIp.add(c0478q2.f893a);
                        this.optionConnPort.add(Integer.valueOf(c0478q2.f894b));
                    } catch (Exception e) {
                        new StringBuilder(f829z[3]).append(str2);
                        ac.m1593i();
                    }
                }
            } catch (Throwable e2) {
                ac.m1579a(TAG, f829z[2], e2);
                this.invalidSis = true;
            }
        }
    }
}
