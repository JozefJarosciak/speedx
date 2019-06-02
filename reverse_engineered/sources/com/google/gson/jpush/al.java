package com.google.gson.jpush;

import com.google.gson.jpush.internal.p186a.C4000j;
import com.google.gson.jpush.p185b.C3976a;
import com.google.gson.jpush.p185b.C3980d;

public abstract class al<T> {
    /* renamed from: a */
    public final C3975w m16088a(T t) {
        try {
            C3980d c4000j = new C4000j();
            mo5673a(c4000j, t);
            return c4000j.mo5698a();
        } catch (Throwable e) {
            throw new C4053x(e);
        }
    }

    /* renamed from: a */
    public abstract T mo5672a(C3976a c3976a);

    /* renamed from: a */
    public abstract void mo5673a(C3980d c3980d, T t);
}
