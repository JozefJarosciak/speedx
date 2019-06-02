package com.baidu.mapapi.search.geocode;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import java.util.List;

public class ReverseGeoCodeResult extends SearchResult {
    public static final Creator<ReverseGeoCodeResult> CREATOR = new C1163b();
    /* renamed from: a */
    private String f3372a;
    /* renamed from: b */
    private String f3373b;
    /* renamed from: c */
    private AddressComponent f3374c;
    /* renamed from: d */
    private LatLng f3375d;
    /* renamed from: e */
    private List<PoiInfo> f3376e;
    /* renamed from: f */
    private String f3377f;

    public static class AddressComponent implements Parcelable {
        public static final Creator<AddressComponent> CREATOR = new C1164c();
        public String city;
        public int countryCode;
        public String countryName;
        public String district;
        public String province;
        public String street;
        public String streetNumber;

        protected AddressComponent(Parcel parcel) {
            this.streetNumber = parcel.readString();
            this.street = parcel.readString();
            this.district = parcel.readString();
            this.city = parcel.readString();
            this.province = parcel.readString();
            this.countryName = parcel.readString();
            this.countryCode = parcel.readInt();
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.streetNumber);
            parcel.writeString(this.street);
            parcel.writeString(this.district);
            parcel.writeString(this.city);
            parcel.writeString(this.province);
            parcel.writeString(this.countryName);
            parcel.writeInt(this.countryCode);
        }
    }

    protected ReverseGeoCodeResult(Parcel parcel) {
        super(parcel);
        this.f3372a = parcel.readString();
        this.f3373b = parcel.readString();
        this.f3374c = (AddressComponent) parcel.readParcelable(AddressComponent.class.getClassLoader());
        this.f3375d = (LatLng) parcel.readValue(LatLng.class.getClassLoader());
        this.f3376e = parcel.createTypedArrayList(PoiInfo.CREATOR);
        this.f3377f = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public String getAddress() {
        return this.f3373b;
    }

    public AddressComponent getAddressDetail() {
        return this.f3374c;
    }

    public String getBusinessCircle() {
        return this.f3372a;
    }

    public LatLng getLocation() {
        return this.f3375d;
    }

    public List<PoiInfo> getPoiList() {
        return this.f3376e;
    }

    public String getSematicDescription() {
        return this.f3377f;
    }

    public void setAddress(String str) {
        this.f3373b = str;
    }

    public void setAddressDetail(AddressComponent addressComponent) {
        this.f3374c = addressComponent;
    }

    public void setBusinessCircle(String str) {
        this.f3372a = str;
    }

    public void setLocation(LatLng latLng) {
        this.f3375d = latLng;
    }

    public void setPoiList(List<PoiInfo> list) {
        this.f3376e = list;
    }

    public void setSematicDescription(String str) {
        this.f3377f = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.f3372a);
        parcel.writeString(this.f3373b);
        parcel.writeParcelable(this.f3374c, 0);
        parcel.writeValue(this.f3375d);
        parcel.writeTypedList(this.f3376e);
        parcel.writeString(this.f3377f);
    }
}
