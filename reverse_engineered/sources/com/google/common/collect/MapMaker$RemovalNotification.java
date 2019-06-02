package com.google.common.collect;

final class MapMaker$RemovalNotification<K, V> extends ImmutableEntry<K, V> {
    private static final long serialVersionUID = 0;
    private final MapMaker$RemovalCause cause;

    MapMaker$RemovalNotification(K k, V v, MapMaker$RemovalCause mapMaker$RemovalCause) {
        super(k, v);
        this.cause = mapMaker$RemovalCause;
    }

    public MapMaker$RemovalCause getCause() {
        return this.cause;
    }

    public boolean wasEvicted() {
        return this.cause.wasEvicted();
    }
}
