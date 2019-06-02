package cn.jpush.android.p002a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import cn.jpush.android.util.ac;

/* renamed from: cn.jpush.android.a.e */
final class C0396e extends BroadcastReceiver {
    /* renamed from: z */
    private static final String[] f459z;
    /* renamed from: a */
    final /* synthetic */ C0395d f460a;

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
        r2 = 1;
        r1 = 0;
        r0 = 3;
        r4 = new java.lang.String[r0];
        r3 = "r\u0002\u0014 z\b^c*gB\u0007d)zB'D\tZ3#Y\u000eG)/N\u0007R\"7H\u000b";
        r0 = -1;
        r5 = r4;
        r6 = r4;
        r4 = r1;
    L_0x000b:
        r3 = r3.toCharArray();
        r7 = r3.length;
        if (r7 > r2) goto L_0x0061;
    L_0x0012:
        r8 = r1;
    L_0x0013:
        r9 = r3;
        r10 = r8;
        r13 = r7;
        r7 = r3;
        r3 = r13;
    L_0x0018:
        r12 = r7[r8];
        r11 = r10 % 5;
        switch(r11) {
            case 0: goto L_0x0055;
            case 1: goto L_0x0058;
            case 2: goto L_0x005b;
            case 3: goto L_0x005e;
            default: goto L_0x001f;
        };
    L_0x001f:
        r11 = 79;
    L_0x0021:
        r11 = r11 ^ r12;
        r11 = (char) r11;
        r7[r8] = r11;
        r8 = r10 + 1;
        if (r3 != 0) goto L_0x002d;
    L_0x0029:
        r7 = r9;
        r10 = r8;
        r8 = r3;
        goto L_0x0018;
    L_0x002d:
        r7 = r3;
        r3 = r9;
    L_0x002f:
        if (r7 > r8) goto L_0x0013;
    L_0x0031:
        r7 = new java.lang.String;
        r7.<init>(r3);
        r3 = r7.intern();
        switch(r0) {
            case 0: goto L_0x0046;
            case 1: goto L_0x0050;
            default: goto L_0x003d;
        };
    L_0x003d:
        r5[r4] = r3;
        r0 = "r\u0002\u0014 z\b^c*gB\u0007d)zB#N\u000e]3\"H\u001cF $^";
        r3 = r0;
        r4 = r2;
        r5 = r6;
        r0 = r1;
        goto L_0x000b;
    L_0x0046:
        r5[r4] = r3;
        r3 = 2;
        r0 = "d\u0005\u0016d\u0010`\u0018\u0011y*";
        r4 = r3;
        r5 = r6;
        r3 = r0;
        r0 = r2;
        goto L_0x000b;
    L_0x0050:
        r5[r4] = r3;
        f459z = r6;
        return;
    L_0x0055:
        r11 = 19;
        goto L_0x0021;
    L_0x0058:
        r11 = 108; // 0x6c float:1.51E-43 double:5.34E-322;
        goto L_0x0021;
    L_0x005b:
        r11 = 112; // 0x70 float:1.57E-43 double:5.53E-322;
        goto L_0x0021;
    L_0x005e:
        r11 = 13;
        goto L_0x0021;
    L_0x0061:
        r8 = r1;
        goto L_0x002f;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.a.e.<clinit>():void");
    }

    private C0396e(C0395d c0395d) {
        this.f460a = c0395d;
    }

    public final void onReceive(Context context, Intent intent) {
        boolean z = false;
        if (this.f460a.f456x && C0395d.m995a(this.f460a.f442j)) {
            if (this.f460a.f448p == 2) {
                if (f459z[1].equals(intent.getAction())) {
                    this.f460a.f444l.removeMessages(5);
                    if (System.currentTimeMillis() - this.f460a.f447o > 4000) {
                        this.f460a.f444l.sendEmptyMessageDelayed(5, 4000);
                    } else {
                        this.f460a.f444l.sendEmptyMessage(5);
                    }
                } else if (this.f460a.f450r) {
                    if (f459z[0].equals(intent.getAction()) && intent.getIntExtra(f459z[2], 4) == 2) {
                        this.f460a.f437d = true;
                        this.f460a.f445m = false;
                        ac.m1581b();
                    }
                }
            }
        } else if (intent.getIntExtra(f459z[2], 4) == 3) {
            WifiManager b = this.f460a.f451s.m1029b();
            if (b != null) {
                z = b.startScan();
            }
            if (z) {
                try {
                    this.f460a.f435A = this.f460a.f451s.m1030c();
                    this.f460a.f442j.unregisterReceiver(this.f460a.f446n);
                    if (!this.f460a.f456x) {
                        this.f460a.mo2194d();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
