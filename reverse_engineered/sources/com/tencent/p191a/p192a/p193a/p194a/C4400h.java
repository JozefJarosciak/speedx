package com.tencent.p191a.p192a.p193a.p194a;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.Log;
import ch.qos.logback.classic.net.SyslogAppender;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import com.tencent.wxop.stat.common.C4534f;
import io.rong.imlib.statistics.UserData;
import org.json.JSONObject;

/* renamed from: com.tencent.a.a.a.a.h */
public final class C4400h {
    /* renamed from: a */
    static String m17237a(Context context) {
        try {
            if (C4400h.m17240a(context, "android.permission.READ_PHONE_STATE")) {
                String deviceId = ((TelephonyManager) context.getSystemService(UserData.PHONE_KEY)).getDeviceId();
                if (deviceId != null) {
                    return deviceId;
                }
            }
            Log.i("MID", "Could not get permission of android.permission.READ_PHONE_STATE");
        } catch (Throwable th) {
            Log.w("MID", th);
        }
        return "";
    }

    /* renamed from: a */
    private static void m17238a(String str, Throwable th) {
        Log.e("MID", str, th);
    }

    /* renamed from: a */
    static void m17239a(JSONObject jSONObject, String str, String str2) {
        if (C4400h.m17242b(str2)) {
            jSONObject.put(str, str2);
        }
    }

    /* renamed from: a */
    static boolean m17240a(Context context, String str) {
        try {
            return context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
        } catch (Throwable th) {
            C4400h.m17238a("checkPermission error", th);
            return false;
        }
    }

    /* renamed from: b */
    static String m17241b(Context context) {
        if (C4400h.m17240a(context, "android.permission.ACCESS_WIFI_STATE")) {
            try {
                WifiManager wifiManager = (WifiManager) context.getSystemService(MapboxEvent.ATTRIBUTE_WIFI);
                return wifiManager == null ? "" : wifiManager.getConnectionInfo().getMacAddress();
            } catch (Exception e) {
                Log.i("MID", "get wifi address error" + e);
                return "";
            }
        }
        Log.i("MID", "Could not get permission of android.permission.ACCESS_WIFI_STATE");
        return "";
    }

    /* renamed from: b */
    static boolean m17242b(String str) {
        return (str == null || str.trim().length() == 0) ? false : true;
    }

    /* renamed from: c */
    public static boolean m17243c(String str) {
        return str != null && str.trim().length() >= 40;
    }

    /* renamed from: f */
    static String m17244f(String str) {
        if (str == null) {
            return null;
        }
        if (VERSION.SDK_INT < 8) {
            return str;
        }
        try {
            return new String(C4534f.m18028b(Base64.decode(str.getBytes("UTF-8"), 0)), "UTF-8").trim().replace(SyslogAppender.DEFAULT_STACKTRACE_PATTERN, "").replace("\n", "").replace("\r", "");
        } catch (Throwable th) {
            C4400h.m17238a("decode error", th);
            return str;
        }
    }

    /* renamed from: g */
    static String m17245g(String str) {
        if (str == null) {
            return null;
        }
        if (VERSION.SDK_INT < 8) {
            return str;
        }
        try {
            return new String(Base64.encode(C4534f.m18026a(str.getBytes("UTF-8")), 0), "UTF-8").trim().replace(SyslogAppender.DEFAULT_STACKTRACE_PATTERN, "").replace("\n", "").replace("\r", "");
        } catch (Throwable th) {
            C4400h.m17238a("decode error", th);
            return str;
        }
    }
}
