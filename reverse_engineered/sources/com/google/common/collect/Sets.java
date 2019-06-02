package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableSet.Builder;
import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@GwtCompatible(emulated = true)
public final class Sets {

    static abstract class ImprovedAbstractSet<E> extends AbstractSet<E> {
        ImprovedAbstractSet() {
        }

        public boolean removeAll(Collection<?> collection) {
            return Sets.removeAllImpl((Set) this, (Collection) collection);
        }

        public boolean retainAll(Collection<?> collection) {
            return super.retainAll((Collection) Preconditions.checkNotNull(collection));
        }
    }

    @GwtIncompatible("NavigableSet")
    static class DescendingSet<E> extends ForwardingNavigableSet<E> {
        private final NavigableSet<E> forward;

        DescendingSet(NavigableSet<E> navigableSet) {
            this.forward = navigableSet;
        }

        protected NavigableSet<E> delegate() {
            return this.forward;
        }

        public E lower(E e) {
            return this.forward.higher(e);
        }

        public E floor(E e) {
            return this.forward.ceiling(e);
        }

        public E ceiling(E e) {
            return this.forward.floor(e);
        }

        public E higher(E e) {
            return this.forward.lower(e);
        }

        public E pollFirst() {
            return this.forward.pollLast();
        }

        public E pollLast() {
            return this.forward.pollFirst();
        }

        public NavigableSet<E> descendingSet() {
            return this.forward;
        }

        public Iterator<E> descendingIterator() {
            return this.forward.iterator();
        }

        public NavigableSet<E> subSet(E e, boolean z, E e2, boolean z2) {
            return this.forward.subSet(e2, z2, e, z).descendingSet();
        }

        public NavigableSet<E> headSet(E e, boolean z) {
            return this.forward.tailSet(e, z).descendingSet();
        }

        public NavigableSet<E> tailSet(E e, boolean z) {
            return this.forward.headSet(e, z).descendingSet();
        }

        public Comparator<? super E> comparator() {
            Comparator comparator = this.forward.comparator();
            if (comparator == null) {
                return Ordering.natural().reverse();
            }
            return reverse(comparator);
        }

        private static <T> Ordering<T> reverse(Comparator<T> comparator) {
            return Ordering.from(comparator).reverse();
        }

        public E first() {
            return this.forward.last();
        }

        public SortedSet<E> headSet(E e) {
            return standardHeadSet(e);
        }

        public E last() {
            return this.forward.first();
        }

        public SortedSet<E> subSet(E e, E e2) {
            return standardSubSet(e, e2);
        }

        public SortedSet<E> tailSet(E e) {
            return standardTailSet(e);
        }

        public Iterator<E> iterator() {
            return this.forward.descendingIterator();
        }

        public Object[] toArray() {
            return standardToArray();
        }

        public <T> T[] toArray(T[] tArr) {
            return standardToArray(tArr);
        }

        public String toString() {
            return standardToString();
        }
    }

    public static abstract class SetView<E> extends AbstractSet<E> {
        private SetView() {
        }

        public ImmutableSet<E> immutableCopy() {
            return ImmutableSet.copyOf((Collection) this);
        }

        public <S extends Set<E>> S copyInto(S s) {
            s.addAll(this);
            return s;
        }
    }

    private static final class CartesianSet<E> extends ForwardingCollection<List<E>> implements Set<List<E>> {
        private final transient ImmutableList<ImmutableSet<E>> axes;
        private final transient CartesianList<E> delegate;

        static <E> Set<List<E>> create(List<? extends Set<? extends E>> list) {
            ImmutableList$Builder immutableList$Builder = new ImmutableList$Builder(list.size());
            for (Collection copyOf : list) {
                Object copyOf2 = ImmutableSet.copyOf(copyOf);
                if (copyOf2.isEmpty()) {
                    return ImmutableSet.of();
                }
                immutableList$Builder.add(copyOf2);
            }
            final ImmutableList build = immutableList$Builder.build();
            return new CartesianSet(build, new CartesianList(new ImmutableList<List<E>>() {
                public int size() {
                    return build.size();
                }

                public List<E> get(int i) {
                    return ((ImmutableSet) build.get(i)).asList();
                }

                boolean isPartialView() {
                    return true;
                }
            }));
        }

        private CartesianSet(ImmutableList<ImmutableSet<E>> immutableList, CartesianList<E> cartesianList) {
            this.axes = immutableList;
            this.delegate = cartesianList;
        }

        protected Collection<List<E>> delegate() {
            return this.delegate;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof CartesianSet)) {
                return super.equals(obj);
            }
            return this.axes.equals(((CartesianSet) obj).axes);
        }

        public int hashCode() {
            int size = size() - 1;
            for (int i = 0; i < this.axes.size(); i++) {
                size = ((size * 31) ^ -1) ^ -1;
            }
            Iterator it = this.axes.iterator();
            int i2 = 1;
            while (it.hasNext()) {
                Set set = (Set) it.next();
                i2 = (((set.hashCode() * (size() / set.size())) + (i2 * 31)) ^ -1) ^ -1;
            }
            return ((i2 + size) ^ -1) ^ -1;
        }
    }

    private static class FilteredSet<E> extends FilteredCollection<E> implements Set<E> {
        FilteredSet(Set<E> set, Predicate<? super E> predicate) {
            super(set, predicate);
        }

        public boolean equals(Object obj) {
            return Sets.equalsImpl(this, obj);
        }

        public int hashCode() {
            return Sets.hashCodeImpl(this);
        }
    }

    private static class FilteredSortedSet<E> extends FilteredSet<E> implements SortedSet<E> {
        FilteredSortedSet(SortedSet<E> sortedSet, Predicate<? super E> predicate) {
            super(sortedSet, predicate);
        }

        public Comparator<? super E> comparator() {
            return ((SortedSet) this.unfiltered).comparator();
        }

        public SortedSet<E> subSet(E e, E e2) {
            return new FilteredSortedSet(((SortedSet) this.unfiltered).subSet(e, e2), this.predicate);
        }

        public SortedSet<E> headSet(E e) {
            return new FilteredSortedSet(((SortedSet) this.unfiltered).headSet(e), this.predicate);
        }

        public SortedSet<E> tailSet(E e) {
            return new FilteredSortedSet(((SortedSet) this.unfiltered).tailSet(e), this.predicate);
        }

        public E first() {
            return iterator().next();
        }

        public E last() {
            SortedSet sortedSet = (SortedSet) this.unfiltered;
            while (true) {
                E last = sortedSet.last();
                if (this.predicate.apply(last)) {
                    return last;
                }
                sortedSet = sortedSet.headSet(last);
            }
        }
    }

    @GwtIncompatible("NavigableSet")
    private static class FilteredNavigableSet<E> extends FilteredSortedSet<E> implements NavigableSet<E> {
        FilteredNavigableSet(NavigableSet<E> navigableSet, Predicate<? super E> predicate) {
            super(navigableSet, predicate);
        }

        NavigableSet<E> unfiltered() {
            return (NavigableSet) this.unfiltered;
        }

        public E lower(E e) {
            return Iterators.getNext(headSet(e, false).descendingIterator(), null);
        }

        public E floor(E e) {
            return Iterators.getNext(headSet(e, true).descendingIterator(), null);
        }

        public E ceiling(E e) {
            return Iterables.getFirst(tailSet(e, true), null);
        }

        public E higher(E e) {
            return Iterables.getFirst(tailSet(e, false), null);
        }

        public E pollFirst() {
            return Iterables.removeFirstMatching(unfiltered(), this.predicate);
        }

        public E pollLast() {
            return Iterables.removeFirstMatching(unfiltered().descendingSet(), this.predicate);
        }

        public NavigableSet<E> descendingSet() {
            return Sets.filter(unfiltered().descendingSet(), this.predicate);
        }

        public Iterator<E> descendingIterator() {
            return Iterators.filter(unfiltered().descendingIterator(), this.predicate);
        }

        public E last() {
            return descendingIterator().next();
        }

        public NavigableSet<E> subSet(E e, boolean z, E e2, boolean z2) {
            return Sets.filter(unfiltered().subSet(e, z, e2, z2), this.predicate);
        }

        public NavigableSet<E> headSet(E e, boolean z) {
            return Sets.filter(unfiltered().headSet(e, z), this.predicate);
        }

        public NavigableSet<E> tailSet(E e, boolean z) {
            return Sets.filter(unfiltered().tailSet(e, z), this.predicate);
        }
    }

    private static final class PowerSet<E> extends AbstractSet<Set<E>> {
        final ImmutableMap<E, Integer> inputSet;

        PowerSet(Set<E> set) {
            boolean z;
            ImmutableMap$Builder builder = ImmutableMap.builder();
            int i = 0;
            for (Object put : (Set) Preconditions.checkNotNull(set)) {
                int i2 = i + 1;
                builder.put(put, Integer.valueOf(i));
                i = i2;
            }
            this.inputSet = builder.build();
            if (this.inputSet.size() <= 30) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkArgument(z, "Too many elements to create power set: %s > 30", Integer.valueOf(this.inputSet.size()));
        }

        public int size() {
            return 1 << this.inputSet.size();
        }

        public boolean isEmpty() {
            return false;
        }

        public Iterator<Set<E>> iterator() {
            return new AbstractIndexedListIterator<Set<E>>(size()) {
                protected Set<E> get(int i) {
                    return new SubSet(PowerSet.this.inputSet, i);
                }
            };
        }

        public boolean contains(Object obj) {
            if (!(obj instanceof Set)) {
                return false;
            }
            return this.inputSet.keySet().containsAll((Set) obj);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof PowerSet)) {
                return super.equals(obj);
            }
            return this.inputSet.equals(((PowerSet) obj).inputSet);
        }

        public int hashCode() {
            return this.inputSet.keySet().hashCode() << (this.inputSet.size() - 1);
        }

        public String toString() {
            String valueOf = String.valueOf(String.valueOf(this.inputSet));
            return new StringBuilder(valueOf.length() + 10).append("powerSet(").append(valueOf).append(")").toString();
        }
    }

    private static final class SubSet<E> extends AbstractSet<E> {
        private final ImmutableMap<E, Integer> inputSet;
        private final int mask;

        /* renamed from: com.google.common.collect.Sets$SubSet$1 */
        class C37071 extends UnmodifiableIterator<E> {
            final ImmutableList<E> elements = SubSet.this.inputSet.keySet().asList();
            int remainingSetBits = SubSet.this.mask;

            C37071() {
            }

            public boolean hasNext() {
                return this.remainingSetBits != 0;
            }

            public E next() {
                int numberOfTrailingZeros = Integer.numberOfTrailingZeros(this.remainingSetBits);
                if (numberOfTrailingZeros == 32) {
                    throw new NoSuchElementException();
                }
                this.remainingSetBits &= (1 << numberOfTrailingZeros) ^ -1;
                return this.elements.get(numberOfTrailingZeros);
            }
        }

        SubSet(ImmutableMap<E, Integer> immutableMap, int i) {
            this.inputSet = immutableMap;
            this.mask = i;
        }

        public Iterator<E> iterator() {
            return new C37071();
        }

        public int size() {
            return Integer.bitCount(this.mask);
        }

        public boolean contains(Object obj) {
            Integer num = (Integer) this.inputSet.get(obj);
            if (num != null) {
                if (((1 << num.intValue()) & this.mask) != 0) {
                    return true;
                }
            }
            return false;
        }
    }

    @GwtIncompatible("NavigableSet")
    static final class UnmodifiableNavigableSet<E> extends ForwardingSortedSet<E> implements Serializable, NavigableSet<E> {
        private static final long serialVersionUID = 0;
        private final NavigableSet<E> delegate;
        private transient UnmodifiableNavigableSet<E> descendingSet;

        UnmodifiableNavigableSet(NavigableSet<E> navigableSet) {
            this.delegate = (NavigableSet) Preconditions.checkNotNull(navigableSet);
        }

        protected SortedSet<E> delegate() {
            return Collections.unmodifiableSortedSet(this.delegate);
        }

        public E lower(E e) {
            return this.delegate.lower(e);
        }

        public E floor(E e) {
            return this.delegate.floor(e);
        }

        public E ceiling(E e) {
            return this.delegate.ceiling(e);
        }

        public E higher(E e) {
            return this.delegate.higher(e);
        }

        public E pollFirst() {
            throw new UnsupportedOperationException();
        }

        public E pollLast() {
            throw new UnsupportedOperationException();
        }

        public NavigableSet<E> descendingSet() {
            NavigableSet<E> navigableSet = this.descendingSet;
            if (navigableSet != null) {
                return navigableSet;
            }
            NavigableSet unmodifiableNavigableSet = new UnmodifiableNavigableSet(this.delegate.descendingSet());
            this.descendingSet = unmodifiableNavigableSet;
            unmodifiableNavigableSet.descendingSet = this;
            return unmodifiableNavigableSet;
        }

        public Iterator<E> descendingIterator() {
            return Iterators.unmodifiableIterator(this.delegate.descendingIterator());
        }

        public NavigableSet<E> subSet(E e, boolean z, E e2, boolean z2) {
            return Sets.unmodifiableNavigableSet(this.delegate.subSet(e, z, e2, z2));
        }

        public NavigableSet<E> headSet(E e, boolean z) {
            return Sets.unmodifiableNavigableSet(this.delegate.headSet(e, z));
        }

        public NavigableSet<E> tailSet(E e, boolean z) {
            return Sets.unmodifiableNavigableSet(this.delegate.tailSet(e, z));
        }
    }

    private Sets() {
    }

    @GwtCompatible(serializable = true)
    public static <E extends Enum<E>> ImmutableSet<E> immutableEnumSet(E e, E... eArr) {
        return ImmutableEnumSet.asImmutable(EnumSet.of(e, eArr));
    }

    @GwtCompatible(serializable = true)
    public static <E extends Enum<E>> ImmutableSet<E> immutableEnumSet(Iterable<E> iterable) {
        if (iterable instanceof ImmutableEnumSet) {
            return (ImmutableEnumSet) iterable;
        }
        if (iterable instanceof Collection) {
            Collection collection = (Collection) iterable;
            if (collection.isEmpty()) {
                return ImmutableSet.of();
            }
            return ImmutableEnumSet.asImmutable(EnumSet.copyOf(collection));
        }
        Iterator it = iterable.iterator();
        if (!it.hasNext()) {
            return ImmutableSet.of();
        }
        Object of = EnumSet.of((Enum) it.next());
        Iterators.addAll(of, it);
        return ImmutableEnumSet.asImmutable(of);
    }

    public static <E extends Enum<E>> EnumSet<E> newEnumSet(Iterable<E> iterable, Class<E> cls) {
        Object noneOf = EnumSet.noneOf(cls);
        Iterables.addAll(noneOf, iterable);
        return noneOf;
    }

    public static <E> HashSet<E> newHashSet() {
        return new HashSet();
    }

    public static <E> HashSet<E> newHashSet(E... eArr) {
        Object newHashSetWithExpectedSize = newHashSetWithExpectedSize(eArr.length);
        Collections.addAll(newHashSetWithExpectedSize, eArr);
        return newHashSetWithExpectedSize;
    }

    public static <E> HashSet<E> newHashSetWithExpectedSize(int i) {
        return new HashSet(Maps.capacity(i));
    }

    public static <E> HashSet<E> newHashSet(Iterable<? extends E> iterable) {
        return iterable instanceof Collection ? new HashSet(Collections2.cast(iterable)) : newHashSet(iterable.iterator());
    }

    public static <E> HashSet<E> newHashSet(Iterator<? extends E> it) {
        Object newHashSet = newHashSet();
        Iterators.addAll(newHashSet, it);
        return newHashSet;
    }

    public static <E> Set<E> newConcurrentHashSet() {
        return newSetFromMap(new ConcurrentHashMap());
    }

    public static <E> Set<E> newConcurrentHashSet(Iterable<? extends E> iterable) {
        Object newConcurrentHashSet = newConcurrentHashSet();
        Iterables.addAll(newConcurrentHashSet, iterable);
        return newConcurrentHashSet;
    }

    public static <E> LinkedHashSet<E> newLinkedHashSet() {
        return new LinkedHashSet();
    }

    public static <E> LinkedHashSet<E> newLinkedHashSetWithExpectedSize(int i) {
        return new LinkedHashSet(Maps.capacity(i));
    }

    public static <E> LinkedHashSet<E> newLinkedHashSet(Iterable<? extends E> iterable) {
        if (iterable instanceof Collection) {
            return new LinkedHashSet(Collections2.cast(iterable));
        }
        LinkedHashSet<E> newLinkedHashSet = newLinkedHashSet();
        Iterables.addAll(newLinkedHashSet, iterable);
        return newLinkedHashSet;
    }

    public static <E extends Comparable> TreeSet<E> newTreeSet() {
        return new TreeSet();
    }

    public static <E extends Comparable> TreeSet<E> newTreeSet(Iterable<? extends E> iterable) {
        Object newTreeSet = newTreeSet();
        Iterables.addAll(newTreeSet, iterable);
        return newTreeSet;
    }

    public static <E> TreeSet<E> newTreeSet(Comparator<? super E> comparator) {
        return new TreeSet((Comparator) Preconditions.checkNotNull(comparator));
    }

    public static <E> Set<E> newIdentityHashSet() {
        return newSetFromMap(Maps.newIdentityHashMap());
    }

    @GwtIncompatible("CopyOnWriteArraySet")
    public static <E> CopyOnWriteArraySet<E> newCopyOnWriteArraySet() {
        return new CopyOnWriteArraySet();
    }

    @GwtIncompatible("CopyOnWriteArraySet")
    public static <E> CopyOnWriteArraySet<E> newCopyOnWriteArraySet(Iterable<? extends E> iterable) {
        return new CopyOnWriteArraySet(iterable instanceof Collection ? Collections2.cast(iterable) : Lists.newArrayList((Iterable) iterable));
    }

    public static <E extends Enum<E>> EnumSet<E> complementOf(Collection<E> collection) {
        if (collection instanceof EnumSet) {
            return EnumSet.complementOf((EnumSet) collection);
        }
        Preconditions.checkArgument(!collection.isEmpty(), "collection is empty; use the other version of this method");
        return makeComplementByHand(collection, ((Enum) collection.iterator().next()).getDeclaringClass());
    }

    public static <E extends Enum<E>> EnumSet<E> complementOf(Collection<E> collection, Class<E> cls) {
        Preconditions.checkNotNull(collection);
        return collection instanceof EnumSet ? EnumSet.complementOf((EnumSet) collection) : makeComplementByHand(collection, cls);
    }

    private static <E extends Enum<E>> EnumSet<E> makeComplementByHand(Collection<E> collection, Class<E> cls) {
        EnumSet<E> allOf = EnumSet.allOf(cls);
        allOf.removeAll(collection);
        return allOf;
    }

    public static <E> Set<E> newSetFromMap(Map<E, Boolean> map) {
        return Platform.newSetFromMap(map);
    }

    public static <E> SetView<E> union(final Set<? extends E> set, final Set<? extends E> set2) {
        Preconditions.checkNotNull(set, "set1");
        Preconditions.checkNotNull(set2, "set2");
        final Set difference = difference(set2, set);
        return new SetView<E>() {
            public int size() {
                return set.size() + difference.size();
            }

            public boolean isEmpty() {
                return set.isEmpty() && set2.isEmpty();
            }

            public Iterator<E> iterator() {
                return Iterators.unmodifiableIterator(Iterators.concat(set.iterator(), difference.iterator()));
            }

            public boolean contains(Object obj) {
                return set.contains(obj) || set2.contains(obj);
            }

            public <S extends Set<E>> S copyInto(S s) {
                s.addAll(set);
                s.addAll(set2);
                return s;
            }

            public ImmutableSet<E> immutableCopy() {
                return new Builder().addAll(set).addAll(set2).build();
            }
        };
    }

    public static <E> SetView<E> intersection(final Set<E> set, final Set<?> set2) {
        Preconditions.checkNotNull(set, "set1");
        Preconditions.checkNotNull(set2, "set2");
        final Predicate in = Predicates.in(set2);
        return new SetView<E>() {
            public Iterator<E> iterator() {
                return Iterators.filter(set.iterator(), in);
            }

            public int size() {
                return Iterators.size(iterator());
            }

            public boolean isEmpty() {
                return !iterator().hasNext();
            }

            public boolean contains(Object obj) {
                return set.contains(obj) && set2.contains(obj);
            }

            public boolean containsAll(Collection<?> collection) {
                return set.containsAll(collection) && set2.containsAll(collection);
            }
        };
    }

    public static <E> SetView<E> difference(final Set<E> set, final Set<?> set2) {
        Preconditions.checkNotNull(set, "set1");
        Preconditions.checkNotNull(set2, "set2");
        final Predicate not = Predicates.not(Predicates.in(set2));
        return new SetView<E>() {
            public Iterator<E> iterator() {
                return Iterators.filter(set.iterator(), not);
            }

            public int size() {
                return Iterators.size(iterator());
            }

            public boolean isEmpty() {
                return set2.containsAll(set);
            }

            public boolean contains(Object obj) {
                return set.contains(obj) && !set2.contains(obj);
            }
        };
    }

    public static <E> SetView<E> symmetricDifference(Set<? extends E> set, Set<? extends E> set2) {
        Preconditions.checkNotNull(set, "set1");
        Preconditions.checkNotNull(set2, "set2");
        return difference(union(set, set2), intersection(set, set2));
    }

    public static <E> Set<E> filter(Set<E> set, Predicate<? super E> predicate) {
        if (set instanceof SortedSet) {
            return filter((SortedSet) set, (Predicate) predicate);
        }
        if (!(set instanceof FilteredSet)) {
            return new FilteredSet((Set) Preconditions.checkNotNull(set), (Predicate) Preconditions.checkNotNull(predicate));
        }
        FilteredSet filteredSet = (FilteredSet) set;
        return new FilteredSet((Set) filteredSet.unfiltered, Predicates.and(filteredSet.predicate, predicate));
    }

    public static <E> SortedSet<E> filter(SortedSet<E> sortedSet, Predicate<? super E> predicate) {
        return Platform.setsFilterSortedSet(sortedSet, predicate);
    }

    static <E> SortedSet<E> filterSortedIgnoreNavigable(SortedSet<E> sortedSet, Predicate<? super E> predicate) {
        if (!(sortedSet instanceof FilteredSet)) {
            return new FilteredSortedSet((SortedSet) Preconditions.checkNotNull(sortedSet), (Predicate) Preconditions.checkNotNull(predicate));
        }
        FilteredSet filteredSet = (FilteredSet) sortedSet;
        return new FilteredSortedSet((SortedSet) filteredSet.unfiltered, Predicates.and(filteredSet.predicate, predicate));
    }

    @GwtIncompatible("NavigableSet")
    public static <E> NavigableSet<E> filter(NavigableSet<E> navigableSet, Predicate<? super E> predicate) {
        if (!(navigableSet instanceof FilteredSet)) {
            return new FilteredNavigableSet((NavigableSet) Preconditions.checkNotNull(navigableSet), (Predicate) Preconditions.checkNotNull(predicate));
        }
        FilteredSet filteredSet = (FilteredSet) navigableSet;
        return new FilteredNavigableSet((NavigableSet) filteredSet.unfiltered, Predicates.and(filteredSet.predicate, predicate));
    }

    public static <B> Set<List<B>> cartesianProduct(List<? extends Set<? extends B>> list) {
        return CartesianSet.create(list);
    }

    public static <B> Set<List<B>> cartesianProduct(Set<? extends B>... setArr) {
        return cartesianProduct(Arrays.asList(setArr));
    }

    @GwtCompatible(serializable = false)
    public static <E> Set<Set<E>> powerSet(Set<E> set) {
        return new PowerSet(set);
    }

    static int hashCodeImpl(Set<?> set) {
        int i = 0;
        for (Object next : set) {
            int hashCode;
            if (next != null) {
                hashCode = next.hashCode();
            } else {
                hashCode = 0;
            }
            i = ((i + hashCode) ^ -1) ^ -1;
        }
        return i;
    }

    static boolean equalsImpl(Set<?> set, Object obj) {
        boolean z = true;
        if (set == obj) {
            return true;
        }
        if (!(obj instanceof Set)) {
            return false;
        }
        Set set2 = (Set) obj;
        try {
            if (!(set.size() == set2.size() && set.containsAll(set2))) {
                z = false;
            }
            return z;
        } catch (NullPointerException e) {
            return false;
        } catch (ClassCastException e2) {
            return false;
        }
    }

    @GwtIncompatible("NavigableSet")
    public static <E> NavigableSet<E> unmodifiableNavigableSet(NavigableSet<E> navigableSet) {
        return ((navigableSet instanceof ImmutableSortedSet) || (navigableSet instanceof UnmodifiableNavigableSet)) ? navigableSet : new UnmodifiableNavigableSet(navigableSet);
    }

    @GwtIncompatible("NavigableSet")
    public static <E> NavigableSet<E> synchronizedNavigableSet(NavigableSet<E> navigableSet) {
        return Synchronized.navigableSet(navigableSet);
    }

    static boolean removeAllImpl(Set<?> set, Iterator<?> it) {
        boolean z = false;
        while (it.hasNext()) {
            z |= set.remove(it.next());
        }
        return z;
    }

    static boolean removeAllImpl(Set<?> set, Collection<?> collection) {
        Preconditions.checkNotNull(collection);
        if (collection instanceof Multiset) {
            collection = ((Multiset) collection).elementSet();
        }
        if (!(collection instanceof Set) || collection.size() <= set.size()) {
            return removeAllImpl((Set) set, collection.iterator());
        }
        return Iterators.removeAll(set.iterator(), collection);
    }
}
