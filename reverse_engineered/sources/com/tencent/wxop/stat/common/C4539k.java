package com.tencent.wxop.stat.common;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import android.support.v4.os.EnvironmentCompat;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import com.tencent.wxop.stat.StatConfig;
import com.tencent.wxop.stat.StatSpecifyReportedInfo;
import io.rong.imlib.statistics.UserData;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.zip.GZIPInputStream;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.json.JSONObject;

/* renamed from: com.tencent.wxop.stat.common.k */
public class C4539k {
    /* renamed from: a */
    private static String f16091a = null;
    /* renamed from: b */
    private static String f16092b = null;
    /* renamed from: c */
    private static String f16093c = null;
    /* renamed from: d */
    private static String f16094d = null;
    /* renamed from: e */
    private static Random f16095e = null;
    /* renamed from: f */
    private static DisplayMetrics f16096f = null;
    /* renamed from: g */
    private static String f16097g = null;
    /* renamed from: h */
    private static String f16098h = "";
    /* renamed from: i */
    private static String f16099i = "";
    /* renamed from: j */
    private static int f16100j = -1;
    /* renamed from: k */
    private static StatLogger f16101k = null;
    /* renamed from: l */
    private static String f16102l = null;
    /* renamed from: m */
    private static String f16103m = null;
    /* renamed from: n */
    private static volatile int f16104n = -1;
    /* renamed from: o */
    private static String f16105o = null;
    /* renamed from: p */
    private static String f16106p = null;
    /* renamed from: q */
    private static long f16107q = -1;
    /* renamed from: r */
    private static String f16108r = "";
    /* renamed from: s */
    private static C4542n f16109s = null;
    /* renamed from: t */
    private static String f16110t = "__MTA_FIRST_ACTIVATE__";
    /* renamed from: u */
    private static int f16111u = -1;
    /* renamed from: v */
    private static long f16112v = -1;
    /* renamed from: w */
    private static int f16113w = 0;
    /* renamed from: x */
    private static String f16114x = "";

    /* renamed from: A */
    public static int m18036A(Context context) {
        return C4544p.m18090a(context, "mta.qq.com.difftime", 0);
    }

    /* renamed from: B */
    public static boolean m18037B(Context context) {
        if (context == null) {
            return false;
        }
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager == null) {
            return false;
        }
        String packageName = context.getPackageName();
        for (RunningAppProcessInfo runningAppProcessInfo : activityManager.getRunningAppProcesses()) {
            if (runningAppProcessInfo.processName.startsWith(packageName)) {
                return runningAppProcessInfo.importance == HttpStatus.SC_BAD_REQUEST;
            }
        }
        return false;
    }

    /* renamed from: C */
    public static String m18038C(Context context) {
        if (context == null) {
            return null;
        }
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(intent, 0);
        return (resolveActivity.activityInfo == null || resolveActivity.activityInfo.packageName.equals("android")) ? null : resolveActivity.activityInfo.packageName;
    }

    /* renamed from: D */
    private static long m18039D(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        MemoryInfo memoryInfo = new MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo.availMem;
    }

    /* renamed from: a */
    public static int m18040a() {
        return C4539k.m18064g().nextInt(Integer.MAX_VALUE);
    }

    /* renamed from: a */
    public static int m18041a(Context context, boolean z) {
        if (z) {
            f16113w = C4539k.m18036A(context);
        }
        return f16113w;
    }

    /* renamed from: a */
    public static Long m18042a(String str, String str2, int i, int i2, Long l) {
        if (str == null || str2 == null) {
            return l;
        }
        if (str2.equalsIgnoreCase(".") || str2.equalsIgnoreCase("|")) {
            str2 = "\\" + str2;
        }
        String[] split = str.split(str2);
        if (split.length != i2) {
            return l;
        }
        try {
            Long valueOf = Long.valueOf(0);
            int i3 = 0;
            while (i3 < split.length) {
                Long valueOf2 = Long.valueOf(((long) i) * (valueOf.longValue() + Long.valueOf(split[i3]).longValue()));
                i3++;
                valueOf = valueOf2;
            }
            return valueOf;
        } catch (NumberFormatException e) {
            return l;
        }
    }

    /* renamed from: a */
    public static String m18043a(int i) {
        Calendar instance = Calendar.getInstance();
        instance.roll(6, i);
        return new SimpleDateFormat("yyyyMMdd").format(instance.getTime());
    }

    /* renamed from: a */
    public static String m18044a(long j) {
        return new SimpleDateFormat("yyyyMMdd").format(new Date(j));
    }

    /* renamed from: a */
    public static String m18045a(Context context, String str) {
        if (!StatConfig.isEnableConcurrentProcess()) {
            return str;
        }
        if (f16103m == null) {
            f16103m = C4539k.m18075q(context);
        }
        return f16103m != null ? str + "_" + f16103m : str;
    }

    /* renamed from: a */
    public static String m18046a(String str) {
        if (str == null) {
            return "0";
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                int i = b & 255;
                if (i < 16) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(Integer.toHexString(i));
            }
            return stringBuffer.toString();
        } catch (Throwable th) {
            return "0";
        }
    }

    /* renamed from: a */
    public static HttpHost m18047a(Context context) {
        if (context == null) {
            return null;
        }
        try {
            if (context.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", context.getPackageName()) != 0) {
                return null;
            }
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return null;
            }
            if (activeNetworkInfo.getTypeName() != null && activeNetworkInfo.getTypeName().equalsIgnoreCase("WIFI")) {
                return null;
            }
            String extraInfo = activeNetworkInfo.getExtraInfo();
            if (extraInfo == null) {
                return null;
            }
            if (extraInfo.equals("cmwap") || extraInfo.equals("3gwap") || extraInfo.equals("uniwap")) {
                return new HttpHost("10.0.0.172", 80);
            }
            if (extraInfo.equals("ctwap")) {
                return new HttpHost("10.0.0.200", 80);
            }
            String defaultHost = Proxy.getDefaultHost();
            if (defaultHost != null && defaultHost.trim().length() > 0) {
                return new HttpHost(defaultHost, Proxy.getDefaultPort());
            }
            return null;
        } catch (Throwable th) {
            f16101k.m18011e(th);
        }
    }

    /* renamed from: a */
    public static void m18048a(Context context, int i) {
        f16113w = i;
        C4544p.m18094b(context, "mta.qq.com.difftime", i);
    }

    /* renamed from: a */
    public static boolean m18049a(StatSpecifyReportedInfo statSpecifyReportedInfo) {
        return statSpecifyReportedInfo == null ? false : C4539k.m18056c(statSpecifyReportedInfo.getAppKey());
    }

    /* renamed from: a */
    public static byte[] m18050a(byte[] bArr) {
        InputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
        byte[] bArr2 = new byte[4096];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bArr.length * 2);
        while (true) {
            int read = gZIPInputStream.read(bArr2);
            if (read != -1) {
                byteArrayOutputStream.write(bArr2, 0, read);
            } else {
                bArr2 = byteArrayOutputStream.toByteArray();
                byteArrayInputStream.close();
                gZIPInputStream.close();
                byteArrayOutputStream.close();
                return bArr2;
            }
        }
    }

    /* renamed from: b */
    public static long m18051b(String str) {
        return C4539k.m18042a(str, ".", 100, 3, Long.valueOf(0)).longValue();
    }

    /* renamed from: b */
    public static synchronized StatLogger m18052b() {
        StatLogger statLogger;
        synchronized (C4539k.class) {
            if (f16101k == null) {
                statLogger = new StatLogger(StatConstants.LOG_TAG);
                f16101k = statLogger;
                statLogger.setDebugEnable(false);
            }
            statLogger = f16101k;
        }
        return statLogger;
    }

    /* renamed from: b */
    public static synchronized String m18053b(Context context) {
        String a;
        synchronized (C4539k.class) {
            if (f16091a == null || f16091a.trim().length() == 0) {
                a = C4545q.m18097a(context);
                f16091a = a;
                if (a == null || f16091a.trim().length() == 0) {
                    f16091a = Integer.toString(C4539k.m18064g().nextInt(Integer.MAX_VALUE));
                }
                a = f16091a;
            } else {
                a = f16091a;
            }
        }
        return a;
    }

    /* renamed from: c */
    public static long m18054c() {
        try {
            Calendar instance = Calendar.getInstance();
            instance.set(11, 0);
            instance.set(12, 0);
            instance.set(13, 0);
            instance.set(14, 0);
            return instance.getTimeInMillis() + 86400000;
        } catch (Throwable th) {
            f16101k.m18011e(th);
            return System.currentTimeMillis() + 86400000;
        }
    }

    /* renamed from: c */
    public static synchronized String m18055c(Context context) {
        String str;
        synchronized (C4539k.class) {
            if (f16093c == null || f16093c.trim().length() == 0) {
                f16093c = C4545q.m18102b(context);
            }
            str = f16093c;
        }
        return str;
    }

    /* renamed from: c */
    public static boolean m18056c(String str) {
        return (str == null || str.trim().length() == 0) ? false : true;
    }

    /* renamed from: d */
    public static DisplayMetrics m18057d(Context context) {
        if (f16096f == null) {
            f16096f = new DisplayMetrics();
            ((WindowManager) context.getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(f16096f);
        }
        return f16096f;
    }

    /* renamed from: d */
    public static String m18058d() {
        if (C4539k.m18056c(f16106p)) {
            return f16106p;
        }
        long e = C4539k.m18059e() / 1000000;
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        String str = String.valueOf((((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize())) / 1000000) + "/" + String.valueOf(e);
        f16106p = str;
        return str;
    }

    /* renamed from: e */
    public static long m18059e() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
    }

    /* renamed from: e */
    public static boolean m18060e(Context context) {
        try {
            if (C4545q.m18101a(context, "android.permission.ACCESS_WIFI_STATE")) {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity");
                if (connectivityManager != null) {
                    NetworkInfo[] allNetworkInfo = connectivityManager.getAllNetworkInfo();
                    if (allNetworkInfo != null) {
                        int i = 0;
                        while (i < allNetworkInfo.length) {
                            if (allNetworkInfo[i].getTypeName().equalsIgnoreCase("WIFI") && allNetworkInfo[i].isConnected()) {
                                return true;
                            }
                            i++;
                        }
                    }
                }
                return false;
            }
            f16101k.warn("can not get the permission of android.permission.ACCESS_WIFI_STATE");
            return false;
        } catch (Throwable th) {
            f16101k.m18011e(th);
        }
    }

    /* renamed from: f */
    public static String m18062f(Context context) {
        if (f16092b != null) {
            return f16092b;
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null) {
                String string = applicationInfo.metaData.getString("TA_APPKEY");
                if (string != null) {
                    f16092b = string;
                    return string;
                }
                f16101k.m18012i("Could not read APPKEY meta-data from AndroidManifest.xml");
            }
        } catch (Throwable th) {
            f16101k.m18012i("Could not read APPKEY meta-data from AndroidManifest.xml");
        }
        return null;
    }

    /* renamed from: g */
    public static String m18063g(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null) {
                Object obj = applicationInfo.metaData.get("InstallChannel");
                if (obj != null) {
                    return obj.toString();
                }
                f16101k.m18014w("Could not read InstallChannel meta-data from AndroidManifest.xml");
            }
        } catch (Throwable th) {
            f16101k.m18010e((Object) "Could not read InstallChannel meta-data from AndroidManifest.xml");
        }
        return null;
    }

    /* renamed from: g */
    private static synchronized Random m18064g() {
        Random random;
        synchronized (C4539k.class) {
            if (f16095e == null) {
                f16095e = new Random();
            }
            random = f16095e;
        }
        return random;
    }

    /* renamed from: h */
    private static long m18065h() {
        if (f16107q > 0) {
            return f16107q;
        }
        long j = 1;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
            j = (long) (Integer.valueOf(bufferedReader.readLine().split("\\s+")[1]).intValue() * 1024);
            bufferedReader.close();
        } catch (Exception e) {
        }
        f16107q = j;
        return j;
    }

    /* renamed from: h */
    public static String m18066h(Context context) {
        return context == null ? null : context.getClass().getName();
    }

    /* renamed from: i */
    public static String m18067i(Context context) {
        if (f16097g != null) {
            return f16097g;
        }
        try {
            if (!C4545q.m18101a(context, "android.permission.READ_PHONE_STATE")) {
                f16101k.m18010e((Object) "Could not get permission of android.permission.READ_PHONE_STATE");
            } else if (C4539k.m18069k(context)) {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(UserData.PHONE_KEY);
                if (telephonyManager != null) {
                    f16097g = telephonyManager.getSimOperator();
                }
            }
        } catch (Throwable th) {
            f16101k.m18011e(th);
        }
        return f16097g;
    }

    /* renamed from: j */
    public static String m18068j(Context context) {
        if (C4539k.m18056c(f16098h)) {
            return f16098h;
        }
        try {
            String str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            f16098h = str;
            if (str == null) {
                return "";
            }
        } catch (Throwable th) {
            f16101k.m18011e(th);
        }
        return f16098h;
    }

    /* renamed from: k */
    public static boolean m18069k(Context context) {
        return context.getPackageManager().checkPermission("android.permission.READ_PHONE_STATE", context.getPackageName()) == 0;
    }

    /* renamed from: l */
    public static String m18070l(Context context) {
        String str = "";
        try {
            if (C4545q.m18101a(context, "android.permission.INTERNET") && C4545q.m18101a(context, "android.permission.ACCESS_NETWORK_STATE")) {
                String typeName;
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
                if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                    typeName = activeNetworkInfo.getTypeName();
                    String extraInfo = activeNetworkInfo.getExtraInfo();
                    if (typeName != null) {
                        if (typeName.equalsIgnoreCase("WIFI")) {
                            return "WIFI";
                        }
                        if (typeName.equalsIgnoreCase("MOBILE")) {
                            return extraInfo != null ? extraInfo : "MOBILE";
                        } else {
                            if (extraInfo != null) {
                                return extraInfo;
                            }
                            return typeName;
                        }
                    }
                }
                typeName = str;
                return typeName;
            }
            f16101k.m18010e((Object) "can not get the permission of android.permission.ACCESS_WIFI_STATE");
            return str;
        } catch (Throwable th) {
            f16101k.m18011e(th);
            return str;
        }
    }

    /* renamed from: m */
    public static Integer m18071m(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(UserData.PHONE_KEY);
            if (telephonyManager != null) {
                return Integer.valueOf(telephonyManager.getNetworkType());
            }
        } catch (Throwable th) {
        }
        return null;
    }

    /* renamed from: n */
    public static String m18072n(Context context) {
        if (C4539k.m18056c(f16099i)) {
            return f16099i;
        }
        try {
            String str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            f16099i = str;
            if (str == null || f16099i.length() == 0) {
                return EnvironmentCompat.MEDIA_UNKNOWN;
            }
        } catch (Throwable th) {
            f16101k.m18011e(th);
        }
        return f16099i;
    }

    /* renamed from: o */
    public static int m18073o(Context context) {
        if (f16100j != -1) {
            return f16100j;
        }
        try {
            if (C4543o.m18089a()) {
                f16100j = 1;
            }
        } catch (Throwable th) {
            f16101k.m18011e(th);
        }
        f16100j = 0;
        return 0;
    }

    /* renamed from: p */
    public static String m18074p(Context context) {
        if (C4539k.m18056c(f16102l)) {
            return f16102l;
        }
        try {
            if (C4545q.m18101a(context, "android.permission.WRITE_EXTERNAL_STORAGE")) {
                String externalStorageState = Environment.getExternalStorageState();
                if (externalStorageState != null && externalStorageState.equals("mounted")) {
                    externalStorageState = Environment.getExternalStorageDirectory().getPath();
                    if (externalStorageState != null) {
                        StatFs statFs = new StatFs(externalStorageState);
                        long blockCount = (((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize())) / 1000000;
                        externalStorageState = String.valueOf((((long) statFs.getBlockSize()) * ((long) statFs.getAvailableBlocks())) / 1000000) + "/" + String.valueOf(blockCount);
                        f16102l = externalStorageState;
                        return externalStorageState;
                    }
                }
                return null;
            }
            f16101k.warn("can not get the permission of android.permission.WRITE_EXTERNAL_STORAGE");
            return null;
        } catch (Throwable th) {
            f16101k.m18011e(th);
        }
    }

    /* renamed from: q */
    static String m18075q(Context context) {
        try {
            if (f16103m != null) {
                return f16103m;
            }
            int myPid = Process.myPid();
            for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
                if (runningAppProcessInfo.pid == myPid) {
                    f16103m = runningAppProcessInfo.processName;
                    break;
                }
            }
            return f16103m;
        } catch (Throwable th) {
        }
    }

    /* renamed from: r */
    public static String m18076r(Context context) {
        return C4539k.m18045a(context, StatConstants.DATABASE_NAME);
    }

    /* renamed from: s */
    public static synchronized Integer m18077s(Context context) {
        Integer valueOf;
        int i = 0;
        synchronized (C4539k.class) {
            if (f16104n <= 0) {
                f16104n = C4544p.m18090a(context, "MTA_EVENT_INDEX", 0);
                C4544p.m18094b(context, "MTA_EVENT_INDEX", f16104n + 1000);
            } else if (f16104n % 1000 == 0) {
                try {
                    int i2 = f16104n + 1000;
                    if (f16104n < 2147383647) {
                        i = i2;
                    }
                    C4544p.m18094b(context, "MTA_EVENT_INDEX", i);
                } catch (Throwable th) {
                    f16101k.m18014w(th);
                }
            }
            i = f16104n + 1;
            f16104n = i;
            valueOf = Integer.valueOf(i);
        }
        return valueOf;
    }

    /* renamed from: t */
    public static String m18078t(Context context) {
        try {
            return String.valueOf(C4539k.m18039D(context) / 1000000) + "/" + String.valueOf(C4539k.m18065h() / 1000000);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: u */
    public static JSONObject m18079u(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("n", C4540l.m18085a());
            String d = C4540l.m18088d();
            if (d != null && d.length() > 0) {
                jSONObject.put("na", d);
            }
            int b = C4540l.m18086b();
            if (b > 0) {
                jSONObject.put("fx", b / 1000000);
            }
            b = C4540l.m18087c();
            if (b > 0) {
                jSONObject.put("fn", b / 1000000);
            }
        } catch (Throwable th) {
            Log.w(StatConstants.LOG_TAG, "get cpu error", th);
        }
        return jSONObject;
    }

    /* renamed from: v */
    public static String m18080v(Context context) {
        if (C4539k.m18056c(f16108r)) {
            return f16108r;
        }
        try {
            SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
            if (sensorManager != null) {
                List sensorList = sensorManager.getSensorList(-1);
                if (sensorList != null) {
                    StringBuilder stringBuilder = new StringBuilder(sensorList.size() * 10);
                    for (int i = 0; i < sensorList.size(); i++) {
                        stringBuilder.append(((Sensor) sensorList.get(i)).getType());
                        if (i != sensorList.size() - 1) {
                            stringBuilder.append(",");
                        }
                    }
                    f16108r = stringBuilder.toString();
                }
            }
        } catch (Throwable th) {
            f16101k.m18011e(th);
        }
        return f16108r;
    }

    /* renamed from: w */
    public static synchronized int m18081w(Context context) {
        int i;
        synchronized (C4539k.class) {
            if (f16111u != -1) {
                i = f16111u;
            } else {
                C4539k.m18082x(context);
                i = f16111u;
            }
        }
        return i;
    }

    /* renamed from: x */
    public static void m18082x(Context context) {
        int a = C4544p.m18090a(context, f16110t, 1);
        f16111u = a;
        if (a == 1) {
            C4544p.m18094b(context, f16110t, 0);
        }
    }

    /* renamed from: y */
    public static boolean m18083y(Context context) {
        if (f16112v < 0) {
            f16112v = C4544p.m18091a(context, "mta.qq.com.checktime", 0);
        }
        return Math.abs(System.currentTimeMillis() - f16112v) > 86400000;
    }

    /* renamed from: z */
    public static void m18084z(Context context) {
        f16112v = System.currentTimeMillis();
        C4544p.m18095b(context, "mta.qq.com.checktime", f16112v);
    }
}
