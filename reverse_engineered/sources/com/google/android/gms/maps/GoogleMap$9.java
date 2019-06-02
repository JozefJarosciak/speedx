package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzv.zza;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.internal.zzg;

class GoogleMap$9 extends zza {
    final /* synthetic */ GoogleMap$OnPolygonClickListener agH;
    final /* synthetic */ GoogleMap agz;

    GoogleMap$9(GoogleMap googleMap, GoogleMap$OnPolygonClickListener googleMap$OnPolygonClickListener) {
        this.agz = googleMap;
        this.agH = googleMap$OnPolygonClickListener;
    }

    public void zza(zzg zzg) {
        this.agH.onPolygonClick(new Polygon(zzg));
    }
}
