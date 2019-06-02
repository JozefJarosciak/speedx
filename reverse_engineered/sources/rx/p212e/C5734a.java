package rx.p212e;

/* compiled from: Timestamped */
/* renamed from: rx.e.a */
public final class C5734a<T> {
    /* renamed from: a */
    private final long f18295a;
    /* renamed from: b */
    private final T f18296b;

    public C5734a(long j, T t) {
        this.f18296b = t;
        this.f18295a = j;
    }

    /* renamed from: a */
    public long m20848a() {
        return this.f18295a;
    }

    /* renamed from: b */
    public T m20849b() {
        return this.f18296b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof C5734a)) {
            return false;
        }
        C5734a c5734a = (C5734a) obj;
        if (this.f18295a != c5734a.f18295a) {
            return false;
        }
        if (this.f18296b == null) {
            if (c5734a.f18296b != null) {
                return false;
            }
            return true;
        } else if (this.f18296b.equals(c5734a.f18296b)) {
            return true;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return (this.f18296b == null ? 0 : this.f18296b.hashCode()) + ((((int) (this.f18295a ^ (this.f18295a >>> 32))) + 31) * 31);
    }

    public String toString() {
        return String.format("Timestamped(timestampMillis = %d, value = %s)", new Object[]{Long.valueOf(this.f18295a), this.f18296b.toString()});
    }
}
