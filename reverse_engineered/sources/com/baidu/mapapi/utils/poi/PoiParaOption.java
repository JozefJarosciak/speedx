package com.baidu.mapapi.utils.poi;

import com.baidu.mapapi.model.LatLng;

public class PoiParaOption {
    /* renamed from: a */
    String f3541a;
    /* renamed from: b */
    String f3542b;
    /* renamed from: c */
    LatLng f3543c;
    /* renamed from: d */
    int f3544d;

    public PoiParaOption center(LatLng latLng) {
        this.f3543c = latLng;
        return this;
    }

    public LatLng getCenter() {
        return this.f3543c;
    }

    public String getKey() {
        return this.f3542b;
    }

    public int getRadius() {
        return this.f3544d;
    }

    public String getUid() {
        return this.f3541a;
    }

    public PoiParaOption key(String str) {
        this.f3542b = str;
        return this;
    }

    public PoiParaOption radius(int i) {
        this.f3544d = i;
        return this;
    }

    public PoiParaOption uid(String str) {
        this.f3541a = str;
        return this;
    }
}
