package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import java.util.Iterator;

@GwtCompatible(serializable = true)
final class LexicographicalOrdering<T> extends Ordering<Iterable<T>> implements Serializable {
    private static final long serialVersionUID = 0;
    final Ordering<? super T> elementOrder;

    LexicographicalOrdering(Ordering<? super T> ordering) {
        this.elementOrder = ordering;
    }

    public int compare(Iterable<T> iterable, Iterable<T> iterable2) {
        Iterator it = iterable2.iterator();
        for (T compare : iterable) {
            if (!it.hasNext()) {
                return 1;
            }
            int compare2 = this.elementOrder.compare(compare, it.next());
            if (compare2 != 0) {
                return compare2;
            }
        }
        if (it.hasNext()) {
            return -1;
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LexicographicalOrdering)) {
            return false;
        }
        return this.elementOrder.equals(((LexicographicalOrdering) obj).elementOrder);
    }

    public int hashCode() {
        return this.elementOrder.hashCode() ^ 2075626741;
    }

    public String toString() {
        String valueOf = String.valueOf(String.valueOf(this.elementOrder));
        return new StringBuilder(valueOf.length() + 18).append(valueOf).append(".lexicographical()").toString();
    }
}
