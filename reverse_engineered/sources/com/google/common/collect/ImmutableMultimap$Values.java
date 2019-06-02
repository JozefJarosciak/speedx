package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import java.util.Iterator;

final class ImmutableMultimap$Values<K, V> extends ImmutableCollection<V> {
    private static final long serialVersionUID = 0;
    private final transient ImmutableMultimap<K, V> multimap;

    ImmutableMultimap$Values(ImmutableMultimap<K, V> immutableMultimap) {
        this.multimap = immutableMultimap;
    }

    public boolean contains(Object obj) {
        return this.multimap.containsValue(obj);
    }

    public UnmodifiableIterator<V> iterator() {
        return this.multimap.valueIterator();
    }

    @GwtIncompatible("not present in emulated superclass")
    int copyIntoArray(Object[] objArr, int i) {
        Iterator it = this.multimap.map.values().iterator();
        while (it.hasNext()) {
            i = ((ImmutableCollection) it.next()).copyIntoArray(objArr, i);
        }
        return i;
    }

    public int size() {
        return this.multimap.size();
    }

    boolean isPartialView() {
        return true;
    }
}
