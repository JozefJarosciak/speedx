package com.beastbikes.android.modules.user.dto;

import com.mapbox.mapboxsdk.style.layers.Property;
import org.json.JSONObject;

/* compiled from: WaterMarkWidgetBase */
/* renamed from: com.beastbikes.android.modules.user.dto.f */
public class C2413f {
    /* renamed from: a */
    private int f11418a;
    /* renamed from: b */
    private int f11419b;
    /* renamed from: c */
    private int f11420c;
    /* renamed from: d */
    private int f11421d;
    /* renamed from: e */
    private int f11422e;
    /* renamed from: f */
    private int f11423f;
    /* renamed from: g */
    private int f11424g;

    public C2413f(JSONObject jSONObject) {
        this.f11418a = jSONObject.optInt("position");
        this.f11419b = jSONObject.optInt(Property.TEXT_ANCHOR_TOP);
        this.f11420c = jSONObject.optInt("left");
        this.f11421d = jSONObject.optInt("right");
        this.f11422e = jSONObject.optInt(Property.TEXT_ANCHOR_BOTTOM);
    }

    /* renamed from: g */
    public int m12252g() {
        return this.f11418a;
    }

    /* renamed from: a */
    public void m12246a(int i) {
        this.f11418a = i;
    }

    /* renamed from: h */
    public int m12254h() {
        return this.f11419b;
    }

    /* renamed from: b */
    public void m12247b(int i) {
        this.f11419b = i;
    }

    /* renamed from: i */
    public int m12255i() {
        return this.f11420c;
    }

    /* renamed from: c */
    public void m12248c(int i) {
        this.f11420c = i;
    }

    /* renamed from: j */
    public int m12256j() {
        return this.f11421d;
    }

    /* renamed from: d */
    public void m12249d(int i) {
        this.f11421d = i;
    }

    /* renamed from: k */
    public int m12257k() {
        return this.f11422e;
    }

    /* renamed from: e */
    public void m12250e(int i) {
        this.f11422e = i;
    }

    /* renamed from: l */
    public int m12258l() {
        return this.f11423f;
    }

    /* renamed from: f */
    public void m12251f(int i) {
        this.f11423f = i;
    }

    /* renamed from: m */
    public int m12259m() {
        return this.f11424g;
    }

    /* renamed from: g */
    public void m12253g(int i) {
        this.f11424g = i;
    }
}
