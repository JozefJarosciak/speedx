package com.beastbikes.android.modules.cycling.club.dto;

import org.json.JSONObject;

/* compiled from: ClubNoticeBean */
/* renamed from: com.beastbikes.android.modules.cycling.club.dto.f */
public class C2067f {
    /* renamed from: a */
    private String f9395a;
    /* renamed from: b */
    private long f9396b;
    /* renamed from: c */
    private String f9397c;
    /* renamed from: d */
    private String f9398d;

    public C2067f(JSONObject jSONObject) {
        this.f9395a = jSONObject.optString("content");
        this.f9396b = jSONObject.optLong("timestamp");
        this.f9397c = jSONObject.optString("clubId");
        this.f9398d = jSONObject.optString("createdAt");
    }

    /* renamed from: a */
    public String m10649a() {
        return this.f9395a;
    }

    /* renamed from: b */
    public String m10650b() {
        return this.f9398d;
    }
}
