package com.google.gson.jpush.internal.p186a;

import com.google.gson.jpush.al;
import com.google.gson.jpush.p185b.C3976a;
import com.google.gson.jpush.p185b.C3979c;
import com.google.gson.jpush.p185b.C3980d;
import java.math.BigDecimal;

/* renamed from: com.google.gson.jpush.internal.a.af */
final class af extends al<BigDecimal> {
    af() {
    }

    /* renamed from: b */
    private static BigDecimal m16184b(C3976a c3976a) {
        if (c3976a.mo5683f() == C3979c.f14328i) {
            c3976a.mo5687j();
            return null;
        }
        try {
            return new BigDecimal(c3976a.mo5685h());
        } catch (Throwable e) {
            throw new com.google.gson.jpush.af(e);
        }
    }

    /* renamed from: a */
    public final /* synthetic */ Object mo5672a(C3976a c3976a) {
        return af.m16184b(c3976a);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo5673a(C3980d c3980d, Object obj) {
        c3980d.mo5695a((BigDecimal) obj);
    }
}
