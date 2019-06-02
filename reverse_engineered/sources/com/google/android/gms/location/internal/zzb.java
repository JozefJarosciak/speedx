package com.google.android.gms.location.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.internal.zzk;
import com.google.android.gms.location.internal.zzi.zza;

public class zzb extends zzk<zzi> {
    private final String adx;
    protected final zzp<zzi> ady = new C34261(this);

    /* renamed from: com.google.android.gms.location.internal.zzb$1 */
    class C34261 implements zzp<zzi> {
        final /* synthetic */ zzb adz;

        C34261(zzb zzb) {
            this.adz = zzb;
        }

        public void zzarv() {
            this.adz.zzarv();
        }

        public /* synthetic */ IInterface zzarw() throws DeadObjectException {
            return zzbnj();
        }

        public zzi zzbnj() throws DeadObjectException {
            return (zzi) this.adz.zzarw();
        }
    }

    public zzb(Context context, Looper looper, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener, String str, zzg zzg) {
        super(context, looper, 23, zzg, connectionCallbacks, onConnectionFailedListener);
        this.adx = str;
    }

    protected Bundle zzaeu() {
        Bundle bundle = new Bundle();
        bundle.putString("client_name", this.adx);
        return bundle;
    }

    protected /* synthetic */ IInterface zzbb(IBinder iBinder) {
        return zzgt(iBinder);
    }

    protected zzi zzgt(IBinder iBinder) {
        return zza.zzgw(iBinder);
    }

    protected String zzra() {
        return "com.google.android.location.internal.GoogleLocationManagerService.START";
    }

    protected String zzrb() {
        return "com.google.android.gms.location.internal.IGoogleLocationManagerService";
    }
}
