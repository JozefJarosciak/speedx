package com.google.common.collect;

import java.io.Serializable;

class ImmutableMultiset$EntrySetSerializedForm<E> implements Serializable {
    final ImmutableMultiset<E> multiset;

    ImmutableMultiset$EntrySetSerializedForm(ImmutableMultiset<E> immutableMultiset) {
        this.multiset = immutableMultiset;
    }

    Object readResolve() {
        return this.multiset.entrySet();
    }
}
