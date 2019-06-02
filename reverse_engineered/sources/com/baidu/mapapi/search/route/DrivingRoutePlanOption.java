package com.baidu.mapapi.search.route;

import java.util.List;

public class DrivingRoutePlanOption {
    public String mCityName;
    public PlanNode mFrom = null;
    public DrivingPolicy mPolicy = DrivingPolicy.ECAR_TIME_FIRST;
    public PlanNode mTo = null;
    public List<PlanNode> mWayPoints = null;
    public DrivingTrafficPolicy mtrafficPolicy = DrivingTrafficPolicy.ROUTE_PATH;

    public enum DrivingPolicy {
        ECAR_AVOID_JAM(3),
        ECAR_TIME_FIRST(0),
        ECAR_DIS_FIRST(1),
        ECAR_FEE_FIRST(2);
        
        /* renamed from: a */
        private int f3417a;

        private DrivingPolicy(int i) {
            this.f3417a = i;
        }

        public int getInt() {
            return this.f3417a;
        }
    }

    public enum DrivingTrafficPolicy {
        ROUTE_PATH(0),
        ROUTE_PATH_AND_TRAFFIC(1);
        
        /* renamed from: a */
        private int f3419a;

        private DrivingTrafficPolicy(int i) {
            this.f3419a = i;
        }

        public int getInt() {
            return this.f3419a;
        }
    }

    public DrivingRoutePlanOption currentCity(String str) {
        this.mCityName = str;
        return this;
    }

    public DrivingRoutePlanOption from(PlanNode planNode) {
        this.mFrom = planNode;
        return this;
    }

    public DrivingRoutePlanOption passBy(List<PlanNode> list) {
        this.mWayPoints = list;
        return this;
    }

    public DrivingRoutePlanOption policy(DrivingPolicy drivingPolicy) {
        this.mPolicy = drivingPolicy;
        return this;
    }

    public DrivingRoutePlanOption to(PlanNode planNode) {
        this.mTo = planNode;
        return this;
    }

    public DrivingRoutePlanOption trafficPolicy(DrivingTrafficPolicy drivingTrafficPolicy) {
        this.mtrafficPolicy = drivingTrafficPolicy;
        return this;
    }
}
