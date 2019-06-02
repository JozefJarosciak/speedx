package com.google.common.collect;

import ch.qos.logback.core.joran.action.Action;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.SortedMap;

@GwtCompatible(emulated = true, serializable = true)
public abstract class ImmutableSortedMap<K, V> extends ImmutableSortedMapFauxverideShim<K, V> implements NavigableMap<K, V> {
    private static final ImmutableSortedMap<Comparable, Object> NATURAL_EMPTY_MAP = new EmptyImmutableSortedMap(NATURAL_ORDER);
    private static final Comparator<Comparable> NATURAL_ORDER = Ordering.natural();
    private static final long serialVersionUID = 0;
    private transient ImmutableSortedMap<K, V> descendingMap;

    abstract ImmutableSortedMap<K, V> createDescendingMap();

    public abstract ImmutableSortedMap<K, V> headMap(K k, boolean z);

    public abstract ImmutableSortedSet<K> keySet();

    public abstract ImmutableSortedMap<K, V> tailMap(K k, boolean z);

    public abstract ImmutableCollection<V> values();

    static <K, V> ImmutableSortedMap<K, V> emptyMap(Comparator<? super K> comparator) {
        if (Ordering.natural().equals(comparator)) {
            return of();
        }
        return new EmptyImmutableSortedMap(comparator);
    }

    static <K, V> ImmutableSortedMap<K, V> fromSortedEntries(Comparator<? super K> comparator, int i, Entry<K, V>[] entryArr) {
        if (i == 0) {
            return emptyMap(comparator);
        }
        ImmutableList$Builder builder = ImmutableList.builder();
        ImmutableList$Builder builder2 = ImmutableList.builder();
        for (int i2 = 0; i2 < i; i2++) {
            Entry entry = entryArr[i2];
            builder.add(entry.getKey());
            builder2.add(entry.getValue());
        }
        return new RegularImmutableSortedMap(new RegularImmutableSortedSet(builder.build(), comparator), builder2.build());
    }

    static <K, V> ImmutableSortedMap<K, V> from(ImmutableSortedSet<K> immutableSortedSet, ImmutableList<V> immutableList) {
        if (immutableSortedSet.isEmpty()) {
            return emptyMap(immutableSortedSet.comparator());
        }
        return new RegularImmutableSortedMap((RegularImmutableSortedSet) immutableSortedSet, immutableList);
    }

    public static <K, V> ImmutableSortedMap<K, V> of() {
        return NATURAL_EMPTY_MAP;
    }

    public static <K extends Comparable<? super K>, V> ImmutableSortedMap<K, V> of(K k, V v) {
        return from(ImmutableSortedSet.of(k), ImmutableList.of(v));
    }

    public static <K extends Comparable<? super K>, V> ImmutableSortedMap<K, V> of(K k, V v, K k2, V v2) {
        return fromEntries(Ordering.natural(), false, 2, ImmutableMap.entryOf(k, v), ImmutableMap.entryOf(k2, v2));
    }

    public static <K extends Comparable<? super K>, V> ImmutableSortedMap<K, V> of(K k, V v, K k2, V v2, K k3, V v3) {
        return fromEntries(Ordering.natural(), false, 3, ImmutableMap.entryOf(k, v), ImmutableMap.entryOf(k2, v2), ImmutableMap.entryOf(k3, v3));
    }

    public static <K extends Comparable<? super K>, V> ImmutableSortedMap<K, V> of(K k, V v, K k2, V v2, K k3, V v3, K k4, V v4) {
        return fromEntries(Ordering.natural(), false, 4, ImmutableMap.entryOf(k, v), ImmutableMap.entryOf(k2, v2), ImmutableMap.entryOf(k3, v3), ImmutableMap.entryOf(k4, v4));
    }

    public static <K extends Comparable<? super K>, V> ImmutableSortedMap<K, V> of(K k, V v, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        return fromEntries(Ordering.natural(), false, 5, ImmutableMap.entryOf(k, v), ImmutableMap.entryOf(k2, v2), ImmutableMap.entryOf(k3, v3), ImmutableMap.entryOf(k4, v4), ImmutableMap.entryOf(k5, v5));
    }

    public static <K, V> ImmutableSortedMap<K, V> copyOf(Map<? extends K, ? extends V> map) {
        return copyOfInternal(map, Ordering.natural());
    }

    public static <K, V> ImmutableSortedMap<K, V> copyOf(Map<? extends K, ? extends V> map, Comparator<? super K> comparator) {
        return copyOfInternal(map, (Comparator) Preconditions.checkNotNull(comparator));
    }

    public static <K, V> ImmutableSortedMap<K, V> copyOfSorted(SortedMap<K, ? extends V> sortedMap) {
        Comparator comparator = sortedMap.comparator();
        if (comparator == null) {
            comparator = NATURAL_ORDER;
        }
        return copyOfInternal(sortedMap, comparator);
    }

    private static <K, V> ImmutableSortedMap<K, V> copyOfInternal(Map<? extends K, ? extends V> map, Comparator<? super K> comparator) {
        boolean z;
        if (map instanceof SortedMap) {
            Comparator comparator2 = ((SortedMap) map).comparator();
            boolean equals = comparator2 == null ? comparator == NATURAL_ORDER : comparator.equals(comparator2);
            z = equals;
        } else {
            z = false;
        }
        if (z && (map instanceof ImmutableSortedMap)) {
            ImmutableSortedMap<K, V> immutableSortedMap = (ImmutableSortedMap) map;
            if (!immutableSortedMap.isPartialView()) {
                return immutableSortedMap;
            }
        }
        Entry[] entryArr = (Entry[]) map.entrySet().toArray(new Entry[0]);
        return fromEntries(comparator, z, entryArr.length, entryArr);
    }

    static <K, V> ImmutableSortedMap<K, V> fromEntries(Comparator<? super K> comparator, boolean z, int i, Entry<K, V>... entryArr) {
        for (int i2 = 0; i2 < i; i2++) {
            Entry entry = entryArr[i2];
            entryArr[i2] = ImmutableMap.entryOf(entry.getKey(), entry.getValue());
        }
        if (!z) {
            sortEntries(comparator, i, entryArr);
            validateEntries(i, entryArr, comparator);
        }
        return fromSortedEntries(comparator, i, entryArr);
    }

    private static <K, V> void sortEntries(Comparator<? super K> comparator, int i, Entry<K, V>[] entryArr) {
        Arrays.sort(entryArr, 0, i, Ordering.from((Comparator) comparator).onKeys());
    }

    private static <K, V> void validateEntries(int i, Entry<K, V>[] entryArr, Comparator<? super K> comparator) {
        for (int i2 = 1; i2 < i; i2++) {
            ImmutableMap.checkNoConflict(comparator.compare(entryArr[i2 + -1].getKey(), entryArr[i2].getKey()) != 0, Action.KEY_ATTRIBUTE, entryArr[i2 - 1], entryArr[i2]);
        }
    }

    public static <K extends Comparable<?>, V> ImmutableSortedMap$Builder<K, V> naturalOrder() {
        return new ImmutableSortedMap$Builder(Ordering.natural());
    }

    public static <K, V> ImmutableSortedMap$Builder<K, V> orderedBy(Comparator<K> comparator) {
        return new ImmutableSortedMap$Builder(comparator);
    }

    public static <K extends Comparable<?>, V> ImmutableSortedMap$Builder<K, V> reverseOrder() {
        return new ImmutableSortedMap$Builder(Ordering.natural().reverse());
    }

    ImmutableSortedMap() {
    }

    ImmutableSortedMap(ImmutableSortedMap<K, V> immutableSortedMap) {
        this.descendingMap = immutableSortedMap;
    }

    public int size() {
        return values().size();
    }

    public boolean containsValue(Object obj) {
        return values().contains(obj);
    }

    boolean isPartialView() {
        return keySet().isPartialView() || values().isPartialView();
    }

    public ImmutableSet<Entry<K, V>> entrySet() {
        return super.entrySet();
    }

    public Comparator<? super K> comparator() {
        return keySet().comparator();
    }

    public K firstKey() {
        return keySet().first();
    }

    public K lastKey() {
        return keySet().last();
    }

    public ImmutableSortedMap<K, V> headMap(K k) {
        return headMap((Object) k, false);
    }

    public ImmutableSortedMap<K, V> subMap(K k, K k2) {
        return subMap((Object) k, true, (Object) k2, false);
    }

    public ImmutableSortedMap<K, V> subMap(K k, boolean z, K k2, boolean z2) {
        boolean z3;
        Preconditions.checkNotNull(k);
        Preconditions.checkNotNull(k2);
        if (comparator().compare(k, k2) <= 0) {
            z3 = true;
        } else {
            z3 = false;
        }
        Preconditions.checkArgument(z3, "expected fromKey <= toKey but %s > %s", new Object[]{k, k2});
        return headMap((Object) k2, z2).tailMap((Object) k, z);
    }

    public ImmutableSortedMap<K, V> tailMap(K k) {
        return tailMap((Object) k, true);
    }

    public Entry<K, V> lowerEntry(K k) {
        return headMap((Object) k, false).lastEntry();
    }

    public K lowerKey(K k) {
        return Maps.keyOrNull(lowerEntry(k));
    }

    public Entry<K, V> floorEntry(K k) {
        return headMap((Object) k, true).lastEntry();
    }

    public K floorKey(K k) {
        return Maps.keyOrNull(floorEntry(k));
    }

    public Entry<K, V> ceilingEntry(K k) {
        return tailMap((Object) k, true).firstEntry();
    }

    public K ceilingKey(K k) {
        return Maps.keyOrNull(ceilingEntry(k));
    }

    public Entry<K, V> higherEntry(K k) {
        return tailMap((Object) k, false).firstEntry();
    }

    public K higherKey(K k) {
        return Maps.keyOrNull(higherEntry(k));
    }

    public Entry<K, V> firstEntry() {
        return isEmpty() ? null : (Entry) entrySet().asList().get(0);
    }

    public Entry<K, V> lastEntry() {
        return isEmpty() ? null : (Entry) entrySet().asList().get(size() - 1);
    }

    @Deprecated
    public final Entry<K, V> pollFirstEntry() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final Entry<K, V> pollLastEntry() {
        throw new UnsupportedOperationException();
    }

    public ImmutableSortedMap<K, V> descendingMap() {
        ImmutableSortedMap<K, V> immutableSortedMap = this.descendingMap;
        if (immutableSortedMap != null) {
            return immutableSortedMap;
        }
        immutableSortedMap = createDescendingMap();
        this.descendingMap = immutableSortedMap;
        return immutableSortedMap;
    }

    public ImmutableSortedSet<K> navigableKeySet() {
        return keySet();
    }

    public ImmutableSortedSet<K> descendingKeySet() {
        return keySet().descendingSet();
    }

    Object writeReplace() {
        return new ImmutableSortedMap$SerializedForm(this);
    }
}
