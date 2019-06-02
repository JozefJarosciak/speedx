package com.beastbikes.android.modules.cycling.activity.ui;

import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

class MapFragment$5 implements OnMapReadyCallback {
    /* renamed from: a */
    final /* synthetic */ MapFragment f8716a;

    MapFragment$5(MapFragment mapFragment) {
        this.f8716a = mapFragment;
    }

    public void onMapReady(GoogleMap googleMap) {
        MapFragment.a(this.f8716a, googleMap);
        googleMap.setMyLocationEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(false);
        MapFragment.a(this.f8716a, new LocationRequest());
        MapFragment.m(this.f8716a).setInterval(5000);
        MapFragment.m(this.f8716a).setFastestInterval(5000);
        MapFragment.m(this.f8716a).setPriority(100);
    }
}
