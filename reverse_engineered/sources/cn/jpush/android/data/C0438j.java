package cn.jpush.android.data;

import android.content.Context;
import cn.jpush.android.helpers.C0456f;
import cn.jpush.android.helpers.C0459i;
import cn.jpush.android.util.C0490b;
import cn.jpush.android.util.C0503p;
import cn.jpush.android.util.C0505r;
import cn.jpush.android.util.C0506s;
import cn.jpush.android.util.ac;

/* renamed from: cn.jpush.android.data.j */
final class C0438j extends Thread {
    /* renamed from: z */
    private static final String[] f681z;
    /* renamed from: a */
    final /* synthetic */ C0437i f682a;
    /* renamed from: b */
    final /* synthetic */ Context f683b;
    /* renamed from: c */
    final /* synthetic */ int f684c;
    /* renamed from: d */
    final /* synthetic */ C0437i f685d;

    static {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:61)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
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
        r2 = "\u00029.i^";
        r0 = -1;
        r5 = r3;
        r6 = r3;
        r3 = r1;
    L_0x000b:
        r2 = r2.toCharArray();
        r7 = r2.length;
        if (r7 > r4) goto L_0x0056;
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
        r11 = 50;
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
        r0 = "J86a\b\u0003~";
        r2 = r0;
        r3 = r4;
        r5 = r6;
        r0 = r1;
        goto L_0x000b;
    L_0x0046:
        r5[r3] = r2;
        f681z = r6;
        return;
    L_0x004b:
        r11 = 44;
        goto L_0x0021;
    L_0x004e:
        r11 = 81;
        goto L_0x0021;
    L_0x0051:
        r11 = 90;
        goto L_0x0021;
    L_0x0054:
        r11 = 4;
        goto L_0x0021;
    L_0x0056:
        r8 = r1;
        goto L_0x002f;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.data.j.<clinit>():void");
    }

    C0438j(C0437i c0437i, C0437i c0437i2, Context context, int i) {
        this.f685d = c0437i;
        this.f682a = c0437i2;
        this.f683b = context;
        this.f684c = i;
    }

    public final void run() {
        String str = this.f682a.f674L.f701j;
        String str2 = this.f682a.c;
        if (!C0456f.m1402a(str)) {
            C0459i.m1415a(this.f682a.c, 996, this.f683b);
        } else if (this.f682a.f674L.f700i) {
            String str3;
            int i;
            String str4 = null;
            for (int i2 = 0; i2 < 4; i2++) {
                str4 = C0506s.m1797a(str, 5, 5000);
                if (!C0506s.m1800a(str4)) {
                    str3 = str4;
                    i = 1;
                    break;
                }
            }
            str3 = str4;
            i = 0;
            if (i == 0) {
                C0459i.m1415a(str2, 1014, this.f683b);
                C0459i.m1416a(str2, 1021, C0490b.m1685b(this.f683b, str), this.f683b);
                ac.m1581b();
                return;
            }
            if (C0429c.m1271a(this.f682a.f674L.f702k, this.f683b, str.substring(0, str.lastIndexOf("/") + 1), str2, this.f682a.m1279e())) {
                str4 = this.f682a.m1279e() ? C0503p.m1783b(this.f683b, str2) + str2 + f681z[0] : C0503p.m1779a(this.f683b, str2) + str2;
                if (C0505r.m1792a(str4, str3, this.f683b)) {
                    this.f682a.f674L.f705n = new StringBuilder(f681z[1]).append(str4).toString();
                    C0459i.m1415a(str2, this.f684c, this.f683b);
                    C0437i.m1312a(this.f685d, this.f682a, this.f683b);
                    return;
                }
                C0459i.m1415a(str2, 1014, this.f683b);
                return;
            }
            ac.m1581b();
            C0459i.m1415a(str2, 1014, this.f683b);
        } else {
            C0459i.m1415a(str2, this.f684c, this.f683b);
            C0437i.m1312a(this.f685d, this.f682a, this.f683b);
        }
    }
}
