package com.google.common.collect;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map.Entry;

class ImmutableMap$SerializedForm implements Serializable {
    private static final long serialVersionUID = 0;
    private final Object[] keys;
    private final Object[] values;

    ImmutableMap$SerializedForm(ImmutableMap<?, ?> immutableMap) {
        this.keys = new Object[immutableMap.size()];
        this.values = new Object[immutableMap.size()];
        Iterator it = immutableMap.entrySet().iterator();
        int i = 0;
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            this.keys[i] = entry.getKey();
            this.values[i] = entry.getValue();
            i++;
        }
    }

    Object readResolve() {
        return createMap(new ImmutableMap$Builder());
    }

    Object createMap(ImmutableMap$Builder<Object, Object> immutableMap$Builder) {
        for (int i = 0; i < this.keys.length; i++) {
            immutableMap$Builder.put(this.keys[i], this.values[i]);
        }
        return immutableMap$Builder.build();
    }
}
