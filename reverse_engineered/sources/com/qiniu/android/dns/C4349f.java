package com.qiniu.android.dns;

/* compiled from: Record */
/* renamed from: com.qiniu.android.dns.f */
public final class C4349f {
    /* renamed from: a */
    public final String f15125a;
    /* renamed from: b */
    public final int f15126b;
    /* renamed from: c */
    public final int f15127c;
    /* renamed from: d */
    public final long f15128d;

    public C4349f(String str, int i, int i2, long j) {
        this.f15125a = str;
        this.f15126b = i;
        if (i2 < 600) {
            i2 = 600;
        }
        this.f15127c = i2;
        this.f15128d = j;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof C4349f)) {
            return false;
        }
        C4349f c4349f = (C4349f) obj;
        if (this.f15125a.equals(c4349f.f15125a) && this.f15126b == c4349f.f15126b && this.f15127c == c4349f.f15127c && this.f15128d == c4349f.f15128d) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    public boolean m17152a() {
        return this.f15126b == 5;
    }

    /* renamed from: b */
    public boolean m17154b() {
        return m17153a(System.currentTimeMillis() / 1000);
    }

    /* renamed from: a */
    public boolean m17153a(long j) {
        return this.f15128d + ((long) this.f15127c) < j;
    }
}
