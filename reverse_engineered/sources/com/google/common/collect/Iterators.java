package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import java.util.Collection;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@GwtCompatible(emulated = true)
public final class Iterators {
    static final UnmodifiableListIterator<Object> EMPTY_LIST_ITERATOR = new Iterators$1();
    private static final Iterator<Object> EMPTY_MODIFIABLE_ITERATOR = new Iterators$2();

    private Iterators() {
    }

    @Deprecated
    public static <T> UnmodifiableIterator<T> emptyIterator() {
        return emptyListIterator();
    }

    static <T> UnmodifiableListIterator<T> emptyListIterator() {
        return EMPTY_LIST_ITERATOR;
    }

    static <T> Iterator<T> emptyModifiableIterator() {
        return EMPTY_MODIFIABLE_ITERATOR;
    }

    public static <T> UnmodifiableIterator<T> unmodifiableIterator(Iterator<T> it) {
        Preconditions.checkNotNull(it);
        if (it instanceof UnmodifiableIterator) {
            return (UnmodifiableIterator) it;
        }
        return new Iterators$3(it);
    }

    @Deprecated
    public static <T> UnmodifiableIterator<T> unmodifiableIterator(UnmodifiableIterator<T> unmodifiableIterator) {
        return (UnmodifiableIterator) Preconditions.checkNotNull(unmodifiableIterator);
    }

    public static int size(Iterator<?> it) {
        int i = 0;
        while (it.hasNext()) {
            it.next();
            i++;
        }
        return i;
    }

    public static boolean contains(Iterator<?> it, Object obj) {
        return any(it, Predicates.equalTo(obj));
    }

    public static boolean removeAll(Iterator<?> it, Collection<?> collection) {
        return removeIf(it, Predicates.in(collection));
    }

    public static <T> boolean removeIf(Iterator<T> it, Predicate<? super T> predicate) {
        Preconditions.checkNotNull(predicate);
        boolean z = false;
        while (it.hasNext()) {
            if (predicate.apply(it.next())) {
                it.remove();
                z = true;
            }
        }
        return z;
    }

    public static boolean retainAll(Iterator<?> it, Collection<?> collection) {
        return removeIf(it, Predicates.not(Predicates.in(collection)));
    }

    public static boolean elementsEqual(Iterator<?> it, Iterator<?> it2) {
        while (it.hasNext()) {
            if (!it2.hasNext()) {
                return false;
            }
            if (!Objects.equal(it.next(), it2.next())) {
                return false;
            }
        }
        if (it2.hasNext()) {
            return false;
        }
        return true;
    }

    public static String toString(Iterator<?> it) {
        return Collections2.STANDARD_JOINER.appendTo(new StringBuilder().append('['), it).append(']').toString();
    }

    public static <T> T getOnlyElement(Iterator<T> it) {
        T next = it.next();
        if (!it.hasNext()) {
            return next;
        }
        StringBuilder stringBuilder = new StringBuilder();
        String valueOf = String.valueOf(String.valueOf(next));
        stringBuilder.append(new StringBuilder(valueOf.length() + 31).append("expected one element but was: <").append(valueOf).toString());
        for (int i = 0; i < 4 && it.hasNext(); i++) {
            String valueOf2 = String.valueOf(String.valueOf(it.next()));
            stringBuilder.append(new StringBuilder(valueOf2.length() + 2).append(", ").append(valueOf2).toString());
        }
        if (it.hasNext()) {
            stringBuilder.append(", ...");
        }
        stringBuilder.append('>');
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    public static <T> T getOnlyElement(Iterator<? extends T> it, T t) {
        return it.hasNext() ? getOnlyElement(it) : t;
    }

    @GwtIncompatible("Array.newInstance(Class, int)")
    public static <T> T[] toArray(Iterator<? extends T> it, Class<T> cls) {
        return Iterables.toArray(Lists.newArrayList(it), cls);
    }

    public static <T> boolean addAll(Collection<T> collection, Iterator<? extends T> it) {
        Preconditions.checkNotNull(collection);
        Preconditions.checkNotNull(it);
        boolean z = false;
        while (it.hasNext()) {
            z |= collection.add(it.next());
        }
        return z;
    }

    public static int frequency(Iterator<?> it, Object obj) {
        return size(filter((Iterator) it, Predicates.equalTo(obj)));
    }

    public static <T> Iterator<T> cycle(Iterable<T> iterable) {
        Preconditions.checkNotNull(iterable);
        return new Iterators$4(iterable);
    }

    public static <T> Iterator<T> cycle(T... tArr) {
        return cycle(Lists.newArrayList(tArr));
    }

    public static <T> Iterator<T> concat(Iterator<? extends T> it, Iterator<? extends T> it2) {
        return concat(ImmutableList.of(it, it2).iterator());
    }

    public static <T> Iterator<T> concat(Iterator<? extends T> it, Iterator<? extends T> it2, Iterator<? extends T> it3) {
        return concat(ImmutableList.of(it, it2, it3).iterator());
    }

    public static <T> Iterator<T> concat(Iterator<? extends T> it, Iterator<? extends T> it2, Iterator<? extends T> it3, Iterator<? extends T> it4) {
        return concat(ImmutableList.of(it, it2, it3, it4).iterator());
    }

    public static <T> Iterator<T> concat(Iterator<? extends T>... itArr) {
        return concat(ImmutableList.copyOf((Object[]) itArr).iterator());
    }

    public static <T> Iterator<T> concat(Iterator<? extends Iterator<? extends T>> it) {
        Preconditions.checkNotNull(it);
        return new Iterators$5(it);
    }

    public static <T> UnmodifiableIterator<List<T>> partition(Iterator<T> it, int i) {
        return partitionImpl(it, i, false);
    }

    public static <T> UnmodifiableIterator<List<T>> paddedPartition(Iterator<T> it, int i) {
        return partitionImpl(it, i, true);
    }

    private static <T> UnmodifiableIterator<List<T>> partitionImpl(Iterator<T> it, int i, boolean z) {
        Preconditions.checkNotNull(it);
        Preconditions.checkArgument(i > 0);
        return new Iterators$6(it, i, z);
    }

    public static <T> UnmodifiableIterator<T> filter(Iterator<T> it, Predicate<? super T> predicate) {
        Preconditions.checkNotNull(it);
        Preconditions.checkNotNull(predicate);
        return new Iterators$7(it, predicate);
    }

    @GwtIncompatible("Class.isInstance")
    public static <T> UnmodifiableIterator<T> filter(Iterator<?> it, Class<T> cls) {
        return filter((Iterator) it, Predicates.instanceOf(cls));
    }

    public static <T> boolean any(Iterator<T> it, Predicate<? super T> predicate) {
        return indexOf(it, predicate) != -1;
    }

    public static <T> boolean all(Iterator<T> it, Predicate<? super T> predicate) {
        Preconditions.checkNotNull(predicate);
        while (it.hasNext()) {
            if (!predicate.apply(it.next())) {
                return false;
            }
        }
        return true;
    }

    public static <T> T find(Iterator<T> it, Predicate<? super T> predicate) {
        return filter((Iterator) it, (Predicate) predicate).next();
    }

    public static <T> T find(Iterator<? extends T> it, Predicate<? super T> predicate, T t) {
        return getNext(filter((Iterator) it, (Predicate) predicate), t);
    }

    public static <T> Optional<T> tryFind(Iterator<T> it, Predicate<? super T> predicate) {
        UnmodifiableIterator filter = filter((Iterator) it, (Predicate) predicate);
        return filter.hasNext() ? Optional.of(filter.next()) : Optional.absent();
    }

    public static <T> int indexOf(Iterator<T> it, Predicate<? super T> predicate) {
        Preconditions.checkNotNull(predicate, "predicate");
        int i = 0;
        while (it.hasNext()) {
            if (predicate.apply(it.next())) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static <F, T> Iterator<T> transform(Iterator<F> it, Function<? super F, ? extends T> function) {
        Preconditions.checkNotNull(function);
        return new Iterators$8(it, function);
    }

    public static <T> T get(Iterator<T> it, int i) {
        checkNonnegative(i);
        int advance = advance(it, i);
        if (it.hasNext()) {
            return it.next();
        }
        throw new IndexOutOfBoundsException("position (" + i + ") must be less than the number of elements that remained (" + advance + ")");
    }

    static void checkNonnegative(int i) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("position (" + i + ") must not be negative");
        }
    }

    public static <T> T get(Iterator<? extends T> it, int i, T t) {
        checkNonnegative(i);
        advance(it, i);
        return getNext(it, t);
    }

    public static <T> T getNext(Iterator<? extends T> it, T t) {
        return it.hasNext() ? it.next() : t;
    }

    public static <T> T getLast(Iterator<T> it) {
        T next;
        do {
            next = it.next();
        } while (it.hasNext());
        return next;
    }

    public static <T> T getLast(Iterator<? extends T> it, T t) {
        return it.hasNext() ? getLast(it) : t;
    }

    public static int advance(Iterator<?> it, int i) {
        boolean z;
        int i2 = 0;
        Preconditions.checkNotNull(it);
        if (i >= 0) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "numberToAdvance must be nonnegative");
        while (i2 < i && it.hasNext()) {
            it.next();
            i2++;
        }
        return i2;
    }

    public static <T> Iterator<T> limit(Iterator<T> it, int i) {
        Preconditions.checkNotNull(it);
        Preconditions.checkArgument(i >= 0, "limit is negative");
        return new Iterators$9(i, it);
    }

    public static <T> Iterator<T> consumingIterator(Iterator<T> it) {
        Preconditions.checkNotNull(it);
        return new Iterators$10(it);
    }

    static <T> T pollNext(Iterator<T> it) {
        if (!it.hasNext()) {
            return null;
        }
        T next = it.next();
        it.remove();
        return next;
    }

    static void clear(Iterator<?> it) {
        Preconditions.checkNotNull(it);
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
    }

    public static <T> UnmodifiableIterator<T> forArray(T... tArr) {
        return forArray(tArr, 0, tArr.length, 0);
    }

    static <T> UnmodifiableListIterator<T> forArray(T[] tArr, int i, int i2, int i3) {
        Preconditions.checkArgument(i2 >= 0);
        Preconditions.checkPositionIndexes(i, i + i2, tArr.length);
        Preconditions.checkPositionIndex(i3, i2);
        if (i2 == 0) {
            return emptyListIterator();
        }
        return new Iterators$11(i2, i3, tArr, i);
    }

    public static <T> UnmodifiableIterator<T> singletonIterator(T t) {
        return new Iterators$12(t);
    }

    public static <T> UnmodifiableIterator<T> forEnumeration(Enumeration<T> enumeration) {
        Preconditions.checkNotNull(enumeration);
        return new Iterators$13(enumeration);
    }

    public static <T> Enumeration<T> asEnumeration(Iterator<T> it) {
        Preconditions.checkNotNull(it);
        return new Iterators$14(it);
    }

    public static <T> PeekingIterator<T> peekingIterator(Iterator<? extends T> it) {
        if (it instanceof Iterators$PeekingImpl) {
            return (Iterators$PeekingImpl) it;
        }
        return new Iterators$PeekingImpl(it);
    }

    @Deprecated
    public static <T> PeekingIterator<T> peekingIterator(PeekingIterator<T> peekingIterator) {
        return (PeekingIterator) Preconditions.checkNotNull(peekingIterator);
    }

    @Beta
    public static <T> UnmodifiableIterator<T> mergeSorted(Iterable<? extends Iterator<? extends T>> iterable, Comparator<? super T> comparator) {
        Preconditions.checkNotNull(iterable, "iterators");
        Preconditions.checkNotNull(comparator, "comparator");
        return new Iterators$MergingIterator(iterable, comparator);
    }

    static <T> ListIterator<T> cast(Iterator<T> it) {
        return (ListIterator) it;
    }
}
