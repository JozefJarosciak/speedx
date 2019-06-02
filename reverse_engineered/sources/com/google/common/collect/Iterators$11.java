package com.google.common.collect;

class Iterators$11 extends AbstractIndexedListIterator<T> {
    final /* synthetic */ Object[] val$array;
    final /* synthetic */ int val$offset;

    Iterators$11(int i, int i2, Object[] objArr, int i3) {
        this.val$array = objArr;
        this.val$offset = i3;
        super(i, i2);
    }

    protected T get(int i) {
        return this.val$array[this.val$offset + i];
    }
}
