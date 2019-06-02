package io.rong.imlib.statistics;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.FeatureInfo;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import com.alipay.sdk.cons.C0844a;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

class CrashDetails {
    private static Map<String, String> customSegments = null;
    private static boolean inBackground = true;
    private static ArrayList<String> logs = new ArrayList();
    private static int startTime = Statistics.currentTimestamp();
    private static long totalMemory = 0;

    CrashDetails() {
    }

    private static long getTotalRAM() {
        if (totalMemory == 0) {
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile("/proc/meminfo", "r");
                Matcher matcher = Pattern.compile("(\\d+)").matcher(randomAccessFile.readLine());
                String str = "";
                while (matcher.find()) {
                    str = matcher.group(1);
                }
                randomAccessFile.close();
                totalMemory = Long.parseLong(str) / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return totalMemory;
    }

    static void inForeground() {
        inBackground = false;
    }

    static void inBackground() {
        inBackground = true;
    }

    static String isInBackground() {
        return Boolean.toString(inBackground);
    }

    static void addLog(String str) {
        logs.add(str);
    }

    static String getLogs() {
        Iterator it = logs.iterator();
        String str = "";
        while (it.hasNext()) {
            str = str + ((String) it.next()) + "\n";
        }
        logs.clear();
        return str;
    }

    static void setCustomSegments(Map<String, String> map) {
        customSegments = new HashMap();
        customSegments.putAll(map);
    }

    static JSONObject getCustomSegments() {
        if (customSegments == null || customSegments.isEmpty()) {
            return null;
        }
        return new JSONObject(customSegments);
    }

    static String getManufacturer() {
        return Build.MANUFACTURER;
    }

    static String getCpu() {
        if (VERSION.SDK_INT < 21) {
            return Build.CPU_ABI;
        }
        return Build.SUPPORTED_ABIS[0];
    }

    static String getOpenGL(Context context) {
        FeatureInfo[] systemAvailableFeatures = context.getPackageManager().getSystemAvailableFeatures();
        if (systemAvailableFeatures != null && systemAvailableFeatures.length > 0) {
            int length = systemAvailableFeatures.length;
            int i = 0;
            while (i < length) {
                FeatureInfo featureInfo = systemAvailableFeatures[i];
                if (featureInfo.name != null) {
                    i++;
                } else if (featureInfo.reqGlEsVersion != 0) {
                    return Integer.toString((featureInfo.reqGlEsVersion & SupportMenu.CATEGORY_MASK) >> 16);
                } else {
                    return C0844a.f2048d;
                }
            }
        }
        return C0844a.f2048d;
    }

    static String getRamCurrent(Context context) {
        MemoryInfo memoryInfo = new MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
        return Long.toString(getTotalRAM() - (memoryInfo.availMem / 1048576));
    }

    static String getRamTotal(Context context) {
        return Long.toString(getTotalRAM());
    }

    static String getDiskCurrent() {
        if (VERSION.SDK_INT < 18) {
            StatFs statFs = new StatFs(Environment.getRootDirectory().getAbsolutePath());
            return Long.toString((((long) (statFs.getBlockCount() * statFs.getBlockSize())) - ((long) (statFs.getBlockSize() * statFs.getAvailableBlocks()))) / 1048576);
        }
        statFs = new StatFs(Environment.getRootDirectory().getAbsolutePath());
        return Long.toString(((statFs.getBlockCountLong() * statFs.getBlockSizeLong()) - (statFs.getBlockSizeLong() * statFs.getAvailableBlocksLong())) / 1048576);
    }

    static String getDiskTotal() {
        if (VERSION.SDK_INT < 18) {
            StatFs statFs = new StatFs(Environment.getRootDirectory().getAbsolutePath());
            return Long.toString(((long) (statFs.getBlockSize() * statFs.getBlockCount())) / 1048576);
        }
        statFs = new StatFs(Environment.getRootDirectory().getAbsolutePath());
        return Long.toString((statFs.getBlockSizeLong() * statFs.getBlockCountLong()) / 1048576);
    }

    static String getBatteryLevel(Context context) {
        String str = null;
        try {
            Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            int intExtra = registerReceiver.getIntExtra("level", -1);
            int intExtra2 = registerReceiver.getIntExtra("scale", -1);
            if (intExtra > -1 && intExtra2 > 0) {
                str = Float.toString((((float) intExtra) / ((float) intExtra2)) * 100.0f);
            }
        } catch (Exception e) {
            if (Statistics.sharedInstance().isLoggingEnabled()) {
                Log.i("Statistics", "Can't get batter level");
            }
        }
        return str;
    }

    static String getRunningTime() {
        return Integer.toString(Statistics.currentTimestamp() - startTime);
    }

    static String getOrientation(Context context) {
        switch (context.getResources().getConfiguration().orientation) {
            case 0:
                return "Unknown";
            case 1:
                return "Portrait";
            case 2:
                return "Landscape";
            case 3:
                return "Square";
            default:
                return null;
        }
    }

    static String isRooted() {
        int i = 0;
        String[] strArr = new String[]{"/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su"};
        int length = strArr.length;
        while (i < length) {
            if (new File(strArr[i]).exists()) {
                return "true";
            }
            i++;
        }
        return "false";
    }

    static String isOnline(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null || connectivityManager.getActiveNetworkInfo() == null || !connectivityManager.getActiveNetworkInfo().isAvailable() || !connectivityManager.getActiveNetworkInfo().isConnected()) {
                return "false";
            }
            return "true";
        } catch (Throwable e) {
            if (Statistics.sharedInstance().isLoggingEnabled()) {
                Log.w("Statistics", "Got exception determining connectivity", e);
            }
            return null;
        }
    }

    static String isMuted(Context context) {
        switch (((AudioManager) context.getSystemService("audio")).getRingerMode()) {
            case 0:
                return "true";
            case 1:
                return "true";
            default:
                return "false";
        }
    }

    static String getCrashData(Context context, String str, Boolean bool) {
        JSONObject jSONObject = new JSONObject();
        fillJSONIfValuesNotEmpty(jSONObject, "_error", str, "_nonfatal", Boolean.toString(bool.booleanValue()), "_logs", getLogs(), "_device", DeviceInfo.getDevice(), "_os", DeviceInfo.getOS(), "_os_version", DeviceInfo.getOSVersion(), "_resolution", DeviceInfo.getResolution(context), "_app_version", DeviceInfo.getAppVersion(context), "_manufacture", getManufacturer(), "_cpu", getCpu(), "_opengl", getOpenGL(context), "_ram_current", getRamCurrent(context), "_ram_total", getRamTotal(context), "_disk_current", getDiskCurrent(), "_disk_total", getDiskTotal(), "_bat", getBatteryLevel(context), "_run", getRunningTime(), "_orientation", getOrientation(context), "_root", isRooted(), "_online", isOnline(context), "_muted", isMuted(context), "_background", isInBackground());
        try {
            jSONObject.put("_custom", getCustomSegments());
        } catch (JSONException e) {
        }
        String jSONObject2 = jSONObject.toString();
        try {
            jSONObject2 = URLEncoder.encode(jSONObject2, "UTF-8");
        } catch (UnsupportedEncodingException e2) {
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
