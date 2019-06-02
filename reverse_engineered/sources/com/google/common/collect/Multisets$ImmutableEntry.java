package com.google.common.collect;

import java.io.Serializable;

final class Multisets$ImmutableEntry<E> extends Multisets$AbstractEntry<E> implements Serializable {
    private static final long serialVersionUID = 0;
    final int count;
    final E element;

    Multisets$ImmutableEntry(E e, int i) {
        this.element = e;
        this.count = i;
        CollectPreconditions.checkNonnegative(i, "count");
    }

    public E getElement() {
        return this.element;
    }

    public int getCount() {
        return this.count;
    }
}
