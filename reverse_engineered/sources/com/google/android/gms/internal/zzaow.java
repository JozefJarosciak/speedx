package com.google.android.gms.internal;

import java.io.IOException;

public abstract class zzaow<M extends zzaow<M>> extends zzapc {
    protected zzaoy bib;

    public M ac() throws CloneNotSupportedException {
        zzaow zzaow = (zzaow) super.ad();
        zzapa.zza(this, zzaow);
        return zzaow;
    }

    public /* synthetic */ zzapc ad() throws CloneNotSupportedException {
        return (zzaow) clone();
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return ac();
    }

    public final <T> T zza(zzaox<M, T> zzaox) {
        if (this.bib == null) {
            return null;
        }
        zzaoz zzaew = this.bib.zzaew(zzapf.zzafa(zzaox.tag));
        return zzaew != null ? zzaew.zzb(zzaox) : null;
    }

    public void zza(zzaov zzaov) throws IOException {
        if (this.bib != null) {
            for (int i = 0; i < this.bib.size(); i++) {
                this.bib.zzaex(i).zza(zzaov);
            }
        }
    }

    protected final boolean zza(zzaou zzaou, int i) throws IOException {
        int position = zzaou.getPosition();
        if (!zzaou.zzaeg(i)) {
            return false;
        }
        int zzafa = zzapf.zzafa(i);
        zzape zzape = new zzape(i, zzaou.zzad(position, zzaou.getPosition() - position));
        zzaoz zzaoz = null;
        if (this.bib == null) {
            this.bib = new zzaoy();
        } else {
            zzaoz = this.bib.zzaew(zzafa);
        }
        if (zzaoz == null) {
            zzaoz = new zzaoz();
            this.bib.zza(zzafa, zzaoz);
        }
        zzaoz.zza(zzape);
        return true;
    }

    protected int zzy() {
        int i = 0;
        if (this.bib == null) {
            return 0;
        }
        int i2 = 0;
        while (i < this.bib.size()) {
            i2 += this.bib.zzaex(i).zzy();
            i++;
        }
        return i2;
    }
}
