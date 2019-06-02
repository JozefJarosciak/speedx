package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.RouteLine;
import com.baidu.mapapi.search.core.RouteNode;
import com.baidu.mapapi.search.core.RouteStep;
import java.util.List;

public class WalkingRouteLine extends RouteLine<WalkingStep> implements Parcelable {
    public static final Creator<WalkingRouteLine> CREATOR = new C1185r();

    public static class WalkingStep extends RouteStep implements Parcelable {
        public static final Creator<WalkingStep> CREATOR = new C1186s();
        /* renamed from: c */
        private int f3490c;
        /* renamed from: d */
        private RouteNode f3491d;
        /* renamed from: e */
        private RouteNode f3492e;
        /* renamed from: f */
        private String f3493f;
        /* renamed from: g */
        private String f3494g;
        /* renamed from: h */
        private String f3495h;
        /* renamed from: i */
        private String f3496i;

        protected WalkingStep(Parcel parcel) {
            super(parcel);
            this.f3490c = parcel.readInt();
            this.f3491d = (RouteNode) parcel.readParcelable(RouteNode.class.getClassLoader());
            this.f3492e = (RouteNode) parcel.readParcelable(RouteNode.class.getClassLoader());
            this.f3493f = parcel.readString();
            this.f3494g = parcel.readString();
            this.f3495h = parcel.readString();
            this.f3496i = parcel.readString();
        }

        public int describeContents() {
            return 0;
        }

        public int getDirection() {
            return this.f3490c;
        }

        public RouteNode getEntrance() {
            return this.f3491d;
        }

        public String getEntranceInstructions() {
            return this.f3494g;
        }

        public RouteNode getExit() {
            return this.f3492e;
        }

        public String getExitInstructions() {
            return this.f3495h;
        }

        public String getInstructions() {
            return this.f3496i;
        }

        public List<LatLng> getWayPoints() {
            if (this.mWayPoints == null) {
                this.mWayPoints = CoordUtil.decodeLocationList(this.f3493f);
            }
            return this.mWayPoints;
        }

        public void setDirection(int i) {
            this.f3490c = i;
        }

        public void setEntrance(RouteNode routeNode) {
            this.f3491d = routeNode;
        }

        public void setEntranceInstructions(String str) {
            this.f3494g = str;
        }

        public void setExit(RouteNode routeNode) {
            this.f3492e = routeNode;
        }

        public void setExitInstructions(String str) {
            this.f3495h = str;
        }

        public void setInstructions(String str) {
            this.f3496i = str;
        }

        public void setPathString(String str) {
            this.f3493f = str;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, 1);
            parcel.writeInt(this.f3490c);
            parcel.writeParcelable(this.f3491d, 1);
            parcel.writeParcelable(this.f3492e, 1);
            parcel.writeString(this.f3493f);
            parcel.writeString(this.f3494g);
            parcel.writeString(this.f3495h);
            parcel.writeString(this.f3496i);
        }
    }

    protected WalkingRouteLine(Parcel parcel) {
        super(parcel);
    }

    public int describeContents() {
        return 0;
    }

    public List<WalkingStep> getAllStep() {
        return super.getAllStep();
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.setType(TYPE.WALKSTEP);
        super.writeToParcel(parcel, 1);
    }
}
