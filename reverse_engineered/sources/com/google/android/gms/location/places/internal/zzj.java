package com.google.android.gms.location.places.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.PlaceDetectionApi;
import com.google.android.gms.location.places.PlaceFilter;
import com.google.android.gms.location.places.PlaceLikelihoodBuffer;
import com.google.android.gms.location.places.PlaceReport;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.zzl;
import com.google.android.gms.location.places.zzl.zzd;
import com.google.android.gms.location.places.zzl.zzf;

public class zzj implements PlaceDetectionApi {
    public PendingResult<PlaceLikelihoodBuffer> getCurrentPlace(GoogleApiClient googleApiClient, final PlaceFilter placeFilter) {
        return googleApiClient.zzc(new zzd<zzk>(this, Places.PLACE_DETECTION_API, googleApiClient) {
            final /* synthetic */ zzj afB;

            protected void zza(zzk zzk) throws RemoteException {
                zzk.zza(new zzl((zzd) this, zzk.getContext()), placeFilter);
            }
        });
    }

    public PendingResult<Status> reportDeviceAtPlace(GoogleApiClient googleApiClient, final PlaceReport placeReport) {
        return googleApiClient.zzd(new zzf<zzk>(this, Places.PLACE_DETECTION_API, googleApiClient) {
            final /* synthetic */ zzj afB;

            protected void zza(zzk zzk) throws RemoteException {
                zzk.zza(new zzl((zzf) this), placeReport);
            }
        });
    }
}
