package com.google.gson.jpush.internal.p186a;

import com.google.gson.jpush.af;
import com.google.gson.jpush.al;
import com.google.gson.jpush.am;
import com.google.gson.jpush.p185b.C3976a;
import com.google.gson.jpush.p185b.C3979c;
import com.google.gson.jpush.p185b.C3980d;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/* renamed from: com.google.gson.jpush.internal.a.u */
public final class C4011u extends al<Date> {
    /* renamed from: a */
    public static final am f14428a = new C4012v();
    /* renamed from: z */
    private static final String f14429z;
    /* renamed from: b */
    private final DateFormat f14430b = new SimpleDateFormat(f14429z);

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = "vB{%B\u0017/O|_B";
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
        r5 = 38;
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
        f14429z = r0;
        r0 = new com.google.gson.jpush.internal.a.v;
        r0.<init>();
        f14428a = r0;
        return;
    L_0x003c:
        r5 = 59;
        goto L_0x0019;
    L_0x003f:
        r5 = 15;
        goto L_0x0019;
    L_0x0042:
        r5 = 54;
        goto L_0x0019;
    L_0x0045:
        r5 = 5;
        goto L_0x0019;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.jpush.internal.a.u.<clinit>():void");
    }

    /* renamed from: a */
    private synchronized void m16304a(C3980d c3980d, Date date) {
        c3980d.mo5700b(date == null ? null : this.f14430b.format(date));
    }

    /* renamed from: b */
    private synchronized Date m16305b(C3976a c3976a) {
        Date date;
        if (c3976a.mo5683f() == C3979c.f14328i) {
            c3976a.mo5687j();
            date = null;
        } else {
            try {
                date = new Date(this.f14430b.parse(c3976a.mo5685h()).getTime());
            } catch (Throwable e) {
                throw new af(e);
            }
        }
        return date;
    }

    /* renamed from: a */
    public final /* synthetic */ Object mo5672a(C3976a c3976a) {
        return m16305b(c3976a);
    }
}
