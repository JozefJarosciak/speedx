package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzm.zza;
import com.google.android.gms.maps.model.LatLng;

class GoogleMap$14 extends zza {
    final /* synthetic */ GoogleMap$OnMapClickListener agO;
    final /* synthetic */ GoogleMap agz;

    GoogleMap$14(GoogleMap googleMap, GoogleMap$OnMapClickListener googleMap$OnMapClickListener) {
        this.agz = googleMap;
        this.agO = googleMap$OnMapClickListener;
    }

    public void onMapClick(LatLng latLng) {
        this.agO.onMapClick(latLng);
    }
}
