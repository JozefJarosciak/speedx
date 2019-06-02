package com.beastbikes.android.modules.cycling.route.ui;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MyLocationData.Builder;
import com.baidu.mapapi.model.LatLng;

class RoutePlanActivity$7 implements Runnable {
    /* renamed from: a */
    final /* synthetic */ BDLocation f10321a;
    /* renamed from: b */
    final /* synthetic */ RoutePlanActivity f10322b;

    RoutePlanActivity$7(RoutePlanActivity routePlanActivity, BDLocation bDLocation) {
        this.f10322b = routePlanActivity;
        this.f10321a = bDLocation;
    }

    public void run() {
        double latitude = this.f10321a.getLatitude();
        double longitude = this.f10321a.getLongitude();
        LatLng latLng = new LatLng(latitude, longitude);
        RoutePlanActivity.h(this.f10322b).setMyLocationData(new Builder().accuracy(this.f10321a.getRadius()).latitude(latitude).longitude(longitude).build());
        RoutePlanActivity.a(this.f10322b, RoutePlanActivity.h(this.f10322b).getMapStatus().zoom);
        RoutePlanActivity.h(this.f10322b).animateMapStatus(MapStatusUpdateFactory.zoomTo(RoutePlanActivity.i(this.f10322b)));
        RoutePlanActivity.j(this.f10322b).stop();
        if (RoutePlanActivity.k(this.f10322b)) {
            RoutePlanActivity.h(this.f10322b).animateMapStatus(MapStatusUpdateFactory.newLatLng(latLng));
        }
        RoutePlanActivity.b(this.f10322b, false);
    }
}
