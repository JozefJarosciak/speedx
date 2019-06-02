package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzo.zza;
import com.google.android.gms.maps.model.LatLng;

class GoogleMap$15 extends zza {
    final /* synthetic */ GoogleMap$OnMapLongClickListener agP;
    final /* synthetic */ GoogleMap agz;

    GoogleMap$15(GoogleMap googleMap, GoogleMap$OnMapLongClickListener googleMap$OnMapLongClickListener) {
        this.agz = googleMap;
        this.agP = googleMap$OnMapLongClickListener;
    }

    public void onMapLongClick(LatLng latLng) {
        this.agP.onMapLongClick(latLng);
    }
}
