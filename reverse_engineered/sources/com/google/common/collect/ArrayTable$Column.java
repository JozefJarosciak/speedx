package com.google.common.collect;

class ArrayTable$Column extends ArrayTable$ArrayMap<R, V> {
    final int columnIndex;
    final /* synthetic */ ArrayTable this$0;

    ArrayTable$Column(ArrayTable arrayTable, int i) {
        this.this$0 = arrayTable;
        super(ArrayTable.access$200(arrayTable));
        this.columnIndex = i;
    }

    String getKeyRole() {
        return "Row";
    }

    V getValue(int i) {
        return this.this$0.at(i, this.columnIndex);
    }

    V setValue(int i, V v) {
        return this.this$0.set(i, this.columnIndex, v);
    }
}
