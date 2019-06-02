package com.google.android.gms.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.internal.zzaa;

public final class zzpo<O extends ApiOptions> {
    private final Api<O> pD;
    private final O rF;

    public zzpo(Api<O> api, O o) {
        this.pD = api;
        this.rF = o;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzpo)) {
            return false;
        }
        zzpo zzpo = (zzpo) obj;
        return zzaa.equal(this.pD, zzpo.pD) && zzaa.equal(this.rF, zzpo.rF);
    }

    public int hashCode() {
        return zzaa.hashCode(this.pD, this.rF);
    }

    public zzc<?> zzanp() {
        return this.pD.zzanp();
    }

    public String zzaok() {
        return this.pD.getName();
    }
}
