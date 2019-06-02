package com.google.android.gms.internal;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public abstract class zzamy {
    public String toString() {
        try {
            Writer stringWriter = new StringWriter();
            zzaor zzaor = new zzaor(stringWriter);
            zzaor.setLenient(true);
            zzanz.zzb(this, zzaor);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    public Number zzczg() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public String zzczh() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public double zzczi() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public long zzczj() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public int zzczk() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public boolean zzczl() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public boolean zzczm() {
        return this instanceof zzamv;
    }

    public boolean zzczn() {
        return this instanceof zzanb;
    }

    public boolean zzczo() {
        return this instanceof zzane;
    }

    public boolean zzczp() {
        return this instanceof zzana;
    }

    public zzanb zzczq() {
        if (zzczn()) {
            return (zzanb) this;
        }
        String valueOf = String.valueOf(this);
        throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 19).append("Not a JSON Object: ").append(valueOf).toString());
    }

    public zzamv zzczr() {
        if (zzczm()) {
            return (zzamv) this;
        }
        throw new IllegalStateException("This is not a JSON Array.");
    }

    public zzane zzczs() {
        if (zzczo()) {
            return (zzane) this;
        }
        throw new IllegalStateException("This is not a JSON Primitive.");
    }

    Boolean zzczt() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }
}
