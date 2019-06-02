package com.tencent.bugly;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.bugly.crashreport.common.info.C4417a;
import com.tencent.bugly.proguard.C4464o;
import com.tencent.bugly.proguard.C4475w;
import com.tencent.bugly.proguard.C4479y;
import java.util.Map;

/* compiled from: BUGLY */
public class Bugly {
    public static final String SDK_IS_DEV = "false";
    /* renamed from: a */
    private static boolean f15194a;
    public static Context applicationContext = null;
    /* renamed from: b */
    private static String[] f15195b = new String[]{"BuglyCrashModule", "BuglyRqdModule", "BuglyBetaModule"};
    /* renamed from: c */
    private static String[] f15196c = new String[]{"BuglyRqdModule", "BuglyCrashModule", "BuglyBetaModule"};
    public static boolean enable = true;
    public static Boolean isDev;

    public static void init(Context context, String str, boolean z) {
        init(context, str, z, null);
    }

    public static synchronized void init(Context context, String str, boolean z, BuglyStrategy buglyStrategy) {
        synchronized (Bugly.class) {
            if (!f15194a) {
                f15194a = true;
                Context a = C4479y.m17772a(context);
                applicationContext = a;
                if (a == null) {
                    Log.e(C4475w.f15774a, "init arg 'context' should not be null!");
                } else {
                    if (isDev()) {
                        f15195b = f15196c;
                    }
                    for (String str2 : f15195b) {
                        try {
                            if (str2.equals("BuglyCrashModule")) {
                                C4402b.m17250a(CrashModule.getInstance());
                            } else if (!(str2.equals("BuglyBetaModule") || str2.equals("BuglyRqdModule"))) {
                                str2.equals("BuglyFeedbackModule");
                            }
                        } catch (Throwable th) {
                            C4475w.m17750b(th);
                        }
                    }
                    C4402b.f15202a = enable;
                    C4402b.m17249a(applicationContext, str, z, buglyStrategy);
                }
            }
        }
    }

    public static synchronized String getAppChannel() {
        String str = null;
        synchronized (Bugly.class) {
            C4417a b = C4417a.m17304b();
            if (b != null) {
                if (TextUtils.isEmpty(b.f15297l)) {
                    C4464o a = C4464o.m17672a();
                    if (a == null) {
                        str = b.f15297l;
                    } else {
                        Map a2 = a.m17690a(556, null, true);
                        if (a2 != null) {
                            byte[] bArr = (byte[]) a2.get("app_channel");
                            if (bArr != null) {
                                str = new String(bArr);
                            }
                        }
                    }
                }
                str = b.f15297l;
            }
        }
        return str;
    }

    public static boolean isDev() {
        if (isDev == null) {
            isDev = Boolean.valueOf(Boolean.parseBoolean("false".replace("@", "")));
        }
        return isDev.booleanValue();
    }
}
