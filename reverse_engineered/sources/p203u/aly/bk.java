package p203u.aly;

import java.io.InputStream;
import java.io.OutputStream;

/* compiled from: TIOStreamTransport */
/* renamed from: u.aly.bk */
public class bk extends bm {
    /* renamed from: a */
    protected InputStream f18898a = null;
    /* renamed from: b */
    protected OutputStream f18899b = null;

    protected bk() {
    }

    public bk(InputStream inputStream) {
        this.f18898a = inputStream;
    }

    public bk(OutputStream outputStream) {
        this.f18899b = outputStream;
    }

    /* renamed from: a */
    public int mo7216a(byte[] bArr, int i, int i2) throws dd {
        if (this.f18898a == null) {
            throw new dd(1, "Cannot read from null inputStream");
        }
        try {
            int read = this.f18898a.read(bArr, i, i2);
            if (read >= 0) {
                return read;
            }
            throw new dd(4);
        } catch (Throwable e) {
            throw new dd(0, e);
        }
    }

    /* renamed from: b */
    public void mo7217b(byte[] bArr, int i, int i2) throws dd {
        if (this.f18899b == null) {
            throw new dd(1, "Cannot write to null outputStream");
        }
        try {
            this.f18899b.write(bArr, i, i2);
        } catch (Throwable e) {
            throw new dd(0, e);
        }
    }
}
