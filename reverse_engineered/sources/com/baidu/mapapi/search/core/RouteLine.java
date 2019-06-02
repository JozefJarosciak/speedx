package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.search.route.BikingRouteLine.BikingStep;
import com.baidu.mapapi.search.route.DrivingRouteLine.DrivingStep;
import com.baidu.mapapi.search.route.TransitRouteLine.TransitStep;
import com.baidu.mapapi.search.route.WalkingRouteLine.WalkingStep;
import java.util.List;

public class RouteLine<T extends RouteStep> implements Parcelable {
    /* renamed from: a */
    TYPE f3342a;
    /* renamed from: b */
    private RouteNode f3343b;
    /* renamed from: c */
    private RouteNode f3344c;
    /* renamed from: d */
    private String f3345d;
    /* renamed from: e */
    private List<T> f3346e;
    /* renamed from: f */
    private int f3347f;
    /* renamed from: g */
    private int f3348g;

    protected enum TYPE {
        DRIVESTEP(0),
        TRANSITSTEP(1),
        WALKSTEP(2),
        BIKINGSTEP(3);
        
        /* renamed from: a */
        private int f3341a;

        private TYPE(int i) {
            this.f3341a = i;
        }

        /* renamed from: a */
        private int m4369a() {
            return this.f3341a;
        }
    }

    protected RouteLine() {
    }

    protected RouteLine(Parcel parcel) {
        int readInt = parcel.readInt();
        this.f3343b = (RouteNode) parcel.readValue(RouteNode.class.getClassLoader());
        this.f3344c = (RouteNode) parcel.readValue(RouteNode.class.getClassLoader());
        this.f3345d = parcel.readString();
        switch (readInt) {
            case 0:
                this.f3346e = parcel.createTypedArrayList(DrivingStep.CREATOR);
                break;
            case 1:
                this.f3346e = parcel.createTypedArrayList(TransitStep.CREATOR);
                break;
            case 2:
                this.f3346e = parcel.createTypedArrayList(WalkingStep.CREATOR);
                break;
            case 3:
                this.f3346e = parcel.createTypedArrayList(BikingStep.CREATOR);
                break;
        }
        this.f3347f = parcel.readInt();
        this.f3348g = parcel.readInt();
    }

    public int describeContents() {
        return 0;
    }

    public List<T> getAllStep() {
        return this.f3346e;
    }

    public int getDistance() {
        return this.f3347f;
    }

    public int getDuration() {
        return this.f3348g;
    }

    public RouteNode getStarting() {
        return this.f3343b;
    }

    public RouteNode getTerminal() {
        return this.f3344c;
    }

    public String getTitle() {
        return this.f3345d;
    }

    protected TYPE getType() {
        return this.f3342a;
    }

    public void setDistance(int i) {
        this.f3347f = i;
    }

    public void setDuration(int i) {
        this.f3348g = i;
    }

    public void setStarting(RouteNode routeNode) {
        this.f3343b = routeNode;
    }

    public void setSteps(List<T> list) {
        this.f3346e = list;
    }

    public void setTerminal(RouteNode routeNode) {
        this.f3344c = routeNode;
    }

    public void setTitle(String str) {
        this.f3345d = str;
    }

    protected void setType(TYPE type) {
        this.f3342a = type;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.f3342a != null) {
            parcel.writeInt(this.f3342a.m4369a());
        } else {
            parcel.writeInt(10);
        }
        parcel.writeValue(this.f3343b);
        parcel.writeValue(this.f3344c);
        parcel.writeString(this.f3345d);
        if (this.f3342a != null) {
            parcel.writeTypedList(this.f3346e);
        }
        parcel.writeInt(this.f3347f);
        parcel.writeInt(this.f3348g);
    }
}
