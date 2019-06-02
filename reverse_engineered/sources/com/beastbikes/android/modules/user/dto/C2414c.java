package com.beastbikes.android.modules.user.dto;

import android.graphics.Bitmap;
import com.mapbox.mapboxsdk.style.layers.Property;
import org.json.JSONObject;

/* compiled from: WaterMarkImage */
/* renamed from: com.beastbikes.android.modules.user.dto.c */
public class C2414c extends C2413f {
    /* renamed from: a */
    private String f11425a;
    /* renamed from: b */
    private String f11426b;
    /* renamed from: c */
    private int f11427c;
    /* renamed from: d */
    private int f11428d;
    /* renamed from: e */
    private Bitmap f11429e;
    /* renamed from: f */
    private Bitmap f11430f;

    public C2414c(C2414c c2414c) {
        this.f11425a = c2414c.m12260a();
        this.f11426b = c2414c.m12262b();
        this.f11427c = c2414c.m12264c();
        this.f11428d = c2414c.m12265d();
        m12246a(c2414c.m12252g());
        m12248c(c2414c.m12255i());
        m12249d(c2414c.m12256j());
        m12247b(c2414c.m12254h());
        m12250e(c2414c.m12257k());
        m12253g(c2414c.m12259m());
        m12251f(c2414c.m12258l());
    }

    public C2414c(JSONObject jSONObject) {
        super(jSONObject);
        this.f11425a = jSONObject.optString("white_url");
        this.f11426b = jSONObject.optString("black_url");
        this.f11427c = jSONObject.optInt(Property.ICON_TEXT_FIT_WIDTH);
        this.f11428d = jSONObject.optInt(Property.ICON_TEXT_FIT_HEIGHT);
    }

    /* renamed from: a */
    public String m12260a() {
        return this.f11425a;
    }

    /* renamed from: b */
    public String m12262b() {
        return this.f11426b;
    }

    /* renamed from: c */
    public int m12264c() {
        return this.f11427c;
    }

    /* renamed from: d */
    public int m12265d() {
        return this.f11428d;
    }

    /* renamed from: e */
    public Bitmap m12266e() {
        return this.f11429e;
    }

    /* renamed from: a */
    public void m12261a(Bitmap bitmap) {
        this.f11429e = bitmap;
    }

    /* renamed from: f */
    public Bitmap m12267f() {
        return this.f11430f;
    }

    /* renamed from: b */
    public void m12263b(Bitmap bitmap) {
        this.f11430f = bitmap;
    }
}
