package com.alipay.p029b.p030a.p031a.p032a.p033a;

import com.alipay.p029b.p030a.p031a.p032a.C0789a;
import java.security.MessageDigest;

/* renamed from: com.alipay.b.a.a.a.a.b */
public final class C0787b {
    /* renamed from: a */
    public static String m3011a(String str) {
        String str2 = null;
        try {
            if (!C0789a.m3020a(str)) {
                MessageDigest instance = MessageDigest.getInstance("SHA-1");
                instance.update(str.getBytes("UTF-8"));
                byte[] digest = instance.digest();
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < digest.length; i++) {
                    stringBuilder.append(String.format("%02x", new Object[]{Byte.valueOf(digest[i])}));
                }
                str2 = stringBuilder.toString();
            }
        } catch (Exception e) {
        }
        return str2;
    }
}
