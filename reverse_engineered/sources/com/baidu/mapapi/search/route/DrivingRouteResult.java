package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.core.TaxiInfo;
import java.util.ArrayList;
import java.util.List;

public final class DrivingRouteResult extends SearchResult implements Parcelable {
    public static final Creator<DrivingRouteResult> CREATOR = new C1173f();
    /* renamed from: a */
    private List<DrivingRouteLine> f3420a;
    /* renamed from: b */
    private List<TaxiInfo> f3421b;
    /* renamed from: c */
    private TaxiInfo f3422c;
    /* renamed from: d */
    private SuggestAddrInfo f3423d;

    protected DrivingRouteResult(Parcel parcel) {
        this.f3420a = new ArrayList();
        parcel.readTypedList(this.f3420a, DrivingRouteLine.CREATOR);
        this.f3421b = new ArrayList();
        parcel.readTypedList(this.f3421b, TaxiInfo.CREATOR);
        this.f3423d = (SuggestAddrInfo) parcel.readParcelable(SuggestAddrInfo.class.getClassLoader());
    }

    public int describeContents() {
        return 0;
    }

    public List<DrivingRouteLine> getRouteLines() {
        return this.f3420a;
    }

    public SuggestAddrInfo getSuggestAddrInfo() {
        return this.f3423d;
    }

    @Deprecated
    public TaxiInfo getTaxiInfo() {
        return this.f3422c;
    }

    public List<TaxiInfo> getTaxiInfos() {
        return this.f3421b;
    }

    public void setRouteLines(List<DrivingRouteLine> list) {
        this.f3420a = list;
    }

    public void setSuggestAddrInfo(SuggestAddrInfo suggestAddrInfo) {
        this.f3423d = suggestAddrInfo;
    }

    public void setTaxiInfos(List<TaxiInfo> list) {
        this.f3421b = list;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.f3420a);
        parcel.writeTypedList(this.f3421b);
        parcel.writeParcelable(this.f3423d, 1);
    }
}
