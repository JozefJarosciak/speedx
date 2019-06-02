package com.google.android.gms.internal;

import java.io.IOException;

final class zzanj<T> extends zzank<T> {
    private final zzams beA;
    private final zzaoo<T> beB;
    private final zzanl beC;
    private zzank<T> bej;
    private final zzang<T> bey;
    private final zzamx<T> bez;

    private static class zza implements zzanl {
        private final zzaoo<?> beD;
        private final boolean beE;
        private final Class<?> beF;
        private final zzang<?> bey;
        private final zzamx<?> bez;

        private zza(Object obj, zzaoo<?> zzaoo, boolean z, Class<?> cls) {
            this.bey = obj instanceof zzang ? (zzang) obj : null;
            this.bez = obj instanceof zzamx ? (zzamx) obj : null;
            boolean z2 = (this.bey == null && this.bez == null) ? false : true;
            zzanq.zzbn(z2);
            this.beD = zzaoo;
            this.beE = z;
            this.beF = cls;
        }

        public <T> zzank<T> zza(zzams zzams, zzaoo<T> zzaoo) {
            boolean isAssignableFrom = this.beD != null ? this.beD.equals(zzaoo) || (this.beE && this.beD.m16037t() == zzaoo.m16036s()) : this.beF.isAssignableFrom(zzaoo.m16036s());
            return isAssignableFrom ? new zzanj(this.bey, this.bez, zzams, zzaoo, this) : null;
        }
    }

    private zzanj(zzang<T> zzang, zzamx<T> zzamx, zzams zzams, zzaoo<T> zzaoo, zzanl zzanl) {
        this.bey = zzang;
        this.bez = zzamx;
        this.beA = zzams;
        this.beB = zzaoo;
        this.beC = zzanl;
    }

    public static zzanl zza(zzaoo<?> zzaoo, Object obj) {
        return new zza(obj, zzaoo, false, null);
    }

    public static zzanl zzb(zzaoo<?> zzaoo, Object obj) {
        return new zza(obj, zzaoo, zzaoo.m16037t() == zzaoo.m16036s(), null);
    }

    private zzank<T> zzczx() {
        zzank<T> zzank = this.bej;
        if (zzank != null) {
            return zzank;
        }
        zzank = this.beA.zza(this.beC, this.beB);
        this.bej = zzank;
        return zzank;
    }

    public void zza(zzaor zzaor, T t) throws IOException {
        if (this.bey == null) {
            zzczx().zza(zzaor, t);
        } else if (t == null) {
            zzaor.mo4206r();
        } else {
            zzanz.zzb(this.bey.zza(t, this.beB.m16037t(), this.beA.beh), zzaor);
        }
    }

    public T zzb(zzaop zzaop) throws IOException {
        if (this.bez == null) {
            return zzczx().zzb(zzaop);
        }
        zzamy zzh = zzanz.zzh(zzaop);
        if (zzh.zzczp()) {
            return null;
        }
        try {
            return this.bez.zzb(zzh, this.beB.m16037t(), this.beA.beg);
        } catch (zzanc e) {
            throw e;
        } catch (Throwable e2) {
            throw new zzanc(e2);
        }
    }
}
