package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzf.zza;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.internal.zzb;

class GoogleMap$8 extends zza {
    final /* synthetic */ GoogleMap$OnCircleClickListener agG;
    final /* synthetic */ GoogleMap agz;

    GoogleMap$8(GoogleMap googleMap, GoogleMap$OnCircleClickListener googleMap$OnCircleClickListener) {
        this.agz = googleMap;
        this.agG = googleMap$OnCircleClickListener;
    }

    public void zza(zzb zzb) {
        this.agG.onCircleClick(new Circle(zzb));
    }
}
