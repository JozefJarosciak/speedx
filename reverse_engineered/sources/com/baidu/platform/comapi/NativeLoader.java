package com.baidu.platform.comapi;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class NativeLoader {
    /* renamed from: a */
    private static Context f3596a;
    /* renamed from: b */
    private static final Set<String> f3597b = new HashSet();
    /* renamed from: c */
    private static final Set<String> f3598c = new HashSet();
    /* renamed from: d */
    private static NativeLoader f3599d;
    /* renamed from: e */
    private static C1215a f3600e = C1215a.ARMEABI;

    /* renamed from: com.baidu.platform.comapi.NativeLoader$a */
    private enum C1215a {
        ARMEABI("armeabi"),
        ARMV7("armeabi-v7a"),
        ARM64("arm64-v8a"),
        X86("x86"),
        X86_64("x86_64");
        
        /* renamed from: f */
        private String f3595f;

        private C1215a(String str) {
            this.f3595f = str;
        }

        /* renamed from: a */
        public String m4545a() {
            return this.f3595f;
        }
    }

    private NativeLoader() {
    }

    @TargetApi(21)
    /* renamed from: a */
    private static C1215a m4546a() {
        String str = VERSION.SDK_INT < 21 ? Build.CPU_ABI : Build.SUPPORTED_ABIS[0];
        if (str == null) {
            return C1215a.ARMEABI;
        }
        if (str.contains("arm") && str.contains("v7")) {
            f3600e = C1215a.ARMV7;
        }
        if (str.contains("arm") && str.contains("64")) {
            f3600e = C1215a.ARM64;
        }
        if (str.contains("x86")) {
            if (str.contains("64")) {
                f3600e = C1215a.X86_64;
            } else {
                f3600e = C1215a.X86;
            }
        }
        return f3600e;
    }

    /* renamed from: a */
    private String m4547a(C1215a c1215a) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("lib/").append(c1215a.m4545a()).append("/");
        return stringBuilder.toString();
    }

    /* renamed from: a */
    private void m4548a(Throwable th) {
        Log.e(NativeLoader.class.getSimpleName(), "loadException", th);
        for (String str : f3598c) {
            Log.e(NativeLoader.class.getSimpleName(), str + " Failed to load.");
        }
    }

    /* renamed from: a */
    private boolean m4549a(String str, String str2) {
        return !copyNativeLibrary(str2, C1215a.ARMV7) ? m4550b(str, str2) : m4554f(str2, str);
    }

    /* renamed from: b */
    private boolean m4550b(String str, String str2) {
        if (copyNativeLibrary(str2, C1215a.ARMEABI)) {
            return m4554f(str2, str);
        }
        Log.e(NativeLoader.class.getSimpleName(), "found lib" + str + ".so error");
        return false;
    }

    /* renamed from: c */
    private boolean m4551c(String str, String str2) {
        return !copyNativeLibrary(str2, C1215a.ARM64) ? m4549a(str, str2) : m4554f(str2, str);
    }

    /* renamed from: d */
    private boolean m4552d(String str, String str2) {
        return !copyNativeLibrary(str2, C1215a.X86) ? m4549a(str, str2) : m4554f(str2, str);
    }

    /* renamed from: e */
    private boolean m4553e(String str, String str2) {
        return !copyNativeLibrary(str2, C1215a.X86_64) ? m4552d(str, str2) : m4554f(str2, str);
    }

    /* renamed from: f */
    private boolean m4554f(String str, String str2) {
        try {
            System.load(new File(getCustomizeNativePath(), str).getAbsolutePath());
            synchronized (f3597b) {
                f3597b.add(str2);
            }
            return true;
        } catch (Throwable th) {
            synchronized (f3598c) {
                f3598c.add(str2);
                m4548a(th);
                return false;
            }
        }
    }

    public static synchronized NativeLoader getInstance() {
        NativeLoader nativeLoader;
        synchronized (NativeLoader.class) {
            if (f3599d == null) {
                f3599d = new NativeLoader();
                f3600e = m4546a();
            }
            nativeLoader = f3599d;
        }
        return nativeLoader;
    }

    public static void setContext(Context context) {
        f3596a = context;
    }

    protected boolean copyNativeLibrary(String str, C1215a c1215a) {
        ZipFile zipFile;
        Throwable e;
        String str2 = m4547a(c1215a) + str;
        try {
            zipFile = new ZipFile(getCodePath());
            try {
                File file = new File(getCustomizeNativePath(), str);
                ZipEntry entry = zipFile.getEntry(str2);
                if (entry != null) {
                    copyStream(zipFile.getInputStream(entry), new FileOutputStream(file));
                    if (zipFile != null) {
                        try {
                            zipFile.close();
                        } catch (IOException e2) {
                            return false;
                        }
                    }
                    return true;
                } else if (zipFile == null) {
                    return false;
                } else {
                    try {
                        zipFile.close();
                        return false;
                    } catch (IOException e3) {
                        return false;
                    }
                }
            } catch (Exception e4) {
                e = e4;
                try {
                    Log.e(NativeLoader.class.getSimpleName(), "copyError", e);
                    if (zipFile != null) {
                        return false;
                    }
                    try {
                        zipFile.close();
                        return false;
                    } catch (IOException e5) {
                        return false;
                    }
                } catch (Throwable th) {
                    e = th;
                    if (zipFile != null) {
                        try {
                            zipFile.close();
                        } catch (IOException e6) {
                            return false;
                        }
                    }
                    throw e;
                }
            }
        } catch (Exception e7) {
            e = e7;
            zipFile = null;
            Log.e(NativeLoader.class.getSimpleName(), "copyError", e);
            if (zipFile != null) {
                return false;
            }
            zipFile.close();
            return false;
        } catch (Throwable th2) {
            e = th2;
            zipFile = null;
            if (zipFile != null) {
                zipFile.close();
            }
            throw e;
        }
    }

    protected final void copyStream(InputStream inputStream, FileOutputStream fileOutputStream) throws IOException {
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

    @TargetApi(8)
    protected String getCodePath() {
        return 8 <= VERSION.SDK_INT ? f3596a.getPackageCodePath() : "";
    }

    protected String getCustomizeNativePath() {
        File file = new File(f3596a.getFilesDir(), "libs");
        file.mkdirs();
        return file.getAbsolutePath();
    }

    protected boolean loadCustomizeNativeLibrary(String str) {
        String mapLibraryName = System.mapLibraryName(str);
        switch (f3600e) {
            case ARM64:
                return m4551c(str, mapLibraryName);
            case ARMV7:
                return m4549a(str, mapLibraryName);
            case ARMEABI:
                return m4550b(str, mapLibraryName);
            case X86_64:
                return m4553e(str, mapLibraryName);
            case X86:
                return m4552d(str, mapLibraryName);
            default:
                return false;
        }
    }

    public synchronized boolean loadLibrary(String str) {
        boolean z = true;
        synchronized (this) {
            try {
                synchronized (f3597b) {
                    if (f3597b.contains(str)) {
                    } else {
                        System.loadLibrary(str);
                        synchronized (f3597b) {
                            f3597b.add(str);
                        }
                    }
                }
            } catch (Throwable th) {
                z = loadCustomizeNativeLibrary(str);
            }
        }
        return z;
    }
}
