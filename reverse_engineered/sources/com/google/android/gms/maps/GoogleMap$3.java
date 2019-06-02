package com.google.android.gms.maps;

import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.maps.internal.zzd.zza;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.internal.zzf;

class GoogleMap$3 extends zza {
    final /* synthetic */ GoogleMap$InfoWindowAdapter agB;
    final /* synthetic */ GoogleMap agz;

    GoogleMap$3(GoogleMap googleMap, GoogleMap$InfoWindowAdapter googleMap$InfoWindowAdapter) {
        this.agz = googleMap;
        this.agB = googleMap$InfoWindowAdapter;
    }

    public zzd zzb(zzf zzf) {
        return zze.zzae(this.agB.getInfoWindow(new Marker(zzf)));
    }

    public zzd zzc(zzf zzf) {
        return zze.zzae(this.agB.getInfoContents(new Marker(zzf)));
    }
}
