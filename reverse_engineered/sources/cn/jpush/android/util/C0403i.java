package cn.jpush.android.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: cn.jpush.android.util.i */
public abstract class C0403i {
    /* renamed from: a */
    private static SharedPreferences f492a = null;
    /* renamed from: z */
    private static final String[] f493z;

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
        r3 = 2;
        r2 = 1;
        r1 = 0;
        r0 = 4;
        r5 = new java.lang.String[r0];
        r4 = "\fk}N7\u001av;\n7\u001d`5A5\nk0A4Asa";
        r0 = -1;
        r6 = r5;
        r7 = r5;
        r5 = r1;
    L_0x000c:
        r4 = r4.toCharArray();
        r8 = r4.length;
        if (r8 > r2) goto L_0x006d;
    L_0x0013:
        r9 = r1;
    L_0x0014:
        r10 = r4;
        r11 = r9;
        r14 = r8;
        r8 = r4;
        r4 = r14;
    L_0x0019:
        r13 = r8[r9];
        r12 = r11 % 5;
        switch(r12) {
            case 0: goto L_0x0062;
            case 1: goto L_0x0065;
            case 2: goto L_0x0067;
            case 3: goto L_0x006a;
            default: goto L_0x0020;
        };
    L_0x0020:
        r12 = 71;
    L_0x0022:
        r12 = r12 ^ r13;
        r12 = (char) r12;
        r8[r9] = r12;
        r9 = r11 + 1;
        if (r4 != 0) goto L_0x002e;
    L_0x002a:
        r8 = r10;
        r11 = r9;
        r9 = r4;
        goto L_0x0019;
    L_0x002e:
        r8 = r4;
        r4 = r10;
    L_0x0030:
        if (r8 > r9) goto L_0x0014;
    L_0x0032:
        r8 = new java.lang.String;
        r8.<init>(r4);
        r4 = r8.intern();
        switch(r0) {
            case 0: goto L_0x0047;
            case 1: goto L_0x0050;
            case 2: goto L_0x005a;
            default: goto L_0x003e;
        };
    L_0x003e:
        r6[r5] = r4;
        r0 = ".@\u0000\u000b\u0004-F|t\f,Vft&\u000ba:J ";
        r4 = r0;
        r5 = r2;
        r6 = r7;
        r0 = r1;
        goto L_0x000c;
    L_0x0047:
        r6[r5] = r4;
        r0 = ".@\u0000";
        r4 = r0;
        r5 = r3;
        r6 = r7;
        r0 = r2;
        goto L_0x000c;
    L_0x0050:
        r6[r5] = r4;
        r4 = 3;
        r0 = "+C\u0012\u001cs-4cfp.F\u0017`uZ";
        r5 = r4;
        r6 = r7;
        r4 = r0;
        r0 = r3;
        goto L_0x000c;
    L_0x005a:
        r6[r5] = r4;
        f493z = r7;
        r0 = 0;
        f492a = r0;
        return;
    L_0x0062:
        r12 = 111; // 0x6f float:1.56E-43 double:5.5E-322;
        goto L_0x0022;
    L_0x0065:
        r12 = 5;
        goto L_0x0022;
    L_0x0067:
        r12 = 83;
        goto L_0x0022;
    L_0x006a:
        r12 = 36;
        goto L_0x0022;
    L_0x006d:
        r9 = r1;
        goto L_0x0030;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.i.<clinit>():void");
    }

    /* renamed from: a */
    protected static void m1032a(Context context, String str, int i) {
        C0403i.m1045q(context);
        Editor edit = f492a.edit();
        edit.putInt(str, i);
        edit.apply();
    }

    /* renamed from: a */
    protected static void m1033a(Context context, String str, long j) {
        C0403i.m1045q(context);
        Editor edit = f492a.edit();
        edit.putLong(str, j);
        edit.apply();
    }

    /* renamed from: a */
    protected static void m1034a(String str, int i) {
        Editor edit = f492a.edit();
        edit.putInt(str, i);
        edit.apply();
    }

    /* renamed from: a */
    protected static void m1035a(String str, long j) {
        Editor edit = f492a.edit();
        edit.putLong(str, j);
        edit.apply();
    }

    /* renamed from: b */
    protected static int m1036b(String str, int i) {
        return f492a.getInt(str, i);
    }

    /* renamed from: b */
    protected static long m1037b(String str, long j) {
        return f492a.getLong(str, j);
    }

    /* renamed from: b */
    protected static void m1038b(Context context, String str, String str2) {
        C0403i.m1045q(context);
        Editor edit = f492a.edit();
        edit.putString(str, str2);
        edit.apply();
    }

    /* renamed from: c */
    protected static String m1039c(Context context, String str, String str2) {
        C0403i.m1045q(context);
        return f492a.getString(str, str2);
    }

    /* renamed from: c */
    protected static void m1040c(String str, String str2) {
        if (f492a != null) {
            Editor edit = f492a.edit();
            edit.putString(str, str2);
            edit.apply();
        }
    }

    /* renamed from: d */
    protected static String m1041d(String str, String str2) {
        return f492a.getString(str, str2);
    }

    /* renamed from: e */
    public static String m1042e(String str, String str2) {
        try {
            str2 = C0489a.m1575a(str, f493z[3]);
        } catch (Exception e) {
            ac.m1586d();
        }
        return str2;
    }

    /* renamed from: n */
    public static void m1043n(String str) {
        Editor edit = f492a.edit();
        edit.remove(str);
        edit.commit();
    }

    /* renamed from: o */
    public static String m1044o(String str) {
        String str2 = "";
        try {
            String str3 = f493z[3];
            if (str3.length() != 16) {
                return null;
            }
            Key secretKeySpec = new SecretKeySpec(str3.getBytes(), f493z[2]);
            Cipher instance = Cipher.getInstance(f493z[1]);
            instance.init(1, secretKeySpec, new IvParameterSpec(str3.getBytes()));
            return C0493e.m1749a(instance.doFinal(str.getBytes()), 2);
        } catch (Exception e) {
            ac.m1586d();
            return str2;
        }
    }

    /* renamed from: q */
    public static void m1045q(Context context) {
        if (f492a == null) {
            f492a = context.getSharedPreferences(f493z[0], 0);
        }
    }
}
