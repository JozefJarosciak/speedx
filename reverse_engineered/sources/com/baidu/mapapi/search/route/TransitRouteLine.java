package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.RouteLine;
import com.baidu.mapapi.search.core.RouteNode;
import com.baidu.mapapi.search.core.RouteStep;
import com.baidu.mapapi.search.core.TaxiInfo;
import com.baidu.mapapi.search.core.VehicleInfo;
import java.util.List;

public final class TransitRouteLine extends RouteLine<TransitStep> implements Parcelable {
    public static final Creator<TransitRouteLine> CREATOR = new C1182o();
    /* renamed from: b */
    private TaxiInfo f3484b;

    public static class TransitStep extends RouteStep implements Parcelable {
        public static final Creator<TransitStep> CREATOR = new C1183p();
        /* renamed from: c */
        private VehicleInfo f3478c;
        /* renamed from: d */
        private RouteNode f3479d;
        /* renamed from: e */
        private RouteNode f3480e;
        /* renamed from: f */
        private TransitRouteStepType f3481f;
        /* renamed from: g */
        private String f3482g;
        /* renamed from: h */
        private String f3483h;

        public enum TransitRouteStepType {
            BUSLINE,
            SUBWAY,
            WAKLING
        }

        protected TransitStep(Parcel parcel) {
            super(parcel);
            this.f3478c = (VehicleInfo) parcel.readParcelable(VehicleInfo.class.getClassLoader());
            this.f3479d = (RouteNode) parcel.readParcelable(RouteNode.class.getClassLoader());
            this.f3480e = (RouteNode) parcel.readParcelable(RouteNode.class.getClassLoader());
            int readInt = parcel.readInt();
            this.f3481f = readInt == -1 ? null : TransitRouteStepType.values()[readInt];
            this.f3482g = parcel.readString();
            this.f3483h = parcel.readString();
        }

        public int describeContents() {
            return 0;
        }

        public RouteNode getEntrance() {
            return this.f3479d;
        }

        public RouteNode getExit() {
            return this.f3480e;
        }

        public String getInstructions() {
            return this.f3482g;
        }

        public TransitRouteStepType getStepType() {
            return this.f3481f;
        }

        public VehicleInfo getVehicleInfo() {
            return this.f3478c;
        }

        public List<LatLng> getWayPoints() {
            if (this.mWayPoints == null) {
                this.mWayPoints = CoordUtil.decodeLocationList(this.f3483h);
            }
            return this.mWayPoints;
        }

        public void setEntrace(RouteNode routeNode) {
            this.f3479d = routeNode;
        }

        public void setExit(RouteNode routeNode) {
            this.f3480e = routeNode;
        }

        public void setInstructions(String str) {
            this.f3482g = str;
        }

        public void setPathString(String str) {
            this.f3483h = str;
        }

        public void setStepType(TransitRouteStepType transitRouteStepType) {
            this.f3481f = transitRouteStepType;
        }

        public void setVehicleInfo(VehicleInfo vehicleInfo) {
            this.f3478c = vehicleInfo;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeParcelable(this.f3478c, 1);
            parcel.writeParcelable(this.f3479d, 1);
            parcel.writeParcelable(this.f3480e, 1);
            parcel.writeInt(this.f3481f == null ? -1 : this.f3481f.ordinal());
            parcel.writeString(this.f3482g);
            parcel.writeString(this.f3483h);
        }
    }

    protected TransitRouteLine(Parcel parcel) {
        super(parcel);
        this.f3484b = (TaxiInfo) parcel.readParcelable(TaxiInfo.class.getClassLoader());
    }

    public int describeContents() {
        return 0;
    }

    @Deprecated
    public TaxiInfo getTaxitInfo() {
        return this.f3484b;
    }

    public void setTaxitInfo(TaxiInfo taxiInfo) {
        this.f3484b = taxiInfo;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.setType(TYPE.TRANSITSTEP);
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.f3484b, 1);
    }
}
