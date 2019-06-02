package com.beastbikes.android.update.p162b;

import com.beastbikes.android.utils.C2555d;
import org.json.JSONObject;

/* compiled from: VersionInfo */
/* renamed from: com.beastbikes.android.update.b.a */
public class C2548a {
    /* renamed from: a */
    private int f12024a;
    /* renamed from: b */
    private int f12025b;
    /* renamed from: c */
    private String f12026c;
    /* renamed from: d */
    private String f12027d;
    /* renamed from: e */
    private String f12028e;
    /* renamed from: f */
    private String f12029f;

    public C2548a(JSONObject jSONObject) {
        this.f12024a = jSONObject.optInt("type");
        this.f12025b = jSONObject.optInt("versionCode");
        this.f12026c = jSONObject.optString("versionName");
        this.f12027d = jSONObject.optString("downloadLink");
        this.f12028e = jSONObject.optString("releaseDate");
        this.f12029f = jSONObject.optString("changeLog");
    }

    /* renamed from: a */
    public String m12744a() {
        return C2555d.m12808c(C2555d.m12820h(this.f12028e));
    }

    /* renamed from: b */
    public String m12745b() {
        return this.f12026c;
    }

    /* renamed from: c */
    public int m12746c() {
        return this.f12025b;
    }

    /* renamed from: d */
    public int m12747d() {
        return this.f12024a;
    }

    /* renamed from: e */
    public String m12748e() {
        return this.f12027d.replace("https://", "http://");
    }

    /* renamed from: f */
    public String m12749f() {
        return this.f12029f;
    }
}
