package com.tencent.wxop.stat.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import io.rong.imlib.statistics.UserData;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.tencent.wxop.stat.common.q */
public class C4545q {
    /* renamed from: a */
    private static String f16117a = "";

    /* renamed from: a */
    public static String m18097a(Context context) {
        try {
            if (C4545q.m18101a(context, "android.permission.READ_PHONE_STATE")) {
                String deviceId = ((TelephonyManager) context.getSystemService(UserData.PHONE_KEY)).getDeviceId();
                if (deviceId != null) {
                    return deviceId;
                }
            }
            Log.e(StatConstants.LOG_TAG, "Could not get permission of android.permission.READ_PHONE_STATE");
        } catch (Throwable th) {
            Log.e(StatConstants.LOG_TAG, "get device id error", th);
        }
        return null;
    }

    /* renamed from: a */
    public static String m18098a(String str) {
        if (str == null) {
            return null;
        }
        if (VERSION.SDK_INT < 8) {
            return str;
        }
        try {
            return new String(C4534f.m18028b(C4535g.m18030a(str.getBytes("UTF-8"), 0)), "UTF-8");
        } catch (Throwable th) {
            Log.e(StatConstants.LOG_TAG, "decode error", th);
            return str;
        }
    }

    /* renamed from: a */
    public static JSONArray m18099a(Context context, int i) {
        try {
            if (C4545q.m18101a(context, "android.permission.INTERNET") && C4545q.m18101a(context, "android.permission.ACCESS_NETWORK_STATE")) {
                WifiManager wifiManager = (WifiManager) context.getSystemService(MapboxEvent.ATTRIBUTE_WIFI);
                if (wifiManager != null) {
                    List scanResults = wifiManager.getScanResults();
                    if (scanResults != null && scanResults.size() > 0) {
                        Collections.sort(scanResults, new C4546r());
                        JSONArray jSONArray = new JSONArray();
                        int i2 = 0;
                        while (i2 < scanResults.size() && i2 < i) {
                            ScanResult scanResult = (ScanResult) scanResults.get(i2);
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("bs", scanResult.BSSID);
                            jSONObject.put("ss", scanResult.SSID);
                            jSONArray.put(jSONObject);
                            i2++;
                        }
                        return jSONArray;
                    }
                }
                return null;
            }
            Log.e(StatConstants.LOG_TAG, "can not get the permisson of android.permission.INTERNET");
            return null;
        } catch (Throwable th) {
            Log.e(StatConstants.LOG_TAG, "isWifiNet error", th);
        }
    }

    /* renamed from: a */
    public static void m18100a(JSONObject jSONObject, String str, String str2) {
        if (str2 != null) {
            try {
                if (str2.length() > 0) {
                    jSONObject.put(str, str2);
                }
            } catch (Throwable th) {
                Log.e(StatConstants.LOG_TAG, "jsonPut error", th);
            }
        }
    }

    /* renamed from: a */
    public static boolean m18101a(Context context, String str) {
        try {
            return context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
        } catch (Throwable th) {
            Log.e(StatConstants.LOG_TAG, "checkPermission error", th);
            return false;
        }
    }

    /* renamed from: b */
    public static String m18102b(Context context) {
        if (C4545q.m18101a(context, "android.permission.ACCESS_WIFI_STATE")) {
            try {
                WifiManager wifiManager = (WifiManager) context.getSystemService(MapboxEvent.ATTRIBUTE_WIFI);
                return wifiManager == null ? "" : wifiManager.getConnectionInfo().getMacAddress();
            } catch (Throwable e) {
                Log.e(StatConstants.LOG_TAG, "get wifi address error", e);
                return "";
            }
        }
        Log.e(StatConstants.LOG_TAG, "Could not get permission of android.permission.ACCESS_WIFI_STATE");
        return "";
    }

    /* renamed from: b */
    public static String m18103b(String str) {
        if (str == null) {
            return null;
        }
        if (VERSION.SDK_INT < 8) {
            return str;
        }
        try {
            return new String(C4535g.m18032b(C4534f.m18026a(str.getBytes("UTF-8")), 0), "UTF-8");
        } catch (Throwable th) {
            Log.e(StatConstants.LOG_TAG, "encode error", th);
            return str;
        }
    }

    /* renamed from: c */
    public static WifiInfo m18104c(Context context) {
        if (C4545q.m18101a(context, "android.permission.ACCESS_WIFI_STATE")) {
            WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(MapboxEvent.ATTRIBUTE_WIFI);
            if (wifiManager != null) {
                return wifiManager.getConnectionInfo();
            }
        }
        return null;
    }

    /* renamed from: d */
    public static String m18105d(Context context) {
        try {
            WifiInfo c = C4545q.m18104c(context);
            if (c != null) {
                return c.getBSSID();
            }
        } catch (Throwable th) {
            Log.e(StatConstants.LOG_TAG, "encode error", th);
        }
        return null;
    }

    /* renamed from: e */
    public static String m18106e(Context context) {
        try {
            WifiInfo c = C4545q.m18104c(context);
            if (c != null) {
                return c.getSSID();
            }
        } catch (Throwable th) {
            Log.e(StatConstants.LOG_TAG, "encode error", th);
        }
        return null;
    }

    /* renamed from: f */
    public static boolean m18107f(Context context) {
        try {
            if (C4545q.m18101a(context, "android.permission.INTERNET") && C4545q.m18101a(context, "android.permission.ACCESS_NETWORK_STATE")) {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                if (connectivityManager != null) {
                    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                    if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                        return true;
                    }
                    Log.w(StatConstants.LOG_TAG, "Network error");
                    return false;
                }
                return false;
            }
            Log.e(StatConstants.LOG_TAG, "can not get the permisson of android.permission.INTERNET");
            return false;
        } catch (Throwable th) {
            Log.e(StatConstants.LOG_TAG, "isNetworkAvailable error", th);
        }
    }
}
