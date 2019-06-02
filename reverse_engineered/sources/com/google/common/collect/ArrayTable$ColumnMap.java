package com.google.common.collect;

import java.util.Map;

class ArrayTable$ColumnMap extends ArrayTable$ArrayMap<C, Map<R, V>> {
    final /* synthetic */ ArrayTable this$0;

    private ArrayTable$ColumnMap(ArrayTable arrayTable) {
        this.this$0 = arrayTable;
        super(ArrayTable.access$500(arrayTable));
    }

    String getKeyRole() {
        return "Column";
    }

    Map<R, V> getValue(int i) {
        return new ArrayTable$Column(this.this$0, i);
    }

    Map<R, V> setValue(int i, Map<R, V> map) {
        throw new UnsupportedOperationException();
    }

    public Map<R, V> put(C c, Map<R, V> map) {
        throw new UnsupportedOperationException();
    }
}
