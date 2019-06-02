package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.RouteLine;
import com.baidu.mapapi.search.core.RouteNode;
import com.baidu.mapapi.search.core.RouteStep;
import java.util.ArrayList;
import java.util.List;

public class DrivingRouteLine extends RouteLine<DrivingStep> implements Parcelable {
    public static final Creator<DrivingRouteLine> CREATOR = new C1171d();
    /* renamed from: b */
    private boolean f3412b;
    /* renamed from: c */
    private List<RouteNode> f3413c;
    /* renamed from: d */
    private int f3414d;
    /* renamed from: e */
    private int f3415e;

    public static class DrivingStep extends RouteStep implements Parcelable {
        public static final Creator<DrivingStep> CREATOR = new C1172e();
        /* renamed from: c */
        List<LatLng> f3402c;
        /* renamed from: d */
        int[] f3403d;
        /* renamed from: e */
        private int f3404e;
        /* renamed from: f */
        private RouteNode f3405f;
        /* renamed from: g */
        private RouteNode f3406g;
        /* renamed from: h */
        private String f3407h;
        /* renamed from: i */
        private String f3408i;
        /* renamed from: j */
        private String f3409j;
        /* renamed from: k */
        private String f3410k;
        /* renamed from: l */
        private int f3411l;

        protected DrivingStep(Parcel parcel) {
            super(parcel);
            this.f3404e = parcel.readInt();
            this.f3405f = (RouteNode) parcel.readParcelable(RouteNode.class.getClassLoader());
            this.f3406g = (RouteNode) parcel.readParcelable(RouteNode.class.getClassLoader());
            this.f3407h = parcel.readString();
            this.f3408i = parcel.readString();
            this.f3409j = parcel.readString();
            this.f3410k = parcel.readString();
            this.f3411l = parcel.readInt();
            this.f3402c = parcel.createTypedArrayList(LatLng.CREATOR);
            this.f3403d = parcel.createIntArray();
        }

        public int describeContents() {
            return 0;
        }

        public int getDirection() {
            return this.f3404e;
        }

        public RouteNode getEntrance() {
            return this.f3405f;
        }

        public String getEntranceInstructions() {
            return this.f3408i;
        }

        public RouteNode getExit() {
            return this.f3406g;
        }

        public String getExitInstructions() {
            return this.f3409j;
        }

        public String getInstructions() {
            return this.f3410k;
        }

        public int getNumTurns() {
            return this.f3411l;
        }

        public int[] getTrafficList() {
            return this.f3403d;
        }

        public List<LatLng> getWayPoints() {
            if (this.mWayPoints == null) {
                this.mWayPoints = CoordUtil.decodeLocationList(this.f3407h);
            }
            return this.f3402c;
        }

        public void setDirection(int i) {
            this.f3404e = i;
        }

        public void setEntrance(RouteNode routeNode) {
            this.f3405f = routeNode;
        }

        public void setEntranceInstructions(String str) {
            this.f3408i = str;
        }

        public void setExit(RouteNode routeNode) {
            this.f3406g = routeNode;
        }

        public void setExitInstructions(String str) {
            this.f3409j = str;
        }

        public void setInstructions(String str) {
            this.f3410k = str;
        }

        public void setNumTurns(int i) {
            this.f3411l = i;
        }

        public void setPathList(List<LatLng> list) {
            this.f3402c = list;
        }

        public void setPathString(String str) {
            this.f3407h = str;
        }

        public void setTrafficList(int[] iArr) {
            this.f3403d = iArr;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f3404e);
            parcel.writeParcelable(this.f3405f, 1);
            parcel.writeParcelable(this.f3406g, 1);
            parcel.writeString(this.f3407h);
            parcel.writeString(this.f3408i);
            parcel.writeString(this.f3409j);
            parcel.writeString(this.f3410k);
            parcel.writeInt(this.f3411l);
            parcel.writeTypedList(this.f3402c);
            parcel.writeIntArray(this.f3403d);
        }
    }

    protected DrivingRouteLine(Parcel parcel) {
        super(parcel);
        this.f3412b = parcel.readByte() != (byte) 0;
        this.f3413c = new ArrayList();
        parcel.readList(this.f3413c, RouteNode.class.getClassLoader());
        this.f3414d = parcel.readInt();
        this.f3415e = parcel.readInt();
    }

    public int describeContents() {
        return 0;
    }

    public int getCongestionDistance() {
        return this.f3414d;
    }

    public int getLightNum() {
        return this.f3415e;
    }

    public List<RouteNode> getWayPoints() {
        return this.f3413c;
    }

    @Deprecated
    public boolean isSupportTraffic() {
        return this.f3412b;
    }

    public void setCongestionDistance(int i) {
        this.f3414d = i;
    }

    public void setLightNum(int i) {
        this.f3415e = i;
    }

    public void setSupportTraffic(boolean z) {
        this.f3412b = z;
    }

    public void setWayPoints(List<RouteNode> list) {
        this.f3413c = list;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.setType(TYPE.DRIVESTEP);
        super.writeToParcel(parcel, i);
        parcel.writeByte(this.f3412b ? (byte) 1 : (byte) 0);
        parcel.writeList(this.f3413c);
        parcel.writeInt(this.f3414d);
        parcel.writeInt(this.f3415e);
    }
}
