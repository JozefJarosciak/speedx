package cn.jpush.android.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

public final class an {
    /* renamed from: a */
    private static String f992a = f993z[2];
    /* renamed from: z */
    private static final String[] f993z;

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
        r3 = "\u0013BdL\u0001l\\d\u0016丢e龹e\u0010";
        r0 = -1;
        r6 = r4;
        r7 = r4;
        r4 = r1;
    L_0x000c:
        r3 = r3.toCharArray();
        r8 = r3.length;
        if (r8 > r2) goto L_0x0067;
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
            case 0: goto L_0x005b;
            case 1: goto L_0x005e;
            case 2: goto L_0x0061;
            case 3: goto L_0x0064;
            default: goto L_0x0020;
        };
    L_0x0020:
        r12 = 34;
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
        r0 = "\u0005X\r";
        r3 = r0;
        r4 = r2;
        r6 = r7;
        r0 = r1;
        goto L_0x000c;
    L_0x0047:
        r6[r4] = r3;
        r0 = "x-\n\b\u0016}*\u000f\u0003\u001b\t^{g\u000e";
        r3 = r0;
        r4 = r5;
        r6 = r7;
        r0 = r2;
        goto L_0x000c;
    L_0x0050:
        r6[r4] = r3;
        f993z = r7;
        r0 = f993z;
        r0 = r0[r5];
        f992a = r0;
        return;
    L_0x005b:
        r12 = 72;
        goto L_0x0022;
    L_0x005e:
        r12 = 28;
        goto L_0x0022;
    L_0x0061:
        r12 = 56;
        goto L_0x0022;
    L_0x0064:
        r12 = 59;
        goto L_0x0022;
    L_0x0067:
        r9 = r1;
        goto L_0x0030;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.an.<clinit>():void");
    }

    /* renamed from: a */
    public static boolean m1657a(String str) {
        return str == null || str.length() == 0 || str.trim().length() == 0;
    }

    /* renamed from: a */
    public static boolean m1658a(String str, String str2) {
        return (str == null || str2 == null) ? false : str.equals(str2);
    }

    /* renamed from: b */
    public static String m1659b(String str) {
        if (str == null || "".equals(str)) {
            return null;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance(f993z[1]);
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            if (digest == null) {
                return "";
            }
            StringBuffer stringBuffer = new StringBuffer(digest.length * 2);
            for (byte b : digest) {
                stringBuffer.append(f993z[2].charAt((b >> 4) & 15)).append(f993z[2].charAt(b & 15));
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    /* renamed from: c */
    public static String m1660c(String str) {
        return m1657a(str) ? "" : Pattern.compile(f993z[0]).matcher(str).replaceAll("");
    }
}
