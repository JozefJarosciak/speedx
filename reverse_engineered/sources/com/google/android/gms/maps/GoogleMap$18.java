package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzi.zza;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.internal.zzf;

class GoogleMap$18 extends zza {
    final /* synthetic */ GoogleMap$OnInfoWindowClickListener agS;
    final /* synthetic */ GoogleMap agz;

    GoogleMap$18(GoogleMap googleMap, GoogleMap$OnInfoWindowClickListener googleMap$OnInfoWindowClickListener) {
        this.agz = googleMap;
        this.agS = googleMap$OnInfoWindowClickListener;
    }

    public void zzh(zzf zzf) {
        this.agS.onInfoWindowClick(new Marker(zzf));
    }
}
