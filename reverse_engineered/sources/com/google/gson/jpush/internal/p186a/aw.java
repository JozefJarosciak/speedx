package com.google.gson.jpush.internal.p186a;

import com.google.gson.jpush.al;
import com.google.gson.jpush.p185b.C3976a;
import com.google.gson.jpush.p185b.C3979c;
import com.google.gson.jpush.p185b.C3980d;

/* renamed from: com.google.gson.jpush.internal.a.aw */
final class aw extends al<Boolean> {
    aw() {
    }

    /* renamed from: a */
    public final /* synthetic */ Object mo5672a(C3976a c3976a) {
        if (c3976a.mo5683f() != C3979c.f14328i) {
            return c3976a.mo5683f() == C3979c.f14325f ? Boolean.valueOf(Boolean.parseBoolean(c3976a.mo5685h())) : Boolean.valueOf(c3976a.mo5686i());
        } else {
            c3976a.mo5687j();
            return null;
        }
    }

    /* renamed from: a */
    public final /* synthetic */ void mo5673a(C3980d c3980d, Object obj) {
        Boolean bool = (Boolean) obj;
        if (bool == null) {
            c3980d.mo5705f();
        } else {
            c3980d.mo5697a(bool.booleanValue());
        }
    }
}
