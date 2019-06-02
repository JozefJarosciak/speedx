package com.google.gson.jpush.internal.p186a;

import com.google.gson.jpush.C1483y;
import com.google.gson.jpush.C3975w;
import com.google.gson.jpush.C4042k;
import com.google.gson.jpush.C4052t;
import com.google.gson.jpush.C4054z;
import com.google.gson.jpush.ac;
import com.google.gson.jpush.af;
import com.google.gson.jpush.al;
import com.google.gson.jpush.internal.C3977u;
import com.google.gson.jpush.internal.ae;
import com.google.gson.jpush.internal.ag;
import com.google.gson.jpush.p185b.C3976a;
import com.google.gson.jpush.p185b.C3979c;
import com.google.gson.jpush.p185b.C3980d;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/* renamed from: com.google.gson.jpush.internal.a.m */
final class C4003m<K, V> extends al<Map<K, V>> {
    /* renamed from: z */
    private static final String[] f14405z;
    /* renamed from: a */
    final /* synthetic */ C4002l f14406a;
    /* renamed from: b */
    private final al<K> f14407b;
    /* renamed from: c */
    private final al<V> f14408c;
    /* renamed from: d */
    private final ae<? extends Map<K, V>> f14409d;

    static {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:61)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1723278948.run(Unknown Source)
*/
        /*
        r4 = 1;
        r1 = 0;
        r0 = 2;
        r3 = new java.lang.String[r0];
        r2 = "\u001e=fw.\u0019)b~g\u0011-o!g";
        r0 = -1;
        r5 = r3;
        r6 = r3;
        r3 = r1;
    L_0x000b:
        r2 = r2.toCharArray();
        r7 = r2.length;
        if (r7 > r4) goto L_0x0057;
    L_0x0012:
        r8 = r1;
    L_0x0013:
        r9 = r2;
        r10 = r8;
        r13 = r7;
        r7 = r2;
        r2 = r13;
    L_0x0018:
        r12 = r7[r8];
        r11 = r10 % 5;
        switch(r11) {
            case 0: goto L_0x004b;
            case 1: goto L_0x004e;
            case 2: goto L_0x0051;
            case 3: goto L_0x0054;
            default: goto L_0x001f;
        };
    L_0x001f:
        r11 = 71;
    L_0x0021:
        r11 = r11 ^ r12;
        r11 = (char) r11;
        r7[r8] = r11;
        r8 = r10 + 1;
        if (r2 != 0) goto L_0x002d;
    L_0x0029:
        r7 = r9;
        r10 = r8;
        r8 = r2;
        goto L_0x0018;
    L_0x002d:
        r7 = r2;
        r2 = r9;
    L_0x002f:
        if (r7 > r8) goto L_0x0013;
    L_0x0031:
        r7 = new java.lang.String;
        r7.<init>(r2);
        r2 = r7.intern();
        switch(r0) {
            case 0: goto L_0x0046;
            default: goto L_0x003d;
        };
    L_0x003d:
        r5[r3] = r2;
        r0 = "\u0014=zw";
        r2 = r0;
        r3 = r4;
        r5 = r6;
        r0 = r1;
        goto L_0x000b;
    L_0x0046:
        r5[r3] = r2;
        f14405z = r6;
        return;
    L_0x004b:
        r11 = 122; // 0x7a float:1.71E-43 double:6.03E-322;
        goto L_0x0021;
    L_0x004e:
        r11 = 72;
        goto L_0x0021;
    L_0x0051:
        r11 = 22;
        goto L_0x0021;
    L_0x0054:
        r11 = 27;
        goto L_0x0021;
    L_0x0057:
        r8 = r1;
        goto L_0x002f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.jpush.internal.a.m.<clinit>():void");
    }

    public C4003m(C4002l c4002l, C4042k c4042k, Type type, al<K> alVar, Type type2, al<V> alVar2, ae<? extends Map<K, V>> aeVar) {
        this.f14406a = c4002l;
        this.f14407b = new C4015y(c4042k, alVar, type);
        this.f14408c = new C4015y(c4042k, alVar2, type2);
        this.f14409d = aeVar;
    }

    /* renamed from: a */
    public final /* synthetic */ Object mo5672a(C3976a c3976a) {
        C3979c f = c3976a.mo5683f();
        if (f == C3979c.f14328i) {
            c3976a.mo5687j();
            return null;
        }
        Map map = (Map) this.f14409d.mo5711a();
        Object a;
        if (f == C3979c.f14320a) {
            c3976a.mo5677a();
            while (c3976a.mo5682e()) {
                c3976a.mo5677a();
                a = this.f14407b.mo5672a(c3976a);
                if (map.put(a, this.f14408c.mo5672a(c3976a)) != null) {
                    throw new af(new StringBuilder(f14405z[0]).append(a).toString());
                }
                c3976a.mo5678b();
            }
            c3976a.mo5678b();
            return map;
        }
        c3976a.mo5679c();
        while (c3976a.mo5682e()) {
            C3977u.f14318a.mo5675a(c3976a);
            a = this.f14407b.mo5672a(c3976a);
            if (map.put(a, this.f14408c.mo5672a(c3976a)) != null) {
                throw new af(new StringBuilder(f14405z[0]).append(a).toString());
            }
        }
        c3976a.mo5681d();
        return map;
    }

    /* renamed from: a */
    public final /* synthetic */ void mo5673a(C3980d c3980d, Object obj) {
        int i = 0;
        Map map = (Map) obj;
        if (map == null) {
            c3980d.mo5705f();
        } else if (this.f14406a.f14404b) {
            List arrayList = new ArrayList(map.size());
            List arrayList2 = new ArrayList(map.size());
            int i2 = 0;
            for (Entry entry : map.entrySet()) {
                C3975w a = this.f14407b.m16088a(entry.getKey());
                arrayList.add(a);
                arrayList2.add(entry.getValue());
                int i3 = ((a instanceof C4052t) || (a instanceof C4054z)) ? 1 : 0;
                i2 = i3 | i2;
            }
            if (i2 != 0) {
                c3980d.mo5699b();
                while (i < arrayList.size()) {
                    c3980d.mo5699b();
                    ag.m16328a((C3975w) arrayList.get(i), c3980d);
                    this.f14408c.mo5673a(c3980d, arrayList2.get(i));
                    c3980d.mo5701c();
                    i++;
                }
                c3980d.mo5701c();
                return;
            }
            c3980d.mo5703d();
            while (i < arrayList.size()) {
                String valueOf;
                C3975w c3975w = (C3975w) arrayList.get(i);
                if (c3975w instanceof ac) {
                    ac j = c3975w.m16076j();
                    if (j.m16086k()) {
                        valueOf = String.valueOf(j.mo5666b());
                    } else if (j.m16079a()) {
                        valueOf = Boolean.toString(j.mo5671g());
                    } else if (j.m16087l()) {
                        valueOf = j.mo5667c();
                    } else {
                        throw new AssertionError();
                    }
                } else if (c3975w instanceof C1483y) {
                    valueOf = f14405z[1];
                } else {
                    throw new AssertionError();
                }
                c3980d.mo5696a(valueOf);
                this.f14408c.mo5673a(c3980d, arrayList2.get(i));
                i++;
            }
            c3980d.mo5704e();
        } else {
            c3980d.mo5703d();
            for (Entry entry2 : map.entrySet()) {
                c3980d.mo5696a(String.valueOf(entry2.getKey()));
                this.f14408c.mo5673a(c3980d, entry2.getValue());
            }
            c3980d.mo5704e();
        }
    }
}
