package cn.jpush.android.util;

import android.content.Context;
import android.content.SharedPreferences;

public class al {
    /* renamed from: c */
    private static al f988c;
    /* renamed from: z */
    private static final String f989z;
    /* renamed from: a */
    private final String f990a = f989z;
    /* renamed from: b */
    private SharedPreferences f991b = null;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = "\u001a/\u0003vx\u0003>)F>\u0019\u001fb";
        r0 = r0.toCharArray();
        r1 = r0.length;
        r2 = 0;
        r3 = 1;
        if (r1 > r3) goto L_0x0027;
    L_0x000b:
        r3 = r0;
        r4 = r2;
        r7 = r1;
        r1 = r0;
        r0 = r7;
    L_0x0010:
        r6 = r1[r2];
        r5 = r4 % 5;
        switch(r5) {
            case 0: goto L_0x0035;
            case 1: goto L_0x0038;
            case 2: goto L_0x003b;
            case 3: goto L_0x003e;
            default: goto L_0x0017;
        };
    L_0x0017:
        r5 = 16;
    L_0x0019:
        r5 = r5 ^ r6;
        r5 = (char) r5;
        r1[r2] = r5;
        r2 = r4 + 1;
        if (r0 != 0) goto L_0x0025;
    L_0x0021:
        r1 = r3;
        r4 = r2;
        r2 = r0;
        goto L_0x0010;
    L_0x0025:
        r1 = r0;
        r0 = r3;
    L_0x0027:
        if (r1 > r2) goto L_0x000b;
    L_0x0029:
        r1 = new java.lang.String;
        r1.<init>(r0);
        r0 = r1.intern();
        f989z = r0;
        return;
    L_0x0035:
        r5 = 80;
        goto L_0x0019;
    L_0x0038:
        r5 = 127; // 0x7f float:1.78E-43 double:6.27E-322;
        goto L_0x0019;
    L_0x003b:
        r5 = 118; // 0x76 float:1.65E-43 double:5.83E-322;
        goto L_0x0019;
    L_0x003e:
        r5 = 5;
        goto L_0x0019;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.al.<clinit>():void");
    }

    /* renamed from: a */
    private SharedPreferences m1650a(Context context) {
        if (this.f991b == null) {
            synchronized (al.class) {
                this.f991b = context.getSharedPreferences(f989z, 0);
            }
        }
        return this.f991b;
    }

    /* renamed from: a */
    public static al m1651a() {
        if (f988c == null) {
            f988c = new al();
        }
        return f988c;
    }

    /* renamed from: a */
    public final long m1652a(Context context, String str, long j) {
        return m1650a(context).getLong(str, j);
    }

    /* renamed from: a */
    public final String m1653a(Context context, String str, String str2) {
        return m1650a(context).getString(str, null);
    }

    /* renamed from: b */
    public final void m1654b(Context context, String str, long j) {
        m1650a(context).edit().putLong(str, j).commit();
    }

    /* renamed from: b */
    public final void m1655b(Context context, String str, String str2) {
        m1650a(context).edit().putString(str, str2).commit();
    }
}
