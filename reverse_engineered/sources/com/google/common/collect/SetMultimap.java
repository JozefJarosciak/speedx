package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

@GwtCompatible
public interface SetMultimap<K, V> extends Multimap<K, V> {
    Map<K, Collection<V>> asMap();

    Set<Entry<K, V>> entries();

    boolean equals(Object obj);

    Set<V> get(K k);

    Set<V> removeAll(Object obj);

    Set<V> replaceValues(K k, Iterable<? extends V> iterable);
}
