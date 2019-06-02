package com.google.android.gms.internal;

import java.io.IOException;

public interface zzai {

    public static final class zza extends zzaow<zza> {
        private static volatile zza[] zzws;
        public int type;
        public String zzwt;
        public zza[] zzwu;
        public zza[] zzwv;
        public zza[] zzww;
        public String zzwx;
        public String zzwy;
        public long zzwz;
        public boolean zzxa;
        public zza[] zzxb;
        public int[] zzxc;
        public boolean zzxd;

        public zza() {
            zzar();
        }

        public static zza[] zzaq() {
            if (zzws == null) {
                synchronized (zzapa.bij) {
                    if (zzws == null) {
                        zzws = new zza[0];
                    }
                }
            }
            return zzws;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            if (this.type != zza.type) {
                return false;
            }
            if (this.zzwt == null) {
                if (zza.zzwt != null) {
                    return false;
                }
            } else if (!this.zzwt.equals(zza.zzwt)) {
                return false;
            }
            if (!zzapa.equals(this.zzwu, zza.zzwu) || !zzapa.equals(this.zzwv, zza.zzwv) || !zzapa.equals(this.zzww, zza.zzww)) {
                return false;
            }
            if (this.zzwx == null) {
                if (zza.zzwx != null) {
                    return false;
                }
            } else if (!this.zzwx.equals(zza.zzwx)) {
                return false;
            }
            if (this.zzwy == null) {
                if (zza.zzwy != null) {
                    return false;
                }
            } else if (!this.zzwy.equals(zza.zzwy)) {
                return false;
            }
            return (this.zzwz == zza.zzwz && this.zzxa == zza.zzxa && zzapa.equals(this.zzxb, zza.zzxb) && zzapa.equals(this.zzxc, zza.zzxc) && this.zzxd == zza.zzxd) ? (this.bib == null || this.bib.isEmpty()) ? zza.bib == null || zza.bib.isEmpty() : this.bib.equals(zza.bib) : false;
        }

        public int hashCode() {
            int i = 1231;
            int i2 = 0;
            int hashCode = ((((((this.zzxa ? 1231 : 1237) + (((((this.zzwy == null ? 0 : this.zzwy.hashCode()) + (((this.zzwx == null ? 0 : this.zzwx.hashCode()) + (((((((((this.zzwt == null ? 0 : this.zzwt.hashCode()) + ((((getClass().getName().hashCode() + 527) * 31) + this.type) * 31)) * 31) + zzapa.hashCode(this.zzwu)) * 31) + zzapa.hashCode(this.zzwv)) * 31) + zzapa.hashCode(this.zzww)) * 31)) * 31)) * 31) + ((int) (this.zzwz ^ (this.zzwz >>> 32)))) * 31)) * 31) + zzapa.hashCode(this.zzxb)) * 31) + zzapa.hashCode(this.zzxc)) * 31;
            if (!this.zzxd) {
                i = 1237;
            }
            hashCode = (hashCode + i) * 31;
            if (!(this.bib == null || this.bib.isEmpty())) {
                i2 = this.bib.hashCode();
            }
            return hashCode + i2;
        }

        public void zza(zzaov zzaov) throws IOException {
            int i = 0;
            zzaov.zzae(1, this.type);
            if (!this.zzwt.equals("")) {
                zzaov.zzr(2, this.zzwt);
            }
            if (this.zzwu != null && this.zzwu.length > 0) {
                for (zzapc zzapc : this.zzwu) {
                    if (zzapc != null) {
                        zzaov.zza(3, zzapc);
                    }
                }
            }
            if (this.zzwv != null && this.zzwv.length > 0) {
                for (zzapc zzapc2 : this.zzwv) {
                    if (zzapc2 != null) {
                        zzaov.zza(4, zzapc2);
                    }
                }
            }
            if (this.zzww != null && this.zzww.length > 0) {
                for (zzapc zzapc22 : this.zzww) {
                    if (zzapc22 != null) {
                        zzaov.zza(5, zzapc22);
                    }
                }
            }
            if (!this.zzwx.equals("")) {
                zzaov.zzr(6, this.zzwx);
            }
            if (!this.zzwy.equals("")) {
                zzaov.zzr(7, this.zzwy);
            }
            if (this.zzwz != 0) {
                zzaov.zzb(8, this.zzwz);
            }
            if (this.zzxd) {
                zzaov.zzj(9, this.zzxd);
            }
            if (this.zzxc != null && this.zzxc.length > 0) {
                for (int zzae : this.zzxc) {
                    zzaov.zzae(10, zzae);
                }
            }
            if (this.zzxb != null && this.zzxb.length > 0) {
                while (i < this.zzxb.length) {
                    zzapc zzapc3 = this.zzxb[i];
                    if (zzapc3 != null) {
                        zzaov.zza(11, zzapc3);
                    }
                    i++;
                }
            }
            if (this.zzxa) {
                zzaov.zzj(12, this.zzxa);
            }
            super.zza(zzaov);
        }

        public zza zzar() {
            this.type = 1;
            this.zzwt = "";
            this.zzwu = zzaq();
            this.zzwv = zzaq();
            this.zzww = zzaq();
            this.zzwx = "";
            this.zzwy = "";
            this.zzwz = 0;
            this.zzxa = false;
            this.zzxb = zzaq();
            this.zzxc = zzapf.bim;
            this.zzxd = false;
            this.bib = null;
            this.bik = -1;
            return this;
        }

        public /* synthetic */ zzapc zzb(zzaou zzaou) throws IOException {
            return zzt(zzaou);
        }

        public zza zzt(zzaou zzaou) throws IOException {
            while (true) {
                int J = zzaou.m16039J();
                int zzc;
                Object obj;
                int i;
                switch (J) {
                    case 0:
                        break;
                    case 8:
                        J = zzaou.m16043N();
                        switch (J) {
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            case 7:
                            case 8:
                                this.type = J;
                                break;
                            default:
                                continue;
                        }
                    case 18:
                        this.zzwt = zzaou.readString();
                        continue;
                    case 26:
                        zzc = zzapf.zzc(zzaou, 26);
                        J = this.zzwu == null ? 0 : this.zzwu.length;
                        obj = new zza[(zzc + J)];
                        if (J != 0) {
                            System.arraycopy(this.zzwu, 0, obj, 0, J);
                        }
                        while (J < obj.length - 1) {
                            obj[J] = new zza();
                            zzaou.zza(obj[J]);
                            zzaou.m16039J();
                            J++;
                        }
                        obj[J] = new zza();
                        zzaou.zza(obj[J]);
                        this.zzwu = obj;
                        continue;
                    case 34:
                        zzc = zzapf.zzc(zzaou, 34);
                        J = this.zzwv == null ? 0 : this.zzwv.length;
                        obj = new zza[(zzc + J)];
                        if (J != 0) {
                            System.arraycopy(this.zzwv, 0, obj, 0, J);
                        }
                        while (J < obj.length - 1) {
                            obj[J] = new zza();
                            zzaou.zza(obj[J]);
                            zzaou.m16039J();
                            J++;
                        }
                        obj[J] = new zza();
                        zzaou.zza(obj[J]);
                        this.zzwv = obj;
                        continue;
                    case 42:
                        zzc = zzapf.zzc(zzaou, 42);
                        J = this.zzww == null ? 0 : this.zzww.length;
                        obj = new zza[(zzc + J)];
                        if (J != 0) {
                            System.arraycopy(this.zzww, 0, obj, 0, J);
                        }
                        while (J < obj.length - 1) {
                            obj[J] = new zza();
                            zzaou.zza(obj[J]);
                            zzaou.m16039J();
                            J++;
                        }
                        obj[J] = new zza();
                        zzaou.zza(obj[J]);
                        this.zzww = obj;
                        continue;
                    case 50:
                        this.zzwx = zzaou.readString();
                        continue;
                    case 58:
                        this.zzwy = zzaou.readString();
                        continue;
                    case 64:
                        this.zzwz = zzaou.m16042M();
                        continue;
                    case 72:
                        this.zzxd = zzaou.m16045P();
                        continue;
                    case 80:
                        int zzc2 = zzapf.zzc(zzaou, 80);
                        Object obj2 = new int[zzc2];
                        i = 0;
                        zzc = 0;
                        while (i < zzc2) {
                            if (i != 0) {
                                zzaou.m16039J();
                            }
                            int N = zzaou.m16043N();
                            switch (N) {
                                case 1:
                                case 2:
                                case 3:
                                case 4:
                                case 5:
                                case 6:
                                case 7:
                                case 8:
                                case 9:
                                case 10:
                                case 11:
                                case 12:
                                case 13:
                                case 14:
                                case 15:
                                case 16:
                                case 17:
                                    J = zzc + 1;
                                    obj2[zzc] = N;
                                    break;
                                default:
                                    J = zzc;
                                    break;
                            }
                            i++;
                            zzc = J;
                        }
                        if (zzc != 0) {
                            J = this.zzxc == null ? 0 : this.zzxc.length;
                            if (J != 0 || zzc != zzc2) {
                                Object obj3 = new int[(J + zzc)];
                                if (J != 0) {
                                    System.arraycopy(this.zzxc, 0, obj3, 0, J);
                                }
                                System.arraycopy(obj2, 0, obj3, J, zzc);
                                this.zzxc = obj3;
                                break;
                            }
                            this.zzxc = obj2;
                            break;
                        }
                        continue;
                    case 82:
                        i = zzaou.zzaei(zzaou.m16048S());
                        zzc = zzaou.getPosition();
                        J = 0;
                        while (zzaou.m16052X() > 0) {
                            switch (zzaou.m16043N()) {
                                case 1:
                                case 2:
                                case 3:
                                case 4:
                                case 5:
                                case 6:
                                case 7:
                                case 8:
                                case 9:
                                case 10:
                                case 11:
                                case 12:
                                case 13:
                                case 14:
                                case 15:
                                case 16:
                                case 17:
                                    J++;
                                    break;
                                default:
                                    break;
                            }
                        }
                        if (J != 0) {
                            zzaou.zzaek(zzc);
                            zzc = this.zzxc == null ? 0 : this.zzxc.length;
                            Object obj4 = new int[(J + zzc)];
                            if (zzc != 0) {
                                System.arraycopy(this.zzxc, 0, obj4, 0, zzc);
                            }
                            while (zzaou.m16052X() > 0) {
                                int N2 = zzaou.m16043N();
                                switch (N2) {
                                    case 1:
                                    case 2:
                                    case 3:
                                    case 4:
                                    case 5:
                                    case 6:
                                    case 7:
                                    case 8:
                                    case 9:
                                    case 10:
                                    case 11:
                                    case 12:
                                    case 13:
                                    case 14:
                                    case 15:
                                    case 16:
                                    case 17:
                                        J = zzc + 1;
                                        obj4[zzc] = N2;
                                        zzc = J;
                                        break;
                                    default:
                                        break;
                                }
                            }
                            this.zzxc = obj4;
                        }
                        zzaou.zzaej(i);
                        continue;
                    case 90:
                        zzc = zzapf.zzc(zzaou, 90);
                        J = this.zzxb == null ? 0 : this.zzxb.length;
                        obj = new zza[(zzc + J)];
                        if (J != 0) {
                            System.arraycopy(this.zzxb, 0, obj, 0, J);
                        }
                        while (J < obj.length - 1) {
                            obj[J] = new zza();
                            zzaou.zza(obj[J]);
                            zzaou.m16039J();
                            J++;
                        }
                        obj[J] = new zza();
                        zzaou.zza(obj[J]);
                        this.zzxb = obj;
                        continue;
                    case 96:
                        this.zzxa = zzaou.m16045P();
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

        protected int zzy() {
            int i;
            int i2 = 0;
            int zzy = super.zzy() + zzaov.zzag(1, this.type);
            if (!this.zzwt.equals("")) {
                zzy += zzaov.zzs(2, this.zzwt);
            }
            if (this.zzwu != null && this.zzwu.length > 0) {
                i = zzy;
                for (zzapc zzapc : this.zzwu) {
                    if (zzapc != null) {
                        i += zzaov.zzc(3, zzapc);
                    }
                }
                zzy = i;
            }
            if (this.zzwv != null && this.zzwv.length > 0) {
                i = zzy;
                for (zzapc zzapc2 : this.zzwv) {
                    if (zzapc2 != null) {
                        i += zzaov.zzc(4, zzapc2);
                    }
                }
                zzy = i;
            }
            if (this.zzww != null && this.zzww.length > 0) {
                i = zzy;
                for (zzapc zzapc22 : this.zzww) {
                    if (zzapc22 != null) {
                        i += zzaov.zzc(5, zzapc22);
                    }
                }
                zzy = i;
            }
            if (!this.zzwx.equals("")) {
                zzy += zzaov.zzs(6, this.zzwx);
            }
            if (!this.zzwy.equals("")) {
                zzy += zzaov.zzs(7, this.zzwy);
            }
            if (this.zzwz != 0) {
                zzy += zzaov.zze(8, this.zzwz);
            }
            if (this.zzxd) {
                zzy += zzaov.zzk(9, this.zzxd);
            }
            if (this.zzxc != null && this.zzxc.length > 0) {
                int i3 = 0;
                for (int zzaeo : this.zzxc) {
                    i3 += zzaov.zzaeo(zzaeo);
                }
                zzy = (zzy + i3) + (this.zzxc.length * 1);
            }
            if (this.zzxb != null && this.zzxb.length > 0) {
                while (i2 < this.zzxb.length) {
                    zzapc zzapc3 = this.zzxb[i2];
                    if (zzapc3 != null) {
                        zzy += zzaov.zzc(11, zzapc3);
                    }
                    i2++;
                }
            }
            return this.zzxa ? zzy + zzaov.zzk(12, this.zzxa) : zzy;
        }
    }
}
