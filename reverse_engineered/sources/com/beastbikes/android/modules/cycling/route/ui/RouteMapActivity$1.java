package com.beastbikes.android.modules.cycling.route.ui;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MyLocationData.Builder;
import com.baidu.mapapi.model.LatLng;

class RouteMapActivity$1 implements Runnable {
    /* renamed from: a */
    final /* synthetic */ BDLocation f10299a;
    /* renamed from: b */
    final /* synthetic */ RouteMapActivity f10300b;

    RouteMapActivity$1(RouteMapActivity routeMapActivity, BDLocation bDLocation) {
        this.f10300b = routeMapActivity;
        this.f10299a = bDLocation;
    }

    public void run() {
        double latitude = this.f10299a.getLatitude();
        double longitude = this.f10299a.getLongitude();
        LatLng latLng = new LatLng(latitude, longitude);
        RouteMapActivity.b(this.f10300b).setMyLocationData(new Builder().direction(RouteMapActivity.a(this.f10300b)).accuracy(this.f10299a.getRadius()).latitude(latitude).longitude(longitude).build());
        RouteMapActivity.a(this.f10300b, RouteMapActivity.b(this.f10300b).getMapStatus().zoom);
        RouteMapActivity.b(this.f10300b).animateMapStatus(MapStatusUpdateFactory.zoomTo(RouteMapActivity.c(this.f10300b)));
        if (RouteMapActivity.d(this.f10300b)) {
            RouteMapActivity.b(this.f10300b).animateMapStatus(MapStatusUpdateFactory.newLatLng(latLng));
        }
        RouteMapActivity.a(this.f10300b, false);
    }
}
