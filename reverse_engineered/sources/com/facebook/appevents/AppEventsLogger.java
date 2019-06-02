package com.facebook.appevents;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.facebook.AccessToken;
import com.facebook.C1472c;
import com.facebook.FacebookException;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.p178a.C2963a;
import com.facebook.internal.C3025m;
import com.facebook.internal.C3048s;
import com.facebook.internal.C3049t;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;

public class AppEventsLogger {
    /* renamed from: a */
    private static final String f13432a = AppEventsLogger.class.getCanonicalName();
    /* renamed from: d */
    private static ScheduledThreadPoolExecutor f13433d;
    /* renamed from: e */
    private static FlushBehavior f13434e = FlushBehavior.AUTO;
    /* renamed from: f */
    private static Object f13435f = new Object();
    /* renamed from: g */
    private static String f13436g;
    /* renamed from: h */
    private static boolean f13437h;
    /* renamed from: i */
    private static String f13438i;
    /* renamed from: b */
    private final String f13439b;
    /* renamed from: c */
    private final AccessTokenAppIdPair f13440c;

    /* renamed from: com.facebook.appevents.AppEventsLogger$1 */
    static class C29611 implements Runnable {
        C29611() {
        }

        public void run() {
            Set<String> hashSet = new HashSet();
            for (AccessTokenAppIdPair applicationId : C2972b.m14430a()) {
                hashSet.add(applicationId.getApplicationId());
            }
            for (String a : hashSet) {
                C3048s.m14727a(a, true);
            }
        }
    }

    public enum FlushBehavior {
        AUTO,
        EXPLICIT_ONLY
    }

    /* renamed from: a */
    public static AppEventsLogger m14408a(Context context) {
        return new AppEventsLogger(context, null, null);
    }

    /* renamed from: a */
    public static AppEventsLogger m14409a(Context context, String str) {
        return new AppEventsLogger(context, str, null);
    }

    /* renamed from: a */
    public static FlushBehavior m14407a() {
        FlushBehavior flushBehavior;
        synchronized (f13435f) {
            flushBehavior = f13434e;
        }
        return flushBehavior;
    }

    /* renamed from: a */
    public void m14415a(String str, Bundle bundle) {
        m14411a(str, null, bundle, false, C2963a.m14418a());
    }

    /* renamed from: b */
    public void m14417b() {
        C2972b.m14434a(FlushReason.EXPLICIT);
    }

    /* renamed from: c */
    static String m14413c() {
        String str;
        synchronized (f13435f) {
            str = f13438i;
        }
        return str;
    }

    /* renamed from: a */
    public void m14416a(String str, Double d, Bundle bundle) {
        m14411a(str, d, bundle, true, C2963a.m14418a());
    }

    private AppEventsLogger(Context context, String str, AccessToken accessToken) {
        this(C3048s.m14773c(context), str, accessToken);
    }

    protected AppEventsLogger(String str, String str2, AccessToken accessToken) {
        C3049t.m14787a();
        this.f13439b = str;
        if (accessToken == null) {
            accessToken = AccessToken.m14270a();
        }
        if (accessToken == null || !(str2 == null || str2.equals(accessToken.m14283h()))) {
            if (str2 == null) {
                str2 = C3048s.m14732a(C1472c.f());
            }
            this.f13440c = new AccessTokenAppIdPair(null, str2);
        } else {
            this.f13440c = new AccessTokenAppIdPair(accessToken);
        }
        m14414d();
    }

    /* renamed from: d */
    private static void m14414d() {
        synchronized (f13435f) {
            if (f13433d != null) {
                return;
            }
            f13433d = new ScheduledThreadPoolExecutor(1);
            f13433d.scheduleAtFixedRate(new C29611(), 0, 86400, TimeUnit.SECONDS);
        }
    }

    /* renamed from: a */
    private void m14411a(String str, Double d, Bundle bundle, boolean z, @Nullable UUID uuid) {
        try {
            m14410a(C1472c.f(), new AppEvent(this.f13439b, str, d, bundle, z, uuid), this.f13440c);
        } catch (JSONException e) {
            C3025m.m14620a(LoggingBehavior.APP_EVENTS, "AppEvents", "JSON encoding for app event failed: '%s'", e.toString());
        } catch (FacebookException e2) {
            C3025m.m14620a(LoggingBehavior.APP_EVENTS, "AppEvents", "Invalid app event: %s", e2.toString());
        }
    }

    /* renamed from: a */
    private static void m14410a(Context context, AppEvent appEvent, AccessTokenAppIdPair accessTokenAppIdPair) {
        C2972b.m14433a(accessTokenAppIdPair, appEvent);
        if (!appEvent.getIsImplicit() && !f13437h) {
            if (appEvent.getName() == "fb_mobile_activate_app") {
                f13437h = true;
            } else {
                C3025m.m14619a(LoggingBehavior.APP_EVENTS, "AppEvents", "Warning: Please call AppEventsLogger.activateApp(...)from the long-lived activity's onResume() methodbefore logging other app events.");
            }
        }
    }

    /* renamed from: b */
    public static String m14412b(Context context) {
        if (f13436g == null) {
            synchronized (f13435f) {
                if (f13436g == null) {
                    f13436g = context.getSharedPreferences("com.facebook.sdk.appEventPreferences", 0).getString("anonymousAppDeviceGUID", null);
                    if (f13436g == null) {
                        f13436g = "XZ" + UUID.randomUUID().toString();
                        context.getSharedPreferences("com.facebook.sdk.appEventPreferences", 0).edit().putString("anonymousAppDeviceGUID", f13436g).apply();
                    }
                }
            }
        }
        return f13436g;
    }
}
