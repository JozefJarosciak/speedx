package com.alipay.p029b.p030a.p031a.p032a.p033a;

import ch.qos.logback.core.net.ssl.SSL;
import java.lang.reflect.Method;
import java.security.Key;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.alipay.b.a.a.a.a.c */
public final class C0788c {
    /* renamed from: a */
    private static String f1853a = new String("idnjfhncnsfuobcnt847y929o449u474w7j3h22aoddc98euk#%&&)*&^%#");

    /* renamed from: a */
    public static String m3012a() {
        String str = new String();
        for (int i = 0; i < f1853a.length() - 1; i += 4) {
            str = str + f1853a.charAt(i);
        }
        return str;
    }

    /* renamed from: a */
    public static String m3013a(String str, String str2) {
        byte[] bArr = null;
        try {
            byte[] a = C0788c.m3014a(str.getBytes());
            byte[] bytes = str2.getBytes();
            Key secretKeySpec = new SecretKeySpec(a, "AES");
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(1, secretKeySpec, new IvParameterSpec(new byte[instance.getBlockSize()]));
            bArr = instance.doFinal(bytes);
        } catch (Exception e) {
        }
        return C0788c.m3016b(bArr);
    }

    /* renamed from: a */
    private static byte[] m3014a(byte[] bArr) {
        KeyGenerator instance = KeyGenerator.getInstance("AES");
        SecureRandom instance2 = SecureRandom.getInstance(SSL.DEFAULT_SECURE_RANDOM_ALGORITHM, "Crypto");
        Method method = SecureRandom.class.getMethod("setSeed", new Class[]{bArr.getClass()});
        method.setAccessible(true);
        method.invoke(instance2, new Object[]{bArr});
        instance.init(128, instance2);
        return instance.generateKey().getEncoded();
    }

    /* renamed from: b */
    public static String m3015b(String str, String str2) {
        try {
            byte[] a = C0788c.m3014a(str.getBytes());
            int length = str2.length() / 2;
            byte[] bArr = new byte[length];
            for (int i = 0; i < length; i++) {
                bArr[i] = Integer.valueOf(str2.substring(i * 2, (i * 2) + 2), 16).byteValue();
            }
            Key secretKeySpec = new SecretKeySpec(a, "AES");
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(2, secretKeySpec, new IvParameterSpec(new byte[instance.getBlockSize()]));
            return new String(instance.doFinal(bArr));
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: b */
    private static String m3016b(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (byte b : bArr) {
            stringBuffer.append("0123456789ABCDEF".charAt((b >> 4) & 15)).append("0123456789ABCDEF".charAt(b & 15));
        }
        return stringBuffer.toString();
    }
}
