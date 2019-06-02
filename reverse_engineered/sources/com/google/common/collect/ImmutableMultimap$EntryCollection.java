package com.google.common.collect;

import java.util.Map.Entry;

class ImmutableMultimap$EntryCollection<K, V> extends ImmutableCollection<Entry<K, V>> {
    private static final long serialVersionUID = 0;
    final ImmutableMultimap<K, V> multimap;

    ImmutableMultimap$EntryCollection(ImmutableMultimap<K, V> immutableMultimap) {
        this.multimap = immutableMultimap;
    }

    public UnmodifiableIterator<Entry<K, V>> iterator() {
        return this.multimap.entryIterator();
    }

    boolean isPartialView() {
        return this.multimap.isPartialView();
    }

    public int size() {
        return this.multimap.size();
    }

    public boolean contains(Object obj) {
        if (!(obj instanceof Entry)) {
            return false;
        }
        Entry entry = (Entry) obj;
        return this.multimap.containsEntry(entry.getKey(), entry.getValue());
    }
}
