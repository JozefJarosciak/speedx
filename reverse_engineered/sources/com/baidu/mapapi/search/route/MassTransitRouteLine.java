package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.alipay.sdk.util.C0880h;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.BusInfo;
import com.baidu.mapapi.search.core.CoachInfo;
import com.baidu.mapapi.search.core.PlaneInfo;
import com.baidu.mapapi.search.core.PriceInfo;
import com.baidu.mapapi.search.core.RouteLine;
import com.baidu.mapapi.search.core.RouteStep;
import com.baidu.mapapi.search.core.TrainInfo;
import com.baidu.platform.comapi.util.CoordTrans;
import java.util.ArrayList;
import java.util.List;

public final class MassTransitRouteLine extends RouteLine<TransitStep> implements Parcelable {
    public static final Creator<MassTransitRouteLine> CREATOR = new C1176i();
    /* renamed from: b */
    private String f3451b;
    /* renamed from: c */
    private double f3452c;
    /* renamed from: d */
    private List<PriceInfo> f3453d;
    /* renamed from: e */
    private List<List<TransitStep>> f3454e = null;

    public static class TransitStep extends RouteStep implements Parcelable {
        public static final Creator<TransitStep> CREATOR = new C1177j();
        /* renamed from: c */
        private List<TrafficCondition> f3441c;
        /* renamed from: d */
        private LatLng f3442d;
        /* renamed from: e */
        private LatLng f3443e;
        /* renamed from: f */
        private TrainInfo f3444f;
        /* renamed from: g */
        private PlaneInfo f3445g;
        /* renamed from: h */
        private CoachInfo f3446h;
        /* renamed from: i */
        private BusInfo f3447i;
        /* renamed from: j */
        private StepVehicleInfoType f3448j;
        /* renamed from: k */
        private String f3449k;
        /* renamed from: l */
        private String f3450l;

        public enum StepVehicleInfoType {
            ESTEP_TRAIN(1),
            ESTEP_PLANE(2),
            ESTEP_BUS(3),
            ESTEP_DRIVING(4),
            ESTEP_WALK(5),
            ESTEP_COACH(6);
            
            /* renamed from: a */
            private int f3438a;

            private StepVehicleInfoType(int i) {
                this.f3438a = 0;
                this.f3438a = i;
            }

            public int getInt() {
                return this.f3438a;
            }
        }

        public static class TrafficCondition implements Parcelable {
            public static final Creator<TrafficCondition> CREATOR = new C1178k();
            /* renamed from: a */
            private int f3439a;
            /* renamed from: b */
            private int f3440b;

            protected TrafficCondition(Parcel parcel) {
                this.f3439a = parcel.readInt();
                this.f3440b = parcel.readInt();
            }

            public int describeContents() {
                return 0;
            }

            public int getTrafficGeoCnt() {
                return this.f3440b;
            }

            public int getTrafficStatus() {
                return this.f3439a;
            }

            public void setTrafficGeoCnt(int i) {
                this.f3440b = i;
            }

            public void setTrafficStatus(int i) {
                this.f3439a = i;
            }

            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(this.f3439a);
                parcel.writeInt(this.f3440b);
            }
        }

        protected TransitStep(Parcel parcel) {
            super(parcel);
            this.f3441c = parcel.createTypedArrayList(TrafficCondition.CREATOR);
            this.f3442d = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
            this.f3443e = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
            this.f3444f = (TrainInfo) parcel.readParcelable(TrainInfo.class.getClassLoader());
            this.f3445g = (PlaneInfo) parcel.readParcelable(PlaneInfo.class.getClassLoader());
            this.f3446h = (CoachInfo) parcel.readParcelable(CoachInfo.class.getClassLoader());
            this.f3447i = (BusInfo) parcel.readParcelable(BusInfo.class.getClassLoader());
            switch (parcel.readInt()) {
                case 1:
                    this.f3448j = StepVehicleInfoType.ESTEP_TRAIN;
                    break;
                case 2:
                    this.f3448j = StepVehicleInfoType.ESTEP_PLANE;
                    break;
                case 3:
                    this.f3448j = StepVehicleInfoType.ESTEP_BUS;
                    break;
                case 4:
                    this.f3448j = StepVehicleInfoType.ESTEP_DRIVING;
                    break;
                case 5:
                    this.f3448j = StepVehicleInfoType.ESTEP_WALK;
                    break;
                case 6:
                    this.f3448j = StepVehicleInfoType.ESTEP_COACH;
                    break;
            }
            this.f3449k = parcel.readString();
            this.f3450l = parcel.readString();
        }

        /* renamed from: a */
        private List<LatLng> m4415a(String str) {
            List<LatLng> arrayList = new ArrayList();
            String[] split = str.split(C0880h.f2220b);
            if (split != null) {
                int i = 0;
                while (i < split.length) {
                    if (!(split[i] == null || split[i] == "")) {
                        String[] split2 = split[i].split(",");
                        if (!(split2 == null || split2[1] == "" || split2[0] == "")) {
                            Object latLng = new LatLng(Double.parseDouble(split2[1]), Double.parseDouble(split2[0]));
                            if (latLng != null && SDKInitializer.getCoordType() == CoordType.GCJ02) {
                                latLng = CoordTrans.baiduToGcj(latLng);
                            }
                            arrayList.add(latLng);
                        }
                    }
                    i++;
                }
            }
            return arrayList;
        }

        public int describeContents() {
            return 0;
        }

        public BusInfo getBusInfo() {
            return this.f3447i;
        }

        public CoachInfo getCoachInfo() {
            return this.f3446h;
        }

        public LatLng getEndLocation() {
            return this.f3443e;
        }

        public String getInstructions() {
            return this.f3449k;
        }

        public PlaneInfo getPlaneInfo() {
            return this.f3445g;
        }

        public LatLng getStartLocation() {
            return this.f3442d;
        }

        public List<TrafficCondition> getTrafficConditions() {
            return this.f3441c;
        }

        public TrainInfo getTrainInfo() {
            return this.f3444f;
        }

        public StepVehicleInfoType getVehileType() {
            return this.f3448j;
        }

        public List<LatLng> getWayPoints() {
            if (this.mWayPoints == null) {
                this.mWayPoints = m4415a(this.f3450l);
            }
            return this.mWayPoints;
        }

        public void setBusInfo(BusInfo busInfo) {
            this.f3447i = busInfo;
        }

        public void setCoachInfo(CoachInfo coachInfo) {
            this.f3446h = coachInfo;
        }

        public void setEndLocation(LatLng latLng) {
            this.f3443e = latLng;
        }

        public void setInstructions(String str) {
            this.f3449k = str;
        }

        public void setPathString(String str) {
            this.f3450l = str;
        }

        public void setPlaneInfo(PlaneInfo planeInfo) {
            this.f3445g = planeInfo;
        }

        public void setStartLocation(LatLng latLng) {
            this.f3442d = latLng;
        }

        public void setTrafficConditions(List<TrafficCondition> list) {
            this.f3441c = list;
        }

        public void setTrainInfo(TrainInfo trainInfo) {
            this.f3444f = trainInfo;
        }

        public void setVehileType(StepVehicleInfoType stepVehicleInfoType) {
            this.f3448j = stepVehicleInfoType;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeTypedList(this.f3441c);
            parcel.writeParcelable(this.f3442d, i);
            parcel.writeParcelable(this.f3443e, i);
            parcel.writeParcelable(this.f3444f, i);
            parcel.writeParcelable(this.f3445g, i);
            parcel.writeParcelable(this.f3446h, i);
            parcel.writeParcelable(this.f3447i, i);
            parcel.writeInt(this.f3448j.getInt());
            parcel.writeString(this.f3449k);
            parcel.writeString(this.f3450l);
        }
    }

    protected MassTransitRouteLine(Parcel parcel) {
        super(parcel);
        int readInt = parcel.readInt();
        this.f3451b = parcel.readString();
        this.f3452c = parcel.readDouble();
        this.f3453d = parcel.createTypedArrayList(PriceInfo.CREATOR);
        if (readInt > 0) {
            this.f3454e = new ArrayList();
            for (int i = 0; i < readInt; i++) {
                this.f3454e.add(parcel.createTypedArrayList(TransitStep.CREATOR));
            }
        }
    }

    public int describeContents() {
        return 0;
    }

    public String getArriveTime() {
        return this.f3451b;
    }

    public List<List<TransitStep>> getNewSteps() {
        return this.f3454e;
    }

    public double getPrice() {
        return this.f3452c;
    }

    public List<PriceInfo> getPriceInfo() {
        return this.f3453d;
    }

    public void setArriveTime(String str) {
        this.f3451b = str;
    }

    public void setNewSteps(List<List<TransitStep>> list) {
        this.f3454e = list;
    }

    public void setPrice(double d) {
        this.f3452c = d;
    }

    public void setPriceInfo(List<PriceInfo> list) {
        this.f3453d = list;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.f3454e == null ? 0 : this.f3454e.size());
        parcel.writeString(this.f3451b);
        parcel.writeDouble(this.f3452c);
        parcel.writeTypedList(this.f3453d);
        for (List writeTypedList : this.f3454e) {
            parcel.writeTypedList(writeTypedList);
        }
    }
}
