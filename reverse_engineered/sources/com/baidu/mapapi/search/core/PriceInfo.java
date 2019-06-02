package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class PriceInfo implements Parcelable {
    public static final Creator<PriceInfo> CREATOR = new C1153f();
    /* renamed from: a */
    private int f3338a;
    /* renamed from: b */
    private double f3339b;

    protected PriceInfo(Parcel parcel) {
        this.f3338a = parcel.readInt();
        this.f3339b = parcel.readDouble();
    }

    public int describeContents() {
        return 0;
    }

    public double getTicketPrice() {
        return this.f3339b;
    }

    public int getTicketType() {
        return this.f3338a;
    }

    public void setTicketPrice(double d) {
        this.f3339b = d;
    }

    public void setTicketType(int i) {
        this.f3338a = i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f3338a);
        parcel.writeDouble(this.f3339b);
    }
}
