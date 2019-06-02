package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Arrays;

final class zzape {
    final byte[] bil;
    final int tag;

    zzape(int i, byte[] bArr) {
        this.tag = i;
        this.bil = bArr;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzape)) {
            return false;
        }
        zzape zzape = (zzape) obj;
        return this.tag == zzape.tag && Arrays.equals(this.bil, zzape.bil);
    }

    public int hashCode() {
        return ((this.tag + 527) * 31) + Arrays.hashCode(this.bil);
    }

    void zza(zzaov zzaov) throws IOException {
        zzaov.zzaes(this.tag);
        zzaov.zzbd(this.bil);
    }

    int zzy() {
        return (zzaov.zzaet(this.tag) + 0) + this.bil.length;
    }
}
