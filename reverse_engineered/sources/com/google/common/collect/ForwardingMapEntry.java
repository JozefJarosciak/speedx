package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import com.j256.ormlite.stmt.query.SimpleComparison;
import java.util.Map.Entry;

@GwtCompatible
public abstract class ForwardingMapEntry<K, V> extends ForwardingObject implements Entry<K, V> {
    protected abstract Entry<K, V> delegate();

    protected ForwardingMapEntry() {
    }

    public K getKey() {
        return delegate().getKey();
    }

    public V getValue() {
        return delegate().getValue();
    }

    public V setValue(V v) {
        return delegate().setValue(v);
    }

    public boolean equals(Object obj) {
        return delegate().equals(obj);
    }

    public int hashCode() {
        return delegate().hashCode();
    }

    protected boolean standardEquals(Object obj) {
        if (!(obj instanceof Entry)) {
            return false;
        }
        Entry entry = (Entry) obj;
        if (Objects.equal(getKey(), entry.getKey()) && Objects.equal(getValue(), entry.getValue())) {
            return true;
        }
        return false;
    }

    protected int standardHashCode() {
        int i = 0;
        Object key = getKey();
        Object value = getValue();
        int hashCode = key == null ? 0 : key.hashCode();
        if (value != null) {
            i = value.hashCode();
        }
        return i ^ hashCode;
    }

    @Beta
    protected String standardToString() {
        String valueOf = String.valueOf(String.valueOf(getKey()));
        String valueOf2 = String.valueOf(String.valueOf(getValue()));
        return new StringBuilder((valueOf.length() + 1) + valueOf2.length()).append(valueOf).append(SimpleComparison.EQUAL_TO_OPERATION).append(valueOf2).toString();
    }
}
