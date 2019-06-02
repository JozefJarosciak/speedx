package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.core.TaxiInfo;
import java.util.ArrayList;
import java.util.List;

public class WalkingRouteResult extends SearchResult implements Parcelable {
    public static final Creator<WalkingRouteResult> CREATOR = new C1187t();
    /* renamed from: a */
    private List<WalkingRouteLine> f3497a;
    /* renamed from: b */
    private TaxiInfo f3498b;
    /* renamed from: c */
    private SuggestAddrInfo f3499c;

    protected WalkingRouteResult(Parcel parcel) {
        this.f3497a = new ArrayList();
        parcel.readList(this.f3497a, WalkingRouteLine.class.getClassLoader());
        this.f3498b = (TaxiInfo) parcel.readParcelable(TaxiInfo.class.getClassLoader());
        this.f3499c = (SuggestAddrInfo) parcel.readParcelable(SuggestAddrInfo.class.getClassLoader());
    }

    public int describeContents() {
        return 0;
    }

    public List<WalkingRouteLine> getRouteLines() {
        return this.f3497a;
    }

    public SuggestAddrInfo getSuggestAddrInfo() {
        return this.f3499c;
    }

    public TaxiInfo getTaxiInfo() {
        return this.f3498b;
    }

    public void setRouteLines(List<WalkingRouteLine> list) {
        this.f3497a = list;
    }

    public void setSuggestAddrInfo(SuggestAddrInfo suggestAddrInfo) {
        this.f3499c = suggestAddrInfo;
    }

    public void setTaxiInfo(TaxiInfo taxiInfo) {
        this.f3498b = taxiInfo;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.f3497a);
        parcel.writeParcelable(this.f3498b, 1);
        parcel.writeParcelable(this.f3499c, 1);
    }
}
