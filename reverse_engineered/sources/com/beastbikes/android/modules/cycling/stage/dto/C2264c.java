package com.beastbikes.android.modules.cycling.stage.dto;

import org.json.JSONObject;

/* compiled from: StageScoreItemDTO */
/* renamed from: com.beastbikes.android.modules.cycling.stage.dto.c */
public class C2264c {
    /* renamed from: a */
    private int f10745a;
    /* renamed from: b */
    private double f10746b;
    /* renamed from: c */
    private double f10747c;
    /* renamed from: d */
    private String f10748d;
    /* renamed from: e */
    private double f10749e;
    /* renamed from: f */
    private int f10750f;
    /* renamed from: g */
    private String f10751g;
    /* renamed from: h */
    private String f10752h;

    public C2264c(JSONObject jSONObject) {
        this.f10745a = jSONObject.optInt("achievement");
        this.f10746b = jSONObject.optDouble("leg_distance");
        this.f10747c = jSONObject.optDouble("speed_avg");
        this.f10748d = jSONObject.optString("leg_name");
        this.f10749e = jSONObject.optDouble("time");
        this.f10750f = jSONObject.optInt("leg_id");
        this.f10751g = jSONObject.optString("date");
        this.f10752h = jSONObject.optString("achievement_name");
    }

    /* renamed from: a */
    public int m11604a() {
        return this.f10745a;
    }

    /* renamed from: b */
    public double m11605b() {
        return this.f10746b;
    }

    /* renamed from: c */
    public double m11606c() {
        return this.f10747c;
    }

    /* renamed from: d */
    public String m11607d() {
        return this.f10748d;
    }

    /* renamed from: e */
    public double m11608e() {
        return this.f10749e;
    }

    /* renamed from: f */
    public int m11609f() {
        return this.f10750f;
    }
}
