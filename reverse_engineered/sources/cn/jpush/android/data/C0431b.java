package cn.jpush.android.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import cn.jpush.android.util.ac;

/* renamed from: cn.jpush.android.data.b */
public final class C0431b extends SQLiteOpenHelper {
    /* renamed from: a */
    public static final String[] f638a;
    /* renamed from: b */
    public static final String[] f639b;
    /* renamed from: c */
    private static C0431b f640c;
    /* renamed from: z */
    private static final String[] f641z;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 13;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "WL s\u0003V";
        r0 = -1;
        r4 = r3;
    L_0x0009:
        r1 = r1.toCharArray();
        r5 = r1.length;
        r6 = 0;
        r7 = 1;
        if (r5 > r7) goto L_0x002e;
    L_0x0012:
        r7 = r1;
        r8 = r6;
        r11 = r5;
        r5 = r1;
        r1 = r11;
    L_0x0017:
        r10 = r5[r6];
        r9 = r8 % 5;
        switch(r9) {
            case 0: goto L_0x00b7;
            case 1: goto L_0x00bb;
            case 2: goto L_0x00bf;
            case 3: goto L_0x00c3;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 112; // 0x70 float:1.57E-43 double:5.53E-322;
    L_0x0020:
        r9 = r9 ^ r10;
        r9 = (char) r9;
        r5[r6] = r9;
        r6 = r8 + 1;
        if (r1 != 0) goto L_0x002c;
    L_0x0028:
        r5 = r7;
        r8 = r6;
        r6 = r1;
        goto L_0x0017;
    L_0x002c:
        r5 = r1;
        r1 = r7;
    L_0x002e:
        if (r5 > r6) goto L_0x0012;
    L_0x0030:
        r5 = new java.lang.String;
        r5.<init>(r1);
        r1 = r5.intern();
        switch(r0) {
            case 0: goto L_0x0044;
            case 1: goto L_0x004c;
            case 2: goto L_0x0054;
            case 3: goto L_0x005c;
            case 4: goto L_0x0064;
            case 5: goto L_0x006c;
            case 6: goto L_0x0074;
            case 7: goto L_0x007d;
            case 8: goto L_0x0087;
            case 9: goto L_0x0092;
            case 10: goto L_0x009d;
            case 11: goto L_0x00a8;
            case 12: goto L_0x00c7;
            case 13: goto L_0x00d9;
            case 14: goto L_0x00e3;
            case 15: goto L_0x00ed;
            case 16: goto L_0x00f7;
            case 17: goto L_0x0106;
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = "OO+E\u0019F";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "CL<E\u0019F\u0001s";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "MJ)h\u0002KX)E\u0019F";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "CL<E\u0019F";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "O]%t/KX";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "QY>l\u0019AYb~\u0012";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "FN#jPV].v\u0015\u0002I<v\u0019QH";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = "FN#jPV].v\u0015\u0002X#m\u001eNU?n";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        r2 = 9;
        r1 = "vT):\u001fNX\u001a\u0002QU#tPKOv:";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0087:
        r3[r2] = r1;
        r2 = 10;
        r1 = "\u0002H$PLY;L\u0015PO%u\u001e\u0002U?:J\u0002";
        r0 = 9;
        r3 = r4;
        goto L_0x0009;
    L_0x0092:
        r3[r2] = r1;
        r2 = 11;
        r1 = "AN){\u0004G\u001c8{\u0012NYlo\u0000NU?nX}U(:\u0019LH)}\u0015P\u001c<h\u0019O]>cPIY5:\u0011WH#s\u001eAN)w\u0015LH`w\u0003Ec%~PVY4n\\CL<E\u0019F\u001c8\bV\u0010!{\u0019Lc%~PVY4n\\\u0002S:\u0002PU(/KXln\u0015ZHe";
        r0 = 10;
        r3 = r4;
        goto L_0x0009;
    L_0x009d:
        r3[r2] = r1;
        r2 = 12;
        r1 = "AN){\u0004G\u001c8{\u0012NYl~\u001fUR s\u0003V\u0014\u0013s\u0014\u0002U\"n\u0015EY>:\u0000PU!{\u0002[\u001c'\t\u0002]9n\u001fKR/h\u0015OY\"n\\OO+E\u0019F\u001c8\bV\u0010>\u0000G]8E\u001eWQls\u001eVY+\u0002\u000eO8{\u0002Vc<u\u0003\u0002U\"n\u0015EY>6\u0015LX\u0013j\u001fQ\u001c%t\u0004G[)h\\AS\"n\u0015LHln\u0015ZHe";
        r0 = 11;
        r3 = r4;
        goto L_0x0009;
    L_0x00a8:
        r3[r2] = r1;
        f641z = r4;
        r0 = 6;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "}U(";
        r0 = 12;
        r4 = r3;
        goto L_0x0009;
    L_0x00b7:
        r9 = 34;
        goto L_0x0020;
    L_0x00bb:
        r9 = 60;
        goto L_0x0020;
    L_0x00bf:
        r9 = 76;
        goto L_0x0020;
    L_0x00c3:
        r9 = 26;
        goto L_0x0020;
    L_0x00c7:
        r3[r2] = r1;
        r0 = 1;
        r1 = f641z;
        r2 = 1;
        r1 = r1[r2];
        r4[r0] = r1;
        r2 = 2;
        r1 = "PY<\u0011Vc\"o\u001d";
        r0 = 13;
        r3 = r4;
        goto L_0x0009;
    L_0x00d9:
        r3[r2] = r1;
        r2 = 3;
        r1 = "QH-h\u0004}L#i";
        r0 = 14;
        r3 = r4;
        goto L_0x0009;
    L_0x00e3:
        r3[r2] = r1;
        r2 = 4;
        r1 = "GR(E\u0000MO";
        r0 = 15;
        r3 = r4;
        goto L_0x0009;
    L_0x00ed:
        r3[r2] = r1;
        r2 = 5;
        r1 = "AS\"n\u0015LH";
        r0 = 16;
        r3 = r4;
        goto L_0x0009;
    L_0x00f7:
        r3[r2] = r1;
        f638a = r4;
        r0 = 4;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "}U(";
        r0 = 17;
        r4 = r3;
        goto L_0x0009;
    L_0x0106:
        r3[r2] = r1;
        r0 = 1;
        r1 = f641z;
        r2 = 1;
        r1 = r1[r2];
        r4[r0] = r1;
        r0 = 2;
        r1 = f641z;
        r2 = 4;
        r1 = r1[r2];
        r4[r0] = r1;
        r0 = 3;
        r1 = f641z;
        r2 = 5;
        r1 = r1[r2];
        r4[r0] = r1;
        f639b = r4;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.data.b.<clinit>():void");
    }

    private C0431b(Context context) {
        super(context, f641z[6], null, 3);
    }

    /* renamed from: a */
    private static C0431b m1282a(Context context) {
        if (f640c == null) {
            f640c = new C0431b(context);
        }
        return f640c;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    public static synchronized java.lang.String m1283a(android.content.Context r16, java.lang.String r17) {
        /*
        r14 = cn.jpush.android.data.C0431b.class;
        monitor-enter(r14);
        r10 = "";
        r13 = "";
        r12 = "";
        r11 = "";
        r9 = 0;
        r1 = cn.jpush.android.data.C0431b.m1282a(r16);	 Catch:{ Exception -> 0x00ae, all -> 0x00ba }
        r1 = r1.getWritableDatabase();	 Catch:{ Exception -> 0x00ae, all -> 0x00ba }
        r2 = f641z;	 Catch:{ Exception -> 0x00ae, all -> 0x00ba }
        r3 = 0;
        r2 = r2[r3];	 Catch:{ Exception -> 0x00ae, all -> 0x00ba }
        r3 = f639b;	 Catch:{ Exception -> 0x00ae, all -> 0x00ba }
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r2 = r1.query(r2, r3, r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x00ae, all -> 0x00ba }
        if (r2 == 0) goto L_0x00ca;
    L_0x0026:
        r3 = r2.getCount();	 Catch:{ Exception -> 0x00c4, all -> 0x00c2 }
        if (r3 < 0) goto L_0x00ca;
    L_0x002c:
        r3 = r2.moveToNext();	 Catch:{ Exception -> 0x00c4, all -> 0x00c2 }
        if (r3 == 0) goto L_0x00ca;
    L_0x0032:
        r3 = f641z;	 Catch:{ Exception -> 0x00c4, all -> 0x00c2 }
        r4 = 4;
        r3 = r3[r4];	 Catch:{ Exception -> 0x00c4, all -> 0x00c2 }
        r3 = r2.getColumnIndex(r3);	 Catch:{ Exception -> 0x00c4, all -> 0x00c2 }
        r4 = r2.getString(r3);	 Catch:{ Exception -> 0x00c4, all -> 0x00c2 }
        r3 = f641z;	 Catch:{ Exception -> 0x00c4, all -> 0x00c2 }
        r5 = 1;
        r3 = r3[r5];	 Catch:{ Exception -> 0x00c4, all -> 0x00c2 }
        r3 = r2.getColumnIndex(r3);	 Catch:{ Exception -> 0x00c4, all -> 0x00c2 }
        r6 = r2.getString(r3);	 Catch:{ Exception -> 0x00c4, all -> 0x00c2 }
        r3 = f641z;	 Catch:{ Exception -> 0x00c4, all -> 0x00c2 }
        r5 = 5;
        r3 = r3[r5];	 Catch:{ Exception -> 0x00c4, all -> 0x00c2 }
        r3 = r2.getColumnIndex(r3);	 Catch:{ Exception -> 0x00c4, all -> 0x00c2 }
        r5 = r2.getString(r3);	 Catch:{ Exception -> 0x00c4, all -> 0x00c2 }
        r3 = f641z;	 Catch:{ Exception -> 0x00c4, all -> 0x00c2 }
        r7 = 3;
        r3 = r3[r7];	 Catch:{ Exception -> 0x00c4, all -> 0x00c2 }
        r3 = r2.getColumnIndex(r3);	 Catch:{ Exception -> 0x00c4, all -> 0x00c2 }
        r3 = r2.getString(r3);	 Catch:{ Exception -> 0x00c4, all -> 0x00c2 }
        r0 = r17;
        r7 = r0.endsWith(r4);	 Catch:{ Exception -> 0x00c4, all -> 0x00c2 }
        if (r7 == 0) goto L_0x002c;
    L_0x006e:
        r15 = r3;
        r3 = r6;
        r6 = r5;
        r5 = r4;
        r4 = r15;
    L_0x0073:
        r7 = f641z;	 Catch:{ Exception -> 0x00c7, all -> 0x00c2 }
        r8 = 0;
        r7 = r7[r8];	 Catch:{ Exception -> 0x00c7, all -> 0x00c2 }
        r8 = f641z;	 Catch:{ Exception -> 0x00c7, all -> 0x00c2 }
        r9 = 2;
        r8 = r8[r9];	 Catch:{ Exception -> 0x00c7, all -> 0x00c2 }
        r9 = 1;
        r9 = new java.lang.String[r9];	 Catch:{ Exception -> 0x00c7, all -> 0x00c2 }
        r10 = 0;
        r9[r10] = r5;	 Catch:{ Exception -> 0x00c7, all -> 0x00c2 }
        r1.delete(r7, r8, r9);	 Catch:{ Exception -> 0x00c7, all -> 0x00c2 }
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00c7, all -> 0x00c2 }
        r1.<init>();	 Catch:{ Exception -> 0x00c7, all -> 0x00c2 }
        r1 = r1.append(r3);	 Catch:{ Exception -> 0x00c7, all -> 0x00c2 }
        r5 = ",";
        r1 = r1.append(r5);	 Catch:{ Exception -> 0x00c7, all -> 0x00c2 }
        r1 = r1.append(r6);	 Catch:{ Exception -> 0x00c7, all -> 0x00c2 }
        r5 = ",";
        r1 = r1.append(r5);	 Catch:{ Exception -> 0x00c7, all -> 0x00c2 }
        r1 = r1.append(r4);	 Catch:{ Exception -> 0x00c7, all -> 0x00c2 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x00c7, all -> 0x00c2 }
        if (r2 == 0) goto L_0x00ac;
    L_0x00a9:
        r2.close();	 Catch:{ all -> 0x00b7 }
    L_0x00ac:
        monitor-exit(r14);
        return r1;
    L_0x00ae:
        r1 = move-exception;
        r2 = r9;
        r1 = r10;
    L_0x00b1:
        if (r2 == 0) goto L_0x00ac;
    L_0x00b3:
        r2.close();	 Catch:{ all -> 0x00b7 }
        goto L_0x00ac;
    L_0x00b7:
        r1 = move-exception;
        monitor-exit(r14);
        throw r1;
    L_0x00ba:
        r1 = move-exception;
        r2 = r9;
    L_0x00bc:
        if (r2 == 0) goto L_0x00c1;
    L_0x00be:
        r2.close();	 Catch:{ all -> 0x00b7 }
    L_0x00c1:
        throw r1;	 Catch:{ all -> 0x00b7 }
    L_0x00c2:
        r1 = move-exception;
        goto L_0x00bc;
    L_0x00c4:
        r1 = move-exception;
        r1 = r10;
        goto L_0x00b1;
    L_0x00c7:
        r1 = move-exception;
        r1 = r3;
        goto L_0x00b1;
    L_0x00ca:
        r4 = r11;
        r5 = r12;
        r6 = r13;
        r3 = r10;
        goto L_0x0073;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.data.b.a(android.content.Context, java.lang.String):java.lang.String");
    }

    /* renamed from: a */
    public static synchronized void m1284a(Context context, C0429c c0429c, String str, String str2) {
        synchronized (C0431b.class) {
            C0431b.m1285a(context, c0429c.f613c, c0429c.f614d, str, str2);
        }
    }

    /* renamed from: a */
    private static synchronized void m1285a(Context context, String str, String str2, String str3, String str4) {
        Cursor cursor;
        Throwable th;
        synchronized (C0431b.class) {
            Cursor query;
            try {
                ContentValues contentValues;
                SQLiteDatabase writableDatabase = C0431b.m1282a(context).getWritableDatabase();
                query = writableDatabase.query(f641z[0], f639b, f641z[2], new String[]{str3}, null, null, null);
                if (query != null) {
                    try {
                        if (query.getCount() > 0) {
                            query.moveToFirst();
                            contentValues = new ContentValues();
                            contentValues.put(f641z[1], str);
                            contentValues.put(f641z[5], str4);
                            contentValues.put(f641z[3], str2);
                            writableDatabase.update(f641z[0], contentValues, f641z[2], new String[]{str3});
                            if (query != null) {
                                query.close();
                            }
                        }
                    } catch (Exception e) {
                        cursor = query;
                        if (cursor != null) {
                            cursor.close();
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (query != null) {
                            query.close();
                        }
                        throw th;
                    }
                }
                contentValues = new ContentValues();
                contentValues.put(f641z[1], str);
                contentValues.put(f641z[4], str3);
                contentValues.put(f641z[5], str4);
                contentValues.put(f641z[3], str2);
                writableDatabase.insert(f641z[0], null, contentValues);
                if (query != null) {
                    query.close();
                }
            } catch (Exception e2) {
                cursor = null;
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Throwable th3) {
                th = th3;
                query = null;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        }
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        ac.m1581b();
        sQLiteDatabase.execSQL(f641z[12]);
        sQLiteDatabase.execSQL(f641z[11]);
    }

    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        new StringBuilder(f641z[9]).append(i).append(f641z[10]).append(i2);
        ac.m1581b();
        sQLiteDatabase.execSQL(f641z[8]);
        sQLiteDatabase.execSQL(f641z[7]);
        onCreate(sQLiteDatabase);
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        new StringBuilder(f641z[9]).append(i).append(f641z[10]).append(i2);
        ac.m1581b();
        sQLiteDatabase.execSQL(f641z[8]);
        sQLiteDatabase.execSQL(f641z[7]);
        onCreate(sQLiteDatabase);
    }
}
