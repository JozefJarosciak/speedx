package com.alipay.sdk.encrypt;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.alipay.sdk.encrypt.e */
public final class C0855e {
    /* renamed from: a */
    private static String f2118a = "DESede/CBC/PKCS5Padding";

    /* renamed from: a */
    public static byte[] m3295a(String str, byte[] bArr) {
        byte[] bArr2 = null;
        try {
            Key secretKeySpec = new SecretKeySpec(str.getBytes(), "DESede");
            AlgorithmParameterSpec ivParameterSpec = new IvParameterSpec(new byte[8]);
            Cipher instance = Cipher.getInstance(f2118a);
            instance.init(1, secretKeySpec, ivParameterSpec);
            bArr2 = instance.doFinal(bArr);
        } catch (Exception e) {
        }
        return bArr2;
    }

    /* renamed from: b */
    public static byte[] m3297b(String str, byte[] bArr) {
        byte[] bArr2 = null;
        try {
            Key secretKeySpec = new SecretKeySpec(str.getBytes(), "DESede");
            AlgorithmParameterSpec ivParameterSpec = new IvParameterSpec(new byte[8]);
            Cipher instance = Cipher.getInstance(f2118a);
            instance.init(2, secretKeySpec, ivParameterSpec);
            bArr2 = instance.doFinal(bArr);
        } catch (Exception e) {
        }
        return bArr2;
    }

    /* renamed from: a */
    public static String m3294a(String str, String str2) {
        String str3 = null;
        try {
            str3 = C0851a.m3282a(C0855e.m3295a(str, str2.getBytes()));
        } catch (Exception e) {
        }
        return str3;
    }

    /* renamed from: b */
    public static String m3296b(String str, String str2) {
        try {
            return new String(C0855e.m3297b(str, C0851a.m3284a(str2)));
        } catch (Exception e) {
            return null;
        }
    }
}
