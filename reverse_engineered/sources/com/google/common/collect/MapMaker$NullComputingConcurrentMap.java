package com.google.common.collect;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;

final class MapMaker$NullComputingConcurrentMap<K, V> extends MapMaker$NullConcurrentMap<K, V> {
    private static final long serialVersionUID = 0;
    final Function<? super K, ? extends V> computingFunction;

    MapMaker$NullComputingConcurrentMap(MapMaker mapMaker, Function<? super K, ? extends V> function) {
        super(mapMaker);
        this.computingFunction = (Function) Preconditions.checkNotNull(function);
    }

    public V get(Object obj) {
        V compute = compute(obj);
        Preconditions.checkNotNull(compute, "%s returned null for key %s.", this.computingFunction, obj);
        notifyRemoval(obj, compute);
        return compute;
    }

    private V compute(K k) {
        Preconditions.checkNotNull(k);
        try {
            return this.computingFunction.apply(k);
        } catch (ComputationException e) {
            throw e;
        } catch (Throwable th) {
            ComputationException computationException = new ComputationException(th);
        }
    }
}
