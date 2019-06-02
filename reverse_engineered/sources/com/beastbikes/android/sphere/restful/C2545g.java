package com.beastbikes.android.sphere.restful;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/* compiled from: ServiceStubProxy */
/* renamed from: com.beastbikes.android.sphere.restful.g */
public class C2545g implements InvocationHandler {
    /* renamed from: a */
    private final Class<?> f12020a;
    /* renamed from: b */
    private final Map<Method, C2538a> f12021b;

    C2545g(Class<?> cls, Map<Method, C2538a> map) {
        this.f12020a = cls;
        this.f12021b = map;
    }

    public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
        C2538a c2538a = (C2538a) this.f12021b.get(method);
        if (c2538a != null) {
            return c2538a.mo3522a(objArr);
        }
        String name = method.getName();
        if ("equals".equals(name) && objArr != null && 1 == objArr.length) {
            return Boolean.valueOf(equals(obj));
        }
        if ("hashCode".equals(name) && (objArr == null || objArr.length == 0)) {
            return Integer.valueOf(hashCode());
        }
        if ("toString".equals(name) && (objArr == null || objArr.length == 0)) {
            return toString();
        }
        throw new NoSuchMethodException(method.toGenericString());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C2545g)) {
            return false;
        }
        if (((C2545g) obj).f12020a != this.f12020a) {
            return false;
        }
        return super.equals(obj);
    }

    public int hashCode() {
        return this.f12020a.hashCode();
    }

    public String toString() {
        return "Proxy for " + this.f12020a.getName();
    }
}
