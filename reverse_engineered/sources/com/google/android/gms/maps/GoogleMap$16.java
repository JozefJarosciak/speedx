package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzq.zza;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.internal.zzf;

class GoogleMap$16 extends zza {
    final /* synthetic */ GoogleMap$OnMarkerClickListener agQ;
    final /* synthetic */ GoogleMap agz;

    GoogleMap$16(GoogleMap googleMap, GoogleMap$OnMarkerClickListener googleMap$OnMarkerClickListener) {
        this.agz = googleMap;
        this.agQ = googleMap$OnMarkerClickListener;
    }

    public boolean zzd(zzf zzf) {
        return this.agQ.onMarkerClick(new Marker(zzf));
    }
}
