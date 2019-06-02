package com.google.android.gms.auth.api.signin.internal;

public class zze {
    static int ek = 31;
    private int el = 1;

    public int zzagc() {
        return this.el;
    }

    public zze zzba(boolean z) {
        this.el = (z ? 1 : 0) + (this.el * ek);
        return this;
    }

    public zze zzr(Object obj) {
        this.el = (obj == null ? 0 : obj.hashCode()) + (this.el * ek);
        return this;
    }
}
