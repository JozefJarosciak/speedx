package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class TaxiInfo implements Parcelable {
    public static final Creator<TaxiInfo> CREATOR = new C1156j();
    /* renamed from: a */
    private float f3349a;
    /* renamed from: b */
    private String f3350b;
    /* renamed from: c */
    private int f3351c;
    /* renamed from: d */
    private int f3352d;
    /* renamed from: e */
    private float f3353e;
    /* renamed from: f */
    private float f3354f;

    TaxiInfo(Parcel parcel) {
        this.f3349a = parcel.readFloat();
        this.f3350b = parcel.readString();
        this.f3351c = parcel.readInt();
        this.f3352d = parcel.readInt();
        this.f3353e = parcel.readFloat();
        this.f3354f = parcel.readFloat();
    }

    public int describeContents() {
        return 0;
    }

    public String getDesc() {
        return this.f3350b;
    }

    public int getDistance() {
        return this.f3351c;
    }

    public int getDuration() {
        return this.f3352d;
    }

    public float getPerKMPrice() {
        return this.f3353e;
    }

    public float getStartPrice() {
        return this.f3354f;
    }

    public float getTotalPrice() {
        return this.f3349a;
    }

    public void setDesc(String str) {
        this.f3350b = str;
    }

    public void setDistance(int i) {
        this.f3351c = i;
    }

    public void setDuration(int i) {
        this.f3352d = i;
    }

    public void setPerKMPrice(float f) {
        this.f3353e = f;
    }

    public void setStartPrice(float f) {
        this.f3354f = f;
    }

    public void setTotalPrice(float f) {
        this.f3349a = f;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.f3349a);
        parcel.writeString(this.f3350b);
        parcel.writeInt(this.f3351c);
        parcel.writeInt(this.f3352d);
        parcel.writeFloat(this.f3353e);
        parcel.writeFloat(this.f3354f);
    }
}
