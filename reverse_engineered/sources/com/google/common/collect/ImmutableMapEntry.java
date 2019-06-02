package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;

@GwtIncompatible("unnecessary")
abstract class ImmutableMapEntry<K, V> extends ImmutableEntry<K, V> {

    static final class TerminalEntry<K, V> extends ImmutableMapEntry<K, V> {
        TerminalEntry(ImmutableMapEntry<K, V> immutableMapEntry) {
            super(immutableMapEntry);
        }

        TerminalEntry(K k, V v) {
            super(k, v);
        }

        ImmutableMapEntry<K, V> getNextInKeyBucket() {
            return null;
        }

        ImmutableMapEntry<K, V> getNextInValueBucket() {
            return null;
        }
    }

    abstract ImmutableMapEntry<K, V> getNextInKeyBucket();

    abstract ImmutableMapEntry<K, V> getNextInValueBucket();

    ImmutableMapEntry(K k, V v) {
        super(k, v);
        CollectPreconditions.checkEntryNotNull(k, v);
    }

    ImmutableMapEntry(ImmutableMapEntry<K, V> immutableMapEntry) {
        super(immutableMapEntry.getKey(), immutableMapEntry.getValue());
    }
}
