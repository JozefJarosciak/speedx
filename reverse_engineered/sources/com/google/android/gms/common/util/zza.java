package com.google.android.gms.common.util;

import android.support.v4.util.ArrayMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;

public class zza<E> extends AbstractSet<E> {
    private final ArrayMap<E, E> AJ;

    public zza() {
        this.AJ = new ArrayMap();
    }

    public zza(int i) {
        this.AJ = new ArrayMap(i);
    }

    public zza(Collection<E> collection) {
        this(collection.size());
        addAll(collection);
    }

    public boolean add(E e) {
        if (this.AJ.containsKey(e)) {
            return false;
        }
        this.AJ.put(e, e);
        return true;
    }

    public boolean addAll(Collection<? extends E> collection) {
        return collection instanceof zza ? zza((zza) collection) : super.addAll(collection);
    }

    public void clear() {
        this.AJ.clear();
    }

    public boolean contains(Object obj) {
        return this.AJ.containsKey(obj);
    }

    public Iterator<E> iterator() {
        return this.AJ.keySet().iterator();
    }

    public boolean remove(Object obj) {
        if (!this.AJ.containsKey(obj)) {
            return false;
        }
        this.AJ.remove(obj);
        return true;
    }

    public int size() {
        return this.AJ.size();
    }

    public boolean zza(zza<? extends E> zza) {
        int size = size();
        this.AJ.putAll(zza.AJ);
        return size() > size;
    }
}
