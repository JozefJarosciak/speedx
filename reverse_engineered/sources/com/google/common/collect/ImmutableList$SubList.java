package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.Iterator;
import java.util.ListIterator;

class ImmutableList$SubList extends ImmutableList<E> {
    final transient int length;
    final transient int offset;
    final /* synthetic */ ImmutableList this$0;

    public /* bridge */ /* synthetic */ Iterator iterator() {
        return super.iterator();
    }

    public /* bridge */ /* synthetic */ ListIterator listIterator() {
        return super.listIterator();
    }

    public /* bridge */ /* synthetic */ ListIterator listIterator(int i) {
        return super.listIterator(i);
    }

    ImmutableList$SubList(ImmutableList immutableList, int i, int i2) {
        this.this$0 = immutableList;
        this.offset = i;
        this.length = i2;
    }

    public int size() {
        return this.length;
    }

    public E get(int i) {
        Preconditions.checkElementIndex(i, this.length);
        return this.this$0.get(this.offset + i);
    }

    public ImmutableList<E> subList(int i, int i2) {
        Preconditions.checkPositionIndexes(i, i2, this.length);
        return this.this$0.subList(this.offset + i, this.offset + i2);
    }

    boolean isPartialView() {
        return true;
    }
}
