package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.Iterator;
import java.util.ListIterator;

class ImmutableList$ReverseImmutableList<E> extends ImmutableList<E> {
    private final transient ImmutableList<E> forwardList;

    public /* bridge */ /* synthetic */ Iterator iterator() {
        return super.iterator();
    }

    public /* bridge */ /* synthetic */ ListIterator listIterator() {
        return super.listIterator();
    }

    public /* bridge */ /* synthetic */ ListIterator listIterator(int i) {
        return super.listIterator(i);
    }

    ImmutableList$ReverseImmutableList(ImmutableList<E> immutableList) {
        this.forwardList = immutableList;
    }

    private int reverseIndex(int i) {
        return (size() - 1) - i;
    }

    private int reversePosition(int i) {
        return size() - i;
    }

    public ImmutableList<E> reverse() {
        return this.forwardList;
    }

    public boolean contains(Object obj) {
        return this.forwardList.contains(obj);
    }

    public int indexOf(Object obj) {
        int lastIndexOf = this.forwardList.lastIndexOf(obj);
        return lastIndexOf >= 0 ? reverseIndex(lastIndexOf) : -1;
    }

    public int lastIndexOf(Object obj) {
        int indexOf = this.forwardList.indexOf(obj);
        return indexOf >= 0 ? reverseIndex(indexOf) : -1;
    }

    public ImmutableList<E> subList(int i, int i2) {
        Preconditions.checkPositionIndexes(i, i2, size());
        return this.forwardList.subList(reversePosition(i2), reversePosition(i)).reverse();
    }

    public E get(int i) {
        Preconditions.checkElementIndex(i, size());
        return this.forwardList.get(reverseIndex(i));
    }

    public int size() {
        return this.forwardList.size();
    }

    boolean isPartialView() {
        return this.forwardList.isPartialView();
    }
}
