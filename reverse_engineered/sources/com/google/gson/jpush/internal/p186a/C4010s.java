package com.google.gson.jpush.internal.p186a;

import com.google.gson.jpush.af;
import com.google.gson.jpush.al;
import com.google.gson.jpush.internal.ae;
import com.google.gson.jpush.p185b.C3976a;
import com.google.gson.jpush.p185b.C3979c;
import com.google.gson.jpush.p185b.C3980d;
import java.util.Map;

/* renamed from: com.google.gson.jpush.internal.a.s */
public final class C4010s<T> extends al<T> {
    /* renamed from: a */
    private final ae<T> f14426a;
    /* renamed from: b */
    private final Map<String, C4008t> f14427b;

    private C4010s(ae<T> aeVar, Map<String, C4008t> map) {
        this.f14426a = aeVar;
        this.f14427b = map;
    }

    /* renamed from: a */
    public final T mo5672a(C3976a c3976a) {
        if (c3976a.mo5683f() == C3979c.f14328i) {
            c3976a.mo5687j();
            return null;
        }
        T a = this.f14426a.mo5711a();
        try {
            c3976a.mo5679c();
            while (c3976a.mo5682e()) {
                C4008t c4008t = (C4008t) this.f14427b.get(c3976a.mo5684g());
                if (c4008t == null || !c4008t.f14419i) {
                    c3976a.mo5691n();
                } else {
                    c4008t.mo5707a(c3976a, (Object) a);
                }
            }
            c3976a.mo5681d();
            return a;
        } catch (Throwable e) {
            throw new af(e);
        } catch (IllegalAccessException e2) {
            throw new AssertionError(e2);
        }
    }

    /* renamed from: a */
    public final void mo5673a(C3980d c3980d, T t) {
        if (t == null) {
            c3980d.mo5705f();
            return;
        }
        c3980d.mo5703d();
        try {
            for (C4008t c4008t : this.f14427b.values()) {
                if (c4008t.mo5709a(t)) {
                    c3980d.mo5696a(c4008t.f14417g);
                    c4008t.mo5708a(c3980d, (Object) t);
                }
            }
            c3980d.mo5704e();
        } catch (IllegalAccessException e) {
            throw new AssertionError();
        }
    }
}
