package com.baidu.location;

import com.baidu.platform.comapi.location.CoordinateType;

public class Jni {
    /* renamed from: a */
    private static int f2301a = 0;
    /* renamed from: b */
    private static int f2302b = 1;
    /* renamed from: c */
    private static int f2303c = 2;
    /* renamed from: d */
    private static int f2304d = 11;
    /* renamed from: e */
    private static int f2305e = 12;
    /* renamed from: f */
    private static int f2306f = 13;
    /* renamed from: g */
    private static int f2307g = 14;
    /* renamed from: h */
    private static int f2308h = 15;
    /* renamed from: i */
    private static int f2309i = 1024;
    /* renamed from: j */
    private static boolean f2310j;

    static {
        f2310j = false;
        try {
            System.loadLibrary("locSDK7a");
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            f2310j = true;
        }
    }

    /* renamed from: a */
    private static native String m3631a(byte[] bArr, int i);

    /* renamed from: b */
    private static native String m3632b(double d, double d2, int i, int i2);

    /* renamed from: c */
    private static native String m3633c(byte[] bArr, int i);

    public static double[] coorEncrypt(double d, double d2, String str) {
        double[] dArr = new double[]{0.0d, 0.0d};
        if (f2310j) {
            return dArr;
        }
        int i = -1;
        if (str.equals(BDLocation.BDLOCATION_GCJ02_TO_BD09)) {
            i = f2301a;
        } else if (str.equals("bd09ll")) {
            i = f2302b;
        } else if (str.equals(CoordinateType.GCJ02)) {
            i = f2303c;
        } else if (str.equals(BDLocation.BDLOCATION_WGS84_TO_GCJ02)) {
            i = f2304d;
        } else if (str.equals(BDLocation.BDLOCATION_BD09_TO_GCJ02)) {
            i = f2305e;
        } else if (str.equals(BDLocation.BDLOCATION_BD09LL_TO_GCJ02)) {
            i = f2306f;
        } else if (str.equals("wgs842mc")) {
            i = f2308h;
        }
        try {
            String[] split = m3632b(d, d2, str.equals("gcj2wgs") ? 16 : i, 132456).split(":");
            dArr[0] = Double.parseDouble(split[0]);
            dArr[1] = Double.parseDouble(split[1]);
        } catch (UnsatisfiedLinkError e) {
        }
        return dArr;
    }

    public static String decodeIBeacon(byte[] bArr, byte[] bArr2) {
        return f2310j ? null : ib(bArr, bArr2);
    }

    private static native String ee(String str, int i);

    public static String en1(String str) {
        int i = 740;
        int i2 = 0;
        if (f2310j) {
            return "err!";
        }
        if (str == null) {
            return "null";
        }
        byte[] bytes = str.getBytes();
        byte[] bArr = new byte[f2309i];
        int length = bytes.length;
        if (length <= 740) {
            i = length;
        }
        length = 0;
        while (i2 < i) {
            if (bytes[i2] != (byte) 0) {
                bArr[length] = bytes[i2];
                length++;
            }
            i2++;
        }
        String str2 = "err!";
        try {
            return m3631a(bArr, 132456);
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            return "err!";
        }
    }

    public static String encode(String str) {
        return f2310j ? "err!" : en1(str) + "|tp=3";
    }

    public static String encode2(String str) {
        if (f2310j) {
            return "err!";
        }
        if (str == null) {
            return "null";
        }
        String str2 = "err!";
        try {
            return m3633c(str.getBytes(), 132456);
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            return "err!";
        }
    }

    public static Long encode3(String str) {
        Long l = null;
        if (!f2310j) {
            String str2;
            String str3 = "";
            try {
                str2 = new String(str.getBytes(), "UTF-8");
            } catch (Exception e) {
                str2 = str3;
            }
            try {
                l = Long.valueOf(murmur(str2));
            } catch (UnsatisfiedLinkError e2) {
                e2.printStackTrace();
            }
        }
        return l;
    }

    private static native String encodeNotLimit(String str, int i);

    public static String encodeOfflineLocationUpdateRequest(String str) {
        if (f2310j) {
            return "err!";
        }
        String str2;
        String str3 = "";
        try {
            str2 = new String(str.getBytes(), "UTF-8");
        } catch (Exception e) {
            str2 = str3;
        }
        str3 = "err!";
        try {
            str2 = encodeNotLimit(str2, 132456);
        } catch (UnsatisfiedLinkError e2) {
            e2.printStackTrace();
            str2 = "err!";
        }
        return str2 + "|tp=3";
    }

    public static String encodeTp4(String str) {
        if (f2310j) {
            return "err!";
        }
        String str2;
        String str3 = "";
        try {
            str2 = new String(str.getBytes(), "UTF-8");
        } catch (Exception e) {
            str2 = str3;
        }
        str3 = "err!";
        try {
            str2 = ee(str2, 132456);
        } catch (UnsatisfiedLinkError e2) {
            e2.printStackTrace();
            str2 = "err!";
        }
        return str2 + "|tp=4";
    }

    /* renamed from: f */
    private static native void m3634f(byte[] bArr, byte[] bArr2);

    /* renamed from: g */
    private static native String m3635g(byte[] bArr);

    public static double getGpsSwiftRadius(float f, double d, double d2) {
        double d3 = 0.0d;
        if (!f2310j) {
            try {
                d3 = gsr(f, d, d2);
            } catch (UnsatisfiedLinkError e) {
            }
        }
        return d3;
    }

    public static String getSkyKey() {
        if (f2310j) {
            return "err!";
        }
        String str = "err!";
        try {
            return sky();
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            return "err!";
        }
    }

    private static native double gsr(float f, double d, double d2);

    public static String gtr2(String str) {
        if (f2310j) {
            return null;
        }
        try {
            String g = m3635g(str.getBytes());
            return (g == null || g.length() < 2 || "no".equals(g)) ? null : g;
        } catch (UnsatisfiedLinkError e) {
            return null;
        }
    }

    private static native String ib(byte[] bArr, byte[] bArr2);

    private static native long murmur(String str);

    public static void removeSoList(int i, String str) {
    }

    private static native String sky();

    public static void tr2(String str, String str2) {
        if (!f2310j) {
            try {
                m3634f(str.getBytes(), str2.getBytes());
            } catch (UnsatisfiedLinkError e) {
                e.printStackTrace();
            }
        }
    }

    public static native void uninstall(String str);
}
