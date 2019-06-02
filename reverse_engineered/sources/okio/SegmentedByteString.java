package okio;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;

final class SegmentedByteString extends ByteString {
    /* renamed from: e */
    final transient byte[][] f18198e;
    /* renamed from: f */
    final transient int[] f18199f;

    SegmentedByteString(C5637c c5637c, int i) {
        int i2 = 0;
        super(null);
        C5654u.m20769a(c5637c.f18210b, 0, (long) i);
        C5651p c5651p = c5637c.f18209a;
        int i3 = 0;
        int i4 = 0;
        while (i4 < i) {
            if (c5651p.f18245c == c5651p.f18244b) {
                throw new AssertionError("s.limit == s.pos");
            }
            i4 += c5651p.f18245c - c5651p.f18244b;
            i3++;
            c5651p = c5651p.f18248f;
        }
        this.f18198e = new byte[i3][];
        this.f18199f = new int[(i3 * 2)];
        C5651p c5651p2 = c5637c.f18209a;
        i4 = 0;
        while (i2 < i) {
            this.f18198e[i4] = c5651p2.f18243a;
            int i5 = (c5651p2.f18245c - c5651p2.f18244b) + i2;
            if (i5 > i) {
                i5 = i;
            }
            this.f18199f[i4] = i5;
            this.f18199f[this.f18198e.length + i4] = c5651p2.f18244b;
            c5651p2.f18246d = true;
            i4++;
            c5651p2 = c5651p2.f18248f;
            i2 = i5;
        }
    }

    public String utf8() {
        return toByteString().utf8();
    }

    public String base64() {
        return toByteString().base64();
    }

    public String hex() {
        return toByteString().hex();
    }

    public ByteString toAsciiLowercase() {
        return toByteString().toAsciiLowercase();
    }

    public ByteString toAsciiUppercase() {
        return toByteString().toAsciiUppercase();
    }

    public ByteString md5() {
        return toByteString().md5();
    }

    public ByteString sha256() {
        return toByteString().sha256();
    }

    public String base64Url() {
        return toByteString().base64Url();
    }

    public ByteString substring(int i) {
        return toByteString().substring(i);
    }

    public ByteString substring(int i, int i2) {
        return toByteString().substring(i, i2);
    }

    public byte getByte(int i) {
        C5654u.m20769a((long) this.f18199f[this.f18198e.length - 1], (long) i, 1);
        int segment = segment(i);
        return this.f18198e[segment][(i - (segment == 0 ? 0 : this.f18199f[segment - 1])) + this.f18199f[this.f18198e.length + segment]];
    }

    private int segment(int i) {
        int binarySearch = Arrays.binarySearch(this.f18199f, 0, this.f18198e.length, i + 1);
        return binarySearch >= 0 ? binarySearch : binarySearch ^ -1;
    }

    public int size() {
        return this.f18199f[this.f18198e.length - 1];
    }

    public byte[] toByteArray() {
        int i = 0;
        Object obj = new byte[this.f18199f[this.f18198e.length - 1]];
        int length = this.f18198e.length;
        int i2 = 0;
        while (i < length) {
            int i3 = this.f18199f[length + i];
            int i4 = this.f18199f[i];
            System.arraycopy(this.f18198e[i], i3, obj, i2, i4 - i2);
            i++;
            i2 = i4;
        }
        return obj;
    }

    public ByteBuffer asByteBuffer() {
        return ByteBuffer.wrap(toByteArray()).asReadOnlyBuffer();
    }

    public void write(OutputStream outputStream) throws IOException {
        int i = 0;
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        }
        int length = this.f18198e.length;
        int i2 = 0;
        while (i < length) {
            int i3 = this.f18199f[length + i];
            int i4 = this.f18199f[i];
            outputStream.write(this.f18198e[i], i3, i4 - i2);
            i++;
            i2 = i4;
        }
    }

    /* renamed from: a */
    void mo6782a(C5637c c5637c) {
        int i = 0;
        int length = this.f18198e.length;
        int i2 = 0;
        while (i < length) {
            int i3 = this.f18199f[length + i];
            int i4 = this.f18199f[i];
            C5651p c5651p = new C5651p(this.f18198e[i], i3, (i3 + i4) - i2);
            if (c5637c.f18209a == null) {
                c5651p.f18249g = c5651p;
                c5651p.f18248f = c5651p;
                c5637c.f18209a = c5651p;
            } else {
                c5637c.f18209a.f18249g.m20759a(c5651p);
            }
            i++;
            i2 = i4;
        }
        c5637c.f18210b = ((long) i2) + c5637c.f18210b;
    }

    public boolean rangeEquals(int i, ByteString byteString, int i2, int i3) {
        if (i < 0 || i > size() - i3) {
            return false;
        }
        int segment = segment(i);
        while (i3 > 0) {
            int i4 = segment == 0 ? 0 : this.f18199f[segment - 1];
            int min = Math.min(i3, ((this.f18199f[segment] - i4) + i4) - i);
            if (!byteString.rangeEquals(i2, this.f18198e[segment], (i - i4) + this.f18199f[this.f18198e.length + segment], min)) {
                return false;
            }
            i += min;
            i2 += min;
            i3 -= min;
            segment++;
        }
        return true;
    }

    public boolean rangeEquals(int i, byte[] bArr, int i2, int i3) {
        if (i < 0 || i > size() - i3 || i2 < 0 || i2 > bArr.length - i3) {
            return false;
        }
        int segment = segment(i);
        while (i3 > 0) {
            int i4 = segment == 0 ? 0 : this.f18199f[segment - 1];
            int min = Math.min(i3, ((this.f18199f[segment] - i4) + i4) - i);
            if (!C5654u.m20771a(this.f18198e[segment], (i - i4) + this.f18199f[this.f18198e.length + segment], bArr, i2, min)) {
                return false;
            }
            i += min;
            i2 += min;
            i3 -= min;
            segment++;
        }
        return true;
    }

    public int indexOf(byte[] bArr, int i) {
        return toByteString().indexOf(bArr, i);
    }

    public int lastIndexOf(byte[] bArr, int i) {
        return toByteString().lastIndexOf(bArr, i);
    }

    private ByteString toByteString() {
        return new ByteString(toByteArray());
    }

    /* renamed from: a */
    byte[] mo6783a() {
        return toByteArray();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        boolean z = (obj instanceof ByteString) && ((ByteString) obj).size() == size() && rangeEquals(0, (ByteString) obj, 0, size());
        return z;
    }

    public int hashCode() {
        int i = this.c;
        if (i == 0) {
            i = 1;
            int length = this.f18198e.length;
            int i2 = 0;
            int i3 = 0;
            while (i2 < length) {
                byte[] bArr = this.f18198e[i2];
                int i4 = this.f18199f[length + i2];
                int i5 = this.f18199f[i2];
                i3 = (i5 - i3) + i4;
                int i6 = i4;
                i4 = i;
                for (i = i6; i < i3; i++) {
                    i4 = (i4 * 31) + bArr[i];
                }
                i2++;
                i3 = i5;
                i = i4;
            }
            this.c = i;
        }
        return i;
    }

    public String toString() {
        return toByteString().toString();
    }

    private Object writeReplace() {
        return toByteString();
    }
}
