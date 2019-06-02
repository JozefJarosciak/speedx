package com.twitter.sdk.android.core.internal.oauth;

import com.google.gson.annotations.SerializedName;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import java.util.Map;

public class GuestAuthToken extends OAuth2Token {
    @SerializedName("guest_token")
    /* renamed from: b */
    private final String f7055b;

    public GuestAuthToken(String str, String str2, String str3) {
        super(str, str2);
        this.f7055b = str3;
    }

    /* renamed from: b */
    public String m8306b() {
        return this.f7055b;
    }

    /* renamed from: a */
    public boolean mo2982a() {
        return System.currentTimeMillis() >= this.a + 10800000;
    }

    /* renamed from: a */
    public Map<String, String> mo2983a(TwitterAuthConfig twitterAuthConfig, String str, String str2, Map<String, String> map) {
        Map<String, String> a = super.mo2983a(twitterAuthConfig, str, str2, map);
        a.put("x-guest-token", m8306b());
        return a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        GuestAuthToken guestAuthToken = (GuestAuthToken) obj;
        if (this.f7055b != null) {
            if (this.f7055b.equals(guestAuthToken.f7055b)) {
                return true;
            }
        } else if (guestAuthToken.f7055b == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (this.f7055b != null ? this.f7055b.hashCode() : 0) + (super.hashCode() * 31);
    }
}
