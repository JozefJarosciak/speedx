package com.google.common.collect;

import ch.qos.logback.core.joran.action.Action;
import com.google.common.annotations.GwtCompatible;
import java.util.Map.Entry;

@GwtCompatible(emulated = true, serializable = true)
final class RegularImmutableMap<K, V> extends ImmutableMap<K, V> {
    private static final double MAX_LOAD_FACTOR = 1.2d;
    private static final long serialVersionUID = 0;
    private final transient ImmutableMapEntry<K, V>[] entries;
    private final transient int mask;
    private final transient ImmutableMapEntry<K, V>[] table;

    private class EntrySet extends ImmutableMapEntrySet<K, V> {
        private EntrySet() {
        }

        ImmutableMap<K, V> map() {
            return RegularImmutableMap.this;
        }

        public UnmodifiableIterator<Entry<K, V>> iterator() {
            return asList().iterator();
        }

        ImmutableList<Entry<K, V>> createAsList() {
            return new RegularImmutableAsList((ImmutableCollection) this, RegularImmutableMap.this.entries);
        }
    }

    private static final class NonTerminalMapEntry<K, V> extends ImmutableMapEntry<K, V> {
        private final ImmutableMapEntry<K, V> nextInKeyBucket;

        NonTerminalMapEntry(K k, V v, ImmutableMapEntry<K, V> immutableMapEntry) {
            super(k, v);
            this.nextInKeyBucket = immutableMapEntry;
        }

        NonTerminalMapEntry(ImmutableMapEntry<K, V> immutableMapEntry, ImmutableMapEntry<K, V> immutableMapEntry2) {
            super(immutableMapEntry);
            this.nextInKeyBucket = immutableMapEntry2;
        }

        ImmutableMapEntry<K, V> getNextInKeyBucket() {
            return this.nextInKeyBucket;
        }

        ImmutableMapEntry<K, V> getNextInValueBucket() {
            return null;
        }
    }

    RegularImmutableMap(TerminalEntry<?, ?>... terminalEntryArr) {
        this(terminalEntryArr.length, terminalEntryArr);
    }

    RegularImmutableMap(int i, TerminalEntry<?, ?>[] terminalEntryArr) {
        this.entries = createEntryArray(i);
        int closedTableSize = Hashing.closedTableSize(i, MAX_LOAD_FACTOR);
        this.table = createEntryArray(closedTableSize);
        this.mask = closedTableSize - 1;
        for (int i2 = 0; i2 < i; i2++) {
            ImmutableMapEntry immutableMapEntry = terminalEntryArr[i2];
            Object key = immutableMapEntry.getKey();
            int smear = this.mask & Hashing.smear(key.hashCode());
            ImmutableMapEntry immutableMapEntry2 = this.table[smear];
            if (immutableMapEntry2 != null) {
                immutableMapEntry = new NonTerminalMapEntry(immutableMapEntry, immutableMapEntry2);
            }
            this.table[smear] = immutableMapEntry;
            this.entries[i2] = immutableMapEntry;
            checkNoConflictInBucket(key, immutableMapEntry, immutableMapEntry2);
        }
    }

    RegularImmutableMap(Entry<?, ?>[] entryArr) {
        int length = entryArr.length;
        this.entries = createEntryArray(length);
        int closedTableSize = Hashing.closedTableSize(length, MAX_LOAD_FACTOR);
        this.table = createEntryArray(closedTableSize);
        this.mask = closedTableSize - 1;
        for (int i = 0; i < length; i++) {
            Entry entry = entryArr[i];
            Object key = entry.getKey();
            Object value = entry.getValue();
            CollectPreconditions.checkEntryNotNull(key, value);
            int smear = this.mask & Hashing.smear(key.hashCode());
            ImmutableMapEntry immutableMapEntry = this.table[smear];
            ImmutableMapEntry terminalEntry = immutableMapEntry == null ? new TerminalEntry(key, value) : new NonTerminalMapEntry(key, value, immutableMapEntry);
            this.table[smear] = terminalEntry;
            this.entries[i] = terminalEntry;
            checkNoConflictInBucket(key, terminalEntry, immutableMapEntry);
        }
    }

    private void checkNoConflictInBucket(K k, ImmutableMapEntry<K, V> immutableMapEntry, ImmutableMapEntry<K, V> immutableMapEntry2) {
        while (immutableMapEntry2 != null) {
            checkNoConflict(!k.equals(immutableMapEntry2.getKey()), Action.KEY_ATTRIBUTE, immutableMapEntry, immutableMapEntry2);
            immutableMapEntry2 = immutableMapEntry2.getNextInKeyBucket();
        }
    }

    private ImmutableMapEntry<K, V>[] createEntryArray(int i) {
        return new ImmutableMapEntry[i];
    }

    public V get(Object obj) {
        if (obj == null) {
            return null;
        }
        for (ImmutableMapEntry immutableMapEntry = this.table[Hashing.smear(obj.hashCode()) & this.mask]; immutableMapEntry != null; immutableMapEntry = immutableMapEntry.getNextInKeyBucket()) {
            if (obj.equals(immutableMapEntry.getKey())) {
                return immutableMapEntry.getValue();
            }
        }
        return null;
    }

    public int size() {
        return this.entries.length;
    }

    boolean isPartialView() {
        return false;
    }

    ImmutableSet<Entry<K, V>> createEntrySet() {
        return new EntrySet();
    }
}
