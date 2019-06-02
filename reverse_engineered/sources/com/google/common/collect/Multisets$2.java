package com.google.common.collect;

import com.google.common.collect.Multiset.Entry;
import java.util.Iterator;
import java.util.Set;

class Multisets$2 extends AbstractMultiset<E> {
    final /* synthetic */ Multiset val$multiset1;
    final /* synthetic */ Multiset val$multiset2;

    Multisets$2(Multiset multiset, Multiset multiset2) {
        this.val$multiset1 = multiset;
        this.val$multiset2 = multiset2;
    }

    public int count(Object obj) {
        int count = this.val$multiset1.count(obj);
        return count == 0 ? 0 : Math.min(count, this.val$multiset2.count(obj));
    }

    Set<E> createElementSet() {
        return Sets.intersection(this.val$multiset1.elementSet(), this.val$multiset2.elementSet());
    }

    Iterator<Entry<E>> entryIterator() {
        final Iterator it = this.val$multiset1.entrySet().iterator();
        return new AbstractIterator<Entry<E>>() {
            protected Entry<E> computeNext() {
                while (it.hasNext()) {
                    Entry entry = (Entry) it.next();
                    Object element = entry.getElement();
                    int min = Math.min(entry.getCount(), Multisets$2.this.val$multiset2.count(element));
                    if (min > 0) {
                        return Multisets.immutableEntry(element, min);
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
