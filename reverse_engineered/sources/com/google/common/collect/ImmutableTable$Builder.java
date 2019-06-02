package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.Table.Cell;
import java.util.Comparator;
import java.util.List;

public final class ImmutableTable$Builder<R, C, V> {
    private final List<Cell<R, C, V>> cells = Lists.newArrayList();
    private Comparator<? super C> columnComparator;
    private Comparator<? super R> rowComparator;

    public ImmutableTable$Builder<R, C, V> orderRowsBy(Comparator<? super R> comparator) {
        this.rowComparator = (Comparator) Preconditions.checkNotNull(comparator);
        return this;
    }

    public ImmutableTable$Builder<R, C, V> orderColumnsBy(Comparator<? super C> comparator) {
        this.columnComparator = (Comparator) Preconditions.checkNotNull(comparator);
        return this;
    }

    public ImmutableTable$Builder<R, C, V> put(R r, C c, V v) {
        this.cells.add(ImmutableTable.cellOf(r, c, v));
        return this;
    }

    public ImmutableTable$Builder<R, C, V> put(Cell<? extends R, ? extends C, ? extends V> cell) {
        if (cell instanceof ImmutableCell) {
            Preconditions.checkNotNull(cell.getRowKey());
            Preconditions.checkNotNull(cell.getColumnKey());
            Preconditions.checkNotNull(cell.getValue());
            this.cells.add(cell);
        } else {
            put(cell.getRowKey(), cell.getColumnKey(), cell.getValue());
        }
        return this;
    }

    public ImmutableTable$Builder<R, C, V> putAll(Table<? extends R, ? extends C, ? extends V> table) {
        for (Cell put : table.cellSet()) {
            put(put);
        }
        return this;
    }

    public ImmutableTable<R, C, V> build() {
        switch (this.cells.size()) {
            case 0:
                return ImmutableTable.of();
            case 1:
                return new SingletonImmutableTable((Cell) Iterables.getOnlyElement(this.cells));
            default:
                return RegularImmutableTable.forCells(this.cells, this.rowComparator, this.columnComparator);
        }
    }
}
