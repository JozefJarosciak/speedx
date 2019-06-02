package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzj.zza;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.internal.zzf;

class GoogleMap$2 extends zza {
    final /* synthetic */ GoogleMap$OnInfoWindowCloseListener agA;
    final /* synthetic */ GoogleMap agz;

    GoogleMap$2(GoogleMap googleMap, GoogleMap$OnInfoWindowCloseListener googleMap$OnInfoWindowCloseListener) {
        this.agz = googleMap;
        this.agA = googleMap$OnInfoWindowCloseListener;
    }

    public void zza(zzf zzf) {
        this.agA.onInfoWindowClose(new Marker(zzf));
    }
}
