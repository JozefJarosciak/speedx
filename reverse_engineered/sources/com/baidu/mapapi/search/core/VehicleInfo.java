package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class VehicleInfo implements Parcelable {
    public static final Creator<VehicleInfo> CREATOR = new C1160n();
    /* renamed from: a */
    private String f3361a;
    /* renamed from: b */
    private int f3362b;
    /* renamed from: c */
    private String f3363c;
    /* renamed from: d */
    private int f3364d;
    /* renamed from: e */
    private int f3365e;

    protected VehicleInfo(Parcel parcel) {
        this.f3361a = parcel.readString();
        this.f3362b = parcel.readInt();
        this.f3363c = parcel.readString();
        this.f3364d = parcel.readInt();
        this.f3365e = parcel.readInt();
    }

    public int describeContents() {
        return 0;
    }

    public int getPassStationNum() {
        return this.f3362b;
    }

    public String getTitle() {
        return this.f3363c;
    }

    public int getTotalPrice() {
        return this.f3365e;
    }

    public String getUid() {
        return this.f3361a;
    }

    public int getZonePrice() {
        return this.f3364d;
    }

    public void setPassStationNum(int i) {
        this.f3362b = i;
    }

    public void setTitle(String str) {
        this.f3363c = str;
    }

    public void setTotalPrice(int i) {
        this.f3365e = i;
    }

    public void setUid(String str) {
        this.f3361a = str;
    }

    public void setZonePrice(int i) {
        this.f3364d = i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f3361a);
        parcel.writeInt(this.f3362b);
        parcel.writeString(this.f3363c);
        parcel.writeInt(this.f3364d);
        parcel.writeInt(this.f3365e);
    }
}
