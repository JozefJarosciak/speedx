package cn.jpush.android.helpers;

import android.content.Context;
import cn.jpush.android.C0404a;
import cn.jpush.android.service.ServiceInterface;
import cn.jpush.android.util.C0502o;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.an;

/* renamed from: cn.jpush.android.helpers.k */
public final class C0461k {
    /* renamed from: z */
    private static final String[] f787z;

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
        r13 = 18;
        r3 = 2;
        r2 = 1;
        r1 = 0;
        r0 = 4;
        r5 = new java.lang.String[r0];
        r4 = "+<";
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
            case 2: goto L_0x0065;
            case 3: goto L_0x0068;
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
        r0 = "6}fCD`yN}t(";
        r4 = r0;
        r5 = r2;
        r6 = r7;
        r0 = r1;
        goto L_0x000e;
    L_0x0048:
        r6[r5] = r4;
        r0 = "iva\u0007d`yN}t2'\u0007qo`\\B`i{eI(";
        r4 = r0;
        r5 = r3;
        r6 = r7;
        r0 = r2;
        goto L_0x000e;
    L_0x0051:
        r6[r5] = r4;
        r4 = 3;
        r0 = "J`oAa\\{fB";
        r5 = r4;
        r6 = r7;
        r4 = r0;
        r0 = r3;
        goto L_0x000e;
    L_0x005b:
        r6[r5] = r4;
        f787z = r7;
        return;
    L_0x0060:
        r12 = 26;
        goto L_0x0023;
    L_0x0063:
        r12 = r13;
        goto L_0x0023;
    L_0x0065:
        r12 = 10;
        goto L_0x0023;
    L_0x0068:
        r12 = 39;
        goto L_0x0023;
    L_0x006b:
        r9 = r1;
        goto L_0x0031;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.helpers.k.<clinit>():void");
    }

    /* renamed from: a */
    public static void m1422a(Context context) {
        ac.m1581b();
        String a = ServiceInterface.m1458a();
        String a2 = C0502o.m1773a(context);
        if (an.m1657a(a2)) {
            a2 = C0404a.m1054I();
        }
        new StringBuilder(f787z[2]).append(a).append(f787z[1]).append(a2);
        ac.m1576a();
        if (an.m1657a(a2)) {
            ac.m1581b();
        } else if (!(a.equals(a2) || a.startsWith(f787z[0]) || !a2.startsWith(f787z[0]))) {
            C0502o.m1774a(context, a);
            ac.m1581b();
            C0461k.m1423b(context);
            C0404a.m1134p(context);
        }
        C0404a.m1128m(a);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: b */
    private static synchronized boolean m1423b(android.content.Context r14) {
        /*
        r6 = 0;
        r13 = 8;
        r4 = 0;
        r10 = cn.jpush.android.helpers.C0461k.class;
        monitor-enter(r10);
        cn.jpush.android.util.ac.m1581b();	 Catch:{ all -> 0x005e }
        r2 = "";
        r3 = "";
        r0 = 8;
        r11 = new byte[r0];	 Catch:{ FileNotFoundException -> 0x0044, IOException -> 0x0058 }
        r0 = f787z;	 Catch:{ FileNotFoundException -> 0x0044, IOException -> 0x0058 }
        r1 = 3;
        r0 = r0[r1];	 Catch:{ FileNotFoundException -> 0x0044, IOException -> 0x0058 }
        r12 = r14.openFileInput(r0);	 Catch:{ FileNotFoundException -> 0x0044, IOException -> 0x0058 }
        r0 = 0;
        r1 = 8;
        r12.read(r11, r0, r1);	 Catch:{ FileNotFoundException -> 0x0044, IOException -> 0x0058 }
        r5 = r4;
        r0 = r6;
    L_0x0024:
        if (r5 >= r13) goto L_0x0033;
    L_0x0026:
        r8 = r0 << r13;
        r0 = r11[r5];	 Catch:{ FileNotFoundException -> 0x0044, IOException -> 0x0081 }
        r0 = r0 & 255;
        r0 = (long) r0;	 Catch:{ FileNotFoundException -> 0x0044, IOException -> 0x0081 }
        r8 = r8 + r0;
        r0 = r5 + 1;
        r5 = r0;
        r0 = r8;
        goto L_0x0024;
    L_0x0033:
        r5 = new java.lang.StringBuilder;	 Catch:{ FileNotFoundException -> 0x0044, IOException -> 0x0081 }
        r5.<init>();	 Catch:{ FileNotFoundException -> 0x0044, IOException -> 0x0081 }
    L_0x0038:
        r8 = r12.read();	 Catch:{ FileNotFoundException -> 0x0044, IOException -> 0x0081 }
        r9 = -1;
        if (r8 == r9) goto L_0x0050;
    L_0x003f:
        r8 = (char) r8;	 Catch:{ FileNotFoundException -> 0x0044, IOException -> 0x0081 }
        r5.append(r8);	 Catch:{ FileNotFoundException -> 0x0044, IOException -> 0x0081 }
        goto L_0x0038;
    L_0x0044:
        r0 = move-exception;
        cn.jpush.android.util.ac.m1581b();	 Catch:{ all -> 0x005e }
        r0 = r6;
    L_0x0049:
        r5 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1));
        if (r5 != 0) goto L_0x0061;
    L_0x004d:
        r0 = r4;
    L_0x004e:
        monitor-exit(r10);
        return r0;
    L_0x0050:
        r12.close();	 Catch:{ FileNotFoundException -> 0x0044, IOException -> 0x0081 }
        r2 = r5.toString();	 Catch:{ FileNotFoundException -> 0x0044, IOException -> 0x0081 }
        goto L_0x0049;
    L_0x0058:
        r0 = move-exception;
        r0 = r6;
    L_0x005a:
        cn.jpush.android.util.ac.m1591g();	 Catch:{ all -> 0x005e }
        goto L_0x0049;
    L_0x005e:
        r0 = move-exception;
        monitor-exit(r10);
        throw r0;
    L_0x0061:
        r5 = cn.jpush.android.util.an.m1657a(r3);	 Catch:{ all -> 0x005e }
        if (r5 == 0) goto L_0x006b;
    L_0x0067:
        r3 = cn.jpush.android.C0404a.m1047B();	 Catch:{ all -> 0x005e }
    L_0x006b:
        r5 = cn.jpush.android.util.an.m1657a(r3);	 Catch:{ all -> 0x005e }
        if (r5 == 0) goto L_0x0076;
    L_0x0071:
        cn.jpush.android.util.ac.m1581b();	 Catch:{ all -> 0x005e }
        r0 = r4;
        goto L_0x004e;
    L_0x0076:
        r4 = cn.jpush.android.util.C0490b.m1718j(r14);	 Catch:{ all -> 0x005e }
        r5 = cn.jpush.android.C0448e.f754f;	 Catch:{ all -> 0x005e }
        cn.jpush.android.C0404a.m1060a(r0, r2, r3, r4, r5);	 Catch:{ all -> 0x005e }
        r0 = 1;
        goto L_0x004e;
    L_0x0081:
        r5 = move-exception;
        goto L_0x005a;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.helpers.k.b(android.content.Context):boolean");
    }
}
