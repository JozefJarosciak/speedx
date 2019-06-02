package com.beastbikes.android.modules.cycling.club.dto;

import org.json.JSONObject;

/* compiled from: ClubLevel */
/* renamed from: com.beastbikes.android.modules.cycling.club.dto.d */
public class C2065d {
    /* renamed from: a */
    private int f9379a;
    /* renamed from: b */
    private int f9380b;
    /* renamed from: c */
    private String f9381c;

    public C2065d(JSONObject jSONObject) {
        this.f9379a = jSONObject.optInt("progress");
        this.f9380b = jSONObject.optInt("maxProgress");
        this.f9381c = jSONObject.optString("name");
    }

    /* renamed from: a */
    public int m10639a() {
        return this.f9379a;
    }

    /* renamed from: b */
    public int m10640b() {
        return this.f9380b;
    }

    /* renamed from: c */
    public String m10641c() {
        return this.f9381c;
    }
}
