package com.twitter.sdk.android.core;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.twitter.sdk.android.core.internal.oauth.AppAuthToken;
import com.twitter.sdk.android.core.internal.oauth.GuestAuthToken;
import com.twitter.sdk.android.core.internal.oauth.OAuth2Token;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: AuthTokenAdapter */
/* renamed from: com.twitter.sdk.android.core.c */
public class C4577c implements JsonDeserializer<C1500b>, JsonSerializer<C1500b> {
    /* renamed from: a */
    static final Map<String, Class<? extends C1500b>> f16203a = new HashMap();
    /* renamed from: b */
    private final Gson f16204b = new Gson();

    public /* synthetic */ Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return m18144a(jsonElement, type, jsonDeserializationContext);
    }

    public /* synthetic */ JsonElement serialize(Object obj, Type type, JsonSerializationContext jsonSerializationContext) {
        return m18143a((C1500b) obj, type, jsonSerializationContext);
    }

    static {
        f16203a.put("oauth1a", TwitterAuthToken.class);
        f16203a.put("oauth2", OAuth2Token.class);
        f16203a.put("guest", GuestAuthToken.class);
        f16203a.put("app", AppAuthToken.class);
    }

    /* renamed from: a */
    public JsonElement m18143a(C1500b c1500b, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonElement jsonObject = new JsonObject();
        jsonObject.addProperty("auth_type", C4577c.m18142a(c1500b.getClass()));
        jsonObject.add("auth_token", this.f16204b.toJsonTree(c1500b));
        return jsonObject;
    }

    /* renamed from: a */
    public C1500b m18144a(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        String asString = asJsonObject.getAsJsonPrimitive("auth_type").getAsString();
        return (C1500b) this.f16204b.fromJson(asJsonObject.get("auth_token"), (Class) f16203a.get(asString));
    }

    /* renamed from: a */
    static String m18142a(Class<? extends C1500b> cls) {
        for (Entry entry : f16203a.entrySet()) {
            if (((Class) entry.getValue()).equals(cls)) {
                return (String) entry.getKey();
            }
        }
        return "";
    }
}
