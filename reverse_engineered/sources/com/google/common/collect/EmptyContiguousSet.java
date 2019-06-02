package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import java.io.Serializable;
import java.util.NoSuchElementException;
import java.util.Set;

@GwtCompatible(emulated = true)
final class EmptyContiguousSet<C extends Comparable> extends ContiguousSet<C> {

    @GwtIncompatible("serialization")
    private static final class SerializedForm<C extends Comparable> implements Serializable {
        private static final long serialVersionUID = 0;
        private final DiscreteDomain<C> domain;

        private SerializedForm(DiscreteDomain<C> discreteDomain) {
            this.domain = discreteDomain;
        }

        private Object readResolve() {
            return new EmptyContiguousSet(this.domain);
        }
    }

    EmptyContiguousSet(DiscreteDomain<C> discreteDomain) {
        super(discreteDomain);
    }

    public C first() {
        throw new NoSuchElementException();
    }

    public C last() {
        throw new NoSuchElementException();
    }

    public int size() {
        return 0;
    }

    public ContiguousSet<C> intersection(ContiguousSet<C> contiguousSet) {
        return this;
    }

    public Range<C> range() {
        throw new NoSuchElementException();
    }

    public Range<C> range(BoundType boundType, BoundType boundType2) {
        throw new NoSuchElementException();
    }

    ContiguousSet<C> headSetImpl(C c, boolean z) {
        return this;
    }

    ContiguousSet<C> subSetImpl(C c, boolean z, C c2, boolean z2) {
        return this;
    }

    ContiguousSet<C> tailSetImpl(C c, boolean z) {
        return this;
    }

    @GwtIncompatible("not used by GWT emulation")
    int indexOf(Object obj) {
        return -1;
    }

    public UnmodifiableIterator<C> iterator() {
        return Iterators.emptyIterator();
    }

    @GwtIncompatible("NavigableSet")
    public UnmodifiableIterator<C> descendingIterator() {
        return Iterators.emptyIterator();
    }

    boolean isPartialView() {
        return false;
    }

    public boolean isEmpty() {
        return true;
    }

    public ImmutableList<C> asList() {
        return ImmutableList.of();
    }

    public String toString() {
        return "[]";
    }

    public boolean equals(Object obj) {
        if (obj instanceof Set) {
            return ((Set) obj).isEmpty();
        }
        return false;
    }

    public int hashCode() {
        return 0;
    }

    @GwtIncompatible("serialization")
    Object writeReplace() {
        return new SerializedForm(this.domain);
    }

    @GwtIncompatible("NavigableSet")
    ImmutableSortedSet<C> createDescendingSet() {
        return new EmptyImmutableSortedSet(Ordering.natural().reverse());
    }
}
