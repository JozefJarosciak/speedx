package com.google.common.collect;

class ImmutableBiMap$SerializedForm extends ImmutableMap$SerializedForm {
    private static final long serialVersionUID = 0;

    ImmutableBiMap$SerializedForm(ImmutableBiMap<?, ?> immutableBiMap) {
        super(immutableBiMap);
    }

    Object readResolve() {
        return createMap(new ImmutableBiMap$Builder());
    }
}
