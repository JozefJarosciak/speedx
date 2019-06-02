package io.rong.imlib.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.j256.ormlite.stmt.query.SimpleComparison;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import io.rong.imlib.statistics.UserData;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;
import org.apache.commons.cli.HelpFormatter;
import org.slf4j.Marker;

public class DeviceUtils {
    public static String getDeviceId(Context context, String str) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Statistics", 0);
        String string = sharedPreferences.getString("deviceId", "");
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        string = ShortMD5(getDeviceIMEI(context), str, context.getPackageName());
        Editor edit = sharedPreferences.edit();
        edit.putString("deviceId", string);
        edit.apply();
        return string;
    }

    public static String getDeviceIMEI(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Statistics", 0);
        CharSequence string = sharedPreferences.getString("IMEI", "");
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        String deviceId;
        try {
            deviceId = ((TelephonyManager) context.getSystemService(UserData.PHONE_KEY)).getDeviceId();
        } catch (SecurityException e) {
            Log.e("DeviceUtils", "SecurityException!!!");
            CharSequence charSequence = string;
        }
        if (TextUtils.isEmpty(deviceId) || deviceId.equals("000000000000000") || deviceId.equals("000000000000")) {
            deviceId = new BigInteger(64, new SecureRandom()).toString(16);
        }
        Editor edit = sharedPreferences.edit();
        edit.putString("IMEI", deviceId);
        edit.apply();
        return deviceId;
    }

    private static String ShortMD5(String... strArr) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            for (String append : strArr) {
                stringBuilder.append(append);
            }
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(stringBuilder.toString().getBytes());
            return new String(Base64.encode(instance.digest(), 0)).replace(SimpleComparison.EQUAL_TO_OPERATION, "").replace(Marker.ANY_NON_NULL_MARKER, HelpFormatter.DEFAULT_OPT_PREFIX).replace("/", "_").replace("\n", "");
        } catch (Exception e) {
            return "";
        }
    }

    public static String getPhoneInformation(Context context) {
        String str;
        String str2 = "";
        try {
            str2 = ((TelephonyManager) context.getSystemService(UserData.PHONE_KEY)).getNetworkOperator();
        } catch (SecurityException e) {
            Log.e("DeviceUtils", "SecurityException!!!");
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        String str3 = "";
        if (connectivityManager == null || connectivityManager.getActiveNetworkInfo() == null) {
            str = str3;
        } else {
            str = connectivityManager.getActiveNetworkInfo().getTypeName();
        }
        String str4 = Build.MANUFACTURER;
        str3 = Build.MODEL;
        if (str4 == null) {
            str4 = "";
        }
        if (str3 == null) {
            str3 = "";
        }
        str = ((((((((((str4 + "|") + str3) + "|") + String.valueOf(VERSION.SDK_INT)) + "|") + str) + "|") + str2) + "|") + context.getPackageName()).replace(HelpFormatter.DEFAULT_OPT_PREFIX, "_");
        Log.i("DeviceUtils", "getPhoneInformation.the phone information is: " + str);
        return str;
    }

    public static String getDeviceIMSI(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Statistics", 0);
        CharSequence string = sharedPreferences.getString("IMSI", "");
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        String subscriberId;
        try {
            subscriberId = ((TelephonyManager) context.getSystemService(UserData.PHONE_KEY)).getSubscriberId();
        } catch (SecurityException e) {
            Log.e("DeviceUtils", "SecurityException!!!");
            CharSequence charSequence = string;
        }
        Log.i("DeviceUtils", "IMSI is: " + subscriberId);
        if (TextUtils.isEmpty(subscriberId)) {
            return subscriberId;
        }
        Editor edit = sharedPreferences.edit();
        edit.putString("IMSI", subscriberId);
        edit.apply();
        return subscriberId;
    }

    public static String getDeviceManufacturer() {
        BufferedReader bufferedReader;
        CharSequence readLine;
        Throwable th;
        String str = "";
        String str2 = "ro.miui.ui.version.name";
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop " + str2).getInputStream()), 1024);
            try {
                readLine = bufferedReader.readLine();
                try {
                    bufferedReader.close();
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e) {
                        }
                    }
                } catch (IOException e2) {
                    try {
                        Log.e("DeviceUtils", "Unable to read sysprop " + str2);
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e3) {
                            }
                        }
                        if (TextUtils.isEmpty(readLine)) {
                            return "Xiaomi";
                        }
                        return Build.MANUFACTURER.replace(HelpFormatter.DEFAULT_OPT_PREFIX, "_");
                    } catch (Throwable th2) {
                        th = th2;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e4) {
                            }
                        }
                        throw th;
                    }
                }
            } catch (IOException e5) {
                Object obj = str;
                Log.e("DeviceUtils", "Unable to read sysprop " + str2);
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (TextUtils.isEmpty(readLine)) {
                    return "Xiaomi";
                }
                return Build.MANUFACTURER.replace(HelpFormatter.DEFAULT_OPT_PREFIX, "_");
            }
        } catch (IOException e6) {
            bufferedReader = null;
            readLine = str;
            Log.e("DeviceUtils", "Unable to read sysprop " + str2);
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (TextUtils.isEmpty(readLine)) {
                return "Xiaomi";
            }
            return Build.MANUFACTURER.replace(HelpFormatter.DEFAULT_OPT_PREFIX, "_");
        } catch (Throwable th3) {
            Throwable th4 = th3;
            bufferedReader = null;
            th = th4;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            throw th;
        }
        if (TextUtils.isEmpty(readLine)) {
            return Build.MANUFACTURER.replace(HelpFormatter.DEFAULT_OPT_PREFIX, "_");
        }
        return "Xiaomi";
    }

    public static String getWifiMacAddress(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Statistics", 0);
        CharSequence string = sharedPreferences.getString("Mac", "");
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        String macAddress;
        try {
            macAddress = ((WifiManager) context.getSystemService(MapboxEvent.ATTRIBUTE_WIFI)).getConnectionInfo().getMacAddress();
        } catch (SecurityException e) {
            Log.e("DeviceUtils", "SecurityException!!!");
            CharSequence charSequence = string;
        }
        Log.i("DeviceUtils", "MAC is: " + macAddress);
        if (TextUtils.isEmpty(macAddress)) {
            return macAddress;
        }
        Editor edit = sharedPreferences.edit();
        edit.putString("Mac", macAddress);
        edit.apply();
        return macAddress;
    }
}
