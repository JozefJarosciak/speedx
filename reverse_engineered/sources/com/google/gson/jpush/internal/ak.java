package com.google.gson.jpush.internal;

import java.lang.reflect.Method;

final class ak extends UnsafeAllocator {
    /* renamed from: a */
    final /* synthetic */ Method f14502a;
    /* renamed from: b */
    final /* synthetic */ int f14503b;

    ak(Method method, int i) {
        this.f14502a = method;
        this.f14503b = i;
    }

    public final <T> T newInstance(Class<T> cls) {
        return this.f14502a.invoke(null, new Object[]{cls, Integer.valueOf(this.f14503b)});
    }
}
