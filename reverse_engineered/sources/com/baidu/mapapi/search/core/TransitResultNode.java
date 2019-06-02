package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.baidu.mapapi.model.LatLng;

public class TransitResultNode implements Parcelable {
    public static final Creator<TransitResultNode> CREATOR = new C1159m();
    /* renamed from: a */
    private int f3357a;
    /* renamed from: b */
    private String f3358b = null;
    /* renamed from: c */
    private LatLng f3359c = null;
    /* renamed from: d */
    private String f3360d = null;

    public TransitResultNode(int i, String str, LatLng latLng, String str2) {
        this.f3357a = i;
        this.f3358b = str;
        this.f3359c = latLng;
        this.f3360d = str2;
    }

    protected TransitResultNode(Parcel parcel) {
        this.f3357a = parcel.readInt();
        this.f3358b = parcel.readString();
        this.f3359c = (LatLng) parcel.readValue(LatLng.class.getClassLoader());
        this.f3360d = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public int getCityId() {
        return this.f3357a;
    }

    public String getCityName() {
        return this.f3358b;
    }

    public LatLng getLocation() {
        return this.f3359c;
    }

    public String getSearchWord() {
        return this.f3360d;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f3357a);
        parcel.writeString(this.f3358b);
        parcel.writeValue(this.f3359c);
        parcel.writeString(this.f3360d);
    }
}
