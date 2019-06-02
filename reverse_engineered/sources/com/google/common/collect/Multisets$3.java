package com.google.common.collect;

import com.google.common.collect.Multiset.Entry;
import java.util.Iterator;
import java.util.Set;

class Multisets$3 extends AbstractMultiset<E> {
    final /* synthetic */ Multiset val$multiset1;
    final /* synthetic */ Multiset val$multiset2;

    Multisets$3(Multiset multiset, Multiset multiset2) {
        this.val$multiset1 = multiset;
        this.val$multiset2 = multiset2;
    }

    public boolean contains(Object obj) {
        return this.val$multiset1.contains(obj) || this.val$multiset2.contains(obj);
    }

    public boolean isEmpty() {
        return this.val$multiset1.isEmpty() && this.val$multiset2.isEmpty();
    }

    public int size() {
        return this.val$multiset1.size() + this.val$multiset2.size();
    }

    public int count(Object obj) {
        return this.val$multiset1.count(obj) + this.val$multiset2.count(obj);
    }

    Set<E> createElementSet() {
        return Sets.union(this.val$multiset1.elementSet(), this.val$multiset2.elementSet());
    }

    Iterator<Entry<E>> entryIterator() {
        final Iterator it = this.val$multiset1.entrySet().iterator();
        final Iterator it2 = this.val$multiset2.entrySet().iterator();
        return new AbstractIterator<Entry<E>>() {
            protected Entry<E> computeNext() {
                if (it.hasNext()) {
                    Entry entry = (Entry) it.next();
                    Object element = entry.getElement();
                    return Multisets.immutableEntry(element, entry.getCount() + Multisets$3.this.val$multiset2.count(element));
                }
                while (it2.hasNext()) {
                    entry = (Entry) it2.next();
                    element = entry.getElement();
                    if (!Multisets$3.this.val$multiset1.contains(element)) {
                        return Multisets.immutableEntry(element, entry.getCount());
                    }
                }
                return (Entry) endOfData();
            }
        };
    }

    int distinctElements() {
        return elementSet().size();
    }
}
