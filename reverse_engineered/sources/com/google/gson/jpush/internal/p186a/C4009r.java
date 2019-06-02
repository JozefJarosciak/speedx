package com.google.gson.jpush.internal.p186a;

import com.google.gson.jpush.C4042k;
import com.google.gson.jpush.al;
import com.google.gson.jpush.p184a.C3972a;
import com.google.gson.jpush.p185b.C3976a;
import com.google.gson.jpush.p185b.C3980d;
import java.lang.reflect.Field;

/* renamed from: com.google.gson.jpush.internal.a.r */
final class C4009r extends C4008t {
    /* renamed from: a */
    final al<?> f14420a = C4007q.m16291a(this.f14425f, this.f14421b, this.f14422c, this.f14423d);
    /* renamed from: b */
    final /* synthetic */ C4042k f14421b;
    /* renamed from: c */
    final /* synthetic */ Field f14422c;
    /* renamed from: d */
    final /* synthetic */ C3972a f14423d;
    /* renamed from: e */
    final /* synthetic */ boolean f14424e;
    /* renamed from: f */
    final /* synthetic */ C4007q f14425f;

    C4009r(C4007q c4007q, String str, boolean z, boolean z2, C4042k c4042k, Field field, C3972a c3972a, boolean z3) {
        this.f14425f = c4007q;
        this.f14421b = c4042k;
        this.f14422c = field;
        this.f14423d = c3972a;
        this.f14424e = z3;
        super(str, z, z2);
    }

    /* renamed from: a */
    final void mo5707a(C3976a c3976a, Object obj) {
        Object a = this.f14420a.mo5672a(c3976a);
        if (a != null || !this.f14424e) {
            this.f14422c.set(obj, a);
        }
    }

    /* renamed from: a */
    final void mo5708a(C3980d c3980d, Object obj) {
        new C4015y(this.f14421b, this.f14420a, this.f14423d.m16058b()).mo5673a(c3980d, this.f14422c.get(obj));
    }

    /* renamed from: a */
    public final boolean mo5709a(Object obj) {
        return this.h && this.f14422c.get(obj) != obj;
    }
}
