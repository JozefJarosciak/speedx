package com.google.android.gms.common.data;

import com.google.android.gms.common.internal.zzab;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class zzb<T> implements Iterator<T> {
    protected final DataBuffer<T> vH;
    protected int vI = -1;

    public zzb(DataBuffer<T> dataBuffer) {
        this.vH = (DataBuffer) zzab.zzaa(dataBuffer);
    }

    public boolean hasNext() {
        return this.vI < this.vH.getCount() + -1;
    }

    public T next() {
        if (hasNext()) {
            DataBuffer dataBuffer = this.vH;
            int i = this.vI + 1;
            this.vI = i;
            return dataBuffer.get(i);
        }
        throw new NoSuchElementException("Cannot advance the iterator beyond " + this.vI);
    }

    public void remove() {
        throw new UnsupportedOperationException("Cannot remove elements from a DataBufferIterator");
    }
}
