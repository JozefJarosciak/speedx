package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;

public class ImmutableMultimap$Builder<K, V> {
    Multimap<K, V> builderMultimap = new ImmutableMultimap$BuilderMultimap();
    Comparator<? super K> keyComparator;
    Comparator<? super V> valueComparator;

    public ImmutableMultimap$Builder<K, V> put(K k, V v) {
        CollectPreconditions.checkEntryNotNull(k, v);
        this.builderMultimap.put(k, v);
        return this;
    }

    public ImmutableMultimap$Builder<K, V> put(Entry<? extends K, ? extends V> entry) {
        return put(entry.getKey(), entry.getValue());
    }

    public ImmutableMultimap$Builder<K, V> putAll(K k, Iterable<? extends V> iterable) {
        if (k == null) {
            String str = "null key in entry: null=";
            String valueOf = String.valueOf(Iterables.toString(iterable));
            throw new NullPointerException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        }
        Collection collection = this.builderMultimap.get(k);
        for (Object next : iterable) {
            CollectPreconditions.checkEntryNotNull(k, next);
            collection.add(next);
        }
        return this;
    }

    public ImmutableMultimap$Builder<K, V> putAll(K k, V... vArr) {
        return putAll((Object) k, Arrays.asList(vArr));
    }

    public ImmutableMultimap$Builder<K, V> putAll(Multimap<? extends K, ? extends V> multimap) {
        for (Entry entry : multimap.asMap().entrySet()) {
            putAll(entry.getKey(), (Iterable) entry.getValue());
        }
        return this;
    }

    public ImmutableMultimap$Builder<K, V> orderKeysBy(Comparator<? super K> comparator) {
        this.keyComparator = (Comparator) Preconditions.checkNotNull(comparator);
        return this;
    }

    public ImmutableMultimap$Builder<K, V> orderValuesBy(Comparator<? super V> comparator) {
        this.valueComparator = (Comparator) Preconditions.checkNotNull(comparator);
        return this;
    }

    public ImmutableMultimap<K, V> build() {
        if (this.valueComparator != null) {
            for (Collection collection : this.builderMultimap.asMap().values()) {
                Collections.sort((List) collection, this.valueComparator);
            }
        }
        if (this.keyComparator != null) {
            Multimap immutableMultimap$BuilderMultimap = new ImmutableMultimap$BuilderMultimap();
            List<Entry> newArrayList = Lists.newArrayList(this.builderMultimap.asMap().entrySet());
            Collections.sort(newArrayList, Ordering.from(this.keyComparator).onKeys());
            for (Entry entry : newArrayList) {
                immutableMultimap$BuilderMultimap.putAll(entry.getKey(), (Iterable) entry.getValue());
            }
            this.builderMultimap = immutableMultimap$BuilderMultimap;
        }
        return ImmutableMultimap.copyOf(this.builderMultimap);
    }
}
