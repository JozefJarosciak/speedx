package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public class BusInfo extends TransitBaseInfo {
    public static final Creator<BusInfo> CREATOR = new C1148a();
    /* renamed from: a */
    private int f3327a;
    /* renamed from: b */
    private int f3328b;

    protected BusInfo(Parcel parcel) {
        super(parcel);
        this.f3327a = parcel.readInt();
        this.f3328b = parcel.readInt();
    }

    public int describeContents() {
        return 0;
    }

    public int getStopNum() {
        return this.f3328b;
    }

    public int getType() {
        return this.f3327a;
    }

    public void setStopNum(int i) {
        this.f3328b = i;
    }

    public void setType(int i) {
        this.f3327a = i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.f3327a);
        parcel.writeInt(this.f3328b);
    }
}
