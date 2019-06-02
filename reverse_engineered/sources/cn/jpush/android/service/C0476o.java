package cn.jpush.android.service;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import cn.jpush.android.util.ac;
import cn.jpush.p005b.p006a.p007a.C0517h;
import java.lang.ref.WeakReference;

/* renamed from: cn.jpush.android.service.o */
final class C0476o extends Handler {
    /* renamed from: z */
    private static final String[] f886z;
    /* renamed from: a */
    private WeakReference<C0475n> f887a;

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
        r5 = "6\u0001-Ty\u0006\f<ImCBh[h\r\u001bhXfC\u001c-BmC\u0001=@eC\u001d-]|\u0006\u001c<\u0002";
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
        r13 = 9;
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
        r0 = "\u0000\u0000&Bl\u0000\u001b!Cg";
        r5 = r0;
        r6 = r2;
        r7 = r8;
        r0 = r1;
        goto L_0x000d;
    L_0x0048:
        r7[r6] = r5;
        r0 = "+\u000e&He\u0006O%_nCBhXa\u0011\n)H@\u0007U";
        r5 = r0;
        r6 = r3;
        r7 = r8;
        r0 = r2;
        goto L_0x000d;
    L_0x0051:
        r7[r6] = r5;
        r0 = "1\n9Yl\u0010\u001b!Bn7\u0007:Ih\u0007";
        r5 = r0;
        r6 = r4;
        r7 = r8;
        r0 = r3;
        goto L_0x000d;
    L_0x005a:
        r7[r6] = r5;
        r5 = 4;
        r0 = "6\u0001 Mg\u0007\u0003-H)\u000e\u001c/\f$C";
        r6 = r5;
        r7 = r8;
        r5 = r0;
        r0 = r4;
        goto L_0x000d;
    L_0x0064:
        r7[r6] = r5;
        f886z = r8;
        return;
    L_0x0069:
        r13 = 99;
        goto L_0x0023;
    L_0x006c:
        r13 = 111; // 0x6f float:1.56E-43 double:5.5E-322;
        goto L_0x0023;
    L_0x006f:
        r13 = 72;
        goto L_0x0023;
    L_0x0072:
        r13 = 44;
        goto L_0x0023;
    L_0x0075:
        r10 = r1;
        goto L_0x0031;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.service.o.<clinit>():void");
    }

    public C0476o(Looper looper, C0475n c0475n) {
        super(looper);
        this.f887a = new WeakReference(c0475n);
    }

    public final void handleMessage(Message message) {
        super.handleMessage(message);
        new StringBuilder(f886z[2]).append(Thread.currentThread().getId());
        ac.m1576a();
        C0475n c0475n = (C0475n) this.f887a.get();
        if (c0475n == null) {
            ac.m1581b();
            return;
        }
        C0477p c0477p;
        switch (message.what) {
            case 7401:
                if (message.obj == null) {
                    ac.m1587d(f886z[3], f886z[0]);
                    return;
                } else {
                    C0475n.m1518a(c0475n, (C0517h) message.obj, message.arg1);
                    return;
                }
            case 7402:
                C0475n.m1516a(c0475n, message.getData().getLong(f886z[1]), message.obj);
                return;
            case 7403:
                c0477p = (C0477p) c0475n.f878a.get((Long) message.obj);
                if (c0477p == null) {
                    ac.m1586d();
                    return;
                } else {
                    c0475n.m1519a(c0477p);
                    return;
                }
            case 7404:
                c0477p = (C0477p) c0475n.f878a.get((Long) message.obj);
                if (c0477p == null) {
                    ac.m1586d();
                    return;
                } else {
                    C0475n.m1517a(c0475n, c0477p);
                    return;
                }
            case 7405:
                C0475n.m1520b(c0475n);
                return;
            default:
                new StringBuilder(f886z[4]).append(message.what);
                ac.m1586d();
                return;
        }
    }
}
