package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.common.math.LongMath;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import java.math.RoundingMode;
import java.util.Arrays;

enum BloomFilterStrategies implements BloomFilter$Strategy {
    MURMUR128_MITZ_32 {
        public <T> boolean put(T t, Funnel<? super T> funnel, int i, BitArray bitArray) {
            long bitSize = bitArray.bitSize();
            long asLong = Hashing.murmur3_128().hashObject(t, funnel).asLong();
            int i2 = (int) asLong;
            int i3 = (int) (asLong >>> 32);
            boolean z = false;
            for (int i4 = 1; i4 <= i; i4++) {
                int i5 = (i4 * i3) + i2;
                if (i5 < 0) {
                    i5 ^= -1;
                }
                z |= bitArray.set(((long) i5) % bitSize);
            }
            return z;
        }

        public <T> boolean mightContain(T t, Funnel<? super T> funnel, int i, BitArray bitArray) {
            long bitSize = bitArray.bitSize();
            long asLong = Hashing.murmur3_128().hashObject(t, funnel).asLong();
            int i2 = (int) asLong;
            int i3 = (int) (asLong >>> 32);
            for (int i4 = 1; i4 <= i; i4++) {
                int i5 = (i4 * i3) + i2;
                if (i5 < 0) {
                    i5 ^= -1;
                }
                if (!bitArray.get(((long) i5) % bitSize)) {
                    return false;
                }
            }
            return true;
        }
    },
    MURMUR128_MITZ_64 {
        public <T> boolean put(T t, Funnel<? super T> funnel, int i, BitArray bitArray) {
            int i2 = 0;
            long bitSize = bitArray.bitSize();
            byte[] bytesInternal = Hashing.murmur3_128().hashObject(t, funnel).getBytesInternal();
            long lowerEight = lowerEight(bytesInternal);
            long upperEight = upperEight(bytesInternal);
            boolean z = false;
            while (i2 < i) {
                z |= bitArray.set((Long.MAX_VALUE & lowerEight) % bitSize);
                lowerEight += upperEight;
                i2++;
            }
            return z;
        }

        public <T> boolean mightContain(T t, Funnel<? super T> funnel, int i, BitArray bitArray) {
            long bitSize = bitArray.bitSize();
            byte[] bytesInternal = Hashing.murmur3_128().hashObject(t, funnel).getBytesInternal();
            long lowerEight = lowerEight(bytesInternal);
            long upperEight = upperEight(bytesInternal);
            for (int i2 = 0; i2 < i; i2++) {
                if (!bitArray.get((Long.MAX_VALUE & lowerEight) % bitSize)) {
                    return false;
                }
                lowerEight += upperEight;
            }
            return true;
        }

        private long lowerEight(byte[] bArr) {
            return Longs.fromBytes(bArr[7], bArr[6], bArr[5], bArr[4], bArr[3], bArr[2], bArr[1], bArr[0]);
        }

        private long upperEight(byte[] bArr) {
            return Longs.fromBytes(bArr[15], bArr[14], bArr[13], bArr[12], bArr[11], bArr[10], bArr[9], bArr[8]);
        }
    };

    static final class BitArray {
        long bitCount;
        final long[] data;

        BitArray(long j) {
            this(new long[Ints.checkedCast(LongMath.divide(j, 64, RoundingMode.CEILING))]);
        }

        BitArray(long[] jArr) {
            boolean z;
            int i = 0;
            if (jArr.length > 0) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkArgument(z, "data length is zero!");
            this.data = jArr;
            long j = 0;
            while (i < jArr.length) {
                j += (long) Long.bitCount(jArr[i]);
                i++;
            }
            this.bitCount = j;
        }

        boolean set(long j) {
            if (get(j)) {
                return false;
            }
            long[] jArr = this.data;
            int i = (int) (j >>> 6);
            jArr[i] = jArr[i] | (1 << ((int) j));
            this.bitCount++;
            return true;
        }

        boolean get(long j) {
            return (this.data[(int) (j >>> 6)] & (1 << ((int) j))) != 0;
        }

        long bitSize() {
            return ((long) this.data.length) * 64;
        }

        long bitCount() {
            return this.bitCount;
        }

        BitArray copy() {
            return new BitArray((long[]) this.data.clone());
        }

        void putAll(BitArray bitArray) {
            boolean z;
            int i = 0;
            if (this.data.length == bitArray.data.length) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkArgument(z, "BitArrays must be of equal length (%s != %s)", Integer.valueOf(this.data.length), Integer.valueOf(bitArray.data.length));
            this.bitCount = 0;
            while (i < this.data.length) {
                long[] jArr = this.data;
                jArr[i] = jArr[i] | bitArray.data[i];
                this.bitCount += (long) Long.bitCount(this.data[i]);
                i++;
            }
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof BitArray)) {
                return false;
            }
            return Arrays.equals(this.data, ((BitArray) obj).data);
        }

        public int hashCode() {
            return Arrays.hashCode(this.data);
        }
    }
}
