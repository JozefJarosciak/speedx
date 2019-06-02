package com.facebook;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.support.v4.media.session.PlaybackStateCompat;
import com.facebook.internal.BoltsMeasurementEventListener;
import com.facebook.internal.C3024l;
import com.facebook.internal.C3035o;
import com.facebook.internal.C3048s;
import com.facebook.internal.C3049t;
import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: FacebookSdk */
/* renamed from: com.facebook.c */
public final class C1472c {
    /* renamed from: a */
    private static final String f6897a = C1472c.class.getCanonicalName();
    /* renamed from: b */
    private static final HashSet<LoggingBehavior> f6898b = new HashSet(Arrays.asList(new LoggingBehavior[]{LoggingBehavior.DEVELOPER_ERRORS}));
    /* renamed from: c */
    private static volatile Executor f6899c;
    /* renamed from: d */
    private static volatile String f6900d;
    /* renamed from: e */
    private static volatile String f6901e;
    /* renamed from: f */
    private static volatile String f6902f;
    /* renamed from: g */
    private static volatile int f6903g;
    /* renamed from: h */
    private static volatile String f6904h = "facebook.com";
    /* renamed from: i */
    private static AtomicLong f6905i = new AtomicLong(PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH);
    /* renamed from: j */
    private static volatile boolean f6906j = false;
    /* renamed from: k */
    private static boolean f6907k = false;
    /* renamed from: l */
    private static C3024l<File> f6908l;
    /* renamed from: m */
    private static Context f6909m;
    /* renamed from: n */
    private static int f6910n = 64206;
    /* renamed from: o */
    private static final Object f6911o = new Object();
    /* renamed from: p */
    private static final BlockingQueue<Runnable> f6912p = new LinkedBlockingQueue(10);
    /* renamed from: q */
    private static final ThreadFactory f6913q = new c$1();
    /* renamed from: r */
    private static Boolean f6914r = Boolean.valueOf(false);

    /* renamed from: a */
    public static synchronized void m8114a(Context context) {
        synchronized (C1472c.class) {
            C1472c.m8115a(context, null);
        }
    }

    /* renamed from: a */
    public static synchronized void m8115a(Context context, c$a c_a) {
        synchronized (C1472c.class) {
            if (!f6914r.booleanValue()) {
                C3049t.a(context, "applicationContext");
                C3049t.b(context, false);
                C3049t.a(context, false);
                f6909m = context.getApplicationContext();
                C1472c.m8120c(f6909m);
                f6914r = Boolean.valueOf(true);
                C3048s.a(f6909m, f6900d);
                C3035o.b();
                BoltsMeasurementEventListener.a(f6909m);
                f6908l = new C3024l(new c$2());
                C1472c.m8122d().execute(new FutureTask(new c$3(c_a, context)));
            } else if (c_a != null) {
                c_a.a();
            }
        }
    }

    /* renamed from: a */
    public static synchronized boolean m8116a() {
        boolean booleanValue;
        synchronized (C1472c.class) {
            booleanValue = f6914r.booleanValue();
        }
        return booleanValue;
    }

    /* renamed from: a */
    public static boolean m8117a(LoggingBehavior loggingBehavior) {
        boolean z;
        synchronized (f6898b) {
            z = C1472c.m8118b() && f6898b.contains(loggingBehavior);
        }
        return z;
    }

    /* renamed from: b */
    public static boolean m8118b() {
        return f6906j;
    }

    /* renamed from: c */
    public static boolean m8121c() {
        return f6907k;
    }

    /* renamed from: d */
    public static Executor m8122d() {
        synchronized (f6911o) {
            if (f6899c == null) {
                f6899c = AsyncTask.THREAD_POOL_EXECUTOR;
            }
        }
        return f6899c;
    }

    /* renamed from: e */
    public static String m8123e() {
        return f6904h;
    }

    /* renamed from: f */
    public static Context m8124f() {
        C3049t.a();
        return f6909m;
    }

    /* renamed from: g */
    public static String m8125g() {
        return "4.14.0";
    }

    /* renamed from: b */
    public static boolean m8119b(Context context) {
        C3049t.a();
        return context.getSharedPreferences("com.facebook.sdk.appEventPreferences", 0).getBoolean("limitEventUsage", false);
    }

    /* renamed from: h */
    public static long m8126h() {
        C3049t.a();
        return f6905i.get();
    }

    /* renamed from: c */
    static void m8120c(Context context) {
        if (context != null) {
            try {
                ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
                if (applicationInfo != null && applicationInfo.metaData != null) {
                    if (f6900d == null) {
                        Object obj = applicationInfo.metaData.get("com.facebook.sdk.ApplicationId");
                        if (obj instanceof String) {
                            String str = (String) obj;
                            if (str.toLowerCase(Locale.ROOT).startsWith("fb")) {
                                f6900d = str.substring(2);
                            } else {
                                f6900d = str;
                            }
                        } else if (obj instanceof Integer) {
                            throw new FacebookException("App Ids cannot be directly placed in the manifest.They must be prefixed by 'fb' or be placed in the string resource file.");
                        }
                    }
                    if (f6901e == null) {
                        f6901e = applicationInfo.metaData.getString("com.facebook.sdk.ApplicationName");
                    }
                    if (f6902f == null) {
                        f6902f = applicationInfo.metaData.getString("com.facebook.sdk.ClientToken");
                    }
                    if (f6903g == 0) {
                        C1472c.m8113a(applicationInfo.metaData.getInt("com.facebook.sdk.WebDialogTheme"));
                    }
                }
            } catch (NameNotFoundException e) {
            }
        }
    }

    /* renamed from: i */
    public static String m8127i() {
        C3049t.a();
        return f6900d;
    }

    /* renamed from: j */
    public static String m8128j() {
        C3049t.a();
        return f6901e;
    }

    /* renamed from: k */
    public static String m8129k() {
        C3049t.a();
        return f6902f;
    }

    /* renamed from: l */
    public static int m8130l() {
        C3049t.a();
        return f6903g;
    }

    /* renamed from: a */
    public static void m8113a(int i) {
        if (i == 0) {
            i = 16973840;
        }
        f6903g = i;
    }

    /* renamed from: m */
    public static int m8131m() {
        C3049t.a();
        return f6910n;
    }
}
