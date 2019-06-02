package com.google.android.gms.internal;

import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.zzc;

public class zzqi<O extends ApiOptions> extends zzpz {
    private final zzc<O> uP;

    public zzqi(zzc<O> zzc) {
        super("Method is not supported by connectionless client. APIs supporting connectionless client must not call this method.");
        this.uP = zzc;
    }

    public Looper getLooper() {
        return this.uP.getLooper();
    }

    public void zza(zzrc zzrc) {
        this.uP.zzanu();
    }

    public void zzb(zzrc zzrc) {
        this.uP.zzanv();
    }

    public <A extends zzb, R extends Result, T extends zzpr$zza<R, A>> T zzc(@NonNull T t) {
        return this.uP.zza((zzpr$zza) t);
    }

    public <A extends zzb, T extends zzpr$zza<? extends Result, A>> T zzd(@NonNull T t) {
        return this.uP.zzb((zzpr$zza) t);
    }
}
