package com.google.common.collect;

import java.util.Iterator;

public abstract class ImmutableCollection$Builder<E> {
    static final int DEFAULT_INITIAL_CAPACITY = 4;

    public abstract ImmutableCollection$Builder<E> add(E e);

    public abstract ImmutableCollection<E> build();

    static int expandedCapacity(int i, int i2) {
        if (i2 < 0) {
            throw new AssertionError("cannot store more than MAX_VALUE elements");
        }
        int i3 = ((i >> 1) + i) + 1;
        if (i3 < i2) {
            i3 = Integer.highestOneBit(i2 - 1) << 1;
        }
        if (i3 < 0) {
            return Integer.MAX_VALUE;
        }
        return i3;
    }

    ImmutableCollection$Builder() {
    }

    public ImmutableCollection$Builder<E> add(E... eArr) {
        for (Object add : eArr) {
            add(add);
        }
        return this;
    }

    public ImmutableCollection$Builder<E> addAll(Iterable<? extends E> iterable) {
        for (Object add : iterable) {
            add(add);
        }
        return this;
    }

    public ImmutableCollection$Builder<E> addAll(Iterator<? extends E> it) {
        while (it.hasNext()) {
            add(it.next());
        }
        return this;
    }
}
