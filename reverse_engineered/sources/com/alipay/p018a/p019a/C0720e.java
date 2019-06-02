package com.alipay.p018a.p019a;

import com.alipay.p018a.p020b.C0726a;
import com.alipay.sdk.util.C0880h;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.json.alipay.C5664a;
import org.json.alipay.C5666b;

/* renamed from: com.alipay.a.a.e */
public final class C0720e {
    /* renamed from: a */
    static List<C0714i> f1713a;

    static {
        List arrayList = new ArrayList();
        f1713a = arrayList;
        arrayList.add(new C0725l());
        f1713a.add(new C0719d());
        f1713a.add(new C0718c());
        f1713a.add(new C0723h());
        f1713a.add(new C0724k());
        f1713a.add(new C0717b());
        f1713a.add(new C0716a());
        f1713a.add(new C0722g());
    }

    /* renamed from: a */
    public static final <T> T m2804a(Object obj, Type type) {
        for (C0714i c0714i : f1713a) {
            if (c0714i.mo2400a(C0726a.m2820a(type))) {
                T a = c0714i.mo2399a(obj, type);
                if (a != null) {
                    return a;
                }
            }
        }
        return null;
    }

    /* renamed from: a */
    public static final Object m2805a(String str, Type type) {
        if (str == null || str.length() == 0) {
            return null;
        }
        Object trim = str.trim();
        return (trim.startsWith("[") && trim.endsWith("]")) ? C0720e.m2804a(new C5664a(trim), type) : (trim.startsWith("{") && trim.endsWith(C0880h.f2222d)) ? C0720e.m2804a(new C5666b(trim), type) : C0720e.m2804a(trim, type);
    }
}
