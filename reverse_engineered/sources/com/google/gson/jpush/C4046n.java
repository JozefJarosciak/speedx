package com.google.gson.jpush;

import com.google.gson.jpush.p185b.C3976a;
import com.google.gson.jpush.p185b.C3979c;
import com.google.gson.jpush.p185b.C3980d;

/* renamed from: com.google.gson.jpush.n */
final class C4046n extends al<Number> {
    /* renamed from: a */
    final /* synthetic */ C4042k f14581a;

    C4046n(C4042k c4042k) {
        this.f14581a = c4042k;
    }

    /* renamed from: a */
    public final /* synthetic */ Object mo5672a(C3976a c3976a) {
        if (c3976a.mo5683f() != C3979c.f14328i) {
            return Double.valueOf(c3976a.mo5688k());
        }
        c3976a.mo5687j();
        return null;
    }

    /* renamed from: a */
    public final /* synthetic */ void mo5673a(C3980d c3980d, Object obj) {
        Number number = (Number) obj;
        if (number == null) {
            c3980d.mo5705f();
            return;
        }
        C4042k.m16378a(this.f14581a, number.doubleValue());
        c3980d.mo5695a(number);
    }
}
