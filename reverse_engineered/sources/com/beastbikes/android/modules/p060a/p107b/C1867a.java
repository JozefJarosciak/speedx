package com.beastbikes.android.modules.p060a.p107b;

import org.json.JSONObject;

/* compiled from: AdDto */
/* renamed from: com.beastbikes.android.modules.a.b.a */
public class C1867a {
    /* renamed from: a */
    private String f8393a;
    /* renamed from: b */
    private String f8394b;

    public C1867a(JSONObject jSONObject) {
        this.f8393a = jSONObject.optString("imageUrl");
        this.f8394b = jSONObject.optString("linkTo");
    }

    /* renamed from: a */
    public String m9712a() {
        return this.f8394b;
    }

    /* renamed from: b */
    public String m9713b() {
        return this.f8393a;
    }
}
