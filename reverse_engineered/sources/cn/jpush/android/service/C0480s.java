package cn.jpush.android.service;

import android.content.Context;

/* renamed from: cn.jpush.android.service.s */
public final class C0480s {
    /* renamed from: z */
    private static final String[] f899z;

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
        r2 = "h-0gk`'zzw'\u0010-fpl.\u0004gky&&aml0";
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
            case 0: goto L_0x004a;
            case 1: goto L_0x004d;
            case 2: goto L_0x0050;
            case 3: goto L_0x0053;
            default: goto L_0x001f;
        };
    L_0x001f:
        r11 = 4;
    L_0x0020:
        r11 = r11 ^ r12;
        r11 = (char) r11;
        r7[r8] = r11;
        r8 = r10 + 1;
        if (r2 != 0) goto L_0x002c;
    L_0x0028:
        r7 = r9;
        r10 = r8;
        r8 = r2;
        goto L_0x0018;
    L_0x002c:
        r7 = r2;
        r2 = r9;
    L_0x002e:
        if (r7 > r8) goto L_0x0013;
    L_0x0030:
        r7 = new java.lang.String;
        r7.<init>(r2);
        r2 = r7.intern();
        switch(r0) {
            case 0: goto L_0x0045;
            default: goto L_0x003c;
        };
    L_0x003c:
        r5[r3] = r2;
        r0 = "n& ";
        r2 = r0;
        r3 = r4;
        r5 = r6;
        r0 = r1;
        goto L_0x000b;
    L_0x0045:
        r5[r3] = r2;
        f899z = r6;
        return;
    L_0x004a:
        r11 = 9;
        goto L_0x0020;
    L_0x004d:
        r11 = 67;
        goto L_0x0020;
    L_0x0050:
        r11 = 84;
        goto L_0x0020;
    L_0x0053:
        r11 = 21;
        goto L_0x0020;
    L_0x0056:
        r8 = r1;
        goto L_0x002e;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.service.s.<clinit>():void");
    }

    /* renamed from: a */
    public static String m1536a(Context context, String str) {
        try {
            Class loadClass = context.getClassLoader().loadClass(f899z[0]);
            return (String) loadClass.getMethod(f899z[1], new Class[]{String.class}).invoke(loadClass, new Object[]{new String(str)});
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e2) {
            return "";
        }
    }

    /* renamed from: a */
    public static String m1537a(Context context, String str, String str2) {
        try {
            Class loadClass = context.getClassLoader().loadClass(f899z[0]);
            return (String) loadClass.getMethod(f899z[1], new Class[]{String.class, String.class}).invoke(loadClass, new Object[]{new String(str), new String(str2)});
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e2) {
            return str2;
        }
    }
}
