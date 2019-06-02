package com.google.gson.jpush.internal.p186a;

import com.google.gson.jpush.C4042k;
import com.google.gson.jpush.al;
import com.google.gson.jpush.internal.ae;
import com.google.gson.jpush.p185b.C3976a;
import com.google.gson.jpush.p185b.C3979c;
import com.google.gson.jpush.p185b.C3980d;
import java.lang.reflect.Type;
import java.util.Collection;

/* renamed from: com.google.gson.jpush.internal.a.d */
final class C3994d<E> extends al<Collection<E>> {
    /* renamed from: a */
    private final al<E> f14384a;
    /* renamed from: b */
    private final ae<? extends Collection<E>> f14385b;

    public C3994d(C4042k c4042k, Type type, al<E> alVar, ae<? extends Collection<E>> aeVar) {
        this.f14384a = new C4015y(c4042k, alVar, type);
        this.f14385b = aeVar;
    }

    /* renamed from: a */
    public final /* synthetic */ Object mo5672a(C3976a c3976a) {
        if (c3976a.mo5683f() == C3979c.f14328i) {
            c3976a.mo5687j();
            return null;
        }
        Collection collection = (Collection) this.f14385b.mo5711a();
        c3976a.mo5677a();
        while (c3976a.mo5682e()) {
            collection.add(this.f14384a.mo5672a(c3976a));
        }
        c3976a.mo5678b();
        return collection;
    }

    /* renamed from: a */
    public final /* synthetic */ void mo5673a(C3980d c3980d, Object obj) {
        Collection<Object> collection = (Collection) obj;
        if (collection == null) {
            c3980d.mo5705f();
            return;
        }
        c3980d.mo5699b();
        for (Object a : collection) {
            this.f14384a.mo5673a(c3980d, a);
        }
        c3980d.mo5701c();
    }
}
