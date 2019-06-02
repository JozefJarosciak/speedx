package com.alipay.sdk.app.statistic;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.alipay.sdk.cons.C0844a;
import com.alipay.sdk.sys.C0869a;
import com.alipay.sdk.sys.C0870b;
import com.alipay.sdk.tid.C0872b;
import com.alipay.sdk.util.C0873a;
import com.j256.ormlite.stmt.query.SimpleComparison;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.cli.HelpFormatter;

/* renamed from: com.alipay.sdk.app.statistic.c */
public final class C0825c {
    /* renamed from: A */
    public static final String f1944A = "BindWaitTimeoutEx";
    /* renamed from: B */
    public static final String f1945B = "CheckClientExistEx";
    /* renamed from: C */
    public static final String f1946C = "CheckClientSignEx";
    /* renamed from: D */
    public static final String f1947D = "GetInstalledAppEx";
    /* renamed from: E */
    public static final String f1948E = "partner";
    /* renamed from: F */
    public static final String f1949F = "out_trade_no";
    /* renamed from: G */
    public static final String f1950G = "trade_no";
    /* renamed from: a */
    public static final String f1951a = "net";
    /* renamed from: b */
    public static final String f1952b = "biz";
    /* renamed from: c */
    public static final String f1953c = "cp";
    /* renamed from: d */
    public static final String f1954d = "auth";
    /* renamed from: e */
    public static final String f1955e = "third";
    /* renamed from: f */
    public static final String f1956f = "FormatResultEx";
    /* renamed from: g */
    public static final String f1957g = "GetApdidEx";
    /* renamed from: h */
    public static final String f1958h = "GetApdidNull";
    /* renamed from: i */
    public static final String f1959i = "GetApdidTimeout";
    /* renamed from: j */
    public static final String f1960j = "GetUtdidEx";
    /* renamed from: k */
    public static final String f1961k = "GetPackageInfoEx";
    /* renamed from: l */
    public static final String f1962l = "NotIncludeSignatures";
    /* renamed from: m */
    public static final String f1963m = "GetInstalledPackagesEx";
    /* renamed from: n */
    public static final String f1964n = "GetPublicKeyFromSignEx";
    /* renamed from: o */
    public static final String f1965o = "H5PayNetworkError";
    /* renamed from: p */
    public static final String f1966p = "H5AuthNetworkError";
    /* renamed from: q */
    public static final String f1967q = "SSLError";
    /* renamed from: r */
    public static final String f1968r = "H5PayDataAnalysisError";
    /* renamed from: s */
    public static final String f1969s = "H5AuthDataAnalysisError";
    /* renamed from: t */
    public static final String f1970t = "PublicKeyUnmatch";
    /* renamed from: u */
    public static final String f1971u = "ClientBindFailed";
    /* renamed from: v */
    public static final String f1972v = "TriDesEncryptError";
    /* renamed from: w */
    public static final String f1973w = "TriDesDecryptError";
    /* renamed from: x */
    public static final String f1974x = "ClientBindException";
    /* renamed from: y */
    public static final String f1975y = "SaveTradeTokenError";
    /* renamed from: z */
    public static final String f1976z = "ClientBindServiceFailed";
    /* renamed from: H */
    String f1977H;
    /* renamed from: I */
    String f1978I;
    /* renamed from: J */
    String f1979J;
    /* renamed from: K */
    String f1980K;
    /* renamed from: L */
    String f1981L;
    /* renamed from: M */
    String f1982M;
    /* renamed from: N */
    String f1983N;
    /* renamed from: O */
    String f1984O;
    /* renamed from: P */
    String f1985P = "";
    /* renamed from: Q */
    String f1986Q;

    public C0825c(Context context) {
        String format = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(new Date());
        this.f1977H = String.format("123456789,%s", new Object[]{format});
        this.f1979J = C0825c.m3189a(context);
        format = C0825c.m3190a(C0844a.f2049e);
        String a = C0825c.m3190a(C0844a.f2050f);
        this.f1980K = String.format("android,3,%s,%s,com.alipay.mcpay,5.0,-,-,-", new Object[]{format, a});
        format = C0825c.m3190a(C0872b.m3405a().f2182a);
        a = C0825c.m3190a(C0870b.m3386a().m3392c());
        this.f1981L = String.format("%s,%s,-,-,-", new Object[]{format, a});
        format = C0825c.m3190a(C0873a.m3421d(context));
        String a2 = C0825c.m3190a(VERSION.RELEASE);
        String a3 = C0825c.m3190a(Build.MODEL);
        String str = HelpFormatter.DEFAULT_OPT_PREFIX;
        String a4 = C0825c.m3190a(C0873a.m3414a(context).m3422a());
        String a5 = C0825c.m3190a(C0873a.m3416b(context).f2207p);
        String a6 = C0825c.m3190a(C0873a.m3414a(context).m3423b());
        this.f1982M = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,-", new Object[]{format, "android", a2, a3, str, a4, a5, "gw", a6});
        this.f1983N = HelpFormatter.DEFAULT_OPT_PREFIX;
        this.f1984O = HelpFormatter.DEFAULT_OPT_PREFIX;
        this.f1986Q = HelpFormatter.DEFAULT_OPT_PREFIX;
    }

    /* renamed from: a */
    private boolean m3193a() {
        return TextUtils.isEmpty(this.f1985P);
    }

    /* renamed from: a */
    public final void m3202a(String str, String str2, Throwable th) {
        m3200a(str, str2, C0825c.m3191a(th));
    }

    /* renamed from: a */
    private void m3192a(String str, String str2, Throwable th, String str3) {
        m3201a(str, str2, C0825c.m3191a(th), str3);
    }

    /* renamed from: a */
    public final void m3201a(String str, String str2, String str3, String str4) {
        String str5 = "";
        if (!TextUtils.isEmpty(this.f1985P)) {
            str5 = str5 + "^";
        }
        this.f1985P += (str5 + String.format("%s,%s,%s,%s", new Object[]{str, str2, C0825c.m3190a(str3), str4}));
    }

    /* renamed from: a */
    public final void m3200a(String str, String str2, String str3) {
        m3201a(str, str2, str3, HelpFormatter.DEFAULT_OPT_PREFIX);
    }

    /* renamed from: a */
    static String m3190a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return str.replace("[", "【").replace("]", "】").replace("(", "（").replace(")", "）").replace(",", "，").replace(HelpFormatter.DEFAULT_OPT_PREFIX, SimpleComparison.EQUAL_TO_OPERATION).replace("^", "~");
    }

    /* renamed from: a */
    static String m3191a(Throwable th) {
        if (th == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        try {
            stringBuffer.append(th.getClass().getName()).append(":");
            stringBuffer.append(th.getMessage());
            stringBuffer.append(" 》 ");
            StackTraceElement[] stackTrace = th.getStackTrace();
            if (stackTrace != null) {
                for (StackTraceElement stackTraceElement : stackTrace) {
                    stringBuffer.append(stackTraceElement.toString() + " 》 ");
                }
            }
        } catch (Throwable th2) {
        }
        return stringBuffer.toString();
    }

    /* renamed from: b */
    private String m3196b(String str) {
        String str2 = null;
        if (TextUtils.isEmpty(this.f1985P)) {
            return "";
        }
        String str3;
        String[] split = str.split(C0869a.f2161b);
        if (split != null) {
            str3 = null;
            for (String split2 : split) {
                String[] split3 = split2.split(SimpleComparison.EQUAL_TO_OPERATION);
                if (split3 != null && split3.length == 2) {
                    if (split3[0].equalsIgnoreCase(f1948E)) {
                        split3[1].replace("\"", "");
                    } else if (split3[0].equalsIgnoreCase(f1949F)) {
                        str3 = split3[1].replace("\"", "");
                    } else if (split3[0].equalsIgnoreCase(f1950G)) {
                        str2 = split3[1].replace("\"", "");
                    }
                }
            }
        } else {
            str3 = null;
        }
        str2 = C0825c.m3190a(str2);
        String a = C0825c.m3190a(C0825c.m3190a(str3));
        this.f1978I = String.format("%s,%s,-,%s,-,-,-", new Object[]{str2, str3, a});
        return String.format("[(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s)]", new Object[]{this.f1977H, this.f1978I, this.f1979J, this.f1980K, this.f1981L, this.f1982M, this.f1983N, this.f1984O, this.f1985P, this.f1986Q});
    }

    @SuppressLint({"SimpleDateFormat"})
    /* renamed from: b */
    private static String m3194b() {
        String format = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(new Date());
        return String.format("123456789,%s", new Object[]{format});
    }

    /* renamed from: c */
    private static String m3198c(String str) {
        String str2;
        String str3 = null;
        String[] split = str.split(C0869a.f2161b);
        if (split != null) {
            str2 = null;
            for (String split2 : split) {
                String[] split3 = split2.split(SimpleComparison.EQUAL_TO_OPERATION);
                if (split3 != null && split3.length == 2) {
                    if (split3[0].equalsIgnoreCase(f1948E)) {
                        split3[1].replace("\"", "");
                    } else if (split3[0].equalsIgnoreCase(f1949F)) {
                        str2 = split3[1].replace("\"", "");
                    } else if (split3[0].equalsIgnoreCase(f1950G)) {
                        str3 = split3[1].replace("\"", "");
                    }
                }
            }
        } else {
            str2 = null;
        }
        str3 = C0825c.m3190a(str3);
        String a = C0825c.m3190a(C0825c.m3190a(str2));
        return String.format("%s,%s,-,%s,-,-,-", new Object[]{str3, C0825c.m3190a(str2), a});
    }

    /* renamed from: a */
    private static String m3189a(Context context) {
        String str = HelpFormatter.DEFAULT_OPT_PREFIX;
        String str2 = HelpFormatter.DEFAULT_OPT_PREFIX;
        if (context != null) {
            try {
                Context applicationContext = context.getApplicationContext();
                str = applicationContext.getPackageName();
                str2 = applicationContext.getPackageManager().getPackageInfo(str, 0).versionName;
            } catch (Throwable th) {
            }
        }
        return String.format("%s,%s,-,-,-", new Object[]{str, str2});
    }

    /* renamed from: c */
    private static String m3197c() {
        String a = C0825c.m3190a(C0844a.f2049e);
        String a2 = C0825c.m3190a(C0844a.f2050f);
        return String.format("android,3,%s,%s,com.alipay.mcpay,5.0,-,-,-", new Object[]{a, a2});
    }

    /* renamed from: d */
    private static String m3199d() {
        String a = C0825c.m3190a(C0872b.m3405a().f2182a);
        String a2 = C0825c.m3190a(C0870b.m3386a().m3392c());
        return String.format("%s,%s,-,-,-", new Object[]{a, a2});
    }

    /* renamed from: b */
    private static String m3195b(Context context) {
        String a = C0825c.m3190a(C0873a.m3421d(context));
        String a2 = C0825c.m3190a(VERSION.RELEASE);
        String a3 = C0825c.m3190a(Build.MODEL);
        String str = HelpFormatter.DEFAULT_OPT_PREFIX;
        String a4 = C0825c.m3190a(C0873a.m3414a(context).m3422a());
        String a5 = C0825c.m3190a(C0873a.m3416b(context).f2207p);
        String a6 = C0825c.m3190a(C0873a.m3414a(context).m3423b());
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,-", new Object[]{a, "android", a2, a3, str, a4, a5, "gw", a6});
    }
}
