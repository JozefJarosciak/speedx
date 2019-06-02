package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

@GwtCompatible
public abstract class Ordering<T> implements Comparator<T> {
    static final int LEFT_IS_GREATER = 1;
    static final int RIGHT_IS_GREATER = -1;

    public abstract int compare(T t, T t2);

    @GwtCompatible(serializable = true)
    public static <C extends Comparable> Ordering<C> natural() {
        return NaturalOrdering.INSTANCE;
    }

    @GwtCompatible(serializable = true)
    public static <T> Ordering<T> from(Comparator<T> comparator) {
        return comparator instanceof Ordering ? (Ordering) comparator : new ComparatorOrdering(comparator);
    }

    @GwtCompatible(serializable = true)
    @Deprecated
    public static <T> Ordering<T> from(Ordering<T> ordering) {
        return (Ordering) Preconditions.checkNotNull(ordering);
    }

    @GwtCompatible(serializable = true)
    public static <T> Ordering<T> explicit(List<T> list) {
        return new ExplicitOrdering(list);
    }

    @GwtCompatible(serializable = true)
    public static <T> Ordering<T> explicit(T t, T... tArr) {
        return explicit(Lists.asList(t, tArr));
    }

    @GwtCompatible(serializable = true)
    public static Ordering<Object> allEqual() {
        return AllEqualOrdering.INSTANCE;
    }

    @GwtCompatible(serializable = true)
    public static Ordering<Object> usingToString() {
        return UsingToStringOrdering.INSTANCE;
    }

    public static Ordering<Object> arbitrary() {
        return Ordering$ArbitraryOrderingHolder.ARBITRARY_ORDERING;
    }

    protected Ordering() {
    }

    @GwtCompatible(serializable = true)
    public <S extends T> Ordering<S> reverse() {
        return new ReverseOrdering(this);
    }

    @GwtCompatible(serializable = true)
    public <S extends T> Ordering<S> nullsFirst() {
        return new NullsFirstOrdering(this);
    }

    @GwtCompatible(serializable = true)
    public <S extends T> Ordering<S> nullsLast() {
        return new NullsLastOrdering(this);
    }

    @GwtCompatible(serializable = true)
    public <F> Ordering<F> onResultOf(Function<F, ? extends T> function) {
        return new ByFunctionOrdering(function, this);
    }

    <T2 extends T> Ordering<Entry<T2, ?>> onKeys() {
        return onResultOf(Maps.keyFunction());
    }

    @GwtCompatible(serializable = true)
    public <U extends T> Ordering<U> compound(Comparator<? super U> comparator) {
        return new CompoundOrdering(this, (Comparator) Preconditions.checkNotNull(comparator));
    }

    @GwtCompatible(serializable = true)
    public static <T> Ordering<T> compound(Iterable<? extends Comparator<? super T>> iterable) {
        return new CompoundOrdering(iterable);
    }

    @GwtCompatible(serializable = true)
    public <S extends T> Ordering<Iterable<S>> lexicographical() {
        return new LexicographicalOrdering(this);
    }

    public <E extends T> E min(Iterator<E> it) {
        E next = it.next();
        while (it.hasNext()) {
            next = min(next, it.next());
        }
        return next;
    }

    public <E extends T> E min(Iterable<E> iterable) {
        return min(iterable.iterator());
    }

    public <E extends T> E min(E e, E e2) {
        return compare(e, e2) <= 0 ? e : e2;
    }

    public <E extends T> E min(E e, E e2, E e3, E... eArr) {
        E min = min(min(e, e2), e3);
        for (Object min2 : eArr) {
            min = min(min, min2);
        }
        return min;
    }

    public <E extends T> E max(Iterator<E> it) {
        E next = it.next();
        while (it.hasNext()) {
            next = max(next, it.next());
        }
        return next;
    }

    public <E extends T> E max(Iterable<E> iterable) {
        return max(iterable.iterator());
    }

    public <E extends T> E max(E e, E e2) {
        return compare(e, e2) >= 0 ? e : e2;
    }

    public <E extends T> E max(E e, E e2, E e3, E... eArr) {
        E max = max(max(e, e2), e3);
        for (Object max2 : eArr) {
            max = max(max, max2);
        }
        return max;
    }

    public <E extends T> List<E> leastOf(Iterable<E> iterable, int i) {
        if (iterable instanceof Collection) {
            Collection collection = (Collection) iterable;
            if (((long) collection.size()) <= 2 * ((long) i)) {
                Object[] toArray = collection.toArray();
                Arrays.sort(toArray, this);
                if (toArray.length > i) {
                    toArray = ObjectArrays.arraysCopyOf(toArray, i);
                }
                return Collections.unmodifiableList(Arrays.asList(toArray));
            }
        }
        return leastOf(iterable.iterator(), i);
    }

    public <E extends T> List<E> leastOf(Iterator<E> it, int i) {
        Preconditions.checkNotNull(it);
        CollectPreconditions.checkNonnegative(i, "k");
        if (i == 0 || !it.hasNext()) {
            return ImmutableList.of();
        }
        if (i >= 1073741823) {
            List newArrayList = Lists.newArrayList(it);
            Collections.sort(newArrayList, this);
            if (newArrayList.size() > i) {
                newArrayList.subList(i, newArrayList.size()).clear();
            }
            newArrayList.trimToSize();
            return Collections.unmodifiableList(newArrayList);
        }
        int i2 = i * 2;
        Object[] objArr = new Object[i2];
        Object next = it.next();
        objArr[0] = next;
        int i3 = 1;
        while (i3 < i && it.hasNext()) {
            Object next2 = it.next();
            int i4 = i3 + 1;
            objArr[i3] = next2;
            next = max(next, next2);
            i3 = i4;
        }
        while (it.hasNext()) {
            next2 = it.next();
            if (compare(next2, next) < 0) {
                i4 = i3 + 1;
                objArr[i3] = next2;
                if (i4 == i2) {
                    i4 = i2 - 1;
                    i3 = 0;
                    int i5 = 0;
                    while (i5 < i4) {
                        int partition = partition(objArr, i5, i4, ((i5 + i4) + 1) >>> 1);
                        if (partition <= i) {
                            if (partition >= i) {
                                break;
                            }
                            i3 = Math.max(partition, i5 + 1);
                            int i6 = partition;
                            partition = i4;
                            i4 = i3;
                            i3 = i6;
                        } else {
                            partition--;
                            i4 = i5;
                        }
                        i5 = i4;
                        i4 = partition;
                    }
                    next = objArr[i3];
                    for (i3++; i3 < i; i3++) {
                        next = max(next, objArr[i3]);
                    }
                    i3 = i;
                } else {
                    i3 = i4;
                }
            }
        }
        Arrays.sort(objArr, 0, i3, this);
        return Collections.unmodifiableList(Arrays.asList(ObjectArrays.arraysCopyOf(objArr, Math.min(i3, i))));
    }

    private <E extends T> int partition(E[] eArr, int i, int i2, int i3) {
        Object obj = eArr[i3];
        eArr[i3] = eArr[i2];
        eArr[i2] = obj;
        int i4 = i;
        while (i < i2) {
            if (compare(eArr[i], obj) < 0) {
                ObjectArrays.swap(eArr, i4, i);
                i4++;
            }
            i++;
        }
        ObjectArrays.swap(eArr, i2, i4);
        return i4;
    }

    public <E extends T> List<E> greatestOf(Iterable<E> iterable, int i) {
        return reverse().leastOf((Iterable) iterable, i);
    }

    public <E extends T> List<E> greatestOf(Iterator<E> it, int i) {
        return reverse().leastOf((Iterator) it, i);
    }

    public <E extends T> List<E> sortedCopy(Iterable<E> iterable) {
        Object[] toArray = Iterables.toArray(iterable);
        Arrays.sort(toArray, this);
        return Lists.newArrayList(Arrays.asList(toArray));
    }

    public <E extends T> ImmutableList<E> immutableSortedCopy(Iterable<E> iterable) {
        Object[] toArray = Iterables.toArray(iterable);
        for (Object checkNotNull : toArray) {
            Preconditions.checkNotNull(checkNotNull);
        }
        Arrays.sort(toArray, this);
        return ImmutableList.asImmutableList(toArray);
    }

    public boolean isOrdered(Iterable<? extends T> iterable) {
        Iterator it = iterable.iterator();
        if (it.hasNext()) {
            Object next = it.next();
            while (it.hasNext()) {
                Object next2 = it.next();
                if (compare(next, next2) > 0) {
                    return false;
                }
                next = next2;
            }
        }
        return true;
    }

    public boolean isStrictlyOrdered(Iterable<? extends T> iterable) {
        Iterator it = iterable.iterator();
        if (it.hasNext()) {
            Object next = it.next();
            while (it.hasNext()) {
                Object next2 = it.next();
                if (compare(next, next2) >= 0) {
                    return false;
                }
                next = next2;
            }
        }
        return true;
    }

    public int binarySearch(List<? extends T> list, T t) {
        return Collections.binarySearch(list, t, this);
    }
}
