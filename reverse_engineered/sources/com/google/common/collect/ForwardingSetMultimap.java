package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Map.Entry;
import java.util.Set;

@GwtCompatible
public abstract class ForwardingSetMultimap<K, V> extends ForwardingMultimap<K, V> implements SetMultimap<K, V> {
    protected abstract SetMultimap<K, V> delegate();

    public Set<Entry<K, V>> entries() {
        return delegate().entries();
    }

    public Set<V> get(K k) {
        return delegate().get(k);
    }

    public Set<V> removeAll(Object obj) {
        return delegate().removeAll(obj);
    }

    public Set<V> replaceValues(K k, Iterable<? extends V> iterable) {
        return delegate().replaceValues(k, iterable);
    }
}
