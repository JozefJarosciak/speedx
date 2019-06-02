package com.google.common.hash;

import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.nio.ByteBuffer;

final class SipHashFunction extends AbstractStreamingHashFunction implements Serializable {
    private static final long serialVersionUID = 0;
    /* renamed from: c */
    private final int f14272c;
    /* renamed from: d */
    private final int f14273d;
    private final long k0;
    private final long k1;

    private static final class SipHasher extends AbstractStreamingHasher {
        private static final int CHUNK_SIZE = 8;
        /* renamed from: b */
        private long f14269b = 0;
        /* renamed from: c */
        private final int f14270c;
        /* renamed from: d */
        private final int f14271d;
        private long finalM = 0;
        private long v0 = 8317987319222330741L;
        private long v1 = 7237128888997146477L;
        private long v2 = 7816392313619706465L;
        private long v3 = 8387220255154660723L;

        SipHasher(int i, int i2, long j, long j2) {
            super(8);
            this.f14270c = i;
            this.f14271d = i2;
            this.v0 ^= j;
            this.v1 ^= j2;
            this.v2 ^= j;
            this.v3 ^= j2;
        }

        protected void process(ByteBuffer byteBuffer) {
            this.f14269b += 8;
            processM(byteBuffer.getLong());
        }

        protected void processRemaining(ByteBuffer byteBuffer) {
            this.f14269b += (long) byteBuffer.remaining();
            long j = null;
            while (byteBuffer.hasRemaining()) {
                this.finalM ^= (((long) byteBuffer.get()) & 255) << j;
                j += 8;
            }
        }

        public HashCode makeHash() {
            this.finalM ^= this.f14269b << 56;
            processM(this.finalM);
            this.v2 ^= 255;
            sipRound(this.f14271d);
            return HashCode.fromLong(((this.v0 ^ this.v1) ^ this.v2) ^ this.v3);
        }

        private void processM(long j) {
            this.v3 ^= j;
            sipRound(this.f14270c);
            this.v0 ^= j;
        }

        private void sipRound(int i) {
            for (int i2 = 0; i2 < i; i2++) {
                this.v0 += this.v1;
                this.v2 += this.v3;
                this.v1 = Long.rotateLeft(this.v1, 13);
                this.v3 = Long.rotateLeft(this.v3, 16);
                this.v1 ^= this.v0;
                this.v3 ^= this.v2;
                this.v0 = Long.rotateLeft(this.v0, 32);
                this.v2 += this.v1;
                this.v0 += this.v3;
                this.v1 = Long.rotateLeft(this.v1, 17);
                this.v3 = Long.rotateLeft(this.v3, 21);
                this.v1 ^= this.v2;
                this.v3 ^= this.v0;
                this.v2 = Long.rotateLeft(this.v2, 32);
            }
        }
    }

    SipHashFunction(int i, int i2, long j, long j2) {
        boolean z;
        Preconditions.checkArgument(i > 0, "The number of SipRound iterations (c=%s) during Compression must be positive.", Integer.valueOf(i));
        if (i2 > 0) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "The number of SipRound iterations (d=%s) during Finalization must be positive.", Integer.valueOf(i2));
        this.f14272c = i;
        this.f14273d = i2;
        this.k0 = j;
        this.k1 = j2;
    }

    public int bits() {
        return 64;
    }

    public Hasher newHasher() {
        return new SipHasher(this.f14272c, this.f14273d, this.k0, this.k1);
    }

    public String toString() {
        int i = this.f14272c;
        int i2 = this.f14273d;
        long j = this.k0;
        return "Hashing.sipHash" + i + i2 + "(" + j + ", " + this.k1 + ")";
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SipHashFunction)) {
            return false;
        }
        SipHashFunction sipHashFunction = (SipHashFunction) obj;
        if (this.f14272c == sipHashFunction.f14272c && this.f14273d == sipHashFunction.f14273d && this.k0 == sipHashFunction.k0 && this.k1 == sipHashFunction.k1) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (int) ((((long) ((getClass().hashCode() ^ this.f14272c) ^ this.f14273d)) ^ this.k0) ^ this.k1);
    }
}
