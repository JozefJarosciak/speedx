package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

final class ImmutableMap$MapViewOfValuesAsSingletonSets<K, V> extends ImmutableMap<K, ImmutableSet<V>> {
    private final ImmutableMap<K, V> delegate;

    /* renamed from: com.google.common.collect.ImmutableMap$MapViewOfValuesAsSingletonSets$1 */
    class C35931 extends ImmutableMapEntrySet<K, ImmutableSet<V>> {
        C35931() {
        }

        ImmutableMap<K, ImmutableSet<V>> map() {
            return ImmutableMap$MapViewOfValuesAsSingletonSets.this;
        }

        public UnmodifiableIterator<Entry<K, ImmutableSet<V>>> iterator() {
            final Iterator it = ImmutableMap$MapViewOfValuesAsSingletonSets.this.delegate.entrySet().iterator();
            return new UnmodifiableIterator<Entry<K, ImmutableSet<V>>>() {
                public boolean hasNext() {
                    return it.hasNext();
                }

                public Entry<K, ImmutableSet<V>> next() {
                    final Entry entry = (Entry) it.next();
                    return new AbstractMapEntry<K, ImmutableSet<V>>() {
                        public K getKey() {
                            return entry.getKey();
                        }

                        public ImmutableSet<V> getValue() {
                            return ImmutableSet.of(entry.getValue());
                        }
                    };
                }
            };
        }
    }

    public /* bridge */ /* synthetic */ Set entrySet() {
        return super.entrySet();
    }

    public /* bridge */ /* synthetic */ Set keySet() {
        return super.keySet();
    }

    public /* bridge */ /* synthetic */ Collection values() {
        return super.values();
    }

    ImmutableMap$MapViewOfValuesAsSingletonSets(ImmutableMap<K, V> immutableMap) {
        this.delegate = (ImmutableMap) Preconditions.checkNotNull(immutableMap);
    }

    public int size() {
        return this.delegate.size();
    }

    public boolean containsKey(Object obj) {
        return this.delegate.containsKey(obj);
    }

    public ImmutableSet<V> get(Object obj) {
        Object obj2 = this.delegate.get(obj);
        return obj2 == null ? null : ImmutableSet.of(obj2);
    }

    boolean isPartialView() {
        return false;
    }

    ImmutableSet<Entry<K, ImmutableSet<V>>> createEntrySet() {
        return new C35931();
    }
}
