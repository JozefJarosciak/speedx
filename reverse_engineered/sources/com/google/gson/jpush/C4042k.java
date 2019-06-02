package com.google.gson.jpush;

import com.alipay.sdk.util.C0880h;
import com.google.gson.jpush.internal.C4022f;
import com.google.gson.jpush.internal.C4035s;
import com.google.gson.jpush.internal.af;
import com.google.gson.jpush.internal.ag;
import com.google.gson.jpush.internal.p186a.C3991a;
import com.google.gson.jpush.internal.p186a.C3993c;
import com.google.gson.jpush.internal.p186a.C3995e;
import com.google.gson.jpush.internal.p186a.C3997g;
import com.google.gson.jpush.internal.p186a.C4002l;
import com.google.gson.jpush.internal.p186a.C4004n;
import com.google.gson.jpush.internal.p186a.C4007q;
import com.google.gson.jpush.internal.p186a.C4011u;
import com.google.gson.jpush.internal.p186a.C4013w;
import com.google.gson.jpush.internal.p186a.C4016z;
import com.google.gson.jpush.p184a.C3972a;
import com.google.gson.jpush.p185b.C3976a;
import com.google.gson.jpush.p185b.C3979c;
import com.google.gson.jpush.p185b.C3980d;
import java.io.StringReader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.google.gson.jpush.k */
public final class C4042k {
    /* renamed from: z */
    private static final String[] f14568z;
    /* renamed from: a */
    final C4043u f14569a;
    /* renamed from: b */
    final ad f14570b;
    /* renamed from: c */
    private final ThreadLocal<Map<C3972a<?>, C4049q<?>>> f14571c;
    /* renamed from: d */
    private final Map<C3972a<?>, al<?>> f14572d;
    /* renamed from: e */
    private final List<am> f14573e;
    /* renamed from: f */
    private final C4022f f14574f;
    /* renamed from: g */
    private final boolean f14575g;
    /* renamed from: h */
    private final boolean f14576h;
    /* renamed from: i */
    private final boolean f14577i;
    /* renamed from: j */
    private final boolean f14578j;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 9;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "\u0007z\f\u0014G\u0013r\n\u0013\u0012";
        r0 = -1;
        r4 = r3;
    L_0x0009:
        r1 = r1.toCharArray();
        r5 = r1.length;
        r6 = 0;
        r7 = 1;
        if (r5 > r7) goto L_0x002e;
    L_0x0012:
        r7 = r1;
        r8 = r6;
        r11 = r5;
        r5 = r1;
        r1 = r11;
    L_0x0017:
        r10 = r5[r6];
        r9 = r8 % 5;
        switch(r9) {
            case 0: goto L_0x0082;
            case 1: goto L_0x0085;
            case 2: goto L_0x0088;
            case 3: goto L_0x008b;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 40;
    L_0x0020:
        r9 = r9 ^ r10;
        r9 = (char) r9;
        r5[r6] = r9;
        r6 = r8 + 1;
        if (r1 != 0) goto L_0x002c;
    L_0x0028:
        r5 = r7;
        r8 = r6;
        r6 = r1;
        goto L_0x0017;
    L_0x002c:
        r5 = r1;
        r1 = r7;
    L_0x002e:
        if (r5 > r6) goto L_0x0012;
    L_0x0030:
        r5 = new java.lang.String;
        r5.<init>(r1);
        r1 = r5.intern();
        switch(r0) {
            case 0: goto L_0x0044;
            case 1: goto L_0x004c;
            case 2: goto L_0x0054;
            case 3: goto L_0x005c;
            case 4: goto L_0x0064;
            case 5: goto L_0x006c;
            case 6: goto L_0x0074;
            case 7: goto L_0x007d;
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = "Mr\u0001\u0013\\\u0000u\f\u0005k\u0013~\u000e\u0014G\u0013hU";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "\u001ah\n\u0012A\u0000w\u0006\u001aM/n\u0003\f[[";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "&H .\b\u0002z\u0001\u000eG\u0015;\u001c\u0005Z\bz\u0003\tR\u0004;";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "+H .\b\u0005t\f\u0015E\u0004u\u001b@_\u0000hO\u000eG\u0015;\t\u0015D\rbO\u0003G\u000fh\u001a\rM\u00055";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "&H .\b\u0002z\u0001\u000eG\u0015;\u0007\u0001F\u0005w\n@";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "A;";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "HF\u0012G\"";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = "Ar\u001c@F\u000eoO\u0001\b\u0017z\u0003\tLA\u0000\u0015J\r~O\u0016I\rn\n@I\u0012;\u001f\u0005ZAQ</fAh\u001f\u0005K\b}\u0006\u0003I\u0015r\u0000\u000e\u0006AO\u0000@G\u0017~\u001d\u0012A\u0005~O\u0014@\bhO\u0002M\tz\u0019\tG\u00137O\u0015[\u0004;(\u0013G\u000fY\u001a\tD\u0005~\u001dN[\u0004i\u0006\u0001D\ba\n3X\u0004x\u0006\u0001D'w\u0000\u0001\\\bu\b0G\bu\u001b6I\rn\n\u0013\u0000H;\u0002\u0005\\\tt\u000bN";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        f14568z = r4;
        return;
    L_0x0082:
        r9 = 97;
        goto L_0x0020;
    L_0x0085:
        r9 = 27;
        goto L_0x0020;
    L_0x0088:
        r9 = 111; // 0x6f float:1.56E-43 double:5.5E-322;
        goto L_0x0020;
    L_0x008b:
        r9 = 96;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.jpush.k.<clinit>():void");
    }

    public C4042k() {
        this(C4035s.f14542a, C3985d.f14345a, Collections.emptyMap(), false, false, false, true, false, false, ag.f14286a, Collections.emptyList());
    }

    C4042k(C4035s c4035s, C3984j c3984j, Map<Type, C4051s<?>> map, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, ag agVar, List<am> list) {
        this.f14571c = new ThreadLocal();
        this.f14572d = Collections.synchronizedMap(new HashMap());
        this.f14569a = new C4044l(this);
        this.f14570b = new C4045m(this);
        this.f14574f = new C4022f(map);
        this.f14575g = z;
        this.f14577i = z3;
        this.f14576h = z4;
        this.f14578j = z5;
        List arrayList = new ArrayList();
        arrayList.add(C4016z.f14453Q);
        arrayList.add(C4004n.f14410a);
        arrayList.add(c4035s);
        arrayList.addAll(list);
        arrayList.add(C4016z.f14478x);
        arrayList.add(C4016z.f14467m);
        arrayList.add(C4016z.f14461g);
        arrayList.add(C4016z.f14463i);
        arrayList.add(C4016z.f14465k);
        arrayList.add(C4016z.m16317a(Long.TYPE, Long.class, agVar == ag.f14286a ? C4016z.f14468n : new C4048p(this)));
        arrayList.add(C4016z.m16317a(Double.TYPE, Double.class, z6 ? C4016z.f14470p : new C4046n(this)));
        arrayList.add(C4016z.m16317a(Float.TYPE, Float.class, z6 ? C4016z.f14469o : new C4047o(this)));
        arrayList.add(C4016z.f14472r);
        arrayList.add(C4016z.f14474t);
        arrayList.add(C4016z.f14480z);
        arrayList.add(C4016z.f14438B);
        arrayList.add(C4016z.m16316a(BigDecimal.class, C4016z.f14476v));
        arrayList.add(C4016z.m16316a(BigInteger.class, C4016z.f14477w));
        arrayList.add(C4016z.f14440D);
        arrayList.add(C4016z.f14442F);
        arrayList.add(C4016z.f14446J);
        arrayList.add(C4016z.f14451O);
        arrayList.add(C4016z.f14444H);
        arrayList.add(C4016z.f14458d);
        arrayList.add(C3995e.f14386a);
        arrayList.add(C4016z.f14449M);
        arrayList.add(C4013w.f14431a);
        arrayList.add(C4011u.f14428a);
        arrayList.add(C4016z.f14447K);
        arrayList.add(C3991a.f14352a);
        arrayList.add(C4016z.f14456b);
        arrayList.add(new C3993c(this.f14574f));
        arrayList.add(new C4002l(this.f14574f, z2));
        arrayList.add(new C3997g(this.f14574f));
        arrayList.add(C4016z.f14454R);
        arrayList.add(new C4007q(this.f14574f, c3984j, c4035s));
        this.f14573e = Collections.unmodifiableList(arrayList);
    }

    /* renamed from: a */
    private C3980d m16376a(Writer writer) {
        if (this.f14577i) {
            writer.write(f14568z[7]);
        }
        C3980d c3980d = new C3980d(writer);
        if (this.f14578j) {
            c3980d.m16153c(f14568z[6]);
        }
        c3980d.m16156d(this.f14575g);
        return c3980d;
    }

    /* renamed from: a */
    private <T> T m16377a(C3976a c3976a, Type type) {
        boolean z = true;
        boolean p = c3976a.m16132p();
        c3976a.m16118a(true);
        try {
            c3976a.mo5683f();
            z = false;
            T a = m16380a(C3972a.m16056a(type)).mo5672a(c3976a);
            c3976a.m16118a(p);
            return a;
        } catch (Throwable e) {
            if (z) {
                c3976a.m16118a(p);
                return null;
            }
            throw new af(e);
        } catch (Throwable e2) {
            throw new af(e2);
        } catch (Throwable e22) {
            throw new af(e22);
        } catch (Throwable th) {
            c3976a.m16118a(p);
        }
    }

    /* renamed from: a */
    static /* synthetic */ void m16378a(C4042k c4042k, double d) {
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            throw new IllegalArgumentException(d + f14568z[8]);
        }
    }

    /* renamed from: a */
    private static void m16379a(Object obj, C3976a c3976a) {
        if (obj != null) {
            try {
                if (c3976a.mo5683f() != C3979c.f14329j) {
                    throw new C4053x(f14568z[4]);
                }
            } catch (Throwable e) {
                throw new af(e);
            } catch (Throwable e2) {
                throw new C4053x(e2);
            }
        }
    }

    /* renamed from: a */
    public final <T> al<T> m16380a(C3972a<T> c3972a) {
        Map map;
        al<T> alVar = (al) this.f14572d.get(c3972a);
        if (alVar == null) {
            Map map2 = (Map) this.f14571c.get();
            Object obj = null;
            if (map2 == null) {
                HashMap hashMap = new HashMap();
                this.f14571c.set(hashMap);
                map = hashMap;
                obj = 1;
            } else {
                map = map2;
            }
            C4049q c4049q = (C4049q) map.get(c3972a);
            if (c4049q == null) {
                try {
                    C4049q c4049q2 = new C4049q();
                    map.put(c3972a, c4049q2);
                    for (am a : this.f14573e) {
                        al a2 = a.mo5674a(this, c3972a);
                        if (a2 != null) {
                            c4049q2.m16393a(a2);
                            this.f14572d.put(c3972a, a2);
                            map.remove(c3972a);
                            if (obj != null) {
                                this.f14571c.remove();
                            }
                        }
                    }
                    throw new IllegalArgumentException(new StringBuilder(f14568z[5]).append(c3972a).toString());
                } catch (Throwable th) {
                    map.remove(c3972a);
                    if (obj != null) {
                        this.f14571c.remove();
                    }
                }
            }
        }
        return alVar;
    }

    /* renamed from: a */
    public final <T> al<T> m16381a(am amVar, C3972a<T> c3972a) {
        Object obj = null;
        if (!this.f14573e.contains(amVar)) {
            obj = 1;
        }
        Object obj2 = obj;
        for (am amVar2 : this.f14573e) {
            if (obj2 != null) {
                al<T> a = amVar2.mo5674a(this, c3972a);
                if (a != null) {
                    return a;
                }
            } else if (amVar2 == amVar) {
                obj2 = 1;
            }
        }
        throw new IllegalArgumentException(new StringBuilder(f14568z[3]).append(c3972a).toString());
    }

    /* renamed from: a */
    public final <T> al<T> m16382a(Class<T> cls) {
        return m16380a(C3972a.m16055a((Class) cls));
    }

    /* renamed from: a */
    public final <T> T m16383a(String str, Class<T> cls) {
        Object obj;
        if (str == null) {
            obj = null;
        } else {
            C3976a c3976a = new C3976a(new StringReader(str));
            obj = m16377a(c3976a, (Type) cls);
            C4042k.m16379a(obj, c3976a);
        }
        return af.m16323a((Class) cls).cast(obj);
    }

    /* renamed from: a */
    public final void m16384a(C3975w c3975w, Appendable appendable) {
        try {
            C3980d a = m16376a(ag.m16327a(appendable));
            boolean g = a.m16159g();
            a.m16151b(true);
            boolean h = a.m16160h();
            a.m16154c(this.f14576h);
            boolean i = a.m16161i();
            a.m16156d(this.f14575g);
            try {
                ag.m16328a(c3975w, a);
                a.m16151b(g);
                a.m16154c(h);
                a.m16156d(i);
            } catch (Throwable e) {
                throw new C4053x(e);
            } catch (Throwable th) {
                a.m16151b(g);
                a.m16154c(h);
                a.m16156d(i);
            }
        } catch (Throwable e2) {
            throw new RuntimeException(e2);
        }
    }

    /* renamed from: a */
    public final void m16385a(Object obj, Type type, Appendable appendable) {
        try {
            C3980d a = m16376a(ag.m16327a(appendable));
            al a2 = m16380a(C3972a.m16056a(type));
            boolean g = a.m16159g();
            a.m16151b(true);
            boolean h = a.m16160h();
            a.m16154c(this.f14576h);
            boolean i = a.m16161i();
            a.m16156d(this.f14575g);
            try {
                a2.mo5673a(a, obj);
                a.m16151b(g);
                a.m16154c(h);
                a.m16156d(i);
            } catch (Throwable e) {
                throw new C4053x(e);
            } catch (Throwable th) {
                a.m16151b(g);
                a.m16154c(h);
                a.m16156d(i);
            }
        } catch (Throwable e2) {
            throw new C4053x(e2);
        }
    }

    public final String toString() {
        return new StringBuilder(f14568z[2]).append(this.f14575g).append(f14568z[0]).append(this.f14573e).append(f14568z[1]).append(this.f14574f).append(C0880h.f2222d).toString();
    }
}
