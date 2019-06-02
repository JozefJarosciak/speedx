package com.beastbikes.android.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/* compiled from: ZipUtils */
public class ab {
    /* renamed from: a */
    public static void m12777a(Collection<File> collection, File file) throws IOException {
        ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(file), 1048576));
        for (File a : collection) {
            m12776a(a, zipOutputStream, "");
        }
        zipOutputStream.close();
    }

    /* renamed from: a */
    private static void m12776a(File file, ZipOutputStream zipOutputStream, String str) {
        BufferedInputStream bufferedInputStream;
        IOException e;
        Throwable th;
        if (file != null && zipOutputStream != null) {
            String str2;
            String str3 = str + (str.trim().length() == 0 ? "" : File.separator) + file.getName();
            try {
                str2 = new String(str3.getBytes(), "utf-8");
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
                str2 = str3;
            }
            if (file.isDirectory()) {
                for (File a : file.listFiles()) {
                    m12776a(a, zipOutputStream, str2);
                }
                return;
            }
            byte[] bArr = new byte[1048576];
            try {
                bufferedInputStream = new BufferedInputStream(new FileInputStream(file), 1048576);
                try {
                    zipOutputStream.putNextEntry(new ZipEntry(str2));
                    while (true) {
                        int read = bufferedInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        zipOutputStream.write(bArr, 0, read);
                    }
                    zipOutputStream.flush();
                    zipOutputStream.closeEntry();
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                } catch (IOException e4) {
                    e3 = e4;
                    try {
                        e3.printStackTrace();
                        if (bufferedInputStream != null) {
                            try {
                                bufferedInputStream.close();
                            } catch (IOException e32) {
                                e32.printStackTrace();
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (bufferedInputStream != null) {
                            try {
                                bufferedInputStream.close();
                            } catch (IOException e5) {
                                e5.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
            } catch (IOException e6) {
                e32 = e6;
                bufferedInputStream = null;
                e32.printStackTrace();
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedInputStream = null;
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                throw th;
            }
        }
    }
}
