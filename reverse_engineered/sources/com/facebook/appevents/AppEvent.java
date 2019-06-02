package com.facebook.appevents;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.alipay.sdk.cons.C0844a;
import com.facebook.FacebookException;
import com.facebook.LoggingBehavior;
import com.facebook.internal.C3025m;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Locale;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

class AppEvent implements Serializable {
    private static final long serialVersionUID = 1;
    private static final HashSet<String> validatedIdentifiers = new HashSet();
    private final boolean isImplicit;
    private final JSONObject jsonObject;
    private final String name;

    static class SerializationProxyV1 implements Serializable {
        private static final long serialVersionUID = -2488473066578201069L;
        private final boolean isImplicit;
        private final String jsonString;

        private SerializationProxyV1(String str, boolean z) {
            this.jsonString = str;
            this.isImplicit = z;
        }

        private Object readResolve() throws JSONException {
            return new AppEvent(this.jsonString, this.isImplicit);
        }
    }

    public AppEvent(String str, String str2, Double d, Bundle bundle, boolean z, @Nullable UUID uuid) throws JSONException, FacebookException {
        this.jsonObject = getJSONObjectForAppEvent(str, str2, d, bundle, z, uuid);
        this.isImplicit = z;
        this.name = str2;
    }

    public String getName() {
        return this.name;
    }

    private AppEvent(String str, boolean z) throws JSONException {
        this.jsonObject = new JSONObject(str);
        this.isImplicit = z;
        this.name = this.jsonObject.optString("_eventName");
    }

    public boolean getIsImplicit() {
        return this.isImplicit;
    }

    public JSONObject getJSONObject() {
        return this.jsonObject;
    }

    private static void validateIdentifier(String str) throws FacebookException {
        String str2 = "^[0-9a-zA-Z_]+[0-9a-zA-Z _-]*$";
        if (str == null || str.length() == 0 || str.length() > 40) {
            if (str == null) {
                str = "<None Provided>";
            }
            throw new FacebookException(String.format(Locale.ROOT, "Identifier '%s' must be less than %d characters", new Object[]{str, Integer.valueOf(40)}));
        }
        synchronized (validatedIdentifiers) {
            boolean contains = validatedIdentifiers.contains(str);
        }
        if (!contains) {
            if (str.matches("^[0-9a-zA-Z_]+[0-9a-zA-Z _-]*$")) {
                synchronized (validatedIdentifiers) {
                    validatedIdentifiers.add(str);
                }
                return;
            }
            throw new FacebookException(String.format("Skipping event named '%s' due to illegal name - must be under 40 chars and alphanumeric, _, - or space, and not start with a space or hyphen.", new Object[]{str}));
        }
    }

    private static JSONObject getJSONObjectForAppEvent(String str, String str2, Double d, Bundle bundle, boolean z, @Nullable UUID uuid) throws FacebookException, JSONException {
        validateIdentifier(str2);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("_eventName", str2);
        jSONObject.put("_logTime", System.currentTimeMillis() / 1000);
        jSONObject.put("_ui", str);
        if (uuid != null) {
            jSONObject.put("_session_id", uuid);
        }
        if (d != null) {
            jSONObject.put("_valueToSum", d.doubleValue());
        }
        if (z) {
            jSONObject.put("_implicitlyLogged", C0844a.f2048d);
        }
        if (bundle != null) {
            for (String str3 : bundle.keySet()) {
                validateIdentifier(str3);
                Object obj = bundle.get(str3);
                if ((obj instanceof String) || (obj instanceof Number)) {
                    jSONObject.put(str3, obj.toString());
                } else {
                    throw new FacebookException(String.format("Parameter value '%s' for key '%s' should be a string or a numeric type.", new Object[]{obj, str3}));
                }
            }
        }
        if (!z) {
            C3025m.m14620a(LoggingBehavior.APP_EVENTS, "AppEvents", "Created app event '%s'", jSONObject.toString());
        }
        return jSONObject;
    }

    private Object writeReplace() {
        return new SerializationProxyV1(this.jsonObject.toString(), this.isImplicit);
    }

    public String toString() {
        return String.format("\"%s\", implicit: %b, json: %s", new Object[]{this.jsonObject.optString("_eventName"), Boolean.valueOf(this.isImplicit), this.jsonObject.toString()});
    }
}
