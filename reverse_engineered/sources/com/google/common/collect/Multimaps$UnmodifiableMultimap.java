package com.google.common.collect;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class Multimaps$UnmodifiableMultimap<K, V> extends ForwardingMultimap<K, V> implements Serializable {
    private static final long serialVersionUID = 0;
    final Multimap<K, V> delegate;
    transient Collection<Entry<K, V>> entries;
    transient Set<K> keySet;
    transient Multiset<K> keys;
    transient Map<K, Collection<V>> map;
    transient Collection<V> values;

    /* renamed from: com.google.common.collect.Multimaps$UnmodifiableMultimap$1 */
    class C36831 implements Function<Collection<V>, Collection<V>> {
        C36831() {
        }

        public Collection<V> apply(Collection<V> collection) {
            return Multimaps.access$000(collection);
        }
    }

    Multimaps$UnmodifiableMultimap(Multimap<K, V> multimap) {
        this.delegate = (Multimap) Preconditions.checkNotNull(multimap);
    }

    protected Multimap<K, V> delegate() {
        return this.delegate;
    }

    public void clear() {
        throw new UnsupportedOperationException();
    }

    public Map<K, Collection<V>> asMap() {
        Map<K, Collection<V>> map = this.map;
        if (map != null) {
            return map;
        }
        map = Collections.unmodifiableMap(Maps.transformValues(this.delegate.asMap(), new C36831()));
        this.map = map;
        return map;
    }

    public Collection<Entry<K, V>> entries() {
        Collection<Entry<K, V>> collection = this.entries;
        if (collection != null) {
            return collection;
        }
        collection = Multimaps.access$100(this.delegate.entries());
        this.entries = collection;
        return collection;
    }

    public Collection<V> get(K k) {
        return Multimaps.access$000(this.delegate.get(k));
    }

    public Multiset<K> keys() {
        Multiset<K> multiset = this.keys;
        if (multiset != null) {
            return multiset;
        }
        multiset = Multisets.unmodifiableMultiset(this.delegate.keys());
        this.keys = multiset;
        return multiset;
    }

    public Set<K> keySet() {
        Set<K> set = this.keySet;
        if (set != null) {
            return set;
        }
        set = Collections.unmodifiableSet(this.delegate.keySet());
        this.keySet = set;
        return set;
    }

    public boolean put(K k, V v) {
        throw new UnsupportedOperationException();
    }

    public boolean putAll(K k, Iterable<? extends V> iterable) {
        throw new UnsupportedOperationException();
    }

    public boolean putAll(Multimap<? extends K, ? extends V> multimap) {
        throw new UnsupportedOperationException();
    }

    public boolean remove(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    public Collection<V> removeAll(Object obj) {
        throw new UnsupportedOperationException();
    }

    public Collection<V> replaceValues(K k, Iterable<? extends V> iterable) {
        throw new UnsupportedOperationException();
    }

    public Collection<V> values() {
        Collection<V> collection = this.values;
        if (collection != null) {
            return collection;
        }
        collection = Collections.unmodifiableCollection(this.delegate.values());
        this.values = collection;
        return collection;
    }
}
