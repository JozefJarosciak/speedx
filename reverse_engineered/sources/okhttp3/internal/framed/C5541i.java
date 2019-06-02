package okhttp3.internal.framed;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import okio.ByteString;
import okio.C5538h;
import okio.C5636e;
import okio.C5637c;
import okio.C5643l;
import okio.C5647m;

/* compiled from: NameValueBlockReader */
/* renamed from: okhttp3.internal.framed.i */
class C5541i {
    /* renamed from: a */
    private final C5643l f17856a;
    /* renamed from: b */
    private int f17857b;
    /* renamed from: c */
    private final C5636e f17858c = C5647m.m20714a(this.f17856a);

    /* compiled from: NameValueBlockReader */
    /* renamed from: okhttp3.internal.framed.i$2 */
    class C55402 extends Inflater {
        /* renamed from: a */
        final /* synthetic */ C5541i f17855a;

        C55402(C5541i c5541i) {
            this.f17855a = c5541i;
        }

        public int inflate(byte[] bArr, int i, int i2) throws DataFormatException {
            int inflate = super.inflate(bArr, i, i2);
            if (inflate != 0 || !needsDictionary()) {
                return inflate;
            }
            setDictionary(C5548m.f17875a);
            return super.inflate(bArr, i, i2);
        }
    }

    public C5541i(C5636e c5636e) {
        this.f17856a = new C5643l(new C5538h(this, c5636e) {
            /* renamed from: a */
            final /* synthetic */ C5541i f17854a;

            public long read(C5637c c5637c, long j) throws IOException {
                if (this.f17854a.f17857b == 0) {
                    return -1;
                }
                long read = super.read(c5637c, Math.min(j, (long) this.f17854a.f17857b));
                if (read == -1) {
                    return -1;
                }
                this.f17854a.f17857b = (int) (((long) this.f17854a.f17857b) - read);
                return read;
            }
        }, new C55402(this));
    }

    /* renamed from: a */
    public List<C5526e> m20102a(int i) throws IOException {
        this.f17857b += i;
        int k = this.f17858c.mo6827k();
        if (k < 0) {
            throw new IOException("numberOfPairs < 0: " + k);
        } else if (k > 1024) {
            throw new IOException("numberOfPairs > 1024: " + k);
        } else {
            List<C5526e> arrayList = new ArrayList(k);
            for (int i2 = 0; i2 < k; i2++) {
                ByteString toAsciiLowercase = m20100b().toAsciiLowercase();
                ByteString b = m20100b();
                if (toAsciiLowercase.size() == 0) {
                    throw new IOException("name.size == 0");
                }
                arrayList.add(new C5526e(toAsciiLowercase, b));
            }
            m20101c();
            return arrayList;
        }
    }

    /* renamed from: b */
    private ByteString m20100b() throws IOException {
        return this.f17858c.mo6816d((long) this.f17858c.mo6827k());
    }

    /* renamed from: c */
    private void m20101c() throws IOException {
        if (this.f17857b > 0) {
            this.f17856a.m20709a();
            if (this.f17857b != 0) {
                throw new IOException("compressedLimit > 0: " + this.f17857b);
            }
        }
    }

    /* renamed from: a */
    public void m20103a() throws IOException {
        this.f17858c.close();
    }
}
