package com.beastbikes.android;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.webkit.CookieManager;
import com.alipay.sdk.util.C0880h;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.framework.android.p088g.C2803i;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

/* compiled from: RestfulAPI */
/* renamed from: com.beastbikes.android.c */
public class C1768c {
    /* renamed from: a */
    public static final String f8075a = (a$c.f7202c + "v3.0");
    /* renamed from: b */
    public static final String f8076b = a$c.m8389f();
    /* renamed from: c */
    public static final String f8077c = (a$c.f7200a + "/app/v2.4");

    /* renamed from: a */
    public static Map<String, String> m9391a(Context context) {
        Map<String, String> treeMap = new TreeMap();
        treeMap.put("User-Agent", C1768c.m9392b(context));
        treeMap.put("X-Client-Lang", Locale.getDefault().getLanguage());
        treeMap.put("Time-Zone", new SimpleDateFormat("Z", Locale.getDefault()).format(new Date()));
        if (AVUser.getCurrentUser() != null) {
            treeMap.put("COOKIE", "sessionid=" + AVUser.getCurrentUser().getSessionToken());
        }
        return treeMap;
    }

    /* renamed from: a */
    public static Map<String, String> m9390a() {
        Map<String, String> treeMap = new TreeMap();
        String str = "S605/" + VERSION.RELEASE;
        String str2 = "User-Agent";
        treeMap.put(str2, str + C0880h.f2220b + Build.FINGERPRINT + ";Beast/1.0_S605;");
        treeMap.put("X-Client-Lang", Locale.getDefault().getLanguage());
        treeMap.put("Time-Zone", new SimpleDateFormat("Z", Locale.getDefault()).format(new Date()));
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            treeMap.put("Authorization", "Token " + BeastBikes.j().getApplicationContext().getSharedPreferences(currentUser.getObjectId(), 0).getString("PREF.USER.SESSION.TOKEN", ""));
        }
        return treeMap;
    }

    /* renamed from: b */
    public static String m9392b(Context context) {
        String str = "Android/" + VERSION.RELEASE;
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();
        String str2 = Build.FINGERPRINT;
        String a = C1768c.m9389a(context, "Channel ID");
        try {
            str = str + C0880h.f2220b + str2 + ";Beast/" + packageManager.getPackageInfo(packageName, 0).versionName + "_" + a + C0880h.f2220b + C2803i.m13764a(context);
        } catch (Exception e) {
        }
        return str;
    }

    /* renamed from: a */
    private static String m9389a(Context context, String str) {
        String str2 = null;
        if (!(context == null || TextUtils.isEmpty(str))) {
            try {
                PackageManager packageManager = context.getPackageManager();
                if (packageManager != null) {
                    ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 128);
                    if (!(applicationInfo == null || applicationInfo.metaData == null)) {
                        str2 = applicationInfo.metaData.getString(str);
                    }
                }
            } catch (NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return str2;
    }

    /* renamed from: b */
    public static void m9393b() {
        CookieManager instance = CookieManager.getInstance();
        if (AVUser.getCurrentUser() != null) {
            StringBuilder stringBuilder = new StringBuilder("sessionid=");
            stringBuilder.append(AVUser.getCurrentUser().getSessionToken()).append(C0880h.f2220b);
            stringBuilder.append("domain=.speedx.com;");
            instance.setCookie(".speedx.com", stringBuilder.toString());
        }
    }
}
