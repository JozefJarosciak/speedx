package com.tencent.bugly.crashreport.biz;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.os.EnvironmentCompat;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.biz.C4412a.C44082;
import com.tencent.bugly.crashreport.biz.C4412a.C4409a;
import com.tencent.bugly.crashreport.biz.C4412a.C4411c;
import com.tencent.bugly.crashreport.common.info.C4417a;
import com.tencent.bugly.crashreport.common.strategy.C4421a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.proguard.C4474v;
import com.tencent.bugly.proguard.C4475w;
import com.tencent.bugly.proguard.C4479y;
import java.util.List;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.crashreport.biz.b */
public class C4415b {
    /* renamed from: a */
    public static C4412a f15243a;
    /* renamed from: b */
    private static boolean f15244b;
    /* renamed from: c */
    private static int f15245c = 10;
    /* renamed from: d */
    private static long f15246d = 300000;
    /* renamed from: e */
    private static long f15247e = 30000;
    /* renamed from: f */
    private static long f15248f = 0;
    /* renamed from: g */
    private static int f15249g;
    /* renamed from: h */
    private static long f15250h;
    /* renamed from: i */
    private static long f15251i;
    /* renamed from: j */
    private static long f15252j = 0;
    /* renamed from: k */
    private static ActivityLifecycleCallbacks f15253k = null;
    /* renamed from: l */
    private static Class<?> f15254l = null;
    /* renamed from: m */
    private static boolean f15255m = true;

    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.crashreport.biz.b$2 */
    static class C44142 implements ActivityLifecycleCallbacks {
        C44142() {
        }

        public final void onActivityStopped(Activity activity) {
        }

        public final void onActivityStarted(Activity activity) {
        }

        public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public final void onActivityResumed(Activity activity) {
            String str = EnvironmentCompat.MEDIA_UNKNOWN;
            if (activity != null) {
                str = activity.getClass().getName();
            }
            if (C4415b.f15254l == null || C4415b.f15254l.getName().equals(str)) {
                C4475w.m17751c(">>> %s onResumed <<<", str);
                C4417a b = C4417a.m17304b();
                if (b != null) {
                    b.f15262B.add(C4415b.m17271a(str, "onResumed"));
                    b.m17324a(true);
                    b.f15301p = str;
                    b.f15302q = System.currentTimeMillis();
                    b.f15305t = b.f15302q - C4415b.f15251i;
                    if (b.f15302q - C4415b.f15250h > (C4415b.f15248f > 0 ? C4415b.f15248f : C4415b.f15247e)) {
                        b.m17331d();
                        C4415b.m17286g();
                        C4475w.m17747a("[session] launch app one times (app in background %d seconds and over %d seconds)", Long.valueOf(r4 / 1000), Long.valueOf(C4415b.f15247e / 1000));
                        if (C4415b.f15249g % C4415b.f15245c == 0) {
                            C4415b.f15243a.m17269a(4, C4415b.f15255m, 0);
                            return;
                        }
                        C4415b.f15243a.m17269a(4, false, 0);
                        long currentTimeMillis = System.currentTimeMillis();
                        if (currentTimeMillis - C4415b.f15252j > C4415b.f15246d) {
                            C4415b.f15252j = currentTimeMillis;
                            C4475w.m17747a("add a timer to upload hot start user info", new Object[0]);
                            if (C4415b.f15255m) {
                                C4474v.m17740a().m17742a(new C4409a(C4415b.f15243a, null, true), C4415b.f15246d);
                            }
                        }
                    }
                }
            }
        }

        public final void onActivityPaused(Activity activity) {
            String str = EnvironmentCompat.MEDIA_UNKNOWN;
            if (activity != null) {
                str = activity.getClass().getName();
            }
            if (C4415b.f15254l == null || C4415b.f15254l.getName().equals(str)) {
                C4475w.m17751c(">>> %s onPaused <<<", str);
                C4417a b = C4417a.m17304b();
                if (b != null) {
                    b.f15262B.add(C4415b.m17271a(str, "onPaused"));
                    b.m17324a(false);
                    b.f15303r = System.currentTimeMillis();
                    b.f15304s = b.f15303r - b.f15302q;
                    C4415b.f15250h = b.f15303r;
                    if (b.f15304s < 0) {
                        b.f15304s = 0;
                    }
                    if (activity != null) {
                        b.f15301p = "background";
                    } else {
                        b.f15301p = EnvironmentCompat.MEDIA_UNKNOWN;
                    }
                }
            }
        }

        public final void onActivityDestroyed(Activity activity) {
        }

        public final void onActivityCreated(Activity activity, Bundle bundle) {
            String str = EnvironmentCompat.MEDIA_UNKNOWN;
            if (activity != null) {
                str = activity.getClass().getName();
            }
            if (C4415b.f15254l == null || C4415b.f15254l.getName().equals(str)) {
                C4475w.m17751c(">>> %s onCreated <<<", str);
                C4417a b = C4417a.m17304b();
                if (b != null) {
                    b.f15262B.add(C4415b.m17271a(str, "onCreated"));
                }
            }
        }
    }

    /* renamed from: a */
    static /* synthetic */ String m17271a(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(C4479y.m17777a());
        stringBuilder.append("  ");
        stringBuilder.append(str);
        stringBuilder.append("  ");
        stringBuilder.append(str2);
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    /* renamed from: g */
    static /* synthetic */ int m17286g() {
        int i = f15249g;
        f15249g = i + 1;
        return i;
    }

    /* renamed from: c */
    private static void m17282c(Context context, BuglyStrategy buglyStrategy) {
        boolean isEnableUserInfo;
        boolean z;
        if (buglyStrategy != null) {
            boolean recordUserInfoOnceADay = buglyStrategy.recordUserInfoOnceADay();
            isEnableUserInfo = buglyStrategy.isEnableUserInfo();
            z = recordUserInfoOnceADay;
        } else {
            isEnableUserInfo = true;
            z = false;
        }
        if (z) {
            Object obj;
            C4417a a = C4417a.m17303a(context);
            List a2 = f15243a.m17267a(a.f15289d);
            if (a2 != null) {
                for (int i = 0; i < a2.size(); i++) {
                    UserInfoBean userInfoBean = (UserInfoBean) a2.get(i);
                    if (userInfoBean.f15222n.equals(a.f15295j) && userInfoBean.f15210b == 1) {
                        long b = C4479y.m17800b();
                        if (b <= 0) {
                            break;
                        } else if (userInfoBean.f15213e >= b) {
                            if (userInfoBean.f15214f <= 0) {
                                C4412a c4412a = f15243a;
                                C4474v a3 = C4474v.m17740a();
                                if (a3 != null) {
                                    a3.m17741a(new C44082(c4412a));
                                }
                            }
                            obj = null;
                            if (obj == null) {
                                isEnableUserInfo = false;
                            } else {
                                return;
                            }
                        }
                    }
                }
            }
            obj = 1;
            if (obj == null) {
                isEnableUserInfo = false;
            } else {
                return;
            }
        }
        C4417a b2 = C4417a.m17304b();
        if (b2 != null) {
            Object obj2 = null;
            String str = null;
            for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
                if (stackTraceElement.getMethodName().equals("onCreate")) {
                    str = stackTraceElement.getClassName();
                }
                if (stackTraceElement.getClassName().equals("android.app.Activity")) {
                    obj2 = 1;
                }
            }
            if (str == null) {
                str = EnvironmentCompat.MEDIA_UNKNOWN;
            } else if (obj2 != null) {
                b2.m17324a(true);
            } else {
                str = "background";
            }
            b2.f15301p = str;
        }
        if (isEnableUserInfo) {
            Application application = null;
            if (VERSION.SDK_INT >= 14) {
                if (context.getApplicationContext() instanceof Application) {
                    application = (Application) context.getApplicationContext();
                }
                if (application != null) {
                    try {
                        if (f15253k == null) {
                            f15253k = new C44142();
                        }
                        application.registerActivityLifecycleCallbacks(f15253k);
                    } catch (Throwable e) {
                        if (!C4475w.m17748a(e)) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        if (f15255m) {
            f15251i = System.currentTimeMillis();
            f15243a.m17269a(1, false, 0);
            C4475w.m17747a("[session] launch app, new start", new Object[0]);
            f15243a.m17268a();
            C4474v.m17740a().m17742a(new C4411c(f15243a, 21600000), 21600000);
        }
    }

    /* renamed from: a */
    public static void m17275a(final Context context, final BuglyStrategy buglyStrategy) {
        if (!f15244b) {
            long appReportDelay;
            f15255m = C4417a.m17303a(context).f15290e;
            f15243a = new C4412a(context, f15255m);
            f15244b = true;
            if (buglyStrategy != null) {
                f15254l = buglyStrategy.getUserInfoActivity();
                appReportDelay = buglyStrategy.getAppReportDelay();
            } else {
                appReportDelay = 0;
            }
            if (appReportDelay <= 0) {
                C4415b.m17282c(context, buglyStrategy);
            } else {
                C4474v.m17740a().m17742a(new Runnable() {
                    public final void run() {
                        C4415b.m17282c(context, buglyStrategy);
                    }
                }, appReportDelay);
            }
        }
    }

    /* renamed from: a */
    public static void m17273a(long j) {
        if (j < 0) {
            j = C4421a.m17388a().m17396c().f15330q;
        }
        f15248f = j;
    }

    /* renamed from: a */
    public static void m17276a(StrategyBean strategyBean, boolean z) {
        if (!(f15243a == null || z)) {
            C4412a c4412a = f15243a;
            C4474v a = C4474v.m17740a();
            if (a != null) {
                a.m17741a(new C44082(c4412a));
            }
        }
        if (strategyBean != null) {
            if (strategyBean.f15330q > 0) {
                f15247e = strategyBean.f15330q;
            }
            if (strategyBean.f15336w > 0) {
                f15245c = strategyBean.f15336w;
            }
            if (strategyBean.f15337x > 0) {
                f15246d = strategyBean.f15337x;
            }
        }
    }

    /* renamed from: a */
    public static void m17272a() {
        if (f15243a != null) {
            f15243a.m17269a(2, false, 0);
        }
    }

    /* renamed from: a */
    public static void m17274a(Context context) {
        if (f15244b && context != null) {
            Application application = null;
            if (VERSION.SDK_INT >= 14) {
                if (context.getApplicationContext() instanceof Application) {
                    application = (Application) context.getApplicationContext();
                }
                if (application != null) {
                    try {
                        if (f15253k != null) {
                            application.unregisterActivityLifecycleCallbacks(f15253k);
                        }
                    } catch (Throwable e) {
                        if (!C4475w.m17748a(e)) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            f15244b = false;
        }
    }
}
