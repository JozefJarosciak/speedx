package com.alipay.sdk.tid;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import com.alipay.sdk.encrypt.C0852b;
import com.alipay.sdk.util.C0873a;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* renamed from: com.alipay.sdk.tid.a */
public final class C0871a extends SQLiteOpenHelper {
    /* renamed from: a */
    private static final String f2178a = "msp.db";
    /* renamed from: b */
    private static final int f2179b = 1;
    /* renamed from: c */
    private WeakReference<Context> f2180c;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: d */
    private void m3399d(java.lang.String r7, java.lang.String r8) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0021 in list [B:4:0x0018]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
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
        r6 = this;
        r1 = 0;
        r1 = r6.getWritableDatabase();	 Catch:{ Exception -> 0x0022, all -> 0x002f }
        r4 = "";	 Catch:{ Exception -> 0x0022, all -> 0x002f }
        r5 = "";	 Catch:{ Exception -> 0x0022, all -> 0x002f }
        r0 = r6;	 Catch:{ Exception -> 0x0022, all -> 0x002f }
        r2 = r7;	 Catch:{ Exception -> 0x0022, all -> 0x002f }
        r3 = r8;	 Catch:{ Exception -> 0x0022, all -> 0x002f }
        r0.m3402a(r1, r2, r3, r4, r5);	 Catch:{ Exception -> 0x0022, all -> 0x002f }
        r0 = com.alipay.sdk.tid.C0871a.m3398c(r7, r8);	 Catch:{ Exception -> 0x0022, all -> 0x002f }
        com.alipay.sdk.tid.C0871a.m3395a(r1, r0);	 Catch:{ Exception -> 0x0022, all -> 0x002f }
        if (r1 == 0) goto L_0x0021;
    L_0x0018:
        r0 = r1.isOpen();
        if (r0 == 0) goto L_0x0021;
    L_0x001e:
        r1.close();
    L_0x0021:
        return;
    L_0x0022:
        r0 = move-exception;
        if (r1 == 0) goto L_0x0021;
    L_0x0025:
        r0 = r1.isOpen();
        if (r0 == 0) goto L_0x0021;
    L_0x002b:
        r1.close();
        goto L_0x0021;
    L_0x002f:
        r0 = move-exception;
        if (r1 == 0) goto L_0x003b;
    L_0x0032:
        r2 = r1.isOpen();
        if (r2 == 0) goto L_0x003b;
    L_0x0038:
        r1.close();
    L_0x003b:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.tid.a.d(java.lang.String, java.lang.String):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    public final void m3403a(java.lang.String r8, java.lang.String r9, java.lang.String r10, java.lang.String r11) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x001f in list [B:6:0x0016]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
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
        r7 = this;
        r1 = 0;
        r2 = 0;
        r1 = r7.getWritableDatabase();	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r0 = com.alipay.sdk.tid.C0871a.m3396a(r1, r8, r9);	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        if (r0 == 0) goto L_0x0020;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
    L_0x000c:
        r0 = r7;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r2 = r8;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r3 = r9;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r4 = r10;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r5 = r11;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r0.m3402a(r1, r2, r3, r4, r5);	 Catch:{ Exception -> 0x0059, all -> 0x009c }
    L_0x0014:
        if (r1 == 0) goto L_0x001f;
    L_0x0016:
        r0 = r1.isOpen();
        if (r0 == 0) goto L_0x001f;
    L_0x001c:
        r1.close();
    L_0x001f:
        return;
    L_0x0020:
        r3 = "insert into tb_tid (name, tid, key_tid, dt) values (?, ?, ?, datetime('now', 'localtime'))";	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r0 = r7.f2180c;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r0 = r0.get();	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r0 = (android.content.Context) r0;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r0 = com.alipay.sdk.util.C0873a.m3419c(r0);	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r4 = 1;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r0 = com.alipay.sdk.encrypt.C0852b.m3287a(r4, r10, r0);	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r4 = 3;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r4 = new java.lang.Object[r4];	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r5 = 0;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r6 = com.alipay.sdk.tid.C0871a.m3398c(r8, r9);	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r4[r5] = r6;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r5 = 1;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r4[r5] = r0;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r0 = 2;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r4[r0] = r11;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r1.execSQL(r3, r4);	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r0 = "select name from tb_tid where tid!='' order by dt asc";	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r3 = 0;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r3 = r1.rawQuery(r0, r3);	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r0 = r3.getCount();	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r4 = 14;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        if (r0 > r4) goto L_0x0066;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
    L_0x0055:
        r3.close();	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        goto L_0x0014;
    L_0x0059:
        r0 = move-exception;
        if (r1 == 0) goto L_0x001f;
    L_0x005c:
        r0 = r1.isOpen();
        if (r0 == 0) goto L_0x001f;
    L_0x0062:
        r1.close();
        goto L_0x001f;
    L_0x0066:
        r0 = r3.getCount();	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r4 = r0 + -14;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r5 = new java.lang.String[r4];	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r0 = r3.moveToFirst();	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        if (r0 == 0) goto L_0x0086;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
    L_0x0074:
        r0 = r2;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
    L_0x0075:
        r6 = 0;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r6 = r3.getString(r6);	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r5[r0] = r6;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r0 = r0 + 1;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r6 = r3.moveToNext();	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        if (r6 == 0) goto L_0x0086;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
    L_0x0084:
        if (r4 > r0) goto L_0x0075;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
    L_0x0086:
        r3.close();	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r0 = r2;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
    L_0x008a:
        if (r0 >= r4) goto L_0x0014;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
    L_0x008c:
        r2 = r5[r0];	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r2 = android.text.TextUtils.isEmpty(r2);	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        if (r2 != 0) goto L_0x0099;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
    L_0x0094:
        r2 = r5[r0];	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        com.alipay.sdk.tid.C0871a.m3395a(r1, r2);	 Catch:{ Exception -> 0x0059, all -> 0x009c }
    L_0x0099:
        r0 = r0 + 1;
        goto L_0x008a;
    L_0x009c:
        r0 = move-exception;
        if (r1 == 0) goto L_0x00a8;
    L_0x009f:
        r2 = r1.isOpen();
        if (r2 == 0) goto L_0x00a8;
    L_0x00a5:
        r1.close();
    L_0x00a8:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.tid.a.a(java.lang.String, java.lang.String, java.lang.String, java.lang.String):void");
    }

    public C0871a(Context context) {
        super(context, f2178a, null, 1);
        this.f2180c = new WeakReference(context);
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table if not exists tb_tid (name text primary key, tid text, key_tid text, dt datetime);");
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("drop table if exists tb_tid");
        onCreate(sQLiteDatabase);
    }

    /* renamed from: a */
    public final String m3401a(String str, String str2) {
        SQLiteDatabase readableDatabase;
        Throwable th;
        String str3 = null;
        String str4 = "select tid from tb_tid where name=?";
        Cursor rawQuery;
        try {
            readableDatabase = getReadableDatabase();
            try {
                rawQuery = readableDatabase.rawQuery(str4, new String[]{C0871a.m3398c(str, str2)});
                try {
                    if (rawQuery.moveToFirst()) {
                        str3 = rawQuery.getString(0);
                    }
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    if (readableDatabase == null || !readableDatabase.isOpen()) {
                        str4 = str3;
                    } else {
                        readableDatabase.close();
                        str4 = str3;
                    }
                } catch (Exception e) {
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    if (readableDatabase == null) {
                    }
                    str4 = null;
                    if (TextUtils.isEmpty(str4)) {
                        return str4;
                    }
                    return C0852b.m3287a(2, str4, C0873a.m3419c((Context) this.f2180c.get()));
                } catch (Throwable th2) {
                    th = th2;
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    readableDatabase.close();
                    throw th;
                }
            } catch (Exception e2) {
                rawQuery = null;
                if (rawQuery != null) {
                    rawQuery.close();
                }
                if (readableDatabase == null) {
                }
                str4 = null;
                if (TextUtils.isEmpty(str4)) {
                    return C0852b.m3287a(2, str4, C0873a.m3419c((Context) this.f2180c.get()));
                }
                return str4;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                rawQuery = null;
                th = th4;
                if (rawQuery != null) {
                    rawQuery.close();
                }
                readableDatabase.close();
                throw th;
            }
        } catch (Exception e3) {
            rawQuery = null;
            readableDatabase = null;
            if (rawQuery != null) {
                rawQuery.close();
            }
            if (readableDatabase == null && readableDatabase.isOpen()) {
                readableDatabase.close();
                str4 = null;
            } else {
                str4 = null;
            }
            if (TextUtils.isEmpty(str4)) {
                return str4;
            }
            return C0852b.m3287a(2, str4, C0873a.m3419c((Context) this.f2180c.get()));
        } catch (Throwable th32) {
            readableDatabase = null;
            th = th32;
            rawQuery = null;
            if (rawQuery != null) {
                rawQuery.close();
            }
            if (readableDatabase != null && readableDatabase.isOpen()) {
                readableDatabase.close();
            }
            throw th;
        }
        if (TextUtils.isEmpty(str4)) {
            return C0852b.m3287a(2, str4, C0873a.m3419c((Context) this.f2180c.get()));
        }
        return str4;
    }

    /* renamed from: e */
    private long m3400e(String str, String str2) {
        SQLiteDatabase readableDatabase;
        Throwable th;
        Cursor cursor = null;
        long j = 0;
        String str3 = "select dt from tb_tid where name=?";
        try {
            readableDatabase = getReadableDatabase();
            try {
                cursor = readableDatabase.rawQuery(str3, new String[]{C0871a.m3398c(str, str2)});
                if (cursor.moveToFirst()) {
                    j = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(cursor.getString(0)).getTime();
                }
                if (cursor != null) {
                    cursor.close();
                }
                if (readableDatabase != null && readableDatabase.isOpen()) {
                    readableDatabase.close();
                }
            } catch (Exception e) {
                if (cursor != null) {
                    cursor.close();
                }
                if (readableDatabase != null && readableDatabase.isOpen()) {
                    readableDatabase.close();
                }
                return j;
            } catch (Throwable th2) {
                th = th2;
                if (cursor != null) {
                    cursor.close();
                }
                if (readableDatabase != null && readableDatabase.isOpen()) {
                    readableDatabase.close();
                }
                throw th;
            }
        } catch (Exception e2) {
            readableDatabase = cursor;
            if (cursor != null) {
                cursor.close();
            }
            readableDatabase.close();
            return j;
        } catch (Throwable th3) {
            th = th3;
            readableDatabase = cursor;
            if (cursor != null) {
                cursor.close();
            }
            readableDatabase.close();
            throw th;
        }
        return j;
    }

    /* renamed from: a */
    private List<String> m3393a() {
        SQLiteDatabase readableDatabase;
        SQLiteDatabase sQLiteDatabase;
        Throwable th;
        Cursor cursor = null;
        List<String> arrayList = new ArrayList();
        Cursor rawQuery;
        try {
            readableDatabase = getReadableDatabase();
            try {
                rawQuery = readableDatabase.rawQuery("select tid from tb_tid", null);
                while (rawQuery.moveToNext()) {
                    try {
                        Object string = rawQuery.getString(0);
                        if (!TextUtils.isEmpty(string)) {
                            arrayList.add(C0852b.m3287a(2, string, C0873a.m3419c((Context) this.f2180c.get())));
                        }
                    } catch (Exception e) {
                        cursor = rawQuery;
                        sQLiteDatabase = readableDatabase;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
                if (rawQuery != null) {
                    rawQuery.close();
                }
                if (readableDatabase != null && readableDatabase.isOpen()) {
                    readableDatabase.close();
                }
            } catch (Exception e2) {
                sQLiteDatabase = readableDatabase;
                if (cursor != null) {
                    cursor.close();
                }
                if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                    sQLiteDatabase.close();
                }
                return arrayList;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                rawQuery = null;
                th = th4;
                if (rawQuery != null) {
                    rawQuery.close();
                }
                if (readableDatabase != null && readableDatabase.isOpen()) {
                    readableDatabase.close();
                }
                throw th;
            }
        } catch (Exception e3) {
            sQLiteDatabase = null;
            if (cursor != null) {
                cursor.close();
            }
            sQLiteDatabase.close();
            return arrayList;
        } catch (Throwable th32) {
            readableDatabase = null;
            th = th32;
            rawQuery = null;
            if (rawQuery != null) {
                rawQuery.close();
            }
            readableDatabase.close();
            throw th;
        }
        return arrayList;
    }

    /* renamed from: b */
    public final String m3404b(String str, String str2) {
        SQLiteDatabase readableDatabase;
        Throwable th;
        String str3 = null;
        String str4 = "select key_tid from tb_tid where name=?";
        Cursor rawQuery;
        try {
            readableDatabase = getReadableDatabase();
            try {
                rawQuery = readableDatabase.rawQuery(str4, new String[]{C0871a.m3398c(str, str2)});
                try {
                    if (rawQuery.moveToFirst()) {
                        str3 = rawQuery.getString(0);
                    }
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    if (readableDatabase != null && readableDatabase.isOpen()) {
                        readableDatabase.close();
                    }
                } catch (Exception e) {
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    readableDatabase.close();
                    return str3;
                } catch (Throwable th2) {
                    th = th2;
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    readableDatabase.close();
                    throw th;
                }
            } catch (Exception e2) {
                rawQuery = null;
                if (rawQuery != null) {
                    rawQuery.close();
                }
                readableDatabase.close();
                return str3;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                rawQuery = null;
                th = th4;
                if (rawQuery != null) {
                    rawQuery.close();
                }
                readableDatabase.close();
                throw th;
            }
        } catch (Exception e3) {
            rawQuery = null;
            readableDatabase = null;
            if (rawQuery != null) {
                rawQuery.close();
            }
            if (readableDatabase != null && readableDatabase.isOpen()) {
                readableDatabase.close();
            }
            return str3;
        } catch (Throwable th32) {
            readableDatabase = null;
            th = th32;
            rawQuery = null;
            if (rawQuery != null) {
                rawQuery.close();
            }
            if (readableDatabase != null && readableDatabase.isOpen()) {
                readableDatabase.close();
            }
            throw th;
        }
        return str3;
    }

    /* renamed from: a */
    private static boolean m3396a(SQLiteDatabase sQLiteDatabase, String str, String str2) {
        int i;
        Cursor cursor = null;
        try {
            int i2;
            cursor = sQLiteDatabase.rawQuery("select count(*) from tb_tid where name=?", new String[]{C0871a.m3398c(str, str2)});
            if (cursor.moveToFirst()) {
                i2 = cursor.getInt(0);
            } else {
                i2 = 0;
            }
            if (cursor != null) {
                cursor.close();
                i = i2;
            } else {
                i = i2;
            }
        } catch (Exception e) {
            if (cursor != null) {
                cursor.close();
                i = 0;
            } else {
                i = 0;
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
        }
        if (i > 0) {
            return true;
        }
        return false;
    }

    /* renamed from: c */
    static String m3398c(String str, String str2) {
        return str + str2;
    }

    /* renamed from: b */
    private void m3397b(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, String str4) {
        int i = 0;
        String a = C0852b.m3287a(1, str3, C0873a.m3419c((Context) this.f2180c.get()));
        sQLiteDatabase.execSQL("insert into tb_tid (name, tid, key_tid, dt) values (?, ?, ?, datetime('now', 'localtime'))", new Object[]{C0871a.m3398c(str, str2), a, str4});
        Cursor rawQuery = sQLiteDatabase.rawQuery("select name from tb_tid where tid!='' order by dt asc", null);
        if (rawQuery.getCount() <= 14) {
            rawQuery.close();
            return;
        }
        int count = rawQuery.getCount() - 14;
        String[] strArr = new String[count];
        if (rawQuery.moveToFirst()) {
            int i2 = 0;
            do {
                strArr[i2] = rawQuery.getString(0);
                i2++;
                if (!rawQuery.moveToNext()) {
                    break;
                }
            } while (count > i2);
        }
        rawQuery.close();
        while (i < count) {
            if (!TextUtils.isEmpty(strArr[i])) {
                C0871a.m3395a(sQLiteDatabase, strArr[i]);
            }
            i++;
        }
    }

    /* renamed from: a */
    final void m3402a(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, String str4) {
        sQLiteDatabase.execSQL("update tb_tid set tid=?, key_tid=?, dt=datetime('now', 'localtime') where name=?", new Object[]{C0852b.m3287a(1, str3, C0873a.m3419c((Context) this.f2180c.get())), str4, C0871a.m3398c(str, str2)});
    }

    /* renamed from: a */
    static void m3395a(SQLiteDatabase sQLiteDatabase, String str) {
        try {
            sQLiteDatabase.delete("tb_tid", "name=?", new String[]{str});
        } catch (Exception e) {
        }
    }

    /* renamed from: a */
    private static void m3394a(SQLiteDatabase sQLiteDatabase) {
        int i = 0;
        Cursor rawQuery = sQLiteDatabase.rawQuery("select name from tb_tid where tid!='' order by dt asc", null);
        if (rawQuery.getCount() <= 14) {
            rawQuery.close();
            return;
        }
        int count = rawQuery.getCount() - 14;
        String[] strArr = new String[count];
        if (rawQuery.moveToFirst()) {
            int i2 = 0;
            do {
                strArr[i2] = rawQuery.getString(0);
                i2++;
                if (!rawQuery.moveToNext()) {
                    break;
                }
            } while (count > i2);
        }
        rawQuery.close();
        while (i < count) {
            if (!TextUtils.isEmpty(strArr[i])) {
                C0871a.m3395a(sQLiteDatabase, strArr[i]);
            }
            i++;
        }
    }
}
