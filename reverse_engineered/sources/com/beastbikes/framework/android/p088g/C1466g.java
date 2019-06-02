package com.beastbikes.framework.android.p088g;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.os.Process;

/* compiled from: ProcessUtils */
/* renamed from: com.beastbikes.framework.android.g.g */
public final class C1466g {
    /* renamed from: a */
    public static String m8066a(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager == null) {
            return "";
        }
        int myPid = Process.myPid();
        if (activityManager.getRunningAppProcesses() == null) {
            return "";
        }
        for (RunningAppProcessInfo runningAppProcessInfo : activityManager.getRunningAppProcesses()) {
            if (runningAppProcessInfo.pid == myPid) {
                return runningAppProcessInfo.processName;
            }
        }
        return "";
    }
}
