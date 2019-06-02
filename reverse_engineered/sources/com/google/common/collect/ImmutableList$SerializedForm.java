package com.google.common.collect;

import java.io.Serializable;

class ImmutableList$SerializedForm implements Serializable {
    private static final long serialVersionUID = 0;
    final Object[] elements;

    ImmutableList$SerializedForm(Object[] objArr) {
        this.elements = objArr;
    }

    Object readResolve() {
        return ImmutableList.copyOf(this.elements);
    }
}
