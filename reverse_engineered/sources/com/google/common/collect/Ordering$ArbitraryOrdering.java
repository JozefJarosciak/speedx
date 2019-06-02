package com.google.common.collect;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Function;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@VisibleForTesting
class Ordering$ArbitraryOrdering extends Ordering<Object> {
    private Map<Object, Integer> uids = Platform.tryWeakKeys(new MapMaker()).makeComputingMap(new C36911());

    /* renamed from: com.google.common.collect.Ordering$ArbitraryOrdering$1 */
    class C36911 implements Function<Object, Integer> {
        final AtomicInteger counter = new AtomicInteger(0);

        C36911() {
        }

        public Integer apply(Object obj) {
            return Integer.valueOf(this.counter.getAndIncrement());
        }
    }

    Ordering$ArbitraryOrdering() {
    }

    public int compare(Object obj, Object obj2) {
        if (obj == obj2) {
            return 0;
        }
        if (obj == null) {
            return -1;
        }
        if (obj2 == null) {
            return 1;
        }
        int identityHashCode = identityHashCode(obj);
        int identityHashCode2 = identityHashCode(obj2);
        if (identityHashCode == identityHashCode2) {
            int compareTo = ((Integer) this.uids.get(obj)).compareTo((Integer) this.uids.get(obj2));
            if (compareTo != 0) {
                return compareTo;
            }
            throw new AssertionError();
        } else if (identityHashCode >= identityHashCode2) {
            return 1;
        } else {
            return -1;
        }
    }

    public String toString() {
        return "Ordering.arbitrary()";
    }

    int identityHashCode(Object obj) {
        return System.identityHashCode(obj);
    }
}
