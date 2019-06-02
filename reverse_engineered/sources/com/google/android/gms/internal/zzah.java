package com.google.android.gms.internal;

import android.support.v4.media.TransportMediator;
import ch.qos.logback.core.net.SyslogConstants;
import com.alibaba.fastjson.asm.Opcodes;
import com.avos.avoscloud.AVException;
import java.io.IOException;

public interface zzah {

    public static final class zza extends zzaow<zza> {
        public int level;
        public int zzum;
        public int zzun;

        public zza() {
            zzab();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            return (this.level == zza.level && this.zzum == zza.zzum && this.zzun == zza.zzun) ? (this.bib == null || this.bib.isEmpty()) ? zza.bib == null || zza.bib.isEmpty() : this.bib.equals(zza.bib) : false;
        }

        public int hashCode() {
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + this.level) * 31) + this.zzum) * 31) + this.zzun) * 31;
            int hashCode2 = (this.bib == null || this.bib.isEmpty()) ? 0 : this.bib.hashCode();
            return hashCode2 + hashCode;
        }

        public void zza(zzaov zzaov) throws IOException {
            if (this.level != 1) {
                zzaov.zzae(1, this.level);
            }
            if (this.zzum != 0) {
                zzaov.zzae(2, this.zzum);
            }
            if (this.zzun != 0) {
                zzaov.zzae(3, this.zzun);
            }
            super.zza(zzaov);
        }

        public zza zzab() {
            this.level = 1;
            this.zzum = 0;
            this.zzun = 0;
            this.bib = null;
            this.bik = -1;
            return this;
        }

        public /* synthetic */ zzapc zzb(zzaou zzaou) throws IOException {
            return zzj(zzaou);
        }

        public zza zzj(zzaou zzaou) throws IOException {
            while (true) {
                int J = zzaou.m16039J();
                switch (J) {
                    case 0:
                        break;
                    case 8:
                        J = zzaou.m16043N();
                        switch (J) {
                            case 1:
                            case 2:
                            case 3:
                                this.level = J;
                                break;
                            default:
                                continue;
                        }
                    case 16:
                        this.zzum = zzaou.m16043N();
                        continue;
                    case 24:
                        this.zzun = zzaou.m16043N();
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
            int zzy = super.zzy();
            if (this.level != 1) {
                zzy += zzaov.zzag(1, this.level);
            }
            if (this.zzum != 0) {
                zzy += zzaov.zzag(2, this.zzum);
            }
            return this.zzun != 0 ? zzy + zzaov.zzag(3, this.zzun) : zzy;
        }
    }

    public static final class zzb extends zzaow<zzb> {
        private static volatile zzb[] zzuo;
        public int name;
        public int[] zzup;
        public int zzuq;
        public boolean zzur;
        public boolean zzus;

        public zzb() {
            zzad();
        }

        public static zzb[] zzac() {
            if (zzuo == null) {
                synchronized (zzapa.bij) {
                    if (zzuo == null) {
                        zzuo = new zzb[0];
                    }
                }
            }
            return zzuo;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzb)) {
                return false;
            }
            zzb zzb = (zzb) obj;
            return (zzapa.equals(this.zzup, zzb.zzup) && this.zzuq == zzb.zzuq && this.name == zzb.name && this.zzur == zzb.zzur && this.zzus == zzb.zzus) ? (this.bib == null || this.bib.isEmpty()) ? zzb.bib == null || zzb.bib.isEmpty() : this.bib.equals(zzb.bib) : false;
        }

        public int hashCode() {
            int i = 1231;
            int hashCode = ((this.zzur ? 1231 : 1237) + ((((((((getClass().getName().hashCode() + 527) * 31) + zzapa.hashCode(this.zzup)) * 31) + this.zzuq) * 31) + this.name) * 31)) * 31;
            if (!this.zzus) {
                i = 1237;
            }
            i = (hashCode + i) * 31;
            hashCode = (this.bib == null || this.bib.isEmpty()) ? 0 : this.bib.hashCode();
            return hashCode + i;
        }

        public void zza(zzaov zzaov) throws IOException {
            if (this.zzus) {
                zzaov.zzj(1, this.zzus);
            }
            zzaov.zzae(2, this.zzuq);
            if (this.zzup != null && this.zzup.length > 0) {
                for (int zzae : this.zzup) {
                    zzaov.zzae(3, zzae);
                }
            }
            if (this.name != 0) {
                zzaov.zzae(4, this.name);
            }
            if (this.zzur) {
                zzaov.zzj(6, this.zzur);
            }
            super.zza(zzaov);
        }

        public zzb zzad() {
            this.zzup = zzapf.bim;
            this.zzuq = 0;
            this.name = 0;
            this.zzur = false;
            this.zzus = false;
            this.bib = null;
            this.bik = -1;
            return this;
        }

        public /* synthetic */ zzapc zzb(zzaou zzaou) throws IOException {
            return zzk(zzaou);
        }

        public zzb zzk(zzaou zzaou) throws IOException {
            while (true) {
                int J = zzaou.m16039J();
                int zzc;
                switch (J) {
                    case 0:
                        break;
                    case 8:
                        this.zzus = zzaou.m16045P();
                        continue;
                    case 16:
                        this.zzuq = zzaou.m16043N();
                        continue;
                    case 24:
                        zzc = zzapf.zzc(zzaou, 24);
                        J = this.zzup == null ? 0 : this.zzup.length;
                        Object obj = new int[(zzc + J)];
                        if (J != 0) {
                            System.arraycopy(this.zzup, 0, obj, 0, J);
                        }
                        while (J < obj.length - 1) {
                            obj[J] = zzaou.m16043N();
                            zzaou.m16039J();
                            J++;
                        }
                        obj[J] = zzaou.m16043N();
                        this.zzup = obj;
                        continue;
                    case 26:
                        int zzaei = zzaou.zzaei(zzaou.m16048S());
                        zzc = zzaou.getPosition();
                        J = 0;
                        while (zzaou.m16052X() > 0) {
                            zzaou.m16043N();
                            J++;
                        }
                        zzaou.zzaek(zzc);
                        zzc = this.zzup == null ? 0 : this.zzup.length;
                        Object obj2 = new int[(J + zzc)];
                        if (zzc != 0) {
                            System.arraycopy(this.zzup, 0, obj2, 0, zzc);
                        }
                        while (zzc < obj2.length) {
                            obj2[zzc] = zzaou.m16043N();
                            zzc++;
                        }
                        this.zzup = obj2;
                        zzaou.zzaej(zzaei);
                        continue;
                    case 32:
                        this.name = zzaou.m16043N();
                        continue;
                    case 48:
                        this.zzur = zzaou.m16045P();
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
            int i = 0;
            int zzy = super.zzy();
            if (this.zzus) {
                zzy += zzaov.zzk(1, this.zzus);
            }
            int zzag = zzaov.zzag(2, this.zzuq) + zzy;
            if (this.zzup == null || this.zzup.length <= 0) {
                zzy = zzag;
            } else {
                for (int zzaeo : this.zzup) {
                    i += zzaov.zzaeo(zzaeo);
                }
                zzy = (zzag + i) + (this.zzup.length * 1);
            }
            if (this.name != 0) {
                zzy += zzaov.zzag(4, this.name);
            }
            return this.zzur ? zzy + zzaov.zzk(6, this.zzur) : zzy;
        }
    }

    public static final class zzc extends zzaow<zzc> {
        private static volatile zzc[] zzut;
        public String zzcb;
        public long zzuu;
        public long zzuv;
        public boolean zzuw;
        public long zzux;

        public zzc() {
            zzaf();
        }

        public static zzc[] zzae() {
            if (zzut == null) {
                synchronized (zzapa.bij) {
                    if (zzut == null) {
                        zzut = new zzc[0];
                    }
                }
            }
            return zzut;
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
            return (this.zzuu == zzc.zzuu && this.zzuv == zzc.zzuv && this.zzuw == zzc.zzuw && this.zzux == zzc.zzux) ? (this.bib == null || this.bib.isEmpty()) ? zzc.bib == null || zzc.bib.isEmpty() : this.bib.equals(zzc.bib) : false;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((((this.zzuw ? 1231 : 1237) + (((((((this.zzcb == null ? 0 : this.zzcb.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31) + ((int) (this.zzuu ^ (this.zzuu >>> 32)))) * 31) + ((int) (this.zzuv ^ (this.zzuv >>> 32)))) * 31)) * 31) + ((int) (this.zzux ^ (this.zzux >>> 32)))) * 31;
            if (!(this.bib == null || this.bib.isEmpty())) {
                i = this.bib.hashCode();
            }
            return hashCode + i;
        }

        public void zza(zzaov zzaov) throws IOException {
            if (!this.zzcb.equals("")) {
                zzaov.zzr(1, this.zzcb);
            }
            if (this.zzuu != 0) {
                zzaov.zzb(2, this.zzuu);
            }
            if (this.zzuv != 2147483647L) {
                zzaov.zzb(3, this.zzuv);
            }
            if (this.zzuw) {
                zzaov.zzj(4, this.zzuw);
            }
            if (this.zzux != 0) {
                zzaov.zzb(5, this.zzux);
            }
            super.zza(zzaov);
        }

        public zzc zzaf() {
            this.zzcb = "";
            this.zzuu = 0;
            this.zzuv = 2147483647L;
            this.zzuw = false;
            this.zzux = 0;
            this.bib = null;
            this.bik = -1;
            return this;
        }

        public /* synthetic */ zzapc zzb(zzaou zzaou) throws IOException {
            return zzl(zzaou);
        }

        public zzc zzl(zzaou zzaou) throws IOException {
            while (true) {
                int J = zzaou.m16039J();
                switch (J) {
                    case 0:
                        break;
                    case 10:
                        this.zzcb = zzaou.readString();
                        continue;
                    case 16:
                        this.zzuu = zzaou.m16042M();
                        continue;
                    case 24:
                        this.zzuv = zzaou.m16042M();
                        continue;
                    case 32:
                        this.zzuw = zzaou.m16045P();
                        continue;
                    case 40:
                        this.zzux = zzaou.m16042M();
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
            int zzy = super.zzy();
            if (!this.zzcb.equals("")) {
                zzy += zzaov.zzs(1, this.zzcb);
            }
            if (this.zzuu != 0) {
                zzy += zzaov.zze(2, this.zzuu);
            }
            if (this.zzuv != 2147483647L) {
                zzy += zzaov.zze(3, this.zzuv);
            }
            if (this.zzuw) {
                zzy += zzaov.zzk(4, this.zzuw);
            }
            return this.zzux != 0 ? zzy + zzaov.zze(5, this.zzux) : zzy;
        }
    }

    public static final class zzd extends zzaow<zzd> {
        public com.google.android.gms.internal.zzai.zza[] zzuy;
        public com.google.android.gms.internal.zzai.zza[] zzuz;
        public zzc[] zzva;

        public zzd() {
            zzag();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzd)) {
                return false;
            }
            zzd zzd = (zzd) obj;
            return (zzapa.equals(this.zzuy, zzd.zzuy) && zzapa.equals(this.zzuz, zzd.zzuz) && zzapa.equals(this.zzva, zzd.zzva)) ? (this.bib == null || this.bib.isEmpty()) ? zzd.bib == null || zzd.bib.isEmpty() : this.bib.equals(zzd.bib) : false;
        }

        public int hashCode() {
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + zzapa.hashCode(this.zzuy)) * 31) + zzapa.hashCode(this.zzuz)) * 31) + zzapa.hashCode(this.zzva)) * 31;
            int hashCode2 = (this.bib == null || this.bib.isEmpty()) ? 0 : this.bib.hashCode();
            return hashCode2 + hashCode;
        }

        public void zza(zzaov zzaov) throws IOException {
            int i = 0;
            if (this.zzuy != null && this.zzuy.length > 0) {
                for (zzapc zzapc : this.zzuy) {
                    if (zzapc != null) {
                        zzaov.zza(1, zzapc);
                    }
                }
            }
            if (this.zzuz != null && this.zzuz.length > 0) {
                for (zzapc zzapc2 : this.zzuz) {
                    if (zzapc2 != null) {
                        zzaov.zza(2, zzapc2);
                    }
                }
            }
            if (this.zzva != null && this.zzva.length > 0) {
                while (i < this.zzva.length) {
                    zzapc zzapc3 = this.zzva[i];
                    if (zzapc3 != null) {
                        zzaov.zza(3, zzapc3);
                    }
                    i++;
                }
            }
            super.zza(zzaov);
        }

        public zzd zzag() {
            this.zzuy = com.google.android.gms.internal.zzai.zza.zzaq();
            this.zzuz = com.google.android.gms.internal.zzai.zza.zzaq();
            this.zzva = zzc.zzae();
            this.bib = null;
            this.bik = -1;
            return this;
        }

        public /* synthetic */ zzapc zzb(zzaou zzaou) throws IOException {
            return zzm(zzaou);
        }

        public zzd zzm(zzaou zzaou) throws IOException {
            while (true) {
                int J = zzaou.m16039J();
                int zzc;
                Object obj;
                switch (J) {
                    case 0:
                        break;
                    case 10:
                        zzc = zzapf.zzc(zzaou, 10);
                        J = this.zzuy == null ? 0 : this.zzuy.length;
                        obj = new com.google.android.gms.internal.zzai.zza[(zzc + J)];
                        if (J != 0) {
                            System.arraycopy(this.zzuy, 0, obj, 0, J);
                        }
                        while (J < obj.length - 1) {
                            obj[J] = new com.google.android.gms.internal.zzai.zza();
                            zzaou.zza(obj[J]);
                            zzaou.m16039J();
                            J++;
                        }
                        obj[J] = new com.google.android.gms.internal.zzai.zza();
                        zzaou.zza(obj[J]);
                        this.zzuy = obj;
                        continue;
                    case 18:
                        zzc = zzapf.zzc(zzaou, 18);
                        J = this.zzuz == null ? 0 : this.zzuz.length;
                        obj = new com.google.android.gms.internal.zzai.zza[(zzc + J)];
                        if (J != 0) {
                            System.arraycopy(this.zzuz, 0, obj, 0, J);
                        }
                        while (J < obj.length - 1) {
                            obj[J] = new com.google.android.gms.internal.zzai.zza();
                            zzaou.zza(obj[J]);
                            zzaou.m16039J();
                            J++;
                        }
                        obj[J] = new com.google.android.gms.internal.zzai.zza();
                        zzaou.zza(obj[J]);
                        this.zzuz = obj;
                        continue;
                    case 26:
                        zzc = zzapf.zzc(zzaou, 26);
                        J = this.zzva == null ? 0 : this.zzva.length;
                        obj = new zzc[(zzc + J)];
                        if (J != 0) {
                            System.arraycopy(this.zzva, 0, obj, 0, J);
                        }
                        while (J < obj.length - 1) {
                            obj[J] = new zzc();
                            zzaou.zza(obj[J]);
                            zzaou.m16039J();
                            J++;
                        }
                        obj[J] = new zzc();
                        zzaou.zza(obj[J]);
                        this.zzva = obj;
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
            int zzy = super.zzy();
            if (this.zzuy != null && this.zzuy.length > 0) {
                i = zzy;
                for (zzapc zzapc : this.zzuy) {
                    if (zzapc != null) {
                        i += zzaov.zzc(1, zzapc);
                    }
                }
                zzy = i;
            }
            if (this.zzuz != null && this.zzuz.length > 0) {
                i = zzy;
                for (zzapc zzapc2 : this.zzuz) {
                    if (zzapc2 != null) {
                        i += zzaov.zzc(2, zzapc2);
                    }
                }
                zzy = i;
            }
            if (this.zzva != null && this.zzva.length > 0) {
                while (i2 < this.zzva.length) {
                    zzapc zzapc3 = this.zzva[i2];
                    if (zzapc3 != null) {
                        zzy += zzaov.zzc(3, zzapc3);
                    }
                    i2++;
                }
            }
            return zzy;
        }
    }

    public static final class zze extends zzaow<zze> {
        private static volatile zze[] zzvb;
        public int key;
        public int value;

        public zze() {
            zzai();
        }

        public static zze[] zzah() {
            if (zzvb == null) {
                synchronized (zzapa.bij) {
                    if (zzvb == null) {
                        zzvb = new zze[0];
                    }
                }
            }
            return zzvb;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zze)) {
                return false;
            }
            zze zze = (zze) obj;
            return (this.key == zze.key && this.value == zze.value) ? (this.bib == null || this.bib.isEmpty()) ? zze.bib == null || zze.bib.isEmpty() : this.bib.equals(zze.bib) : false;
        }

        public int hashCode() {
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + this.key) * 31) + this.value) * 31;
            int hashCode2 = (this.bib == null || this.bib.isEmpty()) ? 0 : this.bib.hashCode();
            return hashCode2 + hashCode;
        }

        public void zza(zzaov zzaov) throws IOException {
            zzaov.zzae(1, this.key);
            zzaov.zzae(2, this.value);
            super.zza(zzaov);
        }

        public zze zzai() {
            this.key = 0;
            this.value = 0;
            this.bib = null;
            this.bik = -1;
            return this;
        }

        public /* synthetic */ zzapc zzb(zzaou zzaou) throws IOException {
            return zzn(zzaou);
        }

        public zze zzn(zzaou zzaou) throws IOException {
            while (true) {
                int J = zzaou.m16039J();
                switch (J) {
                    case 0:
                        break;
                    case 8:
                        this.key = zzaou.m16043N();
                        continue;
                    case 16:
                        this.value = zzaou.m16043N();
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
            return (super.zzy() + zzaov.zzag(1, this.key)) + zzaov.zzag(2, this.value);
        }
    }

    public static final class zzf extends zzaow<zzf> {
        public String version;
        public String[] zzvc;
        public String[] zzvd;
        public com.google.android.gms.internal.zzai.zza[] zzve;
        public zze[] zzvf;
        public zzb[] zzvg;
        public zzb[] zzvh;
        public zzb[] zzvi;
        public zzg[] zzvj;
        public String zzvk;
        public String zzvl;
        public String zzvm;
        public zza zzvn;
        public float zzvo;
        public boolean zzvp;
        public String[] zzvq;
        public int zzvr;

        public zzf() {
            zzaj();
        }

        public static zzf zze(byte[] bArr) throws zzapb {
            return (zzf) zzapc.zza(new zzf(), bArr);
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzf)) {
                return false;
            }
            zzf zzf = (zzf) obj;
            if (!zzapa.equals(this.zzvc, zzf.zzvc) || !zzapa.equals(this.zzvd, zzf.zzvd) || !zzapa.equals(this.zzve, zzf.zzve) || !zzapa.equals(this.zzvf, zzf.zzvf) || !zzapa.equals(this.zzvg, zzf.zzvg) || !zzapa.equals(this.zzvh, zzf.zzvh) || !zzapa.equals(this.zzvi, zzf.zzvi) || !zzapa.equals(this.zzvj, zzf.zzvj)) {
                return false;
            }
            if (this.zzvk == null) {
                if (zzf.zzvk != null) {
                    return false;
                }
            } else if (!this.zzvk.equals(zzf.zzvk)) {
                return false;
            }
            if (this.zzvl == null) {
                if (zzf.zzvl != null) {
                    return false;
                }
            } else if (!this.zzvl.equals(zzf.zzvl)) {
                return false;
            }
            if (this.zzvm == null) {
                if (zzf.zzvm != null) {
                    return false;
                }
            } else if (!this.zzvm.equals(zzf.zzvm)) {
                return false;
            }
            if (this.version == null) {
                if (zzf.version != null) {
                    return false;
                }
            } else if (!this.version.equals(zzf.version)) {
                return false;
            }
            if (this.zzvn == null) {
                if (zzf.zzvn != null) {
                    return false;
                }
            } else if (!this.zzvn.equals(zzf.zzvn)) {
                return false;
            }
            return (Float.floatToIntBits(this.zzvo) == Float.floatToIntBits(zzf.zzvo) && this.zzvp == zzf.zzvp && zzapa.equals(this.zzvq, zzf.zzvq) && this.zzvr == zzf.zzvr) ? (this.bib == null || this.bib.isEmpty()) ? zzf.bib == null || zzf.bib.isEmpty() : this.bib.equals(zzf.bib) : false;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((((((this.zzvp ? 1231 : 1237) + (((((this.zzvn == null ? 0 : this.zzvn.hashCode()) + (((this.version == null ? 0 : this.version.hashCode()) + (((this.zzvm == null ? 0 : this.zzvm.hashCode()) + (((this.zzvl == null ? 0 : this.zzvl.hashCode()) + (((this.zzvk == null ? 0 : this.zzvk.hashCode()) + ((((((((((((((((((getClass().getName().hashCode() + 527) * 31) + zzapa.hashCode(this.zzvc)) * 31) + zzapa.hashCode(this.zzvd)) * 31) + zzapa.hashCode(this.zzve)) * 31) + zzapa.hashCode(this.zzvf)) * 31) + zzapa.hashCode(this.zzvg)) * 31) + zzapa.hashCode(this.zzvh)) * 31) + zzapa.hashCode(this.zzvi)) * 31) + zzapa.hashCode(this.zzvj)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31) + Float.floatToIntBits(this.zzvo)) * 31)) * 31) + zzapa.hashCode(this.zzvq)) * 31) + this.zzvr) * 31;
            if (!(this.bib == null || this.bib.isEmpty())) {
                i = this.bib.hashCode();
            }
            return hashCode + i;
        }

        public void zza(zzaov zzaov) throws IOException {
            int i = 0;
            if (this.zzvd != null && this.zzvd.length > 0) {
                for (String str : this.zzvd) {
                    if (str != null) {
                        zzaov.zzr(1, str);
                    }
                }
            }
            if (this.zzve != null && this.zzve.length > 0) {
                for (zzapc zzapc : this.zzve) {
                    if (zzapc != null) {
                        zzaov.zza(2, zzapc);
                    }
                }
            }
            if (this.zzvf != null && this.zzvf.length > 0) {
                for (zzapc zzapc2 : this.zzvf) {
                    if (zzapc2 != null) {
                        zzaov.zza(3, zzapc2);
                    }
                }
            }
            if (this.zzvg != null && this.zzvg.length > 0) {
                for (zzapc zzapc22 : this.zzvg) {
                    if (zzapc22 != null) {
                        zzaov.zza(4, zzapc22);
                    }
                }
            }
            if (this.zzvh != null && this.zzvh.length > 0) {
                for (zzapc zzapc222 : this.zzvh) {
                    if (zzapc222 != null) {
                        zzaov.zza(5, zzapc222);
                    }
                }
            }
            if (this.zzvi != null && this.zzvi.length > 0) {
                for (zzapc zzapc2222 : this.zzvi) {
                    if (zzapc2222 != null) {
                        zzaov.zza(6, zzapc2222);
                    }
                }
            }
            if (this.zzvj != null && this.zzvj.length > 0) {
                for (zzapc zzapc22222 : this.zzvj) {
                    if (zzapc22222 != null) {
                        zzaov.zza(7, zzapc22222);
                    }
                }
            }
            if (!this.zzvk.equals("")) {
                zzaov.zzr(9, this.zzvk);
            }
            if (!this.zzvl.equals("")) {
                zzaov.zzr(10, this.zzvl);
            }
            if (!this.zzvm.equals("0")) {
                zzaov.zzr(12, this.zzvm);
            }
            if (!this.version.equals("")) {
                zzaov.zzr(13, this.version);
            }
            if (this.zzvn != null) {
                zzaov.zza(14, this.zzvn);
            }
            if (Float.floatToIntBits(this.zzvo) != Float.floatToIntBits(0.0f)) {
                zzaov.zzc(15, this.zzvo);
            }
            if (this.zzvq != null && this.zzvq.length > 0) {
                for (String str2 : this.zzvq) {
                    if (str2 != null) {
                        zzaov.zzr(16, str2);
                    }
                }
            }
            if (this.zzvr != 0) {
                zzaov.zzae(17, this.zzvr);
            }
            if (this.zzvp) {
                zzaov.zzj(18, this.zzvp);
            }
            if (this.zzvc != null && this.zzvc.length > 0) {
                while (i < this.zzvc.length) {
                    String str3 = this.zzvc[i];
                    if (str3 != null) {
                        zzaov.zzr(19, str3);
                    }
                    i++;
                }
            }
            super.zza(zzaov);
        }

        public zzf zzaj() {
            this.zzvc = zzapf.bir;
            this.zzvd = zzapf.bir;
            this.zzve = com.google.android.gms.internal.zzai.zza.zzaq();
            this.zzvf = zze.zzah();
            this.zzvg = zzb.zzac();
            this.zzvh = zzb.zzac();
            this.zzvi = zzb.zzac();
            this.zzvj = zzg.zzak();
            this.zzvk = "";
            this.zzvl = "";
            this.zzvm = "0";
            this.version = "";
            this.zzvn = null;
            this.zzvo = 0.0f;
            this.zzvp = false;
            this.zzvq = zzapf.bir;
            this.zzvr = 0;
            this.bib = null;
            this.bik = -1;
            return this;
        }

        public /* synthetic */ zzapc zzb(zzaou zzaou) throws IOException {
            return zzo(zzaou);
        }

        public zzf zzo(zzaou zzaou) throws IOException {
            while (true) {
                int J = zzaou.m16039J();
                int zzc;
                Object obj;
                switch (J) {
                    case 0:
                        break;
                    case 10:
                        zzc = zzapf.zzc(zzaou, 10);
                        J = this.zzvd == null ? 0 : this.zzvd.length;
                        obj = new String[(zzc + J)];
                        if (J != 0) {
                            System.arraycopy(this.zzvd, 0, obj, 0, J);
                        }
                        while (J < obj.length - 1) {
                            obj[J] = zzaou.readString();
                            zzaou.m16039J();
                            J++;
                        }
                        obj[J] = zzaou.readString();
                        this.zzvd = obj;
                        continue;
                    case 18:
                        zzc = zzapf.zzc(zzaou, 18);
                        J = this.zzve == null ? 0 : this.zzve.length;
                        obj = new com.google.android.gms.internal.zzai.zza[(zzc + J)];
                        if (J != 0) {
                            System.arraycopy(this.zzve, 0, obj, 0, J);
                        }
                        while (J < obj.length - 1) {
                            obj[J] = new com.google.android.gms.internal.zzai.zza();
                            zzaou.zza(obj[J]);
                            zzaou.m16039J();
                            J++;
                        }
                        obj[J] = new com.google.android.gms.internal.zzai.zza();
                        zzaou.zza(obj[J]);
                        this.zzve = obj;
                        continue;
                    case 26:
                        zzc = zzapf.zzc(zzaou, 26);
                        J = this.zzvf == null ? 0 : this.zzvf.length;
                        obj = new zze[(zzc + J)];
                        if (J != 0) {
                            System.arraycopy(this.zzvf, 0, obj, 0, J);
                        }
                        while (J < obj.length - 1) {
                            obj[J] = new zze();
                            zzaou.zza(obj[J]);
                            zzaou.m16039J();
                            J++;
                        }
                        obj[J] = new zze();
                        zzaou.zza(obj[J]);
                        this.zzvf = obj;
                        continue;
                    case 34:
                        zzc = zzapf.zzc(zzaou, 34);
                        J = this.zzvg == null ? 0 : this.zzvg.length;
                        obj = new zzb[(zzc + J)];
                        if (J != 0) {
                            System.arraycopy(this.zzvg, 0, obj, 0, J);
                        }
                        while (J < obj.length - 1) {
                            obj[J] = new zzb();
                            zzaou.zza(obj[J]);
                            zzaou.m16039J();
                            J++;
                        }
                        obj[J] = new zzb();
                        zzaou.zza(obj[J]);
                        this.zzvg = obj;
                        continue;
                    case 42:
                        zzc = zzapf.zzc(zzaou, 42);
                        J = this.zzvh == null ? 0 : this.zzvh.length;
                        obj = new zzb[(zzc + J)];
                        if (J != 0) {
                            System.arraycopy(this.zzvh, 0, obj, 0, J);
                        }
                        while (J < obj.length - 1) {
                            obj[J] = new zzb();
                            zzaou.zza(obj[J]);
                            zzaou.m16039J();
                            J++;
                        }
                        obj[J] = new zzb();
                        zzaou.zza(obj[J]);
                        this.zzvh = obj;
                        continue;
                    case 50:
                        zzc = zzapf.zzc(zzaou, 50);
                        J = this.zzvi == null ? 0 : this.zzvi.length;
                        obj = new zzb[(zzc + J)];
                        if (J != 0) {
                            System.arraycopy(this.zzvi, 0, obj, 0, J);
                        }
                        while (J < obj.length - 1) {
                            obj[J] = new zzb();
                            zzaou.zza(obj[J]);
                            zzaou.m16039J();
                            J++;
                        }
                        obj[J] = new zzb();
                        zzaou.zza(obj[J]);
                        this.zzvi = obj;
                        continue;
                    case 58:
                        zzc = zzapf.zzc(zzaou, 58);
                        J = this.zzvj == null ? 0 : this.zzvj.length;
                        obj = new zzg[(zzc + J)];
                        if (J != 0) {
                            System.arraycopy(this.zzvj, 0, obj, 0, J);
                        }
                        while (J < obj.length - 1) {
                            obj[J] = new zzg();
                            zzaou.zza(obj[J]);
                            zzaou.m16039J();
                            J++;
                        }
                        obj[J] = new zzg();
                        zzaou.zza(obj[J]);
                        this.zzvj = obj;
                        continue;
                    case 74:
                        this.zzvk = zzaou.readString();
                        continue;
                    case 82:
                        this.zzvl = zzaou.readString();
                        continue;
                    case 98:
                        this.zzvm = zzaou.readString();
                        continue;
                    case 106:
                        this.version = zzaou.readString();
                        continue;
                    case 114:
                        if (this.zzvn == null) {
                            this.zzvn = new zza();
                        }
                        zzaou.zza(this.zzvn);
                        continue;
                    case AVException.INVALID_EMAIL_ADDRESS /*125*/:
                        this.zzvo = zzaou.readFloat();
                        continue;
                    case TransportMediator.KEYCODE_MEDIA_RECORD /*130*/:
                        zzc = zzapf.zzc(zzaou, TransportMediator.KEYCODE_MEDIA_RECORD);
                        J = this.zzvq == null ? 0 : this.zzvq.length;
                        obj = new String[(zzc + J)];
                        if (J != 0) {
                            System.arraycopy(this.zzvq, 0, obj, 0, J);
                        }
                        while (J < obj.length - 1) {
                            obj[J] = zzaou.readString();
                            zzaou.m16039J();
                            J++;
                        }
                        obj[J] = zzaou.readString();
                        this.zzvq = obj;
                        continue;
                    case SyslogConstants.LOG_LOCAL1 /*136*/:
                        this.zzvr = zzaou.m16043N();
                        continue;
                    case SyslogConstants.LOG_LOCAL2 /*144*/:
                        this.zzvp = zzaou.m16045P();
                        continue;
                    case Opcodes.IFNE /*154*/:
                        zzc = zzapf.zzc(zzaou, Opcodes.IFNE);
                        J = this.zzvc == null ? 0 : this.zzvc.length;
                        obj = new String[(zzc + J)];
                        if (J != 0) {
                            System.arraycopy(this.zzvc, 0, obj, 0, J);
                        }
                        while (J < obj.length - 1) {
                            obj[J] = zzaou.readString();
                            zzaou.m16039J();
                            J++;
                        }
                        obj[J] = zzaou.readString();
                        this.zzvc = obj;
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
            int i2;
            int i3;
            int i4 = 0;
            int zzy = super.zzy();
            if (this.zzvd == null || this.zzvd.length <= 0) {
                i = zzy;
            } else {
                i2 = 0;
                i3 = 0;
                for (String str : this.zzvd) {
                    if (str != null) {
                        i3++;
                        i2 += zzaov.zztg(str);
                    }
                }
                i = (zzy + i2) + (i3 * 1);
            }
            if (this.zzve != null && this.zzve.length > 0) {
                i2 = i;
                for (zzapc zzapc : this.zzve) {
                    if (zzapc != null) {
                        i2 += zzaov.zzc(2, zzapc);
                    }
                }
                i = i2;
            }
            if (this.zzvf != null && this.zzvf.length > 0) {
                i2 = i;
                for (zzapc zzapc2 : this.zzvf) {
                    if (zzapc2 != null) {
                        i2 += zzaov.zzc(3, zzapc2);
                    }
                }
                i = i2;
            }
            if (this.zzvg != null && this.zzvg.length > 0) {
                i2 = i;
                for (zzapc zzapc22 : this.zzvg) {
                    if (zzapc22 != null) {
                        i2 += zzaov.zzc(4, zzapc22);
                    }
                }
                i = i2;
            }
            if (this.zzvh != null && this.zzvh.length > 0) {
                i2 = i;
                for (zzapc zzapc222 : this.zzvh) {
                    if (zzapc222 != null) {
                        i2 += zzaov.zzc(5, zzapc222);
                    }
                }
                i = i2;
            }
            if (this.zzvi != null && this.zzvi.length > 0) {
                i2 = i;
                for (zzapc zzapc2222 : this.zzvi) {
                    if (zzapc2222 != null) {
                        i2 += zzaov.zzc(6, zzapc2222);
                    }
                }
                i = i2;
            }
            if (this.zzvj != null && this.zzvj.length > 0) {
                i2 = i;
                for (zzapc zzapc22222 : this.zzvj) {
                    if (zzapc22222 != null) {
                        i2 += zzaov.zzc(7, zzapc22222);
                    }
                }
                i = i2;
            }
            if (!this.zzvk.equals("")) {
                i += zzaov.zzs(9, this.zzvk);
            }
            if (!this.zzvl.equals("")) {
                i += zzaov.zzs(10, this.zzvl);
            }
            if (!this.zzvm.equals("0")) {
                i += zzaov.zzs(12, this.zzvm);
            }
            if (!this.version.equals("")) {
                i += zzaov.zzs(13, this.version);
            }
            if (this.zzvn != null) {
                i += zzaov.zzc(14, this.zzvn);
            }
            if (Float.floatToIntBits(this.zzvo) != Float.floatToIntBits(0.0f)) {
                i += zzaov.zzd(15, this.zzvo);
            }
            if (this.zzvq != null && this.zzvq.length > 0) {
                i3 = 0;
                zzy = 0;
                for (String str2 : this.zzvq) {
                    if (str2 != null) {
                        zzy++;
                        i3 += zzaov.zztg(str2);
                    }
                }
                i = (i + i3) + (zzy * 2);
            }
            if (this.zzvr != 0) {
                i += zzaov.zzag(17, this.zzvr);
            }
            if (this.zzvp) {
                i += zzaov.zzk(18, this.zzvp);
            }
            if (this.zzvc == null || this.zzvc.length <= 0) {
                return i;
            }
            i2 = 0;
            i3 = 0;
            while (i4 < this.zzvc.length) {
                String str3 = this.zzvc[i4];
                if (str3 != null) {
                    i3++;
                    i2 += zzaov.zztg(str3);
                }
                i4++;
            }
            return (i + i2) + (i3 * 2);
        }
    }

    public static final class zzg extends zzaow<zzg> {
        private static volatile zzg[] zzvs;
        public int[] zzvt;
        public int[] zzvu;
        public int[] zzvv;
        public int[] zzvw;
        public int[] zzvx;
        public int[] zzvy;
        public int[] zzvz;
        public int[] zzwa;
        public int[] zzwb;
        public int[] zzwc;

        public zzg() {
            zzal();
        }

        public static zzg[] zzak() {
            if (zzvs == null) {
                synchronized (zzapa.bij) {
                    if (zzvs == null) {
                        zzvs = new zzg[0];
                    }
                }
            }
            return zzvs;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzg)) {
                return false;
            }
            zzg zzg = (zzg) obj;
            return (zzapa.equals(this.zzvt, zzg.zzvt) && zzapa.equals(this.zzvu, zzg.zzvu) && zzapa.equals(this.zzvv, zzg.zzvv) && zzapa.equals(this.zzvw, zzg.zzvw) && zzapa.equals(this.zzvx, zzg.zzvx) && zzapa.equals(this.zzvy, zzg.zzvy) && zzapa.equals(this.zzvz, zzg.zzvz) && zzapa.equals(this.zzwa, zzg.zzwa) && zzapa.equals(this.zzwb, zzg.zzwb) && zzapa.equals(this.zzwc, zzg.zzwc)) ? (this.bib == null || this.bib.isEmpty()) ? zzg.bib == null || zzg.bib.isEmpty() : this.bib.equals(zzg.bib) : false;
        }

        public int hashCode() {
            int hashCode = (((((((((((((((((((((getClass().getName().hashCode() + 527) * 31) + zzapa.hashCode(this.zzvt)) * 31) + zzapa.hashCode(this.zzvu)) * 31) + zzapa.hashCode(this.zzvv)) * 31) + zzapa.hashCode(this.zzvw)) * 31) + zzapa.hashCode(this.zzvx)) * 31) + zzapa.hashCode(this.zzvy)) * 31) + zzapa.hashCode(this.zzvz)) * 31) + zzapa.hashCode(this.zzwa)) * 31) + zzapa.hashCode(this.zzwb)) * 31) + zzapa.hashCode(this.zzwc)) * 31;
            int hashCode2 = (this.bib == null || this.bib.isEmpty()) ? 0 : this.bib.hashCode();
            return hashCode2 + hashCode;
        }

        public void zza(zzaov zzaov) throws IOException {
            int i = 0;
            if (this.zzvt != null && this.zzvt.length > 0) {
                for (int zzae : this.zzvt) {
                    zzaov.zzae(1, zzae);
                }
            }
            if (this.zzvu != null && this.zzvu.length > 0) {
                for (int zzae2 : this.zzvu) {
                    zzaov.zzae(2, zzae2);
                }
            }
            if (this.zzvv != null && this.zzvv.length > 0) {
                for (int zzae22 : this.zzvv) {
                    zzaov.zzae(3, zzae22);
                }
            }
            if (this.zzvw != null && this.zzvw.length > 0) {
                for (int zzae222 : this.zzvw) {
                    zzaov.zzae(4, zzae222);
                }
            }
            if (this.zzvx != null && this.zzvx.length > 0) {
                for (int zzae2222 : this.zzvx) {
                    zzaov.zzae(5, zzae2222);
                }
            }
            if (this.zzvy != null && this.zzvy.length > 0) {
                for (int zzae22222 : this.zzvy) {
                    zzaov.zzae(6, zzae22222);
                }
            }
            if (this.zzvz != null && this.zzvz.length > 0) {
                for (int zzae222222 : this.zzvz) {
                    zzaov.zzae(7, zzae222222);
                }
            }
            if (this.zzwa != null && this.zzwa.length > 0) {
                for (int zzae2222222 : this.zzwa) {
                    zzaov.zzae(8, zzae2222222);
                }
            }
            if (this.zzwb != null && this.zzwb.length > 0) {
                for (int zzae22222222 : this.zzwb) {
                    zzaov.zzae(9, zzae22222222);
                }
            }
            if (this.zzwc != null && this.zzwc.length > 0) {
                while (i < this.zzwc.length) {
                    zzaov.zzae(10, this.zzwc[i]);
                    i++;
                }
            }
            super.zza(zzaov);
        }

        public zzg zzal() {
            this.zzvt = zzapf.bim;
            this.zzvu = zzapf.bim;
            this.zzvv = zzapf.bim;
            this.zzvw = zzapf.bim;
            this.zzvx = zzapf.bim;
            this.zzvy = zzapf.bim;
            this.zzvz = zzapf.bim;
            this.zzwa = zzapf.bim;
            this.zzwb = zzapf.bim;
            this.zzwc = zzapf.bim;
            this.bib = null;
            this.bik = -1;
            return this;
        }

        public /* synthetic */ zzapc zzb(zzaou zzaou) throws IOException {
            return zzp(zzaou);
        }

        public zzg zzp(zzaou zzaou) throws IOException {
            while (true) {
                int J = zzaou.m16039J();
                int zzc;
                Object obj;
                int zzaei;
                Object obj2;
                switch (J) {
                    case 0:
                        break;
                    case 8:
                        zzc = zzapf.zzc(zzaou, 8);
                        J = this.zzvt == null ? 0 : this.zzvt.length;
                        obj = new int[(zzc + J)];
                        if (J != 0) {
                            System.arraycopy(this.zzvt, 0, obj, 0, J);
                        }
                        while (J < obj.length - 1) {
                            obj[J] = zzaou.m16043N();
                            zzaou.m16039J();
                            J++;
                        }
                        obj[J] = zzaou.m16043N();
                        this.zzvt = obj;
                        continue;
                    case 10:
                        zzaei = zzaou.zzaei(zzaou.m16048S());
                        zzc = zzaou.getPosition();
                        J = 0;
                        while (zzaou.m16052X() > 0) {
                            zzaou.m16043N();
                            J++;
                        }
                        zzaou.zzaek(zzc);
                        zzc = this.zzvt == null ? 0 : this.zzvt.length;
                        obj2 = new int[(J + zzc)];
                        if (zzc != 0) {
                            System.arraycopy(this.zzvt, 0, obj2, 0, zzc);
                        }
                        while (zzc < obj2.length) {
                            obj2[zzc] = zzaou.m16043N();
                            zzc++;
                        }
                        this.zzvt = obj2;
                        zzaou.zzaej(zzaei);
                        continue;
                    case 16:
                        zzc = zzapf.zzc(zzaou, 16);
                        J = this.zzvu == null ? 0 : this.zzvu.length;
                        obj = new int[(zzc + J)];
                        if (J != 0) {
                            System.arraycopy(this.zzvu, 0, obj, 0, J);
                        }
                        while (J < obj.length - 1) {
                            obj[J] = zzaou.m16043N();
                            zzaou.m16039J();
                            J++;
                        }
                        obj[J] = zzaou.m16043N();
                        this.zzvu = obj;
                        continue;
                    case 18:
                        zzaei = zzaou.zzaei(zzaou.m16048S());
                        zzc = zzaou.getPosition();
                        J = 0;
                        while (zzaou.m16052X() > 0) {
                            zzaou.m16043N();
                            J++;
                        }
                        zzaou.zzaek(zzc);
                        zzc = this.zzvu == null ? 0 : this.zzvu.length;
                        obj2 = new int[(J + zzc)];
                        if (zzc != 0) {
                            System.arraycopy(this.zzvu, 0, obj2, 0, zzc);
                        }
                        while (zzc < obj2.length) {
                            obj2[zzc] = zzaou.m16043N();
                            zzc++;
                        }
                        this.zzvu = obj2;
                        zzaou.zzaej(zzaei);
                        continue;
                    case 24:
                        zzc = zzapf.zzc(zzaou, 24);
                        J = this.zzvv == null ? 0 : this.zzvv.length;
                        obj = new int[(zzc + J)];
                        if (J != 0) {
                            System.arraycopy(this.zzvv, 0, obj, 0, J);
                        }
                        while (J < obj.length - 1) {
                            obj[J] = zzaou.m16043N();
                            zzaou.m16039J();
                            J++;
                        }
                        obj[J] = zzaou.m16043N();
                        this.zzvv = obj;
                        continue;
                    case 26:
                        zzaei = zzaou.zzaei(zzaou.m16048S());
                        zzc = zzaou.getPosition();
                        J = 0;
                        while (zzaou.m16052X() > 0) {
                            zzaou.m16043N();
                            J++;
                        }
                        zzaou.zzaek(zzc);
                        zzc = this.zzvv == null ? 0 : this.zzvv.length;
                        obj2 = new int[(J + zzc)];
                        if (zzc != 0) {
                            System.arraycopy(this.zzvv, 0, obj2, 0, zzc);
                        }
                        while (zzc < obj2.length) {
                            obj2[zzc] = zzaou.m16043N();
                            zzc++;
                        }
                        this.zzvv = obj2;
                        zzaou.zzaej(zzaei);
                        continue;
                    case 32:
                        zzc = zzapf.zzc(zzaou, 32);
                        J = this.zzvw == null ? 0 : this.zzvw.length;
                        obj = new int[(zzc + J)];
                        if (J != 0) {
                            System.arraycopy(this.zzvw, 0, obj, 0, J);
                        }
                        while (J < obj.length - 1) {
                            obj[J] = zzaou.m16043N();
                            zzaou.m16039J();
                            J++;
                        }
                        obj[J] = zzaou.m16043N();
                        this.zzvw = obj;
                        continue;
                    case 34:
                        zzaei = zzaou.zzaei(zzaou.m16048S());
                        zzc = zzaou.getPosition();
                        J = 0;
                        while (zzaou.m16052X() > 0) {
                            zzaou.m16043N();
                            J++;
                        }
                        zzaou.zzaek(zzc);
                        zzc = this.zzvw == null ? 0 : this.zzvw.length;
                        obj2 = new int[(J + zzc)];
                        if (zzc != 0) {
                            System.arraycopy(this.zzvw, 0, obj2, 0, zzc);
                        }
                        while (zzc < obj2.length) {
                            obj2[zzc] = zzaou.m16043N();
                            zzc++;
                        }
                        this.zzvw = obj2;
                        zzaou.zzaej(zzaei);
                        continue;
                    case 40:
                        zzc = zzapf.zzc(zzaou, 40);
                        J = this.zzvx == null ? 0 : this.zzvx.length;
                        obj = new int[(zzc + J)];
                        if (J != 0) {
                            System.arraycopy(this.zzvx, 0, obj, 0, J);
                        }
                        while (J < obj.length - 1) {
                            obj[J] = zzaou.m16043N();
                            zzaou.m16039J();
                            J++;
                        }
                        obj[J] = zzaou.m16043N();
                        this.zzvx = obj;
                        continue;
                    case 42:
                        zzaei = zzaou.zzaei(zzaou.m16048S());
                        zzc = zzaou.getPosition();
                        J = 0;
                        while (zzaou.m16052X() > 0) {
                            zzaou.m16043N();
                            J++;
                        }
                        zzaou.zzaek(zzc);
                        zzc = this.zzvx == null ? 0 : this.zzvx.length;
                        obj2 = new int[(J + zzc)];
                        if (zzc != 0) {
                            System.arraycopy(this.zzvx, 0, obj2, 0, zzc);
                        }
                        while (zzc < obj2.length) {
                            obj2[zzc] = zzaou.m16043N();
                            zzc++;
                        }
                        this.zzvx = obj2;
                        zzaou.zzaej(zzaei);
                        continue;
                    case 48:
                        zzc = zzapf.zzc(zzaou, 48);
                        J = this.zzvy == null ? 0 : this.zzvy.length;
                        obj = new int[(zzc + J)];
                        if (J != 0) {
                            System.arraycopy(this.zzvy, 0, obj, 0, J);
                        }
                        while (J < obj.length - 1) {
                            obj[J] = zzaou.m16043N();
                            zzaou.m16039J();
                            J++;
                        }
                        obj[J] = zzaou.m16043N();
                        this.zzvy = obj;
                        continue;
                    case 50:
                        zzaei = zzaou.zzaei(zzaou.m16048S());
                        zzc = zzaou.getPosition();
                        J = 0;
                        while (zzaou.m16052X() > 0) {
                            zzaou.m16043N();
                            J++;
                        }
                        zzaou.zzaek(zzc);
                        zzc = this.zzvy == null ? 0 : this.zzvy.length;
                        obj2 = new int[(J + zzc)];
                        if (zzc != 0) {
                            System.arraycopy(this.zzvy, 0, obj2, 0, zzc);
                        }
                        while (zzc < obj2.length) {
                            obj2[zzc] = zzaou.m16043N();
                            zzc++;
                        }
                        this.zzvy = obj2;
                        zzaou.zzaej(zzaei);
                        continue;
                    case 56:
                        zzc = zzapf.zzc(zzaou, 56);
                        J = this.zzvz == null ? 0 : this.zzvz.length;
                        obj = new int[(zzc + J)];
                        if (J != 0) {
                            System.arraycopy(this.zzvz, 0, obj, 0, J);
                        }
                        while (J < obj.length - 1) {
                            obj[J] = zzaou.m16043N();
                            zzaou.m16039J();
                            J++;
                        }
                        obj[J] = zzaou.m16043N();
                        this.zzvz = obj;
                        continue;
                    case 58:
                        zzaei = zzaou.zzaei(zzaou.m16048S());
                        zzc = zzaou.getPosition();
                        J = 0;
                        while (zzaou.m16052X() > 0) {
                            zzaou.m16043N();
                            J++;
                        }
                        zzaou.zzaek(zzc);
                        zzc = this.zzvz == null ? 0 : this.zzvz.length;
                        obj2 = new int[(J + zzc)];
                        if (zzc != 0) {
                            System.arraycopy(this.zzvz, 0, obj2, 0, zzc);
                        }
                        while (zzc < obj2.length) {
                            obj2[zzc] = zzaou.m16043N();
                            zzc++;
                        }
                        this.zzvz = obj2;
                        zzaou.zzaej(zzaei);
                        continue;
                    case 64:
                        zzc = zzapf.zzc(zzaou, 64);
                        J = this.zzwa == null ? 0 : this.zzwa.length;
                        obj = new int[(zzc + J)];
                        if (J != 0) {
                            System.arraycopy(this.zzwa, 0, obj, 0, J);
                        }
                        while (J < obj.length - 1) {
                            obj[J] = zzaou.m16043N();
                            zzaou.m16039J();
                            J++;
                        }
                        obj[J] = zzaou.m16043N();
                        this.zzwa = obj;
                        continue;
                    case 66:
                        zzaei = zzaou.zzaei(zzaou.m16048S());
                        zzc = zzaou.getPosition();
                        J = 0;
                        while (zzaou.m16052X() > 0) {
                            zzaou.m16043N();
                            J++;
                        }
                        zzaou.zzaek(zzc);
                        zzc = this.zzwa == null ? 0 : this.zzwa.length;
                        obj2 = new int[(J + zzc)];
                        if (zzc != 0) {
                            System.arraycopy(this.zzwa, 0, obj2, 0, zzc);
                        }
                        while (zzc < obj2.length) {
                            obj2[zzc] = zzaou.m16043N();
                            zzc++;
                        }
                        this.zzwa = obj2;
                        zzaou.zzaej(zzaei);
                        continue;
                    case 72:
                        zzc = zzapf.zzc(zzaou, 72);
                        J = this.zzwb == null ? 0 : this.zzwb.length;
                        obj = new int[(zzc + J)];
                        if (J != 0) {
                            System.arraycopy(this.zzwb, 0, obj, 0, J);
                        }
                        while (J < obj.length - 1) {
                            obj[J] = zzaou.m16043N();
                            zzaou.m16039J();
                            J++;
                        }
                        obj[J] = zzaou.m16043N();
                        this.zzwb = obj;
                        continue;
                    case 74:
                        zzaei = zzaou.zzaei(zzaou.m16048S());
                        zzc = zzaou.getPosition();
                        J = 0;
                        while (zzaou.m16052X() > 0) {
                            zzaou.m16043N();
                            J++;
                        }
                        zzaou.zzaek(zzc);
                        zzc = this.zzwb == null ? 0 : this.zzwb.length;
                        obj2 = new int[(J + zzc)];
                        if (zzc != 0) {
                            System.arraycopy(this.zzwb, 0, obj2, 0, zzc);
                        }
                        while (zzc < obj2.length) {
                            obj2[zzc] = zzaou.m16043N();
                            zzc++;
                        }
                        this.zzwb = obj2;
                        zzaou.zzaej(zzaei);
                        continue;
                    case 80:
                        zzc = zzapf.zzc(zzaou, 80);
                        J = this.zzwc == null ? 0 : this.zzwc.length;
                        obj = new int[(zzc + J)];
                        if (J != 0) {
                            System.arraycopy(this.zzwc, 0, obj, 0, J);
                        }
                        while (J < obj.length - 1) {
                            obj[J] = zzaou.m16043N();
                            zzaou.m16039J();
                            J++;
                        }
                        obj[J] = zzaou.m16043N();
                        this.zzwc = obj;
                        continue;
                    case 82:
                        zzaei = zzaou.zzaei(zzaou.m16048S());
                        zzc = zzaou.getPosition();
                        J = 0;
                        while (zzaou.m16052X() > 0) {
                            zzaou.m16043N();
                            J++;
                        }
                        zzaou.zzaek(zzc);
                        zzc = this.zzwc == null ? 0 : this.zzwc.length;
                        obj2 = new int[(J + zzc)];
                        if (zzc != 0) {
                            System.arraycopy(this.zzwc, 0, obj2, 0, zzc);
                        }
                        while (zzc < obj2.length) {
                            obj2[zzc] = zzaou.m16043N();
                            zzc++;
                        }
                        this.zzwc = obj2;
                        zzaou.zzaej(zzaei);
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
            int i2;
            int i3 = 0;
            int zzy = super.zzy();
            if (this.zzvt == null || this.zzvt.length <= 0) {
                i = zzy;
            } else {
                i2 = 0;
                for (int zzaeo : this.zzvt) {
                    i2 += zzaov.zzaeo(zzaeo);
                }
                i = (zzy + i2) + (this.zzvt.length * 1);
            }
            if (this.zzvu != null && this.zzvu.length > 0) {
                zzy = 0;
                for (int zzaeo2 : this.zzvu) {
                    zzy += zzaov.zzaeo(zzaeo2);
                }
                i = (i + zzy) + (this.zzvu.length * 1);
            }
            if (this.zzvv != null && this.zzvv.length > 0) {
                zzy = 0;
                for (int zzaeo22 : this.zzvv) {
                    zzy += zzaov.zzaeo(zzaeo22);
                }
                i = (i + zzy) + (this.zzvv.length * 1);
            }
            if (this.zzvw != null && this.zzvw.length > 0) {
                zzy = 0;
                for (int zzaeo222 : this.zzvw) {
                    zzy += zzaov.zzaeo(zzaeo222);
                }
                i = (i + zzy) + (this.zzvw.length * 1);
            }
            if (this.zzvx != null && this.zzvx.length > 0) {
                zzy = 0;
                for (int zzaeo2222 : this.zzvx) {
                    zzy += zzaov.zzaeo(zzaeo2222);
                }
                i = (i + zzy) + (this.zzvx.length * 1);
            }
            if (this.zzvy != null && this.zzvy.length > 0) {
                zzy = 0;
                for (int zzaeo22222 : this.zzvy) {
                    zzy += zzaov.zzaeo(zzaeo22222);
                }
                i = (i + zzy) + (this.zzvy.length * 1);
            }
            if (this.zzvz != null && this.zzvz.length > 0) {
                zzy = 0;
                for (int zzaeo222222 : this.zzvz) {
                    zzy += zzaov.zzaeo(zzaeo222222);
                }
                i = (i + zzy) + (this.zzvz.length * 1);
            }
            if (this.zzwa != null && this.zzwa.length > 0) {
                zzy = 0;
                for (int zzaeo2222222 : this.zzwa) {
                    zzy += zzaov.zzaeo(zzaeo2222222);
                }
                i = (i + zzy) + (this.zzwa.length * 1);
            }
            if (this.zzwb != null && this.zzwb.length > 0) {
                zzy = 0;
                for (int zzaeo22222222 : this.zzwb) {
                    zzy += zzaov.zzaeo(zzaeo22222222);
                }
                i = (i + zzy) + (this.zzwb.length * 1);
            }
            if (this.zzwc == null || this.zzwc.length <= 0) {
                return i;
            }
            i2 = 0;
            while (i3 < this.zzwc.length) {
                i2 += zzaov.zzaeo(this.zzwc[i3]);
                i3++;
            }
            return (i + i2) + (this.zzwc.length * 1);
        }
    }

    public static final class zzh extends zzaow<zzh> {
        public static final zzaox<com.google.android.gms.internal.zzai.zza, zzh> zzwd = zzaox.zza(11, zzh.class, 810);
        private static final zzh[] zzwe = new zzh[0];
        public int[] zzwf;
        public int[] zzwg;
        public int[] zzwh;
        public int zzwi;
        public int[] zzwj;
        public int zzwk;
        public int zzwl;

        public zzh() {
            zzam();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzh)) {
                return false;
            }
            zzh zzh = (zzh) obj;
            return (zzapa.equals(this.zzwf, zzh.zzwf) && zzapa.equals(this.zzwg, zzh.zzwg) && zzapa.equals(this.zzwh, zzh.zzwh) && this.zzwi == zzh.zzwi && zzapa.equals(this.zzwj, zzh.zzwj) && this.zzwk == zzh.zzwk && this.zzwl == zzh.zzwl) ? (this.bib == null || this.bib.isEmpty()) ? zzh.bib == null || zzh.bib.isEmpty() : this.bib.equals(zzh.bib) : false;
        }

        public int hashCode() {
            int hashCode = (((((((((((((((getClass().getName().hashCode() + 527) * 31) + zzapa.hashCode(this.zzwf)) * 31) + zzapa.hashCode(this.zzwg)) * 31) + zzapa.hashCode(this.zzwh)) * 31) + this.zzwi) * 31) + zzapa.hashCode(this.zzwj)) * 31) + this.zzwk) * 31) + this.zzwl) * 31;
            int hashCode2 = (this.bib == null || this.bib.isEmpty()) ? 0 : this.bib.hashCode();
            return hashCode2 + hashCode;
        }

        public void zza(zzaov zzaov) throws IOException {
            int i = 0;
            if (this.zzwf != null && this.zzwf.length > 0) {
                for (int zzae : this.zzwf) {
                    zzaov.zzae(1, zzae);
                }
            }
            if (this.zzwg != null && this.zzwg.length > 0) {
                for (int zzae2 : this.zzwg) {
                    zzaov.zzae(2, zzae2);
                }
            }
            if (this.zzwh != null && this.zzwh.length > 0) {
                for (int zzae22 : this.zzwh) {
                    zzaov.zzae(3, zzae22);
                }
            }
            if (this.zzwi != 0) {
                zzaov.zzae(4, this.zzwi);
            }
            if (this.zzwj != null && this.zzwj.length > 0) {
                while (i < this.zzwj.length) {
                    zzaov.zzae(5, this.zzwj[i]);
                    i++;
                }
            }
            if (this.zzwk != 0) {
                zzaov.zzae(6, this.zzwk);
            }
            if (this.zzwl != 0) {
                zzaov.zzae(7, this.zzwl);
            }
            super.zza(zzaov);
        }

        public zzh zzam() {
            this.zzwf = zzapf.bim;
            this.zzwg = zzapf.bim;
            this.zzwh = zzapf.bim;
            this.zzwi = 0;
            this.zzwj = zzapf.bim;
            this.zzwk = 0;
            this.zzwl = 0;
            this.bib = null;
            this.bik = -1;
            return this;
        }

        public /* synthetic */ zzapc zzb(zzaou zzaou) throws IOException {
            return zzq(zzaou);
        }

        public zzh zzq(zzaou zzaou) throws IOException {
            while (true) {
                int J = zzaou.m16039J();
                int zzc;
                Object obj;
                int zzaei;
                Object obj2;
                switch (J) {
                    case 0:
                        break;
                    case 8:
                        zzc = zzapf.zzc(zzaou, 8);
                        J = this.zzwf == null ? 0 : this.zzwf.length;
                        obj = new int[(zzc + J)];
                        if (J != 0) {
                            System.arraycopy(this.zzwf, 0, obj, 0, J);
                        }
                        while (J < obj.length - 1) {
                            obj[J] = zzaou.m16043N();
                            zzaou.m16039J();
                            J++;
                        }
                        obj[J] = zzaou.m16043N();
                        this.zzwf = obj;
                        continue;
                    case 10:
                        zzaei = zzaou.zzaei(zzaou.m16048S());
                        zzc = zzaou.getPosition();
                        J = 0;
                        while (zzaou.m16052X() > 0) {
                            zzaou.m16043N();
                            J++;
                        }
                        zzaou.zzaek(zzc);
                        zzc = this.zzwf == null ? 0 : this.zzwf.length;
                        obj2 = new int[(J + zzc)];
                        if (zzc != 0) {
                            System.arraycopy(this.zzwf, 0, obj2, 0, zzc);
                        }
                        while (zzc < obj2.length) {
                            obj2[zzc] = zzaou.m16043N();
                            zzc++;
                        }
                        this.zzwf = obj2;
                        zzaou.zzaej(zzaei);
                        continue;
                    case 16:
                        zzc = zzapf.zzc(zzaou, 16);
                        J = this.zzwg == null ? 0 : this.zzwg.length;
                        obj = new int[(zzc + J)];
                        if (J != 0) {
                            System.arraycopy(this.zzwg, 0, obj, 0, J);
                        }
                        while (J < obj.length - 1) {
                            obj[J] = zzaou.m16043N();
                            zzaou.m16039J();
                            J++;
                        }
                        obj[J] = zzaou.m16043N();
                        this.zzwg = obj;
                        continue;
                    case 18:
                        zzaei = zzaou.zzaei(zzaou.m16048S());
                        zzc = zzaou.getPosition();
                        J = 0;
                        while (zzaou.m16052X() > 0) {
                            zzaou.m16043N();
                            J++;
                        }
                        zzaou.zzaek(zzc);
                        zzc = this.zzwg == null ? 0 : this.zzwg.length;
                        obj2 = new int[(J + zzc)];
                        if (zzc != 0) {
                            System.arraycopy(this.zzwg, 0, obj2, 0, zzc);
                        }
                        while (zzc < obj2.length) {
                            obj2[zzc] = zzaou.m16043N();
                            zzc++;
                        }
                        this.zzwg = obj2;
                        zzaou.zzaej(zzaei);
                        continue;
                    case 24:
                        zzc = zzapf.zzc(zzaou, 24);
                        J = this.zzwh == null ? 0 : this.zzwh.length;
                        obj = new int[(zzc + J)];
                        if (J != 0) {
                            System.arraycopy(this.zzwh, 0, obj, 0, J);
                        }
                        while (J < obj.length - 1) {
                            obj[J] = zzaou.m16043N();
                            zzaou.m16039J();
                            J++;
                        }
                        obj[J] = zzaou.m16043N();
                        this.zzwh = obj;
                        continue;
                    case 26:
                        zzaei = zzaou.zzaei(zzaou.m16048S());
                        zzc = zzaou.getPosition();
                        J = 0;
                        while (zzaou.m16052X() > 0) {
                            zzaou.m16043N();
                            J++;
                        }
                        zzaou.zzaek(zzc);
                        zzc = this.zzwh == null ? 0 : this.zzwh.length;
                        obj2 = new int[(J + zzc)];
                        if (zzc != 0) {
                            System.arraycopy(this.zzwh, 0, obj2, 0, zzc);
                        }
                        while (zzc < obj2.length) {
                            obj2[zzc] = zzaou.m16043N();
                            zzc++;
                        }
                        this.zzwh = obj2;
                        zzaou.zzaej(zzaei);
                        continue;
                    case 32:
                        this.zzwi = zzaou.m16043N();
                        continue;
                    case 40:
                        zzc = zzapf.zzc(zzaou, 40);
                        J = this.zzwj == null ? 0 : this.zzwj.length;
                        obj = new int[(zzc + J)];
                        if (J != 0) {
                            System.arraycopy(this.zzwj, 0, obj, 0, J);
                        }
                        while (J < obj.length - 1) {
                            obj[J] = zzaou.m16043N();
                            zzaou.m16039J();
                            J++;
                        }
                        obj[J] = zzaou.m16043N();
                        this.zzwj = obj;
                        continue;
                    case 42:
                        zzaei = zzaou.zzaei(zzaou.m16048S());
                        zzc = zzaou.getPosition();
                        J = 0;
                        while (zzaou.m16052X() > 0) {
                            zzaou.m16043N();
                            J++;
                        }
                        zzaou.zzaek(zzc);
                        zzc = this.zzwj == null ? 0 : this.zzwj.length;
                        obj2 = new int[(J + zzc)];
                        if (zzc != 0) {
                            System.arraycopy(this.zzwj, 0, obj2, 0, zzc);
                        }
                        while (zzc < obj2.length) {
                            obj2[zzc] = zzaou.m16043N();
                            zzc++;
                        }
                        this.zzwj = obj2;
                        zzaou.zzaej(zzaei);
                        continue;
                    case 48:
                        this.zzwk = zzaou.m16043N();
                        continue;
                    case 56:
                        this.zzwl = zzaou.m16043N();
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
            int i2;
            int i3 = 0;
            int zzy = super.zzy();
            if (this.zzwf == null || this.zzwf.length <= 0) {
                i = zzy;
            } else {
                i2 = 0;
                for (int zzaeo : this.zzwf) {
                    i2 += zzaov.zzaeo(zzaeo);
                }
                i = (zzy + i2) + (this.zzwf.length * 1);
            }
            if (this.zzwg != null && this.zzwg.length > 0) {
                zzy = 0;
                for (int zzaeo2 : this.zzwg) {
                    zzy += zzaov.zzaeo(zzaeo2);
                }
                i = (i + zzy) + (this.zzwg.length * 1);
            }
            if (this.zzwh != null && this.zzwh.length > 0) {
                zzy = 0;
                for (int zzaeo22 : this.zzwh) {
                    zzy += zzaov.zzaeo(zzaeo22);
                }
                i = (i + zzy) + (this.zzwh.length * 1);
            }
            if (this.zzwi != 0) {
                i += zzaov.zzag(4, this.zzwi);
            }
            if (this.zzwj != null && this.zzwj.length > 0) {
                i2 = 0;
                while (i3 < this.zzwj.length) {
                    i2 += zzaov.zzaeo(this.zzwj[i3]);
                    i3++;
                }
                i = (i + i2) + (this.zzwj.length * 1);
            }
            if (this.zzwk != 0) {
                i += zzaov.zzag(6, this.zzwk);
            }
            return this.zzwl != 0 ? i + zzaov.zzag(7, this.zzwl) : i;
        }
    }

    public static final class zzi extends zzaow<zzi> {
        private static volatile zzi[] zzwm;
        public String name;
        public com.google.android.gms.internal.zzai.zza zzwn;
        public zzd zzwo;

        public zzi() {
            zzao();
        }

        public static zzi[] zzan() {
            if (zzwm == null) {
                synchronized (zzapa.bij) {
                    if (zzwm == null) {
                        zzwm = new zzi[0];
                    }
                }
            }
            return zzwm;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzi)) {
                return false;
            }
            zzi zzi = (zzi) obj;
            if (this.name == null) {
                if (zzi.name != null) {
                    return false;
                }
            } else if (!this.name.equals(zzi.name)) {
                return false;
            }
            if (this.zzwn == null) {
                if (zzi.zzwn != null) {
                    return false;
                }
            } else if (!this.zzwn.equals(zzi.zzwn)) {
                return false;
            }
            if (this.zzwo == null) {
                if (zzi.zzwo != null) {
                    return false;
                }
            } else if (!this.zzwo.equals(zzi.zzwo)) {
                return false;
            }
            return (this.bib == null || this.bib.isEmpty()) ? zzi.bib == null || zzi.bib.isEmpty() : this.bib.equals(zzi.bib);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.zzwo == null ? 0 : this.zzwo.hashCode()) + (((this.zzwn == null ? 0 : this.zzwn.hashCode()) + (((this.name == null ? 0 : this.name.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31)) * 31;
            if (!(this.bib == null || this.bib.isEmpty())) {
                i = this.bib.hashCode();
            }
            return hashCode + i;
        }

        public void zza(zzaov zzaov) throws IOException {
            if (!this.name.equals("")) {
                zzaov.zzr(1, this.name);
            }
            if (this.zzwn != null) {
                zzaov.zza(2, this.zzwn);
            }
            if (this.zzwo != null) {
                zzaov.zza(3, this.zzwo);
            }
            super.zza(zzaov);
        }

        public zzi zzao() {
            this.name = "";
            this.zzwn = null;
            this.zzwo = null;
            this.bib = null;
            this.bik = -1;
            return this;
        }

        public /* synthetic */ zzapc zzb(zzaou zzaou) throws IOException {
            return zzr(zzaou);
        }

        public zzi zzr(zzaou zzaou) throws IOException {
            while (true) {
                int J = zzaou.m16039J();
                switch (J) {
                    case 0:
                        break;
                    case 10:
                        this.name = zzaou.readString();
                        continue;
                    case 18:
                        if (this.zzwn == null) {
                            this.zzwn = new com.google.android.gms.internal.zzai.zza();
                        }
                        zzaou.zza(this.zzwn);
                        continue;
                    case 26:
                        if (this.zzwo == null) {
                            this.zzwo = new zzd();
                        }
                        zzaou.zza(this.zzwo);
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
            int zzy = super.zzy();
            if (!this.name.equals("")) {
                zzy += zzaov.zzs(1, this.name);
            }
            if (this.zzwn != null) {
                zzy += zzaov.zzc(2, this.zzwn);
            }
            return this.zzwo != null ? zzy + zzaov.zzc(3, this.zzwo) : zzy;
        }
    }

    public static final class zzj extends zzaow<zzj> {
        public zzi[] zzwp;
        public zzf zzwq;
        public String zzwr;

        public zzj() {
            zzap();
        }

        public static zzj zzf(byte[] bArr) throws zzapb {
            return (zzj) zzapc.zza(new zzj(), bArr);
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzj)) {
                return false;
            }
            zzj zzj = (zzj) obj;
            if (!zzapa.equals(this.zzwp, zzj.zzwp)) {
                return false;
            }
            if (this.zzwq == null) {
                if (zzj.zzwq != null) {
                    return false;
                }
            } else if (!this.zzwq.equals(zzj.zzwq)) {
                return false;
            }
            if (this.zzwr == null) {
                if (zzj.zzwr != null) {
                    return false;
                }
            } else if (!this.zzwr.equals(zzj.zzwr)) {
                return false;
            }
            return (this.bib == null || this.bib.isEmpty()) ? zzj.bib == null || zzj.bib.isEmpty() : this.bib.equals(zzj.bib);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.zzwr == null ? 0 : this.zzwr.hashCode()) + (((this.zzwq == null ? 0 : this.zzwq.hashCode()) + ((((getClass().getName().hashCode() + 527) * 31) + zzapa.hashCode(this.zzwp)) * 31)) * 31)) * 31;
            if (!(this.bib == null || this.bib.isEmpty())) {
                i = this.bib.hashCode();
            }
            return hashCode + i;
        }

        public void zza(zzaov zzaov) throws IOException {
            if (this.zzwp != null && this.zzwp.length > 0) {
                for (zzapc zzapc : this.zzwp) {
                    if (zzapc != null) {
                        zzaov.zza(1, zzapc);
                    }
                }
            }
            if (this.zzwq != null) {
                zzaov.zza(2, this.zzwq);
            }
            if (!this.zzwr.equals("")) {
                zzaov.zzr(3, this.zzwr);
            }
            super.zza(zzaov);
        }

        public zzj zzap() {
            this.zzwp = zzi.zzan();
            this.zzwq = null;
            this.zzwr = "";
            this.bib = null;
            this.bik = -1;
            return this;
        }

        public /* synthetic */ zzapc zzb(zzaou zzaou) throws IOException {
            return zzs(zzaou);
        }

        public zzj zzs(zzaou zzaou) throws IOException {
            while (true) {
                int J = zzaou.m16039J();
                switch (J) {
                    case 0:
                        break;
                    case 10:
                        int zzc = zzapf.zzc(zzaou, 10);
                        J = this.zzwp == null ? 0 : this.zzwp.length;
                        Object obj = new zzi[(zzc + J)];
                        if (J != 0) {
                            System.arraycopy(this.zzwp, 0, obj, 0, J);
                        }
                        while (J < obj.length - 1) {
                            obj[J] = new zzi();
                            zzaou.zza(obj[J]);
                            zzaou.m16039J();
                            J++;
                        }
                        obj[J] = new zzi();
                        zzaou.zza(obj[J]);
                        this.zzwp = obj;
                        continue;
                    case 18:
                        if (this.zzwq == null) {
                            this.zzwq = new zzf();
                        }
                        zzaou.zza(this.zzwq);
                        continue;
                    case 26:
                        this.zzwr = zzaou.readString();
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
            int zzy = super.zzy();
            if (this.zzwp != null && this.zzwp.length > 0) {
                for (zzapc zzapc : this.zzwp) {
                    if (zzapc != null) {
                        zzy += zzaov.zzc(1, zzapc);
                    }
                }
            }
            if (this.zzwq != null) {
                zzy += zzaov.zzc(2, this.zzwq);
            }
            return !this.zzwr.equals("") ? zzy + zzaov.zzs(3, this.zzwr) : zzy;
        }
    }
}
