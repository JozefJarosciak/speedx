package com.baidu.android.bbalbs.common.p040a;

import java.security.MessageDigest;

/* renamed from: com.baidu.android.bbalbs.common.a.d */
public final class C1010d {
    /* renamed from: a */
    public static byte[] m3533a(byte[] bArr) {
        try {
            return MessageDigest.getInstance("SHA-1").digest(bArr);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
