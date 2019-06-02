package com.beastbikes.android.modules.cycling.sections.dto;

import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: UserSegmentDTO */
/* renamed from: com.beastbikes.android.modules.cycling.sections.dto.e */
public class C2224e {
    /* renamed from: a */
    private JSONArray f10535a;
    /* renamed from: b */
    private double f10536b;
    /* renamed from: c */
    private double f10537c;
    /* renamed from: d */
    private int f10538d;
    /* renamed from: e */
    private long f10539e;
    /* renamed from: f */
    private double f10540f;
    /* renamed from: g */
    private long f10541g;
    /* renamed from: h */
    private JSONArray f10542h;
    /* renamed from: i */
    private double f10543i;
    /* renamed from: j */
    private double f10544j;
    /* renamed from: k */
    private String f10545k;
    /* renamed from: l */
    private int f10546l;
    /* renamed from: m */
    private String f10547m;
    /* renamed from: n */
    private double f10548n;
    /* renamed from: o */
    private String f10549o;
    /* renamed from: p */
    private int f10550p;
    /* renamed from: q */
    private double f10551q;
    /* renamed from: r */
    private String f10552r;
    /* renamed from: s */
    private double f10553s;
    /* renamed from: t */
    private String f10554t;

    public C2224e(JSONObject jSONObject) {
        this.f10535a = jSONObject.optJSONArray("origin");
        if (this.f10535a != null) {
            this.f10536b = this.f10535a.optDouble(0);
            this.f10537c = this.f10535a.optDouble(1);
        }
        this.f10538d = jSONObject.optInt("slope");
        this.f10539e = jSONObject.optLong("segmentId");
        this.f10540f = jSONObject.optDouble("legLength");
        this.f10541g = jSONObject.optLong("duration");
        this.f10542h = jSONObject.optJSONArray("origin");
        if (this.f10542h != null) {
            this.f10543i = this.f10542h.optDouble(0);
            this.f10544j = this.f10542h.optDouble(1);
        }
        this.f10545k = jSONObject.optString("lordNick");
        this.f10546l = jSONObject.optInt("rank");
        this.f10547m = jSONObject.optString("lordAvatar");
        this.f10548n = jSONObject.optDouble("range");
        this.f10549o = jSONObject.optString("polyline");
        this.f10550p = jSONObject.optInt("difficult");
        this.f10551q = jSONObject.optDouble("avgSpeed");
        this.f10552r = jSONObject.optString("lordId");
        this.f10553s = jSONObject.optDouble("altDiff");
        this.f10554t = jSONObject.optString("name");
    }

    /* renamed from: a */
    public long m11433a() {
        return this.f10539e;
    }

    /* renamed from: b */
    public double m11434b() {
        return this.f10540f;
    }

    /* renamed from: c */
    public long m11435c() {
        return this.f10541g;
    }

    /* renamed from: d */
    public int m11436d() {
        return this.f10546l;
    }

    /* renamed from: e */
    public double m11437e() {
        return this.f10551q;
    }

    /* renamed from: f */
    public String m11438f() {
        return this.f10554t;
    }
}
