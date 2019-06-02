package okhttp3.internal.framed;

import java.util.Arrays;

/* compiled from: Settings */
/* renamed from: okhttp3.internal.framed.l */
public final class C5545l {
    /* renamed from: a */
    private int f17863a;
    /* renamed from: b */
    private int f17864b;
    /* renamed from: c */
    private int f17865c;
    /* renamed from: d */
    private final int[] f17866d = new int[10];

    /* renamed from: a */
    void m20116a() {
        this.f17865c = 0;
        this.f17864b = 0;
        this.f17863a = 0;
        Arrays.fill(this.f17866d, 0);
    }

    /* renamed from: a */
    C5545l m20115a(int i, int i2, int i3) {
        if (i < this.f17866d.length) {
            int i4 = 1 << i;
            this.f17863a |= i4;
            if ((i2 & 1) != 0) {
                this.f17864b |= i4;
            } else {
                this.f17864b &= i4 ^ -1;
            }
            if ((i2 & 2) != 0) {
                this.f17865c = i4 | this.f17865c;
            } else {
                this.f17865c = (i4 ^ -1) & this.f17865c;
            }
            this.f17866d[i] = i3;
        }
        return this;
    }

    /* renamed from: a */
    boolean m20118a(int i) {
        if (((1 << i) & this.f17863a) != 0) {
            return true;
        }
        return false;
    }

    /* renamed from: b */
    int m20120b(int i) {
        return this.f17866d[i];
    }

    /* renamed from: c */
    int m20122c(int i) {
        int i2 = 0;
        if (m20127h(i)) {
            i2 = 2;
        }
        if (m20126g(i)) {
            return i2 | 1;
        }
        return i2;
    }

    /* renamed from: b */
    int m20119b() {
        return Integer.bitCount(this.f17863a);
    }

    /* renamed from: c */
    int m20121c() {
        return (2 & this.f17863a) != 0 ? this.f17866d[1] : -1;
    }

    /* renamed from: d */
    int m20123d(int i) {
        return (16 & this.f17863a) != 0 ? this.f17866d[4] : i;
    }

    /* renamed from: e */
    int m20124e(int i) {
        return (32 & this.f17863a) != 0 ? this.f17866d[5] : i;
    }

    /* renamed from: f */
    int m20125f(int i) {
        return (128 & this.f17863a) != 0 ? this.f17866d[7] : i;
    }

    /* renamed from: g */
    boolean m20126g(int i) {
        if (((1 << i) & this.f17864b) != 0) {
            return true;
        }
        return false;
    }

    /* renamed from: h */
    boolean m20127h(int i) {
        if (((1 << i) & this.f17865c) != 0) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    void m20117a(C5545l c5545l) {
        for (int i = 0; i < 10; i++) {
            if (c5545l.m20118a(i)) {
                m20115a(i, c5545l.m20122c(i), c5545l.m20120b(i));
            }
        }
    }
}
