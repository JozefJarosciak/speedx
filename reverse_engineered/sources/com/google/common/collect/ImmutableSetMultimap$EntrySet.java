package com.google.common.collect;

import java.util.Map.Entry;

final class ImmutableSetMultimap$EntrySet<K, V> extends ImmutableSet<Entry<K, V>> {
    private final transient ImmutableSetMultimap<K, V> multimap;

    ImmutableSetMultimap$EntrySet(ImmutableSetMultimap<K, V> immutableSetMultimap) {
        this.multimap = immutableSetMultimap;
    }

    public boolean contains(Object obj) {
        if (!(obj instanceof Entry)) {
            return false;
        }
        Entry entry = (Entry) obj;
        return this.multimap.containsEntry(entry.getKey(), entry.getValue());
    }

    public int size() {
        return this.multimap.size();
    }

    public UnmodifiableIterator<Entry<K, V>> iterator() {
        return this.multimap.entryIterator();
    }

    boolean isPartialView() {
        return false;
    }
}
