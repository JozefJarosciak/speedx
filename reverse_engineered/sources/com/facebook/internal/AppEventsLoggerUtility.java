package com.facebook.internal;

import android.content.Context;
import com.facebook.LoggingBehavior;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class AppEventsLoggerUtility {
    /* renamed from: a */
    private static final Map<GraphAPIActivityType, String> f13522a = new C29911();

    /* renamed from: com.facebook.internal.AppEventsLoggerUtility$1 */
    static class C29911 extends HashMap<GraphAPIActivityType, String> {
        C29911() {
            put(GraphAPIActivityType.MOBILE_INSTALL_EVENT, "MOBILE_APP_INSTALL");
            put(GraphAPIActivityType.CUSTOM_APP_EVENTS, "CUSTOM_APP_EVENTS");
        }
    }

    public enum GraphAPIActivityType {
        MOBILE_INSTALL_EVENT,
        CUSTOM_APP_EVENTS
    }

    /* renamed from: a */
    public static JSONObject m14518a(GraphAPIActivityType graphAPIActivityType, C3000b c3000b, String str, boolean z, Context context) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("event", f13522a.get(graphAPIActivityType));
        C3048s.m14758a(jSONObject, c3000b, str, z);
        try {
            C3048s.m14757a(jSONObject, context);
        } catch (Exception e) {
            C3025m.m14620a(LoggingBehavior.APP_EVENTS, "AppEvents", "Fetching extended device info parameters failed: '%s'", e.toString());
        }
        jSONObject.put("application_package_name", context.getPackageName());
        return jSONObject;
    }
}
