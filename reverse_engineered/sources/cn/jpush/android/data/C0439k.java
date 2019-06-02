package cn.jpush.android.data;

import android.content.Context;
import cn.jpush.android.helpers.C0456f;
import cn.jpush.android.helpers.C0459i;
import cn.jpush.android.util.an;

/* renamed from: cn.jpush.android.data.k */
final class C0439k extends Thread {
    /* renamed from: z */
    private static final String[] f686z;
    /* renamed from: a */
    final /* synthetic */ Context f687a;
    /* renamed from: b */
    final /* synthetic */ int f688b;
    /* renamed from: c */
    final /* synthetic */ C0437i f689c;
    /* renamed from: d */
    final /* synthetic */ C0437i f690d;

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
        r12 = 2;
        r4 = 1;
        r1 = 0;
        r3 = new java.lang.String[r12];
        r2 = "?lgl5 f";
        r0 = -1;
        r5 = r3;
        r6 = r3;
        r3 = r1;
    L_0x000b:
        r2 = r2.toCharArray();
        r7 = r2.length;
        if (r7 > r4) goto L_0x0055;
    L_0x0012:
        r8 = r1;
    L_0x0013:
        r9 = r2;
        r10 = r8;
        r14 = r7;
        r7 = r2;
        r2 = r14;
    L_0x0018:
        r13 = r7[r8];
        r11 = r10 % 5;
        switch(r11) {
            case 0: goto L_0x004b;
            case 1: goto L_0x004e;
            case 2: goto L_0x0050;
            case 3: goto L_0x0052;
            default: goto L_0x001f;
        };
    L_0x001f:
        r11 = 106; // 0x6a float:1.49E-43 double:5.24E-322;
    L_0x0021:
        r11 = r11 ^ r13;
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
        r0 = "?lgl5 b";
        r2 = r0;
        r3 = r4;
        r5 = r6;
        r0 = r1;
        goto L_0x000b;
    L_0x0046:
        r5[r3] = r2;
        f686z = r6;
        return;
    L_0x004b:
        r11 = 73;
        goto L_0x0021;
    L_0x004e:
        r11 = 5;
        goto L_0x0021;
    L_0x0050:
        r11 = r12;
        goto L_0x0021;
    L_0x0052:
        r11 = 27;
        goto L_0x0021;
    L_0x0055:
        r8 = r1;
        goto L_0x002f;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.data.k.<clinit>():void");
    }

    C0439k(C0437i c0437i, Context context, int i, C0437i c0437i2) {
        this.f690d = c0437i;
        this.f687a = context;
        this.f688b = i;
        this.f689c = c0437i2;
    }

    public final void run() {
        if (C0456f.m1402a(this.f690d.f674L.f693b)) {
            this.f690d.f674L.f703l = C0429c.m1270a(this.f690d.f674L.f693b, this.f690d.c, f686z[0], this.f687a);
        }
        if (C0456f.m1402a(this.f690d.f674L.f699h)) {
            this.f690d.f674L.f704m = C0429c.m1270a(this.f690d.f674L.f699h, this.f690d.c, f686z[1], this.f687a);
        }
        if (an.m1657a(this.f690d.f674L.f703l) || an.m1657a(this.f690d.f674L.f704m)) {
            C0459i.m1415a(this.f690d.c, 1014, this.f687a);
            return;
        }
        C0459i.m1415a(this.f690d.c, this.f688b, this.f687a);
        C0437i.m1312a(this.f690d, this.f689c, this.f687a);
    }
}
