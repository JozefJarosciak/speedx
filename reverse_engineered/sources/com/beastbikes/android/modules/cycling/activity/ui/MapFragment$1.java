package com.beastbikes.android.modules.cycling.activity.ui;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MyLocationData.Builder;
import com.baidu.mapapi.model.LatLng;
import com.beastbikes.android.modules.cycling.stage.dto.StagePointDTO;
import com.beastbikes.android.utils.C2558g;

class MapFragment$1 implements Runnable {
    /* renamed from: a */
    final /* synthetic */ BDLocation f8711a;
    /* renamed from: b */
    final /* synthetic */ MapFragment f8712b;

    MapFragment$1(MapFragment mapFragment, BDLocation bDLocation) {
        this.f8712b = mapFragment;
        this.f8711a = bDLocation;
    }

    public void run() {
        try {
            double latitude = this.f8711a.getLatitude();
            double longitude = this.f8711a.getLongitude();
            LatLng latLng = new LatLng(latitude, longitude);
            MapFragment.b(this.f8712b).setMyLocationData(new Builder().direction(MapFragment.a(this.f8712b)).accuracy(this.f8711a.getRadius()).latitude(latitude).longitude(longitude).build());
            if (MapFragment.c(this.f8712b)) {
                MapFragment.b(this.f8712b).animateMapStatus(MapStatusUpdateFactory.newLatLng(latLng));
            }
            MapFragment.a(this.f8712b, false);
            MapFragment.d(this.f8712b);
            com.mapbox.mapboxsdk.geometry.LatLng b = C2558g.m12845b(latLng);
            MapFragment.a(this.f8712b, new StagePointDTO(b.getLongitude(), b.getLatitude()));
            MapFragment.e(this.f8712b);
        } catch (Throwable e) {
            MapFragment.e().error("Unexpected error", e);
        }
    }
}
