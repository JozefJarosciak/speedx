package com.google.gson.jpush.internal.p186a;

import com.google.gson.jpush.C4042k;
import com.google.gson.jpush.al;
import com.google.gson.jpush.p184a.C3972a;
import com.google.gson.jpush.p185b.C3976a;
import com.google.gson.jpush.p185b.C3980d;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/* renamed from: com.google.gson.jpush.internal.a.y */
final class C4015y<T> extends al<T> {
    /* renamed from: a */
    private final C4042k f14434a;
    /* renamed from: b */
    private final al<T> f14435b;
    /* renamed from: c */
    private final Type f14436c;

    C4015y(C4042k c4042k, al<T> alVar, Type type) {
        this.f14434a = c4042k;
        this.f14435b = alVar;
        this.f14436c = type;
    }

    /* renamed from: a */
    public final T mo5672a(C3976a c3976a) {
        return this.f14435b.mo5672a(c3976a);
    }

    /* renamed from: a */
    public final void mo5673a(C3980d c3980d, T t) {
        al a;
        al alVar = this.f14435b;
        Type type = this.f14436c;
        if (t != null && (type == Object.class || (type instanceof TypeVariable) || (type instanceof Class))) {
            type = t.getClass();
        }
        if (type != this.f14436c) {
            a = this.f14434a.m16380a(C3972a.m16056a(type));
            if ((a instanceof C4010s) && !(this.f14435b instanceof C4010s)) {
                a = this.f14435b;
            }
        } else {
            a = alVar;
        }
        a.mo5673a(c3980d, t);
    }
}
