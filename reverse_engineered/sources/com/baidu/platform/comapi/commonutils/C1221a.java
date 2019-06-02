package com.baidu.platform.comapi.commonutils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build.VERSION;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* renamed from: com.baidu.platform.comapi.commonutils.a */
public class C1221a {
    /* renamed from: a */
    private static final boolean f3615a = (VERSION.SDK_INT >= 8);

    /* renamed from: a */
    public static Bitmap m4570a(String str, Context context) {
        try {
            InputStream open = context.getAssets().open(str);
            return open != null ? BitmapFactory.decodeStream(open) : null;
        } catch (Exception e) {
            return BitmapFactory.decodeFile(C1221a.m4573b("assets/" + str, str, context));
        }
    }

    /* renamed from: a */
    private static void m4571a(InputStream inputStream, FileOutputStream fileOutputStream) throws IOException {
        byte[] bArr = new byte[4096];
        while (true) {
            try {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                fileOutputStream.write(bArr, 0, read);
            } finally {
                try {
                    inputStream.close();
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        return;
                    }
                } catch (IOException e2) {
                    return;
                }
            }
        }
        fileOutputStream.flush();
        try {
            fileOutputStream.close();
        } catch (IOException e3) {
        }
    }

    /* renamed from: a */
    public static void m4572a(String str, String str2, Context context) {
        InputStream open;
        InputStream inputStream;
        Throwable th;
        Throwable th2;
        FileOutputStream fileOutputStream = null;
        FileOutputStream fileOutputStream2;
        try {
            open = context.getAssets().open(str);
            if (open != null) {
                byte[] bArr;
                try {
                    bArr = new byte[open.available()];
                    open.read(bArr);
                    File file = new File(context.getFilesDir().getAbsolutePath() + "/" + str2);
                    if (file.exists()) {
                        file.delete();
                    }
                    file.createNewFile();
                    fileOutputStream2 = new FileOutputStream(file);
                } catch (Exception e) {
                    inputStream = open;
                    try {
                        C1221a.m4573b("assets/" + str, str2, context);
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                                return;
                            }
                        }
                        if (fileOutputStream == null) {
                            fileOutputStream.close();
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        open = inputStream;
                        fileOutputStream2 = fileOutputStream;
                        th2 = th;
                        if (open != null) {
                            try {
                                open.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                                throw th2;
                            }
                        }
                        if (fileOutputStream2 != null) {
                            fileOutputStream2.close();
                        }
                        throw th2;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    fileOutputStream2 = null;
                    th2 = th;
                    if (open != null) {
                        open.close();
                    }
                    if (fileOutputStream2 != null) {
                        fileOutputStream2.close();
                    }
                    throw th2;
                }
                try {
                    fileOutputStream2.write(bArr);
                    fileOutputStream2.close();
                } catch (Exception e4) {
                    fileOutputStream = fileOutputStream2;
                    inputStream = open;
                    C1221a.m4573b("assets/" + str, str2, context);
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (fileOutputStream == null) {
                        fileOutputStream.close();
                    }
                } catch (Throwable th5) {
                    th2 = th5;
                    if (open != null) {
                        open.close();
                    }
                    if (fileOutputStream2 != null) {
                        fileOutputStream2.close();
                    }
                    throw th2;
                }
            }
            fileOutputStream2 = null;
            if (open != null) {
                try {
                    open.close();
                } catch (IOException e22) {
                    e22.printStackTrace();
                    return;
                }
            }
            if (fileOutputStream2 != null) {
                fileOutputStream2.close();
            }
        } catch (Exception e5) {
            inputStream = null;
            C1221a.m4573b("assets/" + str, str2, context);
            if (inputStream != null) {
                inputStream.close();
            }
            if (fileOutputStream == null) {
                fileOutputStream.close();
            }
        } catch (Throwable th42) {
            open = null;
            th2 = th42;
            fileOutputStream2 = null;
            if (open != null) {
                open.close();
            }
            if (fileOutputStream2 != null) {
                fileOutputStream2.close();
            }
            throw th2;
        }
    }

    /* renamed from: b */
    private static String m4573b(String str, String str2, Context context) {
        ZipFile zipFile;
        Throwable e;
        ZipFile zipFile2 = null;
        String str3 = "";
        StringBuilder stringBuilder = new StringBuilder(context.getFilesDir().getAbsolutePath());
        if (f3615a) {
            str3 = context.getPackageCodePath();
        }
        try {
            zipFile = new ZipFile(str3);
            try {
                File file;
                File file2;
                int lastIndexOf = str2.lastIndexOf("/");
                if (lastIndexOf > 0) {
                    file = new File(context.getFilesDir().getAbsolutePath());
                    String substring = str2.substring(0, lastIndexOf);
                    file2 = new File(file.getAbsolutePath() + "/" + substring, str2.substring(lastIndexOf + 1, str2.length()));
                } else {
                    file = new File(context.getFilesDir(), "assets");
                    file2 = new File(file.getAbsolutePath(), str2);
                }
                file.mkdirs();
                ZipEntry entry = zipFile.getEntry(str);
                if (entry == null) {
                    if (zipFile != null) {
                        try {
                            zipFile.close();
                        } catch (IOException e2) {
                        }
                    }
                    return null;
                }
                C1221a.m4571a(zipFile.getInputStream(entry), new FileOutputStream(file2));
                stringBuilder.append("/").append(str);
                if (zipFile != null) {
                    try {
                        zipFile.close();
                    } catch (IOException e3) {
                    }
                }
                return stringBuilder.toString();
            } catch (Exception e4) {
                e = e4;
                zipFile2 = zipFile;
                try {
                    Log.e(C1221a.class.getSimpleName(), "copyAssetsError", e);
                    if (zipFile2 != null) {
                        try {
                            zipFile2.close();
                        } catch (IOException e5) {
                        }
                    }
                    return stringBuilder.toString();
                } catch (Throwable th) {
                    e = th;
                    zipFile = zipFile2;
                    if (zipFile != null) {
                        try {
                            zipFile.close();
                        } catch (IOException e6) {
                        }
                    }
                    throw e;
                }
            } catch (Throwable th2) {
                e = th2;
                if (zipFile != null) {
                    zipFile.close();
                }
                throw e;
            }
        } catch (Exception e7) {
            e = e7;
            Log.e(C1221a.class.getSimpleName(), "copyAssetsError", e);
            if (zipFile2 != null) {
                zipFile2.close();
            }
            return stringBuilder.toString();
        } catch (Throwable th3) {
            e = th3;
            zipFile = null;
            if (zipFile != null) {
                zipFile.close();
            }
            throw e;
        }
    }
}
