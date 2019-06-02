package com.alipay.sdk.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebSettings.TextSize;
import android.webkit.WebView;
import android.widget.LinearLayout;
import com.alibaba.fastjson.asm.Opcodes;
import com.alipay.sdk.app.C0821h;
import com.alipay.sdk.app.C0822i;
import com.alipay.sdk.app.statistic.C0823a;
import com.alipay.sdk.app.statistic.C0825c;
import com.alipay.sdk.cons.C0844a;
import com.alipay.sdk.sys.C0869a;
import com.j256.ormlite.stmt.query.SimpleComparison;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.cli.HelpFormatter;
import org.slf4j.Marker;

@SuppressLint({"SetJavaScriptEnabled", "DefaultLocale"})
/* renamed from: com.alipay.sdk.util.l */
public final class C0885l {
    /* renamed from: a */
    static final String f2233a = "com.alipay.android.app";
    /* renamed from: b */
    public static final String f2234b = "com.eg.android.AlipayGphone";
    /* renamed from: c */
    public static final int f2235c = 99;
    /* renamed from: d */
    public static final int f2236d = 73;

    /* renamed from: com.alipay.sdk.util.l$a */
    public static class C0884a {
        /* renamed from: a */
        public Signature[] f2231a;
        /* renamed from: b */
        public int f2232b;

        /* renamed from: a */
        public final boolean m3459a() {
            if (this.f2231a == null || this.f2231a.length <= 0) {
                return false;
            }
            int i = 0;
            while (i < this.f2231a.length) {
                String a = C0885l.m3464a(this.f2231a[i].toByteArray());
                if (a == null || TextUtils.equals(a, C0844a.f2051g)) {
                    i++;
                } else {
                    C0823a.m3184a(C0825c.f1952b, C0825c.f1970t, a);
                    return true;
                }
            }
            return false;
        }
    }

    /* renamed from: a */
    public static Map<String, String> m3465a(String str) {
        Map<String, String> hashMap = new HashMap();
        for (String str2 : str.split(C0869a.f2161b)) {
            int indexOf = str2.indexOf(SimpleComparison.EQUAL_TO_OPERATION, 1);
            hashMap.put(str2.substring(0, indexOf), URLDecoder.decode(str2.substring(indexOf + 1)));
        }
        return hashMap;
    }

    /* renamed from: a */
    public static String m3463a(String str, String str2, String str3) {
        try {
            int length = str.length() + str3.indexOf(str);
            if (length <= str.length()) {
                return "";
            }
            int i = 0;
            if (!TextUtils.isEmpty(str2)) {
                i = str3.indexOf(str2, length);
            }
            if (i <= 0) {
                return str3.substring(length);
            }
            return str3.substring(length, i);
        } catch (Throwable th) {
            return "";
        }
    }

    /* renamed from: a */
    public static String m3464a(byte[] bArr) {
        try {
            String obj = ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(bArr))).getPublicKey().toString();
            if (obj.indexOf("modulus") != -1) {
                return obj.substring(obj.indexOf("modulus") + 8, obj.lastIndexOf(",")).trim();
            }
        } catch (Throwable e) {
            C0823a.m3185a(C0825c.f1954d, C0825c.f1964n, e);
        }
        return null;
    }

    /* renamed from: i */
    private static C0884a m3485i(Context context) {
        return C0885l.m3461a(context, f2234b);
    }

    /* renamed from: a */
    public static C0884a m3461a(Context context, String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, Opcodes.CHECKCAST);
            if (!C0885l.m3467a(packageInfo)) {
                try {
                    packageInfo = C0885l.m3474c(context, str);
                } catch (Throwable th) {
                    C0823a.m3185a(C0825c.f1954d, C0825c.f1963m, th);
                }
            }
        } catch (Throwable th2) {
            C0823a.m3185a(C0825c.f1954d, C0825c.f1963m, th2);
            packageInfo = null;
        }
        if (!C0885l.m3467a(packageInfo) || packageInfo == null) {
            return null;
        }
        C0884a c0884a = new C0884a();
        c0884a.f2231a = packageInfo.signatures;
        c0884a.f2232b = packageInfo.versionCode;
        return c0884a;
    }

    /* renamed from: a */
    private static boolean m3467a(PackageInfo packageInfo) {
        String str = "";
        boolean z = false;
        if (packageInfo == null) {
            str = str + "info == null";
        } else if (packageInfo.signatures == null) {
            str = str + "info.signatures == null";
        } else if (packageInfo.signatures.length <= 0) {
            str = str + "info.signatures.length <= 0";
        } else {
            z = true;
        }
        if (!z) {
            C0823a.m3184a(C0825c.f1954d, C0825c.f1962l, str);
        }
        return z;
    }

    /* renamed from: b */
    private static PackageInfo m3469b(Context context, String str) throws NameNotFoundException {
        return context.getPackageManager().getPackageInfo(str, Opcodes.CHECKCAST);
    }

    /* renamed from: c */
    private static PackageInfo m3474c(Context context, String str) {
        for (PackageInfo packageInfo : context.getPackageManager().getInstalledPackages(Opcodes.CHECKCAST)) {
            if (packageInfo.packageName.equals(str)) {
                return packageInfo;
            }
        }
        return null;
    }

    /* renamed from: b */
    private static C0884a m3470b(PackageInfo packageInfo) {
        if (packageInfo == null) {
            return null;
        }
        C0884a c0884a = new C0884a();
        c0884a.f2231a = packageInfo.signatures;
        c0884a.f2232b = packageInfo.versionCode;
        return c0884a;
    }

    /* renamed from: a */
    public static boolean m3466a(Context context) {
        try {
            if (context.getPackageManager().getPackageInfo(f2233a, 128) == null) {
                return false;
            }
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    /* renamed from: b */
    public static boolean m3472b(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(f2234b, 128);
            if (packageInfo != null && packageInfo.versionCode > 73) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            C0823a.m3185a(C0825c.f1952b, C0825c.f1945B, th);
            return false;
        }
    }

    /* renamed from: c */
    public static boolean m3476c(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(f2234b, 128);
            if (packageInfo != null && packageInfo.versionCode < 99) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            return false;
        }
    }

    /* renamed from: d */
    public static String m3479d(Context context) {
        String a = C0885l.m3462a();
        String b = C0885l.m3471b();
        String e = C0885l.m3481e(context);
        return " (" + a + C0880h.f2220b + b + C0880h.f2220b + e + ";;" + C0885l.m3482f(context) + ")(sdk android)";
    }

    /* renamed from: a */
    public static String m3462a() {
        return "Android " + VERSION.RELEASE;
    }

    /* renamed from: a */
    public static WebView m3460a(Activity activity, String str, String str2) {
        Method method;
        if (!TextUtils.isEmpty(str2)) {
            CookieSyncManager.createInstance(activity.getApplicationContext()).sync();
            CookieManager.getInstance().setCookie(str, str2);
            CookieSyncManager.getInstance().sync();
        }
        View linearLayout = new LinearLayout(activity);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        linearLayout.setOrientation(1);
        activity.setContentView(linearLayout, layoutParams);
        View webView = new WebView(activity);
        layoutParams.weight = 1.0f;
        webView.setVisibility(0);
        linearLayout.addView(webView, layoutParams);
        WebSettings settings = webView.getSettings();
        settings.setUserAgentString(settings.getUserAgentString() + C0885l.m3479d(activity));
        settings.setRenderPriority(RenderPriority.HIGH);
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptEnabled(true);
        settings.setSavePassword(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setMinimumFontSize(settings.getMinimumFontSize() + 8);
        settings.setAllowFileAccess(false);
        settings.setTextSize(TextSize.NORMAL);
        webView.setVerticalScrollbarOverlay(true);
        webView.setDownloadListener(new C0886m(activity));
        if (VERSION.SDK_INT >= 7) {
            try {
                method = webView.getSettings().getClass().getMethod("setDomStorageEnabled", new Class[]{Boolean.TYPE});
                if (method != null) {
                    method.invoke(webView.getSettings(), new Object[]{Boolean.valueOf(true)});
                }
            } catch (Exception e) {
            }
        }
        try {
            webView.removeJavascriptInterface("searchBoxJavaBridge_");
            webView.removeJavascriptInterface("accessibility");
            webView.removeJavascriptInterface("accessibilityTraversal");
        } catch (Throwable th) {
        }
        if (VERSION.SDK_INT >= 19) {
            webView.getSettings().setCacheMode(1);
        }
        webView.loadUrl(str);
        return webView;
    }

    /* renamed from: b */
    public static String m3471b() {
        String d = C0885l.m3478d();
        int indexOf = d.indexOf(HelpFormatter.DEFAULT_OPT_PREFIX);
        if (indexOf != -1) {
            d = d.substring(0, indexOf);
        }
        indexOf = d.indexOf("\n");
        if (indexOf != -1) {
            d = d.substring(0, indexOf);
        }
        return "Linux " + d;
    }

    /* renamed from: d */
    private static String m3478d() {
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader("/proc/version"), 256);
            CharSequence readLine = bufferedReader.readLine();
            bufferedReader.close();
            Matcher matcher = Pattern.compile("\\w+\\s+\\w+\\s+([^\\s]+)\\s+\\(([^\\s@]+(?:@[^\\s.]+)?)[^)]*\\)\\s+\\((?:[^(]*\\([^)]*\\))?[^)]*\\)\\s+([^\\s]+)\\s+(?:PREEMPT\\s+)?(.+)").matcher(readLine);
            if (!matcher.matches()) {
                return "Unavailable";
            }
            if (matcher.groupCount() < 4) {
                return "Unavailable";
            }
            return new StringBuilder(matcher.group(1)).append("\n").append(matcher.group(2)).append(" ").append(matcher.group(3)).append("\n").append(matcher.group(4)).toString();
        } catch (IOException e) {
            return "Unavailable";
        } catch (Throwable th) {
            bufferedReader.close();
        }
    }

    /* renamed from: e */
    public static String m3481e(Context context) {
        return context.getResources().getConfiguration().locale.toString();
    }

    /* renamed from: f */
    public static String m3482f(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(displayMetrics.widthPixels);
        stringBuilder.append(Marker.ANY_MARKER);
        stringBuilder.append(displayMetrics.heightPixels);
        return stringBuilder.toString();
    }

    /* renamed from: j */
    private static DisplayMetrics m3486j(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    /* renamed from: k */
    private static String m3487k(Context context) {
        String a = C0883k.m3457a(context);
        return a.substring(0, a.indexOf("://"));
    }

    /* renamed from: e */
    private static String m3480e() {
        return "-1;-1";
    }

    /* renamed from: c */
    public static String m3475c() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 24; i++) {
            switch (random.nextInt(3)) {
                case 0:
                    stringBuilder.append(String.valueOf((char) ((int) Math.round((Math.random() * 25.0d) + 65.0d))));
                    break;
                case 1:
                    stringBuilder.append(String.valueOf((char) ((int) Math.round((Math.random() * 25.0d) + 97.0d))));
                    break;
                case 2:
                    stringBuilder.append(String.valueOf(new Random().nextInt(10)));
                    break;
                default:
                    break;
            }
        }
        return stringBuilder.toString();
    }

    /* renamed from: b */
    public static boolean m3473b(String str) {
        return Pattern.compile("^http(s)?://([a-z0-9_\\-]+\\.)*(alipay|taobao)\\.(com|net)(:\\d+)?(/.*)?$").matcher(str).matches();
    }

    /* renamed from: g */
    public static String m3483g(Context context) {
        String str = "";
        try {
            for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
                if (runningAppProcessInfo.processName.equals(f2234b)) {
                    str = str + "#M";
                } else {
                    String str2;
                    if (runningAppProcessInfo.processName.startsWith("com.eg.android.AlipayGphone:")) {
                        str2 = str + "#" + runningAppProcessInfo.processName.replace("com.eg.android.AlipayGphone:", "");
                    } else {
                        str2 = str;
                    }
                    str = str2;
                }
            }
        } catch (Throwable th) {
            str = "";
        }
        if (str.length() > 0) {
            str = str.substring(1);
        }
        if (str.length() == 0) {
            return "N";
        }
        return str;
    }

    /* renamed from: h */
    public static String m3484h(Context context) {
        try {
            List installedPackages = context.getPackageManager().getInstalledPackages(0);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < installedPackages.size(); i++) {
                Object obj;
                PackageInfo packageInfo = (PackageInfo) installedPackages.get(i);
                int i2 = packageInfo.applicationInfo.flags;
                if ((i2 & 1) == 0 && (i2 & 128) == 0) {
                    obj = 1;
                } else {
                    obj = null;
                }
                if (obj != null) {
                    if (packageInfo.packageName.equals(f2234b)) {
                        stringBuilder.append(packageInfo.packageName).append(packageInfo.versionCode).append(HelpFormatter.DEFAULT_OPT_PREFIX);
                    } else if (!(packageInfo.packageName.contains("theme") || packageInfo.packageName.startsWith("com.google.") || packageInfo.packageName.startsWith("com.android."))) {
                        stringBuilder.append(packageInfo.packageName).append(HelpFormatter.DEFAULT_OPT_PREFIX);
                    }
                }
            }
            return stringBuilder.toString();
        } catch (Throwable th) {
            C0823a.m3185a(C0825c.f1952b, C0825c.f1947D, th);
            return "";
        }
    }

    @SuppressLint({"InlinedApi"})
    /* renamed from: c */
    private static boolean m3477c(PackageInfo packageInfo) {
        int i = packageInfo.applicationInfo.flags;
        return (i & 1) == 0 && (i & 128) == 0;
    }

    /* renamed from: a */
    public static boolean m3468a(WebView webView, String str, Activity activity) {
        if (!TextUtils.isEmpty(str)) {
            if (str.toLowerCase().startsWith(C0844a.f2052h.toLowerCase()) || str.toLowerCase().startsWith(C0844a.f2053i.toLowerCase())) {
                try {
                    C0884a a = C0885l.m3461a(activity, f2234b);
                    if (!(a == null || a.m3459a())) {
                        if (str.startsWith("intent://platformapi/startapp")) {
                            str = str.replaceFirst("intent://platformapi/startapp\\?", C0844a.f2052h);
                        }
                        activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                    }
                } catch (Throwable th) {
                }
            } else if (TextUtils.equals(str, C0844a.f2055k) || TextUtils.equals(str, C0844a.f2056l)) {
                C0821h.f1929a = C0821h.m3171a();
                activity.finish();
            } else if (str.startsWith(C0844a.f2054j)) {
                C0822i a2;
                try {
                    String substring = str.substring(str.indexOf(C0844a.f2054j) + 24);
                    int parseInt = Integer.parseInt(substring.substring(substring.lastIndexOf(C0844a.f2057m) + 10));
                    if (parseInt == C0822i.SUCCEEDED.f1938h || parseInt == C0822i.PAY_WAITTING.f1938h) {
                        String decode = URLDecoder.decode(str);
                        decode = decode.substring(decode.indexOf(C0844a.f2054j) + 24, decode.lastIndexOf(C0844a.f2057m));
                        a2 = C0822i.m3178a(parseInt);
                        C0821h.f1929a = C0821h.m3172a(a2.f1938h, a2.f1939i, decode);
                        activity.runOnUiThread(new C0887n(activity));
                    } else {
                        a2 = C0822i.m3178a(C0822i.FAILED.f1938h);
                        C0821h.f1929a = C0821h.m3172a(a2.f1938h, a2.f1939i, "");
                        activity.runOnUiThread(new C0887n(activity));
                    }
                } catch (Exception e) {
                    a2 = C0822i.m3178a(C0822i.PARAMS_ERROR.f1938h);
                    C0821h.f1929a = C0821h.m3172a(a2.f1938h, a2.f1939i, "");
                }
            } else {
                webView.loadUrl(str);
            }
        }
        return true;
    }
}
