package io.fabric.sdk.android.services.p204a;

import android.content.Context;

/* compiled from: AbstractValueCache */
/* renamed from: io.fabric.sdk.android.services.a.a */
public abstract class C4850a<T> implements C4849c<T> {
    /* renamed from: a */
    private final C4849c<T> f17119a;

    /* renamed from: a */
    protected abstract T mo6238a(Context context);

    /* renamed from: a */
    protected abstract void mo6239a(Context context, T t);

    public C4850a(C4849c<T> c4849c) {
        this.f17119a = c4849c;
    }

    /* renamed from: a */
    public final synchronized T mo6237a(Context context, C4852d<T> c4852d) throws Exception {
        T a;
        a = mo6238a(context);
        if (a == null) {
            a = this.f17119a != null ? this.f17119a.mo6237a(context, c4852d) : c4852d.mo6250a(context);
            m19073b(context, a);
        }
        return a;
    }

    /* renamed from: b */
    private void m19073b(Context context, T t) {
        if (t == null) {
            throw new NullPointerException();
        }
        mo6239a(context, (Object) t);
    }
}
