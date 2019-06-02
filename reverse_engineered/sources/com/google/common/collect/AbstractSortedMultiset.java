package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset.Entry;
import com.google.common.collect.SortedMultisets.NavigableElementSet;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;

@GwtCompatible(emulated = true)
abstract class AbstractSortedMultiset<E> extends AbstractMultiset<E> implements SortedMultiset<E> {
    @GwtTransient
    final Comparator<? super E> comparator;
    private transient SortedMultiset<E> descendingMultiset;

    abstract Iterator<Entry<E>> descendingEntryIterator();

    AbstractSortedMultiset() {
        this(Ordering.natural());
    }

    AbstractSortedMultiset(Comparator<? super E> comparator) {
        this.comparator = (Comparator) Preconditions.checkNotNull(comparator);
    }

    public NavigableSet<E> elementSet() {
        return (NavigableSet) super.elementSet();
    }

    NavigableSet<E> createElementSet() {
        return new NavigableElementSet(this);
    }

    public Comparator<? super E> comparator() {
        return this.comparator;
    }

    public Entry<E> firstEntry() {
        Iterator entryIterator = entryIterator();
        return entryIterator.hasNext() ? (Entry) entryIterator.next() : null;
    }

    public Entry<E> lastEntry() {
        Iterator descendingEntryIterator = descendingEntryIterator();
        return descendingEntryIterator.hasNext() ? (Entry) descendingEntryIterator.next() : null;
    }

    public Entry<E> pollFirstEntry() {
        Iterator entryIterator = entryIterator();
        if (!entryIterator.hasNext()) {
            return null;
        }
        Entry entry = (Entry) entryIterator.next();
        Entry<E> immutableEntry = Multisets.immutableEntry(entry.getElement(), entry.getCount());
        entryIterator.remove();
        return immutableEntry;
    }

    public Entry<E> pollLastEntry() {
        Iterator descendingEntryIterator = descendingEntryIterator();
        if (!descendingEntryIterator.hasNext()) {
            return null;
        }
        Entry entry = (Entry) descendingEntryIterator.next();
        Entry<E> immutableEntry = Multisets.immutableEntry(entry.getElement(), entry.getCount());
        descendingEntryIterator.remove();
        return immutableEntry;
    }

    public SortedMultiset<E> subMultiset(E e, BoundType boundType, E e2, BoundType boundType2) {
        Preconditions.checkNotNull(boundType);
        Preconditions.checkNotNull(boundType2);
        return tailMultiset(e, boundType).headMultiset(e2, boundType2);
    }

    Iterator<E> descendingIterator() {
        return Multisets.iteratorImpl(descendingMultiset());
    }

    public SortedMultiset<E> descendingMultiset() {
        SortedMultiset<E> sortedMultiset = this.descendingMultiset;
        if (sortedMultiset != null) {
            return sortedMultiset;
        }
        sortedMultiset = createDescendingMultiset();
        this.descendingMultiset = sortedMultiset;
        return sortedMultiset;
    }

    SortedMultiset<E> createDescendingMultiset() {
        return new AbstractSortedMultiset$1(this);
    }
}
