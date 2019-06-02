package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class TransitBaseInfo implements Parcelable {
    public static final Creator<TransitBaseInfo> CREATOR = new C1158l();
    /* renamed from: a */
    private String f3322a;
    /* renamed from: b */
    private String f3323b;
    /* renamed from: c */
    private String f3324c;
    /* renamed from: d */
    private String f3325d;
    /* renamed from: e */
    private String f3326e;

    protected TransitBaseInfo(Parcel parcel) {
        this.f3322a = parcel.readString();
        this.f3323b = parcel.readString();
        this.f3324c = parcel.readString();
        this.f3325d = parcel.readString();
        this.f3326e = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public String getArriveStation() {
        return this.f3324c;
    }

    public String getArriveTime() {
        return this.f3326e;
    }

    public String getDepartureStation() {
        return this.f3323b;
    }

    public String getDepartureTime() {
        return this.f3325d;
    }

    public String getName() {
        return this.f3322a;
    }

    public void setArriveStation(String str) {
        this.f3324c = str;
    }

    public void setArriveTime(String str) {
        this.f3326e = str;
    }

    public void setDepartureStation(String str) {
        this.f3323b = str;
    }

    public void setDepartureTime(String str) {
        this.f3325d = str;
    }

    public void setName(String str) {
        this.f3322a = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f3322a);
        parcel.writeString(this.f3323b);
        parcel.writeString(this.f3324c);
        parcel.writeString(this.f3325d);
        parcel.writeString(this.f3326e);
    }
}
