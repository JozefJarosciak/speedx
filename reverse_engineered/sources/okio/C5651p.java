package okio;

/* compiled from: Segment */
/* renamed from: okio.p */
final class C5651p {
    /* renamed from: a */
    final byte[] f18243a;
    /* renamed from: b */
    int f18244b;
    /* renamed from: c */
    int f18245c;
    /* renamed from: d */
    boolean f18246d;
    /* renamed from: e */
    boolean f18247e;
    /* renamed from: f */
    C5651p f18248f;
    /* renamed from: g */
    C5651p f18249g;

    C5651p() {
        this.f18243a = new byte[8192];
        this.f18247e = true;
        this.f18246d = false;
    }

    C5651p(C5651p c5651p) {
        this(c5651p.f18243a, c5651p.f18244b, c5651p.f18245c);
        c5651p.f18246d = true;
    }

    C5651p(byte[] bArr, int i, int i2) {
        this.f18243a = bArr;
        this.f18244b = i;
        this.f18245c = i2;
        this.f18247e = false;
        this.f18246d = true;
    }

    /* renamed from: a */
    public C5651p m20757a() {
        C5651p c5651p = this.f18248f != this ? this.f18248f : null;
        this.f18249g.f18248f = this.f18248f;
        this.f18248f.f18249g = this.f18249g;
        this.f18248f = null;
        this.f18249g = null;
        return c5651p;
    }

    /* renamed from: a */
    public C5651p m20759a(C5651p c5651p) {
        c5651p.f18249g = this;
        c5651p.f18248f = this.f18248f;
        this.f18248f.f18249g = c5651p;
        this.f18248f = c5651p;
        return c5651p;
    }

    /* renamed from: a */
    public C5651p m20758a(int i) {
        if (i <= 0 || i > this.f18245c - this.f18244b) {
            throw new IllegalArgumentException();
        }
        C5651p c5651p;
        if (i >= 1024) {
            c5651p = new C5651p(this);
        } else {
            c5651p = C5652q.m20762a();
            System.arraycopy(this.f18243a, this.f18244b, c5651p.f18243a, 0, i);
        }
        c5651p.f18245c = c5651p.f18244b + i;
        this.f18244b += i;
        this.f18249g.m20759a(c5651p);
        return c5651p;
    }

    /* renamed from: b */
    public void m20761b() {
        if (this.f18249g == this) {
            throw new IllegalStateException();
        } else if (this.f18249g.f18247e) {
            int i = this.f18245c - this.f18244b;
            if (i <= (this.f18249g.f18246d ? 0 : this.f18249g.f18244b) + (8192 - this.f18249g.f18245c)) {
                m20760a(this.f18249g, i);
                m20757a();
                C5652q.m20763a(this);
            }
        }
    }

    /* renamed from: a */
    public void m20760a(C5651p c5651p, int i) {
        if (c5651p.f18247e) {
            if (c5651p.f18245c + i > 8192) {
                if (c5651p.f18246d) {
                    throw new IllegalArgumentException();
                } else if ((c5651p.f18245c + i) - c5651p.f18244b > 8192) {
                    throw new IllegalArgumentException();
                } else {
                    System.arraycopy(c5651p.f18243a, c5651p.f18244b, c5651p.f18243a, 0, c5651p.f18245c - c5651p.f18244b);
                    c5651p.f18245c -= c5651p.f18244b;
                    c5651p.f18244b = 0;
                }
            }
            System.arraycopy(this.f18243a, this.f18244b, c5651p.f18243a, c5651p.f18245c, i);
            c5651p.f18245c += i;
            this.f18244b += i;
            return;
        }
        throw new IllegalArgumentException();
    }
}
