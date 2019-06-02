package io.rong.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtils {
    private static String TAG = "FileUtils";

    public static InputStream getFileInputStream(String str) {
        try {
            return new FileInputStream(new File(str));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] getByteFromUri(Uri uri) {
        byte[] bArr;
        InputStream fileInputStream = getFileInputStream(uri.getPath());
        int i = 0;
        while (i == 0) {
            try {
                i = fileInputStream.available();
                if (i == 0) {
                    break;
                }
            } catch (Exception e) {
                bArr = null;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e2) {
                    }
                }
            } catch (Throwable th) {
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e3) {
                    }
                }
            }
        }
        bArr = new byte[i];
        fileInputStream.read(bArr);
        if (fileInputStream != null) {
            try {
                fileInputStream.close();
            } catch (IOException e4) {
            }
        }
        return bArr;
    }

    public static void writeByte(Uri uri, byte[] bArr) {
        new File(uri.getPath().substring(0, uri.getPath().lastIndexOf("/"))).mkdirs();
        try {
            OutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(uri.getPath())));
            bufferedOutputStream.write(bArr);
            bufferedOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File convertBitmap2File(Bitmap bitmap, String str, String str2) {
        File file = new File(str);
        if (!file.exists()) {
            RLog.m19420e(TAG, "convertBitmap2File: dir does not exist! -" + file.getAbsolutePath());
            file.mkdirs();
        }
        File file2 = new File(file.getPath() + File.separator + str2);
        try {
            OutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file2));
            bitmap.compress(CompressFormat.JPEG, 100, bufferedOutputStream);
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            RLog.m19420e(TAG, "convertBitmap2File: Exception!");
        }
        return file2;
    }

    public static File copyFile(File file, String str, String str2) {
        File file2 = null;
        if (file.exists()) {
            file2 = new File(str);
            if (!file2.exists()) {
                RLog.m19419d(TAG, "copyFile: dir does not exist!");
                file2.mkdirs();
            }
            file2 = new File(str + str2);
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                }
                fileOutputStream.flush();
                fileOutputStream.close();
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                RLog.m19420e(TAG, "copyFile: Exception!");
            }
        } else {
            RLog.m19420e(TAG, "copyFile: src file does not exist! -" + file.getAbsolutePath());
        }
        return file2;
    }

    public static byte[] file2byte(File file) {
        byte[] bArr = null;
        if (file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArr2 = new byte[1024];
                while (true) {
                    int read = fileInputStream.read(bArr2);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr2, 0, read);
                }
                fileInputStream.close();
                byteArrayOutputStream.close();
                bArr = byteArrayOutputStream.toByteArray();
            } catch (Exception e) {
                e.printStackTrace();
                RLog.m19420e(TAG, "file2byte: Exception!");
            }
        } else {
            RLog.m19420e(TAG, "file2byte: src file does not exist! -" + file.getAbsolutePath());
        }
        return bArr;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.io.File byte2File(byte[] r6, java.lang.String r7, java.lang.String r8) {
        /*
        r2 = 0;
        r1 = new java.io.File;	 Catch:{ Exception -> 0x0058, all -> 0x007b }
        r1.<init>(r7);	 Catch:{ Exception -> 0x0058, all -> 0x007b }
        r0 = r1.exists();	 Catch:{ Exception -> 0x0058, all -> 0x007b }
        if (r0 != 0) goto L_0x0016;
    L_0x000c:
        r0 = TAG;	 Catch:{ Exception -> 0x0058, all -> 0x007b }
        r3 = "byte2File: dir does not exist!";
        io.rong.common.RLog.m19419d(r0, r3);	 Catch:{ Exception -> 0x0058, all -> 0x007b }
        r1.mkdirs();	 Catch:{ Exception -> 0x0058, all -> 0x007b }
    L_0x0016:
        r0 = new java.io.File;	 Catch:{ Exception -> 0x0058, all -> 0x007b }
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0058, all -> 0x007b }
        r3.<init>();	 Catch:{ Exception -> 0x0058, all -> 0x007b }
        r1 = r1.getPath();	 Catch:{ Exception -> 0x0058, all -> 0x007b }
        r1 = r3.append(r1);	 Catch:{ Exception -> 0x0058, all -> 0x007b }
        r3 = java.io.File.separator;	 Catch:{ Exception -> 0x0058, all -> 0x007b }
        r1 = r1.append(r3);	 Catch:{ Exception -> 0x0058, all -> 0x007b }
        r1 = r1.append(r8);	 Catch:{ Exception -> 0x0058, all -> 0x007b }
        r1 = r1.toString();	 Catch:{ Exception -> 0x0058, all -> 0x007b }
        r0.<init>(r1);	 Catch:{ Exception -> 0x0058, all -> 0x007b }
        r3 = new java.io.FileOutputStream;	 Catch:{ Exception -> 0x009d, all -> 0x007b }
        r3.<init>(r0);	 Catch:{ Exception -> 0x009d, all -> 0x007b }
        r4 = new java.io.BufferedOutputStream;	 Catch:{ Exception -> 0x00a0, all -> 0x0092 }
        r4.<init>(r3);	 Catch:{ Exception -> 0x00a0, all -> 0x0092 }
        r4.write(r6);	 Catch:{ Exception -> 0x00a5, all -> 0x0097 }
        if (r4 == 0) goto L_0x0048;
    L_0x0045:
        r4.close();	 Catch:{ IOException -> 0x004e }
    L_0x0048:
        if (r3 == 0) goto L_0x004d;
    L_0x004a:
        r3.close();	 Catch:{ IOException -> 0x0053 }
    L_0x004d:
        return r0;
    L_0x004e:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0048;
    L_0x0053:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x004d;
    L_0x0058:
        r0 = move-exception;
        r1 = r0;
        r3 = r2;
        r0 = r2;
    L_0x005c:
        r1.printStackTrace();	 Catch:{ all -> 0x009b }
        r1 = TAG;	 Catch:{ all -> 0x009b }
        r4 = "byte2File: Exception!";
        io.rong.common.RLog.m19420e(r1, r4);	 Catch:{ all -> 0x009b }
        if (r3 == 0) goto L_0x006b;
    L_0x0068:
        r3.close();	 Catch:{ IOException -> 0x0076 }
    L_0x006b:
        if (r2 == 0) goto L_0x004d;
    L_0x006d:
        r2.close();	 Catch:{ IOException -> 0x0071 }
        goto L_0x004d;
    L_0x0071:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x004d;
    L_0x0076:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x006b;
    L_0x007b:
        r0 = move-exception;
        r3 = r2;
    L_0x007d:
        if (r3 == 0) goto L_0x0082;
    L_0x007f:
        r3.close();	 Catch:{ IOException -> 0x0088 }
    L_0x0082:
        if (r2 == 0) goto L_0x0087;
    L_0x0084:
        r2.close();	 Catch:{ IOException -> 0x008d }
    L_0x0087:
        throw r0;
    L_0x0088:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0082;
    L_0x008d:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0087;
    L_0x0092:
        r0 = move-exception;
        r5 = r3;
        r3 = r2;
        r2 = r5;
        goto L_0x007d;
    L_0x0097:
        r0 = move-exception;
        r2 = r3;
        r3 = r4;
        goto L_0x007d;
    L_0x009b:
        r0 = move-exception;
        goto L_0x007d;
    L_0x009d:
        r1 = move-exception;
        r3 = r2;
        goto L_0x005c;
    L_0x00a0:
        r1 = move-exception;
        r5 = r3;
        r3 = r2;
        r2 = r5;
        goto L_0x005c;
    L_0x00a5:
        r1 = move-exception;
        r2 = r3;
        r3 = r4;
        goto L_0x005c;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.rong.common.FileUtils.byte2File(byte[], java.lang.String, java.lang.String):java.io.File");
    }

    public static String getCachePath(Context context) {
        return getCachePath(context, "'");
    }

    public static String getCachePath(Context context, @NonNull String str) {
        boolean equals = Environment.getExternalStorageState().equals("mounted");
        File externalCacheDir = context.getExternalCacheDir();
        if (!equals || externalCacheDir == null) {
            externalCacheDir = context.getCacheDir();
        }
        File file = new File(externalCacheDir.getPath() + File.separator + str);
        if (!file.exists()) {
            file.mkdir();
        }
        return file.getPath();
    }
}
