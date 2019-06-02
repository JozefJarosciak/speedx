package com.beastbikes.android.modules.cycling.route.dto;

import com.avos.avoscloud.AVUtils;
import org.json.JSONObject;

/* compiled from: CityDTO */
/* renamed from: com.beastbikes.android.modules.cycling.route.dto.a */
public class C2187a {
    /* renamed from: a */
    private String f10258a;
    /* renamed from: b */
    private String f10259b;
    /* renamed from: c */
    private String f10260c;
    /* renamed from: d */
    private String f10261d;
    /* renamed from: e */
    private String f10262e;
    /* renamed from: f */
    private boolean f10263f;
    /* renamed from: g */
    private int f10264g;

    public C2187a(JSONObject jSONObject) {
        this.f10259b = jSONObject.optString("cityId");
        this.f10260c = jSONObject.optString("zh_CN");
        this.f10264g = jSONObject.optInt("sort");
        this.f10261d = jSONObject.optString("en");
        this.f10262e = jSONObject.optString("zh_TW");
        this.f10258a = jSONObject.optString(AVUtils.objectIdTag);
        this.f10263f = jSONObject.optBoolean("isList");
    }

    /* renamed from: a */
    public String m11211a() {
        return this.f10259b;
    }

    /* renamed from: a */
    public void m11212a(String str) {
        this.f10259b = str;
    }

    /* renamed from: b */
    public String m11213b() {
        return this.f10260c;
    }

    /* renamed from: b */
    public void m11214b(String str) {
        this.f10260c = str;
    }

    public int hashCode() {
        if (this.f10259b != null) {
            return this.f10259b.hashCode();
        }
        return super.hashCode();
    }

    public boolean equals(Object obj) {
        if ((obj instanceof C2187a) && this.f10259b != null) {
            return this.f10259b.equals(((C2187a) obj).f10259b);
        }
        return false;
    }
}
