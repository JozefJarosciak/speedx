package cn.jpush.android.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;

/* renamed from: cn.jpush.android.util.k */
public final class C0498k {
    /* renamed from: z */
    private static final String[] f1039z;

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
        r4 = 3;
        r3 = 2;
        r2 = 1;
        r1 = 0;
        r0 = 5;
        r6 = new java.lang.String[r0];
        r5 = "4buN\t$?0\b\u0016/g0\u001d\u00012|u\u001cD)y*N";
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
        r13 = 100;
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
        r0 = "`nN\n/~0\u0000\u0001%n0\r\f%i{N)\u0004?0\r\u000b$o<N\u0016%~e\u001c\n`~b\u001b\u0001";
        r5 = r0;
        r6 = r2;
        r7 = r8;
        r0 = r1;
        goto L_0x000d;
    L_0x0048:
        r7[r6] = r5;
        r0 = "-n%N\r.*d\u0006\u0001`i|\u0007\u00014*v\u0007\b%00";
        r5 = r0;
        r6 = r3;
        r7 = r8;
        r0 = r2;
        goto L_0x000d;
    L_0x0051:
        r7[r6] = r5;
        r0 = "-n%N\u00022e}N\u0017%xf\u000b\u0016`yy\n\u0001z*";
        r5 = r0;
        r6 = r4;
        r7 = r8;
        r0 = r3;
        goto L_0x000d;
    L_0x005a:
        r7[r6] = r5;
        r5 = 4;
        r0 = "\rN%";
        r6 = r5;
        r7 = r8;
        r5 = r0;
        r0 = r4;
        goto L_0x000d;
    L_0x0064:
        r7[r6] = r5;
        f1039z = r8;
        return;
    L_0x0069:
        r13 = 64;
        goto L_0x0023;
    L_0x006c:
        r13 = 10;
        goto L_0x0023;
    L_0x006f:
        r13 = 16;
        goto L_0x0023;
    L_0x0072:
        r13 = 110; // 0x6e float:1.54E-43 double:5.43E-322;
        goto L_0x0023;
    L_0x0075:
        r10 = r1;
        goto L_0x0031;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.k.<clinit>():void");
    }

    /* renamed from: a */
    public static boolean m1759a(String str, File file) {
        new StringBuilder(f1039z[3]).append(str);
        ac.m1581b();
        if (str == null || "".equals(str)) {
            new StringBuilder(f1039z[0]).append(str).append(f1039z[1]);
            ac.m1581b();
            return true;
        } else if (!file.exists() || file.length() == 0) {
            return false;
        } else {
            String b = C0498k.m1761b(file);
            new StringBuilder(f1039z[2]).append(b);
            ac.m1581b();
            if (b == null || "".equals(b) || !b.equals(str)) {
                ac.m1581b();
                return false;
            }
            ac.m1581b();
            return true;
        }
    }

    /* renamed from: a */
    private static byte[] m1760a(File file) {
        InputStream inputStream;
        Throwable th;
        InputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                byte[] bArr = new byte[1024];
                MessageDigest instance = MessageDigest.getInstance(f1039z[4]);
                int read;
                do {
                    read = fileInputStream.read(bArr);
                    if (read > 0) {
                        instance.update(bArr, 0, read);
                    }
                } while (read != -1);
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                        ac.m1591g();
                        return null;
                    }
                }
                return instance.digest();
            } catch (Exception e2) {
                try {
                    ac.m1591g();
                    if (fileInputStream != null) {
                        return null;
                    }
                    try {
                        fileInputStream.close();
                        return null;
                    } catch (IOException e3) {
                        ac.m1591g();
                        return null;
                    }
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    inputStream = fileInputStream;
                    th = th3;
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e4) {
                            ac.m1591g();
                            return null;
                        }
                    }
                    throw th;
                }
            }
        } catch (Exception e5) {
            fileInputStream = null;
            ac.m1591g();
            if (fileInputStream != null) {
                return null;
            }
            fileInputStream.close();
            return null;
        } catch (Throwable th4) {
            th = th4;
            inputStream = null;
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
    }

    /* renamed from: b */
    private static String m1761b(File file) {
        byte[] a = C0498k.m1760a(file);
        String str = "";
        if (a != null && a.length > 0) {
            for (byte b : a) {
                str = str + Integer.toString((b & 255) + 256, 16).substring(1);
            }
        }
        return str;
    }
}
