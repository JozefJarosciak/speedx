package com.google.android.gms.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.internal.zzqs.zzb;

public abstract class zzpx<L> implements zzb<L> {
    private final DataHolder tk;

    protected zzpx(DataHolder dataHolder) {
        this.tk = dataHolder;
    }

    protected abstract void zza(L l, DataHolder dataHolder);

    public void zzapg() {
        if (this.tk != null) {
            this.tk.close();
        }
    }

    public final void zzu(L l) {
        zza(l, this.tk);
    }
}
