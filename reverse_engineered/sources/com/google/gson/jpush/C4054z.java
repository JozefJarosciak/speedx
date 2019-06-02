package com.google.gson.jpush;

import com.google.gson.jpush.internal.C4038w;
import java.util.Map.Entry;
import java.util.Set;

/* renamed from: com.google.gson.jpush.z */
public final class C4054z extends C3975w {
    /* renamed from: a */
    private final C4038w<String, C3975w> f14601a = new C4038w();

    /* renamed from: a */
    public final Set<Entry<String, C3975w>> m16407a() {
        return this.f14601a.entrySet();
    }

    /* renamed from: a */
    public final void m16408a(String str, C3975w c3975w) {
        Object obj;
        if (c3975w == null) {
            obj = C1483y.f7007a;
        }
        this.f14601a.put(str, obj);
    }

    /* renamed from: a */
    public final boolean m16409a(String str) {
        return this.f14601a.containsKey(str);
    }

    /* renamed from: b */
    public final C3975w m16410b(String str) {
        return (C3975w) this.f14601a.get(str);
    }

    public final boolean equals(Object obj) {
        return obj == this || ((obj instanceof C4054z) && ((C4054z) obj).f14601a.equals(this.f14601a));
    }

    public final int hashCode() {
        return this.f14601a.hashCode();
    }
}
