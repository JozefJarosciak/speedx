package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

class Multimaps$MapMultimap<K, V> extends AbstractMultimap<K, V> implements SetMultimap<K, V>, Serializable {
    private static final long serialVersionUID = 7845222491160860175L;
    final Map<K, V> map;

    Multimaps$MapMultimap(Map<K, V> map) {
        this.map = (Map) Preconditions.checkNotNull(map);
    }

    public int size() {
        return this.map.size();
    }

    public boolean containsKey(Object obj) {
        return this.map.containsKey(obj);
    }

    public boolean containsValue(Object obj) {
        return this.map.containsValue(obj);
    }

    public boolean containsEntry(Object obj, Object obj2) {
        return this.map.entrySet().contains(Maps.immutableEntry(obj, obj2));
    }

    public Set<V> get(final K k) {
        return new ImprovedAbstractSet<V>() {

            /* renamed from: com.google.common.collect.Multimaps$MapMultimap$1$1 */
            class C36801 implements Iterator<V> {
                /* renamed from: i */
                int f14268i;

                C36801() {
                }

                public boolean hasNext() {
                    return this.f14268i == 0 && Multimaps$MapMultimap.this.map.containsKey(k);
                }

                public V next() {
                    if (hasNext()) {
                        this.f14268i++;
                        return Multimaps$MapMultimap.this.map.get(k);
                    }
                    throw new NoSuchElementException();
                }

                public void remove() {
                    boolean z = true;
                    if (this.f14268i != 1) {
                        z = false;
                    }
                    CollectPreconditions.checkRemove(z);
                    this.f14268i = -1;
                    Multimaps$MapMultimap.this.map.remove(k);
                }
            }

            public Iterator<V> iterator() {
                return new C36801();
            }

            public int size() {
                return Multimaps$MapMultimap.this.map.containsKey(k) ? 1 : 0;
            }
        };
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

    public Set<V> replaceValues(K k, Iterable<? extends V> iterable) {
        throw new UnsupportedOperationException();
    }

    public boolean remove(Object obj, Object obj2) {
        return this.map.entrySet().remove(Maps.immutableEntry(obj, obj2));
    }

    public Set<V> removeAll(Object obj) {
        Set hashSet = new HashSet(2);
        if (this.map.containsKey(obj)) {
            hashSet.add(this.map.remove(obj));
        }
        return hashSet;
    }

    public void clear() {
        this.map.clear();
    }

    public Set<K> keySet() {
        return this.map.keySet();
    }

    public Collection<V> values() {
        return this.map.values();
    }

    public Set<Entry<K, V>> entries() {
        return this.map.entrySet();
    }

    Iterator<Entry<K, V>> entryIterator() {
        return this.map.entrySet().iterator();
    }

    Map<K, Collection<V>> createAsMap() {
        return new Multimaps$AsMap(this);
    }

    public int hashCode() {
        return this.map.hashCode();
    }
}
