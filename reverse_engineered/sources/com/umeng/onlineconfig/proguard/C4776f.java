package com.umeng.onlineconfig.proguard;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;

/* compiled from: OnlineConfigDeflaterHelper */
/* renamed from: com.umeng.onlineconfig.proguard.f */
public class C4776f {
    /* renamed from: a */
    public static int f16725a;

    /* renamed from: a */
    public static byte[] m18743a(String str, String str2) throws IOException {
        if (C4778h.m18754a(str)) {
            return null;
        }
        return C4776f.m18744a(str.getBytes(str2));
    }

    /* renamed from: a */
    public static byte[] m18744a(byte[] bArr) throws IOException {
        Throwable th;
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        Deflater deflater = new Deflater();
        deflater.setInput(bArr);
        deflater.finish();
        byte[] bArr2 = new byte[8192];
        f16725a = 0;
        ByteArrayOutputStream byteArrayOutputStream;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            while (!deflater.finished()) {
                try {
                    int deflate = deflater.deflate(bArr2);
                    f16725a += deflate;
                    byteArrayOutputStream.write(bArr2, 0, deflate);
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            deflater.end();
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th3) {
            Throwable th4 = th3;
            byteArrayOutputStream = null;
            th = th4;
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            throw th;
        }
    }
}
