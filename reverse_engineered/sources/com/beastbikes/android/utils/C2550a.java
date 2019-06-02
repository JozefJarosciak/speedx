package com.beastbikes.android.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* compiled from: BitmapLoadManager */
/* renamed from: com.beastbikes.android.utils.a */
public class C2550a {
    /* renamed from: a */
    public static Bitmap m12757a(String str, Context context) {
        File e = C2550a.m12767e(str, context);
        if (e == null || !e.exists()) {
            return null;
        }
        return BitmapFactory.decodeFile(e.getPath());
    }

    /* renamed from: b */
    public static void m12763b(String str, Context context) {
        File e = C2550a.m12767e(str, context);
        if (e != null) {
            C2550a.m12761a(str, e, context);
        }
    }

    /* renamed from: c */
    public static boolean m12765c(String str, Context context) {
        File e = C2550a.m12767e(str, context);
        if (e == null || !e.exists()) {
            return false;
        }
        return e.delete();
    }

    /* renamed from: e */
    private static File m12767e(String str, Context context) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            File file;
            Object a = C2550a.m12758a(context);
            if (!TextUtils.isEmpty(a)) {
                file = new File(a, C2550a.m12759a(str));
                return file;
            }
            file = null;
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: d */
    public static boolean m12766d(String str, Context context) {
        return C2550a.m12762a(context, str);
    }

    /* renamed from: a */
    public static String m12758a(Context context) {
        try {
            File file = new File(context.getDir("images", 0).getPath());
            if (!file.exists()) {
                file.mkdirs();
            }
            return file.getPath();
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: a */
    public static boolean m12762a(Context context, String str) {
        return new File(C2550a.m12758a(context), C2550a.m12759a(str)).exists();
    }

    /* renamed from: a */
    private static void m12761a(String str, File file, Context context) {
        InputStream inputStream;
        FileOutputStream fileOutputStream;
        Exception e;
        InputStream inputStream2;
        Throwable th;
        FileOutputStream fileOutputStream2 = null;
        if (C2550a.m12764b(context)) {
            try {
                Bitmap decodeStream;
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();
                inputStream = httpURLConnection.getInputStream();
                try {
                    decodeStream = BitmapFactory.decodeStream(inputStream);
                    fileOutputStream = new FileOutputStream(file);
                } catch (Exception e2) {
                    e = e2;
                    fileOutputStream = null;
                    inputStream2 = inputStream;
                    try {
                        e.printStackTrace();
                        if (inputStream2 != null) {
                            try {
                                inputStream2.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                        if (fileOutputStream == null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e32) {
                                e32.printStackTrace();
                                return;
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        inputStream = inputStream2;
                        fileOutputStream2 = fileOutputStream;
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        if (fileOutputStream2 != null) {
                            try {
                                fileOutputStream2.close();
                            } catch (IOException e42) {
                                e42.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (fileOutputStream2 != null) {
                        fileOutputStream2.close();
                    }
                    throw th;
                }
                try {
                    decodeStream.compress(CompressFormat.PNG, 100, fileOutputStream);
                    fileOutputStream.flush();
                    if (!decodeStream.isRecycled()) {
                        decodeStream.recycle();
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e322) {
                            e322.printStackTrace();
                        }
                    }
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e3222) {
                            e3222.printStackTrace();
                        }
                    }
                } catch (Exception e5) {
                    e = e5;
                    inputStream2 = inputStream;
                    e.printStackTrace();
                    if (inputStream2 != null) {
                        inputStream2.close();
                    }
                    if (fileOutputStream == null) {
                        fileOutputStream.close();
                    }
                } catch (Throwable th4) {
                    th = th4;
                    fileOutputStream2 = fileOutputStream;
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (fileOutputStream2 != null) {
                        fileOutputStream2.close();
                    }
                    throw th;
                }
            } catch (Exception e6) {
                e = e6;
                fileOutputStream = null;
                e.printStackTrace();
                if (inputStream2 != null) {
                    inputStream2.close();
                }
                if (fileOutputStream == null) {
                    fileOutputStream.close();
                }
            } catch (Throwable th5) {
                th = th5;
                inputStream = null;
                if (inputStream != null) {
                    inputStream.close();
                }
                if (fileOutputStream2 != null) {
                    fileOutputStream2.close();
                }
                throw th;
            }
        }
    }

    /* renamed from: b */
    public static boolean m12764b(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity");
        if (connectivityManager == null) {
            return false;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    public static String m12759a(String str) {
        String str2 = "";
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            return C2550a.m12760a(instance.digest());
        } catch (NoSuchAlgorithmException e) {
            return String.valueOf(str.hashCode());
        }
    }

    /* renamed from: a */
    public static String m12760a(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bArr) {
            String toHexString = Integer.toHexString(b & 255);
            if (toHexString.length() == 1) {
                stringBuilder.append('0');
            }
            stringBuilder.append(toHexString);
        }
        return stringBuilder.toString();
    }
}
