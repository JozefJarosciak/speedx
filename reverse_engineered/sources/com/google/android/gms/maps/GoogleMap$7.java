package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzg.zza;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.internal.zzc;

class GoogleMap$7 extends zza {
    final /* synthetic */ GoogleMap$OnGroundOverlayClickListener agF;
    final /* synthetic */ GoogleMap agz;

    GoogleMap$7(GoogleMap googleMap, GoogleMap$OnGroundOverlayClickListener googleMap$OnGroundOverlayClickListener) {
        this.agz = googleMap;
        this.agF = googleMap$OnGroundOverlayClickListener;
    }

    public void zza(zzc zzc) {
        this.agF.onGroundOverlayClick(new GroundOverlay(zzc));
    }
}
