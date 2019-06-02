package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map.Entry;

@GwtCompatible(emulated = true, serializable = true)
final class ImmutableEnumMap<K extends Enum<K>, V> extends ImmutableMap<K, V> {
    private final transient EnumMap<K, V> delegate;

    /* renamed from: com.google.common.collect.ImmutableEnumMap$1 */
    class C35871 extends ImmutableSet<K> {
        C35871() {
        }

        public boolean contains(Object obj) {
            return ImmutableEnumMap.this.delegate.containsKey(obj);
        }

        public int size() {
            return ImmutableEnumMap.this.size();
        }

        public UnmodifiableIterator<K> iterator() {
            return Iterators.unmodifiableIterator(ImmutableEnumMap.this.delegate.keySet().iterator());
        }

        boolean isPartialView() {
            return true;
        }
    }

    /* renamed from: com.google.common.collect.ImmutableEnumMap$2 */
    class C35892 extends ImmutableMapEntrySet<K, V> {

        /* renamed from: com.google.common.collect.ImmutableEnumMap$2$1 */
        class C35881 extends UnmodifiableIterator<Entry<K, V>> {
            private final Iterator<Entry<K, V>> backingIterator = ImmutableEnumMap.this.delegate.entrySet().iterator();

            C35881() {
            }

            public boolean hasNext() {
                return this.backingIterator.hasNext();
            }

            public Entry<K, V> next() {
                Entry entry = (Entry) this.backingIterator.next();
                return Maps.immutableEntry(entry.getKey(), entry.getValue());
            }
        }

        C35892() {
        }

        ImmutableMap<K, V> map() {
            return ImmutableEnumMap.this;
        }

        public UnmodifiableIterator<Entry<K, V>> iterator() {
            return new C35881();
        }
    }

    private static class EnumSerializedForm<K extends Enum<K>, V> implements Serializable {
        private static final long serialVersionUID = 0;
        final EnumMap<K, V> delegate;

        EnumSerializedForm(EnumMap<K, V> enumMap) {
            this.delegate = enumMap;
        }

        Object readResolve() {
            return new ImmutableEnumMap(this.delegate);
        }
    }

    static <K extends Enum<K>, V> ImmutableMap<K, V> asImmutable(EnumMap<K, V> enumMap) {
        switch (enumMap.size()) {
            case 0:
                return ImmutableMap.of();
            case 1:
                Entry entry = (Entry) Iterables.getOnlyElement(enumMap.entrySet());
                return ImmutableMap.of(entry.getKey(), entry.getValue());
            default:
                return new ImmutableEnumMap(enumMap);
        }
    }

    private ImmutableEnumMap(EnumMap<K, V> enumMap) {
        this.delegate = enumMap;
        Preconditions.checkArgument(!enumMap.isEmpty());
    }

    ImmutableSet<K> createKeySet() {
        return new C35871();
    }

    public int size() {
        return this.delegate.size();
    }

    public boolean containsKey(Object obj) {
        return this.delegate.containsKey(obj);
    }

    public V get(Object obj) {
        return this.delegate.get(obj);
    }

    ImmutableSet<Entry<K, V>> createEntrySet() {
        return new C35892();
    }

    boolean isPartialView() {
        return false;
    }

    Object writeReplace() {
        return new EnumSerializedForm(this.delegate);
    }
}
