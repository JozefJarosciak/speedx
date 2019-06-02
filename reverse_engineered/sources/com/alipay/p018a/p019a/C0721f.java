package com.alipay.p018a.p019a;

import com.alipay.p018a.p020b.C0726a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.json.alipay.C5664a;
import org.json.alipay.C5666b;

/* renamed from: com.alipay.a.a.f */
public final class C0721f {
    /* renamed from: a */
    private static List<C0715j> f1714a;

    static {
        List arrayList = new ArrayList();
        f1714a = arrayList;
        arrayList.add(new C0725l());
        f1714a.add(new C0719d());
        f1714a.add(new C0718c());
        f1714a.add(new C0723h());
        f1714a.add(new C0717b());
        f1714a.add(new C0716a());
        f1714a.add(new C0722g());
    }

    /* renamed from: a */
    public static String m2806a(Object obj) {
        if (obj == null) {
            return null;
        }
        Object b = C0721f.m2807b(obj);
        if (C0726a.m2821a(b.getClass())) {
            return C5666b.c(b.toString());
        }
        if (Collection.class.isAssignableFrom(b.getClass())) {
            return new C5664a((List) b).toString();
        }
        if (Map.class.isAssignableFrom(b.getClass())) {
            return new C5666b((Map) b).toString();
        }
        throw new IllegalArgumentException("Unsupported Class : " + b.getClass());
    }

    /* renamed from: b */
    public static Object m2807b(Object obj) {
        if (obj == null) {
            return null;
        }
        for (C0715j c0715j : f1714a) {
            if (c0715j.mo2400a(obj.getClass())) {
                Object a = c0715j.mo2398a(obj);
                if (a != null) {
                    return a;
                }
            }
        }
        throw new IllegalArgumentException("Unsupported Class : " + obj.getClass());
    }
}
