package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.baidu.mapapi.search.core.SearchResult;
import java.util.List;

public class IndoorRouteResult extends SearchResult {
    public static final Creator<IndoorRouteResult> CREATOR = new C1175h();
    /* renamed from: a */
    private List<IndoorRouteLine> f3437a;

    protected IndoorRouteResult(Parcel parcel) {
        super(parcel);
        this.f3437a = parcel.createTypedArrayList(IndoorRouteLine.CREATOR);
    }

    public int describeContents() {
        return 0;
    }

    public List<IndoorRouteLine> getRouteLines() {
        return this.f3437a;
    }

    public void setRouteLines(List<IndoorRouteLine> list) {
        this.f3437a = list;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeTypedList(this.f3437a);
    }
}
