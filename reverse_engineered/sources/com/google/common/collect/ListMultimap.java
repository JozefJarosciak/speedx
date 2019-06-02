package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@GwtCompatible
public interface ListMultimap<K, V> extends Multimap<K, V> {
    Map<K, Collection<V>> asMap();

    boolean equals(Object obj);

    List<V> get(K k);

    List<V> removeAll(Object obj);

    List<V> replaceValues(K k, Iterable<? extends V> iterable);
}
