package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzk.zza;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.internal.zzf;

class GoogleMap$19 extends zza {
    final /* synthetic */ GoogleMap$OnInfoWindowLongClickListener agT;
    final /* synthetic */ GoogleMap agz;

    GoogleMap$19(GoogleMap googleMap, GoogleMap$OnInfoWindowLongClickListener googleMap$OnInfoWindowLongClickListener) {
        this.agz = googleMap;
        this.agT = googleMap$OnInfoWindowLongClickListener;
    }

    public void zzi(zzf zzf) {
        this.agT.onInfoWindowLongClick(new Marker(zzf));
    }
}
