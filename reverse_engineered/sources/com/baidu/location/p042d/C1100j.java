package com.baidu.location.p042d;

import android.content.Context;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Process;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import com.baidu.android.bbalbs.common.p040a.C1008b;
import com.baidu.location.BDLocation;
import com.baidu.location.C1102f;
import com.baidu.location.p043b.C1072a;
import com.baidu.location.p043b.C1074b;
import com.baidu.location.p043b.C1079d;
import com.baidu.location.p043b.C1082g;
import com.baidu.platform.comapi.location.CoordinateType;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Locale;

/* renamed from: com.baidu.location.d.j */
public class C1100j {
    /* renamed from: A */
    public static float f2704A = 3.8f;
    /* renamed from: B */
    public static int f2705B = 3;
    /* renamed from: C */
    public static int f2706C = 10;
    /* renamed from: D */
    public static int f2707D = 2;
    /* renamed from: E */
    public static int f2708E = 7;
    /* renamed from: F */
    public static int f2709F = 20;
    /* renamed from: G */
    public static int f2710G = 70;
    /* renamed from: H */
    public static int f2711H = 120;
    /* renamed from: I */
    public static float f2712I = 2.0f;
    /* renamed from: J */
    public static float f2713J = 10.0f;
    /* renamed from: K */
    public static float f2714K = 50.0f;
    /* renamed from: L */
    public static float f2715L = 200.0f;
    /* renamed from: M */
    public static int f2716M = 16;
    /* renamed from: N */
    public static float f2717N = 0.9f;
    /* renamed from: O */
    public static int f2718O = 10000;
    /* renamed from: P */
    public static float f2719P = 0.5f;
    /* renamed from: Q */
    public static float f2720Q = 0.0f;
    /* renamed from: R */
    public static float f2721R = 0.1f;
    /* renamed from: S */
    public static int f2722S = 30;
    /* renamed from: T */
    public static int f2723T = 100;
    /* renamed from: U */
    public static int f2724U = 0;
    /* renamed from: V */
    public static int f2725V = 0;
    /* renamed from: W */
    public static int f2726W = 0;
    /* renamed from: X */
    public static int f2727X = 420000;
    /* renamed from: Y */
    public static boolean f2728Y = true;
    /* renamed from: Z */
    public static boolean f2729Z = true;
    /* renamed from: a */
    public static boolean f2730a = false;
    public static int aa = 20;
    public static int ab = 300;
    public static int ac = 1000;
    public static long ad = 900000;
    public static long ae = 420000;
    public static long af = 180000;
    public static long ag = 0;
    public static long ah = 15;
    public static long ai = 300000;
    public static int aj = 1000;
    public static int ak = 0;
    public static int al = 30000;
    public static int am = 30000;
    public static float an = 10.0f;
    public static float ao = 6.0f;
    public static float ap = 10.0f;
    public static int aq = 60;
    public static int ar = 70;
    public static int as = 6;
    private static String at = "http://loc.map.baidu.com/sdk.php";
    private static String au = "http://loc.map.baidu.com/user_err.php";
    private static String av = "http://loc.map.baidu.com/oqur.php";
    private static String aw = "http://loc.map.baidu.com/tcu.php";
    private static String ax = "http://loc.map.baidu.com/rtbu.php";
    private static String ay = "http://loc.map.baidu.com/iofd.php";
    private static String az = "http://loc.map.baidu.com/wloc";
    /* renamed from: b */
    public static boolean f2731b = false;
    /* renamed from: c */
    public static boolean f2732c = false;
    /* renamed from: d */
    public static int f2733d = 0;
    /* renamed from: e */
    public static String f2734e = "http://loc.map.baidu.com/sdk_ep.php";
    /* renamed from: f */
    public static String f2735f = "https://loc.map.baidu.com/sdk.php";
    /* renamed from: g */
    public static String f2736g = "no";
    /* renamed from: h */
    public static boolean f2737h = false;
    /* renamed from: i */
    public static boolean f2738i = false;
    /* renamed from: j */
    public static boolean f2739j = false;
    /* renamed from: k */
    public static boolean f2740k = false;
    /* renamed from: l */
    public static boolean f2741l = false;
    /* renamed from: m */
    public static String f2742m = CoordinateType.GCJ02;
    /* renamed from: n */
    public static boolean f2743n = true;
    /* renamed from: o */
    public static int f2744o = 3;
    /* renamed from: p */
    public static double f2745p = 0.0d;
    /* renamed from: q */
    public static double f2746q = 0.0d;
    /* renamed from: r */
    public static double f2747r = 0.0d;
    /* renamed from: s */
    public static double f2748s = 0.0d;
    /* renamed from: t */
    public static int f2749t = 0;
    /* renamed from: u */
    public static byte[] f2750u = null;
    /* renamed from: v */
    public static boolean f2751v = false;
    /* renamed from: w */
    public static int f2752w = 0;
    /* renamed from: x */
    public static float f2753x = 1.1f;
    /* renamed from: y */
    public static float f2754y = 2.2f;
    /* renamed from: z */
    public static float f2755z = 2.3f;

    /* renamed from: a */
    public static int m4006a(Context context) {
        try {
            return System.getInt(context.getContentResolver(), "airplane_mode_on", 0);
        } catch (Exception e) {
            return 2;
        }
    }

    /* renamed from: a */
    public static int m4007a(String str, String str2, String str3) {
        int i = Integer.MIN_VALUE;
        if (!(str == null || str.equals(""))) {
            int indexOf = str.indexOf(str2);
            if (indexOf != -1) {
                indexOf += str2.length();
                int indexOf2 = str.indexOf(str3, indexOf);
                if (indexOf2 != -1) {
                    String substring = str.substring(indexOf, indexOf2);
                    if (!(substring == null || substring.equals(""))) {
                        try {
                            i = Integer.parseInt(substring);
                        } catch (NumberFormatException e) {
                        }
                    }
                }
            }
        }
        return i;
    }

    /* renamed from: a */
    public static Object m4008a(Context context, String str) {
        Object obj = null;
        if (context != null) {
            try {
                obj = context.getApplicationContext().getSystemService(str);
            } catch (Throwable th) {
            }
        }
        return obj;
    }

    /* renamed from: a */
    public static Object m4009a(Object obj, String str, Object... objArr) throws Exception {
        Class cls = obj.getClass();
        Class[] clsArr = new Class[objArr.length];
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            clsArr[i] = objArr[i].getClass();
            if (clsArr[i] == Integer.class) {
                clsArr[i] = Integer.TYPE;
            }
        }
        Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
        if (!declaredMethod.isAccessible()) {
            declaredMethod.setAccessible(true);
        }
        return declaredMethod.invoke(obj, objArr);
    }

    /* renamed from: a */
    public static String m4010a() {
        Calendar instance = Calendar.getInstance();
        int i = instance.get(5);
        int i2 = instance.get(1);
        int i3 = instance.get(2) + 1;
        int i4 = instance.get(11);
        int i5 = instance.get(12);
        int i6 = instance.get(13);
        return String.format(Locale.CHINA, "%d-%02d-%02d %02d:%02d:%02d", new Object[]{Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i), Integer.valueOf(i4), Integer.valueOf(i5), Integer.valueOf(i6)});
    }

    /* renamed from: a */
    public static String m4011a(C1072a c1072a, C1082g c1082g, Location location, String str, int i) {
        String b;
        StringBuffer stringBuffer = new StringBuffer(1024);
        if (c1072a != null) {
            b = C1074b.m3866a().m3879b(c1072a);
            if (b != null) {
                stringBuffer.append(b);
            }
        }
        if (c1082g != null) {
            b = i == 0 ? c1082g.m3949b() : c1082g.m3952c();
            if (b != null) {
                stringBuffer.append(b);
            }
        }
        if (location != null) {
            b = (f2733d == 0 || i == 0) ? C1079d.m3913b(location) : C1079d.m3921c(location);
            if (b != null) {
                stringBuffer.append(b);
            }
        }
        boolean z = false;
        if (i == 0) {
            z = true;
        }
        b = C1091b.m3989a().m3990a(z);
        if (b != null) {
            stringBuffer.append(b);
        }
        if (str != null) {
            stringBuffer.append(str);
        }
        if (i == 0) {
            if (c1072a != null) {
                b = C1074b.m3866a().m3878a(c1072a);
                if (b != null && b.length() + stringBuffer.length() < 750) {
                    stringBuffer.append(b);
                }
            }
            b = stringBuffer.toString();
            if (location != null || c1082g == null) {
                f2744o = 3;
            } else {
                try {
                    float speed = location.getSpeed();
                    int i2 = f2733d;
                    int d = c1082g.m3954d();
                    int a = c1082g.m3945a();
                    boolean e = c1082g.m3955e();
                    if (speed < ao && ((i2 == 1 || i2 == 0) && (d < aq || e))) {
                        f2744o = 1;
                    } else if (speed >= ap || (!(i2 == 1 || i2 == 0 || i2 == 3) || (d >= ar && a <= as))) {
                        f2744o = 3;
                    } else {
                        f2744o = 2;
                    }
                } catch (Exception e2) {
                    f2744o = 3;
                }
            }
            return b;
        }
        if (c1072a != null) {
            b = C1074b.m3866a().m3878a(c1072a);
            stringBuffer.append(b);
        }
        b = stringBuffer.toString();
        if (location != null) {
        }
        f2744o = 3;
        return b;
    }

    /* renamed from: a */
    public static String m4012a(File file, String str) {
        if (!file.isFile()) {
            return null;
        }
        byte[] bArr = new byte[1024];
        try {
            MessageDigest instance = MessageDigest.getInstance(str);
            FileInputStream fileInputStream = new FileInputStream(file);
            while (true) {
                int read = fileInputStream.read(bArr, 0, 1024);
                if (read != -1) {
                    instance.update(bArr, 0, read);
                } else {
                    fileInputStream.close();
                    return new BigInteger(1, instance.digest()).toString(16);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static boolean m4013a(BDLocation bDLocation) {
        int locType = bDLocation.getLocType();
        return locType > 100 && locType < 200;
    }

    /* renamed from: b */
    public static int m4014b(Context context) {
        int i = -1;
        if (VERSION.SDK_INT < 23) {
            return -2;
        }
        try {
            return Secure.getInt(context.getContentResolver(), "location_mode", -1);
        } catch (Exception e) {
            return i;
        }
    }

    /* renamed from: b */
    private static int m4015b(Context context, String str) {
        int i;
        try {
            i = context.checkPermission(str, Process.myPid(), Process.myUid()) == 0 ? 1 : 0;
        } catch (Exception e) {
            i = 1;
        }
        return i == 0 ? 0 : 1;
    }

    /* renamed from: b */
    public static int m4016b(Object obj, String str, Object... objArr) throws Exception {
        Class cls = obj.getClass();
        Class[] clsArr = new Class[objArr.length];
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            clsArr[i] = objArr[i].getClass();
            if (clsArr[i] == Integer.class) {
                clsArr[i] = Integer.TYPE;
            }
        }
        Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
        if (!declaredMethod.isAccessible()) {
            declaredMethod.setAccessible(true);
        }
        return ((Integer) declaredMethod.invoke(obj, objArr)).intValue();
    }

    /* renamed from: b */
    public static String m4017b() {
        try {
            Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration inetAddresses = ((NetworkInterface) networkInterfaces.nextElement()).getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress) inetAddresses.nextElement();
                    if (!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address)) {
                        byte[] address = inetAddress.getAddress();
                        int i = 0;
                        String str = "";
                        while (i < address.length) {
                            String toHexString = Integer.toHexString(address[i] & 255);
                            if (toHexString.length() == 1) {
                                toHexString = '0' + toHexString;
                            }
                            i++;
                            str = str + toHexString;
                        }
                        return str;
                    }
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    /* renamed from: b */
    public static boolean m4018b(String str, String str2, String str3) {
        try {
            PublicKey generatePublic = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(C1008b.m3529a(str3.getBytes())));
            Signature instance = Signature.getInstance("SHA1WithRSA");
            instance.initVerify(generatePublic);
            instance.update(str.getBytes());
            return instance.verify(C1008b.m3529a(str2.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: c */
    public static String m4019c() {
        return at;
    }

    /* renamed from: c */
    public static String m4020c(Context context) {
        int b = C1100j.m4015b(context, "android.permission.ACCESS_COARSE_LOCATION");
        int b2 = C1100j.m4015b(context, "android.permission.ACCESS_FINE_LOCATION");
        return "&per=" + b + "|" + b2 + "|" + C1100j.m4015b(context, "android.permission.READ_PHONE_STATE");
    }

    /* renamed from: d */
    public static String m4021d() {
        return aw;
    }

    /* renamed from: d */
    public static String m4022d(Context context) {
        int type;
        int i = -1;
        if (context != null) {
            try {
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
                if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                    type = activeNetworkInfo.getType();
                    i = type;
                    return "&netc=" + i;
                }
            } catch (Exception e) {
            }
        }
        type = -1;
        i = type;
        return "&netc=" + i;
    }

    /* renamed from: e */
    public static String m4023e() {
        try {
            if (!Environment.getExternalStorageState().equals("mounted")) {
                return null;
            }
            String path = Environment.getExternalStorageDirectory().getPath();
            File file = new File(path + "/baidu/tempdata");
            if (file.exists()) {
                return path;
            }
            file.mkdirs();
            return path;
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: f */
    public static String m4024f() {
        String e = C1100j.m4023e();
        return e == null ? null : e + "/baidu/tempdata";
    }

    /* renamed from: g */
    public static String m4025g() {
        try {
            File file = new File(C1102f.getServiceContext().getFilesDir() + File.separator + "lldt");
            if (!file.exists()) {
                file.mkdirs();
            }
            return file.getAbsolutePath();
        } catch (Exception e) {
            return null;
        }
    }
}
