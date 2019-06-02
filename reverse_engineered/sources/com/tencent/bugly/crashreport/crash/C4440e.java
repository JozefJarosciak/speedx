package com.tencent.bugly.crashreport.crash;

import android.content.Context;
import android.os.Process;
import com.tencent.bugly.crashreport.common.info.C4417a;
import com.tencent.bugly.crashreport.common.info.C4418b;
import com.tencent.bugly.crashreport.common.strategy.C4421a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.proguard.C4475w;
import com.tencent.bugly.proguard.C4478x;
import com.tencent.bugly.proguard.C4479y;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.HashMap;
import org.apache.http.protocol.HttpRequestExecutor;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.crashreport.crash.e */
public final class C4440e implements UncaughtExceptionHandler {
    /* renamed from: h */
    private static String f15484h = null;
    /* renamed from: i */
    private static final Object f15485i = new Object();
    /* renamed from: a */
    private Context f15486a;
    /* renamed from: b */
    private C4433b f15487b;
    /* renamed from: c */
    private C4421a f15488c;
    /* renamed from: d */
    private C4417a f15489d;
    /* renamed from: e */
    private UncaughtExceptionHandler f15490e;
    /* renamed from: f */
    private UncaughtExceptionHandler f15491f;
    /* renamed from: g */
    private boolean f15492g = false;
    /* renamed from: j */
    private int f15493j;

    public C4440e(Context context, C4433b c4433b, C4421a c4421a, C4417a c4417a) {
        this.f15486a = context;
        this.f15487b = c4433b;
        this.f15488c = c4421a;
        this.f15489d = c4417a;
    }

    /* renamed from: a */
    public final synchronized void m17474a() {
        if (this.f15493j >= 10) {
            C4475w.m17747a("java crash handler over %d, no need set.", Integer.valueOf(10));
        } else {
            UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
            if (!(defaultUncaughtExceptionHandler == null || getClass().getName().equals(defaultUncaughtExceptionHandler.getClass().getName()))) {
                if ("com.android.internal.os.RuntimeInit$UncaughtHandler".equals(defaultUncaughtExceptionHandler.getClass().getName())) {
                    C4475w.m17747a("backup system java handler: %s", defaultUncaughtExceptionHandler.toString());
                    this.f15491f = defaultUncaughtExceptionHandler;
                    this.f15490e = defaultUncaughtExceptionHandler;
                } else {
                    C4475w.m17747a("backup java handler: %s", defaultUncaughtExceptionHandler.toString());
                    this.f15490e = defaultUncaughtExceptionHandler;
                }
                m17469a(defaultUncaughtExceptionHandler);
                Thread.setDefaultUncaughtExceptionHandler(this);
                this.f15492g = true;
                this.f15493j++;
                C4475w.m17747a("registered java monitor: %s", toString());
            }
        }
    }

    /* renamed from: b */
    public final synchronized void m17477b() {
        this.f15492g = false;
        C4475w.m17747a("close java monitor!", new Object[0]);
        if (Thread.getDefaultUncaughtExceptionHandler().getClass().getName().contains("bugly")) {
            C4475w.m17747a("Java monitor to unregister: %s", toString());
            Thread.setDefaultUncaughtExceptionHandler(this.f15490e);
            this.f15493j--;
        }
    }

    /* renamed from: a */
    private synchronized void m17469a(UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.f15490e = uncaughtExceptionHandler;
    }

    /* renamed from: b */
    private CrashDetailBean m17471b(Thread thread, Throwable th, boolean z, String str, byte[] bArr) {
        if (th == null) {
            C4475w.m17752d("We can do nothing with a null throwable.", new Object[0]);
            return null;
        }
        Object a;
        boolean l = C4436c.m17444a().m17462l();
        String str2 = (l && z) ? " This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful![Bugly]" : "";
        if (l && z) {
            C4475w.m17753e("This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful!", new Object[0]);
        }
        CrashDetailBean crashDetailBean = new CrashDetailBean();
        crashDetailBean.f15353B = C4418b.m17371g();
        crashDetailBean.f15354C = C4418b.m17367e();
        crashDetailBean.f15355D = C4418b.m17375i();
        crashDetailBean.f15356E = this.f15489d.m17347p();
        crashDetailBean.f15357F = this.f15489d.m17346o();
        crashDetailBean.f15358G = this.f15489d.m17348q();
        crashDetailBean.f15396w = C4479y.m17779a(this.f15486a, C4436c.f15453d, null);
        crashDetailBean.f15397x = C4478x.m17766a(z);
        String str3 = "user log size:%d";
        Object[] objArr = new Object[1];
        objArr[0] = Integer.valueOf(crashDetailBean.f15397x == null ? 0 : crashDetailBean.f15397x.length);
        C4475w.m17747a(str3, objArr);
        crashDetailBean.f15375b = z ? 0 : 2;
        crashDetailBean.f15378e = this.f15489d.m17339h();
        crashDetailBean.f15379f = this.f15489d.f15295j;
        crashDetailBean.f15380g = this.f15489d.m17354w();
        crashDetailBean.f15386m = this.f15489d.m17337g();
        String name = th.getClass().getName();
        String b = C4440e.m17472b(th, 1000);
        if (b == null) {
            b = "";
        }
        String str4 = "stack frame :%d, has cause %b";
        Object[] objArr2 = new Object[2];
        objArr2[0] = Integer.valueOf(th.getStackTrace().length);
        objArr2[1] = Boolean.valueOf(th.getCause() != null);
        C4475w.m17753e(str4, objArr2);
        str3 = "";
        if (th.getStackTrace().length > 0) {
            str3 = th.getStackTrace()[0].toString();
        }
        Throwable th2 = th;
        while (th2 != null && th2.getCause() != null) {
            th2 = th2.getCause();
        }
        if (th2 == null || th2 == th) {
            crashDetailBean.f15387n = name;
            crashDetailBean.f15388o = b + str2;
            if (crashDetailBean.f15388o == null) {
                crashDetailBean.f15388o = "";
            }
            crashDetailBean.f15389p = str3;
            a = C4440e.m17468a(th, C4436c.f15454e);
            crashDetailBean.f15390q = a;
        } else {
            crashDetailBean.f15387n = th2.getClass().getName();
            crashDetailBean.f15388o = C4440e.m17472b(th2, 1000);
            if (crashDetailBean.f15388o == null) {
                crashDetailBean.f15388o = "";
            }
            crashDetailBean.f15389p = th2.getStackTrace()[0].toString();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(name).append(":").append(b).append("\n");
            stringBuilder.append(str3);
            stringBuilder.append("\n......");
            stringBuilder.append("\nCaused by:\n");
            stringBuilder.append(crashDetailBean.f15387n).append(":").append(crashDetailBean.f15388o).append("\n");
            a = C4440e.m17468a(th2, C4436c.f15454e);
            stringBuilder.append(a);
            crashDetailBean.f15390q = stringBuilder.toString();
        }
        crashDetailBean.f15391r = System.currentTimeMillis();
        crashDetailBean.f15394u = C4479y.m17802b(crashDetailBean.f15390q.getBytes());
        try {
            crashDetailBean.f15398y = C4479y.m17786a(C4436c.f15454e, false);
            crashDetailBean.f15399z = this.f15489d.f15289d;
            crashDetailBean.f15352A = thread.getName() + "(" + thread.getId() + ")";
            crashDetailBean.f15398y.put(crashDetailBean.f15352A, a);
            crashDetailBean.f15359H = this.f15489d.m17356y();
            crashDetailBean.f15381h = this.f15489d.m17353v();
            crashDetailBean.f15382i = this.f15489d.m17314I();
            crashDetailBean.f15363L = this.f15489d.f15286a;
            crashDetailBean.f15364M = this.f15489d.m17325a();
            crashDetailBean.f15366O = this.f15489d.m17311F();
            crashDetailBean.f15367P = this.f15489d.m17312G();
            crashDetailBean.f15368Q = this.f15489d.m17357z();
            crashDetailBean.f15369R = this.f15489d.m17310E();
        } catch (Throwable th3) {
            C4475w.m17753e("handle crash error %s", th3.toString());
        }
        if (z) {
            this.f15487b.m17442b(crashDetailBean);
        } else {
            Object obj = (str == null || str.length() <= 0) ? null : 1;
            a = (bArr == null || bArr.length <= 0) ? null : 1;
            if (obj != null) {
                crashDetailBean.f15365N = new HashMap(1);
                crashDetailBean.f15365N.put("UserData", str);
            }
            if (a != null) {
                crashDetailBean.f15370S = bArr;
            }
        }
        return crashDetailBean;
    }

    /* renamed from: a */
    private static boolean m17470a(Thread thread) {
        boolean z;
        synchronized (f15485i) {
            if (f15484h == null || !thread.getName().equals(f15484h)) {
                f15484h = thread.getName();
                z = false;
            } else {
                z = true;
            }
        }
        return z;
    }

    /* renamed from: a */
    public final void m17476a(Thread thread, Throwable th, boolean z, String str, byte[] bArr) {
        if (z) {
            C4475w.m17753e("Java Crash Happen cause by %s(%d)", thread.getName(), Long.valueOf(thread.getId()));
            if (C4440e.m17470a(thread)) {
                C4475w.m17747a("this class has handled this exception", new Object[0]);
                if (this.f15491f != null) {
                    C4475w.m17747a("call system handler", new Object[0]);
                    this.f15491f.uncaughtException(thread, th);
                } else {
                    C4475w.m17753e("current process die", new Object[0]);
                    Process.killProcess(Process.myPid());
                    System.exit(1);
                }
            }
        } else {
            C4475w.m17753e("Java Catch Happen", new Object[0]);
        }
        try {
            if (this.f15492g) {
                if (!this.f15488c.m17395b()) {
                    C4475w.m17753e("waiting for remote sync", new Object[0]);
                    int i = 0;
                    while (!this.f15488c.m17395b()) {
                        C4479y.m17804b(500);
                        i += 500;
                        if (i >= HttpRequestExecutor.DEFAULT_WAIT_FOR_CONTINUE) {
                            break;
                        }
                    }
                }
                if (!this.f15488c.m17395b()) {
                    C4475w.m17752d("no remote but still store!", new Object[0]);
                }
                if (this.f15488c.m17396c().f15320g || !this.f15488c.m17395b()) {
                    CrashDetailBean b = m17471b(thread, th, z, str, bArr);
                    if (b == null) {
                        C4475w.m17753e("pkg crash datas fail!", new Object[0]);
                        if (!z) {
                            return;
                        }
                        if (this.f15490e != null && C4440e.m17473b(this.f15490e)) {
                            C4475w.m17753e("sys default last handle start!", new Object[0]);
                            this.f15490e.uncaughtException(thread, th);
                            C4475w.m17753e("sys default last handle end!", new Object[0]);
                            return;
                        } else if (this.f15491f != null) {
                            C4475w.m17753e("system handle start!", new Object[0]);
                            this.f15491f.uncaughtException(thread, th);
                            C4475w.m17753e("system handle end!", new Object[0]);
                            return;
                        } else {
                            C4475w.m17753e("crashreport last handle start!", new Object[0]);
                            C4475w.m17753e("current process die", new Object[0]);
                            Process.killProcess(Process.myPid());
                            System.exit(1);
                            C4475w.m17753e("crashreport last handle end!", new Object[0]);
                            return;
                        }
                    }
                    C4433b.m17429a(z ? "JAVA_CRASH" : "JAVA_CATCH", C4479y.m17777a(), this.f15489d.f15289d, thread, C4479y.m17781a(th), b);
                    if (!this.f15487b.m17440a(b)) {
                        this.f15487b.m17438a(b, 3000, z);
                    }
                    if (!z) {
                        return;
                    }
                    if (this.f15490e != null && C4440e.m17473b(this.f15490e)) {
                        C4475w.m17753e("sys default last handle start!", new Object[0]);
                        this.f15490e.uncaughtException(thread, th);
                        C4475w.m17753e("sys default last handle end!", new Object[0]);
                        return;
                    } else if (this.f15491f != null) {
                        C4475w.m17753e("system handle start!", new Object[0]);
                        this.f15491f.uncaughtException(thread, th);
                        C4475w.m17753e("system handle end!", new Object[0]);
                        return;
                    } else {
                        C4475w.m17753e("crashreport last handle start!", new Object[0]);
                        C4475w.m17753e("current process die", new Object[0]);
                        Process.killProcess(Process.myPid());
                        System.exit(1);
                        C4475w.m17753e("crashreport last handle end!", new Object[0]);
                        return;
                    }
                }
                String str2;
                C4475w.m17753e("crash report was closed by remote , will not upload to Bugly , print local for helpful!", new Object[0]);
                if (z) {
                    str2 = "JAVA_CRASH";
                } else {
                    str2 = "JAVA_CATCH";
                }
                C4433b.m17429a(str2, C4479y.m17777a(), this.f15489d.f15289d, thread, C4479y.m17781a(th), null);
                if (!z) {
                    return;
                }
                if (this.f15490e != null && C4440e.m17473b(this.f15490e)) {
                    C4475w.m17753e("sys default last handle start!", new Object[0]);
                    this.f15490e.uncaughtException(thread, th);
                    C4475w.m17753e("sys default last handle end!", new Object[0]);
                    return;
                } else if (this.f15491f != null) {
                    C4475w.m17753e("system handle start!", new Object[0]);
                    this.f15491f.uncaughtException(thread, th);
                    C4475w.m17753e("system handle end!", new Object[0]);
                    return;
                } else {
                    C4475w.m17753e("crashreport last handle start!", new Object[0]);
                    C4475w.m17753e("current process die", new Object[0]);
                    Process.killProcess(Process.myPid());
                    System.exit(1);
                    C4475w.m17753e("crashreport last handle end!", new Object[0]);
                    return;
                }
            }
            C4475w.m17751c("Java crash handler is disable. Just return.", new Object[0]);
            if (!z) {
                return;
            }
            if (this.f15490e != null && C4440e.m17473b(this.f15490e)) {
                C4475w.m17753e("sys default last handle start!", new Object[0]);
                this.f15490e.uncaughtException(thread, th);
                C4475w.m17753e("sys default last handle end!", new Object[0]);
            } else if (this.f15491f != null) {
                C4475w.m17753e("system handle start!", new Object[0]);
                this.f15491f.uncaughtException(thread, th);
                C4475w.m17753e("system handle end!", new Object[0]);
            } else {
                C4475w.m17753e("crashreport last handle start!", new Object[0]);
                C4475w.m17753e("current process die", new Object[0]);
                Process.killProcess(Process.myPid());
                System.exit(1);
                C4475w.m17753e("crashreport last handle end!", new Object[0]);
            }
        } catch (Throwable th2) {
            if (z) {
                if (this.f15490e != null && C4440e.m17473b(this.f15490e)) {
                    C4475w.m17753e("sys default last handle start!", new Object[0]);
                    this.f15490e.uncaughtException(thread, th);
                    C4475w.m17753e("sys default last handle end!", new Object[0]);
                } else if (this.f15491f != null) {
                    C4475w.m17753e("system handle start!", new Object[0]);
                    this.f15491f.uncaughtException(thread, th);
                    C4475w.m17753e("system handle end!", new Object[0]);
                } else {
                    C4475w.m17753e("crashreport last handle start!", new Object[0]);
                    C4475w.m17753e("current process die", new Object[0]);
                    Process.killProcess(Process.myPid());
                    System.exit(1);
                    C4475w.m17753e("crashreport last handle end!", new Object[0]);
                }
            }
        }
    }

    public final void uncaughtException(Thread thread, Throwable th) {
        synchronized (f15485i) {
            m17476a(thread, th, true, null, null);
        }
    }

    /* renamed from: b */
    private static boolean m17473b(UncaughtExceptionHandler uncaughtExceptionHandler) {
        if (uncaughtExceptionHandler == null) {
            return true;
        }
        String name = uncaughtExceptionHandler.getClass().getName();
        String str = "uncaughtException";
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            String className = stackTraceElement.getClassName();
            String methodName = stackTraceElement.getMethodName();
            if (name.equals(className) && str.equals(methodName)) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    public final synchronized void m17475a(StrategyBean strategyBean) {
        if (strategyBean != null) {
            if (strategyBean.f15320g != this.f15492g) {
                C4475w.m17747a("java changed to %b", Boolean.valueOf(strategyBean.f15320g));
                if (strategyBean.f15320g) {
                    m17474a();
                } else {
                    m17477b();
                }
            }
        }
    }

    /* renamed from: a */
    private static String m17468a(Throwable th, int i) {
        if (th == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        try {
            if (th.getStackTrace() != null) {
                StackTraceElement[] stackTrace = th.getStackTrace();
                int length = stackTrace.length;
                int i2 = 0;
                while (i2 < length) {
                    StackTraceElement stackTraceElement = stackTrace[i2];
                    if (i <= 0 || stringBuilder.length() < i) {
                        stringBuilder.append(stackTraceElement.toString()).append("\n");
                        i2++;
                    } else {
                        stringBuilder.append("\n[Stack over limit size :" + i + " , has been cutted !]");
                        return stringBuilder.toString();
                    }
                }
            }
        } catch (Throwable th2) {
            C4475w.m17753e("gen stack error %s", th2.toString());
        }
        return stringBuilder.toString();
    }

    /* renamed from: b */
    private static String m17472b(Throwable th, int i) {
        if (th.getMessage() == null) {
            return "";
        }
        if (th.getMessage().length() <= 1000) {
            return th.getMessage();
        }
        return th.getMessage().substring(0, 1000) + "\n[Message over limit size:1000" + ", has been cutted!]";
    }
}
