package com.google.common.collect;

import java.util.Map;
import java.util.Map.Entry;

public class ImmutableMap$Builder<K, V> {
    TerminalEntry<K, V>[] entries;
    int size;

    public ImmutableMap$Builder() {
        this(4);
    }

    ImmutableMap$Builder(int i) {
        this.entries = new TerminalEntry[i];
        this.size = 0;
    }

    private void ensureCapacity(int i) {
        if (i > this.entries.length) {
            this.entries = (TerminalEntry[]) ObjectArrays.arraysCopyOf(this.entries, ImmutableCollection$Builder.expandedCapacity(this.entries.length, i));
        }
    }

    public ImmutableMap$Builder<K, V> put(K k, V v) {
        ensureCapacity(this.size + 1);
        TerminalEntry entryOf = ImmutableMap.entryOf(k, v);
        TerminalEntry[] terminalEntryArr = this.entries;
        int i = this.size;
        this.size = i + 1;
        terminalEntryArr[i] = entryOf;
        return this;
    }

    public ImmutableMap$Builder<K, V> put(Entry<? extends K, ? extends V> entry) {
        return put(entry.getKey(), entry.getValue());
    }

    public ImmutableMap$Builder<K, V> putAll(Map<? extends K, ? extends V> map) {
        ensureCapacity(this.size + map.size());
        for (Entry put : map.entrySet()) {
            put(put);
        }
        return this;
    }

    public ImmutableMap<K, V> build() {
        switch (this.size) {
            case 0:
                return ImmutableMap.of();
            case 1:
                return ImmutableMap.of(this.entries[0].getKey(), this.entries[0].getValue());
            default:
                return new RegularImmutableMap(this.size, this.entries);
        }
    }
}
