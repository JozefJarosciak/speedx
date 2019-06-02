package com.google.android.gms.location.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.zzpr.zzb;
import java.util.List;

class zzf$3 extends zzf$zza {
    final /* synthetic */ zzf adJ;
    final /* synthetic */ List adK;

    zzf$3(zzf zzf, GoogleApiClient googleApiClient, List list) {
        this.adJ = zzf;
        this.adK = list;
        super(googleApiClient);
    }

    protected void zza(zzl zzl) throws RemoteException {
        zzl.zza(this.adK, (zzb) this);
    }
}
