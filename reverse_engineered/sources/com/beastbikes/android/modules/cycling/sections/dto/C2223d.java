package com.beastbikes.android.modules.cycling.sections.dto;

import org.json.JSONObject;

/* compiled from: SegmentRankDTO */
/* renamed from: com.beastbikes.android.modules.cycling.sections.dto.d */
public class C2223d {
    /* renamed from: a */
    private double f10527a;
    /* renamed from: b */
    private String f10528b;
    /* renamed from: c */
    private String f10529c;
    /* renamed from: d */
    private long f10530d;
    /* renamed from: e */
    private String f10531e;
    /* renamed from: f */
    private String f10532f;
    /* renamed from: g */
    private String f10533g;
    /* renamed from: h */
    private String f10534h;

    public C2223d(JSONObject jSONObject) {
        this.f10527a = jSONObject.optDouble("duration");
        this.f10528b = jSONObject.optString("area");
        this.f10529c = jSONObject.optString("nickname");
        this.f10530d = jSONObject.optLong("userIntId");
        this.f10531e = jSONObject.optString("userId");
        this.f10532f = jSONObject.optString("province");
        this.f10533g = jSONObject.optString("avatar");
        this.f10534h = jSONObject.optString("city");
    }

    /* renamed from: a */
    public double m11429a() {
        return this.f10527a;
    }

    /* renamed from: b */
    public String m11430b() {
        return this.f10529c;
    }

    /* renamed from: c */
    public String m11431c() {
        return this.f10531e;
    }

    /* renamed from: d */
    public String m11432d() {
        return this.f10533g;
    }
}
