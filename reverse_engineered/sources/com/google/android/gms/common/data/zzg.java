package com.google.android.gms.common.data;

import java.util.NoSuchElementException;

public class zzg<T> extends zzb<T> {
    private T we;

    public zzg(DataBuffer<T> dataBuffer) {
        super(dataBuffer);
    }

    public T next() {
        if (hasNext()) {
            this.vI++;
            if (this.vI == 0) {
                this.we = this.vH.get(0);
                if (!(this.we instanceof zzc)) {
                    String valueOf = String.valueOf(this.we.getClass());
                    throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 44).append("DataBuffer reference of type ").append(valueOf).append(" is not movable").toString());
                }
            }
            ((zzc) this.we).zzfm(this.vI);
            return this.we;
        }
        throw new NoSuchElementException("Cannot advance the iterator beyond " + this.vI);
    }
}
