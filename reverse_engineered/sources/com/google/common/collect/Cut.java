package com.google.common.collect;

import ch.qos.logback.core.CoreConstants;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Booleans;
import java.io.Serializable;
import java.util.NoSuchElementException;

@GwtCompatible
abstract class Cut<C extends Comparable> implements Serializable, Comparable<Cut<C>> {
    private static final long serialVersionUID = 0;
    final C endpoint;

    private static final class AboveAll extends Cut<Comparable<?>> {
        private static final AboveAll INSTANCE = new AboveAll();
        private static final long serialVersionUID = 0;

        private AboveAll() {
            super(null);
        }

        Comparable<?> endpoint() {
            throw new IllegalStateException("range unbounded on this side");
        }

        boolean isLessThan(Comparable<?> comparable) {
            return false;
        }

        BoundType typeAsLowerBound() {
            throw new AssertionError("this statement should be unreachable");
        }

        BoundType typeAsUpperBound() {
            throw new IllegalStateException();
        }

        Cut<Comparable<?>> withLowerBoundType(BoundType boundType, DiscreteDomain<Comparable<?>> discreteDomain) {
            throw new AssertionError("this statement should be unreachable");
        }

        Cut<Comparable<?>> withUpperBoundType(BoundType boundType, DiscreteDomain<Comparable<?>> discreteDomain) {
            throw new IllegalStateException();
        }

        void describeAsLowerBound(StringBuilder stringBuilder) {
            throw new AssertionError();
        }

        void describeAsUpperBound(StringBuilder stringBuilder) {
            stringBuilder.append("+∞)");
        }

        Comparable<?> leastValueAbove(DiscreteDomain<Comparable<?>> discreteDomain) {
            throw new AssertionError();
        }

        Comparable<?> greatestValueBelow(DiscreteDomain<Comparable<?>> discreteDomain) {
            return discreteDomain.maxValue();
        }

        public int compareTo(Cut<Comparable<?>> cut) {
            return cut == this ? 0 : 1;
        }

        public String toString() {
            return "+∞";
        }

        private Object readResolve() {
            return INSTANCE;
        }
    }

    private static final class AboveValue<C extends Comparable> extends Cut<C> {
        private static final long serialVersionUID = 0;

        public /* bridge */ /* synthetic */ int compareTo(Object obj) {
            return super.compareTo((Cut) obj);
        }

        AboveValue(C c) {
            super((Comparable) Preconditions.checkNotNull(c));
        }

        boolean isLessThan(C c) {
            return Range.compareOrThrow(this.endpoint, c) < 0;
        }

        BoundType typeAsLowerBound() {
            return BoundType.OPEN;
        }

        BoundType typeAsUpperBound() {
            return BoundType.CLOSED;
        }

        Cut<C> withLowerBoundType(BoundType boundType, DiscreteDomain<C> discreteDomain) {
            switch (boundType) {
                case CLOSED:
                    Comparable next = discreteDomain.next(this.endpoint);
                    return next == null ? Cut.belowAll() : Cut.belowValue(next);
                case OPEN:
                    return this;
                default:
                    throw new AssertionError();
            }
        }

        Cut<C> withUpperBoundType(BoundType boundType, DiscreteDomain<C> discreteDomain) {
            switch (boundType) {
                case CLOSED:
                    return this;
                case OPEN:
                    Comparable next = discreteDomain.next(this.endpoint);
                    return next == null ? Cut.aboveAll() : Cut.belowValue(next);
                default:
                    throw new AssertionError();
            }
        }

        void describeAsLowerBound(StringBuilder stringBuilder) {
            stringBuilder.append(CoreConstants.LEFT_PARENTHESIS_CHAR).append(this.endpoint);
        }

        void describeAsUpperBound(StringBuilder stringBuilder) {
            stringBuilder.append(this.endpoint).append(']');
        }

        C leastValueAbove(DiscreteDomain<C> discreteDomain) {
            return discreteDomain.next(this.endpoint);
        }

        C greatestValueBelow(DiscreteDomain<C> discreteDomain) {
            return this.endpoint;
        }

        Cut<C> canonical(DiscreteDomain<C> discreteDomain) {
            Comparable leastValueAbove = leastValueAbove(discreteDomain);
            return leastValueAbove != null ? Cut.belowValue(leastValueAbove) : Cut.aboveAll();
        }

        public int hashCode() {
            return this.endpoint.hashCode() ^ -1;
        }

        public String toString() {
            String valueOf = String.valueOf(String.valueOf(this.endpoint));
            return new StringBuilder(valueOf.length() + 2).append("/").append(valueOf).append("\\").toString();
        }
    }

    private static final class BelowAll extends Cut<Comparable<?>> {
        private static final BelowAll INSTANCE = new BelowAll();
        private static final long serialVersionUID = 0;

        private BelowAll() {
            super(null);
        }

        Comparable<?> endpoint() {
            throw new IllegalStateException("range unbounded on this side");
        }

        boolean isLessThan(Comparable<?> comparable) {
            return true;
        }

        BoundType typeAsLowerBound() {
            throw new IllegalStateException();
        }

        BoundType typeAsUpperBound() {
            throw new AssertionError("this statement should be unreachable");
        }

        Cut<Comparable<?>> withLowerBoundType(BoundType boundType, DiscreteDomain<Comparable<?>> discreteDomain) {
            throw new IllegalStateException();
        }

        Cut<Comparable<?>> withUpperBoundType(BoundType boundType, DiscreteDomain<Comparable<?>> discreteDomain) {
            throw new AssertionError("this statement should be unreachable");
        }

        void describeAsLowerBound(StringBuilder stringBuilder) {
            stringBuilder.append("(-∞");
        }

        void describeAsUpperBound(StringBuilder stringBuilder) {
            throw new AssertionError();
        }

        Comparable<?> leastValueAbove(DiscreteDomain<Comparable<?>> discreteDomain) {
            return discreteDomain.minValue();
        }

        Comparable<?> greatestValueBelow(DiscreteDomain<Comparable<?>> discreteDomain) {
            throw new AssertionError();
        }

        Cut<Comparable<?>> canonical(DiscreteDomain<Comparable<?>> discreteDomain) {
            Cut<Comparable<?>> belowValue;
            try {
                belowValue = Cut.belowValue(discreteDomain.minValue());
            } catch (NoSuchElementException e) {
            }
            return belowValue;
        }

        public int compareTo(Cut<Comparable<?>> cut) {
            return cut == this ? 0 : -1;
        }

        public String toString() {
            return "-∞";
        }

        private Object readResolve() {
            return INSTANCE;
        }
    }

    private static final class BelowValue<C extends Comparable> extends Cut<C> {
        private static final long serialVersionUID = 0;

        public /* bridge */ /* synthetic */ int compareTo(Object obj) {
            return super.compareTo((Cut) obj);
        }

        BelowValue(C c) {
            super((Comparable) Preconditions.checkNotNull(c));
        }

        boolean isLessThan(C c) {
            return Range.compareOrThrow(this.endpoint, c) <= 0;
        }

        BoundType typeAsLowerBound() {
            return BoundType.CLOSED;
        }

        BoundType typeAsUpperBound() {
            return BoundType.OPEN;
        }

        Cut<C> withLowerBoundType(BoundType boundType, DiscreteDomain<C> discreteDomain) {
            switch (boundType) {
                case CLOSED:
                    return this;
                case OPEN:
                    Comparable previous = discreteDomain.previous(this.endpoint);
                    return previous == null ? Cut.belowAll() : new AboveValue(previous);
                default:
                    throw new AssertionError();
            }
        }

        Cut<C> withUpperBoundType(BoundType boundType, DiscreteDomain<C> discreteDomain) {
            switch (boundType) {
                case CLOSED:
                    Comparable previous = discreteDomain.previous(this.endpoint);
                    return previous == null ? Cut.aboveAll() : new AboveValue(previous);
                case OPEN:
                    return this;
                default:
                    throw new AssertionError();
            }
        }

        void describeAsLowerBound(StringBuilder stringBuilder) {
            stringBuilder.append('[').append(this.endpoint);
        }

        void describeAsUpperBound(StringBuilder stringBuilder) {
            stringBuilder.append(this.endpoint).append(CoreConstants.RIGHT_PARENTHESIS_CHAR);
        }

        C leastValueAbove(DiscreteDomain<C> discreteDomain) {
            return this.endpoint;
        }

        C greatestValueBelow(DiscreteDomain<C> discreteDomain) {
            return discreteDomain.previous(this.endpoint);
        }

        public int hashCode() {
            return this.endpoint.hashCode();
        }

        public String toString() {
            String valueOf = String.valueOf(String.valueOf(this.endpoint));
            return new StringBuilder(valueOf.length() + 2).append("\\").append(valueOf).append("/").toString();
        }
    }

    abstract void describeAsLowerBound(StringBuilder stringBuilder);

    abstract void describeAsUpperBound(StringBuilder stringBuilder);

    abstract C greatestValueBelow(DiscreteDomain<C> discreteDomain);

    abstract boolean isLessThan(C c);

    abstract C leastValueAbove(DiscreteDomain<C> discreteDomain);

    abstract BoundType typeAsLowerBound();

    abstract BoundType typeAsUpperBound();

    abstract Cut<C> withLowerBoundType(BoundType boundType, DiscreteDomain<C> discreteDomain);

    abstract Cut<C> withUpperBoundType(BoundType boundType, DiscreteDomain<C> discreteDomain);

    Cut(C c) {
        this.endpoint = c;
    }

    Cut<C> canonical(DiscreteDomain<C> discreteDomain) {
        return this;
    }

    public int compareTo(Cut<C> cut) {
        if (cut == belowAll()) {
            return 1;
        }
        if (cut == aboveAll()) {
            return -1;
        }
        int compareOrThrow = Range.compareOrThrow(this.endpoint, cut.endpoint);
        return compareOrThrow == 0 ? Booleans.compare(this instanceof AboveValue, cut instanceof AboveValue) : compareOrThrow;
    }

    C endpoint() {
        return this.endpoint;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Cut)) {
            return false;
        }
        try {
            if (compareTo((Cut) obj) == 0) {
                return true;
            }
            return false;
        } catch (ClassCastException e) {
            return false;
        }
    }

    static <C extends Comparable> Cut<C> belowAll() {
        return BelowAll.INSTANCE;
    }

    static <C extends Comparable> Cut<C> aboveAll() {
        return AboveAll.INSTANCE;
    }

    static <C extends Comparable> Cut<C> belowValue(C c) {
        return new BelowValue(c);
    }

    static <C extends Comparable> Cut<C> aboveValue(C c) {
        return new AboveValue(c);
    }
}
