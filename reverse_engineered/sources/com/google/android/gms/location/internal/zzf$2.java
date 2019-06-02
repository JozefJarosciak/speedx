package com.google.android.gms.location.internal;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.zzpr.zzb;

class zzf$2 extends zzf$zza {
    final /* synthetic */ PendingIntent Op;
    final /* synthetic */ zzf adJ;

    zzf$2(zzf zzf, GoogleApiClient googleApiClient, PendingIntent pendingIntent) {
        this.adJ = zzf;
        this.Op = pendingIntent;
        super(googleApiClient);
    }

    protected void zza(zzl zzl) throws RemoteException {
        zzl.zza(this.Op, (zzb) this);
    }
}
