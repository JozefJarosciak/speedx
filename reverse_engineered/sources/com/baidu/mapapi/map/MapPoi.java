package com.baidu.mapapi.map;

import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import org.json.JSONObject;

public final class MapPoi {
    /* renamed from: d */
    private static final String f2969d = MapPoi.class.getSimpleName();
    /* renamed from: a */
    String f2970a;
    /* renamed from: b */
    LatLng f2971b;
    /* renamed from: c */
    String f2972c;

    /* renamed from: a */
    void m4132a(JSONObject jSONObject) {
        this.f2970a = jSONObject.optString("tx");
        this.f2971b = CoordUtil.decodeNodeLocation(jSONObject.optString("geo"));
        this.f2972c = jSONObject.optString("ud");
    }

    public String getName() {
        return this.f2970a;
    }

    public LatLng getPosition() {
        return this.f2971b;
    }

    public String getUid() {
        return this.f2972c;
    }
}
