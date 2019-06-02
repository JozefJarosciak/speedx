package com.google.gson.jpush.internal.p186a;

import com.google.gson.jpush.C4042k;
import com.google.gson.jpush.al;
import com.google.gson.jpush.am;
import com.google.gson.jpush.internal.C4018b;
import com.google.gson.jpush.internal.C4022f;
import com.google.gson.jpush.p184a.C3972a;
import java.lang.reflect.Type;
import java.util.Collection;

/* renamed from: com.google.gson.jpush.internal.a.c */
public final class C3993c implements am {
    /* renamed from: a */
    private final C4022f f14383a;

    public C3993c(C4022f c4022f) {
        this.f14383a = c4022f;
    }

    /* renamed from: a */
    public final <T> al<T> mo5674a(C4042k c4042k, C3972a<T> c3972a) {
        Type b = c3972a.m16058b();
        Class a = c3972a.m16057a();
        if (!Collection.class.isAssignableFrom(a)) {
            return null;
        }
        Type a2 = C4018b.m16331a(b, a);
        return new C3994d(c4042k, a2, c4042k.m16380a(C3972a.m16056a(a2)), this.f14383a.m16343a((C3972a) c3972a));
    }
}
