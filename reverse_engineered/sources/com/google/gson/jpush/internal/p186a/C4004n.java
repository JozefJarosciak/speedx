package com.google.gson.jpush.internal.p186a;

import com.google.gson.jpush.C4042k;
import com.google.gson.jpush.al;
import com.google.gson.jpush.am;
import com.google.gson.jpush.internal.C4038w;
import com.google.gson.jpush.p185b.C3976a;
import com.google.gson.jpush.p185b.C3980d;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* renamed from: com.google.gson.jpush.internal.a.n */
public final class C4004n extends al<Object> {
    /* renamed from: a */
    public static final am f14410a = new C4005o();
    /* renamed from: b */
    private final C4042k f14411b;

    private C4004n(C4042k c4042k) {
        this.f14411b = c4042k;
    }

    /* renamed from: a */
    public final Object mo5672a(C3976a c3976a) {
        switch (C4006p.f14412a[c3976a.mo5683f().ordinal()]) {
            case 1:
                List arrayList = new ArrayList();
                c3976a.mo5677a();
                while (c3976a.mo5682e()) {
                    arrayList.add(mo5672a(c3976a));
                }
                c3976a.mo5678b();
                return arrayList;
            case 2:
                Map c4038w = new C4038w();
                c3976a.mo5679c();
                while (c3976a.mo5682e()) {
                    c4038w.put(c3976a.mo5684g(), mo5672a(c3976a));
                }
                c3976a.mo5681d();
                return c4038w;
            case 3:
                return c3976a.mo5685h();
            case 4:
                return Double.valueOf(c3976a.mo5688k());
            case 5:
                return Boolean.valueOf(c3976a.mo5686i());
            case 6:
                c3976a.mo5687j();
                return null;
            default:
                throw new IllegalStateException();
        }
    }

    /* renamed from: a */
    public final void mo5673a(C3980d c3980d, Object obj) {
        if (obj == null) {
            c3980d.mo5705f();
            return;
        }
        al a = this.f14411b.m16382a(obj.getClass());
        if (a instanceof C4004n) {
            c3980d.mo5703d();
            c3980d.mo5704e();
            return;
        }
        a.mo5673a(c3980d, obj);
    }
}
