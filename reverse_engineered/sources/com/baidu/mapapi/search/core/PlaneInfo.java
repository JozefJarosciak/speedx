package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public class PlaneInfo extends TransitBaseInfo {
    public static final Creator<PlaneInfo> CREATOR = new C1151d();
    /* renamed from: a */
    private double f3333a;
    /* renamed from: b */
    private String f3334b;
    /* renamed from: c */
    private double f3335c;
    /* renamed from: d */
    private String f3336d;

    protected PlaneInfo(Parcel parcel) {
        super(parcel);
        this.f3333a = parcel.readDouble();
        this.f3334b = parcel.readString();
        this.f3335c = parcel.readDouble();
        this.f3336d = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public String getAirlines() {
        return this.f3334b;
    }

    public String getBooking() {
        return this.f3336d;
    }

    public double getDiscount() {
        return this.f3333a;
    }

    public double getPrice() {
        return this.f3335c;
    }

    public void setAirlines(String str) {
        this.f3334b = str;
    }

    public void setBooking(String str) {
        this.f3336d = str;
    }

    public void setDiscount(double d) {
        this.f3333a = d;
    }

    public void setPrice(double d) {
        this.f3335c = d;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeDouble(this.f3333a);
        parcel.writeString(this.f3334b);
        parcel.writeDouble(this.f3335c);
        parcel.writeString(this.f3336d);
    }
}
