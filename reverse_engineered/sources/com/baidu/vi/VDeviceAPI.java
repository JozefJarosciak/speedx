package com.baidu.vi;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.StatFs;
import android.provider.Settings.SettingNotFoundException;
import android.provider.Settings.System;
import android.support.v4.media.session.PlaybackStateCompat;
import android.telephony.CellLocation;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import com.mapbox.services.geocoding.v5.GeocodingCriteria;
import io.rong.imlib.statistics.UserData;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class VDeviceAPI {
    /* renamed from: a */
    private static WakeLock f3980a = null;
    /* renamed from: b */
    private static BroadcastReceiver f3981b = null;

    public static String getAppVersion() {
        try {
            return C1367b.m5237a().getPackageManager().getPackageInfo(C1367b.m5237a().getApplicationInfo().packageName, 0).versionName;
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    public static long getAvailableMemory() {
        ActivityManager activityManager = (ActivityManager) C1367b.m5237a().getSystemService("activity");
        MemoryInfo memoryInfo = new MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo.availMem / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
    }

    public static String getCachePath() {
        return Environment.getDataDirectory().getAbsolutePath();
    }

    public static String getCellId() {
        TelephonyManager telephonyManager = (TelephonyManager) C1367b.m5237a().getSystemService(UserData.PHONE_KEY);
        if (telephonyManager == null) {
            return null;
        }
        CellLocation cellLocation = telephonyManager.getCellLocation();
        return cellLocation instanceof GsmCellLocation ? " " + ((GsmCellLocation) cellLocation).getCid() : " ";
    }

    public static int getCurrentNetworkType() {
        NetworkInfo activeNetworkInfo;
        try {
            activeNetworkInfo = ((ConnectivityManager) C1367b.m5237a().getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (Exception e) {
            activeNetworkInfo = null;
        }
        if (activeNetworkInfo == null) {
            return 0;
        }
        switch (activeNetworkInfo.getType()) {
            case 0:
                return 3;
            case 1:
                return 2;
            default:
                return 1;
        }
    }

    public static long getFreeSpace() {
        StatFs statFs = new StatFs(Environment.getRootDirectory().getPath());
        return (((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize())) / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
    }

    public static String getImei() {
        TelephonyManager telephonyManager = (TelephonyManager) C1367b.m5237a().getSystemService(UserData.PHONE_KEY);
        return telephonyManager != null ? telephonyManager.getDeviceId() : null;
    }

    public static String getImsi() {
        TelephonyManager telephonyManager = (TelephonyManager) C1367b.m5237a().getSystemService(UserData.PHONE_KEY);
        return telephonyManager != null ? telephonyManager.getSubscriberId() : null;
    }

    public static String getLac() {
        TelephonyManager telephonyManager = (TelephonyManager) C1367b.m5237a().getSystemService(UserData.PHONE_KEY);
        if (telephonyManager == null) {
            return null;
        }
        CellLocation cellLocation = telephonyManager.getCellLocation();
        return cellLocation instanceof GsmCellLocation ? "" + ((GsmCellLocation) cellLocation).getLac() : "";
    }

    public static String getModuleFileName() {
        return C1367b.m5237a().getFilesDir().getAbsolutePath();
    }

    public static C1369c getNetworkInfo(int i) {
        NetworkInfo networkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) C1367b.m5237a().getSystemService("connectivity");
        switch (i) {
            case 2:
                networkInfo = connectivityManager.getNetworkInfo(1);
                break;
            case 3:
                networkInfo = connectivityManager.getNetworkInfo(0);
                break;
            default:
                networkInfo = null;
                break;
        }
        return networkInfo != null ? new C1369c(networkInfo) : null;
    }

    public static String getOsVersion() {
        return "android";
    }

    public static int getScreenBrightness() {
        int i = -1;
        ContentResolver contentResolver = C1367b.m5237a().getContentResolver();
        int i2 = 0;
        try {
            i2 = System.getInt(contentResolver, "screen_brightness_mode");
        } catch (SettingNotFoundException e) {
        }
        if (i2 != 1) {
            try {
                i = System.getInt(contentResolver, "screen_brightness");
            } catch (SettingNotFoundException e2) {
            }
        }
        return i;
    }

    public static float getScreenDensity() {
        if (C1367b.m5237a() == null) {
            return 0.0f;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) C1367b.m5237a().getSystemService("window");
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        }
        return displayMetrics.density;
    }

    public static int getScreenDensityDpi() {
        if (C1367b.m5237a() == null) {
            return 0;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) C1367b.m5237a().getSystemService("window");
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        }
        return displayMetrics.densityDpi;
    }

    public static long getSdcardFreeSpace() {
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return (((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize())) / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
    }

    public static String getSdcardPath() {
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        return externalStorageDirectory != null ? externalStorageDirectory.getAbsolutePath() : null;
    }

    public static long getSdcardTotalSpace() {
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return (((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize())) / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
    }

    public static float getSystemMetricsX() {
        if (C1367b.m5237a() == null) {
            return 0.0f;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) C1367b.m5237a().getSystemService("window");
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        }
        return (float) displayMetrics.widthPixels;
    }

    public static float getSystemMetricsY() {
        if (C1367b.m5237a() == null) {
            return 0.0f;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) C1367b.m5237a().getSystemService("window");
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        }
        return (float) displayMetrics.heightPixels;
    }

    public static long getTotalMemory() {
        long j = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                j = (long) Integer.valueOf(readLine.split("\\s+")[1]).intValue();
            }
            bufferedReader.close();
        } catch (IOException e) {
        }
        return j;
    }

    public static long getTotalSpace() {
        StatFs statFs = new StatFs(Environment.getRootDirectory().getPath());
        return (((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize())) / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
    }

    public static ScanResult[] getWifiHotpot() {
        List scanResults = ((WifiManager) C1367b.m5237a().getSystemService(MapboxEvent.ATTRIBUTE_WIFI)).getScanResults();
        return (ScanResult[]) scanResults.toArray(new ScanResult[scanResults.size()]);
    }

    public static boolean isWifiConnected() {
        NetworkInfo networkInfo = ((ConnectivityManager) C1367b.m5237a().getSystemService("connectivity")).getNetworkInfo(1);
        return networkInfo == null ? false : networkInfo.isConnected();
    }

    public static void makeCall(String str) {
        C1367b.m5237a().startActivity(new Intent("android.intent.action.DIAL", Uri.parse("tel:" + str)));
    }

    public static native void onNetworkStateChanged();

    public static void openUrl(String str) {
        C1367b.m5237a().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
    }

    public static int sendMMS(String str, String str2, String str3, String str4) {
        if (!PhoneNumberUtils.isWellFormedSmsAddress(str)) {
            return 1;
        }
        try {
            String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(new File(str4)).toString()));
            Intent intent = new Intent("android.intent.action.SEND");
            intent.putExtra(GeocodingCriteria.TYPE_ADDRESS, str);
            intent.putExtra("subject", str2);
            intent.putExtra("sms_body", str3);
            intent.putExtra("android.intent.extra.STREAM", Uri.parse("file://" + str4));
            intent.setType(mimeTypeFromExtension);
            C1367b.m5237a().startActivity(intent);
            return 0;
        } catch (Exception e) {
            return 2;
        }
    }

    public static void sendSMS(String str, String str2) {
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + str));
        intent.putExtra("sms_body", str2);
        C1367b.m5237a().startActivity(intent);
    }

    public static void setNetworkChangedCallback() {
        unsetNetworkChangedCallback();
        f3981b = new C1366a();
        C1367b.m5237a().registerReceiver(f3981b, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    public static void setScreenAlwaysOn(boolean z) {
        if (z) {
            if (f3980a == null) {
                f3980a = ((PowerManager) C1367b.m5237a().getSystemService("power")).newWakeLock(10, "VDeviceAPI");
            }
            f3980a.acquire();
        } else if (f3980a != null && f3980a.isHeld()) {
            f3980a.release();
            f3980a = null;
        }
    }

    public static void setupSoftware(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(Uri.fromFile(new File(str)), "application/vnd.android.package-archive");
        C1367b.m5237a().startActivity(intent);
    }

    public static void unsetNetworkChangedCallback() {
        if (f3981b != null) {
            C1367b.m5237a().unregisterReceiver(f3981b);
            f3981b = null;
        }
    }
}
