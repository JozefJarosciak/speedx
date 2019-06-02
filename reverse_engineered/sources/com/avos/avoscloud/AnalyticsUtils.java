package com.avos.avoscloud;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import com.avos.avoscloud.LogUtil.avlog;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import com.umeng.onlineconfig.OnlineConfigAgent;
import io.rong.imlib.statistics.UserData;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.slf4j.Marker;

public class AnalyticsUtils {
    static List<String> CELLPHONEBLACKLIST = Arrays.asList(new String[]{"d2spr"});
    private static final String TAG = AnalyticsUtils.class.getSimpleName();
    private static final long sendIntervalInDebug = 15000;
    private static final long sendIntervalInProd = 120000;

    public static Map<String, String> getNetworkInfo(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        Map<String, String> hashMap = new HashMap();
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnectedOrConnecting() || withinInBlackList()) {
            hashMap.put("access_subtype", "offline");
            hashMap.put("access", "offline");
            hashMap.put(MapboxEvent.ATTRIBUTE_CARRIER, "");
        } else {
            hashMap.put("access_subtype", activeNetworkInfo.getSubtypeName());
            hashMap.put("access", cleanNetworkTypeName(activeNetworkInfo.getTypeName()));
            hashMap.put(MapboxEvent.ATTRIBUTE_CARRIER, ((TelephonyManager) context.getSystemService(UserData.PHONE_KEY)).getNetworkOperatorName());
        }
        return hashMap;
    }

    private static String cleanNetworkTypeName(String str) {
        if (AVUtils.isBlankString(str)) {
            return "offline";
        }
        if (str.toUpperCase().contains("WIFI")) {
            return "WiFi";
        }
        if (str.contains("MOBILE")) {
            return "Mobile";
        }
        return str;
    }

    public static Map<String, Object> deviceInfo(Context context) {
        Map<String, Object> hashMap = new HashMap();
        Map networkInfo = getNetworkInfo(context);
        if (networkInfo != null) {
            hashMap.putAll(networkInfo);
        }
        networkInfo = getDeviceInfo(context);
        if (networkInfo != null) {
            hashMap.putAll(networkInfo);
        }
        return hashMap;
    }

    public static long getAvailableInternalMemorySize() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
    }

    public static long getTotalInternalMemorySize() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
    }

    public static Map<String, Object> getDeviceInfo(Context context) {
        Object obj;
        Map<String, Object> hashMap = new HashMap();
        String packageName = context.getApplicationContext().getPackageName();
        hashMap.put("package_name", packageName);
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            hashMap.put("app_version", packageInfo.versionName);
            hashMap.put(OnlineConfigAgent.KEY_VERSION_CODE, Integer.valueOf(packageInfo.versionCode));
            hashMap.put(OnlineConfigAgent.KEY_SDK_VERSION, "Android v3.4.1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        int width = defaultDisplay.getWidth();
        hashMap.put(MapboxEvent.ATTRIBUTE_RESOLUTION, "" + width + Marker.ANY_MARKER + defaultDisplay.getHeight());
        hashMap.put("device_model", Build.MODEL);
        hashMap.put("device_manufacturer", Build.MANUFACTURER);
        hashMap.put("os_version", VERSION.RELEASE);
        hashMap.put("device_name", Build.DEVICE);
        hashMap.put("device_brand", Build.BRAND);
        hashMap.put("device_board", Build.BOARD);
        hashMap.put("device_manuid", Build.FINGERPRINT);
        hashMap.put("cpu", getCPUInfo());
        hashMap.put("os", "Android");
        hashMap.put("sdk_type", "Android");
        try {
            packageName = ((WifiManager) AVOSCloud.applicationContext.getSystemService(MapboxEvent.ATTRIBUTE_WIFI)).getConnectionInfo().getMacAddress();
        } catch (Exception e2) {
            if (AVOSCloud.showInternalDebugLog()) {
                avlog.m3506d("failed to get wifi mac address" + e2);
            }
            packageName = null;
        }
        String string = Secure.getString(context.getContentResolver(), "android_id");
        if (AVUtils.isBlankString(packageName)) {
            obj = string;
        } else {
            obj = AVUtils.md5(packageName + string);
        }
        hashMap.put("device_id", obj);
        try {
            Class cls = Class.forName("com.avos.avoscloud.AVInstallation");
            string = "iid";
            hashMap.put(string, (String) cls.getMethod("getObjectId", new Class[0]).invoke(cls.getMethod("getCurrentInstallation", new Class[0]).invoke(cls, new Object[0]), new Object[0]));
        } catch (Exception e3) {
        }
        long rawOffset = (long) TimeZone.getDefault().getRawOffset();
        AVUser currentUser = AVUser.getCurrentUser();
        if (!(currentUser == null || AVUtils.isBlankString(currentUser.getObjectId()))) {
            hashMap.put("uid", currentUser.getObjectId());
        }
        try {
            rawOffset = TimeUnit.HOURS.convert(rawOffset, TimeUnit.MILLISECONDS);
        } catch (NoSuchFieldError e4) {
            rawOffset /= 3600000;
        }
        hashMap.put("time_zone", Long.valueOf(rawOffset));
        hashMap.put(OnlineConfigAgent.KEY_CHANNEL, AVAnalytics.getAppChannel());
        if (!withinInBlackList() && AVOSCloud.applicationContext.checkCallingPermission("android.permission.READ_PHONE_STATE") == 0) {
            hashMap.put("imei", ((TelephonyManager) context.getSystemService(UserData.PHONE_KEY)).getDeviceId());
        }
        return hashMap;
    }

    public static String collectMemInfo() {
        Closeable inputStream;
        Closeable inputStreamReader;
        Closeable bufferedReader;
        Throwable e;
        Closeable errorStream;
        Closeable closeable = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            List arrayList = new ArrayList();
            arrayList.add("dumpsys");
            arrayList.add("meminfo");
            arrayList.add(Integer.toString(Process.myPid()));
            Process exec = Runtime.getRuntime().exec((String[]) arrayList.toArray(new String[arrayList.size()]));
            inputStream = exec.getInputStream();
            try {
                inputStreamReader = new InputStreamReader(inputStream);
                try {
                    bufferedReader = new BufferedReader(inputStreamReader, 8192);
                    while (true) {
                        try {
                            String readLine = bufferedReader.readLine();
                            if (readLine == null) {
                                break;
                            }
                            stringBuilder.append(readLine);
                            stringBuilder.append("\n");
                        } catch (Exception e2) {
                            e = e2;
                            closeable = bufferedReader;
                            bufferedReader = inputStreamReader;
                        } catch (Throwable th) {
                            e = th;
                            closeable = bufferedReader;
                        }
                    }
                    AVPersistenceUtils.closeQuietly(bufferedReader);
                    AVPersistenceUtils.closeQuietly(inputStreamReader);
                    AVPersistenceUtils.closeQuietly(inputStream);
                    errorStream = exec.getErrorStream();
                } catch (Exception e3) {
                    e = e3;
                    bufferedReader = inputStreamReader;
                    try {
                        Log.e(TAG, "DumpSysCollector.meminfo could not retrieve data", e);
                        AVPersistenceUtils.closeQuietly(closeable);
                        AVPersistenceUtils.closeQuietly(bufferedReader);
                        AVPersistenceUtils.closeQuietly(inputStream);
                        return stringBuilder.toString();
                    } catch (Throwable th2) {
                        e = th2;
                        inputStreamReader = bufferedReader;
                        AVPersistenceUtils.closeQuietly(closeable);
                        AVPersistenceUtils.closeQuietly(inputStreamReader);
                        AVPersistenceUtils.closeQuietly(inputStream);
                        throw e;
                    }
                } catch (Throwable th3) {
                    e = th3;
                    AVPersistenceUtils.closeQuietly(closeable);
                    AVPersistenceUtils.closeQuietly(inputStreamReader);
                    AVPersistenceUtils.closeQuietly(inputStream);
                    throw e;
                }
            } catch (Exception e4) {
                e = e4;
                bufferedReader = null;
                Log.e(TAG, "DumpSysCollector.meminfo could not retrieve data", e);
                AVPersistenceUtils.closeQuietly(closeable);
                AVPersistenceUtils.closeQuietly(bufferedReader);
                AVPersistenceUtils.closeQuietly(inputStream);
                return stringBuilder.toString();
            } catch (Throwable th4) {
                e = th4;
                inputStreamReader = null;
                AVPersistenceUtils.closeQuietly(closeable);
                AVPersistenceUtils.closeQuietly(inputStreamReader);
                AVPersistenceUtils.closeQuietly(inputStream);
                throw e;
            }
            try {
                inputStream = new InputStreamReader(errorStream);
                try {
                    closeable = new BufferedReader(inputStream, 8192);
                    try {
                        StringBuilder stringBuilder2 = new StringBuilder();
                        while (true) {
                            String readLine2 = closeable.readLine();
                            if (readLine2 == null) {
                                break;
                            }
                            stringBuilder2.append(readLine2);
                        }
                        if (exec.waitFor() != 0) {
                            Log.e(TAG, stringBuilder2.toString());
                        }
                        AVPersistenceUtils.closeQuietly(closeable);
                        AVPersistenceUtils.closeQuietly(inputStream);
                        AVPersistenceUtils.closeQuietly(errorStream);
                    } catch (Exception e5) {
                        e = e5;
                        bufferedReader = inputStream;
                        inputStream = errorStream;
                    } catch (Throwable th5) {
                        e = th5;
                        inputStreamReader = inputStream;
                        inputStream = errorStream;
                    }
                } catch (Exception e6) {
                    e = e6;
                    closeable = bufferedReader;
                    bufferedReader = inputStream;
                    inputStream = errorStream;
                    Log.e(TAG, "DumpSysCollector.meminfo could not retrieve data", e);
                    AVPersistenceUtils.closeQuietly(closeable);
                    AVPersistenceUtils.closeQuietly(bufferedReader);
                    AVPersistenceUtils.closeQuietly(inputStream);
                    return stringBuilder.toString();
                } catch (Throwable th6) {
                    e = th6;
                    closeable = bufferedReader;
                    inputStreamReader = inputStream;
                    inputStream = errorStream;
                    AVPersistenceUtils.closeQuietly(closeable);
                    AVPersistenceUtils.closeQuietly(inputStreamReader);
                    AVPersistenceUtils.closeQuietly(inputStream);
                    throw e;
                }
            } catch (Exception e7) {
                e = e7;
                closeable = bufferedReader;
                inputStream = errorStream;
                bufferedReader = inputStreamReader;
                Log.e(TAG, "DumpSysCollector.meminfo could not retrieve data", e);
                AVPersistenceUtils.closeQuietly(closeable);
                AVPersistenceUtils.closeQuietly(bufferedReader);
                AVPersistenceUtils.closeQuietly(inputStream);
                return stringBuilder.toString();
            } catch (Throwable th7) {
                e = th7;
                closeable = bufferedReader;
                inputStream = errorStream;
                AVPersistenceUtils.closeQuietly(closeable);
                AVPersistenceUtils.closeQuietly(inputStreamReader);
                AVPersistenceUtils.closeQuietly(inputStream);
                throw e;
            }
        } catch (Exception e8) {
            e = e8;
            bufferedReader = null;
            inputStream = null;
            Log.e(TAG, "DumpSysCollector.meminfo could not retrieve data", e);
            AVPersistenceUtils.closeQuietly(closeable);
            AVPersistenceUtils.closeQuietly(bufferedReader);
            AVPersistenceUtils.closeQuietly(inputStream);
            return stringBuilder.toString();
        } catch (Throwable th8) {
            e = th8;
            inputStreamReader = null;
            inputStream = null;
            AVPersistenceUtils.closeQuietly(closeable);
            AVPersistenceUtils.closeQuietly(inputStreamReader);
            AVPersistenceUtils.closeQuietly(inputStream);
            throw e;
        }
        return stringBuilder.toString();
    }

    public static String getCPUInfo() {
        Closeable bufferedReader;
        Throwable e;
        StringBuffer stringBuffer = new StringBuffer();
        if (new File("/proc/cpuinfo").exists()) {
            try {
                String readLine;
                bufferedReader = new BufferedReader(new FileReader(new File("/proc/cpuinfo")));
                do {
                    try {
                        readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                    } catch (IOException e2) {
                        e = e2;
                    }
                } while (!readLine.contains("Processor"));
                int indexOf = readLine.indexOf(":");
                if (indexOf >= 0 && indexOf < readLine.length() - 1) {
                    stringBuffer.append(readLine.substring(indexOf + 1).trim());
                }
                AVPersistenceUtils.closeQuietly(bufferedReader);
            } catch (IOException e3) {
                e = e3;
                bufferedReader = null;
                try {
                    Log.e(TAG, "getCPUInfo", e);
                    AVPersistenceUtils.closeQuietly(bufferedReader);
                    return stringBuffer.toString();
                } catch (Throwable th) {
                    e = th;
                    AVPersistenceUtils.closeQuietly(bufferedReader);
                    throw e;
                }
            } catch (Throwable th2) {
                e = th2;
                bufferedReader = null;
                AVPersistenceUtils.closeQuietly(bufferedReader);
                throw e;
            }
        }
        return stringBuffer.toString();
    }

    public static String getLocalIpAddress() {
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = 1;
        try {
            Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration inetAddresses = ((NetworkInterface) networkInterfaces.nextElement()).getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    Object obj2;
                    InetAddress inetAddress = (InetAddress) inetAddresses.nextElement();
                    if (inetAddress.isLoopbackAddress()) {
                        obj2 = obj;
                    } else {
                        if (obj == null) {
                            stringBuilder.append('\n');
                        }
                        stringBuilder.append(inetAddress.getHostAddress().toString());
                        obj2 = null;
                    }
                    obj = obj2;
                }
            }
        } catch (SocketException e) {
            Log.i(TAG, e.toString());
        }
        return stringBuilder.toString();
    }

    public static String getApplicationFilePath(Context context) {
        File filesDir = context.getFilesDir();
        if (filesDir != null) {
            return filesDir.getAbsolutePath();
        }
        return "Couldn't retrieve ApplicationFilePath";
    }

    public static long getCurrentTimestamp() {
        return System.currentTimeMillis();
    }

    public static String getRandomString(int i) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder stringBuilder = new StringBuilder(i);
        for (int i2 = 0; i2 < i; i2++) {
            stringBuilder.append(str.charAt(new Random().nextInt(str.length())));
        }
        return stringBuilder.toString();
    }

    public static String uniqueId() {
        return UUID.randomUUID().toString();
    }

    public static boolean isStringEqual(String str, String str2) {
        if (str == null && str2 == null) {
            return true;
        }
        if (str != null) {
            return str.equals(str2);
        }
        return false;
    }

    private static boolean withinInBlackList() {
        if (CELLPHONEBLACKLIST.contains(Build.DEVICE)) {
            return true;
        }
        return false;
    }

    static boolean inDebug(Context context) {
        boolean z = false;
        if (context != null) {
            if ((context.getApplicationInfo().flags & 2) != 0) {
                z = true;
            }
            if (z) {
                Log.i(TAG, "in debug: " + z);
            }
        }
        return z;
    }

    protected static long getRequestInterval() {
        return inDebug(AVOSCloud.applicationContext) ? sendIntervalInDebug : sendIntervalInProd;
    }
}
