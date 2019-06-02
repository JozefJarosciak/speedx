package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;

public final class ImmutableSetMultimap$Builder<K, V> extends ImmutableMultimap$Builder<K, V> {
    public ImmutableSetMultimap$Builder() {
        this.builderMultimap = new ImmutableSetMultimap$BuilderMultimap();
    }

    public ImmutableSetMultimap$Builder<K, V> put(K k, V v) {
        this.builderMultimap.put(Preconditions.checkNotNull(k), Preconditions.checkNotNull(v));
        return this;
    }

    public ImmutableSetMultimap$Builder<K, V> put(Entry<? extends K, ? extends V> entry) {
        this.builderMultimap.put(Preconditions.checkNotNull(entry.getKey()), Preconditions.checkNotNull(entry.getValue()));
        return this;
    }

    public ImmutableSetMultimap$Builder<K, V> putAll(K k, Iterable<? extends V> iterable) {
        Collection collection = this.builderMultimap.get(Preconditions.checkNotNull(k));
        for (Object checkNotNull : iterable) {
            collection.add(Preconditions.checkNotNull(checkNotNull));
        }
        return this;
    }

    public ImmutableSetMultimap$Builder<K, V> putAll(K k, V... vArr) {
        return putAll((Object) k, Arrays.asList(vArr));
    }

    public ImmutableSetMultimap$Builder<K, V> putAll(Multimap<? extends K, ? extends V> multimap) {
        for (Entry entry : multimap.asMap().entrySet()) {
            putAll(entry.getKey(), (Iterable) entry.getValue());
        }
        return this;
    }

    public ImmutableSetMultimap$Builder<K, V> orderKeysBy(Comparator<? super K> comparator) {
        this.keyComparator = (Comparator) Preconditions.checkNotNull(comparator);
        return this;
    }

    public ImmutableSetMultimap$Builder<K, V> orderValuesBy(Comparator<? super V> comparator) {
        super.orderValuesBy(comparator);
        return this;
    }

    public ImmutableSetMultimap<K, V> build() {
        if (this.keyComparator != null) {
            Multimap immutableSetMultimap$BuilderMultimap = new ImmutableSetMultimap$BuilderMultimap();
            List<Entry> newArrayList = Lists.newArrayList(this.builderMultimap.asMap().entrySet());
            Collections.sort(newArrayList, Ordering.from(this.keyComparator).onKeys());
            for (Entry entry : newArrayList) {
                immutableSetMultimap$BuilderMultimap.putAll(entry.getKey(), (Iterable) entry.getValue());
            }
            this.builderMultimap = immutableSetMultimap$BuilderMultimap;
        }
        return ImmutableSetMultimap.access$000(this.builderMultimap, this.valueComparator);
    }
}
