package com.umeng.analytics;

import android.content.Context;
import android.text.TextUtils;
import p203u.aly.af;
import p203u.aly.ah;

/* compiled from: AnalyticsConfig */
/* renamed from: com.umeng.analytics.a */
public class C4731a {
    /* renamed from: a */
    public static String f16605a = null;
    /* renamed from: b */
    public static String f16606b = null;
    /* renamed from: c */
    public static String f16607c = "";
    /* renamed from: d */
    public static String f16608d = "";
    /* renamed from: e */
    public static boolean f16609e = true;
    /* renamed from: f */
    public static boolean f16610f = true;
    /* renamed from: g */
    public static boolean f16611g = true;
    /* renamed from: h */
    public static boolean f16612h = true;
    /* renamed from: i */
    public static long f16613i = 30000;
    /* renamed from: j */
    public static boolean f16614j;
    /* renamed from: k */
    public static int f16615k;
    /* renamed from: l */
    static double[] f16616l = null;
    /* renamed from: m */
    private static String f16617m = null;
    /* renamed from: n */
    private static String f16618n = null;
    /* renamed from: o */
    private static String f16619o = null;
    /* renamed from: p */
    private static int f16620p = 0;

    static {
        f16614j = false;
        f16614j = false;
    }

    /* renamed from: a */
    static void m18616a(Context context, String str) {
        if (context == null) {
            f16617m = str;
            return;
        }
        String n = af.m21132n(context);
        if (TextUtils.isEmpty(n)) {
            Object b = C4762j.m18682a(context).m18697b();
            if (TextUtils.isEmpty(b)) {
                C4762j.m18682a(context).m18693a(str);
            } else if (!b.equals(str)) {
                ah.m21162c("Appkey和上次配置的不一致 ");
                C4762j.m18682a(context).m18693a(str);
            }
            f16617m = str;
            return;
        }
        f16617m = n;
        if (!n.equals(str)) {
            ah.m21162c("Appkey和AndroidManifest.xml中配置的不一致 ");
        }
    }

    /* renamed from: a */
    static void m18617a(String str) {
        f16618n = str;
    }

    /* renamed from: a */
    public static String m18614a(Context context) {
        if (TextUtils.isEmpty(f16617m)) {
            f16617m = af.m21132n(context);
            if (TextUtils.isEmpty(f16617m)) {
                f16617m = C4762j.m18682a(context).m18697b();
            }
        }
        return f16617m;
    }

    /* renamed from: b */
    public static String m18619b(Context context) {
        if (TextUtils.isEmpty(f16618n)) {
            f16618n = af.m21135q(context);
        }
        return f16618n;
    }

    /* renamed from: a */
    public static double[] m18618a() {
        return f16616l;
    }

    /* renamed from: c */
    public static String m18620c(Context context) {
        if (TextUtils.isEmpty(f16619o)) {
            f16619o = C4762j.m18682a(context).m18699c();
        }
        return f16619o;
    }

    /* renamed from: a */
    static void m18615a(Context context, int i) {
        f16620p = i;
        C4762j.m18682a(context).m18692a(f16620p);
    }

    /* renamed from: d */
    public static int m18621d(Context context) {
        if (f16620p == 0) {
            f16620p = C4762j.m18682a(context).m18700d();
        }
        return f16620p;
    }

    /* renamed from: e */
    public static String m18622e(Context context) {
        return "6.0.0";
    }
}
