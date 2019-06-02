package com.beastbikes.android.utils;

import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/* compiled from: LogUtil */
/* renamed from: com.beastbikes.android.utils.l */
public class C2564l {
    /* renamed from: a */
    public static boolean m12871a(String str, String str2) {
        FileOutputStream fileOutputStream;
        FileInputStream fileInputStream;
        Throwable th;
        FileOutputStream fileOutputStream2 = null;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        FileInputStream fileInputStream2;
        try {
            new File(str2).mkdirs();
            String[] list = new File(str).list();
            int length = list.length;
            int i = 0;
            fileInputStream2 = null;
            while (i < length) {
                try {
                    File file;
                    String str3 = list[i];
                    if (str.endsWith(File.separator)) {
                        file = new File(str + str3);
                    } else {
                        file = new File(str + File.separator + str3);
                    }
                    if (file.isFile()) {
                        FileInputStream fileInputStream3 = new FileInputStream(file);
                        try {
                            FileOutputStream fileOutputStream3 = new FileOutputStream(str2 + File.separator + file.getName().toString());
                            try {
                                byte[] bArr = new byte[5120];
                                while (true) {
                                    int read = fileInputStream3.read(bArr);
                                    if (read == -1) {
                                        break;
                                    }
                                    fileOutputStream3.write(bArr, 0, read);
                                }
                                fileOutputStream3.flush();
                                fileOutputStream2 = fileOutputStream3;
                                fileInputStream2 = fileInputStream3;
                            } catch (Exception e) {
                                fileOutputStream = fileOutputStream3;
                                fileInputStream = fileInputStream3;
                            } catch (Throwable th2) {
                                th = th2;
                                fileOutputStream2 = fileOutputStream3;
                                fileInputStream2 = fileInputStream3;
                            }
                        } catch (Exception e2) {
                            fileOutputStream = fileOutputStream2;
                            fileInputStream = fileInputStream3;
                        } catch (Throwable th3) {
                            th = th3;
                            fileInputStream2 = fileInputStream3;
                        }
                    }
                    if (file.isDirectory()) {
                        C2564l.m12871a(str + File.separator + str3, str2 + File.separator + str3);
                    }
                    i++;
                } catch (Exception e3) {
                    fileOutputStream = fileOutputStream2;
                    fileInputStream = fileInputStream2;
                } catch (Throwable th4) {
                    th = th4;
                }
            }
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
            if (fileInputStream2 == null) {
                return true;
            }
            try {
                fileInputStream2.close();
                return true;
            } catch (IOException e42) {
                e42.printStackTrace();
                return true;
            }
        } catch (Exception e5) {
            fileOutputStream = null;
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e6) {
                    e6.printStackTrace();
                }
            }
            if (fileInputStream == null) {
                return false;
            }
            try {
                fileInputStream.close();
                return false;
            } catch (IOException e62) {
                e62.printStackTrace();
                return false;
            }
        } catch (Throwable th5) {
            th = th5;
            fileInputStream2 = null;
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (IOException e422) {
                    e422.printStackTrace();
                }
            }
            if (fileInputStream2 != null) {
                try {
                    fileInputStream2.close();
                } catch (IOException e4222) {
                    e4222.printStackTrace();
                }
            }
            throw th;
        }
    }

    /* renamed from: a */
    public static String m12870a(String str, String str2, int i) {
        return str.substring(0, i + 1) + str2 + str.substring(i + 1, str.length());
    }
}
