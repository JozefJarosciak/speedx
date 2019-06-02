package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.internal.zzn.zza;

class GoogleMap$6 extends zza {
    final /* synthetic */ GoogleMap$OnMapLoadedCallback agE;
    final /* synthetic */ GoogleMap agz;

    GoogleMap$6(GoogleMap googleMap, GoogleMap$OnMapLoadedCallback googleMap$OnMapLoadedCallback) {
        this.agz = googleMap;
        this.agE = googleMap$OnMapLoadedCallback;
    }

    public void onMapLoaded() throws RemoteException {
        this.agE.onMapLoaded();
    }
}
