package com.google.android.gms.location.places;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

public class zzf extends com.google.android.gms.location.places.internal.zzh.zza {
    private final zzb aeA;
    private final zza aeB;

    public static abstract class zzb<A extends zze> extends com.google.android.gms.location.places.zzl.zzb<PlacePhotoMetadataResult, A> {
        public zzb(Api api, GoogleApiClient googleApiClient) {
            super(api, googleApiClient);
        }

        protected /* synthetic */ Result zzc(Status status) {
            return zzdl(status);
        }

        protected PlacePhotoMetadataResult zzdl(Status status) {
            return new PlacePhotoMetadataResult(status, null);
        }
    }

    public static abstract class zza<A extends zze> extends com.google.android.gms.location.places.zzl.zzb<PlacePhotoResult, A> {
        public zza(Api api, GoogleApiClient googleApiClient) {
            super(api, googleApiClient);
        }

        protected /* synthetic */ Result zzc(Status status) {
            return zzdk(status);
        }

        protected PlacePhotoResult zzdk(Status status) {
            return new PlacePhotoResult(status, null);
        }
    }

    public zzf(zza zza) {
        this.aeA = null;
        this.aeB = zza;
    }

    public zzf(zzb zzb) {
        this.aeA = zzb;
        this.aeB = null;
    }

    public void zza(PlacePhotoMetadataResult placePhotoMetadataResult) throws RemoteException {
        this.aeA.zzc(placePhotoMetadataResult);
    }

    public void zza(PlacePhotoResult placePhotoResult) throws RemoteException {
        this.aeB.zzc(placePhotoResult);
    }
}
