package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.Comparator;
import java.util.Iterator;

public class ImmutableSortedMultiset$Builder<E> extends ImmutableMultiset$Builder<E> {
    public ImmutableSortedMultiset$Builder(Comparator<? super E> comparator) {
        super(TreeMultiset.create((Comparator) Preconditions.checkNotNull(comparator)));
    }

    public ImmutableSortedMultiset$Builder<E> add(E e) {
        super.add((Object) e);
        return this;
    }

    public ImmutableSortedMultiset$Builder<E> addCopies(E e, int i) {
        super.addCopies(e, i);
        return this;
    }

    public ImmutableSortedMultiset$Builder<E> setCount(E e, int i) {
        super.setCount(e, i);
        return this;
    }

    public ImmutableSortedMultiset$Builder<E> add(E... eArr) {
        super.add((Object[]) eArr);
        return this;
    }

    public ImmutableSortedMultiset$Builder<E> addAll(Iterable<? extends E> iterable) {
        super.addAll((Iterable) iterable);
        return this;
    }

    public ImmutableSortedMultiset$Builder<E> addAll(Iterator<? extends E> it) {
        super.addAll((Iterator) it);
        return this;
    }

    public ImmutableSortedMultiset<E> build() {
        return ImmutableSortedMultiset.copyOfSorted((SortedMultiset) this.contents);
    }
}
