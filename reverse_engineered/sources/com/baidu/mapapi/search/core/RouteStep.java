package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.baidu.mapapi.model.LatLng;
import java.util.ArrayList;
import java.util.List;

public class RouteStep implements Parcelable {
    public static final Creator<RouteStep> CREATOR = new C1155h();
    /* renamed from: a */
    int f3307a;
    /* renamed from: b */
    int f3308b;
    protected List<LatLng> mWayPoints;

    protected RouteStep() {
    }

    protected RouteStep(Parcel parcel) {
        this.f3307a = parcel.readInt();
        this.f3308b = parcel.readInt();
        this.mWayPoints = new ArrayList();
        parcel.readList(this.mWayPoints, LatLng.class.getClassLoader());
        if (this.mWayPoints.size() == 0) {
            this.mWayPoints = null;
        }
    }

    public int describeContents() {
        return 0;
    }

    public int getDistance() {
        return this.f3307a;
    }

    public int getDuration() {
        return this.f3308b;
    }

    public List<LatLng> getWayPoints() {
        return this.mWayPoints;
    }

    public void setDistance(int i) {
        this.f3307a = i;
    }

    public void setDuration(int i) {
        this.f3308b = i;
    }

    public void setWayPoints(List<LatLng> list) {
        this.mWayPoints = list;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f3307a);
        parcel.writeInt(this.f3308b);
        parcel.writeList(this.mWayPoints);
    }
}
