package cn.jpush.android;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import cn.jpush.android.util.ac;

/* renamed from: cn.jpush.android.f */
final class C0449f implements ServiceConnection {
    /* renamed from: z */
    private static final String[] f767z;

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
        r2 = "\u00057h6B\nt1B\n\u0007y-[\r7y\u001bD\u00177s1C\u00017h:IHt_0@\u0014;r:C\u0010\u001a}2H^";
        r0 = -1;
        r5 = r3;
        r6 = r3;
        r3 = r1;
    L_0x000b:
        r2 = r2.toCharArray();
        r7 = r2.length;
        if (r7 > r4) goto L_0x0057;
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
        r11 = 45;
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
        r0 = "\u00057h6B\nt1B\n\u0007y-[\r7y\u001cB\n:y<Y\u000100n\u000b9l0C\u0001:h\u0011L\t1&";
        r2 = r0;
        r3 = r4;
        r5 = r6;
        r0 = r1;
        goto L_0x000b;
    L_0x0046:
        r5[r3] = r2;
        f767z = r6;
        return;
    L_0x004b:
        r11 = 100;
        goto L_0x0021;
    L_0x004e:
        r11 = 84;
        goto L_0x0021;
    L_0x0051:
        r11 = 28;
        goto L_0x0021;
    L_0x0054:
        r11 = 95;
        goto L_0x0021;
    L_0x0057:
        r8 = r1;
        goto L_0x002f;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.f.<clinit>():void");
    }

    C0449f() {
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        new StringBuilder(f767z[1]).append(componentName.toString());
        ac.m1581b();
        ac.m1584c();
        C0448e.f763o = C0427c.m1255a(iBinder);
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        new StringBuilder(f767z[0]).append(componentName.toString());
        ac.m1581b();
        C0448e.f763o = null;
    }
}
