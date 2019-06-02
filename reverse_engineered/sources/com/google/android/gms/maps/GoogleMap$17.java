package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzr.zza;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.internal.zzf;

class GoogleMap$17 extends zza {
    final /* synthetic */ GoogleMap$OnMarkerDragListener agR;
    final /* synthetic */ GoogleMap agz;

    GoogleMap$17(GoogleMap googleMap, GoogleMap$OnMarkerDragListener googleMap$OnMarkerDragListener) {
        this.agz = googleMap;
        this.agR = googleMap$OnMarkerDragListener;
    }

    public void zze(zzf zzf) {
        this.agR.onMarkerDragStart(new Marker(zzf));
    }

    public void zzf(zzf zzf) {
        this.agR.onMarkerDragEnd(new Marker(zzf));
    }

    public void zzg(zzf zzf) {
        this.agR.onMarkerDrag(new Marker(zzf));
    }
}
