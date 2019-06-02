package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.core.TaxiInfo;
import com.baidu.mapapi.search.core.TransitResultNode;
import java.util.ArrayList;
import java.util.List;

public final class MassTransitRouteResult extends SearchResult implements Parcelable {
    public static final Creator<MassTransitRouteResult> CREATOR = new C1179l();
    /* renamed from: a */
    private TransitResultNode f3461a;
    /* renamed from: b */
    private TransitResultNode f3462b;
    /* renamed from: c */
    private TaxiInfo f3463c;
    /* renamed from: d */
    private int f3464d;
    /* renamed from: e */
    private List<MassTransitRouteLine> f3465e;
    /* renamed from: f */
    private SuggestAddrInfo f3466f;

    MassTransitRouteResult(Parcel parcel) {
        this.f3461a = (TransitResultNode) parcel.readParcelable(TransitResultNode.class.getClassLoader());
        this.f3462b = (TransitResultNode) parcel.readParcelable(TransitResultNode.class.getClassLoader());
        this.f3463c = (TaxiInfo) parcel.readParcelable(TaxiInfo.class.getClassLoader());
        this.f3464d = parcel.readInt();
        this.f3465e = new ArrayList();
        parcel.readList(this.f3465e, MassTransitRouteLine.class.getClassLoader());
        this.f3466f = (SuggestAddrInfo) parcel.readParcelable(SuggestAddrInfo.class.getClassLoader());
    }

    public int describeContents() {
        return 0;
    }

    public TransitResultNode getDestination() {
        return this.f3462b;
    }

    public TransitResultNode getOrigin() {
        return this.f3461a;
    }

    public List<MassTransitRouteLine> getRouteLines() {
        return this.f3465e;
    }

    public SuggestAddrInfo getSuggestAddrInfo() {
        return this.f3466f;
    }

    public TaxiInfo getTaxiInfo() {
        return this.f3463c;
    }

    public int getTotal() {
        return this.f3464d;
    }

    public void setDestination(TransitResultNode transitResultNode) {
        this.f3462b = transitResultNode;
    }

    public void setOrigin(TransitResultNode transitResultNode) {
        this.f3461a = transitResultNode;
    }

    public void setRoutelines(List<MassTransitRouteLine> list) {
        this.f3465e = list;
    }

    public void setSuggestAddrInfo(SuggestAddrInfo suggestAddrInfo) {
        this.f3466f = suggestAddrInfo;
    }

    public void setTaxiInfo(TaxiInfo taxiInfo) {
        this.f3463c = taxiInfo;
    }

    public void setTotal(int i) {
        this.f3464d = i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f3461a, 1);
        parcel.writeParcelable(this.f3462b, 1);
        parcel.writeParcelable(this.f3463c, 1);
        parcel.writeInt(this.f3464d);
        parcel.writeList(this.f3465e);
        parcel.writeParcelable(this.f3466f, 1);
    }
}
