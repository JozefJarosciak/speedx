package com.baidu.mapapi.search.geocode;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;

public class GeoCodeResult extends SearchResult implements Parcelable {
    public static final Creator<GeoCodeResult> CREATOR = new C1162a();
    /* renamed from: a */
    private LatLng f3368a;
    /* renamed from: b */
    private String f3369b;

    protected GeoCodeResult(Parcel parcel) {
        this.f3368a = (LatLng) parcel.readValue(LatLng.class.getClassLoader());
        this.f3369b = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public String getAddress() {
        return this.f3369b;
    }

    public LatLng getLocation() {
        return this.f3368a;
    }

    public void setAddress(String str) {
        this.f3369b = str;
    }

    public void setLocation(LatLng latLng) {
        this.f3368a = latLng;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.f3368a);
        parcel.writeString(this.f3369b);
    }
}
