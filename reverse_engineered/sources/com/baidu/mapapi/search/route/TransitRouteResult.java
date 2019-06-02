package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.core.TaxiInfo;
import java.util.ArrayList;
import java.util.List;

public final class TransitRouteResult extends SearchResult implements Parcelable {
    public static final Creator<TransitRouteResult> CREATOR = new C1184q();
    /* renamed from: a */
    private TaxiInfo f3487a;
    /* renamed from: b */
    private List<TransitRouteLine> f3488b;
    /* renamed from: c */
    private SuggestAddrInfo f3489c;

    protected TransitRouteResult(Parcel parcel) {
        this.f3487a = (TaxiInfo) parcel.readParcelable(TaxiInfo.class.getClassLoader());
        this.f3488b = new ArrayList();
        parcel.readList(this.f3488b, TransitRouteLine.class.getClassLoader());
        this.f3489c = (SuggestAddrInfo) parcel.readParcelable(SuggestAddrInfo.class.getClassLoader());
    }

    public int describeContents() {
        return 0;
    }

    public List<TransitRouteLine> getRouteLines() {
        return this.f3488b;
    }

    public SuggestAddrInfo getSuggestAddrInfo() {
        return this.f3489c;
    }

    public TaxiInfo getTaxiInfo() {
        return this.f3487a;
    }

    public void setRoutelines(List<TransitRouteLine> list) {
        this.f3488b = list;
    }

    public void setSuggestAddrInfo(SuggestAddrInfo suggestAddrInfo) {
        this.f3489c = suggestAddrInfo;
    }

    public void setTaxiInfo(TaxiInfo taxiInfo) {
        this.f3487a = taxiInfo;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f3487a, 1);
        parcel.writeList(this.f3488b);
        parcel.writeParcelable(this.f3489c, 1);
    }
}
