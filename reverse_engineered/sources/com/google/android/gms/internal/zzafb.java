package com.google.android.gms.internal;

import java.io.IOException;

public final class zzafb extends zzaow<zzafb> {
    public String[] aMI;
    public int[] aMJ;
    public byte[][] aMK;

    public zzafb() {
        zzcjy();
    }

    public static zzafb zzas(byte[] bArr) throws zzapb {
        return (zzafb) zzapc.zza(new zzafb(), bArr);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzafb)) {
            return false;
        }
        zzafb zzafb = (zzafb) obj;
        return (zzapa.equals(this.aMI, zzafb.aMI) && zzapa.equals(this.aMJ, zzafb.aMJ) && zzapa.zza(this.aMK, zzafb.aMK)) ? (this.bib == null || this.bib.isEmpty()) ? zzafb.bib == null || zzafb.bib.isEmpty() : this.bib.equals(zzafb.bib) : false;
    }

    public int hashCode() {
        int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + zzapa.hashCode(this.aMI)) * 31) + zzapa.hashCode(this.aMJ)) * 31) + zzapa.zzb(this.aMK)) * 31;
        int hashCode2 = (this.bib == null || this.bib.isEmpty()) ? 0 : this.bib.hashCode();
        return hashCode2 + hashCode;
    }

    public void zza(zzaov zzaov) throws IOException {
        int i = 0;
        if (this.aMI != null && this.aMI.length > 0) {
            for (String str : this.aMI) {
                if (str != null) {
                    zzaov.zzr(1, str);
                }
            }
        }
        if (this.aMJ != null && this.aMJ.length > 0) {
            for (int zzae : this.aMJ) {
                zzaov.zzae(2, zzae);
            }
        }
        if (this.aMK != null && this.aMK.length > 0) {
            while (i < this.aMK.length) {
                byte[] bArr = this.aMK[i];
                if (bArr != null) {
                    zzaov.zza(3, bArr);
                }
                i++;
            }
        }
        super.zza(zzaov);
    }

    public /* synthetic */ zzapc zzb(zzaou zzaou) throws IOException {
        return zzby(zzaou);
    }

    public zzafb zzby(zzaou zzaou) throws IOException {
        while (true) {
            int J = zzaou.m16039J();
            int zzc;
            Object obj;
            switch (J) {
                case 0:
                    break;
                case 10:
                    zzc = zzapf.zzc(zzaou, 10);
                    J = this.aMI == null ? 0 : this.aMI.length;
                    obj = new String[(zzc + J)];
                    if (J != 0) {
                        System.arraycopy(this.aMI, 0, obj, 0, J);
                    }
                    while (J < obj.length - 1) {
                        obj[J] = zzaou.readString();
                        zzaou.m16039J();
                        J++;
                    }
                    obj[J] = zzaou.readString();
                    this.aMI = obj;
                    continue;
                case 16:
                    zzc = zzapf.zzc(zzaou, 16);
                    J = this.aMJ == null ? 0 : this.aMJ.length;
                    obj = new int[(zzc + J)];
                    if (J != 0) {
                        System.arraycopy(this.aMJ, 0, obj, 0, J);
                    }
                    while (J < obj.length - 1) {
                        obj[J] = zzaou.m16043N();
                        zzaou.m16039J();
                        J++;
                    }
                    obj[J] = zzaou.m16043N();
                    this.aMJ = obj;
                    continue;
                case 18:
                    int zzaei = zzaou.zzaei(zzaou.m16048S());
                    zzc = zzaou.getPosition();
                    J = 0;
                    while (zzaou.m16052X() > 0) {
                        zzaou.m16043N();
                        J++;
                    }
                    zzaou.zzaek(zzc);
                    zzc = this.aMJ == null ? 0 : this.aMJ.length;
                    Object obj2 = new int[(J + zzc)];
                    if (zzc != 0) {
                        System.arraycopy(this.aMJ, 0, obj2, 0, zzc);
                    }
                    while (zzc < obj2.length) {
                        obj2[zzc] = zzaou.m16043N();
                        zzc++;
                    }
                    this.aMJ = obj2;
                    zzaou.zzaej(zzaei);
                    continue;
                case 26:
                    zzc = zzapf.zzc(zzaou, 26);
                    J = this.aMK == null ? 0 : this.aMK.length;
                    obj = new byte[(zzc + J)][];
                    if (J != 0) {
                        System.arraycopy(this.aMK, 0, obj, 0, J);
                    }
                    while (J < obj.length - 1) {
                        obj[J] = zzaou.readBytes();
                        zzaou.m16039J();
                        J++;
                    }
                    obj[J] = zzaou.readBytes();
                    this.aMK = obj;
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

    public zzafb zzcjy() {
        this.aMI = zzapf.bir;
        this.aMJ = zzapf.bim;
        this.aMK = zzapf.bis;
        this.bib = null;
        this.bik = -1;
        return this;
    }

    protected int zzy() {
        int i;
        int i2;
        int i3;
        int i4 = 0;
        int zzy = super.zzy();
        if (this.aMI == null || this.aMI.length <= 0) {
            i = zzy;
        } else {
            i2 = 0;
            i3 = 0;
            for (String str : this.aMI) {
                if (str != null) {
                    i3++;
                    i2 += zzaov.zztg(str);
                }
            }
            i = (zzy + i2) + (i3 * 1);
        }
        if (this.aMJ != null && this.aMJ.length > 0) {
            i3 = 0;
            for (int zzy2 : this.aMJ) {
                i3 += zzaov.zzaeo(zzy2);
            }
            i = (i + i3) + (this.aMJ.length * 1);
        }
        if (this.aMK == null || this.aMK.length <= 0) {
            return i;
        }
        i2 = 0;
        i3 = 0;
        while (i4 < this.aMK.length) {
            byte[] bArr = this.aMK[i4];
            if (bArr != null) {
                i3++;
                i2 += zzaov.zzbc(bArr);
            }
            i4++;
        }
        return (i + i2) + (i3 * 1);
    }
}
