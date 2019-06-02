package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.baidu.mapapi.search.core.SearchResult;
import java.util.ArrayList;
import java.util.List;

public class BikingRouteResult extends SearchResult implements Parcelable {
    public static final Creator<BikingRouteLine> CREATOR = new C1170c();
    /* renamed from: a */
    private List<BikingRouteLine> f3400a;
    /* renamed from: b */
    private SuggestAddrInfo f3401b;

    protected BikingRouteResult(Parcel parcel) {
        this.f3400a = new ArrayList();
        parcel.readList(this.f3400a, BikingRouteLine.class.getClassLoader());
        this.f3401b = (SuggestAddrInfo) parcel.readParcelable(SuggestAddrInfo.class.getClassLoader());
    }

    public int describeContents() {
        return 0;
    }

    public List<BikingRouteLine> getRouteLines() {
        return this.f3400a;
    }

    public SuggestAddrInfo getSuggestAddrInfo() {
        return this.f3401b;
    }

    public void setRouteLines(List<BikingRouteLine> list) {
        this.f3400a = list;
    }

    public void setSuggestAddrInfo(SuggestAddrInfo suggestAddrInfo) {
        this.f3401b = suggestAddrInfo;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.f3400a);
        parcel.writeParcelable(this.f3401b, 1);
    }
}
