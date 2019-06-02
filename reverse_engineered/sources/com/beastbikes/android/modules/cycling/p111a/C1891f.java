package com.beastbikes.android.modules.cycling.p111a;

/* compiled from: Simplify */
/* renamed from: com.beastbikes.android.modules.cycling.a.f */
public class C1891f<T> extends C1885a<T> {
    /* renamed from: a */
    private final C1889e<T> f8454a = new C18901(this);

    /* compiled from: Simplify */
    /* renamed from: com.beastbikes.android.modules.cycling.a.f$1 */
    class C18901 implements C1889e<T> {
        /* renamed from: a */
        final /* synthetic */ C1891f f8453a;

        C18901(C1891f c1891f) {
            this.f8453a = c1891f;
        }

        /* renamed from: a */
        public double mo3265a(T t) {
            return ((C1886b) t).mo3263a();
        }

        /* renamed from: b */
        public double mo3266b(T t) {
            return ((C1886b) t).mo3264b();
        }
    }

    public C1891f(T[] tArr) {
        super(tArr);
    }

    /* renamed from: a */
    public double mo3267a(T t, T t2) {
        double a = this.f8454a.mo3265a(t) - this.f8454a.mo3265a(t2);
        double b = this.f8454a.mo3266b(t) - this.f8454a.mo3266b(t2);
        return (a * a) + (b * b);
    }

    /* renamed from: a */
    public double mo3268a(T t, T t2, T t3) {
        double a = this.f8454a.mo3265a(t2);
        double b = this.f8454a.mo3266b(t2);
        double a2 = this.f8454a.mo3265a(t3);
        double b2 = this.f8454a.mo3266b(t3);
        double a3 = this.f8454a.mo3265a(t);
        double b3 = this.f8454a.mo3266b(t);
        double d = a2 - a;
        double d2 = b2 - b;
        if (!(d == 0.0d && d2 == 0.0d)) {
            double d3 = (((a3 - a) * d) + ((b3 - b) * d2)) / ((d * d) + (d2 * d2));
            if (d3 > 1.0d) {
                b = b2;
                b2 = a2;
            } else if (d3 > 0.0d) {
                b2 = (d * d3) + a;
                b += d2 * d3;
            }
            b2 = a3 - b2;
            b = b3 - b;
            return (b * b) + (b2 * b2);
        }
        b2 = a;
        b2 = a3 - b2;
        b = b3 - b;
        return (b * b) + (b2 * b2);
    }
}
