package com.google.gson.jpush.internal.p186a;

import com.google.gson.jpush.al;
import com.google.gson.jpush.annotations.C1481c;
import com.google.gson.jpush.p185b.C3976a;
import com.google.gson.jpush.p185b.C3979c;
import com.google.gson.jpush.p185b.C3980d;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.google.gson.jpush.internal.a.bg */
final class bg<T extends Enum<T>> extends al<T> {
    /* renamed from: a */
    private final Map<String, T> f14381a = new HashMap();
    /* renamed from: b */
    private final Map<T, String> f14382b = new HashMap();

    public bg(Class<T> cls) {
        try {
            for (Enum enumR : (Enum[]) cls.getEnumConstants()) {
                String name = enumR.name();
                C1481c c1481c = (C1481c) cls.getField(name).getAnnotation(C1481c.class);
                if (c1481c != null) {
                    name = c1481c.a();
                    for (Object put : c1481c.b()) {
                        this.f14381a.put(put, enumR);
                    }
                }
                String str = name;
                this.f14381a.put(str, enumR);
                this.f14382b.put(enumR, str);
            }
        } catch (NoSuchFieldException e) {
            throw new AssertionError();
        }
    }

    /* renamed from: a */
    public final /* synthetic */ Object mo5672a(C3976a c3976a) {
        if (c3976a.mo5683f() != C3979c.f14328i) {
            return (Enum) this.f14381a.get(c3976a.mo5685h());
        }
        c3976a.mo5687j();
        return null;
    }

    /* renamed from: a */
    public final /* synthetic */ void mo5673a(C3980d c3980d, Object obj) {
        Enum enumR = (Enum) obj;
        c3980d.mo5700b(enumR == null ? null : (String) this.f14382b.get(enumR));
    }
}
