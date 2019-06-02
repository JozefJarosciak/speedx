package com.google.gson.jpush.internal.p186a;

import com.google.gson.jpush.al;
import com.google.gson.jpush.p185b.C3976a;
import com.google.gson.jpush.p185b.C3979c;
import com.google.gson.jpush.p185b.C3980d;
import java.util.Calendar;
import java.util.GregorianCalendar;

/* renamed from: com.google.gson.jpush.internal.a.aq */
final class aq extends al<Calendar> {
    /* renamed from: z */
    private static final String[] f14363z;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 6;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "'3\f\u0013S";
        r0 = -1;
        r4 = r3;
    L_0x0008:
        r1 = r1.toCharArray();
        r5 = r1.length;
        r6 = 0;
        r7 = 1;
        if (r5 > r7) goto L_0x002d;
    L_0x0011:
        r7 = r1;
        r8 = r6;
        r11 = r5;
        r5 = r1;
        r1 = r11;
    L_0x0016:
        r10 = r5[r6];
        r9 = r8 % 5;
        switch(r9) {
            case 0: goto L_0x0068;
            case 1: goto L_0x006b;
            case 2: goto L_0x006e;
            case 3: goto L_0x0071;
            default: goto L_0x001d;
        };
    L_0x001d:
        r9 = 59;
    L_0x001f:
        r9 = r9 ^ r10;
        r9 = (char) r9;
        r5[r6] = r9;
        r6 = r8 + 1;
        if (r1 != 0) goto L_0x002b;
    L_0x0027:
        r5 = r7;
        r8 = r6;
        r6 = r1;
        goto L_0x0016;
    L_0x002b:
        r5 = r1;
        r1 = r7;
    L_0x002d:
        if (r5 > r6) goto L_0x0011;
    L_0x002f:
        r5 = new java.lang.String;
        r5.<init>(r1);
        r1 = r5.intern();
        switch(r0) {
            case 0: goto L_0x0043;
            case 1: goto L_0x004b;
            case 2: goto L_0x0053;
            case 3: goto L_0x005b;
            case 4: goto L_0x0063;
            default: goto L_0x003b;
        };
    L_0x003b:
        r3[r2] = r1;
        r2 = 1;
        r1 = ".=\u001b(]\u00073\f\u0013S";
        r0 = 0;
        r3 = r4;
        goto L_0x0008;
    L_0x0043:
        r3[r2] = r1;
        r2 = 2;
        r1 = "99\u0001\bU.";
        r0 = 1;
        r3 = r4;
        goto L_0x0008;
    L_0x004b:
        r3[r2] = r1;
        r2 = 3;
        r1 = "39\u0003\u0015";
        r0 = 2;
        r3 = r4;
        goto L_0x0008;
    L_0x0053:
        r3[r2] = r1;
        r2 = 4;
        r1 = "'5\f\u0012O/";
        r0 = 3;
        r3 = r4;
        goto L_0x0008;
    L_0x005b:
        r3[r2] = r1;
        r2 = 5;
        r1 = "\"3\u0017\u0015t,\u0018\u0003\u001e";
        r0 = 4;
        r3 = r4;
        goto L_0x0008;
    L_0x0063:
        r3[r2] = r1;
        f14363z = r4;
        return;
    L_0x0068:
        r9 = 74;
        goto L_0x001f;
    L_0x006b:
        r9 = 92;
        goto L_0x001f;
    L_0x006e:
        r9 = 98;
        goto L_0x001f;
    L_0x0071:
        r9 = 103; // 0x67 float:1.44E-43 double:5.1E-322;
        goto L_0x001f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.jpush.internal.a.aq.<clinit>():void");
    }

    aq() {
    }

    /* renamed from: a */
    public final /* synthetic */ Object mo5672a(C3976a c3976a) {
        if (c3976a.mo5683f() == C3979c.f14328i) {
            c3976a.mo5687j();
            return null;
        }
        c3976a.mo5679c();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (c3976a.mo5683f() != C3979c.f14323d) {
            String g = c3976a.mo5684g();
            int m = c3976a.mo5690m();
            if (f14363z[3].equals(g)) {
                i6 = m;
            } else if (f14363z[0].equals(g)) {
                i5 = m;
            } else if (f14363z[1].equals(g)) {
                i4 = m;
            } else if (f14363z[5].equals(g)) {
                i3 = m;
            } else if (f14363z[4].equals(g)) {
                i2 = m;
            } else if (f14363z[2].equals(g)) {
                i = m;
            }
        }
        c3976a.mo5681d();
        return new GregorianCalendar(i6, i5, i4, i3, i2, i);
    }

    /* renamed from: a */
    public final /* synthetic */ void mo5673a(C3980d c3980d, Object obj) {
        Calendar calendar = (Calendar) obj;
        if (calendar == null) {
            c3980d.mo5705f();
            return;
        }
        c3980d.mo5703d();
        c3980d.mo5696a(f14363z[3]);
        c3980d.mo5694a((long) calendar.get(1));
        c3980d.mo5696a(f14363z[0]);
        c3980d.mo5694a((long) calendar.get(2));
        c3980d.mo5696a(f14363z[1]);
        c3980d.mo5694a((long) calendar.get(5));
        c3980d.mo5696a(f14363z[5]);
        c3980d.mo5694a((long) calendar.get(11));
        c3980d.mo5696a(f14363z[4]);
        c3980d.mo5694a((long) calendar.get(12));
        c3980d.mo5696a(f14363z[2]);
        c3980d.mo5694a((long) calendar.get(13));
        c3980d.mo5704e();
    }
}
