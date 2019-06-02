package cn.jpush.android.helpers;

import android.content.Context;
import android.text.TextUtils;
import cn.jpush.android.data.C0429c;
import cn.jpush.android.data.C0430a;
import cn.jpush.android.data.C0432d;
import cn.jpush.android.data.C0437i;
import cn.jpush.android.data.C0441m;
import cn.jpush.android.data.C0446r;
import cn.jpush.android.data.C0447s;
import cn.jpush.android.util.C0490b;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.an;
import java.util.LinkedList;
import java.util.Queue;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: cn.jpush.android.helpers.f */
public final class C0456f {
    /* renamed from: a */
    private static Queue<C0432d> f776a = new LinkedList();
    /* renamed from: z */
    private static final String[] f777z;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 27;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "\u0012cm\u001f^";
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
            case 0: goto L_0x014e;
            case 1: goto L_0x0152;
            case 2: goto L_0x0156;
            case 3: goto L_0x015a;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 59;
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
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = "(E9>h!C]";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "\u0003rm\u0001Z\u0015";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "(EF>h!C]";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\u0007nF\u001a_";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "\bUv\u001dW\u001f";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "\u000by~,R\u0002";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "\u0016x|#Z\u0014y|<I\u000fmp\u001dZ\nGj\u0014v\u0003yj\u0012\\\u0003*t\u0000\\/n9N\u001b";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = "\u0015bv\u0004d\u0012si\u0016";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        r2 = 9;
        r1 = "\bem\u001a]\u000fix\u001aT\bUm\nK\u0003";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0087:
        r3[r2] = r1;
        r2 = 10;
        r1 = "\u0005ew\u0007^\b~F\u0007B\u0016o";
        r0 = 9;
        r3 = r4;
        goto L_0x0009;
    L_0x0092:
        r3[r2] = r1;
        r2 = 11;
        r1 = "\u0007im\u001aT\b0i\u0001^6kk\u0000^)xp\u0014R\bku>H\u0001G|\u0000H\u0007m|S\u0016Fek\u001a\\\u000fdx\u001fq\u0015ewI1";
        r0 = 10;
        r3 = r4;
        goto L_0x0009;
    L_0x009d:
        r3[r2] = r1;
        r2 = 12;
        r1 = "\t||\u0001I\u000fn|,V\u0015mF\u001a_";
        r0 = 11;
        r3 = r4;
        goto L_0x0009;
    L_0x00a8:
        r3[r2] = r1;
        r2 = 13;
        r1 = "\u000boj\u0000Z\u0001o";
        r0 = 12;
        r3 = r4;
        goto L_0x0009;
    L_0x00b3:
        r3[r2] = r1;
        r2 = 14;
        r1 = "(_U?\u001b\u0005ew\u0007^\u001e~";
        r0 = 13;
        r3 = r4;
        goto L_0x0009;
    L_0x00be:
        r3[r2] = r1;
        r2 = 15;
        r1 = "\bU{\u0006R\nn|\u0001d\u000fn";
        r0 = 14;
        r3 = r4;
        goto L_0x0009;
    L_0x00c9:
        r3[r2] = r1;
        r2 = 16;
        r1 = "\u0007nF\u0007";
        r0 = 15;
        r3 = r4;
        goto L_0x0009;
    L_0x00d4:
        r3[r2] = r1;
        r2 = 17;
        r1 = "3dr\u001dT\u0011d9>h!*i\u0001T\u0012ez\u001cWF||\u0001H\u000few]\u001b!co\u0016\u001b\u0013z9^\u001b";
        r0 = 16;
        r3 = r4;
        goto L_0x0009;
    L_0x00df:
        r3[r2] = r1;
        r2 = 18;
        r1 = "\u000bUz\u001cU\u0012ow\u0007";
        r0 = 17;
        r3 = r4;
        goto L_0x0009;
    L_0x00ea:
        r3[r2] = r1;
        r2 = 19;
        r1 = "3dr\u001dT\u0011*t\u0000\\F~`\u0003^Fk},OF79";
        r0 = 18;
        r3 = r4;
        goto L_0x0009;
    L_0x00f5:
        r3[r2] = r1;
        r2 = 20;
        r1 = "1C_:";
        r0 = 19;
        r3 = r4;
        goto L_0x0009;
    L_0x0100:
        r3[r2] = r1;
        r2 = 21;
        r1 = "8Qq\u0007O\u0016vq\u0007O\u0016yDX\u0001I%7Y";
        r0 = 20;
        r3 = r4;
        goto L_0x0009;
    L_0x010b:
        r3[r2] = r1;
        r2 = 22;
        r1 = "/do\u0012W\u000fn9\u0006I\n*4S";
        r0 = 21;
        r3 = r4;
        goto L_0x0009;
    L_0x0116:
        r3[r2] = r1;
        r2 = 23;
        r1 = " kp\u001f^\u0002*m\u001c\u001b\u0001omSQ\u0015ewS]\u0014etSN\u0014f9\u0011^\u0005kl\u0000^\tl9\u001aU\u0010ku\u001a_Fk\u001f\u001bK*";
        r0 = 22;
        r3 = r4;
        goto L_0x0009;
    L_0x0121:
        r3[r2] = r1;
        r2 = 24;
        r1 = "\u0007im\u001aT\b0u\u001cZ\u0002Gj\u0014q\u0015ew5I\tgL\u0001WF'9";
        r0 = 23;
        r3 = r4;
        goto L_0x0009;
    L_0x012c:
        r3[r2] = r1;
        r2 = 25;
        r1 = "\u0007im\u001aT\b0i\u0012I\u0015oV\u0001R\u0001cw\u0012W+y~>^\u0015yx\u0014^F'9\u001cI\u000fmp\u001dZ\n@j\u001cU\\\u0000";
        r0 = 24;
        r3 = r4;
        goto L_0x0009;
    L_0x0137:
        r3[r2] = r1;
        r2 = 26;
        r1 = "3dr\u001dT\u0011*t\u0000\\F~`\u0003^F'9";
        r0 = 25;
        r3 = r4;
        goto L_0x0009;
    L_0x0142:
        r3[r2] = r1;
        f777z = r4;
        r0 = new java.util.LinkedList;
        r0.<init>();
        f776a = r0;
        return;
    L_0x014e:
        r9 = 102; // 0x66 float:1.43E-43 double:5.04E-322;
        goto L_0x0020;
    L_0x0152:
        r9 = 10;
        goto L_0x0020;
    L_0x0156:
        r9 = 25;
        goto L_0x0020;
    L_0x015a:
        r9 = 115; // 0x73 float:1.61E-43 double:5.7E-322;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.helpers.f.<clinit>():void");
    }

    /* renamed from: a */
    public static C0430a m1397a(Context context, String str, String str2, String str3, String str4) {
        new StringBuilder(f777z[11]).append(str);
        ac.m1581b();
        if (context == null) {
            throw new IllegalArgumentException(f777z[14]);
        } else if (TextUtils.isEmpty(str)) {
            ac.m1588e();
            C0459i.m1415a(f777z[1], 996, context);
            return null;
        } else {
            JSONObject a = C0456f.m1398a(context, f777z[3], str);
            if (a == null) {
                ac.m1581b();
                return null;
            }
            String optString = a.optString(f777z[6], "");
            if (!an.m1657a(optString)) {
                str4 = optString;
            }
            if (an.m1657a(str4)) {
                str4 = a.optString(f777z[4], "");
            }
            new StringBuilder(f777z[7]).append(str4);
            ac.m1581b();
            boolean z = a.optInt(f777z[5], 0) == 1;
            int optInt = z ? a.optInt(f777z[15], 0) : 0;
            C0430a c0430a = new C0430a();
            c0430a.c = str4;
            c0430a.f637a = a;
            c0430a.b = a.optInt(f777z[8], 3);
            c0430a.e = z;
            c0430a.f = optInt;
            c0430a.g = a.optInt(f777z[9], 0);
            c0430a.i = a.optString(f777z[13], "");
            c0430a.j = a.optString(f777z[10], "");
            c0430a.k = a.optString(f777z[0], "");
            c0430a.l = a.optString(f777z[2], "");
            c0430a.m = str2;
            c0430a.n = str3;
            c0430a.d = a.optString(f777z[12], "");
            return c0430a;
        }
    }

    /* renamed from: a */
    private static JSONObject m1398a(Context context, String str, String str2) {
        try {
            return new JSONObject(str2);
        } catch (JSONException e) {
            ac.m1593i();
            C0459i.m1415a(str, 996, context);
            return null;
        }
    }

    /* renamed from: a */
    public static JSONObject m1399a(Context context, String str, JSONObject jSONObject, String str2) {
        JSONObject jSONObject2 = null;
        if (jSONObject == null) {
            ac.m1586d();
            C0459i.m1415a(str, 996, context);
        } else if (TextUtils.isEmpty(str2)) {
            ac.m1586d();
        } else {
            try {
                if (!jSONObject.isNull(str2)) {
                    jSONObject2 = jSONObject.getJSONObject(str2);
                }
            } catch (JSONException e) {
                ac.m1593i();
                C0459i.m1415a(str, 996, context);
            }
        }
        return jSONObject2;
    }

    /* renamed from: a */
    public static void m1400a(Context context, C0430a c0430a) {
        ac.m1576a();
        if (context == null) {
            throw new IllegalArgumentException(f777z[14]);
        }
        int i = c0430a.b;
        JSONObject jSONObject = c0430a.f637a;
        String str = c0430a.c;
        if (i == 3 || i == 4) {
            jSONObject = C0456f.m1399a(context, str, jSONObject, f777z[18]);
            if (jSONObject == null) {
                ac.m1586d();
                return;
            }
            int optInt = jSONObject.optInt(f777z[16], -1);
            if (optInt == 0) {
                C0429c c0441m = new C0441m();
                c0441m.f613c = str;
                c0441m.f612b = i;
                c0441m.f625o = optInt;
                c0441m.f618h = c0430a.h;
                c0441m.f615e = c0430a.e;
                c0441m.f616f = c0430a.f;
                c0441m.f623m = c0430a.m;
                c0441m.f614d = c0430a.d;
                c0441m.f617g = c0430a.g;
                boolean b = c0441m.m1276b(context, jSONObject);
                ac.m1576a();
                if (b) {
                    c0441m.mo2219a(context);
                    ac.m1576a();
                    return;
                }
                ac.m1586d();
                return;
            }
            new StringBuilder(f777z[19]).append(optInt);
            ac.m1586d();
            C0459i.m1415a(str, 996, context);
            return;
        }
        new StringBuilder(f777z[17]).append(i);
        ac.m1581b();
        C0459i.m1415a(str, 996, context);
    }

    /* renamed from: a */
    public static void m1401a(Context context, String str) {
        new StringBuilder(f777z[25]).append(str);
        ac.m1576a();
        if (context == null) {
            throw new IllegalArgumentException(f777z[14]);
        } else if (TextUtils.isEmpty(str)) {
            ac.m1588e();
        } else {
            JSONObject a = C0456f.m1398a(context, f777z[1], str);
            if (a != null) {
                String optString = a.optString(f777z[6], "");
                if (an.m1657a(optString)) {
                    optString = a.optString(f777z[4], "");
                }
                int optInt = a.optInt(f777z[8], -1);
                if (optInt == 2) {
                    String trim = a.optString(f777z[18], "").trim();
                    if (C0456f.m1402a(trim)) {
                        new StringBuilder(f777z[24]).append(trim);
                        ac.m1576a();
                        if (context == null) {
                            throw new IllegalArgumentException(f777z[14]);
                        }
                        new C0457g(trim, context, optString).start();
                        return;
                    }
                    new StringBuilder(f777z[23]).append(trim);
                    ac.m1581b();
                    C0459i.m1415a(optString, 996, context);
                    return;
                }
                a = optInt == 1 ? C0456f.m1399a(context, optString, a, f777z[18]) : null;
                if (a != null) {
                    C0429c c0441m;
                    int optInt2 = a.optInt(f777z[16], -1);
                    switch (optInt2) {
                        case 0:
                            c0441m = new C0441m();
                            break;
                        case 1:
                            c0441m = new C0437i();
                            break;
                        case 2:
                            c0441m = new C0447s();
                            break;
                        case 3:
                            c0441m = new C0446r();
                            break;
                        default:
                            new StringBuilder(f777z[26]).append(optInt2);
                            ac.m1586d();
                            C0459i.m1415a(optString, 996, context);
                            return;
                    }
                    boolean b = c0441m.m1276b(context, a);
                    ac.m1576a();
                    c0441m.f613c = optString;
                    c0441m.f612b = optInt;
                    c0441m.f625o = optInt2;
                    if (b) {
                        c0441m.mo2219a(context);
                        ac.m1576a();
                        return;
                    }
                    ac.m1586d();
                }
            }
        }
    }

    /* renamed from: a */
    public static boolean m1402a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String trim = str.trim();
        boolean matches = trim.matches(f777z[21]);
        if (matches) {
            return matches;
        }
        new StringBuilder(f777z[22]).append(trim);
        ac.m1586d();
        return matches;
    }

    /* renamed from: a */
    public static boolean m1403a(boolean z, int i, Context context) {
        return (z && i == 0) ? true : z && i == 1 && f777z[20].equalsIgnoreCase(C0490b.m1700d(context));
    }
}
