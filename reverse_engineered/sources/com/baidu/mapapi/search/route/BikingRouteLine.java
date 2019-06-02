package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.alipay.sdk.util.C0880h;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.RouteLine;
import com.baidu.mapapi.search.core.RouteNode;
import com.baidu.mapapi.search.core.RouteStep;
import com.baidu.platform.comapi.util.CoordTrans;
import java.util.ArrayList;
import java.util.List;

public class BikingRouteLine extends RouteLine<BikingStep> implements Parcelable {
    public static final Creator<BikingRouteLine> CREATOR = new C1168a();

    public static class BikingStep extends RouteStep implements Parcelable {
        public static final Creator<BikingStep> CREATOR = new C1169b();
        /* renamed from: c */
        private int f3393c;
        /* renamed from: d */
        private RouteNode f3394d;
        /* renamed from: e */
        private RouteNode f3395e;
        /* renamed from: f */
        private String f3396f;
        /* renamed from: g */
        private String f3397g;
        /* renamed from: h */
        private String f3398h;
        /* renamed from: i */
        private String f3399i;

        protected BikingStep(Parcel parcel) {
            super(parcel);
            this.f3393c = parcel.readInt();
            this.f3394d = (RouteNode) parcel.readParcelable(RouteNode.class.getClassLoader());
            this.f3395e = (RouteNode) parcel.readParcelable(RouteNode.class.getClassLoader());
            this.f3396f = parcel.readString();
            this.f3397g = parcel.readString();
            this.f3398h = parcel.readString();
            this.f3399i = parcel.readString();
        }

        /* renamed from: a */
        private List<LatLng> m4413a(String str) {
            if (str == null || str.length() == 0) {
                return null;
            }
            List<LatLng> arrayList = new ArrayList();
            String[] split = str.split(C0880h.f2220b);
            if (split == null || split.length == 0) {
                return null;
            }
            for (String split2 : split) {
                String[] split3 = split2.split(",");
                if (split3 != null && split3.length >= 2) {
                    Object latLng = new LatLng(Double.valueOf(split3[1]).doubleValue(), Double.valueOf(split3[0]).doubleValue());
                    if (latLng != null && SDKInitializer.getCoordType() == CoordType.GCJ02) {
                        latLng = CoordTrans.baiduToGcj(latLng);
                    }
                    arrayList.add(latLng);
                }
            }
            return arrayList;
        }

        public int describeContents() {
            return 0;
        }

        public int getDirection() {
            return this.f3393c;
        }

        public RouteNode getEntrance() {
            return this.f3394d;
        }

        public String getEntranceInstructions() {
            return this.f3397g;
        }

        public RouteNode getExit() {
            return this.f3395e;
        }

        public String getExitInstructions() {
            return this.f3398h;
        }

        public String getInstructions() {
            return this.f3399i;
        }

        public List<LatLng> getWayPoints() {
            if (this.mWayPoints == null) {
                this.mWayPoints = m4413a(this.f3396f);
            }
            return this.mWayPoints;
        }

        public void setDirection(int i) {
            this.f3393c = i;
        }

        public void setEntrance(RouteNode routeNode) {
            this.f3394d = routeNode;
        }

        public void setEntranceInstructions(String str) {
            this.f3397g = str;
        }

        public void setExit(RouteNode routeNode) {
            this.f3395e = routeNode;
        }

        public void setExitInstructions(String str) {
            this.f3398h = str;
        }

        public void setInstructions(String str) {
            this.f3399i = str;
        }

        public void setPathString(String str) {
            this.f3396f = str;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, 1);
            parcel.writeInt(this.f3393c);
            parcel.writeParcelable(this.f3394d, 1);
            parcel.writeParcelable(this.f3395e, 1);
            parcel.writeString(this.f3396f);
            parcel.writeString(this.f3397g);
            parcel.writeString(this.f3398h);
            parcel.writeString(this.f3399i);
        }
    }

    protected BikingRouteLine(Parcel parcel) {
        super(parcel);
    }

    public int describeContents() {
        return 0;
    }

    public List<BikingStep> getAllStep() {
        return super.getAllStep();
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.setType(TYPE.BIKINGSTEP);
        super.writeToParcel(parcel, 1);
    }
}
