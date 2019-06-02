package com.google.common.collect;

import java.util.Collections;
import java.util.List;

class Multimaps$UnmodifiableListMultimap<K, V> extends Multimaps$UnmodifiableMultimap<K, V> implements ListMultimap<K, V> {
    private static final long serialVersionUID = 0;

    Multimaps$UnmodifiableListMultimap(ListMultimap<K, V> listMultimap) {
        super(listMultimap);
    }

    public ListMultimap<K, V> delegate() {
        return (ListMultimap) super.delegate();
    }

    public List<V> get(K k) {
        return Collections.unmodifiableList(delegate().get(k));
    }

    public List<V> removeAll(Object obj) {
        throw new UnsupportedOperationException();
    }

    public List<V> replaceValues(K k, Iterable<? extends V> iterable) {
        throw new UnsupportedOperationException();
    }
}
