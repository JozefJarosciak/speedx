package com.beastbikes.android.modules.user.dto;

import com.mapbox.services.directions.v4.DirectionsCriteria;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: WaterMark */
/* renamed from: com.beastbikes.android.modules.user.dto.b */
public class C2412b {
    /* renamed from: a */
    private String f11412a;
    /* renamed from: b */
    private int f11413b;
    /* renamed from: c */
    private int f11414c;
    /* renamed from: d */
    private List<C2414c> f11415d;
    /* renamed from: e */
    private List<C2416e> f11416e;
    /* renamed from: f */
    private List<C2415d> f11417f;

    public C2412b(JSONObject jSONObject) {
        int i;
        JSONObject optJSONObject;
        int i2 = 0;
        this.f11412a = jSONObject.optString("name");
        this.f11413b = jSONObject.optInt("canvas_width");
        this.f11414c = jSONObject.optInt("canvas_height");
        JSONArray optJSONArray = jSONObject.optJSONArray("images");
        if (optJSONArray != null && optJSONArray.length() > 0) {
            this.f11415d = new ArrayList();
            for (i = 0; i < optJSONArray.length(); i++) {
                optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    C2414c c2414c = new C2414c(optJSONObject);
                    c2414c.m12251f(this.f11413b);
                    c2414c.m12253g(this.f11414c);
                    this.f11415d.add(c2414c);
                }
            }
        }
        optJSONArray = jSONObject.optJSONArray(DirectionsCriteria.INSTRUCTIONS_TEXT);
        if (optJSONArray != null && optJSONArray.length() > 0) {
            this.f11416e = new ArrayList();
            for (i = 0; i < optJSONArray.length(); i++) {
                optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    this.f11416e.add(new C2416e(optJSONObject));
                }
            }
        }
        JSONArray optJSONArray2 = jSONObject.optJSONArray("sep_line");
        if (optJSONArray2 != null && optJSONArray2.length() > 0) {
            while (i2 < optJSONArray2.length()) {
                this.f11417f = new ArrayList();
                JSONObject optJSONObject2 = optJSONArray2.optJSONObject(i2);
                if (optJSONObject2 != null) {
                    this.f11417f.add(new C2415d(optJSONObject2));
                }
                i2++;
            }
        }
    }

    /* renamed from: a */
    public int m12241a() {
        return this.f11413b;
    }

    /* renamed from: b */
    public int m12242b() {
        return this.f11414c;
    }

    /* renamed from: c */
    public List<C2414c> m12243c() {
        return this.f11415d;
    }

    /* renamed from: d */
    public List<C2416e> m12244d() {
        return this.f11416e;
    }

    /* renamed from: e */
    public List<C2415d> m12245e() {
        return this.f11417f;
    }
}
