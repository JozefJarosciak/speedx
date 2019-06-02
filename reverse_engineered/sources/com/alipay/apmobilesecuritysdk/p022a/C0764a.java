package com.alipay.apmobilesecuritysdk.p022a;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.alipay.apmobilesecuritysdk.p023b.C0765a;
import com.alipay.apmobilesecuritysdk.p024c.C0766a;
import com.alipay.apmobilesecuritysdk.p025d.C0771e;
import com.alipay.apmobilesecuritysdk.p026e.C0772a;
import com.alipay.apmobilesecuritysdk.p027f.C0773a;
import com.alipay.apmobilesecuritysdk.p027f.C0774b;
import com.alipay.apmobilesecuritysdk.p027f.C0775c;
import com.alipay.apmobilesecuritysdk.p027f.C0776d;
import com.alipay.apmobilesecuritysdk.p027f.C0779g;
import com.alipay.apmobilesecuritysdk.p027f.C0780h;
import com.alipay.apmobilesecuritysdk.p027f.C0781i;
import com.alipay.p029b.p030a.p031a.p032a.C0789a;
import com.alipay.p029b.p030a.p031a.p035c.C0803d;
import com.alipay.p029b.p030a.p031a.p035c.p036a.C0795a;
import com.alipay.p029b.p030a.p031a.p035c.p036a.C0796b;
import com.alipay.p029b.p030a.p031a.p035c.p036a.C0797c;
import com.alipay.p029b.p030a.p031a.p035c.p037b.C0799a;
import com.alipay.p029b.p030a.p031a.p039e.C0808b;
import com.alipay.sdk.cons.C0844a;
import com.alipay.sdk.cons.C0845b;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/* renamed from: com.alipay.apmobilesecuritysdk.a.a */
public final class C0764a {
    /* renamed from: a */
    private Context f1811a;
    /* renamed from: b */
    private C0765a f1812b = C0765a.m2920a();
    /* renamed from: c */
    private int f1813c = 4;

    public C0764a(Context context) {
        this.f1811a = context;
    }

    /* renamed from: a */
    public static String m2914a(Context context) {
        String b = C0764a.m2918b(context);
        return C0789a.m3020a(b) ? C0780h.m2982c(context) : b;
    }

    /* renamed from: a */
    public static String m2915a(Context context, String str) {
        try {
            String a = C0781i.m2983a(str);
            if (!C0789a.m3020a(a)) {
                return a;
            }
            a = C0779g.m2971a(context, str);
            C0781i.m2987a(str, a);
            if (!C0789a.m3020a(a)) {
                return a;
            }
            return "";
        } catch (Throwable th) {
        }
    }

    /* renamed from: a */
    private static boolean m2916a() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String[] strArr = new String[]{"2016-11-10 2016-11-11", "2016-12-11 2016-12-12"};
        int random = ((int) (((Math.random() * 24.0d) * 60.0d) * 60.0d)) * 1;
        int i = 0;
        while (i < 2) {
            try {
                String[] split = strArr[i].split(" ");
                if (split != null && split.length == 2) {
                    Date date = new Date();
                    Date parse = simpleDateFormat.parse(split[0] + " 00:00:00");
                    Date parse2 = simpleDateFormat.parse(split[1] + " 23:59:59");
                    Calendar instance = Calendar.getInstance();
                    instance.setTime(parse2);
                    instance.add(13, random);
                    parse2 = instance.getTime();
                    if (date.after(parse) && date.before(parse2)) {
                        return true;
                    }
                }
                i++;
            } catch (Exception e) {
            }
        }
        return false;
    }

    /* renamed from: b */
    private C0796b m2917b(Map<String, String> map) {
        try {
            C0774b c;
            Context context = this.f1811a;
            C0797c c0797c = new C0797c();
            String str = "";
            String str2 = "";
            String str3 = "";
            String a = C0772a.m2936a();
            String a2 = C0789a.m3019a(map, "rpcVersion", "");
            String a3 = C0764a.m2915a(context, C0789a.m3019a(map, "appName", ""));
            C0775c c2 = C0776d.m2959c(context);
            C0775c b = C0776d.m2957b();
            if (c2 != null) {
                str = c2.m2948a();
                str3 = c2.m2950c();
            }
            if (C0789a.m3020a(str)) {
                c = C0773a.m2944c(context);
                if (c != null) {
                    str = c.m2945a();
                    str3 = c.m2947c();
                }
            }
            if (b != null) {
                str2 = b.m2948a();
            }
            if (C0789a.m3020a(str2)) {
                c = C0773a.m2942b();
                if (c != null) {
                    str2 = c.m2945a();
                }
            }
            c0797c.m3096a("android");
            c0797c.m3101c(str);
            c0797c.m3099b(str2);
            c0797c.m3103d(a3);
            c0797c.m3105e(a);
            c0797c.m3109g(str3);
            c0797c.m3107f(a2);
            c0797c.m3097a(C0771e.m2932a(context, map));
            return C0803d.m3125a(this.f1811a, this.f1812b.m2923c()).mo2419a(c0797c);
        } catch (Throwable th) {
            C0766a.m2926a(th);
            return null;
        }
    }

    /* renamed from: b */
    private static String m2918b(Context context) {
        try {
            String b = C0781i.m2989b();
            if (!C0789a.m3020a(b)) {
                return b;
            }
            C0775c b2 = C0776d.m2958b(context);
            if (b2 != null) {
                C0781i.m2986a(b2);
                b = b2.m2948a();
                if (C0789a.m3023b(b)) {
                    return b;
                }
            }
            C0774b b3 = C0773a.m2943b(context);
            if (b3 != null) {
                C0781i.m2985a(b3);
                b = b3.m2945a();
                if (C0789a.m3023b(b)) {
                    return b;
                }
            }
            return "";
        } catch (Throwable th) {
        }
    }

    /* renamed from: a */
    public final int m2919a(Map<String, String> map) {
        Object obj = 2;
        Object obj2 = 1;
        try {
            Object obj3;
            int i;
            C0766a.m2924a(this.f1811a, C0789a.m3019a(map, C0845b.f2062c, ""), C0789a.m3019a(map, C0845b.f2066g, ""), C0764a.m2914a(this.f1811a));
            String a = C0789a.m3019a(map, "appName", "");
            C0764a.m2918b(this.f1811a);
            C0764a.m2915a(this.f1811a, a);
            C0781i.m2984a();
            int i2;
            if (!C0764a.m2916a()) {
                C0771e.m2933a();
                if ((!C0789a.m3021a(C0771e.m2934b(this.f1811a, map), C0781i.m2991c()) ? 1 : null) != null) {
                    i2 = 1;
                } else {
                    String a2 = C0789a.m3019a(map, C0845b.f2062c, "");
                    String a3 = C0789a.m3019a(map, C0845b.f2066g, "");
                    if (C0789a.m3023b(a2) && !C0789a.m3021a(a2, C0781i.m2993d())) {
                        i2 = 1;
                    } else if (C0789a.m3023b(a3) && !C0789a.m3021a(a3, C0781i.m2995e())) {
                        i2 = 1;
                    } else if (!C0781i.m2988a(this.f1811a, a)) {
                        i2 = 1;
                    } else if (C0789a.m3020a(C0764a.m2915a(this.f1811a, a))) {
                        i2 = 1;
                    } else if (C0789a.m3020a(C0764a.m2918b(this.f1811a))) {
                        i2 = 1;
                    } else {
                        obj3 = null;
                    }
                }
            } else if (C0789a.m3020a(C0764a.m2915a(this.f1811a, a))) {
                obj3 = 1;
            } else if (C0789a.m3020a(C0764a.m2918b(this.f1811a))) {
                i2 = 1;
            } else {
                obj3 = null;
            }
            if (obj3 == null) {
                i = 0;
            } else {
                Context context = this.f1811a;
                C0765a.m2920a().m2922b();
                C0772a.m2937b();
                C0795a b = m2917b((Map) map);
                if (b != null) {
                    if (b.a) {
                        if (!C0789a.m3020a(b.f1860c)) {
                            obj = 1;
                        }
                    } else if ("APPKEY_ERROR".equals(b.b)) {
                        obj = 3;
                    }
                }
                switch (obj) {
                    case 1:
                        C0780h.m2978a(this.f1811a, C0844a.f2048d.equals(b.f1865h));
                        C0780h.m2979b(this.f1811a, b.f1867j == null ? "0" : b.f1867j);
                        C0781i.m2992c(C0771e.m2934b(this.f1811a, map));
                        C0781i.m2987a(a, b.f1861d);
                        C0781i.m2990b(b.f1860c);
                        C0781i.m2994d(b.f1862e);
                        String a4 = C0789a.m3019a(map, C0845b.f2062c, "");
                        if (!C0789a.m3023b(a4) || C0789a.m3021a(a4, C0781i.m2993d())) {
                            a4 = C0781i.m2993d();
                        } else {
                            C0781i.m2996e(a4);
                        }
                        C0781i.m2996e(a4);
                        a4 = C0789a.m3019a(map, C0845b.f2066g, "");
                        if (!C0789a.m3023b(a4) || C0789a.m3021a(a4, C0781i.m2995e())) {
                            a4 = C0781i.m2995e();
                        } else {
                            C0781i.m2998f(a4);
                        }
                        C0781i.m2998f(a4);
                        C0781i.m2984a();
                        C0776d.m2956a(this.f1811a, C0781i.m2999g());
                        Context context2 = this.f1811a;
                        C0776d.m2954a();
                        C0773a.m2941a(this.f1811a, new C0774b(C0781i.m2989b(), C0781i.m2991c(), C0781i.m2997f()));
                        context2 = this.f1811a;
                        C0773a.m2939a();
                        C0779g.m2974a(this.f1811a, a, C0781i.m2983a(a));
                        context2 = this.f1811a;
                        C0779g.m2972a();
                        C0780h.m2977a(this.f1811a, a, System.currentTimeMillis());
                        break;
                    case 3:
                        i = 1;
                        break;
                    default:
                        if (b != null) {
                            C0766a.m2925a("Server error, result:" + b.f1859b);
                        } else {
                            C0766a.m2925a("Server error, returned null");
                        }
                        if (C0789a.m3020a(C0764a.m2915a(this.f1811a, a))) {
                            i = 4;
                            break;
                        }
                        break;
                }
                i = 0;
            }
            this.f1813c = i;
            C0799a a5 = C0803d.m3125a(this.f1811a, this.f1812b.m2923c());
            Context context3 = this.f1811a;
            ConnectivityManager connectivityManager = (ConnectivityManager) context3.getSystemService("connectivity");
            NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
            if (!(activeNetworkInfo != null && activeNetworkInfo.isConnected() && activeNetworkInfo.getType() == 1)) {
                obj2 = null;
            }
            if (obj2 != null && C0780h.m2980b(context3)) {
                new C0808b(context3.getFilesDir().getAbsolutePath() + "/log/ap", a5).m3134a();
            }
        } catch (Throwable e) {
            C0766a.m2926a(e);
        }
        return this.f1813c;
    }
}
