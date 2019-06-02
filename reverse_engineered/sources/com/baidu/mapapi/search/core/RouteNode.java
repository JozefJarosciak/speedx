package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.baidu.mapapi.model.LatLng;

public class RouteNode implements Parcelable {
    public static final Creator<RouteNode> CREATOR = new C1154g();
    /* renamed from: a */
    private String f3304a;
    /* renamed from: b */
    private LatLng f3305b;
    /* renamed from: c */
    private String f3306c;

    protected RouteNode(Parcel parcel) {
        this.f3304a = parcel.readString();
        this.f3305b = (LatLng) parcel.readValue(LatLng.class.getClassLoader());
        this.f3306c = parcel.readString();
    }

    public static RouteNode location(LatLng latLng) {
        RouteNode routeNode = new RouteNode();
        routeNode.setLocation(latLng);
        return routeNode;
    }

    public static RouteNode titleAndLocation(String str, LatLng latLng) {
        RouteNode routeNode = new RouteNode();
        routeNode.setTitle(str);
        routeNode.setLocation(latLng);
        return routeNode;
    }

    public int describeContents() {
        return 0;
    }

    public LatLng getLocation() {
        return this.f3305b;
    }

    public String getTitle() {
        return this.f3304a;
    }

    public String getUid() {
        return this.f3306c;
    }

    public void setLocation(LatLng latLng) {
        this.f3305b = latLng;
    }

    public void setTitle(String str) {
        this.f3304a = str;
    }

    public void setUid(String str) {
        this.f3306c = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f3304a);
        parcel.writeValue(this.f3305b);
        parcel.writeString(this.f3306c);
    }
}
