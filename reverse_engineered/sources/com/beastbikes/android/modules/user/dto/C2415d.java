package com.beastbikes.android.modules.user.dto;

import com.mapbox.mapboxsdk.style.layers.Property;
import org.json.JSONObject;

/* compiled from: WaterMarkSepLine */
/* renamed from: com.beastbikes.android.modules.user.dto.d */
public class C2415d extends C2413f {
    /* renamed from: a */
    private int f11431a;
    /* renamed from: b */
    private int f11432b;

    public C2415d(JSONObject jSONObject) {
        super(jSONObject);
        this.f11431a = jSONObject.optInt(Property.ICON_TEXT_FIT_WIDTH);
        this.f11432b = jSONObject.optInt(Property.ICON_TEXT_FIT_HEIGHT);
    }

    /* renamed from: a */
    public int m12268a() {
        return this.f11431a;
    }
}
