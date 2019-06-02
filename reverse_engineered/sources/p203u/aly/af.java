package p203u.aly;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import ch.qos.logback.core.CoreConstants;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import com.umeng.analytics.C4731a;
import com.umeng.analytics.C4743c;
import com.umeng.analytics.MobclickAgent$EScenarioType;
import io.rong.imlib.statistics.UserData;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.Locale;
import java.util.Properties;

/* compiled from: DeviceConfig */
/* renamed from: u.aly.af */
public class af {
    /* renamed from: a */
    protected static final String f18579a = af.class.getName();

    /* renamed from: a */
    public static String m21111a(Context context) {
        try {
            return String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
        } catch (NameNotFoundException e) {
            return "";
        }
    }

    /* renamed from: b */
    public static String m21117b(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            return "";
        }
    }

    /* renamed from: a */
    public static boolean m21115a(Context context, String str) {
        if (VERSION.SDK_INT >= 23) {
            try {
                boolean z;
                if (((Integer) Class.forName("android.content.Context").getMethod("checkSelfPermission", new Class[]{String.class}).invoke(context, new Object[]{str})).intValue() == 0) {
                    z = true;
                } else {
                    z = false;
                }
                return z;
            } catch (Exception e) {
                return false;
            }
        } else if (context.getPackageManager().checkPermission(str, context.getPackageName()) == 0) {
            return true;
        } else {
            return false;
        }
    }

    /* renamed from: b */
    private static String m21116b() {
        int i = 0;
        if (C4743c.f16636a) {
            String[] strArr = new String[]{"/sys/class/net/wlan0/address", "/sys/class/net/eth0/address", "/sys/devices/virtual/net/wlan0/address"};
            while (i < strArr.length) {
                try {
                    String a = af.m21112a(strArr[i]);
                    if (a != null) {
                        return a;
                    }
                    i++;
                } catch (Throwable e) {
                    ah.m21166d(f18579a, "open file  Failed", e);
                }
            }
        }
        return null;
    }

    /* renamed from: a */
    private static String m21112a(String str) throws FileNotFoundException {
        Throwable e;
        Throwable th;
        String str2 = null;
        Reader fileReader = new FileReader(str);
        if (fileReader != null) {
            BufferedReader bufferedReader;
            try {
                bufferedReader = new BufferedReader(fileReader, 1024);
                try {
                    str2 = bufferedReader.readLine();
                    if (fileReader != null) {
                        try {
                            fileReader.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                } catch (IOException e3) {
                    e = e3;
                    try {
                        ah.m21166d(f18579a, "Could not read from file " + str, e);
                        if (fileReader != null) {
                            try {
                                fileReader.close();
                            } catch (IOException e222) {
                                e222.printStackTrace();
                            }
                        }
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e2222) {
                                e2222.printStackTrace();
                            }
                        }
                        return str2;
                    } catch (Throwable th2) {
                        th = th2;
                        if (fileReader != null) {
                            try {
                                fileReader.close();
                            } catch (IOException e22222) {
                                e22222.printStackTrace();
                            }
                        }
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e222222) {
                                e222222.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
            } catch (IOException e4) {
                e = e4;
                bufferedReader = str2;
                ah.m21166d(f18579a, "Could not read from file " + str, e);
                if (fileReader != null) {
                    fileReader.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                return str2;
            } catch (Throwable e5) {
                bufferedReader = str2;
                th = e5;
                if (fileReader != null) {
                    fileReader.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                throw th;
            }
        }
        return str2;
    }

    /* renamed from: a */
    public static String m21110a() {
        Throwable th;
        String str;
        Throwable th2;
        String str2 = null;
        try {
            Reader fileReader = new FileReader("/proc/cpuinfo");
            if (fileReader != null) {
                try {
                    BufferedReader bufferedReader = new BufferedReader(fileReader, 1024);
                    str2 = bufferedReader.readLine();
                    bufferedReader.close();
                    fileReader.close();
                } catch (Throwable e) {
                    try {
                        ah.m21166d(f18579a, "Could not read from file /proc/cpuinfo", e);
                    } catch (Throwable e2) {
                        th = e2;
                        str = str2;
                        th2 = th;
                        ah.m21166d(f18579a, "Could not open file /proc/cpuinfo", th2);
                        str2 = str;
                        if (str2 != null) {
                            return "";
                        }
                        return str2.substring(str2.indexOf(58) + 1).trim();
                    }
                }
            }
        } catch (Throwable e22) {
            th = e22;
            str = str2;
            th2 = th;
            ah.m21166d(f18579a, "Could not open file /proc/cpuinfo", th2);
            str2 = str;
            if (str2 != null) {
                return str2.substring(str2.indexOf(58) + 1).trim();
            }
            return "";
        }
        if (str2 != null) {
            return str2.substring(str2.indexOf(58) + 1).trim();
        }
        return "";
    }

    /* renamed from: c */
    public static String m21119c(Context context) {
        if (MobclickAgent$EScenarioType.E_UM_ANALYTICS_OEM.toValue() == C4731a.m18621d(context) || MobclickAgent$EScenarioType.E_UM_GAME_OEM.toValue() == C4731a.m18621d(context)) {
            return af.m21108A(context);
        }
        return af.m21144z(context);
    }

    /* renamed from: d */
    public static String m21121d(Context context) {
        return ag.m21150b(af.m21119c(context));
    }

    /* renamed from: e */
    public static String m21123e(Context context) {
        if (af.m21124f(context) == null) {
            return null;
        }
        int i = context.getResources().getConfiguration().mcc;
        int i2 = context.getResources().getConfiguration().mnc;
        if (i == 0) {
            return null;
        }
        String valueOf = String.valueOf(i2);
        if (i2 < 10) {
            valueOf = String.format("%02d", new Object[]{Integer.valueOf(i2)});
        }
        return new StringBuffer().append(String.valueOf(i)).append(valueOf).toString();
    }

    /* renamed from: f */
    public static String m21124f(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(UserData.PHONE_KEY);
        if (af.m21115a(context, "android.permission.READ_PHONE_STATE")) {
            return telephonyManager.getSubscriberId();
        }
        return null;
    }

    /* renamed from: g */
    public static String m21125g(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(UserData.PHONE_KEY);
            if (!af.m21115a(context, "android.permission.READ_PHONE_STATE")) {
                return "";
            }
            if (telephonyManager == null) {
                return "";
            }
            return telephonyManager.getNetworkOperatorName();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: h */
    public static String[] m21126h(Context context) {
        String[] strArr = new String[]{"", ""};
        try {
            if (af.m21115a(context, "android.permission.ACCESS_NETWORK_STATE")) {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                if (connectivityManager == null) {
                    strArr[0] = "";
                    return strArr;
                } else if (connectivityManager.getNetworkInfo(1).getState() == State.CONNECTED) {
                    strArr[0] = "Wi-Fi";
                    return strArr;
                } else {
                    NetworkInfo networkInfo = connectivityManager.getNetworkInfo(0);
                    if (networkInfo.getState() == State.CONNECTED) {
                        strArr[0] = "2G/3G";
                        strArr[1] = networkInfo.getSubtypeName();
                        return strArr;
                    }
                    return strArr;
                }
            }
            strArr[0] = "";
            return strArr;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: i */
    public static boolean m21127i(Context context) {
        return "Wi-Fi".equals(af.m21126h(context)[0]);
    }

    /* renamed from: j */
    public static boolean m21128j(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.isConnectedOrConnecting();
            }
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    /* renamed from: k */
    public static int m21129k(Context context) {
        try {
            Calendar instance = Calendar.getInstance(af.m21142x(context));
            if (instance != null) {
                return instance.getTimeZone().getRawOffset() / CoreConstants.MILLIS_IN_ONE_HOUR;
            }
        } catch (Throwable e) {
            ah.m21159b(f18579a, "error in getTimeZone", e);
        }
        return 8;
    }

    /* renamed from: l */
    public static boolean m21130l(Context context) {
        Object a = cf.m21838a(context).m21846b().m21827a("");
        if (TextUtils.isEmpty(a)) {
            if (af.m21124f(context) == null) {
                a = af.m21131m(context)[0];
                if (!TextUtils.isEmpty(a) && a.equalsIgnoreCase("cn")) {
                    return true;
                }
            }
            int i = context.getResources().getConfiguration().mcc;
            if (i == 460 || i == 461) {
                return true;
            }
            if (i == 0) {
                a = af.m21131m(context)[0];
                if (!TextUtils.isEmpty(a) && a.equalsIgnoreCase("cn")) {
                    return true;
                }
            }
            return false;
        } else if (a.equals("cn")) {
            return true;
        } else {
            return false;
        }
    }

    /* renamed from: m */
    public static String[] m21131m(Context context) {
        String[] strArr = new String[2];
        try {
            Locale x = af.m21142x(context);
            if (x != null) {
                strArr[0] = x.getCountry();
                strArr[1] = x.getLanguage();
            }
            if (TextUtils.isEmpty(strArr[0])) {
                strArr[0] = "Unknown";
            }
            if (TextUtils.isEmpty(strArr[1])) {
                strArr[1] = "Unknown";
            }
        } catch (Throwable e) {
            ah.m21166d(f18579a, "error in getLocaleInfo", e);
        }
        return strArr;
    }

    /* renamed from: x */
    private static Locale m21142x(Context context) {
        Locale locale = null;
        try {
            Configuration configuration = new Configuration();
            configuration.setToDefaults();
            System.getConfiguration(context.getContentResolver(), configuration);
            if (configuration != null) {
                locale = configuration.locale;
            }
        } catch (Exception e) {
            ah.m21161b(f18579a, "fail to read user config locale");
        }
        if (locale == null) {
            return Locale.getDefault();
        }
        return locale;
    }

    /* renamed from: n */
    public static String m21132n(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null) {
                String string = applicationInfo.metaData.getString("UMENG_APPKEY");
                if (string != null) {
                    return string.trim();
                }
                ah.m21161b(f18579a, "getAppkey failed. the applicationinfo is null!");
            }
        } catch (Throwable e) {
            ah.m21166d(f18579a, "Could not read UMENG_APPKEY meta-data from AndroidManifest.xml.", e);
        }
        return null;
    }

    /* renamed from: o */
    public static String m21133o(Context context) {
        if (VERSION.SDK_INT < 23) {
            return af.m21143y(context);
        }
        String b = af.m21116b();
        if (b == null) {
            return af.m21143y(context);
        }
        return b;
    }

    /* renamed from: y */
    private static String m21143y(Context context) {
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService(MapboxEvent.ATTRIBUTE_WIFI);
            if (af.m21115a(context, "android.permission.ACCESS_WIFI_STATE")) {
                return wifiManager.getConnectionInfo().getMacAddress();
            }
            ah.m21164c(f18579a, "Could not get mac address.[no permission android.permission.ACCESS_WIFI_STATE");
            return "";
        } catch (Exception e) {
            ah.m21164c(f18579a, "Could not get mac address." + e.toString());
            return "";
        }
    }

    /* renamed from: p */
    public static int[] m21134p(Context context) {
        try {
            int a;
            int a2;
            int i;
            Object displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            if ((context.getApplicationInfo().flags & 8192) == 0) {
                a = af.m21109a(displayMetrics, "noncompatWidthPixels");
                a2 = af.m21109a(displayMetrics, "noncompatHeightPixels");
            } else {
                a2 = -1;
                a = -1;
            }
            if (a == -1 || a2 == -1) {
                i = displayMetrics.widthPixels;
                a = displayMetrics.heightPixels;
            } else {
                i = a;
                a = a2;
            }
            int[] iArr = new int[2];
            if (i > a) {
                iArr[0] = a;
                iArr[1] = i;
                return iArr;
            }
            iArr[0] = i;
            iArr[1] = a;
            return iArr;
        } catch (Throwable e) {
            ah.m21166d(f18579a, "read resolution fail", e);
            return null;
        }
    }

    /* renamed from: a */
    private static int m21109a(Object obj, String str) {
        try {
            Field declaredField = DisplayMetrics.class.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField.getInt(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /* renamed from: q */
    public static String m21135q(Context context) {
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
                    ah.m21156a(f18579a, "Could not read UMENG_CHANNEL meta-data from AndroidManifest.xml.");
                }
            }
        } catch (Exception e) {
            ah.m21156a(f18579a, "Could not read UMENG_CHANNEL meta-data from AndroidManifest.xml.");
            e.printStackTrace();
        }
        return str;
    }

    /* renamed from: r */
    public static String m21136r(Context context) {
        return context.getPackageName();
    }

    /* renamed from: s */
    public static String m21137s(Context context) {
        PackageInfo packageInfo;
        String str = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(af.m21136r(context), 64);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            Object obj = str;
        }
        InputStream byteArrayInputStream = new ByteArrayInputStream(packageInfo.signatures[0].toByteArray());
        try {
            CertificateFactory instance = CertificateFactory.getInstance("X509");
        } catch (CertificateException e2) {
            e2.printStackTrace();
            obj = str;
        }
        try {
            X509Certificate x509Certificate = (X509Certificate) instance.generateCertificate(byteArrayInputStream);
        } catch (CertificateException e22) {
            e22.printStackTrace();
            obj = str;
        }
        try {
            str = af.m21114a(MessageDigest.getInstance("MD5").digest(x509Certificate.getEncoded()));
        } catch (NoSuchAlgorithmException e3) {
            e3.printStackTrace();
        } catch (CertificateEncodingException e4) {
            e4.printStackTrace();
        }
        return str;
    }

    /* renamed from: a */
    private static String m21114a(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            String toHexString = Integer.toHexString(bArr[i]);
            int length = toHexString.length();
            if (length == 1) {
                toHexString = "0" + toHexString;
            }
            if (length > 2) {
                toHexString = toHexString.substring(length - 2, length);
            }
            stringBuilder.append(toHexString.toUpperCase());
            if (i < bArr.length - 1) {
                stringBuilder.append(CoreConstants.COLON_CHAR);
            }
        }
        return stringBuilder.toString();
    }

    /* renamed from: t */
    public static String m21138t(Context context) {
        return context.getPackageManager().getApplicationLabel(context.getApplicationInfo()).toString();
    }

    /* renamed from: u */
    public static String m21139u(Context context) {
        String str = null;
        try {
            str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.loadLabel(context.getPackageManager()).toString();
        } catch (Throwable e) {
            ah.m21155a(f18579a, e);
        }
        return str;
    }

    /* renamed from: z */
    private static String m21144z(Context context) {
        Throwable e;
        CharSequence charSequence = "";
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(UserData.PHONE_KEY);
        String deviceId;
        if (VERSION.SDK_INT >= 23) {
            if (telephonyManager != null) {
                try {
                    if (af.m21115a(context, "android.permission.READ_PHONE_STATE")) {
                        deviceId = telephonyManager.getDeviceId();
                        try {
                            ah.m21156a(f18579a, "getDeviceId, IMEI: " + deviceId);
                        } catch (Exception e2) {
                            e = e2;
                            ah.m21163c(f18579a, "No IMEI.", e);
                            if (!TextUtils.isEmpty(deviceId)) {
                                return deviceId;
                            }
                            deviceId = af.m21116b();
                            ah.m21156a(f18579a, "getDeviceId, mc: " + deviceId);
                            if (!TextUtils.isEmpty(deviceId)) {
                                return deviceId;
                            }
                            deviceId = Secure.getString(context.getContentResolver(), "android_id");
                            ah.m21156a(f18579a, "getDeviceId, android_id: " + deviceId);
                            if (!TextUtils.isEmpty(deviceId)) {
                                return deviceId;
                            }
                            if (VERSION.SDK_INT >= 9) {
                                deviceId = Build.SERIAL;
                            }
                            ah.m21156a(f18579a, "getDeviceId, serial no: " + deviceId);
                            return deviceId;
                        }
                        if (!TextUtils.isEmpty(deviceId)) {
                            return deviceId;
                        }
                        deviceId = af.m21116b();
                        ah.m21156a(f18579a, "getDeviceId, mc: " + deviceId);
                        if (!TextUtils.isEmpty(deviceId)) {
                            return deviceId;
                        }
                        deviceId = Secure.getString(context.getContentResolver(), "android_id");
                        ah.m21156a(f18579a, "getDeviceId, android_id: " + deviceId);
                        if (!TextUtils.isEmpty(deviceId)) {
                            return deviceId;
                        }
                        if (VERSION.SDK_INT >= 9) {
                            deviceId = Build.SERIAL;
                        }
                        ah.m21156a(f18579a, "getDeviceId, serial no: " + deviceId);
                        return deviceId;
                    }
                } catch (Throwable e3) {
                    Throwable th = e3;
                    deviceId = charSequence;
                    e = th;
                    ah.m21163c(f18579a, "No IMEI.", e);
                    if (!TextUtils.isEmpty(deviceId)) {
                        return deviceId;
                    }
                    deviceId = af.m21116b();
                    ah.m21156a(f18579a, "getDeviceId, mc: " + deviceId);
                    if (!TextUtils.isEmpty(deviceId)) {
                        return deviceId;
                    }
                    deviceId = Secure.getString(context.getContentResolver(), "android_id");
                    ah.m21156a(f18579a, "getDeviceId, android_id: " + deviceId);
                    if (!TextUtils.isEmpty(deviceId)) {
                        return deviceId;
                    }
                    if (VERSION.SDK_INT >= 9) {
                        deviceId = Build.SERIAL;
                    }
                    ah.m21156a(f18579a, "getDeviceId, serial no: " + deviceId);
                    return deviceId;
                }
            }
            CharSequence charSequence2 = charSequence;
            if (!TextUtils.isEmpty(deviceId)) {
                return deviceId;
            }
            deviceId = af.m21116b();
            ah.m21156a(f18579a, "getDeviceId, mc: " + deviceId);
            if (!TextUtils.isEmpty(deviceId)) {
                return deviceId;
            }
            deviceId = Secure.getString(context.getContentResolver(), "android_id");
            ah.m21156a(f18579a, "getDeviceId, android_id: " + deviceId);
            if (!TextUtils.isEmpty(deviceId)) {
                return deviceId;
            }
            if (VERSION.SDK_INT >= 9) {
                deviceId = Build.SERIAL;
            }
            ah.m21156a(f18579a, "getDeviceId, serial no: " + deviceId);
            return deviceId;
        }
        if (telephonyManager != null) {
            try {
                if (af.m21115a(context, "android.permission.READ_PHONE_STATE")) {
                    charSequence = telephonyManager.getDeviceId();
                }
            } catch (Throwable e32) {
                ah.m21163c(f18579a, "No IMEI.", e32);
            }
        }
        if (!TextUtils.isEmpty(charSequence)) {
            return charSequence;
        }
        ah.m21164c(f18579a, "No IMEI.");
        deviceId = af.m21133o(context);
        if (!TextUtils.isEmpty(deviceId)) {
            return deviceId;
        }
        ah.m21164c(f18579a, "Failed to take mac as IMEI. Try to use Secure.ANDROID_ID instead.");
        deviceId = Secure.getString(context.getContentResolver(), "android_id");
        ah.m21156a(f18579a, "getDeviceId: Secure.ANDROID_ID: " + deviceId);
        return deviceId;
    }

    /* renamed from: A */
    private static String m21108A(Context context) {
        Throwable e;
        String str = "";
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(UserData.PHONE_KEY);
        String string;
        if (VERSION.SDK_INT >= 23) {
            string = Secure.getString(context.getContentResolver(), "android_id");
            ah.m21156a(f18579a, "getDeviceId, android_id: " + string);
            if (!TextUtils.isEmpty(string)) {
                return string;
            }
            string = af.m21116b();
            ah.m21156a(f18579a, "getDeviceId, mc: " + string);
            if (!TextUtils.isEmpty(string)) {
                return string;
            }
            if (telephonyManager != null) {
                try {
                    if (af.m21115a(context, "android.permission.READ_PHONE_STATE")) {
                        str = telephonyManager.getDeviceId();
                        try {
                            ah.m21156a(f18579a, "getDeviceId, IMEI: " + str);
                        } catch (Exception e2) {
                            e = e2;
                            ah.m21163c(f18579a, "No IMEI.", e);
                            if (!TextUtils.isEmpty(str)) {
                                return str;
                            }
                            if (VERSION.SDK_INT >= 9) {
                                str = Build.SERIAL;
                            }
                            ah.m21156a(f18579a, "getDeviceId, serial no: " + str);
                            return str;
                        }
                        if (!TextUtils.isEmpty(str)) {
                            return str;
                        }
                        if (VERSION.SDK_INT >= 9) {
                            str = Build.SERIAL;
                        }
                        ah.m21156a(f18579a, "getDeviceId, serial no: " + str);
                        return str;
                    }
                } catch (Throwable e3) {
                    Throwable th = e3;
                    str = string;
                    e = th;
                    ah.m21163c(f18579a, "No IMEI.", e);
                    if (!TextUtils.isEmpty(str)) {
                        return str;
                    }
                    if (VERSION.SDK_INT >= 9) {
                        str = Build.SERIAL;
                    }
                    ah.m21156a(f18579a, "getDeviceId, serial no: " + str);
                    return str;
                }
            }
            str = string;
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
            if (VERSION.SDK_INT >= 9) {
                str = Build.SERIAL;
            }
            ah.m21156a(f18579a, "getDeviceId, serial no: " + str);
            return str;
        }
        string = Secure.getString(context.getContentResolver(), "android_id");
        ah.m21156a(f18579a, "getDeviceId: Secure.ANDROID_ID: " + string);
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        ah.m21164c(f18579a, "No IMEI.");
        string = af.m21133o(context);
        if (!TextUtils.isEmpty(string) || telephonyManager == null) {
            return string;
        }
        try {
            if (af.m21115a(context, "android.permission.READ_PHONE_STATE")) {
                str = telephonyManager.getDeviceId();
            } else {
                str = string;
            }
            return str;
        } catch (Throwable e32) {
            ah.m21163c(f18579a, "No IMEI.", e32);
            return string;
        }
    }

    /* renamed from: v */
    public static String m21140v(Context context) {
        Properties c = af.m21120c();
        try {
            String property = c.getProperty("ro.miui.ui.version.name");
            if (!TextUtils.isEmpty(property)) {
                return "MIUI";
            }
            if (af.m21122d()) {
                return "Flyme";
            }
            if (TextUtils.isEmpty(af.m21113a(c))) {
                return property;
            }
            return "YunOS";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: w */
    public static String m21141w(Context context) {
        Properties c = af.m21120c();
        try {
            String property = c.getProperty("ro.miui.ui.version.name");
            if (!TextUtils.isEmpty(property)) {
                return property;
            }
            if (af.m21122d()) {
                try {
                    return af.m21118b(c);
                } catch (Exception e) {
                    return property;
                }
            }
            try {
                return af.m21113a(c);
            } catch (Exception e2) {
                return property;
            }
        } catch (Exception e3) {
            e3.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    private static String m21113a(Properties properties) {
        Object property = properties.getProperty("ro.yunos.version");
        return !TextUtils.isEmpty(property) ? property : null;
    }

    /* renamed from: b */
    private static String m21118b(Properties properties) {
        try {
            String toLowerCase = properties.getProperty("ro.build.display.id").toLowerCase(Locale.getDefault());
            if (toLowerCase.contains("flyme os")) {
                return toLowerCase.split(" ")[2];
            }
        } catch (Exception e) {
        }
        return null;
    }

    /* renamed from: c */
    private static Properties m21120c() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    /* renamed from: d */
    private static boolean m21122d() {
        try {
            Build.class.getMethod("hasSmartBar", new Class[0]);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
