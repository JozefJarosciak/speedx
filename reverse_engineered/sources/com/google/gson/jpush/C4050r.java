package com.google.gson.jpush;

import com.google.gson.jpush.internal.C4035s;
import com.google.gson.jpush.p184a.C3972a;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.google.gson.jpush.r */
public final class C4050r {
    /* renamed from: a */
    private C4035s f14585a = C4035s.f14542a;
    /* renamed from: b */
    private ag f14586b = ag.f14286a;
    /* renamed from: c */
    private C3984j f14587c = C3985d.f14345a;
    /* renamed from: d */
    private final Map<Type, C4051s<?>> f14588d = new HashMap();
    /* renamed from: e */
    private final List<am> f14589e = new ArrayList();
    /* renamed from: f */
    private final List<am> f14590f = new ArrayList();
    /* renamed from: g */
    private boolean f14591g;
    /* renamed from: h */
    private String f14592h;
    /* renamed from: i */
    private int f14593i = 2;
    /* renamed from: j */
    private int f14594j = 2;
    /* renamed from: k */
    private boolean f14595k;
    /* renamed from: l */
    private boolean f14596l;
    /* renamed from: m */
    private boolean f14597m = true;
    /* renamed from: n */
    private boolean f14598n;
    /* renamed from: o */
    private boolean f14599o;

    /* renamed from: a */
    public final C4050r m16395a() {
        this.f14585a = this.f14585a.m16361a();
        return this;
    }

    /* renamed from: b */
    public final C4042k m16396b() {
        Object c3974a;
        List arrayList = new ArrayList();
        arrayList.addAll(this.f14589e);
        Collections.reverse(arrayList);
        arrayList.addAll(this.f14590f);
        String str = this.f14592h;
        int i = this.f14593i;
        int i2 = this.f14594j;
        if (str == null || "".equals(str.trim())) {
            if (!(i == 2 || i2 == 2)) {
                c3974a = new C3974a(i, i2);
            }
            return new C4042k(this.f14585a, this.f14587c, this.f14588d, this.f14591g, this.f14595k, this.f14599o, this.f14597m, this.f14598n, this.f14596l, this.f14586b, arrayList);
        }
        c3974a = new C3974a(str);
        arrayList.add(aj.m16092a(C3972a.m16055a(Date.class), c3974a));
        arrayList.add(aj.m16092a(C3972a.m16055a(Timestamp.class), c3974a));
        arrayList.add(aj.m16092a(C3972a.m16055a(java.sql.Date.class), c3974a));
        return new C4042k(this.f14585a, this.f14587c, this.f14588d, this.f14591g, this.f14595k, this.f14599o, this.f14597m, this.f14598n, this.f14596l, this.f14586b, arrayList);
    }
}
