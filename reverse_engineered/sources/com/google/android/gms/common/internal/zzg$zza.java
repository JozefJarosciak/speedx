package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.Scope;
import java.util.Collections;
import java.util.Set;

public final class zzg$zza {
    public final Set<Scope> dY;
    public final boolean yc;

    public zzg$zza(Set<Scope> set, boolean z) {
        zzab.zzaa(set);
        this.dY = Collections.unmodifiableSet(set);
        this.yc = z;
    }
}
