package com.google.android.gms.location;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.internal.zzpr$zza;
import com.google.android.gms.location.internal.zzd;
import com.google.android.gms.location.internal.zzf;
import com.google.android.gms.location.internal.zzl;
import com.google.android.gms.location.internal.zzq;

public class LocationServices {
    public static final Api<NoOptions> API = new Api("LocationServices.API", bO, bN);
    public static final FusedLocationProviderApi FusedLocationApi = new zzd();
    public static final GeofencingApi GeofencingApi = new zzf();
    public static final SettingsApi SettingsApi = new zzq();
    private static final Api.zzf<zzl> bN = new Api.zzf();
    private static final com.google.android.gms.common.api.Api.zza<zzl, NoOptions> bO = new C34211();

    /* renamed from: com.google.android.gms.location.LocationServices$1 */
    class C34211 extends com.google.android.gms.common.api.Api.zza<zzl, NoOptions> {
        C34211() {
        }

        public /* synthetic */ zze zza(Context context, Looper looper, zzg zzg, Object obj, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return zzo(context, looper, zzg, (NoOptions) obj, connectionCallbacks, onConnectionFailedListener);
        }

        public zzl zzo(Context context, Looper looper, zzg zzg, NoOptions noOptions, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return new zzl(context, looper, connectionCallbacks, onConnectionFailedListener, "locationServices", zzg);
        }
    }

    public static abstract class zza<R extends Result> extends zzpr$zza<R, zzl> {
        public zza(GoogleApiClient googleApiClient) {
            super(LocationServices.API, googleApiClient);
        }
    }

    private LocationServices() {
    }

    public static zzl zzj(GoogleApiClient googleApiClient) {
        boolean z = true;
        zzab.zzb(googleApiClient != null, (Object) "GoogleApiClient parameter is required.");
        zzl zzl = (zzl) googleApiClient.zza(bN);
        if (zzl == null) {
            z = false;
        }
        zzab.zza(z, (Object) "GoogleApiClient is not configured to use the LocationServices.API Api. Pass thisinto GoogleApiClient.Builder#addApi() to use this feature.");
        return zzl;
    }
}
