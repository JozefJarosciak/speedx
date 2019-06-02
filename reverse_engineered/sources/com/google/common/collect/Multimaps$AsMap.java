package com.google.common.collect;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

final class Multimaps$AsMap<K, V> extends ImprovedAbstractMap<K, Collection<V>> {
    private final Multimap<K, V> multimap;

    /* renamed from: com.google.common.collect.Multimaps$AsMap$EntrySet */
    class EntrySet extends EntrySet<K, Collection<V>> {

        /* renamed from: com.google.common.collect.Multimaps$AsMap$EntrySet$1 */
        class C36771 implements Function<K, Collection<V>> {
            C36771() {
            }

            public Collection<V> apply(K k) {
                return Multimaps$AsMap.this.multimap.get(k);
            }
        }

        EntrySet() {
        }

        Map<K, Collection<V>> map() {
            return Multimaps$AsMap.this;
        }

        public Iterator<Entry<K, Collection<V>>> iterator() {
            return Maps.asMapEntryIterator(Multimaps$AsMap.this.multimap.keySet(), new C36771());
        }

        public boolean remove(Object obj) {
            if (!contains(obj)) {
                return false;
            }
            Multimaps$AsMap.this.removeValuesForKey(((Entry) obj).getKey());
            return true;
        }
    }

    Multimaps$AsMap(Multimap<K, V> multimap) {
        this.multimap = (Multimap) Preconditions.checkNotNull(multimap);
    }

    public int size() {
        return this.multimap.keySet().size();
    }

    protected Set<Entry<K, Collection<V>>> createEntrySet() {
        return new EntrySet();
    }

    void removeValuesForKey(Object obj) {
        this.multimap.keySet().remove(obj);
    }

    public Collection<V> get(Object obj) {
        return containsKey(obj) ? this.multimap.get(obj) : null;
    }

    public Collection<V> remove(Object obj) {
        return containsKey(obj) ? this.multimap.removeAll(obj) : null;
    }

    public Set<K> keySet() {
        return this.multimap.keySet();
    }

    public boolean isEmpty() {
        return this.multimap.isEmpty();
    }

    public boolean containsKey(Object obj) {
        return this.multimap.containsKey(obj);
    }

    public void clear() {
        this.multimap.clear();
    }
}
