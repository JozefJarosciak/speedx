package com.baidu.mapapi.search.route;

import com.baidu.mapapi.model.LatLng;

public class IndoorPlanNode {
    /* renamed from: a */
    private LatLng f3424a = null;
    /* renamed from: b */
    private String f3425b = null;

    public IndoorPlanNode(LatLng latLng, String str) {
        this.f3424a = latLng;
        this.f3425b = str;
    }

    public String getFloor() {
        return this.f3425b;
    }

    public LatLng getLocation() {
        return this.f3424a;
    }
}
