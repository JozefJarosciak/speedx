package com.google.common.hash;

import com.google.common.annotations.Beta;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.hash.BloomFilterStrategies.BitArray;
import com.google.common.primitives.SignedBytes;
import com.google.common.primitives.UnsignedBytes;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

@Beta
public final class BloomFilter<T> implements Predicate<T>, Serializable {
    private static final BloomFilter$Strategy DEFAULT_STRATEGY = BloomFilterStrategies.MURMUR128_MITZ_64;
    private final BitArray bits;
    private final Funnel<? super T> funnel;
    private final int numHashFunctions;
    private final BloomFilter$Strategy strategy;

    private BloomFilter(BitArray bitArray, int i, Funnel<? super T> funnel, BloomFilter$Strategy bloomFilter$Strategy) {
        boolean z;
        Preconditions.checkArgument(i > 0, "numHashFunctions (%s) must be > 0", new Object[]{Integer.valueOf(i)});
        if (i <= 255) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "numHashFunctions (%s) must be <= 255", new Object[]{Integer.valueOf(i)});
        this.bits = (BitArray) Preconditions.checkNotNull(bitArray);
        this.numHashFunctions = i;
        this.funnel = (Funnel) Preconditions.checkNotNull(funnel);
        this.strategy = (BloomFilter$Strategy) Preconditions.checkNotNull(bloomFilter$Strategy);
    }

    public BloomFilter<T> copy() {
        return new BloomFilter(this.bits.copy(), this.numHashFunctions, this.funnel, this.strategy);
    }

    public boolean mightContain(T t) {
        return this.strategy.mightContain(t, this.funnel, this.numHashFunctions, this.bits);
    }

    @Deprecated
    public boolean apply(T t) {
        return mightContain(t);
    }

    public boolean put(T t) {
        return this.strategy.put(t, this.funnel, this.numHashFunctions, this.bits);
    }

    public double expectedFpp() {
        return Math.pow(((double) this.bits.bitCount()) / ((double) bitSize()), (double) this.numHashFunctions);
    }

    @VisibleForTesting
    long bitSize() {
        return this.bits.bitSize();
    }

    public boolean isCompatible(BloomFilter<T> bloomFilter) {
        Preconditions.checkNotNull(bloomFilter);
        return this != bloomFilter && this.numHashFunctions == bloomFilter.numHashFunctions && bitSize() == bloomFilter.bitSize() && this.strategy.equals(bloomFilter.strategy) && this.funnel.equals(bloomFilter.funnel);
    }

    public void putAll(BloomFilter<T> bloomFilter) {
        boolean z;
        Preconditions.checkNotNull(bloomFilter);
        Preconditions.checkArgument(this != bloomFilter, "Cannot combine a BloomFilter with itself.");
        if (this.numHashFunctions == bloomFilter.numHashFunctions) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "BloomFilters must have the same number of hash functions (%s != %s)", new Object[]{Integer.valueOf(this.numHashFunctions), Integer.valueOf(bloomFilter.numHashFunctions)});
        if (bitSize() == bloomFilter.bitSize()) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "BloomFilters must have the same size underlying bit arrays (%s != %s)", new Object[]{Long.valueOf(bitSize()), Long.valueOf(bloomFilter.bitSize())});
        Preconditions.checkArgument(this.strategy.equals(bloomFilter.strategy), "BloomFilters must have equal strategies (%s != %s)", new Object[]{this.strategy, bloomFilter.strategy});
        Preconditions.checkArgument(this.funnel.equals(bloomFilter.funnel), "BloomFilters must have equal funnels (%s != %s)", new Object[]{this.funnel, bloomFilter.funnel});
        this.bits.putAll(bloomFilter.bits);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BloomFilter)) {
            return false;
        }
        BloomFilter bloomFilter = (BloomFilter) obj;
        if (this.numHashFunctions == bloomFilter.numHashFunctions && this.funnel.equals(bloomFilter.funnel) && this.bits.equals(bloomFilter.bits) && this.strategy.equals(bloomFilter.strategy)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.numHashFunctions), this.funnel, this.strategy, this.bits);
    }

    public static <T> BloomFilter<T> create(Funnel<? super T> funnel, int i, double d) {
        return create(funnel, i, d, DEFAULT_STRATEGY);
    }

    @VisibleForTesting
    static <T> BloomFilter<T> create(Funnel<? super T> funnel, int i, double d, BloomFilter$Strategy bloomFilter$Strategy) {
        boolean z;
        int i2 = 1;
        Preconditions.checkNotNull(funnel);
        Preconditions.checkArgument(i >= 0, "Expected insertions (%s) must be >= 0", new Object[]{Integer.valueOf(i)});
        if (d > 0.0d) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "False positive probability (%s) must be > 0.0", new Object[]{Double.valueOf(d)});
        if (d < 1.0d) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "False positive probability (%s) must be < 1.0", new Object[]{Double.valueOf(d)});
        Preconditions.checkNotNull(bloomFilter$Strategy);
        if (i != 0) {
            i2 = i;
        }
        long optimalNumOfBits = optimalNumOfBits((long) i2, d);
        try {
            return new BloomFilter(new BitArray(optimalNumOfBits), optimalNumOfHashFunctions((long) i2, optimalNumOfBits), funnel, bloomFilter$Strategy);
        } catch (Throwable e) {
            throw new IllegalArgumentException("Could not create BloomFilter of " + optimalNumOfBits + " bits", e);
        }
    }

    public static <T> BloomFilter<T> create(Funnel<? super T> funnel, int i) {
        return create(funnel, i, 0.03d);
    }

    @VisibleForTesting
    static int optimalNumOfHashFunctions(long j, long j2) {
        return Math.max(1, (int) Math.round((((double) j2) / ((double) j)) * Math.log(2.0d)));
    }

    @VisibleForTesting
    static long optimalNumOfBits(long j, double d) {
        if (d == 0.0d) {
            d = Double.MIN_VALUE;
        }
        return (long) ((((double) (-j)) * Math.log(d)) / (Math.log(2.0d) * Math.log(2.0d)));
    }

    private Object writeReplace() {
        return new BloomFilter$SerialForm(this);
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeByte(SignedBytes.checkedCast((long) this.strategy.ordinal()));
        dataOutputStream.writeByte(UnsignedBytes.checkedCast((long) this.numHashFunctions));
        dataOutputStream.writeInt(this.bits.data.length);
        for (long writeLong : this.bits.data) {
            dataOutputStream.writeLong(writeLong);
        }
    }

    public static <T> BloomFilter<T> readFrom(InputStream inputStream, Funnel<T> funnel) throws IOException {
        int readByte;
        int toInt;
        Throwable e;
        String valueOf;
        IOException iOException;
        Preconditions.checkNotNull(inputStream, "InputStream");
        Preconditions.checkNotNull(funnel, "Funnel");
        try {
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            readByte = dataInputStream.readByte();
            try {
                toInt = UnsignedBytes.toInt(dataInputStream.readByte());
            } catch (RuntimeException e2) {
                e = e2;
                toInt = -1;
                valueOf = String.valueOf(String.valueOf("Unable to deserialize BloomFilter from InputStream. strategyOrdinal: "));
                iOException = new IOException(new StringBuilder(valueOf.length() + 65).append(valueOf).append(readByte).append(" numHashFunctions: ").append(toInt).append(" dataLength: ").append(-1).toString());
                iOException.initCause(e);
                throw iOException;
            }
            try {
                int readInt = dataInputStream.readInt();
                BloomFilter$Strategy bloomFilter$Strategy = BloomFilterStrategies.values()[readByte];
                long[] jArr = new long[readInt];
                for (int i = 0; i < jArr.length; i++) {
                    jArr[i] = dataInputStream.readLong();
                }
                return new BloomFilter(new BitArray(jArr), toInt, funnel, bloomFilter$Strategy);
            } catch (RuntimeException e3) {
                e = e3;
                valueOf = String.valueOf(String.valueOf("Unable to deserialize BloomFilter from InputStream. strategyOrdinal: "));
                iOException = new IOException(new StringBuilder(valueOf.length() + 65).append(valueOf).append(readByte).append(" numHashFunctions: ").append(toInt).append(" dataLength: ").append(-1).toString());
                iOException.initCause(e);
                throw iOException;
            }
        } catch (RuntimeException e4) {
            e = e4;
            toInt = -1;
            readByte = -1;
            valueOf = String.valueOf(String.valueOf("Unable to deserialize BloomFilter from InputStream. strategyOrdinal: "));
            iOException = new IOException(new StringBuilder(valueOf.length() + 65).append(valueOf).append(readByte).append(" numHashFunctions: ").append(toInt).append(" dataLength: ").append(-1).toString());
            iOException.initCause(e);
            throw iOException;
        }
    }
}
