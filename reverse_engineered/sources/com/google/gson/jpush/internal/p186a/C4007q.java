package com.google.gson.jpush.internal.p186a;

import com.google.gson.jpush.C3984j;
import com.google.gson.jpush.C4042k;
import com.google.gson.jpush.al;
import com.google.gson.jpush.am;
import com.google.gson.jpush.annotations.C1480b;
import com.google.gson.jpush.annotations.C1481c;
import com.google.gson.jpush.internal.C4018b;
import com.google.gson.jpush.internal.C4022f;
import com.google.gson.jpush.internal.C4035s;
import com.google.gson.jpush.internal.af;
import com.google.gson.jpush.p184a.C3972a;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* renamed from: com.google.gson.jpush.internal.a.q */
public final class C4007q implements am {
    /* renamed from: z */
    private static final String f14413z;
    /* renamed from: a */
    private final C4022f f14414a;
    /* renamed from: b */
    private final C3984j f14415b;
    /* renamed from: c */
    private final C4035s f14416c;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = "\u0018\u0019\nPuY\u000f\n@9U\b\u0003GpH\u0011\n\u0013Sk2!\u0013Q\u0018\u0003Wj\u0018\u0013\u000e^|\\]";
        r0 = r0.toCharArray();
        r1 = r0.length;
        r2 = 0;
        r3 = 1;
        if (r1 > r3) goto L_0x0027;
    L_0x000b:
        r3 = r0;
        r4 = r2;
        r7 = r1;
        r1 = r0;
        r0 = r7;
    L_0x0010:
        r6 = r1[r2];
        r5 = r4 % 5;
        switch(r5) {
            case 0: goto L_0x0035;
            case 1: goto L_0x0038;
            case 2: goto L_0x003b;
            case 3: goto L_0x003e;
            default: goto L_0x0017;
        };
    L_0x0017:
        r5 = 25;
    L_0x0019:
        r5 = r5 ^ r6;
        r5 = (char) r5;
        r1[r2] = r5;
        r2 = r4 + 1;
        if (r0 != 0) goto L_0x0025;
    L_0x0021:
        r1 = r3;
        r4 = r2;
        r2 = r0;
        goto L_0x0010;
    L_0x0025:
        r1 = r0;
        r0 = r3;
    L_0x0027:
        if (r1 > r2) goto L_0x000b;
    L_0x0029:
        r1 = new java.lang.String;
        r1.<init>(r0);
        r0 = r1.intern();
        f14413z = r0;
        return;
    L_0x0035:
        r5 = 56;
        goto L_0x0019;
    L_0x0038:
        r5 = 125; // 0x7d float:1.75E-43 double:6.2E-322;
        goto L_0x0019;
    L_0x003b:
        r5 = 111; // 0x6f float:1.56E-43 double:5.5E-322;
        goto L_0x0019;
    L_0x003e:
        r5 = 51;
        goto L_0x0019;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.jpush.internal.a.q.<clinit>():void");
    }

    public C4007q(C4022f c4022f, C3984j c3984j, C4035s c4035s) {
        this.f14414a = c4022f;
        this.f14415b = c3984j;
        this.f14416c = c4035s;
    }

    /* renamed from: a */
    static /* synthetic */ al m16291a(C4007q c4007q, C4042k c4042k, Field field, C3972a c3972a) {
        C1480b c1480b = (C1480b) field.getAnnotation(C1480b.class);
        if (c1480b != null) {
            al a = C3997g.m16251a(c4007q.f14414a, c4042k, c3972a, c1480b);
            if (a != null) {
                return a;
            }
        }
        return c4042k.m16380a(c3972a);
    }

    /* renamed from: a */
    private List<String> m16292a(Field field) {
        C3984j c3984j = this.f14415b;
        C1481c c1481c = (C1481c) field.getAnnotation(C1481c.class);
        List<String> linkedList = new LinkedList();
        if (c1481c == null) {
            linkedList.add(c3984j.mo5676a(field));
        } else {
            linkedList.add(c1481c.a());
            for (Object add : c1481c.b()) {
                linkedList.add(add);
            }
        }
        return linkedList;
    }

    /* renamed from: a */
    private Map<String, C4008t> m16293a(C4042k c4042k, C3972a<?> c3972a, Class<?> cls) {
        Map<String, C4008t> linkedHashMap = new LinkedHashMap();
        if (cls.isInterface()) {
            return linkedHashMap;
        }
        Type b = c3972a.m16058b();
        Class a;
        while (a != Object.class) {
            for (Field field : a.getDeclaredFields()) {
                boolean a2 = m16294a(field, true);
                boolean a3 = m16294a(field, false);
                if (a2 || a3) {
                    field.setAccessible(true);
                    Type a4 = C4018b.m16333a(r21.m16058b(), a, field.getGenericType());
                    List a5 = m16292a(field);
                    C4008t c4008t = null;
                    int i = 0;
                    while (i < a5.size()) {
                        String str = (String) a5.get(i);
                        if (i != 0) {
                            a2 = false;
                        }
                        C3972a a6 = C3972a.m16056a(a4);
                        C4008t c4008t2 = (C4008t) linkedHashMap.put(str, new C4009r(this, str, a2, a3, c4042k, field, a6, af.m16325a(a6.m16057a())));
                        if (c4008t != null) {
                            c4008t2 = c4008t;
                        }
                        i++;
                        c4008t = c4008t2;
                    }
                    if (c4008t != null) {
                        throw new IllegalArgumentException(b + f14413z + c4008t.f14417g);
                    }
                }
            }
            C3972a a7 = C3972a.m16056a(C4018b.m16333a(a7.m16058b(), a, a.getGenericSuperclass()));
            a = a7.m16057a();
        }
        return linkedHashMap;
    }

    /* renamed from: a */
    private boolean m16294a(Field field, boolean z) {
        C4035s c4035s = this.f14416c;
        return (c4035s.m16362a(field.getType(), z) || c4035s.m16363a(field, z)) ? false : true;
    }

    /* renamed from: a */
    public final <T> al<T> mo5674a(C4042k c4042k, C3972a<T> c3972a) {
        Class a = c3972a.m16057a();
        return !Object.class.isAssignableFrom(a) ? null : new C4010s(this.f14414a.m16343a((C3972a) c3972a), m16293a(c4042k, c3972a, a));
    }
}
