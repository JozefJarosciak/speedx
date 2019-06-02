package com.beastbikes.android.sphere.restful;

import android.content.Context;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/* compiled from: ServiceStubFactory */
/* renamed from: com.beastbikes.android.sphere.restful.e */
public class C1770e {
    /* renamed from: a */
    public Context f8080a;

    public C1770e(Context context) {
        this.f8080a = context;
    }

    /* renamed from: a */
    public <T extends C1600d> T m9396a(Class<T> cls, String str, Map<String, String> map, C1771b c1771b) {
        Map hashMap = new HashMap();
        for (Method method : cls.getMethods()) {
            hashMap.put(method, new C2544f(this.f8080a, cls, method, str, map, c1771b));
        }
        return (C1600d) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new C2545g(cls, hashMap));
    }
}
