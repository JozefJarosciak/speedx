package com.google.common.collect;

class Range$3 extends Ordering<Range<?>> {
    Range$3() {
    }

    public int compare(Range<?> range, Range<?> range2) {
        return ComparisonChain.start().compare(range.lowerBound, range2.lowerBound).compare(range.upperBound, range2.upperBound).result();
    }
}
