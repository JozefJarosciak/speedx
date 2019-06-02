package com.alipay.sdk.encrypt;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.alipay.sdk.encrypt.b */
public final class C0852b {
    /* renamed from: a */
    private static String m3288a(String str, String str2) {
        return C0852b.m3287a(1, str, str2);
    }

    /* renamed from: b */
    private static String m3289b(String str, String str2) {
        return C0852b.m3287a(2, str, str2);
    }

    /* renamed from: a */
    public static String m3287a(int i, String str, String str2) {
        try {
            byte[] a;
            Key secretKeySpec = new SecretKeySpec(str2.getBytes(), "DES");
            Cipher instance = Cipher.getInstance("DES");
            instance.init(i, secretKeySpec);
            if (i == 2) {
                a = C0851a.m3284a(str);
            } else {
                a = str.getBytes("UTF-8");
            }
            byte[] doFinal = instance.doFinal(a);
            if (i == 2) {
                return new String(doFinal);
            }
            return C0851a.m3282a(doFinal);
        } catch (Exception e) {
            return null;
        }
    }
}
