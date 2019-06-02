package com.google.gson.jpush;

import ch.qos.logback.core.CoreConstants;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* renamed from: com.google.gson.jpush.a */
final class C3974a implements ae<Date>, C3973v<Date> {
    /* renamed from: z */
    private static final String[] f14278z;
    /* renamed from: a */
    private final DateFormat f14279a;
    /* renamed from: b */
    private final DateFormat f14280b;
    /* renamed from: c */
    private final DateFormat f14281c;

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
        r13 = 51;
        r3 = 2;
        r2 = 1;
        r1 = 0;
        r0 = 4;
        r5 = new java.lang.String[r0];
        r4 = "T&y]]\u001b18WV\u0007 jZR\u0018,bV\u0013\u0000*8";
        r0 = -1;
        r6 = r5;
        r7 = r5;
        r5 = r1;
    L_0x000e:
        r4 = r4.toCharArray();
        r8 = r4.length;
        if (r8 > r2) goto L_0x006b;
    L_0x0015:
        r9 = r1;
    L_0x0016:
        r10 = r4;
        r11 = r9;
        r15 = r8;
        r8 = r4;
        r4 = r15;
    L_0x001b:
        r14 = r8[r9];
        r12 = r11 % 5;
        switch(r12) {
            case 0: goto L_0x0060;
            case 1: goto L_0x0063;
            case 2: goto L_0x0066;
            case 3: goto L_0x0069;
            default: goto L_0x0022;
        };
    L_0x0022:
        r12 = r13;
    L_0x0023:
        r12 = r12 ^ r14;
        r12 = (char) r12;
        r8[r9] = r12;
        r9 = r11 + 1;
        if (r4 != 0) goto L_0x002f;
    L_0x002b:
        r8 = r10;
        r11 = r9;
        r9 = r4;
        goto L_0x001b;
    L_0x002f:
        r8 = r4;
        r4 = r10;
    L_0x0031:
        if (r8 > r9) goto L_0x0016;
    L_0x0033:
        r8 = new java.lang.String;
        r8.<init>(r4);
        r4 = r8.intern();
        switch(r0) {
            case 0: goto L_0x0048;
            case 1: goto L_0x0051;
            case 2: goto L_0x005b;
            default: goto L_0x003f;
        };
    L_0x003f:
        r6[r5] = r4;
        r0 = " -}\u0013W\u00151}\u0013@\u001c*m_WT'}\u0013RT6lAZ\u001a\"8ER\u00180}";
        r4 = r0;
        r5 = r2;
        r6 = r7;
        r0 = r1;
        goto L_0x000e;
    L_0x0048:
        r6[r5] = r4;
        r0 = "\r<aJ\u001e9\b5WWS\u0011?{{N(u\t@\u0007bB\u0014";
        r4 = r0;
        r5 = r3;
        r6 = r7;
        r0 = r2;
        goto L_0x000e;
    L_0x0051:
        r6[r5] = r4;
        r4 = 3;
        r0 = "!\u0011[";
        r5 = r4;
        r6 = r7;
        r4 = r0;
        r0 = r3;
        goto L_0x000e;
    L_0x005b:
        r6[r5] = r4;
        f14278z = r7;
        return;
    L_0x0060:
        r12 = 116; // 0x74 float:1.63E-43 double:5.73E-322;
        goto L_0x0023;
    L_0x0063:
        r12 = 69;
        goto L_0x0023;
    L_0x0066:
        r12 = 24;
        goto L_0x0023;
    L_0x0069:
        r12 = r13;
        goto L_0x0023;
    L_0x006b:
        r9 = r1;
        goto L_0x0031;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.jpush.a.<clinit>():void");
    }

    C3974a() {
        this(DateFormat.getDateTimeInstance(2, 2, Locale.US), DateFormat.getDateTimeInstance(2, 2));
    }

    public C3974a(int i, int i2) {
        this(DateFormat.getDateTimeInstance(i, i2, Locale.US), DateFormat.getDateTimeInstance(i, i2));
    }

    C3974a(String str) {
        this(new SimpleDateFormat(str, Locale.US), new SimpleDateFormat(str));
    }

    private C3974a(DateFormat dateFormat, DateFormat dateFormat2) {
        this.f14279a = dateFormat;
        this.f14280b = dateFormat2;
        this.f14281c = new SimpleDateFormat(f14278z[2], Locale.US);
        this.f14281c.setTimeZone(TimeZone.getTimeZone(f14278z[3]));
    }

    /* renamed from: a */
    private C3975w m16061a(Date date) {
        C3975w acVar;
        synchronized (this.f14280b) {
            acVar = new ac(this.f14279a.format(date));
        }
        return acVar;
    }

    /* renamed from: a */
    private Date m16062a(C3975w c3975w) {
        Date parse;
        synchronized (this.f14280b) {
            try {
                parse = this.f14280b.parse(c3975w.mo5667c());
            } catch (ParseException e) {
                try {
                    parse = this.f14279a.parse(c3975w.mo5667c());
                } catch (ParseException e2) {
                    try {
                        parse = this.f14281c.parse(c3975w.mo5667c());
                    } catch (Throwable e3) {
                        throw new af(c3975w.mo5667c(), e3);
                    }
                }
            }
        }
        return parse;
    }

    /* renamed from: a */
    public final /* synthetic */ Object mo5665a(C3975w c3975w, Type type, C4043u c4043u) {
        if (c3975w instanceof ac) {
            Date a = m16062a(c3975w);
            if (type == Date.class) {
                return a;
            }
            if (type == Timestamp.class) {
                return new Timestamp(a.getTime());
            }
            if (type == java.sql.Date.class) {
                return new java.sql.Date(a.getTime());
            }
            throw new IllegalArgumentException(getClass() + f14278z[0] + type);
        }
        throw new aa(f14278z[1]);
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(C3974a.class.getSimpleName());
        stringBuilder.append(CoreConstants.LEFT_PARENTHESIS_CHAR).append(this.f14280b.getClass().getSimpleName()).append(CoreConstants.RIGHT_PARENTHESIS_CHAR);
        return stringBuilder.toString();
    }
}
