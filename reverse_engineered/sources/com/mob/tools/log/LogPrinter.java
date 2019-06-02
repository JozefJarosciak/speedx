package com.mob.tools.log;

import android.content.Context;
import android.text.TextUtils;
import java.util.HashMap;

public class LogPrinter {
    private HashMap<String, LogCollector> collectors = new HashMap();
    private String packageName = "";
    private String scope = "";

    public void setContext(Context context) {
        this.packageName = context.getPackageName();
        if (TextUtils.isEmpty(this.packageName)) {
            this.packageName = "";
        } else {
            this.scope = this.packageName;
        }
    }

    public void setCollector(String str, LogCollector logCollector) {
        this.collectors.put(str, logCollector);
    }

    public int println(String str, int i, int i2, String str2) {
        Thread currentThread = Thread.currentThread();
        String processMessage = processMessage(currentThread, str2);
        String scope = getScope(currentThread);
        LogCollector logCollector = (LogCollector) this.collectors.get(str);
        if (logCollector != null) {
            logCollector.log(str, i, i2, scope, processMessage);
        }
        return 0;
    }

    public void nativeCrashLog(String str, String str2) {
        LogCollector logCollector = (LogCollector) this.collectors.get(str);
        if (logCollector != null) {
            logCollector.log(str, 6, 2, this.scope, str2);
        }
    }

    private String processMessage(Thread thread, String str) {
        return String.format("%s %s", new Object[]{thread.getName(), str});
    }

    private String getScope(Thread thread) {
        StackTraceElement[] stackTrace = thread.getStackTrace();
        if (stackTrace == null || stackTrace.length <= 0) {
            return this.scope;
        }
        StackTraceElement stackTraceElement = stackTrace[stackTrace.length - 1];
        String fileName = stackTraceElement.getFileName();
        if (fileName == null || fileName.length() <= 0) {
            fileName = stackTraceElement.getClassName();
        } else {
            fileName = this.scope + "/" + fileName;
        }
        int lineNumber = stackTraceElement.getLineNumber();
        String valueOf = String.valueOf(lineNumber);
        if (lineNumber < 0) {
            valueOf = stackTraceElement.getMethodName();
            if (valueOf == null || valueOf.length() <= 0) {
                valueOf = "Unknown Source";
            }
        }
        return fileName + "(" + valueOf + ")";
    }
}
