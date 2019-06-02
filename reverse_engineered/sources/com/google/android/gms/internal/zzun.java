package com.google.android.gms.internal;

import java.io.IOException;

public interface zzun {

    public static final class zza extends zzapc {
        private static volatile zza[] anV;
        public Integer anW;
        public zze[] anX;
        public zzb[] anY;

        public zza() {
            zzbwa();
        }

        public static zza[] zzbvz() {
            if (anV == null) {
                synchronized (zzapa.bij) {
                    if (anV == null) {
                        anV = new zza[0];
                    }
                }
            }
            return anV;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            if (this.anW == null) {
                if (zza.anW != null) {
                    return false;
                }
            } else if (!this.anW.equals(zza.anW)) {
                return false;
            }
            return !zzapa.equals(this.anX, zza.anX) ? false : zzapa.equals(this.anY, zza.anY);
        }

        public int hashCode() {
            return (((((this.anW == null ? 0 : this.anW.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31) + zzapa.hashCode(this.anX)) * 31) + zzapa.hashCode(this.anY);
        }

        public void zza(zzaov zzaov) throws IOException {
            int i = 0;
            if (this.anW != null) {
                zzaov.zzae(1, this.anW.intValue());
            }
            if (this.anX != null && this.anX.length > 0) {
                for (zzapc zzapc : this.anX) {
                    if (zzapc != null) {
                        zzaov.zza(2, zzapc);
                    }
                }
            }
            if (this.anY != null && this.anY.length > 0) {
                while (i < this.anY.length) {
                    zzapc zzapc2 = this.anY[i];
                    if (zzapc2 != null) {
                        zzaov.zza(3, zzapc2);
                    }
                    i++;
                }
            }
            super.zza(zzaov);
        }

        public /* synthetic */ zzapc zzb(zzaou zzaou) throws IOException {
            return zzbd(zzaou);
        }

        public zza zzbd(zzaou zzaou) throws IOException {
            while (true) {
                int J = zzaou.m16039J();
                int zzc;
                Object obj;
                switch (J) {
                    case 0:
                        break;
                    case 8:
                        this.anW = Integer.valueOf(zzaou.m16043N());
                        continue;
                    case 18:
                        zzc = zzapf.zzc(zzaou, 18);
                        J = this.anX == null ? 0 : this.anX.length;
                        obj = new zze[(zzc + J)];
                        if (J != 0) {
                            System.arraycopy(this.anX, 0, obj, 0, J);
                        }
                        while (J < obj.length - 1) {
                            obj[J] = new zze();
                            zzaou.zza(obj[J]);
                            zzaou.m16039J();
                            J++;
                        }
                        obj[J] = new zze();
                        zzaou.zza(obj[J]);
                        this.anX = obj;
                        continue;
                    case 26:
                        zzc = zzapf.zzc(zzaou, 26);
                        J = this.anY == null ? 0 : this.anY.length;
                        obj = new zzb[(zzc + J)];
                        if (J != 0) {
                            System.arraycopy(this.anY, 0, obj, 0, J);
                        }
                        while (J < obj.length - 1) {
                            obj[J] = new zzb();
                            zzaou.zza(obj[J]);
                            zzaou.m16039J();
                            J++;
                        }
                        obj[J] = new zzb();
                        zzaou.zza(obj[J]);
                        this.anY = obj;
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

        public zza zzbwa() {
            this.anW = null;
            this.anX = zze.zzbwg();
            this.anY = zzb.zzbwb();
            this.bik = -1;
            return this;
        }

        protected int zzy() {
            int i = 0;
            int zzy = super.zzy();
            if (this.anW != null) {
                zzy += zzaov.zzag(1, this.anW.intValue());
            }
            if (this.anX != null && this.anX.length > 0) {
                int i2 = zzy;
                for (zzapc zzapc : this.anX) {
                    if (zzapc != null) {
                        i2 += zzaov.zzc(2, zzapc);
                    }
                }
                zzy = i2;
            }
            if (this.anY != null && this.anY.length > 0) {
                while (i < this.anY.length) {
                    zzapc zzapc2 = this.anY[i];
                    if (zzapc2 != null) {
                        zzy += zzaov.zzc(3, zzapc2);
                    }
                    i++;
                }
            }
            return zzy;
        }
    }

    public static final class zzb extends zzapc {
        private static volatile zzb[] anZ;
        public Integer aoa;
        public String aob;
        public zzc[] aoc;
        public Boolean aod;
        public zzd aoe;

        public zzb() {
            zzbwc();
        }

        public static zzb[] zzbwb() {
            if (anZ == null) {
                synchronized (zzapa.bij) {
                    if (anZ == null) {
                        anZ = new zzb[0];
                    }
                }
            }
            return anZ;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzb)) {
                return false;
            }
            zzb zzb = (zzb) obj;
            if (this.aoa == null) {
                if (zzb.aoa != null) {
                    return false;
                }
            } else if (!this.aoa.equals(zzb.aoa)) {
                return false;
            }
            if (this.aob == null) {
                if (zzb.aob != null) {
                    return false;
                }
            } else if (!this.aob.equals(zzb.aob)) {
                return false;
            }
            if (!zzapa.equals(this.aoc, zzb.aoc)) {
                return false;
            }
            if (this.aod == null) {
                if (zzb.aod != null) {
                    return false;
                }
            } else if (!this.aod.equals(zzb.aod)) {
                return false;
            }
            return this.aoe == null ? zzb.aoe == null : this.aoe.equals(zzb.aoe);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.aod == null ? 0 : this.aod.hashCode()) + (((((this.aob == null ? 0 : this.aob.hashCode()) + (((this.aoa == null ? 0 : this.aoa.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31) + zzapa.hashCode(this.aoc)) * 31)) * 31;
            if (this.aoe != null) {
                i = this.aoe.hashCode();
            }
            return hashCode + i;
        }

        public void zza(zzaov zzaov) throws IOException {
            if (this.aoa != null) {
                zzaov.zzae(1, this.aoa.intValue());
            }
            if (this.aob != null) {
                zzaov.zzr(2, this.aob);
            }
            if (this.aoc != null && this.aoc.length > 0) {
                for (zzapc zzapc : this.aoc) {
                    if (zzapc != null) {
                        zzaov.zza(3, zzapc);
                    }
                }
            }
            if (this.aod != null) {
                zzaov.zzj(4, this.aod.booleanValue());
            }
            if (this.aoe != null) {
                zzaov.zza(5, this.aoe);
            }
            super.zza(zzaov);
        }

        public /* synthetic */ zzapc zzb(zzaou zzaou) throws IOException {
            return zzbe(zzaou);
        }

        public zzb zzbe(zzaou zzaou) throws IOException {
            while (true) {
                int J = zzaou.m16039J();
                switch (J) {
                    case 0:
                        break;
                    case 8:
                        this.aoa = Integer.valueOf(zzaou.m16043N());
                        continue;
                    case 18:
                        this.aob = zzaou.readString();
                        continue;
                    case 26:
                        int zzc = zzapf.zzc(zzaou, 26);
                        J = this.aoc == null ? 0 : this.aoc.length;
                        Object obj = new zzc[(zzc + J)];
                        if (J != 0) {
                            System.arraycopy(this.aoc, 0, obj, 0, J);
                        }
                        while (J < obj.length - 1) {
                            obj[J] = new zzc();
                            zzaou.zza(obj[J]);
                            zzaou.m16039J();
                            J++;
                        }
                        obj[J] = new zzc();
                        zzaou.zza(obj[J]);
                        this.aoc = obj;
                        continue;
                    case 32:
                        this.aod = Boolean.valueOf(zzaou.m16045P());
                        continue;
                    case 42:
                        if (this.aoe == null) {
                            this.aoe = new zzd();
                        }
                        zzaou.zza(this.aoe);
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

        public zzb zzbwc() {
            this.aoa = null;
            this.aob = null;
            this.aoc = zzc.zzbwd();
            this.aod = null;
            this.aoe = null;
            this.bik = -1;
            return this;
        }

        protected int zzy() {
            int zzy = super.zzy();
            if (this.aoa != null) {
                zzy += zzaov.zzag(1, this.aoa.intValue());
            }
            if (this.aob != null) {
                zzy += zzaov.zzs(2, this.aob);
            }
            if (this.aoc != null && this.aoc.length > 0) {
                int i = zzy;
                for (zzapc zzapc : this.aoc) {
                    if (zzapc != null) {
                        i += zzaov.zzc(3, zzapc);
                    }
                }
                zzy = i;
            }
            if (this.aod != null) {
                zzy += zzaov.zzk(4, this.aod.booleanValue());
            }
            return this.aoe != null ? zzy + zzaov.zzc(5, this.aoe) : zzy;
        }
    }

    public static final class zzc extends zzapc {
        private static volatile zzc[] aof;
        public zzf aog;
        public zzd aoh;
        public Boolean aoi;
        public String aoj;

        public zzc() {
            zzbwe();
        }

        public static zzc[] zzbwd() {
            if (aof == null) {
                synchronized (zzapa.bij) {
                    if (aof == null) {
                        aof = new zzc[0];
                    }
                }
            }
            return aof;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzc)) {
                return false;
            }
            zzc zzc = (zzc) obj;
            if (this.aog == null) {
                if (zzc.aog != null) {
                    return false;
                }
            } else if (!this.aog.equals(zzc.aog)) {
                return false;
            }
            if (this.aoh == null) {
                if (zzc.aoh != null) {
                    return false;
                }
            } else if (!this.aoh.equals(zzc.aoh)) {
                return false;
            }
            if (this.aoi == null) {
                if (zzc.aoi != null) {
                    return false;
                }
            } else if (!this.aoi.equals(zzc.aoi)) {
                return false;
            }
            return this.aoj == null ? zzc.aoj == null : this.aoj.equals(zzc.aoj);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.aoi == null ? 0 : this.aoi.hashCode()) + (((this.aoh == null ? 0 : this.aoh.hashCode()) + (((this.aog == null ? 0 : this.aog.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31)) * 31;
            if (this.aoj != null) {
                i = this.aoj.hashCode();
            }
            return hashCode + i;
        }

        public void zza(zzaov zzaov) throws IOException {
            if (this.aog != null) {
                zzaov.zza(1, this.aog);
            }
            if (this.aoh != null) {
                zzaov.zza(2, this.aoh);
            }
            if (this.aoi != null) {
                zzaov.zzj(3, this.aoi.booleanValue());
            }
            if (this.aoj != null) {
                zzaov.zzr(4, this.aoj);
            }
            super.zza(zzaov);
        }

        public /* synthetic */ zzapc zzb(zzaou zzaou) throws IOException {
            return zzbf(zzaou);
        }

        public zzc zzbf(zzaou zzaou) throws IOException {
            while (true) {
                int J = zzaou.m16039J();
                switch (J) {
                    case 0:
                        break;
                    case 10:
                        if (this.aog == null) {
                            this.aog = new zzf();
                        }
                        zzaou.zza(this.aog);
                        continue;
                    case 18:
                        if (this.aoh == null) {
                            this.aoh = new zzd();
                        }
                        zzaou.zza(this.aoh);
                        continue;
                    case 24:
                        this.aoi = Boolean.valueOf(zzaou.m16045P());
                        continue;
                    case 34:
                        this.aoj = zzaou.readString();
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

        public zzc zzbwe() {
            this.aog = null;
            this.aoh = null;
            this.aoi = null;
            this.aoj = null;
            this.bik = -1;
            return this;
        }

        protected int zzy() {
            int zzy = super.zzy();
            if (this.aog != null) {
                zzy += zzaov.zzc(1, this.aog);
            }
            if (this.aoh != null) {
                zzy += zzaov.zzc(2, this.aoh);
            }
            if (this.aoi != null) {
                zzy += zzaov.zzk(3, this.aoi.booleanValue());
            }
            return this.aoj != null ? zzy + zzaov.zzs(4, this.aoj) : zzy;
        }
    }

    public static final class zzd extends zzapc {
        public Integer aok;
        public Boolean aol;
        public String aom;
        public String aon;
        public String aoo;

        public zzd() {
            zzbwf();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzd)) {
                return false;
            }
            zzd zzd = (zzd) obj;
            if (this.aok == null) {
                if (zzd.aok != null) {
                    return false;
                }
            } else if (!this.aok.equals(zzd.aok)) {
                return false;
            }
            if (this.aol == null) {
                if (zzd.aol != null) {
                    return false;
                }
            } else if (!this.aol.equals(zzd.aol)) {
                return false;
            }
            if (this.aom == null) {
                if (zzd.aom != null) {
                    return false;
                }
            } else if (!this.aom.equals(zzd.aom)) {
                return false;
            }
            if (this.aon == null) {
                if (zzd.aon != null) {
                    return false;
                }
            } else if (!this.aon.equals(zzd.aon)) {
                return false;
            }
            return this.aoo == null ? zzd.aoo == null : this.aoo.equals(zzd.aoo);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.aon == null ? 0 : this.aon.hashCode()) + (((this.aom == null ? 0 : this.aom.hashCode()) + (((this.aol == null ? 0 : this.aol.hashCode()) + (((this.aok == null ? 0 : this.aok.intValue()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31)) * 31)) * 31;
            if (this.aoo != null) {
                i = this.aoo.hashCode();
            }
            return hashCode + i;
        }

        public void zza(zzaov zzaov) throws IOException {
            if (this.aok != null) {
                zzaov.zzae(1, this.aok.intValue());
            }
            if (this.aol != null) {
                zzaov.zzj(2, this.aol.booleanValue());
            }
            if (this.aom != null) {
                zzaov.zzr(3, this.aom);
            }
            if (this.aon != null) {
                zzaov.zzr(4, this.aon);
            }
            if (this.aoo != null) {
                zzaov.zzr(5, this.aoo);
            }
            super.zza(zzaov);
        }

        public /* synthetic */ zzapc zzb(zzaou zzaou) throws IOException {
            return zzbg(zzaou);
        }

        public zzd zzbg(zzaou zzaou) throws IOException {
            while (true) {
                int J = zzaou.m16039J();
                switch (J) {
                    case 0:
                        break;
                    case 8:
                        J = zzaou.m16043N();
                        switch (J) {
                            case 0:
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                                this.aok = Integer.valueOf(J);
                                break;
                            default:
                                continue;
                        }
                    case 16:
                        this.aol = Boolean.valueOf(zzaou.m16045P());
                        continue;
                    case 26:
                        this.aom = zzaou.readString();
                        continue;
                    case 34:
                        this.aon = zzaou.readString();
                        continue;
                    case 42:
                        this.aoo = zzaou.readString();
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

        public zzd zzbwf() {
            this.aol = null;
            this.aom = null;
            this.aon = null;
            this.aoo = null;
            this.bik = -1;
            return this;
        }

        protected int zzy() {
            int zzy = super.zzy();
            if (this.aok != null) {
                zzy += zzaov.zzag(1, this.aok.intValue());
            }
            if (this.aol != null) {
                zzy += zzaov.zzk(2, this.aol.booleanValue());
            }
            if (this.aom != null) {
                zzy += zzaov.zzs(3, this.aom);
            }
            if (this.aon != null) {
                zzy += zzaov.zzs(4, this.aon);
            }
            return this.aoo != null ? zzy + zzaov.zzs(5, this.aoo) : zzy;
        }
    }

    public static final class zze extends zzapc {
        private static volatile zze[] aop;
        public Integer aoa;
        public String aoq;
        public zzc aor;

        public zze() {
            zzbwh();
        }

        public static zze[] zzbwg() {
            if (aop == null) {
                synchronized (zzapa.bij) {
                    if (aop == null) {
                        aop = new zze[0];
                    }
                }
            }
            return aop;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zze)) {
                return false;
            }
            zze zze = (zze) obj;
            if (this.aoa == null) {
                if (zze.aoa != null) {
                    return false;
                }
            } else if (!this.aoa.equals(zze.aoa)) {
                return false;
            }
            if (this.aoq == null) {
                if (zze.aoq != null) {
                    return false;
                }
            } else if (!this.aoq.equals(zze.aoq)) {
                return false;
            }
            return this.aor == null ? zze.aor == null : this.aor.equals(zze.aor);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.aoq == null ? 0 : this.aoq.hashCode()) + (((this.aoa == null ? 0 : this.aoa.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31;
            if (this.aor != null) {
                i = this.aor.hashCode();
            }
            return hashCode + i;
        }

        public void zza(zzaov zzaov) throws IOException {
            if (this.aoa != null) {
                zzaov.zzae(1, this.aoa.intValue());
            }
            if (this.aoq != null) {
                zzaov.zzr(2, this.aoq);
            }
            if (this.aor != null) {
                zzaov.zza(3, this.aor);
            }
            super.zza(zzaov);
        }

        public /* synthetic */ zzapc zzb(zzaou zzaou) throws IOException {
            return zzbh(zzaou);
        }

        public zze zzbh(zzaou zzaou) throws IOException {
            while (true) {
                int J = zzaou.m16039J();
                switch (J) {
                    case 0:
                        break;
                    case 8:
                        this.aoa = Integer.valueOf(zzaou.m16043N());
                        continue;
                    case 18:
                        this.aoq = zzaou.readString();
                        continue;
                    case 26:
                        if (this.aor == null) {
                            this.aor = new zzc();
                        }
                        zzaou.zza(this.aor);
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

        public zze zzbwh() {
            this.aoa = null;
            this.aoq = null;
            this.aor = null;
            this.bik = -1;
            return this;
        }

        protected int zzy() {
            int zzy = super.zzy();
            if (this.aoa != null) {
                zzy += zzaov.zzag(1, this.aoa.intValue());
            }
            if (this.aoq != null) {
                zzy += zzaov.zzs(2, this.aoq);
            }
            return this.aor != null ? zzy + zzaov.zzc(3, this.aor) : zzy;
        }
    }

    public static final class zzf extends zzapc {
        public Integer aos;
        public String aot;
        public Boolean aou;
        public String[] aov;

        public zzf() {
            zzbwi();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzf)) {
                return false;
            }
            zzf zzf = (zzf) obj;
            if (this.aos == null) {
                if (zzf.aos != null) {
                    return false;
                }
            } else if (!this.aos.equals(zzf.aos)) {
                return false;
            }
            if (this.aot == null) {
                if (zzf.aot != null) {
                    return false;
                }
            } else if (!this.aot.equals(zzf.aot)) {
                return false;
            }
            if (this.aou == null) {
                if (zzf.aou != null) {
                    return false;
                }
            } else if (!this.aou.equals(zzf.aou)) {
                return false;
            }
            return zzapa.equals(this.aov, zzf.aov);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.aot == null ? 0 : this.aot.hashCode()) + (((this.aos == null ? 0 : this.aos.intValue()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31;
            if (this.aou != null) {
                i = this.aou.hashCode();
            }
            return ((hashCode + i) * 31) + zzapa.hashCode(this.aov);
        }

        public void zza(zzaov zzaov) throws IOException {
            if (this.aos != null) {
                zzaov.zzae(1, this.aos.intValue());
            }
            if (this.aot != null) {
                zzaov.zzr(2, this.aot);
            }
            if (this.aou != null) {
                zzaov.zzj(3, this.aou.booleanValue());
            }
            if (this.aov != null && this.aov.length > 0) {
                for (String str : this.aov) {
                    if (str != null) {
                        zzaov.zzr(4, str);
                    }
                }
            }
            super.zza(zzaov);
        }

        public /* synthetic */ zzapc zzb(zzaou zzaou) throws IOException {
            return zzbi(zzaou);
        }

        public zzf zzbi(zzaou zzaou) throws IOException {
            while (true) {
                int J = zzaou.m16039J();
                switch (J) {
                    case 0:
                        break;
                    case 8:
                        J = zzaou.m16043N();
                        switch (J) {
                            case 0:
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                                this.aos = Integer.valueOf(J);
                                break;
                            default:
                                continue;
                        }
                    case 18:
                        this.aot = zzaou.readString();
                        continue;
                    case 24:
                        this.aou = Boolean.valueOf(zzaou.m16045P());
                        continue;
                    case 34:
                        int zzc = zzapf.zzc(zzaou, 34);
                        J = this.aov == null ? 0 : this.aov.length;
                        Object obj = new String[(zzc + J)];
                        if (J != 0) {
                            System.arraycopy(this.aov, 0, obj, 0, J);
                        }
                        while (J < obj.length - 1) {
                            obj[J] = zzaou.readString();
                            zzaou.m16039J();
                            J++;
                        }
                        obj[J] = zzaou.readString();
                        this.aov = obj;
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

        public zzf zzbwi() {
            this.aot = null;
            this.aou = null;
            this.aov = zzapf.bir;
            this.bik = -1;
            return this;
        }

        protected int zzy() {
            int i = 0;
            int zzy = super.zzy();
            if (this.aos != null) {
                zzy += zzaov.zzag(1, this.aos.intValue());
            }
            if (this.aot != null) {
                zzy += zzaov.zzs(2, this.aot);
            }
            if (this.aou != null) {
                zzy += zzaov.zzk(3, this.aou.booleanValue());
            }
            if (this.aov == null || this.aov.length <= 0) {
                return zzy;
            }
            int i2 = 0;
            int i3 = 0;
            while (i < this.aov.length) {
                String str = this.aov[i];
                if (str != null) {
                    i3++;
                    i2 += zzaov.zztg(str);
                }
                i++;
            }
            return (zzy + i2) + (i3 * 1);
        }
    }
}
