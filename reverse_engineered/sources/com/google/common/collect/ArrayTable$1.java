package com.google.common.collect;

import com.google.common.collect.Table.Cell;

class ArrayTable$1 extends AbstractIndexedListIterator<Cell<R, C, V>> {
    final /* synthetic */ ArrayTable this$0;

    ArrayTable$1(ArrayTable arrayTable, int i) {
        this.this$0 = arrayTable;
        super(i);
    }

    protected Cell<R, C, V> get(final int i) {
        return new AbstractCell<R, C, V>() {
            final int columnIndex = (i % ArrayTable.access$000(ArrayTable$1.this.this$0).size());
            final int rowIndex = (i / ArrayTable.access$000(ArrayTable$1.this.this$0).size());

            public R getRowKey() {
                return ArrayTable.access$100(ArrayTable$1.this.this$0).get(this.rowIndex);
            }

            public C getColumnKey() {
                return ArrayTable.access$000(ArrayTable$1.this.this$0).get(this.columnIndex);
            }

            public V getValue() {
                return ArrayTable$1.this.this$0.at(this.rowIndex, this.columnIndex);
            }
        };
    }
}
