package cn.jpush.android.p002a;

import android.content.Context;
import cn.jpush.android.C0404a;
import cn.jpush.android.util.C0490b;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.ah;
import cn.jpush.android.util.an;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: cn.jpush.android.a.i */
public final class C0400i extends C0395d {
    /* renamed from: B */
    private static final String[] f475B;
    /* renamed from: e */
    private Context f476e;
    /* renamed from: f */
    private String f477f;
    /* renamed from: g */
    private boolean f478g;
    /* renamed from: h */
    private boolean f479h;
    /* renamed from: i */
    private boolean f480i;
    /* renamed from: j */
    private String f481j = null;
    /* renamed from: k */
    private String f482k = null;
    /* renamed from: l */
    private String f483l = null;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 18;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "\u0017\u0002+|";
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
            case 0: goto L_0x00e4;
            case 1: goto L_0x00e8;
            case 2: goto L_0x00ec;
            case 3: goto L_0x00f0;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 46;
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
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = "\r\u0005+uK";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "\u0007\u0014.t";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "\b\u001e!yB;\u0015,k";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\u0007\u0014.tq\u0010\u001e5}\\\u0017";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "(\u001e!yZ\r\u001e,\"\u000e";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "\n\u00146oA\u0016\u001a\u001dlW\u0014\u0014";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "\u0010\b2}";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = "\u0013\u0018$qq\u0010\u001e5}\\\u0017";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        r2 = 9;
        r1 = "\u0003\u00011";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0087:
        r3[r2] = r1;
        r2 = 10;
        r1 = "\u0003\u00011YJ\u0000\u0003'k]^";
        r0 = 9;
        r3 = r4;
        goto L_0x0009;
    L_0x0092:
        r3[r2] = r1;
        r2 = 11;
        r1 = "3\u0018$q\u000e\b\u0014,Z\fKb";
        r0 = 10;
        r3 = r4;
        goto L_0x0009;
    L_0x009d:
        r3[r2] = r1;
        r2 = 12;
        r1 = "\u0005\u001d.";
        r0 = 11;
        r3 = r4;
        goto L_0x0009;
    L_0x00a8:
        r3[r2] = r1;
        r2 = 13;
        r1 = "\b\u001e!GI\u0014\u0002";
        r0 = 12;
        r3 = r4;
        goto L_0x0009;
    L_0x00b3:
        r3[r2] = r1;
        r2 = 14;
        r1 = "\b\u001e!GG\n\u0017-";
        r0 = 13;
        r3 = r4;
        goto L_0x0009;
    L_0x00be:
        r3[r2] = r1;
        r2 = 15;
        r1 = "\b\u001e!GY\r\u0017+";
        r0 = 14;
        r3 = r4;
        goto L_0x0009;
    L_0x00c9:
        r3[r2] = r1;
        r2 = 16;
        r1 = "\b\u001e!GM\u0001\u001d.";
        r0 = 15;
        r3 = r4;
        goto L_0x0009;
    L_0x00d4:
        r3[r2] = r1;
        r2 = 17;
        r1 = "\u0013\u0018$q";
        r0 = 16;
        r3 = r4;
        goto L_0x0009;
    L_0x00df:
        r3[r2] = r1;
        f475B = r4;
        return;
    L_0x00e4:
        r9 = 100;
        goto L_0x0020;
    L_0x00e8:
        r9 = 113; // 0x71 float:1.58E-43 double:5.6E-322;
        goto L_0x0020;
    L_0x00ec:
        r9 = 66;
        goto L_0x0020;
    L_0x00f0:
        r9 = 24;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.a.i.<clinit>():void");
    }

    public C0400i(Context context, boolean z, String str, boolean z2, boolean z3) {
        super(context, str, z2, z3);
        this.f476e = context;
        this.f477f = str;
        this.f478g = z2;
        this.f479h = z3;
        this.f480i = z;
    }

    /* renamed from: a */
    private boolean m1025a(JSONArray jSONArray, JSONArray jSONArray2, String str) {
        if (an.m1657a(str)) {
            if (!an.m1657a(this.f483l)) {
                return false;
            }
        } else if (!str.equals(this.f483l)) {
            return false;
        }
        if (an.m1657a(this.f482k)) {
            if (!(jSONArray2 == null || jSONArray2.length() == 0)) {
                return false;
            }
        } else if (jSONArray2 == null) {
            return false;
        } else {
            if (jSONArray2.length() == 0) {
                return false;
            }
            if (!this.f482k.equals(jSONArray2.toString())) {
                return false;
            }
            ac.m1584c();
        }
        if (an.m1657a(this.f481j)) {
            if (!(jSONArray == null || jSONArray.length() == 0)) {
                return false;
            }
        } else if (jSONArray == null) {
            return false;
        } else {
            if (jSONArray.length() == 0) {
                return false;
            }
            try {
                String optString = ((JSONObject) jSONArray.get(0)).optString(f475B[0]);
                if (!(an.m1657a(optString) || optString.equals(this.f481j))) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: d */
    public final void mo2194d() {
        try {
            if (this.f480i) {
                JSONObject a;
                if (this.f477f.equals(f475B[4])) {
                    if (C0404a.m1145y()) {
                        a = C0490b.m1674a(f475B[16], m1011b());
                        if (a != null && a.length() > 0) {
                            ah.m1621a(this.f476e, a);
                            new StringBuilder(f475B[5]).append(a);
                            ac.m1584c();
                        }
                    }
                } else if (this.f477f.equals(f475B[8])) {
                    if (C0404a.m1145y()) {
                        a = C0490b.m1674a(f475B[15], m1013c());
                        if (a != null && a.length() > 0) {
                            ah.m1621a(this.f476e, a);
                            new StringBuilder(f475B[11]).append(a.toString().getBytes().length);
                            ac.m1584c();
                            new StringBuilder(f475B[5]).append(a);
                            ac.m1584c();
                        }
                    }
                } else if (this.f477f.equals(f475B[9])) {
                    if (C0404a.m1145y()) {
                        String a2 = m1009a();
                        if (!(a2 == null || "".equals(a2))) {
                            try {
                                JSONObject jSONObject = new JSONObject(a2);
                                r0 = new JSONArray();
                                r0.put(jSONObject);
                                a = C0490b.m1674a(f475B[13], r0);
                                if (a != null && a.length() > 0) {
                                    ah.m1621a(this.f476e, a);
                                    new StringBuilder(f475B[5]).append(a);
                                    ac.m1584c();
                                }
                            } catch (JSONException e) {
                                e.getMessage();
                                ac.m1588e();
                            }
                        }
                    }
                } else if (this.f477f.equals(f475B[12]) && C0404a.m1145y()) {
                    JSONArray c = m1013c();
                    JSONArray b = m1011b();
                    r0 = new JSONArray();
                    String a3 = m1009a();
                    new StringBuilder(f475B[10]).append(a3);
                    ac.m1581b();
                    if (m1025a(c, b, a3)) {
                        ac.m1584c();
                    } else {
                        JSONArray jSONArray;
                        if (a3 == null || "".equals(a3)) {
                            jSONArray = r0;
                        } else {
                            try {
                                r0.put(new JSONObject(a3));
                                jSONArray = r0;
                            } catch (Exception e2) {
                                jSONArray = null;
                            }
                        }
                        JSONObject jSONObject2 = new JSONObject();
                        try {
                            jSONObject2.put(f475B[7], f475B[14]);
                            jSONObject2.put(f475B[1], C0404a.m1126m());
                            jSONObject2.put(f475B[6], C0490b.m1696c(this.f476e));
                            jSONObject2.put(f475B[3], C0490b.m1699d());
                            if (c != null && c.length() > 0) {
                                jSONObject2.put(f475B[17], c);
                                this.f481j = ((JSONObject) c.get(0)).optString(f475B[0]);
                            }
                            if (b != null && b.length() > 0) {
                                jSONObject2.put(f475B[2], b);
                                this.f482k = b.toString();
                            }
                            if (jSONArray != null && jSONArray.length() > 0) {
                                jSONObject2.put(f475B[9], jSONArray);
                                this.f483l = a3;
                            }
                            ah.m1621a(this.f476e, jSONObject2);
                        } catch (JSONException e3) {
                        }
                    }
                }
                m1017g();
            }
        } catch (Exception e4) {
            ac.m1593i();
        } finally {
            m1017g();
        }
    }
}
