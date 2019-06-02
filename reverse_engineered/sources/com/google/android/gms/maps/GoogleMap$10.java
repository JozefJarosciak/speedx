package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzw.zza;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.internal.IPolylineDelegate;

class GoogleMap$10 extends zza {
    final /* synthetic */ GoogleMap$OnPolylineClickListener agI;
    final /* synthetic */ GoogleMap agz;

    GoogleMap$10(GoogleMap googleMap, GoogleMap$OnPolylineClickListener googleMap$OnPolylineClickListener) {
        this.agz = googleMap;
        this.agI = googleMap$OnPolylineClickListener;
    }

    public void zza(IPolylineDelegate iPolylineDelegate) {
        this.agI.onPolylineClick(new Polyline(iPolylineDelegate));
    }
}
