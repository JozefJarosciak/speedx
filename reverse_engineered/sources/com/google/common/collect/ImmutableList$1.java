package com.google.common.collect;

class ImmutableList$1 extends AbstractIndexedListIterator<E> {
    final /* synthetic */ ImmutableList this$0;

    ImmutableList$1(ImmutableList immutableList, int i, int i2) {
        this.this$0 = immutableList;
        super(i, i2);
    }

    protected E get(int i) {
        return this.this$0.get(i);
    }
}
