package com.google.android.gms.location.places.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.location.places.AddPlaceRequest;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.AutocompletePredictionBuffer;
import com.google.android.gms.location.places.GeoDataApi;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.PlacePhotoMetadataResult;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.zzf;
import com.google.android.gms.location.places.zzf.zzb;
import com.google.android.gms.location.places.zzl;
import com.google.android.gms.location.places.zzl.zza;
import com.google.android.gms.location.places.zzl.zzc;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.Arrays;

public class zzd implements GeoDataApi {
    public PendingResult<PlaceBuffer> addPlace(GoogleApiClient googleApiClient, final AddPlaceRequest addPlaceRequest) {
        return googleApiClient.zzd(new zzc<zze>(this, Places.GEO_DATA_API, googleApiClient) {
            final /* synthetic */ zzd afs;

            protected void zza(zze zze) throws RemoteException {
                zze.zza(new zzl((zzc) this, zze.getContext()), addPlaceRequest);
            }
        });
    }

    public PendingResult<AutocompletePredictionBuffer> getAutocompletePredictions(GoogleApiClient googleApiClient, String str, LatLngBounds latLngBounds, AutocompleteFilter autocompleteFilter) {
        final String str2 = str;
        final LatLngBounds latLngBounds2 = latLngBounds;
        final AutocompleteFilter autocompleteFilter2 = autocompleteFilter;
        return googleApiClient.zzc(new zza<zze>(this, Places.GEO_DATA_API, googleApiClient) {
            final /* synthetic */ zzd afs;

            protected void zza(zze zze) throws RemoteException {
                zze.zza(new zzl((zza) this), str2, latLngBounds2, autocompleteFilter2);
            }
        });
    }

    public PendingResult<PlaceBuffer> getPlaceById(GoogleApiClient googleApiClient, final String... strArr) {
        boolean z = true;
        if (strArr == null || strArr.length < 1) {
            z = false;
        }
        zzab.zzbn(z);
        return googleApiClient.zzc(new zzc<zze>(this, Places.GEO_DATA_API, googleApiClient) {
            final /* synthetic */ zzd afs;

            protected void zza(zze zze) throws RemoteException {
                zze.zza(new zzl((zzc) this, zze.getContext()), Arrays.asList(strArr));
            }
        });
    }

    public PendingResult<PlacePhotoMetadataResult> getPlacePhotos(GoogleApiClient googleApiClient, final String str) {
        return googleApiClient.zzc(new zzb<zze>(this, Places.GEO_DATA_API, googleApiClient) {
            final /* synthetic */ zzd afs;

            protected void zza(zze zze) throws RemoteException {
                zze.zza(new zzf((zzb) this), str);
            }
        });
    }
}
