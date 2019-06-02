package com.google.common.collect;

import com.google.common.collect.Multiset.Entry;
import java.util.Iterator;

class Multisets$4 extends AbstractMultiset<E> {
    final /* synthetic */ Multiset val$multiset1;
    final /* synthetic */ Multiset val$multiset2;

    Multisets$4(Multiset multiset, Multiset multiset2) {
        this.val$multiset1 = multiset;
        this.val$multiset2 = multiset2;
    }

    public int count(Object obj) {
        int count = this.val$multiset1.count(obj);
        if (count == 0) {
            return 0;
        }
        return Math.max(0, count - this.val$multiset2.count(obj));
    }

    Iterator<Entry<E>> entryIterator() {
        final Iterator it = this.val$multiset1.entrySet().iterator();
        return new AbstractIterator<Entry<E>>() {
            protected Entry<E> computeNext() {
                while (it.hasNext()) {
                    Entry entry = (Entry) it.next();
                    Object element = entry.getElement();
                    int count = entry.getCount() - Multisets$4.this.val$multiset2.count(element);
                    if (count > 0) {
                        return Multisets.immutableEntry(element, count);
                    }
                }
                return (Entry) endOfData();
            }
        };
    }

    int distinctElements() {
        return Iterators.size(entryIterator());
    }
}
