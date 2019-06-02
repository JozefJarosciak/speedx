package com.google.android.gms.common.data;

import android.os.Bundle;
import java.util.Iterator;

public abstract class AbstractDataBuffer<T> implements DataBuffer<T> {
    protected final DataHolder tk;

    protected AbstractDataBuffer(DataHolder dataHolder) {
        this.tk = dataHolder;
        if (this.tk != null) {
            this.tk.zzv(this);
        }
    }

    @Deprecated
    public final void close() {
        release();
    }

    public abstract T get(int i);

    public int getCount() {
        return this.tk == null ? 0 : this.tk.getCount();
    }

    @Deprecated
    public boolean isClosed() {
        return this.tk == null || this.tk.isClosed();
    }

    public Iterator<T> iterator() {
        return new zzb(this);
    }

    public void release() {
        if (this.tk != null) {
            this.tk.close();
        }
    }

    public Iterator<T> singleRefIterator() {
        return new zzg(this);
    }

    public Bundle zzaqy() {
        return this.tk.zzaqy();
    }
}
