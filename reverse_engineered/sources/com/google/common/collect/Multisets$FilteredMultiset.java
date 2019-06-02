package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Multiset.Entry;
import java.util.Iterator;
import java.util.Set;

final class Multisets$FilteredMultiset<E> extends AbstractMultiset<E> {
    final Predicate<? super E> predicate;
    final Multiset<E> unfiltered;

    /* renamed from: com.google.common.collect.Multisets$FilteredMultiset$1 */
    class C36891 implements Predicate<Entry<E>> {
        C36891() {
        }

        public boolean apply(Entry<E> entry) {
            return Multisets$FilteredMultiset.this.predicate.apply(entry.getElement());
        }
    }

    Multisets$FilteredMultiset(Multiset<E> multiset, Predicate<? super E> predicate) {
        this.unfiltered = (Multiset) Preconditions.checkNotNull(multiset);
        this.predicate = (Predicate) Preconditions.checkNotNull(predicate);
    }

    public UnmodifiableIterator<E> iterator() {
        return Iterators.filter(this.unfiltered.iterator(), this.predicate);
    }

    Set<E> createElementSet() {
        return Sets.filter(this.unfiltered.elementSet(), this.predicate);
    }

    Set<Entry<E>> createEntrySet() {
        return Sets.filter(this.unfiltered.entrySet(), new C36891());
    }

    Iterator<Entry<E>> entryIterator() {
        throw new AssertionError("should never be called");
    }

    int distinctElements() {
        return elementSet().size();
    }

    public int count(Object obj) {
        int count = this.unfiltered.count(obj);
        if (count > 0) {
            return this.predicate.apply(obj) ? count : 0;
        } else {
            return 0;
        }
    }

    public int add(E e, int i) {
        Preconditions.checkArgument(this.predicate.apply(e), "Element %s does not match predicate %s", e, this.predicate);
        return this.unfiltered.add(e, i);
    }

    public int remove(Object obj, int i) {
        CollectPreconditions.checkNonnegative(i, "occurrences");
        if (i == 0) {
            return count(obj);
        }
        return contains(obj) ? this.unfiltered.remove(obj, i) : 0;
    }

    public void clear() {
        elementSet().clear();
    }
}
