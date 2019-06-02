package com.baidu.lbsapi.auth;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import android.util.Base64;
import com.alipay.sdk.util.C0880h;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Locale;

/* renamed from: com.baidu.lbsapi.auth.b */
class C1019b {

    /* renamed from: com.baidu.lbsapi.auth.b$a */
    static class C1018a {
        /* renamed from: a */
        public static String m3592a(byte[] bArr) {
            char[] cArr = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
            StringBuilder stringBuilder = new StringBuilder(bArr.length * 2);
            for (int i = 0; i < bArr.length; i++) {
                stringBuilder.append(cArr[(bArr[i] & 240) >> 4]);
                stringBuilder.append(cArr[bArr[i] & 15]);
            }
            return stringBuilder.toString();
        }
    }

    /* renamed from: a */
    static String m3593a() {
        return Locale.getDefault().getLanguage();
    }

    /* renamed from: a */
    protected static String m3594a(Context context) {
        String packageName = context.getPackageName();
        return C1019b.m3595a(context, packageName) + C0880h.f2220b + packageName;
    }

    /* renamed from: a */
    private static String m3595a(Context context, String str) {
        String a;
        String str2 = "";
        try {
            a = C1019b.m3596a((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(context.getPackageManager().getPackageInfo(str, 64).signatures[0].toByteArray())));
        } catch (NameNotFoundException e) {
            a = str2;
        } catch (CertificateException e2) {
            a = str2;
        }
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        while (i < a.length()) {
            stringBuffer.append(a.charAt(i));
            if (i > 0 && i % 2 == 1 && i < a.length() - 1) {
                stringBuffer.append(":");
            }
            i++;
        }
        return stringBuffer.toString();
    }

    /* renamed from: a */
    static String m3596a(X509Certificate x509Certificate) {
        try {
            return C1018a.m3592a(C1019b.m3597a(x509Certificate.getEncoded()));
        } catch (CertificateEncodingException e) {
            return null;
        }
    }

    /* renamed from: a */
    static byte[] m3597a(byte[] bArr) {
        try {
            return MessageDigest.getInstance("SHA1").digest(bArr);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    /* renamed from: b */
    protected static String[] m3598b(Context context) {
        String packageName = context.getPackageName();
        String[] b = C1019b.m3599b(context, packageName);
        if (b == null || b.length <= 0) {
            return null;
        }
        String[] strArr = new String[b.length];
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = b[i] + C0880h.f2220b + packageName;
            if (C1017a.f2273a) {
                C1017a.m3589a("mcode" + strArr[i]);
            }
        }
        return strArr;
    }

    /* renamed from: b */
    private static String[] m3599b(Context context, String str) {
        String[] strArr;
        int i;
        StringBuffer stringBuffer;
        int i2;
        String[] strArr2 = null;
        String[] strArr3;
        try {
            String[] strArr4;
            Signature[] signatureArr = context.getPackageManager().getPackageInfo(str, 64).signatures;
            if (signatureArr == null || signatureArr.length <= 0) {
                strArr4 = null;
            } else {
                strArr3 = new String[signatureArr.length];
                int i3 = 0;
                while (i3 < signatureArr.length) {
                    try {
                        strArr3[i3] = C1019b.m3596a((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(signatureArr[i3].toByteArray())));
                        i3++;
                    } catch (NameNotFoundException e) {
                    } catch (CertificateException e2) {
                    }
                }
                strArr4 = strArr3;
            }
            strArr = strArr4;
        } catch (NameNotFoundException e3) {
            strArr3 = null;
            strArr = strArr3;
            strArr2 = new String[strArr.length];
            i = 0;
            while (i < strArr.length) {
                stringBuffer = new StringBuffer();
                i2 = 0;
                while (i2 < strArr[i].length()) {
                    stringBuffer.append(strArr[i].charAt(i2));
                    stringBuffer.append(":");
                    i2++;
                }
                strArr2[i] = stringBuffer.toString();
                i++;
            }
            return strArr2;
        } catch (CertificateException e4) {
            strArr3 = null;
            strArr = strArr3;
            strArr2 = new String[strArr.length];
            i = 0;
            while (i < strArr.length) {
                stringBuffer = new StringBuffer();
                i2 = 0;
                while (i2 < strArr[i].length()) {
                    stringBuffer.append(strArr[i].charAt(i2));
                    stringBuffer.append(":");
                    i2++;
                }
                strArr2[i] = stringBuffer.toString();
                i++;
            }
            return strArr2;
        }
        if (strArr != null && strArr.length > 0) {
            strArr2 = new String[strArr.length];
            i = 0;
            while (i < strArr.length) {
                stringBuffer = new StringBuffer();
                i2 = 0;
                while (i2 < strArr[i].length()) {
                    stringBuffer.append(strArr[i].charAt(i2));
                    if (i2 > 0 && i2 % 2 == 1 && i2 < strArr[i].length() - 1) {
                        stringBuffer.append(":");
                    }
                    i2++;
                }
                strArr2[i] = stringBuffer.toString();
                i++;
            }
        }
        return strArr2;
    }

    /* renamed from: c */
    static String m3600c(Context context) {
        String str = null;
        if (null == null || "".equals(null)) {
            str = context.getSharedPreferences("mac", 0).getString("macaddr", null);
            if (str == null) {
                str = C1019b.m3602d(context);
                if (str != null) {
                    str = Base64.encodeToString(str.getBytes(), 0);
                    if (!TextUtils.isEmpty(str)) {
                        context.getSharedPreferences("mac", 0).edit().putString("macaddr", str).commit();
                    }
                } else {
                    str = "";
                }
            }
        }
        if (C1017a.f2273a) {
            C1017a.m3589a("getMacID mac_adress: " + str);
        }
        return str;
    }

    /* renamed from: c */
    private static boolean m3601c(Context context, String str) {
        boolean z = context.checkCallingOrSelfPermission(str) != -1;
        if (C1017a.f2273a) {
            C1017a.m3589a("hasPermission " + z + " | " + str);
        }
        return z;
    }

    /* renamed from: d */
    static String m3602d(Context context) {
        String macAddress;
        Exception e;
        try {
            if (C1019b.m3601c(context, "android.permission.ACCESS_WIFI_STATE")) {
                macAddress = ((WifiManager) context.getSystemService(MapboxEvent.ATTRIBUTE_WIFI)).getConnectionInfo().getMacAddress();
                try {
                    if (!TextUtils.isEmpty(macAddress)) {
                        Base64.encode(macAddress.getBytes(), 0);
                    }
                    if (!C1017a.f2273a) {
                        return macAddress;
                    }
                    C1017a.m3589a(String.format("ssid=%s mac=%s", new Object[]{r2.getSSID(), r2.getMacAddress()}));
                    return macAddress;
                } catch (Exception e2) {
                    e = e2;
                    if (C1017a.f2273a) {
                        return macAddress;
                    }
                    C1017a.m3589a(e.toString());
                    return macAddress;
                }
            }
            if (C1017a.f2273a) {
                C1017a.m3589a("You need the android.Manifest.permission.ACCESS_WIFI_STATE permission. Open AndroidManifest.xml and just before the final </manifest> tag add:android.permission.ACCESS_WIFI_STATE");
            }
            return null;
        } catch (Exception e3) {
            Exception exception = e3;
            macAddress = null;
            e = exception;
            if (C1017a.f2273a) {
                return macAddress;
            }
            C1017a.m3589a(e.toString());
            return macAddress;
        }
    }
}
