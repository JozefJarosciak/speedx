package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.baidu.mapapi.search.core.CityInfo;
import com.baidu.mapapi.search.core.PoiInfo;
import java.util.List;

public class SuggestAddrInfo implements Parcelable {
    public static final Creator<SuggestAddrInfo> CREATOR = new C1181n();
    /* renamed from: a */
    private List<PoiInfo> f3472a;
    /* renamed from: b */
    private List<PoiInfo> f3473b;
    /* renamed from: c */
    private List<List<PoiInfo>> f3474c;
    /* renamed from: d */
    private List<CityInfo> f3475d;
    /* renamed from: e */
    private List<CityInfo> f3476e;
    /* renamed from: f */
    private List<List<CityInfo>> f3477f;

    SuggestAddrInfo(Parcel parcel) {
        this.f3472a = parcel.readArrayList(PoiInfo.class.getClassLoader());
        this.f3473b = parcel.readArrayList(PoiInfo.class.getClassLoader());
        this.f3474c = parcel.readArrayList(PoiInfo.class.getClassLoader());
        this.f3475d = parcel.readArrayList(CityInfo.class.getClassLoader());
        this.f3476e = parcel.readArrayList(CityInfo.class.getClassLoader());
        this.f3477f = parcel.readArrayList(CityInfo.class.getClassLoader());
    }

    public int describeContents() {
        return 0;
    }

    public List<CityInfo> getSuggestEndCity() {
        return this.f3476e;
    }

    public List<PoiInfo> getSuggestEndNode() {
        return this.f3473b;
    }

    public List<CityInfo> getSuggestStartCity() {
        return this.f3475d;
    }

    public List<PoiInfo> getSuggestStartNode() {
        return this.f3472a;
    }

    public List<List<CityInfo>> getSuggestWpCity() {
        return this.f3477f;
    }

    public List<List<PoiInfo>> getSuggestWpNode() {
        return this.f3474c;
    }

    public void setSuggestEndCity(List<CityInfo> list) {
        this.f3476e = list;
    }

    public void setSuggestEndNode(List<PoiInfo> list) {
        this.f3473b = list;
    }

    public void setSuggestStartCity(List<CityInfo> list) {
        this.f3475d = list;
    }

    public void setSuggestStartNode(List<PoiInfo> list) {
        this.f3472a = list;
    }

    public void setSuggestWpCity(List<List<CityInfo>> list) {
        this.f3477f = list;
    }

    public void setSuggestWpNode(List<List<PoiInfo>> list) {
        this.f3474c = list;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.f3472a);
        parcel.writeList(this.f3473b);
        parcel.writeList(this.f3474c);
        parcel.writeList(this.f3475d);
        parcel.writeList(this.f3476e);
        parcel.writeList(this.f3477f);
    }
}
