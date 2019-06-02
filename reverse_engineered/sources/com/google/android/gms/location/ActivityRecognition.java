package com.google.android.gms.location;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.Api.zzf;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.internal.zzpr$zza;
import com.google.android.gms.location.internal.zzl;

public class ActivityRecognition {
    public static final Api<NoOptions> API = new Api("ActivityRecognition.API", bO, bN);
    public static final ActivityRecognitionApi ActivityRecognitionApi = new com.google.android.gms.location.internal.zza();
    public static final String CLIENT_NAME = "activity_recognition";
    private static final zzf<zzl> bN = new zzf();
    private static final com.google.android.gms.common.api.Api.zza<zzl, NoOptions> bO = new C34181();

    /* renamed from: com.google.android.gms.location.ActivityRecognition$1 */
    class C34181 extends com.google.android.gms.common.api.Api.zza<zzl, NoOptions> {
        C34181() {
        }

        public /* synthetic */ zze zza(Context context, Looper looper, zzg zzg, Object obj, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return zzo(context, looper, zzg, (NoOptions) obj, connectionCallbacks, onConnectionFailedListener);
        }

        public zzl zzo(Context context, Looper looper, zzg zzg, NoOptions noOptions, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return new zzl(context, looper, connectionCallbacks, onConnectionFailedListener, ActivityRecognition.CLIENT_NAME);
        }
    }

    public static abstract class zza<R extends Result> extends zzpr$zza<R, zzl> {
        public zza(GoogleApiClient googleApiClient) {
            super(ActivityRecognition.API, googleApiClient);
        }
    }

    private ActivityRecognition() {
    }
}
