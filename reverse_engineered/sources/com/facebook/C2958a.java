package com.facebook;

import android.content.SharedPreferences;
import android.os.Bundle;
import com.facebook.internal.C3049t;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: AccessTokenCache */
/* renamed from: com.facebook.a */
class C2958a {
    /* renamed from: a */
    private final SharedPreferences f13429a;
    /* renamed from: b */
    private final C2957a f13430b;
    /* renamed from: c */
    private C2988g f13431c;

    /* compiled from: AccessTokenCache */
    /* renamed from: com.facebook.a$a */
    static class C2957a {
        C2957a() {
        }

        /* renamed from: a */
        public C2988g m14398a() {
            return new C2988g(C1472c.f());
        }
    }

    C2958a(SharedPreferences sharedPreferences, C2957a c2957a) {
        this.f13429a = sharedPreferences;
        this.f13430b = c2957a;
    }

    public C2958a() {
        this(C1472c.f().getSharedPreferences("com.facebook.AccessTokenManager.SharedPreferences", 0), new C2957a());
    }

    /* renamed from: a */
    public AccessToken m14404a() {
        if (m14399c()) {
            return m14400d();
        }
        if (!m14401e()) {
            return null;
        }
        AccessToken f = m14402f();
        if (f == null) {
            return f;
        }
        m14405a(f);
        m14403g().m14508b();
        return f;
    }

    /* renamed from: a */
    public void m14405a(AccessToken accessToken) {
        C3049t.m14790a((Object) accessToken, "accessToken");
        try {
            this.f13429a.edit().putString("com.facebook.AccessTokenManager.CachedAccessToken", accessToken.m14285j().toString()).apply();
        } catch (JSONException e) {
        }
    }

    /* renamed from: b */
    public void m14406b() {
        this.f13429a.edit().remove("com.facebook.AccessTokenManager.CachedAccessToken").apply();
        if (m14401e()) {
            m14403g().m14508b();
        }
    }

    /* renamed from: c */
    private boolean m14399c() {
        return this.f13429a.contains("com.facebook.AccessTokenManager.CachedAccessToken");
    }

    /* renamed from: d */
    private AccessToken m14400d() {
        AccessToken accessToken = null;
        String string = this.f13429a.getString("com.facebook.AccessTokenManager.CachedAccessToken", accessToken);
        if (string != null) {
            try {
                accessToken = AccessToken.m14272a(new JSONObject(string));
            } catch (JSONException e) {
            }
        }
        return accessToken;
    }

    /* renamed from: e */
    private boolean m14401e() {
        return C1472c.c();
    }

    /* renamed from: f */
    private AccessToken m14402f() {
        Bundle a = m14403g().m14507a();
        if (a == null || !C2988g.m14503a(a)) {
            return null;
        }
        return AccessToken.m14271a(a);
    }

    /* renamed from: g */
    private C2988g m14403g() {
        if (this.f13431c == null) {
            synchronized (this) {
                if (this.f13431c == null) {
                    this.f13431c = this.f13430b.m14398a();
                }
            }
        }
        return this.f13431c;
    }
}
