package com.facebook.internal;

import android.util.Log;
import com.avos.avoscloud.AnalyticsEvent;
import com.facebook.C1472c;
import com.facebook.LoggingBehavior;
import java.util.HashMap;
import java.util.Map.Entry;

/* compiled from: Logger */
/* renamed from: com.facebook.internal.m */
public class C3025m {
    /* renamed from: a */
    private static final HashMap<String, String> f13579a = new HashMap();
    /* renamed from: b */
    private final LoggingBehavior f13580b;
    /* renamed from: c */
    private final String f13581c;
    /* renamed from: d */
    private StringBuilder f13582d;
    /* renamed from: e */
    private int f13583e = 3;

    /* renamed from: a */
    public static synchronized void m14622a(String str, String str2) {
        synchronized (C3025m.class) {
            f13579a.put(str, str2);
        }
    }

    /* renamed from: a */
    public static synchronized void m14621a(String str) {
        synchronized (C3025m.class) {
            if (!C1472c.a(LoggingBehavior.INCLUDE_ACCESS_TOKENS)) {
                C3025m.m14622a(str, "ACCESS_TOKEN_REMOVED");
            }
        }
    }

    /* renamed from: a */
    public static void m14619a(LoggingBehavior loggingBehavior, String str, String str2) {
        C3025m.m14617a(loggingBehavior, 3, str, str2);
    }

    /* renamed from: a */
    public static void m14620a(LoggingBehavior loggingBehavior, String str, String str2, Object... objArr) {
        if (C1472c.a(loggingBehavior)) {
            C3025m.m14617a(loggingBehavior, 3, str, String.format(str2, objArr));
        }
    }

    /* renamed from: a */
    public static void m14618a(LoggingBehavior loggingBehavior, int i, String str, String str2, Object... objArr) {
        if (C1472c.a(loggingBehavior)) {
            C3025m.m14617a(loggingBehavior, i, str, String.format(str2, objArr));
        }
    }

    /* renamed from: a */
    public static void m14617a(LoggingBehavior loggingBehavior, int i, String str, String str2) {
        if (C1472c.a(loggingBehavior)) {
            String d = C3025m.m14624d(str2);
            if (!str.startsWith("FacebookSDK.")) {
                str = "FacebookSDK." + str;
            }
            Log.println(i, str, d);
            if (loggingBehavior == LoggingBehavior.DEVELOPER_ERRORS) {
                new Exception().printStackTrace();
            }
        }
    }

    /* renamed from: d */
    private static synchronized String m14624d(String str) {
        synchronized (C3025m.class) {
            for (Entry entry : f13579a.entrySet()) {
                str = str.replace((CharSequence) entry.getKey(), (CharSequence) entry.getValue());
            }
        }
        return str;
    }

    public C3025m(LoggingBehavior loggingBehavior, String str) {
        C3049t.m14791a(str, AnalyticsEvent.labelTag);
        this.f13580b = loggingBehavior;
        this.f13581c = "FacebookSDK." + str;
        this.f13582d = new StringBuilder();
    }

    /* renamed from: a */
    public void m14625a() {
        m14628b(this.f13582d.toString());
        this.f13582d = new StringBuilder();
    }

    /* renamed from: b */
    public void m14628b(String str) {
        C3025m.m14617a(this.f13580b, this.f13583e, this.f13581c, str);
    }

    /* renamed from: c */
    public void m14629c(String str) {
        if (m14623b()) {
            this.f13582d.append(str);
        }
    }

    /* renamed from: a */
    public void m14627a(String str, Object... objArr) {
        if (m14623b()) {
            this.f13582d.append(String.format(str, objArr));
        }
    }

    /* renamed from: a */
    public void m14626a(String str, Object obj) {
        m14627a("  %s:\t%s\n", str, obj);
    }

    /* renamed from: b */
    private boolean m14623b() {
        return C1472c.a(this.f13580b);
    }
}
