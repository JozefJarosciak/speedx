package cn.jpush.android.helpers;

import android.content.Context;
import cn.jpush.android.C0404a;
import cn.jpush.android.p002a.C0400i;
import cn.jpush.android.service.C0479r;
import cn.jpush.android.util.C0490b;
import cn.jpush.android.util.C0492d;
import cn.jpush.android.util.C0508v;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.ah;
import cn.jpush.android.util.an;
import org.apache.commons.cli.HelpFormatter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: cn.jpush.android.helpers.i */
public final class C0459i {
    /* renamed from: a */
    private static int f784a = 20480;
    /* renamed from: z */
    private static final String[] f785z;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 16;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "\u0015tL6;\u001a-J:$\u001beL\u001e7\u0000~W1\u0006\u0011dM3 T:\u001821\u0007dY81=s\u0002";
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
            case 0: goto L_0x00d2;
            case 1: goto L_0x00d6;
            case 2: goto L_0x00da;
            case 3: goto L_0x00de;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 84;
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
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = "\u0019d_\u0000'\u0000vL*'";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "\u0000nH:";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "\u0010vL>";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\u0006rK*8\u0000";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "X7[00\u0011-\u0018";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "Te]/;\u0006c\u0018<;\u001ac]1 N7";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "\u0019d_\u0000=\u0010";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = "\u001dcQ21";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        r2 = 9;
        r1 = "X7\\> \u00157K6.\u0011-\u0018rt";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0087:
        r3[r2] = r1;
        r2 = 10;
        r1 = "\u0015gH\u00008\u001ddL";
        r0 = 9;
        r3 = r4;
        goto L_0x0009;
    L_0x0092:
        r3[r2] = r1;
        r2 = 11;
        r1 = "\u0004v[45\u0013r\u00186:\u0012x\u0018+;\u0000vT:\u0001z\u0018rt";
        r0 = 10;
        r3 = r4;
        goto L_0x0009;
    L_0x009d:
        r3[r2] = r1;
        r2 = 12;
        r1 = "\u000f5L0 \u0015{\u001aeq\u0010;\u001a/5\u0013r\u001aeq\u0010;\u001a,1\u001as]-=\u00105\u0002}q\u00075\u0014}!\u001ds\u001aeq\u0007;\u001a/1\u0006zQ,'\u001dxV\u00008\u001ddL}nQdE";
        r0 = 11;
        r3 = r4;
        goto L_0x0009;
    L_0x00a8:
        r3[r2] = r1;
        r2 = 13;
        r1 = "X5";
        r0 = 12;
        r3 = r4;
        goto L_0x0009;
    L_0x00b3:
        r3[r2] = r1;
        r2 = 14;
        r1 = "\u0015tL6;\u001a-J:$\u001beL\u0010$\u0011eY+=\u001by\u0018rt\u0017xV+1\u001ac\u0002";
        r0 = 13;
        r3 = r4;
        goto L_0x0009;
    L_0x00be:
        r3[r2] = r1;
        r2 = 15;
        r1 = ":Bt\u0013t\u0017xV+1\fc";
        r0 = 14;
        r3 = r4;
        goto L_0x0009;
    L_0x00c9:
        r3[r2] = r1;
        f785z = r4;
        r0 = 20480; // 0x5000 float:2.8699E-41 double:1.01185E-319;
        f784a = r0;
        return;
    L_0x00d2:
        r9 = 116; // 0x74 float:1.63E-43 double:5.73E-322;
        goto L_0x0020;
    L_0x00d6:
        r9 = 23;
        goto L_0x0020;
    L_0x00da:
        r9 = 56;
        goto L_0x0020;
    L_0x00de:
        r9 = 95;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.helpers.i.<clinit>():void");
    }

    /* renamed from: a */
    public static void m1411a(Context context) {
        if (!C0404a.m1145y()) {
            ac.m1586d();
        } else if (context == null) {
            ac.m1586d();
        } else {
            JSONArray l = C0490b.m1722l(context);
            if (l != null && l.length() != 0) {
                int length = l.length();
                int length2 = l.toString().length();
                new StringBuilder(f785z[11]).append(length).append(f785z[9]).append(length2);
                ac.m1584c();
                JSONObject a;
                if (length2 <= f784a) {
                    a = C0490b.m1674a(f785z[10], l);
                    if (a != null && a.length() > 0) {
                        ah.m1621a(context, a);
                        return;
                    }
                    return;
                }
                JSONArray jSONArray = new JSONArray();
                int i = 0;
                while (i < length) {
                    try {
                        jSONArray.put(l.getJSONObject(i));
                    } catch (JSONException e) {
                        ac.m1588e();
                    }
                    if (jSONArray.toString().length() > f784a || length - 1 == i) {
                        a = C0490b.m1674a(f785z[10], jSONArray);
                        if (a != null && a.length() > 0) {
                            ah.m1621a(context, a);
                        }
                        jSONArray = new JSONArray();
                    }
                    i++;
                }
            }
        }
    }

    /* renamed from: a */
    public static void m1412a(Context context, String str) {
        ac.m1581b();
        new C0492d(context, str).start();
    }

    /* renamed from: a */
    public static void m1413a(Context context, JSONObject jSONObject) {
        if (!C0404a.m1145y()) {
            return;
        }
        if (context == null) {
            throw new IllegalArgumentException(f785z[15]);
        } else if (jSONObject != null && jSONObject.length() > 0) {
            ah.m1621a(context, jSONObject);
            new StringBuilder(f785z[14]).append(jSONObject.toString());
            ac.m1581b();
        }
    }

    /* renamed from: a */
    public static void m1414a(Context context, boolean z, String str, boolean z2, boolean z3) {
        ac.m1581b();
        new C0400i(context, true, str, z2, z3).m1016f();
    }

    /* renamed from: a */
    public static void m1415a(String str, int i, Context context) {
        C0459i.m1416a(str, i, null, context);
    }

    /* renamed from: a */
    public static void m1416a(String str, int i, String str2, Context context) {
        if (!C0404a.m1145y()) {
            ac.m1576a();
        } else if (context == null) {
            ac.m1581b();
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(new StringBuilder(f785z[0]).append(str).append(f785z[5]).append(i).append(HelpFormatter.DEFAULT_OPT_PREFIX).append(C0479r.m1534b(i)).toString());
            if (!an.m1657a(str2)) {
                stringBuffer.append(new StringBuilder(f785z[6]).append(str2).toString());
            }
            ac.m1581b();
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(f785z[7], str);
                jSONObject.put(f785z[4], i);
                if (!an.m1657a(str2)) {
                    jSONObject.put(f785z[3], str2);
                }
                jSONObject.put(f785z[8], C0404a.m1126m());
                jSONObject.put(f785z[2], f785z[1]);
                ah.m1621a(context, jSONObject);
            } catch (JSONException e) {
            }
        }
    }

    /* renamed from: b */
    public static void m1417b(Context context) {
        ac.m1576a();
        String[] a = C0508v.m1810a(context);
        if (a == null || a.length == 0) {
            ac.m1588e();
            return;
        }
        int length = a.length;
        String str = "[";
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i < length) {
            String str2 = a[i];
            str = i2 == 0 ? str + "\"" + str2 + "\"" : str + f785z[13] + str2 + "\"";
            int i4 = i + 1;
            i = i2 + 1;
            if (i >= 50 || str.length() > 1000 || i4 == length) {
                String str3 = str + "]";
                str3 = String.format(f785z[12], new Object[]{Integer.valueOf(length), Integer.valueOf(i3), C0404a.m1050E(), Long.valueOf(C0404a.m1144x()), str3});
                ac.m1581b();
                ah.m1621a(context, C0490b.m1673a(f785z[10], str3));
                str = "[";
                i3++;
                i = 0;
            }
            i2 = i;
            i = i4;
        }
    }
}
