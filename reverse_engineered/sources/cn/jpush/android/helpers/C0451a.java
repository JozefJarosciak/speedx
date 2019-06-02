package cn.jpush.android.helpers;

import cn.jpush.android.util.ac;
import java.net.InetAddress;

/* renamed from: cn.jpush.android.helpers.a */
final class C0451a extends Thread {
    /* renamed from: z */
    private static final String[] f770z;
    /* renamed from: a */
    private String f771a = null;
    /* renamed from: b */
    private InetAddress f772b = null;

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
        r4 = 3;
        r3 = 2;
        r2 = 1;
        r1 = 0;
        r0 = 5;
        r6 = new java.lang.String[r0];
        r5 = "J,DQ\u0016n,S\u001e>V\u001a\u0017X\u001bq%\u0017X\bw$\u0017V\u0015k=\r\u001e";
        r0 = -1;
        r7 = r6;
        r8 = r6;
        r6 = r1;
    L_0x000d:
        r5 = r5.toCharArray();
        r9 = r5.length;
        if (r9 > r2) goto L_0x0075;
    L_0x0014:
        r10 = r1;
    L_0x0015:
        r11 = r5;
        r12 = r10;
        r15 = r9;
        r9 = r5;
        r5 = r15;
    L_0x001a:
        r14 = r9[r10];
        r13 = r12 % 5;
        switch(r13) {
            case 0: goto L_0x0069;
            case 1: goto L_0x006c;
            case 2: goto L_0x006f;
            case 3: goto L_0x0072;
            default: goto L_0x0021;
        };
    L_0x0021:
        r13 = 122; // 0x7a float:1.71E-43 double:6.03E-322;
    L_0x0023:
        r13 = r13 ^ r14;
        r13 = (char) r13;
        r9[r10] = r13;
        r10 = r12 + 1;
        if (r5 != 0) goto L_0x002f;
    L_0x002b:
        r9 = r11;
        r12 = r10;
        r10 = r5;
        goto L_0x001a;
    L_0x002f:
        r9 = r5;
        r5 = r11;
    L_0x0031:
        if (r9 > r10) goto L_0x0015;
    L_0x0033:
        r9 = new java.lang.String;
        r9.<init>(r5);
        r5 = r9.intern();
        switch(r0) {
            case 0: goto L_0x0048;
            case 1: goto L_0x0051;
            case 2: goto L_0x005a;
            case 3: goto L_0x0064;
            default: goto L_0x003f;
        };
    L_0x003f:
        r7[r6] = r5;
        r0 = "[&YP\u001f{=^P\u001dP,[N\u001fj";
        r5 = r0;
        r6 = r2;
        r7 = r8;
        r0 = r1;
        goto L_0x000d;
    L_0x0048:
        r7[r6] = r5;
        r0 = "j,DQ\u0016n,S\u001e>V\u001a\u0017\u0013Zp&DJ@";
        r5 = r0;
        r6 = r3;
        r7 = r8;
        r0 = r2;
        goto L_0x000d;
    L_0x0051:
        r7[r6] = r5;
        r0 = "M'\\P\u0015o'\u0017V\u0015k=\u0017[\u0002{,GJ\u0013w'\u0016";
        r5 = r0;
        r6 = r4;
        r7 = r8;
        r0 = r3;
        goto L_0x000d;
    L_0x005a:
        r7[r6] = r5;
        r5 = 4;
        r0 = "L!R\u001e\u001cy [K\b}iVN\n}(EMZl&\u0017V\u001bn,\u0017\\\u001f}'\u0017_Zt(TUZw/\u0017w4L\fep?Li\u0016";
        r6 = r5;
        r7 = r8;
        r5 = r0;
        r0 = r4;
        goto L_0x000d;
    L_0x0064:
        r7[r6] = r5;
        f770z = r8;
        return;
    L_0x0069:
        r13 = 24;
        goto L_0x0023;
    L_0x006c:
        r13 = 73;
        goto L_0x0023;
    L_0x006f:
        r13 = 55;
        goto L_0x0023;
    L_0x0072:
        r13 = 62;
        goto L_0x0023;
    L_0x0075:
        r10 = r1;
        goto L_0x0031;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.helpers.a.<clinit>():void");
    }

    public C0451a(String str) {
        this.f771a = str;
    }

    /* renamed from: a */
    public final synchronized InetAddress m1367a() {
        InetAddress inetAddress;
        if (this.f772b != null) {
            inetAddress = this.f772b;
        } else {
            new StringBuilder(f770z[0]).append(this.f771a);
            ac.m1586d();
            inetAddress = null;
        }
        return inetAddress;
    }

    public final void run() {
        try {
            new StringBuilder(f770z[2]).append(this.f771a);
            ac.m1584c();
            this.f772b = InetAddress.getByName(this.f771a);
        } catch (Throwable e) {
            ac.m1579a(f770z[1], f770z[3], e);
        } catch (Throwable e2) {
            ac.m1579a(f770z[1], f770z[4], e2);
        }
    }
}
