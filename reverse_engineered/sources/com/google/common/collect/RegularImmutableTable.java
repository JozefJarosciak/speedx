package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet.Builder;
import com.google.common.collect.Table.Cell;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

@GwtCompatible
abstract class RegularImmutableTable<R, C, V> extends ImmutableTable<R, C, V> {

    private final class CellSet extends ImmutableSet<Cell<R, C, V>> {

        /* renamed from: com.google.common.collect.RegularImmutableTable$CellSet$1 */
        class C37001 extends ImmutableAsList<Cell<R, C, V>> {
            C37001() {
            }

            public Cell<R, C, V> get(int i) {
                return RegularImmutableTable.this.getCell(i);
            }

            ImmutableCollection<Cell<R, C, V>> delegateCollection() {
                return CellSet.this;
            }
        }

        private CellSet() {
        }

        public int size() {
            return RegularImmutableTable.this.size();
        }

        public UnmodifiableIterator<Cell<R, C, V>> iterator() {
            return asList().iterator();
        }

        ImmutableList<Cell<R, C, V>> createAsList() {
            return new C37001();
        }

        public boolean contains(Object obj) {
            if (!(obj instanceof Cell)) {
                return false;
            }
            Cell cell = (Cell) obj;
            Object obj2 = RegularImmutableTable.this.get(cell.getRowKey(), cell.getColumnKey());
            if (obj2 == null || !obj2.equals(cell.getValue())) {
                return false;
            }
            return true;
        }

        boolean isPartialView() {
            return false;
        }
    }

    private final class Values extends ImmutableList<V> {
        private Values() {
        }

        public int size() {
            return RegularImmutableTable.this.size();
        }

        public V get(int i) {
            return RegularImmutableTable.this.getValue(i);
        }

        boolean isPartialView() {
            return true;
        }
    }

    abstract Cell<R, C, V> getCell(int i);

    abstract V getValue(int i);

    RegularImmutableTable() {
    }

    final ImmutableSet<Cell<R, C, V>> createCellSet() {
        return isEmpty() ? ImmutableSet.of() : new CellSet();
    }

    final ImmutableCollection<V> createValues() {
        return isEmpty() ? ImmutableList.of() : new Values();
    }

    static <R, C, V> RegularImmutableTable<R, C, V> forCells(List<Cell<R, C, V>> list, final Comparator<? super R> comparator, final Comparator<? super C> comparator2) {
        Preconditions.checkNotNull(list);
        if (!(comparator == null && comparator2 == null)) {
            Collections.sort(list, new Comparator<Cell<R, C, V>>() {
                public int compare(Cell<R, C, V> cell, Cell<R, C, V> cell2) {
                    int compare = comparator == null ? 0 : comparator.compare(cell.getRowKey(), cell2.getRowKey());
                    if (compare != 0) {
                        return compare;
                    }
                    if (comparator2 != null) {
                        return comparator2.compare(cell.getColumnKey(), cell2.getColumnKey());
                    }
                    return 0;
                }
            });
        }
        return forCellsInternal(list, comparator, comparator2);
    }

    static <R, C, V> RegularImmutableTable<R, C, V> forCells(Iterable<Cell<R, C, V>> iterable) {
        return forCellsInternal(iterable, null, null);
    }

    private static final <R, C, V> RegularImmutableTable<R, C, V> forCellsInternal(Iterable<Cell<R, C, V>> iterable, Comparator<? super R> comparator, Comparator<? super C> comparator2) {
        Builder builder = ImmutableSet.builder();
        Builder builder2 = ImmutableSet.builder();
        ImmutableList copyOf = ImmutableList.copyOf(iterable);
        Iterator it = copyOf.iterator();
        while (it.hasNext()) {
            Cell cell = (Cell) it.next();
            builder.add(cell.getRowKey());
            builder2.add(cell.getColumnKey());
        }
        ImmutableSet build = builder.build();
        if (comparator != null) {
            Collection newArrayList = Lists.newArrayList((Iterable) build);
            Collections.sort(newArrayList, comparator);
            build = ImmutableSet.copyOf(newArrayList);
        }
        ImmutableSet build2 = builder2.build();
        if (comparator2 != null) {
            Collection newArrayList2 = Lists.newArrayList((Iterable) build2);
            Collections.sort(newArrayList2, comparator2);
            build2 = ImmutableSet.copyOf(newArrayList2);
        }
        return ((long) copyOf.size()) > (((long) build.size()) * ((long) build2.size())) / 2 ? new DenseImmutableTable(copyOf, build, build2) : new SparseImmutableTable(copyOf, build, build2);
    }
}
