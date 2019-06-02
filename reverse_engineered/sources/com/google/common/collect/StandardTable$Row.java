package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class StandardTable$Row extends ImprovedAbstractMap<C, V> {
    Map<C, V> backingRowMap;
    final R rowKey;
    final /* synthetic */ StandardTable this$0;

    /* renamed from: com.google.common.collect.StandardTable$Row$RowEntrySet */
    private final class RowEntrySet extends EntrySet<C, V> {
        private RowEntrySet() {
        }

        Map<C, V> map() {
            return StandardTable$Row.this;
        }

        public int size() {
            Map backingRowMap = StandardTable$Row.this.backingRowMap();
            return backingRowMap == null ? 0 : backingRowMap.size();
        }

        public Iterator<Entry<C, V>> iterator() {
            Map backingRowMap = StandardTable$Row.this.backingRowMap();
            if (backingRowMap == null) {
                return Iterators.emptyModifiableIterator();
            }
            final Iterator it = backingRowMap.entrySet().iterator();
            return new Iterator<Entry<C, V>>() {
                public boolean hasNext() {
                    return it.hasNext();
                }

                public Entry<C, V> next() {
                    final Entry entry = (Entry) it.next();
                    return new ForwardingMapEntry<C, V>() {
                        protected Entry<C, V> delegate() {
                            return entry;
                        }

                        public V setValue(V v) {
                            return super.setValue(Preconditions.checkNotNull(v));
                        }

                        public boolean equals(Object obj) {
                            return standardEquals(obj);
                        }
                    };
                }

                public void remove() {
                    it.remove();
                    StandardTable$Row.this.maintainEmptyInvariant();
                }
            };
        }
    }

    StandardTable$Row(StandardTable standardTable, R r) {
        this.this$0 = standardTable;
        this.rowKey = Preconditions.checkNotNull(r);
    }

    Map<C, V> backingRowMap() {
        if (this.backingRowMap != null && (!this.backingRowMap.isEmpty() || !this.this$0.backingMap.containsKey(this.rowKey))) {
            return this.backingRowMap;
        }
        Map<C, V> computeBackingRowMap = computeBackingRowMap();
        this.backingRowMap = computeBackingRowMap;
        return computeBackingRowMap;
    }

    Map<C, V> computeBackingRowMap() {
        return (Map) this.this$0.backingMap.get(this.rowKey);
    }

    void maintainEmptyInvariant() {
        if (backingRowMap() != null && this.backingRowMap.isEmpty()) {
            this.this$0.backingMap.remove(this.rowKey);
            this.backingRowMap = null;
        }
    }

    public boolean containsKey(Object obj) {
        Map backingRowMap = backingRowMap();
        return (obj == null || backingRowMap == null || !Maps.safeContainsKey(backingRowMap, obj)) ? false : true;
    }

    public V get(Object obj) {
        Map backingRowMap = backingRowMap();
        return (obj == null || backingRowMap == null) ? null : Maps.safeGet(backingRowMap, obj);
    }

    public V put(C c, V v) {
        Preconditions.checkNotNull(c);
        Preconditions.checkNotNull(v);
        if (this.backingRowMap == null || this.backingRowMap.isEmpty()) {
            return this.this$0.put(this.rowKey, c, v);
        }
        return this.backingRowMap.put(c, v);
    }

    public V remove(Object obj) {
        Map backingRowMap = backingRowMap();
        if (backingRowMap == null) {
            return null;
        }
        V safeRemove = Maps.safeRemove(backingRowMap, obj);
        maintainEmptyInvariant();
        return safeRemove;
    }

    public void clear() {
        Map backingRowMap = backingRowMap();
        if (backingRowMap != null) {
            backingRowMap.clear();
        }
        maintainEmptyInvariant();
    }

    protected Set<Entry<C, V>> createEntrySet() {
        return new RowEntrySet();
    }
}
