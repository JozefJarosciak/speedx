package com.google.common.collect;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps.EntryTransformer;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class Multimaps$TransformedEntriesMultimap<K, V1, V2> extends AbstractMultimap<K, V2> {
    final Multimap<K, V1> fromMultimap;
    final EntryTransformer<? super K, ? super V1, V2> transformer;

    /* renamed from: com.google.common.collect.Multimaps$TransformedEntriesMultimap$1 */
    class C36821 implements EntryTransformer<K, Collection<V1>, Collection<V2>> {
        C36821() {
        }

        public Collection<V2> transformEntry(K k, Collection<V1> collection) {
            return Multimaps$TransformedEntriesMultimap.this.transform(k, collection);
        }
    }

    Multimaps$TransformedEntriesMultimap(Multimap<K, V1> multimap, EntryTransformer<? super K, ? super V1, V2> entryTransformer) {
        this.fromMultimap = (Multimap) Preconditions.checkNotNull(multimap);
        this.transformer = (EntryTransformer) Preconditions.checkNotNull(entryTransformer);
    }

    Collection<V2> transform(K k, Collection<V1> collection) {
        Function asValueToValueFunction = Maps.asValueToValueFunction(this.transformer, k);
        if (collection instanceof List) {
            return Lists.transform((List) collection, asValueToValueFunction);
        }
        return Collections2.transform(collection, asValueToValueFunction);
    }

    Map<K, Collection<V2>> createAsMap() {
        return Maps.transformEntries(this.fromMultimap.asMap(), new C36821());
    }

    public void clear() {
        this.fromMultimap.clear();
    }

    public boolean containsKey(Object obj) {
        return this.fromMultimap.containsKey(obj);
    }

    Iterator<Entry<K, V2>> entryIterator() {
        return Iterators.transform(this.fromMultimap.entries().iterator(), Maps.asEntryToEntryFunction(this.transformer));
    }

    public Collection<V2> get(K k) {
        return transform(k, this.fromMultimap.get(k));
    }

    public boolean isEmpty() {
        return this.fromMultimap.isEmpty();
    }

    public Set<K> keySet() {
        return this.fromMultimap.keySet();
    }

    public Multiset<K> keys() {
        return this.fromMultimap.keys();
    }

    public boolean put(K k, V2 v2) {
        throw new UnsupportedOperationException();
    }

    public boolean putAll(K k, Iterable<? extends V2> iterable) {
        throw new UnsupportedOperationException();
    }

    public boolean putAll(Multimap<? extends K, ? extends V2> multimap) {
        throw new UnsupportedOperationException();
    }

    public boolean remove(Object obj, Object obj2) {
        return get(obj).remove(obj2);
    }

    public Collection<V2> removeAll(Object obj) {
        return transform(obj, this.fromMultimap.removeAll(obj));
    }

    public Collection<V2> replaceValues(K k, Iterable<? extends V2> iterable) {
        throw new UnsupportedOperationException();
    }

    public int size() {
        return this.fromMultimap.size();
    }

    Collection<V2> createValues() {
        return Collections2.transform(this.fromMultimap.entries(), Maps.asEntryToValueFunction(this.transformer));
    }
}
