package cn.jpush.android;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Build.VERSION;
import android.os.Bundle;
import cn.jpush.android.api.C0415k;
import cn.jpush.android.helpers.C0461k;
import cn.jpush.android.service.PushProtocol;
import cn.jpush.android.service.PushService;
import cn.jpush.android.util.C0403i;
import cn.jpush.android.util.C0490b;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.an;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: cn.jpush.android.e */
public final class C0448e {
    /* renamed from: a */
    public static boolean f749a = false;
    /* renamed from: b */
    public static int f750b;
    /* renamed from: c */
    public static String f751c;
    /* renamed from: d */
    public static String f752d;
    /* renamed from: e */
    public static Context f753e;
    /* renamed from: f */
    public static String f754f;
    /* renamed from: g */
    public static long f755g = 0;
    /* renamed from: h */
    public static String f756h = "";
    /* renamed from: i */
    public static int f757i;
    /* renamed from: j */
    public static String f758j;
    /* renamed from: k */
    public static boolean f759k = false;
    /* renamed from: l */
    public static boolean f760l = false;
    /* renamed from: m */
    public static boolean f761m = false;
    /* renamed from: n */
    public static boolean f762n = true;
    /* renamed from: o */
    public static C0426b f763o;
    /* renamed from: p */
    private static AtomicBoolean f764p = new AtomicBoolean(false);
    /* renamed from: q */
    private static ServiceConnection f765q = new C0449f();
    /* renamed from: z */
    private static final String[] f766z;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 24;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "\u0005\u0006@\u0018_\t\u0017UC\u001b\"3A\nSH\u0002D\tp\r\u001a\u0014T\u001b\u0006\f@Y_\r\u0005]\u0017^\fC]\u0017\u001b\u0005\u0002Z\u0010]\r\u0010@";
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
            case 0: goto L_0x014c;
            case 1: goto L_0x0150;
            case 2: goto L_0x0154;
            case 3: goto L_0x0158;
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
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = "\u0005\u0006@\u0018_\t\u0017UC\u001b\u000b\u000bU\u0017U\r\u000f\u0014T\u001b\u0006\f@Y_\r\u0005]\u0017^\fC]\u0017\u001b\u0005\u0002Z\u0010]\r\u0010@";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "\"3a*s7\"d)p-:";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "\u0005\u0006@\u0018_\t\u0017UC\u001b+\u0002ZYU\u0007\u0017\u0014\u001e^\u001cCY\u001cO\t'U\rZH\u0005F\u0016VH\"D\tW\u0001\u0000U\rR\u0007\r}\u0017]\u0007";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "=\rQ\u0001K\r\u0000@\u001c_RCR\u0018R\u0004\u0006PYO\u0007CS\u001cOH\u0000A\u000bI\r\r@YZ\u0018\u0013X\u0010X\t\u0017]\u0016UH\nZ\u001fT";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "\u0005\u0006@\u0018_\t\u0017UC\u001b\u000b\u000bU\u0017U\r\u000f\u0014T\u001b";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "\"3A\nS/\u000f[\u001bZ\u0004";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "!\rB\u0018W\u0001\u0007\u0014\u0018K\u0018(Q\u0000\u001bRC";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = "DCd\u0015^\t\u0010QY\\\r\u0017\u0014\u0000T\u001d\u0011\u00148K\u0018\bQ\u0000\u001b\u000e\u0011[\u0014\u001b\"3A\nSH\u0014Q\u001b\u001b\u000b\fZ\nT\u0004\u0006\u0015";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        r2 = 9;
        r1 = "&,\u0014\u0014^\u001c\u0002\u0014\u001dZ\u001c\u0002\u0014\u001d^\u000e\nZ\u001c_H\nZYV\t\r]\u001f^\u001b\u0017\u001a";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0087:
        r3[r2] = r1;
        r2 = 10;
        r1 = "\"3a*s7 |8u&&x";
        r0 = 9;
        r3 = r4;
        goto L_0x0009;
    L_0x0092:
        r3[r2] = r1;
        r2 = 11;
        r1 = "\u0005\u0006@\u0018_\t\u0017UC\u001b\t\u0013D2^\u0011C\u0019Y";
        r0 = 10;
        r3 = r4;
        goto L_0x0009;
    L_0x009d:
        r3[r2] = r1;
        r2 = 12;
        r1 = "\u001b\fb\u001cI\u001b\n[\u0017\u0001";
        r0 = 11;
        r3 = r4;
        goto L_0x0009;
    L_0x00a8:
        r3[r2] = r1;
        r2 = 13;
        r1 = "/\u0006@YH\f\b\u0014\u000f^\u001a\u0010]\u0016UH\u0005U\u0010WI8莃厯H\f\b牼杕夊赍Bi";
        r0 = 12;
        r3 = r4;
        goto L_0x0009;
    L_0x00b3:
        r3[r2] = r1;
        r2 = 14;
        r1 = "&,\u0014\u000f^\u001a\u0010]\u0016U+\fP\u001c\u001b\u0007\u0011\u0014\u000f^\u001a\u0010]\u0016U&\u0002Y\u001c\u001b\f\u0006R\u0010U\r\u0007\u0014\u0010UH\u000eU\u0017R\u000e\u0006G\r\u0015";
        r0 = 13;
        r3 = r4;
        goto L_0x0009;
    L_0x00be:
        r3[r2] = r1;
        r2 = 15;
        r1 = "\"3A\nSH\u0000U\u0017U\u0007\u0017\u0014\u001b^H\nZ\u0010O\u0001\u0002X\u0010A\r\u0007\u0014\u001aT\u0005\u0013X\u001cO\r\u000fMY_\u001d\u0006\u0014\rTH-a5wH\u0002D\tr\u0006\u0005[W";
        r0 = 14;
        r3 = r4;
        goto L_0x0009;
    L_0x00c9:
        r3[r2] = r1;
        r2 = 16;
        r1 = "\u000e\u0002X\n^";
        r0 = 15;
        r3 = r4;
        goto L_0x0009;
    L_0x00d4:
        r3[r2] = r1;
        r2 = 17;
        r1 = ":\u0006Y\u0016O\rCg\u001cI\u001e\nW\u001c\u001b\n\nZ\u001d\u001b\u000e\u0002]\u0015^\fCW\u0018N\u001b\u0006PYY\u0011Cg\u001cX\u001d\u0011]\rB-\u001bW\u001cK\u001c\n[\u0017\u001a";
        r0 = 16;
        r3 = r4;
        goto L_0x0009;
    L_0x00df:
        r3[r2] = r1;
        r2 = 18;
        r1 = "\u001c\u0011A\u001c";
        r0 = 17;
        r3 = r4;
        goto L_0x0009;
    L_0x00ea:
        r3[r2] = r1;
        r2 = 19;
        r1 = "\u0002\u0002B\u0018\u0015\u0006\u0006@WK\u001a\u0006R\u001cI!3BMh\u001c\u0002W\u0012";
        r0 = 18;
        r3 = r4;
        goto L_0x0009;
    L_0x00f5:
        r3[r2] = r1;
        r2 = 20;
        r1 = "\"3A\nSHMG\u0016\u001b\u000e\nX\u001c\u001b\f\f\u0014\u0017T\u001cCY\u0018O\u000b\u000b\u00143k\u001d\u0010\\Y\u0015\u0002\u0002FY]\u0001\u000fQYR\u0006C@\u0011^H\u0013F\u0016Q\r\u0000@U\u001b.\u0002]\u0015^\fC@\u0016\u001b\u0001\r]\r\u001b\"3A\nS";
        r0 = 19;
        r3 = r4;
        goto L_0x0009;
    L_0x0100:
        r3[r2] = r1;
        r2 = 21;
        r1 = "\u0002\u0002B\u0018\u0015\u0006\u0006@WK\u001a\u0006R\u001cI!3BOz\f\u0007F\u001cH\u001b\u0006G";
        r0 = 20;
        r3 = r4;
        goto L_0x0009;
    L_0x010b:
        r3[r2] = r1;
        r2 = 22;
        r1 = "<\"s";
        r0 = 21;
        r3 = r4;
        goto L_0x0009;
    L_0x0116:
        r3[r2] = r1;
        r2 = 23;
        r1 = "\u0005\u0006@\u0018_\t\u0017UC\u001b+\u0002ZYU\u0007\u0017\u0014\u001e^\u001cCu\tK\u0004\nW\u0018O\u0001\fZYR\u000b\fZU\u001b\u0011\fAYL\u0001\u000fXYY\rCZ\u0016OH\u0002V\u0015^H\u0017[YH\u0000\fCYU\u0007\u0017]\u001fR\u000b\u0002@\u0010T\u0006CP\f^H\u0017[YO\u0000\u0006\u0014\u0018K\u0018\u000f]\u001aZ\u001c\n[\u0017\u001b\u0001\u0000[\u0017\u001b\u0001\u0010\u0014\u0017N\u0004\u000f\u001a";
        r0 = 22;
        r3 = r4;
        goto L_0x0009;
    L_0x0121:
        r3[r2] = r1;
        f766z = r4;
        r0 = 0;
        f749a = r0;
        r0 = new java.util.concurrent.atomic.AtomicBoolean;
        r1 = 0;
        r0.<init>(r1);
        f764p = r0;
        r0 = 0;
        f755g = r0;
        r0 = "";
        f756h = r0;
        r0 = 0;
        f759k = r0;
        r0 = 0;
        f760l = r0;
        r0 = 0;
        f761m = r0;
        r0 = 1;
        f762n = r0;
        r0 = new cn.jpush.android.f;
        r0.<init>();
        f765q = r0;
        return;
    L_0x014c:
        r9 = 104; // 0x68 float:1.46E-43 double:5.14E-322;
        goto L_0x0020;
    L_0x0150:
        r9 = 99;
        goto L_0x0020;
    L_0x0154:
        r9 = 52;
        goto L_0x0020;
    L_0x0158:
        r9 = 121; // 0x79 float:1.7E-43 double:6.0E-322;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.e.<clinit>():void");
    }

    /* renamed from: a */
    private static boolean m1358a() {
        int GetSdkVersion;
        UnsatisfiedLinkError e;
        try {
            GetSdkVersion = PushProtocol.GetSdkVersion();
            try {
                new StringBuilder(f766z[12]).append(GetSdkVersion);
                ac.m1576a();
            } catch (UnsatisfiedLinkError e2) {
                e = e2;
                ac.m1589e(f766z[6], f766z[13]);
                e.printStackTrace();
                return GetSdkVersion >= 200;
            }
        } catch (UnsatisfiedLinkError e3) {
            e = e3;
            GetSdkVersion = 0;
            ac.m1589e(f766z[6], f766z[13]);
            e.printStackTrace();
            if (GetSdkVersion >= 200) {
            }
        }
        if (GetSdkVersion >= 200) {
        }
    }

    /* renamed from: a */
    public static synchronized boolean m1359a(Context context) {
        boolean z = false;
        synchronized (C0448e.class) {
            if (f764p.get()) {
                z = true;
            } else {
                ac.m1581b();
                C0403i.m1045q(context.getApplicationContext());
                C0461k.m1422a(context);
                if (C0448e.m1358a()) {
                    f751c = context.getPackageName();
                    f753e = context.getApplicationContext();
                    f755g = C0404a.m1144x();
                    f756h = C0404a.m1046A();
                    ApplicationInfo c = C0448e.m1361c(context);
                    if (c == null) {
                        ac.m1589e(f766z[6], f766z[15]);
                    } else {
                        f750b = c.icon;
                        if (f750b == 0) {
                            ac.m1589e(f766z[6], f766z[23]);
                        }
                        f752d = context.getPackageManager().getApplicationLabel(c).toString();
                        try {
                            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                            f757i = packageInfo.versionCode;
                            String str = packageInfo.versionName;
                            f758j = str;
                            if (str.length() > 30) {
                                f758j = f758j.substring(0, 30);
                            }
                        } catch (Exception e) {
                            ac.m1582b(f766z[6], f766z[14]);
                        }
                        if (C0448e.m1360b(context)) {
                            if (VERSION.SDK_INT >= 14 && (context instanceof Application)) {
                                z = C0490b.m1713g(context);
                                C0415k.f556a = z;
                                if (z) {
                                    C0415k.m1196a((Application) context.getApplicationContext());
                                }
                            }
                            if (VERSION.SDK_INT == 8) {
                                System.setProperty(f766z[19], f766z[18]);
                                System.setProperty(f766z[21], f766z[16]);
                            }
                            f764p.set(true);
                            Context applicationContext = context.getApplicationContext();
                            Intent intent = new Intent();
                            intent.setClass(applicationContext, PushService.class);
                            try {
                                if (applicationContext.bindService(intent, f765q, 1)) {
                                    ac.m1576a();
                                } else {
                                    ac.m1576a();
                                }
                            } catch (SecurityException e2) {
                                ac.m1587d(f766z[22], f766z[17]);
                            }
                            z = true;
                        }
                    }
                } else {
                    ac.m1589e(f766z[6], f766z[20]);
                }
            }
        }
        return z;
    }

    /* renamed from: b */
    private static boolean m1360b(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null) {
                Bundle bundle = applicationInfo.metaData;
                if (bundle != null) {
                    String string = bundle.getString(f766z[2]);
                    f754f = string;
                    if (an.m1657a(string)) {
                        ac.m1589e(f766z[6], f766z[0]);
                        return false;
                    } else if (f754f.length() != 24) {
                        ac.m1589e(f766z[6], new StringBuilder(f766z[7]).append(f754f).append(f766z[8]).toString());
                        C0404a.m1085c(context, 1008);
                        return false;
                    } else {
                        f754f = f754f.toLowerCase(Locale.getDefault());
                        ac.m1582b(f766z[6], new StringBuilder(f766z[11]).append(f754f).toString());
                        C0404a.m1112h(f754f);
                        String c = an.m1660c(bundle.getString(f766z[10]));
                        if (an.m1657a(c)) {
                            ac.m1582b(f766z[6], f766z[1]);
                        } else {
                            ac.m1582b(f766z[6], new StringBuilder(f766z[5]).append(c).toString());
                            C0404a.m1116i(c);
                        }
                        return true;
                    }
                }
                ac.m1582b(f766z[6], f766z[9]);
                return false;
            }
            ac.m1582b(f766z[6], f766z[3]);
            return false;
        } catch (Throwable e) {
            ac.m1579a(f766z[6], f766z[4], e);
            return false;
        }
    }

    /* renamed from: c */
    private static ApplicationInfo m1361c(Context context) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
        } catch (Throwable e) {
            ac.m1583b(f766z[6], f766z[4], e);
            return null;
        }
    }
}
