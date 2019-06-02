package com.google.common.collect;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;

class Iterators$MergingIterator<T> extends UnmodifiableIterator<T> {
    final Queue<PeekingIterator<T>> queue;

    public Iterators$MergingIterator(Iterable<? extends Iterator<? extends T>> iterable, final Comparator<? super T> comparator) {
        this.queue = new PriorityQueue(2, new Comparator<PeekingIterator<T>>() {
            public int compare(PeekingIterator<T> peekingIterator, PeekingIterator<T> peekingIterator2) {
                return comparator.compare(peekingIterator.peek(), peekingIterator2.peek());
            }
        });
        for (Iterator it : iterable) {
            if (it.hasNext()) {
                this.queue.add(Iterators.peekingIterator(it));
            }
        }
    }

    public boolean hasNext() {
        return !this.queue.isEmpty();
    }

    public T next() {
        PeekingIterator peekingIterator = (PeekingIterator) this.queue.remove();
        T next = peekingIterator.next();
        if (peekingIterator.hasNext()) {
            this.queue.add(peekingIterator);
        }
        return next;
    }
}
