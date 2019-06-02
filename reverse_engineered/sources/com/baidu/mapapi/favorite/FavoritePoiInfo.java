package com.baidu.mapapi.favorite;

import com.baidu.mapapi.model.LatLng;

public class FavoritePoiInfo {
    /* renamed from: a */
    String f2784a;
    /* renamed from: b */
    String f2785b;
    /* renamed from: c */
    LatLng f2786c;
    /* renamed from: d */
    String f2787d;
    /* renamed from: e */
    String f2788e;
    /* renamed from: f */
    String f2789f;
    /* renamed from: g */
    long f2790g;

    public FavoritePoiInfo addr(String str) {
        this.f2787d = str;
        return this;
    }

    public FavoritePoiInfo cityName(String str) {
        this.f2788e = str;
        return this;
    }

    public String getAddr() {
        return this.f2787d;
    }

    public String getCityName() {
        return this.f2788e;
    }

    public String getID() {
        return this.f2784a;
    }

    public String getPoiName() {
        return this.f2785b;
    }

    public LatLng getPt() {
        return this.f2786c;
    }

    public long getTimeStamp() {
        return this.f2790g;
    }

    public String getUid() {
        return this.f2789f;
    }

    public FavoritePoiInfo poiName(String str) {
        this.f2785b = str;
        return this;
    }

    public FavoritePoiInfo pt(LatLng latLng) {
        this.f2786c = latLng;
        return this;
    }

    public FavoritePoiInfo uid(String str) {
        this.f2789f = str;
        return this;
    }
}
