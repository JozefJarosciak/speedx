package p203u.aly;

/* compiled from: TMemoryInputTransport */
/* renamed from: u.aly.bl */
public final class bl extends bm {
    /* renamed from: a */
    private byte[] f18900a;
    /* renamed from: b */
    private int f18901b;
    /* renamed from: c */
    private int f18902c;

    /* renamed from: a */
    public void m21714a(byte[] bArr) {
        m21718c(bArr, 0, bArr.length);
    }

    /* renamed from: c */
    public void m21718c(byte[] bArr, int i, int i2) {
        this.f18900a = bArr;
        this.f18901b = i;
        this.f18902c = i + i2;
    }

    /* renamed from: a */
    public void m21712a() {
        this.f18900a = null;
    }

    /* renamed from: a */
    public int mo7216a(byte[] bArr, int i, int i2) throws dd {
        int d = mo7221d();
        if (i2 > d) {
            i2 = d;
        }
        if (i2 > 0) {
            System.arraycopy(this.f18900a, this.f18901b, bArr, i, i2);
            mo7218a(i2);
        }
        return i2;
    }

    /* renamed from: b */
    public void mo7217b(byte[] bArr, int i, int i2) throws dd {
        throw new UnsupportedOperationException("No writing allowed!");
    }

    /* renamed from: b */
    public byte[] mo7219b() {
        return this.f18900a;
    }

    /* renamed from: c */
    public int mo7220c() {
        return this.f18901b;
    }

    /* renamed from: d */
    public int mo7221d() {
        return this.f18902c - this.f18901b;
    }

    /* renamed from: a */
    public void mo7218a(int i) {
        this.f18901b += i;
    }
}
