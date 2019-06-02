package com.google.gson.jpush.internal;

import java.lang.reflect.Method;

final class aj extends UnsafeAllocator {
    /* renamed from: a */
    final /* synthetic */ Method f14500a;
    /* renamed from: b */
    final /* synthetic */ Object f14501b;

    aj(Method method, Object obj) {
        this.f14500a = method;
        this.f14501b = obj;
    }

    public final <T> T newInstance(Class<T> cls) {
        return this.f14500a.invoke(this.f14501b, new Object[]{cls});
    }
}
