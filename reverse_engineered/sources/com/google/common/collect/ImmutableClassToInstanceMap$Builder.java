package com.google.common.collect;

import com.google.common.primitives.Primitives;
import java.util.Map;
import java.util.Map.Entry;

public final class ImmutableClassToInstanceMap$Builder<B> {
    private final ImmutableMap$Builder<Class<? extends B>, B> mapBuilder = ImmutableMap.builder();

    public <T extends B> ImmutableClassToInstanceMap$Builder<B> put(Class<T> cls, T t) {
        this.mapBuilder.put(cls, t);
        return this;
    }

    public <T extends B> ImmutableClassToInstanceMap$Builder<B> putAll(Map<? extends Class<? extends T>, ? extends T> map) {
        for (Entry entry : map.entrySet()) {
            Class cls = (Class) entry.getKey();
            this.mapBuilder.put(cls, cast(cls, entry.getValue()));
        }
        return this;
    }

    private static <B, T extends B> T cast(Class<T> cls, B b) {
        return Primitives.wrap(cls).cast(b);
    }

    public ImmutableClassToInstanceMap<B> build() {
        return new ImmutableClassToInstanceMap(this.mapBuilder.build(), null);
    }
}
