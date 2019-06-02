package com.baidu.platform.comapi.util;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import com.alipay.sdk.util.C0880h;
import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

/* renamed from: com.baidu.platform.comapi.util.a */
public class C1276a {

    /* renamed from: com.baidu.platform.comapi.util.a$a */
    static class C1275a {
        /* renamed from: a */
        public static String m4819a(byte[] bArr) {
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
    public static String m4820a(Context context) {
        String packageName = context.getPackageName();
        return C1276a.m4821a(context, packageName) + C0880h.f2220b + packageName;
    }

    /* renamed from: a */
    private static String m4821a(Context context, String str) {
        String a;
        String str2 = "";
        try {
            a = C1276a.m4822a((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(context.getPackageManager().getPackageInfo(str, 64).signatures[0].toByteArray())));
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            a = str2;
        } catch (CertificateException e2) {
            e2.printStackTrace();
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
    static String m4822a(X509Certificate x509Certificate) {
        try {
            return C1275a.m4819a(C1276a.m4823a(x509Certificate.getEncoded()));
        } catch (CertificateEncodingException e) {
            return null;
        }
    }

    /* renamed from: a */
    static byte[] m4823a(byte[] bArr) {
        try {
            return MessageDigest.getInstance("SHA1").digest(bArr);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
