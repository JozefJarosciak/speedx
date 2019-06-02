package com.alipay.p018a.p019a;

import com.alipay.p018a.p020b.C0726a;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.TreeSet;
import org.json.alipay.C5664a;

/* renamed from: com.alipay.a.a.b */
public final class C0717b implements C0714i, C0715j {
    /* renamed from: a */
    private static Collection<Object> m2794a(Class<?> cls, Type type) {
        if (cls == AbstractCollection.class) {
            return new ArrayList();
        }
        if (cls.isAssignableFrom(HashSet.class)) {
            return new HashSet();
        }
        if (cls.isAssignableFrom(LinkedHashSet.class)) {
            return new LinkedHashSet();
        }
        if (cls.isAssignableFrom(TreeSet.class)) {
            return new TreeSet();
        }
        if (cls.isAssignableFrom(ArrayList.class)) {
            return new ArrayList();
        }
        if (cls.isAssignableFrom(EnumSet.class)) {
            return EnumSet.noneOf(type instanceof ParameterizedType ? ((ParameterizedType) type).getActualTypeArguments()[0] : Object.class);
        }
        try {
            return (Collection) cls.newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("create instane error, class " + cls.getName());
        }
    }

    /* renamed from: a */
    public final Object mo2398a(Object obj) {
        List arrayList = new ArrayList();
        for (Object b : (Iterable) obj) {
            arrayList.add(C0721f.m2807b(b));
        }
        return arrayList;
    }

    /* renamed from: a */
    public final Object mo2399a(Object obj, Type type) {
        int i = 0;
        if (!obj.getClass().equals(C5664a.class)) {
            return null;
        }
        C5664a c5664a = (C5664a) obj;
        Collection a = C0717b.m2794a(C0726a.m2820a(type), type);
        if (type instanceof ParameterizedType) {
            Type type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
            while (i < c5664a.a()) {
                a.add(C0720e.m2804a(c5664a.a(i), type2));
                i++;
            }
            return a;
        }
        throw new IllegalArgumentException("Does not support the implement for generics.");
    }

    /* renamed from: a */
    public final boolean mo2400a(Class<?> cls) {
        return Collection.class.isAssignableFrom(cls);
    }
}
