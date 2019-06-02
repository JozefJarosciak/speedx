package com.google.gson.jpush.internal.p186a;

import com.google.gson.jpush.af;
import com.google.gson.jpush.al;
import com.google.gson.jpush.am;
import com.google.gson.jpush.p185b.C3976a;
import com.google.gson.jpush.p185b.C3979c;
import com.google.gson.jpush.p185b.C3980d;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* renamed from: com.google.gson.jpush.internal.a.e */
public final class C3995e extends al<Date> {
    /* renamed from: a */
    public static final am f14386a = new C3996f();
    /* renamed from: z */
    private static final String[] f14387z;
    /* renamed from: b */
    private final DateFormat f14388b = DateFormat.getDateTimeInstance(2, 2, Locale.US);
    /* renamed from: c */
    private final DateFormat f14389c = DateFormat.getDateTimeInstance(2, 2);
    /* renamed from: d */
    private final DateFormat f14390d;

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
        r2 = "u,j";
        r0 = -1;
        r5 = r3;
        r6 = r3;
        r3 = r1;
    L_0x000b:
        r2 = r2.toCharArray();
        r7 = r2.length;
        if (r7 > r4) goto L_0x005e;
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
            case 0: goto L_0x0052;
            case 1: goto L_0x0055;
            case 2: goto L_0x0058;
            case 3: goto L_0x005b;
            default: goto L_0x001f;
        };
    L_0x001f:
        r11 = 114; // 0x72 float:1.6E-43 double:5.63E-322;
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
        r0 = "Y\u0001P__m5\u0004B\u0016\u0007,\u000en:\u001a\u0015D\u001c\u0001S_s\u0001";
        r2 = r0;
        r3 = r4;
        r5 = r6;
        r0 = r1;
        goto L_0x000b;
    L_0x0046:
        r5[r3] = r2;
        f14387z = r6;
        r0 = new com.google.gson.jpush.internal.a.f;
        r0.<init>();
        f14386a = r0;
        return;
    L_0x0052:
        r11 = 32;
        goto L_0x0021;
    L_0x0055:
        r11 = 120; // 0x78 float:1.68E-43 double:5.93E-322;
        goto L_0x0021;
    L_0x0058:
        r11 = 41;
        goto L_0x0021;
    L_0x005b:
        r11 = 38;
        goto L_0x0021;
    L_0x005e:
        r8 = r1;
        goto L_0x002f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.jpush.internal.a.e.<clinit>():void");
    }

    public C3995e() {
        DateFormat simpleDateFormat = new SimpleDateFormat(f14387z[1], Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(f14387z[0]));
        this.f14390d = simpleDateFormat;
    }

    /* renamed from: a */
    private synchronized Date m16246a(String str) {
        Date parse;
        try {
            parse = this.f14389c.parse(str);
        } catch (ParseException e) {
            try {
                parse = this.f14388b.parse(str);
            } catch (ParseException e2) {
                try {
                    parse = this.f14390d.parse(str);
                } catch (Throwable e3) {
                    throw new af(str, e3);
                }
            }
        }
        return parse;
    }

    /* renamed from: a */
    private synchronized void m16247a(C3980d c3980d, Date date) {
        if (date == null) {
            c3980d.mo5705f();
        } else {
            c3980d.mo5700b(this.f14388b.format(date));
        }
    }

    /* renamed from: a */
    public final /* synthetic */ Object mo5672a(C3976a c3976a) {
        if (c3976a.mo5683f() != C3979c.f14328i) {
            return m16246a(c3976a.mo5685h());
        }
        c3976a.mo5687j();
        return null;
    }
}
