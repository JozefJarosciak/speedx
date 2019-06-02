package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.SortedLists.KeyAbsentBehavior;
import com.google.common.collect.SortedLists.KeyPresentBehavior;
import com.google.common.primitives.Ints;
import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

@Beta
public final class ImmutableRangeSet<C extends Comparable> extends AbstractRangeSet<C> implements Serializable {
    private static final ImmutableRangeSet<Comparable<?>> ALL = new ImmutableRangeSet(ImmutableList.of(Range.all()));
    private static final ImmutableRangeSet<Comparable<?>> EMPTY = new ImmutableRangeSet(ImmutableList.of());
    private transient ImmutableRangeSet<C> complement;
    private final transient ImmutableList<Range<C>> ranges;

    private final class AsSet extends ImmutableSortedSet<C> {
        private final DiscreteDomain<C> domain;
        private transient Integer size;

        /* renamed from: com.google.common.collect.ImmutableRangeSet$AsSet$1 */
        class C36001 extends AbstractIterator<C> {
            Iterator<C> elemItr = Iterators.emptyIterator();
            final Iterator<Range<C>> rangeItr = ImmutableRangeSet.this.ranges.iterator();

            C36001() {
            }

            protected C computeNext() {
                while (!this.elemItr.hasNext()) {
                    if (!this.rangeItr.hasNext()) {
                        return (Comparable) endOfData();
                    }
                    this.elemItr = ContiguousSet.create((Range) this.rangeItr.next(), AsSet.this.domain).iterator();
                }
                return (Comparable) this.elemItr.next();
            }
        }

        /* renamed from: com.google.common.collect.ImmutableRangeSet$AsSet$2 */
        class C36012 extends AbstractIterator<C> {
            Iterator<C> elemItr = Iterators.emptyIterator();
            final Iterator<Range<C>> rangeItr = ImmutableRangeSet.this.ranges.reverse().iterator();

            C36012() {
            }

            protected C computeNext() {
                while (!this.elemItr.hasNext()) {
                    if (!this.rangeItr.hasNext()) {
                        return (Comparable) endOfData();
                    }
                    this.elemItr = ContiguousSet.create((Range) this.rangeItr.next(), AsSet.this.domain).descendingIterator();
                }
                return (Comparable) this.elemItr.next();
            }
        }

        AsSet(DiscreteDomain<C> discreteDomain) {
            super(Ordering.natural());
            this.domain = discreteDomain;
        }

        public int size() {
            Integer num = this.size;
            if (num == null) {
                long size;
                Iterator it = ImmutableRangeSet.this.ranges.iterator();
                long j = 0;
                while (it.hasNext()) {
                    size = ((long) ContiguousSet.create((Range) it.next(), this.domain).size()) + j;
                    if (size >= 2147483647L) {
                        break;
                    }
                    j = size;
                }
                size = j;
                num = Integer.valueOf(Ints.saturatedCast(size));
                this.size = num;
            }
            return num.intValue();
        }

        public UnmodifiableIterator<C> iterator() {
            return new C36001();
        }

        @GwtIncompatible("NavigableSet")
        public UnmodifiableIterator<C> descendingIterator() {
            return new C36012();
        }

        ImmutableSortedSet<C> subSet(Range<C> range) {
            return ImmutableRangeSet.this.subRangeSet((Range) range).asSet(this.domain);
        }

        ImmutableSortedSet<C> headSetImpl(C c, boolean z) {
            return subSet(Range.upTo(c, BoundType.forBoolean(z)));
        }

        ImmutableSortedSet<C> subSetImpl(C c, boolean z, C c2, boolean z2) {
            if (z || z2 || Range.compareOrThrow(c, c2) != 0) {
                return subSet(Range.range(c, BoundType.forBoolean(z), c2, BoundType.forBoolean(z2)));
            }
            return ImmutableSortedSet.of();
        }

        ImmutableSortedSet<C> tailSetImpl(C c, boolean z) {
            return subSet(Range.downTo(c, BoundType.forBoolean(z)));
        }

        public boolean contains(Object obj) {
            boolean z = false;
            if (obj != null) {
                try {
                    z = ImmutableRangeSet.this.contains((Comparable) obj);
                } catch (ClassCastException e) {
                }
            }
            return z;
        }

        int indexOf(Object obj) {
            if (!contains(obj)) {
                return -1;
            }
            Comparable comparable = (Comparable) obj;
            Iterator it = ImmutableRangeSet.this.ranges.iterator();
            long j = 0;
            while (it.hasNext()) {
                Range range = (Range) it.next();
                if (range.contains(comparable)) {
                    return Ints.saturatedCast(((long) ContiguousSet.create(range, this.domain).indexOf(comparable)) + j);
                }
                j = ((long) ContiguousSet.create(range, this.domain).size()) + j;
            }
            throw new AssertionError("impossible");
        }

        boolean isPartialView() {
            return ImmutableRangeSet.this.ranges.isPartialView();
        }

        public String toString() {
            return ImmutableRangeSet.this.ranges.toString();
        }

        Object writeReplace() {
            return new AsSetSerializedForm(ImmutableRangeSet.this.ranges, this.domain);
        }
    }

    private static class AsSetSerializedForm<C extends Comparable> implements Serializable {
        private final DiscreteDomain<C> domain;
        private final ImmutableList<Range<C>> ranges;

        AsSetSerializedForm(ImmutableList<Range<C>> immutableList, DiscreteDomain<C> discreteDomain) {
            this.ranges = immutableList;
            this.domain = discreteDomain;
        }

        Object readResolve() {
            return new ImmutableRangeSet(this.ranges).asSet(this.domain);
        }
    }

    public static class Builder<C extends Comparable<?>> {
        private final RangeSet<C> rangeSet = TreeRangeSet.create();

        public Builder<C> add(Range<C> range) {
            if (range.isEmpty()) {
                String valueOf = String.valueOf(String.valueOf(range));
                throw new IllegalArgumentException(new StringBuilder(valueOf.length() + 33).append("range must not be empty, but was ").append(valueOf).toString());
            } else if (this.rangeSet.complement().encloses(range)) {
                this.rangeSet.add(range);
                return this;
            } else {
                for (Range range2 : this.rangeSet.asRanges()) {
                    boolean z = !range2.isConnected(range) || range2.intersection(range).isEmpty();
                    Preconditions.checkArgument(z, "Ranges may not overlap, but received %s and %s", range2, range);
                }
                throw new AssertionError("should have thrown an IAE above");
            }
        }

        public Builder<C> addAll(RangeSet<C> rangeSet) {
            for (Range add : rangeSet.asRanges()) {
                add(add);
            }
            return this;
        }

        public ImmutableRangeSet<C> build() {
            return ImmutableRangeSet.copyOf(this.rangeSet);
        }
    }

    private final class ComplementRanges extends ImmutableList<Range<C>> {
        private final boolean positiveBoundedAbove;
        private final boolean positiveBoundedBelow;
        private final int size;

        ComplementRanges() {
            this.positiveBoundedBelow = ((Range) ImmutableRangeSet.this.ranges.get(0)).hasLowerBound();
            this.positiveBoundedAbove = ((Range) Iterables.getLast(ImmutableRangeSet.this.ranges)).hasUpperBound();
            int size = ImmutableRangeSet.this.ranges.size() - 1;
            if (this.positiveBoundedBelow) {
                size++;
            }
            if (this.positiveBoundedAbove) {
                size++;
            }
            this.size = size;
        }

        public int size() {
            return this.size;
        }

        public Range<C> get(int i) {
            Cut belowAll;
            Cut aboveAll;
            Preconditions.checkElementIndex(i, this.size);
            if (this.positiveBoundedBelow) {
                belowAll = i == 0 ? Cut.belowAll() : ((Range) ImmutableRangeSet.this.ranges.get(i - 1)).upperBound;
            } else {
                belowAll = ((Range) ImmutableRangeSet.this.ranges.get(i)).upperBound;
            }
            if (this.positiveBoundedAbove && i == this.size - 1) {
                aboveAll = Cut.aboveAll();
            } else {
                aboveAll = ((Range) ImmutableRangeSet.this.ranges.get((this.positiveBoundedBelow ? 0 : 1) + i)).lowerBound;
            }
            return Range.create(belowAll, aboveAll);
        }

        boolean isPartialView() {
            return true;
        }
    }

    private static final class SerializedForm<C extends Comparable> implements Serializable {
        private final ImmutableList<Range<C>> ranges;

        SerializedForm(ImmutableList<Range<C>> immutableList) {
            this.ranges = immutableList;
        }

        Object readResolve() {
            if (this.ranges.isEmpty()) {
                return ImmutableRangeSet.of();
            }
            if (this.ranges.equals(ImmutableList.of(Range.all()))) {
                return ImmutableRangeSet.all();
            }
            return new ImmutableRangeSet(this.ranges);
        }
    }

    public /* bridge */ /* synthetic */ void clear() {
        super.clear();
    }

    public /* bridge */ /* synthetic */ boolean contains(Comparable comparable) {
        return super.contains(comparable);
    }

    public /* bridge */ /* synthetic */ boolean enclosesAll(RangeSet rangeSet) {
        return super.enclosesAll(rangeSet);
    }

    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    public static <C extends Comparable> ImmutableRangeSet<C> of() {
        return EMPTY;
    }

    static <C extends Comparable> ImmutableRangeSet<C> all() {
        return ALL;
    }

    public static <C extends Comparable> ImmutableRangeSet<C> of(Range<C> range) {
        Preconditions.checkNotNull(range);
        if (range.isEmpty()) {
            return of();
        }
        if (range.equals(Range.all())) {
            return all();
        }
        return new ImmutableRangeSet(ImmutableList.of(range));
    }

    public static <C extends Comparable> ImmutableRangeSet<C> copyOf(RangeSet<C> rangeSet) {
        Preconditions.checkNotNull(rangeSet);
        if (rangeSet.isEmpty()) {
            return of();
        }
        if (rangeSet.encloses(Range.all())) {
            return all();
        }
        if (rangeSet instanceof ImmutableRangeSet) {
            ImmutableRangeSet<C> immutableRangeSet = (ImmutableRangeSet) rangeSet;
            if (!immutableRangeSet.isPartialView()) {
                return immutableRangeSet;
            }
        }
        return new ImmutableRangeSet(ImmutableList.copyOf(rangeSet.asRanges()));
    }

    ImmutableRangeSet(ImmutableList<Range<C>> immutableList) {
        this.ranges = immutableList;
    }

    private ImmutableRangeSet(ImmutableList<Range<C>> immutableList, ImmutableRangeSet<C> immutableRangeSet) {
        this.ranges = immutableList;
        this.complement = immutableRangeSet;
    }

    public boolean encloses(Range<C> range) {
        int binarySearch = SortedLists.binarySearch(this.ranges, Range.lowerBoundFn(), range.lowerBound, Ordering.natural(), KeyPresentBehavior.ANY_PRESENT, KeyAbsentBehavior.NEXT_LOWER);
        return binarySearch != -1 && ((Range) this.ranges.get(binarySearch)).encloses(range);
    }

    public Range<C> rangeContaining(C c) {
        int binarySearch = SortedLists.binarySearch(this.ranges, Range.lowerBoundFn(), Cut.belowValue(c), Ordering.natural(), KeyPresentBehavior.ANY_PRESENT, KeyAbsentBehavior.NEXT_LOWER);
        if (binarySearch == -1) {
            return null;
        }
        Range<C> range = (Range) this.ranges.get(binarySearch);
        return range.contains(c) ? range : null;
    }

    public Range<C> span() {
        if (!this.ranges.isEmpty()) {
            return Range.create(((Range) this.ranges.get(0)).lowerBound, ((Range) this.ranges.get(this.ranges.size() - 1)).upperBound);
        }
        throw new NoSuchElementException();
    }

    public boolean isEmpty() {
        return this.ranges.isEmpty();
    }

    public void add(Range<C> range) {
        throw new UnsupportedOperationException();
    }

    public void addAll(RangeSet<C> rangeSet) {
        throw new UnsupportedOperationException();
    }

    public void remove(Range<C> range) {
        throw new UnsupportedOperationException();
    }

    public void removeAll(RangeSet<C> rangeSet) {
        throw new UnsupportedOperationException();
    }

    public ImmutableSet<Range<C>> asRanges() {
        if (this.ranges.isEmpty()) {
            return ImmutableSet.of();
        }
        return new RegularImmutableSortedSet(this.ranges, Range.RANGE_LEX_ORDERING);
    }

    public ImmutableRangeSet<C> complement() {
        ImmutableRangeSet<C> immutableRangeSet = this.complement;
        if (immutableRangeSet != null) {
            return immutableRangeSet;
        }
        if (this.ranges.isEmpty()) {
            immutableRangeSet = all();
            this.complement = immutableRangeSet;
            return immutableRangeSet;
        } else if (this.ranges.size() == 1 && ((Range) this.ranges.get(0)).equals(Range.all())) {
            immutableRangeSet = of();
            this.complement = immutableRangeSet;
            return immutableRangeSet;
        } else {
            immutableRangeSet = new ImmutableRangeSet(new ComplementRanges(), this);
            this.complement = immutableRangeSet;
            return immutableRangeSet;
        }
    }

    private ImmutableList<Range<C>> intersectRanges(final Range<C> range) {
        if (this.ranges.isEmpty() || range.isEmpty()) {
            return ImmutableList.of();
        }
        if (range.encloses(span())) {
            return this.ranges;
        }
        int binarySearch;
        int binarySearch2;
        if (range.hasLowerBound()) {
            binarySearch = SortedLists.binarySearch(this.ranges, Range.upperBoundFn(), range.lowerBound, KeyPresentBehavior.FIRST_AFTER, KeyAbsentBehavior.NEXT_HIGHER);
        } else {
            binarySearch = 0;
        }
        if (range.hasUpperBound()) {
            binarySearch2 = SortedLists.binarySearch(this.ranges, Range.lowerBoundFn(), range.upperBound, KeyPresentBehavior.FIRST_PRESENT, KeyAbsentBehavior.NEXT_HIGHER);
        } else {
            binarySearch2 = this.ranges.size();
        }
        final int i = binarySearch2 - binarySearch;
        if (i == 0) {
            return ImmutableList.of();
        }
        return new ImmutableList<Range<C>>() {
            public int size() {
                return i;
            }

            public Range<C> get(int i) {
                Preconditions.checkElementIndex(i, i);
                if (i == 0 || i == i - 1) {
                    return ((Range) ImmutableRangeSet.this.ranges.get(binarySearch + i)).intersection(range);
                }
                return (Range) ImmutableRangeSet.this.ranges.get(binarySearch + i);
            }

            boolean isPartialView() {
                return true;
            }
        };
    }

    public ImmutableRangeSet<C> subRangeSet(Range<C> range) {
        if (!isEmpty()) {
            Range span = span();
            if (range.encloses(span)) {
                return this;
            }
            if (range.isConnected(span)) {
                return new ImmutableRangeSet(intersectRanges(range));
            }
        }
        return of();
    }

    public ImmutableSortedSet<C> asSet(DiscreteDomain<C> discreteDomain) {
        Preconditions.checkNotNull(discreteDomain);
        if (isEmpty()) {
            return ImmutableSortedSet.of();
        }
        Range canonical = span().canonical(discreteDomain);
        if (canonical.hasLowerBound()) {
            if (!canonical.hasUpperBound()) {
                try {
                    discreteDomain.maxValue();
                } catch (NoSuchElementException e) {
                    throw new IllegalArgumentException("Neither the DiscreteDomain nor this range set are bounded above");
                }
            }
            return new AsSet(discreteDomain);
        }
        throw new IllegalArgumentException("Neither the DiscreteDomain nor this range set are bounded below");
    }

    boolean isPartialView() {
        return this.ranges.isPartialView();
    }

    public static <C extends Comparable<?>> Builder<C> builder() {
        return new Builder();
    }

    Object writeReplace() {
        return new SerializedForm(this.ranges);
    }
}
