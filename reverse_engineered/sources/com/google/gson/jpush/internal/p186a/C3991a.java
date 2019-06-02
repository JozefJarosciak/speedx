package com.google.gson.jpush.internal.p186a;

import com.google.gson.jpush.C4042k;
import com.google.gson.jpush.al;
import com.google.gson.jpush.am;
import com.google.gson.jpush.p185b.C3976a;
import com.google.gson.jpush.p185b.C3979c;
import com.google.gson.jpush.p185b.C3980d;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.google.gson.jpush.internal.a.a */
public final class C3991a<E> extends al<Object> {
    /* renamed from: a */
    public static final am f14352a = new C3992b();
    /* renamed from: b */
    private final Class<E> f14353b;
    /* renamed from: c */
    private final al<E> f14354c;

    public C3991a(C4042k c4042k, al<E> alVar, Class<E> cls) {
        this.f14354c = new C4015y(c4042k, alVar, cls);
        this.f14353b = cls;
    }

    /* renamed from: a */
    public final Object mo5672a(C3976a c3976a) {
        if (c3976a.mo5683f() == C3979c.f14328i) {
            c3976a.mo5687j();
            return null;
        }
        List arrayList = new ArrayList();
        c3976a.mo5677a();
        while (c3976a.mo5682e()) {
            arrayList.add(this.f14354c.mo5672a(c3976a));
        }
        c3976a.mo5678b();
        Object newInstance = Array.newInstance(this.f14353b, arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            Array.set(newInstance, i, arrayList.get(i));
        }
        return newInstance;
    }

    /* renamed from: a */
    public final void mo5673a(C3980d c3980d, Object obj) {
        if (obj == null) {
            c3980d.mo5705f();
            return;
        }
        c3980d.mo5699b();
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            this.f14354c.mo5673a(c3980d, Array.get(obj, i));
        }
        c3980d.mo5701c();
    }
}
