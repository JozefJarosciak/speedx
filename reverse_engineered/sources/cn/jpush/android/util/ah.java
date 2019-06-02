package cn.jpush.android.util;

import android.content.Context;
import cn.jpush.android.C0404a;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.GZIPOutputStream;
import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ah {
    /* renamed from: a */
    public static JSONObject f973a = null;
    /* renamed from: b */
    private static final String f974b = ah.class.getSimpleName();
    /* renamed from: c */
    private static String f975c = "";
    /* renamed from: d */
    private static String f976d;
    /* renamed from: e */
    private static String f977e;
    /* renamed from: f */
    private static final String f978f = new StringBuilder(f981z[24]).append(f976d).toString();
    /* renamed from: g */
    private static ExecutorService f979g = Executors.newSingleThreadExecutor();
    /* renamed from: h */
    private static Object f980h = new Object();
    /* renamed from: z */
    private static final String[] f981z;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 41;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "vC2\rt";
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
            case 0: goto L_0x0212;
            case 1: goto L_0x0216;
            case 2: goto L_0x021a;
            case 3: goto L_0x021e;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 76;
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
            case 8: goto L_0x0087;
            case 9: goto L_0x0092;
            case 10: goto L_0x009d;
            case 11: goto L_0x00a8;
            case 12: goto L_0x00b3;
            case 13: goto L_0x00be;
            case 14: goto L_0x00c9;
            case 15: goto L_0x00d4;
            case 16: goto L_0x00df;
            case 17: goto L_0x00ea;
            case 18: goto L_0x00f5;
            case 19: goto L_0x0100;
            case 20: goto L_0x010b;
            case 21: goto L_0x0116;
            case 22: goto L_0x0121;
            case 23: goto L_0x012c;
            case 24: goto L_0x0137;
            case 25: goto L_0x0142;
            case 26: goto L_0x014d;
            case 27: goto L_0x0158;
            case 28: goto L_0x0163;
            case 29: goto L_0x016e;
            case 30: goto L_0x0179;
            case 31: goto L_0x0184;
            case 32: goto L_0x018f;
            case 33: goto L_0x019a;
            case 34: goto L_0x01a5;
            case 35: goto L_0x01b0;
            case 36: goto L_0x01bb;
            case 37: goto L_0x01c6;
            case 38: goto L_0x01d1;
            case 39: goto L_0x01dc;
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = "Bg\u0004'Fn";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "S{\u0015T*Le\u0019";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "Ps\u001f:Fe";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "V~\u0010";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "\u00119E\u000e{";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "Ig\u0001S$|d\u0000A8|t\u0015C$F9\u001eS#M";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "@x\u001aT)Mc";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = "Ig\u0001S$|d\u0000A8|t\u0015C$FH\u001cI?Wx\u0006YbId\u001bN";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        r2 = 9;
        r1 = "Vc\u0012\rt";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0087:
        r3[r2] = r1;
        r2 = 10;
        r1 = "\u0003~\u001aP9WD\u0000R)BzX\u0000+Ja\u0011\u00009S7\u0006E-G7T\u001a";
        r0 = 9;
        r3 = r4;
        goto L_0x0009;
    L_0x0092:
        r3[r2] = r1;
        r2 = 11;
        r1 = "@x\u001aT)[cTI?\u0003y\u0001L \u0003;TG%UrTU<\u0003e\u0011A(\u0003";
        r0 = 10;
        r3 = r4;
        goto L_0x0009;
    L_0x009d:
        r3[r2] = r1;
        r2 = 12;
        r1 = "\u0003~\u001aT#\u0003]\u0007O\"lu\u001eE/W;TG%UrTU<\u0003e\u0011A(\u0003-";
        r0 = 11;
        r3 = r4;
        goto L_0x0009;
    L_0x00a8:
        r3[r2] = r1;
        r2 = 13;
        r1 = "\u0003~\u0007\u0000\"V{\u0018\flQr\u0000U>M7\u001aU O";
        r0 = 12;
        r3 = r4;
        goto L_0x0009;
    L_0x00b3:
        r3[r2] = r1;
        r2 = 14;
        r1 = "@v\u001a\u00078\u0003x\u0004E\"\u0003";
        r0 = 13;
        r3 = r4;
        goto L_0x0009;
    L_0x00be:
        r3[r2] = r1;
        r2 = 15;
        r1 = "@v\u001a\u00078\u0003r\u001aC#G~\u001aGl";
        r0 = 14;
        r3 = r4;
        goto L_0x0009;
    L_0x00c9:
        r3[r2] = r1;
        r2 = 16;
        r1 = "@v\u001a\u00078\u0003u\u0001I G7";
        r0 = 15;
        r3 = r4;
        goto L_0x0009;
    L_0x00d4:
        r3[r2] = r1;
        r2 = 17;
        r1 = "\u000f7\u0013I:F7\u0001PlQr\u0015Dl\u0019";
        r0 = 16;
        r3 = r4;
        goto L_0x0009;
    L_0x00df:
        r3[r2] = r1;
        r2 = 18;
        r1 = "@v\u001a\u00078\u0003e\u0011A(\u0003";
        r0 = 17;
        r3 = r4;
        goto L_0x0009;
    L_0x00ea:
        r3[r2] = r1;
        r2 = 19;
        r1 = "Qr\u0004O>W7\u0002I-\u0003d\u001dN+OrTC$By\u001aE \u0019";
        r0 = 18;
        r3 = r4;
        goto L_0x0009;
    L_0x00f5:
        r3[r2] = r1;
        r2 = 20;
        r1 = "@b\u0006R)Mc+S)Pd\u001dO\"|q\u001dL)";
        r0 = 19;
        r3 = r4;
        goto L_0x0009;
    L_0x0100:
        r3[r2] = r1;
        r2 = 21;
        r1 = "K~\u0007T#Qn+F%Or";
        r0 = 20;
        r3 = r4;
        goto L_0x0009;
    L_0x010b:
        r3[r2] = r1;
        r2 = 22;
        r1 = "Gr\u0018E8F7\u0012I F7\u0007U/@r\u0007SlE~\u0018E\"Bz\u0011\u001a";
        r0 = 21;
        r3 = r4;
        goto L_0x0009;
    L_0x0116:
        r3[r2] = r1;
        r2 = 23;
        r1 = "@{\u0011A>k~\u0007T#Wn2I FT\u001bN8Fy\u0000\u0000)\u0019";
        r0 = 22;
        r3 = r4;
        goto L_0x0009;
    L_0x0121:
        r3[r2] = r1;
        r2 = 24;
        r1 = "Pc\u0015T?\r}\u0004U?K9\u0017N";
        r0 = 23;
        r3 = r4;
        goto L_0x0009;
    L_0x012c:
        r3[r2] = r1;
        r2 = 25;
        r1 = "Kc\u0000P?\u00198[";
        r0 = 24;
        r3 = r4;
        goto L_0x0009;
    L_0x0137:
        r3[r2] = r1;
        r2 = 26;
        r1 = "P~\u0007\u0000>Fg\u001bR8\u0003b\u0006Lv";
        r0 = 25;
        r3 = r4;
        goto L_0x0009;
    L_0x0142:
        r3[r2] = r1;
        r2 = 27;
        r1 = "Kc\u0000Pv\f8";
        r0 = 26;
        r3 = r4;
        goto L_0x0009;
    L_0x014d:
        r3[r2] = r1;
        r2 = 28;
        r1 = "Pr\u001aDlOx\u0013\u0000?O~\u0017Ev";
        r0 = 27;
        r3 = r4;
        goto L_0x0009;
    L_0x0158:
        r3[r2] = r1;
        r2 = 29;
        r1 = "Ox\u0013\u0000(Ja\u001dD)G7\u001dN8L7";
        r0 = 28;
        r3 = r4;
        goto L_0x0009;
    L_0x0163:
        r3[r2] = r1;
        r2 = 30;
        r1 = "Te\u0015Pl@x\u001aT-Jy\u0011RlFo\u0017E<W~\u001bN`\u0003p\u001dV)\u0003b\u0004\u0000?Fy\u0010\u0000 LpN";
        r0 = 29;
        r3 = r4;
        goto L_0x0009;
    L_0x016e:
        r3[r2] = r1;
        r2 = 31;
        r1 = "\u0003g\u0015R8P";
        r0 = 30;
        r3 = r4;
        goto L_0x0009;
    L_0x0179:
        r3[r2] = r1;
        r2 = 32;
        r1 = "Sb\u0000\u0000/Ly\u0000E\"W7\u0011X/Fg\u0000I#M;TG%UrTU<\u0003d\u0011N(\u0003{\u001bGv";
        r0 = 31;
        r3 = r4;
        goto L_0x0009;
    L_0x0184:
        r3[r2] = r1;
        r2 = 33;
        r1 = "Ox\u0013\u0000?Jm\u0011\u001a";
        r0 = 32;
        r3 = r4;
        goto L_0x0009;
    L_0x018f:
        r3[r2] = r1;
        r2 = 34;
        r1 = "Pv\u0002ElOx\u0013\u0000%M7\u0003R%Wr<I?Wx\u0006Y\u0000LpN*";
        r0 = 33;
        r3 = r4;
        goto L_0x0009;
    L_0x019a:
        r3[r2] = r1;
        r2 = 35;
        r1 = "@v\u001a\u00078\u0003`\u0006I8F7";
        r0 = 34;
        r3 = r4;
        goto L_0x0009;
    L_0x01a5:
        r3[r2] = r1;
        r2 = 36;
        r1 = "\u0003;TG%UrTU<\u0003d\u0015V)\u0003-";
        r0 = 35;
        r3 = r4;
        goto L_0x0009;
    L_0x01b0:
        r3[r2] = r1;
        r2 = 37;
        r1 = "~7X\u0000+Ja\u0011\u00009S7\u0007A:F7N";
        r0 = 36;
        r3 = r4;
        goto L_0x0009;
    L_0x01bb:
        r3[r2] = r1;
        r2 = 38;
        r1 = "e~\u0018E<Bc\u001c\u0000)Qe\u001bRlLqT{";
        r0 = 37;
        r3 = r4;
        goto L_0x0009;
    L_0x01c6:
        r3[r2] = r1;
        r2 = 39;
        r1 = "\u0003x\u0001T<Vc'T>Fv\u0019\flD~\u0002ElVgTS-UrT\u001a";
        r0 = 38;
        r3 = r4;
        goto L_0x0009;
    L_0x01d1:
        r3[r2] = r1;
        r2 = 40;
        r1 = "@x\u001aT)[cTI?\u0003y\u0001L \u0003;TG%UrTU<\u0003d\u0015V)\u0003";
        r0 = 39;
        r3 = r4;
        goto L_0x0009;
    L_0x01dc:
        r3[r2] = r1;
        f981z = r4;
        r0 = cn.jpush.android.util.ah.class;
        r0 = r0.getSimpleName();
        f974b = r0;
        r0 = "";
        f975c = r0;
        r1 = "\faE\u000f>Fg\u001bR8";
        r0 = -1;
    L_0x01ef:
        r1 = r1.toCharArray();
        r2 = r1.length;
        r3 = 0;
        r4 = 1;
        if (r2 > r4) goto L_0x0230;
    L_0x01f8:
        r4 = r1;
        r5 = r3;
        r11 = r2;
        r2 = r1;
        r1 = r11;
    L_0x01fd:
        r7 = r2[r3];
        r6 = r5 % 5;
        switch(r6) {
            case 0: goto L_0x0222;
            case 1: goto L_0x0225;
            case 2: goto L_0x0228;
            case 3: goto L_0x022b;
            default: goto L_0x0204;
        };
    L_0x0204:
        r6 = 76;
    L_0x0206:
        r6 = r6 ^ r7;
        r6 = (char) r6;
        r2[r3] = r6;
        r3 = r5 + 1;
        if (r1 != 0) goto L_0x022e;
    L_0x020e:
        r2 = r4;
        r5 = r3;
        r3 = r1;
        goto L_0x01fd;
    L_0x0212:
        r9 = 35;
        goto L_0x0020;
    L_0x0216:
        r9 = 23;
        goto L_0x0020;
    L_0x021a:
        r9 = 116; // 0x74 float:1.63E-43 double:5.73E-322;
        goto L_0x0020;
    L_0x021e:
        r9 = 32;
        goto L_0x0020;
    L_0x0222:
        r6 = 35;
        goto L_0x0206;
    L_0x0225:
        r6 = 23;
        goto L_0x0206;
    L_0x0228:
        r6 = 116; // 0x74 float:1.63E-43 double:5.73E-322;
        goto L_0x0206;
    L_0x022b:
        r6 = 32;
        goto L_0x0206;
    L_0x022e:
        r2 = r1;
        r1 = r4;
    L_0x0230:
        if (r2 > r3) goto L_0x01f8;
    L_0x0232:
        r2 = new java.lang.String;
        r2.<init>(r1);
        r1 = r2.intern();
        switch(r0) {
            case 0: goto L_0x0244;
            default: goto L_0x023e;
        };
    L_0x023e:
        f976d = r1;
        r1 = "\faF\u000f>Fg\u001bR8";
        r0 = 0;
        goto L_0x01ef;
    L_0x0244:
        f977e = r1;
        r0 = new java.lang.StringBuilder;
        r1 = f981z;
        r2 = 24;
        r1 = r1[r2];
        r0.<init>(r1);
        r1 = f976d;
        r0 = r0.append(r1);
        r0 = r0.toString();
        f978f = r0;
        r0 = java.util.concurrent.Executors.newSingleThreadExecutor();
        f979g = r0;
        r0 = 0;
        f973a = r0;
        r0 = new java.lang.Object;
        r0.<init>();
        f980h = r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.ah.<clinit>():void");
    }

    /* renamed from: a */
    public static String m1612a() {
        long x = C0404a.m1144x();
        if (x == 0) {
            ac.m1581b();
            return null;
        }
        String b = C0490b.m1686b(x + C0490b.m1686b(C0404a.m1046A()));
        return !an.m1657a(b) ? x + ":" + b : null;
    }

    /* renamed from: a */
    public static String m1613a(int i) {
        try {
            InetAddress.getByName(f981z[24]);
            return new StringBuilder(f981z[25]).append(f981z[24]).append(f977e).toString();
        } catch (Exception e) {
            ac.m1592h();
            return m1627b();
        }
    }

    /* renamed from: a */
    public static String m1614a(String str) {
        if (an.m1657a(str)) {
            ac.m1586d();
            return f975c;
        }
        if (!str.startsWith(f981z[27])) {
            str = new StringBuilder(f981z[27]).append(str).toString();
        }
        if (!str.endsWith(f977e)) {
            str = str + f977e;
        }
        f975c = str;
        new StringBuilder(f981z[26]).append(f975c);
        ac.m1576a();
        return f975c;
    }

    /* renamed from: a */
    public static String m1615a(String str, int i) {
        if (an.m1657a(str)) {
            ac.m1581b();
            return null;
        }
        String d = m1634d(str);
        long x = C0404a.m1144x();
        if (x == 0) {
            ac.m1581b();
            return null;
        }
        d = C0490b.m1686b(x + C0490b.m1686b(C0404a.m1046A()) + d);
        return !an.m1657a(d) ? x + ":" + d : null;
    }

    /* renamed from: a */
    private static ArrayList<JSONArray> m1616a(JSONArray jSONArray, int i) {
        UnsupportedEncodingException unsupportedEncodingException;
        UnsupportedEncodingException unsupportedEncodingException2;
        JSONArray jSONArray2;
        ArrayList<JSONArray> arrayList = new ArrayList();
        JSONArray jSONArray3 = new JSONArray();
        if (jSONArray != null && jSONArray.length() > 0) {
            int length = jSONArray.length() - 1;
            int i2 = 0;
            int i3 = 0;
            while (length >= 0) {
                int i4;
                JSONArray jSONArray4;
                int i5;
                int i6;
                JSONObject optJSONObject = jSONArray.optJSONObject(length);
                if (optJSONObject != null) {
                    try {
                        int length2 = optJSONObject.toString().getBytes(f981z[0]).length;
                        i4 = i3 + length2;
                        if (i4 > 204800 && length > 1) {
                            if (length > 1) {
                                return arrayList;
                            }
                            if (length == 1) {
                                try {
                                    jSONArray3.put(optJSONObject);
                                    arrayList.add(jSONArray3);
                                    return arrayList;
                                } catch (UnsupportedEncodingException e) {
                                    unsupportedEncodingException = e;
                                    jSONArray4 = jSONArray3;
                                    i5 = i4;
                                    i4 = i2;
                                }
                            }
                        }
                        if (i2 + length2 > 20480) {
                            try {
                                arrayList.add(jSONArray3);
                                jSONArray4 = new JSONArray();
                            } catch (UnsupportedEncodingException e2) {
                                unsupportedEncodingException2 = e2;
                                jSONArray4 = jSONArray3;
                                i5 = i4;
                                i4 = length2;
                                unsupportedEncodingException = unsupportedEncodingException2;
                                unsupportedEncodingException.getMessage();
                                ac.m1588e();
                                length--;
                                i2 = i4;
                                i6 = i5;
                                jSONArray3 = jSONArray4;
                                i3 = i6;
                            }
                            try {
                                jSONArray4.put(optJSONObject);
                                i5 = i4;
                                i4 = length2;
                            } catch (UnsupportedEncodingException e3) {
                                unsupportedEncodingException2 = e3;
                                i5 = i4;
                                i4 = length2;
                                unsupportedEncodingException = unsupportedEncodingException2;
                                unsupportedEncodingException.getMessage();
                                ac.m1588e();
                                length--;
                                i2 = i4;
                                i6 = i5;
                                jSONArray3 = jSONArray4;
                                i3 = i6;
                            }
                        } else {
                            i3 = i2 + length2;
                            try {
                                jSONArray3.put(optJSONObject);
                                jSONArray2 = jSONArray3;
                                i5 = i4;
                                i4 = i3;
                                jSONArray4 = jSONArray2;
                            } catch (UnsupportedEncodingException e4) {
                                unsupportedEncodingException = e4;
                                jSONArray2 = jSONArray3;
                                i5 = i4;
                                i4 = i3;
                                jSONArray4 = jSONArray2;
                                unsupportedEncodingException.getMessage();
                                ac.m1588e();
                                length--;
                                i2 = i4;
                                i6 = i5;
                                jSONArray3 = jSONArray4;
                                i3 = i6;
                            }
                        }
                    } catch (UnsupportedEncodingException e5) {
                        unsupportedEncodingException = e5;
                        i4 = i2;
                        i6 = i3;
                        jSONArray4 = jSONArray3;
                        i5 = i6;
                        unsupportedEncodingException.getMessage();
                        ac.m1588e();
                        length--;
                        i2 = i4;
                        i6 = i5;
                        jSONArray3 = jSONArray4;
                        i3 = i6;
                    }
                } else {
                    i4 = i2;
                    i6 = i3;
                    jSONArray4 = jSONArray3;
                    i5 = i6;
                }
                length--;
                i2 = i4;
                i6 = i5;
                jSONArray3 = jSONArray4;
                i3 = i6;
            }
            if (jSONArray3.length() > 0) {
                arrayList.add(jSONArray3);
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public static JSONObject m1617a(Context context, String str) {
        Closeable openFileInput;
        FileNotFoundException e;
        Throwable th;
        IOException e2;
        if (str == null || str.length() <= 0) {
            ac.m1581b();
            return null;
        }
        String e3 = m1635e(str);
        if (context == null) {
            new StringBuilder(f981z[11]).append(e3);
            ac.m1581b();
            return null;
        }
        try {
            openFileInput = context.openFileInput(str);
            try {
                byte[] bArr = new byte[(openFileInput.available() + 1)];
                openFileInput.read(bArr);
                m1624a(openFileInput);
                try {
                    String str2 = new String(bArr, f981z[0]);
                    if (!an.m1657a(str2)) {
                        return new JSONObject(str2);
                    }
                    new StringBuilder().append(e3).append(f981z[13]);
                    ac.m1581b();
                    return null;
                } catch (UnsupportedEncodingException e4) {
                    new StringBuilder(f981z[15]).append(e3).append(f981z[17]).append(e4.getMessage());
                    ac.m1581b();
                    return null;
                } catch (JSONException e5) {
                    new StringBuilder(f981z[16]).append(e3).append(f981z[12]).append(e5.getMessage());
                    ac.m1581b();
                    return null;
                }
            } catch (FileNotFoundException e6) {
                e = e6;
                try {
                    new StringBuilder(f981z[14]).append(e3).append(f981z[10]).append(e.getMessage());
                    ac.m1581b();
                    m1624a(openFileInput);
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    m1624a(openFileInput);
                    throw th;
                }
            } catch (IOException e7) {
                e2 = e7;
                new StringBuilder(f981z[18]).append(e3).append(f981z[17]).append(e2.getMessage());
                ac.m1581b();
                m1624a(openFileInput);
                return null;
            }
        } catch (FileNotFoundException e8) {
            e = e8;
            openFileInput = null;
            new StringBuilder(f981z[14]).append(e3).append(f981z[10]).append(e.getMessage());
            ac.m1581b();
            m1624a(openFileInput);
            return null;
        } catch (IOException e9) {
            e2 = e9;
            openFileInput = null;
            new StringBuilder(f981z[18]).append(e3).append(f981z[17]).append(e2.getMessage());
            ac.m1581b();
            m1624a(openFileInput);
            return null;
        } catch (Throwable th3) {
            openFileInput = null;
            th = th3;
            m1624a(openFileInput);
            throw th;
        }
    }

    /* renamed from: a */
    public static void m1618a(Context context) {
        ac.m1581b();
        m1625a(context, f981z[6], null);
        m1628b(context);
    }

    /* renamed from: a */
    public static void m1619a(Context context, int i) {
        int i2 = 0;
        if (f973a != null) {
            JSONObject jSONObject = f973a;
            if (i >= 204800) {
                m1628b(context);
                return;
            }
            int length;
            try {
                length = jSONObject.toString().getBytes(f981z[9]).length;
            } catch (UnsupportedEncodingException e) {
                length = 0;
            }
            int i3 = (length + i) - 204800;
            if (i3 > 0) {
                JSONArray optJSONArray = jSONObject.optJSONArray(f981z[7]);
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    try {
                        JSONObject jSONObject2;
                        JSONArray jSONArray = new JSONArray();
                        for (length = 0; length < optJSONArray.length(); length++) {
                            JSONObject jSONObject3 = optJSONArray.getJSONObject(length);
                            if (jSONObject3 != null) {
                                if (i2 >= i3) {
                                    jSONArray.put(jSONObject3);
                                }
                                i2 += jSONObject3.toString().getBytes(f981z[9]).length;
                            }
                        }
                        if (jSONArray.length() > 0) {
                            jSONObject.put(f981z[7], jSONArray);
                            jSONObject2 = jSONObject;
                        } else {
                            jSONObject2 = null;
                        }
                        f973a = jSONObject2;
                        m1625a(context, f981z[8], jSONObject2);
                    } catch (JSONException e2) {
                    } catch (UnsupportedEncodingException e3) {
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public static void m1620a(Context context, JSONArray jSONArray) {
        if (context == null) {
            ac.m1581b();
        } else if (jSONArray == null || jSONArray.length() <= 0) {
            ac.m1581b();
        } else {
            f979g.execute(new ai(context, jSONArray));
        }
    }

    /* renamed from: a */
    public static void m1621a(Context context, JSONObject jSONObject) {
        m1620a(context, new JSONArray().put(jSONObject));
    }

    /* renamed from: a */
    private static void m1622a(Context context, JSONObject jSONObject, ArrayList<JSONArray> arrayList) {
        if (arrayList.size() <= 0) {
            m1628b(context);
        }
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < arrayList.size(); i++) {
            JSONArray jSONArray2 = (JSONArray) arrayList.get(i);
            for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                if (jSONArray2.optJSONObject(i2) != null) {
                    jSONArray.put(jSONArray2.optJSONObject(i2));
                }
            }
        }
        try {
            jSONObject.put(f981z[7], jSONArray);
        } catch (JSONException e) {
        }
        f973a = jSONObject;
        m1625a(context, f981z[8], jSONObject);
    }

    /* renamed from: a */
    private static void m1623a(Context context, JSONObject jSONObject, JSONArray jSONArray, ArrayList<JSONArray> arrayList) {
        if (arrayList.size() == 1) {
            m1628b(context);
        } else if (jSONArray != null && arrayList.size() > 1) {
            arrayList.remove(jSONArray);
            JSONArray jSONArray2 = new JSONArray();
            for (int i = 0; i < arrayList.size(); i++) {
                JSONArray jSONArray3 = (JSONArray) arrayList.get(i);
                for (int i2 = 0; i2 < jSONArray3.length(); i2++) {
                    if (jSONArray3.optJSONObject(i2) != null) {
                        jSONArray2.put(jSONArray3.optJSONObject(i2));
                    }
                }
            }
            try {
                jSONObject.put(f981z[7], jSONArray2);
            } catch (JSONException e) {
            }
            f973a = jSONObject;
            m1625a(context, f981z[8], jSONObject);
        }
    }

    /* renamed from: a */
    public static void m1624a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    public static boolean m1625a(android.content.Context r8, java.lang.String r9, org.json.JSONObject r10) {
        /*
        r2 = 1;
        r1 = 0;
        r0 = cn.jpush.android.util.an.m1657a(r9);
        if (r0 == 0) goto L_0x000d;
    L_0x0008:
        cn.jpush.android.util.ac.m1581b();
        r0 = r1;
    L_0x000c:
        return r0;
    L_0x000d:
        r4 = m1635e(r9);
        if (r8 != 0) goto L_0x0026;
    L_0x0013:
        r0 = new java.lang.StringBuilder;
        r2 = f981z;
        r3 = 40;
        r2 = r2[r3];
        r0.<init>(r2);
        r0.append(r4);
        cn.jpush.android.util.ac.m1581b();
        r0 = r1;
        goto L_0x000c;
    L_0x0026:
        r5 = f980h;
        monitor-enter(r5);
        r0 = "";
        if (r10 == 0) goto L_0x0053;
    L_0x002d:
        r0 = r10.toString();	 Catch:{ all -> 0x0070 }
        r3 = f981z;	 Catch:{ all -> 0x0070 }
        r6 = 8;
        r3 = r3[r6];	 Catch:{ all -> 0x0070 }
        r3 = r9.equals(r3);	 Catch:{ all -> 0x0070 }
        if (r3 == 0) goto L_0x0053;
    L_0x003d:
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x006b }
        r6 = f981z;	 Catch:{ Exception -> 0x006b }
        r7 = 34;
        r6 = r6[r7];	 Catch:{ Exception -> 0x006b }
        r3.<init>(r6);	 Catch:{ Exception -> 0x006b }
        r6 = 1;
        r6 = r10.toString(r6);	 Catch:{ Exception -> 0x006b }
        r3.append(r6);	 Catch:{ Exception -> 0x006b }
        cn.jpush.android.util.ac.m1576a();	 Catch:{ Exception -> 0x006b }
    L_0x0053:
        r3 = 0;
        r6 = 0;
        r3 = r8.openFileOutput(r9, r6);	 Catch:{ FileNotFoundException -> 0x0073, UnsupportedEncodingException -> 0x009f, IOException -> 0x00ca, NullPointerException -> 0x00f5 }
        r6 = f981z;	 Catch:{ FileNotFoundException -> 0x0128, UnsupportedEncodingException -> 0x009f, IOException -> 0x00ca, NullPointerException -> 0x00f5 }
        r7 = 0;
        r6 = r6[r7];	 Catch:{ FileNotFoundException -> 0x0128, UnsupportedEncodingException -> 0x009f, IOException -> 0x00ca, NullPointerException -> 0x00f5 }
        r0 = r0.getBytes(r6);	 Catch:{ FileNotFoundException -> 0x0128, UnsupportedEncodingException -> 0x009f, IOException -> 0x00ca, NullPointerException -> 0x00f5 }
        r3.write(r0);	 Catch:{ FileNotFoundException -> 0x0128, UnsupportedEncodingException -> 0x009f, IOException -> 0x00ca, NullPointerException -> 0x00f5 }
        m1624a(r3);	 Catch:{ all -> 0x0070 }
        monitor-exit(r5);	 Catch:{ all -> 0x0070 }
        r0 = r2;
        goto L_0x000c;
    L_0x006b:
        r3 = move-exception;
        cn.jpush.android.util.ac.m1592h();	 Catch:{ all -> 0x0070 }
        goto L_0x0053;
    L_0x0070:
        r0 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x0070 }
        throw r0;
    L_0x0073:
        r0 = move-exception;
        r2 = r3;
    L_0x0075:
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0125 }
        r6 = f981z;	 Catch:{ all -> 0x0125 }
        r7 = 14;
        r6 = r6[r7];	 Catch:{ all -> 0x0125 }
        r3.<init>(r6);	 Catch:{ all -> 0x0125 }
        r3 = r3.append(r4);	 Catch:{ all -> 0x0125 }
        r4 = f981z;	 Catch:{ all -> 0x0125 }
        r6 = 39;
        r4 = r4[r6];	 Catch:{ all -> 0x0125 }
        r3 = r3.append(r4);	 Catch:{ all -> 0x0125 }
        r0 = r0.getMessage();	 Catch:{ all -> 0x0125 }
        r3.append(r0);	 Catch:{ all -> 0x0125 }
        cn.jpush.android.util.ac.m1581b();	 Catch:{ all -> 0x0125 }
        m1624a(r2);	 Catch:{ all -> 0x0070 }
        monitor-exit(r5);	 Catch:{ all -> 0x0070 }
        r0 = r1;
        goto L_0x000c;
    L_0x009f:
        r0 = move-exception;
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0120 }
        r6 = f981z;	 Catch:{ all -> 0x0120 }
        r7 = 15;
        r6 = r6[r7];	 Catch:{ all -> 0x0120 }
        r2.<init>(r6);	 Catch:{ all -> 0x0120 }
        r2 = r2.append(r4);	 Catch:{ all -> 0x0120 }
        r4 = f981z;	 Catch:{ all -> 0x0120 }
        r6 = 36;
        r4 = r4[r6];	 Catch:{ all -> 0x0120 }
        r2 = r2.append(r4);	 Catch:{ all -> 0x0120 }
        r0 = r0.getMessage();	 Catch:{ all -> 0x0120 }
        r2.append(r0);	 Catch:{ all -> 0x0120 }
        cn.jpush.android.util.ac.m1581b();	 Catch:{ all -> 0x0120 }
        m1624a(r3);	 Catch:{ all -> 0x0070 }
        monitor-exit(r5);	 Catch:{ all -> 0x0070 }
        r0 = r1;
        goto L_0x000c;
    L_0x00ca:
        r0 = move-exception;
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0120 }
        r6 = f981z;	 Catch:{ all -> 0x0120 }
        r7 = 35;
        r6 = r6[r7];	 Catch:{ all -> 0x0120 }
        r2.<init>(r6);	 Catch:{ all -> 0x0120 }
        r2 = r2.append(r4);	 Catch:{ all -> 0x0120 }
        r4 = f981z;	 Catch:{ all -> 0x0120 }
        r6 = 36;
        r4 = r4[r6];	 Catch:{ all -> 0x0120 }
        r2 = r2.append(r4);	 Catch:{ all -> 0x0120 }
        r0 = r0.getMessage();	 Catch:{ all -> 0x0120 }
        r2.append(r0);	 Catch:{ all -> 0x0120 }
        cn.jpush.android.util.ac.m1581b();	 Catch:{ all -> 0x0120 }
        m1624a(r3);	 Catch:{ all -> 0x0070 }
        monitor-exit(r5);	 Catch:{ all -> 0x0070 }
        r0 = r1;
        goto L_0x000c;
    L_0x00f5:
        r0 = move-exception;
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0120 }
        r6 = f981z;	 Catch:{ all -> 0x0120 }
        r7 = 38;
        r6 = r6[r7];	 Catch:{ all -> 0x0120 }
        r2.<init>(r6);	 Catch:{ all -> 0x0120 }
        r2 = r2.append(r4);	 Catch:{ all -> 0x0120 }
        r4 = f981z;	 Catch:{ all -> 0x0120 }
        r6 = 37;
        r4 = r4[r6];	 Catch:{ all -> 0x0120 }
        r2 = r2.append(r4);	 Catch:{ all -> 0x0120 }
        r0 = r0.getMessage();	 Catch:{ all -> 0x0120 }
        r2.append(r0);	 Catch:{ all -> 0x0120 }
        cn.jpush.android.util.ac.m1581b();	 Catch:{ all -> 0x0120 }
        m1624a(r3);	 Catch:{ all -> 0x0070 }
        monitor-exit(r5);	 Catch:{ all -> 0x0070 }
        r0 = r1;
        goto L_0x000c;
    L_0x0120:
        r0 = move-exception;
    L_0x0121:
        m1624a(r3);	 Catch:{ all -> 0x0070 }
        throw r0;	 Catch:{ all -> 0x0070 }
    L_0x0125:
        r0 = move-exception;
        r3 = r2;
        goto L_0x0121;
    L_0x0128:
        r0 = move-exception;
        r2 = r3;
        goto L_0x0075;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.ah.a(android.content.Context, java.lang.String, org.json.JSONObject):boolean");
    }

    /* renamed from: a */
    private static boolean m1626a(JSONObject jSONObject, Context context) {
        jSONObject.put(f981z[2], "a");
        long x = C0404a.m1144x();
        if (x == 0) {
            ac.m1588e();
            return false;
        }
        jSONObject.put(f981z[4], x);
        String q = C0490b.m1732q(context);
        if (an.m1657a(q)) {
            ac.m1588e();
            return false;
        }
        jSONObject.put(f981z[1], q);
        jSONObject.put(f981z[3], f981z[5]);
        return true;
    }

    /* renamed from: b */
    private static String m1627b() {
        if (an.m1657a(f975c)) {
            m1614a(C0404a.m1141u());
        }
        return f975c;
    }

    /* renamed from: b */
    private static void m1628b(Context context) {
        f973a = null;
        if (!m1625a(context, f981z[8], null)) {
            try {
                if (context.deleteFile(m1635e(f981z[8]))) {
                    new StringBuilder(f981z[22]).append(m1635e(f981z[8]));
                    ac.m1588e();
                }
            } catch (IllegalArgumentException e) {
                new StringBuilder(f981z[23]).append(e.getMessage());
                ac.m1588e();
            } catch (Exception e2) {
                new StringBuilder(f981z[23]).append(e2.getMessage());
                ac.m1588e();
            }
        }
    }

    /* renamed from: b */
    static /* synthetic */ void m1629b(Context context, JSONArray jSONArray) {
        int i = 0;
        String str = f981z[8];
        if (f973a == null) {
            f973a = m1617a(context, str);
        }
        JSONObject jSONObject = f973a;
        JSONObject jSONObject2 = jSONObject == null ? new JSONObject() : jSONObject;
        JSONArray optJSONArray = jSONObject2.optJSONArray(f981z[7]);
        JSONArray jSONArray2 = optJSONArray == null ? new JSONArray() : optJSONArray;
        if (jSONArray != null) {
            try {
                if (jSONArray.length() > 0) {
                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                        jSONArray2.put(jSONArray.get(i2));
                    }
                }
            } catch (JSONException e) {
            }
        }
        if (!C0490b.m1691b(context)) {
            jSONObject2.put(f981z[7], jSONArray2);
            m1625a(context, f981z[8], jSONObject2);
            return;
        }
        if (jSONArray2.length() > 0) {
            ArrayList a = m1616a(jSONArray2, 20480);
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(a);
            try {
                new StringBuilder(f981z[33]).append(jSONArray2.toString().getBytes(f981z[9]).length);
                ac.m1581b();
            } catch (UnsupportedEncodingException e2) {
            }
            new StringBuilder(f981z[29]).append(a.size()).append(f981z[31]);
            ac.m1581b();
            int i3 = 0;
            while (i < a.size()) {
                optJSONArray = (JSONArray) a.get(i);
                if (i3 == 0) {
                    if (optJSONArray.length() <= 0) {
                        arrayList.remove(optJSONArray);
                    } else {
                        try {
                            jSONObject2.put(f981z[7], optJSONArray);
                            try {
                                if (m1626a(jSONObject2, context)) {
                                    if (jSONObject2 != null) {
                                        try {
                                            new StringBuilder(f981z[28]).append(jSONObject2.toString(1));
                                            ac.m1584c();
                                        } catch (JSONException e3) {
                                            new StringBuilder(f981z[28]).append(jSONObject2.toString());
                                            ac.m1584c();
                                        }
                                    }
                                    switch (C0506s.m1796a(context, jSONObject2, true)) {
                                        case -5:
                                        case -4:
                                        case -3:
                                        case -2:
                                            m1623a(context, jSONObject2, optJSONArray, arrayList);
                                            break;
                                        case -1:
                                        case 404:
                                        case 429:
                                        case 500:
                                            m1622a(context, jSONObject2, arrayList);
                                            break;
                                        case 200:
                                            m1623a(context, jSONObject2, optJSONArray, arrayList);
                                            break;
                                        case HttpStatus.SC_UNAUTHORIZED /*401*/:
                                            m1628b(context);
                                            boolean z = true;
                                            break;
                                        default:
                                            break;
                                    }
                                }
                                m1633c(context, jSONObject2);
                                return;
                            } catch (Exception e4) {
                                new StringBuilder(f981z[30]).append(e4);
                                ac.m1581b();
                                m1633c(context, jSONObject2);
                                return;
                            }
                        } catch (JSONException e5) {
                            new StringBuilder(f981z[32]).append(e5);
                            ac.m1581b();
                            m1623a(context, jSONObject2, optJSONArray, arrayList);
                        }
                    }
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    /* renamed from: b */
    public static void m1630b(Context context, JSONObject jSONObject) {
        try {
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(jSONObject);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(f981z[7], jSONArray);
            if (m1626a(jSONObject2, context)) {
                new StringBuilder(f981z[19]).append(jSONObject.toString());
                ac.m1581b();
                if (C0506s.m1796a(context, jSONObject2, true) == 200) {
                    ac.m1581b();
                }
            }
        } catch (JSONException e) {
        } catch (Exception e2) {
        }
    }

    /* renamed from: b */
    public static boolean m1631b(String str) {
        f975c = m1627b();
        return (an.m1657a(str) || an.m1657a(f975c)) ? false : str.equals(f975c);
    }

    /* renamed from: c */
    public static String m1632c(String str) {
        String str2 = null;
        try {
            str2 = C0493e.m1749a(str.getBytes(), 10);
        } catch (Exception e) {
            ac.m1588e();
        }
        return str2;
    }

    /* renamed from: c */
    private static void m1633c(Context context, JSONObject jSONObject) {
        f973a = jSONObject;
        m1625a(context, f981z[8], jSONObject);
    }

    /* renamed from: d */
    private static String m1634d(String str) {
        String str2 = null;
        try {
            byte[] bytes = str.getBytes(f981z[0]);
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            OutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bytes);
            gZIPOutputStream.close();
            bytes = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            str2 = C0490b.m1672a(bytes);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return str2;
    }

    /* renamed from: e */
    private static String m1635e(String str) {
        if (!an.m1657a(str)) {
            return str.equals(f981z[8]) ? f981z[21] : f981z[20];
        } else {
            ac.m1581b();
            return null;
        }
    }
}
