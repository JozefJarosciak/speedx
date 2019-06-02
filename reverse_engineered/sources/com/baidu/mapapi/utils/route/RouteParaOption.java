package com.baidu.mapapi.utils.route;

import com.baidu.mapapi.model.LatLng;

public class RouteParaOption {
    /* renamed from: a */
    LatLng f3548a;
    /* renamed from: b */
    LatLng f3549b;
    /* renamed from: c */
    String f3550c;
    /* renamed from: d */
    String f3551d;
    /* renamed from: e */
    String f3552e;
    /* renamed from: f */
    EBusStrategyType f3553f = EBusStrategyType.bus_recommend_way;

    public enum EBusStrategyType {
        bus_time_first,
        bus_transfer_little,
        bus_walk_little,
        bus_no_subway,
        bus_recommend_way
    }

    public RouteParaOption busStrategyType(EBusStrategyType eBusStrategyType) {
        this.f3553f = eBusStrategyType;
        return this;
    }

    public RouteParaOption cityName(String str) {
        this.f3552e = str;
        return this;
    }

    public RouteParaOption endName(String str) {
        this.f3551d = str;
        return this;
    }

    public RouteParaOption endPoint(LatLng latLng) {
        this.f3549b = latLng;
        return this;
    }

    public EBusStrategyType getBusStrategyType() {
        return this.f3553f;
    }

    public String getCityName() {
        return this.f3552e;
    }

    public String getEndName() {
        return this.f3551d;
    }

    public LatLng getEndPoint() {
        return this.f3549b;
    }

    public String getStartName() {
        return this.f3550c;
    }

    public LatLng getStartPoint() {
        return this.f3548a;
    }

    public RouteParaOption startName(String str) {
        this.f3550c = str;
        return this;
    }

    public RouteParaOption startPoint(LatLng latLng) {
        this.f3548a = latLng;
        return this;
    }
}
