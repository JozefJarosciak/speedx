package com.google.android.gms.internal;

import com.google.android.gms.internal.zzah.zzf;
import com.google.android.gms.internal.zzah.zzj;
import java.io.IOException;

public interface zzadx {

    public static final class zza extends zzaow<zza> {
        public long aDp;
        public zzj aDq;
        public zzf zzwq;

        public zza() {
            zzcgt();
        }

        public static zza zzap(byte[] bArr) throws zzapb {
            return (zza) zzapc.zza(new zza(), bArr);
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            if (this.aDp != zza.aDp) {
                return false;
            }
            if (this.zzwq == null) {
                if (zza.zzwq != null) {
                    return false;
                }
            } else if (!this.zzwq.equals(zza.zzwq)) {
                return false;
            }
            if (this.aDq == null) {
                if (zza.aDq != null) {
                    return false;
                }
            } else if (!this.aDq.equals(zza.aDq)) {
                return false;
            }
            return (this.bib == null || this.bib.isEmpty()) ? zza.bib == null || zza.bib.isEmpty() : this.bib.equals(zza.bib);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.aDq == null ? 0 : this.aDq.hashCode()) + (((this.zzwq == null ? 0 : this.zzwq.hashCode()) + ((((getClass().getName().hashCode() + 527) * 31) + ((int) (this.aDp ^ (this.aDp >>> 32)))) * 31)) * 31)) * 31;
            if (!(this.bib == null || this.bib.isEmpty())) {
                i = this.bib.hashCode();
            }
            return hashCode + i;
        }

        public void zza(zzaov zzaov) throws IOException {
            zzaov.zzb(1, this.aDp);
            if (this.zzwq != null) {
                zzaov.zza(2, this.zzwq);
            }
            if (this.aDq != null) {
                zzaov.zza(3, this.aDq);
            }
            super.zza(zzaov);
        }

        public /* synthetic */ zzapc zzb(zzaou zzaou) throws IOException {
            return zzbt(zzaou);
        }

        public zza zzbt(zzaou zzaou) throws IOException {
            while (true) {
                int J = zzaou.m16039J();
                switch (J) {
                    case 0:
                        break;
                    case 8:
                        this.aDp = zzaou.m16042M();
                        continue;
                    case 18:
                        if (this.zzwq == null) {
                            this.zzwq = new zzf();
                        }
                        zzaou.zza(this.zzwq);
                        continue;
                    case 26:
                        if (this.aDq == null) {
                            this.aDq = new zzj();
                        }
                        zzaou.zza(this.aDq);
                        continue;
                    default:
                        if (!super.zza(zzaou, J)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public zza zzcgt() {
            this.aDp = 0;
            this.zzwq = null;
            this.aDq = null;
            this.bib = null;
            this.bik = -1;
            return this;
        }

        protected int zzy() {
            int zzy = super.zzy() + zzaov.zze(1, this.aDp);
            if (this.zzwq != null) {
                zzy += zzaov.zzc(2, this.zzwq);
            }
            return this.aDq != null ? zzy + zzaov.zzc(3, this.aDq) : zzy;
        }
    }
}
