package com.google.android.gms.location.places;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.util.zzu;
import com.google.android.gms.internal.zzpr$zza;

public class zzl extends com.google.android.gms.location.places.internal.zzi.zza {
    private static final String TAG = zzl.class.getSimpleName();
    private final zzd aeW;
    private final zza aeX;
    private final zze aeY;
    private final zzf aeZ;
    private final zzc afa;
    private final Context mContext;

    public static abstract class zzb<R extends Result, A extends com.google.android.gms.common.api.Api.zze> extends zzpr$zza<R, A> {
        public zzb(Api api, GoogleApiClient googleApiClient) {
            super(api, googleApiClient);
        }
    }

    public static abstract class zzc<A extends com.google.android.gms.common.api.Api.zze> extends zzb<PlaceBuffer, A> {
        public zzc(Api api, GoogleApiClient googleApiClient) {
            super(api, googleApiClient);
        }

        protected /* synthetic */ Result zzc(Status status) {
            return zzdo(status);
        }

        protected PlaceBuffer zzdo(Status status) {
            return new PlaceBuffer(DataHolder.zzfp(status.getStatusCode()), null);
        }
    }

    public static abstract class zza<A extends com.google.android.gms.common.api.Api.zze> extends zzb<AutocompletePredictionBuffer, A> {
        public zza(Api api, GoogleApiClient googleApiClient) {
            super(api, googleApiClient);
        }

        protected /* synthetic */ Result zzc(Status status) {
            return zzdn(status);
        }

        protected AutocompletePredictionBuffer zzdn(Status status) {
            return new AutocompletePredictionBuffer(DataHolder.zzfp(status.getStatusCode()));
        }
    }

    public static abstract class zzd<A extends com.google.android.gms.common.api.Api.zze> extends zzb<PlaceLikelihoodBuffer, A> {
        public zzd(Api api, GoogleApiClient googleApiClient) {
            super(api, googleApiClient);
        }

        protected /* synthetic */ Result zzc(Status status) {
            return zzdp(status);
        }

        protected PlaceLikelihoodBuffer zzdp(Status status) {
            return new PlaceLikelihoodBuffer(DataHolder.zzfp(status.getStatusCode()), 100, null);
        }
    }

    public static abstract class zzf<A extends com.google.android.gms.common.api.Api.zze> extends zzb<Status, A> {
        public zzf(Api api, GoogleApiClient googleApiClient) {
            super(api, googleApiClient);
        }

        protected Status zzb(Status status) {
            return status;
        }

        protected /* synthetic */ Result zzc(Status status) {
            return zzb(status);
        }
    }

    public static abstract class zze<A extends com.google.android.gms.common.api.Api.zze> extends zzb<com.google.android.gms.location.places.personalized.zze, A> {
    }

    public zzl(zza zza) {
        this.aeW = null;
        this.aeX = zza;
        this.aeY = null;
        this.aeZ = null;
        this.afa = null;
        this.mContext = null;
    }

    public zzl(zzc zzc, Context context) {
        this.aeW = null;
        this.aeX = null;
        this.aeY = null;
        this.aeZ = null;
        this.afa = zzc;
        this.mContext = context.getApplicationContext();
    }

    public zzl(zzd zzd, Context context) {
        this.aeW = zzd;
        this.aeX = null;
        this.aeY = null;
        this.aeZ = null;
        this.afa = null;
        this.mContext = context.getApplicationContext();
    }

    public zzl(zzf zzf) {
        this.aeW = null;
        this.aeX = null;
        this.aeY = null;
        this.aeZ = zzf;
        this.afa = null;
        this.mContext = null;
    }

    public void zzbm(DataHolder dataHolder) throws RemoteException {
        zzab.zza(this.aeW != null, (Object) "placeEstimator cannot be null");
        if (dataHolder == null) {
            if (Log.isLoggable(TAG, 6)) {
                String str = TAG;
                String str2 = "onPlaceEstimated received null DataHolder: ";
                String valueOf = String.valueOf(zzu.zzavw());
                Log.e(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            }
            this.aeW.zzz(Status.si);
            return;
        }
        Bundle zzaqy = dataHolder.zzaqy();
        this.aeW.zzc(new PlaceLikelihoodBuffer(dataHolder, zzaqy == null ? 100 : PlaceLikelihoodBuffer.zzak(zzaqy), this.mContext));
    }

    public void zzbn(DataHolder dataHolder) throws RemoteException {
        if (dataHolder == null) {
            if (Log.isLoggable(TAG, 6)) {
                String str = TAG;
                String str2 = "onAutocompletePrediction received null DataHolder: ";
                String valueOf = String.valueOf(zzu.zzavw());
                Log.e(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            }
            this.aeX.zzz(Status.si);
            return;
        }
        this.aeX.zzc(new AutocompletePredictionBuffer(dataHolder));
    }

    public void zzbo(DataHolder dataHolder) throws RemoteException {
        zze zze = null;
        if (dataHolder == null) {
            if (Log.isLoggable(TAG, 6)) {
                String str = TAG;
                String str2 = "onPlaceUserDataFetched received null DataHolder: ";
                String valueOf = String.valueOf(zzu.zzavw());
                Log.e(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            }
            zze.zzz(Status.si);
            return;
        }
        zze.zzc(new com.google.android.gms.location.places.personalized.zze(dataHolder));
    }

    public void zzbp(DataHolder dataHolder) throws RemoteException {
        this.afa.zzc(new PlaceBuffer(dataHolder, this.mContext));
    }

    public void zzdm(Status status) throws RemoteException {
        this.aeZ.zzc(status);
    }
}
