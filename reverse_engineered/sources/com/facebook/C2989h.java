package com.facebook;

import android.content.SharedPreferences;
import com.facebook.internal.C3049t;
import com.google.android.gms.common.Scopes;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProfileCache */
/* renamed from: com.facebook.h */
final class C2989h {
    /* renamed from: a */
    private final SharedPreferences f13517a = C1472c.f().getSharedPreferences("com.facebook.AccessTokenManager.SharedPreferences", 0);

    C2989h() {
    }

    /* renamed from: a */
    Profile m14509a() {
        String string = this.f13517a.getString("com.facebook.ProfileManager.CachedProfile", null);
        if (string != null) {
            try {
                return new Profile(new JSONObject(string));
            } catch (JSONException e) {
            }
        }
        return null;
    }

    /* renamed from: a */
    void m14510a(Profile profile) {
        C3049t.m14790a((Object) profile, Scopes.PROFILE);
        JSONObject c = profile.m14397c();
        if (c != null) {
            this.f13517a.edit().putString("com.facebook.ProfileManager.CachedProfile", c.toString()).apply();
        }
    }

    /* renamed from: b */
    void m14511b() {
        this.f13517a.edit().remove("com.facebook.ProfileManager.CachedProfile").apply();
    }
}
