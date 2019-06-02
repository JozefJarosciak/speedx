package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import java.util.List;

@GwtCompatible(serializable = true)
final class ExplicitOrdering<T> extends Ordering<T> implements Serializable {
    private static final long serialVersionUID = 0;
    final ImmutableMap<T, Integer> rankMap;

    ExplicitOrdering(List<T> list) {
        this(buildRankMap(list));
    }

    ExplicitOrdering(ImmutableMap<T, Integer> immutableMap) {
        this.rankMap = immutableMap;
    }

    public int compare(T t, T t2) {
        return rank(t) - rank(t2);
    }

    private int rank(T t) {
        Integer num = (Integer) this.rankMap.get(t);
        if (num != null) {
            return num.intValue();
        }
        throw new Ordering$IncomparableValueException(t);
    }

    private static <T> ImmutableMap<T, Integer> buildRankMap(List<T> list) {
        ImmutableMap$Builder builder = ImmutableMap.builder();
        int i = 0;
        for (T put : list) {
            int i2 = i + 1;
            builder.put(put, Integer.valueOf(i));
            i = i2;
        }
        return builder.build();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ExplicitOrdering)) {
            return false;
        }
        return this.rankMap.equals(((ExplicitOrdering) obj).rankMap);
    }

    public int hashCode() {
        return this.rankMap.hashCode();
    }

    public String toString() {
        String valueOf = String.valueOf(String.valueOf(this.rankMap.keySet()));
        return new StringBuilder(valueOf.length() + 19).append("Ordering.explicit(").append(valueOf).append(")").toString();
    }
}
