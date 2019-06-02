package com.beastbikes.android.modules.cycling.sections.dto;

import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: SectionDetailListDTO */
/* renamed from: com.beastbikes.android.modules.cycling.sections.dto.b */
public class C2221b {
    /* renamed from: a */
    private boolean f10490a;
    /* renamed from: b */
    private double f10491b;
    /* renamed from: c */
    private long f10492c;
    /* renamed from: d */
    private String f10493d;
    /* renamed from: e */
    private JSONArray f10494e;
    /* renamed from: f */
    private double f10495f = this.f10494e.optDouble(0);
    /* renamed from: g */
    private double f10496g = this.f10494e.optDouble(1);
    /* renamed from: h */
    private int f10497h;
    /* renamed from: i */
    private String f10498i;
    /* renamed from: j */
    private JSONArray f10499j;
    /* renamed from: k */
    private double f10500k;
    /* renamed from: l */
    private double f10501l;
    /* renamed from: m */
    private String f10502m;
    /* renamed from: n */
    private String f10503n;
    /* renamed from: o */
    private double f10504o;
    /* renamed from: p */
    private int f10505p;
    /* renamed from: q */
    private int f10506q;
    /* renamed from: r */
    private String f10507r;
    /* renamed from: s */
    private double f10508s;
    /* renamed from: t */
    private int f10509t;

    public C2221b(JSONObject jSONObject) {
        this.f10490a = jSONObject.optBoolean("hasFavor");
        this.f10491b = jSONObject.optDouble("range");
        this.f10492c = jSONObject.optLong("segmentId");
        this.f10493d = jSONObject.optString("lordNick");
        this.f10494e = jSONObject.optJSONArray("origin");
        this.f10497h = jSONObject.optInt("difficult");
        this.f10498i = jSONObject.optString("lordAvatar");
        this.f10499j = jSONObject.optJSONArray("origin");
        this.f10500k = this.f10499j.optDouble(0);
        this.f10501l = this.f10499j.optDouble(1);
        this.f10502m = jSONObject.optString("lordId");
        this.f10503n = jSONObject.optString("polyline");
        this.f10504o = jSONObject.optDouble("legLength");
        this.f10505p = jSONObject.optInt("favorNum");
        this.f10506q = jSONObject.optInt("slope");
        this.f10507r = jSONObject.optString("name");
        this.f10508s = jSONObject.optDouble("altDiff");
        this.f10509t = jSONObject.optInt("challengeNum");
    }

    /* renamed from: a */
    public boolean m11407a() {
        return this.f10490a;
    }

    /* renamed from: b */
    public double m11408b() {
        return this.f10495f;
    }

    /* renamed from: c */
    public double m11409c() {
        return this.f10496g;
    }

    /* renamed from: d */
    public int m11410d() {
        return this.f10497h;
    }

    /* renamed from: e */
    public String m11411e() {
        return this.f10503n;
    }

    /* renamed from: f */
    public double m11412f() {
        return this.f10504o;
    }

    /* renamed from: g */
    public int m11413g() {
        return this.f10505p;
    }

    /* renamed from: h */
    public int m11414h() {
        return this.f10506q;
    }

    /* renamed from: i */
    public String m11415i() {
        return this.f10507r;
    }

    /* renamed from: j */
    public double m11416j() {
        return this.f10508s;
    }

    /* renamed from: k */
    public int m11417k() {
        return this.f10509t;
    }
}
