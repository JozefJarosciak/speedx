package com.google.gson.jpush.internal.p186a;

import com.google.gson.jpush.C4042k;
import com.google.gson.jpush.al;
import com.google.gson.jpush.am;
import com.google.gson.jpush.internal.C4018b;
import com.google.gson.jpush.internal.C4022f;
import com.google.gson.jpush.p184a.C3972a;
import java.lang.reflect.Type;
import java.util.Map;

/* renamed from: com.google.gson.jpush.internal.a.l */
public final class C4002l implements am {
    /* renamed from: a */
    private final C4022f f14403a;
    /* renamed from: b */
    private final boolean f14404b;

    public C4002l(C4022f c4022f, boolean z) {
        this.f14403a = c4022f;
        this.f14404b = z;
    }

    /* renamed from: a */
    public final <T> al<T> mo5674a(C4042k c4042k, C3972a<T> c3972a) {
        Type b = c3972a.m16058b();
        if (!Map.class.isAssignableFrom(c3972a.m16057a())) {
            return null;
        }
        Type[] b2 = C4018b.m16337b(b, C4018b.m16335b(b));
        b = b2[0];
        al a = (b == Boolean.TYPE || b == Boolean.class) ? C4016z.f14460f : c4042k.m16380a(C3972a.m16056a(b));
        return new C4003m(this, c4042k, b2[0], a, b2[1], c4042k.m16380a(C3972a.m16056a(b2[1])), this.f14403a.m16343a((C3972a) c3972a));
    }
}
