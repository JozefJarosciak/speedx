package com.google.common.hash;

import com.google.common.annotations.Beta;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import java.util.Iterator;
import java.util.zip.Adler32;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

@Beta
public final class Hashing {
    private static final int GOOD_FAST_HASH_SEED = ((int) System.currentTimeMillis());

    private static class Adler32Holder {
        static final HashFunction ADLER_32 = Hashing.checksumHashFunction(ChecksumType.ADLER_32, "Hashing.adler32()");

        private Adler32Holder() {
        }
    }

    enum ChecksumType implements Supplier<Checksum> {
        CRC_32(32) {
            public Checksum get() {
                return new CRC32();
            }
        },
        ADLER_32(32) {
            public Checksum get() {
                return new Adler32();
            }
        };
        
        private final int bits;

        public abstract Checksum get();

        private ChecksumType(int i) {
            this.bits = i;
        }
    }

    @VisibleForTesting
    static final class ConcatenatedHashFunction extends AbstractCompositeHashFunction {
        private final int bits;

        ConcatenatedHashFunction(HashFunction... hashFunctionArr) {
            int i = 0;
            super(hashFunctionArr);
            int i2 = 0;
            while (i < hashFunctionArr.length) {
                i2 += hashFunctionArr[i].bits();
                i++;
            }
            this.bits = i2;
        }

        HashCode makeHash(Hasher[] hasherArr) {
            int i = 0;
            byte[] bArr = new byte[(this.bits / 8)];
            int length = hasherArr.length;
            int i2 = 0;
            while (i < length) {
                HashCode hash = hasherArr[i].hash();
                i2 += hash.writeBytesTo(bArr, i2, hash.bits() / 8);
                i++;
            }
            return HashCode.fromBytesNoCopy(bArr);
        }

        public int bits() {
            return this.bits;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ConcatenatedHashFunction)) {
                return false;
            }
            ConcatenatedHashFunction concatenatedHashFunction = (ConcatenatedHashFunction) obj;
            if (this.bits != concatenatedHashFunction.bits || this.functions.length != concatenatedHashFunction.functions.length) {
                return false;
            }
            for (int i = 0; i < this.functions.length; i++) {
                if (!this.functions[i].equals(concatenatedHashFunction.functions[i])) {
                    return false;
                }
            }
            return true;
        }

        public int hashCode() {
            int i = this.bits;
            for (Object hashCode : this.functions) {
                i ^= hashCode.hashCode();
            }
            return i;
        }
    }

    private static class Crc32Holder {
        static final HashFunction CRC_32 = Hashing.checksumHashFunction(ChecksumType.CRC_32, "Hashing.crc32()");

        private Crc32Holder() {
        }
    }

    private static final class Crc32cHolder {
        static final HashFunction CRC_32_C = new Crc32cHashFunction();

        private Crc32cHolder() {
        }
    }

    private static final class LinearCongruentialGenerator {
        private long state;

        public LinearCongruentialGenerator(long j) {
            this.state = j;
        }

        public double nextDouble() {
            this.state = (2862933555777941757L * this.state) + 1;
            return ((double) (((int) (this.state >>> 33)) + 1)) / 2.147483648E9d;
        }
    }

    private static class Md5Holder {
        static final HashFunction MD5 = new MessageDigestHashFunction("MD5", "Hashing.md5()");

        private Md5Holder() {
        }
    }

    private static class Murmur3_128Holder {
        static final HashFunction GOOD_FAST_HASH_FUNCTION_128 = Hashing.murmur3_128(Hashing.GOOD_FAST_HASH_SEED);
        static final HashFunction MURMUR3_128 = new Murmur3_128HashFunction(0);

        private Murmur3_128Holder() {
        }
    }

    private static class Murmur3_32Holder {
        static final HashFunction GOOD_FAST_HASH_FUNCTION_32 = Hashing.murmur3_32(Hashing.GOOD_FAST_HASH_SEED);
        static final HashFunction MURMUR3_32 = new Murmur3_32HashFunction(0);

        private Murmur3_32Holder() {
        }
    }

    private static class Sha1Holder {
        static final HashFunction SHA_1 = new MessageDigestHashFunction("SHA-1", "Hashing.sha1()");

        private Sha1Holder() {
        }
    }

    private static class Sha256Holder {
        static final HashFunction SHA_256 = new MessageDigestHashFunction("SHA-256", "Hashing.sha256()");

        private Sha256Holder() {
        }
    }

    private static class Sha512Holder {
        static final HashFunction SHA_512 = new MessageDigestHashFunction("SHA-512", "Hashing.sha512()");

        private Sha512Holder() {
        }
    }

    private static class SipHash24Holder {
        static final HashFunction SIP_HASH_24 = new SipHashFunction(2, 4, 506097522914230528L, 1084818905618843912L);

        private SipHash24Holder() {
        }
    }

    public static HashFunction goodFastHash(int i) {
        int checkPositiveAndMakeMultipleOf32 = checkPositiveAndMakeMultipleOf32(i);
        if (checkPositiveAndMakeMultipleOf32 == 32) {
            return Murmur3_32Holder.GOOD_FAST_HASH_FUNCTION_32;
        }
        if (checkPositiveAndMakeMultipleOf32 <= 128) {
            return Murmur3_128Holder.GOOD_FAST_HASH_FUNCTION_128;
        }
        int i2 = (checkPositiveAndMakeMultipleOf32 + 127) / 128;
        HashFunction[] hashFunctionArr = new HashFunction[i2];
        hashFunctionArr[0] = Murmur3_128Holder.GOOD_FAST_HASH_FUNCTION_128;
        int i3 = GOOD_FAST_HASH_SEED;
        for (checkPositiveAndMakeMultipleOf32 = 1; checkPositiveAndMakeMultipleOf32 < i2; checkPositiveAndMakeMultipleOf32++) {
            i3 += 1500450271;
            hashFunctionArr[checkPositiveAndMakeMultipleOf32] = murmur3_128(i3);
        }
        return new ConcatenatedHashFunction(hashFunctionArr);
    }

    public static HashFunction murmur3_32(int i) {
        return new Murmur3_32HashFunction(i);
    }

    public static HashFunction murmur3_32() {
        return Murmur3_32Holder.MURMUR3_32;
    }

    public static HashFunction murmur3_128(int i) {
        return new Murmur3_128HashFunction(i);
    }

    public static HashFunction murmur3_128() {
        return Murmur3_128Holder.MURMUR3_128;
    }

    public static HashFunction sipHash24() {
        return SipHash24Holder.SIP_HASH_24;
    }

    public static HashFunction sipHash24(long j, long j2) {
        return new SipHashFunction(2, 4, j, j2);
    }

    public static HashFunction md5() {
        return Md5Holder.MD5;
    }

    public static HashFunction sha1() {
        return Sha1Holder.SHA_1;
    }

    public static HashFunction sha256() {
        return Sha256Holder.SHA_256;
    }

    public static HashFunction sha512() {
        return Sha512Holder.SHA_512;
    }

    public static HashFunction crc32c() {
        return Crc32cHolder.CRC_32_C;
    }

    public static HashFunction crc32() {
        return Crc32Holder.CRC_32;
    }

    public static HashFunction adler32() {
        return Adler32Holder.ADLER_32;
    }

    private static HashFunction checksumHashFunction(ChecksumType checksumType, String str) {
        return new ChecksumHashFunction(checksumType, checksumType.bits, str);
    }

    public static int consistentHash(HashCode hashCode, int i) {
        return consistentHash(hashCode.padToLong(), i);
    }

    public static int consistentHash(long j, int i) {
        int i2 = 0;
        Preconditions.checkArgument(i > 0, "buckets must be positive: %s", Integer.valueOf(i));
        LinearCongruentialGenerator linearCongruentialGenerator = new LinearCongruentialGenerator(j);
        while (true) {
            int nextDouble = (int) (((double) (i2 + 1)) / linearCongruentialGenerator.nextDouble());
            if (nextDouble < 0 || nextDouble >= i) {
                return i2;
            }
            i2 = nextDouble;
        }
        return i2;
    }

    public static HashCode combineOrdered(Iterable<HashCode> iterable) {
        Iterator it = iterable.iterator();
        Preconditions.checkArgument(it.hasNext(), "Must be at least 1 hash code to combine.");
        byte[] bArr = new byte[(((HashCode) it.next()).bits() / 8)];
        for (HashCode asBytes : iterable) {
            boolean z;
            byte[] asBytes2 = asBytes.asBytes();
            if (asBytes2.length == bArr.length) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkArgument(z, "All hashcodes must have the same bit length.");
            for (int i = 0; i < asBytes2.length; i++) {
                bArr[i] = (byte) ((bArr[i] * 37) ^ asBytes2[i]);
            }
        }
        return HashCode.fromBytesNoCopy(bArr);
    }

    public static HashCode combineUnordered(Iterable<HashCode> iterable) {
        Iterator it = iterable.iterator();
        Preconditions.checkArgument(it.hasNext(), "Must be at least 1 hash code to combine.");
        byte[] bArr = new byte[(((HashCode) it.next()).bits() / 8)];
        for (HashCode asBytes : iterable) {
            boolean z;
            byte[] asBytes2 = asBytes.asBytes();
            if (asBytes2.length == bArr.length) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkArgument(z, "All hashcodes must have the same bit length.");
            for (int i = 0; i < asBytes2.length; i++) {
                bArr[i] = (byte) (bArr[i] + asBytes2[i]);
            }
        }
        return HashCode.fromBytesNoCopy(bArr);
    }

    static int checkPositiveAndMakeMultipleOf32(int i) {
        Preconditions.checkArgument(i > 0, "Number of bits must be positive");
        return (i + 31) & -32;
    }

    private Hashing() {
    }
}
