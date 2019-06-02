package com.google.android.gms.internal;

import java.io.IOException;

public interface zzuo {

    public static final class zza extends zzapc {
        private static volatile zza[] aow;
        public Boolean aox;
        public Boolean aoy;
        public String name;

        public zza() {
            zzbwk();
        }

        public static zza[] zzbwj() {
            if (aow == null) {
                synchronized (zzapa.bij) {
                    if (aow == null) {
                        aow = new zza[0];
                    }
                }
            }
            return aow;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            if (this.name == null) {
                if (zza.name != null) {
                    return false;
                }
            } else if (!this.name.equals(zza.name)) {
                return false;
            }
            if (this.aox == null) {
                if (zza.aox != null) {
                    return false;
                }
            } else if (!this.aox.equals(zza.aox)) {
                return false;
            }
            return this.aoy == null ? zza.aoy == null : this.aoy.equals(zza.aoy);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.aox == null ? 0 : this.aox.hashCode()) + (((this.name == null ? 0 : this.name.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31;
            if (this.aoy != null) {
                i = this.aoy.hashCode();
            }
            return hashCode + i;
        }

        public void zza(zzaov zzaov) throws IOException {
            if (this.name != null) {
                zzaov.zzr(1, this.name);
            }
            if (this.aox != null) {
                zzaov.zzj(2, this.aox.booleanValue());
            }
            if (this.aoy != null) {
                zzaov.zzj(3, this.aoy.booleanValue());
            }
            super.zza(zzaov);
        }

        public /* synthetic */ zzapc zzb(zzaou zzaou) throws IOException {
            return zzbj(zzaou);
        }

        public zza zzbj(zzaou zzaou) throws IOException {
            while (true) {
                int J = zzaou.m16039J();
                switch (J) {
                    case 0:
                        break;
                    case 10:
                        this.name = zzaou.readString();
                        continue;
                    case 16:
                        this.aox = Boolean.valueOf(zzaou.m16045P());
                        continue;
                    case 24:
                        this.aoy = Boolean.valueOf(zzaou.m16045P());
                        continue;
                    default:
                        if (!zzapf.zzb(zzaou, J)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public zza zzbwk() {
            this.name = null;
            this.aox = null;
            this.aoy = null;
            this.bik = -1;
            return this;
        }

        protected int zzy() {
            int zzy = super.zzy();
            if (this.name != null) {
                zzy += zzaov.zzs(1, this.name);
            }
            if (this.aox != null) {
                zzy += zzaov.zzk(2, this.aox.booleanValue());
            }
            return this.aoy != null ? zzy + zzaov.zzk(3, this.aoy.booleanValue()) : zzy;
        }
    }

    public static final class zzb extends zzapc {
        public String ajz;
        public Integer aoA;
        public zzc[] aoB;
        public zza[] aoC;
        public com.google.android.gms.internal.zzun.zza[] aoD;
        public Long aoz;

        public zzb() {
            zzbwl();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzb)) {
                return false;
            }
            zzb zzb = (zzb) obj;
            if (this.aoz == null) {
                if (zzb.aoz != null) {
                    return false;
                }
            } else if (!this.aoz.equals(zzb.aoz)) {
                return false;
            }
            if (this.ajz == null) {
                if (zzb.ajz != null) {
                    return false;
                }
            } else if (!this.ajz.equals(zzb.ajz)) {
                return false;
            }
            if (this.aoA == null) {
                if (zzb.aoA != null) {
                    return false;
                }
            } else if (!this.aoA.equals(zzb.aoA)) {
                return false;
            }
            return !zzapa.equals(this.aoB, zzb.aoB) ? false : !zzapa.equals(this.aoC, zzb.aoC) ? false : zzapa.equals(this.aoD, zzb.aoD);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.ajz == null ? 0 : this.ajz.hashCode()) + (((this.aoz == null ? 0 : this.aoz.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31;
            if (this.aoA != null) {
                i = this.aoA.hashCode();
            }
            return ((((((hashCode + i) * 31) + zzapa.hashCode(this.aoB)) * 31) + zzapa.hashCode(this.aoC)) * 31) + zzapa.hashCode(this.aoD);
        }

        public void zza(zzaov zzaov) throws IOException {
            int i = 0;
            if (this.aoz != null) {
                zzaov.zzb(1, this.aoz.longValue());
            }
            if (this.ajz != null) {
                zzaov.zzr(2, this.ajz);
            }
            if (this.aoA != null) {
                zzaov.zzae(3, this.aoA.intValue());
            }
            if (this.aoB != null && this.aoB.length > 0) {
                for (zzapc zzapc : this.aoB) {
                    if (zzapc != null) {
                        zzaov.zza(4, zzapc);
                    }
                }
            }
            if (this.aoC != null && this.aoC.length > 0) {
                for (zzapc zzapc2 : this.aoC) {
                    if (zzapc2 != null) {
                        zzaov.zza(5, zzapc2);
                    }
                }
            }
            if (this.aoD != null && this.aoD.length > 0) {
                while (i < this.aoD.length) {
                    zzapc zzapc3 = this.aoD[i];
                    if (zzapc3 != null) {
                        zzaov.zza(6, zzapc3);
                    }
                    i++;
                }
            }
            super.zza(zzaov);
        }

        public /* synthetic */ zzapc zzb(zzaou zzaou) throws IOException {
            return zzbk(zzaou);
        }

        public zzb zzbk(zzaou zzaou) throws IOException {
            while (true) {
                int J = zzaou.m16039J();
                int zzc;
                Object obj;
                switch (J) {
                    case 0:
                        break;
                    case 8:
                        this.aoz = Long.valueOf(zzaou.m16042M());
                        continue;
                    case 18:
                        this.ajz = zzaou.readString();
                        continue;
                    case 24:
                        this.aoA = Integer.valueOf(zzaou.m16043N());
                        continue;
                    case 34:
                        zzc = zzapf.zzc(zzaou, 34);
                        J = this.aoB == null ? 0 : this.aoB.length;
                        obj = new zzc[(zzc + J)];
                        if (J != 0) {
                            System.arraycopy(this.aoB, 0, obj, 0, J);
                        }
                        while (J < obj.length - 1) {
                            obj[J] = new zzc();
                            zzaou.zza(obj[J]);
                            zzaou.m16039J();
                            J++;
                        }
                        obj[J] = new zzc();
                        zzaou.zza(obj[J]);
                        this.aoB = obj;
                        continue;
                    case 42:
                        zzc = zzapf.zzc(zzaou, 42);
                        J = this.aoC == null ? 0 : this.aoC.length;
                        obj = new zza[(zzc + J)];
                        if (J != 0) {
                            System.arraycopy(this.aoC, 0, obj, 0, J);
                        }
                        while (J < obj.length - 1) {
                            obj[J] = new zza();
                            zzaou.zza(obj[J]);
                            zzaou.m16039J();
                            J++;
                        }
                        obj[J] = new zza();
                        zzaou.zza(obj[J]);
                        this.aoC = obj;
                        continue;
                    case 50:
                        zzc = zzapf.zzc(zzaou, 50);
                        J = this.aoD == null ? 0 : this.aoD.length;
                        obj = new com.google.android.gms.internal.zzun.zza[(zzc + J)];
                        if (J != 0) {
                            System.arraycopy(this.aoD, 0, obj, 0, J);
                        }
                        while (J < obj.length - 1) {
                            obj[J] = new com.google.android.gms.internal.zzun.zza();
                            zzaou.zza(obj[J]);
                            zzaou.m16039J();
                            J++;
                        }
                        obj[J] = new com.google.android.gms.internal.zzun.zza();
                        zzaou.zza(obj[J]);
                        this.aoD = obj;
                        continue;
                    default:
                        if (!zzapf.zzb(zzaou, J)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public zzb zzbwl() {
            this.aoz = null;
            this.ajz = null;
            this.aoA = null;
            this.aoB = zzc.zzbwm();
            this.aoC = zza.zzbwj();
            this.aoD = com.google.android.gms.internal.zzun.zza.zzbvz();
            this.bik = -1;
            return this;
        }

        protected int zzy() {
            int i;
            int i2 = 0;
            int zzy = super.zzy();
            if (this.aoz != null) {
                zzy += zzaov.zze(1, this.aoz.longValue());
            }
            if (this.ajz != null) {
                zzy += zzaov.zzs(2, this.ajz);
            }
            if (this.aoA != null) {
                zzy += zzaov.zzag(3, this.aoA.intValue());
            }
            if (this.aoB != null && this.aoB.length > 0) {
                i = zzy;
                for (zzapc zzapc : this.aoB) {
                    if (zzapc != null) {
                        i += zzaov.zzc(4, zzapc);
                    }
                }
                zzy = i;
            }
            if (this.aoC != null && this.aoC.length > 0) {
                i = zzy;
                for (zzapc zzapc2 : this.aoC) {
                    if (zzapc2 != null) {
                        i += zzaov.zzc(5, zzapc2);
                    }
                }
                zzy = i;
            }
            if (this.aoD != null && this.aoD.length > 0) {
                while (i2 < this.aoD.length) {
                    zzapc zzapc3 = this.aoD[i2];
                    if (zzapc3 != null) {
                        zzy += zzaov.zzc(6, zzapc3);
                    }
                    i2++;
                }
            }
            return zzy;
        }
    }

    public static final class zzc extends zzapc {
        private static volatile zzc[] aoE;
        public String value;
        public String zzcb;

        public zzc() {
            zzbwn();
        }

        public static zzc[] zzbwm() {
            if (aoE == null) {
                synchronized (zzapa.bij) {
                    if (aoE == null) {
                        aoE = new zzc[0];
                    }
                }
            }
            return aoE;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzc)) {
                return false;
            }
            zzc zzc = (zzc) obj;
            if (this.zzcb == null) {
                if (zzc.zzcb != null) {
                    return false;
                }
            } else if (!this.zzcb.equals(zzc.zzcb)) {
                return false;
            }
            return this.value == null ? zzc.value == null : this.value.equals(zzc.value);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.zzcb == null ? 0 : this.zzcb.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31;
            if (this.value != null) {
                i = this.value.hashCode();
            }
            return hashCode + i;
        }

        public void zza(zzaov zzaov) throws IOException {
            if (this.zzcb != null) {
                zzaov.zzr(1, this.zzcb);
            }
            if (this.value != null) {
                zzaov.zzr(2, this.value);
            }
            super.zza(zzaov);
        }

        public /* synthetic */ zzapc zzb(zzaou zzaou) throws IOException {
            return zzbl(zzaou);
        }

        public zzc zzbl(zzaou zzaou) throws IOException {
            while (true) {
                int J = zzaou.m16039J();
                switch (J) {
                    case 0:
                        break;
                    case 10:
                        this.zzcb = zzaou.readString();
                        continue;
                    case 18:
                        this.value = zzaou.readString();
                        continue;
                    default:
                        if (!zzapf.zzb(zzaou, J)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public zzc zzbwn() {
            this.zzcb = null;
            this.value = null;
            this.bik = -1;
            return this;
        }

        protected int zzy() {
            int zzy = super.zzy();
            if (this.zzcb != null) {
                zzy += zzaov.zzs(1, this.zzcb);
            }
            return this.value != null ? zzy + zzaov.zzs(2, this.value) : zzy;
        }
    }
}
