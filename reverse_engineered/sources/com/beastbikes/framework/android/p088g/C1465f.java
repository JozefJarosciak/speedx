package com.beastbikes.framework.android.p088g;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Process;
import com.umeng.onlineconfig.C4766a;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: PackageUtils */
/* renamed from: com.beastbikes.framework.android.g.f */
public final class C1465f {
    /* renamed from: a */
    private static final Logger f6860a = LoggerFactory.getLogger(C1465f.class);

    /* renamed from: a */
    public static final int m8061a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (Throwable e) {
            f6860a.error(e.getMessage(), e);
            return -1;
        }
    }

    /* renamed from: b */
    public static final String m8062b(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Throwable e) {
            f6860a.error(e.getMessage(), e);
            return C4766a.f16699b;
        }
    }

    private C1465f() {
    }

    /* renamed from: c */
    public static boolean m8063c(Context context) {
        List runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1);
        if (runningTasks.isEmpty() || ((RunningTaskInfo) runningTasks.get(0)).topActivity.getPackageName().equals(context.getPackageName())) {
            f6860a.info("Application Background is false");
            return false;
        }
        f6860a.info("Application Background is true");
        return true;
    }

    /* renamed from: d */
    public static String m8064d(Context context) {
        List runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1);
        if (!runningTasks.isEmpty()) {
            ComponentName componentName = ((RunningTaskInfo) runningTasks.get(0)).topActivity;
            if (componentName != null) {
                return componentName.getClassName();
            }
        }
        return "";
    }

    /* renamed from: e */
    public static void m8065e(Context context) {
        f6860a.info("ReStart application!");
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        launchIntentForPackage.addFlags(67108864);
        context.startActivity(launchIntentForPackage);
        Process.killProcess(Process.myPid());
        System.exit(0);
    }
}
