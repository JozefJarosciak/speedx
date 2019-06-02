package cn.jpush.android.api;

import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import java.util.HashMap;

/* renamed from: cn.jpush.android.api.k */
public final class C0415k {
    /* renamed from: a */
    public static boolean f556a = false;
    /* renamed from: b */
    private static boolean f557b = true;
    /* renamed from: c */
    private static String f558c;
    /* renamed from: d */
    private static HashMap<String, Integer> f559d = new HashMap();
    /* renamed from: e */
    private static String f560e = null;
    /* renamed from: f */
    private static String f561f = null;
    /* renamed from: g */
    private static String f562g = null;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r3 = 1;
        r2 = 0;
        r7 = 0;
        f557b = r3;
        f556a = r2;
        r0 = "诊團惟殂世|S\u0003$JTD\u000e盉SSb\u0012>IPU_d咰R^',INU_d皸NE\u0007(N\u0015\u0019呹豎甔盅元纨诬斅注Ｊ=\u001dINX>#HXB\u0011,_X\u001e\u0018#nXC\u0002 Y\u0015\u0019W品\u001cw`\u0002>Tt^\u0003(N[Q\u0014(\u0012R^',INU_d";
        r0 = r0.toCharArray();
        r1 = r0.length;
        if (r1 > r3) goto L_0x0038;
    L_0x0010:
        r3 = r0;
        r4 = r2;
        r8 = r1;
        r1 = r0;
        r0 = r8;
    L_0x0015:
        r6 = r1[r2];
        r5 = r4 % 5;
        switch(r5) {
            case 0: goto L_0x002a;
            case 1: goto L_0x002d;
            case 2: goto L_0x0030;
            case 3: goto L_0x0033;
            default: goto L_0x001c;
        };
    L_0x001c:
        r5 = 60;
    L_0x001e:
        r5 = r5 ^ r6;
        r5 = (char) r5;
        r1[r2] = r5;
        r2 = r4 + 1;
        if (r0 != 0) goto L_0x0036;
    L_0x0026:
        r1 = r3;
        r4 = r2;
        r2 = r0;
        goto L_0x0015;
    L_0x002a:
        r5 = 61;
        goto L_0x001e;
    L_0x002d:
        r5 = 48;
        goto L_0x001e;
    L_0x0030:
        r5 = 119; // 0x77 float:1.67E-43 double:5.9E-322;
        goto L_0x001e;
    L_0x0033:
        r5 = 77;
        goto L_0x001e;
    L_0x0036:
        r1 = r0;
        r0 = r3;
    L_0x0038:
        if (r1 > r2) goto L_0x0010;
    L_0x003a:
        r1 = new java.lang.String;
        r1.<init>(r0);
        r0 = r1.intern();
        f558c = r0;
        r0 = new java.util.HashMap;
        r0.<init>();
        f559d = r0;
        f560e = r7;
        f561f = r7;
        f562g = r7;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.api.k.<clinit>():void");
    }

    /* renamed from: a */
    public static void m1196a(Application application) {
        ActivityLifecycleCallbacks c0416l = new C0416l();
        application.unregisterActivityLifecycleCallbacks(c0416l);
        application.registerActivityLifecycleCallbacks(c0416l);
    }
}
