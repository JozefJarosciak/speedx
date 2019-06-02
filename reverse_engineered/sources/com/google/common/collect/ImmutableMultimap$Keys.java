package com.google.common.collect;

import com.google.common.collect.Multiset.Entry;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

class ImmutableMultimap$Keys extends ImmutableMultiset<K> {
    final /* synthetic */ ImmutableMultimap this$0;

    ImmutableMultimap$Keys(ImmutableMultimap immutableMultimap) {
        this.this$0 = immutableMultimap;
    }

    public boolean contains(Object obj) {
        return this.this$0.containsKey(obj);
    }

    public int count(Object obj) {
        Collection collection = (Collection) this.this$0.map.get(obj);
        return collection == null ? 0 : collection.size();
    }

    public Set<K> elementSet() {
        return this.this$0.keySet();
    }

    public int size() {
        return this.this$0.size();
    }

    Entry<K> getEntry(int i) {
        Map.Entry entry = (Map.Entry) this.this$0.map.entrySet().asList().get(i);
        return Multisets.immutableEntry(entry.getKey(), ((Collection) entry.getValue()).size());
    }

    boolean isPartialView() {
        return true;
    }
}
