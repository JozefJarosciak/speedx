package p203u.aly;

/* compiled from: TTransport */
/* renamed from: u.aly.bm */
public abstract class bm {
    /* renamed from: a */
    public abstract int mo7216a(byte[] bArr, int i, int i2) throws dd;

    /* renamed from: b */
    public abstract void mo7217b(byte[] bArr, int i, int i2) throws dd;

    /* renamed from: d */
    public int m21708d(byte[] bArr, int i, int i2) throws dd {
        int i3 = 0;
        while (i3 < i2) {
            int a = mo7216a(bArr, i + i3, i2 - i3);
            if (a <= 0) {
                throw new dd("Cannot read. Remote side has closed. Tried to read " + i2 + " bytes, but only got " + i3 + " bytes. (This is often indicative of an internal error on the server side. Please check your server logs.)");
            }
            i3 += a;
        }
        return i3;
    }

    /* renamed from: b */
    public void m21703b(byte[] bArr) throws dd {
        mo7217b(bArr, 0, bArr.length);
    }

    /* renamed from: b */
    public byte[] mo7219b() {
        return null;
    }

    /* renamed from: c */
    public int mo7220c() {
        return 0;
    }

    /* renamed from: d */
    public int mo7221d() {
        return -1;
    }

    /* renamed from: a */
    public void mo7218a(int i) {
    }
}
