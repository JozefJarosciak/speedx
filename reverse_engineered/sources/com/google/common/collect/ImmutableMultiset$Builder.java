package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset.Entry;
import java.util.Iterator;

public class ImmutableMultiset$Builder<E> extends ImmutableCollection$Builder<E> {
    final Multiset<E> contents;

    public ImmutableMultiset$Builder() {
        this(LinkedHashMultiset.create());
    }

    ImmutableMultiset$Builder(Multiset<E> multiset) {
        this.contents = multiset;
    }

    public ImmutableMultiset$Builder<E> add(E e) {
        this.contents.add(Preconditions.checkNotNull(e));
        return this;
    }

    public ImmutableMultiset$Builder<E> addCopies(E e, int i) {
        this.contents.add(Preconditions.checkNotNull(e), i);
        return this;
    }

    public ImmutableMultiset$Builder<E> setCount(E e, int i) {
        this.contents.setCount(Preconditions.checkNotNull(e), i);
        return this;
    }

    public ImmutableMultiset$Builder<E> add(E... eArr) {
        super.add((Object[]) eArr);
        return this;
    }

    public ImmutableMultiset$Builder<E> addAll(Iterable<? extends E> iterable) {
        if (iterable instanceof Multiset) {
            for (Entry entry : Multisets.cast(iterable).entrySet()) {
                addCopies(entry.getElement(), entry.getCount());
            }
        } else {
            super.addAll((Iterable) iterable);
        }
        return this;
    }

    public ImmutableMultiset$Builder<E> addAll(Iterator<? extends E> it) {
        super.addAll((Iterator) it);
        return this;
    }

    public ImmutableMultiset<E> build() {
        return ImmutableMultiset.copyOf(this.contents);
    }
}
