package com.beastbikes.framework.android;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.StatFs;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.view.ViewConfiguration;
import com.beastbikes.framework.android.p086d.C1461a;
import com.beastbikes.framework.android.p088g.C1464b;
import com.beastbikes.framework.android.p088g.C1465f;
import java.io.File;
import java.lang.reflect.Field;
import org.apache.http.HttpHost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ApplicationContext extends MultiDexApplication implements ActivityLifecycleCallbacks {
    /* renamed from: a */
    private static final Logger f3993a = LoggerFactory.getLogger("ApplicationContext");
    /* renamed from: b */
    private static ApplicationContext f3994b;
    /* renamed from: c */
    private Object f3995c;

    static {
        System.loadLibrary("trace");
    }

    /* renamed from: j */
    public static final ApplicationContext m5242j() {
        return f3994b;
    }

    public ApplicationContext() {
        f3994b = this;
    }

    /* renamed from: a */
    public static String m5241a(Context context, String str) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getString(str);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            f3993a.error("PackageManager.NameNotFoundException");
            return "";
        }
    }

    @TargetApi(18)
    public void onCreate() {
        super.onCreate();
        Thread.setDefaultUncaughtExceptionHandler(new C1461a(this));
        registerActivityLifecycleCallbacks(this);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(this);
        try {
            Field declaredField = viewConfiguration.getClass().getDeclaredField("sHasPermanentMenuKey");
            if (declaredField != null) {
                declaredField.setAccessible(true);
                declaredField.setBoolean(viewConfiguration, false);
            }
        } catch (Throwable e) {
            f3993a.error(e.getMessage(), e);
        }
        try {
            long blockCountLong;
            long blockSizeLong;
            File file = new File(getCacheDir(), HttpHost.DEFAULT_SCHEME_NAME);
            if (!file.exists()) {
                file.mkdirs();
            }
            StatFs statFs = new StatFs(file.getAbsolutePath());
            if (VERSION.SDK_INT >= 18) {
                blockCountLong = statFs.getBlockCountLong();
            } else {
                blockCountLong = (long) statFs.getBlockCount();
            }
            if (VERSION.SDK_INT >= 18) {
                blockSizeLong = statFs.getBlockSizeLong();
            } else {
                blockSizeLong = (long) statFs.getBlockSize();
            }
            Class cls = Class.forName("android.net.http.HttpResponseCache");
            this.f3995c = cls.getMethod("install", new Class[]{File.class, Long.TYPE}).invoke(cls, new Object[]{file, Long.valueOf(blockSizeLong * blockCountLong)});
        } catch (Throwable e2) {
            f3993a.error("Install http response cache error", e2);
        }
        f3993a.info(m5240a(80, getPackageName()));
        f3993a.info("> ro.bootloader                : " + Build.BOOTLOADER);
        f3993a.info("> ro.build.id                  : " + Build.ID);
        f3993a.info("> ro.build.display.id          : " + Build.DISPLAY);
        f3993a.info("> ro.build.version.incremental : " + VERSION.INCREMENTAL);
        f3993a.info("> ro.build.version.release     : " + VERSION.RELEASE);
        f3993a.info("> ro.build.version.sdk         : " + VERSION.SDK_INT);
        f3993a.info("> ro.build.version.codename    : " + VERSION.CODENAME);
        f3993a.info("> ro.build.type                : " + Build.TYPE);
        f3993a.info("> ro.build.tags                : " + Build.TAGS);
        f3993a.info("> ro.build.fingerprint         : " + Build.FINGERPRINT);
        f3993a.info("> ro.build.date.utc            : " + Build.TIME);
        f3993a.info("> ro.build.user                : " + Build.USER);
        f3993a.info("> ro.build.host                : " + Build.HOST);
        f3993a.info("> ro.hardware                  : " + Build.HARDWARE);
        f3993a.info("> ro.product.board             : " + Build.BOARD);
        f3993a.info("> ro.product.brand             : " + Build.BRAND);
        f3993a.info("> ro.product.cpu.abi           : " + Build.CPU_ABI);
        f3993a.info("> ro.product.cpu.abi2          : " + Build.CPU_ABI2);
        f3993a.info("> ro.product.device            : " + Build.DEVICE);
        f3993a.info("> ro.product.manufacturer      : " + Build.MANUFACTURER);
        f3993a.info("> ro.product.model             : " + Build.MODEL);
        f3993a.info("> ro.product.name              : " + Build.PRODUCT);
        f3993a.info("> ro.serialno                  : " + Build.SERIAL);
        f3993a.info("> ro.versionCode               : " + C1465f.m8061a(this));
        f3993a.info("> ro.versionName               : " + C1465f.m8062b(this));
        f3993a.info("> ro.channel                   : " + C1464b.m8055a(this));
        f3993a.info("================================================================================");
    }

    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityResumed(Activity activity) {
    }

    public void onActivityPaused(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
    }

    public void onTerminate() {
        if (this.f3995c != null) {
            try {
                Class.forName("android.net.http.HttpResponseCache").getMethod("close", new Class[0]).invoke(this.f3995c, new Object[0]);
            } catch (Throwable e) {
                f3993a.error("Close http response cache error", e);
            }
        }
        super.onTerminate();
    }

    /* renamed from: a */
    private static String m5240a(int i, String str) {
        int i2 = 0;
        int max = Math.max(0, i - 2);
        int length = (max - str.length()) / 2;
        int length2 = (max - str.length()) - length;
        StringBuilder stringBuilder = new StringBuilder();
        for (max = 0; max < length; max++) {
            stringBuilder.append('=');
        }
        stringBuilder.append(' ').append(str).append(' ');
        while (i2 < length2) {
            stringBuilder.append('=');
            i2++;
        }
        return stringBuilder.toString();
    }
}
