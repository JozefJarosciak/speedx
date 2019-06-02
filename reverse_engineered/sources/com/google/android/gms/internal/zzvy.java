package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Api.ApiOptions.Optional;

public final class zzvy implements Optional {
    public static final zzvy aul = new zza().zzbzp();
    private final boolean aum;
    private final boolean aun;
    private final Long auo;
    private final Long aup;
    private final boolean dT;
    private final boolean dV;
    private final String dW;
    private final String dX;

    public static final class zza {
        public zzvy zzbzp() {
            return new zzvy(false, false, null, false, null, false, null, null);
        }
    }

    private zzvy(boolean z, boolean z2, String str, boolean z3, String str2, boolean z4, Long l, Long l2) {
        this.aum = z;
        this.dT = z2;
        this.dW = str;
        this.dV = z3;
        this.aun = z4;
        this.dX = str2;
        this.auo = l;
        this.aup = l2;
    }

    public boolean zzafr() {
        return this.dT;
    }

    public boolean zzaft() {
        return this.dV;
    }

    public String zzafu() {
        return this.dW;
    }

    @Nullable
    public String zzafv() {
        return this.dX;
    }

    public boolean zzbzl() {
        return this.aum;
    }

    public boolean zzbzm() {
        return this.aun;
    }

    @Nullable
    public Long zzbzn() {
        return this.auo;
    }

    @Nullable
    public Long zzbzo() {
        return this.aup;
    }
}
