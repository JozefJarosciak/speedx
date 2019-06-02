package com.beastbikes.android.modules.cycling.sections.dto;

import com.beastbikes.framework.ui.android.WebActivity;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: SectionListDTO */
/* renamed from: com.beastbikes.android.modules.cycling.sections.dto.c */
public class C2222c {
    /* renamed from: a */
    private String f10510a;
    /* renamed from: b */
    private String f10511b;
    /* renamed from: c */
    private double f10512c;
    /* renamed from: d */
    private double f10513d;
    /* renamed from: e */
    private long f10514e;
    /* renamed from: f */
    private int f10515f;
    /* renamed from: g */
    private String f10516g;
    /* renamed from: h */
    private double f10517h;
    /* renamed from: i */
    private String f10518i;
    /* renamed from: j */
    private JSONArray f10519j;
    /* renamed from: k */
    private double f10520k = this.f10519j.optDouble(0);
    /* renamed from: l */
    private double f10521l = this.f10519j.optDouble(1);
    /* renamed from: m */
    private int f10522m;
    /* renamed from: n */
    private String f10523n;
    /* renamed from: o */
    private JSONArray f10524o;
    /* renamed from: p */
    private double f10525p;
    /* renamed from: q */
    private double f10526q;

    public C2222c(JSONObject jSONObject) {
        this.f10510a = jSONObject.optString(WebActivity.EXTRA_TITLE);
        this.f10511b = jSONObject.optString("polyline");
        this.f10512c = jSONObject.optDouble("range");
        this.f10513d = jSONObject.optDouble("legLength");
        this.f10514e = jSONObject.optLong("segmentId");
        this.f10515f = jSONObject.optInt("slope");
        this.f10516g = jSONObject.optString("name");
        this.f10517h = jSONObject.optDouble("altDiff");
        this.f10518i = jSONObject.optString("lordNick");
        this.f10519j = jSONObject.optJSONArray("origin");
        this.f10522m = jSONObject.optInt("difficult");
        this.f10523n = jSONObject.optString("lordAvatar");
        this.f10524o = jSONObject.optJSONArray("origin");
        this.f10525p = this.f10524o.optDouble(0);
        this.f10526q = this.f10524o.optDouble(1);
    }

    /* renamed from: a */
    public String m11418a() {
        return this.f10511b;
    }

    /* renamed from: b */
    public double m11419b() {
        return this.f10512c;
    }

    /* renamed from: c */
    public double m11420c() {
        return this.f10513d;
    }

    /* renamed from: d */
    public long m11421d() {
        return this.f10514e;
    }

    /* renamed from: e */
    public String m11422e() {
        return this.f10516g;
    }

    /* renamed from: f */
    public double m11423f() {
        return this.f10517h;
    }

    /* renamed from: g */
    public String m11424g() {
        return this.f10518i;
    }

    /* renamed from: h */
    public double m11425h() {
        return this.f10520k;
    }

    /* renamed from: i */
    public double m11426i() {
        return this.f10521l;
    }

    /* renamed from: j */
    public int m11427j() {
        return this.f10522m;
    }

    /* renamed from: k */
    public String m11428k() {
        return this.f10523n;
    }
}
