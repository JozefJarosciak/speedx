package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import com.j256.ormlite.stmt.query.SimpleComparison;
import java.util.Map.Entry;

@GwtCompatible
abstract class AbstractMapEntry<K, V> implements Entry<K, V> {
    public abstract K getKey();

    public abstract V getValue();

    AbstractMapEntry() {
    }

    public V setValue(V v) {
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
