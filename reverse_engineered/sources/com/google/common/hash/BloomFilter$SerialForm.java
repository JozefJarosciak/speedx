package com.google.common.hash;

import java.io.Serializable;

class BloomFilter$SerialForm<T> implements Serializable {
    private static final long serialVersionUID = 1;
    final long[] data;
    final Funnel<? super T> funnel;
    final int numHashFunctions;
    final BloomFilter$Strategy strategy;

    BloomFilter$SerialForm(BloomFilter<T> bloomFilter) {
        this.data = BloomFilter.access$000(bloomFilter).data;
        this.numHashFunctions = BloomFilter.access$100(bloomFilter);
        this.funnel = BloomFilter.access$200(bloomFilter);
        this.strategy = BloomFilter.access$300(bloomFilter);
    }

    Object readResolve() {
        return new BloomFilter(new BitArray(this.data), this.numHashFunctions, this.funnel, this.strategy, null);
    }
}
