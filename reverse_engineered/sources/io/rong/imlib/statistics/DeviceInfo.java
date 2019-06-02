package io.rong.imlib.statistics;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import com.alipay.sdk.packet.C0861d;
import com.avos.avoscloud.AVException;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import com.umeng.onlineconfig.OnlineConfigAgent;
import io.rong.imlib.common.BuildVar;
import io.rong.imlib.common.DeviceUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Locale;
import java.util.TimeZone;
import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

class DeviceInfo {
    DeviceInfo() {
    }

    static String getOS() {
        return "Android";
    }

    static String getOSVersion() {
        return VERSION.RELEASE;
    }

    static String getDevice() {
        return Build.MODEL;
    }

    static String getResolution(Context context) {
        String str = "";
        try {
            Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            defaultDisplay.getMetrics(displayMetrics);
            return displayMetrics.widthPixels + "x" + displayMetrics.heightPixels;
        } catch (Throwable th) {
            if (Statistics.sharedInstance().isLoggingEnabled()) {
                Log.i("Statistics", "Device resolution cannot be determined");
            }
            return str;
        }
    }

    static String getDensity(Context context) {
        String str = "";
        switch (context.getResources().getDisplayMetrics().densityDpi) {
            case 120:
                return "LDPI";
            case 160:
                return "MDPI";
            case AVException.USER_WITH_MOBILEPHONE_NOT_FOUND /*213*/:
                return "TVDPI";
            case 240:
                return "HDPI";
            case 320:
                return "XHDPI";
            case HttpStatus.SC_BAD_REQUEST /*400*/:
                return "XMHDPI";
            case 480:
                return "XXHDPI";
            case 640:
                return "XXXHDPI";
            default:
                return str;
        }
    }

    static String getCarrier(Context context) {
        String networkOperatorName;
        String str = "";
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(UserData.PHONE_KEY);
            networkOperatorName = telephonyManager != null ? telephonyManager.getNetworkOperatorName() : str;
        } catch (SecurityException e) {
            networkOperatorName = str;
        }
        if (networkOperatorName == null || networkOperatorName.length() == 0) {
            networkOperatorName = "";
            if (Statistics.sharedInstance().isLoggingEnabled()) {
                Log.i("Statistics", "No carrier found");
            }
        }
        return networkOperatorName;
    }

    static String getNetworkType(Context context) {
        String str = "UNKNOWN";
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.getType() == 1) {
                    return "WIFI";
                }
                if (activeNetworkInfo.getType() == 0) {
                    return "MOBILE";
                }
            }
        }
        return str;
    }

    static String getLocale() {
        Locale locale = Locale.getDefault();
        return locale.getLanguage() + "_" + locale.getCountry();
    }

    static String getAppVersion(Context context) {
        String str = "1.0";
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            if (!Statistics.sharedInstance().isLoggingEnabled()) {
                return str;
            }
            Log.i("Statistics", "No app version found");
            return str;
        }
    }

    static String getStore(Context context) {
        String str = "";
        if (VERSION.SDK_INT >= 3) {
            try {
                str = context.getPackageManager().getInstallerPackageName(context.getPackageName());
            } catch (Exception e) {
                if (Statistics.sharedInstance().isLoggingEnabled()) {
                    Log.i("Statistics", "Can't get Installer package");
                }
            }
            if (str == null || str.length() == 0) {
                str = "";
                if (Statistics.sharedInstance().isLoggingEnabled()) {
                    Log.i("Statistics", "No store found");
                }
            }
        }
        return str;
    }

    static String getMetrics(Context context) {
        JSONObject jSONObject = new JSONObject();
        fillJSONIfValuesNotEmpty(jSONObject, C0861d.f2142n, getDevice(), "osName", getOS(), "osVersion", getOSVersion(), MapboxEvent.ATTRIBUTE_CARRIER, getCarrier(context), MapboxEvent.ATTRIBUTE_RESOLUTION, getResolution(context), "density", getDensity(context), "locale", getLocale(), "appVersion", getAppVersion(context), OnlineConfigAgent.KEY_CHANNEL, getStore(context), "bundleId", context.getPackageName(), "sdkVersion", BuildVar.SDK_VERSION, "network", getNetworkType(context), "timeZone", TimeZone.getDefault().getDisplayName(false, 0), "imei", DeviceUtils.getDeviceIMEI(context), "imsi", DeviceUtils.getDeviceIMSI(context), "mac", DeviceUtils.getWifiMacAddress(context));
        String jSONObject2 = jSONObject.toString();
        try {
            jSONObject2 = URLEncoder.encode(jSONObject2, "UTF-8");
        } catch (UnsupportedEncodingException e) {
        }
        return jSONObject2;
    }

    static void fillJSONIfValuesNotEmpty(JSONObject jSONObject, String... strArr) {
        try {
            if (strArr.length > 0 && strArr.length % 2 == 0) {
                for (int i = 0; i < strArr.length; i += 2) {
                    String str = strArr[i];
                    String str2 = strArr[i + 1];
                    if (str2 != null && str2.length() > 0) {
                        jSONObject.put(str, str2);
                    }
                }
            }
        } catch (JSONException e) {
        }
    }
}
