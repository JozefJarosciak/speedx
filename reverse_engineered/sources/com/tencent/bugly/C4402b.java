package com.tencent.bugly;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.bugly.crashreport.biz.C4415b;
import com.tencent.bugly.crashreport.common.info.C4417a;
import com.tencent.bugly.crashreport.common.strategy.C4421a;
import com.tencent.bugly.proguard.C4461m;
import com.tencent.bugly.proguard.C4464o;
import com.tencent.bugly.proguard.C4471t;
import com.tencent.bugly.proguard.C4475w;
import com.tencent.bugly.proguard.C4478x;
import com.tencent.bugly.proguard.C4479y;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.b */
public final class C4402b {
    /* renamed from: a */
    public static boolean f15202a = true;
    /* renamed from: b */
    public static List<C4401a> f15203b = new ArrayList();
    /* renamed from: c */
    public static boolean f15204c;
    /* renamed from: d */
    private static C4464o f15205d;
    /* renamed from: e */
    private static C4421a f15206e;
    /* renamed from: f */
    private static C4461m f15207f;
    /* renamed from: g */
    private static boolean f15208g;

    /* renamed from: a */
    private static boolean m17251a(C4417a c4417a) {
        List list = c4417a.f15300o;
        c4417a.getClass();
        String str = "bugly";
        if (list == null || !list.contains(str)) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    public static synchronized void m17247a(Context context) {
        synchronized (C4402b.class) {
            C4402b.m17248a(context, null);
        }
    }

    /* renamed from: a */
    public static synchronized void m17248a(Context context, BuglyStrategy buglyStrategy) {
        synchronized (C4402b.class) {
            if (f15208g) {
                C4475w.m17752d("[init] initial Multi-times, ignore this.", new Object[0]);
            } else if (context == null) {
                Log.w(C4475w.f15774a, "[init] context of init() is null, check it.");
            } else {
                C4417a a = C4417a.m17303a(context);
                if (C4402b.m17251a(a)) {
                    f15202a = false;
                } else {
                    String f = a.m17335f();
                    if (f == null) {
                        Log.e(C4475w.f15774a, "[init] meta data of BUGLY_APPID in AndroidManifest.xml should be set.");
                    } else {
                        C4402b.m17249a(context, f, a.f15306u, buglyStrategy);
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public static synchronized void m17249a(Context context, String str, boolean z, BuglyStrategy buglyStrategy) {
        synchronized (C4402b.class) {
            if (f15208g) {
                C4475w.m17752d("[init] initial Multi-times, ignore this.", new Object[0]);
            } else if (context == null) {
                Log.w(C4475w.f15774a, "[init] context is null, check it.");
            } else if (str == null) {
                Log.e(C4475w.f15774a, "init arg 'crashReportAppID' should not be null!");
            } else {
                f15208g = true;
                if (z) {
                    f15204c = true;
                    C4475w.f15775b = true;
                    C4475w.m17752d("Bugly debug模式开启，请在发布时把isDebug关闭。 -- Running in debug model for 'isDebug' is enabled. Please disable it when you release.", new Object[0]);
                    C4475w.m17753e("--------------------------------------------------------------------------------------------", new Object[0]);
                    C4475w.m17752d("Bugly debug模式将有以下行为特性 -- The following list shows the behaviour of debug model: ", new Object[0]);
                    C4475w.m17752d("[1] 输出详细的Bugly SDK的Log -- More detailed log of Bugly SDK will be output to logcat;", new Object[0]);
                    C4475w.m17752d("[2] 每一条Crash都会被立即上报 -- Every crash caught by Bugly will be uploaded immediately.", new Object[0]);
                    C4475w.m17752d("[3] 自定义日志将会在Logcat中输出 -- Custom log will be output to logcat.", new Object[0]);
                    C4475w.m17753e("--------------------------------------------------------------------------------------------", new Object[0]);
                    C4475w.m17749b("[init] Open debug mode of Bugly.", new Object[0]);
                }
                C4475w.m17747a("[init] Bugly version: v%s", "2.4.0");
                C4475w.m17747a(" crash report start initializing...", new Object[0]);
                C4475w.m17749b("[init] Bugly start initializing...", new Object[0]);
                C4475w.m17747a("[init] Bugly complete version: v%s", "2.4.0(1.2.1)");
                Context a = C4479y.m17772a(context);
                C4417a a2 = C4417a.m17303a(a);
                a2.m17351t();
                C4478x.m17763a(a);
                f15205d = C4464o.m17673a(a, f15203b);
                C4471t.m17704a(a);
                f15206e = C4421a.m17389a(a, f15203b);
                f15207f = C4461m.m17649a(a);
                if (C4402b.m17251a(a2)) {
                    f15202a = false;
                } else {
                    a2.m17322a(str);
                    C4475w.m17747a("[param] Set APP ID:%s", str);
                    if (buglyStrategy != null) {
                        String substring;
                        String appVersion = buglyStrategy.getAppVersion();
                        if (!TextUtils.isEmpty(appVersion)) {
                            if (appVersion.length() > 100) {
                                substring = appVersion.substring(0, 100);
                                C4475w.m17752d("appVersion %s length is over limit %d substring to %s", appVersion, Integer.valueOf(100), substring);
                            } else {
                                substring = appVersion;
                            }
                            a2.f15295j = substring;
                            C4475w.m17747a("[param] Set App version: %s", buglyStrategy.getAppVersion());
                        }
                        try {
                            if (buglyStrategy.isReplaceOldChannel()) {
                                appVersion = buglyStrategy.getAppChannel();
                                if (!TextUtils.isEmpty(appVersion)) {
                                    String str2;
                                    if (appVersion.length() > 100) {
                                        C4475w.m17752d("appChannel %s length is over limit %d substring to %s", appVersion, Integer.valueOf(100), appVersion.substring(0, 100));
                                        str2 = substring;
                                    } else {
                                        str2 = appVersion;
                                    }
                                    f15205d.m17693a(556, "app_channel", str2.getBytes(), null, false);
                                    a2.f15297l = str2;
                                }
                            } else {
                                Map a3 = f15205d.m17690a(556, null, true);
                                if (a3 != null) {
                                    byte[] bArr = (byte[]) a3.get("app_channel");
                                    if (bArr != null) {
                                        a2.f15297l = new String(bArr);
                                    }
                                }
                            }
                            C4475w.m17747a("[param] Set App channel: %s", a2.f15297l);
                        } catch (Exception e) {
                            if (f15204c) {
                                e.printStackTrace();
                            }
                        }
                        appVersion = buglyStrategy.getAppPackageName();
                        if (!TextUtils.isEmpty(appVersion)) {
                            if (appVersion.length() > 100) {
                                substring = appVersion.substring(0, 100);
                                C4475w.m17752d("appPackageName %s length is over limit %d substring to %s", appVersion, Integer.valueOf(100), substring);
                            } else {
                                substring = appVersion;
                            }
                            a2.f15288c = substring;
                            C4475w.m17747a("[param] Set App package: %s", buglyStrategy.getAppPackageName());
                        }
                        appVersion = buglyStrategy.getDeviceID();
                        if (appVersion != null) {
                            if (appVersion.length() > 100) {
                                substring = appVersion.substring(0, 100);
                                C4475w.m17752d("deviceId %s length is over limit %d substring to %s", appVersion, Integer.valueOf(100), substring);
                            } else {
                                substring = appVersion;
                            }
                            a2.m17329c(substring);
                            C4475w.m17747a("s[param] Set device ID: %s", substring);
                        }
                        a2.f15290e = buglyStrategy.isUploadProcess();
                        C4478x.f15783a = buglyStrategy.isBuglyLogUpload();
                    }
                    C4415b.m17275a(a, buglyStrategy);
                    for (int i = 0; i < f15203b.size(); i++) {
                        try {
                            if (f15207f.m17660a(((C4401a) f15203b.get(i)).id)) {
                                ((C4401a) f15203b.get(i)).init(a, z, buglyStrategy);
                            }
                        } catch (Throwable th) {
                            if (!C4475w.m17748a(th)) {
                                th.printStackTrace();
                            }
                        }
                    }
                    f15206e.m17392a(buglyStrategy != null ? buglyStrategy.getAppReportDelay() : 0);
                    C4475w.m17749b("[init] Bugly initialization finished.", new Object[0]);
                }
            }
        }
    }

    /* renamed from: a */
    public static synchronized void m17250a(C4401a c4401a) {
        synchronized (C4402b.class) {
            if (!f15203b.contains(c4401a)) {
                f15203b.add(c4401a);
            }
        }
    }
}
