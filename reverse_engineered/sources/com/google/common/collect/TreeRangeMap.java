package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.NoSuchElementException;
import java.util.Set;

@GwtIncompatible("NavigableMap")
@Beta
public final class TreeRangeMap<K extends Comparable, V> implements RangeMap<K, V> {
    private static final RangeMap EMPTY_SUB_RANGE_MAP = new C37401();
    private final NavigableMap<Cut<K>, RangeMapEntry<K, V>> entriesByLowerBound = Maps.newTreeMap();

    /* renamed from: com.google.common.collect.TreeRangeMap$1 */
    static class C37401 implements RangeMap {
        C37401() {
        }

        public Object get(Comparable comparable) {
            return null;
        }

        public Entry<Range, Object> getEntry(Comparable comparable) {
            return null;
        }

        public Range span() {
            throw new NoSuchElementException();
        }

        public void put(Range range, Object obj) {
            Preconditions.checkNotNull(range);
            String valueOf = String.valueOf(String.valueOf(range));
            throw new IllegalArgumentException(new StringBuilder(valueOf.length() + 46).append("Cannot insert range ").append(valueOf).append(" into an empty subRangeMap").toString());
        }

        public void putAll(RangeMap rangeMap) {
            if (!rangeMap.asMapOfRanges().isEmpty()) {
                throw new IllegalArgumentException("Cannot putAll(nonEmptyRangeMap) into an empty subRangeMap");
            }
        }

        public void clear() {
        }

        public void remove(Range range) {
            Preconditions.checkNotNull(range);
        }

        public Map<Range, Object> asMapOfRanges() {
            return Collections.emptyMap();
        }

        public RangeMap subRangeMap(Range range) {
            Preconditions.checkNotNull(range);
            return this;
        }
    }

    private final class AsMapOfRanges extends AbstractMap<Range<K>, V> {

        /* renamed from: com.google.common.collect.TreeRangeMap$AsMapOfRanges$1 */
        class C37411 extends AbstractSet<Entry<Range<K>, V>> {
            C37411() {
            }

            public Iterator<Entry<Range<K>, V>> iterator() {
                return TreeRangeMap.this.entriesByLowerBound.values().iterator();
            }

            public int size() {
                return TreeRangeMap.this.entriesByLowerBound.size();
            }
        }

        private AsMapOfRanges() {
        }

        public boolean containsKey(Object obj) {
            return get(obj) != null;
        }

        public V get(Object obj) {
            if (obj instanceof Range) {
                Range range = (Range) obj;
                RangeMapEntry rangeMapEntry = (RangeMapEntry) TreeRangeMap.this.entriesByLowerBound.get(range.lowerBound);
                if (rangeMapEntry != null && rangeMapEntry.getKey().equals(range)) {
                    return rangeMapEntry.getValue();
                }
            }
            return null;
        }

        public Set<Entry<Range<K>, V>> entrySet() {
            return new C37411();
        }
    }

    private static final class RangeMapEntry<K extends Comparable, V> extends AbstractMapEntry<Range<K>, V> {
        private final Range<K> range;
        private final V value;

        RangeMapEntry(Cut<K> cut, Cut<K> cut2, V v) {
            this(Range.create(cut, cut2), v);
        }

        RangeMapEntry(Range<K> range, V v) {
            this.range = range;
            this.value = v;
        }

        public Range<K> getKey() {
            return this.range;
        }

        public V getValue() {
            return this.value;
        }

        public boolean contains(K k) {
            return this.range.contains(k);
        }

        Cut<K> getLowerBound() {
            return this.range.lowerBound;
        }

        Cut<K> getUpperBound() {
            return this.range.upperBound;
        }
    }

    private class SubRangeMap implements RangeMap<K, V> {
        private final Range<K> subRange;

        class SubRangeMapAsMap extends AbstractMap<Range<K>, V> {

            /* renamed from: com.google.common.collect.TreeRangeMap$SubRangeMap$SubRangeMapAsMap$2 */
            class C37442 extends EntrySet<Range<K>, V> {
                C37442() {
                }

                Map<Range<K>, V> map() {
                    return SubRangeMapAsMap.this;
                }

                public Iterator<Entry<Range<K>, V>> iterator() {
                    if (SubRangeMap.this.subRange.isEmpty()) {
                        return Iterators.emptyIterator();
                    }
                    final Iterator it = TreeRangeMap.this.entriesByLowerBound.tailMap((Cut) MoreObjects.firstNonNull(TreeRangeMap.this.entriesByLowerBound.floorKey(SubRangeMap.this.subRange.lowerBound), SubRangeMap.this.subRange.lowerBound), true).values().iterator();
                    return new AbstractIterator<Entry<Range<K>, V>>() {
                        protected Entry<Range<K>, V> computeNext() {
                            while (it.hasNext()) {
                                RangeMapEntry rangeMapEntry = (RangeMapEntry) it.next();
                                if (rangeMapEntry.getLowerBound().compareTo(SubRangeMap.this.subRange.upperBound) >= 0) {
                                    break;
                                } else if (rangeMapEntry.getUpperBound().compareTo(SubRangeMap.this.subRange.lowerBound) > 0) {
                                    return Maps.immutableEntry(rangeMapEntry.getKey().intersection(SubRangeMap.this.subRange), rangeMapEntry.getValue());
                                }
                            }
                            return (Entry) endOfData();
                        }
                    };
                }

                public boolean retainAll(Collection<?> collection) {
                    return SubRangeMapAsMap.this.removeEntryIf(Predicates.not(Predicates.in(collection)));
                }

                public int size() {
                    return Iterators.size(iterator());
                }

                public boolean isEmpty() {
                    return !iterator().hasNext();
                }
            }

            SubRangeMapAsMap() {
            }

            public boolean containsKey(Object obj) {
                return get(obj) != null;
            }

            public V get(Object obj) {
                try {
                    if (!(obj instanceof Range)) {
                        return null;
                    }
                    Range range = (Range) obj;
                    if (!SubRangeMap.this.subRange.encloses(range) || range.isEmpty()) {
                        return null;
                    }
                    RangeMapEntry rangeMapEntry;
                    if (range.lowerBound.compareTo(SubRangeMap.this.subRange.lowerBound) == 0) {
                        Entry floorEntry = TreeRangeMap.this.entriesByLowerBound.floorEntry(range.lowerBound);
                        if (floorEntry != null) {
                            rangeMapEntry = (RangeMapEntry) floorEntry.getValue();
                        } else {
                            rangeMapEntry = null;
                        }
                    } else {
                        rangeMapEntry = (RangeMapEntry) TreeRangeMap.this.entriesByLowerBound.get(range.lowerBound);
                    }
                    if (rangeMapEntry != null && rangeMapEntry.getKey().isConnected(SubRangeMap.this.subRange) && rangeMapEntry.getKey().intersection(SubRangeMap.this.subRange).equals(range)) {
                        return rangeMapEntry.getValue();
                    }
                    return null;
                } catch (ClassCastException e) {
                    return null;
                }
            }

            public V remove(Object obj) {
                V v = get(obj);
                if (v == null) {
                    return null;
                }
                TreeRangeMap.this.remove((Range) obj);
                return v;
            }

            public void clear() {
                SubRangeMap.this.clear();
            }

            private boolean removeEntryIf(Predicate<? super Entry<Range<K>, V>> predicate) {
                List<Range> newArrayList = Lists.newArrayList();
                for (Entry entry : entrySet()) {
                    if (predicate.apply(entry)) {
                        newArrayList.add(entry.getKey());
                    }
                }
                for (Range remove : newArrayList) {
                    TreeRangeMap.this.remove(remove);
                }
                return !newArrayList.isEmpty();
            }

            public Set<Range<K>> keySet() {
                return new KeySet<Range<K>, V>(this) {
                    public boolean remove(Object obj) {
                        return SubRangeMapAsMap.this.remove(obj) != null;
                    }

                    public boolean retainAll(Collection<?> collection) {
                        return SubRangeMapAsMap.this.removeEntryIf(Predicates.compose(Predicates.not(Predicates.in(collection)), Maps.keyFunction()));
                    }
                };
            }

            public Set<Entry<Range<K>, V>> entrySet() {
                return new C37442();
            }

            public Collection<V> values() {
                return new Values<Range<K>, V>(this) {
                    public boolean removeAll(Collection<?> collection) {
                        return SubRangeMapAsMap.this.removeEntryIf(Predicates.compose(Predicates.in(collection), Maps.valueFunction()));
                    }

                    public boolean retainAll(Collection<?> collection) {
                        return SubRangeMapAsMap.this.removeEntryIf(Predicates.compose(Predicates.not(Predicates.in(collection)), Maps.valueFunction()));
                    }
                };
            }
        }

        SubRangeMap(Range<K> range) {
            this.subRange = range;
        }

        public V get(K k) {
            return this.subRange.contains(k) ? TreeRangeMap.this.get(k) : null;
        }

        public Entry<Range<K>, V> getEntry(K k) {
            if (this.subRange.contains(k)) {
                Entry entry = TreeRangeMap.this.getEntry(k);
                if (entry != null) {
                    return Maps.immutableEntry(((Range) entry.getKey()).intersection(this.subRange), entry.getValue());
                }
            }
            return null;
        }

        public Range<K> span() {
            Cut cut;
            Cut cut2;
            Entry floorEntry = TreeRangeMap.this.entriesByLowerBound.floorEntry(this.subRange.lowerBound);
            if (floorEntry == null || ((RangeMapEntry) floorEntry.getValue()).getUpperBound().compareTo(this.subRange.lowerBound) <= 0) {
                cut = (Cut) TreeRangeMap.this.entriesByLowerBound.ceilingKey(this.subRange.lowerBound);
                if (cut == null || cut.compareTo(this.subRange.upperBound) >= 0) {
                    throw new NoSuchElementException();
                }
                cut2 = cut;
            } else {
                cut2 = this.subRange.lowerBound;
            }
            Entry lowerEntry = TreeRangeMap.this.entriesByLowerBound.lowerEntry(this.subRange.upperBound);
            if (lowerEntry == null) {
                throw new NoSuchElementException();
            }
            if (((RangeMapEntry) lowerEntry.getValue()).getUpperBound().compareTo(this.subRange.upperBound) >= 0) {
                cut = this.subRange.upperBound;
            } else {
                cut = ((RangeMapEntry) lowerEntry.getValue()).getUpperBound();
            }
            return Range.create(cut2, cut);
        }

        public void put(Range<K> range, V v) {
            Preconditions.checkArgument(this.subRange.encloses(range), "Cannot put range %s into a subRangeMap(%s)", range, this.subRange);
            TreeRangeMap.this.put(range, v);
        }

        public void putAll(RangeMap<K, V> rangeMap) {
            if (!rangeMap.asMapOfRanges().isEmpty()) {
                Object[] objArr = new Object[]{rangeMap.span(), this.subRange};
                Preconditions.checkArgument(this.subRange.encloses(rangeMap.span()), "Cannot putAll rangeMap with span %s into a subRangeMap(%s)", objArr);
                TreeRangeMap.this.putAll(rangeMap);
            }
        }

        public void clear() {
            TreeRangeMap.this.remove(this.subRange);
        }

        public void remove(Range<K> range) {
            if (range.isConnected(this.subRange)) {
                TreeRangeMap.this.remove(range.intersection(this.subRange));
            }
        }

        public RangeMap<K, V> subRangeMap(Range<K> range) {
            if (range.isConnected(this.subRange)) {
                return TreeRangeMap.this.subRangeMap(range.intersection(this.subRange));
            }
            return TreeRangeMap.this.emptySubRangeMap();
        }

        public Map<Range<K>, V> asMapOfRanges() {
            return new SubRangeMapAsMap();
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof RangeMap)) {
                return false;
            }
            return asMapOfRanges().equals(((RangeMap) obj).asMapOfRanges());
        }

        public int hashCode() {
            return asMapOfRanges().hashCode();
        }

        public String toString() {
            return asMapOfRanges().toString();
        }
    }

    public static <K extends Comparable, V> TreeRangeMap<K, V> create() {
        return new TreeRangeMap();
    }

    private TreeRangeMap() {
    }

    public V get(K k) {
        Entry entry = getEntry(k);
        return entry == null ? null : entry.getValue();
    }

    public Entry<Range<K>, V> getEntry(K k) {
        Entry floorEntry = this.entriesByLowerBound.floorEntry(Cut.belowValue(k));
        if (floorEntry == null || !((RangeMapEntry) floorEntry.getValue()).contains(k)) {
            return null;
        }
        return (Entry) floorEntry.getValue();
    }

    public void put(Range<K> range, V v) {
        if (!range.isEmpty()) {
            Preconditions.checkNotNull(v);
            remove(range);
            this.entriesByLowerBound.put(range.lowerBound, new RangeMapEntry(range, v));
        }
    }

    public void putAll(RangeMap<K, V> rangeMap) {
        for (Entry entry : rangeMap.asMapOfRanges().entrySet()) {
            put((Range) entry.getKey(), entry.getValue());
        }
    }

    public void clear() {
        this.entriesByLowerBound.clear();
    }

    public Range<K> span() {
        Entry firstEntry = this.entriesByLowerBound.firstEntry();
        Entry lastEntry = this.entriesByLowerBound.lastEntry();
        if (firstEntry != null) {
            return Range.create(((RangeMapEntry) firstEntry.getValue()).getKey().lowerBound, ((RangeMapEntry) lastEntry.getValue()).getKey().upperBound);
        }
        throw new NoSuchElementException();
    }

    private void putRangeMapEntry(Cut<K> cut, Cut<K> cut2, V v) {
        this.entriesByLowerBound.put(cut, new RangeMapEntry(cut, cut2, v));
    }

    public void remove(Range<K> range) {
        if (!range.isEmpty()) {
            RangeMapEntry rangeMapEntry;
            Entry lowerEntry = this.entriesByLowerBound.lowerEntry(range.lowerBound);
            if (lowerEntry != null) {
                rangeMapEntry = (RangeMapEntry) lowerEntry.getValue();
                if (rangeMapEntry.getUpperBound().compareTo(range.lowerBound) > 0) {
                    if (rangeMapEntry.getUpperBound().compareTo(range.upperBound) > 0) {
                        putRangeMapEntry(range.upperBound, rangeMapEntry.getUpperBound(), ((RangeMapEntry) lowerEntry.getValue()).getValue());
                    }
                    putRangeMapEntry(rangeMapEntry.getLowerBound(), range.lowerBound, ((RangeMapEntry) lowerEntry.getValue()).getValue());
                }
            }
            Entry lowerEntry2 = this.entriesByLowerBound.lowerEntry(range.upperBound);
            if (lowerEntry2 != null) {
                rangeMapEntry = (RangeMapEntry) lowerEntry2.getValue();
                if (rangeMapEntry.getUpperBound().compareTo(range.upperBound) > 0) {
                    putRangeMapEntry(range.upperBound, rangeMapEntry.getUpperBound(), ((RangeMapEntry) lowerEntry2.getValue()).getValue());
                    this.entriesByLowerBound.remove(range.lowerBound);
                }
            }
            this.entriesByLowerBound.subMap(range.lowerBound, range.upperBound).clear();
        }
    }

    public Map<Range<K>, V> asMapOfRanges() {
        return new AsMapOfRanges();
    }

    public RangeMap<K, V> subRangeMap(Range<K> range) {
        return range.equals(Range.all()) ? this : new SubRangeMap(range);
    }

    private RangeMap<K, V> emptySubRangeMap() {
        return EMPTY_SUB_RANGE_MAP;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof RangeMap)) {
            return false;
        }
        return asMapOfRanges().equals(((RangeMap) obj).asMapOfRanges());
    }

    public int hashCode() {
        return asMapOfRanges().hashCode();
    }

    public String toString() {
        return this.entriesByLowerBound.values().toString();
    }
}
