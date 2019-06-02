package com.google.common.collect;

import com.google.common.collect.Multiset.Entry;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

class Multimaps$Keys<K, V> extends AbstractMultiset<K> {
    final Multimap<K, V> multimap;

    /* renamed from: com.google.common.collect.Multimaps$Keys$KeysEntrySet */
    class KeysEntrySet extends Multisets$EntrySet<K> {
        KeysEntrySet() {
        }

        Multiset<K> multiset() {
            return Multimaps$Keys.this;
        }

        public Iterator<Entry<K>> iterator() {
            return Multimaps$Keys.this.entryIterator();
        }

        public int size() {
            return Multimaps$Keys.this.distinctElements();
        }

        public boolean isEmpty() {
            return Multimaps$Keys.this.multimap.isEmpty();
        }

        public boolean contains(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) obj;
            Collection collection = (Collection) Multimaps$Keys.this.multimap.asMap().get(entry.getElement());
            if (collection == null || collection.size() != entry.getCount()) {
                return false;
            }
            return true;
        }

        public boolean remove(Object obj) {
            if (obj instanceof Entry) {
                Entry entry = (Entry) obj;
                Collection collection = (Collection) Multimaps$Keys.this.multimap.asMap().get(entry.getElement());
                if (collection != null && collection.size() == entry.getCount()) {
                    collection.clear();
                    return true;
                }
            }
            return false;
        }
    }

    Multimaps$Keys(Multimap<K, V> multimap) {
        this.multimap = multimap;
    }

    Iterator<Entry<K>> entryIterator() {
        return new TransformedIterator<Map.Entry<K, Collection<V>>, Entry<K>>(this.multimap.asMap().entrySet().iterator()) {
            Entry<K> transform(final Map.Entry<K, Collection<V>> entry) {
                return new Multisets$AbstractEntry<K>() {
                    public K getElement() {
                        return entry.getKey();
                    }

                    public int getCount() {
                        return ((Collection) entry.getValue()).size();
                    }
                };
            }
        };
    }

    int distinctElements() {
        return this.multimap.asMap().size();
    }

    Set<Entry<K>> createEntrySet() {
        return new KeysEntrySet();
    }

    public boolean contains(Object obj) {
        return this.multimap.containsKey(obj);
    }

    public Iterator<K> iterator() {
        return Maps.keyIterator(this.multimap.entries().iterator());
    }

    public int count(Object obj) {
        Collection collection = (Collection) Maps.safeGet(this.multimap.asMap(), obj);
        return collection == null ? 0 : collection.size();
    }

    public int remove(Object obj, int i) {
        int i2 = 0;
        CollectPreconditions.checkNonnegative(i, "occurrences");
        if (i == 0) {
            return count(obj);
        }
        Collection collection = (Collection) Maps.safeGet(this.multimap.asMap(), obj);
        if (collection == null) {
            return 0;
        }
        int size = collection.size();
        if (i >= size) {
            collection.clear();
        } else {
            Iterator it = collection.iterator();
            while (i2 < i) {
                it.next();
                it.remove();
                i2++;
            }
        }
        return size;
    }

    public void clear() {
        this.multimap.clear();
    }

    public Set<K> elementSet() {
        return this.multimap.keySet();
    }
}
