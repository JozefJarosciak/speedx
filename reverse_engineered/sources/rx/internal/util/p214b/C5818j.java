package rx.internal.util.p214b;

/* compiled from: SpmcArrayQueue */
/* renamed from: rx.internal.util.b.j */
public final class C5818j<E> extends C5817n<E> {
    public C5818j(int i) {
        super(i);
    }

    public boolean offer(E e) {
        if (e == null) {
            throw new NullPointerException("Null is not a valid element");
        }
        Object[] objArr = this.c;
        long j = this.b;
        long b = m21004b();
        long a = m20987a(b);
        if (m20993b(objArr, a) == null) {
            m20991a(objArr, a, e);
            m21005d(1 + b);
        } else if (b - m21006a() > j) {
            return false;
        } else {
            do {
            } while (m20993b(objArr, a) != null);
        }
        m20991a(objArr, a, e);
        m21005d(1 + b);
        return true;
    }

    public E poll() {
        long a;
        long c = m21008c();
        do {
            a = m21006a();
            if (a >= c) {
                long b = m21004b();
                if (a >= b) {
                    return null;
                }
                m21009e(b);
            }
        } while (!m21007b(a, 1 + a));
        c = m20987a(a);
        Object[] objArr = this.c;
        E a2 = m20989a(objArr, c);
        m20994b(objArr, c, null);
        return a2;
    }

    public E peek() {
        E c;
        long c2 = m21008c();
        do {
            long a = m21006a();
            if (a >= c2) {
                long b = m21004b();
                if (a >= b) {
                    return null;
                }
                m21009e(b);
            }
            c = m20995c(m20987a(a));
        } while (c == null);
        return c;
    }

    public int size() {
        long a = m21006a();
        while (true) {
            long b = m21004b();
            long a2 = m21006a();
            if (a == a2) {
                return (int) (b - a2);
            }
            a = a2;
        }
    }

    public boolean isEmpty() {
        return m21006a() == m21004b();
    }
}
