package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset.Entry;
import java.util.Collection;
import java.util.Comparator;

final class EmptyImmutableSortedMultiset<E> extends ImmutableSortedMultiset<E> {
    private final ImmutableSortedSet<E> elementSet;

    EmptyImmutableSortedMultiset(Comparator<? super E> comparator) {
        this.elementSet = ImmutableSortedSet.emptySet(comparator);
    }

    public Entry<E> firstEntry() {
        return null;
    }

    public Entry<E> lastEntry() {
        return null;
    }

    public int count(Object obj) {
        return 0;
    }

    public boolean containsAll(Collection<?> collection) {
        return collection.isEmpty();
    }

    public int size() {
        return 0;
    }

    public ImmutableSortedSet<E> elementSet() {
        return this.elementSet;
    }

    Entry<E> getEntry(int i) {
        throw new AssertionError("should never be called");
    }

    public ImmutableSortedMultiset<E> headMultiset(E e, BoundType boundType) {
        Preconditions.checkNotNull(e);
        Preconditions.checkNotNull(boundType);
        return this;
    }

    public ImmutableSortedMultiset<E> tailMultiset(E e, BoundType boundType) {
        Preconditions.checkNotNull(e);
        Preconditions.checkNotNull(boundType);
        return this;
    }

    public UnmodifiableIterator<E> iterator() {
        return Iterators.emptyIterator();
    }

    public boolean equals(Object obj) {
        if (obj instanceof Multiset) {
            return ((Multiset) obj).isEmpty();
        }
        return false;
    }

    boolean isPartialView() {
        return false;
    }

    int copyIntoArray(Object[] objArr, int i) {
        return i;
    }

    public ImmutableList<E> asList() {
        return ImmutableList.of();
    }
}
