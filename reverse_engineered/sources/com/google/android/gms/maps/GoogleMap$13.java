package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zze.zza;
import com.google.android.gms.maps.model.CameraPosition;

class GoogleMap$13 extends zza {
    final /* synthetic */ GoogleMap$OnCameraChangeListener agN;
    final /* synthetic */ GoogleMap agz;

    GoogleMap$13(GoogleMap googleMap, GoogleMap$OnCameraChangeListener googleMap$OnCameraChangeListener) {
        this.agz = googleMap;
        this.agN = googleMap$OnCameraChangeListener;
    }

    public void onCameraChange(CameraPosition cameraPosition) {
        this.agN.onCameraChange(cameraPosition);
    }
}
