package rx;

public final class Notification<T> {
    /* renamed from: d */
    private static final Notification<Void> f18265d = new Notification(Kind.OnCompleted, null, null);
    /* renamed from: a */
    private final Kind f18266a;
    /* renamed from: b */
    private final Throwable f18267b;
    /* renamed from: c */
    private final T f18268c;

    public enum Kind {
        OnNext,
        OnError,
        OnCompleted
    }

    private Notification(Kind kind, T t, Throwable th) {
        this.f18268c = t;
        this.f18267b = th;
        this.f18266a = kind;
    }

    /* renamed from: a */
    public Throwable m20788a() {
        return this.f18267b;
    }

    /* renamed from: b */
    public T m20789b() {
        return this.f18268c;
    }

    /* renamed from: c */
    public boolean m20790c() {
        return m20794g() && this.f18268c != null;
    }

    /* renamed from: d */
    public boolean m20791d() {
        return m20793f() && this.f18267b != null;
    }

    /* renamed from: e */
    public Kind m20792e() {
        return this.f18266a;
    }

    /* renamed from: f */
    public boolean m20793f() {
        return m20792e() == Kind.OnError;
    }

    /* renamed from: g */
    public boolean m20794g() {
        return m20792e() == Kind.OnNext;
    }

    public String toString() {
        StringBuilder append = new StringBuilder("[").append(super.toString()).append(" ").append(m20792e());
        if (m20790c()) {
            append.append(" ").append(m20789b());
        }
        if (m20791d()) {
            append.append(" ").append(m20788a().getMessage());
        }
        append.append("]");
        return append.toString();
    }

    public int hashCode() {
        int hashCode = m20792e().hashCode();
        if (m20790c()) {
            hashCode = (hashCode * 31) + m20789b().hashCode();
        }
        if (m20791d()) {
            return (hashCode * 31) + m20788a().hashCode();
        }
        return hashCode;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Notification notification = (Notification) obj;
        if (notification.m20792e() != m20792e()) {
            return false;
        }
        if (m20790c() && !m20789b().equals(notification.m20789b())) {
            return false;
        }
        if (m20791d() && !m20788a().equals(notification.m20788a())) {
            return false;
        }
        if (!m20790c() && !m20791d() && notification.m20790c()) {
            return false;
        }
        if (m20790c() || m20791d() || !notification.m20791d()) {
            return true;
        }
        return false;
    }
}
