package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class zzamv extends zzamy implements Iterable<zzamy> {
    private final List<zzamy> aFD = new ArrayList();

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof zzamv) && ((zzamv) obj).aFD.equals(this.aFD));
    }

    public int hashCode() {
        return this.aFD.hashCode();
    }

    public Iterator<zzamy> iterator() {
        return this.aFD.iterator();
    }

    public void zzc(zzamy zzamy) {
        Object obj;
        if (zzamy == null) {
            obj = zzana.bes;
        }
        this.aFD.add(obj);
    }

    public Number zzczg() {
        if (this.aFD.size() == 1) {
            return ((zzamy) this.aFD.get(0)).zzczg();
        }
        throw new IllegalStateException();
    }

    public String zzczh() {
        if (this.aFD.size() == 1) {
            return ((zzamy) this.aFD.get(0)).zzczh();
        }
        throw new IllegalStateException();
    }

    public double zzczi() {
        if (this.aFD.size() == 1) {
            return ((zzamy) this.aFD.get(0)).zzczi();
        }
        throw new IllegalStateException();
    }

    public long zzczj() {
        if (this.aFD.size() == 1) {
            return ((zzamy) this.aFD.get(0)).zzczj();
        }
        throw new IllegalStateException();
    }

    public int zzczk() {
        if (this.aFD.size() == 1) {
            return ((zzamy) this.aFD.get(0)).zzczk();
        }
        throw new IllegalStateException();
    }

    public boolean zzczl() {
        if (this.aFD.size() == 1) {
            return ((zzamy) this.aFD.get(0)).zzczl();
        }
        throw new IllegalStateException();
    }
}
