package cn.jpush.android.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import cn.jpush.android.util.ac;

/* renamed from: cn.jpush.android.data.g */
final class C0435g extends SQLiteOpenHelper {
    /* renamed from: z */
    private static final String[] f657z;

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
        r12 = 4;
        r2 = 1;
        r1 = 0;
        r0 = 3;
        r4 = new java.lang.String[r0];
        r3 = "@VRd\u001ePE_x{$M[\u0014{\\MN`m$pBXQgeqZQpm{]]ept[P";
        r0 = -1;
        r5 = r4;
        r6 = r4;
        r4 = r1;
    L_0x000c:
        r3 = r3.toCharArray();
        r7 = r3.length;
        if (r7 > r2) goto L_0x0060;
    L_0x0013:
        r8 = r1;
    L_0x0014:
        r9 = r3;
        r10 = r8;
        r14 = r7;
        r7 = r3;
        r3 = r14;
    L_0x0019:
        r13 = r7[r8];
        r11 = r10 % 5;
        switch(r11) {
            case 0: goto L_0x0056;
            case 1: goto L_0x0058;
            case 2: goto L_0x005a;
            case 3: goto L_0x005d;
            default: goto L_0x0020;
        };
    L_0x0020:
        r11 = 62;
    L_0x0022:
        r11 = r11 ^ r13;
        r11 = (char) r11;
        r7[r8] = r11;
        r8 = r10 + 1;
        if (r3 != 0) goto L_0x002e;
    L_0x002a:
        r7 = r9;
        r10 = r8;
        r8 = r3;
        goto L_0x0019;
    L_0x002e:
        r7 = r3;
        r3 = r9;
    L_0x0030:
        if (r7 > r8) goto L_0x0014;
    L_0x0032:
        r7 = new java.lang.String;
        r7.<init>(r3);
        r3 = r7.intern();
        switch(r0) {
            case 0: goto L_0x0047;
            case 1: goto L_0x0051;
            default: goto L_0x003e;
        };
    L_0x003e:
        r5[r4] = r3;
        r0 = "GVXujA$Iu|HA=@ahk~URjki]Xmg|@Wkj=\u001cam`=}pPAZql$TO}sEVD\u0014uA]=ukPKTz}VAPqpP$1XP[my\u0014Rkjz\u0014Pkp=ZKhh1XP[grAPp$tZJacxF\u001ejki\u0014Pqhq\u0018Rj[oQSkrx\u0014WjpxS[v$s[J$jhXR(hskJ}tx\u0014WjpxS[v$s[J$jhXR(hsk[|poU\u001epae@\u001e(hskJvmzS[v[i]Sa$q[Pc$1XP[eyPapmpQ\u001ehksS\u001e-?";
        r3 = r0;
        r4 = r2;
        r5 = r6;
        r0 = r1;
        goto L_0x000c;
    L_0x0047:
        r5[r4] = r3;
        r3 = 2;
        r0 = "nthGV[hrW_h[s[JmbtW_pmrZ\u0010`f";
        r4 = r3;
        r5 = r6;
        r3 = r0;
        r0 = r2;
        goto L_0x000c;
    L_0x0051:
        r5[r4] = r3;
        f657z = r6;
        return;
    L_0x0056:
        r11 = r12;
        goto L_0x0022;
    L_0x0058:
        r11 = r12;
        goto L_0x0022;
    L_0x005a:
        r11 = 29;
        goto L_0x0022;
    L_0x005d:
        r11 = 52;
        goto L_0x0022;
    L_0x0060:
        r8 = r1;
        goto L_0x0030;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.data.g.<clinit>():void");
    }

    public C0435g(Context context) {
        this(context, f657z[2], null, 1);
    }

    private C0435g(Context context, String str, CursorFactory cursorFactory, int i) {
        super(context, str, null, 1);
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL(f657z[1]);
        } catch (Exception e) {
            ac.m1576a();
        }
    }

    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL(f657z[0]);
        onCreate(sQLiteDatabase);
    }
}
