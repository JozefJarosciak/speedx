package com.qiniu.android.p189c;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.CRC32;

/* compiled from: Crc32 */
/* renamed from: com.qiniu.android.c.b */
public final class C4335b {
    /* renamed from: a */
    public static long m17120a(byte[] bArr, int i, int i2) {
        CRC32 crc32 = new CRC32();
        crc32.update(bArr, i, i2);
        return crc32.getValue();
    }

    /* renamed from: a */
    public static long m17119a(byte[] bArr) {
        return C4335b.m17120a(bArr, 0, bArr.length);
    }

    /* renamed from: a */
    public static long m17118a(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bArr = new byte[65536];
        CRC32 crc32 = new CRC32();
        while (true) {
            try {
                int read = fileInputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                crc32.update(bArr, 0, read);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                fileInputStream.close();
            }
        }
        return crc32.getValue();
    }
}
