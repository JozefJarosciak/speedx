package com.baidu.mapapi.search.route;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.search.core.C1146i;
import com.baidu.platform.core.p049d.C1329e;
import com.baidu.platform.core.p049d.C1334j;

public class RoutePlanSearch extends C1146i {
    /* renamed from: a */
    private C1329e f3470a = new C1334j();
    /* renamed from: b */
    private boolean f3471b = false;

    RoutePlanSearch() {
    }

    public static RoutePlanSearch newInstance() {
        BMapManager.init();
        return new RoutePlanSearch();
    }

    public boolean bikingSearch(BikingRoutePlanOption bikingRoutePlanOption) {
        if (this.f3470a == null) {
            throw new IllegalStateException("RoutePlanSearch is null, please call newInstance() first.");
        } else if (bikingRoutePlanOption == null || bikingRoutePlanOption.mTo == null || bikingRoutePlanOption.mFrom == null) {
            throw new IllegalArgumentException("route plan option , origin or destination can not be null");
        } else if (bikingRoutePlanOption.mFrom.getLocation() == null && (bikingRoutePlanOption.mFrom.getName() == null || bikingRoutePlanOption.mFrom.getName() == "")) {
            throw new IllegalArgumentException("route plan option , origin is illegal");
        } else if (bikingRoutePlanOption.mTo.getLocation() != null || (bikingRoutePlanOption.mTo.getName() != null && bikingRoutePlanOption.mTo.getName() != "")) {
            return this.f3470a.mo2700a(bikingRoutePlanOption);
        } else {
            throw new IllegalArgumentException("route plan option , destination is illegal");
        }
    }

    public void destroy() {
        if (!this.f3471b) {
            this.f3471b = true;
            this.f3470a.mo2684a();
            BMapManager.destroy();
        }
    }

    public boolean drivingSearch(DrivingRoutePlanOption drivingRoutePlanOption) {
        if (this.f3470a == null) {
            throw new IllegalStateException("RoutePlanSearch is null, please call newInstance() first.");
        } else if (drivingRoutePlanOption != null && drivingRoutePlanOption.mTo != null && drivingRoutePlanOption.mFrom != null) {
            return this.f3470a.mo2701a(drivingRoutePlanOption);
        } else {
            throw new IllegalArgumentException("route plan option , origin or destination can not be null");
        }
    }

    public boolean masstransitSearch(MassTransitRoutePlanOption massTransitRoutePlanOption) {
        if (this.f3470a == null) {
            throw new IllegalStateException("RoutePlanSearch is null, please call newInstance() first.");
        } else if (massTransitRoutePlanOption == null || massTransitRoutePlanOption.mTo == null || massTransitRoutePlanOption.mFrom == null) {
            throw new IllegalArgumentException("route plan option,origin or destination can not be null");
        } else if (massTransitRoutePlanOption.mFrom.getLocation() == null && (massTransitRoutePlanOption.mFrom.getName() == null || massTransitRoutePlanOption.mFrom.getCity() == null)) {
            throw new IllegalArgumentException("route plan option,origin is illegal");
        } else if (massTransitRoutePlanOption.mTo.getLocation() != null || (massTransitRoutePlanOption.mTo.getName() != null && massTransitRoutePlanOption.mTo.getCity() != null)) {
            return this.f3470a.mo2703a(massTransitRoutePlanOption);
        } else {
            throw new IllegalArgumentException("route plan option,destination is illegal");
        }
    }

    public void setOnGetRoutePlanResultListener(OnGetRoutePlanResultListener onGetRoutePlanResultListener) {
        if (this.f3470a == null) {
            throw new IllegalStateException("RoutePlanSearch is null, please call newInstance() first.");
        } else if (onGetRoutePlanResultListener == null) {
            throw new IllegalArgumentException("listener can not be null");
        } else {
            this.f3470a.mo2699a(onGetRoutePlanResultListener);
        }
    }

    public boolean transitSearch(TransitRoutePlanOption transitRoutePlanOption) {
        if (this.f3470a == null) {
            throw new IllegalStateException("RoutePlanSearch is null, please call newInstance() first.");
        } else if (transitRoutePlanOption != null && transitRoutePlanOption.mCityName != null && transitRoutePlanOption.mTo != null && transitRoutePlanOption.mFrom != null) {
            return this.f3470a.mo2704a(transitRoutePlanOption);
        } else {
            throw new IllegalArgumentException("route plan option,origin or destination or city can not be null");
        }
    }

    public boolean walkingIndoorSearch(IndoorRoutePlanOption indoorRoutePlanOption) {
        if (this.f3470a == null) {
            throw new IllegalStateException("RoutePlanSearch is null, please call newInstance() first.");
        } else if (indoorRoutePlanOption != null && indoorRoutePlanOption.mTo != null && indoorRoutePlanOption.mFrom != null) {
            return this.f3470a.mo2702a(indoorRoutePlanOption);
        } else {
            throw new IllegalArgumentException("option , origin or destination can not be null");
        }
    }

    public boolean walkingSearch(WalkingRoutePlanOption walkingRoutePlanOption) {
        if (this.f3470a == null) {
            throw new IllegalStateException("RoutePlanSearch is null, please call newInstance() first.");
        } else if (walkingRoutePlanOption != null && walkingRoutePlanOption.mTo != null && walkingRoutePlanOption.mFrom != null) {
            return this.f3470a.mo2705a(walkingRoutePlanOption);
        } else {
            throw new IllegalArgumentException("option , origin or destination can not be null");
        }
    }
}
