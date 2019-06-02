package com.baidu.android.bbalbs.common.p040a;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.baidu.android.bbalbs.common.a.a */
public final class C1007a {
    /* renamed from: a */
    public static byte[] m3526a(String str, String str2, byte[] bArr) throws Exception {
        Key secretKeySpec = new SecretKeySpec(str2.getBytes(), "AES");
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(1, secretKeySpec, new IvParameterSpec(str.getBytes()));
        return instance.doFinal(bArr);
    }

    /* renamed from: b */
    public static byte[] m3527b(String str, String str2, byte[] bArr) throws Exception {
        Key secretKeySpec = new SecretKeySpec(str2.getBytes(), "AES");
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(2, secretKeySpec, new IvParameterSpec(str.getBytes()));
        return instance.doFinal(bArr);
    }
}
