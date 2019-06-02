package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.internal.zzs.zza;

class GoogleMap$5 extends zza {
    final /* synthetic */ GoogleMap$OnMyLocationButtonClickListener agD;
    final /* synthetic */ GoogleMap agz;

    GoogleMap$5(GoogleMap googleMap, GoogleMap$OnMyLocationButtonClickListener googleMap$OnMyLocationButtonClickListener) {
        this.agz = googleMap;
        this.agD = googleMap$OnMyLocationButtonClickListener;
    }

    public boolean onMyLocationButtonClick() throws RemoteException {
        return this.agD.onMyLocationButtonClick();
    }
}
