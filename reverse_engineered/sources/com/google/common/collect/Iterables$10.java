package com.google.common.collect;

import java.util.Iterator;

class Iterables$10 extends FluentIterable<T> {
    final /* synthetic */ Iterable val$iterable;
    final /* synthetic */ int val$numberToSkip;

    Iterables$10(Iterable iterable, int i) {
        this.val$iterable = iterable;
        this.val$numberToSkip = i;
    }

    public Iterator<T> iterator() {
        final Iterator it = this.val$iterable.iterator();
        Iterators.advance(it, this.val$numberToSkip);
        return new Iterator<T>() {
            boolean atStart = true;

            public boolean hasNext() {
                return it.hasNext();
            }

            public T next() {
                T next = it.next();
                this.atStart = false;
                return next;
            }

            public void remove() {
                CollectPreconditions.checkRemove(!this.atStart);
                it.remove();
            }
        };
    }
}
