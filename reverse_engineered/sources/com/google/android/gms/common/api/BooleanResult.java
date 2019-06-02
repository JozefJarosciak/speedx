package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.zzab;

public class BooleanResult implements Result {
    private final Status cc;
    private final boolean rD;

    public BooleanResult(Status status, boolean z) {
        this.cc = (Status) zzab.zzb((Object) status, (Object) "Status must not be null");
        this.rD = z;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BooleanResult)) {
            return false;
        }
        BooleanResult booleanResult = (BooleanResult) obj;
        return this.cc.equals(booleanResult.cc) && this.rD == booleanResult.rD;
    }

    public Status getStatus() {
        return this.cc;
    }

    public boolean getValue() {
        return this.rD;
    }

    public final int hashCode() {
        return (this.rD ? 1 : 0) + ((this.cc.hashCode() + 527) * 31);
    }
}
