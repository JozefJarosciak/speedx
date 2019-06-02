package com.beastbikes.android.modules.user.dto;

import com.beastbikes.framework.ui.android.WebActivity;
import com.mapbox.mapboxsdk.style.layers.Property;
import org.json.JSONObject;

/* compiled from: WaterMarkText */
/* renamed from: com.beastbikes.android.modules.user.dto.e */
public class C2416e extends C2413f {
    /* renamed from: a */
    private int f11433a;
    /* renamed from: b */
    private String f11434b;
    /* renamed from: c */
    private String f11435c;
    /* renamed from: d */
    private int f11436d;
    /* renamed from: e */
    private int f11437e;
    /* renamed from: f */
    private String f11438f;

    public C2416e(JSONObject jSONObject) {
        super(jSONObject);
        this.f11433a = jSONObject.optInt("type");
        this.f11434b = jSONObject.optString("unit");
        this.f11435c = jSONObject.optString(WebActivity.EXTRA_TITLE);
        this.f11436d = jSONObject.optInt("font-size");
        this.f11437e = jSONObject.optInt(Property.ICON_TEXT_FIT_HEIGHT);
        this.f11438f = jSONObject.optString("font-name");
    }

    /* renamed from: a */
    public int m12269a() {
        return this.f11433a;
    }

    /* renamed from: b */
    public String m12271b() {
        return this.f11434b;
    }

    /* renamed from: c */
    public String m12272c() {
        return this.f11435c;
    }

    /* renamed from: a */
    public void m12270a(String str) {
        this.f11435c = str;
    }

    /* renamed from: d */
    public int m12273d() {
        return this.f11436d;
    }

    /* renamed from: e */
    public int m12274e() {
        return this.f11437e;
    }

    /* renamed from: f */
    public String m12275f() {
        return this.f11438f;
    }
}
