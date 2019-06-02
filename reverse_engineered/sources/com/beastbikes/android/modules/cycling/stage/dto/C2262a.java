package com.beastbikes.android.modules.cycling.stage.dto;

import org.json.JSONObject;

/* compiled from: StageProfileScoreDTO */
/* renamed from: com.beastbikes.android.modules.cycling.stage.dto.a */
public class C2262a {
    /* renamed from: a */
    private String f10732a;
    /* renamed from: b */
    private String f10733b;
    /* renamed from: c */
    private String f10734c;
    /* renamed from: d */
    private boolean f10735d;
    /* renamed from: e */
    private int f10736e;
    /* renamed from: f */
    private String f10737f;
    /* renamed from: g */
    private long f10738g;
    /* renamed from: h */
    private int f10739h;
    /* renamed from: i */
    private String f10740i;
    /* renamed from: j */
    private String f10741j;

    public C2262a(JSONObject jSONObject) {
        this.f10732a = jSONObject.optString("nickname");
        this.f10734c = jSONObject.optString("prize");
        this.f10735d = jSONObject.optBoolean("best");
        this.f10736e = (int) (jSONObject.optDouble("user_rank_percent") * 100.0d);
        this.f10738g = jSONObject.optLong("time");
        this.f10737f = jSONObject.optString("date");
        this.f10739h = jSONObject.optInt("rank");
        this.f10733b = jSONObject.optString("user_avatar");
        this.f10740i = jSONObject.optString("sport_route_id");
        this.f10741j = jSONObject.optString("user_id");
    }

    /* renamed from: a */
    public String m11591a() {
        return this.f10732a;
    }

    /* renamed from: b */
    public String m11592b() {
        return this.f10733b;
    }

    /* renamed from: c */
    public String m11593c() {
        return this.f10737f;
    }

    /* renamed from: d */
    public long m11594d() {
        return this.f10738g;
    }

    /* renamed from: e */
    public String m11595e() {
        return this.f10734c;
    }

    /* renamed from: f */
    public boolean m11596f() {
        return this.f10735d;
    }

    /* renamed from: g */
    public int m11597g() {
        return this.f10736e;
    }

    /* renamed from: h */
    public int m11598h() {
        return this.f10739h;
    }

    /* renamed from: i */
    public String m11599i() {
        return this.f10740i;
    }

    /* renamed from: j */
    public String m11600j() {
        return this.f10741j;
    }
}
