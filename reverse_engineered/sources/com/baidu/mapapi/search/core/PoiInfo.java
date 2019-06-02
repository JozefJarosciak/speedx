package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.baidu.mapapi.model.LatLng;

public class PoiInfo implements Parcelable {
    public static final Creator<PoiInfo> CREATOR = new C1152e();
    public String address;
    public String city;
    public boolean hasCaterDetails;
    public boolean isPano;
    public LatLng location;
    public String name;
    public String phoneNum;
    public String postCode;
    public POITYPE type;
    public String uid;

    public enum POITYPE {
        POINT(0),
        BUS_STATION(1),
        BUS_LINE(2),
        SUBWAY_STATION(3),
        SUBWAY_LINE(4);
        
        /* renamed from: a */
        private int f3337a;

        private POITYPE(int i) {
        }

        public static POITYPE fromInt(int i) {
            switch (i) {
                case 0:
                    return POINT;
                case 1:
                    return BUS_STATION;
                case 2:
                    return BUS_LINE;
                case 3:
                    return SUBWAY_STATION;
                case 4:
                    return SUBWAY_LINE;
                default:
                    return null;
            }
        }

        public int getInt() {
            return this.f3337a;
        }
    }

    protected PoiInfo(Parcel parcel) {
        this.name = parcel.readString();
        this.uid = parcel.readString();
        this.address = parcel.readString();
        this.city = parcel.readString();
        this.phoneNum = parcel.readString();
        this.postCode = parcel.readString();
        this.type = (POITYPE) parcel.readValue(POITYPE.class.getClassLoader());
        this.location = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
        this.hasCaterDetails = ((Boolean) parcel.readValue(Boolean.class.getClassLoader())).booleanValue();
        this.isPano = ((Boolean) parcel.readValue(Boolean.class.getClassLoader())).booleanValue();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeString(this.uid);
        parcel.writeString(this.address);
        parcel.writeString(this.city);
        parcel.writeString(this.phoneNum);
        parcel.writeString(this.postCode);
        parcel.writeValue(this.type);
        parcel.writeParcelable(this.location, 1);
        parcel.writeValue(Boolean.valueOf(this.hasCaterDetails));
        parcel.writeValue(Boolean.valueOf(this.isPano));
    }
}
