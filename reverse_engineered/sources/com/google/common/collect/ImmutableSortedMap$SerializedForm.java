package com.google.common.collect;

import java.util.Comparator;

class ImmutableSortedMap$SerializedForm extends ImmutableMap$SerializedForm {
    private static final long serialVersionUID = 0;
    private final Comparator<Object> comparator;

    ImmutableSortedMap$SerializedForm(ImmutableSortedMap<?, ?> immutableSortedMap) {
        super(immutableSortedMap);
        this.comparator = immutableSortedMap.comparator();
    }

    Object readResolve() {
        return createMap(new ImmutableSortedMap$Builder(this.comparator));
    }
}
