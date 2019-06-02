package com.qiniu.android.p189c;

import android.util.Base64;
import java.io.UnsupportedEncodingException;

/* compiled from: UrlSafeBase64 */
/* renamed from: com.qiniu.android.c.e */
public final class C4339e {
    /* renamed from: a */
    public static String m17129a(String str) {
        try {
            return C4339e.m17130a(str.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static String m17130a(byte[] bArr) {
        return Base64.encodeToString(bArr, 10);
    }

    /* renamed from: b */
    public static byte[] m17131b(String str) {
        return Base64.decode(str, 10);
    }
}
