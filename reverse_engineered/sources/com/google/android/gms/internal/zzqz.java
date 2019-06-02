package com.google.android.gms.internal;

import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

public class zzqz extends zzpt<Status> {
    @Deprecated
    public zzqz(Looper looper) {
        super(looper);
    }

    public zzqz(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    protected Status zzb(Status status) {
        return status;
    }

    protected /* synthetic */ Result zzc(Status status) {
        return zzb(status);
    }
}
