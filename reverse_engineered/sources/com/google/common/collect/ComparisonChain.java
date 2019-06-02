package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.primitives.Booleans;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import java.util.Comparator;

@GwtCompatible
public abstract class ComparisonChain {
    private static final ComparisonChain ACTIVE = new C35641();
    private static final ComparisonChain GREATER = new InactiveComparisonChain(1);
    private static final ComparisonChain LESS = new InactiveComparisonChain(-1);

    /* renamed from: com.google.common.collect.ComparisonChain$1 */
    static class C35641 extends ComparisonChain {
        C35641() {
            super();
        }

        public ComparisonChain compare(Comparable comparable, Comparable comparable2) {
            return classify(comparable.compareTo(comparable2));
        }

        public <T> ComparisonChain compare(T t, T t2, Comparator<T> comparator) {
            return classify(comparator.compare(t, t2));
        }

        public ComparisonChain compare(int i, int i2) {
            return classify(Ints.compare(i, i2));
        }

        public ComparisonChain compare(long j, long j2) {
            return classify(Longs.compare(j, j2));
        }

        public ComparisonChain compare(float f, float f2) {
            return classify(Float.compare(f, f2));
        }

        public ComparisonChain compare(double d, double d2) {
            return classify(Double.compare(d, d2));
        }

        public ComparisonChain compareTrueFirst(boolean z, boolean z2) {
            return classify(Booleans.compare(z2, z));
        }

        public ComparisonChain compareFalseFirst(boolean z, boolean z2) {
            return classify(Booleans.compare(z, z2));
        }

        ComparisonChain classify(int i) {
            if (i < 0) {
                return ComparisonChain.LESS;
            }
            return i > 0 ? ComparisonChain.GREATER : ComparisonChain.ACTIVE;
        }

        public int result() {
            return 0;
        }
    }

    private static final class InactiveComparisonChain extends ComparisonChain {
        final int result;

        InactiveComparisonChain(int i) {
            super();
            this.result = i;
        }

        public ComparisonChain compare(Comparable comparable, Comparable comparable2) {
            return this;
        }

        public <T> ComparisonChain compare(T t, T t2, Comparator<T> comparator) {
            return this;
        }

        public ComparisonChain compare(int i, int i2) {
            return this;
        }

        public ComparisonChain compare(long j, long j2) {
            return this;
        }

        public ComparisonChain compare(float f, float f2) {
            return this;
        }

        public ComparisonChain compare(double d, double d2) {
            return this;
        }

        public ComparisonChain compareTrueFirst(boolean z, boolean z2) {
            return this;
        }

        public ComparisonChain compareFalseFirst(boolean z, boolean z2) {
            return this;
        }

        public int result() {
            return this.result;
        }
    }

    public abstract ComparisonChain compare(double d, double d2);

    public abstract ComparisonChain compare(float f, float f2);

    public abstract ComparisonChain compare(int i, int i2);

    public abstract ComparisonChain compare(long j, long j2);

    public abstract ComparisonChain compare(Comparable<?> comparable, Comparable<?> comparable2);

    public abstract <T> ComparisonChain compare(T t, T t2, Comparator<T> comparator);

    public abstract ComparisonChain compareFalseFirst(boolean z, boolean z2);

    public abstract ComparisonChain compareTrueFirst(boolean z, boolean z2);

    public abstract int result();

    private ComparisonChain() {
    }

    public static ComparisonChain start() {
        return ACTIVE;
    }
}
