package com.tencent.wxop.stat.common;

import android.util.Log;
import com.tencent.wxop.stat.C4550g;
import com.tencent.wxop.stat.StatConfig;

public final class StatLogger {
    /* renamed from: a */
    private String f16038a = "default";
    /* renamed from: b */
    private boolean f16039b = true;
    /* renamed from: c */
    private int f16040c = 2;

    public StatLogger(String str) {
        this.f16038a = str;
    }

    /* renamed from: a */
    private String m18008a() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace == null) {
            return null;
        }
        for (StackTraceElement stackTraceElement : stackTrace) {
            if (!stackTraceElement.isNativeMethod() && !stackTraceElement.getClassName().equals(Thread.class.getName()) && !stackTraceElement.getClassName().equals(getClass().getName())) {
                return "[" + Thread.currentThread().getName() + "(" + Thread.currentThread().getId() + "): " + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + "]";
            }
        }
        return null;
    }

    /* renamed from: d */
    public final void m18009d(Object obj) {
        if (isDebugEnable()) {
            debug(obj);
        }
    }

    public final void debug(Object obj) {
        if (this.f16040c <= 3) {
            String a = m18008a();
            a = a == null ? obj.toString() : a + " - " + obj;
            Log.d(this.f16038a, a);
            C4550g customLogger = StatConfig.getCustomLogger();
            if (customLogger != null) {
                customLogger.m18118e(a);
            }
        }
    }

    /* renamed from: e */
    public final void m18010e(Object obj) {
        if (isDebugEnable()) {
            error(obj);
        }
    }

    /* renamed from: e */
    public final void m18011e(Throwable th) {
        if (isDebugEnable()) {
            error(th);
        }
    }

    public final void error(Object obj) {
        if (this.f16040c <= 6) {
            String a = m18008a();
            a = a == null ? obj.toString() : a + " - " + obj;
            Log.e(this.f16038a, a);
            C4550g customLogger = StatConfig.getCustomLogger();
            if (customLogger != null) {
                customLogger.m18117d(a);
            }
        }
    }

    public final void error(Throwable th) {
        if (this.f16040c <= 6) {
            Log.e(this.f16038a, "", th);
            C4550g customLogger = StatConfig.getCustomLogger();
            if (customLogger != null) {
                customLogger.m18117d(th);
            }
        }
    }

    public final int getLogLevel() {
        return this.f16040c;
    }

    /* renamed from: i */
    public final void m18012i(Object obj) {
        if (isDebugEnable()) {
            info(obj);
        }
    }

    public final void info(Object obj) {
        if (this.f16040c <= 4) {
            String a = m18008a();
            a = a == null ? obj.toString() : a + " - " + obj;
            Log.i(this.f16038a, a);
            C4550g customLogger = StatConfig.getCustomLogger();
            if (customLogger != null) {
                customLogger.m18114a(a);
            }
        }
    }

    public final boolean isDebugEnable() {
        return this.f16039b;
    }

    public final void setDebugEnable(boolean z) {
        this.f16039b = z;
    }

    public final void setLogLevel(int i) {
        this.f16040c = i;
    }

    public final void setTag(String str) {
        this.f16038a = str;
    }

    /* renamed from: v */
    public final void m18013v(Object obj) {
        if (isDebugEnable()) {
            verbose(obj);
        }
    }

    public final void verbose(Object obj) {
        if (this.f16040c <= 2) {
            String a = m18008a();
            a = a == null ? obj.toString() : a + " - " + obj;
            Log.v(this.f16038a, a);
            C4550g customLogger = StatConfig.getCustomLogger();
            if (customLogger != null) {
                customLogger.m18115b(a);
            }
        }
    }

    /* renamed from: w */
    public final void m18014w(Object obj) {
        if (isDebugEnable()) {
            warn(obj);
        }
    }

    public final void warn(Object obj) {
        if (this.f16040c <= 5) {
            String a = m18008a();
            a = a == null ? obj.toString() : a + " - " + obj;
            Log.w(this.f16038a, a);
            C4550g customLogger = StatConfig.getCustomLogger();
            if (customLogger != null) {
                customLogger.m18116c(a);
            }
        }
    }
}
