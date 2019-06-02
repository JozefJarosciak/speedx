package com.alipay.p029b.p030a.p031a.p034b;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import com.alipay.p029b.p030a.p031a.p032a.C0789a;
import io.rong.imlib.statistics.UserData;
import java.io.File;

/* renamed from: com.alipay.b.a.a.b.d */
public final class C0794d {
    /* renamed from: a */
    private static C0794d f1857a = new C0794d();

    private C0794d() {
    }

    /* renamed from: a */
    public static C0794d m3078a() {
        return f1857a;
    }

    /* renamed from: a */
    private static String m3079a(String str, String str2) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{String.class, String.class}).invoke(null, new Object[]{str, str2});
        } catch (Exception e) {
            return str2;
        }
    }

    /* renamed from: a */
    public static boolean m3080a(Context context) {
        try {
            if (Build.HARDWARE.contains("goldfish") || Build.PRODUCT.contains("sdk") || Build.FINGERPRINT.contains("generic")) {
                return true;
            }
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(UserData.PHONE_KEY);
            if (telephonyManager != null) {
                Object obj;
                String deviceId = telephonyManager.getDeviceId();
                if (deviceId != null) {
                    int length = deviceId.length();
                    if (length != 0) {
                        int i = 0;
                        while (i < length) {
                            if (!Character.isWhitespace(deviceId.charAt(i)) && deviceId.charAt(i) != '0') {
                                obj = null;
                                break;
                            }
                            i++;
                        }
                        i = 1;
                        if (obj != null) {
                            return true;
                        }
                    }
                }
                obj = 1;
                if (obj != null) {
                    return true;
                }
            }
            return C0789a.m3020a(Secure.getString(context.getContentResolver(), "android_id"));
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: b */
    public static String m3081b() {
        return "android";
    }

    /* renamed from: c */
    public static boolean m3082c() {
        String[] strArr = new String[]{"/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/"};
        int i = 0;
        while (i < 5) {
            try {
                if (new File(strArr[i] + "su").exists()) {
                    return true;
                }
                i++;
            } catch (Exception e) {
            }
        }
        return false;
    }

    /* renamed from: d */
    public static String m3083d() {
        return Build.BOARD;
    }

    /* renamed from: e */
    public static String m3084e() {
        return Build.BRAND;
    }

    /* renamed from: f */
    public static String m3085f() {
        return Build.DEVICE;
    }

    /* renamed from: g */
    public static String m3086g() {
        return Build.DISPLAY;
    }

    /* renamed from: h */
    public static String m3087h() {
        return VERSION.INCREMENTAL;
    }

    /* renamed from: i */
    public static String m3088i() {
        return Build.MANUFACTURER;
    }

    /* renamed from: j */
    public static String m3089j() {
        return Build.MODEL;
    }

    /* renamed from: k */
    public static String m3090k() {
        return Build.PRODUCT;
    }

    /* renamed from: l */
    public static String m3091l() {
        return VERSION.RELEASE;
    }

    /* renamed from: m */
    public static String m3092m() {
        return VERSION.SDK;
    }

    /* renamed from: n */
    public static String m3093n() {
        return Build.TAGS;
    }

    /* renamed from: o */
    public static String m3094o() {
        return C0794d.m3079a("ro.kernel.qemu", "0");
    }
}
