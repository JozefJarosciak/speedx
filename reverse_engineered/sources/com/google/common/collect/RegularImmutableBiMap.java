package com.google.common.collect;

import ch.qos.logback.core.joran.action.Action;
import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import java.util.Map.Entry;

@GwtCompatible(emulated = true, serializable = true)
class RegularImmutableBiMap<K, V> extends ImmutableBiMap<K, V> {
    static final double MAX_LOAD_FACTOR = 1.2d;
    private final transient ImmutableMapEntry<K, V>[] entries;
    private final transient int hashCode;
    private transient ImmutableBiMap<V, K> inverse;
    private final transient ImmutableMapEntry<K, V>[] keyTable;
    private final transient int mask;
    private final transient ImmutableMapEntry<K, V>[] valueTable;

    /* renamed from: com.google.common.collect.RegularImmutableBiMap$1 */
    class C36941 extends ImmutableMapEntrySet<K, V> {
        C36941() {
        }

        ImmutableMap<K, V> map() {
            return RegularImmutableBiMap.this;
        }

        public UnmodifiableIterator<Entry<K, V>> iterator() {
            return asList().iterator();
        }

        ImmutableList<Entry<K, V>> createAsList() {
            return new RegularImmutableAsList((ImmutableCollection) this, RegularImmutableBiMap.this.entries);
        }

        boolean isHashCodeFast() {
            return true;
        }

        public int hashCode() {
            return RegularImmutableBiMap.this.hashCode;
        }
    }

    private final class Inverse extends ImmutableBiMap<V, K> {

        final class InverseEntrySet extends ImmutableMapEntrySet<V, K> {

            /* renamed from: com.google.common.collect.RegularImmutableBiMap$Inverse$InverseEntrySet$1 */
            class C36951 extends ImmutableAsList<Entry<V, K>> {
                C36951() {
                }

                public Entry<V, K> get(int i) {
                    Entry entry = RegularImmutableBiMap.this.entries[i];
                    return Maps.immutableEntry(entry.getValue(), entry.getKey());
                }

                ImmutableCollection<Entry<V, K>> delegateCollection() {
                    return InverseEntrySet.this;
                }
            }

            InverseEntrySet() {
            }

            ImmutableMap<V, K> map() {
                return Inverse.this;
            }

            boolean isHashCodeFast() {
                return true;
            }

            public int hashCode() {
                return RegularImmutableBiMap.this.hashCode;
            }

            public UnmodifiableIterator<Entry<V, K>> iterator() {
                return asList().iterator();
            }

            ImmutableList<Entry<V, K>> createAsList() {
                return new C36951();
            }
        }

        private Inverse() {
        }

        public int size() {
            return inverse().size();
        }

        public ImmutableBiMap<K, V> inverse() {
            return RegularImmutableBiMap.this;
        }

        public K get(Object obj) {
            if (obj == null) {
                return null;
            }
            for (ImmutableMapEntry immutableMapEntry = RegularImmutableBiMap.this.valueTable[Hashing.smear(obj.hashCode()) & RegularImmutableBiMap.this.mask]; immutableMapEntry != null; immutableMapEntry = immutableMapEntry.getNextInValueBucket()) {
                if (obj.equals(immutableMapEntry.getValue())) {
                    return immutableMapEntry.getKey();
                }
            }
            return null;
        }

        ImmutableSet<Entry<V, K>> createEntrySet() {
            return new InverseEntrySet();
        }

        boolean isPartialView() {
            return false;
        }

        Object writeReplace() {
            return new InverseSerializedForm(RegularImmutableBiMap.this);
        }
    }

    private static class InverseSerializedForm<K, V> implements Serializable {
        private static final long serialVersionUID = 1;
        private final ImmutableBiMap<K, V> forward;

        InverseSerializedForm(ImmutableBiMap<K, V> immutableBiMap) {
            this.forward = immutableBiMap;
        }

        Object readResolve() {
            return this.forward.inverse();
        }
    }

    private static final class NonTerminalBiMapEntry<K, V> extends ImmutableMapEntry<K, V> {
        private final ImmutableMapEntry<K, V> nextInKeyBucket;
        private final ImmutableMapEntry<K, V> nextInValueBucket;

        NonTerminalBiMapEntry(K k, V v, ImmutableMapEntry<K, V> immutableMapEntry, ImmutableMapEntry<K, V> immutableMapEntry2) {
            super(k, v);
            this.nextInKeyBucket = immutableMapEntry;
            this.nextInValueBucket = immutableMapEntry2;
        }

        NonTerminalBiMapEntry(ImmutableMapEntry<K, V> immutableMapEntry, ImmutableMapEntry<K, V> immutableMapEntry2, ImmutableMapEntry<K, V> immutableMapEntry3) {
            super(immutableMapEntry);
            this.nextInKeyBucket = immutableMapEntry2;
            this.nextInValueBucket = immutableMapEntry3;
        }

        ImmutableMapEntry<K, V> getNextInKeyBucket() {
            return this.nextInKeyBucket;
        }

        ImmutableMapEntry<K, V> getNextInValueBucket() {
            return this.nextInValueBucket;
        }
    }

    RegularImmutableBiMap(TerminalEntry<?, ?>... terminalEntryArr) {
        this(terminalEntryArr.length, terminalEntryArr);
    }

    RegularImmutableBiMap(int i, TerminalEntry<?, ?>[] terminalEntryArr) {
        int closedTableSize = Hashing.closedTableSize(i, MAX_LOAD_FACTOR);
        this.mask = closedTableSize - 1;
        ImmutableMapEntry[] createEntryArray = createEntryArray(closedTableSize);
        ImmutableMapEntry[] createEntryArray2 = createEntryArray(closedTableSize);
        ImmutableMapEntry[] createEntryArray3 = createEntryArray(i);
        int i2 = 0;
        int i3 = 0;
        while (i2 < i) {
            Object obj;
            ImmutableMapEntry nextInKeyBucket;
            NonTerminalBiMapEntry nonTerminalBiMapEntry = terminalEntryArr[i2];
            Object key = nonTerminalBiMapEntry.getKey();
            Object value = nonTerminalBiMapEntry.getValue();
            int hashCode = key.hashCode();
            int hashCode2 = value.hashCode();
            int smear = Hashing.smear(hashCode) & this.mask;
            int smear2 = Hashing.smear(hashCode2) & this.mask;
            ImmutableMapEntry immutableMapEntry = createEntryArray[smear];
            for (obj = immutableMapEntry; obj != null; nextInKeyBucket = obj.getNextInKeyBucket()) {
                checkNoConflict(!key.equals(obj.getKey()), Action.KEY_ATTRIBUTE, nonTerminalBiMapEntry, obj);
            }
            ImmutableMapEntry immutableMapEntry2 = createEntryArray2[smear2];
            for (obj = immutableMapEntry2; obj != null; nextInKeyBucket = obj.getNextInValueBucket()) {
                checkNoConflict(!value.equals(obj.getValue()), "value", nonTerminalBiMapEntry, obj);
            }
            NonTerminalBiMapEntry nonTerminalBiMapEntry2 = (immutableMapEntry == null && immutableMapEntry2 == null) ? nonTerminalBiMapEntry : new NonTerminalBiMapEntry(nonTerminalBiMapEntry, immutableMapEntry, immutableMapEntry2);
            createEntryArray[smear] = nonTerminalBiMapEntry2;
            createEntryArray2[smear2] = nonTerminalBiMapEntry2;
            createEntryArray3[i2] = nonTerminalBiMapEntry2;
            i2++;
            i3 += hashCode ^ hashCode2;
        }
        this.keyTable = createEntryArray;
        this.valueTable = createEntryArray2;
        this.entries = createEntryArray3;
        this.hashCode = i3;
    }

    RegularImmutableBiMap(Entry<?, ?>[] entryArr) {
        int length = entryArr.length;
        int closedTableSize = Hashing.closedTableSize(length, MAX_LOAD_FACTOR);
        this.mask = closedTableSize - 1;
        ImmutableMapEntry[] createEntryArray = createEntryArray(closedTableSize);
        ImmutableMapEntry[] createEntryArray2 = createEntryArray(closedTableSize);
        ImmutableMapEntry[] createEntryArray3 = createEntryArray(length);
        int i = 0;
        int i2 = 0;
        while (i < length) {
            Object obj;
            ImmutableMapEntry nextInKeyBucket;
            Entry entry = entryArr[i];
            Object key = entry.getKey();
            Object value = entry.getValue();
            CollectPreconditions.checkEntryNotNull(key, value);
            int hashCode = key.hashCode();
            int hashCode2 = value.hashCode();
            int smear = Hashing.smear(hashCode) & this.mask;
            int smear2 = Hashing.smear(hashCode2) & this.mask;
            ImmutableMapEntry immutableMapEntry = createEntryArray[smear];
            for (obj = immutableMapEntry; obj != null; nextInKeyBucket = obj.getNextInKeyBucket()) {
                checkNoConflict(!key.equals(obj.getKey()), Action.KEY_ATTRIBUTE, entry, obj);
            }
            ImmutableMapEntry immutableMapEntry2 = createEntryArray2[smear2];
            for (obj = immutableMapEntry2; obj != null; nextInKeyBucket = obj.getNextInValueBucket()) {
                checkNoConflict(!value.equals(obj.getValue()), "value", entry, obj);
            }
            TerminalEntry terminalEntry = (immutableMapEntry == null && immutableMapEntry2 == null) ? new TerminalEntry(key, value) : new NonTerminalBiMapEntry(key, value, immutableMapEntry, immutableMapEntry2);
            createEntryArray[smear] = terminalEntry;
            createEntryArray2[smear2] = terminalEntry;
            createEntryArray3[i] = terminalEntry;
            i++;
            i2 += hashCode ^ hashCode2;
        }
        this.keyTable = createEntryArray;
        this.valueTable = createEntryArray2;
        this.entries = createEntryArray3;
        this.hashCode = i2;
    }

    private static <K, V> ImmutableMapEntry<K, V>[] createEntryArray(int i) {
        return new ImmutableMapEntry[i];
    }

    public V get(Object obj) {
        if (obj == null) {
            return null;
        }
        for (ImmutableMapEntry immutableMapEntry = this.keyTable[Hashing.smear(obj.hashCode()) & this.mask]; immutableMapEntry != null; immutableMapEntry = immutableMapEntry.getNextInKeyBucket()) {
            if (obj.equals(immutableMapEntry.getKey())) {
                return immutableMapEntry.getValue();
            }
        }
        return null;
    }

    ImmutableSet<Entry<K, V>> createEntrySet() {
        return new C36941();
    }

    boolean isPartialView() {
        return false;
    }

    public int size() {
        return this.entries.length;
    }

    public ImmutableBiMap<V, K> inverse() {
        ImmutableBiMap<V, K> immutableBiMap = this.inverse;
        if (immutableBiMap != null) {
            return immutableBiMap;
        }
        immutableBiMap = new Inverse();
        this.inverse = immutableBiMap;
        return immutableBiMap;
    }
}
