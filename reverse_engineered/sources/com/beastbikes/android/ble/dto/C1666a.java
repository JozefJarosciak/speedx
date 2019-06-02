package com.beastbikes.android.ble.dto;

import com.beastbikes.framework.ui.android.WebActivity;
import com.mapbox.services.directions.v4.DirectionsCriteria;
import org.json.JSONObject;

/* compiled from: FirstMaintenanceDTO */
/* renamed from: com.beastbikes.android.ble.dto.a */
public class C1666a {
    /* renamed from: a */
    private int f7534a;
    /* renamed from: b */
    private String f7535b;
    /* renamed from: c */
    private String f7536c;
    /* renamed from: d */
    private String f7537d;
    /* renamed from: e */
    private int f7538e;
    /* renamed from: f */
    private String f7539f;
    /* renamed from: g */
    private String f7540g;
    /* renamed from: h */
    private String f7541h;

    public C1666a(JSONObject jSONObject) {
        if (jSONObject != null) {
            JSONObject optJSONObject = jSONObject.optJSONObject("params");
            if (optJSONObject != null) {
                JSONObject optJSONObject2 = optJSONObject.optJSONObject(DirectionsCriteria.INSTRUCTIONS_TEXT);
                JSONObject optJSONObject3 = optJSONObject2.optJSONObject("coupons");
                if (optJSONObject3 != null) {
                    this.f7534a = optJSONObject3.optInt("status");
                    this.f7537d = optJSONObject3.optString("expireAt");
                    this.f7536c = optJSONObject3.optString("redeemCode");
                    this.f7535b = optJSONObject3.optString("name");
                    this.f7541h = optJSONObject3.optString("description");
                }
                this.f7540g = optJSONObject2.optString(WebActivity.EXTRA_TITLE);
                this.f7539f = optJSONObject2.optString("desc");
                this.f7538e = optJSONObject.optInt("duration");
            }
        }
    }

    /* renamed from: a */
    public int m9037a() {
        return this.f7534a;
    }

    /* renamed from: b */
    public String m9038b() {
        return this.f7535b;
    }

    /* renamed from: c */
    public String m9039c() {
        return this.f7536c;
    }

    /* renamed from: d */
    public String m9040d() {
        return this.f7537d;
    }

    /* renamed from: e */
    public int m9041e() {
        return this.f7538e;
    }

    /* renamed from: f */
    public String m9042f() {
        return this.f7539f;
    }

    /* renamed from: g */
    public String m9043g() {
        return this.f7540g;
    }

    /* renamed from: h */
    public String m9044h() {
        return this.f7541h;
    }
}
