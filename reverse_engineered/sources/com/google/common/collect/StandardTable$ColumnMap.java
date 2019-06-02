package com.google.common.collect;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class StandardTable$ColumnMap extends ImprovedAbstractMap<C, Map<R, V>> {
    final /* synthetic */ StandardTable this$0;

    /* renamed from: com.google.common.collect.StandardTable$ColumnMap$ColumnMapEntrySet */
    class ColumnMapEntrySet extends StandardTable$TableSet<Entry<C, Map<R, V>>> {

        /* renamed from: com.google.common.collect.StandardTable$ColumnMap$ColumnMapEntrySet$1 */
        class C37191 implements Function<C, Map<R, V>> {
            C37191() {
            }

            public Map<R, V> apply(C c) {
                return StandardTable$ColumnMap.this.this$0.column(c);
            }
        }

        ColumnMapEntrySet() {
            super(StandardTable$ColumnMap.this.this$0);
        }

        public Iterator<Entry<C, Map<R, V>>> iterator() {
            return Maps.asMapEntryIterator(StandardTable$ColumnMap.this.this$0.columnKeySet(), new C37191());
        }

        public int size() {
            return StandardTable$ColumnMap.this.this$0.columnKeySet().size();
        }

        public boolean contains(Object obj) {
            if (obj instanceof Entry) {
                Entry entry = (Entry) obj;
                if (StandardTable$ColumnMap.this.this$0.containsColumn(entry.getKey())) {
                    return StandardTable$ColumnMap.this.get(entry.getKey()).equals(entry.getValue());
                }
            }
            return false;
        }

        public boolean remove(Object obj) {
            if (!contains(obj)) {
                return false;
            }
            StandardTable.access$1000(StandardTable$ColumnMap.this.this$0, ((Entry) obj).getKey());
            return true;
        }

        public boolean removeAll(Collection<?> collection) {
            Preconditions.checkNotNull(collection);
            return Sets.removeAllImpl((Set) this, collection.iterator());
        }

        public boolean retainAll(Collection<?> collection) {
            Preconditions.checkNotNull(collection);
            boolean z = false;
            Iterator it = Lists.newArrayList(StandardTable$ColumnMap.this.this$0.columnKeySet().iterator()).iterator();
            while (it.hasNext()) {
                Object next = it.next();
                if (!collection.contains(Maps.immutableEntry(next, StandardTable$ColumnMap.this.this$0.column(next)))) {
                    StandardTable.access$1000(StandardTable$ColumnMap.this.this$0, next);
                    z = true;
                }
            }
            return z;
        }
    }

    /* renamed from: com.google.common.collect.StandardTable$ColumnMap$ColumnMapValues */
    private class ColumnMapValues extends Values<C, Map<R, V>> {
        ColumnMapValues() {
            super(StandardTable$ColumnMap.this);
        }

        public boolean remove(Object obj) {
            for (Entry entry : StandardTable$ColumnMap.this.entrySet()) {
                if (((Map) entry.getValue()).equals(obj)) {
                    StandardTable.access$1000(StandardTable$ColumnMap.this.this$0, entry.getKey());
                    return true;
                }
            }
            return false;
        }

        public boolean removeAll(Collection<?> collection) {
            Preconditions.checkNotNull(collection);
            boolean z = false;
            Iterator it = Lists.newArrayList(StandardTable$ColumnMap.this.this$0.columnKeySet().iterator()).iterator();
            while (it.hasNext()) {
                Object next = it.next();
                if (collection.contains(StandardTable$ColumnMap.this.this$0.column(next))) {
                    StandardTable.access$1000(StandardTable$ColumnMap.this.this$0, next);
                    z = true;
                }
            }
            return z;
        }

        public boolean retainAll(Collection<?> collection) {
            Preconditions.checkNotNull(collection);
            boolean z = false;
            Iterator it = Lists.newArrayList(StandardTable$ColumnMap.this.this$0.columnKeySet().iterator()).iterator();
            while (it.hasNext()) {
                Object next = it.next();
                if (!collection.contains(StandardTable$ColumnMap.this.this$0.column(next))) {
                    StandardTable.access$1000(StandardTable$ColumnMap.this.this$0, next);
                    z = true;
                }
            }
            return z;
        }
    }

    private StandardTable$ColumnMap(StandardTable standardTable) {
        this.this$0 = standardTable;
    }

    public Map<R, V> get(Object obj) {
        return this.this$0.containsColumn(obj) ? this.this$0.column(obj) : null;
    }

    public boolean containsKey(Object obj) {
        return this.this$0.containsColumn(obj);
    }

    public Map<R, V> remove(Object obj) {
        return this.this$0.containsColumn(obj) ? StandardTable.access$1000(this.this$0, obj) : null;
    }

    public Set<Entry<C, Map<R, V>>> createEntrySet() {
        return new ColumnMapEntrySet();
    }

    public Set<C> keySet() {
        return this.this$0.columnKeySet();
    }

    Collection<Map<R, V>> createValues() {
        return new ColumnMapValues();
    }
}
