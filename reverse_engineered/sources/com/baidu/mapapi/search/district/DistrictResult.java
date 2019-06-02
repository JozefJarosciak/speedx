package com.baidu.mapapi.search.district;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import java.util.ArrayList;
import java.util.List;

public class DistrictResult extends SearchResult implements Parcelable {
    public static final Creator<DistrictResult> CREATOR = new C1161a();
    public LatLng centerPt = null;
    public int cityCode;
    public String cityName = null;
    public List<List<LatLng>> polylines = null;

    protected DistrictResult(Parcel parcel) {
        super(parcel);
        this.centerPt = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
        int readInt = parcel.readInt();
        if (readInt > 0) {
            this.polylines = new ArrayList();
            for (int i = 0; i < readInt; i++) {
                this.polylines.add(parcel.createTypedArrayList(LatLng.CREATOR));
            }
        }
        this.cityCode = parcel.readInt();
        this.cityName = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public LatLng getCenterPt() {
        return this.centerPt;
    }

    public int getCityCode() {
        return this.cityCode;
    }

    public String getCityName() {
        return this.cityName;
    }

    public List<List<LatLng>> getPolylines() {
        return this.polylines;
    }

    public void setCenterPt(LatLng latLng) {
        this.centerPt = latLng;
    }

    public void setCityCode(int i) {
        this.cityCode = i;
    }

    public void setCityName(String str) {
        this.cityName = str;
    }

    public void setPolylines(List<List<LatLng>> list) {
        this.polylines = list;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.centerPt, i);
        parcel.writeInt(this.polylines == null ? 0 : this.polylines.size());
        for (List writeTypedList : this.polylines) {
            parcel.writeTypedList(writeTypedList);
        }
        parcel.writeInt(this.cityCode);
        parcel.writeString(this.cityName);
    }
}
