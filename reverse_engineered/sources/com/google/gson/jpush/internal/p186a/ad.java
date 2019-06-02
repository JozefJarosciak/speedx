package com.google.gson.jpush.internal.p186a;

import com.google.gson.jpush.af;
import com.google.gson.jpush.al;
import com.google.gson.jpush.p185b.C3976a;
import com.google.gson.jpush.p185b.C3979c;
import com.google.gson.jpush.p185b.C3980d;

/* renamed from: com.google.gson.jpush.internal.a.ad */
final class ad extends al<Character> {
    /* renamed from: z */
    private static final String f14357z;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r6 = 1;
        r0 = "n\u0001\u0000d!_\u0010\u001efbH\u0011\u0011s#H\r\u0015sn\u000b\u001e\u001fux\u000b";
        r0 = r0.toCharArray();
        r1 = r0.length;
        r2 = 0;
        if (r1 > r6) goto L_0x0027;
    L_0x000b:
        r3 = r0;
        r4 = r2;
        r8 = r1;
        r1 = r0;
        r0 = r8;
    L_0x0010:
        r7 = r1[r2];
        r5 = r4 % 5;
        switch(r5) {
            case 0: goto L_0x0035;
            case 1: goto L_0x0038;
            case 2: goto L_0x003b;
            case 3: goto L_0x003e;
            default: goto L_0x0017;
        };
    L_0x0017:
        r5 = 66;
    L_0x0019:
        r5 = r5 ^ r7;
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
        f14357z = r0;
        return;
    L_0x0035:
        r5 = 43;
        goto L_0x0019;
    L_0x0038:
        r5 = 121; // 0x79 float:1.7E-43 double:6.0E-322;
        goto L_0x0019;
    L_0x003b:
        r5 = 112; // 0x70 float:1.57E-43 double:5.53E-322;
        goto L_0x0019;
    L_0x003e:
        r5 = r6;
        goto L_0x0019;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.jpush.internal.a.ad.<clinit>():void");
    }

    ad() {
    }

    /* renamed from: a */
    public final /* synthetic */ Object mo5672a(C3976a c3976a) {
        if (c3976a.mo5683f() == C3979c.f14328i) {
            c3976a.mo5687j();
            return null;
        }
        String h = c3976a.mo5685h();
        if (h.length() == 1) {
            return Character.valueOf(h.charAt(0));
        }
        throw new af(new StringBuilder(f14357z).append(h).toString());
    }

    /* renamed from: a */
    public final /* synthetic */ void mo5673a(C3980d c3980d, Object obj) {
        Character ch = (Character) obj;
        c3980d.mo5700b(ch == null ? null : String.valueOf(ch));
    }
}
