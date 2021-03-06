package com.google.common.collect;

import com.google.common.base.Function;
import com.google.common.base.Throwables;
import java.io.Serializable;
import java.util.concurrent.ExecutionException;

final class MapMaker$ComputingMapAdapter<K, V> extends ComputingConcurrentHashMap<K, V> implements Serializable {
    private static final long serialVersionUID = 0;

    MapMaker$ComputingMapAdapter(MapMaker mapMaker, Function<? super K, ? extends V> function) {
        super(mapMaker, function);
    }

    public V get(Object obj) {
        try {
            V orCompute = getOrCompute(obj);
            if (orCompute != null) {
                return orCompute;
            }
            String valueOf = String.valueOf(String.valueOf(this.computingFunction));
            String valueOf2 = String.valueOf(String.valueOf(obj));
            throw new NullPointerException(new StringBuilder((valueOf.length() + 24) + valueOf2.length()).append(valueOf).append(" returned null for key ").append(valueOf2).append(".").toString());
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            Throwables.propagateIfInstanceOf(cause, ComputationException.class);
            throw new ComputationException(cause);
        }
    }
}
