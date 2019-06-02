package com.google.common.collect;

import java.util.Comparator;
import java.util.Map.Entry;

public final class ImmutableListMultimap$Builder<K, V> extends ImmutableMultimap$Builder<K, V> {
    public ImmutableListMultimap$Builder<K, V> put(K k, V v) {
        super.put(k, v);
        return this;
    }

    public ImmutableListMultimap$Builder<K, V> put(Entry<? extends K, ? extends V> entry) {
        super.put(entry);
        return this;
    }

    public ImmutableListMultimap$Builder<K, V> putAll(K k, Iterable<? extends V> iterable) {
        super.putAll((Object) k, (Iterable) iterable);
        return this;
    }

    public ImmutableListMultimap$Builder<K, V> putAll(K k, V... vArr) {
        super.putAll((Object) k, (Object[]) vArr);
        return this;
    }

    public ImmutableListMultimap$Builder<K, V> putAll(Multimap<? extends K, ? extends V> multimap) {
        super.putAll(multimap);
        return this;
    }

    public ImmutableListMultimap$Builder<K, V> orderKeysBy(Comparator<? super K> comparator) {
        super.orderKeysBy(comparator);
        return this;
    }

    public ImmutableListMultimap$Builder<K, V> orderValuesBy(Comparator<? super V> comparator) {
        super.orderValuesBy(comparator);
        return this;
    }

    public ImmutableListMultimap<K, V> build() {
        return (ImmutableListMultimap) super.build();
    }
}
