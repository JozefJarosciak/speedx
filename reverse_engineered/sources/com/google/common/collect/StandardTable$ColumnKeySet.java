package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

class StandardTable$ColumnKeySet extends StandardTable$TableSet<C> {
    final /* synthetic */ StandardTable this$0;

    private StandardTable$ColumnKeySet(StandardTable standardTable) {
        this.this$0 = standardTable;
        super(standardTable);
    }

    public Iterator<C> iterator() {
        return this.this$0.createColumnKeyIterator();
    }

    public int size() {
        return Iterators.size(iterator());
    }

    public boolean remove(Object obj) {
        if (obj == null) {
            return false;
        }
        Iterator it = this.this$0.backingMap.values().iterator();
        boolean z = false;
        while (it.hasNext()) {
            Map map = (Map) it.next();
            if (map.keySet().remove(obj)) {
                z = true;
                if (map.isEmpty()) {
                    it.remove();
                }
            }
            z = z;
        }
        return z;
    }

    public boolean removeAll(Collection<?> collection) {
        Preconditions.checkNotNull(collection);
        Iterator it = this.this$0.backingMap.values().iterator();
        boolean z = false;
        while (it.hasNext()) {
            Map map = (Map) it.next();
            if (Iterators.removeAll(map.keySet().iterator(), collection)) {
                z = true;
                if (map.isEmpty()) {
                    it.remove();
                }
            }
            z = z;
        }
        return z;
    }

    public boolean retainAll(Collection<?> collection) {
        Preconditions.checkNotNull(collection);
        Iterator it = this.this$0.backingMap.values().iterator();
        boolean z = false;
        while (it.hasNext()) {
            Map map = (Map) it.next();
            if (map.keySet().retainAll(collection)) {
                z = true;
                if (map.isEmpty()) {
                    it.remove();
                }
            }
            z = z;
        }
        return z;
    }

    public boolean contains(Object obj) {
        return this.this$0.containsColumn(obj);
    }
}
