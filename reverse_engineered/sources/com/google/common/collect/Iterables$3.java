package com.google.common.collect;

import java.util.Iterator;

class Iterables$3 extends TransformedIterator<Iterable<? extends T>, Iterator<? extends T>> {
    Iterables$3(Iterator it) {
        super(it);
    }

    Iterator<? extends T> transform(Iterable<? extends T> iterable) {
        return iterable.iterator();
    }
}
