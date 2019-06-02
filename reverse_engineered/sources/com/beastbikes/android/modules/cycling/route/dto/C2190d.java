package com.beastbikes.android.modules.cycling.route.dto;

import com.beastbikes.android.utils.C2555d;
import java.util.Date;
import org.json.JSONObject;

/* compiled from: RouteCommentDTO */
/* renamed from: com.beastbikes.android.modules.cycling.route.dto.d */
public class C2190d {
    /* renamed from: a */
    private String f10272a;
    /* renamed from: b */
    private String f10273b;
    /* renamed from: c */
    private String f10274c;
    /* renamed from: d */
    private String f10275d;
    /* renamed from: e */
    private String f10276e;
    /* renamed from: f */
    private String f10277f;
    /* renamed from: g */
    private Date f10278g;
    /* renamed from: h */
    private Date f10279h;
    /* renamed from: i */
    private int f10280i;
    /* renamed from: j */
    private String f10281j;
    /* renamed from: k */
    private String f10282k;

    public C2190d(JSONObject jSONObject) {
        this.f10272a = jSONObject.optString("id");
        this.f10273b = jSONObject.optString("routeId");
        this.f10274c = jSONObject.optString("userId");
        this.f10276e = jSONObject.optString("nickname");
        this.f10277f = jSONObject.optString("content");
        this.f10278g = C2555d.m12820h(jSONObject.optString("createdAt"));
        this.f10275d = jSONObject.optString("parentId");
        this.f10279h = C2555d.m12820h(jSONObject.optString("updatedAt"));
        this.f10281j = jSONObject.optString("avatar");
        this.f10282k = jSONObject.optString("remarks");
    }

    /* renamed from: a */
    public String m11221a() {
        return this.f10274c;
    }

    /* renamed from: b */
    public String m11223b() {
        return this.f10276e;
    }

    /* renamed from: c */
    public String m11224c() {
        return this.f10277f;
    }

    /* renamed from: d */
    public Date m11225d() {
        return this.f10278g;
    }

    /* renamed from: e */
    public int m11226e() {
        return this.f10280i;
    }

    /* renamed from: a */
    public void m11222a(int i) {
        this.f10280i = i;
    }

    /* renamed from: f */
    public String m11227f() {
        return this.f10281j;
    }

    /* renamed from: g */
    public String m11228g() {
        return this.f10282k;
    }
}
