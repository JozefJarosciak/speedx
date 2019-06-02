package com.beastbikes.android;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.webkit.CookieSyncManager;
import ch.qos.logback.classic.android.BasicLogcatConfigurator;
import cn.jpush.android.api.JPushInterface;
import cn.sharesdk.framework.ShareSDK;
import com.avos.avoscloud.AVAnalytics;
import com.baidu.mapapi.SDKInitializer;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.ble.biz.p058c.C1386e;
import com.beastbikes.android.main.MainActivity;
import com.beastbikes.android.modules.cycling.SyncService;
import com.beastbikes.android.modules.cycling.activity.biz.C1398a;
import com.beastbikes.android.modules.social.im.p074a.C1434c;
import com.beastbikes.android.p057b.C1381a;
import com.beastbikes.android.p057b.C1383b.C1382a;
import com.beastbikes.android.utils.p080a.C1454a;
import com.beastbikes.android.utils.p080a.C1455b;
import com.beastbikes.framework.android.ApplicationContext;
import com.beastbikes.framework.android.p086d.C1461a;
import com.beastbikes.framework.android.p088g.C1464b;
import com.beastbikes.framework.android.p088g.C1465f;
import com.beastbikes.framework.android.p088g.C1466g;
import com.beastbikes.framework.android.p088g.C1467h;
import com.beastbikes.framework.business.C1372b;
import com.facebook.C1472c;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.bugly.crashreport.CrashReport.UserStrategy;
import com.twitter.sdk.android.C1499a;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.MobclickAgent.C1519a;
import com.umeng.onlineconfig.OnlineConfigAgent;
import io.fabric.sdk.android.C1520c;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeastBikes extends ApplicationContext implements OnSharedPreferenceChangeListener, C1371a, C1372b {
    /* renamed from: a */
    public static boolean f3996a = false;
    /* renamed from: b */
    private static final Logger f3997b = LoggerFactory.getLogger("BeastBikes");
    /* renamed from: c */
    private C1381a f3998c;
    /* renamed from: d */
    private Locale f3999d;
    /* renamed from: e */
    private C1398a f4000e;

    public static native String getApiUrl();

    public static native String getBugglyAppId();

    public static native String getHost();

    public static native String getHostDomain();

    public static native String getMapBoxAccessToken();

    public static native String getRongCloudKey();

    public static native String getTwitterConsumerKey();

    public static native String getTwitterConsumerSecret();

    public static native String getUserPrivateKey();

    private native void native_finalize();

    private native void native_initialize(boolean z);

    static {
        System.loadLibrary("beastbikes-jni");
        BasicLogcatConfigurator.configureDefaultContext();
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        f3997b.trace("onSharedPreferenceChanged: " + str + " =" + String.valueOf(sharedPreferences.getAll().get(str)));
    }

    public void onActivityPaused(Activity activity) {
        super.onActivityPaused(activity);
        AVAnalytics.onPause(activity);
        MobclickAgent.m8334a((Context) this);
    }

    public void onActivityResumed(Activity activity) {
        super.onActivityResumed(activity);
        AVAnalytics.onResume(activity);
        MobclickAgent.m8337b(activity);
        Intent intent = activity.getIntent();
        if (intent != null) {
            Object stringExtra = intent.getStringExtra("extra_notify_count_key");
            if (!TextUtils.isEmpty(stringExtra)) {
                AVUser currentUser = AVUser.getCurrentUser();
                if (currentUser != null) {
                    getSharedPreferences(currentUser.getObjectId(), 0).edit().putInt(stringExtra, 0).apply();
                }
            }
        }
    }

    @TargetApi(19)
    public void onCreate() {
        String a = C1466g.m8066a(this);
        if (TextUtils.isEmpty(a) || a.endsWith(":remote") || a.endsWith(":DownloadingService") || a.endsWith(":ipc") || a.endsWith("io.rong.push")) {
            f3997b.trace("======================== " + a + " ========================");
            return;
        }
        super.onCreate();
        this.f3999d = getResources().getConfiguration().locale;
        try {
            ShareSDK.initSDK(this);
        } catch (RuntimeException e) {
            f3997b.error("ShareSDK Exception e=" + e.getMessage());
        }
        Thread.setDefaultUncaughtExceptionHandler(new C1461a(this));
        a = C1464b.m8055a(this);
        MobclickAgent.m8336a(new C1519a(this, ApplicationContext.m5241a((Context) this, "UMENG_APPKEY"), a));
        OnlineConfigAgent.getInstance().updateOnlineConfig(this);
        JPushInterface.setDebugMode(false);
        JPushInterface.init(this);
        native_initialize(false);
        AVAnalytics.setAnalyticsEnabled(true);
        AVUser.initAVCloudUser();
        C1382a.m5409a(this);
        C1386e.m5413a();
        TwitterAuthConfig twitterAuthConfig = new TwitterAuthConfig(getTwitterConsumerKey(), getTwitterConsumerSecret());
        C1520c.m8347a((Context) this, new C1499a(twitterAuthConfig));
        SDKInitializer.initialize(this);
        C1472c.m8114a((Context) this);
        UserStrategy userStrategy = new UserStrategy(this);
        userStrategy.setAppChannel(a);
        userStrategy.setAppVersion(C1465f.m8062b(this));
        userStrategy.setAppReportDelay(5000);
        CrashReport.initCrashReport(this, getBugglyAppId(), false, userStrategy);
        try {
            PreferenceManager.setDefaultValues(this, C1373R.xml.beastbikes, true);
        } catch (Exception e2) {
        }
        this.f3998c = new C1381a(this);
        C1454a.m7990a().m7995a((Context) this, (OnSharedPreferenceChangeListener) this);
        C1455b.m7999a().m8000a(this, this);
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            f3997b.info("Start SyncService ");
            try {
                CrashReport.setUserId(currentUser.getObjectId());
                startService(new Intent(this, SyncService.class));
            } catch (Exception e3) {
                f3997b.info("OPPO Service SecurityException");
            }
        } else {
            f3997b.info("Start SyncService fail, because user is null!");
        }
        this.f4000e = new C1398a((Context) this);
        Object a2 = C1398a.m5854a((Context) this);
        if (TextUtils.isEmpty(a2)) {
            f3997b.info("activityId = null ");
        } else {
            f3997b.trace("activityId = " + a2);
            try {
                Intent intent = new Intent("com.beastbikes.intent.action.ACTIVITY_MANAGER");
                intent.setPackage(getPackageName());
                startService(intent);
            } catch (SecurityException e4) {
                f3997b.error("Start activity service error, " + e4);
            }
        }
        C1434c.m7291a((Context) this, getRongCloudKey());
        CookieSyncManager.createInstance(this);
        CookieHandler.setDefault(new CookieManager());
    }

    public void onTerminate() {
        native_finalize();
        C1454a.m7990a().m7997b(this, this);
        C1455b.m7999a().m8001b(this, this);
        super.onTerminate();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        f3997b.info("onConfigurationChanged is called!");
        if (this.f3999d != null && !configuration.locale.equals(this.f3999d)) {
            this.f3999d = configuration.locale;
            m5243k();
            C1434c.m7302c().m7315f();
        }
    }

    /* renamed from: a */
    public int m5244a() {
        return C1454a.m7990a().m7991a((Context) this, "beast.setting.accuracy", 0);
    }

    /* renamed from: a */
    public void m5245a(int i) {
        C1454a.m7990a().m7993a((Context) this, "beast.setting.accuracy", Integer.valueOf(i)).apply();
    }

    /* renamed from: a */
    public void m5246a(int i, boolean z) {
        int a = C1454a.m7990a().m7991a((Context) this, "beast.setting.voicefeedback", 0);
        C1454a.m7990a().m7993a((Context) this, "beast.setting.voicefeedback", Integer.valueOf(z ? a | i : a & (i ^ -1))).apply();
    }

    /* renamed from: b */
    public boolean m5250b(int i) {
        return (C1454a.m7990a().m7991a((Context) this, "beast.setting.voicefeedback", 0) & i) != 0;
    }

    /* renamed from: a */
    public void m5247a(boolean z) {
        C1454a.m7990a().m7993a((Context) this, "beast.setting.autopause", Boolean.valueOf(z)).apply();
    }

    /* renamed from: b */
    public boolean m5249b() {
        return C1454a.m7990a().m7996a((Context) this, "beast.setting.autopause", false);
    }

    /* renamed from: c */
    public C1398a m5251c() {
        return this.f4000e;
    }

    /* renamed from: d */
    public C1381a m5253d() {
        return this.f3998c;
    }

    /* renamed from: b */
    public void m5248b(boolean z) {
        C1454a.m7990a().m7993a((Context) this, "beast.setting.foreground", Boolean.valueOf(z)).apply();
    }

    /* renamed from: e */
    public boolean m5256e() {
        return C1454a.m7990a().m7996a((Context) this, "beast.setting.foreground", true);
    }

    /* renamed from: c */
    public void m5252c(boolean z) {
        C1454a.m7990a().m7993a((Context) this, "beast.setting.map.style", Boolean.valueOf(z)).apply();
    }

    /* renamed from: f */
    public boolean m5258f() {
        return C1454a.m7990a().m7996a((Context) this, "beast.setting.map.style", false);
    }

    /* renamed from: d */
    public void m5254d(boolean z) {
        C1454a.m7990a().m7993a((Context) this, "beast.setting.cycling.keep.screen.on", Boolean.valueOf(z)).apply();
    }

    /* renamed from: g */
    public boolean m5259g() {
        return C1454a.m7990a().m7996a((Context) this, "beast.setting.cycling.keep.screen.on", false);
    }

    /* renamed from: e */
    public void m5255e(boolean z) {
        C1454a.m7990a().m7993a((Context) this, "beast.setting.cycling.repair.screen.on", Boolean.valueOf(z)).apply();
    }

    /* renamed from: h */
    public boolean m5260h() {
        return C1454a.m7990a().m7996a((Context) this, "beast.setting.cycling.repair.screen.on", true);
    }

    /* renamed from: f */
    public void m5257f(boolean z) {
        C1454a.m7990a().m7993a((Context) this, "beast.open.cycling.repair", Boolean.valueOf(z)).apply();
    }

    /* renamed from: i */
    public boolean m5261i() {
        return C1454a.m7990a().m7996a((Context) this, "beast.open.cycling.repair", true);
    }

    /* renamed from: k */
    private void m5243k() {
        sendBroadcast(new Intent("action_finish_home_activity"));
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(268435456);
        startActivity(intent);
        if (!C1467h.m8067a(this, SyncService.class.getName())) {
            try {
                stopService(new Intent(this, SyncService.class));
            } catch (Exception e) {
            }
        }
    }
}
