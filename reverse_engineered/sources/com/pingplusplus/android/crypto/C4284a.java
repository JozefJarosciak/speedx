package com.pingplusplus.android.crypto;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.pingplusplus.android.crypto.a */
public class C4284a {
    /* renamed from: a */
    private static String f14956a = "AES/CBC/PKCS5Padding";

    /* renamed from: a */
    public static String m16978a(byte[] bArr, byte[] bArr2) {
        return new String(C4284a.m16981c(bArr, bArr2));
    }

    /* renamed from: a */
    public static byte[] m16979a(byte[] bArr, String str) {
        return C4284a.m16980b(bArr, str.getBytes());
    }

    /* renamed from: b */
    private static byte[] m16980b(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[16];
        int i = 0;
        while (i < bArr.length && i < bArr3.length) {
            bArr3[i] = bArr[i];
            i++;
        }
        Key secretKeySpec = new SecretKeySpec(bArr, "AES");
        Cipher instance = Cipher.getInstance(f14956a);
        instance.init(1, secretKeySpec, new IvParameterSpec(bArr3));
        return instance.doFinal(bArr2);
    }

    /* renamed from: c */
    private static byte[] m16981c(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[16];
        int i = 0;
        while (i < bArr.length && i < bArr3.length) {
            bArr3[i] = bArr[i];
            i++;
        }
        Key secretKeySpec = new SecretKeySpec(bArr, "AES");
        Cipher instance = Cipher.getInstance(f14956a);
        instance.init(2, secretKeySpec, new IvParameterSpec(bArr3));
        return instance.doFinal(bArr2);
    }
}
