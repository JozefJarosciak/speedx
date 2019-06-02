package com.alipay.apmobilesecuritysdk.p027f;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.p024c.C0766a;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.alipay.apmobilesecuritysdk.f.i */
public final class C0781i {
    /* renamed from: a */
    private static String f1834a = "";
    /* renamed from: b */
    private static String f1835b = "";
    /* renamed from: c */
    private static String f1836c = "";
    /* renamed from: d */
    private static String f1837d = "";
    /* renamed from: e */
    private static String f1838e = "";
    /* renamed from: f */
    private static Map<String, String> f1839f = new HashMap();

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    public static synchronized java.lang.String m2983a(java.lang.String r3) {
        /*
        r1 = com.alipay.apmobilesecuritysdk.p027f.C0781i.class;
        monitor-enter(r1);
        r0 = new java.lang.StringBuilder;	 Catch:{ all -> 0x002d }
        r2 = "apdidTokenCache";
        r0.<init>(r2);	 Catch:{ all -> 0x002d }
        r0 = r0.append(r3);	 Catch:{ all -> 0x002d }
        r0 = r0.toString();	 Catch:{ all -> 0x002d }
        r2 = f1839f;	 Catch:{ all -> 0x002d }
        r2 = r2.containsKey(r0);	 Catch:{ all -> 0x002d }
        if (r2 == 0) goto L_0x002a;
    L_0x001a:
        r2 = f1839f;	 Catch:{ all -> 0x002d }
        r0 = r2.get(r0);	 Catch:{ all -> 0x002d }
        r0 = (java.lang.String) r0;	 Catch:{ all -> 0x002d }
        r2 = com.alipay.p029b.p030a.p031a.p032a.C0789a.m3023b(r0);	 Catch:{ all -> 0x002d }
        if (r2 == 0) goto L_0x002a;
    L_0x0028:
        monitor-exit(r1);
        return r0;
    L_0x002a:
        r0 = "";
        goto L_0x0028;
    L_0x002d:
        r0 = move-exception;
        monitor-exit(r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.apmobilesecuritysdk.f.i.a(java.lang.String):java.lang.String");
    }

    /* renamed from: a */
    public static synchronized void m2984a() {
        synchronized (C0781i.class) {
        }
    }

    /* renamed from: a */
    public static synchronized void m2985a(C0774b c0774b) {
        synchronized (C0781i.class) {
            if (c0774b != null) {
                f1834a = c0774b.m2945a();
                f1835b = c0774b.m2946b();
                f1836c = c0774b.m2947c();
            }
        }
    }

    /* renamed from: a */
    public static synchronized void m2986a(C0775c c0775c) {
        synchronized (C0781i.class) {
            if (c0775c != null) {
                f1834a = c0775c.m2948a();
                f1835b = c0775c.m2949b();
                f1837d = c0775c.m2951d();
                f1838e = c0775c.m2952e();
                f1836c = c0775c.m2950c();
            }
        }
    }

    /* renamed from: a */
    public static synchronized void m2987a(String str, String str2) {
        synchronized (C0781i.class) {
            String str3 = "apdidTokenCache" + str;
            if (f1839f.containsKey(str3)) {
                f1839f.remove(str3);
            }
            f1839f.put(str3, str2);
        }
    }

    /* renamed from: a */
    public static synchronized boolean m2988a(Context context, String str) {
        boolean z;
        synchronized (C0781i.class) {
            try {
                if (Math.abs(System.currentTimeMillis() - C0780h.m2981c(context, str)) < 86400000) {
                    z = true;
                }
            } catch (Throwable th) {
                C0766a.m2926a(th);
            }
            z = false;
        }
        return z;
    }

    /* renamed from: b */
    public static synchronized String m2989b() {
        String str;
        synchronized (C0781i.class) {
            str = f1834a;
        }
        return str;
    }

    /* renamed from: b */
    public static void m2990b(String str) {
        f1834a = str;
    }

    /* renamed from: c */
    public static synchronized String m2991c() {
        String str;
        synchronized (C0781i.class) {
            str = f1835b;
        }
        return str;
    }

    /* renamed from: c */
    public static void m2992c(String str) {
        f1835b = str;
    }

    /* renamed from: d */
    public static synchronized String m2993d() {
        String str;
        synchronized (C0781i.class) {
            str = f1837d;
        }
        return str;
    }

    /* renamed from: d */
    public static void m2994d(String str) {
        f1836c = str;
    }

    /* renamed from: e */
    public static synchronized String m2995e() {
        String str;
        synchronized (C0781i.class) {
            str = f1838e;
        }
        return str;
    }

    /* renamed from: e */
    public static void m2996e(String str) {
        f1837d = str;
    }

    /* renamed from: f */
    public static synchronized String m2997f() {
        String str;
        synchronized (C0781i.class) {
            str = f1836c;
        }
        return str;
    }

    /* renamed from: f */
    public static void m2998f(String str) {
        f1838e = str;
    }

    /* renamed from: g */
    public static synchronized C0775c m2999g() {
        C0775c c0775c;
        synchronized (C0781i.class) {
            c0775c = new C0775c(f1834a, f1835b, f1836c, f1837d, f1838e);
        }
        return c0775c;
    }

    /* renamed from: h */
    public static void m3000h() {
        f1839f.clear();
        f1834a = "";
        f1835b = "";
        f1837d = "";
        f1838e = "";
        f1836c = "";
    }
}
