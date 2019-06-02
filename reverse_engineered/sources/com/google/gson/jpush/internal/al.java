package com.google.gson.jpush.internal;

import java.lang.reflect.Method;

final class al extends UnsafeAllocator {
    /* renamed from: a */
    final /* synthetic */ Method f14504a;

    al(Method method) {
        this.f14504a = method;
    }

    public final <T> T newInstance(Class<T> cls) {
        return this.f14504a.invoke(null, new Object[]{cls, Object.class});
    }
}
