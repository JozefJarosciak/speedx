package com.google.common.cache;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.j256.ormlite.stmt.query.SimpleComparison;
import java.util.Map.Entry;

@GwtCompatible
@Beta
public final class RemovalNotification<K, V> implements Entry<K, V> {
    private static final long serialVersionUID = 0;
    private final RemovalCause cause;
    private final K key;
    private final V value;

    RemovalNotification(K k, V v, RemovalCause removalCause) {
        this.key = k;
        this.value = v;
        this.cause = (RemovalCause) Preconditions.checkNotNull(removalCause);
    }

    public RemovalCause getCause() {
        return this.cause;
    }

    public boolean wasEvicted() {
        return this.cause.wasEvicted();
    }

    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return this.value;
    }

    public final V setValue(V v) {
        throw new UnsupportedOperationException();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Entry)) {
            return false;
        }
        Entry entry = (Entry) obj;
        if (Objects.equal(getKey(), entry.getKey()) && Objects.equal(getValue(), entry.getValue())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        Object key = getKey();
        Object value = getValue();
        int hashCode = key == null ? 0 : key.hashCode();
        if (value != null) {
            i = value.hashCode();
        }
        return i ^ hashCode;
    }

    public String toString() {
        String valueOf = String.valueOf(String.valueOf(getKey()));
        String valueOf2 = String.valueOf(String.valueOf(getValue()));
        return new StringBuilder((valueOf.length() + 1) + valueOf2.length()).append(valueOf).append(SimpleComparison.EQUAL_TO_OPERATION).append(valueOf2).toString();
    }
}
