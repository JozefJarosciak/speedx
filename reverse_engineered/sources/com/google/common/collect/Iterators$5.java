package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.Iterator;
import java.util.NoSuchElementException;

class Iterators$5 implements Iterator<T> {
    Iterator<? extends T> current = Iterators.emptyIterator();
    Iterator<? extends T> removeFrom;
    final /* synthetic */ Iterator val$inputs;

    Iterators$5(Iterator it) {
        this.val$inputs = it;
    }

    public boolean hasNext() {
        boolean hasNext;
        while (true) {
            hasNext = ((Iterator) Preconditions.checkNotNull(this.current)).hasNext();
            if (hasNext || !this.val$inputs.hasNext()) {
                return hasNext;
            }
            this.current = (Iterator) this.val$inputs.next();
        }
        return hasNext;
    }

    public T next() {
        if (hasNext()) {
            this.removeFrom = this.current;
            return this.current.next();
        }
        throw new NoSuchElementException();
    }

    public void remove() {
        CollectPreconditions.checkRemove(this.removeFrom != null);
        this.removeFrom.remove();
        this.removeFrom = null;
    }
}
