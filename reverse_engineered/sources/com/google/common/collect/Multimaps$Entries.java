package com.google.common.collect;

import java.util.AbstractCollection;
import java.util.Map.Entry;

abstract class Multimaps$Entries<K, V> extends AbstractCollection<Entry<K, V>> {
    abstract Multimap<K, V> multimap();

    Multimaps$Entries() {
    }

    public int size() {
        return multimap().size();
    }

    public boolean contains(Object obj) {
        if (!(obj instanceof Entry)) {
            return false;
        }
        Entry entry = (Entry) obj;
        return multimap().containsEntry(entry.getKey(), entry.getValue());
    }

    public boolean remove(Object obj) {
        if (!(obj instanceof Entry)) {
            return false;
        }
        Entry entry = (Entry) obj;
        return multimap().remove(entry.getKey(), entry.getValue());
    }

    public void clear() {
        multimap().clear();
    }
}
