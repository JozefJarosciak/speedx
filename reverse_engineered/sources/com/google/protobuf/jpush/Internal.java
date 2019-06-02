package com.google.protobuf.jpush;

import ch.qos.logback.core.net.SyslogConstants;
import com.alibaba.fastjson.asm.Opcodes;

public class Internal {
    /* renamed from: z */
    private static final String[] f14606z;

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
        r3 = "{w[cB\n\u0011-cK";
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
        r11 = 122; // 0x7a float:1.71E-43 double:6.03E-322;
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
        r0 = "gpRcB";
        r3 = r0;
        r4 = r2;
        r5 = r6;
        r0 = r1;
        goto L_0x000b;
    L_0x0046:
        r5[r4] = r3;
        r3 = 2;
        r0 = "xEb/Zdi4*\u0015WW4 \u0015F\u0004g;\nBKf:ZS\u0004g:\u001b\\@u<\u001e\u0012G|/\bSG`+\b\u0012Wq:T";
        r4 = r3;
        r5 = r6;
        r3 = r0;
        r0 = r2;
        goto L_0x000b;
    L_0x0050:
        r5[r4] = r3;
        f14606z = r6;
        return;
    L_0x0055:
        r11 = 50;
        goto L_0x0021;
    L_0x0058:
        r11 = 36;
        goto L_0x0021;
    L_0x005b:
        r11 = 20;
        goto L_0x0021;
    L_0x005e:
        r11 = 78;
        goto L_0x0021;
    L_0x0061:
        r8 = r1;
        goto L_0x002f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.jpush.Internal.<clinit>():void");
    }

    public static C4071c bytesDefaultValue(String str) {
        try {
            return C4071c.m16420a(str.getBytes(f14606z[0]));
        } catch (Throwable e) {
            throw new IllegalStateException(f14606z[2], e);
        }
    }

    public static boolean isValidUtf8(C4071c c4071c) {
        int a = c4071c.m16423a();
        int i = 0;
        while (i < a) {
            int i2 = i + 1;
            int a2 = c4071c.m16422a(i) & 255;
            if (a2 < 128) {
                i = i2;
            } else if (a2 < 194 || a2 > 244) {
                return false;
            } else {
                if (i2 >= a) {
                    return false;
                }
                int i3 = i2 + 1;
                i2 = c4071c.m16422a(i2) & 255;
                if (i2 < 128 || i2 > 191) {
                    return false;
                }
                if (a2 > 223) {
                    if (i3 >= a) {
                        return false;
                    }
                    i = i3 + 1;
                    i3 = c4071c.m16422a(i3) & 255;
                    if (i3 < 128 || i3 > 191) {
                        return false;
                    }
                    if (a2 <= 239) {
                        if ((a2 == 224 && i2 < 160) || (a2 == 237 && i2 > Opcodes.IF_ICMPEQ)) {
                            return false;
                        }
                    } else if (i >= a) {
                        return false;
                    } else {
                        i3 = i + 1;
                        i = c4071c.m16422a(i) & 255;
                        if (i < 128 || i > 191) {
                            return false;
                        }
                        if ((a2 == 240 && i2 < SyslogConstants.LOG_LOCAL2) || (a2 == 244 && i2 > 143)) {
                            return false;
                        }
                    }
                }
                i = i3;
            }
        }
        return true;
    }

    public static String stringDefaultValue(String str) {
        try {
            return new String(str.getBytes(f14606z[0]), f14606z[1]);
        } catch (Throwable e) {
            throw new IllegalStateException(f14606z[2], e);
        }
    }
}
