package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public class CoachInfo extends TransitBaseInfo {
    public static final Creator<CoachInfo> CREATOR = new C1150c();
    /* renamed from: a */
    private double f3329a;
    /* renamed from: b */
    private String f3330b;
    /* renamed from: c */
    private String f3331c;
    /* renamed from: d */
    private String f3332d;

    protected CoachInfo(Parcel parcel) {
        super(parcel);
        this.f3329a = parcel.readDouble();
        this.f3330b = parcel.readString();
        this.f3331c = parcel.readString();
        this.f3332d = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public String getBooking() {
        return this.f3330b;
    }

    public double getPrice() {
        return this.f3329a;
    }

    public String getProviderName() {
        return this.f3331c;
    }

    public String getProviderUrl() {
        return this.f3332d;
    }

    public void setBooking(String str) {
        this.f3330b = str;
    }

    public void setPrice(double d) {
        this.f3329a = d;
    }

    public void setProviderName(String str) {
        this.f3331c = str;
    }

    public void setProviderUrl(String str) {
        this.f3332d = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeDouble(this.f3329a);
        parcel.writeString(this.f3330b);
        parcel.writeString(this.f3331c);
        parcel.writeString(this.f3332d);
    }
}
