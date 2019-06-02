package com.tencent.bugly.crashreport.crash.jni;

import android.annotation.SuppressLint;
import android.content.Context;
import ch.qos.logback.core.spi.AbstractComponentTracker;
import com.tencent.bugly.C4402b;
import com.tencent.bugly.crashreport.C4404a;
import com.tencent.bugly.crashreport.common.info.C4417a;
import com.tencent.bugly.crashreport.common.info.C4418b;
import com.tencent.bugly.crashreport.common.strategy.C4421a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.C4433b;
import com.tencent.bugly.crashreport.crash.C4436c;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.proguard.C4474v;
import com.tencent.bugly.proguard.C4475w;
import com.tencent.bugly.proguard.C4479y;
import java.io.File;

/* compiled from: BUGLY */
public class NativeCrashHandler implements C4404a {
    /* renamed from: a */
    private static NativeCrashHandler f15508a;
    /* renamed from: l */
    private static boolean f15509l = false;
    /* renamed from: m */
    private static boolean f15510m = false;
    /* renamed from: b */
    private final Context f15511b;
    /* renamed from: c */
    private final C4417a f15512c;
    /* renamed from: d */
    private final C4474v f15513d;
    /* renamed from: e */
    private NativeExceptionHandler f15514e;
    /* renamed from: f */
    private String f15515f;
    /* renamed from: g */
    private final boolean f15516g;
    /* renamed from: h */
    private boolean f15517h = false;
    /* renamed from: i */
    private boolean f15518i = false;
    /* renamed from: j */
    private boolean f15519j = false;
    /* renamed from: k */
    private boolean f15520k = false;
    /* renamed from: n */
    private C4433b f15521n;

    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler$1 */
    class C44431 implements Runnable {
        /* renamed from: a */
        private /* synthetic */ NativeCrashHandler f15507a;

        C44431(NativeCrashHandler nativeCrashHandler) {
            this.f15507a = nativeCrashHandler;
        }

        public final void run() {
            if (C4479y.m17790a(this.f15507a.f15511b, "native_record_lock", (long) AbstractComponentTracker.LINGERING_TIMEOUT)) {
                try {
                    this.f15507a.setNativeAppVersion(this.f15507a.f15512c.f15295j);
                    this.f15507a.setNativeAppChannel(this.f15507a.f15512c.f15297l);
                    this.f15507a.setNativeAppPackage(this.f15507a.f15512c.f15288c);
                    this.f15507a.setNativeUserId(this.f15507a.f15512c.m17337g());
                    this.f15507a.setNativeIsAppForeground(this.f15507a.f15512c.m17325a());
                    this.f15507a.setNativeLaunchTime(this.f15507a.f15512c.f15286a);
                } catch (Throwable th) {
                    if (!C4475w.m17748a(th)) {
                        th.printStackTrace();
                    }
                }
                CrashDetailBean a = C4445b.m17492a(this.f15507a.f15511b, this.f15507a.f15515f, this.f15507a.f15514e);
                if (a != null) {
                    C4475w.m17747a("[Native] Get crash from native record.", new Object[0]);
                    if (!this.f15507a.f15521n.m17440a(a)) {
                        this.f15507a.f15521n.m17438a(a, 3000, false);
                    }
                    C4445b.m17497b(this.f15507a.f15515f);
                }
                this.f15507a.m17491a();
                C4479y.m17807b(this.f15507a.f15511b, "native_record_lock");
                return;
            }
            C4475w.m17747a("[Native] Failed to lock file for handling native crash record.", new Object[0]);
        }
    }

    protected native boolean appendNativeLog(String str, String str2, String str3);

    protected native boolean appendWholeNativeLog(String str);

    protected native String getNativeKeyValueList();

    protected native String getNativeLog();

    protected native boolean putNativeKeyValue(String str, String str2);

    protected native String regist(String str, boolean z, int i);

    protected native String removeNativeKeyValue(String str);

    protected native void setNativeInfo(int i, String str);

    protected native void testCrash();

    protected native String unregist();

    @SuppressLint({"SdCardPath"})
    private NativeCrashHandler(Context context, C4417a c4417a, C4433b c4433b, C4474v c4474v, boolean z, String str) {
        this.f15511b = C4479y.m17772a(context);
        try {
            if (C4479y.m17792a(str)) {
                str = context.getDir("bugly", 0).getAbsolutePath();
            }
        } catch (Throwable th) {
            str = "/data/data/" + C4417a.m17303a(context).f15288c + "/app_bugly";
        }
        this.f15521n = c4433b;
        this.f15515f = str;
        this.f15512c = c4417a;
        this.f15513d = c4474v;
        this.f15516g = z;
    }

    public static synchronized NativeCrashHandler getInstance(Context context, C4417a c4417a, C4433b c4433b, C4421a c4421a, C4474v c4474v, boolean z, String str) {
        NativeCrashHandler nativeCrashHandler;
        synchronized (NativeCrashHandler.class) {
            if (f15508a == null) {
                f15508a = new NativeCrashHandler(context, c4417a, c4433b, c4474v, z, str);
            }
            nativeCrashHandler = f15508a;
        }
        return nativeCrashHandler;
    }

    public static synchronized NativeCrashHandler getInstance() {
        NativeCrashHandler nativeCrashHandler;
        synchronized (NativeCrashHandler.class) {
            nativeCrashHandler = f15508a;
        }
        return nativeCrashHandler;
    }

    public synchronized String getDumpFilePath() {
        return this.f15515f;
    }

    public synchronized void setDumpFilePath(String str) {
        this.f15515f = str;
    }

    /* renamed from: a */
    private synchronized void m17481a(boolean z) {
        if (this.f15519j) {
            C4475w.m17752d("[Native] Native crash report has already registered.", new Object[0]);
        } else {
            this.f15514e = new C4444a(this.f15511b, this.f15512c, this.f15521n, C4421a.m17388a(), this.f15515f);
            String replace;
            if (this.f15518i) {
                try {
                    String regist = regist(this.f15515f, z, 1);
                    if (regist != null) {
                        C4475w.m17747a("[Native] Native Crash Report enable.", new Object[0]);
                        C4475w.m17751c("[Native] Check extra jni for Bugly NDK v%s", regist);
                        String replace2 = "2.1.1".replace(".", "");
                        String replace3 = "2.3.0".replace(".", "");
                        replace = regist.replace(".", "");
                        if (replace.length() == 2) {
                            replace = replace + "0";
                        } else if (replace.length() == 1) {
                            replace = replace + "00";
                        }
                        try {
                            if (Integer.parseInt(replace) >= Integer.parseInt(replace2)) {
                                f15509l = true;
                            }
                            if (Integer.parseInt(replace) >= Integer.parseInt(replace3)) {
                                f15510m = true;
                            }
                        } catch (Throwable th) {
                        }
                        if (f15510m) {
                            C4475w.m17747a("[Native] Info setting jni can be accessed.", new Object[0]);
                        } else {
                            C4475w.m17752d("[Native] Info setting jni can not be accessed.", new Object[0]);
                        }
                        if (f15509l) {
                            C4475w.m17747a("[Native] Extra jni can be accessed.", new Object[0]);
                        } else {
                            C4475w.m17752d("[Native] Extra jni can not be accessed.", new Object[0]);
                        }
                        this.f15512c.f15299n = regist;
                        this.f15519j = true;
                    }
                } catch (Throwable th2) {
                    C4475w.m17751c("[Native] Failed to load Bugly SO file.", new Object[0]);
                }
            } else if (this.f15517h) {
                try {
                    Class[] clsArr = new Class[]{String.class, String.class, Integer.TYPE, Integer.TYPE};
                    r4 = new Object[4];
                    C4417a.m17304b();
                    r4[2] = Integer.valueOf(C4417a.m17302J());
                    r4[3] = Integer.valueOf(1);
                    replace = (String) C4479y.m17775a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "registNativeExceptionHandler2", null, clsArr, r4);
                    if (replace == null) {
                        clsArr = new Class[]{String.class, String.class, Integer.TYPE};
                        r4 = new Object[3];
                        r4[0] = this.f15515f;
                        r4[1] = C4418b.m17360a(false);
                        C4417a.m17304b();
                        r4[2] = Integer.valueOf(C4417a.m17302J());
                        replace = (String) C4479y.m17775a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "registNativeExceptionHandler", null, clsArr, r4);
                    }
                    if (replace != null) {
                        this.f15519j = true;
                        C4417a.m17304b().f15299n = replace;
                        C4479y.m17775a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "enableHandler", null, new Class[]{Boolean.TYPE}, new Object[]{Boolean.valueOf(true)});
                        int i = C4402b.f15204c ? 3 : 5;
                        C4479y.m17775a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "setLogMode", null, new Class[]{Integer.TYPE}, new Object[]{Integer.valueOf(i)});
                    }
                } catch (Throwable th3) {
                }
            }
            this.f15518i = false;
            this.f15517h = false;
        }
    }

    public synchronized void startNativeMonitor() {
        if (this.f15518i || this.f15517h) {
            m17481a(this.f15516g);
        } else {
            String str;
            if (!C4479y.m17792a(this.f15512c.f15298m)) {
                str = this.f15512c.f15298m;
            }
            str = "Bugly";
            this.f15512c.getClass();
            this.f15518i = m17483a(C4479y.m17792a(this.f15512c.f15298m) ? str : this.f15512c.f15298m, !C4479y.m17792a(this.f15512c.f15298m));
            if (this.f15518i || this.f15517h) {
                m17481a(this.f15516g);
                this.f15513d.m17741a(new C44431(this));
            }
        }
    }

    /* renamed from: a */
    private static boolean m17483a(String str, boolean z) {
        Throwable th;
        boolean z2;
        try {
            C4475w.m17747a("[Native] Trying to load so: %s", str);
            if (z) {
                System.load(str);
            } else {
                System.loadLibrary(str);
            }
            try {
                C4475w.m17747a("[Native] Successfully loaded SO: %s", str);
                return true;
            } catch (Throwable th2) {
                th = th2;
                z2 = true;
            }
        } catch (Throwable th22) {
            th = th22;
            z2 = false;
            C4475w.m17752d(th.getMessage(), new Object[0]);
            C4475w.m17752d("[Native] Failed to load so: %s", str);
            return z2;
        }
    }

    /* renamed from: b */
    private synchronized void m17485b() {
        if (this.f15519j) {
            try {
                if (unregist() != null) {
                    C4475w.m17747a("[Native] Successfully closed native crash report.", new Object[0]);
                    this.f15519j = false;
                }
            } catch (Throwable th) {
                C4475w.m17751c("[Native] Failed to close native crash report.", new Object[0]);
            }
            try {
                C4479y.m17775a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "enableHandler", null, new Class[]{Boolean.TYPE}, new Object[]{Boolean.valueOf(false)});
                this.f15519j = false;
                C4475w.m17747a("[Native] Successfully closed native crash report.", new Object[0]);
            } catch (Throwable th2) {
                C4475w.m17751c("[Native] Failed to close native crash report.", new Object[0]);
                this.f15518i = false;
                this.f15517h = false;
            }
        } else {
            C4475w.m17752d("[Native] Native crash report has already unregistered.", new Object[0]);
        }
        return;
    }

    public void testNativeCrash() {
        if (this.f15518i) {
            testCrash();
        } else {
            C4475w.m17752d("[Native] Bugly SO file has not been load.", new Object[0]);
        }
    }

    public NativeExceptionHandler getNativeExceptionHandler() {
        return this.f15514e;
    }

    /* renamed from: a */
    protected final void m17491a() {
        long b = C4479y.m17800b() - C4436c.f15455f;
        File file = new File(this.f15515f);
        if (file.exists() && file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length != 0) {
                String str = "tomb_";
                String str2 = ".txt";
                int length = str.length();
                int i = 0;
                for (File file2 : listFiles) {
                    String name = file2.getName();
                    if (name.startsWith(str)) {
                        try {
                            int indexOf = name.indexOf(str2);
                            if (indexOf > 0 && Long.parseLong(name.substring(length, indexOf)) >= b) {
                            }
                        } catch (Throwable th) {
                            C4475w.m17753e("[Native] Tomb file format error, delete %s", name);
                        }
                        if (file2.delete()) {
                            i++;
                        }
                    }
                }
                C4475w.m17751c("[Native] Clean tombs %d", Integer.valueOf(i));
            }
        }
    }

    /* renamed from: b */
    private synchronized void m17486b(boolean z) {
        if (z) {
            startNativeMonitor();
        } else {
            m17485b();
        }
    }

    public synchronized boolean isUserOpened() {
        return this.f15520k;
    }

    /* renamed from: c */
    private synchronized void m17488c(boolean z) {
        if (this.f15520k != z) {
            C4475w.m17747a("user change native %b", Boolean.valueOf(z));
            this.f15520k = z;
        }
    }

    public synchronized void setUserOpened(boolean z) {
        boolean z2 = true;
        synchronized (this) {
            m17488c(z);
            boolean isUserOpened = isUserOpened();
            C4421a a = C4421a.m17388a();
            if (a == null) {
                z2 = isUserOpened;
            } else if (!(isUserOpened && a.m17396c().f15320g)) {
                z2 = false;
            }
            if (z2 != this.f15519j) {
                C4475w.m17747a("native changed to %b", Boolean.valueOf(z2));
                m17486b(z2);
            }
        }
    }

    public synchronized void onStrategyChanged(StrategyBean strategyBean) {
        boolean z = true;
        synchronized (this) {
            if (strategyBean != null) {
                if (strategyBean.f15320g != this.f15519j) {
                    C4475w.m17752d("server native changed to %b", Boolean.valueOf(strategyBean.f15320g));
                }
            }
            if (!(C4421a.m17388a().m17396c().f15320g && this.f15520k)) {
                z = false;
            }
            if (z != this.f15519j) {
                C4475w.m17747a("native changed to %b", Boolean.valueOf(z));
                m17486b(z);
            }
        }
    }

    public boolean appendLogToNative(String str, String str2, String str3) {
        boolean z = false;
        if (!(!this.f15518i || !f15509l || str == null || str2 == null || str3 == null)) {
            try {
                z = appendNativeLog(str, str2, str3);
            } catch (UnsatisfiedLinkError e) {
                f15509l = z;
            } catch (Throwable th) {
                if (!C4475w.m17748a(th)) {
                    th.printStackTrace();
                }
            }
        }
        return z;
    }

    public boolean putKeyValueToNative(String str, String str2) {
        boolean z = false;
        if (this.f15518i && f15509l && str != null && str2 != null) {
            try {
                z = putNativeKeyValue(str, str2);
            } catch (UnsatisfiedLinkError e) {
                f15509l = z;
            } catch (Throwable th) {
                if (!C4475w.m17748a(th)) {
                    th.printStackTrace();
                }
            }
        }
        return z;
    }

    /* renamed from: a */
    private boolean m17482a(int i, String str) {
        if (!this.f15518i || !f15510m) {
            return false;
        }
        try {
            setNativeInfo(i, str);
            return true;
        } catch (UnsatisfiedLinkError e) {
            f15510m = false;
            return false;
        } catch (Throwable th) {
            if (C4475w.m17748a(th)) {
                return false;
            }
            th.printStackTrace();
            return false;
        }
    }

    public boolean setNativeAppVersion(String str) {
        return m17482a(10, str);
    }

    public boolean setNativeAppChannel(String str) {
        return m17482a(12, str);
    }

    public boolean setNativeAppPackage(String str) {
        return m17482a(13, str);
    }

    public boolean setNativeUserId(String str) {
        return m17482a(11, str);
    }

    public boolean setNativeIsAppForeground(boolean z) {
        return m17482a(14, z ? "true" : "false");
    }

    public boolean setNativeLaunchTime(long j) {
        try {
            return m17482a(15, String.valueOf(j));
        } catch (Throwable e) {
            if (!C4475w.m17748a(e)) {
                e.printStackTrace();
            }
            return false;
        }
    }
}
