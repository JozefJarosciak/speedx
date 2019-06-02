package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.SortedSet;

class Multimaps$CustomSortedSetMultimap<K, V> extends AbstractSortedSetMultimap<K, V> {
    @GwtIncompatible("not needed in emulated source")
    private static final long serialVersionUID = 0;
    transient Supplier<? extends SortedSet<V>> factory;
    transient Comparator<? super V> valueComparator;

    Multimaps$CustomSortedSetMultimap(Map<K, Collection<V>> map, Supplier<? extends SortedSet<V>> supplier) {
        super(map);
        this.factory = (Supplier) Preconditions.checkNotNull(supplier);
        this.valueComparator = ((SortedSet) supplier.get()).comparator();
    }

    protected SortedSet<V> createCollection() {
        return (SortedSet) this.factory.get();
    }

    public Comparator<? super V> valueComparator() {
        return this.valueComparator;
    }

    @GwtIncompatible("java.io.ObjectOutputStream")
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(this.factory);
        objectOutputStream.writeObject(backingMap());
    }

    @GwtIncompatible("java.io.ObjectInputStream")
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.factory = (Supplier) objectInputStream.readObject();
        this.valueComparator = ((SortedSet) this.factory.get()).comparator();
        setMap((Map) objectInputStream.readObject());
    }
}
