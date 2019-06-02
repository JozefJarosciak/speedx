package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Map.Entry;

@GwtCompatible(emulated = true, serializable = true)
final class SingletonImmutableBiMap<K, V> extends ImmutableBiMap<K, V> {
    transient ImmutableBiMap<V, K> inverse;
    final transient K singleKey;
    final transient V singleValue;

    SingletonImmutableBiMap(K k, V v) {
        CollectPreconditions.checkEntryNotNull(k, v);
        this.singleKey = k;
        this.singleValue = v;
    }

    private SingletonImmutableBiMap(K k, V v, ImmutableBiMap<V, K> immutableBiMap) {
        this.singleKey = k;
        this.singleValue = v;
        this.inverse = immutableBiMap;
    }

    SingletonImmutableBiMap(Entry<? extends K, ? extends V> entry) {
        this(entry.getKey(), entry.getValue());
    }

    public V get(Object obj) {
        return this.singleKey.equals(obj) ? this.singleValue : null;
    }

    public int size() {
        return 1;
    }

    public boolean containsKey(Object obj) {
        return this.singleKey.equals(obj);
    }

    public boolean containsValue(Object obj) {
        return this.singleValue.equals(obj);
    }

    boolean isPartialView() {
        return false;
    }

    ImmutableSet<Entry<K, V>> createEntrySet() {
        return ImmutableSet.of(Maps.immutableEntry(this.singleKey, this.singleValue));
    }

    ImmutableSet<K> createKeySet() {
        return ImmutableSet.of(this.singleKey);
    }

    public ImmutableBiMap<V, K> inverse() {
        ImmutableBiMap<V, K> immutableBiMap = this.inverse;
        if (immutableBiMap != null) {
            return immutableBiMap;
        }
        immutableBiMap = new SingletonImmutableBiMap(this.singleValue, this.singleKey, this);
        this.inverse = immutableBiMap;
        return immutableBiMap;
    }
}
