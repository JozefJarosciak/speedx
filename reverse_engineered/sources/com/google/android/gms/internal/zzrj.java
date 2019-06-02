package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.Api.zzf;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzg;

public final class zzrj {
    public static final Api<NoOptions> API = new Api("Common.API", bO, bN);
    public static final zzf<zzrn> bN = new zzf();
    private static final zza<zzrn, NoOptions> bO = new C34031();
    public static final zzrk zh = new zzrl();

    /* renamed from: com.google.android.gms.internal.zzrj$1 */
    class C34031 extends zza<zzrn, NoOptions> {
        C34031() {
        }

        public /* synthetic */ zze zza(Context context, Looper looper, zzg zzg, Object obj, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return zzf(context, looper, zzg, (NoOptions) obj, connectionCallbacks, onConnectionFailedListener);
        }

        public zzrn zzf(Context context, Looper looper, zzg zzg, NoOptions noOptions, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return new zzrn(context, looper, zzg, connectionCallbacks, onConnectionFailedListener);
        }
    }
}
