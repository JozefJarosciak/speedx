package com.google.android.gms.internal;

import java.io.IOException;

public interface zzad {

    public static final class zza extends zzaow<zza> {
        public String stackTrace;
        public String zzck;
        public Long zzcl;
        public String zzcm;
        public String zzcn;
        public Long zzco;
        public Long zzcp;
        public String zzcq;
        public Long zzcr;
        public String zzcs;

        public zza() {
            this.zzck = null;
            this.zzcl = null;
            this.stackTrace = null;
            this.zzcm = null;
            this.zzcn = null;
            this.zzco = null;
            this.zzcp = null;
            this.zzcq = null;
            this.zzcr = null;
            this.zzcs = null;
            this.bik = -1;
        }

        public zza zza(zzaou zzaou) throws IOException {
            while (true) {
                int J = zzaou.m16039J();
                switch (J) {
                    case 0:
                        break;
                    case 10:
                        this.zzck = zzaou.readString();
                        continue;
                    case 16:
                        this.zzcl = Long.valueOf(zzaou.m16042M());
                        continue;
                    case 26:
                        this.stackTrace = zzaou.readString();
                        continue;
                    case 34:
                        this.zzcm = zzaou.readString();
                        continue;
                    case 42:
                        this.zzcn = zzaou.readString();
                        continue;
                    case 48:
                        this.zzco = Long.valueOf(zzaou.m16042M());
                        continue;
                    case 56:
                        this.zzcp = Long.valueOf(zzaou.m16042M());
                        continue;
                    case 66:
                        this.zzcq = zzaou.readString();
                        continue;
                    case 72:
                        this.zzcr = Long.valueOf(zzaou.m16042M());
                        continue;
                    case 82:
                        this.zzcs = zzaou.readString();
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

        public void zza(zzaov zzaov) throws IOException {
            if (this.zzck != null) {
                zzaov.zzr(1, this.zzck);
            }
            if (this.zzcl != null) {
                zzaov.zzb(2, this.zzcl.longValue());
            }
            if (this.stackTrace != null) {
                zzaov.zzr(3, this.stackTrace);
            }
            if (this.zzcm != null) {
                zzaov.zzr(4, this.zzcm);
            }
            if (this.zzcn != null) {
                zzaov.zzr(5, this.zzcn);
            }
            if (this.zzco != null) {
                zzaov.zzb(6, this.zzco.longValue());
            }
            if (this.zzcp != null) {
                zzaov.zzb(7, this.zzcp.longValue());
            }
            if (this.zzcq != null) {
                zzaov.zzr(8, this.zzcq);
            }
            if (this.zzcr != null) {
                zzaov.zzb(9, this.zzcr.longValue());
            }
            if (this.zzcs != null) {
                zzaov.zzr(10, this.zzcs);
            }
            super.zza(zzaov);
        }

        public /* synthetic */ zzapc zzb(zzaou zzaou) throws IOException {
            return zza(zzaou);
        }

        protected int zzy() {
            int zzy = super.zzy();
            if (this.zzck != null) {
                zzy += zzaov.zzs(1, this.zzck);
            }
            if (this.zzcl != null) {
                zzy += zzaov.zze(2, this.zzcl.longValue());
            }
            if (this.stackTrace != null) {
                zzy += zzaov.zzs(3, this.stackTrace);
            }
            if (this.zzcm != null) {
                zzy += zzaov.zzs(4, this.zzcm);
            }
            if (this.zzcn != null) {
                zzy += zzaov.zzs(5, this.zzcn);
            }
            if (this.zzco != null) {
                zzy += zzaov.zze(6, this.zzco.longValue());
            }
            if (this.zzcp != null) {
                zzy += zzaov.zze(7, this.zzcp.longValue());
            }
            if (this.zzcq != null) {
                zzy += zzaov.zzs(8, this.zzcq);
            }
            if (this.zzcr != null) {
                zzy += zzaov.zze(9, this.zzcr.longValue());
            }
            return this.zzcs != null ? zzy + zzaov.zzs(10, this.zzcs) : zzy;
        }
    }
}
