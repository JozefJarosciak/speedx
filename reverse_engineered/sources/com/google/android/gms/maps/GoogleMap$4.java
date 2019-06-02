package com.google.android.gms.maps;

import android.location.Location;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener;
import com.google.android.gms.maps.internal.zzt.zza;

class GoogleMap$4 extends zza {
    final /* synthetic */ OnMyLocationChangeListener agC;
    final /* synthetic */ GoogleMap agz;

    GoogleMap$4(GoogleMap googleMap, OnMyLocationChangeListener onMyLocationChangeListener) {
        this.agz = googleMap;
        this.agC = onMyLocationChangeListener;
    }

    public void zzae(zzd zzd) {
        this.agC.onMyLocationChange((Location) zze.zzad(zzd));
    }
}
