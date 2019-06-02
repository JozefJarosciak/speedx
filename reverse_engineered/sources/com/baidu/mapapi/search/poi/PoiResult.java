package com.baidu.mapapi.search.poi;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.baidu.mapapi.search.core.CityInfo;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import java.util.List;

public class PoiResult extends SearchResult implements Parcelable {
    public static final Creator<PoiResult> CREATOR = new C1167c();
    /* renamed from: a */
    private int f3382a = 0;
    /* renamed from: b */
    private int f3383b = 0;
    /* renamed from: c */
    private int f3384c = 0;
    /* renamed from: d */
    private int f3385d = 0;
    /* renamed from: e */
    private List<PoiInfo> f3386e;
    /* renamed from: f */
    private List<CityInfo> f3387f;
    /* renamed from: g */
    private List<PoiAddrInfo> f3388g;
    /* renamed from: h */
    private boolean f3389h = false;

    PoiResult(Parcel parcel) {
        this.f3382a = parcel.readInt();
        this.f3383b = parcel.readInt();
        this.f3384c = parcel.readInt();
        this.f3385d = parcel.readInt();
        this.f3386e = parcel.readArrayList(PoiInfo.class.getClassLoader());
        this.f3387f = parcel.readArrayList(CityInfo.class.getClassLoader());
    }

    public int describeContents() {
        return 0;
    }

    public List<PoiAddrInfo> getAllAddr() {
        return this.f3388g;
    }

    public List<PoiInfo> getAllPoi() {
        return this.f3386e;
    }

    public int getCurrentPageCapacity() {
        return this.f3384c;
    }

    public int getCurrentPageNum() {
        return this.f3382a;
    }

    public List<CityInfo> getSuggestCityList() {
        return this.f3387f;
    }

    public int getTotalPageNum() {
        return this.f3383b;
    }

    public int getTotalPoiNum() {
        return this.f3385d;
    }

    public boolean isHasAddrInfo() {
        return this.f3389h;
    }

    public void setAddrInfo(List<PoiAddrInfo> list) {
        this.f3388g = list;
    }

    public void setCurrentPageCapacity(int i) {
        this.f3384c = i;
    }

    public void setCurrentPageNum(int i) {
        this.f3382a = i;
    }

    public void setHasAddrInfo(boolean z) {
        this.f3389h = z;
    }

    public void setPoiInfo(List<PoiInfo> list) {
        this.f3386e = list;
    }

    public void setSuggestCityList(List<CityInfo> list) {
        this.f3387f = list;
    }

    public void setTotalPageNum(int i) {
        this.f3383b = i;
    }

    public void setTotalPoiNum(int i) {
        this.f3385d = i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f3382a);
        parcel.writeInt(this.f3383b);
        parcel.writeInt(this.f3384c);
        parcel.writeInt(this.f3385d);
        parcel.writeList(this.f3386e);
        parcel.writeList(this.f3387f);
    }
}
