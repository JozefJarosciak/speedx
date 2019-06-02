package okio;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class ByteString implements Serializable, Comparable<ByteString> {
    public static final ByteString EMPTY = of(new byte[0]);
    /* renamed from: a */
    static final char[] f18194a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final long serialVersionUID = 1;
    /* renamed from: b */
    final byte[] f18195b;
    /* renamed from: c */
    transient int f18196c;
    /* renamed from: d */
    transient String f18197d;

    ByteString(byte[] bArr) {
        this.f18195b = bArr;
    }

    public static ByteString of(byte... bArr) {
        if (bArr != null) {
            return new ByteString((byte[]) bArr.clone());
        }
        throw new IllegalArgumentException("data == null");
    }

    public static ByteString of(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new IllegalArgumentException("data == null");
        }
        C5654u.m20769a((long) bArr.length, (long) i, (long) i2);
        Object obj = new byte[i2];
        System.arraycopy(bArr, i, obj, 0, i2);
        return new ByteString(obj);
    }

    public static ByteString encodeUtf8(String str) {
        if (str == null) {
            throw new IllegalArgumentException("s == null");
        }
        ByteString byteString = new ByteString(str.getBytes(C5654u.f18252a));
        byteString.f18197d = str;
        return byteString;
    }

    public String utf8() {
        String str = this.f18197d;
        if (str != null) {
            return str;
        }
        str = new String(this.f18195b, C5654u.f18252a);
        this.f18197d = str;
        return str;
    }

    public String base64() {
        return C5632b.m20586a(this.f18195b);
    }

    public ByteString md5() {
        return digest("MD5");
    }

    public ByteString sha1() {
        return digest("SHA-1");
    }

    public ByteString sha256() {
        return digest("SHA-256");
    }

    private ByteString digest(String str) {
        try {
            return of(MessageDigest.getInstance(str).digest(this.f18195b));
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    public String base64Url() {
        return C5632b.m20589b(this.f18195b);
    }

    public static ByteString decodeBase64(String str) {
        if (str == null) {
            throw new IllegalArgumentException("base64 == null");
        }
        byte[] a = C5632b.m20588a(str);
        return a != null ? new ByteString(a) : null;
    }

    public String hex() {
        int i = 0;
        char[] cArr = new char[(this.f18195b.length * 2)];
        byte[] bArr = this.f18195b;
        int length = bArr.length;
        int i2 = 0;
        while (i < length) {
            byte b = bArr[i];
            int i3 = i2 + 1;
            cArr[i2] = f18194a[(b >> 4) & 15];
            i2 = i3 + 1;
            cArr[i3] = f18194a[b & 15];
            i++;
        }
        return new String(cArr);
    }

    public static ByteString decodeHex(String str) {
        if (str == null) {
            throw new IllegalArgumentException("hex == null");
        } else if (str.length() % 2 != 0) {
            throw new IllegalArgumentException("Unexpected hex string: " + str);
        } else {
            byte[] bArr = new byte[(str.length() / 2)];
            for (int i = 0; i < bArr.length; i++) {
                bArr[i] = (byte) ((decodeHexDigit(str.charAt(i * 2)) << 4) + decodeHexDigit(str.charAt((i * 2) + 1)));
            }
            return of(bArr);
        }
    }

    private static int decodeHexDigit(char c) {
        if (c >= '0' && c <= '9') {
            return c - 48;
        }
        if (c >= 'a' && c <= 'f') {
            return (c - 97) + 10;
        }
        if (c >= 'A' && c <= 'F') {
            return (c - 65) + 10;
        }
        throw new IllegalArgumentException("Unexpected hex digit: " + c);
    }

    public static ByteString read(InputStream inputStream, int i) throws IOException {
        if (inputStream == null) {
            throw new IllegalArgumentException("in == null");
        } else if (i < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + i);
        } else {
            byte[] bArr = new byte[i];
            int i2 = 0;
            while (i2 < i) {
                int read = inputStream.read(bArr, i2, i - i2);
                if (read == -1) {
                    throw new EOFException();
                }
                i2 += read;
            }
            return new ByteString(bArr);
        }
    }

    public ByteString toAsciiLowercase() {
        int i = 0;
        while (i < this.f18195b.length) {
            byte b = this.f18195b[i];
            if (b < (byte) 65 || b > (byte) 90) {
                i++;
            } else {
                byte[] bArr = (byte[]) this.f18195b.clone();
                int i2 = i + 1;
                bArr[i] = (byte) (b + 32);
                for (i = i2; i < bArr.length; i++) {
                    byte b2 = bArr[i];
                    if (b2 >= (byte) 65 && b2 <= (byte) 90) {
                        bArr[i] = (byte) (b2 + 32);
                    }
                }
                return new ByteString(bArr);
            }
        }
        return this;
    }

    public ByteString toAsciiUppercase() {
        int i = 0;
        while (i < this.f18195b.length) {
            byte b = this.f18195b[i];
            if (b < (byte) 97 || b > (byte) 122) {
                i++;
            } else {
                byte[] bArr = (byte[]) this.f18195b.clone();
                int i2 = i + 1;
                bArr[i] = (byte) (b - 32);
                for (i = i2; i < bArr.length; i++) {
                    byte b2 = bArr[i];
                    if (b2 >= (byte) 97 && b2 <= (byte) 122) {
                        bArr[i] = (byte) (b2 - 32);
                    }
                }
                return new ByteString(bArr);
            }
        }
        return this;
    }

    public ByteString substring(int i) {
        return substring(i, this.f18195b.length);
    }

    public ByteString substring(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException("beginIndex < 0");
        } else if (i2 > this.f18195b.length) {
            throw new IllegalArgumentException("endIndex > length(" + this.f18195b.length + ")");
        } else {
            int i3 = i2 - i;
            if (i3 < 0) {
                throw new IllegalArgumentException("endIndex < beginIndex");
            } else if (i == 0 && i2 == this.f18195b.length) {
                return this;
            } else {
                Object obj = new byte[i3];
                System.arraycopy(this.f18195b, i, obj, 0, i3);
                this(obj);
                return this;
            }
        }
    }

    public byte getByte(int i) {
        return this.f18195b[i];
    }

    public int size() {
        return this.f18195b.length;
    }

    public byte[] toByteArray() {
        return (byte[]) this.f18195b.clone();
    }

    /* renamed from: a */
    byte[] mo6783a() {
        return this.f18195b;
    }

    public ByteBuffer asByteBuffer() {
        return ByteBuffer.wrap(this.f18195b).asReadOnlyBuffer();
    }

    public void write(OutputStream outputStream) throws IOException {
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        }
        outputStream.write(this.f18195b);
    }

    /* renamed from: a */
    void mo6782a(C5637c c5637c) {
        c5637c.m20643b(this.f18195b, 0, this.f18195b.length);
    }

    public boolean rangeEquals(int i, ByteString byteString, int i2, int i3) {
        return byteString.rangeEquals(i2, this.f18195b, i, i3);
    }

    public boolean rangeEquals(int i, byte[] bArr, int i2, int i3) {
        return i >= 0 && i <= this.f18195b.length - i3 && i2 >= 0 && i2 <= bArr.length - i3 && C5654u.m20771a(this.f18195b, i, bArr, i2, i3);
    }

    public final boolean startsWith(ByteString byteString) {
        return rangeEquals(0, byteString, 0, byteString.size());
    }

    public final boolean startsWith(byte[] bArr) {
        return rangeEquals(0, bArr, 0, bArr.length);
    }

    public final boolean endsWith(ByteString byteString) {
        return rangeEquals(size() - byteString.size(), byteString, 0, byteString.size());
    }

    public final boolean endsWith(byte[] bArr) {
        return rangeEquals(size() - bArr.length, bArr, 0, bArr.length);
    }

    public final int indexOf(ByteString byteString) {
        return indexOf(byteString.mo6783a(), 0);
    }

    public final int indexOf(ByteString byteString, int i) {
        return indexOf(byteString.mo6783a(), i);
    }

    public final int indexOf(byte[] bArr) {
        return indexOf(bArr, 0);
    }

    public int indexOf(byte[] bArr, int i) {
        int length = this.f18195b.length - bArr.length;
        for (int max = Math.max(i, 0); max <= length; max++) {
            if (C5654u.m20771a(this.f18195b, max, bArr, 0, bArr.length)) {
                return max;
            }
        }
        return -1;
    }

    public final int lastIndexOf(ByteString byteString) {
        return lastIndexOf(byteString.mo6783a(), size());
    }

    public final int lastIndexOf(ByteString byteString, int i) {
        return lastIndexOf(byteString.mo6783a(), i);
    }

    public final int lastIndexOf(byte[] bArr) {
        return lastIndexOf(bArr, size());
    }

    public int lastIndexOf(byte[] bArr, int i) {
        for (int min = Math.min(i, this.f18195b.length - bArr.length); min >= 0; min--) {
            if (C5654u.m20771a(this.f18195b, min, bArr, 0, bArr.length)) {
                return min;
            }
        }
        return -1;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        boolean z = (obj instanceof ByteString) && ((ByteString) obj).size() == this.f18195b.length && ((ByteString) obj).rangeEquals(0, this.f18195b, 0, this.f18195b.length);
        return z;
    }

    public int hashCode() {
        int i = this.f18196c;
        if (i != 0) {
            return i;
        }
        i = Arrays.hashCode(this.f18195b);
        this.f18196c = i;
        return i;
    }

    public int compareTo(ByteString byteString) {
        int size = size();
        int size2 = byteString.size();
        int min = Math.min(size, size2);
        int i = 0;
        while (i < min) {
            int i2 = getByte(i) & 255;
            int i3 = byteString.getByte(i) & 255;
            if (i2 == i3) {
                i++;
            } else if (i2 < i3) {
                return -1;
            } else {
                return 1;
            }
        }
        if (size == size2) {
            return 0;
        }
        if (size >= size2) {
            return 1;
        }
        return -1;
    }

    public String toString() {
        if (this.f18195b.length == 0) {
            return "[size=0]";
        }
        String utf8 = utf8();
        int a = m20580a(utf8, 64);
        if (a != -1) {
            String replace = utf8.substring(0, a).replace("\\", "\\\\").replace("\n", "\\n").replace("\r", "\\r");
            return a < utf8.length() ? "[size=" + this.f18195b.length + " text=" + replace + "…]" : "[text=" + replace + "]";
        } else if (this.f18195b.length <= 64) {
            return "[hex=" + hex() + "]";
        } else {
            return "[size=" + this.f18195b.length + " hex=" + substring(0, 64).hex() + "…]";
        }
    }

    /* renamed from: a */
    static int m20580a(String str, int i) {
        int i2 = 0;
        int length = str.length();
        int i3 = 0;
        while (i2 < length) {
            if (i3 == i) {
                return i2;
            }
            int codePointAt = str.codePointAt(i2);
            if ((Character.isISOControl(codePointAt) && codePointAt != 10 && codePointAt != 13) || codePointAt == 65533) {
                return -1;
            }
            i3++;
            i2 += Character.charCount(codePointAt);
        }
        return str.length();
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException {
        ByteString read = read(objectInputStream, objectInputStream.readInt());
        try {
            Field declaredField = ByteString.class.getDeclaredField("b");
            declaredField.setAccessible(true);
            declaredField.set(this, read.f18195b);
        } catch (NoSuchFieldException e) {
            throw new AssertionError();
        } catch (IllegalAccessException e2) {
            throw new AssertionError();
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(this.f18195b.length);
        objectOutputStream.write(this.f18195b);
    }
}
