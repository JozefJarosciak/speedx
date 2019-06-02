package com.google.common.collect;

import com.google.common.base.Objects;
import com.google.common.collect.Multiset.Entry;

abstract class Multisets$AbstractEntry<E> implements Entry<E> {
    Multisets$AbstractEntry() {
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Entry)) {
            return false;
        }
        Entry entry = (Entry) obj;
        if (getCount() == entry.getCount() && Objects.equal(getElement(), entry.getElement())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        Object element = getElement();
        return (element == null ? 0 : element.hashCode()) ^ getCount();
    }

    public String toString() {
        String valueOf = String.valueOf(getElement());
        int count = getCount();
        if (count == 1) {
            return valueOf;
        }
        valueOf = String.valueOf(String.valueOf(valueOf));
        return new StringBuilder(valueOf.length() + 14).append(valueOf).append(" x ").append(count).toString();
    }
}
