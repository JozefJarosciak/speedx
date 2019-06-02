package com.google.gson.jpush.internal.p186a;

import com.google.gson.jpush.C4053x;
import com.google.gson.jpush.al;
import com.google.gson.jpush.p185b.C3976a;
import com.google.gson.jpush.p185b.C3979c;
import com.google.gson.jpush.p185b.C3980d;
import java.net.URI;

/* renamed from: com.google.gson.jpush.internal.a.ak */
final class ak extends al<URI> {
    /* renamed from: z */
    private static final String f14359z;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = "\fn\bd";
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
        r5 = 49;
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
        f14359z = r0;
        return;
    L_0x0035:
        r5 = 98;
        goto L_0x0019;
    L_0x0038:
        r5 = 27;
        goto L_0x0019;
    L_0x003b:
        r5 = 100;
        goto L_0x0019;
    L_0x003e:
        r5 = 8;
        goto L_0x0019;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.jpush.internal.a.ak.<clinit>():void");
    }

    ak() {
    }

    /* renamed from: b */
    private static URI m16196b(C3976a c3976a) {
        URI uri = null;
        if (c3976a.mo5683f() == C3979c.f14328i) {
            c3976a.mo5687j();
        } else {
            try {
                String h = c3976a.mo5685h();
                if (!f14359z.equals(h)) {
                    uri = new URI(h);
                }
            } catch (Throwable e) {
                throw new C4053x(e);
            }
        }
        return uri;
    }

    /* renamed from: a */
    public final /* synthetic */ Object mo5672a(C3976a c3976a) {
        return ak.m16196b(c3976a);
    }

    /* renamed from: a */
    public final /* synthetic */ void mo5673a(C3980d c3980d, Object obj) {
        URI uri = (URI) obj;
        c3980d.mo5700b(uri == null ? null : uri.toASCIIString());
    }
}
