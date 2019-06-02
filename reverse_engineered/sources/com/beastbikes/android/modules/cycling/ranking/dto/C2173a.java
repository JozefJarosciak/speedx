package com.beastbikes.android.modules.cycling.ranking.dto;

import org.json.JSONObject;

/* compiled from: RankDTO2 */
/* renamed from: com.beastbikes.android.modules.cycling.ranking.dto.a */
public class C2173a {
    /* renamed from: a */
    private boolean f10166a;
    /* renamed from: b */
    private String f10167b;
    /* renamed from: c */
    private int f10168c;
    /* renamed from: d */
    private String f10169d;
    /* renamed from: e */
    private String f10170e;
    /* renamed from: f */
    private long f10171f;
    /* renamed from: g */
    private JSONObject f10172g;
    /* renamed from: h */
    private String f10173h;
    /* renamed from: i */
    private String f10174i;
    /* renamed from: j */
    private double f10175j;
    /* renamed from: k */
    private double f10176k;
    /* renamed from: l */
    private String f10177l;
    /* renamed from: m */
    private String f10178m;
    /* renamed from: n */
    private int f10179n;
    /* renamed from: o */
    private String f10180o;
    /* renamed from: p */
    private String f10181p;
    /* renamed from: q */
    private double f10182q;
    /* renamed from: r */
    private double f10183r;
    /* renamed from: s */
    private int f10184s;
    /* renamed from: t */
    private String f10185t;

    public C2173a(JSONObject jSONObject) {
        this.f10166a = jSONObject.optBoolean("ismanager");
        this.f10167b = jSONObject.optString("clubId");
        this.f10168c = jSONObject.optInt("level");
        this.f10169d = jSONObject.optString("userId");
        this.f10170e = jSONObject.optString("joined");
        this.f10171f = jSONObject.optLong("score");
        this.f10183r = jSONObject.optDouble("milestone");
        this.f10172g = jSONObject.optJSONObject("user");
        if (this.f10172g != null) {
            this.f10173h = this.f10172g.optString("province");
            this.f10174i = this.f10172g.optString("city");
            this.f10175j = (double) this.f10172g.optLong("weeklyDistance");
            this.f10176k = (double) this.f10172g.optLong("totalDistance");
            this.f10177l = this.f10172g.optString("area");
            this.f10178m = this.f10172g.optString("avatar");
            this.f10179n = this.f10172g.optInt("userInt");
            this.f10180o = this.f10172g.optString("avatar");
            this.f10181p = this.f10172g.optString("nickname");
            this.f10182q = (double) this.f10172g.optLong("monthlyDistance");
            this.f10185t = this.f10172g.optString("remarks");
        }
    }

    /* renamed from: a */
    public int m11135a() {
        return this.f10168c;
    }

    /* renamed from: b */
    public String m11137b() {
        return this.f10169d;
    }

    /* renamed from: c */
    public String m11138c() {
        return this.f10173h;
    }

    /* renamed from: d */
    public String m11139d() {
        return this.f10174i;
    }

    /* renamed from: e */
    public String m11140e() {
        return this.f10178m;
    }

    /* renamed from: f */
    public String m11141f() {
        return this.f10180o;
    }

    /* renamed from: g */
    public String m11142g() {
        return this.f10181p;
    }

    /* renamed from: h */
    public double m11143h() {
        return this.f10183r;
    }

    /* renamed from: i */
    public int m11144i() {
        return this.f10184s;
    }

    /* renamed from: a */
    public void m11136a(int i) {
        this.f10184s = i;
    }

    /* renamed from: j */
    public String m11145j() {
        return this.f10185t;
    }
}
