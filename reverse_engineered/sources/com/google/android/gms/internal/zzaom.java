package com.google.android.gms.internal;

import com.google.android.gms.internal.zzaoj.zza;
import java.io.IOException;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

final class zzaom<T> extends zzank<T> {
    private final zzank<T> bej;
    private final zzams bgf;
    private final Type bgg;

    zzaom(zzams zzams, zzank<T> zzank, Type type) {
        this.bgf = zzams;
        this.bej = zzank;
        this.bgg = type;
    }

    private Type zzb(Type type, Object obj) {
        return obj != null ? (type == Object.class || (type instanceof TypeVariable) || (type instanceof Class)) ? obj.getClass() : type : type;
    }

    public void zza(zzaor zzaor, T t) throws IOException {
        zzank zzank = this.bej;
        Type zzb = zzb(this.bgg, t);
        if (zzb != this.bgg) {
            zzank = this.bgf.zza(zzaoo.zzl(zzb));
            if ((zzank instanceof zza) && !(this.bej instanceof zza)) {
                zzank = this.bej;
            }
        }
        zzank.zza(zzaor, t);
    }

    public T zzb(zzaop zzaop) throws IOException {
        return this.bej.zzb(zzaop);
    }
}
