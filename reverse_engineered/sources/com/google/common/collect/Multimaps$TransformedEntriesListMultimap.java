package com.google.common.collect;

import com.google.common.collect.Maps.EntryTransformer;
import java.util.Collection;
import java.util.List;

final class Multimaps$TransformedEntriesListMultimap<K, V1, V2> extends Multimaps$TransformedEntriesMultimap<K, V1, V2> implements ListMultimap<K, V2> {
    Multimaps$TransformedEntriesListMultimap(ListMultimap<K, V1> listMultimap, EntryTransformer<? super K, ? super V1, V2> entryTransformer) {
        super(listMultimap, entryTransformer);
    }

    List<V2> transform(K k, Collection<V1> collection) {
        return Lists.transform((List) collection, Maps.asValueToValueFunction(this.transformer, k));
    }

    public List<V2> get(K k) {
        return transform((Object) k, this.fromMultimap.get(k));
    }

    public List<V2> removeAll(Object obj) {
        return transform(obj, this.fromMultimap.removeAll(obj));
    }

    public List<V2> replaceValues(K k, Iterable<? extends V2> iterable) {
        throw new UnsupportedOperationException();
    }
}
