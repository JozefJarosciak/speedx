package com.google.android.gms.internal;

public class zzm<T> {
    public final T result;
    public final com.google.android.gms.internal.zzb.zza zzbf;
    public final zzr zzbg;
    public boolean zzbh;

    public interface zza {
        void zze(zzr zzr);
    }

    public interface zzb<T> {
        void zzb(T t);
    }

    private zzm(zzr zzr) {
        this.zzbh = false;
        this.result = null;
        this.zzbf = null;
        this.zzbg = zzr;
    }

    private zzm(T t, com.google.android.gms.internal.zzb.zza zza) {
        this.zzbh = false;
        this.result = t;
        this.zzbf = zza;
        this.zzbg = null;
    }

    public static <T> zzm<T> zza(T t, com.google.android.gms.internal.zzb.zza zza) {
        return new zzm(t, zza);
    }

    public static <T> zzm<T> zzd(zzr zzr) {
        return new zzm(zzr);
    }

    public boolean isSuccess() {
        return this.zzbg == null;
    }
}
