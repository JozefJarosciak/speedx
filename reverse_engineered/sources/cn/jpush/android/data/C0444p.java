package cn.jpush.android.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import cn.jpush.android.util.ac;

/* renamed from: cn.jpush.android.data.p */
final class C0444p extends SQLiteOpenHelper {
    /* renamed from: z */
    private static final String[] f728z;

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
        r3 = "&w\"\u0012q\u0013t#\u0000m%t#\bz?)3\u0003";
        r0 = -1;
        r5 = r4;
        r6 = r4;
        r4 = r1;
    L_0x000b:
        r3 = r3.toCharArray();
        r7 = r3.length;
        if (r7 > r2) goto L_0x0060;
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
            case 2: goto L_0x005a;
            case 3: goto L_0x005d;
            default: goto L_0x001f;
        };
    L_0x001f:
        r11 = 25;
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
        r0 = "\u000fU\u0012 M\t'\u0003 [\u0000Bw\u000bi9t?>j8f#\bj8n4\u00129dX>\u00059\u0005I\u0003$^\tUw1K\u0005J\u00163@lL\u001289\rR\u0003.P\u0002D\u0005$T\tI\u0003A5?s\b\u0012v>s\b\n|5'#\u0004a8'9\u000emli\"\ru`t#>w)sw\u0015|4sw\u000fv8'9\u0014u +$\u0015F/h9\u000fF%ww\u0015|4sw\u000fv8'9\u0014u +$\u0015F h4\u0000u\u0013c9\u001298b/\u00155?s\b\u0012v9u4\u00049%i#\u0004~)uw\u000fv8'9\u0014u +$\u0015F*f>\r|('>\u000fm)`2\u00139\"h#Aw9k;Mj8X#\u000em-kw\bw8b0\u0004kli8\u00159\"r;\r5?s\b\u0002v9i#>(ln9\u0015|+b%Mj8X4\u000el\"s\bPF'>\u000fm)`2\u00135?s\b\u0002v9i#>*\u00136gAp\"s2\u0006|>+$\u0015F/h\"\u000fm\u00136gAp\"s2\u0006|>.l";
        r3 = r0;
        r4 = r2;
        r5 = r6;
        r0 = r1;
        goto L_0x000b;
    L_0x0046:
        r5[r4] = r3;
        r3 = 2;
        r0 = "\bU\u001819\u0018F\u0015-\\lN\u0011A\\\u0014N\u00045Jlm'\u0014j$X$\u0015x8n$\u0015p/t";
        r4 = r3;
        r5 = r6;
        r3 = r0;
        r0 = r2;
        goto L_0x000b;
    L_0x0050:
        r5[r4] = r3;
        f728z = r6;
        return;
    L_0x0055:
        r11 = 76;
        goto L_0x0021;
    L_0x0058:
        r11 = 7;
        goto L_0x0021;
    L_0x005a:
        r11 = 87;
        goto L_0x0021;
    L_0x005d:
        r11 = 97;
        goto L_0x0021;
    L_0x0060:
        r8 = r1;
        goto L_0x002f;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.data.p.<clinit>():void");
    }

    public C0444p(Context context) {
        this(context, f728z[0], null, 1);
    }

    private C0444p(Context context, String str, CursorFactory cursorFactory, int i) {
        super(context, str, null, 1);
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL(f728z[1]);
        } catch (Exception e) {
            ac.m1576a();
        }
    }

    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL(f728z[2]);
        onCreate(sQLiteDatabase);
    }
}
