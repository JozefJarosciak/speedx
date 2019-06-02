package com.tencent.bugly.crashreport;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.v4.os.EnvironmentCompat;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.alipay.sdk.util.C0880h;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.C4402b;
import com.tencent.bugly.CrashModule;
import com.tencent.bugly.crashreport.biz.C4415b;
import com.tencent.bugly.crashreport.common.info.C4417a;
import com.tencent.bugly.crashreport.crash.BuglyBroadcastRecevier;
import com.tencent.bugly.crashreport.crash.C4436c;
import com.tencent.bugly.crashreport.crash.h5.C4442b;
import com.tencent.bugly.crashreport.crash.h5.H5JavaScriptInterface;
import com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler;
import com.tencent.bugly.proguard.C4474v;
import com.tencent.bugly.proguard.C4475w;
import com.tencent.bugly.proguard.C4479y;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* compiled from: BUGLY */
public class CrashReport {
    /* renamed from: a */
    private static Context f7037a;

    /* compiled from: BUGLY */
    public static class UserStrategy extends BuglyStrategy {
        /* renamed from: a */
        private CrashReport$CrashHandleCallback f7036a;

        public UserStrategy(Context context) {
        }

        public synchronized CrashReport$CrashHandleCallback getCrashHandleCallback() {
            return this.f7036a;
        }

        public synchronized void setCrashHandleCallback(CrashReport$CrashHandleCallback crashReport$CrashHandleCallback) {
            this.f7036a = crashReport$CrashHandleCallback;
        }
    }

    public static void enableBugly(boolean z) {
        C4402b.f15202a = z;
    }

    public static void initCrashReport(Context context) {
        f7037a = context;
        C4402b.a(CrashModule.getInstance());
        C4402b.a(context);
    }

    public static void initCrashReport(Context context, UserStrategy userStrategy) {
        f7037a = context;
        C4402b.a(CrashModule.getInstance());
        C4402b.a(context, userStrategy);
    }

    public static void initCrashReport(Context context, String str, boolean z) {
        if (context != null) {
            f7037a = context;
            C4402b.a(CrashModule.getInstance());
            C4402b.a(context, str, z, null);
        }
    }

    public static void initCrashReport(Context context, String str, boolean z, UserStrategy userStrategy) {
        if (context != null) {
            f7037a = context;
            C4402b.a(CrashModule.getInstance());
            C4402b.a(context, str, z, userStrategy);
        }
    }

    public static String getBuglyVersion(Context context) {
        if (context == null) {
            C4475w.d("Please call with context.", new Object[0]);
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
        C4417a.a(context);
        return C4417a.c();
    }

    public static void testJavaCrash() {
        if (!C4402b.f15202a) {
            Log.w(C4475w.f15774a, "Can not test Java crash because bugly is disable.");
        } else if (CrashModule.hasInitialized()) {
            C4417a b = C4417a.b();
            if (b != null) {
                b.b(24096);
            }
            throw new RuntimeException("This Crash create for Test! You can go to Bugly see more detail!");
        } else {
            Log.e(C4475w.f15774a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        }
    }

    public static void testNativeCrash() {
        if (!C4402b.f15202a) {
            Log.w(C4475w.f15774a, "Can not test native crash because bugly is disable.");
        } else if (CrashModule.hasInitialized()) {
            C4475w.a("start to create a native crash for test!", new Object[0]);
            C4436c.a().j();
        } else {
            Log.e(C4475w.f15774a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        }
    }

    public static void testANRCrash() {
        if (!C4402b.f15202a) {
            Log.w(C4475w.f15774a, "Can not test ANR crash because bugly is disable.");
        } else if (CrashModule.hasInitialized()) {
            C4475w.a("start to create a anr crash for test!", new Object[0]);
            C4436c.a().k();
        } else {
            Log.e(C4475w.f15774a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        }
    }

    public static void postCatchedException(Throwable th) {
        postCatchedException(th, Thread.currentThread(), false);
    }

    public static void postCatchedException(Throwable th, Thread thread) {
        postCatchedException(th, thread, false);
    }

    public static void postCatchedException(Throwable th, Thread thread, boolean z) {
        if (!C4402b.f15202a) {
            Log.w(C4475w.f15774a, "Can not post crash caught because bugly is disable.");
        } else if (!CrashModule.hasInitialized()) {
            Log.e(C4475w.f15774a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else if (th == null) {
            C4475w.d("throwable is null, just return", new Object[0]);
        } else {
            Thread currentThread;
            if (thread == null) {
                currentThread = Thread.currentThread();
            } else {
                currentThread = thread;
            }
            C4436c.a().a(currentThread, th, false, null, null, z);
        }
    }

    public static void closeNativeReport() {
        if (!C4402b.f15202a) {
            Log.w(C4475w.f15774a, "Can not close native report because bugly is disable.");
        } else if (CrashModule.hasInitialized()) {
            C4436c.a().f();
        } else {
            Log.e(C4475w.f15774a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        }
    }

    public static void startCrashReport() {
        if (!C4402b.f15202a) {
            Log.w(C4475w.f15774a, "Can not start crash report because bugly is disable.");
        } else if (CrashModule.hasInitialized()) {
            C4436c.a().c();
        } else {
            Log.w(C4475w.f15774a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        }
    }

    public static void closeCrashReport() {
        if (!C4402b.f15202a) {
            Log.w(C4475w.f15774a, "Can not close crash report because bugly is disable.");
        } else if (CrashModule.hasInitialized()) {
            C4436c.a().d();
        } else {
            Log.w(C4475w.f15774a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        }
    }

    public static void closeBugly() {
        if (!C4402b.f15202a) {
            Log.w(C4475w.f15774a, "Can not close bugly because bugly is disable.");
        } else if (!CrashModule.hasInitialized()) {
            Log.w(C4475w.f15774a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else if (f7037a != null) {
            BuglyBroadcastRecevier instance = BuglyBroadcastRecevier.getInstance();
            if (instance != null) {
                instance.unregist(f7037a);
            }
            closeCrashReport();
            C4415b.a(f7037a);
            C4474v a = C4474v.a();
            if (a != null) {
                a.b();
            }
        }
    }

    public static void setUserSceneTag(Context context, int i) {
        if (!C4402b.f15202a) {
            Log.w(C4475w.f15774a, "Can not set tag caught because bugly is disable.");
        } else if (context == null) {
            Log.e(C4475w.f15774a, "setTag args context should not be null");
        } else {
            if (i <= 0) {
                C4475w.d("setTag args tagId should > 0", new Object[0]);
            }
            C4417a.a(context).a(i);
            C4475w.b("[param] set user scene tag: %d", new Object[]{Integer.valueOf(i)});
        }
    }

    public static int getUserSceneTagId(Context context) {
        if (!C4402b.f15202a) {
            Log.w(C4475w.f15774a, "Can not get user scene tag because bugly is disable.");
            return -1;
        } else if (context != null) {
            return C4417a.a(context).F();
        } else {
            Log.e(C4475w.f15774a, "getUserSceneTagId args context should not be null");
            return -1;
        }
    }

    public static String getUserData(Context context, String str) {
        if (!C4402b.f15202a) {
            Log.w(C4475w.f15774a, "Can not get user data because bugly is disable.");
            return EnvironmentCompat.MEDIA_UNKNOWN;
        } else if (context == null) {
            Log.e(C4475w.f15774a, "getUserDataValue args context should not be null");
            return EnvironmentCompat.MEDIA_UNKNOWN;
        } else if (C4479y.a(str)) {
            return null;
        } else {
            return C4417a.a(context).g(str);
        }
    }

    public static void putUserData(Context context, String str, String str2) {
        if (!C4402b.f15202a) {
            Log.w(C4475w.f15774a, "Can not put user data because bugly is disable.");
        } else if (context == null) {
            Log.w(C4475w.f15774a, "putUserData args context should not be null");
        } else if (str == null) {
            str;
            C4475w.d("putUserData args key should not be null or empty", new Object[0]);
        } else if (str2 == null) {
            str2;
            C4475w.d("putUserData args value should not be null", new Object[0]);
        } else if (str.matches("[a-zA-Z[0-9]]+")) {
            if (str2.length() > 200) {
                C4475w.d("user data value length over limit %d, it will be cutted!", new Object[]{Integer.valueOf(200)});
                str2 = str2.substring(0, 200);
            }
            C4417a a = C4417a.a(context);
            NativeCrashHandler instance;
            if (a.C().contains(str)) {
                instance = NativeCrashHandler.getInstance();
                if (instance != null) {
                    instance.putKeyValueToNative(str, str2);
                }
                C4417a.a(context).b(str, str2);
                C4475w.c("replace KV %s %s", new Object[]{str, str2});
            } else if (a.B() >= 10) {
                C4475w.d("user data size is over limit %d, it will be cutted!", new Object[]{Integer.valueOf(10)});
            } else {
                if (str.length() > 50) {
                    C4475w.d("user data key length over limit %d , will drop this new key %s", new Object[]{Integer.valueOf(50), str});
                    str = str.substring(0, 50);
                }
                instance = NativeCrashHandler.getInstance();
                if (instance != null) {
                    instance.putKeyValueToNative(str, str2);
                }
                C4417a.a(context).b(str, str2);
                C4475w.b("[param] set user data: %s - %s", new Object[]{str, str2});
            }
        } else {
            C4475w.d("putUserData args key should match [a-zA-Z[0-9]]+  {" + str + C0880h.f2222d, new Object[0]);
        }
    }

    public static String removeUserData(Context context, String str) {
        if (!C4402b.f15202a) {
            Log.w(C4475w.f15774a, "Can not remove user data because bugly is disable.");
            return EnvironmentCompat.MEDIA_UNKNOWN;
        } else if (context == null) {
            Log.e(C4475w.f15774a, "removeUserData args context should not be null");
            return EnvironmentCompat.MEDIA_UNKNOWN;
        } else if (C4479y.a(str)) {
            return null;
        } else {
            C4475w.b("[param] remove user data: %s", new Object[]{str});
            return C4417a.a(context).f(str);
        }
    }

    public static Set<String> getAllUserDataKeys(Context context) {
        if (!C4402b.f15202a) {
            Log.w(C4475w.f15774a, "Can not get all keys of user data because bugly is disable.");
            return new HashSet();
        } else if (context != null) {
            return C4417a.a(context).C();
        } else {
            Log.e(C4475w.f15774a, "getAllUserDataKeys args context should not be null");
            return new HashSet();
        }
    }

    public static int getUserDatasSize(Context context) {
        if (!C4402b.f15202a) {
            Log.w(C4475w.f15774a, "Can not get size of user data because bugly is disable.");
            return -1;
        } else if (context != null) {
            return C4417a.a(context).B();
        } else {
            Log.e(C4475w.f15774a, "getUserDatasSize args context should not be null");
            return -1;
        }
    }

    public static String getAppID() {
        if (!C4402b.f15202a) {
            Log.w(C4475w.f15774a, "Can not get App ID because bugly is disable.");
            return EnvironmentCompat.MEDIA_UNKNOWN;
        } else if (CrashModule.hasInitialized()) {
            return C4417a.a(f7037a).f();
        } else {
            Log.e(C4475w.f15774a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
    }

    public static void setUserId(String str) {
        if (!C4402b.f15202a) {
            Log.w(C4475w.f15774a, "Can not set user ID because bugly is disable.");
        } else if (CrashModule.hasInitialized()) {
            setUserId(f7037a, str);
        } else {
            Log.e(C4475w.f15774a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        }
    }

    public static void setUserId(Context context, String str) {
        if (!C4402b.f15202a) {
            Log.w(C4475w.f15774a, "Can not set user ID because bugly is disable.");
        } else if (context == null) {
            Log.e(C4475w.f15774a, "Context should not be null when bugly has not been initialed!");
        } else if (str == null) {
            C4475w.d("userId should not be null", new Object[0]);
        } else {
            if (str.length() > 100) {
                C4475w.d("userId %s length is over limit %d substring to %s", new Object[]{str, Integer.valueOf(100), str.substring(0, 100)});
                str = r0;
            }
            if (!str.equals(C4417a.a(context).g())) {
                C4417a.a(context).b(str);
                C4475w.b("[user] set userId : %s", new Object[]{str});
                NativeCrashHandler instance = NativeCrashHandler.getInstance();
                if (instance != null) {
                    instance.setNativeUserId(str);
                }
                if (CrashModule.hasInitialized()) {
                    C4415b.a();
                }
            }
        }
    }

    public static String getUserId() {
        if (!C4402b.f15202a) {
            Log.w(C4475w.f15774a, "Can not get user ID because bugly is disable.");
            return EnvironmentCompat.MEDIA_UNKNOWN;
        } else if (CrashModule.hasInitialized()) {
            return C4417a.a(f7037a).g();
        } else {
            Log.e(C4475w.f15774a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
    }

    public static String getAppVer() {
        if (!C4402b.f15202a) {
            Log.w(C4475w.f15774a, "Can not get app version because bugly is disable.");
            return EnvironmentCompat.MEDIA_UNKNOWN;
        } else if (CrashModule.hasInitialized()) {
            return C4417a.a(f7037a).f15295j;
        } else {
            Log.e(C4475w.f15774a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
    }

    public static String getAppChannel() {
        if (!C4402b.f15202a) {
            Log.w(C4475w.f15774a, "Can not get App channel because bugly is disable.");
            return EnvironmentCompat.MEDIA_UNKNOWN;
        } else if (CrashModule.hasInitialized()) {
            return C4417a.a(f7037a).f15297l;
        } else {
            Log.e(C4475w.f15774a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
    }

    public static void setContext(Context context) {
        f7037a = context;
    }

    public static boolean isLastSessionCrash() {
        if (!C4402b.f15202a) {
            Log.w(C4475w.f15774a, "The info 'isLastSessionCrash' is not accurate because bugly is disable.");
            return false;
        } else if (CrashModule.hasInitialized()) {
            return C4436c.a().b();
        } else {
            Log.e(C4475w.f15774a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return false;
        }
    }

    public static void setSdkExtraData(Context context, String str, String str2) {
        if (!C4402b.f15202a) {
            Log.w(C4475w.f15774a, "Can not put SDK extra data because bugly is disable.");
        } else if (context != null && !C4479y.a(str) && !C4479y.a(str2)) {
            C4417a.a(context).a(str, str2);
        }
    }

    public static Map<String, String> getSdkExtraData() {
        if (!C4402b.f15202a) {
            Log.w(C4475w.f15774a, "Can not get SDK extra data because bugly is disable.");
            return new HashMap();
        } else if (CrashModule.hasInitialized()) {
            return C4417a.a(f7037a).f15261A;
        } else {
            Log.e(C4475w.f15774a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return null;
        }
    }

    public static Map<String, String> getSdkExtraData(Context context) {
        if (!C4402b.f15202a) {
            Log.w(C4475w.f15774a, "Can not get SDK extra data because bugly is disable.");
            return new HashMap();
        } else if (context != null) {
            return C4417a.a(context).f15261A;
        } else {
            C4475w.d("Context should not be null.", new Object[0]);
            return null;
        }
    }

    private static void putSdkData(Context context, String str, String str2) {
        if (context != null && !C4479y.a(str) && !C4479y.a(str2)) {
            String replace = str.replace("[a-zA-Z[0-9]]+", "");
            if (replace.length() > 100) {
                Log.w(C4475w.f15774a, String.format("putSdkData key length over limit %d, will be cutted.", new Object[]{Integer.valueOf(50)}));
                replace = replace.substring(0, 50);
            }
            if (str2.length() > 500) {
                Log.w(C4475w.f15774a, String.format("putSdkData value length over limit %d, will be cutted!", new Object[]{Integer.valueOf(200)}));
                str2 = str2.substring(0, 200);
            }
            C4417a.a(context).c(replace, str2);
            C4475w.b(String.format("[param] putSdkData data: %s - %s", new Object[]{replace, str2}), new Object[0]);
        }
    }

    public static void setIsAppForeground(Context context, boolean z) {
        if (!C4402b.f15202a) {
            Log.w(C4475w.f15774a, "Can not set 'isAppForeground' because bugly is disable.");
        } else if (context == null) {
            C4475w.d("Context should not be null.", new Object[0]);
        } else {
            if (z) {
                C4475w.c("App is in foreground.", new Object[0]);
            } else {
                C4475w.c("App is in background.", new Object[0]);
            }
            C4417a.a(context).a(z);
        }
    }

    public static void setIsDevelopmentDevice(Context context, boolean z) {
        if (!C4402b.f15202a) {
            Log.w(C4475w.f15774a, "Can not set 'isDevelopmentDevice' because bugly is disable.");
        } else if (context == null) {
            C4475w.d("Context should not be null.", new Object[0]);
        } else {
            if (z) {
                C4475w.c("This is a development device.", new Object[0]);
            } else {
                C4475w.c("This is not a development device.", new Object[0]);
            }
            C4417a.a(context).f15310y = z;
        }
    }

    public static void setSessionIntervalMills(long j) {
        if (C4402b.f15202a) {
            C4415b.a(j);
        } else {
            Log.w(C4475w.f15774a, "Can not set 'SessionIntervalMills' because bugly is disable.");
        }
    }

    public static void setAppVersion(Context context, String str) {
        if (!C4402b.f15202a) {
            Log.w(C4475w.f15774a, "Can not set App version because bugly is disable.");
        } else if (context == null) {
            Log.w(C4475w.f15774a, "setAppVersion args context should not be null");
        } else if (str == null) {
            Log.w(C4475w.f15774a, "App version is null, will not set");
        } else {
            C4417a.a(context).f15295j = str;
            NativeCrashHandler instance = NativeCrashHandler.getInstance();
            if (instance != null) {
                instance.setNativeAppVersion(str);
            }
        }
    }

    public static void setAppChannel(Context context, String str) {
        if (!C4402b.f15202a) {
            Log.w(C4475w.f15774a, "Can not set App channel because Bugly is disable.");
        } else if (context == null) {
            Log.w(C4475w.f15774a, "setAppChannel args context should not be null");
        } else if (str == null) {
            Log.w(C4475w.f15774a, "App channel is null, will not set");
        } else {
            C4417a.a(context).f15297l = str;
            NativeCrashHandler instance = NativeCrashHandler.getInstance();
            if (instance != null) {
                instance.setNativeAppChannel(str);
            }
        }
    }

    public static void setAppPackage(Context context, String str) {
        if (!C4402b.f15202a) {
            Log.w(C4475w.f15774a, "Can not set App package because bugly is disable.");
        } else if (context == null) {
            Log.w(C4475w.f15774a, "setAppPackage args context should not be null");
        } else if (str == null) {
            Log.w(C4475w.f15774a, "App package is null, will not set");
        } else {
            C4417a.a(context).f15288c = str;
            NativeCrashHandler instance = NativeCrashHandler.getInstance();
            if (instance != null) {
                instance.setNativeAppPackage(str);
            }
        }
    }

    public static void setCrashFilter(String str) {
        if (C4402b.f15202a) {
            Log.w(C4475w.f15774a, "Set crash stack filter: " + str);
            C4436c.f15461l = str;
            return;
        }
        Log.w(C4475w.f15774a, "Can not set App package because bugly is disable.");
    }

    public static void setCrashRegularFilter(String str) {
        if (C4402b.f15202a) {
            Log.w(C4475w.f15774a, "Set crash stack filter: " + str);
            C4436c.f15462m = str;
            return;
        }
        Log.w(C4475w.f15774a, "Can not set App package because bugly is disable.");
    }

    public static boolean setJavascriptMonitor(WebView webView, boolean z) {
        return setJavascriptMonitor(webView, z, false);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public static boolean setJavascriptMonitor(WebView webView, boolean z, boolean z2) {
        if (webView == null) {
            Log.w(C4475w.f15774a, "Webview is null.");
            return false;
        } else if (CrashModule.hasInitialized()) {
            C4475w.a("Set Javascript exception monitor of webview.", new Object[0]);
            if (C4402b.f15202a) {
                C4475w.c("URL of webview is %s", new Object[]{webView.getUrl()});
                if (webView.getUrl() == null) {
                    return false;
                }
                if (z2 || VERSION.SDK_INT >= 19) {
                    WebSettings settings = webView.getSettings();
                    if (!settings.getJavaScriptEnabled()) {
                        C4475w.a("Enable the javascript needed by webview monitor.", new Object[0]);
                        settings.setJavaScriptEnabled(true);
                    }
                    H5JavaScriptInterface instance = H5JavaScriptInterface.getInstance(webView);
                    if (instance != null) {
                        C4475w.a("Add a secure javascript interface to the webview.", new Object[0]);
                        webView.addJavascriptInterface(instance, "exceptionUploader");
                    }
                    if (z) {
                        C4475w.a("Inject bugly.js(v%s) to the webview.", new Object[]{C4442b.b()});
                        String a = C4442b.a();
                        if (a == null) {
                            C4475w.e("Failed to inject Bugly.js.", new Object[]{C4442b.b()});
                            return false;
                        }
                        webView.loadUrl("javascript:" + a);
                    }
                    return true;
                }
                C4475w.e("This interface is only available for Android 4.4 or later.", new Object[0]);
                return false;
            }
            Log.w(C4475w.f15774a, "Can not set JavaScript monitor because bugly is disable.");
            return false;
        } else {
            C4475w.e("CrashReport has not been initialed! please to call method 'initCrashReport' first!", new Object[0]);
            return false;
        }
    }
}
