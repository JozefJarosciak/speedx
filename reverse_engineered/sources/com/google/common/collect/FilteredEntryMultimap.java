package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

@GwtCompatible
class FilteredEntryMultimap<K, V> extends AbstractMultimap<K, V> implements FilteredMultimap<K, V> {
    final Predicate<? super Entry<K, V>> predicate;
    final Multimap<K, V> unfiltered;

    class AsMap extends ImprovedAbstractMap<K, Collection<V>> {

        /* renamed from: com.google.common.collect.FilteredEntryMultimap$AsMap$2 */
        class C35762 extends EntrySet<K, Collection<V>> {

            /* renamed from: com.google.common.collect.FilteredEntryMultimap$AsMap$2$1 */
            class C35751 extends AbstractIterator<Entry<K, Collection<V>>> {
                final Iterator<Entry<K, Collection<V>>> backingIterator = FilteredEntryMultimap.this.unfiltered.asMap().entrySet().iterator();

                C35751() {
                }

                protected Entry<K, Collection<V>> computeNext() {
                    while (this.backingIterator.hasNext()) {
                        Entry entry = (Entry) this.backingIterator.next();
                        Object key = entry.getKey();
                        Collection filterCollection = FilteredEntryMultimap.filterCollection((Collection) entry.getValue(), new ValuePredicate(key));
                        if (!filterCollection.isEmpty()) {
                            return Maps.immutableEntry(key, filterCollection);
                        }
                    }
                    return (Entry) endOfData();
                }
            }

            C35762() {
            }

            Map<K, Collection<V>> map() {
                return AsMap.this;
            }

            public Iterator<Entry<K, Collection<V>>> iterator() {
                return new C35751();
            }

            public boolean removeAll(Collection<?> collection) {
                return FilteredEntryMultimap.this.removeEntriesIf(Predicates.in(collection));
            }

            public boolean retainAll(Collection<?> collection) {
                return FilteredEntryMultimap.this.removeEntriesIf(Predicates.not(Predicates.in(collection)));
            }

            public int size() {
                return Iterators.size(iterator());
            }
        }

        AsMap() {
        }

        public boolean containsKey(Object obj) {
            return get(obj) != null;
        }

        public void clear() {
            FilteredEntryMultimap.this.clear();
        }

        public Collection<V> get(Object obj) {
            Collection collection = (Collection) FilteredEntryMultimap.this.unfiltered.asMap().get(obj);
            if (collection == null) {
                return null;
            }
            Collection<V> filterCollection = FilteredEntryMultimap.filterCollection(collection, new ValuePredicate(obj));
            if (filterCollection.isEmpty()) {
                filterCollection = null;
            }
            return filterCollection;
        }

        public Collection<V> remove(Object obj) {
            Collection collection = (Collection) FilteredEntryMultimap.this.unfiltered.asMap().get(obj);
            if (collection == null) {
                return null;
            }
            Object newArrayList = Lists.newArrayList();
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                Object next = it.next();
                if (FilteredEntryMultimap.this.satisfies(obj, next)) {
                    it.remove();
                    newArrayList.add(next);
                }
            }
            if (newArrayList.isEmpty()) {
                return null;
            }
            if (FilteredEntryMultimap.this.unfiltered instanceof SetMultimap) {
                return Collections.unmodifiableSet(Sets.newLinkedHashSet(newArrayList));
            }
            return Collections.unmodifiableList(newArrayList);
        }

        Set<K> createKeySet() {
            return new KeySet<K, Collection<V>>(this) {
                public boolean removeAll(Collection<?> collection) {
                    return FilteredEntryMultimap.this.removeEntriesIf(Maps.keyPredicateOnEntries(Predicates.in(collection)));
                }

                public boolean retainAll(Collection<?> collection) {
                    return FilteredEntryMultimap.this.removeEntriesIf(Maps.keyPredicateOnEntries(Predicates.not(Predicates.in(collection))));
                }

                public boolean remove(Object obj) {
                    return AsMap.this.remove(obj) != null;
                }
            };
        }

        Set<Entry<K, Collection<V>>> createEntrySet() {
            return new C35762();
        }

        Collection<Collection<V>> createValues() {
            return new Values<K, Collection<V>>(this) {
                public boolean remove(Object obj) {
                    if (obj instanceof Collection) {
                        Collection collection = (Collection) obj;
                        Iterator it = FilteredEntryMultimap.this.unfiltered.asMap().entrySet().iterator();
                        while (it.hasNext()) {
                            Entry entry = (Entry) it.next();
                            Collection filterCollection = FilteredEntryMultimap.filterCollection((Collection) entry.getValue(), new ValuePredicate(entry.getKey()));
                            if (!filterCollection.isEmpty() && collection.equals(filterCollection)) {
                                if (filterCollection.size() == ((Collection) entry.getValue()).size()) {
                                    it.remove();
                                } else {
                                    filterCollection.clear();
                                }
                                return true;
                            }
                        }
                    }
                    return false;
                }

                public boolean removeAll(Collection<?> collection) {
                    return FilteredEntryMultimap.this.removeEntriesIf(Maps.valuePredicateOnEntries(Predicates.in(collection)));
                }

                public boolean retainAll(Collection<?> collection) {
                    return FilteredEntryMultimap.this.removeEntriesIf(Maps.valuePredicateOnEntries(Predicates.not(Predicates.in(collection))));
                }
            };
        }
    }

    class Keys extends Multimaps$Keys<K, V> {

        /* renamed from: com.google.common.collect.FilteredEntryMultimap$Keys$1 */
        class C35791 extends Multisets$EntrySet<K> {
            C35791() {
            }

            Multiset<K> multiset() {
                return Keys.this;
            }

            public Iterator<Multiset.Entry<K>> iterator() {
                return Keys.this.entryIterator();
            }

            public int size() {
                return FilteredEntryMultimap.this.keySet().size();
            }

            private boolean removeEntriesIf(final Predicate<? super Multiset.Entry<K>> predicate) {
                return FilteredEntryMultimap.this.removeEntriesIf(new Predicate<Entry<K, Collection<V>>>() {
                    public boolean apply(Entry<K, Collection<V>> entry) {
                        return predicate.apply(Multisets.immutableEntry(entry.getKey(), ((Collection) entry.getValue()).size()));
                    }
                });
            }

            public boolean removeAll(Collection<?> collection) {
                return removeEntriesIf(Predicates.in(collection));
            }

            public boolean retainAll(Collection<?> collection) {
                return removeEntriesIf(Predicates.not(Predicates.in(collection)));
            }
        }

        Keys() {
            super(FilteredEntryMultimap.this);
        }

        public int remove(Object obj, int i) {
            CollectPreconditions.checkNonnegative(i, "occurrences");
            if (i == 0) {
                return count(obj);
            }
            Collection collection = (Collection) FilteredEntryMultimap.this.unfiltered.asMap().get(obj);
            if (collection == null) {
                return 0;
            }
            Iterator it = collection.iterator();
            int i2 = 0;
            while (it.hasNext()) {
                if (FilteredEntryMultimap.this.satisfies(obj, it.next())) {
                    i2++;
                    if (i2 <= i) {
                        it.remove();
                    }
                }
            }
            return i2;
        }

        public Set<Multiset.Entry<K>> entrySet() {
            return new C35791();
        }
    }

    final class ValuePredicate implements Predicate<V> {
        private final K key;

        ValuePredicate(K k) {
            this.key = k;
        }

        public boolean apply(V v) {
            return FilteredEntryMultimap.this.satisfies(this.key, v);
        }
    }

    FilteredEntryMultimap(Multimap<K, V> multimap, Predicate<? super Entry<K, V>> predicate) {
        this.unfiltered = (Multimap) Preconditions.checkNotNull(multimap);
        this.predicate = (Predicate) Preconditions.checkNotNull(predicate);
    }

    public Multimap<K, V> unfiltered() {
        return this.unfiltered;
    }

    public Predicate<? super Entry<K, V>> entryPredicate() {
        return this.predicate;
    }

    public int size() {
        return entries().size();
    }

    private boolean satisfies(K k, V v) {
        return this.predicate.apply(Maps.immutableEntry(k, v));
    }

    static <E> Collection<E> filterCollection(Collection<E> collection, Predicate<? super E> predicate) {
        if (collection instanceof Set) {
            return Sets.filter((Set) collection, (Predicate) predicate);
        }
        return Collections2.filter(collection, predicate);
    }

    public boolean containsKey(Object obj) {
        return asMap().get(obj) != null;
    }

    public Collection<V> removeAll(Object obj) {
        return (Collection) MoreObjects.firstNonNull(asMap().remove(obj), unmodifiableEmptyCollection());
    }

    Collection<V> unmodifiableEmptyCollection() {
        return this.unfiltered instanceof SetMultimap ? Collections.emptySet() : Collections.emptyList();
    }

    public void clear() {
        entries().clear();
    }

    public Collection<V> get(K k) {
        return filterCollection(this.unfiltered.get(k), new ValuePredicate(k));
    }

    Collection<Entry<K, V>> createEntries() {
        return filterCollection(this.unfiltered.entries(), this.predicate);
    }

    Collection<V> createValues() {
        return new FilteredMultimapValues(this);
    }

    Iterator<Entry<K, V>> entryIterator() {
        throw new AssertionError("should never be called");
    }

    Map<K, Collection<V>> createAsMap() {
        return new AsMap();
    }

    public Set<K> keySet() {
        return asMap().keySet();
    }

    boolean removeEntriesIf(Predicate<? super Entry<K, Collection<V>>> predicate) {
        Iterator it = this.unfiltered.asMap().entrySet().iterator();
        boolean z = false;
        while (it.hasNext()) {
            boolean z2;
            Entry entry = (Entry) it.next();
            Object key = entry.getKey();
            Collection filterCollection = filterCollection((Collection) entry.getValue(), new ValuePredicate(key));
            if (filterCollection.isEmpty() || !predicate.apply(Maps.immutableEntry(key, filterCollection))) {
                z2 = z;
            } else {
                if (filterCollection.size() == ((Collection) entry.getValue()).size()) {
                    it.remove();
                } else {
                    filterCollection.clear();
                }
                z2 = true;
            }
            z = z2;
        }
        return z;
    }

    Multiset<K> createKeys() {
        return new Keys();
    }
}
