package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.Comparator;
import java.util.Map.Entry;

@GwtCompatible(emulated = true)
final class EmptyImmutableSortedMap<K, V> extends ImmutableSortedMap<K, V> {
    private final transient ImmutableSortedSet<K> keySet;

    EmptyImmutableSortedMap(Comparator<? super K> comparator) {
        this.keySet = ImmutableSortedSet.emptySet(comparator);
    }

    EmptyImmutableSortedMap(Comparator<? super K> comparator, ImmutableSortedMap<K, V> immutableSortedMap) {
        super(immutableSortedMap);
        this.keySet = ImmutableSortedSet.emptySet(comparator);
    }

    public V get(Object obj) {
        return null;
    }

    public ImmutableSortedSet<K> keySet() {
        return this.keySet;
    }

    public int size() {
        return 0;
    }

    public boolean isEmpty() {
        return true;
    }

    public ImmutableCollection<V> values() {
        return ImmutableList.of();
    }

    public String toString() {
        return "{}";
    }

    boolean isPartialView() {
        return false;
    }

    public ImmutableSet<Entry<K, V>> entrySet() {
        return ImmutableSet.of();
    }

    ImmutableSet<Entry<K, V>> createEntrySet() {
        throw new AssertionError("should never be called");
    }

    public ImmutableSetMultimap<K, V> asMultimap() {
        return ImmutableSetMultimap.of();
    }

    public ImmutableSortedMap<K, V> headMap(K k, boolean z) {
        Preconditions.checkNotNull(k);
        return this;
    }

    public ImmutableSortedMap<K, V> tailMap(K k, boolean z) {
        Preconditions.checkNotNull(k);
        return this;
    }

    ImmutableSortedMap<K, V> createDescendingMap() {
        return new EmptyImmutableSortedMap(Ordering.from(comparator()).reverse(), this);
    }
}
