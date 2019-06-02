package cn.jpush.android.helpers;

import android.content.Context;
import android.telephony.TelephonyManager;
import cn.jpush.android.C0404a;
import cn.jpush.android.C0448e;
import cn.jpush.android.service.ServiceInterface;
import cn.jpush.android.util.C0490b;
import cn.jpush.android.util.ac;

/* renamed from: cn.jpush.android.helpers.j */
public final class C0460j {
    /* renamed from: z */
    private static final String[] f786z;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 6;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "\u0010\u0015\u000eV*\u0018\u001fDT \u0003\u0016\u0003W6\u0018\u0014\u0004\n\u00174:.{\u001594$a\u001a\"/+p\u0000";
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
            case 0: goto L_0x0068;
            case 1: goto L_0x006b;
            case 2: goto L_0x006e;
            case 3: goto L_0x0071;
            default: goto L_0x001d;
        };
    L_0x001d:
        r9 = 69;
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
            default: goto L_0x003b;
        };
    L_0x003b:
        r3[r2] = r1;
        r2 = 1;
        r1 = "U_";
        r0 = 0;
        r3 = r4;
        goto L_0x0008;
    L_0x0043:
        r3[r2] = r1;
        r2 = 2;
        r1 = "\u0001\u0013\u0005J ";
        r0 = 1;
        r3 = r4;
        goto L_0x0008;
    L_0x004b:
        r3[r2] = r1;
        r2 = 3;
        r1 = "%\u0013\u000f\u00043\u0014\t\u0019M*\u001f5\u000bI Q\u0012\u0019\u0004+\u001e\u000fJR$\u001d\u0012\u000e\be!\u0017\u000fE6\u0014[\tL \u0012\u0010J]*\u0004\tJe+\u0015\t\u0005M!<\u001a\u0004M#\u0014\b\u001e\n=\u001c\u0017";
        r0 = 2;
        r3 = r4;
        goto L_0x0008;
    L_0x0053:
        r3[r2] = r1;
        r2 = 4;
        r1 = "$\u0015\u0001J*\u0006\u0015";
        r0 = 3;
        r3 = r4;
        goto L_0x0008;
    L_0x005b:
        r3[r2] = r1;
        r2 = 5;
        r1 = "\"\u001e\u0018R,\u0012\u001e\"A)\u0001\u001e\u0018";
        r0 = 4;
        r3 = r4;
        goto L_0x0008;
    L_0x0063:
        r3[r2] = r1;
        f786z = r4;
        return;
    L_0x0068:
        r9 = 113; // 0x71 float:1.58E-43 double:5.6E-322;
        goto L_0x001f;
    L_0x006b:
        r9 = 123; // 0x7b float:1.72E-43 double:6.1E-322;
        goto L_0x001f;
    L_0x006e:
        r9 = 106; // 0x6a float:1.49E-43 double:5.24E-322;
        goto L_0x001f;
    L_0x0071:
        r9 = 36;
        goto L_0x001f;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.helpers.j.<clinit>():void");
    }

    /* renamed from: a */
    public static String m1418a(Context context) {
        String subscriberId;
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(f786z[2]);
        String h = C0490b.m1714h(context);
        if (C0490b.m1697c(context, f786z[0])) {
            try {
                subscriberId = telephonyManager.getSubscriberId();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (h == null) {
                h = " ";
            }
            if (subscriberId == null) {
                subscriberId = " ";
            }
            return h + f786z[1] + subscriberId + f786z[1] + context.getPackageName() + f786z[1] + C0448e.f754f;
        }
        subscriberId = null;
        if (h == null) {
            h = " ";
        }
        if (subscriberId == null) {
            subscriberId = " ";
        }
        return h + f786z[1] + subscriberId + f786z[1] + context.getPackageName() + f786z[1] + C0448e.f754f;
    }

    /* renamed from: a */
    public static boolean m1419a(int i) {
        return i == 14 || i == 13 || i == 15;
    }

    /* renamed from: b */
    public static String m1420b(Context context) {
        try {
            String str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            if (str.length() <= 30) {
                return str;
            }
            ac.m1589e(f786z[5], f786z[3]);
            return str.substring(0, 30);
        } catch (Exception e) {
            return f786z[4];
        }
    }

    /* renamed from: c */
    public static void m1421c(Context context) {
        if (C0404a.m1095d(context)) {
            ac.m1581b();
            ServiceInterface.m1460a(context, 1);
            C0404a.m1067a(context, false);
            return;
        }
        ac.m1581b();
    }
}
