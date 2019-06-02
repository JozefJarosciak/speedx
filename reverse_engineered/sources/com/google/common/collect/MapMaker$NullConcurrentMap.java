package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

class MapMaker$NullConcurrentMap<K, V> extends AbstractMap<K, V> implements Serializable, ConcurrentMap<K, V> {
    private static final long serialVersionUID = 0;
    private final MapMaker$RemovalCause removalCause;
    private final MapMaker$RemovalListener<K, V> removalListener;

    MapMaker$NullConcurrentMap(MapMaker mapMaker) {
        this.removalListener = mapMaker.getRemovalListener();
        this.removalCause = mapMaker.nullRemovalCause;
    }

    public boolean containsKey(Object obj) {
        return false;
    }

    public boolean containsValue(Object obj) {
        return false;
    }

    public V get(Object obj) {
        return null;
    }

    void notifyRemoval(K k, V v) {
        this.removalListener.onRemoval(new MapMaker$RemovalNotification(k, v, this.removalCause));
    }

    public V put(K k, V v) {
        Preconditions.checkNotNull(k);
        Preconditions.checkNotNull(v);
        notifyRemoval(k, v);
        return null;
    }

    public V putIfAbsent(K k, V v) {
        return put(k, v);
    }

    public V remove(Object obj) {
        return null;
    }

    public boolean remove(Object obj, Object obj2) {
        return false;
    }

    public V replace(K k, V v) {
        Preconditions.checkNotNull(k);
        Preconditions.checkNotNull(v);
        return null;
    }

    public boolean replace(K k, V v, V v2) {
        Preconditions.checkNotNull(k);
        Preconditions.checkNotNull(v2);
        return false;
    }

    public Set<Entry<K, V>> entrySet() {
        return Collections.emptySet();
    }
}
