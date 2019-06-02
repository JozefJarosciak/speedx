package com.google.android.gms.internal;

import java.io.IOException;

public abstract class zzapc {
    protected volatile int bik = -1;

    public static final <T extends zzapc> T zza(T t, byte[] bArr) throws zzapb {
        return zzb(t, bArr, 0, bArr.length);
    }

    public static final void zza(zzapc zzapc, byte[] bArr, int i, int i2) {
        try {
            zzaov zzc = zzaov.zzc(bArr, i, i2);
            zzapc.zza(zzc);
            zzc.ab();
        } catch (Throwable e) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e);
        }
    }

    public static final <T extends zzapc> T zzb(T t, byte[] bArr, int i, int i2) throws zzapb {
        try {
            zzaou zzb = zzaou.zzb(bArr, i, i2);
            t.zzb(zzb);
            zzb.zzaef(0);
            return t;
        } catch (zzapb e) {
            throw e;
        } catch (IOException e2) {
            throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).");
        }
    }

    public static final byte[] zzf(zzapc zzapc) {
        byte[] bArr = new byte[zzapc.ao()];
        zza(zzapc, bArr, 0, bArr.length);
        return bArr;
    }

    public zzapc ad() throws CloneNotSupportedException {
        return (zzapc) super.clone();
    }

    public int an() {
        if (this.bik < 0) {
            ao();
        }
        return this.bik;
    }

    public int ao() {
        int zzy = zzy();
        this.bik = zzy;
        return zzy;
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return ad();
    }

    public String toString() {
        return zzapd.zzg(this);
    }

    public void zza(zzaov zzaov) throws IOException {
    }

    public abstract zzapc zzb(zzaou zzaou) throws IOException;

    protected int zzy() {
        return 0;
    }
}
