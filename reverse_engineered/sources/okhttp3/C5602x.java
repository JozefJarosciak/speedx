package okhttp3;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import okhttp3.internal.C5586l;
import okio.ByteString;
import okio.C5635d;
import okio.C5647m;

/* compiled from: RequestBody */
/* renamed from: okhttp3.x */
public abstract class C5602x {
    public abstract C5608s contentType();

    public abstract void writeTo(C5635d c5635d) throws IOException;

    public long contentLength() throws IOException {
        return -1;
    }

    public static C5602x create(C5608s c5608s, String str) {
        Charset charset = C5586l.f18008c;
        if (c5608s != null) {
            charset = c5608s.m20422c();
            if (charset == null) {
                charset = C5586l.f18008c;
                c5608s = C5608s.m20418a(c5608s + "; charset=utf-8");
            }
        }
        return C5602x.create(c5608s, str.getBytes(charset));
    }

    public static C5602x create(final C5608s c5608s, final ByteString byteString) {
        return new C5602x() {
            public C5608s contentType() {
                return c5608s;
            }

            public long contentLength() throws IOException {
                return (long) byteString.size();
            }

            public void writeTo(C5635d c5635d) throws IOException {
                c5635d.mo6812b(byteString);
            }
        };
    }

    public static C5602x create(C5608s c5608s, byte[] bArr) {
        return C5602x.create(c5608s, bArr, 0, bArr.length);
    }

    public static C5602x create(final C5608s c5608s, final byte[] bArr, final int i, final int i2) {
        if (bArr == null) {
            throw new NullPointerException("content == null");
        }
        C5586l.m20326a((long) bArr.length, (long) i, (long) i2);
        return new C5602x() {
            public C5608s contentType() {
                return c5608s;
            }

            public long contentLength() {
                return (long) i2;
            }

            public void writeTo(C5635d c5635d) throws IOException {
                c5635d.mo6815c(bArr, i, i2);
            }
        };
    }

    public static C5602x create(final C5608s c5608s, final File file) {
        if (file != null) {
            return new C5602x() {
                public C5608s contentType() {
                    return c5608s;
                }

                public long contentLength() {
                    return file.length();
                }

                public void writeTo(C5635d c5635d) throws IOException {
                    Closeable closeable = null;
                    try {
                        closeable = C5647m.m20717a(file);
                        c5635d.mo6808a(closeable);
                    } finally {
                        C5586l.m20327a(closeable);
                    }
                }
            };
        }
        throw new NullPointerException("content == null");
    }
}
