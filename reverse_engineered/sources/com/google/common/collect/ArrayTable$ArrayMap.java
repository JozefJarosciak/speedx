package com.google.common.collect;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

abstract class ArrayTable$ArrayMap<K, V> extends ImprovedAbstractMap<K, V> {
    private final ImmutableMap<K, Integer> keyIndex;

    /* renamed from: com.google.common.collect.ArrayTable$ArrayMap$1 */
    class C35561 extends EntrySet<K, V> {
        C35561() {
        }

        Map<K, V> map() {
            return ArrayTable$ArrayMap.this;
        }

        public Iterator<Entry<K, V>> iterator() {
            return new AbstractIndexedListIterator<Entry<K, V>>(size()) {
                protected Entry<K, V> get(final int i) {
                    return new AbstractMapEntry<K, V>() {
                        public K getKey() {
                            return ArrayTable$ArrayMap.this.getKey(i);
                        }

                        public V getValue() {
                            return ArrayTable$ArrayMap.this.getValue(i);
                        }

                        public V setValue(V v) {
                            return ArrayTable$ArrayMap.this.setValue(i, v);
                        }
                    };
                }
            };
        }
    }

    abstract String getKeyRole();

    abstract V getValue(int i);

    abstract V setValue(int i, V v);

    private ArrayTable$ArrayMap(ImmutableMap<K, Integer> immutableMap) {
        this.keyIndex = immutableMap;
    }

    public Set<K> keySet() {
        return this.keyIndex.keySet();
    }

    K getKey(int i) {
        return this.keyIndex.keySet().asList().get(i);
    }

    public int size() {
        return this.keyIndex.size();
    }

    public boolean isEmpty() {
        return this.keyIndex.isEmpty();
    }

    protected Set<Entry<K, V>> createEntrySet() {
        return new C35561();
    }

    public boolean containsKey(Object obj) {
        return this.keyIndex.containsKey(obj);
    }

    public V get(Object obj) {
        Integer num = (Integer) this.keyIndex.get(obj);
        if (num == null) {
            return null;
        }
        return getValue(num.intValue());
    }

    public V put(K k, V v) {
        Integer num = (Integer) this.keyIndex.get(k);
        if (num != null) {
            return setValue(num.intValue(), v);
        }
        String valueOf = String.valueOf(String.valueOf(getKeyRole()));
        String valueOf2 = String.valueOf(String.valueOf(k));
        String valueOf3 = String.valueOf(String.valueOf(this.keyIndex.keySet()));
        throw new IllegalArgumentException(new StringBuilder(((valueOf.length() + 9) + valueOf2.length()) + valueOf3.length()).append(valueOf).append(" ").append(valueOf2).append(" not in ").append(valueOf3).toString());
    }

    public V remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    public void clear() {
        throw new UnsupportedOperationException();
    }
}
