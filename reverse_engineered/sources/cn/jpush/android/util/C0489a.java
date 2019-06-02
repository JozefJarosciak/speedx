package cn.jpush.android.util;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: cn.jpush.android.util.a */
public final class C0489a {
    /* renamed from: z */
    private static final String[] f935z;

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
        r5 = 2;
        r2 = 1;
        r1 = 0;
        r0 = 3;
        r4 = new java.lang.String[r0];
        r3 = "JBQ\u000f-ID-p%HT7p\u000fockN\t";
        r0 = -1;
        r6 = r4;
        r7 = r4;
        r4 = r1;
    L_0x000c:
        r3 = r3.toCharArray();
        r8 = r3.length;
        if (r8 > r2) goto L_0x005f;
    L_0x0013:
        r9 = r1;
    L_0x0014:
        r10 = r3;
        r11 = r9;
        r14 = r8;
        r8 = r3;
        r3 = r14;
    L_0x0019:
        r13 = r8[r9];
        r12 = r11 % 5;
        switch(r12) {
            case 0: goto L_0x0055;
            case 1: goto L_0x0058;
            case 2: goto L_0x005a;
            case 3: goto L_0x005c;
            default: goto L_0x0020;
        };
    L_0x0020:
        r12 = 110; // 0x6e float:1.54E-43 double:5.43E-322;
    L_0x0022:
        r12 = r12 ^ r13;
        r12 = (char) r12;
        r8[r9] = r12;
        r9 = r11 + 1;
        if (r3 != 0) goto L_0x002e;
    L_0x002a:
        r8 = r10;
        r11 = r9;
        r9 = r3;
        goto L_0x0019;
    L_0x002e:
        r8 = r3;
        r3 = r10;
    L_0x0030:
        if (r8 > r9) goto L_0x0014;
    L_0x0032:
        r8 = new java.lang.String;
        r8.<init>(r3);
        r3 = r8.intern();
        switch(r0) {
            case 0: goto L_0x0047;
            case 1: goto L_0x0050;
            default: goto L_0x003e;
        };
    L_0x003e:
        r6[r4] = r3;
        r0 = "JTAi'";
        r3 = r0;
        r4 = r2;
        r6 = r7;
        r0 = r1;
        goto L_0x000c;
    L_0x0047:
        r6[r4] = r3;
        r0 = "JBQ";
        r3 = r0;
        r4 = r5;
        r6 = r7;
        r0 = r2;
        goto L_0x000c;
    L_0x0050:
        r6[r4] = r3;
        f935z = r7;
        return;
    L_0x0055:
        r12 = 11;
        goto L_0x0022;
    L_0x0058:
        r12 = 7;
        goto L_0x0022;
    L_0x005a:
        r12 = r5;
        goto L_0x0022;
    L_0x005c:
        r12 = 32;
        goto L_0x0022;
    L_0x005f:
        r9 = r1;
        goto L_0x0030;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.a.<clinit>():void");
    }

    /* renamed from: a */
    public static String m1575a(String str, String str2) {
        if (str2 == null) {
            return null;
        }
        try {
            if (str2.length() != 16) {
                return null;
            }
            Key secretKeySpec = new SecretKeySpec(str2.getBytes(f935z[1]), f935z[2]);
            Cipher instance = Cipher.getInstance(f935z[0]);
            instance.init(2, secretKeySpec, new IvParameterSpec(str2.getBytes()));
            try {
                return new String(instance.doFinal(C0493e.m1750a(str, 2)));
            } catch (Exception e) {
                return null;
            }
        } catch (Exception e2) {
            return null;
        }
    }
}
