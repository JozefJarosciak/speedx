package com.beastbikes.android.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore.Images.Media;
import android.text.TextUtils;
import android.util.Log;
import ch.qos.logback.core.pattern.color.ANSIConstants;
import com.beastbikes.android.C1373R;
import com.squareup.picasso.Picasso;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* compiled from: FileUtil */
/* renamed from: com.beastbikes.android.utils.f */
public class C2557f {
    /* renamed from: a */
    private static ConcurrentHashMap<String, ReentrantReadWriteLock> f12037a = new ConcurrentHashMap();

    /* renamed from: a */
    public static byte[] m12836a(File file) {
        IOException e;
        Closeable closeable;
        Throwable th;
        Closeable closeable2 = null;
        if (file == null) {
            Log.e(ANSIConstants.ESC_END, "null file object.");
            return null;
        } else if (file.exists() && file.isFile()) {
            Lock readLock = C2557f.m12839d(file.getAbsolutePath()).readLock();
            readLock.lock();
            try {
                byte[] bArr = new byte[((int) file.length())];
                int i = 0;
                Closeable bufferedInputStream = new BufferedInputStream(new FileInputStream(file), 8192);
                while (i < bArr.length) {
                    try {
                        int read = bufferedInputStream.read(bArr, i, bArr.length - i);
                        if (read > 0) {
                            i += read;
                        }
                    } catch (IOException e2) {
                        e = e2;
                        closeable = bufferedInputStream;
                    } catch (Throwable th2) {
                        th = th2;
                        closeable2 = bufferedInputStream;
                    }
                }
                C2557f.m12833a(bufferedInputStream);
                readLock.unlock();
                return bArr;
            } catch (IOException e3) {
                e = e3;
                closeable = null;
                try {
                    e.printStackTrace();
                    C2557f.m12833a(closeable);
                    readLock.unlock();
                    return null;
                } catch (Throwable th3) {
                    th = th3;
                    closeable2 = closeable;
                    C2557f.m12833a(closeable2);
                    readLock.unlock();
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                C2557f.m12833a(closeable2);
                readLock.unlock();
                throw th;
            }
        } else {
            Log.d(ANSIConstants.ESC_END, new FileNotFoundException().toString());
            return null;
        }
    }

    /* renamed from: d */
    private static ReentrantReadWriteLock m12839d(String str) {
        ReentrantReadWriteLock reentrantReadWriteLock = (ReentrantReadWriteLock) f12037a.get(str);
        if (reentrantReadWriteLock != null) {
            return reentrantReadWriteLock;
        }
        ReentrantReadWriteLock reentrantReadWriteLock2 = new ReentrantReadWriteLock();
        reentrantReadWriteLock = (ReentrantReadWriteLock) f12037a.putIfAbsent(str, reentrantReadWriteLock2);
        return reentrantReadWriteLock != null ? reentrantReadWriteLock : reentrantReadWriteLock2;
    }

    /* renamed from: a */
    public static void m12833a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    public static File m12829a(Context context, String str, String str2) {
        return new File(context.getDir(str, 0), str2);
    }

    /* renamed from: a */
    public static boolean m12835a(byte[] bArr, File file) {
        Closeable fileOutputStream;
        Exception e;
        Throwable th;
        Lock writeLock = C2557f.m12839d(file.getAbsolutePath()).writeLock();
        if (!writeLock.tryLock()) {
            return true;
        }
        try {
            fileOutputStream = new FileOutputStream(file, false);
            try {
                fileOutputStream.write(bArr);
                if (fileOutputStream != null) {
                    C2557f.m12833a(fileOutputStream);
                }
                writeLock.unlock();
                return true;
            } catch (Exception e2) {
                e = e2;
                try {
                    Log.d(ANSIConstants.ESC_END, e.toString());
                    if (fileOutputStream != null) {
                        C2557f.m12833a(fileOutputStream);
                    }
                    writeLock.unlock();
                    return false;
                } catch (Throwable th2) {
                    th = th2;
                    if (fileOutputStream != null) {
                        C2557f.m12833a(fileOutputStream);
                    }
                    writeLock.unlock();
                    throw th;
                }
            }
        } catch (Exception e3) {
            e = e3;
            fileOutputStream = null;
            Log.d(ANSIConstants.ESC_END, e.toString());
            if (fileOutputStream != null) {
                C2557f.m12833a(fileOutputStream);
            }
            writeLock.unlock();
            return false;
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
            if (fileOutputStream != null) {
                C2557f.m12833a(fileOutputStream);
            }
            writeLock.unlock();
            throw th;
        }
    }

    /* renamed from: a */
    public static String m12832a(String str, Context context) {
        String string = context.getString(C1373R.string.activity_finished_share_sdcard_success);
        if (!Environment.getExternalStorageState().equals("mounted")) {
            return context.getString(C1373R.string.select_image_view_no_sdcard);
        }
        try {
            Bitmap bitmap = Picasso.with(context).load(str).get();
            Uri parse = Uri.parse(Media.insertImage(context.getContentResolver(), bitmap, str, str));
            context.sendBroadcast(new Intent("android.hardware.action.NEW_PICTURE", parse));
            context.sendBroadcast(new Intent("com.android.camera.NEW_PICTURE", parse));
            if (bitmap == null || bitmap.isRecycled()) {
                return string;
            }
            bitmap.recycle();
            return string;
        } catch (Exception e) {
            return context.getString(C1373R.string.image_save_fail);
        }
    }

    /* renamed from: a */
    public static boolean m12834a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        File file = new File(str);
        if (file != null && file.exists() && file.isFile()) {
            file.delete();
        }
        if (file.exists()) {
            return false;
        }
        return true;
    }

    /* renamed from: b */
    public static boolean m12837b(String str) {
        File file = new File(str);
        if (!file.exists()) {
            return false;
        }
        if (file.isFile()) {
            return C2557f.m12834a(str);
        }
        return C2557f.m12838c(str);
    }

    /* renamed from: c */
    public static boolean m12838c(String str) {
        if (!str.endsWith(File.separator)) {
            str = str + File.separator;
        }
        File file = new File(str);
        if (!file.exists() || !file.isDirectory()) {
            return false;
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return true;
        }
        boolean z = true;
        for (int i = 0; i < listFiles.length; i++) {
            if (!listFiles[i].isFile()) {
                z = C2557f.m12838c(listFiles[i].getAbsolutePath());
                if (!z) {
                    break;
                }
            } else {
                z = C2557f.m12834a(listFiles[i].getAbsolutePath());
                if (!z) {
                    break;
                }
            }
        }
        if (z) {
            return file.delete();
        }
        return false;
    }

    /* renamed from: a */
    public static String m12831a(Uri uri, Context context) {
        String string = context.getString(C1373R.string.activity_finished_share_sdcard_success);
        if (!Environment.getExternalStorageState().equals("mounted")) {
            return context.getString(C1373R.string.select_image_view_no_sdcard);
        }
        try {
            Bitmap bitmap = Picasso.with(context).load(uri).get();
            Uri parse = Uri.parse(Media.insertImage(context.getContentResolver(), bitmap, uri.getUserInfo(), uri.getUserInfo()));
            context.sendBroadcast(new Intent("android.hardware.action.NEW_PICTURE", parse));
            context.sendBroadcast(new Intent("com.android.camera.NEW_PICTURE", parse));
            if (bitmap == null || bitmap.isRecycled()) {
                return string;
            }
            bitmap.recycle();
            return string;
        } catch (Exception e) {
            return context.getString(C1373R.string.image_save_fail);
        }
    }

    /* renamed from: a */
    public static String m12830a(Context context, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getAssets().open(str)));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                stringBuilder.append(readLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
            stringBuilder.delete(0, stringBuilder.length());
        }
        return stringBuilder.toString().trim();
    }
}
