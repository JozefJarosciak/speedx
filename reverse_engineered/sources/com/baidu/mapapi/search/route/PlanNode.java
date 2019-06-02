package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.baidu.mapapi.model.LatLng;

public class PlanNode implements Parcelable {
    public static final Creator<PlanNode> CREATOR = new C1180m();
    /* renamed from: a */
    private LatLng f3467a = null;
    /* renamed from: b */
    private String f3468b = null;
    /* renamed from: c */
    private String f3469c = null;

    protected PlanNode(Parcel parcel) {
        this.f3467a = (LatLng) parcel.readValue(LatLng.class.getClassLoader());
        this.f3468b = parcel.readString();
        this.f3469c = parcel.readString();
    }

    PlanNode(LatLng latLng, String str, String str2) {
        this.f3467a = latLng;
        this.f3468b = str;
        this.f3469c = str2;
    }

    public static PlanNode withCityCodeAndPlaceName(int i, String str) {
        return new PlanNode(null, String.valueOf(i), str);
    }

    public static PlanNode withCityNameAndPlaceName(String str, String str2) {
        return new PlanNode(null, str, str2);
    }

    public static PlanNode withLocation(LatLng latLng) {
        return new PlanNode(latLng, null, null);
    }

    public int describeContents() {
        return 0;
    }

    public String getCity() {
        return this.f3468b;
    }

    public LatLng getLocation() {
        return this.f3467a;
    }

    public String getName() {
        return this.f3469c;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.f3467a);
        parcel.writeString(this.f3468b);
        parcel.writeString(this.f3469c);
    }
}
