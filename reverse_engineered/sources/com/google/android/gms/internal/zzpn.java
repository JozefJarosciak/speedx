package com.google.android.gms.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.util.SparseArray;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.FirebaseException;

public abstract class zzpn {
    public final int it;
    public final int sn;

    public static final class zza extends zzpn {
        public final zzpr$zza<? extends Result, com.google.android.gms.common.api.Api.zzb> so;

        public zza(int i, int i2, zzpr$zza<? extends Result, com.google.android.gms.common.api.Api.zzb> zzpr_zza) {
            super(i, i2);
            this.so = zzpr_zza;
        }

        public boolean cancel() {
            return this.so.zzaos();
        }

        public void zza(SparseArray<zzrd> sparseArray) {
            zzrd zzrd = (zzrd) sparseArray.get(this.sn);
            if (zzrd != null) {
                zzrd.zzg(this.so);
            }
        }

        public void zzb(com.google.android.gms.common.api.Api.zzb zzb) throws DeadObjectException {
            this.so.zzb(zzb);
        }

        public void zzx(@NonNull Status status) {
            this.so.zzz(status);
        }
    }

    public static final class zzb<TResult> extends zzpn {
        private static final Status sr = new Status(8, "Connection to Google Play services was lost while executing the API call.");
        private final zzrb<com.google.android.gms.common.api.Api.zzb, TResult> sp;
        private final TaskCompletionSource<TResult> sq;

        public zzb(int i, int i2, zzrb<com.google.android.gms.common.api.Api.zzb, TResult> zzrb, TaskCompletionSource<TResult> taskCompletionSource) {
            super(i, i2);
            this.sq = taskCompletionSource;
            this.sp = zzrb;
        }

        public void zzb(com.google.android.gms.common.api.Api.zzb zzb) throws DeadObjectException {
            try {
                this.sp.zza(zzb, this.sq);
            } catch (DeadObjectException e) {
                zzx(sr);
                throw e;
            } catch (RemoteException e2) {
                zzx(sr);
            }
        }

        public void zzx(@NonNull Status status) {
            if (status.getStatusCode() == 8) {
                this.sq.setException(new FirebaseException(status.getStatusMessage()));
            } else {
                this.sq.setException(new FirebaseApiNotAvailableException(status.getStatusMessage()));
            }
        }
    }

    public zzpn(int i, int i2) {
        this.sn = i;
        this.it = i2;
    }

    public boolean cancel() {
        return true;
    }

    public void zza(SparseArray<zzrd> sparseArray) {
    }

    public abstract void zzb(com.google.android.gms.common.api.Api.zzb zzb) throws DeadObjectException;

    public abstract void zzx(@NonNull Status status);
}
