package com.tencent.bugly.proguard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.field.FieldType;
import com.tencent.bugly.C4401a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.o */
public final class C4464o {
    /* renamed from: a */
    private static C4464o f15709a = null;
    /* renamed from: b */
    private static C4465p f15710b = null;
    /* renamed from: c */
    private static boolean f15711c = false;

    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.proguard.o$a */
    class C4463a extends Thread {
        /* renamed from: a */
        private int f15691a;
        /* renamed from: b */
        private C4462n f15692b;
        /* renamed from: c */
        private String f15693c;
        /* renamed from: d */
        private ContentValues f15694d;
        /* renamed from: e */
        private boolean f15695e;
        /* renamed from: f */
        private String[] f15696f;
        /* renamed from: g */
        private String f15697g;
        /* renamed from: h */
        private String[] f15698h;
        /* renamed from: i */
        private String f15699i;
        /* renamed from: j */
        private String f15700j;
        /* renamed from: k */
        private String f15701k;
        /* renamed from: l */
        private String f15702l;
        /* renamed from: m */
        private String f15703m;
        /* renamed from: n */
        private String[] f15704n;
        /* renamed from: o */
        private int f15705o;
        /* renamed from: p */
        private String f15706p;
        /* renamed from: q */
        private byte[] f15707q;
        /* renamed from: r */
        private /* synthetic */ C4464o f15708r;

        public C4463a(C4464o c4464o, int i, C4462n c4462n) {
            this.f15708r = c4464o;
            this.f15691a = i;
            this.f15692b = c4462n;
        }

        /* renamed from: a */
        public final void m17665a(boolean z, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
            this.f15695e = z;
            this.f15693c = str;
            this.f15696f = strArr;
            this.f15697g = str2;
            this.f15698h = strArr2;
            this.f15699i = str3;
            this.f15700j = str4;
            this.f15701k = str5;
            this.f15702l = str6;
        }

        /* renamed from: a */
        public final void m17664a(int i, String str, byte[] bArr) {
            this.f15705o = i;
            this.f15706p = str;
            this.f15707q = bArr;
        }

        public final void run() {
            switch (this.f15691a) {
                case 1:
                    this.f15708r.m17669a(this.f15693c, this.f15694d, this.f15692b);
                    return;
                case 2:
                    this.f15708r.m17667a(this.f15693c, this.f15703m, this.f15704n, this.f15692b);
                    return;
                case 3:
                    this.f15708r.m17671a(this.f15695e, this.f15693c, this.f15696f, this.f15697g, this.f15698h, this.f15699i, this.f15700j, this.f15701k, this.f15702l, this.f15692b);
                    return;
                case 4:
                    this.f15708r.m17678a(this.f15705o, this.f15706p, this.f15707q, this.f15692b);
                    return;
                case 5:
                    this.f15708r.m17675a(this.f15705o, this.f15692b);
                    return;
                case 6:
                    this.f15708r.m17677a(this.f15705o, this.f15706p, this.f15692b);
                    return;
                default:
                    return;
            }
        }
    }

    private C4464o(Context context, List<C4401a> list) {
        f15710b = new C4465p(context, list);
    }

    /* renamed from: a */
    public static synchronized C4464o m17673a(Context context, List<C4401a> list) {
        C4464o c4464o;
        synchronized (C4464o.class) {
            if (f15709a == null) {
                f15709a = new C4464o(context, list);
            }
            c4464o = f15709a;
        }
        return c4464o;
    }

    /* renamed from: a */
    public static synchronized C4464o m17672a() {
        C4464o c4464o;
        synchronized (C4464o.class) {
            c4464o = f15709a;
        }
        return c4464o;
    }

    /* renamed from: a */
    public final long m17687a(String str, ContentValues contentValues, C4462n c4462n, boolean z) {
        return m17669a(str, contentValues, null);
    }

    /* renamed from: a */
    public final Cursor m17688a(String str, String[] strArr, String str2, String[] strArr2, C4462n c4462n, boolean z) {
        return m17671a(false, str, strArr, str2, null, null, null, null, null, null);
    }

    /* renamed from: a */
    public final int m17686a(String str, String str2, String[] strArr, C4462n c4462n, boolean z) {
        return m17667a(str, str2, null, null);
    }

    /* renamed from: a */
    private synchronized long m17669a(String str, ContentValues contentValues, C4462n c4462n) {
        long j = 0;
        synchronized (this) {
            try {
                SQLiteDatabase writableDatabase = f15710b.getWritableDatabase();
                if (!(writableDatabase == null || contentValues == null)) {
                    long replace = writableDatabase.replace(str, FieldType.FOREIGN_ID_FIELD_SUFFIX, contentValues);
                    if (replace >= 0) {
                        C4475w.m17751c("[Database] insert %s success.", str);
                    } else {
                        C4475w.m17752d("[Database] replace %s error.", str);
                    }
                    j = replace;
                }
                if (c4462n != null) {
                    Long.valueOf(j);
                }
            } catch (Throwable th) {
                if (c4462n != null) {
                    Long.valueOf(0);
                }
            }
        }
        return j;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    private synchronized android.database.Cursor m17671a(boolean r12, java.lang.String r13, java.lang.String[] r14, java.lang.String r15, java.lang.String[] r16, java.lang.String r17, java.lang.String r18, java.lang.String r19, java.lang.String r20, com.tencent.bugly.proguard.C4462n r21) {
        /*
        r11 = this;
        monitor-enter(r11);
        r10 = 0;
        r0 = f15710b;	 Catch:{ Throwable -> 0x0020 }
        r0 = r0.getWritableDatabase();	 Catch:{ Throwable -> 0x0020 }
        if (r0 == 0) goto L_0x0035;
    L_0x000a:
        r1 = r12;
        r2 = r13;
        r3 = r14;
        r4 = r15;
        r5 = r16;
        r6 = r17;
        r7 = r18;
        r8 = r19;
        r9 = r20;
        r0 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Throwable -> 0x0020 }
    L_0x001c:
        if (r21 == 0) goto L_0x001e;
    L_0x001e:
        monitor-exit(r11);
        return r0;
    L_0x0020:
        r0 = move-exception;
        r1 = com.tencent.bugly.proguard.C4475w.m17748a(r0);	 Catch:{ all -> 0x002e }
        if (r1 != 0) goto L_0x002a;
    L_0x0027:
        r0.printStackTrace();	 Catch:{ all -> 0x002e }
    L_0x002a:
        if (r21 == 0) goto L_0x0033;
    L_0x002c:
        r0 = r10;
        goto L_0x001e;
    L_0x002e:
        r0 = move-exception;
        throw r0;	 Catch:{ all -> 0x0030 }
    L_0x0030:
        r0 = move-exception;
        monitor-exit(r11);
        throw r0;
    L_0x0033:
        r0 = r10;
        goto L_0x001e;
    L_0x0035:
        r0 = r10;
        goto L_0x001c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.o.a(boolean, java.lang.String, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.tencent.bugly.proguard.n):android.database.Cursor");
    }

    /* renamed from: a */
    private synchronized int m17667a(String str, String str2, String[] strArr, C4462n c4462n) {
        int i = 0;
        synchronized (this) {
            try {
                SQLiteDatabase writableDatabase = f15710b.getWritableDatabase();
                if (writableDatabase != null) {
                    i = writableDatabase.delete(str, str2, strArr);
                }
                if (c4462n != null) {
                    Integer.valueOf(i);
                }
            } catch (Throwable th) {
                if (c4462n != null) {
                    Integer.valueOf(0);
                }
            }
        }
        return i;
    }

    /* renamed from: a */
    public final boolean m17693a(int i, String str, byte[] bArr, C4462n c4462n, boolean z) {
        if (z) {
            return m17678a(i, str, bArr, null);
        }
        Runnable c4463a = new C4463a(this, 4, null);
        c4463a.m17664a(i, str, bArr);
        C4474v.m17740a().m17741a(c4463a);
        return true;
    }

    /* renamed from: a */
    public final Map<String, byte[]> m17690a(int i, C4462n c4462n, boolean z) {
        return m17675a(i, null);
    }

    /* renamed from: a */
    public final boolean m17692a(int i, String str, C4462n c4462n, boolean z) {
        return m17677a(555, str, null);
    }

    /* renamed from: a */
    private boolean m17678a(int i, String str, byte[] bArr, C4462n c4462n) {
        boolean z = false;
        try {
            C4466q c4466q = new C4466q();
            c4466q.f15715a = (long) i;
            c4466q.f15720f = str;
            c4466q.f15719e = System.currentTimeMillis();
            c4466q.f15721g = bArr;
            z = m17682b(c4466q);
            if (c4462n != null) {
                Boolean.valueOf(z);
            }
        } catch (Throwable th) {
            if (c4462n != null) {
                Boolean.valueOf(z);
            }
        }
        return z;
    }

    /* renamed from: a */
    private Map<String, byte[]> m17675a(int i, C4462n c4462n) {
        Map<String, byte[]> map;
        Throwable th;
        try {
            List<C4466q> c = m17684c(i);
            Map<String, byte[]> hashMap = new HashMap();
            try {
                for (C4466q c4466q : c) {
                    Object obj = c4466q.f15721g;
                    if (obj != null) {
                        hashMap.put(c4466q.f15720f, obj);
                    }
                }
                if (c4462n != null) {
                    return hashMap;
                }
                return hashMap;
            } catch (Throwable th2) {
                Throwable th3 = th2;
                map = hashMap;
                th = th3;
                if (!C4475w.m17748a(th)) {
                    th.printStackTrace();
                }
                return c4462n == null ? map : map;
            }
        } catch (Throwable th22) {
            th = th22;
            map = null;
            if (C4475w.m17748a(th)) {
                th.printStackTrace();
            }
            if (c4462n == null) {
            }
        }
    }

    /* renamed from: a */
    public final synchronized boolean m17694a(C4466q c4466q) {
        boolean z = false;
        synchronized (this) {
            if (c4466q != null) {
                try {
                    SQLiteDatabase writableDatabase = f15710b.getWritableDatabase();
                    if (writableDatabase != null) {
                        ContentValues c = C4464o.m17683c(c4466q);
                        if (c != null) {
                            long replace = writableDatabase.replace("t_lr", FieldType.FOREIGN_ID_FIELD_SUFFIX, c);
                            if (replace >= 0) {
                                C4475w.m17751c("[Database] insert %s success.", "t_lr");
                                c4466q.f15715a = replace;
                                z = true;
                            }
                        }
                    }
                } catch (Throwable th) {
                    if (!C4475w.m17748a(th)) {
                        th.printStackTrace();
                    }
                }
            }
        }
        return z;
    }

    /* renamed from: b */
    private synchronized boolean m17682b(C4466q c4466q) {
        boolean z = false;
        synchronized (this) {
            if (c4466q != null) {
                try {
                    SQLiteDatabase writableDatabase = f15710b.getWritableDatabase();
                    if (writableDatabase != null) {
                        ContentValues d = C4464o.m17685d(c4466q);
                        if (d != null) {
                            long replace = writableDatabase.replace("t_pf", FieldType.FOREIGN_ID_FIELD_SUFFIX, d);
                            if (replace >= 0) {
                                C4475w.m17751c("[Database] insert %s success.", "t_pf");
                                c4466q.f15715a = replace;
                                z = true;
                            }
                        }
                    }
                } catch (Throwable th) {
                    if (!C4475w.m17748a(th)) {
                        th.printStackTrace();
                    }
                }
            }
        }
        return z;
    }

    /* renamed from: a */
    public final synchronized List<C4466q> m17689a(int i) {
        Throwable th;
        Cursor cursor;
        List<C4466q> list;
        SQLiteDatabase writableDatabase = f15710b.getWritableDatabase();
        if (writableDatabase != null) {
            String str;
            Cursor cursor2;
            if (i >= 0) {
                try {
                    str = "_tp = " + i;
                } catch (Throwable th2) {
                    th = th2;
                    cursor2 = null;
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    throw th;
                }
            }
            str = null;
            cursor2 = writableDatabase.query("t_lr", null, str, null, null, null, null);
            if (cursor2 == null) {
                if (cursor2 != null) {
                    cursor2.close();
                }
                list = null;
            } else {
                try {
                    StringBuilder stringBuilder = new StringBuilder();
                    List<C4466q> arrayList = new ArrayList();
                    while (cursor2.moveToNext()) {
                        C4466q a = C4464o.m17674a(cursor2);
                        if (a != null) {
                            arrayList.add(a);
                        } else {
                            try {
                                stringBuilder.append(" or _id").append(" = ").append(cursor2.getLong(cursor2.getColumnIndex(FieldType.FOREIGN_ID_FIELD_SUFFIX)));
                            } catch (Throwable th3) {
                                th = th3;
                            }
                        }
                    }
                    str = stringBuilder.toString();
                    if (str.length() > 0) {
                        int delete = writableDatabase.delete("t_lr", str.substring(4), null);
                        C4475w.m17752d("[Database] deleted %s illegal data %d", "t_lr", Integer.valueOf(delete));
                    }
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    list = arrayList;
                } catch (Throwable th32) {
                    th = th32;
                }
            }
        }
        list = null;
        return list;
    }

    /* renamed from: a */
    public final synchronized void m17691a(List<C4466q> list) {
        if (list != null) {
            if (list.size() != 0) {
                SQLiteDatabase writableDatabase = f15710b.getWritableDatabase();
                if (writableDatabase != null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (C4466q c4466q : list) {
                        stringBuilder.append(" or _id").append(" = ").append(c4466q.f15715a);
                    }
                    String stringBuilder2 = stringBuilder.toString();
                    if (stringBuilder2.length() > 0) {
                        stringBuilder2 = stringBuilder2.substring(4);
                    }
                    stringBuilder.setLength(0);
                    try {
                        int delete = writableDatabase.delete("t_lr", stringBuilder2, null);
                        C4475w.m17751c("[Database] deleted %s data %d", "t_lr", Integer.valueOf(delete));
                    } catch (Throwable th) {
                        if (!C4475w.m17748a(th)) {
                            th.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    /* renamed from: b */
    public final synchronized void m17695b(int i) {
        String str = null;
        synchronized (this) {
            SQLiteDatabase writableDatabase = f15710b.getWritableDatabase();
            if (writableDatabase != null) {
                if (i >= 0) {
                    try {
                        str = "_tp = " + i;
                    } catch (Throwable th) {
                        if (!C4475w.m17748a(th)) {
                            th.printStackTrace();
                        }
                    }
                }
                int delete = writableDatabase.delete("t_lr", str, null);
                C4475w.m17751c("[Database] deleted %s data %d", "t_lr", Integer.valueOf(delete));
            }
        }
    }

    /* renamed from: c */
    private static ContentValues m17683c(C4466q c4466q) {
        if (c4466q == null) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (c4466q.f15715a > 0) {
                contentValues.put(FieldType.FOREIGN_ID_FIELD_SUFFIX, Long.valueOf(c4466q.f15715a));
            }
            contentValues.put("_tp", Integer.valueOf(c4466q.f15716b));
            contentValues.put("_pc", c4466q.f15717c);
            contentValues.put("_th", c4466q.f15718d);
            contentValues.put("_tm", Long.valueOf(c4466q.f15719e));
            if (c4466q.f15721g != null) {
                contentValues.put("_dt", c4466q.f15721g);
            }
            return contentValues;
        } catch (Throwable th) {
            if (C4475w.m17748a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    private static C4466q m17674a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            C4466q c4466q = new C4466q();
            c4466q.f15715a = cursor.getLong(cursor.getColumnIndex(FieldType.FOREIGN_ID_FIELD_SUFFIX));
            c4466q.f15716b = cursor.getInt(cursor.getColumnIndex("_tp"));
            c4466q.f15717c = cursor.getString(cursor.getColumnIndex("_pc"));
            c4466q.f15718d = cursor.getString(cursor.getColumnIndex("_th"));
            c4466q.f15719e = cursor.getLong(cursor.getColumnIndex("_tm"));
            c4466q.f15721g = cursor.getBlob(cursor.getColumnIndex("_dt"));
            return c4466q;
        } catch (Throwable th) {
            if (C4475w.m17748a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: c */
    private synchronized List<C4466q> m17684c(int i) {
        List<C4466q> list;
        Throwable th;
        Cursor cursor;
        Cursor query;
        try {
            SQLiteDatabase writableDatabase = f15710b.getWritableDatabase();
            if (writableDatabase != null) {
                String str = "_id = " + i;
                query = writableDatabase.query("t_pf", null, str, null, null, null, null);
                if (query == null) {
                    if (query != null) {
                        query.close();
                    }
                    list = null;
                } else {
                    try {
                        StringBuilder stringBuilder = new StringBuilder();
                        List<C4466q> arrayList = new ArrayList();
                        while (query.moveToNext()) {
                            C4466q b = C4464o.m17681b(query);
                            if (b != null) {
                                arrayList.add(b);
                            } else {
                                try {
                                    stringBuilder.append(" or _tp").append(" = ").append(query.getString(query.getColumnIndex("_tp")));
                                } catch (Throwable th2) {
                                    th = th2;
                                }
                            }
                        }
                        if (stringBuilder.length() > 0) {
                            stringBuilder.append(" and _id").append(" = ").append(i);
                            int delete = writableDatabase.delete("t_pf", str.substring(4), null);
                            C4475w.m17752d("[Database] deleted %s illegal data %d.", "t_pf", Integer.valueOf(delete));
                        }
                        if (query != null) {
                            query.close();
                        }
                        list = arrayList;
                    } catch (Throwable th22) {
                        th = th22;
                    }
                }
            }
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
        list = null;
        return list;
    }

    /* renamed from: a */
    private synchronized boolean m17677a(int i, String str, C4462n c4462n) {
        boolean z = true;
        boolean z2 = false;
        synchronized (this) {
            try {
                SQLiteDatabase writableDatabase = f15710b.getWritableDatabase();
                if (writableDatabase != null) {
                    String str2;
                    if (C4479y.m17792a(str)) {
                        str2 = "_id = " + i;
                    } else {
                        str2 = "_id = " + i + " and _tp" + " = \"" + str + "\"";
                    }
                    C4475w.m17751c("[Database] deleted %s data %d", "t_pf", Integer.valueOf(writableDatabase.delete("t_pf", str2, null)));
                    if (writableDatabase.delete("t_pf", str2, null) <= 0) {
                        z = false;
                    }
                    z2 = z;
                }
                if (c4462n != null) {
                    Boolean.valueOf(z2);
                }
            } catch (Throwable th) {
                if (c4462n != null) {
                    Boolean.valueOf(false);
                }
            }
        }
        return z2;
    }

    /* renamed from: d */
    private static ContentValues m17685d(C4466q c4466q) {
        if (c4466q == null || C4479y.m17792a(c4466q.f15720f)) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (c4466q.f15715a > 0) {
                contentValues.put(FieldType.FOREIGN_ID_FIELD_SUFFIX, Long.valueOf(c4466q.f15715a));
            }
            contentValues.put("_tp", c4466q.f15720f);
            contentValues.put("_tm", Long.valueOf(c4466q.f15719e));
            if (c4466q.f15721g == null) {
                return contentValues;
            }
            contentValues.put("_dt", c4466q.f15721g);
            return contentValues;
        } catch (Throwable th) {
            if (!C4475w.m17748a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: b */
    private static C4466q m17681b(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            C4466q c4466q = new C4466q();
            c4466q.f15715a = cursor.getLong(cursor.getColumnIndex(FieldType.FOREIGN_ID_FIELD_SUFFIX));
            c4466q.f15719e = cursor.getLong(cursor.getColumnIndex("_tm"));
            c4466q.f15720f = cursor.getString(cursor.getColumnIndex("_tp"));
            c4466q.f15721g = cursor.getBlob(cursor.getColumnIndex("_dt"));
            return c4466q;
        } catch (Throwable th) {
            if (C4475w.m17748a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }
}
