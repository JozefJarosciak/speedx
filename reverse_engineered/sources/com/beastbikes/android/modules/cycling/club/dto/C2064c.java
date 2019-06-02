package com.beastbikes.android.modules.cycling.club.dto;

import ch.qos.logback.core.CoreConstants;
import org.json.JSONObject;

/* compiled from: ClubActivityOriginator */
/* renamed from: com.beastbikes.android.modules.cycling.club.dto.c */
public class C2064c {
    /* renamed from: a */
    private String f9371a;
    /* renamed from: b */
    private String f9372b;
    /* renamed from: c */
    private String f9373c;
    /* renamed from: d */
    private String f9374d;
    /* renamed from: e */
    private String f9375e;
    /* renamed from: f */
    private int f9376f;
    /* renamed from: g */
    private String f9377g;
    /* renamed from: h */
    private String f9378h;

    public C2064c(JSONObject jSONObject) {
        m10634c(jSONObject.optString("area"));
        m10637f(jSONObject.optString("avatar"));
        m10635d(jSONObject.optString("avatarImage"));
        m10633b(jSONObject.optString("city"));
        m10638g(jSONObject.optString("nickname"));
        m10632a(jSONObject.optString("province"));
        m10636e(jSONObject.optString("userId"));
        m10631a(jSONObject.optInt("userIntId"));
    }

    /* renamed from: a */
    public void m10632a(String str) {
        this.f9371a = str;
    }

    /* renamed from: b */
    public void m10633b(String str) {
        this.f9372b = str;
    }

    /* renamed from: c */
    public void m10634c(String str) {
        this.f9373c = str;
    }

    /* renamed from: d */
    public void m10635d(String str) {
        this.f9374d = str;
    }

    /* renamed from: e */
    public void m10636e(String str) {
        this.f9375e = str;
    }

    /* renamed from: a */
    public void m10631a(int i) {
        this.f9376f = i;
    }

    /* renamed from: f */
    public void m10637f(String str) {
        this.f9377g = str;
    }

    /* renamed from: g */
    public void m10638g(String str) {
        this.f9378h = str;
    }

    public String toString() {
        return "ClubActivityOriginator{province='" + this.f9371a + CoreConstants.SINGLE_QUOTE_CHAR + ", city='" + this.f9372b + CoreConstants.SINGLE_QUOTE_CHAR + ", area='" + this.f9373c + CoreConstants.SINGLE_QUOTE_CHAR + ", avatarImage='" + this.f9374d + CoreConstants.SINGLE_QUOTE_CHAR + ", userId='" + this.f9375e + CoreConstants.SINGLE_QUOTE_CHAR + ", userIntId=" + this.f9376f + ", avatar='" + this.f9377g + CoreConstants.SINGLE_QUOTE_CHAR + ", nickname='" + this.f9378h + CoreConstants.SINGLE_QUOTE_CHAR + CoreConstants.CURLY_RIGHT;
    }
}
