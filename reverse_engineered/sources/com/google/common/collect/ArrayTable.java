package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Table.Cell;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@GwtCompatible(emulated = true)
@Beta
public final class ArrayTable<R, C, V> extends AbstractTable<R, C, V> implements Serializable {
    private static final long serialVersionUID = 0;
    private final V[][] array;
    private final ImmutableMap<C, Integer> columnKeyToIndex;
    private final ImmutableList<C> columnList;
    private transient ArrayTable$ColumnMap columnMap;
    private final ImmutableMap<R, Integer> rowKeyToIndex;
    private final ImmutableList<R> rowList;
    private transient ArrayTable$RowMap rowMap;

    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public static <R, C, V> ArrayTable<R, C, V> create(Iterable<? extends R> iterable, Iterable<? extends C> iterable2) {
        return new ArrayTable(iterable, iterable2);
    }

    public static <R, C, V> ArrayTable<R, C, V> create(Table<R, C, V> table) {
        return table instanceof ArrayTable ? new ArrayTable((ArrayTable) table) : new ArrayTable((Table) table);
    }

    private ArrayTable(Iterable<? extends R> iterable, Iterable<? extends C> iterable2) {
        boolean z = true;
        this.rowList = ImmutableList.copyOf((Iterable) iterable);
        this.columnList = ImmutableList.copyOf((Iterable) iterable2);
        Preconditions.checkArgument(!this.rowList.isEmpty());
        if (this.columnList.isEmpty()) {
            z = false;
        }
        Preconditions.checkArgument(z);
        this.rowKeyToIndex = index(this.rowList);
        this.columnKeyToIndex = index(this.columnList);
        this.array = (Object[][]) Array.newInstance(Object.class, new int[]{this.rowList.size(), this.columnList.size()});
        eraseAll();
    }

    private static <E> ImmutableMap<E, Integer> index(List<E> list) {
        ImmutableMap$Builder builder = ImmutableMap.builder();
        for (int i = 0; i < list.size(); i++) {
            builder.put(list.get(i), Integer.valueOf(i));
        }
        return builder.build();
    }

    private ArrayTable(Table<R, C, V> table) {
        this(table.rowKeySet(), table.columnKeySet());
        putAll(table);
    }

    private ArrayTable(ArrayTable<R, C, V> arrayTable) {
        this.rowList = arrayTable.rowList;
        this.columnList = arrayTable.columnList;
        this.rowKeyToIndex = arrayTable.rowKeyToIndex;
        this.columnKeyToIndex = arrayTable.columnKeyToIndex;
        Object[][] objArr = (Object[][]) Array.newInstance(Object.class, new int[]{this.rowList.size(), this.columnList.size()});
        this.array = objArr;
        eraseAll();
        for (int i = 0; i < this.rowList.size(); i++) {
            System.arraycopy(arrayTable.array[i], 0, objArr[i], 0, arrayTable.array[i].length);
        }
    }

    public ImmutableList<R> rowKeyList() {
        return this.rowList;
    }

    public ImmutableList<C> columnKeyList() {
        return this.columnList;
    }

    public V at(int i, int i2) {
        Preconditions.checkElementIndex(i, this.rowList.size());
        Preconditions.checkElementIndex(i2, this.columnList.size());
        return this.array[i][i2];
    }

    public V set(int i, int i2, V v) {
        Preconditions.checkElementIndex(i, this.rowList.size());
        Preconditions.checkElementIndex(i2, this.columnList.size());
        V v2 = this.array[i][i2];
        this.array[i][i2] = v;
        return v2;
    }

    @GwtIncompatible("reflection")
    public V[][] toArray(Class<V> cls) {
        Object[][] objArr = (Object[][]) Array.newInstance(cls, new int[]{this.rowList.size(), this.columnList.size()});
        for (int i = 0; i < this.rowList.size(); i++) {
            System.arraycopy(this.array[i], 0, objArr[i], 0, this.array[i].length);
        }
        return objArr;
    }

    @Deprecated
    public void clear() {
        throw new UnsupportedOperationException();
    }

    public void eraseAll() {
        for (Object[] fill : this.array) {
            Arrays.fill(fill, null);
        }
    }

    public boolean contains(Object obj, Object obj2) {
        return containsRow(obj) && containsColumn(obj2);
    }

    public boolean containsColumn(Object obj) {
        return this.columnKeyToIndex.containsKey(obj);
    }

    public boolean containsRow(Object obj) {
        return this.rowKeyToIndex.containsKey(obj);
    }

    public boolean containsValue(Object obj) {
        for (Object[] objArr : this.array) {
            for (Object equal : r3[r2]) {
                if (Objects.equal(obj, equal)) {
                    return true;
                }
            }
        }
        return false;
    }

    public V get(Object obj, Object obj2) {
        Integer num = (Integer) this.rowKeyToIndex.get(obj);
        Integer num2 = (Integer) this.columnKeyToIndex.get(obj2);
        return (num == null || num2 == null) ? null : at(num.intValue(), num2.intValue());
    }

    public boolean isEmpty() {
        return false;
    }

    public V put(R r, C c, V v) {
        boolean z;
        boolean z2;
        Preconditions.checkNotNull(r);
        Preconditions.checkNotNull(c);
        Integer num = (Integer) this.rowKeyToIndex.get(r);
        if (num != null) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "Row %s not in %s", new Object[]{r, this.rowList});
        Integer num2 = (Integer) this.columnKeyToIndex.get(c);
        if (num2 != null) {
            z2 = true;
        } else {
            z2 = false;
        }
        Preconditions.checkArgument(z2, "Column %s not in %s", new Object[]{c, this.columnList});
        return set(num.intValue(), num2.intValue(), v);
    }

    public void putAll(Table<? extends R, ? extends C, ? extends V> table) {
        super.putAll(table);
    }

    @Deprecated
    public V remove(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    public V erase(Object obj, Object obj2) {
        Integer num = (Integer) this.rowKeyToIndex.get(obj);
        Integer num2 = (Integer) this.columnKeyToIndex.get(obj2);
        if (num == null || num2 == null) {
            return null;
        }
        return set(num.intValue(), num2.intValue(), null);
    }

    public int size() {
        return this.rowList.size() * this.columnList.size();
    }

    public Set<Cell<R, C, V>> cellSet() {
        return super.cellSet();
    }

    Iterator<Cell<R, C, V>> cellIterator() {
        return new ArrayTable$1(this, size());
    }

    public Map<R, V> column(C c) {
        Preconditions.checkNotNull(c);
        Integer num = (Integer) this.columnKeyToIndex.get(c);
        return num == null ? ImmutableMap.of() : new ArrayTable$Column(this, num.intValue());
    }

    public ImmutableSet<C> columnKeySet() {
        return this.columnKeyToIndex.keySet();
    }

    public Map<C, Map<R, V>> columnMap() {
        Map<C, Map<R, V>> map = this.columnMap;
        if (map != null) {
            return map;
        }
        Map arrayTable$ColumnMap = new ArrayTable$ColumnMap(this, null);
        this.columnMap = arrayTable$ColumnMap;
        return arrayTable$ColumnMap;
    }

    public Map<C, V> row(R r) {
        Preconditions.checkNotNull(r);
        Integer num = (Integer) this.rowKeyToIndex.get(r);
        return num == null ? ImmutableMap.of() : new ArrayTable$Row(this, num.intValue());
    }

    public ImmutableSet<R> rowKeySet() {
        return this.rowKeyToIndex.keySet();
    }

    public Map<R, Map<C, V>> rowMap() {
        Map<R, Map<C, V>> map = this.rowMap;
        if (map != null) {
            return map;
        }
        Map arrayTable$RowMap = new ArrayTable$RowMap(this, null);
        this.rowMap = arrayTable$RowMap;
        return arrayTable$RowMap;
    }

    public Collection<V> values() {
        return super.values();
    }
}
