package okhttp3.internal;

/* compiled from: NamedRunnable */
/* renamed from: okhttp3.internal.h */
public abstract class C5503h implements Runnable {
    /* renamed from: b */
    protected final String f17705b;

    /* renamed from: b */
    protected abstract void mo6710b();

    public C5503h(String str, Object... objArr) {
        this.f17705b = C5586l.m20319a(str, objArr);
    }

    public final void run() {
        String name = Thread.currentThread().getName();
        Thread.currentThread().setName(this.f17705b);
        try {
            mo6710b();
        } finally {
            Thread.currentThread().setName(name);
        }
    }
}
