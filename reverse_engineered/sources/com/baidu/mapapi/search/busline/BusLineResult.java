package com.baidu.mapapi.search.busline;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.baidu.mapapi.search.core.RouteNode;
import com.baidu.mapapi.search.core.RouteStep;
import com.baidu.mapapi.search.core.SearchResult;
import java.util.Date;
import java.util.List;

public class BusLineResult extends SearchResult implements Parcelable {
    public static final Creator<BusLineResult> CREATOR = new C1147a();
    /* renamed from: a */
    private String f3309a = null;
    /* renamed from: b */
    private String f3310b = null;
    /* renamed from: c */
    private boolean f3311c;
    /* renamed from: d */
    private Date f3312d;
    /* renamed from: e */
    private Date f3313e;
    /* renamed from: f */
    private String f3314f;
    /* renamed from: g */
    private List<BusStation> f3315g = null;
    /* renamed from: h */
    private List<BusStep> f3316h = null;
    /* renamed from: i */
    private float f3317i;
    /* renamed from: j */
    private float f3318j;
    /* renamed from: k */
    private String f3319k = null;

    public static class BusStation extends RouteNode {
    }

    public static class BusStep extends RouteStep {
    }

    BusLineResult(Parcel parcel) {
        this.f3309a = parcel.readString();
        this.f3310b = parcel.readString();
        this.f3311c = ((Boolean) parcel.readValue(Boolean.class.getClassLoader())).booleanValue();
        this.f3312d = (Date) parcel.readValue(Date.class.getClassLoader());
        this.f3313e = (Date) parcel.readValue(Date.class.getClassLoader());
        this.f3314f = parcel.readString();
        this.f3315g = parcel.readArrayList(BusStation.class.getClassLoader());
        this.f3316h = parcel.readArrayList(RouteStep.class.getClassLoader());
    }

    public int describeContents() {
        return 0;
    }

    public float getBasePrice() {
        return this.f3317i;
    }

    public String getBusCompany() {
        return this.f3309a;
    }

    public String getBusLineName() {
        return this.f3310b;
    }

    public Date getEndTime() {
        return this.f3313e;
    }

    public String getLineDirection() {
        return this.f3319k;
    }

    public float getMaxPrice() {
        return this.f3318j;
    }

    public Date getStartTime() {
        return this.f3312d;
    }

    public List<BusStation> getStations() {
        return this.f3315g;
    }

    public List<BusStep> getSteps() {
        return this.f3316h;
    }

    public String getUid() {
        return this.f3314f;
    }

    public boolean isMonthTicket() {
        return this.f3311c;
    }

    public void setBasePrice(float f) {
        this.f3317i = f;
    }

    public void setBusLineName(String str) {
        this.f3310b = str;
    }

    public void setEndTime(Date date) {
        this.f3313e = date;
    }

    public void setLineDirection(String str) {
        this.f3319k = str;
    }

    public void setMaxPrice(float f) {
        this.f3318j = f;
    }

    public void setMonthTicket(boolean z) {
        this.f3311c = z;
    }

    public void setStartTime(Date date) {
        this.f3312d = date;
    }

    public void setStations(List<BusStation> list) {
        this.f3315g = list;
    }

    public void setSteps(List<BusStep> list) {
        this.f3316h = list;
    }

    public void setUid(String str) {
        this.f3314f = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f3309a);
        parcel.writeString(this.f3310b);
        parcel.writeValue(Boolean.valueOf(this.f3311c));
        parcel.writeValue(this.f3312d);
        parcel.writeValue(this.f3313e);
        parcel.writeString(this.f3314f);
        parcel.writeList(this.f3315g);
        parcel.writeList(this.f3316h);
    }
}
