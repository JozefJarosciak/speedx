package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@GwtCompatible
abstract class AbstractListMultimap<K, V> extends AbstractMapBasedMultimap<K, V> implements ListMultimap<K, V> {
    private static final long serialVersionUID = 6588350623831699109L;

    abstract List<V> createCollection();

    protected AbstractListMultimap(Map<K, Collection<V>> map) {
        super(map);
    }

    List<V> createUnmodifiableEmptyCollection() {
        return ImmutableList.of();
    }

    public List<V> get(K k) {
        return (List) super.get(k);
    }

    public List<V> removeAll(Object obj) {
        return (List) super.removeAll(obj);
    }

    public List<V> replaceValues(K k, Iterable<? extends V> iterable) {
        return (List) super.replaceValues(k, iterable);
    }

    public boolean put(K k, V v) {
        return super.put(k, v);
    }

    public Map<K, Collection<V>> asMap() {
        return super.asMap();
    }

    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
