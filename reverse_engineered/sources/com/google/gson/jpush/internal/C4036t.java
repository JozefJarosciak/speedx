package com.google.gson.jpush.internal;

import com.google.gson.jpush.C4042k;
import com.google.gson.jpush.al;
import com.google.gson.jpush.p184a.C3972a;
import com.google.gson.jpush.p185b.C3976a;
import com.google.gson.jpush.p185b.C3980d;

/* renamed from: com.google.gson.jpush.internal.t */
final class C4036t extends al<T> {
    /* renamed from: a */
    final /* synthetic */ boolean f14549a;
    /* renamed from: b */
    final /* synthetic */ boolean f14550b;
    /* renamed from: c */
    final /* synthetic */ C4042k f14551c;
    /* renamed from: d */
    final /* synthetic */ C3972a f14552d;
    /* renamed from: e */
    final /* synthetic */ C4035s f14553e;
    /* renamed from: f */
    private al<T> f14554f;

    C4036t(C4035s c4035s, boolean z, boolean z2, C4042k c4042k, C3972a c3972a) {
        this.f14553e = c4035s;
        this.f14549a = z;
        this.f14550b = z2;
        this.f14551c = c4042k;
        this.f14552d = c3972a;
    }

    /* renamed from: a */
    private al<T> m16364a() {
        al<T> alVar = this.f14554f;
        if (alVar != null) {
            return alVar;
        }
        alVar = this.f14551c.m16381a(this.f14553e, this.f14552d);
        this.f14554f = alVar;
        return alVar;
    }

    /* renamed from: a */
    public final T mo5672a(C3976a c3976a) {
        if (!this.f14549a) {
            return m16364a().mo5672a(c3976a);
        }
        c3976a.mo5691n();
        return null;
    }

    /* renamed from: a */
    public final void mo5673a(C3980d c3980d, T t) {
        if (this.f14550b) {
            c3980d.mo5705f();
        } else {
            m16364a().mo5673a(c3980d, t);
        }
    }
}
