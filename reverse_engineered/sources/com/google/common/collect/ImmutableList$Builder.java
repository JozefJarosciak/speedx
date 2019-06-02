package com.google.common.collect;

import java.util.Iterator;

public final class ImmutableList$Builder<E> extends ImmutableCollection$ArrayBasedBuilder<E> {
    public ImmutableList$Builder() {
        this(4);
    }

    ImmutableList$Builder(int i) {
        super(i);
    }

    public ImmutableList$Builder<E> add(E e) {
        super.add((Object) e);
        return this;
    }

    public ImmutableList$Builder<E> addAll(Iterable<? extends E> iterable) {
        super.addAll(iterable);
        return this;
    }

    public ImmutableList$Builder<E> add(E... eArr) {
        super.add((Object[]) eArr);
        return this;
    }

    public ImmutableList$Builder<E> addAll(Iterator<? extends E> it) {
        super.addAll((Iterator) it);
        return this;
    }

    public ImmutableList<E> build() {
        return ImmutableList.asImmutableList(this.contents, this.size);
    }
}
