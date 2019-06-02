package com.google.android.gms.location.places.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.location.places.PlaceFilter;
import com.google.android.gms.location.places.PlaceReport;
import com.google.android.gms.location.places.PlacesOptions;
import com.google.android.gms.location.places.PlacesOptions.Builder;
import com.google.android.gms.location.places.zzl;
import java.util.Locale;

public class zzk extends com.google.android.gms.common.internal.zzk<zzf> {
    private final PlacesParams afy;
    private final Locale afz;

    public static class zza extends com.google.android.gms.common.api.Api.zza<zzk, PlacesOptions> {
        public /* synthetic */ zze zza(Context context, Looper looper, zzg zzg, Object obj, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return zzb(context, looper, zzg, (PlacesOptions) obj, connectionCallbacks, onConnectionFailedListener);
        }

        public zzk zzb(Context context, Looper looper, zzg zzg, PlacesOptions placesOptions, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            PlacesOptions build = placesOptions == null ? new Builder().build() : placesOptions;
            String packageName = context.getPackageName();
            if (build.afb != null) {
                packageName = build.afb;
            }
            return new zzk(context, looper, zzg, connectionCallbacks, onConnectionFailedListener, packageName, build);
        }
    }

    private zzk(Context context, Looper looper, zzg zzg, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener, String str, PlacesOptions placesOptions) {
        super(context, looper, 67, zzg, connectionCallbacks, onConnectionFailedListener);
        this.afz = Locale.getDefault();
        String str2 = null;
        if (zzg.getAccount() != null) {
            str2 = zzg.getAccount().name;
        }
        this.afy = new PlacesParams(str, this.afz, str2, placesOptions.afc, placesOptions.afd);
    }

    public void zza(zzl zzl, PlaceFilter placeFilter) throws RemoteException {
        if (placeFilter == null) {
            placeFilter = PlaceFilter.zzboc();
        }
        ((zzf) zzarw()).zza(placeFilter, this.afy, (zzi) zzl);
    }

    public void zza(zzl zzl, PlaceReport placeReport) throws RemoteException {
        zzab.zzaa(placeReport);
        ((zzf) zzarw()).zza(placeReport, this.afy, (zzi) zzl);
    }

    protected /* synthetic */ IInterface zzbb(IBinder iBinder) {
        return zzhd(iBinder);
    }

    protected zzf zzhd(IBinder iBinder) {
        return com.google.android.gms.location.places.internal.zzf.zza.zzgz(iBinder);
    }

    protected String zzra() {
        return "com.google.android.gms.location.places.PlaceDetectionApi";
    }

    protected String zzrb() {
        return "com.google.android.gms.location.places.internal.IGooglePlaceDetectionService";
    }
}
