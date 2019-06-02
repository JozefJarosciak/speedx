package com.facebook.internal;

import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* compiled from: ProfileInformationCache */
/* renamed from: com.facebook.internal.q */
class C3039q {
    /* renamed from: a */
    private static final ConcurrentHashMap<String, JSONObject> f13610a = new ConcurrentHashMap();

    /* renamed from: a */
    public static JSONObject m14702a(String str) {
        return (JSONObject) f13610a.get(str);
    }

    /* renamed from: a */
    public static void m14703a(String str, JSONObject jSONObject) {
        f13610a.put(str, jSONObject);
    }
}
