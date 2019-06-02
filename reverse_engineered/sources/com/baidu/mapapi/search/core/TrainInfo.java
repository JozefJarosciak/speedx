package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public class TrainInfo extends TransitBaseInfo {
    public static final Creator<TrainInfo> CREATOR = new C1157k();
    /* renamed from: a */
    private double f3355a;
    /* renamed from: b */
    private String f3356b;

    protected TrainInfo(Parcel parcel) {
        super(parcel);
        this.f3355a = parcel.readDouble();
        this.f3356b = parcel.readString();
    }

    /* renamed from: a */
    public void m4371a(double d) {
        this.f3355a = d;
    }

    /* renamed from: a */
    public void m4372a(String str) {
        this.f3356b = str;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeDouble(this.f3355a);
        parcel.writeString(this.f3356b);
    }
}
