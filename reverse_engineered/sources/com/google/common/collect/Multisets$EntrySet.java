package com.google.common.collect;

import com.google.common.collect.Multiset.Entry;

abstract class Multisets$EntrySet<E> extends ImprovedAbstractSet<Entry<E>> {
    abstract Multiset<E> multiset();

    Multisets$EntrySet() {
    }

    public boolean contains(Object obj) {
        if (!(obj instanceof Entry)) {
            return false;
        }
        Entry entry = (Entry) obj;
        if (entry.getCount() > 0 && multiset().count(entry.getElement()) == entry.getCount()) {
            return true;
        }
        return false;
    }

    public boolean remove(Object obj) {
        if (!(obj instanceof Entry)) {
            return false;
        }
        Entry entry = (Entry) obj;
        Object element = entry.getElement();
        int count = entry.getCount();
        if (count != 0) {
            return multiset().setCount(element, count, 0);
        }
        return false;
    }

    public void clear() {
        multiset().clear();
    }
}
