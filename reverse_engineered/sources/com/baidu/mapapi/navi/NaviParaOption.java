package com.baidu.mapapi.navi;

import com.baidu.mapapi.model.LatLng;

public class NaviParaOption {
    /* renamed from: a */
    LatLng f3300a;
    /* renamed from: b */
    String f3301b;
    /* renamed from: c */
    LatLng f3302c;
    /* renamed from: d */
    String f3303d;

    public NaviParaOption endName(String str) {
        this.f3303d = str;
        return this;
    }

    public NaviParaOption endPoint(LatLng latLng) {
        this.f3302c = latLng;
        return this;
    }

    public String getEndName() {
        return this.f3303d;
    }

    public LatLng getEndPoint() {
        return this.f3302c;
    }

    public String getStartName() {
        return this.f3301b;
    }

    public LatLng getStartPoint() {
        return this.f3300a;
    }

    public NaviParaOption startName(String str) {
        this.f3301b = str;
        return this;
    }

    public NaviParaOption startPoint(LatLng latLng) {
        this.f3300a = latLng;
        return this;
    }
}
