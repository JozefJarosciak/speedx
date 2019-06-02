package com.google.android.gms.location.internal;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.zzpr.zzb;
import com.google.android.gms.location.GeofencingRequest;

class zzf$1 extends zzf$zza {
    final /* synthetic */ PendingIntent Op;
    final /* synthetic */ GeofencingRequest adI;
    final /* synthetic */ zzf adJ;

    zzf$1(zzf zzf, GoogleApiClient googleApiClient, GeofencingRequest geofencingRequest, PendingIntent pendingIntent) {
        this.adJ = zzf;
        this.adI = geofencingRequest;
        this.Op = pendingIntent;
        super(googleApiClient);
    }

    protected void zza(zzl zzl) throws RemoteException {
        zzl.zza(this.adI, this.Op, (zzb) this);
    }
}
