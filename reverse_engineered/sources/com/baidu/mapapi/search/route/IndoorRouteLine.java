package com.baidu.mapapi.search.route;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.RouteLine;
import com.baidu.mapapi.search.core.RouteNode;
import com.baidu.mapapi.search.core.RouteStep;
import java.util.ArrayList;
import java.util.List;

@SuppressLint({"ParcelCreator"})
public class IndoorRouteLine extends RouteLine<IndoorRouteStep> {
    public static final Creator<IndoorRouteLine> CREATOR = new C1174g();

    public static class IndoorRouteStep extends RouteStep {
        /* renamed from: c */
        private RouteNode f3430c;
        /* renamed from: d */
        private RouteNode f3431d;
        /* renamed from: e */
        private String f3432e;
        /* renamed from: f */
        private String f3433f;
        /* renamed from: g */
        private String f3434g;
        /* renamed from: h */
        private List<IndoorStepNode> f3435h;
        /* renamed from: i */
        private List<Double> f3436i;

        public static class IndoorStepNode {
            /* renamed from: a */
            private String f3426a;
            /* renamed from: b */
            private int f3427b;
            /* renamed from: c */
            private LatLng f3428c;
            /* renamed from: d */
            private String f3429d;

            public String getDetail() {
                return this.f3429d;
            }

            public LatLng getLocation() {
                return this.f3428c;
            }

            public String getName() {
                return this.f3426a;
            }

            public int getType() {
                return this.f3427b;
            }

            public void setDetail(String str) {
                this.f3429d = str;
            }

            public void setLocation(LatLng latLng) {
                this.f3428c = latLng;
            }

            public void setName(String str) {
                this.f3426a = str;
            }

            public void setType(int i) {
                this.f3427b = i;
            }
        }

        /* renamed from: a */
        private List<LatLng> m4414a(List<Double> list) {
            List<LatLng> arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i += 2) {
                arrayList.add(new LatLng(((Double) list.get(i)).doubleValue(), ((Double) list.get(i + 1)).doubleValue()));
            }
            return arrayList;
        }

        public String getBuildingId() {
            return this.f3434g;
        }

        public RouteNode getEntrace() {
            return this.f3430c;
        }

        public RouteNode getExit() {
            return this.f3431d;
        }

        public String getFloorId() {
            return this.f3433f;
        }

        public String getInstructions() {
            return this.f3432e;
        }

        public List<IndoorStepNode> getStepNodes() {
            return this.f3435h;
        }

        public List<LatLng> getWayPoints() {
            if (this.mWayPoints == null) {
                this.mWayPoints = m4414a(this.f3436i);
            }
            return this.mWayPoints;
        }

        public void setBuildingId(String str) {
            this.f3434g = str;
        }

        public void setEntrace(RouteNode routeNode) {
            this.f3430c = routeNode;
        }

        public void setExit(RouteNode routeNode) {
            this.f3431d = routeNode;
        }

        public void setFloorId(String str) {
            this.f3433f = str;
        }

        public void setInstructions(String str) {
            this.f3432e = str;
        }

        public void setPath(List<Double> list) {
            this.f3436i = list;
        }

        public void setStepNodes(List<IndoorStepNode> list) {
            this.f3435h = list;
        }
    }

    public IndoorRouteLine() {
        setType(TYPE.WALKSTEP);
    }

    protected IndoorRouteLine(Parcel parcel) {
        super(parcel);
    }

    public int describeContents() {
        return 0;
    }

    public List<IndoorRouteStep> getAllStep() {
        return super.getAllStep();
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }
}
