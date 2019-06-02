package com.umeng.onlineconfig.proguard;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.wifi.WifiManager;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import com.umeng.onlineconfig.C4766a;
import com.umeng.onlineconfig.OnlineConfigLog;
import io.rong.imlib.statistics.UserData;

/* compiled from: OnlineConfigDeviceConfig */
/* renamed from: com.umeng.onlineconfig.proguard.g */
public class C4777g {
    /* renamed from: a */
    public static final String f16726a = "";
    /* renamed from: b */
    private static final String f16727b = C4777g.class.getName();

    /* renamed from: a */
    public static boolean m18747a(Context context, String str) {
        if (context.getPackageManager().checkPermission(str, context.getPackageName()) != 0) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    public static String m18746a(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null) {
                String string = applicationInfo.metaData.getString("UMENG_APPKEY");
                if (string != null) {
                    return string.trim();
                }
                OnlineConfigLog.m18730e(f16727b, "Could not read UMENG_APPKEY meta-data from AndroidManifest.xml.");
            }
        } catch (Exception e) {
            OnlineConfigLog.m18731e(f16727b, "Could not read UMENG_APPKEY meta-data from AndroidManifest.xml.", e);
        }
        return null;
    }

    /* renamed from: b */
    public static String m18748b(Context context) {
        try {
            return String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
        } catch (NameNotFoundException e) {
            return "";
        }
    }

    /* renamed from: c */
    public static String m18749c(Context context) {
        String str = "Unknown";
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (!(applicationInfo == null || applicationInfo.metaData == null)) {
                Object obj = applicationInfo.metaData.get("UMENG_CHANNEL");
                if (obj != null) {
                    String obj2 = obj.toString();
                    if (obj2 != null) {
                        return obj2;
                    }
                    OnlineConfigLog.m18732i(f16727b, "Could not read UMENG_CHANNEL meta-data from AndroidManifest.xml.");
                }
            }
        } catch (Exception e) {
            OnlineConfigLog.m18732i(f16727b, "Could not read UMENG_CHANNEL meta-data from AndroidManifest.xml.");
            e.printStackTrace();
        }
        return str;
    }

    /* renamed from: d */
    public static String m18750d(Context context) {
        String deviceId;
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(UserData.PHONE_KEY);
        if (telephonyManager == null) {
            OnlineConfigLog.m18736w(f16727b, "No IMEI.");
        }
        String str = "";
        try {
            if (C4777g.m18747a(context, "android.permission.READ_PHONE_STATE")) {
                deviceId = telephonyManager.getDeviceId();
                if (TextUtils.isEmpty(deviceId)) {
                    return deviceId;
                }
                OnlineConfigLog.m18736w(f16727b, "No IMEI.");
                deviceId = C4777g.m18751e(context);
                if (TextUtils.isEmpty(deviceId)) {
                    return deviceId;
                }
                OnlineConfigLog.m18736w(f16727b, "Failed to take mac as IMEI. Try to use Secure.ANDROID_ID instead.");
                deviceId = Secure.getString(context.getContentResolver(), "android_id");
                OnlineConfigLog.m18732i(f16727b, "getDeviceId: Secure.ANDROID_ID: " + deviceId);
                return deviceId;
            }
        } catch (Exception e) {
            OnlineConfigLog.m18737w(f16727b, "No IMEI.", e);
        }
        deviceId = str;
        if (TextUtils.isEmpty(deviceId)) {
            return deviceId;
        }
        OnlineConfigLog.m18736w(f16727b, "No IMEI.");
        deviceId = C4777g.m18751e(context);
        if (TextUtils.isEmpty(deviceId)) {
            return deviceId;
        }
        OnlineConfigLog.m18736w(f16727b, "Failed to take mac as IMEI. Try to use Secure.ANDROID_ID instead.");
        deviceId = Secure.getString(context.getContentResolver(), "android_id");
        OnlineConfigLog.m18732i(f16727b, "getDeviceId: Secure.ANDROID_ID: " + deviceId);
        return deviceId;
    }

    /* renamed from: e */
    public static String m18751e(Context context) {
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService(MapboxEvent.ATTRIBUTE_WIFI);
            if (C4777g.m18747a(context, "android.permission.ACCESS_WIFI_STATE")) {
                return wifiManager.getConnectionInfo().getMacAddress();
            }
            OnlineConfigLog.m18736w(f16727b, "Could not get mac address.[no permission android.permission.ACCESS_WIFI_STATE");
            return "";
        } catch (Exception e) {
            OnlineConfigLog.m18736w(f16727b, "Could not get mac address." + e.toString());
        }
    }

    /* renamed from: f */
    public static String m18752f(Context context) {
        return context.getPackageName();
    }

    /* renamed from: a */
    public static String m18745a() {
        return C4766a.f16699b;
    }

    /* renamed from: g */
    public static boolean m18753g(Context context) {
        try {
            return (context.getApplicationInfo().flags & 2) != 0;
        } catch (Exception e) {
            return false;
        }
    }
}
