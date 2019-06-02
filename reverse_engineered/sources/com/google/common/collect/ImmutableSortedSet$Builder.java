package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet.Builder;
import java.util.Comparator;
import java.util.Iterator;

public final class ImmutableSortedSet$Builder<E> extends Builder<E> {
    private final Comparator<? super E> comparator;

    public ImmutableSortedSet$Builder(Comparator<? super E> comparator) {
        this.comparator = (Comparator) Preconditions.checkNotNull(comparator);
    }

    public ImmutableSortedSet$Builder<E> add(E e) {
        super.add((Object) e);
        return this;
    }

    public ImmutableSortedSet$Builder<E> add(E... eArr) {
        super.add((Object[]) eArr);
        return this;
    }

    public ImmutableSortedSet$Builder<E> addAll(Iterable<? extends E> iterable) {
        super.addAll((Iterable) iterable);
        return this;
    }

    public ImmutableSortedSet$Builder<E> addAll(Iterator<? extends E> it) {
        super.addAll((Iterator) it);
        return this;
    }

    public ImmutableSortedSet<E> build() {
        ImmutableSortedSet<E> construct = ImmutableSortedSet.construct(this.comparator, this.size, this.contents);
        this.size = construct.size();
        return construct;
    }
}
