package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Map;
import java.util.Set;

@GwtCompatible
public interface BiMap<K, V> extends Map<K, V> {
    V forcePut(K k, V v);

    BiMap<V, K> inverse();

    V put(K k, V v);

    void putAll(Map<? extends K, ? extends V> map);

    Set<V> values();
}
