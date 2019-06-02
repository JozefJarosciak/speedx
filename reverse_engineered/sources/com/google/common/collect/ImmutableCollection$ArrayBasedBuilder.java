package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.Collection;

abstract class ImmutableCollection$ArrayBasedBuilder<E> extends ImmutableCollection$Builder<E> {
    Object[] contents;
    int size = 0;

    ImmutableCollection$ArrayBasedBuilder(int i) {
        CollectPreconditions.checkNonnegative(i, "initialCapacity");
        this.contents = new Object[i];
    }

    private void ensureCapacity(int i) {
        if (this.contents.length < i) {
            this.contents = ObjectArrays.arraysCopyOf(this.contents, ImmutableCollection$Builder.expandedCapacity(this.contents.length, i));
        }
    }

    public ImmutableCollection$ArrayBasedBuilder<E> add(E e) {
        Preconditions.checkNotNull(e);
        ensureCapacity(this.size + 1);
        Object[] objArr = this.contents;
        int i = this.size;
        this.size = i + 1;
        objArr[i] = e;
        return this;
    }

    public ImmutableCollection$Builder<E> add(E... eArr) {
        ObjectArrays.checkElementsNotNull(eArr);
        ensureCapacity(this.size + eArr.length);
        System.arraycopy(eArr, 0, this.contents, this.size, eArr.length);
        this.size += eArr.length;
        return this;
    }

    public ImmutableCollection$Builder<E> addAll(Iterable<? extends E> iterable) {
        if (iterable instanceof Collection) {
            Collection collection = (Collection) iterable;
            ensureCapacity(collection.size() + this.size);
        }
        super.addAll((Iterable) iterable);
        return this;
    }
}
