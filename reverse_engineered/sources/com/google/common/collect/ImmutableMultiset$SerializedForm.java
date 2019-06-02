package com.google.common.collect;

import com.google.common.collect.Multiset.Entry;
import java.io.Serializable;

class ImmutableMultiset$SerializedForm implements Serializable {
    private static final long serialVersionUID = 0;
    final int[] counts;
    final Object[] elements;

    ImmutableMultiset$SerializedForm(Multiset<?> multiset) {
        int size = multiset.entrySet().size();
        this.elements = new Object[size];
        this.counts = new int[size];
        int i = 0;
        for (Entry entry : multiset.entrySet()) {
            this.elements[i] = entry.getElement();
            this.counts[i] = entry.getCount();
            i++;
        }
    }

    Object readResolve() {
        Iterable create = LinkedHashMultiset.create(this.elements.length);
        for (int i = 0; i < this.elements.length; i++) {
            create.add(this.elements[i], this.counts[i]);
        }
        return ImmutableMultiset.copyOf(create);
    }
}
