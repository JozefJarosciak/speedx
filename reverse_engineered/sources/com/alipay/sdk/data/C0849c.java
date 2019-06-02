package com.alipay.sdk.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.widget.TextView;
import com.alipay.mobilesecuritysdk.face.SecurityClientMobile;
import com.alipay.sdk.app.statistic.C0823a;
import com.alipay.sdk.app.statistic.C0825c;
import com.alipay.sdk.cons.C0844a;
import com.alipay.sdk.cons.C0845b;
import com.alipay.sdk.sys.C0870b;
import com.alipay.sdk.tid.C0872b;
import com.alipay.sdk.util.C0873a;
import com.alipay.sdk.util.C0880h;
import com.alipay.sdk.util.C0883k;
import com.alipay.sdk.util.C0885l;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/* renamed from: com.alipay.sdk.data.c */
public final class C0849c {
    /* renamed from: d */
    private static final String f2097d = "virtualImeiAndImsi";
    /* renamed from: e */
    private static final String f2098e = "virtual_imei";
    /* renamed from: f */
    private static final String f2099f = "virtual_imsi";
    /* renamed from: g */
    private static C0849c f2100g;
    /* renamed from: a */
    public String f2101a;
    /* renamed from: b */
    public String f2102b = "sdk-and-lite";
    /* renamed from: c */
    public String f2103c;

    /* renamed from: c */
    private String m3271c() {
        return this.f2103c;
    }

    private C0849c() {
    }

    /* renamed from: a */
    public static synchronized C0849c m3265a() {
        C0849c c0849c;
        synchronized (C0849c.class) {
            if (f2100g == null) {
                f2100g = new C0849c();
            }
            c0849c = f2100g;
        }
        return c0849c;
    }

    /* renamed from: a */
    public final synchronized void m3278a(String str) {
        if (!TextUtils.isEmpty(str)) {
            PreferenceManager.getDefaultSharedPreferences(C0870b.m3386a().f2177a).edit().putString(C0845b.f2068i, str).commit();
            C0844a.f2046b = str;
        }
    }

    /* renamed from: d */
    private static String m3274d() {
        return C0844a.f2048d;
    }

    /* renamed from: a */
    private static String m3266a(Context context) {
        return Float.toString(new TextView(context).getTextSize());
    }

    /* renamed from: e */
    private static String m3275e() {
        return "-1;-1";
    }

    /* renamed from: a */
    private String m3268a(C0872b c0872b) {
        String e;
        String a;
        Context context = C0870b.m3386a().f2177a;
        C0873a a2 = C0873a.m3414a(context);
        if (TextUtils.isEmpty(this.f2101a)) {
            String a3 = C0885l.m3462a();
            String b = C0885l.m3471b();
            e = C0885l.m3481e(context);
            a = C0883k.m3457a(context);
            this.f2101a = "Msp/15.2.2" + " (" + a3 + C0880h.f2220b + b + C0880h.f2220b + e + C0880h.f2220b + a.substring(0, a.indexOf("://")) + C0880h.f2220b + C0885l.m3482f(context) + C0880h.f2220b + Float.toString(new TextView(context).getTextSize());
        }
        e = C0873a.m3416b(context).f2207p;
        a = "-1;-1";
        String str = C0844a.f2048d;
        String a4 = a2.m3422a();
        String b2 = a2.m3423b();
        Context context2 = C0870b.m3386a().f2177a;
        SharedPreferences sharedPreferences = context2.getSharedPreferences(f2097d, 0);
        CharSequence string = sharedPreferences.getString(f2099f, null);
        if (TextUtils.isEmpty(string)) {
            if (TextUtils.isEmpty(C0872b.m3405a().f2182a)) {
                Object c = C0870b.m3386a().m3392c();
                string = TextUtils.isEmpty(c) ? C0849c.m3269b() : c.substring(3, 18);
            } else {
                string = C0873a.m3414a(context2).m3422a();
            }
            sharedPreferences.edit().putString(f2099f, string).commit();
        }
        CharSequence charSequence = string;
        Context context3 = C0870b.m3386a().f2177a;
        SharedPreferences sharedPreferences2 = context3.getSharedPreferences(f2097d, 0);
        string = sharedPreferences2.getString(f2098e, null);
        if (TextUtils.isEmpty(string)) {
            if (TextUtils.isEmpty(C0872b.m3405a().f2182a)) {
                string = C0849c.m3269b();
            } else {
                string = C0873a.m3414a(context3).m3423b();
            }
            sharedPreferences2.edit().putString(f2098e, string).commit();
        }
        CharSequence charSequence2 = string;
        if (c0872b != null) {
            this.f2103c = c0872b.f2183b;
        }
        String replace = Build.MANUFACTURER.replace(C0880h.f2220b, " ");
        String replace2 = Build.MODEL.replace(C0880h.f2220b, " ");
        boolean b3 = C0870b.m3388b();
        String str2 = a2.f2186a;
        WifiInfo connectionInfo = ((WifiManager) context.getSystemService(MapboxEvent.ATTRIBUTE_WIFI)).getConnectionInfo();
        String ssid = connectionInfo != null ? connectionInfo.getSSID() : "-1";
        connectionInfo = ((WifiManager) context.getSystemService(MapboxEvent.ATTRIBUTE_WIFI)).getConnectionInfo();
        String bssid = connectionInfo != null ? connectionInfo.getBSSID() : "00";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.f2101a).append(C0880h.f2220b).append(e).append(C0880h.f2220b).append(a).append(C0880h.f2220b).append(str).append(C0880h.f2220b).append(a4).append(C0880h.f2220b).append(b2).append(C0880h.f2220b).append(this.f2103c).append(C0880h.f2220b).append(replace).append(C0880h.f2220b).append(replace2).append(C0880h.f2220b).append(b3).append(C0880h.f2220b).append(str2).append(";-1;-1;").append(this.f2102b).append(C0880h.f2220b).append(charSequence).append(C0880h.f2220b).append(charSequence2).append(C0880h.f2220b).append(ssid).append(C0880h.f2220b).append(bssid);
        if (c0872b != null) {
            HashMap hashMap = new HashMap();
            hashMap.put(C0845b.f2062c, c0872b.f2182a);
            hashMap.put(C0845b.f2066g, C0870b.m3386a().m3392c());
            c = m3279b(context, hashMap);
            if (!TextUtils.isEmpty(c)) {
                stringBuilder.append(C0880h.f2220b).append(c);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    /* renamed from: f */
    private static String m3276f() {
        Context context = C0870b.m3386a().f2177a;
        SharedPreferences sharedPreferences = context.getSharedPreferences(f2097d, 0);
        String string = sharedPreferences.getString(f2098e, null);
        if (TextUtils.isEmpty(string)) {
            if (TextUtils.isEmpty(C0872b.m3405a().f2182a)) {
                string = C0849c.m3269b();
            } else {
                string = C0873a.m3414a(context).m3423b();
            }
            sharedPreferences.edit().putString(f2098e, string).commit();
        }
        return string;
    }

    /* renamed from: g */
    private static String m3277g() {
        Context context = C0870b.m3386a().f2177a;
        SharedPreferences sharedPreferences = context.getSharedPreferences(f2097d, 0);
        String string = sharedPreferences.getString(f2099f, null);
        if (TextUtils.isEmpty(string)) {
            if (TextUtils.isEmpty(C0872b.m3405a().f2182a)) {
                Object c = C0870b.m3386a().m3392c();
                if (TextUtils.isEmpty(c)) {
                    string = C0849c.m3269b();
                } else {
                    string = c.substring(3, 18);
                }
            } else {
                string = C0873a.m3414a(context).m3422a();
            }
            sharedPreferences.edit().putString(f2099f, string).commit();
        }
        return string;
    }

    /* renamed from: b */
    public static String m3269b() {
        return Long.toHexString(System.currentTimeMillis()) + (new Random().nextInt(9000) + 1000);
    }

    /* renamed from: b */
    private static String m3270b(Context context) {
        WifiInfo connectionInfo = ((WifiManager) context.getSystemService(MapboxEvent.ATTRIBUTE_WIFI)).getConnectionInfo();
        if (connectionInfo != null) {
            return connectionInfo.getSSID();
        }
        return "-1";
    }

    /* renamed from: c */
    private static String m3272c(Context context) {
        WifiInfo connectionInfo = ((WifiManager) context.getSystemService(MapboxEvent.ATTRIBUTE_WIFI)).getConnectionInfo();
        if (connectionInfo != null) {
            return connectionInfo.getBSSID();
        }
        return "00";
    }

    /* renamed from: a */
    static String m3267a(Context context, HashMap<String, String> hashMap) {
        Object obj = "";
        try {
            obj = SecurityClientMobile.GetApdid(context, hashMap);
        } catch (Throwable th) {
            C0823a.m3185a(C0825c.f1955e, C0825c.f1957g, th);
        }
        if (TextUtils.isEmpty(obj)) {
            C0823a.m3184a(C0825c.f1955e, C0825c.f1958h, "apdid == null");
        }
        return obj;
    }

    /* renamed from: b */
    public final String m3279b(Context context, HashMap<String, String> hashMap) {
        Future submit = Executors.newFixedThreadPool(2).submit(new C0850d(this, context, hashMap));
        String str = "";
        try {
            return (String) submit.get(3000, TimeUnit.MILLISECONDS);
        } catch (Throwable th) {
            C0823a.m3185a(C0825c.f1955e, C0825c.f1959i, th);
            return str;
        }
    }
}
