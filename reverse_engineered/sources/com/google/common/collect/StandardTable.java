package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.collect.Table.Cell;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

@GwtCompatible
class StandardTable<R, C, V> extends AbstractTable<R, C, V> implements Serializable {
    private static final long serialVersionUID = 0;
    @GwtTransient
    final Map<R, Map<C, V>> backingMap;
    private transient Set<C> columnKeySet;
    private transient StandardTable$ColumnMap columnMap;
    @GwtTransient
    final Supplier<? extends Map<C, V>> factory;
    private transient Map<R, Map<C, V>> rowMap;

    StandardTable(Map<R, Map<C, V>> map, Supplier<? extends Map<C, V>> supplier) {
        this.backingMap = map;
        this.factory = supplier;
    }

    public boolean contains(Object obj, Object obj2) {
        return (obj == null || obj2 == null || !super.contains(obj, obj2)) ? false : true;
    }

    public boolean containsColumn(Object obj) {
        if (obj == null) {
            return false;
        }
        for (Map safeContainsKey : this.backingMap.values()) {
            if (Maps.safeContainsKey(safeContainsKey, obj)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsRow(Object obj) {
        return obj != null && Maps.safeContainsKey(this.backingMap, obj);
    }

    public boolean containsValue(Object obj) {
        return obj != null && super.containsValue(obj);
    }

    public V get(Object obj, Object obj2) {
        return (obj == null || obj2 == null) ? null : super.get(obj, obj2);
    }

    public boolean isEmpty() {
        return this.backingMap.isEmpty();
    }

    public int size() {
        int i = 0;
        for (Map size : this.backingMap.values()) {
            i = size.size() + i;
        }
        return i;
    }

    public void clear() {
        this.backingMap.clear();
    }

    private Map<C, V> getOrCreate(R r) {
        Map<C, V> map = (Map) this.backingMap.get(r);
        if (map != null) {
            return map;
        }
        map = (Map) this.factory.get();
        this.backingMap.put(r, map);
        return map;
    }

    public V put(R r, C c, V v) {
        Preconditions.checkNotNull(r);
        Preconditions.checkNotNull(c);
        Preconditions.checkNotNull(v);
        return getOrCreate(r).put(c, v);
    }

    public V remove(Object obj, Object obj2) {
        if (obj == null || obj2 == null) {
            return null;
        }
        Map map = (Map) Maps.safeGet(this.backingMap, obj);
        if (map == null) {
            return null;
        }
        V remove = map.remove(obj2);
        if (map.isEmpty()) {
            this.backingMap.remove(obj);
        }
        return remove;
    }

    private Map<R, V> removeColumn(Object obj) {
        Map<R, V> linkedHashMap = new LinkedHashMap();
        Iterator it = this.backingMap.entrySet().iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            Object remove = ((Map) entry.getValue()).remove(obj);
            if (remove != null) {
                linkedHashMap.put(entry.getKey(), remove);
                if (((Map) entry.getValue()).isEmpty()) {
                    it.remove();
                }
            }
        }
        return linkedHashMap;
    }

    private boolean containsMapping(Object obj, Object obj2, Object obj3) {
        return obj3 != null && obj3.equals(get(obj, obj2));
    }

    private boolean removeMapping(Object obj, Object obj2, Object obj3) {
        if (!containsMapping(obj, obj2, obj3)) {
            return false;
        }
        remove(obj, obj2);
        return true;
    }

    public Set<Cell<R, C, V>> cellSet() {
        return super.cellSet();
    }

    Iterator<Cell<R, C, V>> cellIterator() {
        return new StandardTable$CellIterator(this, null);
    }

    public Map<C, V> row(R r) {
        return new StandardTable$Row(this, r);
    }

    public Map<R, V> column(C c) {
        return new StandardTable$Column(this, c);
    }

    public Set<R> rowKeySet() {
        return rowMap().keySet();
    }

    public Set<C> columnKeySet() {
        Set<C> set = this.columnKeySet;
        if (set != null) {
            return set;
        }
        set = new StandardTable$ColumnKeySet(this, null);
        this.columnKeySet = set;
        return set;
    }

    Iterator<C> createColumnKeyIterator() {
        return new StandardTable$ColumnKeyIterator(this, null);
    }

    public Collection<V> values() {
        return super.values();
    }

    public Map<R, Map<C, V>> rowMap() {
        Map<R, Map<C, V>> map = this.rowMap;
        if (map != null) {
            return map;
        }
        map = createRowMap();
        this.rowMap = map;
        return map;
    }

    Map<R, Map<C, V>> createRowMap() {
        return new StandardTable$RowMap(this);
    }

    public Map<C, Map<R, V>> columnMap() {
        Map<C, Map<R, V>> map = this.columnMap;
        if (map != null) {
            return map;
        }
        Map standardTable$ColumnMap = new StandardTable$ColumnMap(this, null);
        this.columnMap = standardTable$ColumnMap;
        return standardTable$ColumnMap;
    }
}
