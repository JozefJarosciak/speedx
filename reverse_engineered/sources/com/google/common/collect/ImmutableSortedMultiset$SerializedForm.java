package com.google.common.collect;

import com.google.common.collect.Multiset.Entry;
import java.io.Serializable;
import java.util.Comparator;

final class ImmutableSortedMultiset$SerializedForm<E> implements Serializable {
    Comparator<? super E> comparator;
    int[] counts;
    E[] elements;

    ImmutableSortedMultiset$SerializedForm(SortedMultiset<E> sortedMultiset) {
        this.comparator = sortedMultiset.comparator();
        int size = sortedMultiset.entrySet().size();
        this.elements = new Object[size];
        this.counts = new int[size];
        size = 0;
        for (Entry entry : sortedMultiset.entrySet()) {
            this.elements[size] = entry.getElement();
            this.counts[size] = entry.getCount();
            size++;
        }
    }

    Object readResolve() {
        int length = this.elements.length;
        ImmutableSortedMultiset$Builder immutableSortedMultiset$Builder = new ImmutableSortedMultiset$Builder(this.comparator);
        for (int i = 0; i < length; i++) {
            immutableSortedMultiset$Builder.addCopies(this.elements[i], this.counts[i]);
        }
        return immutableSortedMultiset$Builder.build();
    }
}
