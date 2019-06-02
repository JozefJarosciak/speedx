package com.google.common.collect;

class ArrayTable$Row extends ArrayTable$ArrayMap<C, V> {
    final int rowIndex;
    final /* synthetic */ ArrayTable this$0;

    ArrayTable$Row(ArrayTable arrayTable, int i) {
        this.this$0 = arrayTable;
        super(ArrayTable.access$500(arrayTable));
        this.rowIndex = i;
    }

    String getKeyRole() {
        return "Column";
    }

    V getValue(int i) {
        return this.this$0.at(this.rowIndex, i);
    }

    V setValue(int i, V v) {
        return this.this$0.set(this.rowIndex, i, v);
    }
}
