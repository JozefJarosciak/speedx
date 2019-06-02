package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzpr.zzb;

public final class zzrl implements zzrk {

    private static class zza extends zzri {
        private final zzb<Status> zj;

        public zza(zzb<Status> zzb) {
            this.zj = zzb;
        }

        public void zzgj(int i) throws RemoteException {
            this.zj.setResult(new Status(i));
        }
    }

    public PendingResult<Status> zzg(GoogleApiClient googleApiClient) {
        return googleApiClient.zzd(new zza(this, googleApiClient) {
            final /* synthetic */ zzrl zi;

            protected void zza(zzrn zzrn) throws RemoteException {
                ((zzrp) zzrn.zzarw()).zza(new zza(this));
            }
        });
    }
}
