package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.DeadObjectException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzah;

public class zzqa implements zzqe {
    private final zzqf tm;
    private boolean tn = false;

    public zzqa(zzqf zzqf) {
        this.tm = zzqf;
    }

    private <A extends zzb> void zzf(zzpr$zza<? extends Result, A> zzpr_zza) throws DeadObjectException {
        this.tm.sX.ue.zzg(zzpr_zza);
        zzb zzb = this.tm.sX.zzb(zzpr_zza.zzanp());
        if (zzb.isConnected() || !this.tm.un.containsKey(zzpr_zza.zzanp())) {
            if (zzb instanceof zzah) {
                zzb = ((zzah) zzb).zzatj();
            }
            zzpr_zza.zzb(zzb);
            return;
        }
        zzpr_zza.zzz(new Status(17));
    }

    public void begin() {
    }

    public void connect() {
        if (this.tn) {
            this.tn = false;
            this.tm.zza(new zza(this, this) {
                final /* synthetic */ zzqa to;

                public void zzapi() {
                    this.to.tm.ur.zzm(null);
                }
            });
        }
    }

    public boolean disconnect() {
        if (this.tn) {
            return false;
        }
        if (this.tm.sX.zzapu()) {
            this.tn = true;
            for (zzrc zzaqt : this.tm.sX.ud) {
                zzaqt.zzaqt();
            }
            return false;
        }
        this.tm.zzi(null);
        return true;
    }

    public void onConnected(Bundle bundle) {
    }

    public void onConnectionSuspended(int i) {
        this.tm.zzi(null);
        this.tm.ur.zzc(i, this.tn);
    }

    public void zza(ConnectionResult connectionResult, Api<?> api, int i) {
    }

    void zzaph() {
        if (this.tn) {
            this.tn = false;
            this.tm.sX.ue.release();
            disconnect();
        }
    }

    public <A extends zzb, R extends Result, T extends zzpr$zza<R, A>> T zzc(T t) {
        return zzd(t);
    }

    public <A extends zzb, T extends zzpr$zza<? extends Result, A>> T zzd(T t) {
        try {
            zzf(t);
        } catch (DeadObjectException e) {
            this.tm.zza(new zza(this, this) {
                final /* synthetic */ zzqa to;

                public void zzapi() {
                    this.to.onConnectionSuspended(1);
                }
            });
        }
        return t;
    }
}
