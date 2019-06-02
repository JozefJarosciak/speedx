package com.facebook.appevents;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import com.facebook.C1472c;
import com.facebook.C2987f;
import com.facebook.FacebookRequestError;
import com.facebook.GraphRequest;
import com.facebook.GraphRequest.C2942b;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.AppEventsLogger.FlushBehavior;
import com.facebook.internal.C3025m;
import com.facebook.internal.C3048s;
import com.facebook.internal.C3048s.C3045b;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;

/* compiled from: AppEventQueue */
/* renamed from: com.facebook.appevents.b */
class C2972b {
    /* renamed from: a */
    private static final String f13457a = C2972b.class.getName();
    /* renamed from: b */
    private static volatile C2966a f13458b = new C2966a();
    /* renamed from: c */
    private static final ScheduledExecutorService f13459c = Executors.newSingleThreadScheduledExecutor();
    /* renamed from: d */
    private static ScheduledFuture f13460d;
    /* renamed from: e */
    private static final Runnable f13461e = new C29671();

    /* compiled from: AppEventQueue */
    /* renamed from: com.facebook.appevents.b$1 */
    static class C29671 implements Runnable {
        C29671() {
        }

        public void run() {
            C2972b.f13460d = null;
            if (AppEventsLogger.m14407a() != FlushBehavior.EXPLICIT_ONLY) {
                C2972b.m14437b(FlushReason.TIMER);
            }
        }
    }

    C2972b() {
    }

    /* renamed from: a */
    public static void m14434a(final FlushReason flushReason) {
        f13459c.execute(new Runnable() {
            public void run() {
                C2972b.m14437b(flushReason);
            }
        });
    }

    /* renamed from: a */
    public static void m14433a(final AccessTokenAppIdPair accessTokenAppIdPair, final AppEvent appEvent) {
        f13459c.execute(new Runnable() {
            public void run() {
                C2972b.f13458b.m14424a(accessTokenAppIdPair, appEvent);
                if (AppEventsLogger.m14407a() != FlushBehavior.EXPLICIT_ONLY && C2972b.f13458b.m14426b() > 100) {
                    C2972b.m14437b(FlushReason.EVENT_THRESHOLD);
                } else if (C2972b.f13460d == null) {
                    C2972b.f13460d = C2972b.f13459c.schedule(C2972b.f13461e, 15, TimeUnit.SECONDS);
                }
            }
        });
    }

    /* renamed from: a */
    public static Set<AccessTokenAppIdPair> m14430a() {
        return f13458b.m14423a();
    }

    /* renamed from: b */
    static void m14437b(FlushReason flushReason) {
        f13458b.m14425a(C2974c.m14441a());
        try {
            C2975d a = C2972b.m14429a(flushReason, f13458b);
            if (a != null) {
                Intent intent = new Intent("com.facebook.sdk.APP_EVENTS_FLUSHED");
                intent.putExtra("com.facebook.sdk.APP_EVENTS_NUM_EVENTS_FLUSHED", a.f13463a);
                intent.putExtra("com.facebook.sdk.APP_EVENTS_FLUSH_RESULT", a.f13464b);
                LocalBroadcastManager.getInstance(C1472c.f()).sendBroadcast(intent);
            }
        } catch (Throwable e) {
            Log.w(f13457a, "Caught unexpected exception while flushing app events: ", e);
        }
    }

    /* renamed from: a */
    private static C2975d m14429a(FlushReason flushReason, C2966a c2966a) {
        C2975d c2975d = new C2975d();
        boolean b = C1472c.b(C1472c.f());
        List<GraphRequest> arrayList = new ArrayList();
        for (AccessTokenAppIdPair accessTokenAppIdPair : c2966a.m14423a()) {
            GraphRequest a = C2972b.m14428a(accessTokenAppIdPair, c2966a.m14422a(accessTokenAppIdPair), b, c2975d);
            if (a != null) {
                arrayList.add(a);
            }
        }
        if (arrayList.size() <= 0) {
            return null;
        }
        C3025m.m14620a(LoggingBehavior.APP_EVENTS, f13457a, "Flushing %d events due to %s.", Integer.valueOf(c2975d.f13463a), flushReason.toString());
        for (GraphRequest a2 : arrayList) {
            a2.m14384i();
        }
        return c2975d;
    }

    /* renamed from: a */
    private static GraphRequest m14428a(final AccessTokenAppIdPair accessTokenAppIdPair, final C2976e c2976e, boolean z, final C2975d c2975d) {
        C3045b a = C3048s.m14727a(accessTokenAppIdPair.getApplicationId(), false);
        final GraphRequest a2 = GraphRequest.m14333a(null, String.format("%s/activities", new Object[]{r0}), null, null);
        Bundle e = a2.m14380e();
        if (e == null) {
            e = new Bundle();
        }
        e.putString("access_token", accessTokenAppIdPair.getAccessTokenString());
        String c = AppEventsLogger.m14413c();
        if (c != null) {
            e.putString("device_token", c);
        }
        a2.m14371a(e);
        if (a == null) {
            return null;
        }
        int a3 = c2976e.m14448a(a2, C1472c.f(), a.m14715a(), z);
        if (a3 == 0) {
            return null;
        }
        c2975d.f13463a = a3 + c2975d.f13463a;
        a2.m14372a(new C2942b() {
            /* renamed from: a */
            public void mo3687a(C2987f c2987f) {
                C2972b.m14436b(accessTokenAppIdPair, a2, c2987f, c2976e, c2975d);
            }
        });
        return a2;
    }

    /* renamed from: b */
    private static void m14436b(final AccessTokenAppIdPair accessTokenAppIdPair, GraphRequest graphRequest, C2987f c2987f, final C2976e c2976e, C2975d c2975d) {
        FlushResult flushResult;
        FacebookRequestError a = c2987f.m14499a();
        String str = "Success";
        FlushResult flushResult2 = FlushResult.SUCCESS;
        String str2;
        if (a == null) {
            str2 = str;
            flushResult = flushResult2;
        } else if (a.m14302b() == -1) {
            Object obj = "Failed: No Connectivity";
            flushResult = FlushResult.NO_CONNECTIVITY;
        } else {
            str2 = String.format("Failed:\n  Response: %s\n  Error %s", new Object[]{c2987f.toString(), a.toString()});
            flushResult = FlushResult.SERVER_ERROR;
        }
        if (C1472c.a(LoggingBehavior.APP_EVENTS)) {
            String jSONArray;
            try {
                jSONArray = new JSONArray((String) graphRequest.m14383h()).toString(2);
            } catch (JSONException e) {
                jSONArray = "<Can't encode events for debug logging>";
            }
            C3025m.m14620a(LoggingBehavior.APP_EVENTS, f13457a, "Flush completed\nParams: %s\n  Result: %s\n  Events JSON: %s", graphRequest.m14370a().toString(), obj, jSONArray);
        }
        c2976e.m14450a(a != null);
        if (flushResult == FlushResult.NO_CONNECTIVITY) {
            C1472c.d().execute(new Runnable() {
                public void run() {
                    C2974c.m14442a(accessTokenAppIdPair, c2976e);
                }
            });
        }
        if (flushResult != FlushResult.SUCCESS && c2975d.f13464b != FlushResult.NO_CONNECTIVITY) {
            c2975d.f13464b = flushResult;
        }
    }
}
