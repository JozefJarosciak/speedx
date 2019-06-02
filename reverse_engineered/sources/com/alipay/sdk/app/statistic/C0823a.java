package com.alipay.sdk.app.statistic;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.sys.C0869a;
import com.j256.ormlite.stmt.query.SimpleComparison;

/* renamed from: com.alipay.sdk.app.statistic.a */
public final class C0823a {
    /* renamed from: a */
    public static final String f1940a = "alipay_cashier_statistic_record";
    /* renamed from: b */
    private static C0825c f1941b;

    /* renamed from: a */
    public static void m3182a(Context context) {
        if (f1941b == null) {
            f1941b = new C0825c(context);
        }
    }

    /* renamed from: b */
    private static void m3188b(Context context, String str) {
        new Thread(new C0824b(context, str)).start();
    }

    /* renamed from: a */
    public static synchronized void m3183a(Context context, String str) {
        String str2 = null;
        synchronized (C0823a.class) {
            if (f1941b != null) {
                C0825c c0825c = f1941b;
                if (TextUtils.isEmpty(c0825c.f1985P)) {
                    str2 = "";
                } else {
                    String str3;
                    String[] split = str.split(C0869a.f2161b);
                    if (split != null) {
                        str3 = null;
                        for (String split2 : split) {
                            String[] split3 = split2.split(SimpleComparison.EQUAL_TO_OPERATION);
                            if (split3 != null && split3.length == 2) {
                                if (split3[0].equalsIgnoreCase(C0825c.f1948E)) {
                                    split3[1].replace("\"", "");
                                } else if (split3[0].equalsIgnoreCase(C0825c.f1949F)) {
                                    str3 = split3[1].replace("\"", "");
                                } else if (split3[0].equalsIgnoreCase(C0825c.f1950G)) {
                                    str2 = split3[1].replace("\"", "");
                                }
                            }
                        }
                    } else {
                        str3 = null;
                    }
                    str2 = C0825c.m3190a(str2);
                    String a = C0825c.m3190a(C0825c.m3190a(str3));
                    c0825c.f1978I = String.format("%s,%s,-,%s,-,-,-", new Object[]{str2, str3, a});
                    str2 = String.format("[(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s)]", new Object[]{c0825c.f1977H, c0825c.f1978I, c0825c.f1979J, c0825c.f1980K, c0825c.f1981L, c0825c.f1982M, c0825c.f1983N, c0825c.f1984O, c0825c.f1985P, c0825c.f1986Q});
                }
                new Thread(new C0824b(context, str2)).start();
                f1941b = null;
            }
        }
    }

    /* renamed from: a */
    public static void m3187a(String str, Throwable th) {
        if (f1941b != null && th.getClass() != null) {
            f1941b.m3202a(str, th.getClass().getSimpleName(), th);
        }
    }

    /* renamed from: a */
    private static void m3186a(String str, String str2, Throwable th, String str3) {
        if (f1941b != null) {
            f1941b.m3201a(str, str2, C0825c.m3191a(th), str3);
        }
    }

    /* renamed from: a */
    public static void m3185a(String str, String str2, Throwable th) {
        if (f1941b != null) {
            f1941b.m3202a(str, str2, th);
        }
    }

    /* renamed from: a */
    public static void m3184a(String str, String str2, String str3) {
        if (f1941b != null) {
            f1941b.m3200a(str, str2, str3);
        }
    }
}
