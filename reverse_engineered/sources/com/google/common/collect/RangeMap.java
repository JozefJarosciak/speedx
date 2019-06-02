package com.google.common.collect;

import com.google.common.annotations.Beta;
import java.util.Map;
import java.util.Map.Entry;

@Beta
public interface RangeMap<K extends Comparable, V> {
    Map<Range<K>, V> asMapOfRanges();

    void clear();

    boolean equals(Object obj);

    V get(K k);

    Entry<Range<K>, V> getEntry(K k);

    int hashCode();

    void put(Range<K> range, V v);

    void putAll(RangeMap<K, V> rangeMap);

    void remove(Range<K> range);

    Range<K> span();

    RangeMap<K, V> subRangeMap(Range<K> range);

    String toString();
}
