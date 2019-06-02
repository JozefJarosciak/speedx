package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class StandardTable$Column extends ImprovedAbstractMap<R, V> {
    final C columnKey;
    final /* synthetic */ StandardTable this$0;

    /* renamed from: com.google.common.collect.StandardTable$Column$EntrySet */
    private class EntrySet extends ImprovedAbstractSet<Entry<R, V>> {
        private EntrySet() {
        }

        public Iterator<Entry<R, V>> iterator() {
            return new EntrySetIterator();
        }

        public int size() {
            int i = 0;
            for (Map containsKey : StandardTable$Column.this.this$0.backingMap.values()) {
                int i2;
                if (containsKey.containsKey(StandardTable$Column.this.columnKey)) {
                    i2 = i + 1;
                } else {
                    i2 = i;
                }
                i = i2;
            }
            return i;
        }

        public boolean isEmpty() {
            return !StandardTable$Column.this.this$0.containsColumn(StandardTable$Column.this.columnKey);
        }

        public void clear() {
            StandardTable$Column.this.removeFromColumnIf(Predicates.alwaysTrue());
        }

        public boolean contains(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) obj;
            return StandardTable.access$400(StandardTable$Column.this.this$0, entry.getKey(), StandardTable$Column.this.columnKey, entry.getValue());
        }

        public boolean remove(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) obj;
            return StandardTable.access$500(StandardTable$Column.this.this$0, entry.getKey(), StandardTable$Column.this.columnKey, entry.getValue());
        }

        public boolean retainAll(Collection<?> collection) {
            return StandardTable$Column.this.removeFromColumnIf(Predicates.not(Predicates.in(collection)));
        }
    }

    /* renamed from: com.google.common.collect.StandardTable$Column$EntrySetIterator */
    private class EntrySetIterator extends AbstractIterator<Entry<R, V>> {
        final Iterator<Entry<R, Map<C, V>>> iterator;

        private EntrySetIterator() {
            this.iterator = StandardTable$Column.this.this$0.backingMap.entrySet().iterator();
        }

        protected Entry<R, V> computeNext() {
            while (this.iterator.hasNext()) {
                final Entry entry = (Entry) this.iterator.next();
                if (((Map) entry.getValue()).containsKey(StandardTable$Column.this.columnKey)) {
                    return new AbstractMapEntry<R, V>() {
                        public R getKey() {
                            return entry.getKey();
                        }

                        public V getValue() {
                            return ((Map) entry.getValue()).get(StandardTable$Column.this.columnKey);
                        }

                        public V setValue(V v) {
                            return ((Map) entry.getValue()).put(StandardTable$Column.this.columnKey, Preconditions.checkNotNull(v));
                        }
                    };
                }
            }
            return (Entry) endOfData();
        }
    }

    /* renamed from: com.google.common.collect.StandardTable$Column$KeySet */
    private class KeySet extends KeySet<R, V> {
        KeySet() {
            super(StandardTable$Column.this);
        }

        public boolean contains(Object obj) {
            return StandardTable$Column.this.this$0.contains(obj, StandardTable$Column.this.columnKey);
        }

        public boolean remove(Object obj) {
            return StandardTable$Column.this.this$0.remove(obj, StandardTable$Column.this.columnKey) != null;
        }

        public boolean retainAll(Collection<?> collection) {
            return StandardTable$Column.this.removeFromColumnIf(Maps.keyPredicateOnEntries(Predicates.not(Predicates.in(collection))));
        }
    }

    /* renamed from: com.google.common.collect.StandardTable$Column$Values */
    private class Values extends Values<R, V> {
        Values() {
            super(StandardTable$Column.this);
        }

        public boolean remove(Object obj) {
            return obj != null && StandardTable$Column.this.removeFromColumnIf(Maps.valuePredicateOnEntries(Predicates.equalTo(obj)));
        }

        public boolean removeAll(Collection<?> collection) {
            return StandardTable$Column.this.removeFromColumnIf(Maps.valuePredicateOnEntries(Predicates.in(collection)));
        }

        public boolean retainAll(Collection<?> collection) {
            return StandardTable$Column.this.removeFromColumnIf(Maps.valuePredicateOnEntries(Predicates.not(Predicates.in(collection))));
        }
    }

    StandardTable$Column(StandardTable standardTable, C c) {
        this.this$0 = standardTable;
        this.columnKey = Preconditions.checkNotNull(c);
    }

    public V put(R r, V v) {
        return this.this$0.put(r, this.columnKey, v);
    }

    public V get(Object obj) {
        return this.this$0.get(obj, this.columnKey);
    }

    public boolean containsKey(Object obj) {
        return this.this$0.contains(obj, this.columnKey);
    }

    public V remove(Object obj) {
        return this.this$0.remove(obj, this.columnKey);
    }

    boolean removeFromColumnIf(Predicate<? super Entry<R, V>> predicate) {
        Iterator it = this.this$0.backingMap.entrySet().iterator();
        boolean z = false;
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            Map map = (Map) entry.getValue();
            Object obj = map.get(this.columnKey);
            if (obj != null && predicate.apply(Maps.immutableEntry(entry.getKey(), obj))) {
                map.remove(this.columnKey);
                z = true;
                if (map.isEmpty()) {
                    it.remove();
                }
            }
            z = z;
        }
        return z;
    }

    Set<Entry<R, V>> createEntrySet() {
        return new EntrySet();
    }

    Set<R> createKeySet() {
        return new KeySet();
    }

    Collection<V> createValues() {
        return new Values();
    }
}
