package okio;

import android.support.v4.media.session.PlaybackStateCompat;
import ch.qos.logback.core.spi.AbstractComponentTracker;
import com.alibaba.fastjson.asm.Opcodes;
import com.alibaba.fastjson.util.UTF8Decoder.Surrogate;
import com.facebook.stetho.dumpapp.Framer;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

/* compiled from: Buffer */
/* renamed from: okio.c */
public final class C5637c implements Cloneable, C5635d, C5636e {
    /* renamed from: c */
    private static final byte[] f18208c = new byte[]{(byte) 48, Framer.STDOUT_FRAME_PREFIX, Framer.STDERR_FRAME_PREFIX, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102};
    /* renamed from: a */
    C5651p f18209a;
    /* renamed from: b */
    long f18210b;

    /* compiled from: Buffer */
    /* renamed from: okio.c$1 */
    class C56331 extends OutputStream {
        /* renamed from: a */
        final /* synthetic */ C5637c f18206a;

        C56331(C5637c c5637c) {
            this.f18206a = c5637c;
        }

        public void write(int i) {
            this.f18206a.m20641b((byte) i);
        }

        public void write(byte[] bArr, int i, int i2) {
            this.f18206a.m20643b(bArr, i, i2);
        }

        public void flush() {
        }

        public void close() {
        }

        public String toString() {
            return this + ".outputStream()";
        }
    }

    /* compiled from: Buffer */
    /* renamed from: okio.c$2 */
    class C56342 extends InputStream {
        /* renamed from: a */
        final /* synthetic */ C5637c f18207a;

        C56342(C5637c c5637c) {
            this.f18207a = c5637c;
        }

        public int read() {
            if (this.f18207a.f18210b > 0) {
                return this.f18207a.mo6823i() & 255;
            }
            return -1;
        }

        public int read(byte[] bArr, int i, int i2) {
            return this.f18207a.m20622a(bArr, i, i2);
        }

        public int available() {
            return (int) Math.min(this.f18207a.f18210b, 2147483647L);
        }

        public void close() {
        }

        public String toString() {
            return this.f18207a + ".inputStream()";
        }
    }

    /* renamed from: b */
    public /* synthetic */ C5635d mo6811b(String str) throws IOException {
        return m20631a(str);
    }

    /* renamed from: b */
    public /* synthetic */ C5635d mo6812b(ByteString byteString) throws IOException {
        return m20635a(byteString);
    }

    /* renamed from: c */
    public /* synthetic */ C5635d mo6814c(byte[] bArr) throws IOException {
        return m20642b(bArr);
    }

    /* renamed from: c */
    public /* synthetic */ C5635d mo6815c(byte[] bArr, int i, int i2) throws IOException {
        return m20643b(bArr, i, i2);
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return m20686u();
    }

    /* renamed from: h */
    public /* synthetic */ C5635d mo6821h(int i) throws IOException {
        return m20656e(i);
    }

    /* renamed from: i */
    public /* synthetic */ C5635d mo6824i(int i) throws IOException {
        return m20654d(i);
    }

    /* renamed from: j */
    public /* synthetic */ C5635d mo6825j(int i) throws IOException {
        return m20649c(i);
    }

    /* renamed from: k */
    public /* synthetic */ C5635d mo6828k(int i) throws IOException {
        return m20641b(i);
    }

    /* renamed from: k */
    public /* synthetic */ C5635d mo6829k(long j) throws IOException {
        return m20670j(j);
    }

    /* renamed from: l */
    public /* synthetic */ C5635d mo6830l(long j) throws IOException {
        return m20668i(j);
    }

    /* renamed from: w */
    public /* synthetic */ C5635d mo6836w() throws IOException {
        return m20653d();
    }

    /* renamed from: a */
    public long m20623a() {
        return this.f18210b;
    }

    /* renamed from: b */
    public C5637c mo6810b() {
        return this;
    }

    /* renamed from: c */
    public OutputStream m20648c() {
        return new C56331(this);
    }

    /* renamed from: d */
    public C5637c m20653d() {
        return this;
    }

    /* renamed from: e */
    public C5635d mo6817e() {
        return this;
    }

    /* renamed from: f */
    public boolean mo6818f() {
        return this.f18210b == 0;
    }

    /* renamed from: a */
    public void mo6809a(long j) throws EOFException {
        if (this.f18210b < j) {
            throw new EOFException();
        }
    }

    /* renamed from: b */
    public boolean mo6813b(long j) {
        return this.f18210b >= j;
    }

    /* renamed from: g */
    public InputStream mo6819g() {
        return new C56342(this);
    }

    /* renamed from: a */
    public C5637c m20636a(C5637c c5637c, long j, long j2) {
        if (c5637c == null) {
            throw new IllegalArgumentException("out == null");
        }
        C5654u.m20769a(this.f18210b, j, j2);
        if (j2 != 0) {
            c5637c.f18210b += j2;
            C5651p c5651p = this.f18209a;
            while (j >= ((long) (c5651p.f18245c - c5651p.f18244b))) {
                j -= (long) (c5651p.f18245c - c5651p.f18244b);
                c5651p = c5651p.f18248f;
            }
            while (j2 > 0) {
                C5651p c5651p2 = new C5651p(c5651p);
                c5651p2.f18244b = (int) (((long) c5651p2.f18244b) + j);
                c5651p2.f18245c = Math.min(c5651p2.f18244b + ((int) j2), c5651p2.f18245c);
                if (c5637c.f18209a == null) {
                    c5651p2.f18249g = c5651p2;
                    c5651p2.f18248f = c5651p2;
                    c5637c.f18209a = c5651p2;
                } else {
                    c5637c.f18209a.f18249g.m20759a(c5651p2);
                }
                j2 -= (long) (c5651p2.f18245c - c5651p2.f18244b);
                c5651p = c5651p.f18248f;
                j = 0;
            }
        }
        return this;
    }

    /* renamed from: h */
    public long m20664h() {
        long j = this.f18210b;
        if (j == 0) {
            return 0;
        }
        C5651p c5651p = this.f18209a.f18249g;
        if (c5651p.f18245c >= 8192 || !c5651p.f18247e) {
            return j;
        }
        return j - ((long) (c5651p.f18245c - c5651p.f18244b));
    }

    /* renamed from: i */
    public byte mo6823i() {
        if (this.f18210b == 0) {
            throw new IllegalStateException("size == 0");
        }
        C5651p c5651p = this.f18209a;
        int i = c5651p.f18244b;
        int i2 = c5651p.f18245c;
        int i3 = i + 1;
        byte b = c5651p.f18243a[i];
        this.f18210b--;
        if (i3 == i2) {
            this.f18209a = c5651p.m20757a();
            C5652q.m20763a(c5651p);
        } else {
            c5651p.f18244b = i3;
        }
        return b;
    }

    /* renamed from: c */
    public byte m20647c(long j) {
        C5654u.m20769a(this.f18210b, j, 1);
        C5651p c5651p = this.f18209a;
        while (true) {
            int i = c5651p.f18245c - c5651p.f18244b;
            if (j < ((long) i)) {
                return c5651p.f18243a[c5651p.f18244b + ((int) j)];
            }
            j -= (long) i;
            c5651p = c5651p.f18248f;
        }
    }

    /* renamed from: j */
    public short mo6826j() {
        if (this.f18210b < 2) {
            throw new IllegalStateException("size < 2: " + this.f18210b);
        }
        C5651p c5651p = this.f18209a;
        int i = c5651p.f18244b;
        int i2 = c5651p.f18245c;
        if (i2 - i < 2) {
            return (short) (((mo6823i() & 255) << 8) | (mo6823i() & 255));
        }
        byte[] bArr = c5651p.f18243a;
        int i3 = i + 1;
        int i4 = i3 + 1;
        i = ((bArr[i] & 255) << 8) | (bArr[i3] & 255);
        this.f18210b -= 2;
        if (i4 == i2) {
            this.f18209a = c5651p.m20757a();
            C5652q.m20763a(c5651p);
        } else {
            c5651p.f18244b = i4;
        }
        return (short) i;
    }

    /* renamed from: k */
    public int mo6827k() {
        if (this.f18210b < 4) {
            throw new IllegalStateException("size < 4: " + this.f18210b);
        }
        C5651p c5651p = this.f18209a;
        int i = c5651p.f18244b;
        int i2 = c5651p.f18245c;
        if (i2 - i < 4) {
            return ((((mo6823i() & 255) << 24) | ((mo6823i() & 255) << 16)) | ((mo6823i() & 255) << 8)) | (mo6823i() & 255);
        }
        byte[] bArr = c5651p.f18243a;
        int i3 = i + 1;
        int i4 = i3 + 1;
        i = ((bArr[i] & 255) << 24) | ((bArr[i3] & 255) << 16);
        i3 = i4 + 1;
        i |= (bArr[i4] & 255) << 8;
        i4 = i3 + 1;
        i |= bArr[i3] & 255;
        this.f18210b -= 4;
        if (i4 == i2) {
            this.f18209a = c5651p.m20757a();
            C5652q.m20763a(c5651p);
            return i;
        }
        c5651p.f18244b = i4;
        return i;
    }

    /* renamed from: l */
    public short mo6831l() {
        return C5654u.m20768a(mo6826j());
    }

    /* renamed from: m */
    public int mo6832m() {
        return C5654u.m20767a(mo6827k());
    }

    /* renamed from: n */
    public long mo6833n() {
        if (this.f18210b == 0) {
            throw new IllegalStateException("size == 0");
        }
        long j = 0;
        int i = 0;
        Object obj = null;
        do {
            C5651p c5651p = this.f18209a;
            byte[] bArr = c5651p.f18243a;
            int i2 = c5651p.f18244b;
            int i3 = c5651p.f18245c;
            int i4 = i2;
            while (i4 < i3) {
                byte b = bArr[i4];
                if (b >= (byte) 48 && b <= (byte) 57) {
                    i2 = b - 48;
                } else if (b >= (byte) 97 && b <= (byte) 102) {
                    i2 = (b - 97) + 10;
                } else if (b < (byte) 65 || b > (byte) 70) {
                    if (i != 0) {
                        obj = 1;
                        if (i4 != i3) {
                            this.f18209a = c5651p.m20757a();
                            C5652q.m20763a(c5651p);
                        } else {
                            c5651p.f18244b = i4;
                        }
                        if (obj == null) {
                            break;
                        }
                    } else {
                        throw new NumberFormatException("Expected leading [0-9a-fA-F] character but was 0x" + Integer.toHexString(b));
                    }
                } else {
                    i2 = (b - 65) + 10;
                }
                if ((-1152921504606846976L & j) != 0) {
                    throw new NumberFormatException("Number too large: " + new C5637c().m20670j(j).m20641b((int) b).m20681p());
                }
                i++;
                i4++;
                j = ((long) i2) | (j << 4);
            }
            if (i4 != i3) {
                c5651p.f18244b = i4;
            } else {
                this.f18209a = c5651p.m20757a();
                C5652q.m20763a(c5651p);
            }
            if (obj == null) {
                break;
            }
        } while (this.f18209a != null);
        this.f18210b -= (long) i;
        return j;
    }

    /* renamed from: o */
    public ByteString m20680o() {
        return new ByteString(mo6835s());
    }

    /* renamed from: d */
    public ByteString mo6816d(long j) throws EOFException {
        return new ByteString(mo6820g(j));
    }

    /* renamed from: a */
    public long mo6807a(C5492r c5492r) throws IOException {
        long j = this.f18210b;
        if (j > 0) {
            c5492r.mo6706a(this, j);
        }
        return j;
    }

    /* renamed from: p */
    public String m20681p() {
        try {
            return m20628a(this.f18210b, C5654u.f18252a);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    /* renamed from: e */
    public String m20655e(long j) throws EOFException {
        return m20628a(j, C5654u.f18252a);
    }

    /* renamed from: a */
    public String m20629a(Charset charset) {
        try {
            return m20628a(this.f18210b, charset);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    /* renamed from: a */
    public String m20628a(long j, Charset charset) throws EOFException {
        C5654u.m20769a(this.f18210b, 0, j);
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        } else if (j > 2147483647L) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j);
        } else if (j == 0) {
            return "";
        } else {
            C5651p c5651p = this.f18209a;
            if (((long) c5651p.f18244b) + j > ((long) c5651p.f18245c)) {
                return new String(mo6820g(j), charset);
            }
            String str = new String(c5651p.f18243a, c5651p.f18244b, (int) j, charset);
            c5651p.f18244b = (int) (((long) c5651p.f18244b) + j);
            this.f18210b -= j;
            if (c5651p.f18244b != c5651p.f18245c) {
                return str;
            }
            this.f18209a = c5651p.m20757a();
            C5652q.m20763a(c5651p);
            return str;
        }
    }

    /* renamed from: q */
    public String mo6834q() throws EOFException {
        long a = mo6806a((byte) 10);
        if (a != -1) {
            return m20658f(a);
        }
        C5637c c5637c = new C5637c();
        m20636a(c5637c, 0, Math.min(32, this.f18210b));
        throw new EOFException("\\n not found: size=" + m20623a() + " content=" + c5637c.m20680o().hex() + "…");
    }

    /* renamed from: f */
    String m20658f(long j) throws EOFException {
        if (j <= 0 || m20647c(j - 1) != (byte) 13) {
            String e = m20655e(j);
            mo6822h(1);
            return e;
        }
        e = m20655e(j - 1);
        mo6822h(2);
        return e;
    }

    /* renamed from: r */
    public int m20683r() throws EOFException {
        if (this.f18210b == 0) {
            throw new EOFException();
        }
        int i;
        int i2;
        int i3;
        byte c = m20647c(0);
        if ((c & 128) == 0) {
            i = 0;
            i2 = c & 127;
            i3 = 1;
        } else if ((c & 224) == Opcodes.CHECKCAST) {
            i2 = c & 31;
            i3 = 2;
            i = 128;
        } else if ((c & 240) == 224) {
            i2 = c & 15;
            i3 = 3;
            i = 2048;
        } else if ((c & 248) == 240) {
            i2 = c & 7;
            i3 = 4;
            i = 65536;
        } else {
            mo6822h(1);
            return 65533;
        }
        if (this.f18210b < ((long) i3)) {
            throw new EOFException("size < " + i3 + ": " + this.f18210b + " (to read code point prefixed 0x" + Integer.toHexString(c) + ")");
        }
        int i4 = i2;
        i2 = 1;
        while (i2 < i3) {
            c = m20647c((long) i2);
            if ((c & Opcodes.CHECKCAST) == 128) {
                i2++;
                i4 = (c & 63) | (i4 << 6);
            } else {
                mo6822h((long) i2);
                return 65533;
            }
        }
        mo6822h((long) i3);
        if (i4 > Surrogate.UCS4_MAX) {
            return 65533;
        }
        if (i4 >= 55296 && i4 <= 57343) {
            return 65533;
        }
        if (i4 < i) {
            return 65533;
        }
        return i4;
    }

    /* renamed from: s */
    public byte[] mo6835s() {
        try {
            return mo6820g(this.f18210b);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    /* renamed from: g */
    public byte[] mo6820g(long j) throws EOFException {
        C5654u.m20769a(this.f18210b, 0, j);
        if (j > 2147483647L) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j);
        }
        byte[] bArr = new byte[((int) j)];
        m20639a(bArr);
        return bArr;
    }

    /* renamed from: a */
    public void m20639a(byte[] bArr) throws EOFException {
        int i = 0;
        while (i < bArr.length) {
            int a = m20622a(bArr, i, bArr.length - i);
            if (a == -1) {
                throw new EOFException();
            }
            i += a;
        }
    }

    /* renamed from: a */
    public int m20622a(byte[] bArr, int i, int i2) {
        C5654u.m20769a((long) bArr.length, (long) i, (long) i2);
        C5651p c5651p = this.f18209a;
        if (c5651p == null) {
            return -1;
        }
        int min = Math.min(i2, c5651p.f18245c - c5651p.f18244b);
        System.arraycopy(c5651p.f18243a, c5651p.f18244b, bArr, i, min);
        c5651p.f18244b += min;
        this.f18210b -= (long) min;
        if (c5651p.f18244b != c5651p.f18245c) {
            return min;
        }
        this.f18209a = c5651p.m20757a();
        C5652q.m20763a(c5651p);
        return min;
    }

    /* renamed from: t */
    public void m20685t() {
        try {
            mo6822h(this.f18210b);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    /* renamed from: h */
    public void mo6822h(long j) throws EOFException {
        while (j > 0) {
            if (this.f18209a == null) {
                throw new EOFException();
            }
            int min = (int) Math.min(j, (long) (this.f18209a.f18245c - this.f18209a.f18244b));
            this.f18210b -= (long) min;
            j -= (long) min;
            C5651p c5651p = this.f18209a;
            c5651p.f18244b = min + c5651p.f18244b;
            if (this.f18209a.f18244b == this.f18209a.f18245c) {
                C5651p c5651p2 = this.f18209a;
                this.f18209a = c5651p2.m20757a();
                C5652q.m20763a(c5651p2);
            }
        }
    }

    /* renamed from: a */
    public C5637c m20635a(ByteString byteString) {
        if (byteString == null) {
            throw new IllegalArgumentException("byteString == null");
        }
        byteString.mo6782a(this);
        return this;
    }

    /* renamed from: a */
    public C5637c m20631a(String str) {
        return m20632a(str, 0, str.length());
    }

    /* renamed from: a */
    public C5637c m20632a(String str, int i, int i2) {
        if (str == null) {
            throw new IllegalArgumentException("string == null");
        } else if (i < 0) {
            throw new IllegalAccessError("beginIndex < 0: " + i);
        } else if (i2 < i) {
            throw new IllegalArgumentException("endIndex < beginIndex: " + i2 + " < " + i);
        } else if (i2 > str.length()) {
            throw new IllegalArgumentException("endIndex > string.length: " + i2 + " > " + str.length());
        } else {
            while (i < i2) {
                int i3;
                char charAt = str.charAt(i);
                if (charAt < '') {
                    int i4;
                    C5651p f = m20659f(1);
                    byte[] bArr = f.f18243a;
                    int i5 = f.f18245c - i;
                    int min = Math.min(i2, 8192 - i5);
                    i3 = i + 1;
                    bArr[i5 + i] = (byte) charAt;
                    while (i3 < min) {
                        char charAt2 = str.charAt(i3);
                        if (charAt2 >= '') {
                            break;
                        }
                        i4 = i3 + 1;
                        bArr[i3 + i5] = (byte) charAt2;
                        i3 = i4;
                    }
                    i4 = (i3 + i5) - f.f18245c;
                    f.f18245c += i4;
                    this.f18210b += (long) i4;
                } else if (charAt < 'ࠀ') {
                    m20641b((charAt >> 6) | Opcodes.CHECKCAST);
                    m20641b((charAt & 63) | 128);
                    i3 = i + 1;
                } else if (charAt < '?' || charAt > '?') {
                    m20641b((charAt >> 12) | 224);
                    m20641b(((charAt >> 6) & 63) | 128);
                    m20641b((charAt & 63) | 128);
                    i3 = i + 1;
                } else {
                    i3 = i + 1 < i2 ? str.charAt(i + 1) : 0;
                    if (charAt > '?' || i3 < 56320 || i3 > 57343) {
                        m20641b(63);
                        i++;
                    } else {
                        i3 = ((i3 & -56321) | ((charAt & -55297) << 10)) + 65536;
                        m20641b((i3 >> 18) | 240);
                        m20641b(((i3 >> 12) & 63) | 128);
                        m20641b(((i3 >> 6) & 63) | 128);
                        m20641b((i3 & 63) | 128);
                        i3 = i + 2;
                    }
                }
                i = i3;
            }
            return this;
        }
    }

    /* renamed from: a */
    public C5637c m20630a(int i) {
        if (i < 128) {
            m20641b(i);
        } else if (i < 2048) {
            m20641b((i >> 6) | Opcodes.CHECKCAST);
            m20641b((i & 63) | 128);
        } else if (i < 65536) {
            if (i < 55296 || i > 57343) {
                m20641b((i >> 12) | 224);
                m20641b(((i >> 6) & 63) | 128);
                m20641b((i & 63) | 128);
            } else {
                throw new IllegalArgumentException("Unexpected code point: " + Integer.toHexString(i));
            }
        } else if (i <= Surrogate.UCS4_MAX) {
            m20641b((i >> 18) | 240);
            m20641b(((i >> 12) & 63) | 128);
            m20641b(((i >> 6) & 63) | 128);
            m20641b((i & 63) | 128);
        } else {
            throw new IllegalArgumentException("Unexpected code point: " + Integer.toHexString(i));
        }
        return this;
    }

    /* renamed from: a */
    public C5637c m20634a(String str, Charset charset) {
        return m20633a(str, 0, str.length(), charset);
    }

    /* renamed from: a */
    public C5637c m20633a(String str, int i, int i2, Charset charset) {
        if (str == null) {
            throw new IllegalArgumentException("string == null");
        } else if (i < 0) {
            throw new IllegalAccessError("beginIndex < 0: " + i);
        } else if (i2 < i) {
            throw new IllegalArgumentException("endIndex < beginIndex: " + i2 + " < " + i);
        } else if (i2 > str.length()) {
            throw new IllegalArgumentException("endIndex > string.length: " + i2 + " > " + str.length());
        } else if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        } else if (charset.equals(C5654u.f18252a)) {
            return m20631a(str);
        } else {
            byte[] bytes = str.substring(i, i2).getBytes(charset);
            return m20643b(bytes, 0, bytes.length);
        }
    }

    /* renamed from: b */
    public C5637c m20642b(byte[] bArr) {
        if (bArr != null) {
            return m20643b(bArr, 0, bArr.length);
        }
        throw new IllegalArgumentException("source == null");
    }

    /* renamed from: b */
    public C5637c m20643b(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new IllegalArgumentException("source == null");
        }
        C5654u.m20769a((long) bArr.length, (long) i, (long) i2);
        int i3 = i + i2;
        while (i < i3) {
            C5651p f = m20659f(1);
            int min = Math.min(i3 - i, 8192 - f.f18245c);
            System.arraycopy(bArr, i, f.f18243a, f.f18245c, min);
            i += min;
            f.f18245c = min + f.f18245c;
        }
        this.f18210b += (long) i2;
        return this;
    }

    /* renamed from: a */
    public long mo6808a(C5520s c5520s) throws IOException {
        if (c5520s == null) {
            throw new IllegalArgumentException("source == null");
        }
        long j = 0;
        while (true) {
            long read = c5520s.read(this, PlaybackStateCompat.ACTION_PLAY_FROM_URI);
            if (read == -1) {
                return j;
            }
            j += read;
        }
    }

    /* renamed from: b */
    public C5637c m20641b(int i) {
        C5651p f = m20659f(1);
        byte[] bArr = f.f18243a;
        int i2 = f.f18245c;
        f.f18245c = i2 + 1;
        bArr[i2] = (byte) i;
        this.f18210b++;
        return this;
    }

    /* renamed from: c */
    public C5637c m20649c(int i) {
        C5651p f = m20659f(2);
        byte[] bArr = f.f18243a;
        int i2 = f.f18245c;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 8) & 255);
        i2 = i3 + 1;
        bArr[i3] = (byte) (i & 255);
        f.f18245c = i2;
        this.f18210b += 2;
        return this;
    }

    /* renamed from: d */
    public C5637c m20654d(int i) {
        C5651p f = m20659f(4);
        byte[] bArr = f.f18243a;
        int i2 = f.f18245c;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 24) & 255);
        i2 = i3 + 1;
        bArr[i3] = (byte) ((i >>> 16) & 255);
        i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 8) & 255);
        i2 = i3 + 1;
        bArr[i3] = (byte) (i & 255);
        f.f18245c = i2;
        this.f18210b += 4;
        return this;
    }

    /* renamed from: e */
    public C5637c m20656e(int i) {
        return m20654d(C5654u.m20767a(i));
    }

    /* renamed from: i */
    public C5637c m20668i(long j) {
        if (j == 0) {
            return m20641b(48);
        }
        long j2;
        Object obj;
        if (j < 0) {
            j2 = -j;
            if (j2 < 0) {
                return m20631a("-9223372036854775808");
            }
            obj = 1;
        } else {
            obj = null;
            j2 = j;
        }
        int i = j2 < 100000000 ? j2 < AbstractComponentTracker.LINGERING_TIMEOUT ? j2 < 100 ? j2 < 10 ? 1 : 2 : j2 < 1000 ? 3 : 4 : j2 < 1000000 ? j2 < 100000 ? 5 : 6 : j2 < 10000000 ? 7 : 8 : j2 < 1000000000000L ? j2 < 10000000000L ? j2 < 1000000000 ? 9 : 10 : j2 < 100000000000L ? 11 : 12 : j2 < 1000000000000000L ? j2 < 10000000000000L ? 13 : j2 < 100000000000000L ? 14 : 15 : j2 < 100000000000000000L ? j2 < 10000000000000000L ? 16 : 17 : j2 < 1000000000000000000L ? 18 : 19;
        if (obj != null) {
            i++;
        }
        C5651p f = m20659f(i);
        byte[] bArr = f.f18243a;
        int i2 = f.f18245c + i;
        while (j2 != 0) {
            i2--;
            bArr[i2] = f18208c[(int) (j2 % 10)];
            j2 /= 10;
        }
        if (obj != null) {
            bArr[i2 - 1] = Framer.STDIN_FRAME_PREFIX;
        }
        f.f18245c += i;
        this.f18210b = ((long) i) + this.f18210b;
        return this;
    }

    /* renamed from: j */
    public C5637c m20670j(long j) {
        if (j == 0) {
            return m20641b(48);
        }
        int numberOfTrailingZeros = (Long.numberOfTrailingZeros(Long.highestOneBit(j)) / 4) + 1;
        C5651p f = m20659f(numberOfTrailingZeros);
        byte[] bArr = f.f18243a;
        int i = f.f18245c;
        for (int i2 = (f.f18245c + numberOfTrailingZeros) - 1; i2 >= i; i2--) {
            bArr[i2] = f18208c[(int) (15 & j)];
            j >>>= 4;
        }
        f.f18245c += numberOfTrailingZeros;
        this.f18210b = ((long) numberOfTrailingZeros) + this.f18210b;
        return this;
    }

    /* renamed from: f */
    C5651p m20659f(int i) {
        if (i < 1 || i > 8192) {
            throw new IllegalArgumentException();
        } else if (this.f18209a == null) {
            this.f18209a = C5652q.m20762a();
            C5651p c5651p = this.f18209a;
            C5651p c5651p2 = this.f18209a;
            r0 = this.f18209a;
            c5651p2.f18249g = r0;
            c5651p.f18248f = r0;
            return r0;
        } else {
            r0 = this.f18209a.f18249g;
            if (r0.f18245c + i > 8192 || !r0.f18247e) {
                return r0.m20759a(C5652q.m20762a());
            }
            return r0;
        }
    }

    /* renamed from: a */
    public void mo6706a(C5637c c5637c, long j) {
        if (c5637c == null) {
            throw new IllegalArgumentException("source == null");
        } else if (c5637c == this) {
            throw new IllegalArgumentException("source == this");
        } else {
            C5654u.m20769a(c5637c.f18210b, 0, j);
            while (j > 0) {
                C5651p c5651p;
                if (j < ((long) (c5637c.f18209a.f18245c - c5637c.f18209a.f18244b))) {
                    c5651p = this.f18209a != null ? this.f18209a.f18249g : null;
                    if (c5651p != null && c5651p.f18247e) {
                        if ((((long) c5651p.f18245c) + j) - ((long) (c5651p.f18246d ? 0 : c5651p.f18244b)) <= PlaybackStateCompat.ACTION_PLAY_FROM_URI) {
                            c5637c.f18209a.m20760a(c5651p, (int) j);
                            c5637c.f18210b -= j;
                            this.f18210b += j;
                            return;
                        }
                    }
                    c5637c.f18209a = c5637c.f18209a.m20758a((int) j);
                }
                C5651p c5651p2 = c5637c.f18209a;
                long j2 = (long) (c5651p2.f18245c - c5651p2.f18244b);
                c5637c.f18209a = c5651p2.m20757a();
                if (this.f18209a == null) {
                    this.f18209a = c5651p2;
                    c5651p2 = this.f18209a;
                    c5651p = this.f18209a;
                    C5651p c5651p3 = this.f18209a;
                    c5651p.f18249g = c5651p3;
                    c5651p2.f18248f = c5651p3;
                } else {
                    this.f18209a.f18249g.m20759a(c5651p2).m20761b();
                }
                c5637c.f18210b -= j2;
                this.f18210b += j2;
                j -= j2;
            }
        }
    }

    public long read(C5637c c5637c, long j) {
        if (c5637c == null) {
            throw new IllegalArgumentException("sink == null");
        } else if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (this.f18210b == 0) {
            return -1;
        } else {
            if (j > this.f18210b) {
                j = this.f18210b;
            }
            c5637c.mo6706a(this, j);
            return j;
        }
    }

    /* renamed from: a */
    public long mo6806a(byte b) {
        return m20625a(b, 0);
    }

    /* renamed from: a */
    public long m20625a(byte b, long j) {
        long j2 = 0;
        if (j < 0) {
            throw new IllegalArgumentException("fromIndex < 0");
        }
        C5651p c5651p = this.f18209a;
        if (c5651p == null) {
            return -1;
        }
        C5651p c5651p2;
        if (this.f18210b - j >= j) {
            c5651p2 = c5651p;
            while (true) {
                long j3 = ((long) (c5651p2.f18245c - c5651p2.f18244b)) + j2;
                if (j3 >= j) {
                    break;
                }
                c5651p2 = c5651p2.f18248f;
                j2 = j3;
            }
        } else {
            j2 = this.f18210b;
            c5651p2 = c5651p;
            while (j2 > j) {
                c5651p2 = c5651p2.f18249g;
                j2 -= (long) (c5651p2.f18245c - c5651p2.f18244b);
            }
        }
        while (j2 < this.f18210b) {
            byte[] bArr = c5651p2.f18243a;
            int i = c5651p2.f18245c;
            for (int i2 = (int) ((((long) c5651p2.f18244b) + j) - j2); i2 < i; i2++) {
                if (bArr[i2] == b) {
                    return j2 + ((long) (i2 - c5651p2.f18244b));
                }
            }
            j2 += (long) (c5651p2.f18245c - c5651p2.f18244b);
            c5651p2 = c5651p2.f18248f;
            j = j2;
        }
        return -1;
    }

    public void flush() {
    }

    public void close() {
    }

    public C5522t timeout() {
        return C5522t.f17784b;
    }

    public boolean equals(Object obj) {
        long j = 0;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C5637c)) {
            return false;
        }
        C5637c c5637c = (C5637c) obj;
        if (this.f18210b != c5637c.f18210b) {
            return false;
        }
        if (this.f18210b == 0) {
            return true;
        }
        C5651p c5651p = this.f18209a;
        C5651p c5651p2 = c5637c.f18209a;
        int i = c5651p.f18244b;
        int i2 = c5651p2.f18244b;
        while (j < this.f18210b) {
            long min = (long) Math.min(c5651p.f18245c - i, c5651p2.f18245c - i2);
            int i3 = 0;
            while (((long) i3) < min) {
                int i4 = i + 1;
                byte b = c5651p.f18243a[i];
                i = i2 + 1;
                if (b != c5651p2.f18243a[i2]) {
                    return false;
                }
                i3++;
                i2 = i;
                i = i4;
            }
            if (i == c5651p.f18245c) {
                c5651p = c5651p.f18248f;
                i = c5651p.f18244b;
            }
            if (i2 == c5651p2.f18245c) {
                c5651p2 = c5651p2.f18248f;
                i2 = c5651p2.f18244b;
            }
            j += min;
        }
        return true;
    }

    public int hashCode() {
        C5651p c5651p = this.f18209a;
        if (c5651p == null) {
            return 0;
        }
        int i = 1;
        do {
            int i2 = c5651p.f18244b;
            while (i2 < c5651p.f18245c) {
                int i3 = c5651p.f18243a[i2] + (i * 31);
                i2++;
                i = i3;
            }
            c5651p = c5651p.f18248f;
        } while (c5651p != this.f18209a);
        return i;
    }

    public String toString() {
        return m20687v().toString();
    }

    /* renamed from: u */
    public C5637c m20686u() {
        C5637c c5637c = new C5637c();
        if (this.f18210b == 0) {
            return c5637c;
        }
        c5637c.f18209a = new C5651p(this.f18209a);
        C5651p c5651p = c5637c.f18209a;
        C5651p c5651p2 = c5637c.f18209a;
        C5651p c5651p3 = c5637c.f18209a;
        c5651p2.f18249g = c5651p3;
        c5651p.f18248f = c5651p3;
        for (c5651p = this.f18209a.f18248f; c5651p != this.f18209a; c5651p = c5651p.f18248f) {
            c5637c.f18209a.f18249g.m20759a(new C5651p(c5651p));
        }
        c5637c.f18210b = this.f18210b;
        return c5637c;
    }

    /* renamed from: v */
    public ByteString m20687v() {
        if (this.f18210b <= 2147483647L) {
            return m20662g((int) this.f18210b);
        }
        throw new IllegalArgumentException("size > Integer.MAX_VALUE: " + this.f18210b);
    }

    /* renamed from: g */
    public ByteString m20662g(int i) {
        if (i == 0) {
            return ByteString.EMPTY;
        }
        return new SegmentedByteString(this, i);
    }
}
