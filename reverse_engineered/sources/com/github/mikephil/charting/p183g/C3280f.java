package com.github.mikephil.charting.p183g;

/* compiled from: ObjectPool */
/* renamed from: com.github.mikephil.charting.g.f */
public class C3280f<T extends C3273a> {
    /* renamed from: a */
    private static int f14202a = 0;
    /* renamed from: b */
    private int f14203b;
    /* renamed from: c */
    private int f14204c;
    /* renamed from: d */
    private Object[] f14205d;
    /* renamed from: e */
    private int f14206e;
    /* renamed from: f */
    private T f14207f;
    /* renamed from: g */
    private float f14208g;

    /* compiled from: ObjectPool */
    /* renamed from: com.github.mikephil.charting.g.f$a */
    public static abstract class C3273a {
        /* renamed from: d */
        public static int f14173d = -1;
        /* renamed from: e */
        int f14174e = f14173d;

        /* renamed from: a */
        protected abstract C3273a mo4018a();
    }

    /* renamed from: a */
    public static synchronized C3280f m15903a(int i, C3273a c3273a) {
        C3280f c3280f;
        synchronized (C3280f.class) {
            c3280f = new C3280f(i, c3273a);
            c3280f.f14203b = f14202a;
            f14202a++;
        }
        return c3280f;
    }

    private C3280f(int i, T t) {
        if (i <= 0) {
            throw new IllegalArgumentException("Object Pool must be instantiated with a capacity greater than 0!");
        }
        this.f14204c = i;
        this.f14205d = new Object[this.f14204c];
        this.f14206e = 0;
        this.f14207f = t;
        this.f14208g = 1.0f;
        m15904b();
    }

    /* renamed from: a */
    public void m15908a(float f) {
        if (f > 1.0f) {
            f = 1.0f;
        } else if (f < 0.0f) {
            f = 0.0f;
        }
        this.f14208g = f;
    }

    /* renamed from: b */
    private void m15904b() {
        m15905b(this.f14208g);
    }

    /* renamed from: b */
    private void m15905b(float f) {
        int i = 1;
        int i2 = (int) (((float) this.f14204c) * f);
        if (i2 >= 1) {
            if (i2 > this.f14204c) {
                i = this.f14204c;
            } else {
                i = i2;
            }
        }
        for (i2 = 0; i2 < i; i2++) {
            this.f14205d[i2] = this.f14207f.mo4018a();
        }
        this.f14206e = i - 1;
    }

    /* renamed from: a */
    public synchronized T m15907a() {
        C3273a c3273a;
        if (this.f14206e == -1 && this.f14208g > 0.0f) {
            m15904b();
        }
        c3273a = (C3273a) this.f14205d[this.f14206e];
        c3273a.f14174e = C3273a.f14173d;
        this.f14206e--;
        return c3273a;
    }

    /* renamed from: a */
    public synchronized void m15909a(T t) {
        if (t.f14174e == C3273a.f14173d) {
            this.f14206e++;
            if (this.f14206e >= this.f14205d.length) {
                m15906c();
            }
            t.f14174e = this.f14203b;
            this.f14205d[this.f14206e] = t;
        } else if (t.f14174e == this.f14203b) {
            throw new IllegalArgumentException("The object passed is already stored in this pool!");
        } else {
            throw new IllegalArgumentException("The object to recycle already belongs to poolId " + t.f14174e + ".  Object cannot belong to two different pool instances simultaneously!");
        }
    }

    /* renamed from: c */
    private void m15906c() {
        int i = this.f14204c;
        this.f14204c *= 2;
        Object[] objArr = new Object[this.f14204c];
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = this.f14205d[i2];
        }
        this.f14205d = objArr;
    }
}
