package com.google.android.gms.maps;

import android.location.Location;
import android.os.RemoteException;
import com.google.android.gms.maps.LocationSource.OnLocationChangedListener;
import com.google.android.gms.maps.internal.ILocationSourceDelegate.zza;
import com.google.android.gms.maps.internal.zzl;
import com.google.android.gms.maps.model.RuntimeRemoteException;

class GoogleMap$12 extends zza {
    final /* synthetic */ LocationSource agK;
    final /* synthetic */ GoogleMap agz;

    GoogleMap$12(GoogleMap googleMap, LocationSource locationSource) {
        this.agz = googleMap;
        this.agK = locationSource;
    }

    public void activate(final zzl zzl) {
        this.agK.activate(new OnLocationChangedListener(this) {
            final /* synthetic */ GoogleMap$12 agM;

            public void onLocationChanged(Location location) {
                try {
                    zzl.zzd(location);
                } catch (RemoteException e) {
                    throw new RuntimeRemoteException(e);
                }
            }
        });
    }

    public void deactivate() {
        this.agK.deactivate();
    }
}
