package com.google.common.collect;

import java.util.Map;

class ArrayTable$RowMap extends ArrayTable$ArrayMap<R, Map<C, V>> {
    final /* synthetic */ ArrayTable this$0;

    private ArrayTable$RowMap(ArrayTable arrayTable) {
        this.this$0 = arrayTable;
        super(ArrayTable.access$200(arrayTable));
    }

    String getKeyRole() {
        return "Row";
    }

    Map<C, V> getValue(int i) {
        return new ArrayTable$Row(this.this$0, i);
    }

    Map<C, V> setValue(int i, Map<C, V> map) {
        throw new UnsupportedOperationException();
    }

    public Map<C, V> put(R r, Map<C, V> map) {
        throw new UnsupportedOperationException();
    }
}
