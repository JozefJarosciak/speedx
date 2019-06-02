package com.alibaba.fastjson.util;

public class IdentityHashMap<K, V> {
    public static final int DEFAULT_TABLE_SIZE = 1024;
    private final Entry<K, V>[] buckets;
    private final int indexMask;

    protected static final class Entry<K, V> {
        public final int hashCode;
        public final K key;
        public final Entry<K, V> next;
        public V value;

        public Entry(K k, V v, int i, Entry<K, V> entry) {
            this.key = k;
            this.value = v;
            this.next = entry;
            this.hashCode = i;
        }
    }

    public IdentityHashMap() {
        this(1024);
    }

    public IdentityHashMap(int i) {
        this.indexMask = i - 1;
        this.buckets = new Entry[i];
    }

    public final V get(K k) {
        for (Entry entry = this.buckets[System.identityHashCode(k) & this.indexMask]; entry != null; entry = entry.next) {
            if (k == entry.key) {
                return entry.value;
            }
        }
        return null;
    }

    public boolean put(K k, V v) {
        int identityHashCode = System.identityHashCode(k);
        int i = identityHashCode & this.indexMask;
        for (Entry entry = this.buckets[i]; entry != null; entry = entry.next) {
            if (k == entry.key) {
                entry.value = v;
                return true;
            }
        }
        this.buckets[i] = new Entry(k, v, identityHashCode, this.buckets[i]);
        return false;
    }

    public int size() {
        int i = 0;
        int i2 = 0;
        while (i < this.buckets.length) {
            Entry entry = this.buckets[i];
            while (entry != null) {
                int i3 = i2 + 1;
                entry = entry.next;
                i2 = i3;
            }
            i++;
        }
        return i2;
    }
}
