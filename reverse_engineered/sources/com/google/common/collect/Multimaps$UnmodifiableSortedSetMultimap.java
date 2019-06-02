package com.google.common.collect;

import java.util.Collections;
import java.util.Comparator;
import java.util.SortedSet;

class Multimaps$UnmodifiableSortedSetMultimap<K, V> extends Multimaps$UnmodifiableSetMultimap<K, V> implements SortedSetMultimap<K, V> {
    private static final long serialVersionUID = 0;

    Multimaps$UnmodifiableSortedSetMultimap(SortedSetMultimap<K, V> sortedSetMultimap) {
        super(sortedSetMultimap);
    }

    public SortedSetMultimap<K, V> delegate() {
        return (SortedSetMultimap) super.delegate();
    }

    public SortedSet<V> get(K k) {
        return Collections.unmodifiableSortedSet(delegate().get(k));
    }

    public SortedSet<V> removeAll(Object obj) {
        throw new UnsupportedOperationException();
    }

    public SortedSet<V> replaceValues(K k, Iterable<? extends V> iterable) {
        throw new UnsupportedOperationException();
    }

    public Comparator<? super V> valueComparator() {
        return delegate().valueComparator();
    }
}
