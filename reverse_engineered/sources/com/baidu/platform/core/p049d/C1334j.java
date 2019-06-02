package com.baidu.platform.core.p049d;

import com.baidu.mapapi.search.route.BikingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.IndoorRoutePlanOption;
import com.baidu.mapapi.search.route.MassTransitRoutePlanOption;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.TransitRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.platform.base.C1208a;
import com.baidu.platform.base.SearchType;

/* renamed from: com.baidu.platform.core.d.j */
public class C1334j extends C1208a implements C1329e {
    /* renamed from: b */
    OnGetRoutePlanResultListener f3965b = null;

    /* renamed from: a */
    public void mo2684a() {
        this.f3965b = null;
    }

    /* renamed from: a */
    public void mo2699a(OnGetRoutePlanResultListener onGetRoutePlanResultListener) {
        this.f3965b = onGetRoutePlanResultListener;
    }

    /* renamed from: a */
    public boolean mo2700a(BikingRoutePlanOption bikingRoutePlanOption) {
        this.a = new C1324a();
        this.a.m4537a(new C1340p(this));
        this.a.m4536a(SearchType.BIKE_ROUTE);
        return m4533a(new C1325b(bikingRoutePlanOption));
    }

    /* renamed from: a */
    public boolean mo2701a(DrivingRoutePlanOption drivingRoutePlanOption) {
        this.a = new C1327c();
        this.a.m4537a(new C1339o(this));
        this.a.m4536a(SearchType.DRIVE_ROUTE);
        return m4533a(new C1328d(drivingRoutePlanOption));
    }

    /* renamed from: a */
    public boolean mo2702a(IndoorRoutePlanOption indoorRoutePlanOption) {
        this.a = new C1330f();
        this.a.m4537a(new C1338n(this));
        this.a.m4536a(SearchType.INDOOR_ROUTE);
        return m4533a(new C1331g(indoorRoutePlanOption));
    }

    /* renamed from: a */
    public boolean mo2703a(MassTransitRoutePlanOption massTransitRoutePlanOption) {
        this.a = new C1332h();
        this.a.m4537a(new C1336l(this));
        this.a.m4536a(SearchType.MASS_TRANSIT_ROUTE);
        return m4533a(new C1333i(massTransitRoutePlanOption));
    }

    /* renamed from: a */
    public boolean mo2704a(TransitRoutePlanOption transitRoutePlanOption) {
        this.a = new C1342r();
        this.a.m4537a(new C1335k(this));
        this.a.m4536a(SearchType.TRANSIT_ROUTE);
        return m4533a(new C1343s(transitRoutePlanOption));
    }

    /* renamed from: a */
    public boolean mo2705a(WalkingRoutePlanOption walkingRoutePlanOption) {
        this.a = new C1344t();
        this.a.m4537a(new C1337m(this));
        this.a.m4536a(SearchType.WALK_ROUTE);
        return m4533a(new C1345u(walkingRoutePlanOption));
    }
}
