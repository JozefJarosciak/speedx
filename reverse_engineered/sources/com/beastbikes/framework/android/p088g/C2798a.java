package com.beastbikes.framework.android.p088g;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: AlgorithmUtils */
/* renamed from: com.beastbikes.framework.android.g.a */
public final class C2798a {
    /* renamed from: a */
    private static final Logger f13017a = LoggerFactory.getLogger(C2798a.class);

    /* renamed from: a */
    public static String m13751a(String str) {
        try {
            return C2798a.m13752a(MessageDigest.getInstance("MD5").digest(str.getBytes()));
        } catch (Throwable e) {
            f13017a.error(e.getMessage(), e);
            throw new UnsupportedOperationException(e);
        }
    }

    /* renamed from: a */
    public static String m13750a(File file) throws FileNotFoundException, IOException {
        InputStream fileInputStream;
        byte[] bArr = new byte[4096];
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            fileInputStream = new FileInputStream(file);
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                instance.update(bArr, 0, read);
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                }
            }
            return new BigInteger(1, instance.digest()).toString(16);
        } catch (Throwable e2) {
            f13017a.error(e2.getMessage(), e2);
            throw new UnsupportedOperationException(e2);
        } catch (Throwable th) {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e3) {
                }
            }
        }
    }

    /* renamed from: a */
    public static String m13752a(byte[] bArr) {
        int length = bArr.length;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(String.format("%02x", new Object[]{Integer.valueOf(bArr[i] & 255)}));
        }
        return stringBuilder.toString();
    }

    private C2798a() {
    }
}
