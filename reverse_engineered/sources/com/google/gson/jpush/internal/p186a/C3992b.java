package com.google.gson.jpush.internal.p186a;

import com.google.gson.jpush.C4042k;
import com.google.gson.jpush.al;
import com.google.gson.jpush.am;
import com.google.gson.jpush.internal.C4018b;
import com.google.gson.jpush.p184a.C3972a;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

/* renamed from: com.google.gson.jpush.internal.a.b */
final class C3992b implements am {
    C3992b() {
    }

    /* renamed from: a */
    public final <T> al<T> mo5674a(C4042k c4042k, C3972a<T> c3972a) {
        Type b = c3972a.m16058b();
        if (!(b instanceof GenericArrayType) && (!(b instanceof Class) || !((Class) b).isArray())) {
            return null;
        }
        b = C4018b.m16339d(b);
        return new C3991a(c4042k, c4042k.m16380a(C3972a.m16056a(b)), C4018b.m16335b(b));
    }
}
