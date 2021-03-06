package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.Iterator;

class Iterators$PeekingImpl<E> implements PeekingIterator<E> {
    private boolean hasPeeked;
    private final Iterator<? extends E> iterator;
    private E peekedElement;

    public Iterators$PeekingImpl(Iterator<? extends E> it) {
        this.iterator = (Iterator) Preconditions.checkNotNull(it);
    }

    public boolean hasNext() {
        return this.hasPeeked || this.iterator.hasNext();
    }

    public E next() {
        if (!this.hasPeeked) {
            return this.iterator.next();
        }
        E e = this.peekedElement;
        this.hasPeeked = false;
        this.peekedElement = null;
        return e;
    }

    public void remove() {
        Preconditions.checkState(!this.hasPeeked, "Can't remove after you've peeked at next");
        this.iterator.remove();
    }

    public E peek() {
        if (!this.hasPeeked) {
            this.peekedElement = this.iterator.next();
            this.hasPeeked = true;
        }
        return this.peekedElement;
    }
}
