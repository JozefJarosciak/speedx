package com.google.android.gms.maps;

import android.graphics.Bitmap;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.maps.internal.zzac.zza;

class GoogleMap$11 extends zza {
    final /* synthetic */ GoogleMap$SnapshotReadyCallback agJ;
    final /* synthetic */ GoogleMap agz;

    GoogleMap$11(GoogleMap googleMap, GoogleMap$SnapshotReadyCallback googleMap$SnapshotReadyCallback) {
        this.agz = googleMap;
        this.agJ = googleMap$SnapshotReadyCallback;
    }

    public void onSnapshotReady(Bitmap bitmap) throws RemoteException {
        this.agJ.onSnapshotReady(bitmap);
    }

    public void zzaf(zzd zzd) throws RemoteException {
        this.agJ.onSnapshotReady((Bitmap) zze.zzad(zzd));
    }
}
