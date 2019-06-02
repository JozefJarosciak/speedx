package com.google.common.collect;

import com.google.common.collect.Multiset.Entry;
import java.util.Collection;
import java.util.Iterator;

abstract class Multisets$ElementSet<E> extends ImprovedAbstractSet<E> {
    abstract Multiset<E> multiset();

    Multisets$ElementSet() {
    }

    public void clear() {
        multiset().clear();
    }

    public boolean contains(Object obj) {
        return multiset().contains(obj);
    }

    public boolean containsAll(Collection<?> collection) {
        return multiset().containsAll(collection);
    }

    public boolean isEmpty() {
        return multiset().isEmpty();
    }

    public Iterator<E> iterator() {
        return new TransformedIterator<Entry<E>, E>(multiset().entrySet().iterator()) {
            E transform(Entry<E> entry) {
                return entry.getElement();
            }
        };
    }

    public boolean remove(Object obj) {
        int count = multiset().count(obj);
        if (count <= 0) {
            return false;
        }
        multiset().remove(obj, count);
        return true;
    }

    public int size() {
        return multiset().entrySet().size();
    }
}
