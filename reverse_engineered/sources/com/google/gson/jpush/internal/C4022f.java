package com.google.gson.jpush.internal;

import com.google.gson.jpush.C4051s;
import com.google.gson.jpush.p184a.C3972a;
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

/* renamed from: com.google.gson.jpush.internal.f */
public final class C4022f {
    /* renamed from: a */
    private final Map<Type, C4051s<?>> f14517a;

    public C4022f(Map<Type, C4051s<?>> map) {
        this.f14517a = map;
    }

    /* renamed from: a */
    private <T> ae<T> m16342a(Class<? super T> cls) {
        try {
            Constructor declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            if (!declaredConstructor.isAccessible()) {
                declaredConstructor.setAccessible(true);
            }
            return new C4028l(this, declaredConstructor);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    /* renamed from: a */
    public final <T> ae<T> m16343a(C3972a<T> c3972a) {
        Type b = c3972a.m16058b();
        Class a = c3972a.m16057a();
        C4051s c4051s = (C4051s) this.f14517a.get(b);
        if (c4051s != null) {
            return new C4023g(this, c4051s, b);
        }
        c4051s = (C4051s) this.f14517a.get(a);
        if (c4051s != null) {
            return new C4027k(this, c4051s, b);
        }
        ae<T> a2 = m16342a(a);
        if (a2 != null) {
            return a2;
        }
        a2 = Collection.class.isAssignableFrom(a) ? SortedSet.class.isAssignableFrom(a) ? new C4029m(this) : EnumSet.class.isAssignableFrom(a) ? new C4030n(this, b) : Set.class.isAssignableFrom(a) ? new C4031o(this) : Queue.class.isAssignableFrom(a) ? new C4032p(this) : new C4033q(this) : Map.class.isAssignableFrom(a) ? SortedMap.class.isAssignableFrom(a) ? new C4034r(this) : (!(b instanceof ParameterizedType) || String.class.isAssignableFrom(C3972a.m16056a(((ParameterizedType) b).getActualTypeArguments()[0]).m16057a())) ? new C4025i(this) : new C4024h(this) : null;
        return a2 == null ? new C4026j(this, a, b) : a2;
    }

    public final String toString() {
        return this.f14517a.toString();
    }
}
