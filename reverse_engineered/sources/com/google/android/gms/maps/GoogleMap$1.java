package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzh.zza;
import com.google.android.gms.maps.model.IndoorBuilding;
import com.google.android.gms.maps.model.internal.zzd;

class GoogleMap$1 extends zza {
    final /* synthetic */ GoogleMap$OnIndoorStateChangeListener agy;
    final /* synthetic */ GoogleMap agz;

    GoogleMap$1(GoogleMap googleMap, GoogleMap$OnIndoorStateChangeListener googleMap$OnIndoorStateChangeListener) {
        this.agz = googleMap;
        this.agy = googleMap$OnIndoorStateChangeListener;
    }

    public void onIndoorBuildingFocused() {
        this.agy.onIndoorBuildingFocused();
    }

    public void zza(zzd zzd) {
        this.agy.onIndoorLevelActivated(new IndoorBuilding(zzd));
    }
}
