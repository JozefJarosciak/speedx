package com.google.gson.jpush.internal.p186a;

import com.google.gson.jpush.af;
import com.google.gson.jpush.al;
import com.google.gson.jpush.am;
import com.google.gson.jpush.p185b.C3976a;
import com.google.gson.jpush.p185b.C3979c;
import com.google.gson.jpush.p185b.C3980d;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/* renamed from: com.google.gson.jpush.internal.a.w */
public final class C4013w extends al<Time> {
    /* renamed from: a */
    public static final am f14431a = new C4014x();
    /* renamed from: z */
    private static final String f14432z;
    /* renamed from: b */
    private final DateFormat f14433b = new SimpleDateFormat(f14432z);

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = "82Kx\tj)\u00025\u0005";
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
            case 0: goto L_0x003c;
            case 1: goto L_0x003f;
            case 2: goto L_0x0042;
            case 3: goto L_0x0045;
            default: goto L_0x0017;
        };
    L_0x0017:
        r5 = 100;
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
        f14432z = r0;
        r0 = new com.google.gson.jpush.internal.a.x;
        r0.<init>();
        f14431a = r0;
        return;
    L_0x003c:
        r5 = 80;
        goto L_0x0019;
    L_0x003f:
        r5 = 90;
        goto L_0x0019;
    L_0x0042:
        r5 = 113; // 0x71 float:1.58E-43 double:5.6E-322;
        goto L_0x0019;
    L_0x0045:
        r5 = 21;
        goto L_0x0019;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.jpush.internal.a.w.<clinit>():void");
    }

    /* renamed from: a */
    private synchronized void m16309a(C3980d c3980d, Time time) {
        c3980d.mo5700b(time == null ? null : this.f14433b.format(time));
    }

    /* renamed from: b */
    private synchronized Time m16310b(C3976a c3976a) {
        Time time;
        if (c3976a.mo5683f() == C3979c.f14328i) {
            c3976a.mo5687j();
            time = null;
        } else {
            try {
                time = new Time(this.f14433b.parse(c3976a.mo5685h()).getTime());
            } catch (Throwable e) {
                throw new af(e);
            }
        }
        return time;
    }

    /* renamed from: a */
    public final /* synthetic */ Object mo5672a(C3976a c3976a) {
        return m16310b(c3976a);
    }
}
