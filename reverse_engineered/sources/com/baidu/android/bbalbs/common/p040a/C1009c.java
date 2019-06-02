package com.baidu.android.bbalbs.common.p040a;

import java.security.MessageDigest;

/* renamed from: com.baidu.android.bbalbs.common.a.c */
public final class C1009c {
    /* renamed from: a */
    public static String m3531a(byte[] bArr, String str, boolean z) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bArr) {
            String toHexString = Integer.toHexString(b & 255);
            if (z) {
                toHexString = toHexString.toUpperCase();
            }
            if (toHexString.length() == 1) {
                stringBuilder.append("0");
            }
            stringBuilder.append(toHexString).append(str);
        }
        return stringBuilder.toString();
    }

    /* renamed from: a */
    public static String m3532a(byte[] bArr, boolean z) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.reset();
            instance.update(bArr);
            return C1009c.m3531a(instance.digest(), "", z);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
