package com.google.android.gms.internal;

import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.zza;
import com.google.android.gms.common.api.zzb;
import java.util.Set;

public final class zzpq extends zzpt<zzb> {
    private int sx;
    private boolean sy;

    private void zza(ConnectionResult connectionResult) {
        ArrayMap arrayMap = null;
        for (int i = 0; i < arrayMap.size(); i++) {
            zza((zzpo) arrayMap.keyAt(i), connectionResult);
        }
    }

    public void zza(zzpo<?> zzpo, ConnectionResult connectionResult) {
        synchronized (null) {
            ArrayMap arrayMap = null;
            try {
                arrayMap.put(zzpo, connectionResult);
                this.sx--;
                boolean isSuccess = connectionResult.isSuccess();
                if (!isSuccess) {
                    this.sy = isSuccess;
                }
                if (this.sx == 0) {
                    Status status = this.sy ? new Status(13) : Status.sg;
                    arrayMap = null;
                    zzc(arrayMap.size() == 1 ? new zza(status, null) : new zzb(status, null));
                }
            } finally {
            }
        }
    }

    public Set<zzpo<?>> zzaon() {
        ArrayMap arrayMap = null;
        return arrayMap.keySet();
    }

    protected /* synthetic */ Result zzc(Status status) {
        return zzy(status);
    }

    protected zzb zzy(Status status) {
        zzb zzb;
        synchronized (null) {
            try {
                zza(new ConnectionResult(8));
                ArrayMap arrayMap = null;
                if (arrayMap.size() != 1) {
                    zzb = new zzb(status, null);
                }
            } finally {
            }
        }
        return zzb;
    }
}
