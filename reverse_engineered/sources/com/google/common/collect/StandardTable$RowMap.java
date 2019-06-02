package com.google.common.collect;

import com.google.common.base.Function;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class StandardTable$RowMap extends ImprovedAbstractMap<R, Map<C, V>> {
    final /* synthetic */ StandardTable this$0;

    /* renamed from: com.google.common.collect.StandardTable$RowMap$EntrySet */
    class EntrySet extends StandardTable$TableSet<Entry<R, Map<C, V>>> {

        /* renamed from: com.google.common.collect.StandardTable$RowMap$EntrySet$1 */
        class C37221 implements Function<R, Map<C, V>> {
            C37221() {
            }

            public Map<C, V> apply(R r) {
                return StandardTable$RowMap.this.this$0.row(r);
            }
        }

        EntrySet() {
            super(StandardTable$RowMap.this.this$0);
        }

        public Iterator<Entry<R, Map<C, V>>> iterator() {
            return Maps.asMapEntryIterator(StandardTable$RowMap.this.this$0.backingMap.keySet(), new C37221());
        }

        public int size() {
            return StandardTable$RowMap.this.this$0.backingMap.size();
        }

        public boolean contains(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) obj;
            if (entry.getKey() != null && (entry.getValue() instanceof Map) && Collections2.safeContains(StandardTable$RowMap.this.this$0.backingMap.entrySet(), entry)) {
                return true;
            }
            return false;
        }

        public boolean remove(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) obj;
            if (entry.getKey() != null && (entry.getValue() instanceof Map) && StandardTable$RowMap.this.this$0.backingMap.entrySet().remove(entry)) {
                return true;
            }
            return false;
        }
    }

    StandardTable$RowMap(StandardTable standardTable) {
        this.this$0 = standardTable;
    }

    public boolean containsKey(Object obj) {
        return this.this$0.containsRow(obj);
    }

    public Map<C, V> get(Object obj) {
        return this.this$0.containsRow(obj) ? this.this$0.row(obj) : null;
    }

    public Map<C, V> remove(Object obj) {
        return obj == null ? null : (Map) this.this$0.backingMap.remove(obj);
    }

    protected Set<Entry<R, Map<C, V>>> createEntrySet() {
        return new EntrySet();
    }
}
