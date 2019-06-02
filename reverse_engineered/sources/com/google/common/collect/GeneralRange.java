package com.google.common.collect;

import ch.qos.logback.core.CoreConstants;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.Comparator;

@GwtCompatible(serializable = true)
final class GeneralRange<T> implements Serializable {
    private final Comparator<? super T> comparator;
    private final boolean hasLowerBound;
    private final boolean hasUpperBound;
    private final BoundType lowerBoundType;
    private final T lowerEndpoint;
    private transient GeneralRange<T> reverse;
    private final BoundType upperBoundType;
    private final T upperEndpoint;

    static <T extends Comparable> GeneralRange<T> from(Range<T> range) {
        Object upperEndpoint;
        Object lowerEndpoint = range.hasLowerBound() ? range.lowerEndpoint() : null;
        BoundType lowerBoundType = range.hasLowerBound() ? range.lowerBoundType() : BoundType.OPEN;
        if (range.hasUpperBound()) {
            upperEndpoint = range.upperEndpoint();
        } else {
            upperEndpoint = null;
        }
        return new GeneralRange(Ordering.natural(), range.hasLowerBound(), lowerEndpoint, lowerBoundType, range.hasUpperBound(), upperEndpoint, range.hasUpperBound() ? range.upperBoundType() : BoundType.OPEN);
    }

    static <T> GeneralRange<T> all(Comparator<? super T> comparator) {
        return new GeneralRange(comparator, false, null, BoundType.OPEN, false, null, BoundType.OPEN);
    }

    static <T> GeneralRange<T> downTo(Comparator<? super T> comparator, T t, BoundType boundType) {
        return new GeneralRange(comparator, true, t, boundType, false, null, BoundType.OPEN);
    }

    static <T> GeneralRange<T> upTo(Comparator<? super T> comparator, T t, BoundType boundType) {
        return new GeneralRange(comparator, false, null, BoundType.OPEN, true, t, boundType);
    }

    static <T> GeneralRange<T> range(Comparator<? super T> comparator, T t, BoundType boundType, T t2, BoundType boundType2) {
        return new GeneralRange(comparator, true, t, boundType, true, t2, boundType2);
    }

    private GeneralRange(Comparator<? super T> comparator, boolean z, T t, BoundType boundType, boolean z2, T t2, BoundType boundType2) {
        int i = 1;
        this.comparator = (Comparator) Preconditions.checkNotNull(comparator);
        this.hasLowerBound = z;
        this.hasUpperBound = z2;
        this.lowerEndpoint = t;
        this.lowerBoundType = (BoundType) Preconditions.checkNotNull(boundType);
        this.upperEndpoint = t2;
        this.upperBoundType = (BoundType) Preconditions.checkNotNull(boundType2);
        if (z) {
            comparator.compare(t, t);
        }
        if (z2) {
            comparator.compare(t2, t2);
        }
        if (z && z2) {
            int compare = comparator.compare(t, t2);
            Preconditions.checkArgument(compare <= 0, "lowerEndpoint (%s) > upperEndpoint (%s)", t, t2);
            if (compare == 0) {
                int i2;
                if (boundType != BoundType.OPEN) {
                    i2 = 1;
                } else {
                    i2 = 0;
                }
                if (boundType2 == BoundType.OPEN) {
                    i = 0;
                }
                Preconditions.checkArgument(i2 | i);
            }
        }
    }

    Comparator<? super T> comparator() {
        return this.comparator;
    }

    boolean hasLowerBound() {
        return this.hasLowerBound;
    }

    boolean hasUpperBound() {
        return this.hasUpperBound;
    }

    boolean isEmpty() {
        return (hasUpperBound() && tooLow(getUpperEndpoint())) || (hasLowerBound() && tooHigh(getLowerEndpoint()));
    }

    boolean tooLow(T t) {
        int i = 1;
        if (!hasLowerBound()) {
            return false;
        }
        int compare = this.comparator.compare(t, getLowerEndpoint());
        int i2 = compare < 0 ? 1 : 0;
        compare = compare == 0 ? 1 : 0;
        if (getLowerBoundType() != BoundType.OPEN) {
            i = 0;
        }
        return i2 | (compare & i);
    }

    boolean tooHigh(T t) {
        int i = 1;
        if (!hasUpperBound()) {
            return false;
        }
        int compare = this.comparator.compare(t, getUpperEndpoint());
        int i2 = compare > 0 ? 1 : 0;
        compare = compare == 0 ? 1 : 0;
        if (getUpperBoundType() != BoundType.OPEN) {
            i = 0;
        }
        return i2 | (compare & i);
    }

    boolean contains(T t) {
        return (tooLow(t) || tooHigh(t)) ? false : true;
    }

    GeneralRange<T> intersect(GeneralRange<T> generalRange) {
        int compare;
        BoundType boundType;
        Object obj;
        Preconditions.checkNotNull(generalRange);
        Preconditions.checkArgument(this.comparator.equals(generalRange.comparator));
        boolean z = this.hasLowerBound;
        Object lowerEndpoint = getLowerEndpoint();
        BoundType lowerBoundType = getLowerBoundType();
        if (!hasLowerBound()) {
            z = generalRange.hasLowerBound;
            lowerEndpoint = generalRange.getLowerEndpoint();
            lowerBoundType = generalRange.getLowerBoundType();
        } else if (generalRange.hasLowerBound()) {
            compare = this.comparator.compare(getLowerEndpoint(), generalRange.getLowerEndpoint());
            if (compare < 0 || (compare == 0 && generalRange.getLowerBoundType() == BoundType.OPEN)) {
                lowerEndpoint = generalRange.getLowerEndpoint();
                lowerBoundType = generalRange.getLowerBoundType();
            }
        }
        boolean z2 = this.hasUpperBound;
        Object upperEndpoint = getUpperEndpoint();
        BoundType upperBoundType = getUpperBoundType();
        if (!hasUpperBound()) {
            z2 = generalRange.hasUpperBound;
            upperEndpoint = generalRange.getUpperEndpoint();
            upperBoundType = generalRange.getUpperBoundType();
        } else if (generalRange.hasUpperBound()) {
            compare = this.comparator.compare(getUpperEndpoint(), generalRange.getUpperEndpoint());
            if (compare > 0 || (compare == 0 && generalRange.getUpperBoundType() == BoundType.OPEN)) {
                upperEndpoint = generalRange.getUpperEndpoint();
                upperBoundType = generalRange.getUpperBoundType();
            }
        }
        if (z && z2) {
            compare = this.comparator.compare(lowerEndpoint, upperEndpoint);
            if (compare > 0 || (compare == 0 && lowerBoundType == BoundType.OPEN && r7 == BoundType.OPEN)) {
                lowerBoundType = BoundType.OPEN;
                upperBoundType = BoundType.CLOSED;
                boundType = lowerBoundType;
                obj = upperEndpoint;
                return new GeneralRange(this.comparator, z, obj, boundType, z2, upperEndpoint, upperBoundType);
            }
        }
        boundType = lowerBoundType;
        obj = lowerEndpoint;
        return new GeneralRange(this.comparator, z, obj, boundType, z2, upperEndpoint, upperBoundType);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GeneralRange)) {
            return false;
        }
        GeneralRange generalRange = (GeneralRange) obj;
        if (this.comparator.equals(generalRange.comparator) && this.hasLowerBound == generalRange.hasLowerBound && this.hasUpperBound == generalRange.hasUpperBound && getLowerBoundType().equals(generalRange.getLowerBoundType()) && getUpperBoundType().equals(generalRange.getUpperBoundType()) && Objects.equal(getLowerEndpoint(), generalRange.getLowerEndpoint()) && Objects.equal(getUpperEndpoint(), generalRange.getUpperEndpoint())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.comparator, getLowerEndpoint(), getLowerBoundType(), getUpperEndpoint(), getUpperBoundType()});
    }

    GeneralRange<T> reverse() {
        GeneralRange<T> generalRange = this.reverse;
        if (generalRange != null) {
            return generalRange;
        }
        generalRange = new GeneralRange(Ordering.from(this.comparator).reverse(), this.hasUpperBound, getUpperEndpoint(), getUpperBoundType(), this.hasLowerBound, getLowerEndpoint(), getLowerBoundType());
        generalRange.reverse = this;
        this.reverse = generalRange;
        return generalRange;
    }

    public String toString() {
        return this.comparator + ":" + (this.lowerBoundType == BoundType.CLOSED ? '[' : CoreConstants.LEFT_PARENTHESIS_CHAR) + (this.hasLowerBound ? this.lowerEndpoint : "-∞") + CoreConstants.COMMA_CHAR + (this.hasUpperBound ? this.upperEndpoint : "∞") + (this.upperBoundType == BoundType.CLOSED ? ']' : CoreConstants.RIGHT_PARENTHESIS_CHAR);
    }

    T getLowerEndpoint() {
        return this.lowerEndpoint;
    }

    BoundType getLowerBoundType() {
        return this.lowerBoundType;
    }

    T getUpperEndpoint() {
        return this.upperEndpoint;
    }

    BoundType getUpperBoundType() {
        return this.upperBoundType;
    }
}
