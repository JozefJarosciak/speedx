package com.google.android.gms.location.places.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.internal.zzk;
import com.google.android.gms.location.places.AddPlaceRequest;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.PlacesOptions;
import com.google.android.gms.location.places.PlacesOptions.Builder;
import com.google.android.gms.location.places.zzf;
import com.google.android.gms.location.places.zzl;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.List;
import java.util.Locale;

public class zze extends zzk<zzg> {
    private final PlacesParams afy;
    private final Locale afz;

    public static class zza extends com.google.android.gms.common.api.Api.zza<zze, PlacesOptions> {
        public zze zza(Context context, Looper looper, zzg zzg, PlacesOptions placesOptions, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            PlacesOptions build = placesOptions == null ? new Builder().build() : placesOptions;
            String packageName = context.getPackageName();
            if (build.afb != null) {
                packageName = build.afb;
            }
            return new zze(context, looper, zzg, connectionCallbacks, onConnectionFailedListener, packageName, build);
        }
    }

    private zze(Context context, Looper looper, zzg zzg, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener, String str, PlacesOptions placesOptions) {
        super(context, looper, 65, zzg, connectionCallbacks, onConnectionFailedListener);
        this.afz = Locale.getDefault();
        String str2 = null;
        if (zzg.getAccount() != null) {
            str2 = zzg.getAccount().name;
        }
        this.afy = new PlacesParams(str, this.afz, str2, placesOptions.afc, placesOptions.afd);
    }

    public void zza(zzf zzf, String str) throws RemoteException {
        zzab.zzb((Object) str, (Object) "placeId cannot be null");
        ((zzg) zzarw()).zza(str, this.afy, (zzh) zzf);
    }

    public void zza(zzf zzf, String str, int i, int i2, int i3) throws RemoteException {
        boolean z = true;
        zzab.zzb((Object) str, (Object) "fifeUrl cannot be null");
        zzab.zzb(i > 0, (Object) "width should be > 0");
        if (i <= 0) {
            z = false;
        }
        zzab.zzb(z, (Object) "height should be > 0");
        ((zzg) zzarw()).zza(str, i, i2, i3, this.afy, (zzh) zzf);
    }

    public void zza(zzl zzl, AddPlaceRequest addPlaceRequest) throws RemoteException {
        zzab.zzb((Object) addPlaceRequest, (Object) "userAddedPlace == null");
        ((zzg) zzarw()).zza(addPlaceRequest, this.afy, (zzi) zzl);
    }

    public void zza(zzl zzl, String str, @Nullable LatLngBounds latLngBounds, @Nullable AutocompleteFilter autocompleteFilter) throws RemoteException {
        zzab.zzb((Object) zzl, (Object) "callback == null");
        ((zzg) zzarw()).zza(str == null ? "" : str, latLngBounds, autocompleteFilter == null ? new AutocompleteFilter.Builder().build() : autocompleteFilter, this.afy, (zzi) zzl);
    }

    public void zza(zzl zzl, List<String> list) throws RemoteException {
        ((zzg) zzarw()).zzb((List) list, this.afy, (zzi) zzl);
    }

    protected /* synthetic */ IInterface zzbb(IBinder iBinder) {
        return zzgy(iBinder);
    }

    protected zzg zzgy(IBinder iBinder) {
        return com.google.android.gms.location.places.internal.zzg.zza.zzha(iBinder);
    }

    protected String zzra() {
        return "com.google.android.gms.location.places.GeoDataApi";
    }

    protected String zzrb() {
        return "com.google.android.gms.location.places.internal.IGooglePlacesService";
    }
}
