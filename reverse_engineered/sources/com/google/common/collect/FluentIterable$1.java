package com.google.common.collect;

import java.util.Iterator;

class FluentIterable$1 extends FluentIterable<E> {
    final /* synthetic */ Iterable val$iterable;

    FluentIterable$1(Iterable iterable, Iterable iterable2) {
        this.val$iterable = iterable2;
        super(iterable);
    }

    public Iterator<E> iterator() {
        return this.val$iterable.iterator();
    }
}
