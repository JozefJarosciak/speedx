package com.google.gson.jpush;

import com.google.gson.jpush.internal.C4017a;
import com.google.gson.jpush.p184a.C3972a;

final class ak implements am {
    /* renamed from: a */
    private final C3972a<?> f14296a;
    /* renamed from: b */
    private final boolean f14297b;
    /* renamed from: c */
    private final Class<?> f14298c;
    /* renamed from: d */
    private final ae<?> f14299d;
    /* renamed from: e */
    private final C3973v<?> f14300e;

    private ak(Object obj, C3972a<?> c3972a, boolean z, Class<?> cls) {
        this.f14299d = obj instanceof ae ? (ae) obj : null;
        this.f14300e = obj instanceof C3973v ? (C3973v) obj : null;
        boolean z2 = (this.f14299d == null && this.f14300e == null) ? false : true;
        C4017a.m16320a(z2);
        this.f14296a = c3972a;
        this.f14297b = z;
        this.f14298c = cls;
    }

    /* renamed from: a */
    public final <T> al<T> mo5674a(C4042k c4042k, C3972a<T> c3972a) {
        boolean isAssignableFrom = this.f14296a != null ? this.f14296a.equals(c3972a) || (this.f14297b && this.f14296a.m16058b() == c3972a.m16057a()) : this.f14298c.isAssignableFrom(c3972a.m16057a());
        return isAssignableFrom ? new aj(this.f14299d, this.f14300e, c4042k, c3972a, this) : null;
    }
}
