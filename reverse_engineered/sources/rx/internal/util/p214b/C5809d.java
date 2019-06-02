package rx.internal.util.p214b;

/* compiled from: MpmcArrayQueue */
/* renamed from: rx.internal.util.b.d */
public class C5809d<E> extends C5808e<E> {
    public C5809d(int i) {
        super(Math.max(2, i));
    }

    public boolean offer(E e) {
        if (e == null) {
            throw new NullPointerException("Null is not a valid element");
        }
        long j = this.b + 1;
        long[] jArr = this.d;
        long j2 = Long.MAX_VALUE;
        while (true) {
            long b = m20999b();
            long d = m20998d(b);
            long a = m20996a(jArr, d) - b;
            if (a == 0) {
                if (m21000c(b, 1 + b)) {
                    m20990a(m20987a(b), (Object) e);
                    m20997a(jArr, d, 1 + b);
                    return true;
                }
            } else if (a < 0 && b - j <= r4) {
                d = b - j;
                j2 = m21001a();
                if (d <= j2) {
                    return false;
                }
            }
            j2 = j2;
        }
    }

    public E poll() {
        long[] jArr = this.d;
        long j = -1;
        while (true) {
            long a = m21001a();
            long d = m20998d(a);
            long a2 = m20996a(jArr, d) - (1 + a);
            if (a2 == 0) {
                if (m21002b(a, 1 + a)) {
                    j = m20987a(a);
                    E b = m20992b(j);
                    m20990a(j, null);
                    m20997a(jArr, d, (this.b + a) + 1);
                    return b;
                }
            } else if (a2 < 0 && a >= r4) {
                j = m20999b();
                if (a == j) {
                    return null;
                }
            }
            j = j;
        }
    }

    public E peek() {
        E b;
        long a;
        do {
            a = m21001a();
            b = m20992b(m20987a(a));
            if (b != null) {
                break;
            }
        } while (a != m20999b());
        return b;
    }

    public int size() {
        long a = m21001a();
        while (true) {
            long b = m20999b();
            long a2 = m21001a();
            if (a == a2) {
                return (int) (b - a2);
            }
            a = a2;
        }
    }

    public boolean isEmpty() {
        return m21001a() == m20999b();
    }
}
