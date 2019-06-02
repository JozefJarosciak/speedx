package com.avos.avoscloud;

import android.content.Context;
import android.os.Process;
import android.util.Log;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AVUncaughtExceptionHandler implements UncaughtExceptionHandler {
    private final String LOG_TAG = AVUncaughtExceptionHandler.class.getSimpleName();
    private Thread brokenThread;
    private final Context context;
    private final UncaughtExceptionHandler defaultExceptionHandler;
    private boolean enabled = false;
    private Throwable unhandledThrowable;

    public AVUncaughtExceptionHandler(Context context) {
        this.context = context;
        this.defaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public void enableCrashHanlder(boolean z) {
        this.enabled = z;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        try {
            if (this.enabled) {
                this.brokenThread = thread;
                this.unhandledThrowable = th;
                Log.e(this.LOG_TAG, "AVUncaughtExceptionHandler caught a " + th.getClass().getSimpleName() + " exception ");
                handleException(this.unhandledThrowable, false, true);
            } else if (this.defaultExceptionHandler != null) {
                Log.w(this.LOG_TAG, "AVUncaughtExceptionHandler is disabled and fallback to default handler.");
                this.defaultExceptionHandler.uncaughtException(thread, th);
            } else {
                Log.w(this.LOG_TAG, "AVUncaughtExceptionHandler is disabled and there is no default handler, good luck.");
            }
        } catch (Throwable th2) {
            if (this.defaultExceptionHandler != null) {
                this.defaultExceptionHandler.uncaughtException(thread, th);
            }
        }
    }

    public void handleException(Throwable th, boolean z) {
        handleException(th, false, z);
    }

    public void handleException(Throwable th) {
        handleException(th, false, false);
    }

    private void handleException(Throwable th, boolean z, boolean z2) {
        if (this.enabled) {
            if (th == null) {
                th = new Exception("Report requested by developer");
            }
            AVAnalytics.reportError(this.context, crashData(this.context, th), null);
            if (z2) {
                endApplication();
            }
        }
    }

    private String getStackTrace(Throwable th) {
        Writer stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        while (th != null) {
            th.printStackTrace(printWriter);
            th = th.getCause();
        }
        String obj = stringWriter.toString();
        printWriter.close();
        return obj;
    }

    private Map<String, Object> crashData(Context context, Throwable th) {
        Map<String, Object> hashMap = new HashMap();
        try {
            hashMap.put("reason", th.toString());
            hashMap.put("stack_trace", getStackTrace(th));
            hashMap.put("date", AVUtils.stringFromDate(new Date()));
            try {
                Class cls = Class.forName("com.avos.avoscloud.AVInstallation");
                String str = "installationId";
                hashMap.put(str, (String) cls.getMethod("getInstallationId", new Class[0]).invoke(cls.getMethod("getCurrentInstallation", new Class[0]).invoke(cls, new Object[0]), new Object[0]));
            } catch (Exception e) {
            }
            hashMap.put("packageName", context.getPackageName());
            hashMap.putAll(AnalyticsUtils.getDeviceInfo(context));
            hashMap.put("memInfo", AnalyticsUtils.collectMemInfo());
            hashMap.put("totalDiskSpace", Long.valueOf(AnalyticsUtils.getTotalInternalMemorySize()));
            hashMap.put("availableDiskSpace", Long.valueOf(AnalyticsUtils.getAvailableInternalMemorySize()));
            hashMap.put("appFilePath", AnalyticsUtils.getApplicationFilePath(context));
            hashMap.put("ipAddress", AnalyticsUtils.getLocalIpAddress());
        } catch (Throwable e2) {
            Log.e(this.LOG_TAG, "Error while retrieving crash data", e2);
        }
        return hashMap;
    }

    private void endApplication() {
        AVAnalytics.impl.pauseSession();
        AVAnalytics.impl.archiveCurrentSession();
        if (this.defaultExceptionHandler != null) {
            this.defaultExceptionHandler.uncaughtException(this.brokenThread, this.unhandledThrowable);
            return;
        }
        Log.e(this.LOG_TAG, this.context.getPackageName() + " fatal error : " + this.unhandledThrowable.getMessage(), this.unhandledThrowable);
        Process.killProcess(Process.myPid());
        System.exit(10);
    }
}
